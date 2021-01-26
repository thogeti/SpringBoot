package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.DispenseNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.query.api.CDRMedReviewGet;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ChannelType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.CommunicationModeType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Compound;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Dispense;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Dispenser;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DosageForm;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Drug;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.EngagementType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Initiator;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Interchangeability;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedicalPractitioner;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Note;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Pack;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PharmacyChannel;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PrescriptionResumeReason;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PrescriptionResumeReasonTypeDisplay;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ProfessionalService;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Recorder;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.RouteOfAdmin;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.SubstitutionReason;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Supervisor;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.TransactionSourceChannel;

import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class DispenseMedReviewGet extends CDRMedReviewGet {

	private static Logger logger = LogManager.getLogger(DispenseMedReviewGet.class);
	private final static String QUERYSQL = "SELECT TX.SIGCD TX_SIGCD,"
			                               + " TX.SIGDESCRPTNTLANG TX_SIGDESCRPTNTLANG , "
	                                       + " TX.DAYSSPLY TX_DAYSSPLY,   "
	                                       + "TX.CRTTIMESTAMP TX_CRTTIMESTAMP, "
	                                       + " TX.LSTUPDT TX_LSTUPDT, "
	                                       + "TX.PCKUPTIME TX_PCKUPTIME,"
	                                       + " TX.QTYTXD TX_QTYTXD, "
	                                       + "  TX.TXTIME TX_TXTIME,"
	                                       + " TX.DAYSINTRVL TX_DAYSINTRVL, "
	                                       + " TX.HOMEDELVRYFLAG TX_HOMEDELVRYFLAG, "
	                                       + " TX.SPRVSRKEY TX_SPRVSRKEY, "
                                           + "  TX.TXTYPKEY TX_TXTYPKEY,"
                                           + " TX.RTEOFADMIN_CERXTYPKEY TX_RTEOFADMIN_CERXTYPKEY ,"
	                                       + " TX.PKGFRMTYPKEY TX_PKGFRMTYPKEY,"
	                                       + " TX.QTYRMNG TX_QTYRMNG , "
                                           + "TX.TXNNUM TX_TXNNUM, TX.ENGMNTTYPKEY TX_ENGMNTTYPKEY,"
                                           + " TX.COMMMODETYPKEY TX_COMMMODETYPKEY ,"
                                           + " TX.PHARMACYCHNLTYPKEY TX_PHARMACYCHNLTYPKEY,  "
	                                       +" TX.TXNSRCECHNLTYPKEY TX_TXNSRCECHNLTYPKEY,"
	                                       + "TX.LASTPICKUPTIME TX_LASTPICKUPTIME,"
	                                       + " TX.DSGFRMKEY  TX_DSGFRMKEY," //CR_63 
                                           + "TXSTAT.TXFILLSTATTYPKEY TXSTAT_TXFILLSTATTYPKEY, "
	
                                           + " PRFSNLSVC.CNSMRID PRFSNLSVC_CNSMRID, "
	                                       + "   PRFSNLSVC.PRFSNLSVCKEY PRFSNLSVC_PRFSNLSVCKEY, "
	                                       + "PRFSNLSVC.PRFSNLSVCTYPKEY PRFSNLSVC_PRFSNLSVCTYPKEY  "
	                                       + "FROM TX TX "
	                                       + "LEFT OUTER JOIN TXSTAT TXSTAT ON TX.TXKEY = TXSTAT.TXKEY "
                                       //	+ "LEFT OUTER JOIN PACK PACK ON TX.PACKKEY = PACK.PACKKEY "
                                           + "LEFT OUTER JOIN PRFSNLSVC PRFSNLSVC ON TX.TXKEY = PRFSNLSVC.TXKEY ";
	
	private final static String QUERYBYTXNUMSQL = QUERYSQL
	+ "WHERE TX.STORENUM = ? AND TX.TXNNUM = ? "
	+ "ORDER BY TX.TXNNUM DESC";
 
	

	/*private final static String QUERYBYRXKEYSQL = QUERYSQL
	+ "WHERE TX.RXKEY = ? " 
	+ "ORDER BY TX.TXNNUM DESC";*/

	private final static String QUERYBYRXKEYSQL = QUERYSQL
			+ "WHERE TX.TXKEY in (select tmp2.txkey\r\n" + 
			"  from (select tmp1.txkey,\r\n" + 
			"               tmp1.txNum,\r\n" + 
			"               tmp1.txStatus,\r\n" + 
			"               DENSE_RANK() OVER (PARTITION BY tmp1.txStatus ORDER BY tmp1.txNum DESC) AS statusRank\r\n" + 
			"          from (select t.txkey,\r\n" + 
			"                       t.txnnum    txNum,\r\n" + 
			"                       f.TXFILLSTATTYPKEY,\r\n" + 
			"                       decode(f.cddescr,\r\n" + 
			"                              'Complete',  f.cddescr,\r\n" + 
			"                              'Cancelled', f.cddescr,\r\n" + 
			"                              'Other') txStatus\r\n" + 
			"                  from tx     t,\r\n" + 
			"                       txstat s,\r\n" + 
			"                       TXFILLSTATTYP f\r\n" + 
			"                 where t.rxkey = ? \r\n" + 
			"                   and t.txkey = s.txkey \r\n" + 
			"                   and s.TXFILLSTATTYPKEY = f.TXFILLSTATTYPKEY) tmp1\r\n" + 
			"                 order by tmp1.txNum desc) tmp2\r\n" + 
			"  where tmp2.statusRank = 1) " 
			+ "ORDER BY TX.TXNNUM DESC";

		
	public List<Dispense> fetchList(Connection connection, Long prescriptionKey) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException
	{
		Long querytimer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("Start populate dispense list : prescription number " + prescriptionKey);}
		List<Dispense> dispenses = new ArrayList<Dispense>();
		preparedStatement = connection.prepareStatement(QUERYBYRXKEYSQL);
		preparedStatement.setLong(1, prescriptionKey);
	
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DispenseMedReviewGet.queryByRxKeySQL. prescriptionKey : " + prescriptionKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DispenseMedReviewGet.queryByRxKeySQL. prescriptionKey : " + prescriptionKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			Dispense dispense = scanBase();
			scanChildren(connection, dispense);
			if(dispense!=null)
				dispenses.add(dispense);
			
		}
		super.close();// VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return dispenses;
	};
	
	
		
	
	public Dispense fetch(Connection connection, String storeNum, String dispenseNum) throws SQLException, NamingException, IOException, ParseException,
			DatatypeConfigurationException, CDRInternalException {
		try {
			return populate(connection, storeNum, dispenseNum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (CodeNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
		}
	}

	private Dispense populate(Connection connection, String storeNumber, String dispenseNumber) throws SQLException, NamingException, IOException,
			ParseException, DatatypeConfigurationException, CDRInternalException {

		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseMedReviewGet.populate. DispenseNumber : " + dispenseNumber + ", StoreNumber : "
				+ storeNumber);}

		Dispense dispense = null;
		preparedStatement = connection.prepareStatement(QUERYBYTXNUMSQL);
		preparedStatement.setString(1, storeNumber);
		preparedStatement.setString(2, dispenseNumber);

		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DispenseMedReviewGet.queryByTxNumSQL. DispenseNumber : " + dispenseNumber
				+ ", StoreNumber : " + storeNumber);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug(
				"EndExecuteQuery: DispenseMedReviewGet.queryByTxNumSQL. DispenseNumber : " + dispenseNumber + ", StoreNumber : "
						+ storeNumber + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms");}

		// String packageForm = populatePackageForm(rs, alias);
		// dispense.setPackageForm(packageForm);

		if (resultSet.next()) {
			dispense = scanBase();
			scanChildren(connection, dispense);
		}
		;

		if(logger.isInfoEnabled()) {logger.info("EndApiCall: DispenseMedReviewGet.populate. DispenseNumber : " + dispenseNumber + ", StoreNumber : "
				+ storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return dispense;
	}
	
	
	private Dispense scanBase() throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseMedReviewGet.scanBase.");}

		Dispense dispense = new Dispense();
		dispense.setTransactionNumber(getInt("TX_TXNNUM"));// A

		dispense.setDaysSupply(getInt("TX_DAYSSPLY"));// A

		dispense.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("TX_CRTTIMESTAMP"), true));// A

		dispense.setLastUpdate(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("TX_LSTUPDT"), true)); // A

		dispense.setPickupTime(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("TX_PCKUPTIME"), true)); // A
		// dispense.setPickupTime2(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("TX_LASTPICKUPTIME"),
		// true));
		dispense.setQuantityDispensed(resultSet.getBigDecimal("TX_QTYTXD")); // A

		dispense.setDispenseTime(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("TX_TXTIME"), true)); // A
		dispense.setDaysInterval(getInt("TX_DAYSINTRVL"));// A

		dispense.setIsHomeDeliveryFlag(CommonUtil.convertYesNoFlagToBoolean(getString("TX_HOMEDELVRYFLAG"))); // A
		dispense.setQuantityRemaining(getInt("TX_QTYRMNG"));
		Long routeOfAdminKey = getLong("TX_RTEOFADMIN_CERXTYPKEY"); // A
		if (routeOfAdminKey != null) {
			String routeOfAdminCode = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);

			dispense.setAdministrationRouteCode(routeOfAdminCode);
		}

		Long txTypeKey = getLong("TX_TXTYPKEY"); // A
													
		if (txTypeKey != null) {
			String dispenseCode = getCodeFromKey(LT_TXTYP, txTypeKey);
			dispense.setDispenseType(dispenseCode);
		}

		Long packageFormTypeKey = getLong("TX_PKGFRMTYPKEY"); // A
		if (packageFormTypeKey != null) {
			String packageFormCode = getCodeFromKey(LT_PKGFRMTYP, packageFormTypeKey);
			dispense.setPackageForm(packageFormCode);
		}
		
		Long pharmacyChannelTypeKey = getLong("TX_PHARMACYCHNLTYPKEY");
		Long engagementTypeKey = getLong("TX_ENGMNTTYPKEY");
		Long communicationModeKey = getLong("TX_COMMMODETYPKEY");
		
		if(pharmacyChannelTypeKey != null && engagementTypeKey != null && communicationModeKey !=null)
		{
			PharmacyChannel pharmacyChannel = new PharmacyChannel();
			String pharmacyChannelTypeCode = getCodeFromKey(LT_PHARMACYCHNLTYP, pharmacyChannelTypeKey);
			pharmacyChannel.setChannelType(pharmacyChannelTypeCode);//A

			String engagementTypeCode = getCodeFromKey(LT_ENGMNTTYP, engagementTypeKey);
			pharmacyChannel.setEngagment(engagementTypeCode);//A
			
			String communicationModeCode = getCodeFromKey(LT_COMMMODETYP, communicationModeKey);
			pharmacyChannel.setCommunicationMode(communicationModeCode); //A
			
			dispense.setPharmacyChannel(pharmacyChannel);
		}
		
		dispense.setSigCode(getString("TX_SIGCD"));		//A
		dispense.setSigDescriptionPatientLanguage(getString("TX_SIGDESCRPTNTLANG"));//A
		
		Long transactionSourceChannelKey = getLong("TX_TXNSRCECHNLTYPKEY");
		if(transactionSourceChannelKey != null)
		{
			String transactionSourceChannelCode = null;
			transactionSourceChannelCode =	 getCodeFromKey(LT_TXNSRCECHNLTYP, transactionSourceChannelKey);
		//	TransactionSourceChannel transactionSourceChannel = (!CommonUtil.isEmpty(transactionSourceChannelCode)) ? TransactionSourceChannel.fromValue(transactionSourceChannelCode)	 : null;
			dispense.setTransactionSourceCode(transactionSourceChannelCode);
		};
       
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: DispenseMedReviewGet.scanBase. Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		
		return dispense;
	 } 
	
	
	private void scanChildren(Connection connection, Dispense dispense) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseMedReviewGet.scanChildren");}
		
		Long dosageFormKey = getLong("TX_DSGFRMKEY");
		if(dosageFormKey != null) {
			ca.shoppersdrugmart.rxhb.ehealth.DosageForm dosageFormEhealth = (ca.shoppersdrugmart.rxhb.ehealth.DosageForm) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,dosageFormKey, "DSGFRM");
			DosageForm dosageForm = new DosageForm();
			dosageForm.setFullName(dosageFormEhealth.getFullName());
			dosageForm.setFullNameFrench(dosageFormEhealth.getFullNameFrench());
		    dispense.setDosageForm(dosageForm);
		}

		
		Long professionalServiceKey = getLong("PRFSNLSVC_PRFSNLSVCKEY");
		if (professionalServiceKey != null) {
			ProfessionalService professionalService = new ProfessionalService();
			/*professionalService.setConsultationTimestamp(CommonUtil.getXMLGregorianCalendar(getDate("PRFSNLSVC_CNSLTTNTIMESTAMP"), true));
			BigDecimal tmpBigDecimal = getBigDecimal("PRFSNLSVC_CNSLTTNLENGTH");
			if (tmpBigDecimal != null) {
				BigInteger tmpBigInteger = tmpBigDecimal.toBigInteger();
				professionalService.setConsultationLength(tmpBigInteger);
			}
			;*/
			professionalService.setConsumerId(getString("PRFSNLSVC_CNSMRID")); //A
		//	professionalService.setProducerId(getString("PRFSNLSVC_PRDCRID"));
		
			Long professionalServiceTypeKey = getLong("PRFSNLSVC_PRFSNLSVCTYPKEY"); //A
			if(professionalServiceKey!=null)
			{	
			String professionalServiceTypeCode = getCodeFromKey(LT_PRFSNLSVCTYP, professionalServiceTypeKey);
			professionalService.setObservationTypeCode(professionalServiceTypeCode);
			};
			
	
			
			dispense.setProfessionalService(professionalService);
		}
		
		Long dispenseFillStatusKey = getLong("TXSTAT_TXFILLSTATTYPKEY"); //A
		if(dispenseFillStatusKey != null)
		{
	        String fillStatusCode = getCodeFromKey(LT_TXFILLSTATTYP, dispenseFillStatusKey);
	        if (fillStatusCode != null)
	        {
	        	dispense.setFillStatus(fillStatusCode);
	        }
		}
		
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: DispenseMedReviewGet.scanChildren. Total time is : " + (System.currentTimeMillis() - timer) + " ms");}		
	}
	
	
	
	}
