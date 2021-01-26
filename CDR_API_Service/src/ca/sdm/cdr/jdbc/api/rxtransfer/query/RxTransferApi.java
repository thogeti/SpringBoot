package ca.sdm.cdr.jdbc.api.rxtransfer.query;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.AssociateCustomerException;
import com.sdm.cdr.exception.api.CustomerAlreadyExistsException;
import com.sdm.cdr.exception.api.CustomerChannelNotFoundException;
import com.sdm.cdr.exception.api.CustomerNotFoundException;
import com.sdm.cdr.exception.api.CustomerTypeNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.query.api.CDRGet;
import ca.sdm.cdr.jdbc.query.api.PatientViewGet;
import ca.sdm.cdr.jdbc.upsert.api.CDRUpsert;
import ca.shoppersdrugmart.rxhb.drx.cse.Customer;
import ca.shoppersdrugmart.rxhb.drx.cse.UserTypeEnum;
import ca.shoppersdrugmart.rxhb.drx.rxtransfer.RxTransfer;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;

public class RxTransferApi extends CDRUpsert {
	private static final Logger logger = LogManager.getLogger("RxTransferApi");

	private final static String GETRXKEY = "select rxkey from rx where rxnum = ? and storenum = ? ";
			
	private final static String RXTRANSFERCLOB_INSERT_QUERY = "MERGE INTO RXTRF_TEMP a   "
			+ "USING (SELECT  ? RXTRFID,? USERID,? FRMSTORETYP,? FRMSTORENAME,? FRMSTOREPHONE,? TRFALLRXFLAG,? TRFTOSTORENUM, "
			+ "	? PHARMACYCHNLTYPKEY,? COMMMODETYPKEY,? ENGAMNTTYPKEY,"+CommonUtil.getPsToDateFunctionStr()+" INITIATIONTS,XMLType(?) PATIENT_XML FROM dual) b "
			+ "	on(a.RXTRFID = b.RXTRFID and a.TRFTOSTORENUM = b.TRFTOSTORENUM) "
			+ "	WHEN MATCHED THEN " + "   UPDATE SET USERID  = b.USERID ,"
			+ "	FRMSTORETYP = b.FRMSTORETYP,FRMSTORENAME = b.FRMSTORENAME,FRMSTOREPHONE=b.FRMSTOREPHONE,"
			+ "	TRFALLRXFLAG =b.TRFALLRXFLAG,PHARMACYCHNLTYPKEY = b.PHARMACYCHNLTYPKEY,"
			+ "	COMMMODETYPKEY= b.COMMMODETYPKEY,INITIATIONTS = b. INITIATIONTS " + "	WHEN NOT MATCHED THEN "
			+ "	insert (RXTRFKEY,RXTRFID,USERID,FRMSTORETYP,FRMSTORENAME,FRMSTOREPHONE,TRFALLRXFLAG,TRFTOSTORENUM,"
			+ "	PHARMACYCHNLTYPKEY,COMMMODETYPKEY,ENGAMNTTYPKEY,INITIATIONTS,PATIENT_XML) "
			+ "	values(RXTRF_SEQ.nextval,b.RXTRFID, b.USERID,b.FRMSTORETYP,b.FRMSTORENAME,b.FRMSTOREPHONE,"
			+ "	b.TRFALLRXFLAG,b.TRFTOSTORENUM,b.PHARMACYCHNLTYPKEY,b.COMMMODETYPKEY,b.ENGAMNTTYPKEY,b.INITIATIONTS,b.PATIENT_XML)";
	/*private final static String RXTORXTRF_INSERT_QUERY1 = " insert into RXTORXTRF (  RXTORXTRF, RXTRFKEY, RXKEY, RXNUM )   "
			+ " with   alreadyInserted as (select distinct r.rxkey from rx    r, RXTORXTRF t     "
			+ " where r.storenum = ?  and r.rxkey = t.rxkey  and ";

	private final static String RXTORXTRF_INSERT_QUERY2 = " select RXTORXTRF_SEQ.nextval, ? , r.rxkey, r.rxnum from rx    r, ptnt  p "
			+ "	   where r.storenum = ? " + " and r.rxkey not in (select r.rxkey from alreadyInserted) "
			+ " and r.ptntkey  = p.ptntkey " + " and p.CNSMRID  = ? " + " and p.storenum = ? and ";*/
	
