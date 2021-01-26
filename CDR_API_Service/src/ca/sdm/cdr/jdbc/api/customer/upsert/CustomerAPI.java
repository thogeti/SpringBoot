package ca.sdm.cdr.jdbc.api.customer.upsert;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSNRLTYP;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.AssociateCustomerException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import com.sdm.cdr.exception.api.CreateCustomerException;
import com.sdm.cdr.exception.api.CustomerAlreadyExistsAndRegisteredException;
import com.sdm.cdr.exception.api.CustomerAlreadyExistsException;
import com.sdm.cdr.exception.api.CustomerChannelNotFoundException;
import com.sdm.cdr.exception.api.CustomerNotFoundException;
import com.sdm.cdr.exception.api.CustomerPatientAssociationExistException;
import com.sdm.cdr.exception.api.CustomerPatientAssociationNotExistException;
import com.sdm.cdr.exception.api.CustomerTypeNotFoundException;
import com.sdm.cdr.exception.api.DissociateCustomerException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.SourceSystemNotValidException;
import com.sdm.cdr.exception.api.UpdateCustomerException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.query.api.PatientViewGet;
import ca.sdm.cdr.jdbc.upsert.api.CDRUpsert;
import ca.sdm.cdr.jdbc.upsert.api.PersonApi;
import ca.shoppersdrugmart.rxhb.drx.cse.Customer;
//import ca.shoppersdrugmart.rxhb.drx.cse.Preference;
import ca.shoppersdrugmart.rxhb.drx.cse.Preference;
import ca.shoppersdrugmart.rxhb.drx.cse.UserTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.customerservice.AssociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.DissociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomer;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Province;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Division;
import com.sdm.cdr.exception.api.DivisionTypeNotFoundException;

public class CustomerAPI  {
	private static final Logger logger = LogManager.getLogger("CustomerAPI");
	
	
	private final static String UPSERTCDRPTNTMPNG = " MERGE INTO CDRPTNTMPNG a                  " +
			" USING        (                                                                    " +
			"                     SELECT                                                        " +
			"                           ?                          PTNTID                      " +
			"                          , ?                          PTNTKEY                     " +
			"                          , ?                          CUSTKEY,                     " +
			                         CommonUtil.TO_TIMESTAMP_TZ +" PTNTCRTTS,                   " +
			                       CommonUtil.TO_TIMESTAMP_TZ +" PTNTUPDTS                   " +
			"                          , ?                          VRFPHARMACYCHNLTYPKEY       " +
			"                          , ?                          VRFCOMMMODETYPKEY           " +
			"                          , ?                          VRFENGMNTTYPKEY             " +
			"                          , ?                          ASSOCFLAG                   " +
			"                          , ?                          ASSOCTYP                    " +
			"                     FROM dual) b                                                  " +
			" ON                                                                                " +
			"              (                                                                    " +
			"                           a.CUSTKEY     = b.CUSTKEY                               " +
			"                           and a.PTNTKEY = b.PTNTKEY                               " +
			"              )                                                                    " +
			" WHEN MATCHED THEN                                                                 " +
			" UPDATE                                                                            " +
			" SET              VRFPHARMACYCHNLTYPKEY = b.VRFPHARMACYCHNLTYPKEY                  " +
			"                , VRFCOMMMODETYPKEY     = b.VRFCOMMMODETYPKEY                      " +
			"                , VRFENGMNTTYPKEY       = b.VRFENGMNTTYPKEY                        " +
			"                , PTNTUPDTS	         = b.PTNTUPDTS		                        " +
			"                , ASSOCFLAG             = b.ASSOCFLAG                              " +
			"                , ASSOCTYP              = b.ASSOCTYP                               " +
			" WHEN NOT MATCHED THEN                                                             " +
			" insert                                                                            " +
			"        (CDRPTNTMPNGKEY                                                            " +
			"             , PTNTID                                                              " +
			"             , PTNTKEY                                                             " +
			"             , CUSTKEY                                                             " +
			"             , PTNTCRTTS                                                           " +
			"             , PTNTUPDTS                                                           " +
			"             , VRFPHARMACYCHNLTYPKEY                                               " +
			"             , VRFCOMMMODETYPKEY                                                   " +
			"             , VRFENGMNTTYPKEY                                                     " +
			"             , ASSOCFLAG                                                           " +
			"             , ASSOCTYP                                                            " +
			"        )                                                                          " +
			"        values                                                                     " +
			"        (CDRPTNTMPNG_SEQ.nextval                                                   " +
			"             , b.PTNTID                                                            " +
			"             , b.PTNTKEY                                                           " +
			"             , b.CUSTKEY                                                           " +
			"             , b.PTNTCRTTS                                                         " +
			"             , b.PTNTUPDTS                                                         " +
			"             , b.VRFPHARMACYCHNLTYPKEY                                             " +
			"             , b.VRFCOMMMODETYPKEY                                                 " +
			"             , b.VRFENGMNTTYPKEY                                                   " +
			"             , b.ASSOCFLAG                                                         " +
			"             , b.ASSOCTYP)                                                         ";
	
	
	
	private final static String ALREADYEXISTCUSTSQL = "SELECT CUSTKEY,PRSNKEY FROM CUST WHERE CUSTID = ? ";
	
	private final static String ALREADYEXISTUSERSQL = "SELECT CUSTKEY,PRSNKEY FROM CUST WHERE USERID = ? ";
	
		private final static String ALREADYEXISTCUSTOMERSQLCUSTID = "select c.custkey,c.prsnkey,c.CUSTTYP,cm.cdrptntmpngkey,cm.ptntid,cm.ptntkey,cp.prsnprefkey "
			+ " from cust c " + " LEFT OUTER JOIN cdrptntmpng CM ON C.CUSTKEY = CM.CUSTKEY "
			+ " LEFT OUTER JOIN PRSNPREF CP ON  C.PRSNKEY = CP.PRSNKEY " + " WHERE C.CUSTID = ? ";

	private final static String ALREADYEXISTCUSTOMERSQLUSERID = "select c.custkey,c.prsnkey,c.CUSTTYP,c.USERTYP,cm.cdrptntmpngkey,cm.ptntid,cm.ptntkey,cp.PRSNPREFkey "
			+ " from cust c " + " LEFT OUTER JOIN cdrptntmpng CM ON C.CUSTKEY = CM.CUSTKEY "
			+ " LEFT OUTER JOIN PRSNPREF CP ON  C.PRSNKEY = CP.PRSNKEY " + " WHERE C.USERID = ? and C.USERTYP = 'Customer' ";
	
	private final static String UPDATECUSTCUSTOMERSQLUSERID = "select c.custkey,listagg( cm.CARERCVKEY, ',') within group (order by cm.CARERCVKEY) as CARERCVKEYS "
			+ " from cust c " + " LEFT OUTER JOIN cdrptntmpng CM ON C.CUSTKEY = CM.CUSTKEY and cm.CARERCVKEY is not null  and cm.ASSOCFLAG = 'Y' "
			+ "  WHERE C.USERID = ? and C.USERTYP = 'Customer' GROUP BY C.CUSTKEY ";
	
	private final static String CHECKCAREGIVERQUERY = "SELECT C.PRSNKEY,CP.PRSNPREFKEY FROM CUST C LEFT OUTER JOIN PRSNPREF CP ON  C.PRSNKEY = CP.PRSNKEY WHERE C.CUSTKEY = ?  ";
	
	private final static String ALREADYEXISTCARERECEIVER = "SELECT CDRPTNTMPNGKEY FROM CDRPTNTMPNG WHERE CARERCVKEY = ? AND CUSTKEY = ? AND ASSOCFLAG = 'Y' ";

	private final static String UPDATECUSTSQL2 = " UPDATE CUST SET USERTYP = ? ";
	private final static String UPDATECUSTWHERE = " where CUSTKEY = ? ";
	private final static String UPDATEPRSNPREFSQL = "UPDATE PRSNPREF SET ";

	private final static String UPDATECUSFPREDWHERE = " WHERE PRSNPREFKEY = ? ";

	private final static String QUERYPTNTKEYSQL = " SELECT PTNT.PTNTKEY, PTNT.CNSMRID,PTNT.CRTTIMESTAMP,PTNT.LSTUPDTTIMESTAMP FROM PTNT WHERE PTNT.CNSMRID = ? AND PTNT.STORENUM = ? ";
	
	private final static String QUERYCNTCTMTHDSQL = " SELECT CNSMRID FROM CNTCTMTHD WHERE PRSNKEY = ? ";

	

	private final static String QUERYPRSNRLKEYSQL = "  SELECT PRSN.PRSNKEY , PRSNRL.PRSNRLKEY " + "	FROM "
			+ "	     PRSN LEFT OUTER JOIN PRSNRL ON PRSN.PRSNKEY = PRSNRL.PRSNKEY "
			+ "	          AND PRSNRLTYPKEY = ?  "
			+ "	WHERE PRSNRL.PRSNRLKEY = ? ";
	//Drop59 Division Changes
	//private final static String INSERTCUSTSQL = "INSERT INTO CUST (CUSTKEY,PRSNKEY,CUSTID,CUSTTYP,USERTYP,USERID,EMPID,OPTNUM,PHARMACYCHNLTYPKEY,COMMMODETYPKEY,DEACTIVATIONRSN,"
	//		+ "DETTAILNT,STATUS,REQSRCDESC,REGREMID,SUCCESSLGNCNT,CRTTIMESTAMP,LSTLGNTIMESTAMP,ENGMNTTYPKEY,RECUPDCNT) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , "
	//		+ CommonUtil.getPsToDateFunctionStr() + "," + CommonUtil.getPsToDateFunctionStr() + ", ?  , ? )";
	private final static String INSERTCUSTSQL = "INSERT INTO CUST (CUSTKEY,PRSNKEY,CUSTID,CUSTTYP,USERTYP,USERID,EMPID,OPTNUM,PHARMACYCHNLTYPKEY,COMMMODETYPKEY,DEACTIVATIONRSN,"
			+ "DETTAILNT,STATUS,REQSRCDESC,REGREMID,SUCCESSLGNCNT,CRTTIMESTAMP,LSTLGNTIMESTAMP,ENGMNTTYPKEY,RECUPDCNT,DIVISION) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , "
			+ CommonUtil.getPsToDateFunctionStr() + "," + CommonUtil.getPsToDateFunctionStr() + ", ?  , ?, ? )";
	
	
	private final static String INSERTCDRPTNTMPNGSQL = "INSERT INTO CDRPTNTMPNG (CDRPTNTMPNGKEY , PTNTID , PTNTKEY , CUSTKEY , PTNTCRTTS , PTNTUPDTS , VRFPHARMACYCHNLTYPKEY , VRFCOMMMODETYPKEY , VRFENGMNTTYPKEY , ASSOCFLAG , ASSOCTYP ) VALUES ( ? , ?, ? , ? , "
			+ CommonUtil.TO_TIMESTAMP_TZ + "," + CommonUtil.TO_TIMESTAMP_TZ + " , ? , ? , ? , ? , ? )";

