package ca.sdm.cdr.jdbc.api.patient.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 

//import com.sdm.cdr.exception.CDRDataException;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.traverse.TraversePatientConsent;
import ca.sdm.cdr.jdbc.api.traverse.TraversePatientCoverage;
import ca.sdm.cdr.jdbc.api.traverse.TraversePatientIdentification;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;



/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
TE99
                                 
*/


public class QueryPatientPersonalInfo extends JDBCConnection {
	private static Logger logger = LogManager.getLogger(QueryPatientPersonalInfo.class);

	
	boolean hasNext = false;
	/******************************************************************************
	 * query alias
	 ******************************************************************************/
	private final String patientAlias  = "PTNT" ;
	private final String patientContactMethodViewAlias = "p_c_vw"  ;
	private final String patientIdAlias  = "PtntId" ;
	private final String patientCoverageAlias = "IC";  	
	private final String patientConsentAlias = "PTNTCNSNT";
	private final String patientConsentDispenserViewAlias  = "prl_vw" ;
	private final String patientConsentDispenserContactMethodViewAlias = "cd_c_vw"  ;

	//	private final String custPrefAlias = "CUSTPREF";
	private final String patientMedicalConditionAlias ="PtMd";
	private final String patientMedicalConditionNoteAlias ="PtMdNt";
	
	private final String patientAddressContactMethodAlias = "PTNTCNTCTMTHD" ;
	private final String ptntAddressAlias = "PTNTADDR";  	
	private final String ptntEmailAlias = "PTNTEMAIL";  	
	private final String ptntTelecomAlias = "PTNTTELECOM";  	
	private final String ptntNoteAlias = "PTNTNT";  	
	
	

	
	/******************************************************************************
	 * HashMaps , HashSet
	 ******************************************************************************/

			
	/******************************************************************************
	 * JaxB objects
	 ******************************************************************************/

//	Patient 	currentPatientObject 					= null ;

	InsuranceCoverage 	currentPatientInsuranceCoverageObj 	= null ;
	Consent 	currentPatientConsentObj 	= null ;
	Patient currentPatientObject = null;
	
    private Set <Long> coverageHashSet = new HashSet<Long>();
    private Set <Long> patientIdHashSet = new HashSet<Long>();
    


	long currentPtntKey;
	long currentPtntNtKey;
	
	
	// Traverse 
	TraversePatientConsent traversePatientConsent = null ;

	TraversePatientCoverage traversePatientCoverage = null;

	TraversePatientIdentification traversePatientIdentification = null;
	
/******************************************************/

	private Map<String, Integer> PatientUniqAddr = new HashMap<String, Integer>();  //VL99
	HashSet<Long> PtntCvrgHashMap = null;
	HashSet<Long> PtntConsentHashMap = null;
	Set <Long> consentHashSet = new HashSet<Long>();

	
	public QueryPatientPersonalInfo (Connection  connection)
	{
		super(connection);
	}
	
	public Consent getCurrentConsentObject()
	{
		return currentPatientConsentObj ;
	}
	

