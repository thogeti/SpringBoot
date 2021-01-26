package ca.sdm.cdr.jdbc.api.preference.upsert;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.AssociateCustomerException;
import com.sdm.cdr.exception.api.CustomerChannelNotFoundException;
import com.sdm.cdr.exception.api.CustomerPatientAssociationNotExistException;
import com.sdm.cdr.exception.api.CustomerTypeNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.upsert.api.CDRUpsert;
import ca.shoppersdrugmart.rx.trr.businesseventmessagetype.EntityTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.cse.EntityType;
import ca.shoppersdrugmart.rxhb.drx.cse.UpdatePreferenceEntityBySourceChannel;
import ca.shoppersdrugmart.rxhb.drx.cse.UserTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.preference.EntityPreference;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
public class PreferenceAPI extends CDRUpsert {

	private static final Logger logger = LogManager.getLogger("PreferenceAPI");
	//private final static String GETPHRCOMMENGKEYSQL = "select ct.CUSTKEY,ct.prsnkey ,cp.CDRPTNTMPNGKEY from CDRPTNTMPNG cp,cust ct where cp.custkey=ct.custkey and cp.ptntkey=?";
	private final static String GETPHRCOMMENGKEYSQL = "select ct.CUSTKEY,ct.PRSNKEY ,cp.CDRPTNTMPNGKEY from CDRPTNTMPNG cp left outer join cust ct on  cp.custkey=ct.custkey where  cp.ptntkey=?";
	private final static String INSERT_ENTPREF_A="MERGE INTO ENTPREF A USING (SELECT ? PTNTKEY, "
			+" ? RXKEY,? CDRPTNTMPNGKEY,? ENTTYP,? PHARMACYCHNLTYPKEY,? COMMMODETYPKEY, " 
			+" ? ENGAMNTTYPKEY,? ACTVFLG, "+CommonUtil.TO_TIMESTAMP_TZ +" EFFSTRTDT,"+CommonUtil.TO_TIMESTAMP_TZ +" EFFENDDT FROM DUAL ) B "  ;

	//+" ON ( A.CDRPTNTMPNGKEY =B.CDRPTNTMPNGKEY) " ;
						
	private final static String INSERT_ENTPREF_B=" WHEN MATCHED THEN " 
			+" UPDATE SET ENTTYP=B.ENTTYP, " 
			+" PHARMACYCHNLTYPKEY=B.PHARMACYCHNLTYPKEY, " 
			+" COMMMODETYPKEY=B.COMMMODETYPKEY,ENGAMNTTYPKEY=B.ENGAMNTTYPKEY "
			+" WHEN NOT MATCHED THEN	"
			+ " INSERT VALUES(ENTPREF_SEQ.nextval,B.PTNTKEY,B.RXKEY,B.CDRPTNTMPNGKEY,B.ENTTYP,B.PHARMACYCHNLTYPKEY,"
			+ "B.COMMMODETYPKEY,B.ENGAMNTTYPKEY,B.ACTVFLG,B.EFFSTRTDT,B.EFFENDDT)";
	
	private final static String INSERT_ENTPREF1="INSERT INTO ENTPREF"
			+ "VALUES(?,?,?,?,?,?,?,?,?,"+CommonUtil.TO_TIMESTAMP_TZ +","+CommonUtil.TO_TIMESTAMP_TZ+")";
	private final static String UPDATE_ENTPREF1=" UPDATE ENTPREF SET ENTTYP=?,PHARMACYCHNLTYPKEY=?,"
			+ "COMMMODETYPKEY=?,ENGAMNTTYPKEY=?  WHERE CDRPTNTMPNGKEY=?";
	/*private final static String UPDATE_PRSNPREF="UPDATE PRSNPREF SET ENTPREFKEY=?, GLBAUOTFILL = ? , "
			+ "RXAUTOFILL=? ,PICKUPRMNDR=?,LSTMDFTS="+CommonUtil.TO_TIMESTAMP_TZ + "where PRSNKEY=?";	*/
	