	//private final static String RXTORXTRF_INSERT_QUERY = " insert into RXTORXTRF (  RXTORXTRF, RXTRFKEY, RXKEY, RXNUM ) VALUES(RXTORXTRF_SEQ.nextval, ? , ?, ?)   ";
			
	private final static String RXTORXTRF_INSERT_QUERY = "MERGE INTO RXTORXTRF a   "
			+ "USING (SELECT  ? RXTRFKEY,? RXKEY,? RXNUM FROM dual) b "
			+ "	on(a.RXTRFKEY = b.RXTRFKEY and a.RXNUM = b.RXNUM) "
			+ "	WHEN MATCHED THEN " + "   UPDATE SET RXKEY  = b.RXKEY 	WHEN NOT MATCHED THEN "
			+ "	insert (RXTORXTRF, RXTRFKEY, RXKEY, RXNUM) "
			+ "	values(RXTORXTRF_SEQ.nextval,b.RXTRFKEY, b.RXKEY,b.RXNUM)";
	
	private final static String QUERYRXTRFIDSQL = "SELECT RXTRFKEY FROM RXTRF WHERE RXTRFID = ? ";
	
	private final static String RXTRANSFER_INSERT_QUERY = "MERGE INTO RXTRF a   "
			+ "USING (SELECT  ? RXTRFID,? PTNTTXFRKEY,? FRMSTORETYP,? FRMSTORENAME,? FRMSTOREPHONE,? TRFALLRXFLAG,? TRFTOSTORENUM, "
			+ "	? PHARMACYCHNLTYPKEY,? COMMMODETYPKEY,? ENGAMNTTYPKEY,"+CommonUtil.getPsToDateFunctionStr()+" INITIATIONTS FROM dual) b "
			+ "	on(a.RXTRFID = b.RXTRFID and a.TRFTOSTORENUM = b.TRFTOSTORENUM) "
			+ "	WHEN MATCHED THEN " + "   UPDATE SET PTNTTXFRKEY  = b.PTNTTXFRKEY ,"
			+ "	FRMSTORETYP = b.FRMSTORETYP,FRMSTORENAME = b.FRMSTORENAME,FRMSTOREPHONE=b.FRMSTOREPHONE,"
			+ "	TRFALLRXFLAG =b.TRFALLRXFLAG,PHARMACYCHNLTYPKEY = b.PHARMACYCHNLTYPKEY,"
			+ "	COMMMODETYPKEY= b.COMMMODETYPKEY,INITIATIONTS = b. INITIATIONTS " + "	WHEN NOT MATCHED THEN "
			+ "	insert (RXTRFKEY,RXTRFID,PTNTTXFRKEY,FRMSTORETYP,FRMSTORENAME,FRMSTOREPHONE,TRFALLRXFLAG,TRFTOSTORENUM,"
			+ "	PHARMACYCHNLTYPKEY,COMMMODETYPKEY,ENGAMNTTYPKEY,INITIATIONTS) "
			+ "	values(RXTRF_SEQ.nextval,b.RXTRFID, b.PTNTTXFRKEY,b.FRMSTORETYP,b.FRMSTORENAME,b.FRMSTOREPHONE,"
			+ "	b.TRFALLRXFLAG,b.TRFTOSTORENUM,b.PHARMACYCHNLTYPKEY,b.COMMMODETYPKEY,b.ENGAMNTTYPKEY,b.INITIATIONTS)";
	
	//+ "	on(a.PTNTKEY = b.PTNTKEY  and	a.CUSTKEY= b.CUSTKEY and  a.TRFTOSTORENUM = b.TRFTOSTORENUM) "
	
