package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PACKSZUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSCBRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXFILLSTATTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXHLDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TRTMNTTYP;

import java.io.IOException;
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

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Compound;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Dispense;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Drug;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.FormularyRing;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Pack;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PackSizeUoMTypeDisplay;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Patient;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Prescriber;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Prescription;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PrescriptionHoldReasonTypeDisplay;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.CrxReferenceBean;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.upsert.api.PrescriptionApi;


public class PrescriptionMedReviewGet extends CDRMedReviewGet {

	private static Logger logger = LogManager.getLogger(PrescriptionMedReviewGet.class);
	 private    Map <Long, Drug> drgPK = new HashMap<Long, Drug>();
	 private     Map <Long, Compound> cmpndPK = new HashMap<Long, Compound>();
	// APR.DPHARM.023  TE97_023 start
	public PrescriptionMedReviewGet() {
		try {
			queryBasicSQL = TableCacheSingleton.getResource("PrescriptionMedReviewGet_QueryBasicSQL.sql");
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	private static String queryBasicSQL ="\r\n" + 
			"SELECT distinct RX.RXID RX_RXID,\r\n" + 
			"                RX.STORENUM STORENUM,\r\n" + 
			"                RX.UPDTTIMESTAMP RX_UPDTTIMESTAMP,\r\n" + 
			"                RX.ADMINSTARTDATE RX_ADMINSTARTDATE,\r\n" + 
			"                RX.ADMINSTOPDATE RX_ADMINSTOPDATE,\r\n" + 
			"                RX.QTYTXD RX_QTYTXD,\r\n" + 
			"                RX.RMNGQTY RX_RMNGQTY,\r\n" + 
			"                RX.RXNUM RX_RXNUM,\r\n" + 
			"                RX.RFLSRMNGCNT RX_RFLSRMNGCNT,\r\n" + 
			"                RX.TOTALQTYATHRZD RX_TOTALQTYATHRZD,\r\n" + 
			"                RX.DRGTOTALDAYSOFSPLYCNT RX_DRGTOTALDAYSOFSPLYCNT,\r\n" + 
			"                RX.RXDT RX_RXDT,\r\n" + 
			"                RX.RXEXPRTNDT RX_RXEXPRTNDT,\r\n" + 
			"                RX.SIGDESCRPTNTLANG RX_SIGDESCRPTNTLANG,  \r\n" + 
			"                RX.CRTTIMESTAMP RX_CRTTIMESTAMP,\r\n" + 
			"                RX.QTYRXD RX_QTYRXD, \r\n" + 
			"                RX.SVCLOCKEY RX_SVCLOCKEY,\r\n" + 
			"                RX.PRSCBRKEY RX_PRSCBRKEY,\r\n" + 
			"                RX.LINKEDRXKEY RX_LINKEDRXKEY,\r\n" + 
			"                RX.RCRDRKEY RX_RCRDRKEY,\r\n" + 
			"                RX.SPRVSRKEY RX_SPRVSRKEY, \r\n" + 
			"                RX.STORENUM RX_STORENUM,\r\n" + 
			"                RX.RXKEY RX_RXKEY,\r\n" + 
			"                RX.TXSUBDCLNDRSNTYPKEY RX_TXSUBDCLNDRSNTYPKEY, \r\n" + 
			"                RX.PRSCBRTYPKEY RX_PRSCBRTYPKEY,\r\n" + 
			"                RX.RXHLDRSNTYPKEY RX_RXHLDRSNTYPKEY, \r\n" + 
			"                RX.RXRFSLRSNTYPKEY RX_RXRFSLRSNTYPKEY,\r\n" + 
			"                RX.SIGCD RX_SIGCD,\r\n" + 
			"                RX.STRNGTHUOMTYPKEY RX_STRNGTHUOMTYPKEY,\r\n" + 
			"                RX.TRTMNTTYPKEY RX_TRTMNTTYPKEY, \r\n" + 
			"                RX.CMPNDKEY RX_CMPNDKEY,\r\n" + 
			"                RX.DRGKEY RX_DRGKEY,\r\n" + 
			"                RX.PACKKEY RX_PACKKEY,\r\n" + 
			"                RX.PTNTKEY RX_PTNTKEY,\r\n" + 
			"                RX.PRSNKEY RX_PRSNKEY, \r\n" + 
			"                RX.PRESCRIBERADDRKEY RX_PRESCRIBERADDRKEY";
	
	//RXSTAT.FILLSTATEFFDT RXSTAT_FILLSTATEFFDT, RXSTAT.FILLSTATKEY RXSTAT_FILLSTATKEY ,"
	//+ "RXSTAT.SUBFILLSTATKEY RXSTAT_SUBFILLSTATKEY
	// APR.DPHARM.023  TE97_023 end
	
	private  static String queryAdancedSQL =
			", " 
			+ "PACK.PACKID PACK_PACKID,    PACK.STRNGTH PACK_STRNGTH, PACK.GTIN PACK_GTIN, "
			+ "PACK.PACKSZ PACK_PACKSZ, PACK.PACKKEY PACK_PACKKEY, PACK.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY, PACK.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, "
			+ "PACK.DRGKEY PACK_DRGKEY ";
	
	private final static String queryByRxNumSQL = queryBasicSQL + queryAdancedSQL 
			+ "FROM RX "
		//	+ "LEFT OUTER JOIN RXSTAT RXSTAT ON RXSTAT.RXKEY = RX.RXKEY "
			+ "LEFT OUTER JOIN PACK PACK ON RX.PACKKEY = PACK.PACKKEY "			
			+ "WHERE RX.STORENUM = ? AND RX.RXNUM = ?";

	private final static String queryByRxKeySQL = queryBasicSQL + queryAdancedSQL
			+ "FROM RX "
		//	+ "LEFT OUTER JOIN RXSTAT RXSTAT ON RXSTAT.RXKEY = RX.RXKEY "
			+ "LEFT OUTER JOIN PACK PACK ON RX.PACKKEY = PACK.PACKKEY "			
			+ "WHERE RX.RXKEY = ?";
	
	/*private final static String queryLinkedRxSQL = queryBasicSQL
			+ "  FROM RX "
		//	+ "LEFT OUTER JOIN RXSTAT RXSTAT ON RXSTAT.RXKEY = RX.RXKEY "
			+ "WHERE RX.RXKEY = ?";*/
	private final static String queryLinkedRxSQL = "Select RX.RXNUM RX_RXNUM from RX WHERE RX.RXKEY = ? ";
	
	private final static String queryByPatientKeySQL = queryBasicSQL + queryAdancedSQL
			+ "FROM RX "
			//	+ "LEFT OUTER JOIN RXSTAT RXSTAT ON RXSTAT.RXKEY = RX.RXKEY "
			+ "LEFT OUTER JOIN PACK PACK ON RX.PACKKEY = PACK.PACKKEY "			
			+ "WHERE RX.PTNTKEY = ?";
	
   	private final static String GETGTINFROMTXSQL  ="select t.txnnum,t.packkey,p.drgkey,p.gtin,p.PACKID PACK_PACKID, p.ALTRNTVPACKSZ PACK_ALTRNTVPACKSZ, p.ALTRNTVPACKSZUOM PACK_ALTRNTVPACKSZUOM,"
			+ " p.STRNGTH PACK_STRNGTH, p.GTIN PACK_GTIN, " 
			+ " p.PACKSZ PACK_PACKSZ,   p.PACKKEY PACK_PACKKEY, p.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY, p.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, " 
			+ " p.DRGKEY PACK_DRGKEY  from tx   t,   pack p   where t.rxkey = ?  and t.packkey is not null  and t.packkey = p.packkey "
			+ " order by t.txnnum desc fetch first 1 rows only ";
	//private final static String forwardRxSQL="select rxnum from rx where linkedrxkey = (select rxkey from rx where rxnum = ? and storenum = ?) ";
//  LTPHCP319 Started
	private final static String rxStatSql = 
			  " SELECT S.FILLSTATKEY    RXSTAT_FILLSTATKEY,"
			+ "        S.SUBFILLSTATKEY RXSTAT_SUBFILLSTATKEY "
			+ "   FROM RXSTAT S"
			+ "  WHERE S.RXKEY = ? ";
	
	
	private final static String forwardRxSQL=
			" SELECT RX.RXNUM      RX_NUM, "
	  	  + "        S.FILLSTATKEY RXSTAT_FILLSTATKEY "
	  	  + "   FROM RX RX, RXSTAT S    "
		  + "  WHERE RX.LINKEDRXKEY = S.RXKEY"
		  + "    AND RX.LINKEDRXKEY IS NOT NULL "
		  + "    AND RX.LINKEDRXKEY = ? ";
//  LTPHCP319 Started
	//private final static String forwardRxSQL="select RX.RXNUM RX_NUM ,RXSTAT.FILLSTATKEY RXSTAT_FILLSTATKEY from RX RX LEFT OUTER JOIN RXSTAT RXSTAT ON RXSTAT.RXKEY = RX.RXKEY  where linkedrxkey is not null and linkedrxkey= ? ";
	public Prescription fetchByRxNum(Connection connection, String storeNumber, String prescriptionNumber,boolean sendToODM,CrxReferenceBean bean) throws CDRInternalException, ParseException, SQLException, DatatypeConfigurationException, NamingException, IOException
	{
		try {
			return populateByRxNum(connection, storeNumber, prescriptionNumber,sendToODM, bean);
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
		}

	}

	public Prescription fetchByRxKey(Connection connection, Long prescriptionKey,boolean sendToODM,CrxReferenceBean bean) throws CDRInternalException, ParseException, SQLException, DatatypeConfigurationException, NamingException, IOException
	{
		try {
			return populateByRxKey(connection, prescriptionKey,sendToODM,bean);
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
		}
	}

	public List<Prescription> fetchByPatientId(Connection connection, String storeNumber, String patientId,boolean sendToODM) throws CDRInternalException, ParseException, SQLException, DatatypeConfigurationException, NamingException, IOException, PatientNotFoundException
	{
		try {
			PatientViewMedReviewGet patientViewMedReviewGet = new PatientViewMedReviewGet();
			Patient patient = patientViewMedReviewGet.fetch(connection, storeNumber, patientId);
			Long patientKey = patientViewMedReviewGet.getPatientKey();
					
			if(patientKey ==null)
				throw new PatientNotFoundException(String.valueOf(storeNumber), patientId);
			List<Prescription> prescriptions = populateByPatientKey(connection, patientKey, sendToODM, storeNumber);
			
			/*Iterator<Prescription>  iterator = prescriptions.iterator();
			while (iterator.hasNext()) {
				Prescription current = iterator.next();
				current.setPatient(patient);
			}*/
			return prescriptions;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
		}

	}
	
	//CDR-CR-67 Forward Linking
	private Prescription getForwardRx(Connection connection, Long prescriptionKey,String storeNum) throws SQLException, CDRInternalException,
	ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PrescriptionMedReviewGet.getForwardRx. PrescriptionKey : " + prescriptionKey );}
		preparedStatement = connection.prepareStatement(forwardRxSQL);
		preparedStatement.setLong(1, prescriptionKey);
	
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PrescriptionMedReviewGet.getForwardRx. PrescriptionKey : " + prescriptionKey);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : PrescriptionMedReviewGet.getForwardRx. PrescriptionKey : " + prescriptionKey + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		Prescription forwardRx=null;
		if (resultSet.next()) {
			forwardRx = new Prescription();
			forwardRx.setPrescriptionNumber(getInt("RX_NUM"));

		}
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PrescriptionMedReviewGet.getForwardRx. PrescriptionKey : " + prescriptionKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return forwardRx;
		
	}
	
	private Prescription populateLinkedRx(Connection connection, Long prescriptionKey) throws SQLException, CDRInternalException,
			ParseException, DatatypeConfigurationException, NamingException, IOException {
		
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PrescriptionGet.populateLinkedRx. PrescriptionKey : " + prescriptionKey );}
		
		
		Prescription prescription = null;
		preparedStatement = connection.prepareStatement(queryLinkedRxSQL);
		preparedStatement.setLong(1, prescriptionKey);

		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PrescriptionGet.populateLinkedRx. PrescriptionKey : " + prescriptionKey);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : PrescriptionGet.populateLinkedRx. PrescriptionKey : " + prescriptionKey + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}

		if (resultSet.next()) {
			prescription  = new Prescription();
			prescription.setPrescriptionNumber(getInt("RX_RXNUM"));
		}
		;
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PrescriptionGet.populateLinkedRx. PrescriptionKey : " + prescriptionKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return prescription;
	}
	
	
//// APR.DPHARM.023  TE97_023 start
	private Prescription scanBase() throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Prescription prescription = new Prescription();
		prescription.setUpdateTimestamp(getCalendar("RX_UPDTTIMESTAMP")); // A
		prescription.setAdministrationPeriodStartDate(getCalendar("RX_ADMINSTARTDATE"));// A
		prescription.setAdministrationPeriodEndDate(getCalendar("RX_ADMINSTOPDATE"));// A
		// prescription.setQuantityDispensed(getBigDecimal("RX_QTYTXD"));//A
		prescription.setRemainingQuantity(getBigDecimal("RX_RMNGQTY"));// A
		prescription.setPrescriptionNumber(getInt("RX_RXNUM"));// A
		prescription.setRefillsRemainingCount(getInt("RX_RFLSRMNGCNT"));// A
		prescription.setTotalQuantityAuthorized(getBigDecimal("RX_TOTALQTYATHRZD"));// A
		prescription.setDrugTotalDaysOfSupplyCount(getInt("RX_DRGTOTALDAYSOFSPLYCNT"));// A
		prescription.setPrescriptionDate(getCalendar("RX_RXDT"));// A
		prescription.setPrescriptionExpirationDate(getCalendar("RX_RXEXPRTNDT"));// A
		prescription.setSIGCode(getString("RX_SIGCD")); // A
		prescription.setSIGDescriptionPatientLanguage(getString("RX_SIGDESCRPTNTLANG"));// A
		prescription.setCreateTimestamp(getCalendar("RX_CRTTIMESTAMP"));// A
		prescription.setQuantityPrescribed(getBigDecimal("RX_QTYRXD"));// A
//		prescription.setNoMoreDispensesAllowedFlag(null);

