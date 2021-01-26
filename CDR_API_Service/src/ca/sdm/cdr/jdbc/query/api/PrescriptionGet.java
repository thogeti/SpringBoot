package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NOSUBRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PACKSZUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSCBRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSNRLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RNWLRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXFILLSTATTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXHLDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXRSMRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXSRCETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_SUBINITRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TRTMNTTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.DispenseNotFoundException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.upsert.api.PrescriptionApi;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.ForwardRx;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.PackSizeUoMTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriberType;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionHoldReason;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionHoldReasonTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionResumeReason;
import ca.shoppersdrugmart.rxhb.ehealth.ReauthFaxFlag;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import ca.shoppersdrugmart.rxhb.ehealth.Source;
import ca.shoppersdrugmart.rxhb.ehealth.SubstitutionInitiator;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.TreatmentType;


public class PrescriptionGet extends CDRGet {
	 private static Logger logger = LogManager.getLogger(PrescriptionGet.class);
	 
	 private Prescription mainPrescription = null;
     private Long mainRxKey = 0L;  
     private Long linkedRxKey = 0L;
     private Long forwardRxKey = 0L;
       

	 private Map <Integer, Long[]> normalRxMap = new HashMap<Integer, Long[]>();        // rxNum, [RxKey, linkedRxKey]
	 private Map <Long, Long>      linkedRxMap = new HashMap<Long, Long>();             // linkedRxKey,  RxKey
	                 
	 private Map <Long, Prescription> rxkeyPrescriptionMap = new HashMap<Long, Prescription>();  // RxKey, Prescription
	 
	 // 2020-10-15_Dispences Started
	 private Set<String> primaryRxDispensesSet = new HashSet<String>();   // (rxNum || txNum)
	 private Set<String> linkedRxDispensesSet = new HashSet<String>();    // (rxNum || txNum)
	 // 2020-10-15_Dispences Ended
	 private static String queryBasicSQL;
	 private final static String GETGTINFROMTXSQL  ="select t.txnnum,t.packkey,p.drgkey,p.gtin,p.PACKID PACK_PACKID, p.ALTRNTVPACKSZ PACK_ALTRNTVPACKSZ, p.ALTRNTVPACKSZUOM PACK_ALTRNTVPACKSZUOM,"
	 		 + " p.STRNGTH PACK_STRNGTH, p.GTIN PACK_GTIN,p.ISACTFLG PACK_ISACTFLG, p.CNSMRID PACK_CNSMRID, p.ISCRNTFLG PACK_ISCRNTFLG, p.PRDCRID PACK_PRDCRID, p.MFCTRDISCNTDDT PACK_MFCTRDISCNTDDT," 
			 + " p.PACKSZ PACK_PACKSZ, p.MSTRID PACK_MSTRID, p.PACKKEY PACK_PACKKEY, p.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY, p.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, " 
			 + " p.DRGKEY PACK_DRGKEY  from tx   t,   pack p   where t.rxkey = ?  and t.packkey is not null  and t.packkey = p.packkey  "
			 + " order by t.txnnum desc fetch first 1 rows only ";

		
		
