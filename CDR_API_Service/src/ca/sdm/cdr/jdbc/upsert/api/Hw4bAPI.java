package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_HW4BEVENTTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.HW4BException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.HW4BEventUpsert;
import ca.shoppersdrugmart.rxhb.ehealth.AlertFlag;
import ca.shoppersdrugmart.rxhb.ehealth.HW4BEvent;

public class Hw4bAPI extends CDRUpsert{
	private static final Logger logger = LogManager.getLogger("Hw4bAPI");
	private final static String UPSERTHW4BSQL = "MERGE INTO HW4B_EVENT a   " + 
			 "USING (SELECT  ? CNSMRID,? EVENT_TYPE, ? STORENUM,? PTNTID,? PTNTKEY, ? RXKEY,? RXNUM,? TXKEY,? TXNUM,? DIN,? SUBJECT_PLAN_ID,? SUBJECT_SPECIAL_PROGRAM_ID,? ALERT_FLAG,? PHARMACIST_PREF_ALT_DRG ,"+CommonUtil.getPsToDateFunctionStr()+" CREATE_DATETIME,? CREATE_USERID,"+CommonUtil.getPsToDateFunctionStr()+" CDRCRTTDT,"+CommonUtil.getPsToDateFunctionStr()+" CDRUPDTDT , ? HW4B_EVENT_TYP_KEY FROM dual) b " +
			 "	on(a.CNSMRID = b.CNSMRID and a.STORENUM = b.STORENUM) " + 
			 "	WHEN MATCHED THEN " + "   UPDATE SET EVENT_TYPE  = b.EVENT_TYPE,PTNTID = b.PTNTID,PTNTKEY = b.PTNTKEY,RXKEY = b.RXKEY , RXNUM = b.RXNUM, TXKEY = b.TXKEY, TXNUM = b.TXNUM, DIN = b.DIN, SUBJECT_PLAN_ID = b.SUBJECT_PLAN_ID, SUBJECT_SPECIAL_PROGRAM_ID = b.SUBJECT_SPECIAL_PROGRAM_ID, ALERT_FLAG = b.ALERT_FLAG, PHARMACIST_PREF_ALT_DRG = b.PHARMACIST_PREF_ALT_DRG ,CREATE_DATETIME = b.CREATE_DATETIME, CREATE_USERID = b.CREATE_USERID, CDRUPDTDT = b.CDRUPDTDT , HW4B_EVENT_TYP_KEY = b.HW4B_EVENT_TYP_KEY	WHEN NOT MATCHED THEN " +

			 "	insert (EVENT_KEY , CNSMRID , EVENT_TYPE ,STORENUM, PTNTID,PTNTKEY , RXKEY,RXNUM,TXKEY,TXNUM,DIN,SUBJECT_PLAN_ID,SUBJECT_SPECIAL_PROGRAM_ID,ALERT_FLAG,PHARMACIST_PREF_ALT_DRG ,CREATE_DATETIME, CREATE_USERID, CDRCRTTDT, CDRUPDTDT ,HW4B_EVENT_TYP_KEY  ) " + 
			 "	values(HW4B_EVENT_SEQ.nextval,b.CNSMRID , b.EVENT_TYPE , b.STORENUM , b.PTNTID ,b.PTNTKEY, b.RXKEY , b.RXNUM ,b.TXKEY,b.TXNUM,b.DIN,b.SUBJECT_PLAN_ID,b.SUBJECT_SPECIAL_PROGRAM_ID,b.ALERT_FLAG,b.PHARMACIST_PREF_ALT_DRG ,b.CREATE_DATETIME, b.CREATE_USERID, b.CDRCRTTDT, b.CDRUPDTDT , b.HW4B_EVENT_TYP_KEY )";

