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
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;

public class TraverseMedicalCondition {

	String patientAlias  ;
	// 001 : Medical condition
	String medicalConditionAlias ;
	// 001-Recorder : MEdical Condition Recorder
	String medicalConditionRecorderViewAlias ;
	String medicalConditionRecorderContactMethodViewAlias ;
	// 001-Supervisor : MEdical Condition Supervisor
	String medicalConditionSupervisorViewAlias ;
	String medicalConditionSupervisorContactMethodViewAlias ;
	
	//005: Medical condition note
	String medicalConditionNoteAlias ;
	// 005-Recorder : MEdical Condition Recorder
	String medicalConditionNoteRecorderViewAlias  ;
	String medicalConditionNoteRecorderContactMethodViewAlias ;
	//005-Supervisor : Medical Condition Recorder
	String medicalConditionNoteSupervisorViewAlias  ;
	String medicalConditionNoteSupervisorContactMethodViewAlias  ;
	
	
	PatientMedicalCondition currentMedicalConditionObject = null ;
	
	Set <Long> patientMedicalConditionHashSet = new HashSet<Long>();
	private Set <Long> noteHashSet = new HashSet<Long>();
	/* Travers Classes */
	TraverseRecorderByView   traverseMedicalConditionRecorder = null ;
	TraverseSupervisorByView traverseMedicalConditionSupervisor= null ;
	TraverseNoteByView traverseNote = null ; 

	
	
	
	public TraverseMedicalCondition(String patientAlias, String medicalConditionAlias  
			, String medicalConditionRecorderViewAlias , String medicalConditionRecorderContactMethodViewAlias 
			, String medicalConditionSupervisorViewAlias , String medicalConditionSupervisorContactMethodViewAlias
			, String  medicalConditionNoteAlias
			, String medicalConditionNoteRecorderViewAlias , String medicalConditionNoteRecorderContactMethodViewAlias
			, String medicalConditionNoteSupervisorViewAlias , String medicalConditionNoteSupervisorContactMethodViewAlias )
	{
		this.patientAlias = patientAlias ;
		// 001 : Medical condition
		this.medicalConditionAlias = medicalConditionAlias ;
		// 001-Recorder : MEdical Condition Recorder
		this.medicalConditionRecorderViewAlias = medicalConditionRecorderViewAlias ; 
		this.medicalConditionRecorderContactMethodViewAlias = medicalConditionRecorderContactMethodViewAlias ;
		// 001-Supervisor : MEdical Condition Supervisor
		this.medicalConditionSupervisorViewAlias = medicalConditionSupervisorViewAlias;
		this.medicalConditionSupervisorContactMethodViewAlias = medicalConditionSupervisorContactMethodViewAlias;
		//005: Medical condition note
		this.medicalConditionNoteAlias = medicalConditionNoteAlias;
		// 005-Recorder : MEdical Condition Recorder
		this.medicalConditionNoteRecorderViewAlias = medicalConditionNoteRecorderViewAlias;
		this.medicalConditionNoteRecorderContactMethodViewAlias = medicalConditionNoteRecorderContactMethodViewAlias;
		//005-Supervisor : Medical Condition Recorder
		this.medicalConditionNoteSupervisorViewAlias = medicalConditionNoteSupervisorViewAlias;
		this.medicalConditionNoteSupervisorContactMethodViewAlias = medicalConditionNoteSupervisorContactMethodViewAlias;
		
		
	}
	
	public PatientMedicalCondition getCurrentMedicalConditionObject()
	{
		return currentMedicalConditionObject ;
	}
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentMedicalConditionKey = ResultSetWrapper.getLong(rs, medicalConditionAlias , "_PTNTMDCLCNDTNKEY" ) ;
		
		if( currentMedicalConditionKey <= 0 )
			return currentMedicalConditionKey;
		if( patientMedicalConditionHashSet.contains(currentMedicalConditionKey) == false ) 
		{
			patientMedicalConditionHashSet.add(currentMedicalConditionKey);
			PatientMedicalCondition medicalCondition = PopulateJAXB.populatePatientMedicalCondition(rs, medicalConditionAlias);
			if( medicalCondition  != null )
			{
				currentMedicalConditionObject = medicalCondition  ; 
				traverseMedicalConditionRecorder = null;
				traverseMedicalConditionSupervisor = null;
				traverseNote = null;
								
				traverseMedicalConditionRecorder = new TraverseRecorderByView (	PersonRoleType.RECORDER , medicalConditionRecorderViewAlias , medicalConditionRecorderContactMethodViewAlias 
						, null );
				traverseMedicalConditionSupervisor = new TraverseSupervisorByView(	PersonRoleType.SUPERVISOR , medicalConditionSupervisorViewAlias , medicalConditionSupervisorContactMethodViewAlias 
						, null);
		
				traverseNote = new TraverseNoteByView(NoteTypeTable.PATIENT_MEDICAL_CONDITION_NOTE, medicalConditionNoteAlias , 
												/* 0060-SuperVisor Role */
												medicalConditionNoteSupervisorViewAlias , medicalConditionNoteSupervisorContactMethodViewAlias ,  null ,
												/* 0060-Recorder Role */
												medicalConditionNoteRecorderViewAlias ,medicalConditionNoteSupervisorContactMethodViewAlias,  null ,
												/* Location */ 
												null );
				
				
				if( traverseMedicalConditionRecorder.traverse(rs) == true ) 
					currentMedicalConditionObject.setRecorder(traverseMedicalConditionRecorder.getRecorder());
				
	
				if( traverseMedicalConditionSupervisor.traverse(rs) == true ) 
					currentMedicalConditionObject.setSupervisor(traverseMedicalConditionSupervisor.getSupervisor());
	
				
				traverseNote(rs);
						
			}
			  
		}
		else
		{
			if( traverseMedicalConditionRecorder.traverse(rs) == true ) 
				currentMedicalConditionObject.setRecorder(traverseMedicalConditionRecorder.getRecorder());
			

			if( traverseMedicalConditionSupervisor.traverse(rs) == true ) 
				currentMedicalConditionObject.setSupervisor(traverseMedicalConditionSupervisor.getSupervisor());

			
			traverseNote(rs);	  
		}
		return currentMedicalConditionKey;

	}
	
	private void traverseNote(ResultSet rs) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long noteKey = traverseNote.traverse(rs);
		if( noteKey<= 0 )
			return ;
			
		if( noteHashSet.contains(noteKey) == false  )
		{
			currentMedicalConditionObject.getNote().add(traverseNote.getCurrentNoteObject());
			noteHashSet.add(noteKey);
		}
	}
	
	
	
}