	public Map<String, Object> fetchRx(Connection connection, Long rxKey, String storeNumber, String prescriptionNumber, boolean sendToODM, boolean isFilterActive) throws CDRInternalException, ParseException, SQLException, DatatypeConfigurationException, NamingException, IOException {
		try {
			PreparedStatement preparedStatement = null;
			if (rxKey != null && rxKey > 0L) {
				String sql =
						   "select r1.rxkey        MAINRXKEY,   "
		                 + "       r1.linkedrxkey  LINKEDRXKEY, "
		                 + "       r2.rxkey        FORWARDRXKEY "
		                 + "  from rx r1                        "
		                 + "           left outer join rx r2    "
		                 + "                on r2.linkedrxkey is not null and r1.rxkey = r2.linkedrxkey "
		                 + "  where r1.rxkey = ?                ";
									
				    preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setLong(1, rxKey);
			} else {
				String sql =
						   "select r1.rxkey        MAINRXKEY,   "
		                 + "       r1.linkedrxkey  LINKEDRXKEY, "
		                 + "       r2.rxkey        FORWARDRXKEY "
		                 + "  from rx r1                        "
		                 + "           left outer join rx r2    "
		                 + "                on r2.linkedrxkey is not null and r1.rxkey = r2.linkedrxkey "
		                 + "  where r1.rxnum = ?                "
		                 + "    and r1.storenum = ?             ";
									
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, prescriptionNumber);
					preparedStatement.setString(2, storeNumber);
			}
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				mainRxKey = rs.getLong("MAINRXKEY");
				linkedRxKey = rs.getLong("LINKEDRXKEY");
				forwardRxKey = rs.getLong("FORWARDRXKEY");

				linkedRxKey = linkedRxKey != null ? linkedRxKey : 0L;
				forwardRxKey = forwardRxKey != null ? forwardRxKey : 0L;
				
//              2020-10-15_Dispences Started
				Map<String, Object> adheranceArtifacts = new HashMap<String, Object>(); 
				Prescription prescr = populateBy3RxKeys(connection, sendToODM, isFilterActive);
				adheranceArtifacts.put("prescription", prescr);
				adheranceArtifacts.put("primaryRxDispensesSet", primaryRxDispensesSet);
				adheranceArtifacts.put("linkedRxDispensesSet", linkedRxDispensesSet);
				return adheranceArtifacts;
//              2020-10-15_Dispences Ended	
			} else {
				return null;  // TODO: Run TestCase to generate "RxNum not found error"
			}
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
		finally {
			CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
			super.close();
		}

	}
	 

	private Prescription populateBy3RxKeys(Connection connection, boolean sendToODM, boolean isFilterActive) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		logger.debug("populateBy3RxKeys started mainRxKey: " + mainRxKey + " linkedRxKey: " + linkedRxKey + " forwardRxKey: " + forwardRxKey);
		
		queryBasicSQL = TableCacheSingleton.getResource("PrescriptionGet1.sql");
	//	queryBasicSQL = queryBasicSQL.replace("and RX.PTNTKEY = ? and rx.updttimestamp >= (SYSDATE - INTERVAL '3' year)", "");
		queryBasicSQL = queryBasicSQL.replace("and RX.PTNTKEY = ? and to_char(rx.updttimestamp, 'YYYY-MM-DD') >= ?", "");
		Prescription prescription = null;
		ResultSet resultSet = null;
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		Long querytimer = System.currentTimeMillis() ;
		try {
	
		PreparedStatement preparedStatement = connection.prepareStatement(queryBasicSQL);
	 	preparedStatement.setLong(1, mainRxKey);
		preparedStatement.setLong(2, linkedRxKey);
		preparedStatement.setLong(3, forwardRxKey); 
		
		  querytimer = System.currentTimeMillis();
		 resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			prescription = scanBase(resultSet);
			populateRxStatData(prescription, resultSet);
			populatePrescriber(prescription,resultSet,sendToODM);
			scanChildren(connection, prescription, true, sendToODM, resultSet, isFilterActive);
			prescriptionList.add(prescription);
		}
		}finally {
		logger.debug("populateBy3RxKeys ended mainRxKey: " + mainRxKey + " linkedRxKey: " + linkedRxKey + " forwardRxKey: " + forwardRxKey + "  Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;		
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet); //   FIXED: ORA-01000: maximum open cursors exceeded
		}
		
		populateLinkedForwardRx(prescriptionList);
		return mainPrescription;
	}

	
	public Map<String, Object> fetchByPatientId(Connection connection, String storeNumber, String patientId,boolean sendToODM,boolean isFilterActive) throws CDRInternalException, ParseException, SQLException, DatatypeConfigurationException, NamingException, IOException, PatientNotFoundException {
		try {
			
			PatientViewGet patientViewGet = new PatientViewGet();
			Patient patient = patientViewGet.fetch(connection, storeNumber, patientId);
			Long patientKey = patientViewGet.getPatientKey();
			
			if(patientKey ==null)
				throw new PatientNotFoundException(String.valueOf(storeNumber), patientId);
			List<Prescription> prescriptions = populateByPatientKey(connection, patientKey,sendToODM,isFilterActive);
			Iterator<Prescription>  iterator = prescriptions.iterator();
			while (iterator.hasNext()) {
				Prescription current = iterator.next();
				current.setPatient(patient);
			}
			// 2020-10-15_Dispences Started
			Map<String, Object> adheranceArtifacts = new HashMap<String, Object>(); 
			adheranceArtifacts.put("prescriptions", prescriptions);
			adheranceArtifacts.put("primaryRxDispensesSet", primaryRxDispensesSet);
			adheranceArtifacts.put("linkedRxDispensesSet", linkedRxDispensesSet);
			return adheranceArtifacts;
			// 2020-10-15_Dispences Ended
			 
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
		finally {
			super.close();
		}
	}
	

	private List<Prescription> populateByPatientKey(Connection connection, Long patientKey,boolean sendToODM,boolean isFilterActive) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("populateByPatientKey started PatientKey : " + patientKey );}
		
		queryBasicSQL = TableCacheSingleton.getResource("PrescriptionGet1.sql");
		queryBasicSQL = queryBasicSQL.replace("and RX.RXKEY in (?, ?, ?)", "");
		
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		preparedStatement=	connection.prepareStatement(queryBasicSQL);
		preparedStatement.setLong(1, patientKey);

		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    	   LocalDate years3oldDate = LocalDate.now().minusYears(3);
    	   String years3oldDateString = years3oldDate.format(format);
    	   preparedStatement.setString(2,years3oldDateString);
		Long querytimer = System.currentTimeMillis();
		  resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			Prescription prescription = scanBase(resultSet);
			populateRxStatData(prescription, resultSet);
			populatePrescriber(prescription,resultSet,sendToODM); 
			scanChildren(connection, prescription, false,sendToODM,resultSet,isFilterActive);
			prescriptionList.add(prescription);
		}
		}finally {
		if (logger.isInfoEnabled())  {logger.info("populateByPatientKey ended PatientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");	}	
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet); //   FIXED: ORA-01000: maximum open cursors exceeded
		}
		populateLinkedForwardRx(prescriptionList);
		
		return prescriptionList;
	}
	
	
	private void populatePrescriber(Prescription prescription, ResultSet resultSet, boolean sendToODM)
			throws NamingException, IOException {
		Prescriber prescriber = null;
		boolean isActiveFlag;
		String consumerId;
		Long personRoleTypeKey;
		// String producerId;
		prescriber = new Prescriber();
		Person person = new Person();
		Address address = new Address();
		try {
			isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PRSNRL_ISACTFLG"));

			personRoleTypeKey = CommonUtil.getLong("PRSNRL_PRSNRLTYPKEY", resultSet);

			person.setFirstName(resultSet.getString("PRSN_FRSTNM"));
			person.setMiddleName(resultSet.getString("PRSN_MDLNM"));
			person.setLastName(resultSet.getString("PRSN_LSTNM"));
			consumerId = resultSet.getString("PRSN_CNSMRID");
			prescriber.setConsumerId(consumerId);
			prescriber.setIsActiveFlag(isActiveFlag);
			prescriber.setPrscbrKey(resultSet.getLong("RX_PRSCBRKEY"));

			if (sendToODM) {
				populateAddressPrescriberODM(address, resultSet);
				person.getAddress().add(address);
			}
			prescriber.setPerson(person);
			Long prescriberTypeKey = CommonUtil.getLong("RX_PRSCBRTYPKEY", resultSet);
			if (prescriberTypeKey != null) {
				String prescriberTypeCode = getCodeFromKey(LT_PRSCBRTYP, prescriberTypeKey);
				prescriber.setPrescriberTypeCode(PrescriberType.fromValue(prescriberTypeCode));
			}
			prescription.setPrescriber(prescriber);

			prescription.setPrescriber(prescriber);
		} catch (CDRInternalException | SQLException e) {

			e.printStackTrace();
		}

	}



	
	private Address populateAddressPrescriberODM(Address address, ResultSet rs) throws SQLException {
		boolean hasValue = false;
		String value = null;
		
		 value = rs.getString("isMailAddressFlag");
	        if (value != null) {
		        address.setIsMailAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
		        hasValue = true;
	        }
	        value = rs.getString("isReauthEmailFlag");
	        if (value != null) {
		        address.setIsReauthEmailFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
		        hasValue = true;
	        }
	        value = rs.getString("isReauthPhoneFlag");
	        if (value != null) {
		        address.setIsReauthPhoneFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
		        hasValue = true;
	        }
	        value = rs.getString("isReauthFaxFlag");
	        if (value != null && !value.equals("U")) {
		        address.setIsReauthFaxFlag(ReauthFaxFlag.fromValue(CommonUtil.convertTrueFlaseReauthFlagString(value))); 
		        hasValue = true;
	        }
	        value = rs.getString("isReauthVisitFlag");
	        if (value != null) {
		        address.setIsReauthVisitFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
		        hasValue = true;
	        }
	        if (hasValue)
				return address;
			else
				return null;
		 
	}


	private Prescription scanBase(ResultSet rs) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException {
		Prescription prescription = new Prescription();
		prescription.setUpdateTimestamp(CommonUtil.getCalendar("RX_UPDTTIMESTAMP",rs));
		prescription.setAdministrationPeriodStartDate(CommonUtil.getCalendar("RX_ADMINSTARTDATE",rs));
		prescription.setAdministrationPeriodEndDate(CommonUtil.getCalendar("RX_ADMINSTOPDATE",rs));
		prescription.setIsAuthoritative(CommonUtil.getBoolean("RX_ISATHRTV",rs));
		prescription.setConsumerId(rs.getString("RX_CNSMRID"));
		prescription.setProducerId(rs.getString("RX_PRDCRID"));
		prescription.setQuantityDispensed(CommonUtil.getBigDecimal("RX_QTYTXD",rs));
		prescription.setRemainingQuantity(CommonUtil.getBigDecimal("RX_RMNGQTY",rs));
		prescription.setPrescriptionNumber(CommonUtil.getInt("RX_RXNUM",rs));
		
		prescription.setRxKey(CommonUtil.getLong("RX_RXKEY",rs));
	//	prescription.setPrscbrKey(CommonUtil.getLong("RX_PRSCBRKEY",rs));
		prescription.setRefillsRemainingCount(CommonUtil.getInt("RX_RFLSRMNGCNT",rs));
		prescription.setTotalQuantityAuthorized(CommonUtil.getBigDecimal("RX_TOTALQTYATHRZD",rs));
		prescription.setDrugTotalDaysOfSupplyCount(CommonUtil.getInt("RX_DRGTOTALDAYSOFSPLYCNT",rs));
		prescription.setDrugTrialDaysOfSupplyCount(CommonUtil.getInt("RX_DRGTRLDAYSOFSPLYCNT",rs));
		prescription.setDrugTrialFlag(CommonUtil.getBoolean("RX_DRGTRLFLG",rs));
		prescription.setDrugTrialQuantity(CommonUtil.getInt("RX_DRGTRLQTY",rs));
		prescription.setInferredPrescriptionFlag(CommonUtil.getBoolean("RX_INFRDRXFLG",rs));

		prescription.setNumberOfRefills(CommonUtil.getInt("RX_NUMOFRFLS",rs));
		prescription.setPrescriptionDate(CommonUtil.getCalendar("RX_RXDT",rs));
		prescription.setPrescriptionExpirationDate(CommonUtil.getCalendar("RX_RXEXPRTNDT",rs));
		
		prescription.setSIGAdditionalInformation(rs.getString("RX_SIGADDTNLINFRMATION"));
		prescription.setSIGCode(rs.getString("RX_SIGCD"));
		prescription.setSIGDescriptionPatientLanguage(rs.getString("RX_SIGDESCRPTNTLANG"));
		prescription.setPrescriptionDispensableFlag(CommonUtil.getBoolean("RX_RXDSPNSBLFLG",rs));
		
		prescription.setLegacyFlag(CommonUtil.getBoolean("RX_LEGACYFLG",rs));
		prescription.setHoldEndDate(CommonUtil.getCalendar("RX_HLDENDDT",rs));
//		prescription.setTransferableCode(null);
		prescription.setCreateTimestamp(CommonUtil.getCalendar("RX_CRTTIMESTAMP",rs));
		prescription.setQuantityPrescribed(CommonUtil.getBigDecimal("RX_QTYRXD",rs));
//		prescription.setNoMoreDispensesAllowedFlag(null);
		
		String isBatchFlag = rs.getString("RX_BATCHFLG");
		if (isBatchFlag != null)
			prescription.setIsBatchFlag(CommonUtil.convertYesNoFlagToBoolean(isBatchFlag));
		
		Long noSubReasonTypeKey = CommonUtil.getLong("RX_NOSUBRSNTYPKEY",rs);
		if (noSubReasonTypeKey != null) {
			String noSubReasonTypeCode = getCodeFromKey(LT_NOSUBRSNTYP, noSubReasonTypeKey);
			prescription.setNoSubstitutionReason(noSubReasonTypeCode);
		};

		Long renewalReasonTypeKey = CommonUtil.getLong("RX_RNWLRSNTYPKEY",rs);
		if (renewalReasonTypeKey != null) {
			String renewalReasonCode = getCodeFromKey(LT_RNWLRSNTYP, renewalReasonTypeKey);
			prescription.setRenewalReasonCode(renewalReasonCode);
		};

		Long SubInitiatorTypeKey = CommonUtil.getLong("RX_SUBINITRTYPKEY",rs);
		if (SubInitiatorTypeKey != null) {
			String subInitiatorTypeCode = getCodeFromKey(LT_SUBINITRTYP, SubInitiatorTypeKey);
			SubstitutionInitiator substitutionInitiator = SubstitutionInitiator.fromValue(subInitiatorTypeCode);
			prescription.setSubstitutionInitiator(substitutionInitiator);
			 
		}
		;
		
		Long treatmentTypeKey = CommonUtil.getLong("RX_TRTMNTTYPKEY",rs);
		if (treatmentTypeKey != null) {
			String treatmentTypeCode = getCodeFromKey(LT_TRTMNTTYP, treatmentTypeKey);
			TreatmentType treatmentType = TreatmentType.fromValue(treatmentTypeCode);
			prescription.setTreatmentType(treatmentType);
		}
		;

		Long rxResumeReasonTypeKey = CommonUtil.getLong("RX_RXRSMRSNTYPKEY",rs);
		if (rxResumeReasonTypeKey != null) {
			String rxResumeReasonTypeCd = getCodeFromKey(LT_RXRSMRSNTYP, rxResumeReasonTypeKey);
			PrescriptionResumeReason prescriptionResumeReason = PrescriptionResumeReason.fromValue(rxResumeReasonTypeCd);
			prescription.setResumeReasonCode(prescriptionResumeReason);
		};

		Long rxSourceTypeKey = CommonUtil.getLong("RX_RXSRCETYPKEY",rs);
		if (rxSourceTypeKey != null) {
			String rxSourceTypeCode = getCodeFromKey(LT_RXSRCETYP, rxSourceTypeKey);
			Source source = Source.fromValue(rxSourceTypeCode);
			prescription.setSource(source);
		};
		return prescription;
	}

	
	private void populateRxStatData(Prescription prescription, ResultSet rs)
			throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		 
				Long fillStatusKey = new Long(rs.getLong("RXSTAT_FILLSTATKEY"));
				if (fillStatusKey != null && fillStatusKey > 0L) {
					String fillStatusCode = getCodeFromKey(LT_RXFILLSTATTYP, fillStatusKey);
					RxFillStatus rxFillStatus = RxFillStatus.fromValue(fillStatusCode);
					prescription.setFillStatusCode(rxFillStatus);
				}

				Long subFillStatusKey =  new Long(rs.getLong("RXSTAT_SUBFILLSTATKEY"));
				if (fillStatusKey != null &&  subFillStatusKey > 0L) {
					String subFillStatusCode = getCodeFromKey(LT_RXFILLSTATTYP, subFillStatusKey);
					if (subFillStatusCode != null) {
						RxFillStatus rxSubFillStatus = RxFillStatus.fromValue(subFillStatusCode);
						prescription.setFillStatusSubCode(rxSubFillStatus);
					}
				}
				
				Timestamp timestamp = rs.getTimestamp("RXSTAT_FILLSTATEFFDT");
				if (rs.wasNull())
					prescription.setFillStatusEffectiveDate(null);
				else
					prescription.setFillStatusEffectiveDate(CommonUtil.getXMLGregorianCalendar(timestamp, true));
	}



	private void scanChildren(Connection connection, Prescription prescription, boolean isPatientRequired,boolean sendToODM,ResultSet rs,boolean isFilterActive) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException {
		Long[] twoKeys;
		
		Long prescribedDrugKey = CommonUtil.getLong("RX_DRGKEY",rs);
		if(prescribedDrugKey != null) {
			DrugGet drugGet = new DrugGet();
			Drug drug = drugGet.fetch(connection, prescribedDrugKey);
			if (drug != null) {
				prescription.setPrescribedDrug(drug);	
			}
		}
		
		Long prescribedCompoundKey = CommonUtil.getLong("RX_CMPNDKEY",rs);
		if(prescribedCompoundKey != null) {
			CompoundGet compoundGet = new CompoundGet();
			Compound compound = compoundGet.fetch(connection, prescribedCompoundKey);
			prescription.setPrescribedCompound(compound);
		}
		
		int rxNum = CommonUtil.getInt("RX_RXNUM", rs);
		Long rxKey = CommonUtil.getLong("RX_RXKEY",rs);
		DispenseGet dispenseGet = new DispenseGet();
		List<Dispense> dispenseList = dispenseGet.fetchList(connection, rxKey, rxNum,  primaryRxDispensesSet, linkedRxDispensesSet, isFilterActive);   // LTPHCP319
		
		if(dispenseList != null && dispenseList.size() !=0 )
			prescription.getDispense().addAll(dispenseList);	
		
	
		Long holdReasonTypeKey = CommonUtil.getLong("RX_RXHLDRSNTYPKEY",rs);
		if (holdReasonTypeKey != null) {
			String holdReasonTypeCode = getCodeFromKey(LT_RXHLDRSNTYP, holdReasonTypeKey);
			PrescriptionHoldReasonTypeDisplay prescriptionHoldReasonTypeDisplay = new PrescriptionHoldReasonTypeDisplay();
			prescriptionHoldReasonTypeDisplay.setPrescriptionHoldReasonTypeCode(PrescriptionHoldReason.fromValue(holdReasonTypeCode));
			prescription.setHoldReasonCode(prescriptionHoldReasonTypeDisplay);
		};
		
		Long packKey = CommonUtil.getLong("RX_PACKKEY",rs);
		String storeNum=rs.getString("STORENUM");
			if (packKey != null) {
			Pack pack = CommonUtil.populatePack(rs);
			PrescriptionApi prescriptionApi=new PrescriptionApi();
			HashMap<String,String> packMap=(HashMap<String, String>) prescriptionApi.getCrxDrugAdherance(connection, storeNum, pack.getGTIN());
			if(packMap!=null){
				pack.setStrength(packMap.get("STRENTH"));
				pack.setStrengthUOMCode(packMap.get("STRENTHUOM"));
				pack.setAutoRefillFlag(packMap.get("AUTOREFILLFLAG"));
				pack.setRefillReminderFlag(packMap.get("REFILLREMINDERFLAG"));
				pack.setPickupReminderFlag(packMap.get("PICKUPREMINDERFLAG"));
				pack.setTrrFlag(packMap.get("TRRFLAG"));
			}
			if (pack != null) {
				prescription.setPrescribedPack(pack);
				Long drugKey = CommonUtil.getLong("PACK_DRGKEY",rs);
				if (drugKey != null) {
					DrugGet drugGet = new DrugGet();
					Drug drug = drugGet.fetch(connection, drugKey);
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
						pack.setAutoRefillFlag(packMap.get("AUTOREFILLFLAG"));
						pack.setRefillReminderFlag(packMap.get("REFILLREMINDERFLAG"));
						pack.setPickupReminderFlag(packMap.get("PICKUPREMINDERFLAG"));
						pack.setTrrFlag(packMap.get("TRRFLAG"));
					}
						prescription.setPrescribedPack(pack);
				}
			};

	/*		
		Long prescriberKey = CommonUtil.getLong("RX_PRSCBRKEY",rs);
		Long prescriberAddressKey=CommonUtil.getLong("RX_PRESCRIBERADDRKEY",rs);
		if(prescriberKey != null) {
			PersonRoleViewGet prescriberGet = new PersonRoleViewGet();
			Prescriber prescriber = prescriberGet.fetchPrescriber(connection, prescriberKey,rxKey,prescriberAddressKey,sendToODM);
			if(prescriber!=null) {
				Long prescriberTypeKey = CommonUtil.getLong("RX_PRSCBRTYPKEY",rs);
				if(prescriberTypeKey != null) {
					String prescriberTypeCode = getCodeFromKey(LT_PRSCBRTYP, prescriberTypeKey);
					prescriber.setPrescriberTypeCode(PrescriberType.fromValue(prescriberTypeCode));
				}
				prescription.setPrescriber(prescriber);
			}
		}
		*/
		if (isPatientRequired) {
			Long patientKey = CommonUtil.getLong("RX_PTNTKEY",rs);
			if (patientKey != null) {
				PatientViewGet patientViewGet = new PatientViewGet();
				Patient patient = patientViewGet.fetch(connection, patientKey);
				prescription.setPatient(patient);
				
			}
		};

	
		Long locationKey = CommonUtil.getLong("RX_SVCLOCKEY",rs);			
		if(locationKey != null) {
			LocationGet locationGet = new LocationGet();
			Location location = locationGet.fetch(connection, locationKey);
			prescription.setServiceLocation(location);
		}
		
		Long linkedRxKey = CommonUtil.getLong("RX_LINKEDRXKEY",rs);
		if (linkedRxKey != null && linkedRxKey != 0L) {
			linkedRxMap.put(linkedRxKey, rxKey);
		} 

		twoKeys = new Long[2];
		twoKeys[0] = rxKey;
		twoKeys[1] = linkedRxKey;
		normalRxMap.put(CommonUtil.getInt("RX_RXNUM", rs), twoKeys);
		rxkeyPrescriptionMap.put(rxKey, prescription);
	};


	
	/*	
	LTPHCP319 Started
	            twoKeys[0]  twoKeys[1]
----------      ---------   --------------	
rxNum	        rxKey	    linkedRxKey
----------      ---------   --------------
9326438			174986552	(null)
9351243	 x		160393626	(null)    --> has forwardRxNum = 9358970  if (linkedRxSet.contains(rxKey)... )
9351603			160397303	(null)
9358970	 x 		160448040	160393626 --> has linkedRxNum = 9351243   if (linkedRxKey != 0L ) {...
9362857			160493488	(null)
9362858			160505950	(null)
9362859			160503933	(null)
	
*/	
	
	private void populateLinkedForwardRx(List<Prescription> prescriptionList) throws ParseException, DatatypeConfigurationException, CDRInternalException, NamingException {
		  Long[] twoKeys;
		  Long rxKey = 0L;
		  Long linkedRxKey = 0L;
		  
		  int rxNum = 0;
		  int relatedRxNum = 0;
		  int relatedTxNum = 0;
		  String txCompositeKey = null;
		  
		  Prescription relatedPrescription = null;
		  Prescription forwardRx = null;
		  RxFillStatus fillStatusCode;
		  
		  if (logger.isDebugEnabled()) {logger.debug("linkedRxDispenses: " + linkedRxDispensesSet.size() + "   " + linkedRxDispensesSet); }
		  
		  for (Prescription currentPrescr : prescriptionList) {
			   rxNum = currentPrescr.getPrescriptionNumber();
			   twoKeys = new Long[2];
			   twoKeys = normalRxMap.get(rxNum);
			   rxKey = twoKeys[0];
			   linkedRxKey = twoKeys[1];
			   
//             -----------------------------------------------------
//			   ForwardRx block started
//             -----------------------------------------------------			   
			   if ( linkedRxMap.containsKey(rxKey) ) { 
				   relatedPrescription = new Prescription();
				   relatedPrescription = rxkeyPrescriptionMap.get(linkedRxMap.get(rxKey));
				   relatedRxNum = relatedPrescription.getPrescriptionNumber();
				   fillStatusCode = relatedPrescription.getFillStatusCode();
				   
				   if (rxNum < relatedRxNum) {
					   forwardRx = new Prescription(); 
					   forwardRx.setPrescriptionNumber(relatedRxNum);
					   forwardRx.setFillStatusCode(fillStatusCode);
					   if (forwardRx != null) {
						  currentPrescr.setForwardRx(forwardRx);
				       }
			        }  
			   }

			   
//             -----------------------------------------------------
//			   LinkedRx block started
//             -----------------------------------------------------			   
			   boolean containsLinkedRxKey = rxkeyPrescriptionMap.containsKey(linkedRxKey);
			   if (containsLinkedRxKey && linkedRxKey != null && linkedRxKey != 0L ) {
				   relatedPrescription = new Prescription();
				   relatedPrescription = rxkeyPrescriptionMap.get(linkedRxKey);
				   relatedRxNum = relatedPrescription.getPrescriptionNumber();
				   
				   if (rxNum > relatedRxNum) {
					   List<Dispense> linkedRxDispList = new ArrayList<Dispense>();
					   for (Dispense thisDispense : relatedPrescription.getDispense()) {
						   relatedTxNum = thisDispense.getTransactionNumber();
						   txCompositeKey = String.format("%20d %20d", relatedRxNum, relatedTxNum);
						   if (linkedRxDispensesSet.contains(txCompositeKey))  {
							   linkedRxDispList.add(thisDispense);
  						       if (logger.isDebugEnabled()) {logger.debug("Used by linkedRx txCompositeKey: " + txCompositeKey); }  						       
						   }
					   }
					   Prescription linkedRx = populateLinkedPrescription(relatedPrescription);
					   linkedRx.getDispense().addAll(linkedRxDispList);
					   currentPrescr.setLinkedrx(linkedRx);
					   
				   }
			   } 
			   
			   
//             -----------------------------------------------------
//			   mainPrescription block started
//             -----------------------------------------------------			   
			   if (rxKey.equals(mainRxKey)) {
				   mainPrescription = currentPrescr;
			   }
			   
			   
		  } // end "for" main loop
	} 
	
	
