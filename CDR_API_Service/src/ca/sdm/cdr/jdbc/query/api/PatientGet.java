package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GNDRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GRPMBRSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PTNTTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.GroupMembership;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientGender;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;
import ca.shoppersdrugmart.rxhb.ehealth.PatientType;
import ca.shoppersdrugmart.rxhb.ehealth.Person;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
                                 
*/


public class PatientGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(PatientGet.class);
	private Long patientKey = null;
	
	private final static String QUERYBASICSQL = "SELECT "
			+ "PTNT.DTOFBIRTH PTNT_DTOFBIRTH, PTNT.DCSDDT PTNT_DCSDDT, PTNT.PTNTACTIND PTNT_PTNTACTIND, PTNT.NOKNWNALRGYFLG PTNT_NOKNWNALRGYFLG, " //PTNT.RCRDRKEY PTNT_RCRDRKEY,
			+ "PTNT.CRTTIMESTAMP PTNT_CRTTIMESTAMP, PTNT.LSTUPDTTIMESTAMP PTNT_LSTUPDTTIMESTAMP, PTNT.PTNTKEY PTNT_PTNTKEY, PTNT.GNDRTYPKEY PTNT_GNDRTYPKEY, " //PTNT.STORENUM PTNT_STORENUM, 
			+ "PTNT.GRPMBRSHPTYPKEY PTNT_GRPMBRSHPTYPKEY, PTNT.PTNTOPTNOPTTYPKEY PTNT_PTNTOPTNOPTTYPKEY, PTNT.PTNTTYPKEY PTNT_PTNTTYPKEY, "
			+ "PTNT.FRSTNM PTNT_FRSTNM, PTNT.MDLNM PTNT_MDLNM, PTNT.LSTNM PTNT_LSTNM, PTNT.CNSMRID PTNT_CNSMRID, "
			+ "CNTCTMTHD.CNTCTMTHDTYPKEY CNTCTMTHD_CNTCTMTHDTYPKEY, CNTCTMTHD.CNTCTMTHDKEY CNTCTMTHD_CNTCTMTHDKEY, "			
			+ "TELECOM.TELFAXIND TELECOM_TELFAXIND, TELECOM.CNTRYCD TELECOM_CNTRYCD, TELECOM.TELECOMNUM TELECOM_TELECOMNUM, "
			+ "EMAIL.EMAILADDR EMAIL_EMAILADDR, "
			+ "ADDR.ADDRLNONE ADDR_ADDRLNONE, ADDR.ADDRLNTWO ADDR_ADDRLNTWO, ADDR.CITYNM ADDR_CITYNM, ADDR.PROVCD ADDR_PROVCD, ADDR.CNTRYCD ADDR_CNTRYCD, ADDR.POSTALCD ADDR_POSTALCD "
			+ "FROM PTNT PTNT " 
			+ "LEFT OUTER JOIN CNTCTMTHD CNTCTMTHD ON PTNT.PTNTKEY = CNTCTMTHD.PTNTKEY "
			+ "LEFT OUTER JOIN TELECOM TELECOM ON CNTCTMTHD.CNTCTMTHDKEY = TELECOM.TELECOMKEY "
			+ "LEFT OUTER JOIN ADDR ADDR ON CNTCTMTHD.CNTCTMTHDKEY = ADDR.ADDRKEY "
			+ "LEFT OUTER JOIN EMAIL EMAIL ON CNTCTMTHD.CNTCTMTHDKEY = EMAIL.EMAILKEY ";


	private final static String QUERYBYPTNTKEYSQL = QUERYBASICSQL 
			+ "WHERE PTNT.PTNTKEY = ?";

	private final static String QUERYBYPTNTIDSQL = QUERYBASICSQL
			+ "WHERE PTNT.STORENUM = ? AND PTNT.CNSMRID = ? ";
	
	public Patient fetch(Connection connection, Long patientKey, Map<Long, Drug> drgPK) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			return populateByPatientKey(connection, patientKey,drgPK);
		} catch (CDRInternalException e) {
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
		} finally
		{
			super.close();
		}
	}

	public Patient fetch(Connection connection, String storenumber, String patientId, Map<Long, Drug> drgPK) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			return populateByPatientId(connection, storenumber, patientId,drgPK);
		} catch (CDRInternalException e) {
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
		} finally {
			super.close();
		}
	}
	
	
	private Patient populateByPatientKey(Connection connection, Long patientKey, Map<Long, Drug> drgPK) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey);}

		this.patientKey = patientKey;
		Patient patient = null;
		preparedStatement = connection.prepareStatement(QUERYBYPTNTKEYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: PatientGet.queryByPtntKeySQL patientKey : " + patientKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: PatientGet.queryByPtntKeySQL patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		Address address = null;		
		while (resultSet.next()) {
			if (patient == null) {
				patient = new Patient();
				scanBasic(patient);
				scanChildren(connection, patient,drgPK);
			};

			Long contactMethodKey = getLong("CNTCTMTHD_CNTCTMTHDKEY");
			if (contactMethodKey != null) {
				if (address == null) {
					address = new Address();
				}
				populateAddress(address);
			}
		}

		if (logger.isInfoEnabled())  {logger.info("EndApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return patient;
	}
	
	private Patient populateByPatientId(Connection connection, String storeNumber, String patientId, Map<Long, Drug> drgPK) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
	
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ", storeNumber : " + storeNumber);}

		Patient patient = null;
		preparedStatement = connection.prepareStatement(QUERYBYPTNTIDSQL);
		preparedStatement.setString(1, storeNumber);
		preparedStatement.setString(2, patientId);
		
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: PatientGet.queryByPtntIdSQL patientKey : " + patientKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: PatientGet.queryByPtntIdSQL patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		Address address = null;
		while (resultSet.next()) {
			patientKey = getLong("PTNT_PTNTKEY");
			if (patient == null) {
				patient = new Patient();
				scanBasic(patient);
				scanChildren(connection, patient,drgPK);
			};
			
			Long contactMethodKey = getLong("CNTCTMTHD_CNTCTMTHDKEY");
			if (contactMethodKey != null) {
				if (address == null) {
					address = new Address();
				}
				populateAddress(address);
			}
		}
		
		if(patient != null && address!=null)
