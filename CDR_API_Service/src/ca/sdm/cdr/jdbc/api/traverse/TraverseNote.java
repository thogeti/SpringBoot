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

public class TraverseNote {


	/******************************************************************************************/ 
	/*    Table Alias variables */   
	String noteAlias; 
	
	/* 0060-SuperVisor Role */
	String noteSupervisorPrsnRoleAlias  		;
	String noteSupervisorPrsnAlias  		;
	String noteSupervisorContactMethodAlias  	;
	String noteSupervisorContactMethodEmailAlias	;
	String noteSupervisorContactMethodAddressAlias;
	String noteSupervisorContactMethodTelcomAlias ;
	String noteSupervisorPrfsnlRegAlias 		;
	/* 0060-Recorder Role */;
	String noteRecorderPrsnRoleAlias  		;
	String noteRecorderPrsnAlias  		;
	String noteRecorderContactMethodAlias  	;
	String noteRecorderContactMethodEmailAlias  	;
	String noteRecorderContactMethodAddressAlias  ;
	String noteRecorderContactMethodTelcomAlias  	;
	String noteRecorderPrfsnlRegAlias 		;
	/* 0060-Location */
	String noteLocationContactMethodAlias  	;
	String noteLocationContactMethodEmailAlias  	;
	String noteLocationContactMethodAddressAlias  ;
	String noteLocationContactMethodTelcomAlias  	;
	
	/* Table Key variables */ 
	long currentNoteKey = 0;
	/* HashSet variables */ 
	
	private Set <Long> noteHashSet = new HashSet<Long>();
	
	
	
	/* current Objects variables */ 
	Note currentNoteObject  = null; 
	/* Travers Classes */
	
	
	TraverseRecorder   traverseNoteRecorder = null ;
	TraverseSupervisor traverseNoteSupervisor = null ;

	NoteTypeTable noteType = null;
	
	/******************************************************************************************/ 
	
	
	
	public Note getCurrentNoteObject()
	{
		return currentNoteObject;
	}
	
	public TraverseNote( NoteTypeTable noteType , String noteAlias , 
			/* 0060-SuperVisor Role */
			String noteSupervisorPrsnRoleAlias  ,String noteSupervisorPrsnAlias  ,String noteSupervisorContactMethodAlias  ,
			String noteSupervisorContactMethodEmailAlias,String noteSupervisorContactMethodAddressAlias,String noteSupervisorContactMethodTelcomAlias ,
			String noteSupervisorPrfsnlRegAlias ,
			/* 0060-Recorder */
			String noteRecorderPrsnRoleAlias  ,String noteRecorderPrsnAlias  ,String noteRecorderContactMethodAlias  ,
			String noteRecorderContactMethodEmailAlias  ,String noteRecorderContactMethodAddressAlias  ,String noteRecorderContactMethodTelcomAlias  ,
			String noteRecorderPrfsnlRegAlias ,
			// 0060-Location
			String noteLocationContactMethodAlias  ,String noteLocationContactMethodEmailAlias  ,
			String noteLocationContactMethodAddressAlias  ,String noteLocationContactMethodTelcomAlias  )
	{
		this.noteType = noteType ;
		this.noteAlias = noteAlias; 
		this.noteSupervisorPrsnRoleAlias  =noteSupervisorPrsnRoleAlias  ;
		this.noteSupervisorPrsnAlias  =noteSupervisorPrsnAlias  ;
		this.noteSupervisorContactMethodAlias  =noteSupervisorContactMethodAlias  ;
		this.noteSupervisorContactMethodEmailAlias=noteSupervisorContactMethodEmailAlias;
		this.noteSupervisorContactMethodAddressAlias=noteSupervisorContactMethodAddressAlias;
		this.noteSupervisorContactMethodTelcomAlias =noteSupervisorContactMethodTelcomAlias ;
		this.noteSupervisorPrfsnlRegAlias =noteSupervisorPrfsnlRegAlias ;
		this.noteRecorderPrsnRoleAlias  =noteRecorderPrsnRoleAlias  ;
		this.noteRecorderPrsnAlias  =noteRecorderPrsnAlias  ;
		this.noteRecorderContactMethodAlias  =noteRecorderContactMethodAlias  ;
		this.noteRecorderContactMethodEmailAlias  =noteRecorderContactMethodEmailAlias  ;
		this.noteRecorderContactMethodAddressAlias  =noteRecorderContactMethodAddressAlias  ;
		this.noteRecorderContactMethodTelcomAlias  =noteRecorderContactMethodTelcomAlias  ;
		this.noteRecorderPrfsnlRegAlias =noteRecorderPrfsnlRegAlias ;
		this.noteLocationContactMethodAlias  =noteLocationContactMethodAlias  ;
		this.noteLocationContactMethodEmailAlias  =noteLocationContactMethodEmailAlias  ;
		this.noteLocationContactMethodAddressAlias  =noteLocationContactMethodAddressAlias  ;
		this.noteLocationContactMethodTelcomAlias  =noteLocationContactMethodTelcomAlias  ;
	
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
			
			traverseNoteRecorder = new TraverseRecorder(	PersonRoleType.RECORDER , noteRecorderPrsnRoleAlias , noteRecorderPrsnAlias , 
					noteRecorderContactMethodAlias ,  noteRecorderContactMethodEmailAlias ,
					noteRecorderContactMethodAddressAlias , noteRecorderContactMethodTelcomAlias , noteRecorderPrfsnlRegAlias);

			traverseNoteSupervisor = new TraverseSupervisor(	PersonRoleType.SUPERVISOR , noteSupervisorPrsnRoleAlias , noteSupervisorPrsnAlias , 
					noteSupervisorContactMethodAlias ,  noteSupervisorContactMethodEmailAlias ,
					noteSupervisorContactMethodAddressAlias , noteSupervisorContactMethodTelcomAlias , noteSupervisorPrfsnlRegAlias);
			
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
