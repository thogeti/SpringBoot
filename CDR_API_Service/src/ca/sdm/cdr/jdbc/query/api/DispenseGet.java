package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.DispenseNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.ChannelType;
import ca.shoppersdrugmart.rxhb.ehealth.CommunicationModeType;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
//import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.EngagementType;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.Interchangeability;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionResumeReason;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionResumeReasonTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalService;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.RouteOfAdmin;
import ca.shoppersdrugmart.rxhb.ehealth.SubstitutionReason;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.TransactionSourceChannel;
import ca.shoppersdrugmart.rxhb.ehealth.TxFillStatus;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class DispenseGet extends CDRGet {

	private static Logger logger = LogManager.getLogger(DispenseGet.class);
	
	/*  LTPHCP319_B Deprecation Started
	private final static String QUERYSQL = "SELECT "
	+ "TX.SIGCD TX_SIGCD,TX.ADDTNLDSGINSTRCTNTXT TX_ADDTNLDSGINSTRCTNTXT, TX.ADMINPRDENDDT TX_ADMINPRDENDDT, TX.DAYSSPLY TX_DAYSSPLY, TX.ADMINPRDSTRTDT TX_ADMINPRDSTRTDT, "
	+ "TX.CRTTIMESTAMP TX_CRTTIMESTAMP, TX.PRDCRID TX_PRDCRID, TX.CNSMRID TX_CNSMRID, TX.TXNCANCLRSNTXT TX_TXNCANCLRSNTXT, TX.LSTUPDT TX_LSTUPDT, "
	+ "TX.PCKUPTIME TX_PCKUPTIME, TX.QTYTXD TX_QTYTXD, TX.TOTALAMTPAID TX_TOTALAMTPAID, TX.TXTIME TX_TXTIME, TX.DAYSINTRVL TX_DAYSINTRVL, "
	+ "TX.DRGLOTNUM TX_DRGLOTNUM, TX.DRGEXPRTNDT TX_DRGEXPRTNDT, TX.SIGDESCRPTNTLANG TX_SIGDESCRPTNTLANG, TX.SEQNUM TX_SEQNUM, TX.PREVTXDAYSSPLY TX_PREVTXDAYSSPLY, "
	+ "TX.QTYRMNG TX_QTYRMNG, TX.HOMEDELVRYFLAG TX_HOMEDELVRYFLAG ,   "
	+ " TX.RTEOFADMIN_CERXTYPKEY TX_RTEOFADMIN_CERXTYPKEY, TX.DISCNTDRSNTYPKEY TX_DISCNTDRSNTYPKEY, "
	+ "TX.TXRSMRSNTYPKEY TX_TXRSMRSNTYPKEY, TX.TXSUBRSNTYPKEY TX_TXSUBRSNTYPKEY, TX.TXTYPKEY TX_TXTYPKEY, "
	+ "TX.INTRCHGBLTYTYPKEY TX_INTRCHGBLTYTYPKEY, TX.PKGFRMTYPKEY TX_PKGFRMTYPKEY, TX.PHARMACYCHNLTYPKEY TX_PHARMACYCHNLTYPKEY, TX.TXNSRCECHNLTYPKEY TX_TXNSRCECHNLTYPKEY, "
	+ "TX.CMPNDKEY TX_CMPNDKEY, TX.PACKKEY TX_PACKKEY, "
	+ "TX.TXNNUM TX_TXNNUM, TX.ENGMNTTYPKEY TX_ENGMNTTYPKEY, TX.COMMMODETYPKEY TX_COMMMODETYPKEY, "
	+ "TX.LASTPICKUPTIME TX_LASTPICKUPTIME," //CR_63 
	+ "TXSTAT.TXFILLSTATTYPKEY TXSTAT_TXFILLSTATTYPKEY,  "
	
	+ "PACK.PACKID PACK_PACKID, PACK.ALTRNTVPACKSZ PACK_ALTRNTVPACKSZ, PACK.ALTRNTVPACKSZUOM PACK_ALTRNTVPACKSZUOM, PACK.STRNGTH PACK_STRNGTH, PACK.GTIN PACK_GTIN, "
	+ "PACK.ISACTFLG PACK_ISACTFLG, PACK.CNSMRID PACK_CNSMRID, PACK.ISCRNTFLG PACK_ISCRNTFLG, PACK.PRDCRID PACK_PRDCRID, PACK.MFCTRDISCNTDDT PACK_MFCTRDISCNTDDT, "
	+ "PACK.PACKSZ PACK_PACKSZ, PACK.MSTRID PACK_MSTRID,  PACK.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY, PACK.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, "
	+ "PACK.DRGKEY PACK_DRGKEY "
    + "FROM TX TX "
	+ "LEFT OUTER JOIN TXSTAT TXSTAT ON TX.TXKEY = TXSTAT.TXKEY "
	+ "LEFT OUTER JOIN PACK PACK ON TX.PACKKEY = PACK.PACKKEY " ;
	
	
	private final static String QUERYBYRXKEYSQL = QUERYSQL
	+ "WHERE TX.RXKEY = ? " 
	+ "  and TX.LSTUPDT >= (SYSDATE - INTERVAL '3' year) "   // LTPHCP319
	+ "ORDER BY TX.TXNNUM DESC";
	
	 LTPHCP319_B Deprecation Ended  */
    
//	LTPHCP319_B Started

	private final static  String QUERYSQL = "SELECT /*+ index TX_3YEARS_IDX */ "
    +" tx.sigcd                   tx_sigcd, "
    +" tx.addtnldsginstrctntxt    tx_addtnldsginstrctntxt, "
    +" tx.adminprdenddt           tx_adminprdenddt, "
    +" tx.dayssply                tx_dayssply, "
    +" tx.adminprdstrtdt          tx_adminprdstrtdt, "  
    +" tx.crttimestamp            tx_crttimestamp, "
    +" tx.prdcrid                 tx_prdcrid, "
    +" tx.cnsmrid                 tx_cnsmrid, "
    +" tx.txncanclrsntxt          tx_txncanclrsntxt, "
    +" tx.lstupdt                 tx_lstupdt, "
    +" tx.pckuptime               tx_pckuptime, "
    +" tx.qtytxd                  tx_qtytxd, "
    +" tx.totalamtpaid            tx_totalamtpaid, "
    +" tx.txtime                  tx_txtime, "
    +" tx.daysintrvl              tx_daysintrvl, "
    +" tx.drglotnum               tx_drglotnum, "
    +"tx.drgexprtndt             tx_drgexprtndt, "
    +" tx.sigdescrptntlang        tx_sigdescrptntlang, "
    +"tx.seqnum                  tx_seqnum, "
    +" tx.prevtxdayssply          tx_prevtxdayssply, "
    +" tx.qtyrmng                 tx_qtyrmng,"
    +" tx.homedelvryflag          tx_homedelvryflag,"
    +" tx.rteofadmin_cerxtypkey   tx_rteofadmin_cerxtypkey,"
    +" tx.discntdrsntypkey        tx_discntdrsntypkey,"
    +" tx.txrsmrsntypkey          tx_txrsmrsntypkey,"
    +" tx.txsubrsntypkey          tx_txsubrsntypkey,"
    +" tx.txtypkey                tx_txtypkey,"
    +" tx.intrchgbltytypkey       tx_intrchgbltytypkey,"
    +" tx.pkgfrmtypkey            tx_pkgfrmtypkey,"
    +" tx.pharmacychnltypkey      tx_pharmacychnltypkey,"
    +" tx.txnsrcechnltypkey       tx_txnsrcechnltypkey,"
    +" tx.cmpndkey                tx_cmpndkey,"
    +" tx.packkey                 tx_packkey,"
    +" tx.txnnum                  tx_txnnum,"
    +" tx.engmnttypkey            tx_engmnttypkey,"
    +" tx.commmodetypkey          tx_commmodetypkey,"
    +" tx.lastpickuptime          tx_lastpickuptime,"
    +" txstat.txfillstattypkey    txstat_txfillstattypkey,"
    +" pack.packid                pack_packid,"
    +" pack.altrntvpacksz         pack_altrntvpacksz, "
    +" pack.altrntvpackszuom      pack_altrntvpackszuom, "
    +" pack.strngth               pack_strngth, "
    +" pack.gtin                  pack_gtin, "
    +" pack.isactflg              pack_isactflg,"
    +" pack.cnsmrid               pack_cnsmrid, "
    +" pack.iscrntflg             pack_iscrntflg, "
    +" pack.prdcrid               pack_prdcrid, "
    +" pack.mfctrdiscntddt        pack_mfctrdiscntddt, "
    +" pack.packsz                pack_packsz, "
    +" pack.mstrid                pack_mstrid, "
    +" pack.packszuomtypkey       pack_packszuomtypkey, "
    +" pack.strngthuomtypkey      pack_strngthuomtypkey, "
    +" pack.drgkey                pack_drgkey "
    +" FROM tx tx, "
    +" txstat, "
    +" pack ";
//	LTPHCP319_B Ended	




private final static  String QUERYBYTXNUMSQL = QUERYSQL
	+" WHERE tx.txkey = txstat.txkey       "
    +"  AND tx.packkey = pack.packkey(+)  "
    + "  AND TX.STORENUM = ?    "
    + "  AND TX.TXNNUM = ?  "
    +"ORDER BY TX.TXNNUM DESC";
 
//  LTPHCP319_B Started
	
private static    String QUERYBYRXKEYSQL = QUERYSQL

  +	"	WHERE tx.txkey = txstat.txkey       "
  + "  AND tx.packkey = pack.packkey(+)  "  
  +	"	  AND tx.rxkey = ?                  "
  +     "	ORDER BY TX.TXNNUM DESC";

//  LTPHCP319_B Ended
	

//  LTPHCP319_B Started
	
    private  static   String QUERYBYRXKEYSQL1 = QUERYSQL
	  +	"	WHERE tx.txkey = txstat.txkey       "
      +     "	  AND tx.packkey = pack.packkey(+)  "
      +     "     and to_char(TX.LSTUPDT, 'YYYY-MM-DD') >= ? "
	  +	    "	  and tx.rxkey = ?     "
      +     "	ORDER BY TX.TXNNUM DESC";


//  LTPHCP319_B Ended
	



	private final static String FINDRXKEYBYTXNUMSQL = "SELECT RXKEY FROM TX WHERE STORENUM = ? AND TXNNUM = ?";
		
		
	public List<Dispense> fetchList(Connection connection, Long prescriptionKey,int rxNum, Set<String> primaryRxDispensesSet, Set<String> linkedRxDispensesSet, boolean isFilterActive) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long querytimer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("Start populate dispense list : prescription number " + prescriptionKey);}
		List<Dispense> dispenses = new ArrayList<Dispense>();
		Set <String> txStatusSet = new HashSet<String>(); // LTPHCP319
		Set <String> linkedDspList = new HashSet<String>();
