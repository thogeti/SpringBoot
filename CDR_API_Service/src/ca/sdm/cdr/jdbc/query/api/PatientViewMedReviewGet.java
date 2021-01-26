package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GNDRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GRPMBRSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_LANGUAGES;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PTNTTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CustomerNotFoundException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Address;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.AdverseDrugReaction;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Allergy;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Consent;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.GroupMembership;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.InsuranceCoverage;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Language;
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
TE99                                 
*/


public class PatientViewMedReviewGet extends CDRMedReviewGet {
	private Long patientKey = null;
	private static Logger logger = LogManager.getLogger(PatientViewMedReviewGet.class);
//	private static final String SYNONYM_PATIENT_CONTACT_METHOD = "PATIENT_CONTACT_METHOD_VW";	
	private static final String SYNONYM_PATIENT_CONTACT_METHOD = "PATIENT_CONTACT_METHOD";	
	private Map<String, Integer> PatientUniqAddr = new HashMap<String, Integer>();  //VL99
	
	public Long getPatientKey() {
		return patientKey;
	}

	public void setPatientKey(Long patientKey) {
		this.patientKey = patientKey;
	}
	

//TE99 Started	
	//+ "CONTACT.ADDR_TYPE ADDR_TYPE, CONTACT.ADDR_TYPE_CODE ADDR_TYPE_CODE,CONTACT.ACTVFLG ACTVFLG,
	//+ "CONTACT.ALTERN_ROWID ALTERN_ROWID,CONTACT.ALTERN_TELECOMKEY ALTERN_TELECOMKEY,CONTACT.ALTERN_TELECOMNUM ALTERN_TELECOMNUM,"
	//+ "PTNT.SALUTATION PTNT_SALUTATION,PTNT.MOBILENUM PTNT_MOBILENUM," Need to introduce future implementation in below query

  //TE97_024 added
	private final static String QUERYBASICSQL = "SELECT "	
			+ "PTNT.DTOFBIRTH PTNT_DTOFBIRTH, PTNT.DCSDDT PTNT_DCSDDT, PTNT.PTNTACTIND PTNT_PTNTACTIND, PTNT.NOKNWNALRGYFLG PTNT_NOKNWNALRGYFLG, " //PTNT.RCRDRKEY PTNT_RCRDRKEY,
			+ "PTNT.CRTTIMESTAMP PTNT_CRTTIMESTAMP, PTNT.LSTUPDTTIMESTAMP PTNT_LSTUPDTTIMESTAMP, PTNT.PTNTKEY PTNT_PTNTKEY, PTNT.GNDRTYPKEY PTNT_GNDRTYPKEY, " //PTNT.STORENUM PTNT_STORENUM, 
			+ "PTNT.GRPMBRSHPTYPKEY PTNT_GRPMBRSHPTYPKEY, PTNT.PTNTOPTNOPTTYPKEY PTNT_PTNTOPTNOPTTYPKEY, PTNT.PTNTTYPKEY PTNT_PTNTTYPKEY, "
			+ "PTNT.FRSTNM PTNT_FRSTNM, PTNT.MDLNM PTNT_MDLNM, PTNT.LSTNM PTNT_LSTNM, PTNT.CNSMRID PTNT_CNSMRID,"
		//	+ "CONTACT.EMAIL_ROWID EMAIL_ROWID,  CONTACT.EMAIL_EMAILKEY EMAIL_EMAILKEY,  CONTACT.EMAIL_EMAILADDR EMAIL_EMAILADDR,  "
		//	+ "CONTACT.PHONE_ROWID PHONE_ROWID,  CONTACT.PHONE_TELECOMKEY PHONE_TELECOMKEY,  CONTACT.PHONE_TELFAXIND PHONE_TELFAXIND,  CONTACT.PHONE_CNTRYCD PHONE_CNTRYCD,  CONTACT.PHONE_TELECOMNUM PHONE_TELECOMNUM,  "
	//		+ "CONTACT.FAX_ROWID FAX_ROWID,  CONTACT.FAX_TELECOMKEY FAX_TELECOMKEY,  CONTACT.FAX_TELFAXIND FAX_TELFAXIND,  CONTACT.FAX_CNTRYCD FAX_CNTRYCD,  CONTACT.FAX_TELECOMNUM FAX_TELECOMNUM,  "
	//		+ "CONTACT.ADDR_ROWID ADDR_ROWID,  CONTACT.ADDR_ADDRLNONE ADDR_ADDRLNONE,  CONTACT.ADDR_ADDRLNTWO ADDR_ADDRLNTWO,  CONTACT.ADDR_CITYNM ADDR_CITYNM,  "
	//		+ "CONTACT.ADDR_PROVCD ADDR_PROVCD,  CONTACT.ADDR_CNTRYCD ADDR_CNTRYCD,  CONTACT.ADDR_POSTALCD ADDR_POSTALCD, "
	//		+ "CONTACT.CM_CNSMRID CM_CNSMRID, "
			+ "CONTACT.ADDR_ADDRKEY ADDR_ADDRKEY "
			+ "FROM PTNT PTNT " 
			+ "LEFT OUTER JOIN "
			+ SYNONYM_PATIENT_CONTACT_METHOD
			+ " CONTACT ON PTNT.PTNTKEY = CONTACT.PTNT_PTNTKEY ";			
//TE99 Ended
	
	
	
