package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GNDRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GRPMBRSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PTNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_LANGUAGES;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.ConsentNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.bean.UpsertResponse;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentReasonCode;
import ca.shoppersdrugmart.rxhb.ehealth.GroupMembership;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyCentralBusinessEvent;
import generated.RxHBBusinessEventActionEnum;
import javax.xml.datatype.XMLGregorianCalendar;
/*
@revision 
TAG  Date	     Vendor        Name 	     Change
---- -----------  -----------  -----------   -------------------
TE99 2018-02-12   NTT Data                   QHR Accuro Project

*/


public class PatientApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(PatientApi.class);
//MOBILENUM, SALUTATION, //Temporary fix for Drop 50    ,17,18,
	private final static String INSERTSQL = "INSERT INTO PTNT " 
			+ "(DTOFBIRTH, "					// 1 
			+ "DCSDDT, "						// 2
			+ "GNDRTYPKEY, GRPMBRSHPTYPKEY, PTNTACTIND, NOKNWNALRGYFLG, "  // 3,4,5,6
			+ "CRTTIMESTAMP, "					// 7
			+ "LSTUPDTTIMESTAMP,"				// 8 				
			+ " STORENUM, PTNTTYPKEY, "			// 9 , 10
			+ " FRSTNM , LSTNM , CNSMRID, "		// 11 , 12 , 13
			+ " SNDXFRSTNM , SNDXLSTNM, MDLNM, MOBILENUM,LANGUAGECORRESPONDENCE,SIGLANGUAGE,MONOGRAPHLANGUAGE,  "	// 14, 15,16,17 TE99 ADDED INTO PTNT TABLE  
			+ "PTNTKEY)" 						// 19		
			+ " VALUES (" + CommonUtil.TO_TIMESTAMP_TZ + ", " 
			+ "         " + CommonUtil.TO_TIMESTAMP_TZ + ", " 
			+ "    	  ?, ?, ?, ?, " +
			" 		  " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"         " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"		  ?, ?, " +
			"		  ? , ? , ? , "  + // FRSTNM , MDLNM , CNSMRID
			"		  SOUNDEX(?) , SOUNDEX(?) ,?, ?,?,?,?, "	 +		// SNDXFRSTNM , SNDXLSTNM	//TE99 ADDED INTO PTNT TABLE	
			" PTNT_SEQ.NEXTVAL)";
			
			
/*	private final static String SELECTSQL = "SELECT PTNT.PTNTKEY,			" +
			 "		 PTNT.LSTUPDTTIMESTAMP	" +
			 "  FROM 	" +
			 "		 PTNT PTNT			" +
			 "	WHERE PTNT.CNSMRID = ? 	" +
			 "	  AND PTNT.STORENUM= ?	" ;
*/	
	//MOBILENUM,SALUTATION, //Temporary fix for Drop 50 
	private final static String UPDATESQL = "UPDATE PTNT " +
			 "   SET (DTOFBIRTH, "
			 + " DCSDDT, "
			 + " GNDRTYPKEY, GRPMBRSHPTYPKEY, PTNTACTIND, NOKNWNALRGYFLG, "
			 + " CRTTIMESTAMP, "
			 + " LSTUPDTTIMESTAMP, " 
			 + " PTNTTYPKEY ,"  
			 + " FRSTNM , LSTNM , CNSMRID ,MDLNM, "//TE99 ADDED INTO PTNT TABLE
			 + " SNDXFRSTNM , SNDXLSTNM, MOBILENUM ,LANGUAGECORRESPONDENCE,SIGLANGUAGE,MONOGRAPHLANGUAGE  ) = " + //added mobilenum as part of drop56
			" (SELECT " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"         " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"    	  ?, ?, ?, ?, " +
			" 		  " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"         " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
			"			? ," +
			"		  ? , ? , ? ,?, " + 			// FRSTNM , LSTNM , CNSMRID //TE99 ADDED INTO PTNT TABLE	
			"		  SOUNDEX(?) , SOUNDEX(?)  "	 + ", ? ,?,?,? "+	// SNDXFRSTNM , SNDXLSTNM	
			"			FROM DUAL) " +
		    "	WHERE PTNTKEY = ?";
	
//	private Long patientKey = 0L;
//	private XMLGregorianCalendar LastUpdateTimestampDB;
//	private boolean PerformIns = false;
//	private boolean PerformUpd = false;
		
	private List<Allergy> allergyList = null;
	private List<Consent> consentList = null;