//      2020-10-15_Dispences Started
//		Set <String> txStatusSet = new HashSet<String>();
        List<String> primaryRxStatusList = new ArrayList<>();
	    List<String> linkedRxStatusList = new ArrayList<>();
	    boolean primaryRxDispensesFilterActive;
		boolean linkedRxDispensesFilterActive;
	    if (rxNum > 0) {
		    primaryRxDispensesFilterActive = true;
			linkedRxDispensesFilterActive = true;
	    } else {
		    primaryRxDispensesFilterActive = false;
			linkedRxDispensesFilterActive = false;
	    }
//      2020-10-15_Dispences Ended
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
	    if(isFilterActive) { // isFilterActive = true only for PAtientAdherence Cal
	    	  preparedStatement = connection.prepareStatement(QUERYBYRXKEYSQL1); 
	    	 
	    	   DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    	   LocalDate years3oldDate = LocalDate.now().minusYears(3);
	    	   String years3oldDateString = years3oldDate.format(format);
	    	   preparedStatement.setString(1,years3oldDateString);
	    	   preparedStatement.setLong(2, prescriptionKey);
		}
	    else {
	    	  preparedStatement = connection.prepareStatement(QUERYBYRXKEYSQL);
	    	  preparedStatement.setLong(1, prescriptionKey);
	    }
	     
	 	
	
	 	if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DispenseGet.queryByRxKeySQL. prescriptionKey : " + prescriptionKey );}
		  resultSet = preparedStatement.executeQuery();
		 if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DispenseGet.queryByRxKeySQL. prescriptionKey : " + prescriptionKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			Dispense dispense = scanBase(resultSet);
			scanChildren(connection,dispense,resultSet,rxNum, primaryRxDispensesSet, linkedRxDispensesSet, primaryRxStatusList, linkedRxStatusList, primaryRxDispensesFilterActive, linkedRxDispensesFilterActive);
			if(dispense!=null)
				dispenses.add(dispense);
		}
		}
	//	linkedRxDispenses.addAll(linkedDspList);
		finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		return dispenses;
	};
	
	
		
	
	public Dispense fetch(Connection connection, String storeNum, String dispenseNum, Map<Long, Drug> drgPK,Map <Long, Compound> cmpndPK) throws SQLException, NamingException, IOException, ParseException,
			DatatypeConfigurationException, CDRInternalException {
		try {
			return populate(connection, storeNum, dispenseNum,drgPK,cmpndPK);
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

	private Dispense populate(Connection connection, String storeNumber, String dispenseNumber, Map<Long, Drug> drgPK,Map <Long, Compound> cmpndPK) throws SQLException, NamingException, IOException,
			ParseException, DatatypeConfigurationException, CDRInternalException {

		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseGet.populate. DispenseNumber : " + dispenseNumber + ", StoreNumber : " + storeNumber );}

		Dispense dispense = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		preparedStatement=connection.prepareStatement(QUERYBYTXNUMSQL);
		preparedStatement.setString(1, storeNumber);
		preparedStatement.setString(2, dispenseNumber);
		
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DispenseGet.queryByTxNumSQL. DispenseNumber : " + dispenseNumber + ", StoreNumber : " + storeNumber );}
		 resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DispenseGet.queryByTxNumSQL. DispenseNumber : " + dispenseNumber + ", StoreNumber : " + storeNumber  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}

		// String packageForm = populatePackageForm(rs, alias);
		// dispense.setPackageForm(packageForm);

		if (resultSet.next()) {
			dispense = scanBase(resultSet);
			scanChildren(connection, dispense,resultSet,0, null,null, null,null, false, false);// LTPHCP319
		};
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: DispenseGet.populate. DispenseNumber : " + dispenseNumber + ", StoreNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		
		return dispense;
	}
	
	
	private Dispense scanBase(ResultSet rs) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseGet.scanBase.");}
		
		Dispense dispense = new Dispense();
		dispense.setTransactionNumber(CommonUtil.getInt("TX_TXNNUM",rs));

		// String txId = getString("TXID");
		dispense.setAdditionalDosageInstructionText(rs.getString("TX_ADDTNLDSGINSTRCTNTXT"));
		dispense.setAdministrationPeriodEnd(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate("TX_ADMINPRDENDDT")));
		dispense.setDaysSupply(CommonUtil.getInt("TX_DAYSSPLY",rs));
		dispense.setAdministrationPeriodStart(
				CommonUtil.getXMLGregorianCalendarByDate(rs.getDate("TX_ADMINPRDSTRTDT")));

		dispense.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_CRTTIMESTAMP"), true));
		dispense.setProducerId(rs.getString("TX_PRDCRID"));
		dispense.setConsumerId(rs.getString("TX_CNSMRID"));
		dispense.setTransactionCancellationReasonText(rs.getString("TX_TXNCANCLRSNTXT"));
		dispense.setLastUpdate(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_LSTUPDT"), true));

		dispense.setPickupTime(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_PCKUPTIME"), true));  
		dispense.setPickupTime2(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_LASTPICKUPTIME"), true));  
		dispense.setQuantityDispensed(CommonUtil.getBigDecimal("TX_QTYTXD",rs));
		dispense.setTotalAmountPaid(CommonUtil.getBigDecimal("TX_TOTALAMTPAID",rs));
		dispense.setDispenseTime(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_TXTIME"), true));
		dispense.setDaysInterval(CommonUtil.getInt("TX_DAYSINTRVL",rs));

		dispense.setDrugLotNumber(rs.getString("TX_DRGLOTNUM"));
		//dispense.setDosageInstructionExpiryDate(); //setDosageInstructionExpiryDate is not available 
		dispense.setDrugExpirationDate(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate("TX_DRGEXPRTNDT")));
		dispense.setSigDescriptionPatientLanguage(rs.getString("TX_SIGDESCRPTNTLANG"));
		dispense.setSequenceNumber(CommonUtil.getInt("TX_SEQNUM",rs));
		dispense.setPreviousDispenseDaysSupply(CommonUtil.getInt("TX_PREVTXDAYSSPLY",rs));

		dispense.setQuantityRemaining(CommonUtil.getInt("TX_QTYRMNG",rs));
		dispense.setIsHomeDeliveryFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("TX_HOMEDELVRYFLAG"))); 
		
		Long routeOfAdminKey = CommonUtil.getLong("TX_RTEOFADMIN_CERXTYPKEY",rs);
		if ( routeOfAdminKey != null)
		{
			String routeOfAdminCode = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
			RouteOfAdmin routeOfAdmin = (routeOfAdminCode != null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
			dispense.setAdministrationRouteCode(routeOfAdmin);
		}
		Long discontinuedReasonKey = CommonUtil.getLong("TX_DISCNTDRSNTYPKEY",rs);
		if (discontinuedReasonKey != null)
		{	
			String discontinuedReasonCode = getCodeFromKey(LT_DISCNTDRSNTYP, discontinuedReasonKey);
			dispense.setDiscontinuedReasonCode(discontinuedReasonCode);
		}
		//Long reqCommModeTypeKey = getLong("TX_TXREQCOMMMODETYPKEY");
		
		Long resumeReasonTypeKey = CommonUtil.getLong("TX_TXRSMRSNTYPKEY",rs);
		if(resumeReasonTypeKey != null)
		{
			String resumeReasonCode = getCodeFromKey(LT_TXRSMRSNTYP, resumeReasonTypeKey);
			PrescriptionResumeReasonTypeDisplay prescriptionResumeReasonTypeDisplay = new PrescriptionResumeReasonTypeDisplay();
			prescriptionResumeReasonTypeDisplay.setPrescriptionResumeReasonTypeCode(PrescriptionResumeReason.fromValue(resumeReasonCode));
			dispense.setResumeReasonCode(prescriptionResumeReasonTypeDisplay);
		}

		Long substitutionTypeKey = CommonUtil.getLong("TX_TXSUBRSNTYPKEY",rs);
		if (substitutionTypeKey != null)
		{
			String substitutionCode = getCodeFromKey(LT_TXSUBRSNTYP, substitutionTypeKey);
			SubstitutionReason substitutionReason = SubstitutionReason.fromValue(substitutionCode);
			dispense.setSubstitutionReason(substitutionReason);
		}
		Long txTypeKey = CommonUtil.getLong("TX_TXTYPKEY",rs);
		if (txTypeKey != null) {
			String dispenseCode = getCodeFromKey(LT_TXTYP, txTypeKey);
			dispense.setDispenseType(dispenseCode);
		}
		
		Long interchangeabilityTypeKey = CommonUtil.getLong("TX_INTRCHGBLTYTYPKEY",rs);
		if( interchangeabilityTypeKey != null)
		{
			 String interchangeabilityCode = getCodeFromKey(LT_INTRCHGBLTYTYP, interchangeabilityTypeKey);
			 Interchangeability interchangeability = Interchangeability.fromValue(interchangeabilityCode);
			 dispense.setInterchangeability(interchangeability);
		}
		
		Long packageFormTypeKey = CommonUtil.getLong("TX_PKGFRMTYPKEY",rs);
		if (packageFormTypeKey != null)
		{
			 String packageFormCode = getCodeFromKey(LT_PKGFRMTYP, packageFormTypeKey);
			 dispense.setPackageForm(packageFormCode);
		}
		
		Long pharmacyChannelTypeKey =CommonUtil.getLong("TX_PHARMACYCHNLTYPKEY",rs);
		Long engagementTypeKey = CommonUtil.getLong("TX_ENGMNTTYPKEY",rs);
		Long communicationModeKey = CommonUtil.getLong("TX_COMMMODETYPKEY",rs);
		
		if(pharmacyChannelTypeKey != null && engagementTypeKey != null && communicationModeKey !=null)
		{
			PharmacyChannel pharmacyChannel = new PharmacyChannel();
			String pharmacyChannelTypeCode = getCodeFromKey(LT_PHARMACYCHNLTYP, pharmacyChannelTypeKey);
			pharmacyChannel.setChannelType(ChannelType.fromValue(pharmacyChannelTypeCode));

			String engagementTypeCode = getCodeFromKey(LT_ENGMNTTYP, engagementTypeKey);
			pharmacyChannel.setEngagment(EngagementType.fromValue(engagementTypeCode));
			
			String communicationModeCode = getCodeFromKey(LT_COMMMODETYP, communicationModeKey);
			pharmacyChannel.setCommunicationMode(CommunicationModeType.fromValue(communicationModeCode));
			
			dispense.setPharmacyChannel(pharmacyChannel);
		}
		
		dispense.setSigCode(rs.getString("TX_SIGCD"));			
		
		Long transactionSourceChannelKey = CommonUtil.getLong("TX_TXNSRCECHNLTYPKEY",rs);
		if(transactionSourceChannelKey != null)
		{
			String transactionSourceChannelCode = null;
			transactionSourceChannelCode =	 getCodeFromKey(LT_TXNSRCECHNLTYP, transactionSourceChannelKey);
			TransactionSourceChannel transactionSourceChannel = (!CommonUtil.isEmpty(transactionSourceChannelCode)) ? TransactionSourceChannel.fromValue(transactionSourceChannelCode)	 : null;
			dispense.setTransactionSourceCode(transactionSourceChannel);
		};
	    
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: DispenseGet.scanBase. Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		
		return dispense;
	}
	
	
	private void scanChildren(Connection connection, 
            Dispense dispense,
            ResultSet rs,
            int rxNum, 
            Set<String> primaryRxDispensesSet,
       	  Set<String> linkedRxDispensesSet,
            List<String> primaryRxStatusList,
    	      List<String> linkedRxStatusList,
    	      boolean primaryRxDispensesFilterActive,
    		  boolean linkedRxDispensesFilterActive) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException {			                  

		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DispenseGet.scanChildren");}
		
		
		Long compoundKey = CommonUtil.getLong("TX_CMPNDKEY",rs);
		if(compoundKey != null)
		{
			CompoundGet compoundGet = new CompoundGet();
			Compound compound = compoundGet.fetch(connection, compoundKey);
			dispense.setCompound(compound);
		}

		Long packKey = CommonUtil.getLong("TX_PACKKEY",rs);
		if (packKey != null) {
			Pack pack = CommonUtil.populatePack(rs);
			if (pack != null) {
				dispense.setPack(pack);
				Long drugKey = CommonUtil.getLong("PACK_DRGKEY",rs);
				if (drugKey != null) {
					DrugGet drugGet = new DrugGet();
					Drug drug = drugGet.fetch(connection, drugKey);
					if (drug != null) {
						pack.setDrug(drug);
					}
				}
			}
		};
	
		int txNum = CommonUtil.getInt("TX_TXNNUM",rs);   // LTPHCP319
		Long dispenseFillStatusKey = CommonUtil.getLong("TXSTAT_TXFILLSTATTYPKEY",rs);
		if(dispenseFillStatusKey != null) {
	        String fillStatusCode = getCodeFromKey(LT_TXFILLSTATTYP, dispenseFillStatusKey);
	        if (fillStatusCode != null) {
	        	dispense.setFillStatus(TxFillStatus.fromValue(fillStatusCode));
	        	
	        	// 2020-10-15_Dispences Started
	        	String txCompositeKey = String.format("%20d %20d", rxNum, txNum);
	        	if (primaryRxDispensesFilterActive) {
	                primaryRxDispensesFilterActive = PrimaryRxDispensesFilter(primaryRxDispensesSet, primaryRxStatusList, dispense.getFillStatus().value(), txCompositeKey);
	            }    
	               
	            if (linkedRxDispensesFilterActive) {
	                linkedRxDispensesFilterActive = LinkedRxDispensesFilter(linkedRxDispensesSet, linkedRxStatusList, dispense.getFillStatus().value(), txCompositeKey);
	            }	        	
	            // 2020-10-15_Dispences Ended
	        /*	if (rxNum > 0 && linkedRxDispenses != null) {
	        		String txCompositeKey = String.format("%20d %20d", rxNum, txNum);
	        		if (linkedRxDispenses.isEmpty() ) {
	        				linkedRxDispenses.add(txCompositeKey);
		        			txStatusSet.add(dispense.getFillStatus().value());
	        		
	        		} else {
	        			if ((! txStatusSet.contains(TxFillStatus.COMPLETE.value()))  &&
        					(! linkedRxDispenses.contains(txCompositeKey))  &&
        					(  TxFillStatus.COMPLETE.equals(dispense.getFillStatus()) )) {
	        				
		        			linkedRxDispenses.add(txCompositeKey);
		        			txStatusSet.add(dispense.getFillStatus().value());
        				}
	        		}
	        	}*/
	        	
	        }
		}
		if(logger.isInfoEnabled()) { logger.info("EndApiCall: DispenseGet.scanChildren. Total time is : " + (System.currentTimeMillis() - timer) + " ms");}		
	}
	
	// 2020-10-15_Dispences Started
		private boolean PrimaryRxDispensesFilter(Set<String> primaryRxDispensesSet, List<String> primaryRxStatusList, String currDispenseStatus, String txCompositeKey) {
	         Predicate<String> testCompleteStatus = completeStatus -> (completeStatus.equals("Complete"));
	         Predicate<String> testCancelledStatus = cancelledStatus -> (cancelledStatus.equals("Cancelled"));
	        
	         boolean isFilterActive = true;
			 Long totalCompleteDispenses = primaryRxStatusList.stream().filter(testCompleteStatus).count();
	        
	         // save first dispense status
			 if (primaryRxStatusList.isEmpty() ) {
				 primaryRxStatusList.add(currDispenseStatus);
				 primaryRxDispensesSet.add(txCompositeKey);
				 
				 isFilterActive = true;
				 return isFilterActive;
			 }
	        
					 
			 if ( primaryRxStatusList.get(0).equals("Cancelled") &&
				  primaryRxStatusList.size() == 1                && 
				! currDispenseStatus.equals("Cancelled") ) {

				  primaryRxStatusList.add(currDispenseStatus);
				  primaryRxDispensesSet.add(txCompositeKey);

				  isFilterActive = true;
				  return isFilterActive;
			 }

			 
			 if ( currDispenseStatus.equals("Complete") ) {
				 if ( totalCompleteDispenses.intValue() < 2 ) {
					  primaryRxStatusList.add(currDispenseStatus);
					  primaryRxDispensesSet.add(txCompositeKey);
					  isFilterActive = true;
					  return isFilterActive;
				 } else {
					  isFilterActive = false;
					  return isFilterActive;
				 }
			 }
			 return isFilterActive;
	    }
		
		 private boolean LinkedRxDispensesFilter(Set<String> linkedRxDispensesSet, List<String> linkedRxStatusList, String currDispenseStatus, String txCompositeKey) {
	    		Predicate<String> testCompleteStatus = completeStatus -> (completeStatus.equals("Complete"));
	    		Predicate<String> testCancelledStatus = cancelledStatus -> (cancelledStatus.equals("Cancelled"));

	    		boolean isFilterActive = true;
	    		Long totalCompleteDispenses = linkedRxStatusList.stream().filter(testCompleteStatus).count();

				// save first dispense status
				if (linkedRxStatusList.isEmpty() ) {
					linkedRxStatusList.add(currDispenseStatus);
					linkedRxDispensesSet.add(txCompositeKey);
					 
					isFilterActive = true;
					return isFilterActive;
				}
				
				
				if ( currDispenseStatus.equals("Complete") ) {
					 if ( totalCompleteDispenses.intValue() < 2 ) {
						  linkedRxStatusList.add(currDispenseStatus);
						  linkedRxDispensesSet.add(txCompositeKey);
						  isFilterActive = true;
						  return isFilterActive;
					 } else {
						  isFilterActive = false;
						  return isFilterActive;
					 }
				}
				return isFilterActive;
		}
	    // 2020-10-15_Dispences Ended
	
	public Long findRxKey(Connection connection, String txnNumber, String storeNumber) throws DispenseNotFoundException, SQLException {
		try {
			Long querytimer = System.currentTimeMillis();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Long rxKey ;
			try {
			preparedStatement=connection.prepareStatement(FINDRXKEYBYTXNUMSQL);
			preparedStatement.setString(1, storeNumber);			
			preparedStatement.setString(2, txnNumber);

			if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DispenseGet.findRxKey. DispenseNumber : " + txnNumber + ", StoreNumber : " + storeNumber);}
			 resultSet = preparedStatement.executeQuery();
			if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DispenseGet.findRxKey. DispenseNumber : " + txnNumber + ", StoreNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms");}

			if (!resultSet.next()) // No dispense record found, a dispense always has a Rxkey
			{
				if(logger.isDebugEnabled()) {logger.debug("dispense not found for store number " + storeNumber + ", dispense number " + txnNumber);}
				throw new DispenseNotFoundException(String.valueOf(storeNumber), String.valueOf(txnNumber));
			}
			  rxKey = CommonUtil.getLong("RXKEY",resultSet);
			}finally {
			CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
			}
			return rxKey;
		} catch (DispenseNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
		}
	}
}
