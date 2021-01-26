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
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.ReactionCodeMissingException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.NonDrugAllergen;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

/**
 * Reaction API used to persist Reaction entity.
 * 
 * @author LTrevino
 *
 */
public class ReactionApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(ReactionApi.class);
	  Map <String, Long> drgPK = new HashMap<String, Long>();
	   Map <String, Timestamp> drgTime = new HashMap<String, Timestamp>();

	private final static String INSERTSQL = 
	"insert into RCTN (" +
		"EXPOSEDMTRLNM,	EXPOSEDMTRL,		PRDCRID,	RCTNDESCRENG,	RCTNSTRTTIMESTAMP, "	+
		"CNSMRID,		RCTNSVRTYTYPKEY,	RCTNTYPKEY,	SUBRCTNTYPKEY,	PTNTADVRSDRGRCTNKEY, "	+
		"PTNTALRGYKEY,	RCTNKEY" +
	") values (" +
		" ?, ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, ?, ?" +
		",?, ?" +
	")";
	
	private final static String UPDATESQL =
			"UPDATE RCTN "
			+ "SET EXPOSEDMTRLNM=?,	EXPOSEDMTRL=?,	PRDCRID=?,	RCTNDESCRENG=?,	RCTNSTRTTIMESTAMP="	
			+ CommonUtil.getPsToDateFunctionStr() + 
			", CNSMRID=?,	RCTNSVRTYTYPKEY=?,	RCTNTYPKEY=?, SUBRCTNTYPKEY=?, PTNTADVRSDRGRCTNKEY=?, PTNTALRGYKEY=? WHERE RCTNKEY=?";
	
	private final static String MERGESQL = 
			" MERGE INTO DRGRCTN TARGET   " + 
					"  USING    " + 
					"  ( SELECT DRGRCTNKEY  FROM    " + 
					"       (SELECT DRGRCTNKEY FROM DRGRCTN WHERE DRGKEY = ? AND RCTNKEY = ? " + 
					"            UNION ALL   " + 
					"            SELECT NULL DRGRCTNKEY FROM DUAL    " + 
					"       )WHERE ROWNUM=1   " + 
					"       ORDER BY DRGRCTNKEY DESC   " + 
					"  )  SRC    " + 
					"  ON  ( SRC.DRGRCTNKEY = TARGET.DRGRCTNKEY )   " + 
					"    WHEN NOT MATCHED THEN   " + 
					"      INSERT (DRGRCTNKEY , DRGKEY , RCTNKEY)   " + 
					"      VALUES (DRGRCTN_SEQ.NEXTVAL, ? , ? )  " ;
	
	/**
	 * Attempt to persist a Reaction instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Reaction doesn't exist, then insert a new record.
	 * If the Reaction exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param reaction		Reaction instance.
	 * 
	 * @return				ReactionKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 */
	public Long upsertReactionForAllergy(Connection connection, String storeNum, Reaction reaction, Long patientAllergyKey ) throws SQLException, NamingException, IOException, CDRException  
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertReactionForAllergy, storeNum : " + storeNum + ", patientAllergyKey : " + patientAllergyKey);}
			return 	upsertReaction(connection, storeNum, reaction,  patientAllergyKey ,null);
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertReactionForAllergy, storeNum : " + storeNum + ", patientAllergyKey : " + patientAllergyKey);}
		}
	}

	public Long upsertReactionForAdverseDrugReaction(Connection connection, String storeNum, Reaction reaction, Long patientAdverseDrugReactionKey ) throws SQLException, NamingException, IOException, CDRException  
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertReactionForAdverseDrugReaction, storeNum : " + storeNum + ", patientAdverseDrugReactionKey : " + patientAdverseDrugReactionKey);}
			return 	upsertReaction(connection, storeNum, reaction,  null , patientAdverseDrugReactionKey);
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertReactionForAdverseDrugReaction, storeNum : " + storeNum + ", patientAdverseDrugReactionKey : " + patientAdverseDrugReactionKey);}
		}
	}
	
	private Long upsertReaction(Connection connection, String storeNumber, Reaction reaction, Long patientAllergyKey , Long patientAdverseDrugReactionKey) throws SQLException, NamingException, IOException, CDRException {
		
//		Long allergyKey = (allergyConsumerId!=null) ? FindUtil.findAllergyKey(connection, allergyConsumerId, storeNum) : null;
//		Long adverseDrugReactionKey = (adverseDrugReactionConsumerId!=null) ? FindUtil.findAdverseDrugReactionKey(connection, adverseDrugReactionConsumerId, storeNum) : null;
		
		// reactionCode must be presented to force the uniqueness of the reaction.
		String reactionCode = (reaction.getReactionCode()!=null) ? reaction.getReactionCode().value() : null;
		
		if (reactionCode==null) 
		{
			throw new ReactionCodeMissingException();
		}
		Long subReactionTypeKey = getKeyFromCode(LT_SUBRCTNTYP, reactionCode);	

		// As per Shantanu's email on 2016-07-25, since Reaction JAXB does not have consumerId, 
		//for uniqueness Reaction Code should be used in combination with parent key (e.g. allergyKey / adverseDrugReactionKey).
		Long reactionKey = null;
		if (patientAllergyKey!=null) {
			reactionKey = FindUtil.findReactionKeyForAllergy(connection, subReactionTypeKey, patientAllergyKey);
		} else if (patientAdverseDrugReactionKey!=null) {
			reactionKey = FindUtil.findReactionKeyForAdverseDrugReaction(connection, subReactionTypeKey, patientAdverseDrugReactionKey);
		}
		
		NonDrugAllergen exposedMaterial = null;
		List<NonDrugAllergen> exposedMaterials = reaction.getExposedMaterial();
		for (int ii=0; exposedMaterials!=null && ii<exposedMaterials.size(); ii++) {
			exposedMaterial = exposedMaterials.get(ii);
			if (exposedMaterial!=null)
				break;
		}
		
		// Persist Reaction instance.
		if (reactionKey==null) {
			reactionKey=insertReaction(connection, storeNumber, reaction, patientAllergyKey, patientAdverseDrugReactionKey, exposedMaterial, subReactionTypeKey);
		} else {
			// RCTN table doesn't have an Update Timestamp field.
			updateReaction(connection, storeNumber, reactionKey, reaction, patientAllergyKey, patientAdverseDrugReactionKey, exposedMaterial , subReactionTypeKey);
		}			
		
		super.close();
		Store store = new Store();
		store.setStorenumber(storeNumber);
		List<Drug> drugs = reaction.getDrug();
		if (drugs!=null && drugs.size() > 0) {
			DrugApi drugApi = new DrugApi();
			for (Drug drug : drugs) {
				Long drugKey = drugApi.upsertDrug(connection, storeNumber, drug ,drgPK,drgTime );
				if (logger.isInfoEnabled())  {logger.info("Reaction[" + reactionKey + "].drug[" + drugKey + "] persisted.");}
				upsertDrugReactionTable(connection, reactionKey , drugKey );
				super.close();
			}
		}
		
		List<Note> notes = reaction.getNote();
		if (notes!=null && notes.size() > 0) {
			NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.REACTION_NOTE);
			noteApi.upsertNoteList(connection, store, notes , reactionKey);
		}
		List<Prescription> prescriptions = reaction.getCausativeRx();
		if (prescriptions!=null && prescriptions.size() > 0) {
			/*
			PrescriptionApi prescriptionApi = new PrescriptionApi(connection);
			for (Prescription prescription : prescriptions) {
				prescription.getConsumerId();
				prescriptionApi.upsertPrescription(connection, storeNumber, prescription);
			}
			*/
		}
		return reactionKey;
	}

	private Long upsertDrugReactionTable(Connection connection, long reactionKey , long drugKey ) throws SQLException
	{
		ps = connection.prepareStatement(MERGESQL);
		
    	setPsLongParam(1, drugKey); 
    	setPsLongParam(2, reactionKey); 
    	setPsLongParam(3, drugKey); 
    	setPsLongParam(4, reactionKey); 

		ps.executeUpdate();
		Long DrgRctnKey = null;  
		rs = ps.getGeneratedKeys();
		if (rs.next())
		{
			DrgRctnKey = rs.getLong(1);
		}
		CommonUtil.closePreparedStatementResultSet(ps, rs);
		
		return DrgRctnKey;
	}