	private  static String QUERYBYPTNTKEYSQL ="" ;

	private final static String QUERYBYPTNTIDSQL = QUERYBASICSQL
			+ "WHERE PTNT.STORENUM = ? AND PTNT.CNSMRID = ? fetch first row only ";


	public Patient fetch(Connection connection, Long patientKey) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
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
	}

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
	
	
	private Patient populateByPatientKey(Connection connection, Long patientKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PatientViewGet.populate. patientKey : " + patientKey );}
		
		this.patientKey = patientKey;
		Patient patient = null;
		Person person = null;
		QUERYBYPTNTKEYSQL = TableCacheSingleton.getResource("PatPersInfo.sql");
		preparedStatement = connection.prepareStatement(QUERYBYPTNTKEYSQL);
		preparedStatement.setLong(1, patientKey);
		preparedStatement.setLong(2, patientKey);
		resultSet = preparedStatement.executeQuery();

		patient = new Patient();   //VL99
		person = new Person();     //VL99
/*
		if (resultSet.next()) {
//VL99		patient = new Patient();
			scanBasic(patient);
			scanChildren(connection, patient);
		}
*/
		int i = 1;
		Address thisRow=new Address();
		while( resultSet.next() ) {
			scanBasic(patient, person,thisRow);
			if (i == 1) {scanChildren(connection, patient);}
			i++;
	    }
		 person.getAddress().add(thisRow);
		 if(logger.isInfoEnabled()) {logger.info("EndApiCall: PatientViewGet.populate. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return patient;
	}
	
	private Patient populateByPatientId(Connection connection, String storeNumber, String patientId) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PatientMetricsGet.populate. patientKey : " + patientKey + ", storeNumber : " + storeNumber );}

		/*Patient patient = null;
		Person person = null;*/
		Long patientKey = null;
		Patient patient =null;
		try {
			patientKey = FindUtil.findPatientKey(connection, patientId, storeNumber);
			if(patientKey!=null){//DROP_53 ADDED 11/07
			 patient = populateByPatientKey(connection,patientKey);
			}
		} catch (PatientNotFoundException e) {
			
			e.printStackTrace();
		}
	/*	preparedStatement = connection.prepareStatement(QUERYBYPTNTIDSQL);
		preparedStatement.setString(1, storeNumber);
		preparedStatement.setString(2, patientId);
		resultSet = preparedStatement.executeQuery();*/
		
	/*	patient = new Patient();
		person = new Person(); */
/* VL99:		
		if (resultSet.next()) {
			patientKey = getLong("PTNT_PTNTKEY");
   	  	    patient = new Patient();
			scanBasic(patient, person);
			scanChildren(connection, patient);
		}
*/
		
		/*int i = 1;
		while( resultSet.next() ) {
			scanBasic(patient, person);
			if (i == 1) {scanChildren(connection, patient);}
			i++;
	    }*/

		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PatientMetricsGet.populate. patientKey : " + patientKey + ", storeNumber : " + storeNumber  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return patient;
	}

	
//VL99 Started
	//TE97 added for patientKey set if is null start
	
	private void scanBasic(Patient patient, Person person,Address thisRow) throws ParseException, DatatypeConfigurationException, SQLException, CDRInternalException, NamingException, IOException {
//		Person person = new Person();
//  	Address address = populateAddress();
//  	person.setAddress(address);
		if (getBigDecimal("ADDR_ADDRKEY") != null) {getPersonAdresses(person,thisRow);}
		
		patient.setConsumerId(getString("PTNT_CNSMRID"));
		person.setFirstName(getString("PTNT_FRSTNM"));
		person.setMiddleName(getString("PTNT_MDLNM"));
		person.setLastName(getString("PTNT_LSTNM"));
		patient.setPerson(person);
		patient.setDateOfBirth(CommonUtil.getXMLGregorianCalendarByDate(getDate("PTNT_DTOFBIRTH")));
		patient.setDeceaseddate(CommonUtil.getXMLGregorianCalendarByDate(getDate("PTNT_DCSDDT")));
		patient.setIsactive(CommonUtil.convertYesNoFlagToBoolean(getString("PTNT_PTNTACTIND")));
		patient.setNoKnownAllergyFlag(CommonUtil.convertYesNoFlagToBoolean(getString("PTNT_NOKNWNALRGYFLG")));
		patient.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(getDate("PTNT_CRTTIMESTAMP"), true));
		patient.setLastUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(getDate("PTNT_LSTUPDTTIMESTAMP"), true));
  ///TE97 Commented and uncomment for future implemteation