	private final static String QUERYHW4BINSERTSQL ="	insert INTO HW4B_EVENT(EVENT_KEY , CNSMRID , EVENT_TYPE ,STORENUM, PTNTID,PTNTKEY , RXKEY,RXNUM,TXKEY,TXNUM,DIN,SUBJECT_PLAN_ID,SUBJECT_SPECIAL_PROGRAM_ID,ALERT_FLAG,PHARMACIST_PREF_ALT_DRG ,CREATE_DATETIME, CREATE_USERID, CDRCRTTDT, CDRUPDTDT ) " + 
			 "	values(HW4B_EVENT_SEQ.nextval,? , ?,? , ?,?, ?, ? ,?,?,?,?,?,?,? ," + CommonUtil.TO_TIMESTAMP_TZ + ", ?," + CommonUtil.TO_TIMESTAMP_TZ + ", " + CommonUtil.TO_TIMESTAMP_TZ + " )";
	private final static String QUERYHW4BPTNTKEYSQL = " SELECT PTNT.PTNTKEY FROM PTNT WHERE PTNT.CNSMRID = ? AND PTNT.STORENUM = ? ";
	
	private final static String QUERYHW4BRXKEYSQL ="SELECT rx.RXKEY FROM RX WHERE UPPER(RX.CnsmrId) = UPPER(?) and UPPER(RX.StoreNum) = UPPER(?)";
	
	private final static String QUERYHW4BCOUNTSQL ="SELECT count (*) RECCOUNT FROM HW4B_EVENT HW WHERE HW.CNSMRID = ? and HW.STORENUM = ?";
	 