//	List<InsuranceCoverage> insCovList = null;
	private List<PatientIdentification> PatientIdList = null;
	private List<AdverseDrugReaction> advdrgReactList = null;
	
	public UpsertResponse upsertPatient(Connection connection, Store store , Patient patient,XMLGregorianCalendar consumerSendTime) throws Exception, InvalidInputException 
	{
		try {
			Long timer = System.currentTimeMillis();
			String patientConsumerId = patient.getConsumerId();
			String storeNumber = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: PatientApi.upsertPatient. patientConsumerId : " + patientConsumerId
					+ ", storeNumber : " + storeNumber);}
//			findPersonbyConsumerID(connection, patientConsumerId, storeNumber, patient.getLastUpdateTimestamp());
//			super.close();
			
			Map<String, Object> paatientData = FindUtil.findPatientData(connection , patientConsumerId , storeNumber);

			Long patientKey = (Long) paatientData.get("PTNTKEY");
			Timestamp updateTimestampDb = (Timestamp) paatientData.get("LSTUPDTTIMESTAMP");
			boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, patient.getLastUpdateTimestamp());
		
			if (isUpdateRequestNew == false) 
			{
				if(logger.isDebugEnabled()) {logger.debug("Patient lastUpdated : " + updateTimestampDb + " is less than DB last updated : " + patient.getLastUpdateTimestamp());}
				UpsertResponse upsertResponse = new UpsertResponse();
				upsertResponse.setPatientKey(patientKey);
				upsertResponse.setRxHBBusinessEventActionEnum(RxHBBusinessEventActionEnum.UPDATE);

				return upsertResponse;
			}
			
			NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.PATIENT_NOTE);
			MedCondApi mdcCondApi = new MedCondApi(patient);
			MetricsApi mtrcicsApi = new MetricsApi();
			AllergyApi PtntAlrgy = new AllergyApi();
			ConsentApi PtntCnsnt = new ConsentApi();
			// InsuranceCoverageApi InsCvrg = new
			// InsuranceCoverageApi(connection);
			AdverseDrugReactionApi AdvdrgReact = new AdverseDrugReactionApi();
			PatientIdentificationApi patientIdApi = new PatientIdentificationApi();
			UpsertResponse upsertResponse = new UpsertResponse();
			// PersonRoleApi prsnRoleApi = new PersonRoleApi(connection);

			RxHBBusinessEventActionEnum rxHBBusinessEventActionEnum = null;

			if( patientKey == null )
			{
				patientKey = insertPatient(connection, storeNumber,
						patient /* , patientKey */);
				rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.CREATE;
			}
			else
			{
				updatePatient(connection, patient, patientKey);
				rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.UPDATE;
			}
			
			

			ContactMethodApi contactMethodApi = new ContactMethodApi();
			contactMethodApi.upsertContactMethodByPatientKey(connection, storeNumber, patientKey, patient.getPerson());

			noteApi.upsertNoteList(connection, store, patient.getNote(), patientKey);
			mdcCondApi.upsertMedCond(connection, store, patientKey, patient.getPatientMedicalCondition());
			mtrcicsApi.upsertMetrics(connection, patientKey, patient.getPatientMetrics());

			/*
			 * PersonRole personRole = new PersonRole(patient); if
			 * (patient.isIsactive() !=null)
			 * personRole.setActiveFlag(patient.isIsactive().booleanValue())
			 * ; prsnRoleApi.upsertPersonRole(patientKey, store,
			 * personRole);
			 */
			allergyList = patient.getAllergy();
			if (allergyList != null) {
				for (Allergy thisAllergy : allergyList) {
					PtntAlrgy.upsertAllergy(connection, patient.getConsumerId(), storeNumber, thisAllergy,
							null);
				}
			}
			String consumerSendTimestamp = null;
					
				String consentUpdateTimestamp=null; 
				String consumerSendDate = null;
				String consentReasonCodeStr= null;
				String consentReasonCodeName= null;
				String consentUpdateDate=null; 
				Boolean dateEqual = false;
				Boolean consentReasonCodeFlag = false;
				XMLGregorianCalendar consentUpdateTimestamploc;
						XMLGregorianCalendar consumerSendTimeloc =(XMLGregorianCalendar) consumerSendTime;
					consumerSendTimestamp=	CommonUtil.toTimestampStr(consumerSendTimeloc);	
			consentList = patient.getConsent();
			if (consentList != null) {
				for (Consent thisConsent : consentList) {
					//Praveen T added for SmartNotification 
					//PtntCnsnt.upsertConsent(connection, patient.getConsumerId(), storeNumber, thisConsent);
					//if (upsertResponse.getAutoRefillFlag() == false && thisConsent.getConsentType() != null && thisConsent.getConsentType().equals("AUTOREFILL") ){
					consentUpdateTimestamploc = thisConsent.getConsentUpdateTimestamp();
					
					consentUpdateTimestamp =  CommonUtil.toTimestampStr(consentUpdateTimestamploc);
					
					consumerSendDate = CommonUtil.toDateStrWithOutTime(consumerSendTimeloc);
					consentUpdateDate = CommonUtil.toDateStrWithOutTime(consentUpdateTimestamploc);
					//consumerSendDate.equalsIgnoreCase(consentUpdateDate)
					//try{
					//consentReasonCodeStr = thisConsent.getConsentReasonCode().value();
					consentReasonCodeName= thisConsent.getConsentReasonCode()!= null ? thisConsent.getConsentReasonCode().name() : null;
					//Praveen T added for SmartNotification -3 
					if(consentReasonCodeName == null){
						consentReasonCodeStr = null;
					}else{
					
						if (!FindEnum.contains(ConsentReasonCode.class, consentReasonCodeName)) {
							throw new InvalidInputException("Invalid ConsentReasonCode");
							//throw new ConsentNotFoundException( patientKey,patient.getConsumerId() , consentReasonCodeName ,"Invalid ConsentReasonCode");
							}
						else{
							consentReasonCodeStr = thisConsent.getConsentReasonCode().value();
						}
					}
					//}
					/*catch (ConsentNotFoundException e) {
						e.printStackTrace();
						throw e;
					}*/
					/*if(consumerSendDate.equalsIgnoreCase(consentUpdateDate))
					{
						
						dateEqual= true;
					}
					 if ((consentReasonCodeStr.equals("Accepted")) || (consentReasonCodeStr.equals("Declined")))
					 {
						 consentReasonCodeFlag= true;
						 
					 }*/
					
					if (upsertResponse.getAutoRefillFlag() == false &&
							thisConsent.getConsentType() != null        &&
				    thisConsent.getConsentType().equals("AUTOREFILL") ){
						//Praveen T added for SmartNotification -2 
						//Praveen T added for SmartNotification -3 
							if (consentReasonCodeStr != null) {	
								if ((consentReasonCodeStr.equals("Accepted")) || (consentReasonCodeStr.equals("Declined"))){
										if (consumerSendDate.equalsIgnoreCase(consentUpdateDate)){
											upsertResponse.setAutoRefillFlag(true);
										}
									}
							}else{
								upsertResponse.setAutoRefillFlag(false);
							}
		                }
				//if consent type GAR or CFSS 
					 //is consent reason code Accept / decline
					  // is event timestamp = consent timestamp
					//patientconsent upsert
				PtntCnsnt.upsertConsentSN(connection, patientKey, thisConsent.getConsumerId(),storeNumber, thisConsent);
				//Praveen T added for SmartNotification 
				}
			}

			/*
			 * insCovList = patient.getInsuranceCoverage(); if ( insCovList
			 * != null ) { for(InsuranceCoverage thisInsCov:insCovList) {
			 * InsCvrg.upsertInsuranceCoverage(patient.getConsumerId(),
			 * storeNumberString, thisInsCov, patientKey); } }
			 */

			advdrgReactList = patient.getAdverseDrugReaction();
			if (advdrgReactList != null) {
				for (AdverseDrugReaction thisAdvdrgReact : advdrgReactList) {
					AdvdrgReact.upsertAdverseDrugReaction(connection, patientKey, storeNumber, thisAdvdrgReact);
				}
			}

			PatientIdList = patient.getPatientIdentification();
			if (PatientIdList != null) {
				for (PatientIdentification patientId : PatientIdList) {
					patientIdApi.upsertPatientIdentification(connection, patientId, patientKey);
				}
			}

			PatientCoverageApi patientCoverageApi = new PatientCoverageApi();
			List<InsuranceCoverage> InsuranceCoverageList = patient.getInsuranceCoverage();
			if (InsuranceCoverageList != null) {
				for (InsuranceCoverage patientInsurance : InsuranceCoverageList) {
					patientCoverageApi.mergePatientCoverageApi(connection, patientInsurance, patientKey);
				}
			}

			//UpsertResponse upsertResponse = new UpsertResponse();

			upsertResponse.setRxHBBusinessEventActionEnum(rxHBBusinessEventActionEnum);
			upsertResponse.setPatientKey(patientKey);
			if(logger.isDebugEnabled()) {logger.debug(": PatientApi.upsertPatient. patientConsumerId : " + patientConsumerId + ", storeNumber : "
					+ storeNumber + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}

			return upsertResponse;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally
		{
			super.close();
			if(patient != null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: PatientApi.upsertPatient. patientConsumerId : " + patient.getConsumerId());}
		}
	}
	
/*	public UpsertResponse upsertPatient(String storeNumber , Patient patient) throws Exception {
		/* this method returns the RxHBBusinessEventActionEnum.CREATE or RxHBBusinessEventActionEnum.UPDATE.
		 * if the input patient lastUpdateTimeStamp is less than the timestamp in the Ptnt table, then no update would happen. and the method returns null
		 * 
		 /
		UpsertResponse upsertResponse = upsertPatient(storeNumber ,  patient);
		return upsertResponse;
	}
*/	
	
	private Long insertPatient(Connection connection, String storeNumber, Patient patient /*, long PatientKey*/) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {

		ps = connection.prepareStatement(INSERTSQL, new String[] {"PTNTKEY"} /*PreparedStatement.RETURN_GENERATED_KEYS*/);
		String genderTypeCode = (patient.getGenderTypeCode()!=null) ? patient.getGenderTypeCode().value() : null;
		Long gndrTypKey = getKeyFromCode(LT_GNDRTYP, genderTypeCode);
		GroupMembership groupMembership = patient.getGroupmembership();

		setPsXMLGregorianCalendarParam(1, patient.getDateOfBirth());
		setPsXMLGregorianCalendarParam(2, patient.getDeceaseddate());
		
		setPsLongParam(3, gndrTypKey);

		String groupMembershipCode = (groupMembership!=null) ? groupMembership.value() : null;
		Long grpMbrshpTypKey = getKeyFromCode(LT_GRPMBRSHPTYP, groupMembershipCode);
		setPsLongParam(4, grpMbrshpTypKey);
		String patientTypeCode = (patient.getPatienttype()!=null) ? patient.getPatienttype().value() : null;
		Long ptntTypKey = getKeyFromCode(LT_PTNTTYP, patientTypeCode);

		setPsBooleanParam(5, patient.isIsactive() );
		setPsBooleanParam(6, patient.isNoKnownAllergyFlag() );
		
		setPsXMLGregorianCalendarParam(7, patient.getCreateTimestamp());
		setPsXMLGregorianCalendarParam(8, patient.getLastUpdateTimestamp());
		setPsStringParam(9, storeNumber);
		setPsLongParam(10, ptntTypKey);
		
		Person person = patient.getPerson();
		setPsStringParam(11, person.getFirstName());
		setPsStringParam(12, person.getLastName());
		setPsStringParam(13, patient.getConsumerId());
		setPsStringParam(14, person.getFirstName());
		setPsStringParam(15, person.getLastName());
		
		//TE99 added ptnt records
		setPsStringParam(16, person.getMiddleName());
		////Temporary fix for Drop 50 
		setPsStringParam(17, patient.getMobileNumber());//uncommented as part of Drop56
		//DROP57 : Language Data Fields (Bilingual) change. 
		String langCorrCode = (patient.getLanguageCorrespondence()!=null)? patient.getLanguageCorrespondence().value():null;
		Long langCorrKey = getKeyFromCode(LT_LANGUAGES,langCorrCode);
		setPsLongParam(18,langCorrKey);
		
		String sigLangCode =  (patient.getSigLanguage()!=null)? patient.getSigLanguage().value():null;
		Long sigLangKey = getKeyFromCode(LT_LANGUAGES,sigLangCode);
		setPsLongParam(19,sigLangKey);
		
		String monographLangCode =(patient.getMonographLanguage()!=null)?patient.getMonographLanguage().value():null;
		Long mongraphlandKey = getKeyFromCode(LT_LANGUAGES,monographLangCode);
		setPsLongParam(20,mongraphlandKey);
	/*	String salut = (person.getSalutation()!= null) ? person.getSalutation().value() : null;
		setPsStringParam(18, salut);
		*/
		int res = ps.executeUpdate();
		Long patientKey = null;  
		rs = ps.getGeneratedKeys();
		if (rs.next()) {
			patientKey = rs.getLong(1);
		}

		if (logger.isInfoEnabled())  {logger.info(res + " patient has been inserted into ptnt table with PtntKey " + patientKey );}
		return patientKey;
	}

	
	private void updatePatient(Connection connection, Patient patient, long PatientKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long ptntTypKey = null;
		Long langCorrKey=null;
		Long sigLangKey = null;
		Long monographLangKey =null;
		ps = connection.prepareStatement(UPDATESQL);
		Long gndrTypKey = null;
		
		if(patient.getGenderTypeCode() !=null) {
			gndrTypKey = getKeyFromCode(LT_GNDRTYP, patient.getGenderTypeCode().value());
		}
		
		GroupMembership groupMembership = patient.getGroupmembership();
		Long grpMbrshpTypKey = null;
		if( groupMembership != null )
		{
			grpMbrshpTypKey = getKeyFromCode(LT_GRPMBRSHPTYP, groupMembership.value());
		}

		
		if (patient.getPatienttype() != null) {
			ptntTypKey = getKeyFromCode(LT_PTNTTYP, patient.getPatienttype().value());
		}
		
		setPsXMLGregorianCalendarParam(1, patient.getDateOfBirth());
		setPsXMLGregorianCalendarParam(2, patient.getDeceaseddate());
		
		setPsLongParam(3, gndrTypKey);
		setPsLongParam(4, grpMbrshpTypKey);

		setPsBooleanParam(5, patient.isIsactive());
		setPsBooleanParam(6, patient.isNoKnownAllergyFlag());
		
		setPsXMLGregorianCalendarParam(7, patient.getCreateTimestamp());
		setPsXMLGregorianCalendarParam(8, patient.getLastUpdateTimestamp());

		setPsLongParam(9, ptntTypKey);

		Person person = patient.getPerson();
		setPsStringParam(10, person.getFirstName());
		setPsStringParam(11, person.getLastName());
		setPsStringParam(12, patient.getConsumerId());
		//TE99 added ptnt records
		setPsStringParam(13, person.getMiddleName());
		//Temporary fix for Drop 50//
		//setPsStringParam(14, patient.getMobileNumber());//Drop56 		
		/*String salut = (person.getSalutation()!= null) ? person.getSalutation().value() : null;
		setPsStringParam(15, salut);*/
		setPsStringParam(14, person.getFirstName());
		setPsStringParam(15, person.getLastName());
		setPsStringParam(16, patient.getMobileNumber());//Drop56
		
		if(patient.getLanguageCorrespondence()!=null) {
			langCorrKey = getKeyFromCode(LT_LANGUAGES,patient.getLanguageCorrespondence().value());
		}
		setPsLongParam(17, langCorrKey);
		if(patient.getSigLanguage()!=null) {
			sigLangKey = getKeyFromCode(LT_LANGUAGES,patient.getSigLanguage().value());
		}
		setPsLongParam(18, sigLangKey);
		if(patient.getMonographLanguage()!=null) {
			monographLangKey = getKeyFromCode(LT_LANGUAGES,patient.getMonographLanguage().value());
		}
		setPsLongParam(19, monographLangKey);
		setPsLongParam(20, PatientKey);

		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info(res + " patient has been updated in ptnt table with PtntKey " + PatientKey );}
	}
	