	private final static String UPDATE_PRSNPREF="MERGE INTO PRSNPREF A USING  "
	+" (SELECT ? ENTPREFKEY,? GLBAUOTFILL,? RXAUTOFILL,? PICKUPRMNDR, "+CommonUtil.TO_TIMESTAMP_TZ +" LSTMDFTS  FROM DUAL )B "
	+" ON (A.ENTPREFKEY=B.ENTPREFKEY) "
	+" WHEN MATCHED THEN "
	+" UPDATE SET "
	+" GLBAUOTFILL=B.GLBAUOTFILL, "
	+" RXAUTOFILL=B.RXAUTOFILL, "
	+" PICKUPRMNDR=B.PICKUPRMNDR, "
	+" LSTMDFTS=B.LSTMDFTS "
	+" WHEN NOT MATCHED THEN "
	+" INSERT (PRSNPREFKEY,ENTPREFKEY,GLBAUOTFILL,RXAUTOFILL,PICKUPRMNDR,LSTMDFTS)  VALUES (PRSNPREF_SEQ.nextval,B.ENTPREFKEY,B.GLBAUOTFILL,B.RXAUTOFILL,B.PICKUPRMNDR,B.LSTMDFTS) ";

	
	public void setEntityPreference(Connection connection, EntityPreference entityPreferenceRequest) 
			throws Exception{
	try {			
	
		String id=null;
		String storeNumber=null;
		List<UpdatePreferenceEntityBySourceChannel> preferences =entityPreferenceRequest.getEntityPreference();
		
		if( preferences != null && preferences.size()>0)
		{
	     for( UpdatePreferenceEntityBySourceChannel preference: preferences )
			{
	    	 
	    	 	validatePreference(preference);
				EntityType entityType =preference.getEntityType();
				id=preference.getEntityId();
				storeNumber = CommonUtil.createStoreLeadingZeros(preference.getStoreNumber());
				Long timer = System.currentTimeMillis();
				Long patientKey = null;
				Long rxkey=null;
				String entityVal=null;
				String rmndflg=null;
				if(preference.getAutoRefillFlag()!=null) {
					rmndflg=preference.getAutoRefillFlag();
				}else 
					if(preference.getPickupReminderFlag()!=null) {
					rmndflg=preference.getPickupReminderFlag();
				}
				
				/*if() {
					
				}
				*/
				if (logger.isInfoEnabled())  {logger.info("StartApiCall: PreferenceAPI.setEntityPreference. patientId : " + id
						+ ", storeNumber : " + storeNumber);}
				
			///Pickup_Reminderl_v0.1.txt	
			if ( entityType.value()== "Patient"){
				entityVal="P";
				patientKey= FindUtil.findPatientKey(connection, id, storeNumber);
				
				if (patientKey==null || patientKey.longValue() < 1) {
					// if no patient found , throws an exception 
					throw new PatientNotFoundException("No patient found for Patient: '" + id + "', StoreNum: '" + storeNumber + "'. ");
				}
			}		
			//RxAuto_Refill_v0.1.xml
			else if ( entityType.value()== "Rx"){
				entityVal="R";
				Map<String, Object> data=FindUtil.findPrescriptionDataByRxNum(connection, Integer.parseInt(id), storeNumber);
				patientKey=(Long) data.get("PTNTKEY");
				rxkey=(Long) data.get("RXKEY");
				if (rxkey==null || rxkey.longValue() < 1) {
					// if no patient found , throws an exception 
					throw new RxNotFoundException(storeNumber , id);
				}
			}
			
			Map<String,Long> ptnmpngdata= getCDRPTNTMappingDetails(connection,patientKey,id,entityType,storeNumber);
		   PharmacyChannel pharmacyChannel=preference.getSourceChannel();
		  Long ptntmappingkey= ptnmpngdata.get("CDRPTNTMPNGKEY");
		  /*if(ptntmappingkey != null){
			  throw new InvalidInputException("Patient " + id + " is already associated with the Customer");
		  }*/
		  Long psnkey= ptnmpngdata.get("PRSNKEY");
		 insertENTPreference(connection, patientKey, rxkey, entityVal, pharmacyChannel,ptntmappingkey);
		 Long entprefkey=FindUtil.findEntPref(connection, ptntmappingkey, patientKey, rxkey,entityVal);
		 updatePersonPreference(connection, entprefkey, entityVal, rmndflg,preference,psnkey);
		   	
		}
		}
		}
		
	catch(CDRInternalException e){
		e.printStackTrace();
		throw e;
	}catch (SQLException e) {
		e.printStackTrace();
		throw e;
	
	}  catch (NamingException e) {
		e.printStackTrace();
		throw e;
	} catch (IOException e) {
		e.printStackTrace();
		throw e;
	}
	finally
	{
		super.close();
		if (logger.isInfoEnabled())  {logger.info("EndsApiCall : preferenceApi");}
	}
	}
	
	
	
