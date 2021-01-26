package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DISCNTDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GNDRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GRPMBRSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_INTRCHGBLTYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PKGFRMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PTNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXNSRCECHNLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXRSMRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXSUBRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.ChannelNotFoundException;
import com.sdm.cdr.exception.api.CustomerChannelNotFoundException;
import com.sdm.cdr.exception.api.CustomerTypeNotFoundException;
import com.sdm.cdr.exception.api.DataValidationException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.RefillChannelNotFoundException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.singleton.SourcePharmacyChannelSingleton;
import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.bean.UpsertResponse;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.drx.cse.RefillRequest;
import ca.shoppersdrugmart.rxhb.drx.cse.UserTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.cse.VerificationStateEnum;
import ca.shoppersdrugmart.rxhb.drx.dispensing.Refill;
import ca.shoppersdrugmart.rxhb.drx.dispensing.RefillResponse;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.ChannelType;
import ca.shoppersdrugmart.rxhb.ehealth.CommunicationModeType;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.GroupMembership;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import generated.RxHBBusinessEventActionEnum;


/**
 * Dispense API used to persist Dispense entity.
 * 
 *
 */
public class RefillDispensingApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(RefillDispensingApi.class);
	public final static String successFlag = "1";
	
	private final static String QUERYCUSTKEYSQL = "SELECT CUSTKEY FROM CUST WHERE USERID = ? AND USERTYP = 'Customer' ";
	
	private final static String MERGESQL = "MERGE INTO TXDRX a		"+
"      USING (SELECT  	?                     	RXKEY,              "+
"                     	null                    TXKEY,              "+
"                    	?            			CORRID,             "+
"                   	?             			FILLREQID,          "+
"                     	?                       COMMMODETYPKEY,     "+
"                     	?                       PHARMACYCHNLTYPKEY, "+
"                    	?           			BATCHID,            "+
"                    	?         				FILLREQSTATUS,      "+
"                    	?           			FILLREQTYP,         "+
          CommonUtil.TO_TIMESTAMP_TZ	+" 					 LSTUPDTIMESTAMP,    "+
                     CommonUtil.TO_TIMESTAMP_TZ	+"						 REQTIMESTAMP,       "+
 //Praveen Modified 
 //"                    null						 PRMSDTIMESTAMP,     "+