/*	private void findPatientbyConsumerID(Connection connection, String consumerId, String storeNumber, XMLGregorianCalendar LastUpdateTimestampXML)
			throws SQLException, ParseException, DatatypeConfigurationException, InvalidInputException {
		ps = connection.prepareStatement(SELECTSQL);
		ps.setString(1, consumerId);
		ps.setString(2, storeNumber);
		rs = ps.executeQuery();
		logger.info("New inp messge. consumerId = " + consumerId + "  StoreNum = " + storeNumber);

		if (rs.next()) {
			patientKey = rs.getLong("PTNTKEY");
			Timestamp lastUpdatedTimeStampDB = rs.getTimestamp("LSTUPDTTIMESTAMP");
			boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(lastUpdatedTimeStampDB, LastUpdateTimestampXML);
			
			if( isUpdateRequestNew == true )
			LastUpdateTimestampDB = CommonUtil.getXMLGregorianCalendar(rs.getDate("LSTUPDTTIMESTAMP"), true);

			int result = LastUpdateTimestampXML.toGregorianCalendar()
					.compareTo(LastUpdateTimestampDB.toGregorianCalendar());
			if (result >= 0) {
				PerformUpd = true;
				PerformIns = false;
				logger.info("This message will be updated");
			}

		} else {
			PerformUpd = false;
			PerformIns = true;
			logger.info("This message will be inserted");
		}

		if (patientKey > 0 && PerformUpd == false && PerformIns == false) {
			logger.info("This inp message ignored because the " + "Last updatetimestamp from inp message = "
					+ CommonUtil.toTimestampStr(LastUpdateTimestampXML)
					+ "  is less than Last updatetimestamp from DataBase    = "
					+ CommonUtil.toTimestampStr(LastUpdateTimestampDB));
		}
	}
*/	
}
