package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNTCTMTHDTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.ContactMethodRoleType;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.ContactMethodType;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.TelecomType;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ReauthFaxFlag;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
TE99                                 
*/

//public class ContactMethodApi extends CDRUpsert {
public class ContactMethodApi {

	final static Logger logger = LogManager.getLogger(ContactMethodApi.class);
	private final static String UPDATEADDSQL = "UPDATE ADDR SET " + "		   (ADDRLNONE,	"
			+ "			ADDRLNTWO,	" + "			CITYNM,		" + "			PROVCD,		"
			+ "			CNTRYCD,	" + "			POSTALCD) =	" + "	 (SELECT ?, ?, ?, ?, ?, ? FROM DUAL) "
			+ "  WHERE ADDRKEY = ? ";

	private final static String INSERTTELSQL = "INSERT INTO TELECOM (  " + "				TELFAXIND,	"
			+ "				CNTRYCD,	" + "				TELECOMNUM,	" + "				TELECOMKEY)	"
			+ "	 VALUES (?, ?, ?, ?)";

	// VL99 Started
	private final static String UPDATETELSQL = "UPDATE TELECOM	SET TELFAXIND  = ?,	"
			+ "			        CNTRYCD    = ?,	" + "			        TELECOMNUM = ?  "
			+ " WHERE TELECOMKEY = ?              ";

	private final static String UPDATE_cntctmthd = "update cntctmthd set CNTCTPRPSTYPKEY = ?, "
			+ "                     CNTCTPRPSTYPCD  = ?  " + " where cntctmthdkey = ?";
	// VL99 Ended

	private final static String INSERTEMAILSQL = "INSERT INTO EMAIL (  " + "				EMAILADDR,	"
			+ "				EMAILKEY)	" + "	 VALUES (?, ?)";

	private final static String INSERTADDSQL = " INSERT INTO ADDR (  " + "			ADDRLNONE,	"
			+ "			ADDRLNTWO,	" + "			CITYNM,		" + "			PROVCD,		"
			+ "			CNTRYCD,	" + "			POSTALCD,	" + "			ADDRKEY)	"
			+ "	 VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	
	//TE97_Drop53  New Prescriber Logic Introduced start FLAGUPDTTIMESTAMP is passing null.	 
private final static String  INSERT_PRESCRIBER_ADDRESS_FLAG1="insert into PRESCRIBER_ADDRESS_FLAG "
		+ "	 (PRESCRIBERADDRKEY,PRSCBRKEY,PRESCRIBER_ADDRESS_CNSMRID,STORENUM,"
		+ "CRTTIMESTAMP,UPDTTIMESTAMP";
		private final static String  INSERT_PRESCRIBER_ADDRESS_FLAG2= "  values(?,?,?,?,"
		+ CommonUtil.TO_TIMESTAMP_TZ +","+ CommonUtil.TO_TIMESTAMP_TZ ;

private final static String  UPDATE_PRESCRIBER_ADDRESS_FLAG_QUERY1="update PRESCRIBER_ADDRESS_FLAG SET";
		

private final static String  UPDATE_PRESCRIBER_ADDRESS_FLAG_QUERY2="UPDTTIMESTAMP= "+ CommonUtil.TO_TIMESTAMP_TZ 
        +",FLAGUPDTTIMESTAMP="+ CommonUtil.TO_TIMESTAMP_TZ +",PRESCRIBER_ADDRESS_CNSMRID =?"
		+ " where PRESCRIBERADDRKEY= ? and PRSCBRKEY=?";
		//+ "and PRESCRIBER_ADDRESS_CNSMRID=?";


private final static String ADDRFLAGBEFORE ="select PRESCRIBERADDRKEY addrkey,MAILFLG,REAUTHEMAILFLAG,REAUTHPHONEFLAG,REAUTHFAXFLAG,REAUTHVISITFLAG"
		+ " from PRESCRIBER_ADDRESS_FLAG where PRSCBRKEY = ? "
		+ " and (PRESCRIBER_ADDRESS_CNSMRID is null or PRESCRIBER_ADDRESS_CNSMRID = ?) and storenum=?";


//TE97_Drop53  New Prescriber Logic Introduced end.

