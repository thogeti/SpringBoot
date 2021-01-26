package ca.sdm.cdr.jdbc.api.populate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PatientVitalType;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.TelecomType;
import ca.shoppersdrugmart.rxhb.drx.patientservice.PatientService;
import ca.shoppersdrugmart.rxhb.ehealth.ActiveIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.AllergySeverityLevel;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyStatus;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyTest;
import ca.shoppersdrugmart.rxhb.ehealth.BenefitsCardholderRelationship;
import ca.shoppersdrugmart.rxhb.ehealth.ChannelType;
import ca.shoppersdrugmart.rxhb.ehealth.Colour;
import ca.shoppersdrugmart.rxhb.ehealth.CommunicationModeType;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentOverrideReason;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentReasonCode;
import ca.shoppersdrugmart.rxhb.ehealth.ContactPurposeType;
import ca.shoppersdrugmart.rxhb.ehealth.DINType;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.DrugNameAlternative;
import ca.shoppersdrugmart.rxhb.ehealth.EngagementType;
import ca.shoppersdrugmart.rxhb.ehealth.Flavour;
import ca.shoppersdrugmart.rxhb.ehealth.GroupMembership;
import ca.shoppersdrugmart.rxhb.ehealth.IdentificationType;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import ca.shoppersdrugmart.rxhb.ehealth.Language;
import ca.shoppersdrugmart.rxhb.ehealth.LicenseIssuingBodyType;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Manufacturer;
import ca.shoppersdrugmart.rxhb.ehealth.ManufacturerRecall;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;
import ca.shoppersdrugmart.rxhb.ehealth.NonDrugAllergen;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.NoteCategory;
import ca.shoppersdrugmart.rxhb.ehealth.NoteType;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientGender;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;
import ca.shoppersdrugmart.rxhb.ehealth.PatientType;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriberType;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalService;
import ca.shoppersdrugmart.rxhb.ehealth.Province;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;
import ca.shoppersdrugmart.rxhb.ehealth.ReactionSeverityLevel;
import ca.shoppersdrugmart.rxhb.ehealth.ReauthFaxFlag;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.ResponsiblePerson;
import ca.shoppersdrugmart.rxhb.ehealth.RouteOfAdmin;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import ca.shoppersdrugmart.rxhb.ehealth.Schedule;
import ca.shoppersdrugmart.rxhb.ehealth.Shape;
import ca.shoppersdrugmart.rxhb.ehealth.SubjectReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.SalutationType;
import ca.shoppersdrugmart.rxhb.ehealth.TransactionSourceChannel;
import ca.shoppersdrugmart.rxhb.ehealth.TxFillStatus;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL46 2017-12-21   NTT Data     Vlad Eidinov  SQL Optimization to improve 
                                             GetPatientByQueryCriteria performance

TE99                                 
*/


public class PopulateJAXB {
	final static Logger logger = LogManager.getLogger(PopulateJAXB.class.getName());
    static	String jndiVersion ;
	
	public PopulateJAXB() {
		jndiVersion = TableCacheSingleton.JNDI_VERSION;
	}
	
	/*
		rs       : is the result set
		postfix  : is the postfix that oracle will generate automatically for the dublicate column names. like "ptntAllergy_alias"
	*/
	
	
	/****************************************************************************************************/
	// Populate Patient
	/**
	 * @throws IOException 
	 * @throws NamingException 
	 * **************************************************************************************************/

	public static Patient populatePatient(ResultSet resultSet, String patientAlias  /*, String custPrefAlias */) throws SQLException , ParseException, DatatypeConfigurationException, CDRInternalException, IOException, NamingException {
		Patient patient  = new Patient ();
		Person person = new Person();
			
		/* Person table fields */
		
		patient.setConsumerId(( resultSet.getString( patientAlias + "_CnsmrId".toUpperCase() ) ) );
		person.setFirstName( resultSet.getString( patientAlias + "_FrstNm".toUpperCase() ) );
		person.setMiddleName( resultSet.getString( patientAlias + "_MDLNM".toUpperCase() ) ); //TE99 Added Middle Name.
		person.setLastName( resultSet.getString( patientAlias + "_LstNm".toUpperCase() ) );
		
		//Temporary fix for Drop 50
		/*String salut = resultSet.getString(patientAlias + "_SALUTATION");
		if (salut != null) { 
		   person.setSalutation(SalutationType.fromValue(salut));
		}*/
		
		/* patient table fields */
		patient.setCreateTimestamp( CommonUtil.getXMLGregorianCalendar( resultSet.getDate( patientAlias + "_CrtTimestamp".toUpperCase() ) , true ) );
		patient.setDateOfBirth( CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate( patientAlias + "_DtOfBirth".toUpperCase()  ) )  );
		patient.setDeceaseddate(  CommonUtil.getXMLGregorianCalendarByDate( resultSet.getDate( patientAlias + "_DcsdDt".toUpperCase()  )  )  );
		
		String gndrTypCdDescr =  null;
		PatientGender patientGender = null;
		
		gndrTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_GNDRTYP , ResultSetWrapper.getKey(resultSet, patientAlias, "_GndrTypKey"));
		if( gndrTypCdDescr != null )
		{
			patientGender = PatientGender.fromValue(gndrTypCdDescr);
			patient.setGenderTypeCode(patientGender);
		}
		

		Long GroupMemberShipKey = ResultSetWrapper.getKey(resultSet , patientAlias, "_GrpMbrshpTypKey");
		if( GroupMemberShipKey != null && GroupMemberShipKey != 0L)
		{
			String GrpMbrshpTypCdDescr =  null;
			GrpMbrshpTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_GRPMBRSHPTYP , GroupMemberShipKey);
			patient.setGroupmembership( GroupMembership.fromValue(GrpMbrshpTypCdDescr) );
		}
		
		patient.setIsactive( CommonUtil.convertYesNoFlagToBoolean(resultSet.getString( patientAlias + "_PtntActInd".toUpperCase()  ) )  );
		patient.setLastUpdateTimestamp( CommonUtil.getXMLGregorianCalendar( resultSet.getDate( patientAlias + "_LstUpDtTimestamp".toUpperCase() ) , true  )  );
		patient.setNoKnownAllergyFlag( CommonUtil.convertYesNoFlagToBoolean( resultSet.getString( patientAlias + "_noKnwnAlrgyFlg".toUpperCase()  ) )  );
		String PtntTypCdDescr =  null;
		PtntTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PTNTTYP, ResultSetWrapper.getKey(resultSet, patientAlias, "_PtntTypKey"));

		if( PtntTypCdDescr != null )
		{
			patient.setPatienttype( PatientType.fromValue(PtntTypCdDescr) );
		}
		//DROP-57 :Language Data Fields (Bilingual) change.
		// LANGUAGECORRESPONDENCE
		String lang = null;
		lang = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_LANGUAGES, ResultSetWrapper.getKey(resultSet, patientAlias, "_languagecorrespondence"));
		if(lang!=null){
		patient.setLanguageCorrespondence(Language.fromValue(lang));
		}
		
		//SIGLANGUAGE
		lang=TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_LANGUAGES, ResultSetWrapper.getKey(resultSet, patientAlias, "_siglanguage"));
		if(lang!=null){
		patient.setSigLanguage(Language.fromValue(lang));
		}
		//MONOGRAPHLANGUAGE
		lang=TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_LANGUAGES, ResultSetWrapper.getKey(resultSet, patientAlias, "_monographlanguage"));
		if(lang!=null){
		patient.setMonographLanguage(Language.fromValue(lang));
		}
		//Temporary fix for Drop 50
		patient.setMobileNumber( resultSet.getString( patientAlias + "_MOBILENUM".toUpperCase() ) ); //Drop56
		patient.setPerson(person);
		/* CustPref fields */
		
		/*********************************************
		 * Change request to remove CustPref 
		 *********************************************/		
