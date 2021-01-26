package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyTest;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

/**
 * Allergy API used to persist Allergy entity.
 * 
 * @author LTrevino
 *
 */
public class AllergyApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(AllergyApi.class);
	
	  Map <String, Long> drgPK = new HashMap<String, Long>();
	   Map <String, Timestamp> drgTime = new HashMap<String, Timestamp>();
	
	private final static String INSERTSQL = 
	"insert into PTNTALRGY (" +
			"   ALRGYACTFLG,			INTOLERANCEFLG,	CNSMRID,		CRTTIMESTAMP,	DESCRENG, "				+
			"   PRDCRID,				DESCRFR,		ALRGYSTRTDT,	RPTDT,			UPDTTIMESTAMP, "		+
			"   UPDTRSN,				SVCLOCKEY,		NONDRGALLRGN,	RXID,			RCRDRKEY, "				+
			"   RPTRKEY,				UPDTRCRDR,		SPRVSRKEY,		ALLRGNTYPKEY,	ALRGYCONFSTATTYPKEY, "	+
			"   ALRGYSVRTYLVLTYPKEY,	ALRGYTYPKEY,	DRGKEY,			LOCKEY,			PTNTKEY, "				+
			"	PTNTALRGYKEY"	+
	") values (" +
		" ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() + 
						", " + CommonUtil.getPsToDateFunctionStr()  + ", " + CommonUtil.getPsToDateFunctionStr() + 
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?" +
	")";

	private final static String UPDATESQL =
	"update PTNTALRGY set " +
	"	ALRGYACTFLG=?,			INTOLERANCEFLG=?,	CNSMRID=?,		CRTTIMESTAMP="+ CommonUtil.getPsToDateFunctionStr() +",	DESCRENG=?,	"	+
	"	PRDCRID=?,				DESCRFR=?, ALRGYSTRTDT="+ CommonUtil.getPsToDateFunctionStr() + ", " +
	" RPTDT=" + CommonUtil.getPsToDateFunctionStr() + ", UPDTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ", " +
	"	UPDTRSN=?,				SVCLOCKEY=?,		NONDRGALLRGN=?,	RXID=?,			RCRDRKEY=?, "				+
	"	RPTRKEY=?,				UPDTRCRDR=?,		SPRVSRKEY=?,	ALLRGNTYPKEY=?,	ALRGYCONFSTATTYPKEY=?, "	+
	"	ALRGYSVRTYLVLTYPKEY=?,	ALRGYTYPKEY=?,		DRGKEY=?,		LOCKEY=?,		PTNTKEY=?"					+
	" where PTNTALRGYKEY=?";
	
	/**
	 * Attempt to persist a Allergy instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Allergy doesn't exist, then insert a new record.
	 * If the Allergy exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergy		Allergy instance.
	 * 
	 * @return				AllergyKey value.
	 * @throws SQLException
	 * @throws NamingException 
	 * @throws IOException 
	 * @throws CDRException 
	 */
	public Long upsertAllergy(Connection connection, String patientConsumerId, String storeNumber, Allergy allergy, String rxId) throws NamingException, IOException, CDRException, SQLException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertAllergy. patient Id: " + patientConsumerId + ", store number " + storeNumber + ", rxId : " + rxId);}
			
			Store store = new Store();
			store.setStorenumber(storeNumber);
			
			Long patientKey = FindUtil.findPatientKey(connection, patientConsumerId, storeNumber);
			Map<String, Object> mapData = FindUtil.findAllergyData( connection,  patientKey , allergy.getConsumerId());
			Long allergyKey = (Long) mapData.get("PTNTALRGYKEY");
			Timestamp updateTimestampDb = (Timestamp) mapData.get("UPDTTIMESTAMP");
			boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, allergy.getUpdateTimestamp());
			if (isUpdateRequestNew == false) 
			{
				if(logger.isDebugEnabled()) {logger.debug("Allergy lastUpdatedtimeStamp : " +  allergy.getUpdateTimestamp() + " is less than DB last updated : " + updateTimestampDb);}
				return allergyKey;
			}
			
			String locationConsumerId = (allergy.getServiceLocation() != null)
					? allergy.getServiceLocation().getConsumerId() : null;
			Long locationKey;
			locationKey = FindUtil.findLocationKey(connection, locationConsumerId, storeNumber);

			Long drugKey = null;
			Drug drug = allergy.getDrug();
			if (drug != null) {
				DrugApi drugApi = new DrugApi();
				drugKey = drugApi.upsertDrug(connection, storeNumber, drug,drgPK , drgTime);
			}

			Long updateRecorderKey = null;
			Recorder updateRecorder = allergy.getUpdateRecorder();
			if (updateRecorder != null) {
				PersonRole personRole = new PersonRole(updateRecorder);
				PersonApi personApi = new PersonApi();
				updateRecorderKey = personApi.upsertPerson(connection, store, personRole);
			}

			Long recorderKey = null;
			Recorder recorder = allergy.getRecorder();
			if (recorder != null) {
				PersonRole personRole = new PersonRole(recorder);
				PersonApi personApi = new PersonApi();
				recorderKey = personApi.upsertPerson(connection, store, personRole);
			}

			Long supervisorKey = null;
			Supervisor supervisor = allergy.getSupervisor();
			if (supervisor != null) {
				PersonRole personRole = new PersonRole(supervisor);
				PersonApi personApi = new PersonApi();
				supervisorKey = personApi.upsertPerson(connection, store, personRole);
			}


			if (allergyKey == null) {
				allergyKey = insertAllergy(connection, storeNumber, allergy, patientKey, locationKey, drugKey, rxId, recorderKey, null,
						updateRecorderKey, supervisorKey);
			} else {
				// PTNTALRGY table doesn't have an Update Timestamp field.
				updateAllergy(connection, storeNumber, allergyKey, allergy, patientKey, locationKey, drugKey, rxId, recorderKey, null,
						updateRecorderKey, supervisorKey);
			}

			List<AllergyTest> allergyTests = allergy.getAllergyTest();
			if (allergyTests != null && allergyTests.size() > 0) {
				AllergyTestApi allergyTestApi = new AllergyTestApi();
				for (AllergyTest allergyTest : allergyTests) {
					allergyTestApi.upsertAllergyTest(connection, storeNumber, allergyTest, allergyKey);
				}
			}

			List<Reaction> reactions = allergy.getReaction();
			if (reactions != null && reactions.size() > 0) {
				ReactionApi reactionApi = new ReactionApi();
				for (Reaction reaction : reactions) {
					reactionApi.upsertReactionForAllergy(connection, storeNumber, reaction, allergyKey);
				}
			}

			List<Note> notes = allergy.getNote();
			if (notes != null && notes.size() > 0) {
				NoteApi noteApi = new NoteApi(NoteTypeTable.PATIENT_ALLERGY_NOTE);
				noteApi.upsertNoteList(connection, store, notes, allergyKey);
			}

			Dispenser dispenser = allergy.getDispenser();
			if (dispenser != null) {
				PersonApi personApi = new PersonApi();
				PersonRole personRole = new PersonRole(dispenser);
				personApi.upsertPerson(connection, store, personRole);
			}

			return allergyKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertAllergy. patient Id: " + patientConsumerId + ", store number " + storeNumber + ", rxId : " + rxId);}
		}
	}
	
	/**
	 * Attempt to update a Allergy instance, which is matched by allergyKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergyKey	Allergy Key.
	 * @param allergy		Allergy instance.
	 * 
	 * @return				AllergyKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long updateAllergy(Connection connection, String storeNum, Long allergyKey, Allergy allergy, Long patientKey, Long locationKey, Long drugKey 
			, String rxId, Long recorderKey, Long reporterKey, Long updateRecorderKey, Long supervisorKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, allergyKey, allergy, patientKey, locationKey, drugKey, rxId, recorderKey, reporterKey, updateRecorderKey, supervisorKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Allergy intances updated: " + res + ". AllergyKey: " + allergyKey + ", consumerId: '" + allergy.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return allergyKey;
	}

	/**
	 * Attempt to insert a new Allergy into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergy			Allergy instance.
	 * 
	 * @return				AllergyKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long insertAllergy(Connection connection, String storeNum, Allergy allergy, Long patientKey, Long locationKey, Long drugKey 
			, String rxId, Long recorderKey, Long reporterKey, Long updateRecorderKey, Long supervisorKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long allergyKey = IdGenerator.generate(connection, "PTNTALRGY");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, allergyKey, allergy, patientKey, locationKey, drugKey, rxId, recorderKey, reporterKey, updateRecorderKey, supervisorKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Allergy intances updated: " + res + ". AllergyKey: " + allergyKey + ", consumerId: '" + allergy.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return allergyKey;
	}
	
	
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergyKey		Allergy Key.
	 * @param allergy			Allergy instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long allergyKey, Allergy allergy, Long patientKey, Long locationKey, Long drugKey 
			, String rxId, Long recorderKey, Long reporterKey, Long updateRecorderKey, Long supervisorKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		setPsStringParam(1, CommonUtil.convertBooleanToYesNoFlag(allergy.isAllergyActiveFlag()));
		setPsStringParam(2, CommonUtil.convertBooleanToYesNoFlag(allergy.isIntoleranceFlag()));
		setPsStringParam(3, allergy.getConsumerId());
		//setPsStringParam(4, CommonUtil.toTimestampStr(allergy.getCreateTimestamp())); 
		setPsXMLGregorianCalendarParam(4, allergy.getCreateTimestamp());
		setPsStringParam(5, allergy.getDescriptionEnglish());
		setPsStringParam(6, allergy.getProducerId());
		setPsStringParam(7, allergy.getDescriptionFrench());
//		setPsStringParam(8,  CommonUtil.toTimestampStr(allergy.getAllergyStartDate()));
		setPsXMLGregorianCalendarParam(8, allergy.getAllergyStartDate());

//		setPsStringParam(9,  CommonUtil.toTimestampStr(allergy.getReportedDate()));
		setPsXMLGregorianCalendarParam(9, allergy.getReportedDate());
//		setPsStringParam(10, CommonUtil.toTimestampStr(allergy.getUpdateTimestamp()));
		setPsXMLGregorianCalendarParam(10, allergy.getUpdateTimestamp());
		setPsStringParam(11, allergy.getUpdateReason());
		setPsLongParam(12, locationKey);
		String nonDrugAllergen = (allergy.getNonDrugAllergen()!=null) ? allergy.getNonDrugAllergen().value() : null;
		setPsStringParam(13, nonDrugAllergen);
		
		setPsStringParam(14, rxId);
		setPsLongParam(15, recorderKey);
		setPsLongParam(16, reporterKey);
		setPsLongParam(17, updateRecorderKey);
		setPsLongParam(18, supervisorKey);
		
		String allergenTypeCode = null; // ? --> No mapping or field provided
		// select ALLRGNTYP.* from ALLRGNTYP where ALLRGNTYPCD='[allergenTypeCode]'
		Long allergenTypeKey = getKeyFromCode(LT_ALLRGNTYP, allergenTypeCode);
		setPsLongParam(19, allergenTypeKey);
		
		String allergyConfirmationStatusTypeCode = (allergy.getAllergyConfirmationStatusCode()!=null) ? allergy.getAllergyConfirmationStatusCode().value() : null;
		// select ALRGYCONFSTATTYP.* from ALRGYCONFSTATTYP where ALRGYCONFSTATTYPCD='[allergyConfirmationStatusCode]'
		Long allergyConfirmationStatusTypeKey = getKeyFromCode(LT_ALRGYCONFSTATTYP, allergyConfirmationStatusTypeCode);
		setPsLongParam(20, allergyConfirmationStatusTypeKey);
		
		String allergySeverityCode = (allergy.getAllergySeverityCode()!=null) ? allergy.getAllergySeverityCode().value() : null;
		//select ALRGYSVRTYLVLTYP.* from ALRGYSVRTYLVLTYP where ALRGYSVRTYTYPCD ='[]'
		Long allergySeverityKey = getKeyFromCode(LT_ALRGYSVRTYLVLTYP, allergySeverityCode);
		setPsLongParam(21, allergySeverityKey);
		
		String allergyTypeCode = allergy.getAllergyType();
		// select * from ALRGYTYP where CDDESCRIPTOIN='[allergyTypeCode]'
		Long allergyTypeKey = getKeyFromCode(LT_ALRGYTYP, allergyTypeCode);
		
		setPsLongParam(22, allergyTypeKey);
		
		setPsLongParam(23, drugKey);
		setPsLongParam(24, locationKey);
		setPsLongParam(25, patientKey);
		setPsLongParam(26, allergyKey);
	}
}