	private final static String INSERTCARERECEIVERSQL = "MERGE INTO CDRPTNTMPNG a   " + 
						 "USING (SELECT  ? CUSTKEY,? VRFPHARMACYCHNLTYPKEY,? VRFCOMMMODETYPKEY, ? VRFENGMNTTYPKEY,? ASSOCFLAG,? ASSOCTYP, ? CARERCVKEY, "+ CommonUtil.TO_TIMESTAMP_TZ + " PTNTCRTTS , "+ CommonUtil.TO_TIMESTAMP_TZ + " PTNTUPDTS FROM dual) b " + 
						 "	on(a.CUSTKEY = b.CUSTKEY and a.CARERCVKEY = b.CARERCVKEY) " + 
						 "	WHEN MATCHED THEN " + "   UPDATE SET VRFPHARMACYCHNLTYPKEY  = b.VRFPHARMACYCHNLTYPKEY,VRFCOMMMODETYPKEY = b.VRFCOMMMODETYPKEY,VRFENGMNTTYPKEY = b.VRFENGMNTTYPKEY , ASSOCFLAG = b.ASSOCFLAG , PTNTUPDTS = b.PTNTUPDTS 	WHEN NOT MATCHED THEN " + 
						 "	insert (CDRPTNTMPNGKEY , CUSTKEY , VRFPHARMACYCHNLTYPKEY , VRFCOMMMODETYPKEY , VRFENGMNTTYPKEY , ASSOCFLAG , ASSOCTYP , CARERCVKEY , PTNTCRTTS , PTNTUPDTS) " + 
						 "	values(CDRPTNTMPNG_SEQ.nextval,b.CUSTKEY , b.VRFPHARMACYCHNLTYPKEY , b.VRFCOMMMODETYPKEY , b.VRFENGMNTTYPKEY , b.ASSOCFLAG , b.ASSOCTYP , b.CARERCVKEY , b.PTNTCRTTS , b.PTNTUPDTS)";
	private final static String DEASSOCIATECARERECEIVERSQL =  "UPDATE CDRPTNTMPNG SET ASSOCFLAG = 'N' , PTNTUPDTS = "+ CommonUtil.getPsToDateFunctionStr() +" WHERE CARERCVKEY = ? AND CUSTKEY = ? ";
	
	private final static String DEASSOCIATEHARDCARERECEIVERSQL =  "DELETE FROM CDRPTNTMPNG WHERE CARERCVKEY = ? AND CUSTKEY = ? ";
	//LTPHCP-45 CDRPTNTUPDATE Timestamp
	//private final static String UPDATECDRPTNTMPNGSQL = "UPDATE CDRPTNTMPNG SET ASSOCTYP = ? , ASSOCFLAG = 'Y' WHERE CUSTKEY = ? AND ASSOCTYP = 'PreRegCustomer' ";
	private final static String UPDATECDRPTNTMPNGSQL = "UPDATE CDRPTNTMPNG SET ASSOCTYP = ? , ASSOCFLAG = 'Y', PTNTUPDTS = "+ CommonUtil.getPsToDateFunctionStr() + "WHERE CUSTKEY = ? AND ASSOCTYP = 'PreRegCustomer' ";
	
	private final static String INSERTPRSNPREFWITHOUTSQL = "INSERT INTO PRSNPREF (PRSNPREFKEY , LANGPREF  ,  PRSNKEY ) VALUES ( ? , ? , ? )";
	
	private final static String INSERTPRSNPREFSQL = "INSERT INTO PRSNPREF (PRSNPREFKEY , LANGPREF  , RFLRMNDR , DRGDTLINFRMATIONFLG , PRSNKEY , FILLNTFEML , FILLNTFSMS , PICKUPNTFEML , PICKUPNTFSMS ,NTFEML , NTFSMS , MRKTEML , GLBAUOTFILL , LSTMDFTS) VALUES ( ? , ? , ? , ?, ? , ? , ? , ? , ?, ? , ? , ? , ? , "+ CommonUtil.getPsToDateFunctionStr() + ")";
	
	private final static String PTNTASSOCCDRPTNTSQL = "select c.custkey,c.custid,c.userid, " + 
			"       c.prsnkey" + 
			"  from ptnt        p," + 
			"       cust        c, " + 
			"       CDRPTNTMPNG m" + 
			" where p.storenum = ?" + 
			"   and p.cnsmrid  = ?  " + 
			" and c.PHARMACYCHNLTYPKEY = ? "+
			"   and p.ptntkey  = m.ptntkey " + 
			"   and m.custkey  = c.custkey ";
	
	private final static String PTNTASSOCPREREGSQL = "select c.custkey,c.custid,c.userid, " + 
			"       c.prsnkey" + 
			"  from ptnt        p," + 
			"       cust        c, " + 
			"       CDRPTNTMPNG m" + 
			" where p.ptntkey = ?" + 
			" and c.PHARMACYCHNLTYPKEY = ? AND M.ASSOCTYP = 'PreRegCustomer' "+
			"   and p.ptntkey  = m.ptntkey " + 
			"   and m.custkey  = c.custkey ";
	
	private final static String PTNTEXISTINCDRPTNTSQL = "SELECT CDRPTNTMPNGKEY,ASSOCTYP,ASSOCFLAG FROM CDRPTNTMPNG WHERE PTNTKEY = ? AND CUSTKEY = ? AND ASSOCFLAG = 'Y' ";
	
	private final static String DISASSOCIATEPTNTSQL = "UPDATE CDRPTNTMPNG SET ASSOCFLAG = 'N', PTNTUPDTS = "+ CommonUtil.getPsToDateFunctionStr() +" WHERE PTNTKEY = ? AND CUSTKEY = ? ";
	
	private final static String UPDPRSNPREFLSTMDFTMS = "UPDATE PRSNPREF SET LSTMDFTS = "+ CommonUtil.getPsToDateFunctionStr() +" WHERE PRSNKEY IN (SELECT PRSNKEY FROM CUST WHERE CUSTKEY = ? )";
	
	private final static String UPDCUSTLSTUPDTMS = "UPDATE CUST SET LSTUPDTTIMESTAMP = "+ CommonUtil.getPsToDateFunctionStr() +" WHERE CUSTKEY = ? ";
	
	private final static String DISASSOCIATEHARDPTNTSQL = "DELETE FROM CDRPTNTMPNG WHERE PTNTKEY = ? AND CUSTKEY = ? ";
	
	private final static String DISASSOCIATEHARDENTPREFSQL = "DELETE FROM ENTPREF WHERE CDRPTNTMPNGKEY = ? ";

	
	private final static String GETADDRESSQUERY = "select COALESCE(a.addrkey, -3)  addr_addrkey," + 
			"                             a.ADDRLNONE              ADDR_ADDRLNONE," + 
			"                             a.ADDRLNTWO              ADDR_ADDRLNTWO," + 
			"                             a.CITYNM                 ADDR_CITYNM," + 
			"                             a.CNTRYCD                ADDR_CNTRYCD," + 
			"                             a.POSTALCD               ADDR_POSTALCD," + 
			"                             a.PROVCD                 ADDR_PROVCD," + 
			"                             c.CNSMRID                CM_CNSMRID," + 
			"" + 
			"                             e.EMAILADDR              EMAIL_EMAILADDR," + 
			"                             COALESCE(e.EMAILKEY, -3) EMAIL_EMAILKEY," + 
			"" + 
			"                             decode(t.TELFAXIND, 'F', t.TELECOMKEY, -3)   FAX_TELECOMKEY," + 
			"                             decode(t.TELFAXIND, 'F', t.TELECOMNUM, null) FAX_TELECOMNUM," + 
			"" + 
			"                             decode(t.TELFAXIND, " + 
			"                             'T', t.TELECOMKEY," + 
			"                             'P', t.TELECOMKEY, -3)                PHONE_TELECOMKEY," + 
			"                             decode(t.TELFAXIND, " + 
			"                             'T', t.TELECOMNUM," + 
			"                             'P', t.TELECOMNUM, null)              PHONE_TELECOMNUM," + 
			
			"                             c.ptntkey ptnt_ptntkey" + 
		
			"                        from cntctmthd  c" + 
			"                                left outer join addr a" + 
			"                                  on c.cntctmthdtypcd = 'Postal Address' and" + 
			"                                     c.cntctmthdkey   = a.addrkey" + 
			"                              " + 
			"                                left outer join telecom  t" + 
			"                                  on c.cntctmthdtypcd = 'Telecom' and" + 
			"                                     c.cntctmthdkey   = t.telecomkey" + 
			
			"                                left outer join email    e" + 
			"                                  on c.cntctmthdtypcd = 'Email Address' and" + 
			"                                     c.cntctmthdkey   = e.emailkey" + 
			"                       where c.prsnkey = ? ";
	@SuppressWarnings("unused")
	public void createCustomer(Connection connection, CreateCustomer createCustomer)
			throws SQLException, InvalidInputException, CustomerPatientAssociationExistException,
			KeyNotFoundFromTableCacheException, PatientNotFoundException, NamingException, IOException,
			 ConsumerIdMissingException, CustomerAlreadyExistsException,
			CreateCustomerException, CustomerNotFoundException, CustomerTypeNotFoundException, CustomerChannelNotFoundException, SourceSystemNotValidException,DivisionTypeNotFoundException {
		String storeNumber = null;
		String customerId = null;
		String userId = null;
		String patientId = null;
		String ptntCreateTimestamp = null;
		String ptntLastUpdateTimestamp = null;
		Long  ptntKey = null;
		String custUserID = null;
		try {
			
			Patient patient = createCustomer.getPatient();
			
			
			Customer customer = createCustomer.getCustomer();
			
			customerId = customer.getCustomerid();
			userId = customer.getUserId();
			PharmacyChannel pharmacyChannel = customer.getSourceChannel();
			
			String userTypeEnum = customer.getUserType() != null ? customer.getUserType().name() : null;
			
			if (!FindEnum.contains(UserTypeEnum.class, userTypeEnum)) {
				if(logger.isErrorEnabled()) {logger.error("Customer Type " + userTypeEnum + " not found");}
				throw new CustomerTypeNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
			
			if(customer.getUserType() != null){
				
				if (userId != null
						&& CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().value())) {
					throw new InvalidInputException("User Type should be Customer when User ID is present");
				}
				
				if (customerId != null && userId == null
						&& CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().value())) {
					throw new InvalidInputException("User ID is mandatory when User type is Customer");
				}
			}
			
			
			if(patient != null) {
				patientId = patient.getConsumerId();
			if (patientId == null || patientId.trim().length() == 0) {
				throw new InvalidInputException("Consumer ID is mandatory for Create Customer");
			}
			
			
			Store storepat = patient.getStore();
			if (storepat == null ) {
				throw new InvalidInputException("Store Number is mandatory for Create Customer");
			}
			storeNumber = storepat.getStorenumber() != null && !"".equals(storepat.getStorenumber()) ? CommonUtil.createStoreLeadingZeros(storepat.getStorenumber()) : null;
			if (storeNumber == null || storeNumber.trim().length() == 0) {
				throw new InvalidInputException("Store Number is mandatory for Create Customer");
			}
			}
			if ((customerId == null || customerId.trim().length() == 0)
					&& (userId == null || userId.trim().length() == 0)) {
				throw new InvalidInputException("Customer ID or User ID mandatory for Create Customer");
			}

			if (pharmacyChannel == null ) {
				throw new InvalidInputException("Source Channel is mandatory for Create Customer");
			}
			
			 custUserID = userId != null ? userId : customerId;
			

			
			if (logger.isInfoEnabled())  {logger.info("Start Create customer for : Store Number  %s  , PatientId :  %s with Customer/User id %s " ,storeNumber,patientId, custUserID);}
			Long channelKey = null;
			if(pharmacyChannel != null){
				String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
				if(channelID != null){
			try {
				channelKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for  %s" , channelID);}
				throw new CustomerChannelNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for  %s" , channelID);}
					throw new CustomerChannelNotFoundException(storeNumber ,patientId , customerId ,userId);
				}
			}
			
			Long communicationKey = null;
			if(pharmacyChannel != null){
				String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
				if(communicationID != null){
			try {
				communicationKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, communicationID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer Communication type in request is not valid for  %s" , communicationID);}
				throw new InvalidInputException("Customer Communication Mode is not valid for Create Customer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer Communication type in request is not valid for %s " , communicationID);}
					throw new InvalidInputException("Customer Communication Mode is not valid for Create Customer");
				}
			}
			
			Long engagementKey = null;
			if(pharmacyChannel != null){
				String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
				if(engID != null){
			try {
				engagementKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer Engagement type in request is not valid for  %s" , engID);}
				throw new InvalidInputException("Customer Engagement type is not valid for Create Customer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer Engagement type in request is not valid for  %s" , engID);}
					throw new InvalidInputException("Customer Engagement type is not valid for Create Customer");
				}
			}

			//Drop59 Division Changes
			String divisionEnum = customer.getDivision() != null ? customer.getDivision().name() : null;
			
				if (!FindEnum.contains(Division.class, divisionEnum)) {
					divisionEnum = "SDM";
				//	logger.error("Division  " + divisionEnum + " not found");
				//	throw new DivisionTypeNotFoundException(storeNumber ,patientId , customerId ,userId);
				}
			divisionEnum = divisionEnum.toUpperCase();
			
			
			if(patientId != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
			try {
			ps = connection.prepareStatement(QUERYPTNTKEYSQL);
			CommonUtil.setPsStringParam(ps, 1, patientId);
			CommonUtil.setPsStringParam(ps, 2, storeNumber);
			rs = ps.executeQuery();

			if (!rs.next()) // No patient record found
			{
				if(logger.isErrorEnabled()) {logger.error("patient not found for store number  %s, patient id  %s" ,storeNumber, patientId);}
				throw new PatientNotFoundException(storeNumber, patientId);
			}

			 ptntKey = rs.getLong("PTNTKEY");

			 ptntCreateTimestamp = rs.getString("CRTTIMESTAMP");
			 ptntLastUpdateTimestamp = rs.getString("LSTUPDTTIMESTAMP");

	//		super.close();
				}catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } 
      		finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
				}
			
			try {
			ps = connection.prepareStatement(PTNTASSOCPREREGSQL);
			CommonUtil.setPsLongParam(ps, 1, ptntKey);
			CommonUtil.setPsLongParam(ps, 2, channelKey);
			rs = ps.executeQuery();
			if (rs.next()) // No patient record found
			{
				String custassocid = rs.getString("userid") != null ? rs.getString("userid") : rs.getString("custid");
				if(logger.isErrorEnabled()) {logger.error("patient already associated with the customer  %s  , patient id %s" , custassocid, patientId);}
				throw new CustomerPatientAssociationExistException(custassocid,patientId,storeNumber);
			}

			

	//		super.close();
			}
			catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
			
			}
			