/*		String RflRmndr = resultSet.getString( custPrefAlias + "_RflRmndr".toUpperCase()  );
		if( RflRmndr!= null )
		{
			try {
				patient.setPatientrefillreminder( OptInOptions.fromValue(RflRmndr) );
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		}
*/		
		
		
		return patient;
	}
	
	
	public static PatientIdentification populatePatientIdentification(ResultSet rs, String patientIdAlias ) throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException 
	{
		
		PatientIdentification patientIdentification = null ;
		Long patientIdKey = rs.getLong(patientIdAlias + "_PTNTIDKEY");
		if( patientIdKey == null || patientIdKey <= 0 )
			return null;
		
		patientIdentification = new PatientIdentification();
		
		String idNumber = rs.getString(patientIdAlias + "_IDNUM");
		
		String issuingAuthorityName = rs.getString(patientIdAlias + "_ISSNGATHRTYNM");

		Long idTypeKey = rs.getLong(patientIdAlias + "_IDTYPKEY");
		
		if( idNumber == null && issuingAuthorityName == null && ( idTypeKey == null || idTypeKey <= 0 ))
		{
			// the PatientIdentigficationNumber has no actual value in the xml, and must return null
			return null;
		}

		patientIdentification.setIdentificationNumber(idNumber);
		patientIdentification.setIssuingAuthorityName(issuingAuthorityName);
		if ( idTypeKey != null && idTypeKey> 0 )
		{
			String idTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_IDTYP, idTypeKey );

			IdentificationType identificationType = (idTypeCode!=null) ? IdentificationType.fromValue(idTypeCode) : null;
	
			patientIdentification.setIdentificationTypeCode(identificationType);
		}
		
		return patientIdentification;
		
		
	}
	
	
	public static Prescriber populatePrescriber(ResultSet rs, String prescriberAlias , String personAlias) throws SQLException, CDRInternalException 
	{
		Prescriber prescriber  = new Prescriber();
		Person person = new Person();
		
		String prescriberTypeStr = rs.getString(prescriberAlias + "_PRSCBRTYPKEY");
		PrescriberType prescriberType = (prescriberTypeStr!=null) ? PrescriberType.valueOf(prescriberTypeStr.toUpperCase()) : null;
		prescriber.setPrescriberTypeCode(prescriberType);
			
		/* Person table fields */
		
		prescriber.setConsumerId(( rs.getString( personAlias + "_CnsmrId".toUpperCase() ) ) );
		prescriber.setProducerId( rs.getString( personAlias + "_PrdcrId".toUpperCase() ) );
		
		person.setFirstName( rs.getString( personAlias + "_FrstNm".toUpperCase() ) );
		person.setLastName( rs.getString( personAlias + "_LstNm".toUpperCase() ) );

		/* prescriber table fields */

		prescriber.setIsActiveFlag( CommonUtil.convertYesNoFlagToBoolean(rs.getString( prescriberAlias + "_ISACTFLG") )  );

		prescriber.setPerson(person);
		
		return prescriber;
	}	
	
	
	public static Prescriber populatePrescriberFromView(ResultSet rs, String personRoleViewAlias) throws SQLException, CDRInternalException 
	{
		Prescriber prescriber  = new Prescriber();
		
		
		String prescriberTypeStr = ResultSetWrapper.getString(rs , personRoleViewAlias + "_PRSCBRTYP_PRSCBRTYPKEY");
		PrescriberType prescriberType = (prescriberTypeStr!=null) ? PrescriberType.valueOf(prescriberTypeStr.toUpperCase()) : null;
		prescriber.setPrescriberTypeCode(prescriberType);
			
		/* Person table fields */
		
		prescriber.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID"));
		prescriber.setProducerId( ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID") );
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );

		/* prescriber table fields */

		prescriber.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSNRL_ISACTFLG"))  );

		prescriber.setPerson(person);
		
		return prescriber;
	}	
	
	public static ResponsiblePerson populateResponsiblePerson(ResultSet rs, String personAlias) throws SQLException , ParseException, DatatypeConfigurationException, IOException
	{
		ResponsiblePerson responsiblePerson  = new ResponsiblePerson();
		Person person = new Person();

		/* Person table fields */
		
		responsiblePerson.setConsumerId(( rs.getString( personAlias + "_CnsmrId".toUpperCase() ) ) );
		responsiblePerson.setProducerId( rs.getString( personAlias + "_PrdcrId".toUpperCase() ) );
		
		person.setFirstName( rs.getString( personAlias + "_FrstNm".toUpperCase() ) );
		person.setLastName( rs.getString( personAlias + "_LstNm".toUpperCase() ) );

		/* responsiblePerson table fields */
		responsiblePerson.setPerson(person);
		
		return responsiblePerson;
	}	
	
	
	
	
	public static PatientMedicalCondition populatePatientMedicalCondition(ResultSet rs, String patientMedicalConditionAlias ) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		PatientMedicalCondition patientMedicalCondition = null;
		String  patientMedicalConditionKey = rs.getString(patientMedicalConditionAlias + "_PtntMdclCndtnKey".toUpperCase());
		if( patientMedicalConditionKey != null )
		{
			patientMedicalCondition = new PatientMedicalCondition();

			patientMedicalCondition.setConditionStartDate( CommonUtil.getXMLGregorianCalendarByDate( rs.getDate(patientMedicalConditionAlias + "_CndtnStrtDt".toUpperCase() )  ) ); 
			patientMedicalCondition.setConditionDescription(rs.getString(patientMedicalConditionAlias + "_CndtnDescr".toUpperCase()));
			patientMedicalCondition.setConditionEndDate(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate(patientMedicalConditionAlias + "_CndtnEndDt".toUpperCase()) ) );
			patientMedicalCondition.setChronicConditionFlag( CommonUtil.convertYesNoFlagToBoolean( rs.getString(patientMedicalConditionAlias + "_chronicCndtnFlg".toUpperCase()) ) );
			patientMedicalCondition.setConditionCreateTimestamp( CommonUtil.getXMLGregorianCalendar(rs.getDate(patientMedicalConditionAlias + "_CndtnCrtTimestamp".toUpperCase() ) , true ) );
			patientMedicalCondition.setConditionActiveFlag( CommonUtil.convertYesNoFlagToBoolean( rs.getString(patientMedicalConditionAlias + "_CndtnActFlg".toUpperCase()) ) );
			patientMedicalCondition.setProducerId(rs.getString(patientMedicalConditionAlias + "_PrdcrId".toUpperCase()));
			patientMedicalCondition.setConsumerId(rs.getString(patientMedicalConditionAlias + "_CnsmrId".toUpperCase()));

			
			/*  
			 * not required. only for HW display
			 *  
			patientMedicalCondition.setConditionTypeDisplay();
			patientMedicalCondition.setUpdateReason( rs.getString(patientMedicalConditionAlias + "_".toUpperCase()));
			patientMedicalCondition.setUpdateReasonType();
			patientMedicalCondition.setUpdateRecorderTimestamp();
			patientMedicalCondition.setUpdateTimeStamp();
			
			 */
			
		}
		
		return patientMedicalCondition;
	}
	
	
	/****************************************************************************************************/
	// Populate Allergy
	/**
	 * @throws IOException 
	 * @throws NamingException **************************************************************************************************/

	public static Allergy populateAllergy(ResultSet rs, String alias) throws SQLException , ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException
	{
		Allergy allergy = new Allergy ();
			
//		allergy.setAllergen(rs.getString("AllrgnTyp_CdDescr".toUpperCase() ));
		allergy.setAllergyActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString( alias + "_AlrgyActFlg".toUpperCase()  ) ) );
		
		String allergyConfStatCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ALRGYCONFSTATTYP, rs.getLong(alias+"_AlrgyConfStatTypKey".toUpperCase()) );
		if( allergyConfStatCode != null )
		{
			allergy.setAllergyConfirmationStatusCode(AllergyStatus.fromValue(allergyConfStatCode));
		}
		
		String allergySeverityCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ALRGYSVRTYLVLTYP, rs.getLong(alias+"_AlrgySvrtyLvlTypKey".toUpperCase()) );
		if( allergySeverityCode != null )
		{
			allergy.setAllergySeverityCode(AllergySeverityLevel.fromValue(allergySeverityCode));
		}
		


		
		
		allergy.setAllergyStartDate(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate( alias + "_AlrgyStrtDt".toUpperCase() ) ) );
		
		
		String alrgyTypCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ALRGYTYP, rs.getLong(alias+"_AlrgyTypKey".toUpperCase()) );
		if( alrgyTypCode != null )
		{
			allergy.setAllergyType( alrgyTypCode );
		}

		allergy.setConsumerId(rs.getString( alias + "_CnsmrId".toUpperCase() ));
		allergy.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate( alias + "_CrtTimestamp".toUpperCase() ) , true ) );
		allergy.setDescriptionEnglish(rs.getString( alias +  "_DescrEng".toUpperCase() ));
		allergy.setDescriptionFrench(rs.getString( alias +  "_DescrFr".toUpperCase() ));
		allergy.setIntoleranceFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString( alias + "_intoleranceFlg".toUpperCase() ) ));

		allergy.setNonDrugAllergen(NonDrugAllergen.fromValue(rs.getString(alias+"_NonDrgAllrgn".toUpperCase() ) ) );
		
		allergy.setProducerId(rs.getString( alias +  "_PrdcrId".toUpperCase() ));
		allergy.setReportedDate(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate( alias + "_RptDt".toUpperCase() ) ) );
		allergy.setUpdateReason(rs.getString( alias + "_upDtRsn".toUpperCase() ));
		allergy.setUpdateTimestamp( CommonUtil.getXMLGregorianCalendar(rs.getDate( alias + "_upDtTimestamp".toUpperCase() ) , true ) );
		
		return allergy;
	}
	
	/****************************************************************************************************/
	// Populate AllergyTest
	/**
	 * @throws NamingException **************************************************************************************************/
	
	public static AllergyTest populateAllergyTest(ResultSet rs, String alias) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		AllergyTest allergyTest = null;
		String  PtntAlrgyKey = rs.getString(alias + "_PtntAlrgyKey".toUpperCase());
		if( PtntAlrgyKey != null )
		{
			allergyTest = new AllergyTest();
			
			allergyTest.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase()));
			allergyTest.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase()));
			allergyTest.setTestDatetime(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_TstDttime".toUpperCase()) , true ) );
			allergyTest.setTestResult(rs.getString(alias + "_TstRslt".toUpperCase()));
			

			String allergyTestTypCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ALRGYTSTTYP, rs.getLong(alias+"_AlrgyTstTypKey".toUpperCase()) );
			if( allergyTestTypCode != null )
			{
				allergyTest.setTestType(allergyTestTypCode);
			}

			
		}
		
		return allergyTest;
		
	}
	
	/****************************************************************************************************/
	// Populate Note
	/**
	 * @throws SQLException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRInternalException **************************************************************************************************/

	public static PatientMetrics populatePatientMetrics(ResultSet rs, String ptntMtrcsAlias , PatientMetrics  patientMetrics ) throws SQLException, ParseException, DatatypeConfigurationException, NamingException, IOException, CDRInternalException
	{
//		PatientMetrics metrics = null ;
		long  PtntMtrcsKey = rs.getLong( ptntMtrcsAlias + "_PtntMtrcsKey");

		if( PtntMtrcsKey != 0 )
		{
//			metrics = new PatientMetrics();
			
			patientMetrics.setConsumerId(rs.getString(ptntMtrcsAlias + "_CnsmrId".toUpperCase()) );
			patientMetrics.setProducerId(rs.getString(ptntMtrcsAlias + "_PrdcrId".toUpperCase()) );
			patientMetrics.setVitalsDate(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate(ptntMtrcsAlias + "_PtntVtlDt".toUpperCase())) ) ;
			long ptntVtlTypKey = ResultSetWrapper.getKey(rs, ptntMtrcsAlias, "_PTNTVTLTYPKEY");
			String ptntVtlTypCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PTNTVTLTYP, ptntVtlTypKey); 

			if( ptntVtlTypCode.equals(PatientVitalType.Glucose.value() ) )
			{
				patientMetrics.setBloodGlucose(ResultSetWrapper.getBigDecimal(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
				patientMetrics.setBloodGlucoseAutomaticMeasurementFlag(ResultSetWrapper.getBoolean(rs, ptntMtrcsAlias, "_AUTMTCLYMSRDFLG"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Body_Temperature.value() ) )
			{
				patientMetrics.setBodyTemperature(ResultSetWrapper.getBigDecimal(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Diastolic_Blood_Pressure.value() ) )
			{
				patientMetrics.setDiastolicBloodPressure(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			
			if( ptntVtlTypCode.equals(PatientVitalType.HeartRate.value() ) )
			{
				patientMetrics.setHeartRate(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Height.value() ) )
			{
				patientMetrics.setHeight(ResultSetWrapper.getBigDecimal(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
				patientMetrics.setHeightDate(ResultSetWrapper.getCalendar(rs, ptntMtrcsAlias, "_PTNTVTLDT"));
				patientMetrics.setHeightMeasurementFlag(ResultSetWrapper.getBoolean(rs, ptntMtrcsAlias, "_AUTMTCLYMSRDFLG"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Respiratory_Rate.value() ) )
			{
				patientMetrics.setRespiratoryRate(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Systolic_Blood_Pressure.value() ) )
			{
				patientMetrics.setSystolicBloodPressure(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			
			if( ptntVtlTypCode.equals(PatientVitalType.Systolic_Blood_Pressure.value() ) )
			{
				patientMetrics.setSystolicBloodPressure(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Systolic_Blood_Pressure.value() ) )
			{
				patientMetrics.setSystolicBloodPressure(ResultSetWrapper.getBigInteger(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
			}
			if( ptntVtlTypCode.equals(PatientVitalType.Weight.value() ) )
			{
				patientMetrics.setWeight(ResultSetWrapper.getBigDecimal(rs, ptntMtrcsAlias, "_PTNTVTLVALUE"));
				patientMetrics.setWeightDate(ResultSetWrapper.getCalendar(rs, ptntMtrcsAlias, "_PTNTVTLDT"));
				patientMetrics.setWeightMeasurementFlag(ResultSetWrapper.getBoolean(rs, ptntMtrcsAlias, "_AUTMTCLYMSRDFLG"));

			}
		}
		return patientMetrics;
	
	}
	
	public static Note populateNote(ResultSet rs, String alias , String noteKey) throws SQLException, ParseException, DatatypeConfigurationException, IOException, CDRInternalException, NamingException
	{
		
		/* note can be for any entity. to be bale to find the key from the table , the noteKey must be passed to this function.
		 * for instance if populating for 
		 * Patient note ---- > noteKey = "PtntNtKey"
		 * Allergy Note ---- > noteKey = "PtntAlrgyNyKey"
		 * and so on for any other notes table
		 */
		Note note = null; 
		String  noteKeyValue = rs.getString(alias + "_" + noteKey.toUpperCase());
		if( noteKeyValue != null )
		{
	
			
			note = new Note();
			
			note.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())); 
			note.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_CrtTimestamp".toUpperCase()) , true ) ); 
			note.setCreateUserId(rs.getString(alias + "_CrtUsrId".toUpperCase())); 
			note.setIsPharmacist(CommonUtil.convertYesNoFlagToBoolean(rs.getString(alias + "_isPhrmcst".toUpperCase())) );
			
			String ntCtgryTypCdDescr = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_NTCTGRYTYP, ResultSetWrapper.getKey(rs, alias, "_NtCtgryTypKey"));
			note.setNoteEnglish(rs.getString(alias + "_NtEng".toUpperCase())); 
			note.setNoteFrench(rs.getString(alias + "_NtFr".toUpperCase())); 
			

			try	{
				NoteCategory ntCtgryTypCd = (ntCtgryTypCdDescr!=null) ? NoteCategory.fromValue(ntCtgryTypCdDescr) : null;
				note.setNoteCategory(ntCtgryTypCd); 
			} catch (Exception ex) {
				
			}

			
			try
			{
				/* 
				 * Sharooz Shiran :
				 * Important : this column is not defined in all Notes table, therefore the "Column not found" SQLException must be handled and ignored.
				 * 
				 */
				Long ntTypKey = ResultSetWrapper.getKey(rs, alias, "_" + "NtTypKey");
				if ( ntTypKey != null && ntTypKey > 0) {
					String ntTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_NTTYP, ntTypKey);
					NoteType ntTypCd = (ntTypCdDescr!=null) ? NoteType.fromValue(ntTypCdDescr) : null;
					note.setNoteTypeCode(ntTypCd);
					
				}
				
			} catch(Exception e) {
				
			}
			
			note.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())); 
			note.setUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_upDtTimestamp".toUpperCase()) , true ) ); 
		}
		
		
		return note ;
	}


	/****************************************************************************************************/
	// Populate Reaction
	/**
	 * @throws NamingException **************************************************************************************************/
	public static Reaction populateReaction(ResultSet rs, String alias) throws CodeNotFoundFromTableCacheException, IOException, SQLException, ParseException, DatatypeConfigurationException, NamingException 
	{
		Reaction reaction = new Reaction();
		
		//reaction.setExposedMaterialName(value)
//		reaction.setExposureMethod( rs.getLong(alias + "_SubRctnTypKey") ); 
		reaction.setProducerid(rs.getString(alias + "_PrdcrId".toUpperCase()));
		
		String subRctnTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_SUBRCTNTYP, ResultSetWrapper.getKey(rs, alias, "_SubRctnTypKey"));
		reaction.setReactionCode(SubjectReaction.fromValue(subRctnTypCdDescr));
		
		reaction.setReactionDescriptionEnglish(rs.getString(alias + "_RctnDescrEng".toUpperCase()));
		
		String rctnSvrtyTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RCTNSVRTYTYP, ResultSetWrapper.getKey(rs, alias, "_RctnSvrtyTypKey"));
		reaction.setReactionSeverityCode(ReactionSeverityLevel.fromValue(rctnSvrtyTypCdDescr));
		reaction.setReactionStartTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_RctnStrtTimestamp".toUpperCase()) , true ) );
		return reaction ; 
	}


	
	/****************************************************************************************************/
	// Populate ProfessionalService  added by VLAD on 20160624 
	/****************************************************************************************************/
	public static ProfessionalService populateProfService(ResultSet rs, String alias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException { 
       //!!! TODO: perhaps have to populate "observationTypeCode" via TableCacheSingleton
		
	   ProfessionalService Prfservice = new ProfessionalService();

	   Prfservice.setConsultationTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_CNSLTTNTIMESTAMP") , true ) );
	   
	// BigDecimal tmpBigDecimal = new BigDecimal(5.0d);	   
	   BigDecimal tmpBigDecimal = rs.getBigDecimal(alias + "_CNSLTTNLENGTH");
	   BigInteger tmpBigInteger = (tmpBigDecimal!=null) ? tmpBigDecimal.toBigInteger() : null;
	   Prfservice.setConsultationLength(tmpBigInteger);

	   Prfservice.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
	   Prfservice.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
	   
	   String obsTypeCode = rs.getString(alias + "_OBSRVTNTYPCD");
//	   ServiceType observationTypeCode = (obsTypeCode!=null) ? ServiceType.fromValue(obsTypeCode) : null;
//	   Prfservice.setObservationTypeCode(observationTypeCode);
	   Prfservice.setObservationTypeCode(obsTypeCode);

	   return Prfservice;
	}	
	

	
	
	
	
	/****************************************************************************************************/
	// Populate Dispense  added by VLAD on 20160622   + CURR
	/**
	 * @throws NamingException **************************************************************************************************/
	public static Dispense populateDispense(ResultSet rs, String alias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException 
	{
	   Dispense dispense = new Dispense();

	   String packageForm = populatePackageForm(rs, alias);
	   dispense.setPackageForm(packageForm);
		   
	   dispense.setAdditionalDosageInstructionText(rs.getString(alias + "_ADDTNLDSGINSTRCTNTXT"));
	   dispense.setAdministrationPeriodEnd(CommonUtil.getXMLGregorianCalendarByDate(rs.getDate(alias + "_ADMINPRDENDDT") ) );
	   dispense.setDaysSupply(rs.getInt(alias + "_DAYSSPLY"));
	   dispense.setAdministrationPeriodStart( CommonUtil.getXMLGregorianCalendarByDate(rs.getDate(alias + "_ADMINPRDSTRTDT") ) );
	   dispense.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_CRTTIMESTAMP") , true ) );

	   dispense.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
	   dispense.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
	   dispense.setTransactionCancellationReasonText(rs.getString(alias + "_TXNCANCLRSNTXT"));

	   dispense.setLastUpdate(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_LSTUPDT") , true ) );
	   dispense.setPickupTime(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_PCKUPTIME") , true ) );//CR_63
	   dispense.setPickupTime2(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_LASTPICKUPTIME") , true ) );//CR_63
	   
	   dispense.setQuantityDispensed(rs.getBigDecimal(alias + "_QTYTXD") ) ;
	   dispense.setTotalAmountPaid(rs.getBigDecimal(alias + "_TOTALAMTPAID") ) ;
	   String txNum = rs.getString(alias + "_TXNNUM");
	   Integer txNumber = null;
	   try {
		   txNumber = new Integer(txNum);
	   } catch (NumberFormatException nfe) {
	   }
	   dispense.setTransactionNumber(txNumber);
	   dispense.setDispenseTime(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_TXTIME") , true ) );	   
	   dispense.setDaysInterval(rs.getInt(alias + "_DAYSINTRVL") );
	   dispense.setDosageInstructionExpiryDate(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_DRGEXPRTNDT") , true ) );	   
	   dispense.setDrugLotNumber(rs.getString(alias + "_DRGLOTNUM"));
	   dispense.setDrugExpirationDate(CommonUtil.getXMLGregorianCalendar(rs.getDate(alias + "_DRGEXPRTNDT") , true ) );
	   dispense.setSigCode(rs.getString(alias + "_SIGCD"));
	   dispense.setSigDescriptionPatientLanguage(rs.getString(alias + "_SIGDESCRPTNTLANG"));
	   dispense.setSequenceNumber(rs.getInt(alias + "_SEQNUM") );
	   dispense.setPreviousDispenseDaysSupply(rs.getInt(alias + "_PREVTXDAYSSPLY") );
	   dispense.setQuantityRemaining(rs.getInt(alias + "_QTYRMNG") );
	   
	   Long transactionSourceChannelKey = ResultSetWrapper.getLong(rs, alias, "TXNSRCECHNLTYPKEY");
	   String transactionSourceChannelCode = null;
	   if (transactionSourceChannelKey!=null && transactionSourceChannelKey.longValue() > 0)
		   transactionSourceChannelCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_TXNSRCECHNLTYP, transactionSourceChannelKey);
	   TransactionSourceChannel transactionSourceChannel = (!StringUtil.isEmpty(transactionSourceChannelCode)) ? TransactionSourceChannel.fromValue(transactionSourceChannelCode) : null;
	   dispense.setTransactionSourceCode(transactionSourceChannel);
	   
	   PharmacyChannel pharmacyChannel = populatePharmacyChannel(rs, alias);
	   dispense.setPharmacyChannel(pharmacyChannel);
	   
	   Long routeOfAdminKey = rs.getLong(alias + "_RteOfAdmin_CeRXTypKey");
	   String routeOfAdminCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
	   RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
	   dispense.setAdministrationRouteCode(routeOfAdmin);
	   
//	   String storeNum = rs.getString(alias + "_STORENUM");
//	   Integer storeNumber = null;
//	   if (storeNum!=null) try {
//		   storeNumber = new Integer(storeNum);
//	   } catch (NumberFormatException nfe) {
//	   }
//	   dispense.setSequenceNumber(storeNumber);
	   
	   Long discontinuedReasonKey = rs.getLong(alias + "_DISCNTDRSNTYPKEY");
	   String discontinuedReasonCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DISCNTDRSNTYP, discontinuedReasonKey);
	   dispense.setDiscontinuedReasonCode(discontinuedReasonCode);
	   
	   Long txTypeKey = rs.getLong(alias + "_TXTYPKEY");
	   String dispenseTypeCode = null;
	   if (txTypeKey!=null && txTypeKey.longValue() > 0)
		   dispenseTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_TXTYP, txTypeKey);
	   dispense.setDispenseType(dispenseTypeCode);		   
	   
	   return dispense;
	}	

	
	
	
	
	
	/**
	 * 
	 * @param rs
	 * @param alias
	 * @return
	 * @throws SQLException
	 * @throws CodeNotFoundFromTableCacheException
	 * @throws NamingException
	 * @throws IOException
	 */
	public static PharmacyChannel populatePharmacyChannel(ResultSet rs, String alias) throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException {

		Long channelTypeKey = ResultSetWrapper.getKey(rs, alias, "PharmacyChnlTypKey");
		String channelTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PHARMACYCHNLTYP, channelTypeKey);
		ChannelType channelType = (channelTypeCode!=null) ? ChannelType.fromValue(channelTypeCode) : null;
				
		Long commModeTypeKey = ResultSetWrapper.getKey(rs, alias, "CommModeTypKey");
		String commModeTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_COMMMODETYP, commModeTypeKey);
		CommunicationModeType commMode = (commModeTypeCode!=null) ? CommunicationModeType.fromValue(commModeTypeCode) : null;

		Long engagementTypeKey = ResultSetWrapper.getKey(rs, alias, "EngmntTypKey");
		String engagementTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ENGMNTTYP, engagementTypeKey);
		EngagementType engagementType = (engagementTypeCode!=null) ? EngagementType.fromValue(engagementTypeCode) : null;
		
		// if none of the Pharmacy Channel's fields are populated, avoid generation of pharmacyChannel element. 
		if (channelType==null && commMode==null && engagementType==null) {
			return null;
		} else {
			PharmacyChannel pharmacyChannel = new PharmacyChannel();
			pharmacyChannel.setChannelType(channelType);
			pharmacyChannel.setCommunicationMode(commMode);
			pharmacyChannel.setEngagment(engagementType);
			return pharmacyChannel;
		}
	}	
	
	
	
	/**
	 * Get populated Prescription object.
	 * 
	 * @return				Populated Prescription object.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException 
	 * @throws CDRInternalException 
	 * @throws CDRDataException
	 */
	public static Prescription populatePrescription(ResultSet rs, String rxAlias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Prescription prescription = new Prescription();
		
		prescription.setUpdateTimestamp(ResultSetWrapper.getCalendar(rs, rxAlias, "_UPDTTIMESTAMP"));
		prescription.setIsAuthoritative(ResultSetWrapper.getBoolean(rs, rxAlias, "ISATHRTV"));
		prescription.setConsumerId(ResultSetWrapper.getString(rs, rxAlias, "CNSMRID"));
		prescription.setProducerId(ResultSetWrapper.getString(rs, rxAlias, "PRDCRID"));
		prescription.setQuantityDispensed(ResultSetWrapper.getBigDecimal(rs, rxAlias, "QTYTXD"));
		prescription.setRemainingQuantity(ResultSetWrapper.getBigDecimal(rs, rxAlias, "RMNGQTY"));
		prescription.setPrescriptionNumber(ResultSetWrapper.getInt(rs, rxAlias, "RXNUM"));
		prescription.setRefillsRemainingCount(ResultSetWrapper.getInt(rs, rxAlias, "RFLSRMNGCNT"));
		prescription.setTotalQuantityAuthorized(ResultSetWrapper.getBigDecimal(rs, rxAlias, "TOTALQTYATHRZD"));
		String totalDaysOfSupplyCountDb = ResultSetWrapper.getString(rs, rxAlias, "DRGTOTALDAYSOFSPLYCNT");
		Integer totalDaysOfSupplyCount = null;
		try {
			totalDaysOfSupplyCount = (totalDaysOfSupplyCountDb!=null) ? new Integer(totalDaysOfSupplyCountDb) : null;
		} catch (Exception nfe) {
		}	
		prescription.setDrugTotalDaysOfSupplyCount(totalDaysOfSupplyCount);
		prescription.setDrugTrialDaysOfSupplyCount(ResultSetWrapper.getInt(rs, rxAlias, "DRGTRLDAYSOFSPLYCNT"));
		prescription.setDrugTrialFlag(ResultSetWrapper.getBoolean(rs, rxAlias, "DRGTRLFLG"));
		prescription.setDrugTrialQuantity(ResultSetWrapper.getInt(rs, rxAlias, "DRGTRLQTY"));
		prescription.setInferredPrescriptionFlag(ResultSetWrapper.getBoolean(rs, rxAlias, "INFRDRXFLG"));
		prescription.setNumberOfRefills(ResultSetWrapper.getInt(rs, rxAlias, "NUMOFRFLS"));
		prescription.setPrescriptionDate(ResultSetWrapper.getCalendar(rs, rxAlias, "RXDT"));
		prescription.setPrescriptionExpirationDate(ResultSetWrapper.getCalendar(rs, rxAlias, "RXEXPRTNDT"));
		prescription.setSIGAdditionalInformation(ResultSetWrapper.getString(rs, rxAlias, "SIGADDTNLINFRMATION"));
		prescription.setSIGDescriptionPatientLanguage(ResultSetWrapper.getString(rs, rxAlias, "SIGDESCRPTNTLANG"));
		prescription.setPrescriptionDispensableFlag(ResultSetWrapper.getBoolean(rs, rxAlias, "RXDSPNSBLFLG"));
		prescription.setLegacyFlag(ResultSetWrapper.getBoolean(rs, rxAlias, "LEGACYFLG"));
		prescription.setHoldEndDate(ResultSetWrapper.getCalendar(rs, rxAlias, "HLDENDDT"));
		prescription.setCreateTimestamp(ResultSetWrapper.getCalendar(rs, rxAlias, "CRTTIMESTAMP"));
		prescription.setQuantityPrescribed(ResultSetWrapper.getBigDecimal(rs, rxAlias, "QTYRXD"));
		//String treatmentType =  TableCacheSingleton.getInstance().getCodeFromKey(LT_TRTMNTTYP , ResultSetWrapper.getKey(rs, "RX", "_TRTMNTTYPKEY"));
		//prescription.setTreatmentType(TreatmentType.fromValue(treatmentType));
		return prescription;
	}
	
	
	

	/****************************************************************************************************/
	// Populate Compound added by VLAD on 20160622 
	/****************************************************************************************************/
	public static Compound populateCompound(ResultSet rs, String alias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException,NamingException
	{
		Compound compound = null ;
		String  compundKeyValue = rs.getString(alias + "_CmpndKey".toUpperCase());
		if( compundKeyValue != null )
		{
			compound = new Compound();

			compound.setUnitOfMeasure(rs.getString(alias + "_CMPNDUOM"));
			
			// BigDecimal tmpBigDecimal = new BigDecimal(5.0d);	   
			BigDecimal tmpBigDecimal = rs.getBigDecimal(alias + "_UOMQTYCONVERFCTR");
			BigInteger tmpBigInteger = (tmpBigDecimal!=null) ? tmpBigDecimal.toBigInteger() : null;			
			compound.setUofMQuantityConversionFactor(tmpBigInteger);

			compound.setNameFrench(rs.getString(alias + "_NMFR"));
			compound.setNameEnglish(rs.getString(alias + "_NMENG"));
			compound.setPreperationdirections(rs.getString(alias + "_PREPDIRCTN"));
			compound.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
			compound.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
			compound.setQuantity(rs.getBigDecimal(alias + "_QTY"));
			//added 60 prescribedCompound
			Long routeOfAdminKey = rs.getLong(alias + "_RteOfAdmin_CeRXTypKey");
			   String routeOfAdminCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
			   RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
			   compound.setAdministrationRouteCode(routeOfAdmin);
			//added 60  prescribedCompound
		
		}
		
	    return compound;
	}	
	

	
	
	/****************************************************************************************************/
	// Populate populateCompIngr added by VLAD on 20160622 
	/**
	 * @throws NamingException 
	 * **************************************************************************************************/
	public static CompoundIngredient populateCompIngr(ResultSet rs, String alias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException
	{
       //!!! TODO: perhaps have to populate "quantityusedUOM" via TableCacheSingleton		
		CompoundIngredient compIngr = new CompoundIngredient();
		compIngr.setQuantityUsed(rs.getBigDecimal(alias + "_QTYUSED"));

		Long packSizeUOMTypeKey = ResultSetWrapper.getKey(rs, alias, "PACKSZUOMTYPKEY");
		String packSizeUOMTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PACKSZUOMTYP, packSizeUOMTypeKey);
		compIngr.setQuantityusedUOM(packSizeUOMTypeCode);
		   
	    return compIngr;
	}	
	

	/****************************************************************************************************/
	// Populate pack added by VLAD on 20160622 
	/**
	 * @throws NamingException **************************************************************************************************/
	public static Pack populatePack(ResultSet rs, String alias, String drugAlias, String drugMfctrAlias, String drgMlclAlias, String dsgFrmAlias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException
	{
       //!!! TODO: perhaps have to populate "packSizeUOMCode" via TableCacheSingleton		
		Pack pack = new Pack();
		pack.setAlternativepacksize(rs.getInt(alias + "_ALTRNTVPACKSZ"));
		pack.setAlternativepacksizeunitofmeasure(rs.getString(alias + "_ALTRNTVPACKSZUOM"));
		pack.setStrength(rs.getString(alias + "_STRNGTH"));//TE97_024
		pack.setGTIN(rs.getString(alias + "_GTIN")) ;

		pack.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(alias + "_ISACTFLG" )) );
		pack.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
		pack.setIsCurrentFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(alias + "_isCrntFlg".toUpperCase())) ) ;
		pack.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
		pack.setManufacturerDiscontinuedDate( CommonUtil.getXMLGregorianCalendar( rs.getDate( alias + "_MfctrDiscntdDt".toUpperCase() ) , true ) );
		pack.setPackSize(rs.getBigDecimal(alias + "_PACKSZ") ) ;
		pack.setMasterid(rs.getString(alias + "_MstrID".toUpperCase())) ;
		
		Drug drug = populateDrug(rs, drugAlias, drugMfctrAlias, drgMlclAlias, dsgFrmAlias);
		pack.setDrug(drug);
	    return pack;
	}	
	
	
	
	
	/****************************************************************************************************/
	// Populate Drug    VLAD Added Molecule on 20160623
	/**
	 * @throws NamingException **************************************************************************************************/
	public static Drug populateDrug(ResultSet rs, String alias, String manufacturerAlias , String dsgFrmAlias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException 
	{
		Drug drug = new Drug();
		Long drugKey = rs.getLong(alias + "_DrgKey");
		if( drugKey == null | drugKey == 0L)
			return null;
		
		Long adminRateTypeKey = ResultSetWrapper.getKey(rs, alias, "_AdminRteTypKey");
		 
		if (adminRateTypeKey!=null && adminRateTypeKey > 0L)
		{
			String adminRteTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_ADMINRTETYP, adminRateTypeKey);
			drug.setAdministrationRouteCode(RouteOfAdmin.fromValue(adminRteTypCdDescr) );
		}
		//added 60 prescribedDrug
		Long routeOfAdminKey = rs.getLong(alias + "_RteOfAdmin_CeRXTypKey");
		if (routeOfAdminKey!=null && routeOfAdminKey > 0L){  
		String routeOfAdminCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
		   RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
		   drug.setAdministrationRouteCode(routeOfAdmin);
		}
		//added 60  prescribedDrug
		drug.setAdultDosageMaximumQuantity(rs.getBigDecimal(alias + "_AdltDsgMaxQty".toUpperCase()) );
		drug.setAdultDosageMinimumQuantity(rs.getBigDecimal(alias + "_AdltDsgMinQty".toUpperCase()) );
		
		DrugNameAlternative drugNameAlternative = new DrugNameAlternative();
		drugNameAlternative.setEquivalentto(rs.getString(alias + "_EquvnltTo".toUpperCase()) );
		drugNameAlternative.setTradenamealternative(rs.getString(alias + "_EquvnltTo".toUpperCase()) );
		drugNameAlternative.setTradereference(rs.getString(alias + "_TrdRef".toUpperCase()) );
		drug.setAlternativename(drugNameAlternative);
		drug.setBranddrug(rs.getString(alias + "_brandDrg".toUpperCase()) );
		drug.setChemicalLabelNameEnglish(rs.getString(alias + "_ChmclLblNmEng".toUpperCase())) ;
		drug.setChemicalLabelNameFrench(rs.getString(alias + "_ChmclLblNmFr".toUpperCase())) ;
		drug.setChildDosageMaximumQuantity(rs.getBigDecimal(alias + "_ChldDsgMaxQty".toUpperCase())) ;
		drug.setChildDosageMinimumQuantity(rs.getBigDecimal(alias + "_ChldDsgMinQty".toUpperCase())) ;
		drug.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
//		String din = rs.getString(alias + "_DIN");
		drug.setDin( rs.getString(alias + "_DIN") );
		
		drug.setChemicalLabelNameEnglish(rs.getString(alias + "_CHMCLLBLNMENG"));
		drug.setChemicalLabelNameFrench(rs.getString(alias + "_CHMCLLBLNMFR"));
		
		Long dinTypeKey = ResultSetWrapper.getKey(rs, alias, "_dINTypKey");
		if (dinTypeKey!=null && dinTypeKey > 0L)
		{
			String DINTypCdDescr =  null;
			DINTypCdDescr = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DINTYP ,dinTypeKey);
			DINType dinType = null;
			dinType = (DINTypCdDescr!=null) ? DINType.fromValue(DINTypCdDescr) : null;
			drug.setDintype(dinType) ;
		}

		// Set Dosage Form
		if (dsgFrmAlias!=null) {
	  		DosageForm dosageForm = new DosageForm () ;
			dosageForm.setFullName(rs.getString(dsgFrmAlias + "_DsgFrmFullNm".toUpperCase()));
			dosageForm.setId(rs.getInt(dsgFrmAlias + "_DsgFrmId".toUpperCase()));
			dosageForm.setShortName(rs.getString(dsgFrmAlias + "_DsgFrmShortNm".toUpperCase()));
			drug.setDosageform(dosageForm) ;
		}

		// DosageRange is only for Dispay and not required
	    // drug.setDosageRange((rs.getString(alias + "_".toUpperCase())) ;
		
		drug.setDrugDescription( rs.getString(alias + "_DrgDescr".toUpperCase())) ;
		Long drgFlvKey= ResultSetWrapper.getKey(rs, alias, "_DrgFlvrTypKey");
		if(drgFlvKey!=null && drgFlvKey > 0L )
		{
			String drgFlvrTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGFLVRTYP, drgFlvKey);
			drug.setFlavour(Flavour.fromValue(drgFlvrTypCdDescr)) ;
		}
		
		drug.setFormvariant(rs.getInt(alias + "_FrmVariant".toUpperCase())) ;
		drug.setIsdevice( CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isDevice".toUpperCase()) ) );
		drug.setIsimmunization(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isImmnztn".toUpperCase()) ) );
		drug.setIsproprietary( CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isProp".toUpperCase()) ) );
		drug.setIsreportable(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isReportable".toUpperCase()) ) );
		drug.setIswritten(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isWrtn".toUpperCase()) ) );
		

				
		// if manufacturer key exist, populate the Manufacturer				
		String MfctrKey = rs.getString(manufacturerAlias + "_MfctrKey".toUpperCase());
		if( MfctrKey != null )
		{
			// Set Manufacturer
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerId(rs.getInt(manufacturerAlias + "_MfctrId".toUpperCase()));
			manufacturer.setManufacturerNameEnglish(rs.getString(manufacturerAlias + "_MfctrNmEng".toUpperCase()));
			manufacturer.setManufacturerNameFrench(rs.getString(manufacturerAlias + "_MfctrNmFr".toUpperCase()));
			manufacturer.setPrefix(rs.getString(manufacturerAlias + "_MfctrPrefix".toUpperCase()));
			manufacturer.setVendorCode(rs.getString(manufacturerAlias + "_vendorCd".toUpperCase()));
			drug.setManufacturer(manufacturer) ;
		}

/*		Molecule mlcl = populateMolecule(rs, drgMlclAlias);
		drug.setMolecule(mlcl);
  */      
/*
		// if manufacturerRecall key exist, populate the ManufacturerRecall				
		String MfctrDrgRecallKey = rs.getString(manufacturerRecallAlias + "_MfctrDrgRecallKey".toUpperCase());
		if( MfctrDrgRecallKey != null )
		{
			ManufacturerRecall manufacturerRecall = new ManufacturerRecall();
			manufacturerRecall.setRecalldate(value)
			manufacturerRecall.
		}
		
		// Set manufacturer Recall
		
		
		
		manufacturerRecall
		
		drug.setRecall()
*/
		drug.setMarkings(rs.getString(alias + "_Mrkng".toUpperCase())) ;
		drug.setMonograph(rs.getString(alias + "_monograph".toUpperCase())) ;
		drug.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
		
		Long drgSchedulKey= ResultSetWrapper.getKey(rs, alias, "_DrgSchdlTypKey");
		if(drgSchedulKey!=null && drgSchedulKey > 0L)
		{
			String drgSchdlTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGSCHDLTYP, drgSchedulKey);
			drug.setSchedule(Schedule.fromValue(drgSchdlTypCdDescr)) ;
		}
		
		Long drgShpKey = ResultSetWrapper.getKey(rs, alias, "_DrgShpTypKey");
		if(drgShpKey!=null && drgShpKey  > 0L )
		{
			String DrgShpTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGSHPTYP, drgShpKey);
			drug.setShape(Shape.fromValue(DrgShpTypCdDescr)) ;
		}
		
		drug.setTradeNameEnglish(rs.getString(alias + "_TrdNmEng".toUpperCase())) ;
		drug.setTradeNameFrench(rs.getString(alias + "_TrdNmFr".toUpperCase())) ;
		
		return drug ; 
	}
	
	public static Drug populateDrug(ResultSet rs, String alias, String manufacturerAlias, String drgMlclAlias, String dsgFrmAlias) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException 
	{
		Drug drug = new Drug();
		Long drugKey = rs.getLong(alias + "_DrgKey");
		if( drugKey == null | drugKey == 0L)
			return null;
		
		Long adminRateTypeKey = ResultSetWrapper.getKey(rs, alias, "_AdminRteTypKey");
		 
		if (adminRateTypeKey!=null && adminRateTypeKey > 0L)
		{
			String adminRteTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, adminRateTypeKey);
			drug.setAdministrationRouteCode(RouteOfAdmin.fromValue(adminRteTypCdDescr) );
		}
		//added 60 prescribedDrug
				Long routeOfAdminKey = rs.getLong(alias + "_RteOfAdmin_CeRXTypKey");
				   String routeOfAdminCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
				   RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
				   drug.setAdministrationRouteCode(routeOfAdmin);
				//added 60  prescribedDrug
		drug.setAdultDosageMaximumQuantity(rs.getBigDecimal(alias + "_AdltDsgMaxQty".toUpperCase()) );
		drug.setAdultDosageMinimumQuantity(rs.getBigDecimal(alias + "_AdltDsgMinQty".toUpperCase()) );
		
		DrugNameAlternative drugNameAlternative = new DrugNameAlternative();
		drugNameAlternative.setEquivalentto(rs.getString(alias + "_EquvnltTo".toUpperCase()) );
		drugNameAlternative.setTradenamealternative(rs.getString(alias + "_EquvnltTo".toUpperCase()) );
		drugNameAlternative.setTradereference(rs.getString(alias + "_TrdRef".toUpperCase()) );
		drug.setAlternativename(drugNameAlternative);
		drug.setBranddrug(rs.getString(alias + "_brandDrg".toUpperCase()) );
		drug.setChemicalLabelNameEnglish(rs.getString(alias + "_ChmclLblNmEng".toUpperCase())) ;
		drug.setChemicalLabelNameFrench(rs.getString(alias + "_ChmclLblNmFr".toUpperCase())) ;
		drug.setChildDosageMaximumQuantity(rs.getBigDecimal(alias + "_ChldDsgMaxQty".toUpperCase())) ;
		drug.setChildDosageMinimumQuantity(rs.getBigDecimal(alias + "_ChldDsgMinQty".toUpperCase())) ;
		drug.setConsumerId(rs.getString(alias + "_CnsmrId".toUpperCase())) ;
//		String din = rs.getString(alias + "_DIN");
		drug.setDin( rs.getString(alias + "_DIN") );
		
		drug.setChemicalLabelNameEnglish(rs.getString(alias + "_CHMCLLBLNMENG"));
		drug.setChemicalLabelNameFrench(rs.getString(alias + "_CHMCLLBLNMFR"));
		
		Long dinTypeKey = ResultSetWrapper.getKey(rs, alias, "_dINTypKey");
		if (dinTypeKey!=null && dinTypeKey > 0L)
		{
			String DINTypCdDescr =  null;
			DINTypCdDescr = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DINTYP ,dinTypeKey);
			DINType dinType = null;
			dinType = (DINTypCdDescr!=null) ? DINType.fromValue(DINTypCdDescr) : null;
			drug.setDintype(dinType) ;
		}

		// Set Dosage Form
		if (dsgFrmAlias!=null) {
	  		DosageForm dosageForm = new DosageForm () ;
			dosageForm.setFullName(rs.getString(dsgFrmAlias + "_DsgFrmFullNm".toUpperCase()));
			dosageForm.setId(rs.getInt(dsgFrmAlias + "_DsgFrmId".toUpperCase()));
			dosageForm.setShortName(rs.getString(dsgFrmAlias + "_DsgFrmShortNm".toUpperCase()));
			drug.setDosageform(dosageForm) ;
		}

		// DosageRange is only for Dispay and not required
	    // drug.setDosageRange((rs.getString(alias + "_".toUpperCase())) ;
		
		drug.setDrugDescription( rs.getString(alias + "_DrgDescr".toUpperCase())) ;
		Long drgFlvKey= ResultSetWrapper.getKey(rs, alias, "_DrgFlvrTypKey");
		if(drgFlvKey!=null && drgFlvKey > 0L )
		{
			String drgFlvrTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGFLVRTYP, drgFlvKey);
			drug.setFlavour(Flavour.fromValue(drgFlvrTypCdDescr)) ;
		}
		
		drug.setFormvariant(rs.getInt(alias + "_FrmVariant".toUpperCase())) ;
		drug.setIsdevice( CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isDevice".toUpperCase()) ) );
		drug.setIsimmunization(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isImmnztn".toUpperCase()) ) );
		drug.setIsproprietary( CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isProp".toUpperCase()) ) );
		drug.setIsreportable(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isReportable".toUpperCase()) ) );
		drug.setIswritten(CommonUtil.convertYesNoFlagToBoolean( rs.getString(alias + "_isWrtn".toUpperCase()) ) );
		

				
		// if manufacturer key exist, populate the Manufacturer				
		String MfctrKey = rs.getString(manufacturerAlias + "_MfctrKey".toUpperCase());
		if( MfctrKey != null )
		{
			// Set Manufacturer
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerId(rs.getInt(manufacturerAlias + "_MfctrId".toUpperCase()));
			manufacturer.setManufacturerNameEnglish(rs.getString(manufacturerAlias + "_MfctrNmEng".toUpperCase()));
			manufacturer.setManufacturerNameFrench(rs.getString(manufacturerAlias + "_MfctrNmFr".toUpperCase()));
			manufacturer.setPrefix(rs.getString(manufacturerAlias + "_MfctrPrefix".toUpperCase()));
			manufacturer.setVendorCode(rs.getString(manufacturerAlias + "_vendorCd".toUpperCase()));
			drug.setManufacturer(manufacturer) ;
		}

		Molecule mlcl = populateMolecule(rs, drgMlclAlias);
		drug.setMolecule(mlcl);
        
/*
		// if manufacturerRecall key exist, populate the ManufacturerRecall				
		String MfctrDrgRecallKey = rs.getString(manufacturerRecallAlias + "_MfctrDrgRecallKey".toUpperCase());
		if( MfctrDrgRecallKey != null )
		{
			ManufacturerRecall manufacturerRecall = new ManufacturerRecall();
			manufacturerRecall.setRecalldate(value)
			manufacturerRecall.
		}
		
		// Set manufacturer Recall
		
		
		
		manufacturerRecall
		
		drug.setRecall()
*/
		drug.setMarkings(rs.getString(alias + "_Mrkng".toUpperCase())) ;
		drug.setMonograph(rs.getString(alias + "_monograph".toUpperCase())) ;
		drug.setProducerId(rs.getString(alias + "_PrdcrId".toUpperCase())) ;
		
		Long drgSchedulKey= ResultSetWrapper.getKey(rs, alias, "_DrgSchdlTypKey");
		if(drgSchedulKey!=null && drgSchedulKey > 0L)
		{
			String drgSchdlTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGSCHDLTYP, drgSchedulKey);
			drug.setSchedule(Schedule.fromValue(drgSchdlTypCdDescr)) ;
		}
		
		Long drgShpKey = ResultSetWrapper.getKey(rs, alias, "_DrgShpTypKey");
		if(drgShpKey!=null && drgShpKey  > 0L )
		{
			String DrgShpTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGSHPTYP, drgShpKey);
			drug.setShape(Shape.fromValue(DrgShpTypCdDescr)) ;
		}
		
		drug.setTradeNameEnglish(rs.getString(alias + "_TrdNmEng".toUpperCase())) ;
		drug.setTradeNameFrench(rs.getString(alias + "_TrdNmFr".toUpperCase())) ;
		
		return drug ; 
	}
	
	/****************************************************************************************************/
	// Populate ManufacturerRecall
	/**
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException **************************************************************************************************/
	
	public static ManufacturerRecall populateManufacturerRecall(ResultSet rs,  String manufacturerRecallAlias ) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException  
	{
		ManufacturerRecall manufacturerRecall = null ;
		String MfctrDrgRecallKey = rs.getString(manufacturerRecallAlias + "_MfctrDrgRecallKey".toUpperCase());
		if( MfctrDrgRecallKey != null )
		{
			manufacturerRecall = new ManufacturerRecall ();
			if ( manufacturerRecall == null )
			{
				manufacturerRecall = new ManufacturerRecall();
			}
			manufacturerRecall.setRecalldate(CommonUtil.getXMLGregorianCalendar(rs.getDate(manufacturerRecallAlias + "_recallDt".toUpperCase()) , true ) );
//			manufacturerRecall.getLotnumber().add(rs.getInt(manufacturerRecallAlias + "_lotNum".toUpperCase()));
		}
		
		// Set manufacturer Recall
		
		return manufacturerRecall;
	}
	
	/****************************************************************************************************/
	// Populate Drug Colour
	/**
	 * @throws IOException 
	 * @throws NamingException **************************************************************************************************/
	
	public static Colour populateDrugColour (ResultSet rs, String drugColourAlias ) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		
		if( rs.getString(drugColourAlias + "_DrgClrKey".toUpperCase()) != null )
		{
			String drugColourTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_DRGCLRTYP, ResultSetWrapper.getKey(rs, drugColourAlias, "DrgClrTypKey"));
			return Colour.fromValue(drugColourTypCdDescr);
		}
		return null;

	}
	


	/**
	 * Populate Molecule
	 * 
	 * @param rs
	 * @param moleculeAlias
	 * @return
	 * @throws SQLException
	 * @throws CDRInternalException
	 * @throws IOException
	 * @throws NamingException
	 */
	public static Molecule populateMolecule(ResultSet rs, String moleculeAlias ) throws SQLException, CDRInternalException, IOException, NamingException {
		if (rs.getString(moleculeAlias + "_MlclKey".toUpperCase()) == null) {
			return null;
		}
		TableCacheSingleton tc = TableCacheSingleton.getInstance(jndiVersion);
		Molecule molecule = new Molecule();
		molecule.setConsumerId(rs.getString(moleculeAlias + "_CnsmrId".toUpperCase()));
		molecule.setIsRefrigerated(CommonUtil.convertYesNoFlagToBoolean(rs.getString(moleculeAlias + "_isRefrigerated".toUpperCase())) );
		molecule.setMoleculeName(rs.getString(moleculeAlias + "_MlclNm".toUpperCase()));
		molecule.setProducerId(rs.getString(moleculeAlias + "_PrdcrId".toUpperCase()));
		molecule.setStrength(rs.getDouble(moleculeAlias + "_Strngth".toUpperCase()));
		Long strengthUomCode = ResultSetWrapper.getKey(rs, moleculeAlias, "_StrngthUOMTypKey");
		String StrngthUOMTypCdDescr =  tc.getCodeFromKey(LT_STRNGTHUOMTYP, strengthUomCode);
		molecule.setStrengthUOMCode(StrngthUOMTypCdDescr);
		return molecule;
	}

	
	/****************************************************************************************************/
	// Populate Molecule ActiveIngredient
	/**
	 * @throws IOException 
	 * @throws NamingException **************************************************************************************************/
	
	public static ActiveIngredient populateMoleculeActiveIngredient(ResultSet rs, String moleculeActiveIngredientAlias ) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		ActiveIngredient activeIngredient = null ;
		if( rs.getString(moleculeActiveIngredientAlias + "_ActIngrdntKey".toUpperCase()) != null  )
		{
			activeIngredient = new ActiveIngredient();
			
			activeIngredient.setChemicalNameAlternative(rs.getString(moleculeActiveIngredientAlias + "_ChmclNmAltrntv".toUpperCase()));
			activeIngredient.setChemicalNameEnglish(rs.getString(moleculeActiveIngredientAlias + "_ChmclNmEng".toUpperCase()));
			activeIngredient.setChemicalNameFrench(rs.getString(moleculeActiveIngredientAlias + "_ChmclNmFr".toUpperCase()));
			activeIngredient.setConsumerId(rs.getString(moleculeActiveIngredientAlias + "_CnsmrId".toUpperCase()));
			
			/* name is not required
			activeIngredient.setName(rs.getString(moleculeActiveIngredientAlias + "_".toUpperCase()));
			*/
			
			activeIngredient.setProducerId(rs.getString(moleculeActiveIngredientAlias + "_PrdcrId".toUpperCase()));
			activeIngredient.setStrength(rs.getDouble(moleculeActiveIngredientAlias + "_Strngth".toUpperCase()));
			
			String StrngthUOMTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_STRNGTHUOMTYP, ResultSetWrapper.getKey(rs, moleculeActiveIngredientAlias, "StrngthUOMTypKey"));
			activeIngredient.setStrengthUOMCode(StrngthUOMTypCdDescr);

			
		}
		return activeIngredient;
	}

	/****************************************************************************************************/
	// Populate Location
	/**
	 * @throws IOException **************************************************************************************************/
	
	public static Location populateLocation(ResultSet rs, String locationAlias ) throws SQLException, CDRInternalException, IOException  
	{
		Location location = null;
		if( rs.getString(locationAlias + "_LocKey".toUpperCase()) != null )
		{
			location = new Location();	
			
		//	location.setBanner(rs.getString(locationAlias + "_banner".toUpperCase()) ); // Yet to implement
			location.setConsumerId(rs.getString(locationAlias + "_CnsmrId".toUpperCase()) );
			location.setLocationName(rs.getString(locationAlias + "_LocNm".toUpperCase()) );
			location.setLocationStatus(rs.getString(locationAlias + "_LocStat".toUpperCase()) );
			location.setLocationType(rs.getString(locationAlias + "_LocTyp".toUpperCase()) );
			location.setProducerId(rs.getString(locationAlias + "_PrdcrId".toUpperCase()) );
		}
		
		return location;
		
	}

	
	/****************************************************************************************************/
	// Populate Initiator added by VLAD on 20160623 
	/****************************************************************************************************/
	public static Initiator populateInitiator(ResultSet rs, String personAlias) throws SQLException, CDRInternalException  
	{
		 Initiator initiator = new Initiator();
		 
		 initiator.setConsumerId(rs.getString(personAlias + "_CnsmrId".toUpperCase())) ;
		 initiator.setProducerId(rs.getString(personAlias + "_PrdcrId".toUpperCase())) ;

		 Person person = populatePerson(rs, personAlias );
		 initiator.setPerson(person) ;
		 
		 
		 return initiator;
	}
	
	
	public static Initiator populateInitiatorFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException, CDRInternalException  
	{
		Initiator initiator= new Initiator();

		initiator.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID")) ;
		initiator.setProducerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID")) ;
		
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );
		initiator.setPerson(person) ;
		
		return initiator;
	}
	/****************************************************************************************************/
	// Populate MedicalPractitioner  added by VLAD on 20160624 
	/****************************************************************************************************/
	public static MedicalPractitioner populateMedicalPractitioner(ResultSet rs, String personAlias , String prsnRoleAlias) throws SQLException, CDRInternalException  { 
		MedicalPractitioner medPr = new MedicalPractitioner();

		medPr.setConsumerId(rs.getString(personAlias + "_CnsmrId".toUpperCase())) ;
		medPr.setIsActive(CommonUtil.convertYesNoFlagToBoolean(rs.getString(prsnRoleAlias + "_isActFlg".toUpperCase())) ) ;
		medPr.setProducerId(rs.getString(personAlias + "_PrdcrId".toUpperCase())) ;
		
		Person person = populatePerson(rs, personAlias );
		medPr.setPerson(person) ;
 
	    return medPr;
	}	
	
	public static MedicalPractitioner populateMedicalPractitionerFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException, CDRInternalException  
	{
		MedicalPractitioner medicalPractitioner= new MedicalPractitioner();

		medicalPractitioner.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID")) ;
		medicalPractitioner.setIsActive( CommonUtil.convertYesNoFlagToBoolean(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSNRL_ISACTFLG")) );
		medicalPractitioner.setProducerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID")) ;
		
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );
		medicalPractitioner.setPerson(person) ;
		
		return medicalPractitioner;
	}
	
	
	/****************************************************************************************************/
	// Populate Recorder
	/****************************************************************************************************/
	public static Recorder populateRecorder(ResultSet rs, String personAlias , String prsnRoleAlias) throws SQLException, CDRInternalException  
	{
		
		Recorder recorder = new Recorder();

		recorder.setConsumerId(rs.getString(personAlias + "_CnsmrId".toUpperCase())) ;
		recorder.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(prsnRoleAlias + "_isActFlg".toUpperCase())) ) ;
		recorder.setProducerId(rs.getString(personAlias + "_PrdcrId".toUpperCase())) ;
		
		
		Person person = populatePerson(rs, personAlias );
		recorder.setPerson(person) ;
		
		return recorder;
	}

	public static Recorder populateRecorderFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException, CDRInternalException  
	{
		Recorder recorder= new Recorder();

		recorder.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID")) ;
		
		recorder.setIsActiveFlag( CommonUtil.convertYesNoFlagToBoolean(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSNRL_ISACTFLG")) );
		recorder.setProducerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID")) ;
		
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );
		recorder.setPerson(person) ;
		
		return recorder;
	}


	/****************************************************************************************************/
	// Populate Supervisor
	/****************************************************************************************************/

	public static Supervisor populateSupervisor(ResultSet rs, String personAlias , String prsnRoleAlias) throws SQLException, CDRInternalException  
	{
		Supervisor supervisor = new Supervisor();

		supervisor.setConsumerId(rs.getString(personAlias + "_CnsmrId".toUpperCase())) ;
		supervisor.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(prsnRoleAlias + "_isActFlg".toUpperCase())) ) ;
		supervisor.setProducerId(rs.getString(personAlias + "_PrdcrId".toUpperCase())) ;
		
		
		Person person = populatePerson(rs, personAlias );
		supervisor.setPerson(person) ;
		
		return supervisor;
	}
	
	public static Supervisor populateSupervisorFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException, CDRInternalException  
	{
		Supervisor supervisor= new Supervisor();

		supervisor.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID")) ;
		
		
		supervisor.setIsActiveFlag( CommonUtil.convertYesNoFlagToBoolean(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSNRL_ISACTFLG")) );
		supervisor.setProducerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID")) ;
		
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );
		supervisor.setPerson(person) ;
		
		return supervisor;
	}
	
	/****************************************************************************************************/
	// Populate Dispenser
	/****************************************************************************************************/
	public static Dispenser populateDispenser(ResultSet rs, String personAlias , String prsnRoleAlias) throws SQLException, CDRInternalException  
	{
		Dispenser dispenser = new Dispenser();

		dispenser.setConsumerId(rs.getString(personAlias + "_CnsmrId".toUpperCase())) ;
		dispenser.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(prsnRoleAlias + "_isActFlg".toUpperCase())) ) ;
		dispenser.setProducerId(rs.getString(personAlias + "_PrdcrId".toUpperCase())) ;
		
		
		Person person = populatePerson(rs, personAlias );
		dispenser.setPerson(person) ;
		
		return dispenser;
	}
	
	public static Dispenser populateDispenserFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException, CDRInternalException  

	{
		Dispenser dispenser = new Dispenser();

		dispenser.setConsumerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_CNSMRID")) ;
		
		
		dispenser.setIsActiveFlag( CommonUtil.convertYesNoFlagToBoolean(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSNRL_ISACTFLG")) );
		dispenser.setProducerId(ResultSetWrapper.getString(rs, personRoleViewAlias, "_PRSN_PRDCRID")) ;
		
		
		Person person = populatePersonFromView(rs, personRoleViewAlias );
		dispenser.setPerson(person) ;
		
		return dispenser;
	}


	public static Person populatePersonFromView(ResultSet rs, String personRoleViewAlias ) throws SQLException  
	{
		Person person = new Person();
		
		person.setFirstName(ResultSetWrapper.getString(rs, personRoleViewAlias, "PRSN_FRSTNM")) ;
		person.setLastName(ResultSetWrapper.getString(rs, personRoleViewAlias, "PRSN_LSTNM")) ;
//		person.setAddress((rs.getString(personAlias + "_".toUpperCase())) ;
		return person;
	}
	/****************************************************************************************************/
	// Populate Person
	/****************************************************************************************************/

	public static Person populatePerson(ResultSet rs, String personAlias ) throws SQLException  
	{
		Person person = new Person();
		
		person.setFirstName(rs.getString(personAlias + "_FrstNm".toUpperCase())) ;
		person.setLastName(rs.getString(personAlias + "_LstNm".toUpperCase())) ;
//		person.setAddress((rs.getString(personAlias + "_".toUpperCase())) ;
		return person;
	}
	
	
	/****************************************************************************************************/
	// Populate Address
	/**
	 * @throws IOException 
	 * @throws NamingException 
	 * **************************************************************************************************/
	
	public static Address populateAddress(ResultSet rs, String contactMethodAlias , String addressAlias ) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		Address address = null ;
		Long cntctMthdTypKey = ResultSetWrapper.getKey(rs, contactMethodAlias, "_CntctMthdTypKey");
		if( cntctMthdTypKey != null &&  cntctMthdTypKey > 0L )
		{		
			String CntctMthdTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNTCTMTHDTYP, cntctMthdTypKey );
			
			String AddrKey = rs.getString(addressAlias + "_AddrKey");
			if( CntctMthdTypCdDescr.equals("Postal Address") && AddrKey != null )
			{
			
				address = new Address();
				
				String addressLineOne = rs.getString(addressAlias + "_AddrLnOne".toUpperCase());
				if( addressLineOne != null )
				{
					address.setAddressLineOne(addressLineOne) ;
				}
				
				String addressLineTwo = rs.getString(addressAlias + "_AddrLnTwo".toUpperCase());
				if( addressLineTwo != null )
				{
					address.setAddressLineTwo(addressLineTwo) ;
				}
				
				String cityName = rs.getString(addressAlias + "_cityNm".toUpperCase());
				if( cityName != null )
				{
					address.setCityName(cityName) ;
				}
				
				address.setConsumerId(rs.getString(contactMethodAlias + "_CnsmrId".toUpperCase())) ;
				address.setCountryCode(rs.getString(addressAlias + "_CntryCd".toUpperCase())) ;
				address.setIsPrimaryAddressFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString(contactMethodAlias + "_primaryFlg".toUpperCase())) ) ;
				address.setPostalCode(rs.getString(addressAlias + "_postalCd".toUpperCase())) ;
				address.setProducerId(rs.getString(contactMethodAlias + "_PrdcrId".toUpperCase())) ;
				String provCode = rs.getString(addressAlias + "_ProvCd".toUpperCase()) ; 
				if( provCode  != null )
				{
					address.setProvinceCode(Province.fromValue( provCode) ) ;
				}
		//		person.setAddress((rs.getString(personAlias + "_".toUpperCase())) ;
			}
		}
		return address;
	}

	/****************************************************************************************************/
	// get email 
	/**
	 * @throws 
	 * IOException 
	 * @throws NamingException **************************************************************************************************/
	
	public static String getEmailFromResultSet(ResultSet rs,  String contactMethodAlias, String emailAlias) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		Long cntctMthdTypKey = ResultSetWrapper.getKey(rs, contactMethodAlias, "_CntctMthdTypKey");
		
		if( cntctMthdTypKey != null &&  cntctMthdTypKey > 0L )
		{		
			String CntctMthdTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNTCTMTHDTYP, cntctMthdTypKey);
			
			if( CntctMthdTypCdDescr.equals("Email Address") )
			{
	
				return rs.getString(emailAlias + "_emailAddr".toUpperCase()) ;
			}
		}
		return null;
	}
	
	/****************************************************************************************************/
	// get email 
	/**
	 * @throws 
	 * IOException **************************************************************************************************/
	
