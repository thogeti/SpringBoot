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
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;

public class TraversePatientMetrics {


	String patientMetricsAlias ;
	
	String patientMetricsNoteAlias   ;
	String patientMetricsNoteSupervisorViewAlias  ;
	String patientMetricsNoteSupervisorContactMethodViewAlias  ;
	String patientMetricsNoteRecorderViewAlias  ;
	String patientMetricsNoteRecorderContactMethodViewAlias  ;
	
	
	
	PatientMetrics currentPatientMetricsObject = null ;
	
	Set <String> patientMetricsConsumerIdHashSet = new HashSet<String>();
	private Set <Long> noteHashSet = new HashSet<Long>();
	/* Travers Classes */
	TraverseNoteByView traverseNote = null ; 

	String currentPatientMetricsConsumerId = null ;
	
	
	public TraversePatientMetrics(String patientMetricsAlias  
			, String  patientMetricsNoteAlias
			, String patientMetricsNoteRecorderViewAlias , String patientMetricsNoteRecorderContactMethodViewAlias
			, String patientMetricsNoteSupervisorViewAlias , String patientMetricsNoteSupervisorContactMethodViewAlias )
	{
		// 001 : Patient Metrics
		this.patientMetricsAlias = patientMetricsAlias ;
		//005: Patient Metrics note
		this.patientMetricsNoteAlias = patientMetricsNoteAlias;
		// 005-Recorder : Patient Metrics Recorder
		this.patientMetricsNoteRecorderViewAlias = patientMetricsNoteRecorderViewAlias;
		this.patientMetricsNoteRecorderContactMethodViewAlias = patientMetricsNoteRecorderContactMethodViewAlias;
		//005-Supervisor : Patient Metrics Recorder
		this.patientMetricsNoteSupervisorViewAlias = patientMetricsNoteSupervisorViewAlias;
		this.patientMetricsNoteSupervisorContactMethodViewAlias = patientMetricsNoteSupervisorContactMethodViewAlias;
		
		
	}
	
	public PatientMetrics getCurrentPatientMetricsObject()
	{
		return currentPatientMetricsObject ;
	}
	
	public String traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		currentPatientMetricsConsumerId = ResultSetWrapper.getString(rs, patientMetricsAlias , "_CNSMRID" ) ;
		
		if( currentPatientMetricsConsumerId == null || currentPatientMetricsConsumerId  == ""  )
			return currentPatientMetricsConsumerId;
		
		if( patientMetricsConsumerIdHashSet.contains(currentPatientMetricsConsumerId) == false ) 
		{
			currentPatientMetricsObject = null ;
			patientMetricsConsumerIdHashSet.add(currentPatientMetricsConsumerId);
			currentPatientMetricsObject = new PatientMetrics ();
			
			currentPatientMetricsObject  = PopulateJAXB.populatePatientMetrics(rs, patientMetricsAlias , currentPatientMetricsObject );
			traverseNote = null;
								
			traverseNote = new TraverseNoteByView(NoteTypeTable.PATIENT_METRICS_NOTE, patientMetricsNoteAlias , 
											/* 0060-SuperVisor Role */
											patientMetricsNoteSupervisorViewAlias , patientMetricsNoteSupervisorContactMethodViewAlias ,  null ,
											/* 0060-Recorder Role */
											patientMetricsNoteRecorderViewAlias ,patientMetricsNoteSupervisorContactMethodViewAlias,  null ,
											/* Location */ 
											null );
				
				
				
			traverseNote(rs);
						
			
			  
		}
		else
		{
			currentPatientMetricsObject  = PopulateJAXB.populatePatientMetrics(rs, patientMetricsAlias , currentPatientMetricsObject );
	
			traverseNote(rs);	  
		}
		return currentPatientMetricsConsumerId;

	}
	
	private void traverseNote(ResultSet rs) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long noteKey = traverseNote.traverse(rs);
		if( noteKey<= 0 )
			return ;
			
		if( noteHashSet.contains(noteKey) == false  )
		{
			currentPatientMetricsObject.getNote().add(traverseNote.getCurrentNoteObject());
			noteHashSet.add(noteKey);
		}
	}
	
	

}
