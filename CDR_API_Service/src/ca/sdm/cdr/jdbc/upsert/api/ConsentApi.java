package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.ConsentNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentOverrideReason;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentReasonCode;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;
import ca.sdm.cdr.common.util.FindEnum;

/**
 * Consent API used to persist Consent entity.
 * 
 * @author LTrevino
 *
 */
public class ConsentApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(ConsentApi.class);
	private final static String INSERTSQL = 
	"INSERT INTO PTNTCNSNT (" +
	"	CNSMRID,	CNSNTEFFTIMESTAMP,	CNSNTENDTIMESTAMP,	PRDCRID,		TXR, " +
	"	PTNTID,		CNSNTOVRDRSNTYPKEY,	PTNTKEY,  PTNTCNSNTKEY " +
	") VALUES (" +
		" ?, " + CommonUtil.getPsToDateFunctionStr() + ", " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, ?, ?, ?" +
	")";

	private final static String UPDATESQL =
	"update PTNTCNSNT set " +
	"   CNSMRID=?,	CNSNTEFFTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + 
		",	CNSNTENDTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ",	PRDCRID=?,	TXR=?," + 
	"   PTNTID=?,	CNSNTOVRDRSNTYPKEY=?,	PTNTKEY=?	 " +
	" WHERE PTNTCNSNTKEY=?";
	

	private final static String UPSERTCONSERTSQL = "MERGE INTO PTNTCNSNT a " + 
			 "USING (SELECT  ? CNSMRID,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTEFFTIMESTAMP,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTENDTIMESTAMP,? PRDCRID,? TXR,? CNSNTOVRDRSNTYPKEY,? PTNTKEY,? CONSENTTYPE,? CNSNTRSNTYPKEY,? CONSENTPROVIDERTYPE,? AGENTFRSTNM,? AGENTLSTNM,? AGENTRELATIONSHIP,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTCRTTIMESTAMP,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTUPTTIMESTAMP,? USERID , ? NOTIFMETHOD  FROM dual) b " +
			 "	on(a.PTNTKEY = b.PTNTKEY and a.CNSMRID = b.CNSMRID) " + 
			 "	WHEN MATCHED THEN " + "   UPDATE SET  CNSNTEFFTIMESTAMP=b.CNSNTEFFTIMESTAMP, CNSNTENDTIMESTAMP=b.CNSNTENDTIMESTAMP, PRDCRID=b.PRDCRID,TXR= b.TXR, CNSNTOVRDRSNTYPKEY=b.CNSNTOVRDRSNTYPKEY,CONSENTTYPE=b.CONSENTTYPE, CNSNTRSNTYPKEY=b.CNSNTRSNTYPKEY, CONSENTPROVIDERTYPE=b.CONSENTPROVIDERTYPE, AGENTFRSTNM=b.AGENTFRSTNM,AGENTLSTNM=b.AGENTLSTNM, AGENTRELATIONSHIP=b.AGENTRELATIONSHIP, CNSNTCRTTIMESTAMP=b.CNSNTCRTTIMESTAMP, CNSNTUPTTIMESTAMP=b.CNSNTUPTTIMESTAMP,USERID =b.USERID , NOTIFMETHOD = b.NOTIFMETHOD 	WHEN NOT MATCHED THEN " +

			 "	insert (CNSMRID,	CNSNTEFFTIMESTAMP,	CNSNTENDTIMESTAMP,	PRDCRID,		TXR, PTNTCNSNTKEY,		CNSNTOVRDRSNTYPKEY,	PTNTKEY,  CONSENTTYPE,CNSNTRSNTYPKEY,CONSENTPROVIDERTYPE,AGENTFRSTNM,AGENTLSTNM,AGENTRELATIONSHIP,CNSNTCRTTIMESTAMP,CNSNTUPTTIMESTAMP, USERID , NOTIFMETHOD) " + 
			 "	values(b.CNSMRID, b.CNSNTEFFTIMESTAMP, b.CNSNTENDTIMESTAMP, b.PRDCRID, b.TXR, PTNTCNSNT_SEQ.nextval, b.CNSNTOVRDRSNTYPKEY, b.PTNTKEY, b.CONSENTTYPE, b.CNSNTRSNTYPKEY, b.CONSENTPROVIDERTYPE, b.AGENTFRSTNM,b.AGENTLSTNM, b.AGENTRELATIONSHIP, b.CNSNTCRTTIMESTAMP, b.CNSNTUPTTIMESTAMP, b.USERID , b.NOTIFMETHOD) ";


	/*
	private final static String UPSERTCONSERTSQL = "MERGE INTO PTNTCNSNT a " + 
			 "USING (SELECT  ? CNSMRID,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTEFFTIMESTAMP,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTENDTIMESTAMP,? PRDCRID,? TXR,? CNSNTOVRDRSNTYPKEY,? PTNTKEY,? CONSENTTYPE,? CNSNTRSNTYPKEY,? CONSENTPROVIDERTYPE,? CAREGIVERNAME,? CAREGIVERRELATION,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTCRTTIMESTAMP,"+CommonUtil.getPsToDateFunctionStr()+" CNSNTUPTTIMESTAMP, ? NOTIFMETHOD FROM dual) b " +
			 "	on(a.PTNTKEY = b.PTNTKEY and a.CNSMRID = b.CNSMRID) " + 
			 "	WHEN MATCHED THEN " + "   UPDATE SET  CNSNTEFFTIMESTAMP=b.CNSNTEFFTIMESTAMP, CNSNTENDTIMESTAMP=b.CNSNTENDTIMESTAMP, PRDCRID=b.PRDCRID,TXR= b.TXR, CNSNTOVRDRSNTYPKEY=b.CNSNTOVRDRSNTYPKEY,CONSENTTYPE=b.CONSENTTYPE, CNSNTRSNTYPKEY=b.CNSNTRSNTYPKEY, CONSENTPROVIDERTYPE=b.CONSENTPROVIDERTYPE, CAREGIVERNAME=b.CAREGIVERNAME, CAREGIVERRELATION=b.CAREGIVERRELATION, CNSNTCRTTIMESTAMP=b.CNSNTCRTTIMESTAMP, CNSNTUPTTIMESTAMP=b.CNSNTUPTTIMESTAMP,NOTIFMETHOD = b.NOTIFMETHOD 	WHEN NOT MATCHED THEN " +

			 "	insert (CNSMRID,	CNSNTEFFTIMESTAMP,	CNSNTENDTIMESTAMP,	PRDCRID,		TXR, PTNTCNSNTKEY,		CNSNTOVRDRSNTYPKEY,	PTNTKEY,  CONSENTTYPE,CNSNTRSNTYPKEY,CONSENTPROVIDERTYPE,CAREGIVERNAME,CAREGIVERRELATION,CNSNTCRTTIMESTAMP,CNSNTUPTTIMESTAMP, NOTIFMETHOD ) " + 
			 "	values(b.CNSMRID, b.CNSNTEFFTIMESTAMP, b.CNSNTENDTIMESTAMP, b.PRDCRID, b.TXR, PTNTCNSNT_SEQ.nextval, b.CNSNTOVRDRSNTYPKEY, b.PTNTKEY, b.CONSENTTYPE, b.CNSNTRSNTYPKEY, b.CONSENTPROVIDERTYPE, b.CAREGIVERNAME, b.CAREGIVERRELATION, b.CNSNTCRTTIMESTAMP, b.CNSNTUPTTIMESTAMP, b.NOTIFMETHOD)";
*/
	/**
	 * Attempt to persist a Consent instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Consent doesn't exist, then insert a new record.
	 * If the Consent exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consent		Consent instance.
	 * 
	 * @return				ConsentKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 */
	
	public Long upsertConsent(Connection connection, String consumerId, String storeNumber, Consent consent)
			throws SQLException, NamingException, IOException, CDRException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertConsent. store number " + storeNumber + ", consumerId : "+ consumerId + ", consent consumerId : "+consent.getConsumerId());}
			Long consentKey;
			consentKey = FindUtil.findPatientConsentKey(connection, consumerId, storeNumber);

			Long dispenserKey = null;
			Dispenser dispenser = consent.getDispenser();
			if (dispenser != null) {
				Store store = new Store();
				store.setStorenumber(storeNumber);
				PersonRole personRole = new PersonRole(dispenser);
				PersonApi personApi = new PersonApi();
				dispenserKey = personApi.upsertPerson(connection, store, personRole);
			}

			if (consentKey == null) {
				consentKey = insertConsent(connection, consumerId, storeNumber, consent, dispenserKey);
			} else {
				// PTNTCNST table doesn't have an Update Timestamp field.
				updateConsent(connection, consumerId, storeNumber, consentKey, consent, dispenserKey);
			}

			return consentKey;
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertConsent. store number " + storeNumber + ", consumerId : "+ consumerId + ", consent consumerId : "+consent.getConsumerId());}
		}
	}	
	
	
	/**
	 * Attempt to update a Consent instance, which is matched by consentKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consentKey	Consent Key.
	 * @param consent		Consent instance.
	 * 
	 * @return				ConsentKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws PatientNotFoundException 
	 * @throws InvalidInputException 
	 */
	private Long updateConsent(Connection connection, String consumerId, String storeNum, Long consentKey, Consent consent , Long dispenserKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, PatientNotFoundException, InvalidInputException {
		if (consumerId==null || consumerId.trim().length() < 1) {
			throw new InvalidInputException("ConsumerId is required.");
		}
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(connection, consumerId, storeNum, consentKey, consent , dispenserKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Consent intances updated: " + res + ". ConsentKey: " + consentKey + ", consumerId: '" + consumerId + "', storeNum: '" + storeNum + "'.");}
		return consentKey;
	}

	
	/**
	 * Attempt to insert a new Consent into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consent		Consent instance.
	 * 
	 * @return				ConsentKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws PatientNotFoundException 
	 * @throws InvalidInputException 
	 */
	private Long insertConsent(Connection connection, String consumerId, String storeNum, Consent consent , Long dispenserKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, PatientNotFoundException, InvalidInputException {
		if (consumerId==null || consumerId.trim().length() < 1) {
			throw new InvalidInputException("ConsumerId is required.");
		}
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long consentKey = IdGenerator.generate(connection, "PTNTCNSNT");
		/*
		 * 
		 Long consentOverrideReasonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP, consentOverrideReasonCode);
		 */
		////Praveen T added for SmartNotification 
		String contype = consent.getConsentType();
		try {
			consentKey = getKeyFromCode(LT_CNSNTRSNTYP, contype);
	   } catch (KeyNotFoundFromTableCacheException e) {
		if(logger.isErrorEnabled()) {logger.error("The Consent type in request is not valid for " + consentKey);}
		//throw new ConsentNotFoundException(storeNum ,consumerId , contype ,consumerId);
	   }
		////Praveen T added for SmartNotification 
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(connection, consumerId, storeNum, consentKey, consent , dispenserKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Consent intances updated: " + res + ". ConsentKey: " + consentKey + ", consumerId: '" + consumerId + "', storeNum: '" + storeNum + "'.");}
		return consentKey;
	}
	
	//Praveen T added for SmartNotification 
	public void upsertConsentSN(Connection connection,Long patientKey, String consumerId, String storeNumber, Consent consent)
			throws SQLException, NamingException, IOException, CDRException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertConsent. store number " + storeNumber + ", consumerId : "+ consumerId + ", consent consumerId : "+consent.getConsumerId());}
			//Praveen T added for SmartNotification
			//Long consentKey;
			//consentKey = FindUtil.findPatientConsentKey(connection, consumerId, storeNumber);
			//Praveen T added for SmartNotification
			Long dispenserKey = null;
			Dispenser dispenser = consent.getDispenser();
			if (dispenser != null) {
				Store store = new Store();
				store.setStorenumber(storeNumber);
				PersonRole personRole = new PersonRole(dispenser);
				PersonApi personApi = new PersonApi();
				dispenserKey = personApi.upsertPerson(connection, store, personRole);
			}
			upsertConsentTableSN( connection, patientKey,  consumerId,  storeNumber,  consent ,  dispenserKey);
			//Praveen T added for SmartNotification
			/*if (consentKey == null) {
				consentKey = insertConsent(connection, consumerId, storeNumber, consent, dispenserKey);
			} else {
				// PTNTCNST table doesn't have an Update Timestamp field.
				updateConsent(connection, consumerId, storeNumber, consentKey, consent, dispenserKey);
			}*/

			//return consentKey;
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertConsentSN. store number " + storeNumber + ", consumerId : "+ consumerId + ", consent consumerId : "+consent.getConsumerId());}
		}
	}	
	
	//upsertConsentTableSN
	/**
	 * Attempt to persist a Consent instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Consent doesn't exist, then insert a new record.
	 * If the Consent exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consent		Consent instance.
	 * 
	 * @return				ConsentKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 */
////Praveen T added for SmartNotification
	public void upsertConsentTableSN(Connection connection,Long patientKey, String consumerId, String storeNum, Consent consent , Long dispenserKey)
			throws SQLException, NamingException, IOException, CDRException ,ConsentNotFoundException,KeyNotFoundFromTableCacheException{
		try {
			if (consumerId==null || consumerId.trim().length() < 1) {
				throw new InvalidInputException("ConsumerId is required.");
			}
			
			if (storeNum==null || storeNum.trim().length() < 1) {
				throw new InvalidInputException("StoreNum is required");
			}
			//Long consentKey = IdGenerator.generate(connection, "PTNTCNSNT");
			/*
			 * 
			 Long consentOverrideReasonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP, consentOverrideReasonCode);
			 */
			////Praveen T added for SmartNotification 
			//Praveen T added for SmartNotification  -2
			/*Long consentResonKey = 0L;	
			String contypeReson = null;
			if(consent.getConsentReasonCode() == null) {
				 throw new InvalidInputException("Invalid ConsentReasonCode");
			}else {
			 contypeReson = consent.getConsentReasonCode().value();
			
			 consentResonKey = getKeyFromCode(LT_CNSNTRSNTYP,contypeReson);
			 	
			 if(consentResonKey==null) {
				 throw new InvalidInputException("Invalid ConsentReasonCode");
			 }
			}
			Long consenOverrideResonKey =0L; 
			String consentOverrideRsnCode = null;
			if(consent.getConsentOverrideReasonCode()==null) {
				 throw new InvalidInputException("Invalid ConsentOverrideReasonCode");
			 }else {
				 consentOverrideRsnCode = consent.getConsentOverrideReasonCode().value();
				 consenOverrideResonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP,consentOverrideRsnCode) ;
				 if(consenOverrideResonKey==null) {
					 throw new InvalidInputException("Invalid ConsentOverrideReasonCode"); 
				 }
			 }*/
			 //Praveen T added for SmartNotification -2
			Long consentResonKey = 0L;	
			String contypeReson = null;
			String contypeResonVal = null;
			contypeReson =consent.getConsentReasonCode()!= null ? consent.getConsentReasonCode().name() : null;
			//Praveen T added for SmartNotification -3
			//for Null added
			if (contypeReson == null){
				consentResonKey = null;
				//contypeReson =null;
			}else{
				if (!FindEnum.contains(ConsentReasonCode.class, contypeReson)) {
				throw new InvalidInputException("Invalid ConsentReasonCode");
				//throw new ConsentNotFoundException( patientKey,consumerId , contypeReson ,"Invalid ConsentReasonCode");
				}else{
					contypeResonVal =consent.getConsentReasonCode()!= null ? consent.getConsentReasonCode().value(): null;
				consentResonKey = getKeyFromCode(LT_CNSNTRSNTYP,contypeResonVal);
				if(consentResonKey==null || consentResonKey ==0L) {
					 throw new InvalidInputException("Invalid ConsentReasonCodeKey");
				 }
				}	
			}
			//ConsentOverrideReason
			Long consenOverrideResonKey =0L; 
			String consentOverrideRsnCode = null;
			String consentOverrideRsnCodeVal = null;
			consentOverrideRsnCode = consent.getConsentOverrideReasonCode()!= null ? consent.getConsentOverrideReasonCode().name(): null;
			//Praveen T added for SmartNotification -3
			//for Null added
			if(consentOverrideRsnCode == null){
				//consentOverrideRsnCode = null;
				consenOverrideResonKey = null;
			}else{
					if (!FindEnum.contains(ConsentOverrideReason.class, consentOverrideRsnCode)) {
					throw new InvalidInputException("Invalid consentOverrideRsnCode");
					
					}else{
						consentOverrideRsnCodeVal = consent.getConsentOverrideReasonCode()!= null ? consent.getConsentOverrideReasonCode().value(): null;
					consenOverrideResonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP,consentOverrideRsnCodeVal) ;
					if(consenOverrideResonKey==null || consenOverrideResonKey == 0L) {
						 throw new InvalidInputException("Invalid consenOverrideResonKey");
					 }
					}
			}
			if(logger.isDebugEnabled()) {logger.debug("The contypeReson in request : " + contypeReson +":"+consentResonKey);
			 logger.debug("consentResonKey:"+consentResonKey);}
							
		   
			////Praveen T added for SmartNotification 
			ps = connection.prepareStatement(UPSERTCONSERTSQL);
			setPsParamsSN(connection,patientKey, consumerId, storeNum, consentResonKey,consenOverrideResonKey, consent , dispenserKey);
			int res = ps.executeUpdate();
			if(logger.isDebugEnabled()) {logger.debug("Total Consent intances updated: " + res +  ", consumerId: '" + consumerId + "', storeNum: '" + storeNum + "'.");}
			//return consentKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}catch(KeyNotFoundFromTableCacheException e) {
			  throw e;
			}catch (NamingException e) {
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertConsentTableSN. store number " + storeNum + ", consumerId : "+ consumerId + ", consent consumerId : "+consent.getConsumerId());}
		}
	}
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param patientKey1			PatientKey.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consentKey	Consent Key.
	 * @param consentResonKey	Consent reason Key.
	 * @param consent		Consent instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws PatientNotFoundException 
	 */
	private void setPsParamsSN(Connection connection,Long patientKey1, String consumerId, String storeNum, Long consentResonKey,Long consentOverrideResonKey, Consent consent , Long dispenserKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, PatientNotFoundException {
			
		setPsStringParam(1, consumerId);
		setPsStringParam(2, CommonUtil.toTimestampStr(consent.getConsentEffectiveTimestamp()));
		setPsStringParam(3, CommonUtil.toTimestampStr(consent.getConsentEndTimestamp()));
		setPsStringParam(4, consent.getProducerId()); 
		setPsLongParam(5, dispenserKey);
		//String patientId = null; // Not used, as per mapping document
		//setPsStringParam(6, patientId);
		//Praveen T added for SmartNotification -3 
		///ConsentOverrideReason consentOverrideReason = consent.getConsentOverrideReasonCode();
		///String consentOverrideReasonCode = (consentOverrideReason!=null) ? consentOverrideReason.value() : null;
		//select CnsntOvrdRsnTyp.* from CnsntOvrdRsnTyp where CnsntOvrdRsnTyp.CnsntOvrdRsnTypCd='[consentOverrideReasonCode]'
		///Long consentOverrideReasonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP, consentOverrideReasonCode);
		//setPsLongParam(6, consentKey);		
		setPsLongParam(6, consentOverrideResonKey);	
		//Praveen T added for SmartNotification
		//Long patientKey = FindUtil.findPatientKey(connection, consumerId, storeNum);
		setPsLongParam(7, patientKey1);		
		//setPsLongParam(9, consentKey);
		//consent
		//CONSENTTYPE
		setPsStringParam(8, consent.getConsentType());
		//CNSNTRSNTYPKEY		//
		setPsLongParam(9, consentResonKey);
		//CONSENTPROVIDERTYPE
		setPsStringParam(10, consent.getConsentProvider());
		//Praveen T added for SmartNotification -2
		//AGENTFRSTNM
		//setPsStringParam(11, consent.getAgentName());
		setPsStringParam(11, consent.getAgentFirstName());
		
		//AGENTLSTNM
		setPsStringParam(12, consent.getAgentLastName());
		//AGENTRELATIONSHIP
		setPsStringParam(13, consent.getAgentRelationship());
		//CNSNTCRTTIMESTAMP
		setPsStringParam(14, CommonUtil.toTimestampStr(consent.getConsentCreateTimestamp()));
		//CNSNTUPTTIMESTAMP
		setPsStringParam(15, CommonUtil.toTimestampStr(consent.getConsentUpdateTimestamp()));
		//NOTIFMETHOD
		//setPsStringParam(16, consent.getNotificationMethod());		
		//USERID
		setPsStringParam(16, consent.getConsentUserId());
		//NOTIFMETHOD
		setPsStringParam(17, consent.getNotificationMethod());	
		//Praveen T added for SmartNotification -2
	}
	//Praveen T added for SmartNotification 
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param consentKey	Consent Key.
	 * @param consent		Consent instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws PatientNotFoundException 
	 */
	private void setPsParams(Connection connection, String consumerId, String storeNum, Long consentKey, Consent consent , Long dispenserKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, PatientNotFoundException {
		setPsStringParam(1, consumerId);
		setPsStringParam(2, CommonUtil.toTimestampStr(consent.getConsentEffectiveTimestamp()));
		setPsStringParam(3, CommonUtil.toTimestampStr(consent.getConsentEndTimestamp()));
		setPsStringParam(4, consent.getProducerId()); 
		setPsLongParam(5, dispenserKey);
		String patientId = null; // Not used, as per mapping document
		setPsStringParam(6, patientId);
		
		ConsentOverrideReason consentOverrideReason = consent.getConsentOverrideReasonCode();
		String consentOverrideReasonCode = (consentOverrideReason!=null) ? consentOverrideReason.value() : null;
		//select CnsntOvrdRsnTyp.* from CnsntOvrdRsnTyp where CnsntOvrdRsnTyp.CnsntOvrdRsnTypCd='[consentOverrideReasonCode]'
		Long consentOverrideReasonKey = getKeyFromCode(LT_CNSNTOVRDRSNTYP, consentOverrideReasonCode);
		setPsLongParam(7, consentOverrideReasonKey);
		
		Long patientKey = FindUtil.findPatientKey(connection, consumerId, storeNum);
		setPsLongParam(8, patientKey);
		
		setPsLongParam(9, consentKey);
	}
}