/*	public static String getPatinetRefillReminderResultSet(ResultSet rs,  String CustPref) throws SQLException
	{
		return rs.getString(CustPref + "_RflRmndr".toUpperCase());
	}
*/	
	/****************************************************************************************************/
	// get phone number
	/**
	 * @throws 
	 * IOException 
	 * @throws NamingException **************************************************************************************************/
	
	public static String getPhoneFromResultSet(ResultSet rs,  String contactMethodAlias ,String telecomAlias) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		
		Long cntctMthdTypKey = ResultSetWrapper.getKey(rs, contactMethodAlias, "_CntctMthdTypKey");
		
		if( cntctMthdTypKey != null &&  cntctMthdTypKey > 0L )
		{		

			String CntctMthdTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNTCTMTHDTYP, cntctMthdTypKey);
			
			
			
			if( CntctMthdTypCdDescr.equals("Telecom") )
			{
				String telFaxIndicator = rs.getString(telecomAlias + "_TelFaxInd".toUpperCase());
				if(telFaxIndicator!= null && telFaxIndicator.equals(TelecomType.TELEPHONE.value()) )
				{
					return rs.getString(telecomAlias + "_telecomNum".toUpperCase()) ;
				}
			}
		}
		return null;
	}
	
	/****************************************************************************************************/
	// get Fax number
	/**
	 * @throws 
	 * IOException 
	 * @throws NamingException **************************************************************************************************/
	
	public static String getFaxFromResultSet(ResultSet rs,  String contactMethodAlias ,String telecomAlias) throws SQLException, CDRInternalException, IOException, NamingException  
	{
		
		Long cntctMthdTypKey = ResultSetWrapper.getKey(rs, contactMethodAlias, "_CntctMthdTypKey");
		
		if( cntctMthdTypKey != null &&  cntctMthdTypKey > 0L )
		{		
		
			String CntctMthdTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNTCTMTHDTYP, cntctMthdTypKey);
			
			if( CntctMthdTypCdDescr.equals("Telecom") )
			{
				String telFaxIndicator = rs.getString(telecomAlias + "_TelFaxInd".toUpperCase());
				if(telFaxIndicator!= null && telFaxIndicator.equals(TelecomType.FAX.value()) ){
					return rs.getString(telecomAlias + "_telecomNum".toUpperCase()) ;
				}
			}
		}
		return null;
	}
	
	
	/****************************************************************************************************/
	// Populate Coverage
	/**
	 * @throws IOException 
	 * @throws CDRInternalException 
	 * @throws NamingException **************************************************************************************************/
	
	public static InsuranceCoverage populateCoverage(ResultSet rs, String coverageAlias ) throws SQLException, IOException, CDRInternalException, NamingException 
	{
		InsuranceCoverage coverage = null;
		if( rs.getString(coverageAlias + "_PtntCvrgKey".toUpperCase()) != null )
		{
			coverage = new InsuranceCoverage();	
			
			coverage.setCarrierId(rs.getString(coverageAlias + "_CrrId".toUpperCase()) );
			coverage.setGroupId(rs.getString(coverageAlias + "_GrpId".toUpperCase()));
			coverage.setClientId((rs.getString(coverageAlias + "_ClntId".toUpperCase())));
			coverage.setPlanNumber(rs.getString(coverageAlias + "_PlanNum".toUpperCase()));

			coverage.setIsActive(CommonUtil.convertYesNoFlagToBoolean(rs.getString(coverageAlias + "_ActFlg".toUpperCase()) ) );

			coverage.setPriority(rs.getInt(coverageAlias + "_Priority".toUpperCase()));
			coverage.setCardIdentifier(rs.getString(coverageAlias + "_CardId".toUpperCase()) );
			coverage.setInsuranceCoverageIdentifier(rs.getString(coverageAlias + "_InsCvrgId".toUpperCase()));
			coverage.setThirdPartyConsumerIdentifier(rs.getString(coverageAlias + "_TPId".toUpperCase()));
			
			Long bnftCardhldrRltnshpTypKey = ResultSetWrapper.getKey(rs, coverageAlias , "BnftCardhldrRltnshpTypKey".toUpperCase());
			if( bnftCardhldrRltnshpTypKey != null && bnftCardhldrRltnshpTypKey > 0L )
			{
				String BnftCardhldrRltnshppTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_BNFTCARDHLDRRLTNSHPTYP, bnftCardhldrRltnshpTypKey );
				coverage.setBenefitsCardholderRelationshipCode(BenefitsCardholderRelationship.fromValue(BnftCardhldrRltnshppTypCdDescr));
			}
			
			coverage.setConsumerId(rs.getString(coverageAlias + "_PtntCvrgId".toUpperCase()));
		}
		
		return coverage;
		
	}
	
	/****************************************************************************************************/
	// Populate Consent
	/**
	 * @throws IOException 
	 * @throws CDRInternalException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * @throws NamingException 
	 * **************************************************************************************************/
	
	public static Consent populateConsent(ResultSet rs, String consentAlias ) throws SQLException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException 
	{
		Consent consent = null;
		
		if( rs.getString(consentAlias + "_PtntCnsntKey".toUpperCase()) != null )
		{
			consent = new Consent();	

			consent.setConsentEffectiveTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(consentAlias + "_CnsntEffTimestamp".toUpperCase()) , true) );
			consent.setConsentEndTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(consentAlias + "_CnsntEndTimestamp".toUpperCase()) , true) );
			
			String CnsntOvrdRsnTypCdDescr =  TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNSNTOVRDRSNTYP, ResultSetWrapper.getKey(rs, consentAlias, "_CnsntOvrdRsnTypKey"));
      
			// Added by Siddhi for PROD defect ConsentOverrideReason = null
			if(CnsntOvrdRsnTypCdDescr !=null){
			consent.setConsentOverrideReasonCode(ConsentOverrideReason.fromValue(CnsntOvrdRsnTypCdDescr) );
			}
			else {
			    consent.setConsentOverrideReasonCode(null);
			}
			consent.setConsumerId(rs.getString(consentAlias + "_CnsmrId".toUpperCase()) );
			consent.setProducerId(rs.getString(consentAlias + "_PrdcrId".toUpperCase()) );
			//Added as part of SmartNotification
			String cnsntRsnTypCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_CNSNTRSNTYP, ResultSetWrapper.getKey(rs, consentAlias, "_CNSNTRSNTYPKEY"));
			if(cnsntRsnTypCode != null){
				consent.setConsentReasonCode(ConsentReasonCode.fromValue(cnsntRsnTypCode));
			}
			consent.setConsentType(rs.getString(consentAlias + "_consenttype".toUpperCase()));
			consent.setConsentCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(consentAlias +"_cnsntcrttimestamp".toUpperCase()) , true));
			consent.setConsentUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(consentAlias +"_cnsntupttimestamp".toUpperCase()) , true));
		//	consent.setAgentName(rs.getString(consentAlias + "_caregivername".toUpperCase()));
			consent.setAgentFirstName(rs.getString(consentAlias + "_agentfrstnm".toUpperCase()));
			consent.setAgentLastName(rs.getString(consentAlias + "_agentlstnm".toUpperCase()));
			consent.setAgentRelationship(rs.getString(consentAlias + "_agentrelationship".toUpperCase()));
			consent.setConsentProvider(rs.getString(consentAlias + "_consentprovidertype".toUpperCase()));
			consent.setConsentUserId(rs.getString(consentAlias + "_userid".toUpperCase()));
		    consent.setNotificationMethod(rs.getString(consentAlias + "_notifmethod".toUpperCase()));
		}
		
		return consent;
		
	}

	/****************************************************************************************************/
	// Populate Consent
	/**
	 * @throws IOException 
	 * @throws CDRInternalException 
	 * @throws DatatypeConfigurationException 
	 * @throws ParseException 
	 * **************************************************************************************************/
	
	public static AdverseDrugReaction populateAdverseDrugReaction(ResultSet rs, String adverseDrugReactionAlias ) throws SQLException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException 
	{
		AdverseDrugReaction adverseDrugReaction = null;
		if( rs.getString(adverseDrugReactionAlias + "_PtntAdvrsDrgRctnKey".toUpperCase()) != null )
		{
			adverseDrugReaction = new AdverseDrugReaction();	
			adverseDrugReaction.setConsumerId(rs.getString(adverseDrugReactionAlias + "_CnsmrId".toUpperCase()) );
			adverseDrugReaction.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(adverseDrugReactionAlias + "_CrtTimestamp".toUpperCase()) , true) );
			adverseDrugReaction.setDescriptionEnglish(rs.getString(adverseDrugReactionAlias + "_DescrEng".toUpperCase()) );
			adverseDrugReaction.setOccuranceStartTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(adverseDrugReactionAlias + "_OccrStrtTimestamp".toUpperCase()) , true) );
			adverseDrugReaction.setProducerId(rs.getString(adverseDrugReactionAlias + "_PrdcrId".toUpperCase()) );
			adverseDrugReaction.setUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(rs.getDate(adverseDrugReactionAlias + "_upDtTimestamp".toUpperCase())  , true ) );

		}
		
		return adverseDrugReaction;
		
	}	
	

	


	/****************************************************************************************************/
	// Populate customer
	/**
	 * @throws IOException 
	 * **************************************************************************************************/

