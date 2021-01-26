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
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;

public class TraverseAdverseDrugReaction {

	private String adrAlias  ; 
	private String adrSupervisorPersonAlias ; 
	private String adrSupervisorPersonRoleAlias ; 
	private String adrSupervisorContactMethodAlias ; 
	private String adrSupervisorAddressAlias; 
	private String adrSupervisorEmailAlias ; 
	private String adrSupervisorTelecomAlias; 
	private String adrRecorderPersonAlias ; 
	private String adrRecorderPersonRoleAlias ; 
	private String adrRecorderContactMethodAlias ; 
	private String adrRecorderAddressAlias ; 
	private String adrRecorderEmailAlias; 
	private String adrRecorderTelecomAlias  ; 
	
	Set <Long> patientADRHashSet = new HashSet<Long>();
	
	AdverseDrugReaction currentAdverseDrugReactionObject = null ;
	
	TraverseRecorder   traverseADRRecorder = null ;
	TraverseSupervisor traverseADRSupervisor= null ;

	
	
	public TraverseAdverseDrugReaction(String adrAlias  
			, String adrSupervisorPersonAlias , String adrSupervisorPersonRoleAlias 
			, String adrSupervisorContactMethodAlias , String adrSupervisorAddressAlias
			, String adrSupervisorEmailAlias ,  String adrSupervisorTelecomAlias
			, String adrRecorderPersonAlias , String adrRecorderPersonRoleAlias
			, String adrRecorderContactMethodAlias  
			, String adrRecorderAddressAlias , String adrRecorderEmailAlias, String adrRecorderTelecomAlias  )
	{
		this.adrAlias  = adrAlias ; 
		this.adrSupervisorPersonAlias = adrSupervisorPersonAlias; 
		this.adrSupervisorPersonRoleAlias = adrSupervisorPersonRoleAlias  ; 
		this.adrSupervisorContactMethodAlias = adrSupervisorContactMethodAlias ; 
		this.adrSupervisorAddressAlias = adrSupervisorAddressAlias ; 
		this.adrSupervisorEmailAlias = adrSupervisorEmailAlias ; 
		this.adrSupervisorTelecomAlias = adrSupervisorTelecomAlias ; 
		this.adrRecorderPersonAlias = adrRecorderPersonAlias ; 
		this.adrRecorderPersonRoleAlias = adrRecorderPersonRoleAlias ; 
		this.adrRecorderPersonRoleAlias = adrRecorderPersonRoleAlias ; 
		this.adrRecorderContactMethodAlias = adrRecorderContactMethodAlias ; 
		this.adrRecorderContactMethodAlias = adrRecorderContactMethodAlias ; 
		this.adrRecorderAddressAlias = adrRecorderAddressAlias ; 
		this.adrRecorderAddressAlias = adrRecorderAddressAlias ; 
		this.adrRecorderEmailAlias = adrRecorderEmailAlias; 
		this.adrRecorderTelecomAlias = adrRecorderTelecomAlias  ; 
	}
	
	public AdverseDrugReaction getCurrentAdverseDrugReactionObject()
	{
		return currentAdverseDrugReactionObject ;
	}
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentADRKey = ResultSetWrapper.getLong(rs, adrAlias , "_PTNTADVRSDRGRCTNKEY" ) ;
		
		if( currentADRKey <= 0 )
			return currentADRKey;
		if( patientADRHashSet.contains(currentADRKey) == false ) 
		{
			patientADRHashSet.add(currentADRKey);
			AdverseDrugReaction adverseDrugReaction = PopulateJAXB.populateAdverseDrugReaction(rs, adrAlias);
			if( adverseDrugReaction  != null )
			{
				currentAdverseDrugReactionObject = adverseDrugReaction  ; 
				traverseADRRecorder = null;
				traverseADRSupervisor = null;
				
				
				traverseADRRecorder = new TraverseRecorder (	PersonRoleType.RECORDER , adrRecorderPersonRoleAlias , adrRecorderPersonAlias , 
						adrRecorderContactMethodAlias ,  adrRecorderEmailAlias ,
						adrRecorderAddressAlias , adrRecorderTelecomAlias , null);
				
				
				
				traverseADRSupervisor = new TraverseSupervisor (	PersonRoleType.SUPERVISOR , adrSupervisorPersonRoleAlias , adrSupervisorPersonAlias , 
						adrSupervisorContactMethodAlias ,  adrSupervisorEmailAlias ,
						adrSupervisorAddressAlias , adrSupervisorTelecomAlias , null);
		
			
				
				if( traverseADRRecorder.traverse(rs) == true ) 
					currentAdverseDrugReactionObject.setRecorder(traverseADRRecorder.getRecorder());
				
	
				if( traverseADRSupervisor.traverse(rs) == true ) 
					currentAdverseDrugReactionObject.setSupervisor(traverseADRSupervisor.getSupervisor());
	
				
			
						
			}
			  
		}
		else
		{
			
			if( traverseADRRecorder.traverse(rs) == true ) 
				currentAdverseDrugReactionObject.setRecorder(traverseADRRecorder.getRecorder());
			

			if( traverseADRSupervisor.traverse(rs) == true ) 
				currentAdverseDrugReactionObject.setSupervisor(traverseADRSupervisor.getSupervisor());
 
		}
		return currentADRKey;

	}
	
	
	
}
