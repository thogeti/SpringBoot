package ca.sdm.cdr.jdbc.api.prescription.query;


import java.io.File;
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
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.dispense.query.QueryDispense;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.util.CdrQueryMgr;
import ca.sdm.cdr.jdbc.query.api.DispenseGet;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
//import ca.shoppersdrugmart.rxhb.ehealth.DiscontinuedReason;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionHoldReasonTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionResumeReason;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import ca.shoppersdrugmart.rxhb.ehealth.Source;
import ca.shoppersdrugmart.rxhb.ehealth.SubstitutionInitiator;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.TreatmentType;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;



public class QueryPrescription extends JDBCConnection {
	
	private static Logger logger = LogManager.getLogger(QueryPrescription.class);
	
	private File   queryOutputFolder = null;
	private String queryOutputFileName = null;
	public String jndiVersion=null;

	public QueryPrescription(Connection conn) {
		super(conn);
		jndiVersion= TableCacheSingleton.JNDI_VERSION;
	} 
	
	public QueryPrescription(Connection conn,File queryOutputFolder, String queryOutputFileName) {
		this(conn);
		this.queryOutputFolder = queryOutputFolder;
		this.queryOutputFileName = queryOutputFileName;
	}	
	
	
	
	private String prepareSQL(String whereClause) throws CodeNotFoundFromTableCacheException, SQLException, IOException {
		StringBuffer sql = new StringBuffer("");
		CdrQueryMgr cdrQueryMgr = null;
		cdrQueryMgr = new CdrQueryMgr("Rx-select.sql");
		String tableAliasList[][] = cdrQueryMgr.getTableAliasArray();
		//TableColumnSingleton columnCache = TableColumnSingleton.getInstance(conn);
		TableColumnSingleton columnCache = TableColumnSingleton.getInstance();
		String selectString = columnCache.createSQLSelectFromColumns(connection, tableAliasList);
		sql.append("\n\n-------------------- \n");
		sql.append("\n\nSELECT DISTINCT \n" + selectString);
		sql.append("\n\n" + cdrQueryMgr.getFromClause());
		sql.append("\n\n" + whereClause + "\n\n");
		sql.append("\n" + cdrQueryMgr.getOrderByClause());
		if (getQueryFile()!=null) {
			CdrQueryMgr.appendToFile(getQueryFile(), sql.toString());
		}
		return sql.toString();
	}	
	
	
	/**
	 * 
	 * @param patientKey
	 * @return
	 * @throws SQLException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws PatientNotFoundException 
	 * @throws NamingException 
	 * @throws CDRInternalException 
	 * @throws CDRDataException 
	 */
	public Prescription[] getPrescriptionsByPatient(String consumerId, String storeNum, boolean populatelinkedPrescriptions) throws SQLException, IOException, ParseException, DatatypeConfigurationException, PatientNotFoundException, NamingException, CDRInternalException {
		Map<Long, Prescription> rxKeyMap = null;
		if (consumerId==null)
			return null;
		String whereClause = " where PtntPrsn.CNSMRID = ? and Ptnt.STORENUM = ?";
		String selectSql = prepareSQL(whereClause);
		PreparedStatement ps = null;
		ResultSet rs = null;
		Prescription[] rxArray ;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setString(1, consumerId);
		ps.setString(2, storeNum);
		rs = ps.executeQuery();
		Map<String, String> relatedResultsMap = new HashMap<String, String>();
		rxKeyMap = populateRxKeyMap(rs, "RX", relatedResultsMap);
		 rxArray = toArray(rxKeyMap);
		if (rxArray==null || rxArray.length < 1) {
			// Check for matching Patient
			Long patientKey = FindUtil.findPatientKey(connection, consumerId, storeNum);
			if (patientKey==null) {
				throw new PatientNotFoundException(storeNum,consumerId);
			}
		}
		}
		finally {
	       CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return rxArray;
	}	

	
	
