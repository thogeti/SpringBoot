package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GNDRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GRPMBRSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PTNTTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Address;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Patient;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientGender;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientMetrics;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Person;*/
import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
//use MedicationReview
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Address;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.AdverseDrugReaction;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Allergy;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Consent;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.GroupMembership;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.InsuranceCoverage;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Note;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Patient;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientGender;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientIdentification;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientMedicalCondition;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientMetrics;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Person;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
                                 
*/


public class PatientMedReviewGet extends CDRMedReviewGet {
	private static Logger logger = LogManager.getLogger(PatientMedReviewGet.class);
	private Long patientKey = null;
	public Patient patient =null;

	public PatientMedReviewGet() {
		try {
			QUERYBASICSQL = TableCacheSingleton.getResource("PatientMedReviewGet_QueryBasicSQL.sql");
		} catch (IOException e) {
			 
			e.printStackTrace();
		}	 
	}
	private  static String QUERYBASICSQL =   "SELECT PTNT.DTOFBIRTH PTNT_DTOFBIRTH,\r\n" + 
			"       PTNT.DCSDDT PTNT_DCSDDT,\r\n" + 
			"       PTNT.PTNTACTIND PTNT_PTNTACTIND,\r\n" + 
			"       PTNT.NOKNWNALRGYFLG PTNT_NOKNWNALRGYFLG, \r\n" + 
			"       PTNT.CRTTIMESTAMP PTNT_CRTTIMESTAMP,\r\n" + 
			"       PTNT.LSTUPDTTIMESTAMP PTNT_LSTUPDTTIMESTAMP,\r\n" + 
			"       PTNT.PTNTKEY PTNT_PTNTKEY,\r\n" + 
			"       PTNT.GNDRTYPKEY PTNT_GNDRTYPKEY, \r\n" + 
			"       PTNT.GRPMBRSHPTYPKEY PTNT_GRPMBRSHPTYPKEY,\r\n" + 
			"       PTNT.PTNTOPTNOPTTYPKEY PTNT_PTNTOPTNOPTTYPKEY, \r\n" + 
			"       PTNT.PTNTTYPKEY PTNT_PTNTTYPKEY,\r\n" + 
			"       PTNT.FRSTNM PTNT_FRSTNM,\r\n" + 
			"       PTNT.MDLNM PTNT_MDLNM,\r\n" + 
			"       PTNT.LSTNM PTNT_LSTNM,\r\n" + 
			"       PTNT.CNSMRID PTNT_CNSMRID, \r\n" + 
			"       CNTCTMTHD.CNTCTMTHDTYPKEY CNTCTMTHD_CNTCTMTHDTYPKEY,\r\n" + 
			"       CNTCTMTHD.CNTCTMTHDKEY CNTCTMTHD_CNTCTMTHDKEY, \r\n" + 
			"       TELECOM.TELFAXIND TELECOM_TELFAXIND,\r\n" + 
			"       TELECOM.CNTRYCD TELECOM_CNTRYCD,\r\n" + 
			"       TELECOM.TELECOMNUM TELECOM_TELECOMNUM,\r\n" + 
			"       EMAIL.EMAILADDR EMAIL_EMAILADDR,\r\n" + 
			"       ADDR.ADDRLNONE ADDR_ADDRLNONE,\r\n" + 
			"       ADDR.ADDRLNTWO ADDR_ADDRLNTWO,\r\n" + 
			"       ADDR.CITYNM ADDR_CITYNM,\r\n" + 
			"       ADDR.PROVCD ADDR_PROVCD,\r\n" + 
			"       ADDR.CNTRYCD ADDR_CNTRYCD,\r\n" + 
			"       ADDR.POSTALCD ADDR_POSTALCD \r\n" + 
			"       FROM PTNT PTNT \r\n" + 
			"       LEFT OUTER JOIN CNTCTMTHD CNTCTMTHD ON PTNT.PTNTKEY = CNTCTMTHD.PTNTKEY\r\n" + 
			"       LEFT OUTER JOIN TELECOM TELECOM ON CNTCTMTHD.CNTCTMTHDKEY = TELECOM.TELECOMKEY\r\n" + 
			"       LEFT OUTER JOIN ADDR ADDR ON CNTCTMTHD.CNTCTMTHDKEY = ADDR.ADDRKEY\r\n" + 
			"       LEFT OUTER JOIN EMAIL EMAIL ON CNTCTMTHD.CNTCTMTHDKEY = EMAIL.EMAILKEY ";