	private void validatePreference(UpdatePreferenceEntityBySourceChannel preference) throws InvalidInputException, NamingException, SQLException, IOException {
		String id = preference.getEntityId();
		EntityType entityType = preference.getEntityType();
		PharmacyChannel pharmacyChannel = preference.getSourceChannel();
		if(id==null ) {
			throw new InvalidInputException("EntityId  is mandatory for Preference ");
		}
		if(preference.getStoreNumber() ==null ||preference.getStoreNumber() =="") {
			throw new InvalidInputException("StoreNumber is mandatory for Preference ");
		}
		if(entityType==null ||entityType.value()==null) {
			throw new InvalidInputException("Invalid entry for EntityType entity Id "+id);
		}
		String entityTypeEnum = entityType != null ? entityType.name() : null;
		if (!FindEnum.contains(EntityTypeEnum.class, entityTypeEnum)) {
			throw new InvalidInputException("EntityType is not found entity Id:"+id);
		}
		
		if(entityType.value()== "Patient" &&(preference.getGlobalAutoFill() ==null && preference.getPickupReminderFlag()==null) &&
				(preference.getGlobalAutoFill() ==null || preference.getPickupReminderFlag()==null)) {
		if(preference.getPickupReminderFlag()==null) {
			throw new InvalidInputException("PickupReminderFlag is mandatory when EntityType is Patient");
		}else 
		if(preference.getGlobalAutoFill() ==null){
			throw new InvalidInputException("GlobalAutoFill is mandatory when EntityType is Patient");
		}
		}
		if(entityType.value()== "Rx" && preference.getAutoRefillFlag()==null) {
			throw new InvalidInputException("AutoRefillFlag is mandatory when EntityType is Prescription(Rx)");
		}
		
		
			  if(preference.getPickupReminderFlag()!=null && !(preference.getPickupReminderFlag().equals("1") || preference.getPickupReminderFlag().equals("2"))) {
			   throw new InvalidInputException("PickupReminderFlag should be either 1 or 2 ");
			  }
			  if(preference.getGlobalAutoFill()!=null && !(preference.getGlobalAutoFill().equals(1)|| preference.getGlobalAutoFill().equals(2))) {
				   throw new InvalidInputException("GlobalAutoFill should be either 1 or 2 ");
			  }
			  if(preference.getAutoRefillFlag()!=null && !(preference.getAutoRefillFlag().equals("1") || preference.getAutoRefillFlag().equals("2"))) {
				   throw new InvalidInputException("AutoRefillFlag should be either 1 or 2 ");
			  }
			  
		
		Long channelKey = null;
		if(pharmacyChannel != null){
			String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
			if(channelID != null){
		try {
			channelKey = getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
		} catch (KeyNotFoundFromTableCacheException e) {
			if(logger.isErrorEnabled()) {logger.error("The Preference Channel type in request is not valid for " + channelID);}
			throw new InvalidInputException("Preference Channel is not valid ");
		}
			}
			else{
				if(logger.isErrorEnabled()) {logger.error("The Preference Channel type in request is not valid for " + channelID);}
				throw new InvalidInputException("Preference Channel is not valid ");
			}
		}
		
		Long communicationKey = null;
		if(pharmacyChannel != null){
			String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
			if(communicationID != null){
		try {
			communicationKey = getKeyFromCode(LT_COMMMODETYP, communicationID);
		} catch (KeyNotFoundFromTableCacheException e) {
			if(logger.isErrorEnabled()) {logger.error("Preference Communication type in request is not valid for " + communicationID);}
			throw new InvalidInputException("Preference Communication Mode is not valid.");
		}
			}
			else{
				if(logger.isErrorEnabled()) {logger.error("Preference Communication type in request is not valid for " + communicationID);}
				throw new InvalidInputException("Preference Communication Mode is not valid.");
			}
		}
		
		Long engagementKey = null;
		if(pharmacyChannel != null){
			String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
			if(engID != null){
		try {
			engagementKey = getKeyFromCode(LT_ENGMNTTYP, engID);
		} catch (KeyNotFoundFromTableCacheException e) {
			if(logger.isErrorEnabled()) {logger.error("Preference Engagement type in request is not valid for " + engID);}
			throw new InvalidInputException("Preference Engagement type is not valid .");
		}
			}
			else{
				if(logger.isErrorEnabled()) {logger.error("Preference Engagement type in request is not valid for " + engID);}
				throw new InvalidInputException("Preference Engagement type is not valid.");
			}
		}

		
	}



