package ca.sdm.cdr.jdbc.api.customer.query;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.AssociateCustomerException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.CustomerNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.SourceSystemNotValidException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.query.api.CDRGet;
import ca.shoppersdrugmart.rxhb.drx.cse.Customer;
import ca.shoppersdrugmart.rxhb.drx.cse.Preference;
import ca.shoppersdrugmart.rxhb.drx.cse.UserTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomerResponse;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.ChannelType;
import ca.shoppersdrugmart.rxhb.ehealth.CommunicationModeType;
import ca.shoppersdrugmart.rxhb.ehealth.EngagementType;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Store;

public class QueryCustomer extends CDRGet {
	
	private static Logger logger = LogManager.getLogger("CustomerService");
	private final static String LANGUAGE_ENG = "eng";
	private final static String LANGUAGE_FRA = "fra";

	private  static String GETCUSTOMERSQL = null;
	//SELECT c.CUSTKEY,count(cp.cdrptntmpngkey) as cdrptnt_count from CUST c left outer join cdrptntmpng cp on  c.custkey = cp.custkey WHERE CUSTID= ? AND CUSTTYP= ? group by c.custkey
private static String GET_CUSTKEYBYUSERID="SELECT C.CUSTKEY,C.PRSNKEY,COUNT(CP.CDRPTNTMPNGKEY) AS CDRPTNT_COUNT,C.PRSNKEY FROM CUST C LEFT OUTER JOIN CDRPTNTMPNG CP ON  C.CUSTKEY = CP.CUSTKEY  WHERE USERID= ? AND USERTYP= ? GROUP BY C.CUSTKEY,C.PRSNKEY ";
private static String GET_CUSTKEYBYCUSTID="SELECT C.CUSTKEY,C.PRSNKEY,COUNT(CP.CDRPTNTMPNGKEY) AS CDRPTNT_COUNT,C.PRSNKEY FROM CUST C LEFT OUTER JOIN CDRPTNTMPNG CP ON  C.CUSTKEY = CP.CUSTKEY WHERE CUSTID= ? AND CUSTTYP= ? GROUP BY C.CUSTKEY,C.PRSNKEY ";
private static String GET_CUSTKEYBYCUSTIDPRG="SELECT C.CUSTKEY,COUNT(M.CDRPTNTMPNGKEY) AS CDRPTNT_COUNT,C.PRSNKEY FROM CUST C LEFT OUTER JOIN CDRPTNTMPNG M ON C.CUSTKEY = M.CUSTKEY and M.ASSOCFLAG = 'Y' WHERE  C.USERID = ? AND C.USERTYP = 'Customer' AND M.ASSOCTYP = ? GROUP BY C.CUSTKEY,C.PRSNKEY ";
//This method should return CustKey . ams
		public Map getCustkeyByPatientId(Connection connection,String storeNumber, String patientId ) throws Exception {
			Map<String,String> custKeyPrsnKeyMap = new HashMap<String,String>();
			custKeyPrsnKeyMap = FindUtil.findCustKey(connection,storeNumber,patientId);
			return custKeyPrsnKeyMap;
			
		}
		
	
		public List<Customer>  getCustomer(Connection connection, GetCustomer getcustomer) throws Exception {
			List<Customer>  customer=null;
			try {
				String cust_user_id=null!=getcustomer.getUserId()?getcustomer.getUserId():null;
				String userType=null!=getcustomer.getUserType()?getcustomer.getUserType().value():null;
				String storenum=null!=getcustomer.getStoreNumber()?getcustomer.getStoreNumber():null;
				String patientId=null!=getcustomer.getPatientId()?getcustomer.getPatientId():null;
				
				Long custkey=null;
				Integer cdrPtntCount  = 0;
				Long prsnKey = null;
				Map custMap = null;
				//custid =PreRegCustomer   custid comes; other wise userid coming from incoming payload
			    //validateCustomer(getcustomer);
				if((cust_user_id != null && storenum != null && patientId !=null) ||(cust_user_id != null)){
					if(userType==null) {
						if(logger.isErrorEnabled()) {logger.error("User Type  is  required/invalid");}
						throw new InvalidInputException("User Type  is  required/invalid");
					}
				}
				if(cust_user_id == null && patientId !=null && storenum ==null ) {
					if(logger.isErrorEnabled()) {logger.error("Store Number is  required");}
					throw new InvalidInputException("Store Number is  required");
				}
				if(cust_user_id == null && patientId ==null && storenum !=null ) {
					if(logger.isErrorEnabled()) {logger.error("Patient Id is  required");}
					throw new InvalidInputException("Patient Id is  required");
				}
				//|| (cust_user_id != null && storenum != null && patientId !=null && userType!=null)
				
			    if((cust_user_id != null && userType!=null)){
			    if(!FindEnum.contains(UserTypeEnum.class, getcustomer.getUserType().name())) {
			    	if(logger.isErrorEnabled()) {logger.error("Invalid User Type  User type " + userType);}
					throw new CustomerNotFoundException(null, null,cust_user_id,null);
			    }
			    if(userType.equals("PreRegCustomer")) {
			    	custMap=getCustKey(connection, GET_CUSTKEYBYCUSTID, cust_user_id, userType);
			    	if(!custMap.isEmpty()) {
			    	custkey = (Long) custMap.get("custKey");
			    	cdrPtntCount = (Integer) custMap.get("cdrPtntMpngCount");
			    	prsnKey = (Long) custMap.get("prsnKey");
			    	}
					if(custkey ==null) {
				    	if(logger.isErrorEnabled()) {logger.error("customer not found for Cust Id " + cust_user_id + ", User type " + userType);}
						throw new CustomerNotFoundException(null, null,cust_user_id,null);
				    }
				}else 
				
				if(userType.equals("Customer")) {
					custMap=getCustKey(connection, GET_CUSTKEYBYUSERID, cust_user_id, userType);
					if(!custMap.isEmpty()) {
				    	custkey = (Long) custMap.get("custKey");
				    	cdrPtntCount = (Integer) custMap.get("cdrPtntMpngCount");
				    	prsnKey = (Long) custMap.get("prsnKey");
				    	}
					if(custkey ==null) {
				    	if(logger.isErrorEnabled()) {logger.error("customer not found for User Id " + cust_user_id + ", User type " + userType);}
						throw new CustomerNotFoundException(null, null,null,cust_user_id);
				    }
				}
				else 
				if(userType.equals("Caregiver")) {
					custMap=getCustKey(connection, GET_CUSTKEYBYCUSTIDPRG, cust_user_id, userType);
					if(!custMap.isEmpty()) {
				    	custkey = (Long) custMap.get("custKey");
				    	cdrPtntCount = (Integer) custMap.get("cdrPtntMpngCount");
				    	prsnKey = (Long) custMap.get("prsnKey");
				    	}
					if(custkey ==null) {
				    	if(logger.isErrorEnabled()) {logger.error("customer not found for User Id " + cust_user_id + ", User type " + userType);}
						throw new CustomerNotFoundException(null, null,null,cust_user_id);
				    }
				}
			    if(cdrPtntCount > 0) {
			    	if(userType.equals("Caregiver")){
			    		customer=getCustomerByCustKeyCaregiver(connection, custkey,userType);
			    	}else if(userType.equals("Customer")|| userType.equals("PreRegCustomer")){
			    		customer=getCustomerByCustKeyCustomer_PreRegCustomer(connection, custkey,userType);
			    	}
			    		
			    }
			    else {
			    	customer=getCustomerWithOutPtnt(connection, custkey,userType,prsnKey);
			    }
			    if(customer ==null || customer.size()==0) {
			    	if(logger.isErrorEnabled()) {logger.error("customer not found for User Id " + cust_user_id + ", User type " + userType);}
					throw new CustomerNotFoundException(null, null,null,cust_user_id);
				}
			    }else
			    	if(storenum != null && patientId !=null){
			    			customer =getCustomerByCustPtnt(connection, getcustomer);
			    	if(customer ==null || customer.size()==0) {
						if(logger.isErrorEnabled()) {logger.error("customer not found for store number " + storenum + ", patient id " + patientId);}
						throw new CustomerNotFoundException(String.valueOf(storenum), patientId,null,null);
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
				if (logger.isInfoEnabled())  {logger.info("EndsApiCall : associateCustomer");}
			}
			return customer;
		}
		
		
		


		private Map getCustKey(Connection connection, String sql,String id,String type) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SourceSystemNotValidException {
			
			Long custkey=null;
			Integer cdrPtntCount = 0;
			Long prsnkey = null;
			Map custMap = new HashMap();
			PreparedStatement ps = null ;
			ResultSet rs = null;
				try {
				ps = connection.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, id);  //preparedStatement.setLong(1, prsnKey);
				CommonUtil.setPsStringParam(ps, 2, type); //preparedStatement.setLong(2, prsnKey);
				rs = ps.executeQuery();
				if(rs.next()) {
					custkey=rs.getLong("CUSTKEY");
					cdrPtntCount = rs.getInt("CDRPTNT_COUNT");
					prsnkey = rs.getLong("PRSNKEY");
					custMap.put("custKey", custkey);
					custMap.put("cdrPtntMpngCount", cdrPtntCount);
					custMap.put("prsnKey", prsnkey);
				}
				}			
			 catch (SQLException e) {
			throw new SQLException(e);
			}finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
			return custMap;
		}
	
			
			
			private List<Customer>  getCustomerByCustPtnt(Connection connection, GetCustomer getcustomer) throws SQLException, NamingException, IOException, InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SourceSystemNotValidException, ParseException, DatatypeConfigurationException, CodeNotFoundFromTableCacheException, CDRInternalException {
				Long custkey = null;
				String storeNumber = getcustomer.getStoreNumber();
				storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);  
				String patientId = getcustomer.getPatientId();
				String usertype=null!=getcustomer.getUserType()?getcustomer.getUserType().value():null;
				if(usertype ==null) {
					usertype="ALL";
				}
				List<Customer> customer = null;  
				PreparedStatement ps =null;
				ResultSet rs = null;
				try {
					GETCUSTOMERSQL=TableCacheSingleton.getResource("GetCustomerQueryByPtntId.sql");
					ps = connection.prepareStatement(GETCUSTOMERSQL);
					CommonUtil.setPsLongParam(ps, 1, storeNumber);  //preparedStatement.setLong(1, prsnKey);
					CommonUtil.setPsLongParam(ps, 2, patientId); //preparedStatement.setLong(2, prsnKey);
					CommonUtil.setPsStringParam(ps, 3, usertype);
					CommonUtil.setPsStringParam(ps, 4, usertype);
					rs = ps.executeQuery();
					customer=scan(custkey,rs);
					
					}			
				 catch (SQLException e) {
				throw  new SQLException(e);
				}finally {
					CommonUtil.closePreparedStatementResultSet(ps, rs);
				}
				return customer;
			}
		