//	public static Customer populateCustomer(ResultSet rs) throws SQLException , ParseException, DatatypeConfigurationException, CDRInternalException, IOException
//	{
//		Customer customer  = new Customer ();
//		Preference preference=new Preference();
//		
//		customer.setCustomerid(rs.getString("SRCESYSID"));
//		customer.setEmail(rs.getString("EMAILADDR"));
//		customer.getPatientid().add(rs.getString("CNSMRID"));
//		customer.setLanguage(rs.getString("LANGPREF"));
//		customer.setPhonenumber(rs.getString("TELECOMNUM"));
//		
//		return customer;
//	}
	
	public static TxFillStatus populateDispenseFillStatus(ResultSet rs, String alias) throws NamingException, SQLException, IOException, CodeNotFoundFromTableCacheException {
        Long fillStatusKey = ResultSetWrapper.getKey(rs, alias, "TXFILLSTATTYPKEY");
        if (fillStatusKey==null) {
                return null;
        }
        TableCacheSingleton tc = TableCacheSingleton.getInstance(jndiVersion);
        String fillStatusCode = (fillStatusKey!=null) ? tc.getCodeFromKey(LT_TXFILLSTATTYP, fillStatusKey) : null;
        TxFillStatus txFillStatus = null;
		txFillStatus = (fillStatusCode!=null) ? TxFillStatus.fromValue(fillStatusCode) : null;
        return txFillStatus;
   }
	
	
	public static RxFillStatus populatePrescriptionFillStatus(ResultSet rs, String alias) throws NamingException, SQLException, IOException, CodeNotFoundFromTableCacheException {
        Long fillStatusKey = ResultSetWrapper.getKey(rs, alias, "FILLSTATKEY");
        if (fillStatusKey==null) {
        	return null;
        }
        TableCacheSingleton tc = TableCacheSingleton.getInstance(jndiVersion);
        String fillStatusCode = null;
       	fillStatusCode = (fillStatusKey!=null) ? tc.getCodeFromKey(LT_RXFILLSTATTYP, fillStatusKey) : null;
        RxFillStatus rxFillStatus = null;
		rxFillStatus = (fillStatusCode!=null) ? RxFillStatus.fromValue(fillStatusCode) : null;
        return rxFillStatus;
   }
	
	public static XMLGregorianCalendar populateFillStatusEffectiveDate(ResultSet rs, String alias) throws SQLException, ParseException, DatatypeConfigurationException {
		XMLGregorianCalendar fillStatusEffectiveDate = ResultSetWrapper.getCalendar(rs, alias, "FILLSTATEFFDT");
		return fillStatusEffectiveDate;
	}


	public static String populatePackageForm(ResultSet rs, String alias) throws NamingException, SQLException, IOException, CodeNotFoundFromTableCacheException {
		Long packageFormKey =ResultSetWrapper.getKey(rs, alias, "PkgFrmTypKey");
		if (packageFormKey==null) {
			return null;
		}
		TableCacheSingleton tc = TableCacheSingleton.getInstance(jndiVersion);
		String packageFormDescr = (packageFormKey!=null) ? tc.getCodeFromKey(LT_PKGFRMTYP, packageFormKey) : null;
		return packageFormDescr;
	}
	
	public static ProfessionalRegistration populateProfessionalRegistration(ResultSet rs, String alias) throws NamingException, SQLException, IOException, CodeNotFoundFromTableCacheException 
	{
		Long profRegKey = ResultSetWrapper.getKey(rs, alias, "PRFSNLREGKEY");
        
        if( profRegKey == null || profRegKey == 0L )
        	return null;
        
        ProfessionalRegistration  profRegistration = new ProfessionalRegistration();
        String issuingBodyCode = rs.getString(alias + "_ISSNGBODYCD".toUpperCase());
        if( issuingBodyCode != null )
        {
            profRegistration.setIssuingBodyCode(LicenseIssuingBodyType.fromValue(issuingBodyCode));
        	
        }
        
        profRegistration.setLicenseNumber(rs.getString(alias + "_LICNUM".toUpperCase()));
        
        Long provKey = rs.getLong(alias + "_PROVKEY".toUpperCase());
        if( provKey != null && provKey != 0L )
        {
        	String provCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PROV, provKey);
        	profRegistration.setLicensedProvince(Province.fromValue(provCode));
        }
        
        return profRegistration;
	}

	
    // VL46 Started	
	public static void PopulateAddress46(ResultSet rs, String Alias, Address addr,List<String> alterPhonesList) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {  
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
			    if (rs.getBigDecimal(Alias + "ADDR_ADDRKEY").compareTo(BigDecimal.ZERO) > 0) {
				    addr.setAddressLineOne(ResultSetWrapper.getString(rs, Alias, "ADDR_ADDRLNONE"));
					addr.setAddressLineTwo(ResultSetWrapper.getString(rs, Alias, "ADDR_ADDRLNTWO"));
					addr.setCityName(ResultSetWrapper.getString(rs, Alias, "ADDR_CITYNM"));
					addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
				    addr.setCountryCode(ResultSetWrapper.getString(rs, Alias, "ADDR_CNTRYCD"));
				    addr.setPostalCode(ResultSetWrapper.getString(rs,Alias, "ADDR_POSTALCD"));
				    String   value =null;
				    ///TE97_024
			     /*   value = ResultSetWrapper.getString(rs,Alias,"ACTVFLG");
			        if (value != null) {
			        	addr.setIsActiveAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
				     
			        }*/
			     
			      //TE97_024   PRIMARYFLG         (Default null)
			      /*  value = ResultSetWrapper.getString(rs,Alias,"PRIMARYFLG");
			        if (value != null) {
				        try {
				        	addr.setIsPrimaryAddressFlag(CommonUtil.convertYesNoFlagToBoolean(value));
						} catch (CDRInternalException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				      
			        }*/
			    
//				    addr.setContactPurposeType(ContactPurposeType.fromValue(ResultSetWrapper.getString(rs,Alias, "ADDR_TYPE"))); //TE99 ADDED CONTACT PURPOSE TYPE
				    
				    String provCode = ResultSetWrapper.getString(rs,Alias, "ADDR_PROVCD");
				    if( provCode != null ) {
					    addr.setProvinceCode(Province.fromValue( provCode) ) ;
				    }
				 }
					 
				     
			     if (rs.getBigDecimal(keyAlias + "EMAIL_EMAILKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
					 addr.setEmail(ResultSetWrapper.getString(rs, Alias, "EMAIL_EMAILADDR"));
			     }    
				     
			     if (rs.getBigDecimal(keyAlias + "PHONE_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
				     addr.setPrimaryPhoneNumber(ResultSetWrapper.getString(rs, Alias, "PHONE_TELECOMNUM"));
			     }
			     
			     if (rs.getBigDecimal(keyAlias + "FAX_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {
			    	 addr.setConsumerId(ResultSetWrapper.getString(rs, Alias, "CM_CNSMRID"));
					 addr.setFaxNumber(ResultSetWrapper.getString(rs, Alias, "FAX_TELECOMNUM"));
			     }
			     String thisAlterPhone =null;  
			     
			 	
			     
				 //TE99 added for Alternate Phone Number  
			if (rs.getBigDecimal(keyAlias + "ALTERN_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {
			thisAlterPhone = ResultSetWrapper.getString(rs, Alias, "ALTERN_TELECOMNUM");
				if (thisAlterPhone != null && thisAlterPhone.trim().length() > 2 && !alterPhonesList.contains(thisAlterPhone)) {
					alterPhonesList.add(thisAlterPhone);
					addr.setAlternatePhoneNumber(alterPhonesList);
				}

			}	 
			
		}
	}
	
	
	
}