	//HW4BEventUpsert
	@SuppressWarnings("unused")
	public void upsertHW4BEvent(Connection connection, HW4BEventUpsert hw4BUpsert) 
			throws HW4BException,InvalidInputException,SQLException, DatatypeConfigurationException, 
			ParseException, NamingException, IOException, CDRInternalException, PatientNotFoundException,
			RxNotFoundException, KeyNotFoundFromTableCacheException {
		
		//
		String consumerId = null;			
		String storeNumber = null;
		/////
		
		String producerId = null;
		String eventType = null;
		
		String patientNumber = null;
		Long patientKey= 0L;
		//String patientKeyStr= null;
		Long prescriptionKey = 0L;
		//String prescriptionKeyStr = null;
		String prescriptionNumber = null;
		Long transactionKey = 0L;
		//String transactionKeyStr = null;
		String transactionNumber = null;
		String din = null;
		String planId = null;
		Long specialProgramId = 0L;
		
		String alertFlag = null;
		String alertFlagEnum = null;
		String pharmacistPreferredAlternateDrug = null;
		XMLGregorianCalendar  createTimestamp = null;
        String createUserId = null;
        Integer rxNumberInt= 0;
        Long rxKey = 0L;
		//
		
			HW4BEvent nHw4bEvent = new HW4BEvent();
			nHw4bEvent = hw4BUpsert.getPharmacyBusinessEvent().getBusinessEventPayload().getHw4BEvent();
			
			//consumerId
			consumerId= nHw4bEvent.getConsumerId();			

			if (consumerId == null || consumerId.trim().length() == 0) {
				throw new InvalidInputException("Consumer ID is mandatory for upsert HW4BEvent");
			}
			
			//storeNumber
			storeNumber= nHw4bEvent.getStoreNumber();		

			if (storeNumber == null || storeNumber.trim().length() == 0) {
				throw new InvalidInputException("storeNumber is mandatory for upsert HW4BEvent");
			}
			
			//&& alertFlag!=null
			if(consumerId!=null && storeNumber!=null ){
		    	 if (logger.isInfoEnabled())  {logger.info("StartsApicall : Hw4bAPI:upsertHW4BEvent : consumerId :" + consumerId );
					
		    	 logger.info("StartsApicall : Hw4bAPI:upsertHW4BEvent : storeNumber :" + storeNumber );}
									
		     }
			else {
				if (logger.isInfoEnabled())  {logger.info("StartsApicall : Hw4bAPI:upsertHW4BEvent : consumerId ,storeNumber null value");}
			}
			////&//////
			producerId = nHw4bEvent.getPatientNumber();
			eventType= nHw4bEvent.getEventType();
			patientNumber = nHw4bEvent.getPatientNumber();
			prescriptionNumber = nHw4bEvent.getPrescriptionNumber();
			 
			transactionNumber = nHw4bEvent.getTransactionNumber();
			 din = nHw4bEvent.getDin();
			 planId = nHw4bEvent.getPlanId();
			 //Hw4B 0527
			 //specialProgramId = new Long( nHw4bEvent.getSpecialProgramId());
			 
			 if((nHw4bEvent.getSpecialProgramId() != null))
			{
				 specialProgramId = new Long( nHw4bEvent.getSpecialProgramId());	 
			 }else{
				 specialProgramId = null;
			 }
			 
			 			 
			 //HW4B 0527
			 pharmacistPreferredAlternateDrug = nHw4bEvent.getPharmacistPreferredAlternateDrug();
			  createTimestamp = nHw4bEvent.getCreateTimestamp();
	         createUserId = nHw4bEvent.getCreateUserId();
	         if(logger.isDebugEnabled()) {logger.debug("createTimestamp:"+createTimestamp);}
	         
	       //patientNumber
	         if((patientNumber!= null) &&( patientNumber.trim().length() > 0) && !(patientNumber.isEmpty())){
		         patientKey = (FindUtil.findPatientKey(connection, patientNumber, storeNumber));
		          if(logger.isDebugEnabled()) {logger.debug("patientKey LONG *****:"+patientKey);}
		         if(patientKey == null || patientKey == 0L ) {
		        	 patientKey= 0L;	
		 		}	         
	         }
	         else{
	        	 if(patientNumber.isEmpty()){
	        		 patientNumber=null;
	        		 patientKey=0L;
	        	 }
	         }
	       //patientNumber
	         if(logger.isDebugEnabled()) {logger.debug("prescriptionKeyStr***1**Null:"+prescriptionNumber);}
	         
	         //prescriptionKey
	         /*if(prescriptionNumber == null){
        		 System.out.println(" prescriptionNumber NULL VALUES :"+prescriptionNumber);
        		 prescriptionNumber= null;
        		 prescriptionKey= 0L;
        	 }*/
		         if((prescriptionNumber!= null) &&( prescriptionNumber.trim().length() > 0) && !(prescriptionNumber.isEmpty())){
		        	 rxNumberInt= new Integer(nHw4bEvent.getPrescriptionNumber());
		         prescriptionKey = (FindUtil.findPrescriptionKeyByRxNum(connection, rxNumberInt, storeNumber));
		         
			         if(prescriptionKey == null || prescriptionKey == 0L ) {
			        	 prescriptionKey = 0L;
			 		}
		         
		         }
		         else{
		        //	 System.out.println(" prescriptionNumber NULL VALUES 1:"+prescriptionNumber);
		        	 //if(prescriptionNumber.isEmpty()) {
		        	 if( (prescriptionNumber == null) || (( prescriptionNumber.trim().length() == 0))){
		        	//	System.out.println(" prescriptionNumber NULL VALUES 2:"+prescriptionNumber);
		        		 prescriptionNumber= null;
		        		 prescriptionKey= 0L;
		        	 }
		        	 
		         }
		         
	         //prescriptionKey
	         //transactionKey
	         
		         if((transactionNumber!= null) &&( transactionNumber.trim().length() > 0) && !(transactionNumber.isEmpty())){
			         transactionKey = (FindUtil.findDispenseKeyByTxnNum(connection, transactionNumber, storeNumber));
			         if(transactionKey == null || transactionKey == 0L ) {
			        	 transactionKey = 0L;
			        
			 		}
			        
		         }
		         else{
		        	 //if(transactionNumber.isEmpty()){
		        	 if((transactionNumber== null)|| (( transactionNumber.trim().length() == 0))){
		        		 transactionNumber= null;
		        		 transactionKey =0L;
		        	 }
		         }
	         //transactionKey
	         
 		         //AlertFlag
		         try{
			          alertFlagEnum = nHw4bEvent.getAlertFlag() != null ? nHw4bEvent.getAlertFlag().name() : null;
						if(alertFlagEnum==null) {
							throw new InvalidInputException("AlertFlag is mandatory or invalid for upsert HW4BEvent");
						}
						if (!FindEnum.contains(AlertFlag.class, alertFlagEnum)) {
							if(logger.isDebugEnabled()) {logger.debug("AlertFlag " + alertFlagEnum + " not found");}
							throw new HW4BException(consumerId,storeNumber ,alertFlagEnum );
						}
			         }catch (HW4BException e) {
							if(logger.isDebugEnabled()) {logger.debug("The AlertFlag type in request is not valid for " + alertFlag);}
							throw new InvalidInputException("AlertFlag is invalid for upsert HW4BEvent");
						}
		         
		         //AlertFlag
		         
		      //   HW4B_EVENT_TYP_KEY  
		         Long hw4bEventKey =0L;
		         String hw4bEventCodeVal=null;
	         
	        	hw4bEventCodeVal = nHw4bEvent.getAlertFlag() != null ? nHw4bEvent.getAlertFlag().value() : null;
				hw4bEventKey = getKeyFromCode(LT_HW4BEVENTTYP,hw4bEventCodeVal) ;
				if(hw4bEventKey==null || hw4bEventKey == 0L) {
				 throw new InvalidInputException("AlertFlag is invalid for upsert HW4BEvent");
				 }
			  //HW4B_EVENT_TYP_KEY
	         String currentTMCreated= CommonUtil.getCurrentTimeStamp();	
	 		String currentTMUpdated= CommonUtil.getCurrentTimeStamp(); 		
	 		if(logger.isDebugEnabled()) {logger.debug("currentTMCreated:"+currentTMCreated);
	 		logger.debug("currentTMUpdated:"+currentTMUpdated);}
	 		
	         updateCDRHW4BEVENTTable(connection,consumerId,eventType,storeNumber,
	        		 patientNumber,patientKey,prescriptionKey,prescriptionNumber,transactionKey,transactionNumber,
	        		 din,planId,specialProgramId,alertFlagEnum,
	        		 hw4bEventKey,pharmacistPreferredAlternateDrug,createTimestamp,
	        		 createUserId,currentTMCreated,currentTMUpdated);
	        			
	}