//		    patient.getPerson().setAddress(address);
	        patient.getPerson().getAddress().add(address);  //VL99

		if (logger.isInfoEnabled())  {logger.info("EndApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ", storeNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return patient;
	}

	private void scanBasic(Patient patient) throws ParseException, DatatypeConfigurationException, SQLException, CDRInternalException, NamingException, IOException
	{
		Person person = new Person();
		person.setFirstName(getString("PTNT_FRSTNM"));
		person.setLastName(getString("PTNT_LSTNM"));
		patient.setPerson(person);
		patient.setDateOfBirth(CommonUtil.getXMLGregorianCalendarByDate(getDate("PTNT_DTOFBIRTH")));
		patient.setDeceaseddate(CommonUtil.getXMLGregorianCalendarByDate(getDate("PTNT_DCSDDT")));
		patient.setIsactive(CommonUtil.convertYesNoFlagToBoolean(getString("PTNT_PTNTACTIND")));
		patient.setNoKnownAllergyFlag(CommonUtil.convertYesNoFlagToBoolean(getString("PTNT_NOKNWNALRGYFLG")));
		patient.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(getDate("PTNT_CRTTIMESTAMP"), true));
		patient.setLastUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(getDate("PTNT_LSTUPDTTIMESTAMP"), true));
		patient.setConsumerId(getString("PTNT_CNSMRID"));
		Long genderTypeKey = getLong("PTNT_GNDRTYPKEY");
		if (genderTypeKey != null) {
			String genderTypeCode = getCodeFromKey(LT_GNDRTYP, genderTypeKey);
			PatientGender patientGender = PatientGender.fromValue(genderTypeCode);
			patient.setGenderTypeCode(patientGender);
		};

		Long groupMemberShipKey = getLong("PTNT_GRPMBRSHPTYPKEY");
		if (groupMemberShipKey != null) {
			String groupMemberShipCode = getCodeFromKey(LT_GRPMBRSHPTYP, groupMemberShipKey);
			patient.setGroupmembership(GroupMembership.fromValue(groupMemberShipCode));
		};

//		patient.setPatientrefillreminder(); Not available from CustPref
		
		Long patientTypeKey = getLong("PTNT_PTNTTYPKEY");
		if (patientTypeKey != null) {
			String PatientTypeCode = getCodeFromKey(LT_PTNTTYP, patientTypeKey);
			patient.setPatienttype(PatientType.fromValue(PatientTypeCode));
		};
	}
	
	private void scanChildren(Connection connection, Patient patient, Map<Long, Drug> drgPK) throws SQLException, ParseException, DatatypeConfigurationException, NamingException, IOException, CDRInternalException {
		PatientMetricsGet patientMetricsGet = new PatientMetricsGet();
		List<PatientMetrics> patientMetrics = patientMetricsGet.fetch(connection, patientKey);
		if (patientMetrics != null && patientMetrics.size() > 0)
			patient.getPatientMetrics().addAll(patientMetrics);
		NoteGet noteGet = new NoteGet("PTNTNT", "PTNTKEY", true);
		List<Note> notes = noteGet.fetch(connection, patientKey);
		if (notes != null && notes.size() > 0)
			patient.getNote().addAll(notes);
		
		ConsentGet consentGet = new ConsentGet();
		List<Consent> consents = consentGet.fetch(connection, patientKey);
		if(consents!=null&&consents.size() > 0)
			patient.getConsent().addAll(consents);
		
		PatientIdentificationGet patientIdentificationGet = new PatientIdentificationGet();		
		List<PatientIdentification> patientIdentifications = patientIdentificationGet.fetch(connection, patientKey);
		if(patientIdentifications!=null&&patientIdentifications.size() > 0)
			patient.getPatientIdentification().addAll(patientIdentifications);
		
		PatientCoverageGet patientCoverageGet = new PatientCoverageGet();		
		List<InsuranceCoverage> patientCoverages = patientCoverageGet.fetch(connection, patientKey);
		if(patientCoverages!=null&&patientCoverages.size() > 0)
			patient.getInsuranceCoverage().addAll(patientCoverages);
		
		AdverseDrugReactionGet adverseDrugReactionGet = new AdverseDrugReactionGet();		
		List<AdverseDrugReaction> adverseDrugReactions = adverseDrugReactionGet.fetch(connection, patientKey);
		if(adverseDrugReactions!=null&&adverseDrugReactions.size() > 0)
			patient.getAdverseDrugReaction().addAll(adverseDrugReactions);
		
		PatientMedicalConditionGet patientMedicalConditionGet = new PatientMedicalConditionGet();		
		List<PatientMedicalCondition> patientMedicalConditions = patientMedicalConditionGet.fetch(connection, patientKey);
		if(patientMedicalConditions!=null&&patientMedicalConditions.size() > 0)
			patient.getPatientMedicalCondition().addAll(patientMedicalConditions);
		
		AllergyGet allergyGet = new AllergyGet();		
		List<Allergy> allergies = allergyGet.fetch(connection, patientKey,drgPK);
		if(allergies!=null&&allergies.size() > 0)
			patient.getAllergy().addAll(allergies);
	}
}
