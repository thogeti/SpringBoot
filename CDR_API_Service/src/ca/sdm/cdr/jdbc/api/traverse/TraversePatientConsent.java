package ca.sdm.cdr.jdbc.api.traverse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;

public class TraversePatientConsent {

	private String patientConsentAlias;
	private String dispenserPersonRoleViewAlias ; 
	private String dispenserContactMethodViewAlias; 
	private String dispenserPrfsnlRegAlias;
	
	/* current Objects variables */ 
	Consent currentPatientConsentObject = null;
	/* HashSet variables */ 

	Set <Long> patientConsentHashSet = new HashSet<Long>();

	/* Travers Classes */
	TraverseDispenserByView   traverseDispenser = null ;

	
	public TraversePatientConsent(String patientConsentAlias, String dispenserPersonRoleViewAlias ,  String dispenserContactMethodViewAlias , String dispenserPrfsnlRegAlias)
	{
		this.patientConsentAlias = patientConsentAlias ;
		this.dispenserPersonRoleViewAlias = dispenserPersonRoleViewAlias ;
		this.dispenserContactMethodViewAlias = dispenserContactMethodViewAlias ; 
		this.dispenserPrfsnlRegAlias = dispenserPrfsnlRegAlias ;
		
	}
	
	public Consent getCurrentPatientConsentObject()
	{
		return currentPatientConsentObject ;
	}
	
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentConsentKey = ResultSetWrapper.getLong(rs, patientConsentAlias , "_PTNTCNSNTKEY" ) ;
		
		if( currentConsentKey <= 0 )
			return currentConsentKey;
		if( patientConsentHashSet.contains(currentConsentKey) == false ) 
		{
			
			Consent consent = PopulateJAXB.populateConsent(rs, patientConsentAlias);
			if( consent  != null )
			{
				currentPatientConsentObject = consent  ; 
				traverseDispenser = null;
				
				traverseDispenser = new TraverseDispenserByView (	PersonRoleType.DISPENSER , dispenserPersonRoleViewAlias , dispenserContactMethodViewAlias , dispenserPrfsnlRegAlias);

				if( traverseDispenser.traverse(rs) == true ) 
					currentPatientConsentObject.setDispenser(traverseDispenser.getDispenser());
			}
			  
		}
		else
		{
			if( traverseDispenser.traverse(rs) == true ) 
				currentPatientConsentObject.setDispenser(traverseDispenser.getDispenser());
			  
		}
		return currentConsentKey;

	}
}