	private   static String QUERYBYPTNTKEYSQL = QUERYBASICSQL 
			+ "WHERE PTNT.PTNTKEY = ?";

	private   static String QUERYBYPTNTIDSQL = QUERYBASICSQL
			+ "WHERE PTNT.STORENUM = ? AND PTNT.CNSMRID = ? ";
	
	/*public Patient fetch(Connection connection, Long patientKey) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			return populateByPatientKey(connection, patientKey);
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
	}*/

	public Patient fetch(Connection connection, String storenumber, String patientId) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			return populateByPatientId(connection, storenumber, patientId);
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
	
	
	/*private Patient populateByPatientKey(Connection connection, Long patientKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		
		Long timer = System.currentTimeMillis();
		logger.info("StartApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey);
		QUERYBASICSQL = TableCacheSingleton.getResource("PatientMedReviewGet_QueryBasicSQL.sql");
		this.patientKey = patientKey;
		Patient patient = null;
		preparedStatement = connection.prepareStatement(QUERYBYPTNTKEYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
		logger.debug("StartExecuteQuery: PatientGet.queryByPtntKeySQL patientKey : " + patientKey );
		resultSet = preparedStatement.executeQuery();
		logger.debug("EndExecuteQuery: PatientGet.queryByPtntKeySQL patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		Address address = null;		
		while (resultSet.next()) {
			if (patient == null) {
				patient = new Patient();
				scanBasic(patient);
				scanChildren(connection, patient);
			};

			Long contactMethodKey = getLong("CNTCTMTHD_CNTCTMTHDKEY");
			if (contactMethodKey != null) {
				if (address == null) {
					address = new Address();
				}
				populateAddress(address);
			}
		}

		logger.info("EndApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		return patient;
	}*/
	
	private Patient populateByPatientId(Connection connection, String storeNumber, String patientId) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
	
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ", storeNumber : " + storeNumber);}

		QUERYBASICSQL = TableCacheSingleton.getResource("PatientMedReviewGet_QueryBasicSQL.sql");
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
				scanChildren(connection, patient);
			};
			
			Long contactMethodKey = getLong("CNTCTMTHD_CNTCTMTHDKEY");
			if (contactMethodKey != null) {
				if (address == null) {
					address = new Address();
				}
				populateAddress(address);
			}
		}
		
		if(patient != null && address!=null) {
//		    patient.getPerson().setAddress(address);
	//        patient.getPerson().getAddress().add(address);  //VL99
		}
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PatientGet.populateByPatientKey. patientKey : " + patientKey + ", storeNumber : " + storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
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
		//	patient.setGenderTypeCode(patientGender);
			patient.setGenderTypeCode(genderTypeCode);
		};

		Long groupMemberShipKey = getLong("PTNT_GRPMBRSHPTYPKEY");
		if (groupMemberShipKey != null) {
			String groupMemberShipCode = getCodeFromKey(LT_GRPMBRSHPTYP, groupMemberShipKey);
		//	patient.setGroupmembership(GroupMembership.fromValue(groupMemberShipCode));
			patient.setGroupmembership(groupMemberShipCode);
		};

//		patient.setPatientrefillreminder(); Not available from CustPref
		
		Long patientTypeKey = getLong("PTNT_PTNTTYPKEY");
		if (patientTypeKey != null) {
			String PatientTypeCode = getCodeFromKey(LT_PTNTTYP, patientTypeKey);
			patient.setPatienttype(PatientTypeCode);
		};
	}
	
	private void scanChildren(Connection connection, Patient patient) throws SQLException, ParseException, DatatypeConfigurationException, NamingException, IOException, CDRInternalException {
		PatientMetricsMedReviewGet patientMetricsMedReviewGet = new PatientMetricsMedReviewGet();
		List<PatientMetrics> patientMetrics = patientMetricsMedReviewGet.fetch(connection, patientKey);
		if (patientMetrics != null && patientMetrics.size() > 0)
			patient.getPatientMetrics().addAll(patientMetrics);
		NoteMedReviewGet noteMedReviewGet = new NoteMedReviewGet("PTNTNT", "PTNTKEY", true);
		List<Note> notes = noteMedReviewGet.fetch(connection, patientKey);
		if (notes != null && notes.size() > 0)
			patient.getNote().addAll(notes);
		
		/*ConsentGet consentGet = new ConsentGet();
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
		List<Allergy> allergies = allergyGet.fetch(connection, patientKey);
		if(allergies!=null&&allergies.size() > 0)
			patient.getAllergy().addAll(allergies);*/
	}
}