/*		String salutation=getString("PTNT_SALUTATION");
		if (salutation !=null) {
			person.setSalutation(SalutationType.fromValue(getString("PTNT_SALUTATION")));
		};
		*/
		patient.setMobileNumber(getString("PTNT_MOBILENUM"));
		

		Long genderTypeKey = getLong("PTNT_GNDRTYPKEY");
		if (genderTypeKey != null) {
			String genderTypeCode = getCodeFromKey(LT_GNDRTYP, genderTypeKey);
			PatientGender patientGender = PatientGender.fromValue(genderTypeCode);
			patient.setGenderTypeCode(genderTypeCode);
		};

		Long groupMemberShipKey = getLong("PTNT_GRPMBRSHPTYPKEY");
		if (groupMemberShipKey != null) {
			String groupMemberShipCode = getCodeFromKey(LT_GRPMBRSHPTYP, groupMemberShipKey);
			patient.setGroupmembership(groupMemberShipCode);
		};
        
		//DROP-57 :Language Data Fields (Bilingual) change.
		// LANGUAGECORRESPONDENCE
		Long lang = getLong("PTNT_LANGUAGECORRESPONDENCE");
		String langCode =null;
		if(lang!=null){
		  langCode = getCodeFromKey(LT_LANGUAGES, lang);
		patient.setLanguageCorrespondence(langCode);
		}
		//SIGLANGUAGE
		lang = getLong("PTNT_SIGLANGUAGE");
		if(lang!=null){
		langCode=getCodeFromKey(LT_LANGUAGES,lang );
		patient.setSigLanguage(langCode);
		}
		//MONOGRAPHLANGUAGE
		lang = getLong("PTNT_MONOGRAPHLANGUAGE");
		if(lang!=null){
		langCode=getCodeFromKey(LT_LANGUAGES,lang  );
		patient.setMonographLanguage(langCode);
		}
//		patient.setPatientrefillreminder(); Not available from CustPref
		
		Long patientTypeKey = getLong("PTNT_PTNTTYPKEY");
		if (patientTypeKey != null) {
			String PatientTypeCode = getCodeFromKey(LT_PTNTTYP, patientTypeKey);
			patient.setPatienttype(PatientTypeCode);
		};
		if(this.patientKey ==null && null !=getLong("PTNT_PTNTKEY")){
			setPatientKey(getLong("PTNT_PTNTKEY"));
			}
		
		
		
		
	}
//TE97 added for patientKey set if is null start
	