private Prescription populateLinkedPrescription(Prescription linkedRx) {
		Prescription rx = new Prescription();
		try{
			List<Dispense> displist =null;
			int relatedTxNum = 0;
			String txCompositeKey = null;
			rx.setUpdateTimestamp(linkedRx.getUpdateTimestamp());
			
			if(null != linkedRx.getAdministrationPeriodStartDate()){
				rx.setAdministrationPeriodStartDate(linkedRx.getAdministrationPeriodStartDate());
			}
			if(null !=linkedRx.getAdministrationPeriodEndDate()){
				rx.setAdministrationPeriodEndDate(linkedRx.getAdministrationPeriodEndDate());
			}
			
			rx.setConsumerId(null !=linkedRx.getConsumerId()?linkedRx.getConsumerId():null);
			rx.setProducerId(null !=linkedRx.getProducerId()?linkedRx.getProducerId():null);
			rx.setIsAuthoritative(linkedRx.isIsAuthoritative()); //Un comment for future implementation
			rx.setQuantityDispensed(null !=linkedRx.getQuantityDispensed()?linkedRx.getQuantityDispensed():null);
			rx.setRemainingQuantity(null !=linkedRx.getRemainingQuantity()?linkedRx.getRemainingQuantity():null);
			rx.setPrescriptionNumber(null !=linkedRx.getPrescriptionNumber()?linkedRx.getPrescriptionNumber() : null);
			rx.setRefillsRemainingCount(null !=linkedRx.getRefillsRemainingCount()? linkedRx.getRefillsRemainingCount():null);
			rx.setTotalQuantityAuthorized(null !=linkedRx.getTotalQuantityAuthorized()?linkedRx.getTotalQuantityAuthorized():null);
			rx.setDrugTotalDaysOfSupplyCount(null !=linkedRx.getDrugTotalDaysOfSupplyCount()?linkedRx.getDrugTotalDaysOfSupplyCount():null);
			rx.setDrugTrialDaysOfSupplyCount(null != linkedRx.getDrugTrialDaysOfSupplyCount()?linkedRx.getDrugTrialDaysOfSupplyCount() : null);
			rx.setDrugTrialFlag(null !=linkedRx.isDrugTrialFlag()?linkedRx.isDrugTrialFlag():null);
			rx.setDrugTrialQuantity(null !=linkedRx.getDrugTrialQuantity()? linkedRx.getDrugTrialQuantity() : null);
		
			if(null != linkedRx.isInferredPrescriptionFlag() ){
				rx.setInferredPrescriptionFlag(linkedRx.isInferredPrescriptionFlag());
			}
			rx.setNumberOfRefills(null !=linkedRx.getNumberOfRefills()?linkedRx.getNumberOfRefills():null);
			rx.setPrescriptionDate(linkedRx.getPrescriptionDate());
			rx.setPrescriptionExpirationDate(linkedRx.getPrescriptionExpirationDate());
							
	//		rx.setSIGAdditionalInformation(linkedRx.getSIGDescriptionPatientLanguage());
			rx.setSIGCode(null !=linkedRx.getSIGCode()?linkedRx.getSIGCode() : null);
			rx.setSIGDescriptionPatientLanguage(null != linkedRx.getSIGDescriptionPatientLanguage()?linkedRx.getSIGDescriptionPatientLanguage(): null);

			if(null !=linkedRx.isPrescriptionDispensableFlag() ){
				rx.setPrescriptionDispensableFlag(linkedRx.isPrescriptionDispensableFlag());
			}
			rx.setFillStatusEffectiveDate(linkedRx.getFillStatusEffectiveDate());
			if( null != linkedRx.isLegacyFlag()) {
				rx.setLegacyFlag(linkedRx.isLegacyFlag());
			}
			
			rx.setHoldEndDate(linkedRx.getHoldEndDate());
//			rx.setTransferableCode(null);
			rx.setCreateTimestamp(linkedRx.getCreateTimestamp());
			rx.setQuantityPrescribed(null != linkedRx.getQuantityPrescribed()? linkedRx.getQuantityPrescribed(): null);
//			rx.setNoMoreDispensesAllowedFlag(null);
			
			if(null != linkedRx.isIsBatchFlag()) {
				rx.setIsBatchFlag(linkedRx.isIsBatchFlag());
			}
			rx.setNoSubstitutionReason(null !=linkedRx.getNoSubstitutionReason()?linkedRx.getNoSubstitutionReason() : null);
			
			if(null != linkedRx.getFillStatusCode()){
				rx.setFillStatusCode(linkedRx.getFillStatusCode());
			}
			
			if(null != linkedRx.getFillStatusSubCode()){
				rx.setFillStatusSubCode(linkedRx.getFillStatusSubCode());
			}
			rx.setRenewalReasonCode(null != linkedRx.getRenewalReasonCode()? linkedRx.getRenewalReasonCode() : null );
	        
			if(null != linkedRx.getSubstitutionInitiator()){
	        rx.setSubstitutionInitiator(linkedRx.getSubstitutionInitiator());
		    }
			if(null != linkedRx.getTreatmentType()){
				rx.setTreatmentType(linkedRx.getTreatmentType());
			}
			
			rx.setResumeReasonCode((null !=linkedRx.getResumeReasonCode())? linkedRx.getResumeReasonCode(): null);
			rx.setSource(linkedRx.getSource());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rx;
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
				packtx.setAlternativepacksize(CommonUtil.getInt("PACK_ALTRNTVPACKSZ",rs));
				packtx.setAlternativepacksizeunitofmeasure(rs.getString("PACK_ALTRNTVPACKSZUOM"));
				packtx.setStrength(rs.getString("PACK_STRNGTH"));//TE97_024
				packtx.setGTIN(rs.getString("PACK_GTIN")) ;
				
				packtx.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISACTFLG" )));
				packtx.setConsumerId(rs.getString("PACK_CNSMRID")) ;
				packtx.setIsCurrentFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISCRNTFLG"))) ;
				packtx.setProducerId(rs.getString("PACK_PRDCRID")) ;
				packtx.setManufacturerDiscontinuedDate(CommonUtil.getXMLGregorianCalendar(rs.getDate("PACK_MFCTRDISCNTDDT") , true));
				packtx.setPackSize(CommonUtil.getBigDecimal("PACK_PACKSZ",rs) ) ;
				packtx.setMasterid(rs.getString("PACK_MSTRID")) ;
				Long packSizeUOMTypeKey = CommonUtil.getLong("PACK_PACKSZUOMTYPKEY",rs);
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
				
				Long strengthUOMTypeKey = CommonUtil.getLong("PACK_STRNGTHUOMTYPKEY",rs);
				if(strengthUOMTypeKey!=null)
				{
			        String strengthUOMTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, strengthUOMTypeKey);
			        packtx.setStrengthUOMCode(strengthUOMTypeCode);
				}
				
				Long drugKey = CommonUtil.getLong("PACK_DRGKEY",rs);
				if (drugKey != null) {
					DrugGet drugGet = new DrugGet();
					Drug drug = drugGet.fetch(connection, drugKey);
					if (drug != null) {
						packtx.setDrug(drug);
					}
				}
			
		}
			
			
		}
		}catch(Exception ex) {
			throw new SQLException(ex);
		}finally{
			ps.close();
			rs.close();
		}
		
		return packtx;
	}
}
