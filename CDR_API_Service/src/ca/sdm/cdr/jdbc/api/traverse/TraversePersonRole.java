package ca.sdm.cdr.jdbc.api.traverse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.naming.NamingException;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.jdbc.api.address.query.AddressApi;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
                                 
*/


public abstract class TraversePersonRole {

	private PersonRoleType roleType ;
	private String personRoleAlias ;			// PrsnRl table for ptntAllergyReaction   	
	private String personAlias ;			// Prsn   table for ptntAllergyReaction 
	private String contactMethodAlias ;		// CntctMthd   table for ptntAllergyReaction 
	private String contactMethodEmailAlias ;	// Email   table for ptntAllergyReaction 
	private String contactMethodAddressAlias ;	// Addr   table for ptntAllergyReaction 
	private String contactMethodTelcomAlias ;	// Telcom   table for ptntAllergyReaction 
	private String prfsnlRegAlias ;			// ProfRegistration table for ptntAllergyReaction 
	
//	Supervisor currentObj 		= null ;
	Person currentPersonObj = null;
	Address  		currentAddressObj 		= null ;
	ProfessionalRegistration currentProfRegistrationObj = null;

	
	HashSet<Long> personRoleHashMap = new HashSet<Long>();
	HashSet<Long> ProfRegistrationHashMap = new HashSet<Long>();
	
	String currentEmail;
	String currentPhone;
	String currentFax;
	
	/* Patient Allergy   and Address */
	long currentPersonRoleKey;
	long currentPersonKey;
	long currentContactMethodKey;
	long currentEmailKey;
	long currentAddressKey;
	long currentTelecomKey;
	long currentProfRegistrationKey;
	
	
	public TraversePersonRole(PersonRoleType roleType , String personRoleAlias , String personAlias , String contactMethodAlias ,  String contactMethodEmailAlias ,
			String contactMethodAddressAlias , String contactMethodTelcomAlias , String prfsnlRegAlias)
	{
		this.roleType = roleType ;
		this.personRoleAlias = personRoleAlias ;
		this.personAlias = personAlias;
		this.contactMethodAlias = contactMethodAlias;
		this.contactMethodEmailAlias = contactMethodEmailAlias ;
		this.contactMethodAddressAlias = contactMethodAddressAlias ;
		this.contactMethodTelcomAlias= contactMethodTelcomAlias;
		this.prfsnlRegAlias = prfsnlRegAlias;
		
		
	}
	
	public boolean traverse(ResultSet rs) throws CodeNotFoundFromTableCacheException, CDRInternalException, SQLException, IOException, NamingException
	{
		boolean isNewPersonRoleObject = false ;
		currentPersonRoleKey = rs.getLong(personRoleAlias + "_PRSNRLKEY");

		if( currentPersonRoleKey <= 0 )
			return isNewPersonRoleObject;
		
		if ( personRoleHashMap.contains(currentPersonRoleKey) == false )
		{
			isNewPersonRoleObject = true;

			//currentPatientNoteRecorderObj = null;
			resetPersonRoleObject();
	
//			Recorder recorder = PopulateJAXB.populateRecorder(rs, ptntNoteRecorderPrsnAlias, ptntNoteRecorderPrsnRoleAlias);
			setCurrentPersonRoleObject(rs , personAlias , personRoleAlias );
			
//			if( recorder != null )
			if( getCurrentPersonRoleObjects() != null )
			{
	//			currentPatientNoteRecorderObj = recorder ;
				
//				currentPatientNoteObj.setRecorder(currentPatientNoteRecorderObj);
				personRoleHashMap.add(currentPersonRoleKey);
				
				traversrPersonRoleAddress(rs);
				traversPersonRoleProfessionalRegistration(rs);
			}
		}
		else
		{
			// populate Note and other PatientNote objects
			traversrPersonRoleAddress(rs);
			traversPersonRoleProfessionalRegistration(rs);
	
		}
		
		return isNewPersonRoleObject;
		
	}
	
	private void traversrPersonRoleAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
	{
		
//		currentPatientAddressObj = populateAddress(rs , currentPatientAddressObj , currentPatientEmail , currentPatientPhone , currentPatientFax);
		currentAddressObj = AddressApi.populateAddress(rs , currentAddressObj ,currentEmail , currentPhone , currentFax, contactMethodAlias,
				contactMethodAddressAlias ,contactMethodEmailAlias ,contactMethodTelcomAlias , contactMethodTelcomAlias);
		
		if ( currentAddressObj!= null )
//			getCurrentPresonRolePerson().setAddress(currentAddressObj);
			getCurrentPresonRolePerson().getAddress().add(currentAddressObj);  //VL99 not yet
		
	}
	
	private void traversPersonRoleProfessionalRegistration(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
	{
	
		if( currentProfRegistrationKey <= 0 )
			return;
		
		if ( ProfRegistrationHashMap.contains(currentProfRegistrationKey) == false  )
		{
			currentProfRegistrationObj = null;
	
			ProfessionalRegistration professionalRegistration = PopulateJAXB.populateProfessionalRegistration(rs, prfsnlRegAlias);
			
			if( professionalRegistration != null )
			{
	//			patient.getNote().add(note);
				currentProfRegistrationObj = professionalRegistration ;
				setCurrentPresonRoleObjectProfessionalRegistration(currentProfRegistrationObj);
//				getCurrentCurrentPresonRoleObjectProfessionalRegistration().add(currentProfRegistrationObj);
				
				ProfRegistrationHashMap.add(currentProfRegistrationKey);
				
			}
		}
	
	}
	
	public abstract void setCurrentPersonRoleObject(ResultSet rs , String personAlias , String personRoleAlias)  throws CDRInternalException, SQLException ;
	
	public abstract Object getCurrentPersonRoleObjects();

	public abstract Person getCurrentPresonRolePerson();
//	public abstract List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration();

	public abstract void resetPersonRoleObject();
	public abstract void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj);
	
//	public abstract void setParentPersonRoleObject();
	
}