		private List<Customer>  getCustomerByCustKeyCustomer_PreRegCustomer(Connection connection, Long  custKey,String usertype) throws SQLException, NamingException, IOException, InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SourceSystemNotValidException, ParseException, DatatypeConfigurationException, CodeNotFoundFromTableCacheException, CDRInternalException {
			
			
			List<Customer>  customer = null;  
			PreparedStatement ps =null;
			ResultSet rs=null ;
			try {
							
				GETCUSTOMERSQL=TableCacheSingleton.getResource("GetCustomerQueryByCustKeyCustomer_PreRegCustomer.sql");
				
				 ps = connection.prepareStatement(GETCUSTOMERSQL);
				
				
				////CommonUtil.setPsStringParam(preparedStatement, 1, usertype);//preparedStatement.setLong(3, custKey);
				//CommonUtil.setPsStringParam(preparedStatement, 2,usertype );
				//CommonUtil.setPsStringParam(preparedStatement, 3,usertype );
				CommonUtil.setPsLongParam(ps, 1, custKey);
				if(usertype.trim().equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType())) {
					CommonUtil.setPsStringParam(ps, 2,usertype);
					CommonUtil.setPsStringParam(ps, 3,"N");
				}
				else if(usertype.trim().equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())) {
					CommonUtil.setPsStringParam(ps, 2,usertype);
					CommonUtil.setPsStringParam(ps, 3,"Y");
				}
				