	/*private final static String PTNTTXFR_INSERT_QUERY = "MERGE INTO PTNTTXFR a   "
			+ "USING (SELECT  ? USERID,? STORENUM,? FRSTNM,? LSTNM,? ADDRLNONE,? CITYNM,? PROVCD, "
			+ "	? POSTALCD,? PHONENUM,"+CommonUtil.getPsToDateFunctionStr()+" DTOFBIRTH FROM dual) b "
			+ "	on(a.USERID = b.USERID and a.STORENUM = b.STORENUM and a.FRSTNM = b.FRSTNM and a.LSTNM = b.LSTNM) "
			+ "	WHEN MATCHED THEN " + "   UPDATE SET DTOFBIRTH  = b.DTOFBIRTH ,"
			+ "	ADDRLNONE = b.ADDRLNONE,CITYNM = b.CITYNM,PROVCD=b.PROVCD,"
			+ "	POSTALCD =b.POSTALCD,PHONENUM = b.PHONENUM"
			+ "		WHEN NOT MATCHED THEN "
			+ "	insert (PTNTTXFRKEY,USERID,STORENUM,FRSTNM,LSTNM,DTOFBIRTH,ADDRLNONE,CITYNM,PROVCD,POSTALCD,PHONENUM)"
			+ "	values(PTNTTXFR_SEQ.nextval,b.USERID, b.STORENUM,b.FRSTNM,b.LSTNM,b.DTOFBIRTH,"
			+ "	b.ADDRLNONE,b.CITYNM,b.PROVCD,b.POSTALCD,b.PHONENUM)";*/
	
	private final static String PTNTTXFR_INSERT_QUERY = "INSERT INTO PTNTTXFR (PTNTTXFRKEY,USERID,STORENUM,FRSTNM,LSTNM,DTOFBIRTH,ADDRLNONE,CITYNM,PROVCD,POSTALCD,PHONENUM) VALUES(?,?,?,?,?,"+CommonUtil.getPsToDateFunctionStr()+",?,?,?,?,?)";

