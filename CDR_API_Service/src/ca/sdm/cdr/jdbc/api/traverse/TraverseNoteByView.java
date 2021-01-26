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
import ca.shoppersdrugmart.rxhb.ehealth.Note;

public class TraverseNoteByView {


	/******************************************************************************************/ 
	/*    Table Alias variables */   
	String noteAlias; 
	
	/* 0060-SuperVisor Role */
	String noteSupervisorPersonRoleViewAlias; 
	String noteSupervisorContactMethodViewAlias; 
	String noteSupervisorPrfsnlRegAlias ;
	
	
	/* 0060-Recorder Role */;
	String noteRecorderPersonRoleViewAlias ; 
	String noteRecorderContactMethodViewAlias;  
	String noteRecorderPrfsnlRegAlias;
	
	/* 0060-Location */
	String noteLocationContactMethodViewAlias  	;
	
	/* Table Key variables */ 
	long currentNoteKey = 0;
	/* HashSet variables */ 
	
	private Set <Long> noteHashSet = new HashSet<Long>();
	
	
	
	/* current Objects variables */ 
	Note currentNoteObject  = null; 
	/* Travers Classes */
	
	
	TraverseRecorderByView   traverseNoteRecorder = null ;
	TraverseSupervisorByView traverseNoteSupervisor = null ;

	NoteTypeTable noteType = null;
	
	/******************************************************************************************/ 
	
	
	
	public Note getCurrentNoteObject()
	{
		return currentNoteObject;
	}
	
	public TraverseNoteByView( NoteTypeTable noteType , String noteAlias , 
			/* 0060-SuperVisor Role */
			String noteSupervisorPersonRoleViewAlias , String noteSupervisorContactMethodViewAlias ,  String noteSupervisorPrfsnlRegAlias ,
			/* 0060-Recorder Role */
			String noteRecorderPersonRoleViewAlias , String noteRecorderContactMethodViewAlias ,  String noteRecorderPrfsnlRegAlias ,
			/* Location */ 
			String noteLocationContactMethodViewAlias )
	{
		this.noteType = noteType ;
		this.noteAlias = noteAlias; 
		this.noteSupervisorPersonRoleViewAlias  =noteSupervisorPersonRoleViewAlias  ;
		this.noteSupervisorContactMethodViewAlias  =noteSupervisorContactMethodViewAlias  ;
		this.noteSupervisorPrfsnlRegAlias  =noteSupervisorPrfsnlRegAlias  ;
		this.noteRecorderPersonRoleViewAlias=noteRecorderPersonRoleViewAlias;
		this.noteRecorderContactMethodViewAlias=noteRecorderContactMethodViewAlias;
		this.noteRecorderPrfsnlRegAlias =noteRecorderPrfsnlRegAlias ;
	}

	public Long traverse(ResultSet rs) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		currentNoteKey =  ResultSetWrapper.getLong(rs, noteAlias  , "_"+noteType.getNoteTableName()+"Key") ;


		if( currentNoteKey <= 0 )
			return currentNoteKey;
		
		if ( noteHashSet.contains(currentNoteKey) == false  )
		{
			/* reset Note variables */
			
			currentNoteObject = null;
			traverseNoteRecorder = null;
			traverseNoteSupervisor = null;
			
			 traverseNoteSupervisor= new TraverseSupervisorByView(	PersonRoleType.SUPERVISOR  , noteSupervisorPersonRoleViewAlias ,  noteSupervisorContactMethodViewAlias , noteSupervisorPrfsnlRegAlias);

			 traverseNoteRecorder = new TraverseRecorderByView(	PersonRoleType.RECORDER, noteRecorderPersonRoleViewAlias , noteRecorderContactMethodViewAlias , noteRecorderPrfsnlRegAlias);
			
			Note note = PopulateJAXB.populateNote(rs, noteAlias,  noteType.getNoteTableName()+"Key" );
			
			if( note != null )
			{
	//			patient.getNote().add(note);
				currentNoteObject = note ;
				noteHashSet.add(currentNoteKey);
				
				if( traverseNoteRecorder.traverse(rs) == true ) 
					currentNoteObject.setRecorder(traverseNoteRecorder.getRecorder());

				if( traverseNoteSupervisor.traverse(rs) == true ) 
					currentNoteObject.setSupervisor(traverseNoteSupervisor.getSupervisor());

/*				traversrPatientNoteRecorder(rs);
				traversrPatientNoteSupervisor(rs);
*/				
			}
		}
		else
		{
			// populate Note and other PatientNote objects
			traverseNoteRecorder.traverse(rs) ; 
			traverseNoteSupervisor.traverse(rs);
			
/*			traversrPatientNoteRecorder(rs);
			traversrPatientNoteSupervisor(rs);
*/	
		}
	
		return currentNoteKey ;
	}	
}