	/**
	 * 
	 * @param patientKey
	 * @return
	 * @throws SQLException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRInternalException 
	 * @throws CDRDataException 
	 */
	public Prescription[] getPrescriptionsByPatientKey(Long patientKey, boolean populatelinkedPrescriptions) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Map<Long, Prescription> rxKeyMap = null;
		if (patientKey==null)
			return null;
		String selectSql = prepareSQL("WHERE Rx.PTNTKEY = ?");
		PreparedStatement ps =  null;
		ResultSet rs = null;
		Prescription[] rxArray;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, patientKey);
		rs = ps.executeQuery();
		Map<String, String> relatedResultsMap = new HashMap<String, String>();
		rxKeyMap = populateRxKeyMap(rs, "RX", relatedResultsMap);
		rxArray = toArray(rxKeyMap);
		}
		finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}		
		return rxArray;
	}	
	

	
	/**
	 * 
	 * @param rxNum
	 * @return
	 * @throws SQLException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRInternalException 
	 * @throws CDRDataException 
	 */
	public Prescription getPrescriptionByRxNum(String rxNum, String storeNum, boolean populateLinkedRx) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Prescription prescription = null;
		String selectSql = prepareSQL("WHERE UPPER(Rx.RXNUM)=UPPER(?) AND Rx.StoreNum=?");
		if (logger.isTraceEnabled())  {logger.trace("selectSql : \n" + selectSql);}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setString(1, rxNum);
		ps.setString(2, storeNum);
		rs = ps.executeQuery();
		Map<String, String> relatedResultsMap = new HashMap<String, String>();
		Map<Long, Prescription> rxKeyMap = populateRxKeyMap(rs, "RX", relatedResultsMap);
		List<Prescription> rxList = toList(rxKeyMap);
		prescription = (rxList!=null && rxList.size()>0) ? rxList.get(0) : null;
		if (prescription==null)
			return null;
		if (populateLinkedRx) {
			String linkedRxKeyStr = relatedResultsMap.get("LINKEDRXKEY");
			Long linkedRxKey = null;
			if (linkedRxKeyStr!=null) try {
				linkedRxKey = new Long(linkedRxKeyStr);
			} catch (Exception parseEx) {
				
			}	
			if (linkedRxKey!=null && linkedRxKey.longValue()>0) {
				Prescription linkedRxData = getPrescriptionByRxKey(new Long(linkedRxKey));	
				prescription.setLinkedrx(linkedRxData);
			}
		}
		}
		finally {
			 CommonUtil.closePreparedStatementResultSet(ps, rs); 
		}		
		return prescription;
	}
	
	
	
	
	/**
	 * 
	 * @param rxKey
	 * @return
	 * @throws SQLException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRInternalException 
	 * @throws CDRDataException 
	 */
	public Prescription getPrescriptionByRxKey(Long rxKey) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Prescription prescription = null;
		if (rxKey==null)
			return null;
		String selectSql = prepareSQL("WHERE Rx.RXKEY = ?");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, rxKey);
		rs = ps.executeQuery();
		Map<String, String> relatedResultsMap = new HashMap<String, String>();
		Map<Long, Prescription> rxKeyMap = populateRxKeyMap(rs, "RX", relatedResultsMap);
		List<Prescription> rxList = toList(rxKeyMap);
		prescription = (rxList!=null && rxList.size()>0) ? rxList.get(0) : null;
		}finally {
		 CommonUtil.closePreparedStatementResultSet(ps, rs);
		}		
		return prescription;
	}	

	
	/**
	 * 
	 * @param rxConsumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 * @throws CDRInternalException 
	 */
	public Prescription getPrescriptionByConsumerId(String rxConsumerId, String storeNum) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Prescription prescription = null;
		if (rxConsumerId==null || storeNum==null)
			return null;
		String selectSql = prepareSQL("WHERE UPPER(RX.CnsmrId) = UPPER(?) and UPPER(RX.StoreNum) = UPPER(?)");
		PreparedStatement ps = connection.prepareStatement(selectSql);
		CommonUtil.setPsStringParam(ps, 1, rxConsumerId);
		CommonUtil.setPsStringParam(ps, 2, storeNum);
		ResultSet rs = ps.executeQuery();
		Map<String, String> relatedResultsMap = new HashMap<String, String>();
		Map<Long, Prescription> rxKeyMap = populateRxKeyMap(rs, "RX", relatedResultsMap);
		List<Prescription> rxList = toList(rxKeyMap);
		prescription = (rxList!=null && rxList.size()>0) ? rxList.get(0) : null;
		try {
			rs.close();
			ps.close();
		} catch (Exception ex) {
		}		
		return prescription;
	}
	
	
	/**
	 * 
	 * @param rs
	 * @param rxAlias
	 * @param relatedResultsMap
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws CDRInternalException 
	 */
	private Map<Long, Prescription> populateRxKeyMap(ResultSet rs, String rxAlias, Map<String, String> relatedResultsMap) throws SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException, CDRInternalException  {
		Map<Long, Prescription> prescriptionKeyMap = new HashMap<Long, Prescription>();
		Map<Long, Long> dispenseKeyMap = new HashMap<Long, Long>();
		Prescription prescription = null;
		
		int rowNo=0, maxRows = 1000;
		
		long currRxKey = -1;       boolean newRx = false;
		long currNoteKey = -1;     boolean newNote = false;	
		
		while (rs.next() && rowNo < maxRows) {
			rowNo++;
			Long rxKey = ResultSetWrapper.getLong(rs, rxAlias, "RXKEY");
			if (newRx = (rxKey.longValue() != currRxKey)) {
				currRxKey = rxKey.longValue();
				prescription = new Prescription();
			
				prescription = PopulateJAXB.populatePrescription(rs, rxAlias);

				populateRxLookupValues(rs, rxAlias, prescription);
				
				//Long rxStatKey = PopulateJAXB.getKeyVal(rs, "RXSTAT", "RXSTATKEY");
				RxFillStatus rxFillStatus = null; 
				rxFillStatus = PopulateJAXB.populatePrescriptionFillStatus(rs, "RXSTAT");
				prescription.setFillStatusCode(rxFillStatus);

				XMLGregorianCalendar fillStatusEffectiveDate = PopulateJAXB.populateFillStatusEffectiveDate(rs, "RXSTAT");
				prescription.setFillStatusEffectiveDate(fillStatusEffectiveDate);
				
				//Long serviceLocationKey = ResultSetWrapper.getLong(rs, "RXLOC", "LOCKEY");
				Location svcLocation = null;
				svcLocation = PopulateJAXB.populateLocation(rs, "RXLOC");
				prescription.setServiceLocation(svcLocation);
				
				//Long patientKey = ResultSetWrapper.getLong(rs, "PTNT", "PTNTKEY");
				Patient patient = null;
				patient = PopulateJAXB.populatePatient(rs, "PTNT" /*, "PtntPref" */);
				prescription.setPatient(patient);
				
				//String rxrKey = PopulateJAXB.getStringVal(rs, "Prscbr", "PRSCBRKEY");
				Prescriber prescriber = null;
				prescriber = PopulateJAXB.populatePrescriber(rs, "Prscbr", "PrscbrPrsn");
				prescription.setPrescriber(prescriber);			
				
				Drug drug = null;
				drug = PopulateJAXB.populateDrug(rs, "RxDrg", "RxDrgMfctr", "RxDrgMlcl", "rxDsgFrm");
				prescription.setPrescribedDrug(drug);
				
				Pack pack = null;
				pack = PopulateJAXB.populatePack(rs, "RxPck", "RxPckDrg", "RxPckDrgMfctr", "RxPckDrgMlcl", "pckDsgFrm");
				if( pack  != null )
				{
					prescription.setPrescribedPack(pack);
				}
				
				Compound compound = null;
				compound = PopulateJAXB.populateCompound(rs, "RxCmpnd");
				if( compound != null )
				{
					prescription.setPrescribedCompound(compound);
				}
				//Long recorderKey = ResultSetWrapper.getLong(rs, "RcrdrPrsn", "RCRDRKEY");
				Recorder recorder = null;
				recorder = PopulateJAXB.populateRecorder(rs, "RcrdrPrsn", "Rcrdr");
				prescription.setRecorder(recorder);			
	
				//Long holdReasonTypeKey = ResultSetWrapper.getLong(rs, rxAlias, "RXHLDRSNTYPKEY");
				PrescriptionHoldReasonTypeDisplay prescriptionHoldReasonTypeDisplay  = new PrescriptionHoldReasonTypeDisplay();
				prescription.setHoldReasonCode(prescriptionHoldReasonTypeDisplay);
				
				//Long personKey = ResultSetWrapper.getLong(rs, "Rspnsbl", "PRSNKEY"); // ?
//				ResponsiblePerson responsiblePerson = null;
//				responsiblePerson = PopulateJAXB.populateResponsiblePerson(rs, "RspnsblPrsn");
//				prescription.setResponsiblePerson(responsiblePerson);
				
				Long linkedRxKey = ResultSetWrapper.getLong(rs, rxAlias, "LINKEDRXKEY");
				if (linkedRxKey!=null && relatedResultsMap!=null) {
					relatedResultsMap.put("LINKEDRXKEY", linkedRxKey.toString());
				}
				
				//Long supervisorKey = ResultSetWrapper.getLong(rs, rxAlias, "SPRVSRKEY");
				Supervisor supervisor = null;
				supervisor = PopulateJAXB.populateSupervisor(rs, "SprvsrPrsn", "Sprvsr");
				prescription.setSupervisor(supervisor);
				
				//keepRelatedInfo(prescription, rs, rxAlias, relatedResultsMap);
				
				Long dispenseKey = ResultSetWrapper.getLong(rs, "RXDISP", "RXKEY");
				dispenseKeyMap.put(currRxKey, dispenseKey);
				
				prescriptionKeyMap.put(currRxKey, prescription);
			} //newRx
		
			Long noteKey = ResultSetWrapper.getLong(rs, "RXNT", "RXNTKEY");
			if (newNote=(noteKey!=null && noteKey.longValue()!=currNoteKey)) {
				currNoteKey = noteKey.longValue();
				Note noteData = null;
				noteData = PopulateJAXB.populateNote(rs, "RXNT", "RXNTKEY");
				if (noteData != null)
				prescription.getNote().add(noteData);
			}

		}
		
		//separate SQL call to get Dispense instances via queryDispense.getDispenseList(RxKey)
//		for (Long iRxKey : prescriptionKeyMap.keySet()) {
//			Prescription iPrescription = prescriptionKeyMap.get(iRxKey);
//			Integer iRxNum = iPrescription.getPrescriptionNumber();
//			logger.info("Calling getDispenseList for RxNum: " + iRxNum + ", RxKey: " + iRxKey);
//
//			if (iRxKey!=null && iRxKey.longValue()>0) {
//				QueryDispense qDispense = new QueryDispense(connection);
//				List<Dispense> dispenseList = qDispense.getDispenseList(iRxKey);
//				iPrescription.getDispense().clear();
//				iPrescription.getDispense().addAll(dispenseList);
//			}
//			
//		}

		for (Long iRxKey : prescriptionKeyMap.keySet()) {
		Prescription iPrescription = prescriptionKeyMap.get(iRxKey);
		Integer iRxNum = iPrescription.getPrescriptionNumber();
		if (logger.isInfoEnabled())  {logger.info("Calling getDispenseList for RxNum: " + iRxNum + ", RxKey: " + iRxKey);}

		if (iRxKey!=null && iRxKey.longValue()>0) {
			DispenseGet dispenseGet = new DispenseGet();
			List<Dispense> dispenseList = dispenseGet.fetchList(connection, iRxKey,0,  null,null,false);
			iPrescription.getDispense().clear();
			iPrescription.getDispense().addAll(dispenseList);
		}
		
	}
		
		return prescriptionKeyMap;
	}
	
	
	
	private void populateRxLookupValues(ResultSet rs, String tableAlias, Prescription prescription) throws CodeNotFoundFromTableCacheException, NamingException, SQLException, IOException {
		TableCacheSingleton tableCache = TableCacheSingleton.getInstance(jndiVersion);

		String renewalReasonCode = null;
		Long renewalReasonTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "RNWLRSNTYPKEY");		
		if (renewalReasonTypeKey!=null && renewalReasonTypeKey.longValue()>0) 
			renewalReasonCode = tableCache.getCodeFromKey(LT_RNWLRSNTYP, renewalReasonTypeKey);
		prescription.setRenewalReasonCode(renewalReasonCode);		
		
		String noSubReasonTypeCode = null;
		Long noSubReasonTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "NOSUBRSNTYPKEY");
		if (noSubReasonTypeKey!=null && noSubReasonTypeKey.longValue()>0) 
			noSubReasonTypeCode = tableCache.getCodeFromKey(LT_NOSUBRSNTYP, noSubReasonTypeKey);
		prescription.setNoSubstitutionReason(noSubReasonTypeCode);		
		
		String sigCode = rs.getString(tableAlias + "_SIGCD");
		prescription.setSIGCode(sigCode);

		String SubInitiatorTypeCode = null;
		Long SubInitiatorTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "SUBINITRTYPKEY");
		if (SubInitiatorTypeKey!=null && SubInitiatorTypeKey.longValue()>0)
			SubInitiatorTypeCode = tableCache.getCodeFromKey(LT_SUBINITRTYP, SubInitiatorTypeKey);
		SubstitutionInitiator substitutionInitiator = (SubInitiatorTypeCode!=null) ? SubstitutionInitiator.fromValue(SubInitiatorTypeCode) : null;
		prescription.setSubstitutionInitiator(substitutionInitiator);
		
		String treatmentTypeCode = null;
		Long treatmentTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "TRTMNTTYPKEY");
		if (treatmentTypeKey!=null && treatmentTypeKey.longValue() > 0)
			treatmentTypeCode = tableCache.getCodeFromKey(LT_TRTMNTTYP, treatmentTypeKey);
		TreatmentType treatmentType = (treatmentTypeCode!=null) ? TreatmentType.fromValue(treatmentTypeCode) : null;
		prescription.setTreatmentType(treatmentType);		
		
		String rxResumeReasonTypeCd = null;
		Long rxResumeReasonTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "RXRSMRSNTYPKEY");
		if (rxResumeReasonTypeKey!=null && rxResumeReasonTypeKey.longValue()>0) 
			rxResumeReasonTypeCd = tableCache.getCodeFromKey(LT_RXRSMRSNTYP, rxResumeReasonTypeKey);
		PrescriptionResumeReason prescriptionResumeReason = (rxResumeReasonTypeCd!=null) ? PrescriptionResumeReason.fromValue(rxResumeReasonTypeCd) : null;
		prescription.setResumeReasonCode(prescriptionResumeReason);

		String rxSourceTypeCode = null;
		Long rxSourceTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "RXSRCETYPKEY");
		if (rxSourceTypeKey!=null && rxSourceTypeKey.longValue() > 0)
			rxSourceTypeCode = tableCache.getCodeFromKey(LT_RXSRCETYP, rxSourceTypeKey);
		Source source = (rxSourceTypeCode!=null) ? Source.fromValue(rxSourceTypeCode) : null;
		prescription.setSource(source);
		
		/*
		String discReasonTypeCode = null;
		Long discReasonTypeKey = ResultSetWrapper.getLong(rs, tableAlias, "RxDiscntdRsnTypKey");
		if (discReasonTypeKey!=null && discReasonTypeKey.longValue() > 0)
			discReasonTypeCode = tableCache.getCodeFromKey(LT_DISCNTDRSNTYP, discReasonTypeKey);
		DiscontinuedReason discontinuedReason = (discReasonTypeCode!=null) ? DiscontinuedReason.fromValue(discReasonTypeCode) : null;
		prescription.setDiscontinuedReasonCode(discontinuedReason);
		*/
	}
	
	

	/*
	 * Keep primary key values in case we want to log them
	 * 
	 * @param prescription
	 * @param rs
	 * @param rxAlias
	 * @param relatedResultsMap
	 *
	private void keepRelatedInfo(Prescription prescription, ResultSet rs, String rxAlias, Map<String, String> relatedResultsMap) {
		Long strngThUomTypeKey = ResultSetWrapper.getLong(rs, rxAlias, "STRNGTHUOMTYPKEY");
		Long rxRflsReasonTypeKey = ResultSetWrapper.getLong(rs, rxAlias, "RXRFSLRSNTYPKEY");			
		Long compundKey = ResultSetWrapper.getLong(rs, rxAlias, "CMPNDKEY");
		Long drugKey = ResultSetWrapper.getLong(rs, rxAlias, "DRGKEY");
		Long packKey = ResultSetWrapper.getLong(rs, rxAlias, "PACKKEY");
		String storeNum = PopulateJAXB.getStringVal(rs, rxAlias, "STORENUM");
		boolean batchFlag = PopulateJAXB.getBooleanVal(rs, rxAlias, "BATCHFLG");
		Long rxSubDeclinedReasonTypeKey = ResultSetWrapper.getLong(rs, rxAlias, "TXSUBDCLNDRSNTYPKEY");
		Long rxrTypeKey = ResultSetWrapper.getLong(rs, rxAlias, "PRSCBRTYPKEY");
	}
	*/
	
	
	
	private static List<Prescription> toList(Map<Long, Prescription> rxKeyMap) {
		List<Prescription> rxList = new ArrayList<Prescription>();
		for (Prescription iRx : rxKeyMap.values()) {
			rxList.add(iRx);
		}
		return rxList;
	}
	
	
	private static Prescription[] toArray(Map<Long, Prescription> rxKeyMap) {
		List<Prescription> rxList = toList(rxKeyMap);
		Prescription[] rxArray = new Prescription[rxList.size()];
		rxArray = rxList.toArray(rxArray);
		return rxArray;
	}
	
	
	private File getQueryFile() {
		if (queryOutputFolder==null || queryOutputFileName==null || queryOutputFileName.trim().length()<1)
			return null;
		File queryOutFile = null;
		try {
			queryOutputFolder.mkdirs();
			queryOutFile = new File(this.queryOutputFolder, this.queryOutputFileName); 
		} catch (Exception exc) {
			return null;
		}
		return queryOutFile;
	}
	

	
}