CommonUtil.TO_TIMESTAMP_TZ	+"						 PRMSDTIMESTAMP,       "+
"                     	?                       VERSION,            "+
"                    	?         				VRFYSTATE,          "+
"                     	?                       RETRYCNT,           "+
"                    	?                 		EVENTNM,            "+
"                    	?                      	STORENUM,           "+
"                    	?                   	RXNUM,              "+
"                     null                      RSNCD,               "+
"                     ?                      ENGMNTTYPKEY,          "+
"                     ?                      CUSTKEY,               "+
"                     ?                      USERID               "+
"             FROM dual) b                                           "+
"      ON (a.CORRID   = b.CORRID   and                               "+
"          a.STORENUM = b.STORENUM and                               "+
"          a.RXNUM    = b.RXNUM)                                     "+
"                                                                    "+
"      WHEN MATCHED THEN                                             "+
"        UPDATE SET RXKEY              = b.RXKEY,                    "+
"                   TXKEY              = b.TXKEY,                    "+
"                   FILLREQID          = b.FILLREQID,                "+
"                   COMMMODETYPKEY     = b.COMMMODETYPKEY,           "+
"                   PHARMACYCHNLTYPKEY = b.PHARMACYCHNLTYPKEY,       "+
"                   BATCHID            = b.BATCHID,                  "+
"                   FILLREQSTATUS      = b.FILLREQSTATUS,            "+
"                   FILLREQTYP         = b.FILLREQTYP,               "+
"                   LSTUPDTIMESTAMP    = b.LSTUPDTIMESTAMP,          "+
"                   REQTIMESTAMP       = b.REQTIMESTAMP,             "+
"                   PRMSDTIMESTAMP     = b.PRMSDTIMESTAMP,           "+
"                   VERSION            = b.VERSION,                  "+
"                   VRFYSTATE          = b.VRFYSTATE,                "+
"                   RETRYCNT           = b.RETRYCNT,                 "+
"                   EVENTNM            = b.EVENTNM,                  "+
"                   RSNCD              = b.RSNCD  ,                   "+
"                   ENGMNTTYPKEY       = b.ENGMNTTYPKEY,                     "+
"                   CUSTKEY       = b.CUSTKEY,                     "+
"                   USERID       = b.USERID                     "+
"                                                                    "+
"      WHEN NOT MATCHED THEN                                         "+
"           insert (TXDRXKEY,                                        "+
"                   RXKEY,                                           "+
"                   TXKEY,                                           "+
"                   CORRID,                                          "+
"                   FILLREQID,                                       "+
"                   COMMMODETYPKEY,                                  "+
"                   PHARMACYCHNLTYPKEY,                              "+
"                   BATCHID,                                         "+
"                   FILLREQSTATUS,                                   "+
"                   FILLREQTYP,                                      "+
"                   LSTUPDTIMESTAMP,                                 "+
"                   REQTIMESTAMP,                                    "+
"                   PRMSDTIMESTAMP,                                  "+
"                   VERSION,                                         "+
"                   VRFYSTATE,                                       "+
"                   RETRYCNT,                                        "+
"                   EVENTNM,                                         "+
"                   STORENUM,                                        "+
"                   RXNUM,                                           "+
"                   RSNCD,ENGMNTTYPKEY,CUSTKEY,USERID)                                           "+
"           values (TXDRX_SEQ.nextval,                               "+
"                   b.RXKEY,                                         "+
"                   b.TXKEY,                                         "+
"                   b.CORRID,                                        "+
"                   b.FILLREQID,                                     "+
"                   b.COMMMODETYPKEY,                                "+
"                   b.PHARMACYCHNLTYPKEY,                            "+
"                   b.BATCHID,                                       "+
"                   b.FILLREQSTATUS,                                 "+
"                   b.FILLREQTYP,                                    "+
"                   b.LSTUPDTIMESTAMP,                               "+
"                   b.REQTIMESTAMP,                                  "+
"                   b.PRMSDTIMESTAMP,                                "+
"                   b.VERSION,                                       "+
"                   b.VRFYSTATE,                                     "+
"                   b.RETRYCNT,                                      "+
"                   b.EVENTNM,                                       "+
"                   b.STORENUM,                                      "+
"                   b.RXNUM,                                         "+
"                   b.RSNCD,b.ENGMNTTYPKEY,b.CUSTKEY,b.USERID)                ";
		
	/**
	 * 
	 * @param dispense
	 * @param store
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws NamingException
	 * @throws CDRException 
	 * @throws JAXBException 
	 */
		private List<Prescription> prescriptionList = null;
		private List<RefillRequest> refillList = null;

		public RefillResponse upsertRefillDispense(Connection connection, Refill refill) throws Exception 
		{
			try {
				Long timer = System.currentTimeMillis();
				PharmacyChannel pharmacyChannel = refill.getSourceChannel();
				prescriptionList = refill.getPrescription();
				refillList = refill.getRefillRequest();
				String corrid =null;
				Long custKey = null;
				String userId = null;
				if(refillList.size()>0) {
				RefillRequest refillRequest = refillList.get(0);
				  corrid = refillRequest.getCorrelationId();
				  
				  String verificationTypeEnum = refillRequest.getVerificationState() != null ? refillRequest.getVerificationState().name() : null;
					
					if (!FindEnum.contains(VerificationStateEnum.class, verificationTypeEnum)) {
						if(logger.isErrorEnabled()) {logger.error("Verification Type " + verificationTypeEnum + " not found");}
						throw new InvalidInputException("Verification State is not valid for Refill Submission");
					}
					else {
						if(refillRequest.getVerificationState().value().equalsIgnoreCase(VerificationStateEnum.VERIFIED_CUSTOMER.value())) {
							userId = refillRequest.getUserId();
							ps = connection.prepareStatement(QUERYCUSTKEYSQL);
							CommonUtil.setPsStringParam(ps, 1, userId);
							
							rs = ps.executeQuery();

							if (rs.next()) // No patient record found
							{
								custKey = rs.getLong("CUSTKEY");
							}

							 super.close();
						}
					}
					
				  
				}
				if (corrid == null) {
					throw new DataValidationException("Correlation ID not provided.");
				}
				PharmacyChannel sourceChannel= refill.getSourceChannel();
				if (sourceChannel == null ) {
					throw new InvalidInputException("Source Channel is mandatory for Refill Submission");
				}
				Long channelKey = null;
				if(sourceChannel != null){
					String channelID = sourceChannel.getChannelType() != null ? sourceChannel.getChannelType().value() : null;
					if(channelID != null){
				try {
					channelKey = getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
				} catch (KeyNotFoundFromTableCacheException e) {
					if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for " + channelID);}
					throw new RefillChannelNotFoundException(null,null,corrid);
				}
					}
					else{
						if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for " + channelID);}
						throw new RefillChannelNotFoundException(null,null,corrid);
					}
				
					
					Long communicationKey = null;
					if(pharmacyChannel != null){
						String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
						if(communicationID != null){
					try {
						communicationKey = getKeyFromCode(LT_COMMMODETYP, communicationID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("Refill Communication type in request is not valid for " + communicationID);}
						throw new InvalidInputException("Refill Communication Mode is not valid.");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("Refill Communication type in request is not valid for " + communicationID);}
							throw new InvalidInputException("Refill Communication Mode is not valid.");
						}
					}
					
					Long engagementKey = null;
					if(pharmacyChannel != null){
						String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
						if(engID != null){
					try {
						engagementKey = getKeyFromCode(LT_ENGMNTTYP, engID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("Refill Engagement type in request is not valid for " + engID);}
						throw new InvalidInputException("Refill Engagement type is not valid .");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("Refill Engagement type in request is not valid for " + engID);}
							throw new InvalidInputException("Refill Engagement type is not valid.");
						}
					}
				}
				
				
				
				
				if (logger.isInfoEnabled())  {logger.info("StartApiCall: RefillDispensingApi.upsertRefillDispense. Corelation ID  : " + corrid);}
				for(Prescription prescription : prescriptionList){
					String storeNumber = CommonUtil.createStoreLeadingZeros(prescription.getServiceLocation().getStore().get(0).getStorenumber());
					Integer prescriptionNumber = (prescription.getPrescriptionNumber() != null)? prescription.getPrescriptionNumber() : null;
					if (prescriptionNumber == null) {
						throw new DataValidationException("Prescription Number not provided.");
					}

					if (prescriptionNumber.intValue() == 0) {
						throw new DataValidationException("Prescription Number not valid to be 0.");
					}
					
					Map<String, Object> prescriptionData = FindUtil.findPrescriptionDataByRxNum(connection, prescriptionNumber , storeNumber );
					Long prescriptionKey = (Long) prescriptionData.get("RXKEY");
					
					if(prescriptionKey == null){
						throw new RxNotFoundException(storeNumber,prescriptionNumber.toString());
					}
					
						upsertRefill(connection,storeNumber,prescriptionNumber,refill,prescriptionKey,custKey,userId);
					
				}
				
				

				RefillResponse upsertResponse = new RefillResponse();

				upsertResponse.setFlag(successFlag);
				upsertResponse.setCorrelationID(corrid);;
				if(logger.isDebugEnabled()) {logger.debug(": RefillDispensingapi.upsertRefillDispense. Corr ID : " + corrid + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}

				return upsertResponse;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally
			{
				super.close();
				if(refill != null)
				if (logger.isInfoEnabled())  {logger.info("EndApiCall: RefillDispensingapi.upsertRefillDispense. " );}
			}
		}
	
	/**
	 * Attempt to persist a Dispense instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Dispense doesn't exist, then insert a new record.
	 * If the Dispense exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param dispense			Dispense instance.
	 * 
	 * @return				DispenseKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 * @throws JAXBException 
	 */
	private void upsertRefill(Connection connection, String storeNumber, Integer prescriptionNumber,Refill refill,Long prescriptionKey,Long custKey,String userId ) throws SQLException, IOException, NamingException, CDRException, JAXBException {
	
		if(logger.isDebugEnabled()) {logger.debug("START upsertRefillDispense");}
		
		
		prescriptionList = refill.getPrescription();
		refillList = refill.getRefillRequest();
		//XMLGregorianCalendar pickupTime=refill.getRequestedPickupTime();
		PharmacyChannel sourceChannel= refill.getSourceChannel();
		
		RefillRequest refillRequest = refillList.get(0);
		String corrid = refillRequest.getCorrelationId();
		String channelType = sourceChannel.getChannelType().value();
		String comm=sourceChannel.getCommunicationMode().value();
		
		
		
		
		ps = connection.prepareStatement(MERGESQL);
		
		setPsLongParam(1, prescriptionKey);
		setPsStringParam(2, corrid);
		setPsStringParam(3, refillRequest.getFillRequestId());
		Long commTypKey = getKeyFromCode(LT_COMMMODETYP, comm);
		setPsLongParam(4, commTypKey);
		Long chaneelTypKey = getKeyFromCode(LT_PHARMACYCHNLTYP, channelType);
		setPsLongParam(5, chaneelTypKey);
		setPsStringParam(6, refillRequest.getBatchIdentifier());
		setPsStringParam(7, refillRequest.getFillRequestStatus());
		setPsStringParam(8, refillRequest.getFillRequestType());
		
		if(refillRequest.getLastModifiedTimestamp() == null && refillRequest.getRequestTimestamp() != null){
			setPsStringParam(9, CommonUtil.toTimestampStr(refillRequest.getRequestTimestamp()));
			setPsStringParam(10, CommonUtil.toTimestampStr(refillRequest.getRequestTimestamp()));
		}
		else if(refillRequest.getLastModifiedTimestamp() == null && refillRequest.getRequestTimestamp() == null){
			setPsStringParam(9, CommonUtil.getCurrentTimeStamp());
			setPsStringParam(10, CommonUtil.getCurrentTimeStamp());
		}
	//Praveen Modified 
		if (refillRequest.getPromisedTimestamp()!= null)
		{
			setPsStringParam(11, CommonUtil.toTimestampStr(refillRequest.getPromisedTimestamp()));
		}
		else{
			setPsStringParam(11, null);
		}
		/*
		setPsLongParam(11, refillRequest.getVersion());
		setPsStringParam(12, refillRequest.getVerificationState()!=null?refillRequest.getVerificationState().value():null);
		setPsLongParam(13, refillRequest.getRetryCount());
		setPsStringParam(14, refillRequest.getEventName()!=null ? refillRequest.getEventName().value() : null);
		setPsStringParam(15, storeNumber);
		setPsStringParam(16, prescriptionNumber.toString());
		Long engagementTypeKey = getKeyFromCode(LT_ENGMNTTYP, sourceChannel.getEngagment().value());
		setPsLongParam(17, engagementTypeKey);
		
		setPsLongParam(18, custKey);
		setPsStringParam(19, userId);
		*/
		setPsLongParam(12, refillRequest.getVersion());
		setPsStringParam(13, refillRequest.getVerificationState()!=null?refillRequest.getVerificationState().value():null);
		setPsLongParam(14, refillRequest.getRetryCount());
		setPsStringParam(15, refillRequest.getEventName()!=null ? refillRequest.getEventName().value() : null);
		setPsStringParam(16, storeNumber);
		setPsStringParam(17, prescriptionNumber.toString());
		Long engagementTypeKey = getKeyFromCode(LT_ENGMNTTYP, sourceChannel.getEngagment().value());
		setPsLongParam(18, engagementTypeKey);
		
		setPsLongParam(19, custKey);
		setPsStringParam(20, userId);
		//Praveen Modified 
		int res = ps.executeUpdate();
		
		
		if (logger.isInfoEnabled())  {logger.info("Total Refill Dispense intances updated: " + res + ". StoreNumber: " + storeNumber + ", Prescription Number: '" + prescriptionNumber.toString() + "', Corr ID: '" + corrid + "', PromisedTimestamp: '" +refillRequest.getPromisedTimestamp()+"'.");}
		
	}
	
}