package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.InvalidInputException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

/**
 * AdverseDrugReaction API used to persist AdverseDrugReaction entity.
 * 
 * @author LTrevino
 *
 */
public class AdverseDrugReactionApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(AdverseDrugReactionApi.class);
	private final static String UPDATESQL =
	"update PTNTADVRSDRGRCTN set " +
	"	CNSMRID=?,	PRDCRID=?,	CRTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ", UPDTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ", DESCRENG=?, " +
	"	OCCRSTRTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ", UPDTRCRDR=?,	RCRDR=?,	SPRVSR=?, RPTR=?, " +
	"	SVCLOCKEY=?,	PTNTKEY=?"	+
	" where PTNTADVRSDRGRCTNKEY=?";
	
	private final static String INSERTSQL = 
	"insert into PTNTADVRSDRGRCTN (" +
		"CNSMRID,			PRDCRID,	CRTTIMESTAMP,	UPDTTIMESTAMP,	DESCRENG, "	+
		"OCCRSTRTTIMESTAMP,	UPDTRCRDR,	RCRDR,			SPRVSR,			RPTR, "		+
		"SVCLOCKEY,			PTNTKEY,	PTNTADVRSDRGRCTNKEY"	+
	") values (" +
		"  ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", " + CommonUtil.getPsToDateFunctionStr() + ", ?" + 
		", " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, ?" +
		", ?, ?, ?" +
	")";

	/**
	 * Attempt to persist a AdverseDrugReaction instance, which is searched by consumerId and storeNum.
	 * 
	 * If the AdverseDrugReaction doesn't exist, then insert a new record.
	 * If the AdverseDrugReaction exists, then update its data.
	 * 
	 * @param consumerId			Consumer ID.
	 * @param storeNum				Store Number.
	 * @param adverseDrugReaction	AdverseDrugReaction instance.
	 * 
	 * @return						AdverseDrugReactionKey value.
	 * 
	 * @throws SQLException
	 * @throws NamingException 
	 * @throws IOException 
	 * @throws CDRException 
	 */
	
	public Long upsertAdverseDrugReaction(Connection connection, Long patientKey, String storeNumber, AdverseDrugReaction adverseDrugReaction)
			throws SQLException, NamingException, IOException, CDRException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertAdverseDrugReaction. patientKey: " + patientKey + ", store number " + storeNumber);}
			Store store = new Store();
			store.setStorenumber(storeNumber);
			
			
			 
			Map<String, Object> adverseDrugReactionData = FindUtil.findAdverseDrugReactionData(connection,
					adverseDrugReaction.getConsumerId(), patientKey, storeNumber);
			Long adverseDrugReactionKey = (Long) adverseDrugReactionData.get("PTNTADVRSDRGRCTNKEY");
			Timestamp updateTimestampDb = (Timestamp) adverseDrugReactionData.get("UPDTTIMESTAMP");
			
			boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, adverseDrugReaction.getUpdateTimestamp());
			if (isUpdateRequestNew == false) 
			{
				if(logger.isDebugEnabled()) {logger.debug("AdverseDrugReaction lastUpdatedtimeStamp : " +  adverseDrugReaction.getUpdateTimestamp() + " is less than DB last updated : " + updateTimestampDb);}
				return adverseDrugReactionKey;
			}

			String locationConsumerId = (adverseDrugReaction.getServiceLocation() != null)
					? adverseDrugReaction.getServiceLocation().getConsumerId() : null;
					
					
			Long locationKey;
			locationKey = FindUtil.findLocationKey(connection, locationConsumerId, storeNumber);
			// Long patientKey = FindUtil.findPatientKey(connection,
			// patientConsumerId, storeNumber);

			Long recorderKey = null;
			Recorder recorder = adverseDrugReaction.getRecorder();
			if (recorder != null) {
				PersonRole personRole = new PersonRole(recorder);
				PersonApi personApi = new PersonApi();
				recorderKey = personApi.upsertPerson(connection, store, personRole);
			}

			Long supervisorKey = null;
			Supervisor supervisor = adverseDrugReaction.getSupervisor();
			if (supervisor != null) {
				PersonRole personRole = new PersonRole(supervisor);
				PersonApi personApi = new PersonApi();
				supervisorKey = personApi.upsertPerson(connection, store, personRole);
			}

			Long reporterKey = null;
			Reporter reporter = adverseDrugReaction.getReporter();
			if (reporter != null) {
				PersonRole personRole = new PersonRole(reporter);
				PersonApi personApi = new PersonApi();
				reporterKey = personApi.upsertPerson(connection, store, personRole);
			}

			Long updateRecorderKey = null;
			Recorder updateRecorder = adverseDrugReaction.getUpdateRecorder();
			if (updateRecorder != null) {
				PersonRole personRole = new PersonRole(updateRecorder);
				PersonApi personApi = new PersonApi();
				updateRecorderKey = personApi.upsertPerson(connection, store, personRole);
			}

			if (adverseDrugReactionKey == null) {
				adverseDrugReactionKey = insertAdverseDrugReaction(connection, storeNumber, adverseDrugReaction, patientKey,
						locationKey, updateRecorderKey, recorderKey, supervisorKey, reporterKey);
			} else {
				// PTNTADVRSDRGRCTN table doesn't have an Update Timestamp
				// field.
				updateAdverseDrugReaction(connection, storeNumber, adverseDrugReactionKey, adverseDrugReaction, patientKey,
						locationKey, updateRecorderKey, recorderKey, supervisorKey, reporterKey);
			}

			Reaction reaction = adverseDrugReaction.getReaction();
			if (reaction != null) {
				ReactionApi reactionApi = new ReactionApi();
				reactionApi.upsertReactionForAdverseDrugReaction(connection, storeNumber, reaction, adverseDrugReactionKey);
			}

			List<Note> notes = adverseDrugReaction.getNote();
			if (notes != null) {
				NoteApi noteApi = new NoteApi(NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE);
				noteApi.upsertNoteList(connection, store, notes, adverseDrugReactionKey);
			}

			Prescriber prescriber = adverseDrugReaction.getPrescriber();
			if (prescriber != null) {
				PersonRole personRole = new PersonRole(prescriber);
				PersonApi personApi = new PersonApi();
				personApi.upsertPerson(connection, store, personRole);
			}

			return adverseDrugReactionKey;
		} catch (SQLException e) {
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
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertAdverseDrugReaction. patientKey: " + patientKey + ", store number " + storeNumber);}
		}
	}	
	
	/**
	 * Attempt to update a AdverseDrugReaction instance, which is matched by adverseDrugReactionKey.
	 * 
	 * @param consumerId				Consumer ID.
	 * @param storeNumber					Store Number.
	 * @param adverseDrugReactionKey	AdverseDrugReaction Key.
	 * @param adverseDrugReaction		AdverseDrugReaction instance.
	 * 
	 * @return							AdverseDrugReactionKey value.
	 * @throws InvalidInputException 
	 * 
	 * @throws SQLException
	 */
	private Long updateAdverseDrugReaction(Connection connection, String storeNumber, Long adverseDrugReactionKey, AdverseDrugReaction adverseDrugReaction, Long patientKey,
			Long locationKey, Long updateRecorderKey, Long recorderKey, Long supervisorKey, Long reporterKey) throws InvalidInputException, SQLException  {
		if (storeNumber==null || storeNumber.trim().length() < 1) {
			throw new InvalidInputException("storeNumber is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNumber, adverseDrugReactionKey, adverseDrugReaction, patientKey, locationKey, updateRecorderKey, recorderKey, supervisorKey, reporterKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total AdverseDrugReaction intances updated: " + res + ". AdverseDrugReactionKey: " + adverseDrugReactionKey + ", consumerId: '" + adverseDrugReaction.getConsumerId() + "', storeNumber: '" + storeNumber + "'.");}
		return adverseDrugReactionKey;
	}

	
	
	/**
	 * Attempt to insert a new AdverseDrugReaction into corresponding database table.
	 * 
	 * @param consumerId			Consumer ID.
	 * @param storeNum				Store Number.
	 * @param adverseDrugReaction	AdverseDrugReaction instance.
	 * 
	 * @return						AdverseDrugReactionKey.
	 * 
	 * @throws SQLException
	 * @throws InvalidInputException 
	 */
	private Long insertAdverseDrugReaction(Connection connection, String storeNum, AdverseDrugReaction adverseDrugReaction, Long patientKey,
			Long locationKey, Long updateRecorderKey, Long recorderKey, Long supervisorKey, Long reporterKey) throws SQLException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long adverseDrugReactionKey = IdGenerator.generate(connection, "PTNTADVRSDRGRCTN");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, adverseDrugReactionKey, adverseDrugReaction, patientKey, locationKey, updateRecorderKey, recorderKey, supervisorKey, reporterKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total AdverseDrugReaction intances updated: " + res + ". AdverseDrugReactionKey: " + adverseDrugReactionKey + ", consumerId: '" + adverseDrugReaction.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return adverseDrugReactionKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param adverseDrugReactionKey		AdverseDrugReaction Key.
	 * @param adverseDrugReaction			AdverseDrugReaction instance.
	 * 
	 * @throws SQLException
	 */
	private void setPsParams(String storeNum, Long adverseDrugReactionKey, AdverseDrugReaction adverseDrugReaction, Long patientKey, Long locKey,
			Long updateRecorderKey, Long recorderKey, Long supervisorKey, Long reporterKey) throws SQLException {
		setPsStringParam(1, adverseDrugReaction.getConsumerId());
		setPsStringParam(2, adverseDrugReaction.getProducerId());
		String createTs = CommonUtil.toTimestampStr(adverseDrugReaction.getCreateTimestamp());
		setPsStringParam(3, createTs);
		String updateTs = CommonUtil.toTimestampStr(adverseDrugReaction.getUpdateTimestamp());
		setPsStringParam(4, updateTs);
		setPsStringParam(5, adverseDrugReaction.getDescriptionEnglish());
		String occTs = CommonUtil.toTimestampStr(adverseDrugReaction.getOccuranceStartTimestamp());
		setPsStringParam(6, occTs);
		setPsLongParam(7, updateRecorderKey);
		setPsLongParam(8, recorderKey);
		setPsLongParam(9, supervisorKey);
		setPsLongParam(10, reporterKey);
		setPsLongParam(11, locKey);
		setPsLongParam(12, patientKey);
		setPsLongParam(13, adverseDrugReactionKey);
	}
}