	public int rxTransfer(Connection connection, RxTransfer rxTransferRequest) throws 
			SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException,
			CustomerNotFoundException, InvalidInputException, RxNotFoundException, CustomerTypeNotFoundException, JAXBException {
		int result = 0;
		try {
			String fromStoreType = rxTransferRequest.getFromStoreType();
			String fromStoreName = rxTransferRequest.getFromStoreName();
			String fromStorePhone = rxTransferRequest.getFromStorePhoneNumber();
			String transferAllFlag = rxTransferRequest.getTransferAllPrescriptions();
			Customer customer = rxTransferRequest.getCustomer();
			Patient patient = rxTransferRequest.getPatient();
			/*JAXBContext jaxbContext = JAXBContext.newInstance(RxTransfer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			QName qName = new QName("ca.shoppersdrugmart.rxhb.drx.rxtransfer", "RxTransfer");
		    JAXBElement<RxTransfer> root = new JAXBElement<>(qName, RxTransfer.class, rxTransferRequest);

			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(root, sw);
			String patientxml = sw.toString();
	        System.err.println(sw.toString());*/
			
			
			String userId = customer.getUserId();
			String custId = customer.getCustomerid();
			String trfToStoreNum = rxTransferRequest.getTransferToStoreId();
			List<Prescription> prescrnList = new ArrayList<Prescription>();
			XMLGregorianCalendar initiationTime = rxTransferRequest.getInitiationTime();
			PharmacyChannel pharmacyChannel = rxTransferRequest.getSourceChannel();
			String storeNum = null;
			if(patient.getStore()!=null) {
				storeNum = patient.getStore().getStorenumber();
			}		
			
			
			if(transferAllFlag==null) {
				throw new InvalidInputException(" TransferAllFlag is mandatory for RxTransfer ");	
			}
			else {
				if(!(transferAllFlag.equalsIgnoreCase("1") || transferAllFlag.equalsIgnoreCase("2")) ) {
					throw new InvalidInputException(" Transfer all prescriptions flag should be 1 or 2 for RxTransfer ");	
				}
			}
			
			if(trfToStoreNum==null && "".equalsIgnoreCase(trfToStoreNum)) {
				throw new InvalidInputException(" Transfer to Store ID is mandatory for RxTransfer ");
			}
			
			if(fromStoreType==null && "".equalsIgnoreCase(fromStoreType)) {
				throw new InvalidInputException(" From Store Type is mandatory for RxTransfer ");
			}
			
			if(fromStoreName==null && "".equalsIgnoreCase(fromStoreName)) {
				throw new InvalidInputException(" From Store Name is mandatory for RxTransfer ");
			}
			
			if(fromStorePhone==null && "".equalsIgnoreCase(fromStorePhone)) {
				throw new InvalidInputException(" From Store Phone is mandatory for RxTransfer ");
			}
			
			Long channelKey = null;
			if(pharmacyChannel != null){
				String channelID = pharmacyChannel.getChannelType() != null ? pharmacyChannel.getChannelType().value() : null;
				if(channelID != null){
			try {
				channelKey = getKeyFromCode(LT_PHARMACYCHNLTYP, channelID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("Channel type in request is not valid for " + channelID);}
				throw new InvalidInputException("Channel Type is not valid for Rx Transfer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("Channel type in request is not valid for " + channelID);}
					throw new InvalidInputException("Channel Type is not valid for Rx Transfer");
				}
			}
			
			Long communicationKey = null;
			if(pharmacyChannel != null){
				String communicationID = pharmacyChannel.getCommunicationMode() != null ? pharmacyChannel.getCommunicationMode().value() : null;
				if(communicationID != null){
			try {
				communicationKey = getKeyFromCode(LT_COMMMODETYP, communicationID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("Communication type in request is not valid for " + communicationID);}
				throw new InvalidInputException("Communication Mode is not valid for Rx Transfer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("Communication type in request is not valid for " + communicationID);}
					throw new InvalidInputException("Communication Mode is not valid for Rx Transfer");
				}
			}
			
			Long engagementKey = null;
			if(pharmacyChannel != null){
				String engID = pharmacyChannel.getEngagment() != null ? pharmacyChannel.getEngagment().value() : null;
				if(engID != null){
			try {
				engagementKey = getKeyFromCode(LT_ENGMNTTYP, engID);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("Engagement type in request is not valid for " + engID);}
				throw new InvalidInputException("Engagement type is not valid for Rx Transfer");
			}
				}
				else{
					if(logger.isErrorEnabled()) {logger.error("Engagement type in request is not valid for " + engID);}
					throw new InvalidInputException("Engagement type is not valid for Rx Transfer");
				}
			}
			String rxTransferID = null;
			if(rxTransferRequest.getPrescriptionTransferId()==null) {
				if(logger.isErrorEnabled()) {logger.error("Prescription Transfer ID is mandatory for Rx transfer");}
				throw new InvalidInputException("Prescription Transfer ID is mandatory for Rx transfer");
			}
			else {
				rxTransferID = rxTransferRequest.getPrescriptionTransferId();
			}
			
			
			ps = connection.prepareStatement(QUERYRXTRFIDSQL);
			CommonUtil.setPsStringParam(ps, 1, rxTransferID);
			
			rs = ps.executeQuery();

			if (rs.next()) // No patient record found
			{
				if(logger.isErrorEnabled()) {logger.error("Prescription Transfer ID already Exists");}
				throw new InvalidInputException("Prescription Transfer ID already exists for Rx transfer.");
			}
			prescrnList = rxTransferRequest.getPrescription();

			// If transferAllFlag is 'N' and prescrnList is empty ,throw
			// exception
			/*if (transferAllFlag.equalsIgnoreCase("N") && prescrnList.isEmpty()) {
				throw new InvalidInputException(" Preacriptions required when Transfer all prescriptions flag is N for RxTransfer ");
			}*/
			
			/*result = insertIntoRxTRFCLOBTable(connection, rxTransferRequest,patientxml,channelKey, communicationKey,
					engagementKey);*/
			
			
			Long ptntTxFrKey = null;
		if(patient != null) {
			
			ptntTxFrKey = insertIntoPTNTTXFRTable(connection, patient, userId);
		}
			result = insertIntoRxTRFTable(connection, rxTransferID, ptntTxFrKey, fromStoreType, fromStoreName, trfToStoreNum,
					fromStorePhone, transferAllFlag, initiationTime,channelKey, communicationKey,
					engagementKey,  prescrnList);
			//Long rxTRFKEY = FindUtil.findRXTRFKEY(connection, rxTransferID);
			
			
			
			/*if(transferAllFlag.equalsIgnoreCase("N") && !prescrnList.isEmpty()) {
		 insertIntoRxToRxTransferTable(connection, rxTRFKEY,storeNum,  transferAllFlag,prescrnList);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		
		}  catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndsApiCall : associateCustomer");}
		}

		return result;

	}

	/**
	 * This method is to insert the data into RXTRF. It will insert only 1 row.
	 * 
	 * @param connection
	 * @param rxTRFKEY
	 * @param patientKey
	 * @param custkey
	 * @param fromStoreType
	 * @param fromStoreName
	 * @param trfToStoreNum
	 * @param fromStorePhone
	 * @param transferAllFlag
	 * @param initiationTime
	 * @param pharmacyChnlType
	 * @param commodeType
	 * @param engagementType
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private int insertIntoRxTRFTable(Connection connection, String RXTRFID,Long PTNTTXFRKEY, String fromStoreType,
			String fromStoreName, String trfToStoreNum, String fromStorePhone, String transferAllFlag,
			XMLGregorianCalendar initiationTime, Long pharmacyChnlType, Long commodeType, Long engagementType,
			 List<Prescription> prescrnList) throws IOException, SQLException {

		int res = 0;
		try {

			ps = connection.prepareStatement(RXTRANSFER_INSERT_QUERY);
			CommonUtil.setPsStringParam(ps, 1, RXTRFID);
			CommonUtil.setPsLongParam(ps, 2, PTNTTXFRKEY);
			CommonUtil.setPsStringParam(ps, 3, fromStoreType);
			CommonUtil.setPsStringParam(ps, 4, fromStoreName);
			CommonUtil.setPsStringParam(ps, 5, fromStorePhone);
			CommonUtil.setPsStringParam(ps, 6, transferAllFlag);
			CommonUtil.setPsStringParam(ps, 7, trfToStoreNum);
			CommonUtil.setPsLongParam(ps, 8, pharmacyChnlType);
			CommonUtil.setPsLongParam(ps, 9, commodeType);
			CommonUtil.setPsLongParam(ps, 10, engagementType);
			CommonUtil.setPsStringParam(ps, 11, CommonUtil.toTimestampStr(initiationTime));

			res = ps.executeUpdate();
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}

		return res;
	}
	
	private Long insertIntoPTNTTXFRTable(Connection connection, Patient patient,String userId) throws IOException, SQLException {

		int res = 0;
		Long ptnttxfrkey = null;
		String storenum = null;
		String firstName = null;
		String lastName  = null;
		String addressLine = null;
		String cityName = null;
		String provinceCode = null;
		String phoneNum = null;
		String postalCode = null;
		try {
			
			if(patient.getStore()!=null) {
				 storenum = patient.getStore().getStorenumber();
			}
			if(patient.getPerson()!=null) {
				Person person = patient.getPerson();
				firstName  = person.getFirstName();
				lastName = person.getLastName();
				if(person.getAddress()!=null && person.getAddress().size()>0) {
			    Address address = person.getAddress().get(0);
				addressLine = (address.getAddressLineOne()!=null?address.getAddressLineOne():"")+" "+(address.getAddressLineTwo()!=null?address.getAddressLineTwo():"");
				cityName = address.getCityName();
				provinceCode = address.getProvinceCode()!=null?address.getProvinceCode().value():null;
				phoneNum  = address.getPrimaryPhoneNumber();
				postalCode = address.getPostalCode();
				
				}
				
			}
			 ptnttxfrkey = IdGenerator.generate(connection, "PTNTTXFR");
			//USERID,STORENUM,FRSTNM,LSTNM,DTOFBIRTH,ADDRLNONE,CITYNM,PROVCD,POSTALCD,PHONENUM
			ps = connection.prepareStatement(PTNTTXFR_INSERT_QUERY);
			CommonUtil.setPsLongParam(ps, 1, ptnttxfrkey);
			CommonUtil.setPsStringParam(ps, 2, userId);
			CommonUtil.setPsStringParam(ps, 3, storenum);
			CommonUtil.setPsStringParam(ps, 4, firstName);
			CommonUtil.setPsStringParam(ps, 5, lastName);
			CommonUtil.setPsStringParam(ps, 6, CommonUtil.toTimestampStr(patient.getDateOfBirth()));
			CommonUtil.setPsStringParam(ps, 7, addressLine);
			CommonUtil.setPsStringParam(ps, 8, cityName);
			CommonUtil.setPsStringParam(ps, 9, provinceCode);
			CommonUtil.setPsStringParam(ps, 10, postalCode);
			CommonUtil.setPsStringParam(ps, 11, phoneNum);
			
			res = ps.executeUpdate();
			
			//ptnttxfrkey = FindUtil.findPTNTTXFRKEY(connection,userId,storenum,firstName,lastName);
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}

		return ptnttxfrkey;
	}

	/**
	 * This method is to insert multiple records in to the RXTORXTRF table.
	 * 
	 * @param rxTRFKEY
	 * @param patientId
	 * @param storeNumber
	 * @return
	 * @throws SQLException
	 */
	private void insertIntoRxToRxTransferTable(Connection connection, long rxTRFKEY,String storeNum, String transferAllFlag, List<Prescription> prescrnList)
			throws SQLException {
		String TRFALLFLAGY = " r.rxnum between '00' and '9999999999999999999'";
		String TRFALLFLAG_N = " r.rxnum in ( ";// ?, ?, ?, ?,)";
		String ALLRXNUM_LIST = " r.rxnum in (";
		int count = 0;
		int res = 0;
		int index = 0;
		List<String> allRxNumList = new ArrayList<String>();
		
			/*if (transferAllFlag.equalsIgnoreCase("Y")) {
				allRxNumList = FindUtil.findRXNumbersbyPTNTKEY(connection, ptntKey);

				if (!allRxNumList.isEmpty()) {
					count = allRxNumList.size();
					for (int i = 1; i < count; i++) {
						ALLRXNUM_LIST += "?,";
					}
					ALLRXNUM_LIST += " ?))";
				}
				ps = connection.prepareStatement(
						RXTORXTRF_INSERT_QUERY1 + ALLRXNUM_LIST + RXTORXTRF_INSERT_QUERY2 + TRFALLFLAGY);
				// CommonUtil.setPsLongParam(ps, 1, rxTRFKEY);
				CommonUtil.setPsStringParam(ps, 1, storeNumber);
				index = 2;
				for (String rxNum : allRxNumList) {
					CommonUtil.setPsStringParam(ps, index++, rxNum);

				}
				CommonUtil.setPsLongParam(ps, index++, rxTRFKEY);
				CommonUtil.setPsStringParam(ps, index++, storeNumber);
				CommonUtil.setPsStringParam(ps, index++, patientId);
				CommonUtil.setPsStringParam(ps, index++, storeNumber);
			} else*/ 
			if (transferAllFlag.equalsIgnoreCase("N")) {
				try {
				
				for (Prescription prescription : prescrnList) {
					long rxNum = prescription.getPrescriptionNumber();
					Long rxKey = null;
					if(storeNum != null) {
						ps = connection.prepareStatement(GETRXKEY);
						CommonUtil.setPsLongParam(ps, 1, rxNum);
						CommonUtil.setPsStringParam(ps, 2, storeNum);
						rs = ps.executeQuery();

						if (rs.next()) // No patient record found
						{
							rxKey  = rs.getLong("rxkey");
						}
					}
				
				super.close();
				ps = connection.prepareStatement(RXTORXTRF_INSERT_QUERY);
				CommonUtil.setPsLongParam(ps, 1,rxTRFKEY);
				CommonUtil.setPsLongParam(ps, 2, rxKey);
				CommonUtil.setPsLongParam(ps, 3, rxNum);
				res = ps.executeUpdate();
				}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}
			}	
		
	}
	
	private int insertIntoRxTRFCLOBTable(Connection connection, RxTransfer rxTransferRequest,String sw,Long channelKey, Long communicationKey,
			Long engagementKey) throws IOException, SQLException {

		int res = 0;
		try {

			ps = connection.prepareStatement(RXTRANSFERCLOB_INSERT_QUERY);
			CommonUtil.setPsStringParam(ps, 1, rxTransferRequest.getPrescriptionTransferId());
			String userId = null;
			if(rxTransferRequest.getCustomer() != null) {
				userId = rxTransferRequest.getCustomer().getUserId();
			}
			CommonUtil.setPsStringParam(ps, 2, userId);
			CommonUtil.setPsStringParam(ps, 3, rxTransferRequest.getFromStoreType());
			CommonUtil.setPsStringParam(ps, 4, rxTransferRequest.getFromStoreName());
			CommonUtil.setPsStringParam(ps, 5, rxTransferRequest.getFromStorePhoneNumber());
			CommonUtil.setPsStringParam(ps, 6, rxTransferRequest.getTransferAllPrescriptions());
			CommonUtil.setPsStringParam(ps, 7, rxTransferRequest.getTransferToStoreId());
			CommonUtil.setPsLongParam(ps, 8, channelKey);
			CommonUtil.setPsLongParam(ps, 9, communicationKey);
			CommonUtil.setPsLongParam(ps, 10, engagementKey);
			CommonUtil.setPsStringParam(ps, 11, CommonUtil.toTimestampStr(rxTransferRequest.getInitiationTime()));
			CommonUtil.setPsStringParam(ps, 12, sw.toString());
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}

		return res;
	}
}