			String customerExistQuery = null;

			if (userId != null) {
				customerExistQuery = ALREADYEXISTUSERSQL;
			} else if (customerId != null) {
				customerExistQuery = ALREADYEXISTCUSTSQL;
			}

			PreparedStatement ps =null;
			ResultSet rs =null;
            try {
			ps = connection.prepareStatement(customerExistQuery);

			ps.setString(1, custUserID);

			rs = ps.executeQuery();

			if (rs.next()) {
				if(logger.isErrorEnabled()) {logger.error("Customer/User " + custUserID + " already exists with store number " + storeNumber
						+ ", patient id " + patientId);}
				throw new CustomerAlreadyExistsException(storeNumber, custUserID);
			}
			;

			

		//	super.close();
            }catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
            	CommonUtil.closePreparedStatementResultSet(ps, rs);
            }
			Long prsnKey = null;
			Long prsnrlKey = null;
			PersonApi personApi = new PersonApi();
			if (customer != null) {
				List<Address> addressList = null;
				Person person = null;
				if(customer.getPerson() == null) {
					person  = new Person();
					customer.setPerson(person);
					addressList = new ArrayList<Address>();
				}
				else {
					person = customer.getPerson();
					addressList = customer.getPerson().getAddress(); 
				}
				
				 
				if(addressList.size() > 0){
				Address add = addressList.get(0);
				if(customer.getEmail() != null) {
				add.setEmail(customer.getEmail());
				}
				if(customer.getPhonenumber() != null) {
				add.setPrimaryPhoneNumber(customer.getPhonenumber());
				}
				}
				else{
					
					Address add = new Address();
					add.setEmail(customer.getEmail());
					add.setPrimaryPhoneNumber(customer.getPhonenumber());
					customer.getPerson().getAddress().add(add);
					
				}
				PersonRole customerPersonRole = new PersonRole(createCustomer);
				Store store = new Store();
				store.setStorenumber(storeNumber);

				prsnrlKey = personApi.upsertPerson(connection, store, customerPersonRole);
				if(logger.isDebugEnabled()) {logger.debug("person inserted with personKey " + prsnrlKey);}
				try {
				ps = connection.prepareStatement(QUERYPRSNRLKEYSQL);
				String personRoleTypeCode = (customerPersonRole.getRole() != null)
						? customerPersonRole.getRole().getRoleType() : null;
				Long roleTypeKey = (!StringUtil.isEmpty(personRoleTypeCode))
						? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PRSNRLTYP, personRoleTypeCode) : null;
			//	setPsLongParam(1, roleTypeKey);
				CommonUtil.setPsLongParam(ps,1, roleTypeKey);	
				CommonUtil.setPsLongParam(ps,2, prsnrlKey);
			//	setPsLongParam(2, prsnrlKey);
				rs = ps.executeQuery();
				if (rs.next()) {
					prsnKey = ResultSetWrapper.getLong(rs, "prsnKey".toUpperCase());

				}
		//		super.close();
				}finally {
					CommonUtil.closePreparedStatementResultSet(ps, rs);
				}
			}
			
			long custKey = IdGenerator.generate(connection, "CUST");
			String userType = null;
			String custTyp = null;
			Long chaneelTypKey ;
			Long commTypKey ;
			Long engagementTypeKey;
			try {
			ps = connection.prepareStatement(INSERTCUSTSQL);
			CommonUtil.setPsLongParam(ps, 1, custKey);
			CommonUtil.setPsLongParam(ps, 2, prsnKey);
			CommonUtil.setPsStringParam(ps, 3, customerId);
			
			if (customer.getUserId() != null && customer.getUserType() == null) {
				userType = UserTypeEnum.CUSTOMER.value();
				custTyp = UserTypeEnum.CUSTOMER.value();
			} else if (customer.getUserId() == null && customer.getUserType() == null) {
				userType = UserTypeEnum.PRE_REG_CUSTOMER.value();
				custTyp = UserTypeEnum.PRE_REG_CUSTOMER.value();
			} else {
				userType = customer.getUserType() != null ? customer.getUserType().value() : null;
				custTyp = customer.getUserType() != null ? customer.getUserType().value() : null;
			}
			CommonUtil.setPsStringParam(ps, 4, custTyp);
			CommonUtil.setPsStringParam(ps, 5, userType);
			
				CommonUtil.setPsStringParam(ps, 6, userId);
				CommonUtil.setPsStringParam(ps, 7, null);
			
			CommonUtil.setPsStringParam(ps, 8, customer.getOptimumNumber());
			  chaneelTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, pharmacyChannel.getChannelType().value());
			CommonUtil.setPsLongParam(ps, 9, chaneelTypKey);
			  commTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, pharmacyChannel.getCommunicationMode().value());
			CommonUtil.setPsLongParam(ps, 10, commTypKey);

			CommonUtil.setPsStringParam(ps, 11, customer.getDeactivationReason());
			CommonUtil.setPsIntParam(ps, 12, customer.getDetailedNotification());
			CommonUtil.setPsStringParam(ps, 13, customer.getStatus() != null ? customer.getStatus().value() : null);
			CommonUtil.setPsStringParam(ps, 14, customer.getRequestSource());
			CommonUtil.setPsStringParam(ps, 15, customer.getRegistrationReminderId());
			CommonUtil.setPsIntParam(ps, 16, customer.getSuccesfullLoginAttempt());
			CommonUtil.setPsStringParam(ps, 17, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
		        CommonUtil.setPsStringParam(ps, 18, CommonUtil.toTimestampStr(customer.getLastLoginTimestamp()));
			String engagementTypeCode = (pharmacyChannel!=null && pharmacyChannel.getEngagment()!=null) ? pharmacyChannel.getEngagment().value() : null;
			  engagementTypeKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engagementTypeCode);
			CommonUtil.setPsLongParam(ps, 19, engagementTypeKey);
			CommonUtil.setPsIntParam(ps, 20, customer.getVersion());
			//Drop59 Division Changes
			
		//	CommonUtil.setPsStringParam(ps, 21, customer.getDivision() != null ? customer.getDivision().value() : null);
			 CommonUtil.setPsStringParam(ps, 21,divisionEnum);
			ps.executeUpdate();

	//		super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
			
			if(patientId != null) {
			try {	
			long cDRPtntMpngKey = IdGenerator.generate(connection, "CDRPtntMpng");
			ps = connection.prepareStatement(INSERTCDRPTNTMPNGSQL);

			CommonUtil.setPsLongParam(ps, 1, cDRPtntMpngKey);
			
			CommonUtil.setPsStringParam(ps, 2, patientId);
			CommonUtil.setPsLongParam(ps, 3, ptntKey);
			
			CommonUtil.setPsLongParam(ps, 4, custKey);
			CommonUtil.setPsStringParam(ps, 5, CommonUtil.getCurrentTimeStamp());
			CommonUtil.setPsStringParam(ps, 6,  CommonUtil.getCurrentTimeStamp());
			CommonUtil.setPsLongParam(ps, 7, chaneelTypKey);
			CommonUtil.setPsLongParam(ps, 8, commTypKey);
			CommonUtil.setPsLongParam(ps, 9, engagementTypeKey);
			if (userId != null && CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equalsIgnoreCase(userType)) {
				CommonUtil.setPsStringParam(ps, 10, "Y");
				CommonUtil.setPsStringParam(ps, 11, userType);
			}
			else if(customerId != null && CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equalsIgnoreCase(userType)) {
				CommonUtil.setPsStringParam(ps, 10, "N");
				CommonUtil.setPsStringParam(ps, 11, userType);
			}
			

			ps.executeUpdate();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		//	super.close();
			}

			insertPrsnPref(connection, customer, prsnKey);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		//	throw new CreateCustomerException(storeNumber, custUserID, e.getMessage());
		} catch (CustomerAlreadyExistsException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
	//		super.close();
		}
	}

	public void updateCustomer(Connection connection, UpdateCustomer updateCustomer) throws SQLException,
			NamingException, IOException, 
			InvalidInputException,  CustomerNotFoundException,
			UpdateCustomerException, CustomerAlreadyExistsAndRegisteredException, CustomerChannelNotFoundException, CustomerTypeNotFoundException, SourceSystemNotValidException, CodeNotFoundFromTableCacheException, CDRInternalException,DivisionTypeNotFoundException {
		String customerId = null;
		String userId = null;
		String storeNumber = null;
		String patientId = null;
		String custUserId = null;
		try {

			Customer customer = updateCustomer.getCustomer();

			customerId = customer.getCustomerid();
			userId = customer.getUserId();

			
			String userTypeEnum = customer.getUserType() != null ? customer.getUserType().name() : null;

			
			if (!FindEnum.contains(UserTypeEnum.class, userTypeEnum)) {
				if(logger.isErrorEnabled()) {logger.error("Customer Type " + userTypeEnum + " not found");}
				throw new CustomerTypeNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
			//Drop59 Division Changes
			
			String divisionEnum = customer.getDivision() != null ? customer.getDivision().name() : null;
			
			
			if ( !FindEnum.contains(Division.class, divisionEnum)) {
				divisionEnum = "SDM";
			//	logger.error("Division  " + divisionEnum + " not found");
		    //		throw new DivisionTypeNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
			divisionEnum = divisionEnum.toUpperCase();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String carerecKeys = null;
			
			//Drop59 Division Changes
			if(customer.getUserType() != null && CDREnumerations.PersonRoleType.CAREGIVER.getRoleType().equalsIgnoreCase(customer.getUserType().value())) {
				try {
				ps = connection.prepareStatement(UPDATECUSTCUSTOMERSQLUSERID);

				ps.setString(1, userId);

				rs = ps.executeQuery();

				if (!rs.next()) {
					if(logger.isErrorEnabled()) {logger.error("customer/user " + userId + " not found");}
					throw new CustomerNotFoundException(storeNumber ,patientId , customerId ,userId);
				}
				;
				
					  carerecKeys = rs.getString("CARERCVKEYS");
					
					
					
					if (carerecKeys == null) {
						if(logger.isErrorEnabled()) {logger.error("customer/user " + userId + " not found");}
						throw new InvalidInputException("User ID : "+userId+" is not a valid Caregiver for Update Customer");
					}
					;
					
			//		super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } 
			finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
				PreparedStatement ps1   = null;
				ResultSet   rs1=null;
					String[] carreckeys = carerecKeys.split(",");
					
					for(String keyCarerec : carreckeys){
					try {	
					ps1 = connection.prepareStatement(CHECKCAREGIVERQUERY);

					ps1.setString(1, keyCarerec);

					rs1 = ps1.executeQuery();
					
					if(rs1.next()){
					
					Long PRSNPREFKey = rs1.getLong("PRSNPREFkey");
					
					
					
					 if(PRSNPREFKey != null) {
							updatePrsnPref(connection, customer, PRSNPREFKey);
						}
				
					}
					}catch (SQLException e) {
	                    e.printStackTrace();
	                    throw e;
	                  } finally {
						CommonUtil.closePreparedStatementResultSet(ps1, rs1);
					}
					  }
			//		super.close();
				
			}
			else {
			if(customer.getUserType() != null){
				
				if (userId != null
						&& CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().value())) {
					throw new InvalidInputException("User Type should be Customer when User ID is present");
				}
				
				if (userId != null
						&& !(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().value()) || CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().value()))) {
					throw new InvalidInputException("User Type should be Pre Register Customer or Customer for Update Customer");
				}
				
				/*if (customerId != null && userId == null
						&& CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equalsIgnoreCase(customer.getUserType().name())) {
					throw new InvalidInputException("User ID is mandatory when User type is Customer");
				}*/
			}
			
			
			
			if ((customerId == null || customerId.trim().length() == 0)
					&& (userId == null || userId.trim().length() == 0)) {
				throw new InvalidInputException("Customer ID or User ID mandatory for update Customer");
			}

			
			
			

			PharmacyChannel pharmacyChannel = customer.getSourceChannel();
			if (pharmacyChannel != null ) {
			
			Long channelKey = null;
			if(pharmacyChannel != null){
				String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
				if(channelID != null){
			try {
				channelKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for " + channelID);}
				throw new CustomerChannelNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer Channel type in request is not valid for " + channelID);}
					throw new CustomerChannelNotFoundException(storeNumber ,patientId , customerId ,userId);
				}
			}

			Long communicationKey = null;
			if(pharmacyChannel != null){
				String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
				if(communicationID != null){
			try {
				communicationKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, communicationID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer Communication type in request is not valid for " + communicationID);}
				throw new InvalidInputException("Customer Communication Mode is not valid for update Customer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer Communication type in request is not valid for " + communicationID);}
					throw new InvalidInputException("Customer Communication Mode is not valid for update Customer");
				}
			}
			
			Long engagementKey = null;
			if(pharmacyChannel != null){
				String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
				if(engID != null){
			try {
				engagementKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The Customer engagement type in request is not valid for " + engID);}
				throw new InvalidInputException("Customer engagement type is not valid for update Customer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("The Customer engagement type in request is not valid for " + engID);}
					throw new InvalidInputException("Customer engagement type is not valid for update Customer");
				}
			}

			}

			String customerExistQuery = null;

			if (customerId != null) {
				customerExistQuery = ALREADYEXISTCUSTOMERSQLCUSTID;
			} else if (userId != null) {
				customerExistQuery = ALREADYEXISTCUSTOMERSQLUSERID;
			}

			 custUserId = customerId != null ?  customerId : userId ;
			 long prsnKey = 0L;
			long custKey=0L;
			String custType=null;
			long PRSNPREFKey=0L;
			Long cdrPtntMpngKey=0L;
			PreparedStatement ps2=null;
			ResultSet rs2=null;
			try { 
			ps2 = connection.prepareStatement(customerExistQuery);

			ps2.setString(1, custUserId);

			rs2 = ps2.executeQuery();

			if (!rs2.next()) {
				if(logger.isErrorEnabled()) {logger.error("customer/user " + custUserId + " not found");}
				throw new CustomerNotFoundException(storeNumber ,patientId , customerId ,userId);
			}
			;

			  custKey = rs2.getLong("custkey");
			  prsnKey = rs2.getLong("prsnkey");
			 cdrPtntMpngKey = rs2.getLong("cdrptntmpngkey");
			 PRSNPREFKey = rs2.getLong("PRSNPREFkey");
			 custType = rs2.getString("CUSTTYP");

	//		super.close();
			

			if (!custType.trim().equalsIgnoreCase(userTypeEnum)
					&& custType.equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())) {
				if(logger.isErrorEnabled()) {logger.error("customer already exist and registered " + custUserId);}
				throw new CustomerAlreadyExistsAndRegisteredException(storeNumber ,patientId , customerId ,userId);
			}
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps2, rs2);
			}
			String cnsmrid = null;
			PreparedStatement ps3=null;
			ResultSet rs3=null;
			try {
			ps3 = connection.prepareStatement(QUERYCNTCTMTHDSQL);

			ps3.setLong(1, prsnKey);

			rs3 = ps3.executeQuery();

			if (rs3.next()) {
				cnsmrid = rs3.getString("CNSMRID");
			}
			;
		
		//	super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps3, rs3);
			}
			
			

			
				
			
			Long prsnrlKey = null;
			PersonApi personApi = new PersonApi();
			PreparedStatement ps4 = null;
			ResultSet rs4=null;
			if (customer != null) {
				
				try {
				ps4 = connection.prepareStatement(GETADDRESSQUERY);

				ps4.setLong(1, prsnKey);

				rs4 = ps4.executeQuery();
				
				List<Address> addressList = null;
				Person person = null;
				if(customer.getPerson() == null) {
					person  = new Person();
					customer.setPerson(person);
					addressList = new ArrayList<Address>();
				}
				else {
					person = customer.getPerson();
					addressList = customer.getPerson().getAddress(); 
				}
				
				if (addressList.size() > 0) {
					Address add = addressList.get(0);
					while(rs4.next()) {
					traversrPersonAddress(rs4, add, person,customer);
					}
					
				}
				else{
					Address add = new Address();
					while(rs4.next()) {
					traversrPersonAddress(rs4, add, person,customer);
					}
					
				}
			//	super.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } finally {
					CommonUtil.closePreparedStatementResultSet(ps4, rs4);
				}
				PersonRole customerPersonRole = new PersonRole(updateCustomer);

				prsnrlKey = personApi.upsertPersonCustomer(connection, customerPersonRole, prsnKey);
				if(logger.isDebugEnabled()) {logger.debug("person updated with personKey " + prsnrlKey);}

		//		super.close();
			

				StringBuilder custUpdateQuery = new StringBuilder();

				String userType = customer.getUserType() != null ? customer.getUserType().value() : null;

				if (userType != null) {
					custUpdateQuery.append(" , CUSTTYP = '" + userType+"'");
				}
				
				

				if (userId != null) {
					custUpdateQuery.append(", USERID = '" + userId+"'");
				}

				if (customer.getOptimumNumber() != null) {
					custUpdateQuery.append(", OPTNUM = '" + customer.getOptimumNumber()+"'");
				}

				if (pharmacyChannel != null && pharmacyChannel.getChannelType() != null) {
					Long chaneelTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, pharmacyChannel.getChannelType().value());
					custUpdateQuery.append(", PHARMACYCHNLTYPKEY = " + chaneelTypKey);
				}

				if (pharmacyChannel!= null && pharmacyChannel.getCommunicationMode() != null) {
					Long commTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, pharmacyChannel.getCommunicationMode().value());
					custUpdateQuery.append(", COMMMODETYPKEY = " + commTypKey);
				}

				if (customer.getDeactivationReason() != null) {
					custUpdateQuery.append(", DEACTIVATIONRSN = '" + customer.getDeactivationReason()+"'");
				}

				if (customer.getDetailedNotification() != null) {
					custUpdateQuery.append(", DETTAILNT = " + customer.getDetailedNotification());
				}

				if (customer.getStatus() != null) {
					custUpdateQuery.append(", STATUS = '" + customer.getStatus().value()+"'");
				}

				if (customer.getRequestSource() != null) {
					custUpdateQuery.append(", REQSRCDESC = '" + customer.getRequestSource()+"'");
				}

				if (customer.getRegistrationReminderId() != null) {
					custUpdateQuery.append(", REGREMID = " + customer.getRegistrationReminderId());
				}

				if (customer.getSuccesfullLoginAttempt() != null) {
					custUpdateQuery.append(", SUCCESSLGNCNT = " + customer.getSuccesfullLoginAttempt());
				}
				
				if(pharmacyChannel!= null && pharmacyChannel.getEngagment() != null){
					String engagementTypeCode = (pharmacyChannel!=null && pharmacyChannel.getEngagment()!=null) ? pharmacyChannel.getEngagment().value() : null;
					Long engagementTypeKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engagementTypeCode);
					custUpdateQuery.append(", ENGMNTTYPKEY = " + engagementTypeKey);
				}
				
				if (customer.getVersion() != null) {
					custUpdateQuery.append(", RECUPDCNT = " + customer.getVersion());
				}
				
				if(customer.getCreationTimestamp() != null) {
					//custUpdateQuery.append(", LSTUPDTTIMESTAMP = '" + CommonUtil.toTimestampStr(customer.getCreationTimestamp())+"'");
					custUpdateQuery.append(", LSTUPDTTIMESTAMP = " + CommonUtil.TO_TIMESTAMP_TZ );
				}
				
				if (customer.getLastLoginTimestamp() != null) {
					custUpdateQuery.append(", LSTLGNTIMESTAMP = " + CommonUtil.TO_TIMESTAMP_TZ);
				}
				custUpdateQuery.append(", DIVISION = '" +divisionEnum+"'");
				/*//Drop59 Division Changes
				if (customer.getDivision() != null) {
					custUpdateQuery.append(", DIVISION = '" + customer.getDivision().value()+"'");
				}
				else {
					custUpdateQuery.append(", DIVISION = NULL");
				}*/
				
			
				String custUpdateMainQuery = UPDATECUSTSQL2 + custUpdateQuery.toString() + UPDATECUSTWHERE;
				PreparedStatement ps5=null;
				ResultSet rs5=null;
                //CommonUtil.getPsToDateFunctionStr()
				try {
				ps5 = connection.prepareStatement(custUpdateMainQuery);
				CommonUtil.setPsStringParam(ps5, 1, userType);
				if (customer.getLastLoginTimestamp() != null && customer.getCreationTimestamp() !=null) {
					CommonUtil.setPsStringParam(ps5, 2, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
					CommonUtil.setPsStringParam(ps5, 3, CommonUtil.toTimestampStr(customer.getLastLoginTimestamp()));
					CommonUtil.setPsLongParam(ps5, 4, custKey);
				} 
				else if (customer.getLastLoginTimestamp() != null || customer.getCreationTimestamp() !=null) {
					if(customer.getCreationTimestamp() != null){
						CommonUtil.setPsStringParam(ps5, 2, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));	
					}
				    if(customer.getLastLoginTimestamp() != null){
				    	CommonUtil.setPsStringParam(ps5, 2, CommonUtil.toTimestampStr(customer.getLastLoginTimestamp()));
				    }
				CommonUtil.setPsLongParam(ps5, 3, custKey);
				}
				else{
					CommonUtil.setPsLongParam(ps5, 2, custKey);
				}
				int res = ps5.executeUpdate();

				if (logger.isInfoEnabled())  {logger.info("total number of customers updated " + res);}

	//			super.close();
				}catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } finally {
					CommonUtil.closePreparedStatementResultSet(ps5, rs5);
				}
				PreparedStatement ps6=null;
				ResultSet rs6=null;
				if (!custType.trim().equalsIgnoreCase(userTypeEnum)
						&& custType.equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType())) {
					if(cdrPtntMpngKey != null) {
						try {
						ps6 = connection.prepareStatement(UPDATECDRPTNTMPNGSQL);

						CommonUtil.setPsStringParam(ps6, 1, userType);
						//CommonUtil.setPsLongParam(ps, 2, custKey);
						//LTPHCP-45 CDRPTNTUPDATE Timestamp
						CommonUtil.setPsStringParam(ps6, 2, CommonUtil.getCurrentTimeStamp());
						CommonUtil.setPsLongParam(ps6, 3, custKey);
						

						ps6.executeUpdate();

		//				super.close();
						}catch (SQLException e) {
		                    e.printStackTrace();
		                    throw e;
		                  } finally {
							CommonUtil.closePreparedStatementResultSet(ps6, rs6);
						}
					}
				}
				
				

			updatePrsnPref(connection, customer, PRSNPREFKey);	
			
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateCustomerException(custUserId, e.getMessage());
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
	//		super.close();
		}
	}
	
	/*public Map<String,Long> getCustKeyAndPTNTKey(Connection connection, Object object) throws InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SQLException, DatatypeConfigurationException, NamingException, ParseException, IOException, CDRInternalException, CustomerPatientAssociationExistException, CustomerTypeNotFoundException{
		Map<String,Long> custKeyPTNTKeyMap = new HashMap<String,Long>();
		try {
			String storeNumber = null;
			List<Patient> patientList = null;
			
			String customerId = null;
			String userId = null;
			String userTypeEnum =null;
			Customer customer=null;
			if (object instanceof AssociateCustomer) {
				patientList = ((AssociateCustomer) object).getPatient();
				if (((AssociateCustomer) object).getCustomer() != null) {
					customer=(((AssociateCustomer) object)).getCustomer();
					customerId = customer.getCustomerid();
					userId = customer.getUserId();
					userTypeEnum= customer.getUserType() != null ? customer.getUserType().name() : null;
				}

			}
			if (object instanceof DissociateCustomer) {

				patientList = ((DissociateCustomer) object).getPatient();
				if (((DissociateCustomer) object).getCustomer() != null) {
					customer=(((DissociateCustomer) object)).getCustomer();
					customerId = customer.getCustomerid();
					userId = customer.getUserId();
					userTypeEnum= customer.getUserType() != null ? customer.getUserType().name() : null;
				}
			}
			Map<String, String> custkeyMap = new HashMap<String, String>();
			Long custKey = null;

			for (Patient patient : patientList) {
				String patientId = patient.getConsumerId();

				logger.info(
						"StartsApicall : AssociateOrDissociateCustomerAPI : patientId :" + patientId + " customerId :" + customerId);
				if (patientId == null || patientId.trim().length() == 0) {
					throw new InvalidInputException("patient id not valid");
				}

				if ((customerId == null || customerId.trim().length() == 0)
						&& (userId == null || userId.trim().length() == 0)) {
					throw new InvalidInputException("Customer ID or User ID mandatory");
				}
				// Get StoreNumber from Patient
				if (patient.getStore() != null && patient.getStore().getStorenumber() != null) {
					storeNumber = patient.getStore().getStorenumber();
				}

				// Get PTNTKey
				PatientViewGet patientViewGet = new PatientViewGet();
				patientViewGet.fetch(connection, storeNumber, patientId);
				Long patientKey = patientViewGet.getPatientKey();

				if (patientKey == null) {
					throw new PatientNotFoundException(storeNumber, patientId);
				}

				String customerExistQuery = null;

				if (customerId != null) {
					customerExistQuery = ALREADYEXISTCUSTOMERSQLCUSTID;
				} else if (userId != null) {
					customerExistQuery = ALREADYEXISTCUSTOMERSQLUSERID;
				}

				String custUserId = customerId != null ? customerId : userId;

				ps = connection.prepareStatement(customerExistQuery);

				ps.setString(1, custUserId);

				rs = ps.executeQuery();

				if (!rs.next()) {
					logger.error("customer/user " + custUserId + " not found");
					throw new CustomerNotFoundException(storeNumber ,patientId , customerId ,userId);
				}
				;

				 custKey = rs.getLong("custkey");
				

				super.close();
				
                if (!FindEnum.contains(UserTypeEnum.class, userTypeEnum)) {
                    logger.error("Customer Type " + userTypeEnum + " not found");
                    throw new CustomerTypeNotFoundException(storeNumber ,patientId , customerId ,null);
              }
              

				if (object instanceof AssociateCustomer) {
					// Check whether the customer-patient association is already
					// exists
					boolean flag = isPatientAssociatedwithCustomer(connection, patientKey, custKey);
					if (flag) {
						throw new CustomerPatientAssociationExistException(customerId, storeNumber, patientId);
					}
				}
				
				
				//return map
				custKeyPTNTKeyMap.put("CUSTKEY", custKey);
				custKeyPTNTKeyMap.put("PTNTKEY",patientKey);

			}
			
		}catch(CDRInternalException e){
		e.printStackTrace();
		throw e;
	}catch (SQLException e) {
		e.printStackTrace();
		throw e;
	}  catch (DatatypeConfigurationException e) {
		e.printStackTrace();
		throw e;
	}catch (ParseException e) {
		e.printStackTrace();
		throw e;
	}catch (NamingException e) {
		e.printStackTrace();
		throw e;
	} catch (IOException e) {
		e.printStackTrace();
		throw e;
	}
	finally
	{
		super.close();
		logger.info("EndsApiCall : AssociateOrDissociateCustomerAPI");
	}
		return custKeyPTNTKeyMap;
	
	}*/
	
	@SuppressWarnings("unused")
	public void associateCustomer(Connection connection, AssociateCustomer associateCustomer) throws InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SQLException, DatatypeConfigurationException, ParseException, NamingException, IOException, CDRInternalException, CustomerPatientAssociationExistException, CustomerTypeNotFoundException, AssociateCustomerException, CustomerChannelNotFoundException, CustomerPatientAssociationNotExistException{
		Customer customer = null;
		String storeNumber = null;
		String patientId = null;
		
		Long custKey =null;
		Long patientKey=null;
		String userTypeEnum = null;
			String userId = null;
			String assoctyp = null;
			PreparedStatement ps = null;
			ResultSet rs =null;
		try{
		if(associateCustomer.getCustomer()!= null){			
	     customer = associateCustomer.getCustomer();
	     userTypeEnum= customer.getUserType() != null ? customer.getUserType().name() : null;
	     
				userId = customer.getUserId();

				if (userId == null || userId.trim().length() == 0) {
					throw new InvalidInputException("User ID is mandatory for Associate Customer");
				}	
			
	     if (!FindEnum.contains(UserTypeEnum.class, userTypeEnum)) {
             if(logger.isErrorEnabled()) {logger.error("User Type " + userTypeEnum + " not found");}
             throw new CustomerTypeNotFoundException(storeNumber ,patientId , null ,userId);
       }
	     
	     if(userTypeEnum != null && !(customer.getUserType().equals(UserTypeEnum.CUSTOMER) || customer.getUserType().equals(UserTypeEnum.CAREGIVER))) {
	    	 throw new InvalidInputException("User Type should be Customer or CareGiver for Associate Customer");
	     }
	     assoctyp = customer.getUserType().value();
	     ps = connection.prepareStatement(ALREADYEXISTCUSTOMERSQLUSERID);

			ps.setString(1, userId);

			rs = ps.executeQuery();

			if (!rs.next()) {
				if(logger.isErrorEnabled()) {logger.error("user " + userId + " not found");}
				throw new CustomerNotFoundException(storeNumber ,patientId , null ,userId);
			}
			;

			 custKey = rs.getLong("custkey");
			 Long cdrptntmpngkey = rs.getLong("cdrptntmpngkey");
			 Long prsnprefkey = rs.getLong("PRSNPREFKEY");
			 Long prsnKey = rs.getLong("PRSNKEY");

	//		super.close();
			 CommonUtil.closePreparedStatementResultSet(ps, rs);
			
			List<Patient> patients = associateCustomer.getPatient();
			List<Customer> careReceipents = associateCustomer.getCareRecipient();
			
			if(patients.size() == 0 && careReceipents.size() == 0) {
				throw new InvalidInputException("Either Patient or Care Receiver is mandatory for Associate Customer");
			}
	     
	     if(customer.getUserType()!=null && customer.getUserType().equals(UserTypeEnum.CUSTOMER)){
	    	 if (logger.isInfoEnabled())  {logger.info("StartsApicall : AssociateOrDissociateCustomerAPI : patientId :" + patientId + " userID :" + userId);}
				
	    	 
				
				 
		    	 for(Patient patient : patients) {
		    		 updateAssociateCustomer(connection,patient,customer,userTypeEnum,custKey);
		    	 }
								
	     }
	     else if(customer.getUserType()!=null && customer.getUserType().equals(UserTypeEnum.CAREGIVER)){
	    	 
	    	
	    	 for(Patient patient : patients) {
	    		 
	    		 updateAssociateCustomer(connection,patient,customer,userTypeEnum,custKey);
	    	 }
	    	 
	    
	    	 for(Customer careReceipent  : careReceipents) {
	    		 String userIdCR = careReceipent.getUserId();
	    		 Long custKeyCR =0L;
	    		 try {
	    		 ps = connection.prepareStatement(ALREADYEXISTCUSTOMERSQLUSERID);

					ps.setString(1, userIdCR);

					rs = ps.executeQuery();

					if (!rs.next()) {
						if(logger.isErrorEnabled()) {logger.error("user " + userIdCR + " not found");}
						throw new CustomerNotFoundException(null ,null , null ,userIdCR);
					}
					;

					 custKeyCR = rs.getLong("custkey");
					 Long cdrptntmpngkeyCR = rs.getLong("cdrptntmpngkey");
					 Long prsnprefkeyCR = rs.getLong("PRSNPREFKEY");
					 Long prsnKeyCR = rs.getLong("PRSNKEY");
	    		 }catch (SQLException e) {
	                    e.printStackTrace();
	                    throw e;
	                  } finally {
	    			 CommonUtil.closePreparedStatementResultSet(ps, rs);
	    		 }

				//	super.close();
				//Commented as part of OF-158 hotfix	
				/*	ps = connection.prepareStatement(ALREADYEXISTCARERECEIVER);

					ps.setLong(1, custKeyCR);
					ps.setLong(2, custKey);

					rs = ps.executeQuery();

					if (rs.next()) {
						logger.error("user " + userIdCR + " not found");
						throw new CustomerPatientAssociationExistException(userIdCR, null,null);
					}
					;

					
					super.close();*/
					
					PharmacyChannel pharmacyChannel = careReceipent.getVerificationChannel();
					if (pharmacyChannel == null ) {
						throw new InvalidInputException("Verification Channel is mandatory for Associate Customer");
					}
					Long channelKey = null;
					if(pharmacyChannel != null){
						String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
						if(channelID != null){
					try {
						channelKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification Channel type in request is not valid for " + channelID);}
						throw new CustomerChannelNotFoundException(storeNumber ,patientId , null ,userId);
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Verification Channel type in request is not valid for " + channelID);}
							throw new CustomerChannelNotFoundException(storeNumber ,patientId , null ,userId);
						}
					}

					Long communicationKey = null;
					if(pharmacyChannel != null){
						String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
						if(communicationID != null){
					try {
						communicationKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, communicationID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification Communication type in request is not valid for " + communicationID);}
						throw new InvalidInputException("Verification Communication Mode is not valid for Associate Customer");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Verification Communication type in request is not valid for " + communicationID);}
							throw new InvalidInputException("Verification Communication Mode is not valid for Associate Customer");
						}
					}
					
					Long engagementKey = null;
					if(pharmacyChannel != null){
						String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
						if(engID != null){
					try {
						engagementKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification Engegement type in request is not valid for " + engID);}
						throw new InvalidInputException("Verification Engagement Type is not valid for Associate Customer");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Customer Engegement Type in request is not valid for " + engID);}
							throw new InvalidInputException("Customer Engagement Type is not valid for Associate Customer");
						}
					}
						
							//long cDRPtntMpngKeyCR = IdGenerator.generate(connection, "CDRPtntMpng");
					try {
							ps = connection.prepareStatement(INSERTCARERECEIVERSQL);

							//CommonUtil.setPsLongParam(ps, 1, cDRPtntMpngKeyCR);
							CommonUtil.setPsLongParam(ps, 1, custKey);
							
							CommonUtil.setPsLongParam(ps, 2, channelKey);
							
							CommonUtil.setPsLongParam(ps, 3, communicationKey);
							
							CommonUtil.setPsLongParam(ps, 4, engagementKey);
							CommonUtil.setPsStringParam(ps, 5, "Y");
							CommonUtil.setPsStringParam(ps, 6, assoctyp);
							CommonUtil.setPsLongParam(ps, 7, custKeyCR);
							CommonUtil.setPsStringParam(ps, 8, CommonUtil.getCurrentTimeStamp());
							CommonUtil.setPsStringParam(ps, 9, CommonUtil.getCurrentTimeStamp());
							ps.executeUpdate();
					}catch (SQLException e) {
	                    e.printStackTrace();
	                    throw e;
	                  } finally {
						CommonUtil.closePreparedStatementResultSet(ps, null);
					}
						//	super.close();
							}
	
					
	    	 }
	     if(prsnprefkey != null) {
				updatePrsnPref(connection, customer, prsnprefkey);
			}
			else {
				insertPrsnPref(connection, customer, prsnKey);
			}	
	     
	     }
	  
	}catch(CDRInternalException e){
		e.printStackTrace();
		throw e;
	}catch (SQLException e) {
		e.printStackTrace();
	//	throw new AssociateCustomerException(userId,patientId,e.getMessage());
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
	//	super.close();
		if (logger.isInfoEnabled())  {logger.info("EndsApiCall : associateCustomer");}
	}
}
	

	private void updateAssociateCustomer(Connection connection,Patient patient,Customer customer,String assotyp,Long custKey) throws SQLException, InvalidInputException, NamingException, IOException, CustomerChannelNotFoundException, PatientNotFoundException, CustomerPatientAssociationExistException, CustomerNotFoundException, KeyNotFoundFromTableCacheException {
		Long timer = 0L;
		String storeNumber = null;
		String patientId = null;
		Long patientKey = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		assotyp = customer.getUserType().value();
			String userId = null;
			String ptntCreateTimestamp =null;
			String ptntLastUpdateTimestamp =null;
	     try{
	    	 patientId = patient.getConsumerId();
    		 if (patientId == null || patientId.trim().length() == 0) {
					throw new InvalidInputException("Consumer ID is mandatory for Associate Customer");
				}
    		 
    		 
    		// Get StoreNumber from Patient
				if (patient.getStore() != null ) {
					Store storepat = patient.getStore();
					storeNumber = storepat.getStorenumber() != null && !"".equals(storepat.getStorenumber()) ? CommonUtil.createStoreLeadingZeros(storepat.getStorenumber()) : null;
					
					if (storeNumber == null || storeNumber.trim().length() == 0) {
						throw new InvalidInputException("Store Number is mandatory for Associate Customer");
					}
				}
				
				
				  PharmacyChannel pharmacyChannel = patient.getVerificationChannel();
					if (pharmacyChannel == null ) {
						throw new InvalidInputException("Verification Channel is mandatory for Associate Customer");
					}
					Long channelKey = null;
					if(pharmacyChannel != null){
						String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
						if(channelID != null){
					try {
						channelKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification Channel type in request is not valid for %s " , channelID);}
						throw new CustomerChannelNotFoundException(storeNumber ,patientId , customer.getCustomerid() ,customer.getUserId());
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Verification Channel type in request is not valid for %s" , channelID);}
							throw new CustomerChannelNotFoundException(storeNumber ,patientId , customer.getCustomerid() ,customer.getUserId());
						}
					}
				
					Long communicationKey = null;
					if(pharmacyChannel != null){
						String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
						if(communicationID != null){
					try {
						communicationKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, communicationID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification Communication type in request is not valid for  %s" , communicationID);}
						throw new InvalidInputException("Verification Communication Mode is not valid for Associate Customer");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Verification Communication type in request is not valid for %s" , communicationID);}
							throw new InvalidInputException("Verification Communication Mode is not valid for Associate Customer");
						}
					}
					
					Long engagementKey = null;
					if(pharmacyChannel != null){
						String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
						if(engID != null){
					try {
						engagementKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engID);
					} catch (KeyNotFoundFromTableCacheException e) {
						if(logger.isErrorEnabled()) {logger.error("The Verification engagement type in request is not valid for %s " , engID);}
						throw new InvalidInputException("Verification engagement Mode is not valid for Associate Customer");
					}
						}
						else{
							if(logger.isErrorEnabled()) {logger.error("The Verification engagement type in request is not valid for %s " , engID);}
							throw new InvalidInputException("Verification engagement Mode is not valid for Associate Customer");
						}
					}
				try {	
				ps = connection.prepareStatement(QUERYPTNTKEYSQL);
				CommonUtil.setPsStringParam(ps, 1, patientId);
				CommonUtil.setPsStringParam(ps, 2, storeNumber);
				rs = ps.executeQuery();
				if (!rs.next()) // No patient record found
				{
					if(logger.isErrorEnabled()) {logger.error("patient not found for store number %s, patient id %s" ,storeNumber, patientId);}
					throw new PatientNotFoundException(storeNumber, patientId);
				}

				 patientKey = rs.getLong("PTNTKEY");

				  ptntCreateTimestamp = rs.getString("CRTTIMESTAMP");
				  
				  ptntLastUpdateTimestamp = rs.getString("LSTUPDTTIMESTAMP");
				}catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } finally {
					CommonUtil.closePreparedStatementResultSet(ps, rs);
				}
			//	super.close();
				try {
				ps = connection.prepareStatement(PTNTEXISTINCDRPTNTSQL);
				CommonUtil.setPsLongParam(ps, 1, patientKey);
				CommonUtil.setPsLongParam(ps, 2, custKey);
				rs = ps.executeQuery();
				if (rs.next()) // No patient record found
				{
					if(logger.isErrorEnabled()) {logger.error("patient already associated with the customer %s, patient id  %s" ,userId ,patientId);}
					throw new CustomerPatientAssociationExistException(userId,patientId,storeNumber);
				}

				}catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } finally {
					CommonUtil.closePreparedStatementResultSet(ps, rs);
				}

			//	super.close();
				
								
					updateCDRPTNTMPNGTable(connection,patientKey,custKey,"Y",patient ,ptntCreateTimestamp,ptntLastUpdateTimestamp,assotyp);//Set assocflag as 1  	
					

			if(logger.isDebugEnabled()) {logger.debug("SQL execute time is : %s ms" , (System.currentTimeMillis() - timer) );}
	     }catch(SQLException e){
	    	e.printStackTrace();
	    	throw e;
	     }
		
	}
	
	
	private void updateCDRPTNTMPNGTable(Connection connection, Long patientKey, Long custKey ,String flag,Patient patient,String   ptntCreateTimestamp,String ptntLastUpdateTimestamp,String assotyp) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long timer = 0L;
		PreparedStatement ps = null;
		try{
		timer = System.currentTimeMillis();
		ps = connection.prepareStatement(UPSERTCDRPTNTMPNG);
		//logger.trace("UpdateQuery : " + UPSERTCDRPTNTMPNG );

		CommonUtil.setPsStringParam(ps, 1, patient.getConsumerId());
		CommonUtil.setPsLongParam(ps, 2, patientKey);
		CommonUtil.setPsLongParam(ps,3, custKey);
		CommonUtil.setPsStringParam(ps, 4, CommonUtil.getCurrentTimeStamp());
		CommonUtil.setPsStringParam(ps, 5, CommonUtil.getCurrentTimeStamp());
		PharmacyChannel sourceChannel = patient.getVerificationChannel();
		Long chaneelTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PHARMACYCHNLTYP, sourceChannel.getChannelType().value());
		CommonUtil.setPsLongParam(ps, 6, chaneelTypKey);
		Long commTypKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_COMMMODETYP, sourceChannel.getCommunicationMode().value());
		CommonUtil.setPsLongParam(ps, 7, commTypKey);
		String engagementTypeCode = (sourceChannel!=null && sourceChannel.getEngagment()!=null) ? sourceChannel.getEngagment().value() : null;
		Long engagementTypeKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_ENGMNTTYP, engagementTypeCode);
		CommonUtil.setPsLongParam(ps, 8, engagementTypeKey);
		CommonUtil.setPsStringParam(ps, 9, flag);
		CommonUtil.setPsStringParam(ps, 10, assotyp);
			
	    ps.executeUpdate();
	//    super.close();
	    if(logger.isDebugEnabled()) {logger.debug("SQL execute time is : %s ms " , (System.currentTimeMillis() - timer) );}
	    }catch(SQLException e){
		 e.printStackTrace();
		 throw e;
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, null);
		}
		
	}
	
	
	@SuppressWarnings("unused")
	public void dissociateCustomer(Connection connection, DissociateCustomer dissociateCustomer) throws InvalidInputException, PatientNotFoundException, CustomerNotFoundException, SQLException, DatatypeConfigurationException, ParseException, NamingException, IOException, CDRInternalException, CustomerPatientAssociationExistException, CustomerTypeNotFoundException, DissociateCustomerException, CustomerChannelNotFoundException, CustomerPatientAssociationNotExistException{
		Customer customer = null;
		String storeNumber = null;
		String patientId = null;
		
		Long custKey =null;
		Long patientKey=null;
		String userTypeEnum = null;
			String userId = null;
			String assoctyp = null;
			PreparedStatement ps =null;
			ResultSet rs =null;
			 Long cdrptntmpngkey = 0L;
		try{
		if(dissociateCustomer.getCustomer()!= null){			
	     customer = dissociateCustomer.getCustomer();
	     userTypeEnum= customer.getUserType() != null ? customer.getUserType().name() : null;
	     
				userId = customer.getUserId();

				if (userId == null || userId.trim().length() == 0) {
					throw new InvalidInputException("User ID is mandatory for Dissociate Customer");
				}	
			
	     if (!FindEnum.contains(UserTypeEnum.class, userTypeEnum)) {
             if(logger.isErrorEnabled()) {logger.error("User Type %s not found",userTypeEnum);}
             throw new CustomerTypeNotFoundException(storeNumber ,patientId , null ,userId);
       }
	     
	     if(userTypeEnum != null && !(customer.getUserType().equals(UserTypeEnum.CUSTOMER) || customer.getUserType().equals(UserTypeEnum.CAREGIVER))) {
	    	 throw new InvalidInputException("User Type should be Customer or CareGiver for Dissociate Customer");
	     }
	     try {
	     ps = connection.prepareStatement(ALREADYEXISTCUSTOMERSQLUSERID);

			ps.setString(1, userId);

			rs = ps.executeQuery();

			if (!rs.next()) {
				if(logger.isErrorEnabled()) {logger.error("user %s not found",userId);}
				throw new CustomerNotFoundException(storeNumber ,patientId , null ,userId);
			}
			;

			 custKey = rs.getLong("custkey");
			   cdrptntmpngkey = rs.getLong("cdrptntmpngkey");
			 Long prsnprefkey = rs.getLong("PRSNPREFKEY");
			 Long prsnKey = rs.getLong("PRSNKEY");
	     }catch (SQLException e) {
             e.printStackTrace();
            throw e;
          } finally {
	    	 CommonUtil.closePreparedStatementResultSet(ps, rs);
	     }
	//		super.close();
			

		   if(customer.getUserType()!=null){
		    	 List<Patient> patients = dissociateCustomer.getPatient();
		    	
		    	 for(Patient patient : patients) {
		    		 
		    		 updateDissociateCustomer(connection,patient,customer,userTypeEnum,cdrptntmpngkey,custKey);
		    	 }
		    	 List<Customer> careReceipents = dissociateCustomer.getCareRecipient();
		    
		    	 for(Customer careReceipent  : careReceipents) {
		    		 String userIdCR = careReceipent.getUserId();
		    		 Long custKeyCR = 0L;
		    		 try {
		    		 ps = connection.prepareStatement(ALREADYEXISTCUSTOMERSQLUSERID);

						ps.setString(1, userIdCR);

						rs = ps.executeQuery();

						if (!rs.next()) {
							if(logger.isErrorEnabled()) {logger.error("user  %s  not found",userIdCR);}
							throw new CustomerNotFoundException(null ,null , null ,userIdCR);
						}
						;

						   custKeyCR = rs.getLong("custkey");
						 Long cdrptntmpngkeyCR = rs.getLong("cdrptntmpngkey");
						 Long prsnprefkeyCR = rs.getLong("PRSNPREFKEY");
						 Long prsnKeyCR = rs.getLong("PRSNKEY");
		    		 }catch (SQLException e) {
		                    e.printStackTrace();
		                    throw e;
		                  } finally {
		    			 CommonUtil.closePreparedStatementResultSet(ps, rs);
		    		 }
				//		super.close();
						try {
						ps = connection.prepareStatement(ALREADYEXISTCARERECEIVER);

						ps.setLong(1, custKeyCR);
						ps.setLong(2, custKey);

						rs = ps.executeQuery();

						if (!rs.next()) {
							if(logger.isErrorEnabled()) {logger.error("user %s not found",userIdCR);}
							throw new CustomerPatientAssociationNotExistException(userIdCR, null);
						}
						;
						}catch (SQLException e) {
		                    e.printStackTrace();
		                    throw e;
		                  } finally {
							CommonUtil.closePreparedStatementResultSet(ps, rs);
						}
						
					//	super.close();
						if(customer.getUserType().equals(UserTypeEnum.CUSTOMER)){
							
							if(customer.getCreationTimestamp() != null) {
								try {
								ps = connection.prepareStatement(UPDPRSNPREFLSTMDFTMS);
								CommonUtil.setPsStringParam(ps, 1, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
								CommonUtil.setPsLongParam(ps,2, custKey);
								 ps.executeUpdate();
								}finally {
									CommonUtil.closePreparedStatementResultSet(ps, rs);
								}
								//    super.close();
							}
								
								
							try {
							ps = connection.prepareStatement(DEASSOCIATECARERECEIVERSQL);
							CommonUtil.setPsStringParam(ps, 1, CommonUtil.getCurrentTimeStamp());
							CommonUtil.setPsLongParam(ps, 2, custKeyCR);
							CommonUtil.setPsLongParam(ps, 3, custKey);
							

							ps.executeUpdate();
							}catch (SQLException e) {
			                    e.printStackTrace();
			                    throw e;
			                  } finally {
								CommonUtil.closePreparedStatementResultSet(ps, rs);
							}

						//	super.close();
												
							}
							else if(customer.getUserType().equals(UserTypeEnum.CAREGIVER)){
								if(customer.getCreationTimestamp() != null) {
									try {
									ps = connection.prepareStatement(UPDCUSTLSTUPDTMS);
									CommonUtil.setPsStringParam(ps, 1, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
									CommonUtil.setPsLongParam(ps,2, custKey);
									 ps.executeUpdate();
									}catch (SQLException e) {
					                    e.printStackTrace();
					                    throw e;
					                  }finally {
										CommonUtil.closePreparedStatementResultSet(ps, rs);
									}
								//	    super.close();
								}
								try {
								ps = connection.prepareStatement(DEASSOCIATEHARDCARERECEIVERSQL);
								CommonUtil.setPsLongParam(ps, 1, custKeyCR);
								CommonUtil.setPsLongParam(ps, 2, custKey);
								

								ps.executeUpdate();
								}catch (SQLException e) {
				                    e.printStackTrace();
				                    throw e;
				                  } finally {
									CommonUtil.closePreparedStatementResultSet(ps, rs);
								}
							//	super.close();
													
							}
							
							
						
								
								

								
								}
		
						
		    	 }
			
		}
		
		}catch(CDRInternalException e){
			e.printStackTrace();
			throw e;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DissociateCustomerException(null ,patientId , null ,userId);
		
		}  catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
	//		super.close();
			if (logger.isInfoEnabled())  {logger.info("EndsApiCall : associateCustomer");}
		}
	}
	
	private void insertPrsnPref(Connection connection,Customer customer,Long prsnKey) throws SQLException {
		PreparedStatement ps =null;
		try {
			Preference preference = customer.getPreference();
			if(preference == null){
				String langPref = customer.getLanguage();

				long PRSNPREFKey = IdGenerator.generate(connection, "PRSNPREF");
				try {
				ps = connection.prepareStatement(INSERTPRSNPREFWITHOUTSQL);
				CommonUtil.setPsLongParam(ps, 1, PRSNPREFKey);
				CommonUtil.setPsStringParam(ps, 2, langPref);
				
				CommonUtil.setPsLongParam(ps, 3, prsnKey);
				ps.executeUpdate();
				}catch (SQLException e) {
                    e.printStackTrace();
                   throw e;
                 } finally {
					CommonUtil.closePreparedStatementResultSet(ps, null);
				}
			}
			else{
			String langPref = customer.getLanguage();
			try {
			long PRSNPREFKey = IdGenerator.generate(connection, "PRSNPREF");
			String RflRmndr = preference.getIsautoreminderselected();
			String DrgDtlInFrmationFlg = preference.getIsnotificationdetailshowingdrug();
			ps = connection.prepareStatement(INSERTPRSNPREFSQL);
			CommonUtil.setPsLongParam(ps, 1, PRSNPREFKey);
			CommonUtil.setPsStringParam(ps, 2, langPref);
			CommonUtil.setPsStringParam(ps, 3, RflRmndr);
			CommonUtil.setPsStringParam(ps, 4, DrgDtlInFrmationFlg);
			CommonUtil.setPsLongParam(ps, 5, prsnKey);
			CommonUtil.setPsStringParam(ps, 6, preference.getEmailFillNotification());
			CommonUtil.setPsStringParam(ps, 7, preference.getSmsFillNotification());
			CommonUtil.setPsStringParam(ps, 8, preference.getEmailPickupNotification());
			CommonUtil.setPsStringParam(ps, 9, preference.getSmsPickupNotification());
			CommonUtil.setPsStringParam(ps, 10, preference.getEmailNotification());
			CommonUtil.setPsStringParam(ps, 11, preference.getSmsNotification());
			CommonUtil.setPsStringParam(ps, 12, preference.getEmailMarketing());
			CommonUtil.setPsLongParam(ps, 13, preference.getGlobalAutoFill());
			CommonUtil.setPsStringParam(ps, 14, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
			
			ps.executeUpdate();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, null);
			}
			}
		//	super.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	private void updatePrsnPref(Connection connection,Customer customer,Long PRSNPREFKey) throws SQLException {
		StringBuilder PRSNPREFUpdateQuery = new StringBuilder();
		Preference preference = customer.getPreference();
		try {
		if (preference != null) {
			String langPref = customer.getLanguage();
			if (customer.getLanguage() != null) {
				PRSNPREFUpdateQuery.append(" LANGPREF = '" + langPref+"' , ");
			}

			String RflRmndr = preference.getIsautoreminderselected();

			if (RflRmndr != null) {
				PRSNPREFUpdateQuery.append(" RFLRMNDR = '" + RflRmndr+"' , ");
			}
			String DrgDtlInFrmationFlg = preference.getIsnotificationdetailshowingdrug();

			if (DrgDtlInFrmationFlg != null) {
				PRSNPREFUpdateQuery.append(" DRGDTLINFRMATIONFLG = '" + DrgDtlInFrmationFlg+"' , ");
			}

			if (preference.getEmailFillNotification() != null) {
				PRSNPREFUpdateQuery.append(" FILLNTFEML = " + preference.getEmailFillNotification()+" , ");
			}

			if (preference.getSmsFillNotification() != null) {
				PRSNPREFUpdateQuery.append("  FILLNTFSMS = '" + preference.getSmsFillNotification()+"' , ");
			}

			if (preference.getEmailPickupNotification() != null) {
				PRSNPREFUpdateQuery.append("  PICKUPNTFEML = " + preference.getEmailPickupNotification()+" , ");
			}

			if (preference.getSmsPickupNotification() != null) {
				PRSNPREFUpdateQuery.append("  PICKUPNTFSMS = '" + preference.getSmsPickupNotification()+"' , ");
			}

			if (preference.getEmailNotification() != null) {
				PRSNPREFUpdateQuery.append("  NTFEML = " + preference.getEmailNotification()+" , ");
			}

			if (preference.getSmsNotification() != null) {
				PRSNPREFUpdateQuery.append("  NTFSMS = " + preference.getSmsNotification()+" , ");
			}

			if (preference.getEmailMarketing() != null) {
				PRSNPREFUpdateQuery.append("  MRKTEML = " + preference.getEmailMarketing()+" , ");
			}
			
			if (preference.getGlobalAutoFill() != null) {
				PRSNPREFUpdateQuery.append("  GLBAUOTFILL = " + preference.getGlobalAutoFill()+" , ");
			}
			
			if (customer.getCreationTimestamp() != null) {
				//PRSNPREFUpdateQuery.append("  LSTMDFTS = " + CommonUtil.toTimestampStr(customer.getCreationTimestamp()) +" , ");
				PRSNPREFUpdateQuery.append("  LSTMDFTS = " + CommonUtil.TO_TIMESTAMP_TZ  +" , ");
			}
			
			String prsnprfquery = PRSNPREFUpdateQuery.substring(0, PRSNPREFUpdateQuery.length()-2);

			String PRSNPREFUpdateMainQuery = UPDATEPRSNPREFSQL + prsnprfquery
					+ UPDATECUSFPREDWHERE;
			PreparedStatement ps =null;
			try {
			ps = connection.prepareStatement(PRSNPREFUpdateMainQuery);

			
			if (customer.getCreationTimestamp() != null) {
				CommonUtil.setPsStringParam(ps, 1, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
				CommonUtil.setPsLongParam(ps, 2, PRSNPREFKey);
			}else{
				
				CommonUtil.setPsLongParam(ps, 1, PRSNPREFKey);
			}
			ps.executeUpdate();

	//		super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, null);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	private void updateDissociateCustomer(Connection connection, Patient patient, Customer customer, String assotyp,
			Long cdrptntmpngKey, Long custKey) throws SQLException, InvalidInputException, NamingException, IOException,
			CustomerChannelNotFoundException, PatientNotFoundException, CustomerPatientAssociationExistException,
			CustomerNotFoundException, KeyNotFoundFromTableCacheException, CustomerPatientAssociationNotExistException {
		Long timer = 0L;
		String storeNumber = null;
		String patientId = null;
		Long patientKey = null;

		String userId = null;
		PreparedStatement ps =null;
		ResultSet rs =null;

		if (customer.getUserId() != null) {
			userId = customer.getUserId();
		}
		try {
			patientId = patient.getConsumerId();
			if (patientId == null || patientId.trim().length() == 0) {
				throw new InvalidInputException("Consumer ID is mandatory for Disassociate Customer");
			}

			// Get StoreNumber from Patient
			if (patient.getStore() != null) {
				Store storepat = patient.getStore();
				storeNumber = storepat.getStorenumber() != null && !"".equals(storepat.getStorenumber())
						? CommonUtil.createStoreLeadingZeros(storepat.getStorenumber()) : null;

				if (storeNumber == null || storeNumber.trim().length() == 0) {
					throw new InvalidInputException("Store Number is mandatory for Disassociate Customer");
				}
			}

			/*
			 * PharmacyChannel pharmacyChannel =
			 * patient.getVerificationChannel(); if (pharmacyChannel == null ) {
			 * throw new InvalidInputException(
			 * "Verification Channel is mandatory for Associate Customer"); }
			 * Long channelKey = null; if(pharmacyChannel != null){ String
			 * channelID = pharmacyChannel.getChannelType() != null ?
			 * pharmacyChannel.getChannelType().value() : null; if(channelID !=
			 * null){ try { channelKey = getKeyFromCode(LT_PHARMACYCHNLTYP,
			 * channelID); } catch (KeyNotFoundFromTableCacheException e) {
			 * logger.error(
			 * "The Verification Channel type in request is not valid for " +
			 * channelID); throw new
			 * CustomerChannelNotFoundException(storeNumber ,patientId ,
			 * customer.getCustomerid() ,customer.getUserId()); } } else{
			 * logger.error(
			 * "The Verification Channel type in request is not valid for " +
			 * channelID); throw new
			 * CustomerChannelNotFoundException(storeNumber ,patientId ,
			 * customer.getCustomerid() ,customer.getUserId()); } }
			 */
			try {
			ps = connection.prepareStatement(QUERYPTNTKEYSQL);
			CommonUtil.setPsStringParam(ps, 1, patientId);
			CommonUtil.setPsStringParam(ps, 2, storeNumber);
			rs = ps.executeQuery();
			if (!rs.next()) // No patient record found
			{
				if(logger.isErrorEnabled()) {logger.error("patient not found for store number %s ,  patient id %s" ,storeNumber, patientId);}
				throw new PatientNotFoundException(storeNumber, patientId);
			}

			patientKey = rs.getLong("PTNTKEY");
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		//	super.close();
			try {
			ps = connection.prepareStatement(PTNTEXISTINCDRPTNTSQL);
			CommonUtil.setPsLongParam(ps, 1, patientKey);
			CommonUtil.setPsLongParam(ps, 2, custKey);
			rs = ps.executeQuery();
			if (!rs.next()) // No patient record found
			{
				if(logger.isErrorEnabled()) {logger.error("patient not associated with the customer  %s, patient id %s" ,userId ,patientId);}
				throw new CustomerPatientAssociationNotExistException(userId, patientId);
			}
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
			// Commented as part of OF-158
			/*
			 * String assocFlag = rs.getString("ASSOCFLAG");
			 * 
			 * if(assocFlag != null && "N".equalsIgnoreCase(assocFlag)) {
			 * logger.error("patient not associated with the customer " + userId
			 * + ", patient id " + patientId); throw new
			 * CustomerPatientAssociationNotExistException(userId,patientId); }
			 */

			// super.close();

			if (customer.getUserType().equals(UserTypeEnum.CUSTOMER)) {

				if (customer.getCreationTimestamp() != null) {
					try {
					ps = connection.prepareStatement(UPDPRSNPREFLSTMDFTMS);
					CommonUtil.setPsStringParam(ps, 1, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
					CommonUtil.setPsLongParam(ps, 2, custKey);
					ps.executeUpdate();
				//	super.close();
					}finally {
						CommonUtil.closePreparedStatementResultSet(ps, rs);
					}
				}

				// ps = connection.prepareStatement(DISASSOCIATEPTNTSQL);
				// Commented as part of OF-158
				/*
				 * ps = connection.prepareStatement(DISASSOCIATEHARDPTNTSQL); //
				 * CommonUtil.setPsStringParam(ps, 1,
				 * CommonUtil.getCurrentTimeStamp());
				 * CommonUtil.setPsLongParam(ps, 1, patientKey);
				 * CommonUtil.setPsLongParam(ps,2, custKey);
				 * 
				 * ps.executeUpdate(); super.close();
				 */

			} else if (customer.getUserType().equals(UserTypeEnum.CAREGIVER)) {
				if (customer.getCreationTimestamp() != null) {
					try {
					ps = connection.prepareStatement(UPDCUSTLSTUPDTMS);
					CommonUtil.setPsStringParam(ps, 1, CommonUtil.toTimestampStr(customer.getCreationTimestamp()));
					CommonUtil.setPsLongParam(ps, 2, custKey);
					ps.executeUpdate();
				//	super.close();
					}catch (SQLException e) {
	                    e.printStackTrace();
	                    throw e;
	                  } finally {
						CommonUtil.closePreparedStatementResultSet(ps, rs);
					}
				}

			}
			try {
			ps = connection.prepareStatement(DISASSOCIATEHARDPTNTSQL);
			CommonUtil.setPsLongParam(ps, 1, patientKey);
			CommonUtil.setPsLongParam(ps, 2, custKey);
			ps.executeUpdate();
		//	super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}

			if(logger.isDebugEnabled()) {logger.debug("SQL execute time is : %s ms" , (System.currentTimeMillis() - timer) );}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	private void traversrPersonAddress(ResultSet rs, Address thisRow, Person person, Customer customer)
			throws CDRInternalException, SQLException, IOException, NamingException,
			CodeNotFoundFromTableCacheException {

		if (rs.getString("CM_CNSMRID") != null && rs.getString("CM_CNSMRID").length() > 1) {
			PopulateAddress46(rs, "", thisRow, customer);
			person.getAddress().add(thisRow);

		}

	}
	
	public static void PopulateAddress46(ResultSet rs, String Alias, Address addr,Customer customer) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {  
//		long contactMethodKey = rs.getLong("CM_CNSMRID");
//		if ( contactMethodKey > 0 ) {

		String  keyAlias = "";
		if ( !(Alias == null ||	Alias.trim().length() == 0) ) {
			if (Alias.endsWith("_")) {
				keyAlias = Alias;
			} else {
				keyAlias = Alias + "_";
			}
		}

		
		if (rs.getString(Alias + "CM_CNSMRID") != null && rs.getString(Alias + "CM_CNSMRID").length() > 1) {
			addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
			    if (rs.getBigDecimal(Alias + "ADDR_ADDRKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	if(addr.getAddressLineOne()==null) {
				    addr.setAddressLineOne(ResultSetWrapper.getString(rs, Alias, "ADDR_ADDRLNONE"));
			    	}
			    	if(addr.getAddressLineTwo()==null) {
					addr.setAddressLineTwo(ResultSetWrapper.getString(rs, Alias, "ADDR_ADDRLNTWO"));
			    	}
			    	
			    	if(addr.getCityName() == null ) {
			    		addr.setCityName(ResultSetWrapper.getString(rs, Alias, "ADDR_CITYNM"));
			    	}
					
					
					if(addr.getCountryCode() == null) {
				    addr.setCountryCode(ResultSetWrapper.getString(rs, Alias, "ADDR_CNTRYCD"));
					}
					
					if(addr.getPostalCode() == null) {
				    addr.setPostalCode(ResultSetWrapper.getString(rs,Alias, "ADDR_POSTALCD"));
					}
				    String   value =null;
				   
				    if(addr.getProvinceCode() == null) {
				    String provCode = ResultSetWrapper.getString(rs,Alias, "ADDR_PROVCD");
				    if( provCode != null ) {
					    addr.setProvinceCode(Province.fromValue( provCode) ) ;
				    }
				    }
				 }
			   
					 
				     
			     if (rs.getBigDecimal(keyAlias + "EMAIL_EMAILKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 if(customer.getEmail() == null) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
					 addr.setEmail(ResultSetWrapper.getString(rs, Alias, "EMAIL_EMAILADDR"));
			    	 }
			    	 else {
			    		 addr.setEmail(customer.getEmail());
			    	 }
			     }    
				     
			     if (rs.getBigDecimal(keyAlias + "PHONE_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 if(customer.getPhonenumber() == null) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
				     addr.setPrimaryPhoneNumber(ResultSetWrapper.getString(rs, Alias, "PHONE_TELECOMNUM"));
			    	 }
			    	 else {
			    		 addr.setPrimaryPhoneNumber(customer.getPhonenumber());
			    	 }
			     }
			     
			     if (rs.getBigDecimal(keyAlias + "FAX_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 if(addr.getFaxNumber() == null) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
					 addr.setFaxNumber(ResultSetWrapper.getString(rs, Alias, "FAX_TELECOMNUM"));
			    	 }
			     }
			     
			    
			}
		
		
	}


}