/*	public Long upsertReaction(String storeNum, Reaction reaction, String allergyConsumerId, String adverseDrugReactionConsumerId) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, ConsumerIdMissingException, EntityNotFoundException, ReactionCodeMissingException {
		
		Long allergyKey = (allergyConsumerId!=null) ? FindUtil.findAllergyKey(connection, allergyConsumerId, storeNum) : null;
		Long adverseDrugReactionKey = (adverseDrugReactionConsumerId!=null) ? FindUtil.findAdverseDrugReactionKey(connection, adverseDrugReactionConsumerId, storeNum) : null;
		
		// reactionCode must be presented to force the uniqueness of the reaction.
		String reactionCode = (reaction.getReactionCode()!=null) ? reaction.getReactionCode().value() : null;
		
		if (reactionCode==null) 
		{
			throw new ReactionCodeMissingException();
		}
		Long subReactionTypeKey = TableCacheSingleton.getInstance().getKeyFromCode(LT_SUBRCTNTYP, reactionCode);	

		// As per Shantanu's email on 2016-07-25, since Reaction JAXB does not have consumerId, 
		//for uniqueness Reaction Code should be used in combination with parent key (e.g. allergyKey / adverseDrugReactionKey).
		Long reactionKey = null;
		if (allergyKey!=null) {
			reactionKey = FindUtil.findReactionKeyForAllergy(connection, subReactionTypeKey, allergyKey);
		} else if (adverseDrugReactionKey!=null) {
			reactionKey = FindUtil.findReactionKeyForAdverseDrugReaction(connection, reaction, adverseDrugReactionKey);
		}
		
		NonDrugAllergen exposedMaterial = null;
		List<NonDrugAllergen> exposedMaterials = reaction.getExposedMaterial();
		for (int ii=0; exposedMaterials!=null && ii<exposedMaterials.size(); ii++) {
			exposedMaterial = exposedMaterials.get(ii);
			if (exposedMaterial!=null)
				break;
		}
		
		// Persist Reaction instance.
		if (reactionKey==null) {
			reactionKey=insertReaction(storeNum, reaction, allergyKey, adverseDrugReactionKey, exposedMaterial, subReactionTypeKey);
		} else {
			// RCTN table doesn't have an Update Timestamp field.
			updateReaction(storeNum, reactionKey, reaction, allergyKey, adverseDrugReactionKey, exposedMaterial , subReactionTypeKey);
		}			
		
		Store store = new Store();
		store.setStorenumber(new Integer(storeNum));
		List<Drug> drugs = reaction.getDrug();
		if (drugs!=null && drugs.size() > 0) {
			DrugApi drugApi = new DrugApi(connection);
			for (Drug drug : drugs) {
				Long drugKey = drugApi.upsertDrug(storeNum, drug);
				logger.info("Reaction[" + reactionKey + "].drug[" + drugKey + "] persisted.");
			}
		}
		List<Note> notes = reaction.getNote();
		if (notes!=null && notes.size() > 0) {
			NoteApi noteApi = new NoteApi(connection, CDREnumerations.NoteTypeTable.REACTION_NOTE);
			noteApi.upsertNoteList(store, notes , reactionKey);
		}
		List<Prescription> prescriptions = reaction.getCausativeRx();
		if (prescriptions!=null && prescriptions.size() > 0) {
			/*
			PrescriptionApi prescriptionApi = new PrescriptionApi(connection);
			for (Prescription prescription : prescriptions) {
				prescription.getConsumerId();
				prescriptionApi.upsertPrescription(connection, storeNum, prescription);
			}
			/
		}
		return reactionKey;
	}
	
	*/
	
	/**
	 * Attempt to update a Reaction instance, which is matched by reactionKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param reactionKey	Reaction Key.
	 * @param reaction		Reaction instance.
	 * 
	 * @return				ReactionKey value.
	 * 
	 * @throws SQLException
	 * @throws ReactionCodeMissingException 
	 * @throws InvalidInputException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long updateReaction(Connection connection, String storeNum, Long reactionKey, Reaction reaction, Long allergyKey, Long adverseDrugReactionKey,
			NonDrugAllergen exposedMaterial , Long subReactionTypeKey) throws SQLException, ReactionCodeMissingException, InvalidInputException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, reactionKey, reaction, allergyKey, adverseDrugReactionKey, exposedMaterial , subReactionTypeKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Reaction intances updated: " + res + ". ReactionKey: " + reactionKey + ", storeNum: '" + storeNum + "'.");}
		return reactionKey;
	}

	private Long insertReaction(Connection connection, String storeNum, Reaction reaction, Long allergyKey, Long adverseDrugReactionKey, NonDrugAllergen exposedMaterial , Long subReactionTypeKey) throws SQLException, ReactionCodeMissingException, InvalidInputException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long reactionKey = IdGenerator.generate(connection, "RCTN");

		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, reactionKey, reaction, allergyKey, adverseDrugReactionKey, exposedMaterial, subReactionTypeKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Reaction intances updated: " + res + ". ReactionKey: " + reactionKey + ", storeNum: '" + storeNum + "'.");}
		return reactionKey;
	}
	
	
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param reactionKey	Reaction Key.
	 * @param reaction		Reaction instance.
	 * 
	 * @throws SQLException
	 * @throws ReactionCodeMissingException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long reactionKey, Reaction reaction, Long allergyKey, Long adverseDrugReactionKey, 
				NonDrugAllergen exposedMaterial , Long subReactionTypeKey) throws SQLException, ReactionCodeMissingException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		String exposedMaterialName =  (exposedMaterial!=null) ? exposedMaterial.name()  : null;
		setPsStringParam(1, exposedMaterialName);
		String exposedMaterialValue = (exposedMaterial!=null) ? exposedMaterial.value() : null;
		setPsStringParam(2, exposedMaterialValue);
		setPsStringParam(3, reaction.getProducerid());
		setPsStringParam(4, reaction.getReactionDescriptionEnglish());
		String reactionStartTimestamp = CommonUtil.toTimestampStr(reaction.getReactionStartTimestamp());
		setPsStringParam(5, reactionStartTimestamp);
		String consumerId = null; // Not present in mapping document. Identified as a gap as per conversation on 2016-07-25.
		setPsStringParam(6, consumerId);
		
		String severityCode = (reaction.getReactionSeverityCode()!=null) ? reaction.getReactionSeverityCode().value() : null;
		Long severityTypeKey = null;
		// select RctnSvrtyTypKey from RctnSvrtyTyp where RctnSvrtyTyp.RctnSvrtyCd like '%%'

		if (!StringUtil.isEmpty(severityCode)) 
			severityTypeKey = getKeyFromCode(LT_RCTNSVRTYTYP, severityCode);
		setPsLongParam(7, severityTypeKey);
		String reactionTypeKey = null ;
		setPsLongParam(8, reactionTypeKey);
		setPsLongParam(9, subReactionTypeKey);
		setPsLongParam(10, adverseDrugReactionKey);
		setPsLongParam(11, allergyKey);
		setPsLongParam(12, reactionKey);
	}
}