	private String preparePersonalInfoSQL( long ptntKey ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
//										{"CustPref","CustPref"} ,
										{"Ptnt","Ptnt"} ,
 									    {"PATIENT_CONTACT_METHOD","p_c_vw"} ,
										{"PtntId","PtntId"} ,
										// Patient Coverage   
										{"PtntCvrg","IC"} ,
										// Patient Consent  
										{"PtntCnsnt","PtntCnsnt"} ,
										{"PERSON","prl_vw"} ,
										{"PERSON_CONTACT_METHOD","cd_c_vw"} 
										
										
									};
				
				
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		String sqlQuery = 
			" select distinct " +  selectString +
			" from    " + 
			"	Ptnt      Ptnt   " + 
			"	left outer join PtntId PtntId   " + 
			"		on Ptnt.ptntKey = PtntId.PtntKey   " + 
			"	left outer join PATIENT_CONTACT_METHOD p_c_vw" + 
			"		on ptnt.ptntKey = p_c_vw.ptnt_ptntkey " + 
			"	left outer join PtntCvrg   IC   " + 
			"		on Ptnt.ptntKey = IC.PtntKey   " + 
			"	left outer join PtntCnsnt PtntCnsnt   " + 
			"		on Ptnt.ptntKey = PtntCnsnt.PtntKey   " + 
			"	left outer join PERSON prl_vw " + 
			"		on PtntCnsnt.TXR = prl_vw.prsnrl_prsnrlkey " + 
			"	left outer join prsnrltyp  " + 
			"		on prsnrltyp.PRSNRLTYPKEY = prl_vw.PRSNRL_PRSNRLTYPKEY and  prsnrltyp.CDDESCR = '" + PersonRoleType.DISPENSER + "'" +  
			"	left outer join PERSON_CONTACT_METHOD cd_c_vw" + 
			"		on prl_vw.prsn_prsnkey = cd_c_vw.prsn_prsnkey " + 
			" " + 
			"where    " + 
			"	ptnt.ptntKey = ?    " + 
			"	order by    " + 
			"	Ptnt.PtntKey,     " + 
			"	IC.PtntCvrgKey,    " + 
			"	PtntCnsnt.PtntCnsntKey ," +
			"	prl_vw.prsn_prsnkey  " ;

		
		if (logger.isTraceEnabled())  {logger.trace("PatientPersonalInfo : \n" + sqlQuery) ;}
		return sqlQuery ;
		
	}
	


	public Patient getPatientPersonalInfo( long ptntKey ) throws Exception
	{
		if(logger.isDebugEnabled()) {logger.debug("Start getPatientPersonalInfo");}
		
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{			
		
			String query = TableCacheSingleton.getResource("PatPersInfo.sql");
			
			//	String query =	preparePersonalInfoSQL(ptntKey);
			
			ps = connection.prepareStatement(query);
	    	CommonUtil.setPsLongParam(ps, 1, ptntKey); //preparedStatement.setLong(1, ptntKey );
	    	CommonUtil.setPsLongParam(ps, 2, ptntKey);
	    	rs = ps.executeQuery();
	/*
			PtntCvrgHashMap = new HashSet<Long>();
			patientIdentificationHashMap = new HashSet<Long>();
			PtntConsentHashMap = new HashSet<Long>();
			PtntNoteHashMap	= new HashSet<Long>();
			PtntNoteRecorderHashMap = new HashSet<Long>();
			PtntNoteRecorderProfRegistrationHashMap = new HashSet<Long>();
			
			PtntNoteSupervisorHashMap = new HashSet<Long>();
			PtntNoteSupervisorProfRegistrationHashMap = new HashSet<Long>();
		*/	
	    	 List<String> alterPhonesList = new ArrayList<String>();
	    	 Set<String> alterPhonesSet =  new HashSet<String>();
	    	 String thisAlterPhone =null;
	    	Address thisrow=new Address();
	    	while( rs.next() ) {
				long rsPtntKey = rs.getLong( patientAlias +"_PTNTKEY");
				if ( currentPtntKey != rsPtntKey ) {
					currentPtntKey = rsPtntKey ;
					Patient p = PopulateJAXB.populatePatient(rs, patientAlias );
					if( p != null ) {
						currentPatientObject =  p ;
						traversePatientConsent = null ;
						traversePatientConsent = new TraversePatientConsent(patientConsentAlias,  patientConsentDispenserViewAlias , patientConsentDispenserContactMethodViewAlias,  null);
	
						traversePatientCoverage = null ;
						traversePatientCoverage = new TraversePatientCoverage(patientCoverageAlias);

						traversePatientIdentification = null;
						traversePatientIdentification = new TraversePatientIdentification(patientIdAlias);
						
						
						
						traversrPatientAddress(rs,thisrow);					
						traversePatientConsent(rs);
						traversePatientCoverage(rs);
						traversrPatientIdentification(rs);

/*					traversePatientCoverage.traverse(rs);
						traversePatientIdentification.traverse(rs);
						Address address = traverseAddress.traverse() ;
						if( address != null )
							patient.getPerson().setAddress(address);
						
						currentPatientConsent =
*/
					}
				}
				 thisAlterPhone =	createAlternatePhoneset(rs,thisrow);	
					
				 if (thisAlterPhone != null && thisAlterPhone.trim().length() > 2) {
                	    alterPhonesSet.add(thisAlterPhone);
                  }
               
    		    traversrPatientAddress(rs,thisrow);
				traversePatientConsent(rs);
				traversePatientCoverage(rs);
				traversrPatientIdentification(rs);
			}
			if(!alterPhonesSet.isEmpty()) {
           		alterPhonesList.addAll(alterPhonesSet);
           	}
			if(alterPhonesList!=null &&!alterPhonesList.isEmpty()) {
				
		    	thisrow.setAlternatePhoneNumber(alterPhonesList);
		    }
			
		}
		
		catch( Exception e ) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(logger.isDebugEnabled()) {logger.debug("END getPatientPersonalInfo");}
		}				

		return currentPatientObject;
		
	}
	
	private void traversePatientConsent(ResultSet rs) throws CDRInternalException, CodeNotFoundFromTableCacheException,
	SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		
		long consentKey = traversePatientConsent.traverse(rs);
		if( consentKey<= 0 )
			return ;
			
		if( consentHashSet.contains(consentKey ) == false  )
		{
			currentPatientObject.getConsent().add(traversePatientConsent.getCurrentPatientConsentObject());
			consentHashSet.add(consentKey);
		}
		
	}
	
	private void traversePatientCoverage( ResultSet rs) throws CDRInternalException, CodeNotFoundFromTableCacheException,
	SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		
		long coverageKey = traversePatientCoverage.traverse(rs);
		if( coverageKey<= 0 )
			return ;
			
		if( coverageHashSet.contains(coverageKey ) == false  )
		{
			currentPatientObject.getInsuranceCoverage().add(traversePatientCoverage.getCurrentPatientCoverageObject());
			coverageHashSet.add(coverageKey);
		}
		
	}


	
	/*// VL99 Started
	private void traversrPatientAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
 		
		currentPatientAddressObj = populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax);
		TraverseAddressByView traverseAddress = new TraverseAddressByView(patientContactMethodViewAlias);
		Address address = traverseAddress.traverse(rs);
		
		int index = 0;
		Address prevRow = new Address(); // prev row of SQL ResultSet Address data
		Address thisRow = new Address(); // this row of SQL ResultSet Address data
		String alias = patientContactMethodViewAlias.toUpperCase();
		
		if (rs.getString(alias + "_CM_CNSMRID") != null &&
			rs.getString(alias + "_CM_CNSMRID").length() > 1) {
		
			    PopulateJAXB.PopulateAddress46(rs, alias + "_", thisRow);
		        if ( !(thisRow.getConsumerId() == null || thisRow.getConsumerId().trim().length() == 0)) {
		        	
					if ( PatientUniqAddr.containsKey(thisRow.getConsumerId()) ) {
						index = PatientUniqAddr.get(thisRow.getConsumerId());
						prevRow = currentPatientObject.getPerson().getAddress().get(index);
						
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
		           
		                //TE99 
		                if ( !(prevRow.getAlternatePhoneNumber() == null || prevRow.getAlternatePhoneNumber().trim().length() == 0)) {
		                	thisRow.setAlternatePhoneNumber(prevRow.getAlternatePhoneNumber());
		                }
		                
		                //TE99 Added for contact purpose type
		                if ( !(prevRow.getContactPurposeType() == null )) {
		                	thisRow.setContactPurposeType(prevRow.getContactPurposeType());
		                }
		                
		            	thisRow.setIsActiveAddressFlag(prevRow.isIsActiveAddressFlag()); //TE99
		                currentPatientObject.getPerson().getAddress().set(index, thisRow);
					} else {
		                index = (currentPatientObject.getPerson() != null) ? currentPatientObject.getPerson().getAddress().size() : 0;				
				    	PatientUniqAddr.put(thisRow.getConsumerId(), index);
				    	currentPatientObject.getPerson().getAddress().add(index, thisRow);
					}
		
		//			System.out.println(thisRow.getConsumerId() + "   index = " + index + "  PatientUniqAddr = " + PatientUniqAddr); 
				}
        
		}
	}*/
	// VL99 Ended
	
	
	// VL99 Started
		private void traversrPatientAddress(ResultSet rs,Address thisRow) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		
	
			//TraverseAddressByView traverseAddress = new TraverseAddressByView(patientContactMethodViewAlias);
			//Address address = traverseAddress.traverse(rs) ;
			String alias = patientContactMethodViewAlias.toUpperCase();
			alias="";
			 	     
			List<String> alterPhonesSet =  new ArrayList<String>();
			/*if (rs.getString(alias + "_CM_CNSMRID") != null &&
				rs.getString(alias + "_CM_CNSMRID").length() > 1)*/
				if (rs.getString(alias + "CM_CNSMRID") != null &&
				rs.getString(alias + "CM_CNSMRID").length() > 1) {
				    PopulateJAXB.PopulateAddress46(rs, alias, thisRow,alterPhonesSet);
				    
				     
				    if(currentPatientObject.getPerson().getAddress().size()==0){ // added to limit the address to one instance
				    currentPatientObject.getPerson().getAddress().add(thisRow);
				    }
				   
				 
			}
	        
			
		}
		private String createAlternatePhoneset(ResultSet rs, Address thisRow) {
			String alias = patientContactMethodViewAlias.toUpperCase();
			alias="";
			String thisAlterPhone =null;
			 //GET AlternatePH 
		    try {
				if (rs.getBigDecimal(alias + "ALTERN_TELECOMKEY").compareTo(BigDecimal.ZERO) > 0) {	
					thisRow.setConsumerId(ResultSetWrapper.getString(rs, alias, "CM_CNSMRID"));
					thisAlterPhone = ResultSetWrapper.getString(rs, alias, "ALTERN_TELECOMNUM");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    return thisAlterPhone;
		
	}

		// VL99 Ended
	
	private void traversrPatientIdentification(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException, DatatypeConfigurationException, ParseException  
	{
		
		long patientIdKey = traversePatientIdentification.traverse(rs);
		if( patientIdKey<= 0 )
			return ;
			
		if( patientIdHashSet.contains(patientIdKey ) == false  )
		{
			currentPatientObject.getPatientIdentification().add(traversePatientIdentification.getCurrentPatientIdentificationObject());
			patientIdHashSet.add(patientIdKey);
		}
		
	}
	
	/*
	private void traversePatient(ResultSet rs) throws SQLException, CDRInternalException, CodeNotFoundFromTableCacheException,
 ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		currentPatientIdentificationKey = rs.getLong( patientIdAlias + "_PTNTIDKEY" ); 
		currentPtntKey		= rs.getLong( patientAlias +"_PTNTKEY");
		currentPtntNtKey	= rs.getLong( ptntNoteAlias + "_PTNTNTKEY");
		currentPtntCvrgKey	= rs.getLong( ptntCoverageAlias + "_PTNTCVRGKEY");
		currentPtntCnsntKey	= rs.getLong( ptntConsentAlias + "_PTNTCNSNTKEY");
		currentCntctMthdKey	= rs.getLong( patientAddressContactMethodAlias + "_CNTCTMTHDKEY");
		currentAddrKey		= rs.getLong( ptntAddressAlias + "_ADDRKEY");
		currentEmailKey		= rs.getLong( ptntEmailAlias + "_EMAILKEY");
		currentTelecomKey 	= rs.getLong( ptntTelecomAlias + "_TELECOMKEY");
		currentPatientMedicalConditionKey 	= rs.getLong( patientMedicalConditionAlias + "_PTNTMDCLCNDTNKEY");
		currentPatientMedicalConditionNoteKey 	= rs.getLong( patientMedicalConditionNoteAlias + "_PTNTMDCLCNDTNNTKEY");
		
		 // Patient Note Recorder 
		 
		currentPtntNtRecCntctMthdKey = rs.getLong( ptntNoteRecorderContactMethodAlias + "_CNTCTMTHDKEY");
		currentPtntNtRecPrsnKey  = rs.getLong( ptntNoteRecorderPrsnAlias + "_PRSNKEY");
		currentPtntNtRecPrsnRlKey = rs.getLong( ptntNoteRecorderPrsnRoleAlias+ "_PRSNRLKEY");
		currentPtntNtRecProfRegistrationKey = rs.getLong( ptntNoteRecorderPrfsnlRegAlias+ "_PRFSNLREGKEY");

		 // Patient Note Supervisor 
		currentPtntNtSupervisorCntctMthdKey = rs.getLong( ptntNoteSupervisorContactMethodAlias + "_CNTCTMTHDKEY");
		currentPtntNtSupervisorPrsnKey  = rs.getLong( ptntNoteSupervisorPrsnAlias + "_PRSNKEY");
		currentPtntNtSupervisorPrsnRlKey = rs.getLong( ptntNoteSupervisorPrsnRoleAlias+ "_PRSNRLKEY");
		currentPtntNtSupervisorProfRegistrationKey = rs.getLong( ptntNoteSupervisorPrfsnlRegAlias+ "_PRFSNLREGKEY");


		if ( prevPtntKey != currentPtntKey )
		{

			prevPtntKey		= currentPtntKey	;
			prevPtntNtKey		= currentPtntNtKey	;
			prevPtntCnsntKey	= currentPtntCnsntKey	;
			prevCntctMthdKey	= currentCntctMthdKey	;
			prevAddrKey		= currentAddrKey	;
			prevEmailKey		= currentEmailKey	;
			prevTelecomKey 		= currentTelecomKey 	;
			prevPatientMedicalConditionKey 	 = currentPatientMedicalConditionKey 	;
			prevPatientMedicalConditionNoteKey = currentPatientMedicalConditionNoteKey;


			patient 					= null ;
			currentPatientAddressObj 	= null ;
			currentPatientNoteObj 		= null ;
//			currentPatientCoverageObj 	= null ;
			currentPatientConsentObj 	= null ;
			currentPatientMedicalConditionObj = null ;
			currentPatientMedicalConditionNoteObj = null ;
			
			currentPatientEmail = null;
			currentPatientPhone = null ;
			currentPatientFax = null ;
					
			
			
			PtntCvrgHashMap = null;
			patientIdentificationHashMap = null ;
			PtntConsentHashMap = null;
			PtntNoteHashMap	= null;
			patientMedicalConditionHashMap = null;
			patientMedicalConditionNoteHashMap = null;
			
			PtntCvrgHashMap = new HashSet<Long>();
			patientIdentificationHashMap = new HashSet<Long>();
			PtntConsentHashMap = new HashSet<Long>();
			PtntNoteHashMap	= new HashSet<Long>();
			patientMedicalConditionHashMap = new HashSet<Long>();
			patientMedicalConditionNoteHashMap = new HashSet<Long>();
			
			/****************************
			 *  Patient Recorder 
			 /
			currentPatientNoteRecorderObj = null ; 
			currentPatientNoteRecAddressObj = null ;
			currentRecorderProfRegistrationObj = null; 
			PtntNoteRecorderHashMap = null;
			PtntNoteRecorderHashMap = new HashSet<Long>();
			PtntNoteRecorderProfRegistrationHashMap = null;
			PtntNoteRecorderProfRegistrationHashMap = new HashSet<Long>();
			currentPatientNoteRecEmail = null;
			currentPatientNoteRecFax = null;
			currentPatientNoteRecPhone = null ;

			
			
			Patient p = PopulateJAXB.populatePatient(rs, patientAlias );
			
			if(p != null )
			{
				patient = p ;
				traversePatientIdentification(rs);
				traversePatientNote(rs);
				traverssPatientCoverage(rs);
				traversePatientConsent(rs);
				traversePatientAddress(rs);
				traversePatientMedicalCondition(rs);
		
			}
			
			
		}
		else
		{
			traversePatientNote(rs);
			traverssPatientCoverage(rs);
			traversePatientConsent(rs);
			traversePatientAddress(rs);
		}
	}
	*/

	/*

	private void traversePatientMedicalCondition(ResultSet rs) throws CDRInternalException, CodeNotFoundFromTableCacheException,
									SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		if( currentPatientMedicalConditionKey <= 0 )
			return;
		
		if ( patientMedicalConditionHashMap.contains(currentPatientMedicalConditionKey) == false  )
		{
			currentPatientMedicalConditionObj = null;
	
			PatientMedicalCondition medicalCondition = PopulateJAXB.populatePatientMedicalCondition(rs, patientMedicalConditionAlias);
			if( medicalCondition != null )
			{
	//			patient.getNote().add(note);
				currentPatientMedicalConditionObj = medicalCondition ;
				patient.getPatientMedicalCondition().add(currentPatientMedicalConditionObj);
				patientMedicalConditionHashMap.add(currentPatientMedicalConditionKey);
				
				traversPatientMedicalConditionNote(rs);
			}
			prevPtntCnsntKey = currentPtntCnsntKey  ;
		}
		else
		{
			// populate Note and other PatientNote objects
	
			
		}
		
	}
	
	
	private void traversePatientIdentification(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException 
	{
		if( currentPatientIdentificationKey <= 0 )
			return;
		
		if ( patientIdentificationHashMap.contains(currentPatientIdentificationKey) == false )
		{
			PatientIdentification patientIdentification = PopulateJAXB.populatePatientIdentification(rs , patientIdAlias  );
	
			
			if( patientIdentification != null )
			{
				patient.getPatientIdentification().add(patientIdentification);
			}
		}
	}
	
	private void traversPatientMedicalConditionNote(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException 
	{
		if( currentPatientMedicalConditionNoteKey <= 0 )
			return;
		
		if ( patientMedicalConditionNoteHashMap.contains(currentPatientMedicalConditionNoteKey) == false )
		{
			currentPatientMedicalConditionNoteObj = null;
	
			Note note = PopulateJAXB.populateNote(rs , patientMedicalConditionNoteAlias , "PtntMdclCndtnNtKey" );
			
			if( note != null )
			{
				currentPatientMedicalConditionNoteObj = note ;
				currentPatientMedicalConditionObj.getNote().add(currentPatientMedicalConditionNoteObj);
				patientMedicalConditionNoteHashMap.add(currentPatientMedicalConditionNoteKey);
			}
			prevPatientMedicalConditionNoteKey = currentPatientMedicalConditionNoteKey  ;
		}
		else
		{
			// populate Note and other PatientNote objects
	
		}
		
	}
	
	

	private void traverssPatientCoverage(ResultSet rs) throws SQLException, ParseException, DatatypeConfigurationException, IOException, CDRInternalException, NamingException 
	{
		
		if ( PtntCvrgHashMap.contains(currentPtntCvrgKey) == false )
		{
			currentPatientInsuranceCoverageObj = null;
	
			InsuranceCoverage coverage = PopulateJAXB.populateCoverage(rs, ptntCoverageAlias);
			if( coverage != null )
			{
	//			patient.getNote().add(note);
				currentPatientInsuranceCoverageObj = coverage ;
				patient.getInsuranceCoverage().add(currentPatientInsuranceCoverageObj);
				PtntCvrgHashMap.add(currentPtntCvrgKey);
			}
		}
		else
		{
			// populate Note and other PatientNote objects
	
		}
	}
	
	
	
	private void traversePatientNote(ResultSet rs) throws CDRInternalException, CodeNotFoundFromTableCacheException,
					SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException 
	{
		
		if( currentPtntNtKey <= 0 )
			return;
		
		if ( PtntNoteHashMap.contains(currentPtntNtKey) == false )
		{
			currentPatientNoteObj = null;
	
			Note note = PopulateJAXB.populateNote(rs, ptntNoteAlias, "PtntNtKey" );
			
			if( note != null )
			{
	//			patient.getNote().add(note);
				currentPatientNoteObj = note ;
				patient.getNote().add(currentPatientNoteObj);
				PtntNoteHashMap.add(currentPtntNtKey);
				
				traversrPatientNoteRecorder(rs);
				traversrPatientNoteSupervisor(rs);
			}
			prevPtntNtKey = currentPtntNtKey  ;
		}
		else
		{
			// populate Note and other PatientNote objects
			traversrPatientNoteRecorder(rs);
			traversrPatientNoteSupervisor(rs);
	
		}
		
	}

	private void traversrPatientNoteRecorder(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
	{
		if( currentPtntNtRecPrsnRlKey <= 0 )
			return;
		
		if ( PtntNoteRecorderHashMap.contains(currentPtntNtRecPrsnRlKey) == false )
		{
			currentPatientNoteRecorderObj = null;
	
			Recorder recorder = PopulateJAXB.populateRecorder(rs, ptntNoteRecorderPrsnAlias, ptntNoteRecorderPrsnRoleAlias);
			
			if( recorder != null )
			{
	//			patient.getNote().add(note);
				currentPatientNoteRecorderObj = recorder ;
				
				currentPatientNoteObj.setRecorder(currentPatientNoteRecorderObj);
				PtntNoteRecorderHashMap.add(currentPtntNtRecPrsnRlKey);
				
				traversrPatientNoteRecorderAddress(rs);
				traversPatientNoteRecorderProfessionalRegistration(rs);
			}
		}
		else
		{
			// populate Note and other PatientNote objects
			traversrPatientNoteRecorderAddress(rs);
			traversPatientNoteRecorderProfessionalRegistration(rs);
	
		}
		
	}

	
	private void traversPatientNoteRecorderProfessionalRegistration(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
	{
	
		if( currentPtntNtRecProfRegistrationKey <= 0 )
			return;
		
		if ( PtntNoteRecorderProfRegistrationHashMap.contains(currentPtntNtRecProfRegistrationKey) == false  )
		{
			currentRecorderProfRegistrationObj = null;
	
			ProfessionalRegistration professionalRegistration = PopulateJAXB.populateProfessionalRegistration(rs, ptntNoteRecorderPrfsnlRegAlias);
			
			if( professionalRegistration != null )
			{
	//			patient.getNote().add(note);
				currentRecorderProfRegistrationObj = professionalRegistration ;
				
				currentPatientNoteRecorderObj.getProfessionalRegistration().add(currentRecorderProfRegistrationObj);
				
				PtntNoteRecorderProfRegistrationHashMap.add(currentPtntNtRecProfRegistrationKey);
				
			}
		}
	
	}
	
	
	private void traversrPatientNoteRecorderAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
	{
		
//		currentPatientAddressObj = populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax);
		currentPatientNoteRecAddressObj = AddressApi.populateAddress(rs , currentPatientNoteRecAddressObj ,currentPatientNoteRecEmail , currentPatientNoteRecPhone , currentPatientNoteRecFax, ptntNoteRecorderContactMethodAlias,
				ptntNoteRecorderContactMethodAddressAlias ,ptntNoteRecorderContactMethodEmailAlias ,ptntNoteRecorderContactMethodTelcomAlias , ptntTelecomAlias);
		
		if ( currentPatientNoteRecAddressObj!= null )
			currentPatientNoteRecorderObj.getPerson().setAddress(currentPatientNoteRecAddressObj);

		
	}


	private void traversrPatientNoteSupervisor(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
	{
		if( currentPtntNtSupervisorPrsnRlKey <= 0 )
			return;
		
		if ( PtntNoteSupervisorHashMap.contains(currentPtntNtSupervisorPrsnRlKey) == false  )
		{
			currentPatientNoteSupervisorObj = null;
	
			Supervisor Supervisor = PopulateJAXB.populateSupervisor(rs, ptntNoteSupervisorPrsnAlias, ptntNoteSupervisorPrsnRoleAlias);
			
			if( Supervisor != null )
			{
	//			patient.getNote().add(note);
				currentPatientNoteSupervisorObj = Supervisor ;
				
				currentPatientNoteObj.setSupervisor(currentPatientNoteSupervisorObj);
				PtntNoteSupervisorHashMap.add(currentPtntNtSupervisorPrsnRlKey);
				
				traversrPatientNoteSupervisorAddress(rs);
				traversPatientNoteSupervisorProfessionalRegistration(rs);
			}
		}
		else
		{
			// populate Note and other PatientNote objects
			traversrPatientNoteSupervisorAddress(rs);
			traversPatientNoteSupervisorProfessionalRegistration(rs);
	
		}
		
	}
	
	private void traversPatientNoteSupervisorProfessionalRegistration(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
	{
	
		if( currentPtntNtSupervisorProfRegistrationKey <=0 )
			return;
		
		if ( PtntNoteSupervisorProfRegistrationHashMap.contains(currentPtntNtSupervisorProfRegistrationKey) == false )
		{
			currentSupervisorProfRegistrationObj = null;
	
			ProfessionalRegistration professionalRegistration = PopulateJAXB.populateProfessionalRegistration(rs, ptntNoteSupervisorPrfsnlRegAlias);
			
			if( professionalRegistration != null )
			{
	//			patient.getNote().add(note);
				currentSupervisorProfRegistrationObj = professionalRegistration ;
				
				currentPatientNoteSupervisorObj.getProfessionalRegistration().add(currentSupervisorProfRegistrationObj);
				
				PtntNoteSupervisorProfRegistrationHashMap.add(currentPtntNtSupervisorProfRegistrationKey);
				
			}
		}
	
	}
	
	
	
	private void traversrPatientNoteSupervisorAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
	{
		
//		currentPatientAddressObj = populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax);
		currentPatientNoteSupervisorAddressObj = AddressApi.populateAddress(rs , currentPatientNoteSupervisorAddressObj ,currentPatientNoteSupervisorEmail , currentPatientNoteSupervisorPhone , currentPatientNoteSupervisorFax, ptntNoteSupervisorContactMethodAlias,
				ptntNoteSupervisorContactMethodAddressAlias ,ptntNoteSupervisorContactMethodEmailAlias ,ptntNoteSupervisorContactMethodTelcomAlias , ptntNoteSupervisorContactMethodTelcomAlias);
		
		if ( currentPatientNoteSupervisorAddressObj!= null )
			currentPatientNoteSupervisorObj.getPerson().setAddress(currentPatientNoteSupervisorAddressObj);

		
	}

	private void traversePatientAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
	{
		
//		currentPatientAddressObj = populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax);
		currentPatientAddressObj = AddressApi.populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax, patientAddressContactMethodAlias,ptntAddressAlias ,
				ptntEmailAlias ,ptntTelecomAlias , ptntTelecomAlias);
		
		if ( currentPatientAddressObj!= null )
			patient.getPerson().setAddress(currentPatientAddressObj);

		
	}
	
	*/	
}