/*	private void getPersonAdresses(Person person) throws ParseException, DatatypeConfigurationException, SQLException, CDRInternalException, NamingException, IOException {
		    int index = 0;
		    Address prevRow = new Address(); // prev row of SQL ResultSet Address data
		    Address thisRow = new Address(); // this row of SQL ResultSet Address data
		    
	        PopulateJAXB.PopulateAddress46(resultSet, "", thisRow);
	        if ( !(thisRow.getConsumerId() == null || thisRow.getConsumerId().trim().length() == 0)) {
	        	
				if ( PatientUniqAddr.containsKey(thisRow.getConsumerId()) ) {
					index = PatientUniqAddr.get(thisRow.getConsumerId());
					prevRow = person.getAddress().get(index);
					
	                if ( !(prevRow.getAddressLineOne() == null || prevRow.getAddressLineOne().trim().length() == 0)) {
	                	thisRow.setAddressLineOne(prevRow.getAddressLineOne());
	                }

	                if ( !(prevRow.getAddressLineTwo() == null || prevRow.getAddressLineTwo().trim().length() == 0)) {
	                	thisRow.setAddressLineTwo(prevRow.getAddressLineTwo());
	                }
	                
	                if ( !(prevRow.getCityName() == null || prevRow.getCityName().trim().length() == 0)) {
	                	thisRow.setCityName(prevRow.getCityName());
	                }
	                
	                if ( !(prevRow.getCountryCode() == null || prevRow.getCountryCode().trim().length() == 0)) {
	                	thisRow.setCountryCode(prevRow.getCountryCode());
	                }

	                if ( !(prevRow.getPostalCode() == null || prevRow.getPostalCode().trim().length() == 0)) {
	                	thisRow.setPostalCode(prevRow.getPostalCode());
	                }

	                if ( !(prevRow.getProvinceCode() == null )) {
	                	thisRow.setProvinceCode(prevRow.getProvinceCode());
	                }
	                
	                if ( !(prevRow.getEmail() == null || prevRow.getEmail().trim().length() == 0)) {
	                	thisRow.setEmail(prevRow.getEmail());
	                }
	                
	                if ( !(prevRow.getPrimaryPhoneNumber() == null || prevRow.getPrimaryPhoneNumber().trim().length() == 0)) {
	                	thisRow.setPrimaryPhoneNumber(prevRow.getPrimaryPhoneNumber());
	                }

	                if ( !(prevRow.getFaxNumber() == null || prevRow.getFaxNumber().trim().length() == 0)) {
	                	thisRow.setFaxNumber(prevRow.getFaxNumber());
	                }
 
	                if ( !(prevRow.getAlternatePhoneNumber() == null || prevRow.getAlternatePhoneNumber().trim().length() == 0)) {
	                	thisRow.setAlternatePhoneNumber(prevRow.getAlternatePhoneNumber());
	                }

	                if ( !(prevRow.getContactPurposeType() == null )) {
	                	thisRow.setContactPurposeType(prevRow.getContactPurposeType());
	                }
	                
	            	thisRow.setIsActiveAddressFlag(prevRow.isIsActiveAddressFlag());
	            	person.getAddress().set(index, thisRow);
				} else {
					index = (person != null) ? person.getAddress().size() : 0;
			    	PatientUniqAddr.put(thisRow.getConsumerId(), index);
			    	person.getAddress().add(index, thisRow);
				}
//				System.out.println(thisRow.getConsumerId() + "   index = " + index + "  PatientUniqAddr = " + PatientUniqAddr);
				
	        }

	}*/
//VL99 Ended

	
	
	//TE97 added for patientKey set if is null start  and multipe address code commented above use above code for multiple address.
	
		private void getPersonAdresses(Person person,Address thisRow) throws ParseException, DatatypeConfigurationException, SQLException, CDRInternalException, NamingException, IOException {
			  
			  // this row of SQL ResultSet Address data
			    List<String> alterPhonesSet =  new ArrayList<String>();
		//        PopulateJAXB.PopulateAddress46(resultSet, "", thisRow,alterPhonesSet);
		     

		}
	//VL99 Ended
	
	private void scanChildren(Connection connection, Patient patient) throws SQLException, ParseException, DatatypeConfigurationException, NamingException, IOException, CDRInternalException {
		PatientMetricsGet patientMetricsGet = new PatientMetricsGet();
	/*	List<PatientMetrics> patientMetrics = patientMetricsGet.fetch(connection, patientKey);
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
		List<Allergy> allergies = allergyGet.fetch(connection, patientKey);
		if(allergies!=null&&allergies.size() > 0)
			patient.getAllergy().addAll(allergies);*/
	}
}