		// populateRxStatData(prescription,getLong("RX_RXKEY"));

		Long treatmentTypeKey = getLong("RX_TRTMNTTYPKEY"); // A
		if (treatmentTypeKey != null) {
			String treatmentTypeCode = getCodeFromKey(LT_TRTMNTTYP, treatmentTypeKey);
			prescription.setTreatmentType(treatmentTypeCode);
		}
		;

		return prescription;
	}
	
	private void populateRxStatData(Connection connection, Prescription prescription)
			throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException, ParseException, DatatypeConfigurationException {

		
			PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {  
			ps = connection.prepareStatement(rxStatSql);
			ps.setLong(1, getLong("RX_RXKEY"));

			  rs = ps.executeQuery();

			if (rs.next()) {
				Long fillStatusKey = new Long(rs.getLong("RXSTAT_FILLSTATKEY"));
			  	if (fillStatusKey != null && fillStatusKey > 0L) {
					String fillStatusCode = getCodeFromKey(LT_RXFILLSTATTYP, fillStatusKey);
				    prescription.setFillStatusCode(fillStatusCode);
				}

				Long subFillStatusKey = new Long(rs.getLong("RXSTAT_SUBFILLSTATKEY"));
				if (fillStatusKey != null && subFillStatusKey > 0L) {
					String subFillStatusCode = getCodeFromKey(LT_RXFILLSTATTYP, subFillStatusKey);
					if (subFillStatusCode != null) {
						prescription.setFillStatusSubCode(subFillStatusCode);
					}
				}
						
			}
			

		} catch (SQLException e) {
			throw e;
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
	}

	// APR.DPHARM.023  TE97_023 end
	
	//TE97 added Linked Rx Dispense start
	private void scanChildren(Connection connection, Prescription prescription, boolean isPatientRequired,boolean sendToODM,CrxReferenceBean bean) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Long prescribedDrugKey = getLong("RX_DRGKEY");
		String storeNum=getString("STORENUM");
		String din =null;
		if(prescribedDrugKey != null)
		{
			DrugMedReviewGet drugGet = new DrugMedReviewGet();
			// VLAD CRX_REFERENCE_BEAN Started
			Drug drug = drugGet.fetch(connection, prescribedDrugKey,storeNum,bean,drgPK);
			if (drug != null) {
				prescription.setPrescribedDrug(drug);	
				din = drug.getDin();
				if(drug.getRing().isEmpty()&& din!=null) {
				List<FormularyRing> ringList =	FindUtil.populateFormularyRingForPrescribedDrug(connection,din,storeNum);
				drug.getRing().addAll(ringList);
				}
			}
		}
		
		Long prescribedCompoundKey = getLong("RX_CMPNDKEY");
		if(prescribedCompoundKey != null)
		{
			CompoundMedReviewGet compoundMedReviewGet = new CompoundMedReviewGet();
			Compound compound = compoundMedReviewGet.fetch(connection, prescribedCompoundKey,drgPK,cmpndPK);
			prescription.setPrescribedCompound(compound);
		}
		
//		Long discontinueReasonTypeKey = getLong("RX_RxDiscntdRsnTypKey"); //Field not presented.
//		prescription.setDiscontinuedReasonCode(null);
		
		Long rxKey = getLong("RX_RXKEY");
	DispenseMedReviewGet dispenseMedReviewGet = new DispenseMedReviewGet();
		List<Dispense> dispenseList =dispenseMedReviewGet.fetchList(connection, rxKey) ;
		
		if(dispenseList != null && dispenseList.size() !=0 )
			prescription.getDispense().addAll(dispenseList);	
		
	
		/*			Long recorderKey = getLong("RX_RCRDRKEY");
		if(recorderKey!=null)
		{
			PersonRoleViewGet recorderGet = new PersonRoleViewGet();
			Recorder recorder = recorderGet.fetchRecorder(connection,  recorderKey);
			
			prescription.setRecorder(recorder);
			
		};
		*/
		Long holdReasonTypeKey = getLong("RX_RXHLDRSNTYPKEY"); //A
		if (holdReasonTypeKey != null) {
			String holdReasonTypeCode = getCodeFromKey(LT_RXHLDRSNTYP, holdReasonTypeKey);
			PrescriptionHoldReasonTypeDisplay prescriptionHoldReasonTypeDisplay = new PrescriptionHoldReasonTypeDisplay();
			prescriptionHoldReasonTypeDisplay.setPrescriptionHoldReasonTypeCode(holdReasonTypeCode);
			prescription.setHoldReasonCode(prescriptionHoldReasonTypeDisplay);
		};
		
		Long packKey = getLong("RX_PACKKEY");
		String GTIN = null;
		
			if (packKey != null) {
			Pack pack = populatePack();
			PrescriptionApi prescriptionApi=new PrescriptionApi();
			HashMap<String,String> packMap=(HashMap<String, String>) prescriptionApi.getCrxDrugAdherance(connection, storeNum, pack.getGTIN());
			if(packMap!=null){
				pack.setStrength(packMap.get("STRENTH"));
				GTIN = pack.getGTIN();
				if(GTIN != null) {
			 	List<FormularyRing> ringList =	FindUtil.populateFormularyRingForPrescribedPack(connection,GTIN,storeNum);
				if(ringList!=null && ringList.size()>0)
				pack.getRing().addAll(ringList); 
				}
			}
			if (pack != null) {
				prescription.setPrescribedPack(pack);
				Long drugKey = getLong("PACK_DRGKEY");
				if (drugKey != null) {
					DrugMedReviewGet drugMedReviewGet = new DrugMedReviewGet();
					// VLAD CRX_REFERENCE_BEAN
					Drug drug = drugMedReviewGet.fetch(connection, drugKey,storeNum, bean,drgPK);
					if (drug != null) {
						pack.setDrug(drug);
					}
				}
			}
		}
			else {
				Pack pack = getPackFrmTXGTIN(connection,storeNum,rxKey);
				if(pack != null) {
					PrescriptionApi prescriptionApi=new PrescriptionApi();
					HashMap<String,String> packMap=(HashMap<String, String>) prescriptionApi.getCrxDrugAdherance(connection, storeNum, pack.getGTIN());
					if(packMap!=null){
						pack.setStrength(packMap.get("STRENTH"));
						pack.setStrengthUOMCode(packMap.get("STRENTHUOM"));
						GTIN = pack.getGTIN();
						List<FormularyRing> ring =	FindUtil.populateFormularyRingForPrescribedPack(connection,GTIN,storeNum);
						pack.getRing().addAll(ring);
						/*	pack.setAutoRefillFlag(packMap.get("AUTOREFILLFLAG"));
						pack.setRefillReminderFlag(packMap.get("REFILLREMINDERFLAG"));
						pack.setPickupReminderFlag(packMap.get("PICKUPREMINDERFLAG"));
						pack.setTrrFlag(packMap.get("TRRFLAG"));*/
					}
					   
						prescription.setPrescribedPack(pack);
						
					
				}
			};
		
		Long prescriberKey = getLong("RX_PRSCBRKEY");
		Long prescriberAddressKey=getLong("RX_PRESCRIBERADDRKEY");
		if(prescriberKey != null)
		{
			PersonRoleViewMedReviewGet prescriberMedReviewGet = new PersonRoleViewMedReviewGet();
			Prescriber prescriber = prescriberMedReviewGet.fetchPrescriber(connection, prescriberKey,rxKey,prescriberAddressKey,sendToODM);
			if(prescriber!=null)
			{
				Long prescriberTypeKey = getLong("RX_PRSCBRTYPKEY");
				if(prescriberTypeKey != null)
				{
					String prescriberTypeCode = getCodeFromKey(LT_PRSCBRTYP, prescriberTypeKey);
					prescriber.setPrescriberTypeCode(prescriberTypeCode);//A
				}
				prescription.setPrescriber(prescriber);
			}
		}
		
		/*	if (isPatientRequired) {
			Long patientKey = getLong("RX_PTNTKEY");
			if (patientKey != null) {
				PatientViewGet patientViewGet = new PatientViewGet();
				Patient patient = patientViewGet.fetch(connection, patientKey);
				prescription.setPatient(patient);
				
			}
		};

				Long responsiblePersonKey = getLong("XXXX");
//		if(responsiblePersonKey != null)
//		{
//			PersonRoleGet responsiblePersonGet = new PersonRoleGet(connection);
//			ResponsiblePerson responsiblePerson = responsiblePersonGet.fetchResponsiblePerson(responsiblePersonKey);
//			prescription.setResponsiblePerson(responsiblePerson);
//		}
		
		Long supervisorKey = getLong("RX_SPRVSRKEY");
		if(supervisorKey != null)
		{
			PersonRoleViewGet supervisorGet = new PersonRoleViewGet();
			Supervisor supervisor = supervisorGet.fetchSupervisor(connection, supervisorKey);
			prescription.setSupervisor(supervisor);
		}
		*/
		Long linkedRxKey = getLong("RX_LINKEDRXKEY");
		PrescriptionMedReviewGet prescriptionMedReviewGet = new PrescriptionMedReviewGet();
		if(linkedRxKey!=null)
		{
			Prescription linkedRx = prescriptionMedReviewGet.populateLinkedRx(connection, linkedRxKey);
			prescription.setLinkedrx(linkedRx);
			
			/*List<Dispense> dispense=dispenseGet.fetchLinkedRXDispList(connection, linkedRxKey) ;
			if(dispense != null && dispense.size()>0){
				linkedRx.getDispense().addAll(dispense);	
		    }
		
			SubscriptionAPI subscriptionAPI=new SubscriptionAPI();
			subscriptionAPI.SubscribeLinkedRx(connection, rxKey, linkedRxKey);
			subscriptionAPI.SubscribeLinkedRx(connection, linkedRxKey,rxKey);*/
			
		} 
		
		//CDR-CR67 ForwardLinking
		Prescription forwardRx=prescriptionMedReviewGet.getForwardRx(connection, rxKey,storeNum);
		if(forwardRx!=null){
			prescription.setForwardRx(forwardRx);
		}
		/*Long locationKey = getLong("RX_SVCLOCKEY");			
		if(locationKey != null)
		{
			LocationGet locationGet = new LocationGet();
	//		Location location = locationGet.fetch(connection, locationKey);
			prescription.setServiceLocation(location);
		}*/
		
		/*Long prescriptionKey = getLong("RX_RXKEY");
		NoteGet noteGet = new NoteGet("RXNT", "RXKEY", true);
	//	List<Note> notes = noteGet.fetch(connection, prescriptionKey);
		if(notes!=null && notes.size() > 0)
			prescription.getNote().addAll(notes);*/
		
	};
	

	//TE97 added Linked Rx Dispense end
	
	private Prescription populateByRxNum(Connection connection, String storeNumber, String prescriptionNumber,boolean sendToODM,CrxReferenceBean bean) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {

		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PrescriptionMedReviewGet.populateByRxNum. PrescriptionNumber : " + prescriptionNumber + ", StoreNumber : " + storeNumber);}
		queryBasicSQL = TableCacheSingleton.getResource("PrescriptionMedReviewGet_QueryBasicSQL.sql");    
		Prescription prescription = null;
		preparedStatement = connection.prepareStatement(queryByRxNumSQL);
		preparedStatement.setString(1, storeNumber);
		preparedStatement.setString(2, prescriptionNumber);

		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PrescriptionMedReviewGet.queryByRxNumSQL. PrescriptionNumber :" + prescriptionNumber + ", StoreNumber : " + storeNumber);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isInfoEnabled()) {logger.debug("EndExecuteQuery : PrescriptionMedReviewGet.queryByRxNumSQL. PrescriptionNumber :" + prescriptionNumber + ", StoreNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}

		if (resultSet.next()) {
			prescription = scanBase();
			populateRxStatData(connection, prescription);
			scanChildren(connection, prescription, true,sendToODM,bean);
		}
		
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PrescriptionGet.populateByRxNum. PrescriptionNumber : " + prescriptionNumber + ", StoreNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return prescription;
	}

	private Prescription populateByRxKey(Connection connection, Long prescriptionKey,boolean sendToODM,CrxReferenceBean bean) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PrescriptionGet.populateByRxKey. PrescriptionKey : " + prescriptionKey );}

		Prescription prescription = null;
		preparedStatement = connection.prepareStatement(queryByRxKeySQL);
		preparedStatement.setLong(1, prescriptionKey);

		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PrescriptionGet.queryByRxKeySQL. PrescriptionKey :" + prescriptionKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : PrescriptionGet.queryByRxKeySQL. PrescriptionKey :" + prescriptionKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}

		if (resultSet.next()) {
			prescription = scanBase();
			populateRxStatData(connection, prescription);
			scanChildren(connection, prescription, true,sendToODM,bean);
		}
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PrescriptionGet.populateByRxKey. PrescriptionKey : " + prescriptionKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return prescription;
	}

	private List<Prescription> populateByPatientKey(Connection connection, Long patientKey,boolean sendToODM,String storeNumber) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PrescriptionGet.populateByPatientKey. PatientKey : " + patientKey );}
		
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		preparedStatement = connection.prepareStatement(queryByPatientKeySQL);
		preparedStatement.setLong(1, patientKey);

		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : PrescriptionGet.populateByPatientKey. PatientKey : " + patientKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : PrescriptionGet.populateByPatientKey. PatientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		// VLAD CRX_REFERENCE_BEAN
		CrxReferenceBean bean = getCrxRegerenceBean(connection, storeNumber, patientKey);
		while (resultSet.next()) {
			Prescription prescription = scanBase();
			populateRxStatData(connection, prescription);
			scanChildren(connection, prescription, false,sendToODM,bean);
			prescriptionList.add(prescription);
		}
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PrescriptionGet.populateByPatientKey. PatientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return prescriptionList;
	}
	public Pack getPackFrmTXGTIN(Connection connection, String storeNumber,Long rxkey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException  {
		
		Pack packtx = null;
		PreparedStatement	ps=null;
		ResultSet rs=null;
		try {
		if (storeNumber!=null && rxkey != null) {
			
			ps = connection.prepareStatement(GETGTINFROMTXSQL);
			ps.setLong(1, rxkey);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				packtx = new Pack();
			//	packtx.setAlternativepacksize(rs.getInt("PACK_ALTRNTVPACKSZ"));
			//	packtx.setAlternativepacksizeunitofmeasure(rs.getString("PACK_ALTRNTVPACKSZUOM"));
				packtx.setStrength(rs.getString("PACK_STRNGTH"));//TE97_024 //A
				packtx.setGTIN(rs.getString("PACK_GTIN")) ; //A
				
			//	packtx.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISACTFLG" )));
			//	packtx.setConsumerId(rs.getString("PACK_CNSMRID")) ;
			//	packtx.setIsCurrentFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISCRNTFLG"))) ;
			//	packtx.setProducerId(rs.getString("PACK_PRDCRID")) ;
			//	packtx.setManufacturerDiscontinuedDate(CommonUtil.getXMLGregorianCalendar(rs.getDate("PACK_MFCTRDISCNTDDT") , true));
				packtx.setPackSize(rs.getBigDecimal("PACK_PACKSZ") ) ;//A
			//	packtx.setMasterid(rs.getString("PACK_MSTRID")) ;
				Long packSizeUOMTypeKey = rs.getLong("PACK_PACKSZUOMTYPKEY"); //A
				if(packSizeUOMTypeKey != null)
				{
			        String packSizeUOMTypeCode = getCodeFromKey(LT_PACKSZUOMTYP, packSizeUOMTypeKey);
			        if (packSizeUOMTypeCode != null)
			        {
			        	PackSizeUoMTypeDisplay packSizeUoMTypeDisplay = new PackSizeUoMTypeDisplay();
			        	packSizeUoMTypeDisplay.setPackSizeUoMTypeCode(packSizeUOMTypeCode);
			        	packtx.setPackSizeUOMCode(packSizeUoMTypeDisplay);			        	
			        }
				}
				
				Long strengthUOMTypeKey = rs.getLong("PACK_STRNGTHUOMTYPKEY"); //A
				if(strengthUOMTypeKey!=null)
				{
			        String strengthUOMTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, strengthUOMTypeKey);
			        packtx.setStrengthUOMCode(strengthUOMTypeCode);
				}
				
			/*	Long drugKey = rs.getLong("PACK_DRGKEY");
				if (drugKey != null) {
					DrugGet drugGet = new DrugGet();
					Drug drug = drugGet.fetch(connection, drugKey);
					if (drug != null) {
						packtx.setDrug(drug);
					}
				}*/
			
		}
			
			
		}
		}catch(Exception ex) {
			throw new SQLException(ex);
		}finally{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		
		return packtx;
	}
	
	// VLAD CRX_REFERENCE_BEAN Started
		public CrxReferenceBean getCrxRegerenceBean(Connection connection, String storenum, Long patientKey) throws SQLException {
			   CrxReferenceBean bean = null;
			   Long provKey = 0L;
			   PreparedStatement ps = null;
		       ResultSet rs = null;
		       try {
			       ps = connection.prepareStatement("select provkey from Prov2Store_VW where storenum = ?");
			       ps.setString(1, storenum);
			       rs = ps.executeQuery();
			       if (rs.next()) { provKey = rs.getLong("PROVKEY"); }
		           if (provKey != null && provKey > 0L) {
					   bean = new CrxReferenceBean(connection, patientKey, provKey);
				   }
			   } catch (SQLException e) {
				     throw e;
		       } finally {
		    	    CommonUtil.closePreparedStatementResultSet(ps, rs);
		    	  
		   		
			   }
		       return bean;
		}
		// VLAD CRX_REFERENCE_BEAN Ended
	
}