		@SuppressWarnings("unused")
		private void updateCDRHW4BEVENTTable(Connection connection,String CNSMRID,String EVENT_TYPE,String STORENUM,
				String PTNTID,Long PTNTKEY,Long RXKEY,String RXNUM,Long TXKEY,String TXNUM,
        		 String DIN,String SUBJECT_PLAN_ID,Long SUBJECT_SPECIAL_PROGRAM_ID,String alertFlag,
        		 Long hw4bEventKey,String PHARMACIST_PREF_ALT_DRG,XMLGregorianCalendar CREATE_DATETIME,
        		 String CREATE_USERID,String CDRCRTTDT,String CDRUPDTDT )throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long timer = 0L;
		String NullVal = null;
		String dateStr= CommonUtil.getPsToDateFunctionStr();
		//String currentTMCreated= CommonUtil.getCurrentTimeStamp();	
		//String currentTMUpdated= CommonUtil.getCurrentTimeStamp();
		if(logger.isDebugEnabled()) {logger.debug("dateStr:"+dateStr);}
		//logger.debug("currentTM:"+currentTMCreated);
		try{
		timer = System.currentTimeMillis();
		ps = connection.prepareStatement(UPSERTHW4BSQL, new String[] { "EVENT_KEY" });
		//ps = connection.prepareStatement(QUERYHW4BINSERTSQL, new String[] { "EVENT_KEY" });
		
		//logger.trace("UpdateQuery : " + UPSERTCDRPTNTMPNG );
		//? CNSMRID,? EVENT_TYPE, ? STORENUM,? PTNTID, ? RXKEY,? RXNUM,? TXKEY,? TXNUM,? DIN,? SUBJECT_PLAN_ID,? SUBJECT_SPECIAL_PROGRAM_ID,? ALERT_FLAG,? PHARMACIST_PREF_ALT_DRG ,? CREATE_DATETIME,? CREATE_USERID,? CDRCRTTDT,? CDRUPDTDT
		CommonUtil.setPsStringParam(ps, 1, CNSMRID);
		//Drop57 hotfix for eventtype data type 
		//CommonUtil.setPsLongParam(ps, 2, EVENT_TYPE);
		CommonUtil.setPsStringParam(ps,2, EVENT_TYPE);
		
		//Drop57 hotfix for eventtype data type 
		CommonUtil.setPsStringParam(ps,3, STORENUM);
		CommonUtil.setPsStringParam(ps, 4, PTNTID);
		//CommonUtil.setPsStringParam(ps, 5, PTNTKEY);
		CommonUtil.setPsLongParam(ps, 5, PTNTKEY);
		//CommonUtil.setPsStringParam(ps, 6, RXKEY);
		CommonUtil.setPsLongParam(ps, 6, RXKEY);
		CommonUtil.setPsStringParam(ps, 7, RXNUM);
		//CommonUtil.setPsStringParam(ps, 8, TXKEY);
		CommonUtil.setPsLongParam(ps, 8, TXKEY);
		CommonUtil.setPsStringParam(ps, 9, TXNUM);
		CommonUtil.setPsStringParam(ps, 10, DIN);
		CommonUtil.setPsStringParam(ps, 11, SUBJECT_PLAN_ID);
		CommonUtil.setPsLongParam(ps, 12, SUBJECT_SPECIAL_PROGRAM_ID);
    	CommonUtil.setPsStringParam(ps, 13, alertFlag);
    
		CommonUtil.setPsStringParam(ps, 14, PHARMACIST_PREF_ALT_DRG);
		CommonUtil.setPsStringParam(ps, 15, CommonUtil.toTimestampStr(CREATE_DATETIME));
		//CommonUtil.setPsStringParam(ps, 15, NullVal);
		if(logger.isDebugEnabled()) {logger.debug("Before Insert:"+CommonUtil.toTimestampStr(CREATE_DATETIME));}
		
		CommonUtil.setPsStringParam(ps, 16, CREATE_USERID);
		
		CommonUtil.setPsStringParam(ps, 17, CDRCRTTDT);
		CommonUtil.setPsStringParam(ps, 18, CDRUPDTDT);		
		CommonUtil.setPsLongParam(ps, 19, hw4bEventKey);
					
	    //ps.executeUpdate();
	    
	    ////added for rec count
	    int res = ps.executeUpdate();
	    //System.out.println("*****************RES**************:"+res);
        rs = ps.getGeneratedKeys();
        Long primaryKey = 0L;
        if (rs.next()) {
            primaryKey = rs.getLong(1);
        }
        if (primaryKey > 0) {
        	//System.out.println("*****************Create**************"+primaryKey +":"+res);
        }
         else{
        	// System.out.println("*****************Update**************"+primaryKey+":"+res);
         }
	    ///added for rec count
	    super.close();
	    if(logger.isDebugEnabled()) {logger.debug("SQL execute time is : " + (System.currentTimeMillis() - timer) + " ms");}
	    }catch(SQLException e){
		 e.printStackTrace();
		 throw e;
		}
		
	}
		
		
		//count
		public Long countHW4BEvent(Connection connection, HW4BEventUpsert hw4BUpsert) 
				throws SQLException,InvalidInputException, KeyNotFoundFromTableCacheException, NamingException, IOException, PatientNotFoundException,RxNotFoundException, HW4BException  {
			
			String consumerId = null;
			
			String storeNumber = null;
			
	       
	        Long hw4bCount = 0L;
			
			HW4BEvent nHw4bEvent = hw4BUpsert.getPharmacyBusinessEvent().getBusinessEventPayload().getHw4BEvent();
			
			//consumerId
			consumerId= nHw4bEvent.getConsumerId();			
					
			if (consumerId == null || consumerId.trim().length() == 0) {
				throw new InvalidInputException("Consumer ID is mandatory for upsert HW4BEvent");
			}
			
			//storeNumber
			storeNumber= nHw4bEvent.getStoreNumber();		

			if (storeNumber  == null || storeNumber.trim().length() == 0) {
				throw new InvalidInputException("storeNumber is mandatory for upsert HW4BEvent");
			}
					
	 		
	 		
	 		if((consumerId!= null) && (storeNumber!= null)){
	 			hw4bCount = (FindUtil.findHW4BCount(connection, consumerId,storeNumber)); 
	 			if(logger.isDebugEnabled()) {logger.debug("HW4B record Count  :"+hw4bCount);}
	 		}
	         return hw4bCount;
		}
		
		
		
	
}