	private Map<String,Long> getCDRPTNTMappingDetails(Connection connection,Long pntntkey, String id, EntityType entityType, String storeNumber) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException, CustomerPatientAssociationNotExistException {
		Long timer = System.currentTimeMillis();
		Map<String,Long> resultData=new HashMap<String,Long>();
		 Long ptntmappingkey= null;
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PreferenceAPI.getCDRPTNTMappingDetails. PTNTKEY : " + pntntkey );}
		ps = connection.prepareStatement(GETPHRCOMMENGKEYSQL);
		ps.setLong(1, pntntkey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PreferenceAPI.getCDRPTNTMappingDetails. PTNTKEY :" + pntntkey);}
		rs = ps.executeQuery();
		if(rs.next()) {
			resultData.put("CDRPTNTMPNGKEY",rs.getLong("CDRPTNTMPNGKEY"));
			resultData.put("PRSNKEY",rs.getLong("PRSNKEY"));
			resultData.put("CUSTKEY",rs.getLong("CUSTKEY"));
			if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PreferenceAPI.getCDRPTNTMappingDetails. Records found");}
		//throw new CustomerPatientAssociationNotExistException(id,entityType.value(),storeNumber);	
		}
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : PreferenceAPI.getCDRPTNTMappingDetails. PTNTKEY :" + pntntkey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: PreferenceAPI.getCDRPTNTMappingDetails. PTNTKEY : " + pntntkey + ",. Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	return resultData;
	}
	
	
	
		public Long insertENTPreference(Connection connection,Long pntntkey,Long rxkey,String entityType,PharmacyChannel pharmacyChannel,Long ptntmapkey) throws KeyNotFoundFromTableCacheException, NamingException, IOException, SQLException {
		Long res=0L;
		Long entprefkey =0L;
			try {
		//entprefkey = IdGenerator.generate(connection, "ENTPREF");
	//	String sql=	INSERT_ENTPREF_A + (entityType.equals("P")? " ON ( A.PTNTKEY =B.PTNTKEY) ": " ON ( A.RXKEY =B.RXKEY) ")+ INSERT_ENTPREF_B;
		 String sql = INSERT_ENTPREF_A + (entityType.equals("P") ? 
	  	              " ON ( A.PTNTKEY = B.PTNTKEY AND A.RXKEY IS NULL) ": 
	  		    	       " ON ( A.PTNTKEY = B.PTNTKEY AND A.RXKEY IS NOT NULL AND A.RXKEY = B.RXKEY) ") + INSERT_ENTPREF_B; 			
		
		ps = connection.prepareStatement(sql);
		//ps.setLong(1, entprefkey);
		ps.setLong(1, pntntkey);
		CommonUtil.setPsLongParam(ps, 2, rxkey);
		CommonUtil.setPsLongParam(ps, 3,ptntmapkey);
		//ps.setLong(3,ptntmapkey);
		ps.setString(4, entityType);
		Long chaneelTypKey = getKeyFromCode(LT_PHARMACYCHNLTYP, pharmacyChannel.getChannelType().value());
		ps.setLong(5, chaneelTypKey);
		Long commTypKey = getKeyFromCode(LT_COMMMODETYP, pharmacyChannel.getCommunicationMode().value());
		ps.setLong(6, commTypKey);
		Long engagementTypeKey = getKeyFromCode(LT_ENGMNTTYP, pharmacyChannel.getEngagment().value());
		ps.setLong(7, engagementTypeKey);
		ps.setString(8, "Y");
		ps.setString(9, CommonUtil.getCurrentTimeStamp());
		ps.setString(10, CommonUtil.getEndTimeStamp());
		res = (long) ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total ENTPreference intances updated: " + res + ". pntntkey: " + pntntkey + ", rxkey: '" + rxkey + "', entityType: '" + entityType + "'.");}
		} catch (SQLException e) {
		e.printStackTrace();
		 throw e;
		}
		return entprefkey;
		}

	
	private Long updatePersonPreference(Connection connection,Long entprefkey,String entitytype,String reminderFalg, UpdatePreferenceEntityBySourceChannel preference,Long prsnkey) throws SQLException, CDRInternalException {
		
		Long res=0L;
		try {
		ps = connection.prepareStatement(UPDATE_PRSNPREF);
		ps.setLong(1, entprefkey);
		CommonUtil.setPsLongParam(ps, 2, preference.getGlobalAutoFill());
		CommonUtil.setPsStringParam(ps, 3, entitytype.equals("R")?reminderFalg:null);
		CommonUtil.setPsStringParam(ps, 4, entitytype.equals("P")?reminderFalg:null);
		CommonUtil.setPsStringParam(ps, 5, CommonUtil.toTimestampStr(preference.getLastUpdateTimestamp()));
		//Long prsnkey=rs.getLong("prsnkey");
		//CommonUtil.setPsLongParam(ps, 6,prsnkey);
		//ps.setLong(6, prsnkey);
		res = (long) ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total ENTPreference intances updated: " + res + ". entprefkey: " + entprefkey + ", prsnkey: '" + prsnkey + "', entityType: '" + entitytype + "'.");}
		} catch (SQLException e) {
		e.printStackTrace();
		 throw new SQLException(e);
		}
		return res;
		
		
	}
	
			
}