				CommonUtil.setPsLongParam(ps, 4, custKey);
				/*CommonUtil.setPsStringParam(preparedStatement, 4,usertype=="Caregiver"?"Caregiver":"Customer" );
				CommonUtil.setPsStringParam(preparedStatement, 5,"Customer");*/
				rs = ps.executeQuery();
				customer=scan(custKey,rs);
												
			}
			
			 catch (SQLException e) {
					throw  new SQLException(e);
					}finally {
						CommonUtil.closePreparedStatementResultSet(ps, rs);
					}
			return customer;
		}
		
		private List<Customer>  getCustomerByCustKeyCaregiver(Connection connection, Long  custKey,String usertype) throws SQLException, NamingException, IOException, InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SourceSystemNotValidException, ParseException, DatatypeConfigurationException, CodeNotFoundFromTableCacheException, CDRInternalException {
			
			
			List<Customer>  customer = null;  
			PreparedStatement ps =null;
			ResultSet rs =null;
			try {
							
				GETCUSTOMERSQL=TableCacheSingleton.getResource("GetCustomerQueryByCustKeyCaregiver.sql");
				
				ps = connection.prepareStatement(GETCUSTOMERSQL);
				
				
			//	CommonUtil.setPsStringParam(preparedStatement, 1, usertype);//preparedStatement.setLong(3, custKey);
			//	CommonUtil.setPsStringParam(preparedStatement, 2,usertype );
				//CommonUtil.setPsStringParam(preparedStatement, 3,usertype );
				CommonUtil.setPsLongParam(ps, 1, custKey);
				CommonUtil.setPsLongParam(ps, 2, custKey);
				CommonUtil.setPsLongParam(ps, 3, custKey);
				/*CommonUtil.setPsStringParam(preparedStatement, 4,usertype=="Caregiver"?"Caregiver":"Customer" );
				CommonUtil.setPsStringParam(preparedStatement, 5,"Customer");*/
				rs = ps.executeQuery();
				customer=scan(custKey,rs);
												
			}
			
			 catch (SQLException e) {
					throw  new SQLException(e);
					}finally {
						CommonUtil.closePreparedStatementResultSet(ps, rs);
					}
			return customer;
		}

		private List<Customer>  getCustomerWithOutPtnt(Connection connection, Long  custKey,String usertype,Long prsnkey) throws SQLException, NamingException, IOException, InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SourceSystemNotValidException, ParseException, DatatypeConfigurationException, CodeNotFoundFromTableCacheException, CDRInternalException {
			
			
			List<Customer>  customer = null;  
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				
					GETCUSTOMERSQL=TableCacheSingleton.getResource("GetCustomerQueryByWithOutPatient.sql");
				
				ps = connection.prepareStatement(GETCUSTOMERSQL);
				
				CommonUtil.setPsLongParam(ps, 1, prsnkey);
				CommonUtil.setPsLongParam(ps, 2, custKey);
				CommonUtil.setPsStringParam(ps, 3, usertype);//preparedStatement.setLong(3, custKey);
				
			//	CommonUtil.setPsLongParam(preparedStatement, 4, custKey);
				rs = ps.executeQuery();
				customer=scan(custKey,rs);
												
			}
			
			 catch (SQLException e) {
					throw  new SQLException(e);
					}finally {
						CommonUtil.closePreparedStatementResultSet(ps, rs);
					}
			return customer;
		}
		
		private List<Customer> scan(Long custKeyMain,ResultSet resultSet) throws SQLException, ParseException, DatatypeConfigurationException, CodeNotFoundFromTableCacheException, CDRInternalException, IOException, NamingException {
			Customer customer = null;;  
			Preference preference = null;
			List<Customer> custList = new ArrayList<Customer>();
			Map<Long,Long> custmap=new HashMap<Long,Long>();
			boolean customerFlag = false;
			try {
				int i=0;
				
				while(resultSet.next()){
					customerFlag = true;
					Long custkey=resultSet.getLong("custkey");
					if(custmap.isEmpty()) {
						//57 57
						custmap.put(custkey, custkey);
						customer=new Customer();
					}
					//58 /58
					if(!custmap.containsKey(custkey)) {
						custList.add(customer);
						custmap.put(custkey, custkey);
						customer=new Customer();
						i=0;
					}
					
								
					
					if(i==0) {
							
					customer.setCustomerid(resultSet.getString("custid"));
					customer.setUserId(resultSet.getString("userid"));
					customer.setUserType(UserTypeEnum.fromValue(resultSet.getString("userType")));
					customer.setCreationTimestamp(CommonUtil.getCalendar("CRTTIMESTAMP",resultSet));
					String language = resultSet.getString("LANGUAGE_PREFERENCE");
					customer.setLanguage(language);
					//customer.getPatientid().add(patientId);
					
					customer.setOptimumNumber(resultSet.getString("OPTIMUM_NUMBER"));
					customer.setDeactivationReason(resultSet.getString("DEACTIVATION_REASON"));
					customer.setDetailedNotification(resultSet.getInt("DETAILED_NOTIFICATION"));
					customer.setRequestSource(resultSet.getString("REQUEST_SOURCE"));
					//customer.setPhonenumber(resultSet.getString("PHONE_TELECOMNUM"));
					
					
					//Set Person
					Person person = new Person();
					person.setFirstName(resultSet.getString("FIRST_NAME"));
					person.setLastName(resultSet.getString("LAST_NAME"));
					person.setDateOfBirth(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("dtofbirth")));	
					person.setDeceaseddate(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("DTOFDECEASE")));	
					customer.setPerson(person);
					customer.setRegistrationReminderId(resultSet.getString("REGISTRATION_REMINDER_ID"));
					//traversrPersonAddress(resultSet,addr,person);
					//Set Preference
					preference = new Preference();
					preference.setEmailNotification(resultSet.getString("EMAIL_NOTIFICATION"));
					preference.setEmailFillNotification(resultSet.getString("EMAIL_FILL_NOTIFICATION"));
					preference.setSmsNotification(resultSet.getString("SMS_NOTIFICATION"));
					preference.setEmailPickupNotification(resultSet.getString("EMAIL_PICKUP_NOTIFICATION"));
					preference.setEmailMarketing(resultSet.getString("MARKETING_EMAIL"));
					preference.setSmsFillNotification(resultSet.getString("SMS_FILL_NOTIFICATION"));
					preference.setSmsPickupNotification(resultSet.getString("SMS_PICKUP_NOTIFICATION"));
					customer.setPreference(preference);
					
					PharmacyChannel pharmacyChannel = new PharmacyChannel();
					String pharmacyChannelTypeCode = getCodeFromKey(LT_PHARMACYCHNLTYP, resultSet.getLong("PHARMACYCHNLTYPKEY"));
					pharmacyChannel.setChannelType(ChannelType.fromValue(pharmacyChannelTypeCode));
					
					
					String engagementTypeCode = getCodeFromKey(LT_ENGMNTTYP, resultSet.getLong("ENGMNTTYPKEY"));
					pharmacyChannel.setEngagment(EngagementType.fromValue(engagementTypeCode));
					
					String communicationModeCode = getCodeFromKey(LT_COMMMODETYP, resultSet.getLong("COMMMODETYPKEY"));
					pharmacyChannel.setCommunicationMode(CommunicationModeType.fromValue(communicationModeCode));
								
					customer.setSourceChannel(pharmacyChannel);
					//customer.set
					customer.setEmail(resultSet.getString("EMAIL_EMAILADDR"));
					customer.setPhonenumber(resultSet.getString("PHONE_TELECOMNUM"));
					
					
					
					
					
					}
					Patient ptnt=null;
					
					String storenum=resultSet.getString("storenum");
					String ptntid=resultSet.getString("patientID");
								
					if(storenum!=null && ptntid != null && !"0000".equals(storenum) && !"0000".equals(ptntid)) {
						ptnt=new Patient();
						Store str=new Store();
						str.setStorenumber(storenum);
						ptnt.setStore(str);
						ptnt.setConsumerId(ptntid);
						PharmacyChannel verificationChannel = new PharmacyChannel();
						String pharmacyChannelTypeCode = getCodeFromKey(LT_PHARMACYCHNLTYP, resultSet.getLong("VRFPHARMACYCHNLTYPKEY"));
						verificationChannel.setChannelType(ChannelType.fromValue(pharmacyChannelTypeCode));
						String engagementTypeCode = getCodeFromKey(LT_ENGMNTTYP, resultSet.getLong("VRFENGMNTTYPKEY"));
						verificationChannel.setEngagment(EngagementType.fromValue(engagementTypeCode));
						String communicationModeCode = getCodeFromKey(LT_COMMMODETYP, resultSet.getLong("VRFCOMMMODETYPKEY"));
						verificationChannel.setCommunicationMode(CommunicationModeType.fromValue(communicationModeCode));
					    ptnt.setVerificationChannel(verificationChannel);
						customer.getPatient().add(ptnt);
				
					}
					else if(!custKeyMain.equals(custkey)){
						PharmacyChannel verificationChannel = new PharmacyChannel();
						String pharmacyChannelTypeCode = getCodeFromKey(LT_PHARMACYCHNLTYP, resultSet.getLong("VRFPHARMACYCHNLTYPKEY"));
						verificationChannel.setChannelType(ChannelType.fromValue(pharmacyChannelTypeCode));
						String engagementTypeCode = getCodeFromKey(LT_ENGMNTTYP, resultSet.getLong("VRFENGMNTTYPKEY"));
						verificationChannel.setEngagment(EngagementType.fromValue(engagementTypeCode));
						String communicationModeCode = getCodeFromKey(LT_COMMMODETYP, resultSet.getLong("VRFCOMMMODETYPKEY"));
						verificationChannel.setCommunicationMode(CommunicationModeType.fromValue(communicationModeCode));
						customer.setVerificationChannel(verificationChannel);
						
				
					}
					i++;
					
				}
				if(customerFlag) {
				custList.add(customer);
				}
				
				
				

			
			}  catch (SQLException e) {
				throw  new SQLException(e);
				}
			return custList;
		}

		
		private void traversrPersonAddress(ResultSet rs,Address thisRow,Person person) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
			
			
			List<String> alterPhonesSet =  new ArrayList<String>();
			
			if (rs.getString( "CM_CNSMRID") != null &&
				rs.getString( "CM_CNSMRID").length() > 1) {
				PopulateJAXB.PopulateAddress46(rs, "", thisRow,alterPhonesSet);
				 person.getAddress().add(thisRow);
				 
			}
	        
			
		}
		
		
		

}