	public void upsertContactMethodByPatientKey(Connection connection, String storeNumber, Long patientKey,
			Person person) throws KeyNotFoundFromTableCacheException, ConsumerIdMissingException, SQLException,
			IOException, NamingException {
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertContactMethodByPatientKey. store number " + storeNumber + ", patientKey : "
				+ patientKey);}

		// VL99 upsertContactMethod(connection, storeNumber, patientKey, null,
		// person.getAddress().get(0) ,ContactMethodRoleType.PATIENT);
		List<Address> addresses = person.getAddress();
		for (Address currAddr : addresses) {
			upsertContactMethod(connection, storeNumber, patientKey, null, currAddr, ContactMethodRoleType.PATIENT,null);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertContactMethodByPatientKey. store number " + storeNumber + ", patientKey : "
				+ patientKey);}
	}

	public Long upsertContactMethodByPersonKey(Connection connection, String storeNumber, Long patientKey,
			PersonRole personrole, Long personRoleKey) throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException,
			ConsumerIdMissingException {
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertContactMethodByPersonKey. store number " + storeNumber + ", patientKey : "
				+ patientKey);}
		Long presciberAddressKey=null;
		// VL99 upsertContactMethod(connection, storeNumber, patientKey, null,
		// person.getAddress().get(0) , ContactMethodRoleType.PERSON);
		Person person=personrole.getPerson();
		List<Address> addresses = person.getAddress();
		ContactMethodApi contactMethodApi = new ContactMethodApi();
	if(addresses.size()>0){
		for (Address currAddr : addresses) {
		upsertContactMethod(connection, storeNumber, patientKey, null, currAddr, ContactMethodRoleType.PERSON,personrole.getRole());
		presciberAddressKey=contactMethodApi.upsertPrescriberAddressFlag(connection, currAddr, ContactMethodRoleType.PERSON, personrole.getRole(), storeNumber, personRoleKey);
		}
	}else{
			presciberAddressKey=contactMethodApi.upsertPrescriberAddressFlag(connection, null, ContactMethodRoleType.PERSON, personrole.getRole(), storeNumber, personRoleKey);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertContactMethodByPersonKey. store number " + storeNumber + ", patientKey : "
				+ patientKey);}
		return presciberAddressKey;
	}
	
	
	//TE97_Drop53  New Prescriber Logic Introduced start.
	//Upsert ---Address.Consumerid,addrkey and PrescriberKey.

	private Long insertPrescriberAddressFlag(Connection connection, Long prsnlkey,
			Address inputAddress, String storeNumber) throws SQLException,
			KeyNotFoundFromTableCacheException, IOException, NamingException {
		Long res = 0L;
		Long addresskey = 0L;
String addrCnsmrid  = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
		if(inputAddress !=null && inputAddress.getConsumerId()!=null)
		{
			addrCnsmrid = inputAddress.getConsumerId();
		}
		ps= connection.prepareStatement(ADDRFLAGBEFORE);
		CommonUtil.setPsLongParam(ps, 1, prsnlkey);
	
		CommonUtil.setPsStringParam(ps, 2, null!=inputAddress?inputAddress.getConsumerId():"");
		CommonUtil.setPsStringParam(ps, 3, storeNumber);
		rs = ps.executeQuery();
		if(!rs.next()){
		
			 addresskey = IdGenerator.generate(connection, "PRESCRIBER_ADDRESS_FLAG");
			 
				StringBuilder  prescriberAddrFlgInsertQuery=new StringBuilder();
				StringBuilder  prescriberAddrFlgVlauesQuery=new StringBuilder();
//				prescriberAddrFl1= null &&gUpdateQuery.append("update PRESCRIBER_ADDRESS_FLAG set ");
				if(inputAddress !=null && inputAddress.isIsMailAddressFlag()!= null){
					String mailFlag=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsMailAddressFlag());
					prescriberAddrFlgInsertQuery.append(" ,MAILFLG");
					prescriberAddrFlgVlauesQuery.append(",'"+mailFlag+"'");
				}
				
				if(inputAddress !=null && inputAddress.isIsReauthEmailFlag() != null){
					String reauthEmailFlag=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthEmailFlag());
					prescriberAddrFlgInsertQuery.append(", REAUTHEMAILFLAG");
					prescriberAddrFlgVlauesQuery.append(",'"+reauthEmailFlag+"'");
				}
					
				if(inputAddress !=null && inputAddress.isIsReauthPhoneFlag()!= null){
					String reauthPhoneFlag=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthPhoneFlag());
					prescriberAddrFlgInsertQuery.append(",REAUTHPHONEFLAG");
					prescriberAddrFlgVlauesQuery.append(",'"+reauthPhoneFlag+"'");
				}
				
				if(inputAddress !=null && inputAddress.getIsReauthFaxFlag()!= null){
					String reauthFaxFlag=CommonUtil.convertYesNoReauthString(inputAddress.getIsReauthFaxFlag().value());
					prescriberAddrFlgInsertQuery.append(" ,REAUTHFAXFLAG");
					prescriberAddrFlgVlauesQuery.append(",'"+reauthFaxFlag+"'");
						
				}
				if(inputAddress !=null && inputAddress.isIsReauthVisitFlag()!= null){
					String reauthVisitFlag=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthVisitFlag());
					prescriberAddrFlgInsertQuery.append(" ,REAUTHVISITFLAG");
					prescriberAddrFlgVlauesQuery.append(",'"+reauthVisitFlag+"'");
						
				
				}
			String prsInsrtMainQuery=INSERT_PRESCRIBER_ADDRESS_FLAG1+prescriberAddrFlgInsertQuery.toString()+") "+INSERT_PRESCRIBER_ADDRESS_FLAG2+prescriberAddrFlgVlauesQuery.toString()+")";
			ps = connection.prepareStatement(prsInsrtMainQuery);
			CommonUtil.setPsLongParam(ps, 1,addresskey );
			CommonUtil.setPsLongParam(ps, 2, prsnlkey);
			
			CommonUtil.setPsStringParam(ps, 3, inputAddress !=null ? inputAddress.getConsumerId():null);
			CommonUtil.setPsStringParam(ps, 4, storeNumber);
		    CommonUtil.setPsStringParam(ps, 5, CommonUtil.getCurrentTimeStamp());
			CommonUtil.setPsStringParam(ps, 6, CommonUtil.getCurrentTimeStamp());
			
			
			res = (long) ps.executeUpdate();
			if(logger.isDebugEnabled()) {logger.debug(res + " PrescriberAddressFlag row inserted with Prscbrkey "+ prsnlkey +", Addr Cnsmrid "+ addrCnsmrid + "AddrKey = " + addresskey);}
			return addresskey;
		}
		else{
			addresskey = rs.getLong("addrkey");
			if(logger.isDebugEnabled()) {logger.debug("0 PrescriberAddressFlag row inserted with Prscbrkey "+ prsnlkey +", Addr Cnsmrid "+ addrCnsmrid + "AddrKey = " + addresskey);}
		}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		//	logger.debug(res + " PrescriberAddressFlag table was inserted with Prscbrkey "+ prsnlkey +", Addr Cnsmrid "+ inputAddress !=null ? inputAddress.getConsumerId():"null" + "AddrKey = " + addresskey);
		}
		return addresskey;
		
		
	}

	
	
	private Long updatePrescriberAddressFlag(Connection connection, Long prsnlkey,String storeNum,
			Address inputAddress) throws SQLException,
			KeyNotFoundFromTableCacheException, IOException, NamingException {
	
		Long res = 0L;
		Long addressKey=0L;
		boolean changeFound=false;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
		ps= connection.prepareStatement(ADDRFLAGBEFORE);
		CommonUtil.setPsLongParam(ps, 1, prsnlkey);
		CommonUtil.setPsStringParam(ps, 2, inputAddress.getConsumerId());
		CommonUtil.setPsStringParam(ps, 3, storeNum);
		rs = ps.executeQuery();
		if(rs.next()){
		
	StringBuilder  prescriberAddrFlgUpdateQuery=new StringBuilder();
//	prescriberAddrFlgUpdateQuery.append("update PRESCRIBER_ADDRESS_FLAG set ");
	if(inputAddress.isIsMailAddressFlag()!= null){
		String mailFlagBefore=rs.getString("MAILFLG");
		String mailFlagAfter=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsMailAddressFlag());
		if(!mailFlagBefore.equals(mailFlagAfter)){
			prescriberAddrFlgUpdateQuery.append(" MAILFLG='"+mailFlagAfter+"',");
			changeFound=true;
		}
	}
	
	if(inputAddress.isIsReauthEmailFlag() != null){
		String reauthEmailFlagBefore=rs.getString("REAUTHEMAILFLAG");
		String reauthEmailFlagAfter=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthEmailFlag());
		if(!reauthEmailFlagBefore.equals(reauthEmailFlagAfter)){
			prescriberAddrFlgUpdateQuery.append(" REAUTHEMAILFLAG='"+reauthEmailFlagAfter+"',");
			changeFound=true;
		}
	}
		
	if(inputAddress.isIsReauthPhoneFlag()!= null){
		String reauthPhoneFlagBefore=rs.getString("REAUTHPHONEFLAG");
		String reauthPhoneFlagAfter=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthPhoneFlag());
		if(!reauthPhoneFlagBefore.equals(reauthPhoneFlagAfter)){
			prescriberAddrFlgUpdateQuery.append(" REAUTHPHONEFLAG='"+reauthPhoneFlagAfter+"',");
			changeFound=true;
		}
	}
	
	if(inputAddress.getIsReauthFaxFlag()!= null){
		String reauthFaxFlagBefore=rs.getString("REAUTHFAXFLAG");
		String reauthFaxFlagAfter=CommonUtil.convertYesNoReauthString(inputAddress.getIsReauthFaxFlag().value());
		if(!reauthFaxFlagBefore.equals(reauthFaxFlagAfter)){
			prescriberAddrFlgUpdateQuery.append(" REAUTHFAXFLAG='"+reauthFaxFlagAfter+"',");
			changeFound=true;
		}
	}
	if(inputAddress.isIsReauthVisitFlag()!= null){
		String reauthVisitFlagBefore=rs.getString("REAUTHVISITFLAG");
		String reauthVisitFlagAfter=CommonUtil.convertYesNoFlagBooleanString(inputAddress.isIsReauthVisitFlag());
		if(!reauthVisitFlagBefore.equals(reauthVisitFlagAfter)){
			prescriberAddrFlgUpdateQuery.append(" REAUTHVISITFLAG='"+reauthVisitFlagAfter+"',");
			changeFound=true;
		}
	
	}
	
	addressKey=rs.getLong("addrkey");
	ps.close();
	if(changeFound){
	String prescriberAddrFlgMainQuery=UPDATE_PRESCRIBER_ADDRESS_FLAG_QUERY1+prescriberAddrFlgUpdateQuery.toString()+UPDATE_PRESCRIBER_ADDRESS_FLAG_QUERY2;
	ps = connection.prepareStatement(prescriberAddrFlgMainQuery);
		
			
			CommonUtil.setPsStringParam(ps, 1, CommonUtil.getCurrentTimeStamp());
			CommonUtil.setPsStringParam(ps, 2, CommonUtil.getCurrentTimeStamp());
			CommonUtil.setPsStringParam(ps, 3, inputAddress.getConsumerId());
			CommonUtil.setPsLongParam(ps, 4, addressKey);
			CommonUtil.setPsLongParam(ps, 5, prsnlkey);
		//
			
			res = (long) ps.executeUpdate();
			if(logger.isDebugEnabled()) {logger.debug(res + " PrescriberAddressFlag table was updated with Prscbrkey "+ prsnlkey +", Addr Cnsmrid "+ inputAddress.getConsumerId() + "AddrKey = " + addressKey);}
	}  //}
		}else{
			
			 addressKey=	insertPrescriberAddressFlag(connection, prsnlkey,inputAddress, storeNum);
			if(logger.isDebugEnabled()) {logger.debug(res + " PrescriberAddressFlag table was inserted with Prscbrkey "+ prsnlkey +", Addr Cnsmrid "+ inputAddress.getConsumerId() + "AddrKey = " + addressKey);}
		}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			
		}
		return addressKey;
		
	}
	//TE97_Drop53  New Prescriber Logic Introduced end.
	
	public Long upsertContactMethodByLocationKey(Connection connection, String storeNumber, Long locationKey,
			Address address) throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException,
			ConsumerIdMissingException {
		Long contactMethodKey = null;
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertContactMethodByLocationKey. store number " + storeNumber + ", locationKey : "
				+ locationKey);}
		contactMethodKey = upsertContactMethod(connection, storeNumber, null, locationKey, address,
				ContactMethodRoleType.LOCATION,null);
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertContactMethodByLocationKey. store number " + storeNumber + ", locationKey : "
				+ locationKey);}
		return contactMethodKey;
	}

	private boolean isPostAddressEmpty(Address address) {
		if (address.getAddressLineOne() != null)
			return false;
		if (address.getAddressLineTwo() != null)
			return false;
		if (address.getCityName() != null)
			return false;
		if (address.getCountryCode() != null)
			return false;
		if (address.getPostalCode() != null)
			return false;
		if (address.getProvinceCode() != null)
			return false;
		return true;
	}
	
	private Long upsertContactMethod(Connection connection, String storeNumber, Long patientKey, Long locationKey,
			Address inputAddress, ContactMethodRoleType contactMethodRoleType, PersonRoleType personRoleType) throws SQLException,
			KeyNotFoundFromTableCacheException, IOException, NamingException, ConsumerIdMissingException {
		// Long contactMethodKey = null ;
		if (inputAddress == null)
			return null;

		Long contactMethodKey = null;
		String addressConsumerId = inputAddress.getConsumerId();
		//Changes for Customer address consumer ID generation start
		if(addressConsumerId == null && (personRoleType.getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())
				|| personRoleType.getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType()))){
			addressConsumerId = IdGenerator.generate(connection, "CntctMthd").toString();
			inputAddress.setConsumerId(addressConsumerId);
		}
		
		//Changes for Customer address consumer ID generation end
		if (addressConsumerId == null || "".equalsIgnoreCase(addressConsumerId))
			throw new ConsumerIdMissingException("Address");

		// String storeNumberString =
		// CommonUtil.createStoreLeadingZeros(store.getStorenumber());
		// Long locationKey = null;
		// locationKey = getKeyFromCode(LT_STORE, storeNumber );
		if (!isPostAddressEmpty(inputAddress)) {
			contactMethodKey = upsertAddress(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
			upsertEmail(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
			upsertTelecom(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
		}
		else if(personRoleType.getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())
				|| personRoleType.getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType())) {
			contactMethodKey = upsertAddress(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
			upsertEmail(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
			upsertTelecom(connection, locationKey, patientKey, inputAddress, contactMethodRoleType);
		}

		/*
		 * if ((inputAddress.getPrimaryPhoneNumber() != null &&
		 * inputAddress.getPrimaryPhoneNumber().trim().length() > 0) ||
		 * (inputAddress.getFaxNumber() != null &&
		 * inputAddress.getFaxNumber().trim().length() > 0))
		 * upsertTelecom(connection, locationKey, patientKey, inputAddress,
		 * contactMethodRoleType);
		 */
		return contactMethodKey;
	}

	
	/*
	 * upsert Telecom
	 */
	private void upsertTelecom(Connection connection, Long locationKey, Long patientKey, Address address,
			ContactMethodRoleType contactMethodRoleType)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			String whereClasue = null;
			Long primaryKey = null;
			Long telecomKey = null;
			String telecomNum = null;
			String telFaxInd = null;
            int telecomCountT = 0;
            int telecomCountF = 0;
            Set <String> alterPhonesSet = new HashSet<String>();
            List<String> alterPhonesList = null;
            
            alterPhonesList = address.getAlternatePhoneNumber();
            if (alterPhonesList != null) {
                for (String thisAlterPhone : alterPhonesList) {
                	if (thisAlterPhone != null && thisAlterPhone.trim().length() > 2) {
                	    alterPhonesSet.add(thisAlterPhone);
                	}
                }
            }

           
            String faxNum = (address.getFaxNumber() != null) ? address.getFaxNumber() : null;
        	String primaryPhoneNum = (address.getPrimaryPhoneNumber() != null) ? address.getPrimaryPhoneNumber() : null;
			
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT) {
				whereClasue = " and c.ptntKey = ?	" + " and c.CNSMRID = ? "
						    + " and c.CntctMthdTypCd = '" + ContactMethodType.Telecom.value() + "'";
			} else if (contactMethodRoleType == ContactMethodRoleType.PERSON) {
				whereClasue = " and c.prsnKey = ?	" + " and c.CNSMRID = ? "
						    + " and c.CntctMthdTypCd = '" + ContactMethodType.Telecom.value() + "'";
			} else if (contactMethodRoleType == ContactMethodRoleType.LOCATION) {
				whereClasue = " and c.locKey = ?	" + " and c.CNSMRID = ? "
						    + " and c.CntctMthdTypCd = '" + ContactMethodType.Telecom.value() + "'";
			}

			String sql = "select c.CntctMthdKey  CntctMthdKey, "
					   + "       t.TelecomKey    TelecomKey,   "
					   + "       t.TelFaxInd     TelFaxInd,    "
					   + "       t.TELECOMNUM    TELECOMNUM    "
					   + "  from CntctMthd c,                  "
					   + "       Telecom   t                   "
				  	   + " where c.CntctMthdTypCd = 'Telecom'  " 
					   + " and c.CntctMthdKey = t.TelecomKey "
				  	   +  whereClasue;
			
			//-----------------------------------------------------------------------------------//
			//-Update or delete PrimaryPhoneNum and FaxNum-
			//----------------------------------------------------------------------------------//
			ps = connection.prepareStatement(sql);
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT
					|| contactMethodRoleType == ContactMethodRoleType.PERSON) {
				ps.setLong(1, patientKey);
			} else {
				ps.setLong(1, locationKey);
			}
			ps.setString(2, address.getConsumerId());
			if (logger.isTraceEnabled())  {logger.trace("sql : \n" + sql);}
			Map<String, Long> telcomMap = new HashMap<String, Long>();
			List<String> alterPhoneDBList = new ArrayList<String>();
			rs = ps.executeQuery();
			int i=1;
			while (rs!=null && rs.next()) {
				 
				   primaryKey = rs.getLong("CNTCTMTHDKEY");
				   telecomKey = rs.getLong("TelecomKey");
				   telFaxInd = rs.getString("TelFaxInd");
				   telecomNum = rs.getString("TELECOMNUM");
					if (telFaxInd != null && !(telFaxInd.equals(TelecomType.ALTERNATEPHONE.value()) )) {
						telcomMap.put(telFaxInd, telecomKey);
					}else {
						alterPhoneDBList.add(telecomNum);
						
					}
					
			}
				  // ---------------------------------------------------
					// Primary phone num
					// ---------------------------------------------------
					Long primaryPhoneKey = telcomMap.get(TelecomType.TELEPHONE.value());
				
					if (primaryPhoneNum != null && (primaryPhoneKey == null || primaryPhoneKey == 0L)) {
						primaryPhoneKey = insertContactMethodByPatientKey(connection, locationKey, patientKey, address,
								ContactMethodType.Telecom.value(), contactMethodRoleType);
						insertTelecom(connection, primaryPhoneKey, address, TelecomType.TELEPHONE,"");
					}

					if (primaryPhoneNum != null && !(primaryPhoneKey == null || primaryPhoneKey == 0L)) {
						updateTelecom(connection, primaryPhoneKey, address, TelecomType.TELEPHONE,"");
					}

					if (primaryPhoneNum == null && !(primaryPhoneKey == null || primaryPhoneKey == 0L)) {
						deleteTelecom(connection, primaryPhoneKey);
					}

					// ---------------------------------------------------
					// Alternative phone num
					// Insert multiple new Alternative phone numbers
					// There are no update/delete operations for Alternative phone numbers
					// ---------------------------------------------------
					if (alterPhonesSet.size() > 0 ) {
						for (String thisPhone : alterPhonesSet) {
						if(!alterPhoneDBList.contains(thisPhone)){
							 	
						 primaryKey = insertContactMethodByPatientKey(connection, locationKey, patientKey, address,
							              ContactMethodType.Telecom.value(), contactMethodRoleType);
						     insertTelecom(connection, primaryKey, address, TelecomType.ALTERNATEPHONE, thisPhone);
							}
						}
					}	

					// ---------------------------------------------------
					// fax num
					// ---------------------------------------------------
					Long faxKey = telcomMap.get(TelecomType.FAX.value());
					
					if (faxNum != null && (faxKey == null || faxKey == 0L)) {
						faxKey = insertContactMethodByPatientKey(connection, locationKey, patientKey, address,
								ContactMethodType.Telecom.value(), contactMethodRoleType);
						insertTelecom(connection, faxKey, address, TelecomType.FAX,"");
					}

					if (faxNum != null && !(faxKey == null || faxKey == 0L)) {
						updateTelecom(connection, faxKey, address, TelecomType.FAX,"");
					}

					if (faxNum == null && !(faxKey == null || faxKey == 0L)) {
						deleteTelecom(connection, faxKey);
					}

				
		} catch (SQLException e) {
		  	     e.printStackTrace();
			     throw new SQLException(e);
		} finally {
			  //   super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
	}

	private void insertTelecom(Connection connection, Long contanctMethodKey, Address address, TelecomType telecomType, String alternPhoneNum)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			ps = connection.prepareStatement(INSERTTELSQL);
			CommonUtil.setPsStringParam(ps, 1, telecomType.value());
			CommonUtil.setPsStringParam(ps, 2, address.getCountryCode());
			if (telecomType == TelecomType.TELEPHONE) {
				CommonUtil.setPsStringParam(ps, 3, address.getPrimaryPhoneNumber());
			} else if (telecomType == TelecomType.ALTERNATEPHONE) {
				CommonUtil.setPsStringParam(ps, 3, alternPhoneNum);
			} else {
				CommonUtil.setPsStringParam(ps, 3, address.getFaxNumber());
			}
			CommonUtil.setPsLongParam(ps, 4, contanctMethodKey);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if (logger.isInfoEnabled())  {logger.info(res + " new row was inserted into Telecom table with telecomKey = " + contanctMethodKey);}
		}
	}

	// VL99 Started
	private void updateTelecom(Connection connection, Long contanctMethodKey, Address address, TelecomType telecomType, String alternPhoneNum)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		if (logger.isTraceEnabled())  {logger.trace("updateTelecom query : \n" + UPDATETELSQL);}
		int res = 0;
		PreparedStatement ps = null;
	//	ResultSet rs =null;
		try {
			// ------------------------------------------------------------------
			ps = connection.prepareStatement(UPDATETELSQL);
			// ------------------------------------------------------------------
			CommonUtil.setPsStringParam(ps, 1, telecomType.value());
			CommonUtil.setPsStringParam(ps, 2, address.getCountryCode());
			if (telecomType == TelecomType.TELEPHONE) {
				CommonUtil.setPsStringParam(ps, 3, address.getPrimaryPhoneNumber());
			} else if (telecomType == TelecomType.ALTERNATEPHONE) {
				CommonUtil.setPsStringParam(ps, 3, alternPhoneNum);
			} else {
				CommonUtil.setPsStringParam(ps, 3, address.getFaxNumber());
			}
			CommonUtil.setPsLongParam(ps, 4, contanctMethodKey);
			res = ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, null);
			// ------------------------------------------------------------------
			ps = connection.prepareStatement(UPDATE_cntctmthd);
			// ------------------------------------------------------------------
			String CntctPrpsTypCd = (address.getContactPurposeType() != null) ? address.getContactPurposeType().value()
					: null;
		//	Long CntctPrpsTypKey = getKeyFromCode("CNTCTPRPSTYP", CntctPrpsTypCd);
			Long CntctPrpsTypKey=TableCacheSingleton.getInstance(connection).getKeyFromCode("CNTCTPRPSTYP", CntctPrpsTypCd);
			CommonUtil.setPsLongParam(ps, 1, CntctPrpsTypKey);
			CommonUtil.setPsStringParam(ps, 2, CntctPrpsTypCd);
			CommonUtil.setPsLongParam(ps, 3, contanctMethodKey);
			res = ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, null);
			if (logger.isInfoEnabled())  {logger.info(res + "new row was inserted into Telecom table with telecomKey = " + contanctMethodKey);}
		}

	}

	private void deleteTelecom(Connection connection, Long cntctMthdKey) throws SQLException {
		String deleteSQL1 = "delete from telecom where telecomkey = ? ";
		String deleteSQL2 = "delete from cntctmthd where cntctmthdkey = ? ";

		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			ps = connection.prepareStatement(deleteSQL1);
			CommonUtil.setPsLongParam(ps, 1, cntctMthdKey);
			res = ps.executeUpdate();

			ps = connection.prepareStatement(deleteSQL2);
			CommonUtil.setPsLongParam(ps, 1, cntctMthdKey);
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(logger.isDebugEnabled()) {logger.debug(res + " row was deleted from cntctmthd.cntctmthdkey = " + cntctMthdKey);}
		}
	}
	// VL99 Ended

	private void upsertEmail(Connection connection, Long locationKey, Long patientKey, Address addr,
			ContactMethodRoleType contactMethodRoleType)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {

		Long cntctMthdKey = 0L;
		String whereClasue = null;
		String emailAddr = (addr.getEmail() != null) ? addr.getEmail() : null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT) {
				whereClasue = " where CntctMthd.ptntKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.EmailAddress.value() + "'";
			} else if (contactMethodRoleType == ContactMethodRoleType.PERSON) {
				whereClasue = " where CntctMthd.prsnKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.EmailAddress.value() + "'";
			} else if (contactMethodRoleType == ContactMethodRoleType.LOCATION) {
				whereClasue = " where CntctMthd.locKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.EmailAddress.value() + "'";
			}

			String sql = "select CntctMthd.CntctMthdKey  CntctMthdKey, "
					+ "   	  Email.EmailKey          EmailKey		" + " from CntctMthd  "
					+ "           left outer join Email "
					+ "                   on CntctMthd.CntctMthdKey = Email.EmailKey 	" + whereClasue;

			if (logger.isTraceEnabled())  {logger.trace("sql : \n" + sql);}
			ps = connection.prepareStatement(sql);
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT
					|| contactMethodRoleType == ContactMethodRoleType.PERSON) {
				ps.setLong(1, patientKey);
			} else {
				ps.setLong(1, locationKey);
			}

			ps.setString(2, addr.getConsumerId());
			rs = ps.executeQuery();
			if (rs.next()) {
				cntctMthdKey = rs.getLong("CNTCTMTHDKEY");
				updateEmail(connection, cntctMthdKey, addr);
			} else {
				if (emailAddr != null) {
					cntctMthdKey = insertContactMethodByPatientKey(connection, locationKey, patientKey, addr,
							ContactMethodType.EmailAddress.value(), contactMethodRoleType);
					insertEmail(connection, cntctMthdKey, addr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}

	}

	private void insertEmail(Connection connection, Long cntctMthdKey, Address address)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			ps = connection.prepareStatement(INSERTEMAILSQL);
			CommonUtil.setPsStringParam(ps, 1, address.getEmail());
			CommonUtil.setPsLongParam(ps, 2, cntctMthdKey);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(logger.isDebugEnabled()) {logger.debug(res + " new row was inserted into Email table with emailKey = " + cntctMthdKey);}
		}
	}

	private void updateEmail(Connection connection, Long cntctMthdKey, Address addr)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		String deleteSQL1 = "delete from email where emailKey = ? ";
		String deleteSQL2 = "delete from cntctmthd where cntctmthdkey = ? ";
		String updateSQL = "update email set emailAddr = ? where emailKey = ? ";
		String textMessage = null;
		String emailAddr = (addr.getEmail() != null) ? addr.getEmail() : null;

		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			if (emailAddr == null) {
				ps = connection.prepareStatement(deleteSQL1);
				CommonUtil.setPsLongParam(ps, 1, cntctMthdKey);
				res = ps.executeUpdate();

				ps = connection.prepareStatement(deleteSQL2);
				CommonUtil.setPsLongParam(ps, 1, cntctMthdKey);
				res = ps.executeUpdate();
				textMessage = " row was deleted from email.emailKey = ";
			} else {
				ps = connection.prepareStatement(updateSQL);
				CommonUtil.setPsStringParam(ps, 1, addr.getEmail());
				CommonUtil.setPsLongParam(ps, 2, cntctMthdKey);
				textMessage = " row was updated in email.emailKey = ";
				res = ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(logger.isDebugEnabled()) {logger.debug(res + textMessage + cntctMthdKey);}
		}
	}

	
	
	public Long upsertPrescriberAddressFlag(Connection connection, Address address,
			ContactMethodRoleType contactMethodRoleType, PersonRoleType personRoleType, String storeNumber, Long persnrlKey)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		Long prescriberAddressKey = 0L;
		try {
		if (contactMethodRoleType == ContactMethodRoleType.PERSON && personRoleType != null&& (PersonRoleType.PRESCRIBER == personRoleType|| PersonRoleType.MEDICALPRACTITIONER == personRoleType)) {
			if(address != null){
			prescriberAddressKey=updatePrescriberAddressFlag(connection, persnrlKey,storeNumber,address); //TE97_Drop53  New Prescriber Logic Introduced end.  
			}else{
				prescriberAddressKey=insertPrescriberAddressFlag(connection, persnrlKey, null,storeNumber);
			}
			}
			 			
	   return prescriberAddressKey;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
		}

	}
	
	/*
	 * upsert Address
	 */

	private Long upsertAddress(Connection connection, Long locationKey, Long patientKey, Address address,
			ContactMethodRoleType contactMethodRoleType)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		Long cntctMthdKey = 0L;
		String whereClasue = null;
		try {
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT) {
				whereClasue = "	where CntctMthd.ptntKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.PostalAddress.value() + "'";

			} else if (contactMethodRoleType == ContactMethodRoleType.PERSON) {
				whereClasue = "	where CntctMthd.prsnKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.PostalAddress.value() + "'";

			} else if (contactMethodRoleType == ContactMethodRoleType.LOCATION) {
				whereClasue = "	where CntctMthd.locKey = ?	" + " and CntctMthd.CNSMRID = ? "
						+ " and CntctMthd.CntctMthdTypCd = '" + ContactMethodType.PostalAddress.value() + "'";

			}

			String sql = "select CntctMthd.CntctMthdKey  CntctMthdKey, " + "       Addr.AddrKey            AddrKey 	   "
					+ " from CntctMthd              " + "        left outer join Addr "
					+ "             on CntctMthd.CntctMthdKey = Addr.AddrKey	" + whereClasue;

			if (logger.isTraceEnabled())  {logger.trace("sql : \n" + sql);}
			PreparedStatement ps =null;
			ResultSet rs = null;
			try {
			ps = connection.prepareStatement(sql);
			
			if (contactMethodRoleType == ContactMethodRoleType.PATIENT
					|| contactMethodRoleType == ContactMethodRoleType.PERSON) {
				ps.setLong(1, patientKey);
			} else {
				ps.setLong(1, locationKey);
			}
			
			//if insert is coming
			
			
			ps.setString(2, address.getConsumerId());

			rs = ps.executeQuery();
			if (rs.next()) {
				cntctMthdKey = rs.getLong("CNTCTMTHDKEY");
				Long addressKey = rs.getLong("ADDRKEY");
		//		super.close();
				if (addressKey == null || addressKey == 0L) {
					insertAddress(connection, cntctMthdKey, address);
					
				} else {
					updateAddress(connection, cntctMthdKey, address, locationKey, patientKey, contactMethodRoleType);
				}

				/*
				 * if (addressKey == null || addressKey == 0L ) {
				 * insertAddress(cntctMthdKey, address ); } else {
				 * updateAddress(cntctMthdKey, address); } cntn--addresskey
				 */
			} else {
			//	super.close();
				cntctMthdKey = insertContactMethodByPatientKey(connection, locationKey, patientKey, address,
						ContactMethodType.PostalAddress.value(), contactMethodRoleType);
				insertAddress(connection, cntctMthdKey, address);
			
			}
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			}finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
			return cntctMthdKey;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
		//	CommonUtil.closePreparedStatementResultSet(ps, rs);
		}

	}

	private void insertAddress(Connection connection, Long cntctMthdKey, Address address) throws SQLException {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(INSERTADDSQL);
			CommonUtil.setPsStringParam(ps, 1, address.getAddressLineOne());
			CommonUtil.setPsStringParam(ps, 2, address.getAddressLineTwo());
			CommonUtil.setPsStringParam(ps, 3, address.getCityName());
			if (address.getProvinceCode() != null) {
				CommonUtil.setPsStringParam(ps, 4, address.getProvinceCode().value());
			} else {
				CommonUtil.setPsStringParam(ps, 4, null);
			}

			CommonUtil.setPsStringParam(ps, 5, address.getCountryCode());
			CommonUtil.setPsStringParam(ps, 6, address.getPostalCode());

			CommonUtil.setPsLongParam(ps, 7, cntctMthdKey);
			res = ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, null);
			if(logger.isDebugEnabled()) {logger.debug(res + " addr table was inserted with AddrKey = " + cntctMthdKey);}
		}
	}

	private void updateAddress(Connection connection, Long cntctMthdKey, Address address, Long locationKey,
			Long patientKey, ContactMethodRoleType contactMethodRoleType)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		int res = 0;
		PreparedStatement ps =null;
		ResultSet rs = null;
		String CntctPrpsTypCd = null;
		Long CntctPrpsTypKey = 0L;
		try {
			ps = connection.prepareStatement(UPDATEADDSQL);
			CommonUtil.setPsStringParam(ps, 1, address.getAddressLineOne());
			CommonUtil.setPsStringParam(ps, 2, address.getAddressLineTwo());
			CommonUtil.setPsStringParam(ps, 3, address.getCityName());
			if (address.getProvinceCode() != null) {
				CommonUtil.setPsStringParam(ps, 4, address.getProvinceCode().value());
			} else {
				CommonUtil.setPsStringParam(ps, 4, null);
			}

			CommonUtil.setPsStringParam(ps, 5, address.getCountryCode());
			CommonUtil.setPsStringParam(ps, 6, address.getPostalCode());

			CommonUtil.setPsLongParam(ps, 7, cntctMthdKey);
			res = ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, rs); 
			  CntctPrpsTypCd = (address.getContactPurposeType() != null) ? address.getContactPurposeType().value()
					: null;
			  CntctPrpsTypKey =TableCacheSingleton.getInstance(connection).getKeyFromCode("CNTCTPRPSTYP", CntctPrpsTypCd);
			//+ "                     ACTVFLG = ?          " //////Temporary fix for Drop 50
			
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
			try {
				String SQL = "update CntctMthd set CNTCTPRPSTYPKEY = ?, "
						   + "                     CNTCTPRPSTYPCD = ? ";
				String prscriberSQL=",ACTVFLG = ? ";
				String whrSQL = " where CNSMRID = ?   ";

				if (contactMethodRoleType == ContactMethodRoleType.PATIENT) {
					SQL += whrSQL + "and PtntKey = ?";
				}
				if (contactMethodRoleType == ContactMethodRoleType.PERSON) {
					SQL += prscriberSQL + whrSQL + "and PrsnKey = ?";
				}
				if (contactMethodRoleType == ContactMethodRoleType.LOCATION) {
					SQL += whrSQL + "and LocKey = ?";
				}
			ps = connection.prepareStatement(SQL);
			CommonUtil.setPsLongParam(ps, 1, CntctPrpsTypKey);
			CommonUtil.setPsStringParam(ps, 2, CntctPrpsTypCd);

			// TE97_024
			// ACTVFLG 
			/*if (address.isIsActiveAddressFlag() == null) {
				CommonUtil.setPsStringParam(ps, 3, "N");
			} else {
				CommonUtil.setPsStringParam(ps, 3,
						CommonUtil.convertYesNoFlagBooleanString(address.isIsActiveAddressFlag()));
			}*/

			

			if (contactMethodRoleType == ContactMethodRoleType.PERSON) {
			
if	(address.isIsActiveAddressFlag() == null) {		
				    CommonUtil.setPsStringParam(ps, 3, null);
				} else {
				    CommonUtil.setPsStringParam(ps, 3, CommonUtil.convertYesNoFlagBooleanString(address.isIsActiveAddressFlag()));
				}			

			CommonUtil.setPsStringParam(ps, 4, address.getConsumerId());
			if (contactMethodRoleType == ContactMethodRoleType.PERSON)   {CommonUtil.setPsLongParam(ps, 5, patientKey);}
			//TE97_023 
				}
			else{
				// CommonUtil.setPsLongParam(ps, 4, cntctMthdKey);
				CommonUtil.setPsStringParam(ps, 3, address.getConsumerId());

			if (contactMethodRoleType == ContactMethodRoleType.PATIENT)  {CommonUtil.setPsLongParam(ps, 4, patientKey);}

			if (contactMethodRoleType == ContactMethodRoleType.LOCATION) {CommonUtil.setPsLongParam(ps, 4, locationKey);}
			}
			res = ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(logger.isDebugEnabled()) {logger.debug(res + " addr table was updated with AddrKey = " + cntctMthdKey);}
		}
	}

	//TE99 Started	
			private Long insertContactMethodByPatientKey(Connection connection, Long locationKey , Long patientPersonkey, Address address, String CntctMthdTyp , ContactMethodRoleType contactMethodRoleType ) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
				int res = 0 ;
				long CntctMthdKey = 0 ;
				try { 
			
			           
					String sql = "insert into CntctMthd ( " +
							"		CntctMthdTypCd		, 	CntctPrpsTypCd	, 	CnsmrId			, " +
							"		PrdcrId				, 	primaryFlg		,	DEFAULTCOMMFLG	,	 " + 
							"		CntctMthdTypKey		,	CntctPrpsTypKey	,	" + // LocKey			,	" +
							"		CntctMthdKey		,	";
							

				String presbsql="Actvflg";
					
					if ( contactMethodRoleType == ContactMethodRoleType.PATIENT ) { 
						sql +=  "		 PtntKey  	) " +
								" values (	?, ?, ?, " +
								" 			?, ?, " +
								"			?, ?, " + 
								"			?,?," + 
								"			? )";
					}
					else
					if( contactMethodRoleType == ContactMethodRoleType.PERSON ) { 
						sql+=	" PrsnKey,"+ presbsql + " ) ";
					sql += 	" values (	?, ?, ?, " +
								" 			?, ?, " +
								"			?, ?, " + 
							"			?, ?, ?," + 
								"			? )"; 
					}
					else
					if ( contactMethodRoleType == ContactMethodRoleType.LOCATION ) { 
						sql+= 	" LocKey ) " + 
								" values (	?, ?, ?, " +
								" 			?,?, " +
								"			?, ?, " + 
							"			?, ?," +
								"			? )"; 
					}
					
					CntctMthdKey = IdGenerator.generate(connection, "CntctMthd");
					Long CntctMthdTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_CNTCTMTHDTYP, CntctMthdTyp);
				    PreparedStatement ps = null;
				    try {
					ps = connection.prepareStatement(sql);
			
					CommonUtil.setPsStringParam(ps, 1, CntctMthdTyp);

					String CntctPrpsTypCd = (address.getContactPurposeType()!= null) ? address.getContactPurposeType().value() : null;
					CommonUtil.setPsStringParam(ps, 2, CntctPrpsTypCd); 
					CommonUtil.setPsStringParam(ps, 3, address.getConsumerId());
					CommonUtil.setPsStringParam(ps, 4, address.getProducerId());
					CommonUtil.setPsBooleanParam(ps, 5, address.isIsPrimaryAddressFlag() );
			
					boolean TmpFiller = false;
					CommonUtil.setPsBooleanParam(ps, 6, TmpFiller); 
					CommonUtil.setPsLongParam(ps, 7, CntctMthdTypKey);
			
					Long CntctPrpsTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode("CNTCTPRPSTYP", CntctPrpsTypCd);
					CommonUtil.setPsLongParam(ps, 8, CntctPrpsTypKey); 
					CommonUtil.setPsLongParam(ps, 9, CntctMthdKey);
					
					
					
					/*//ACTVFLG Commented for future impletemation
					if	(address.isIsActiveAddressFlag() == null) {		
					    CommonUtil.setPsStringParam(ps, 10, "N");
					} else {
					    CommonUtil.setPsStringParam(ps, 10, CommonUtil.convertYesNoFlagBooleanString(address.isIsActiveAddressFlag()));
					}*/
					
	
					
					if( contactMethodRoleType == ContactMethodRoleType.LOCATION ) 
						CommonUtil.setPsLongParam(ps, 10, locationKey);
					else
						CommonUtil.setPsLongParam(ps, 10, patientPersonkey);
					
					if( contactMethodRoleType == ContactMethodRoleType.PERSON ) { 
					
					//ACTVFLG
					if	(address.isIsActiveAddressFlag() == null) {		
					    CommonUtil.setPsStringParam(ps, 11, null);
					} else {
					    CommonUtil.setPsStringParam(ps, 11, CommonUtil.convertYesNoFlagBooleanString(address.isIsActiveAddressFlag()));
					}
					
					
					}

					res = ps.executeUpdate();
					if (logger.isInfoEnabled())  {logger.info(res + " new records have been inserted into CntctMthd table with CntctMthdKey = " +  CntctMthdKey);}
				    }finally {
				    	CommonUtil.closePreparedStatementResultSet(ps, null);
				    }
					return CntctMthdKey;
				} catch (SQLException e) {
					  e.printStackTrace();
					  throw new SQLException(e);
				}	
			}
		//TE99 Ended

}
