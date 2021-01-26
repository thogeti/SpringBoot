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

public class TraverseAdverseDrugReactionByView {

	// 001 : Medical condition
	String adrAlias ;
	// 001-Recorder : MEdical Condition Recorder
	String adrRecorderViewAlias ;
	String adrRecorderContactMethodViewAlias ;
	// 001-Supervisor : MEdical Condition Supervisor
	String adrSupervisorViewAlias ;
	String adrSupervisorContactMethodViewAlias ;
	
	Set <Long> patientADRHashSet = new HashSet<Long>();
	
	AdverseDrugReaction currentAdverseDrugReactionObject = null ;
	
	TraverseRecorderByView   traverseADRRecorder = null ;
	TraverseSupervisorByView traverseADRSupervisor= null ;

	
	
	public TraverseAdverseDrugReactionByView(String adrAlias  
			, String adrRecorderViewAlias , String adrRecorderContactMethodViewAlias 
			, String adrSupervisorViewAlias , String adrSupervisorContactMethodViewAlias
)
	{
		// 001 : Medical condition
		this.adrAlias = adrAlias ;
		// 001-Recorder : MEdical Condition Recorder
		this.adrRecorderViewAlias = adrRecorderViewAlias ; 
		this.adrRecorderContactMethodViewAlias = adrRecorderContactMethodViewAlias ;
		// 001-Supervisor : MEdical Condition Supervisor
		this.adrSupervisorViewAlias = adrSupervisorViewAlias;
		this.adrSupervisorContactMethodViewAlias = adrSupervisorContactMethodViewAlias;
	
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
				
				
				traverseADRRecorder = new TraverseRecorderByView (PersonRoleType.RECORDER , adrRecorderViewAlias , adrRecorderContactMethodViewAlias 
						, null );
				
				
				
				traverseADRSupervisor = new TraverseSupervisorByView (PersonRoleType.SUPERVISOR , adrSupervisorViewAlias , adrSupervisorContactMethodViewAlias 
						, null);
		
			
				
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
