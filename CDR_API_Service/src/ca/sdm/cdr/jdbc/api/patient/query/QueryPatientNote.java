package ca.sdm.cdr.jdbc.api.patient.query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.traverse.TraverseNoteByView;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.rxhb.ehealth.Note;

public class QueryPatientNote  extends JDBCConnection {
	private static Logger logger = LogManager.getLogger(QueryPatientNote.class);
	
	//005: Medical condition note
	private final String patientNoteAlias ="ptntnt";
	// 005-Recorder : MEdical Condition Recorder
	private final String patientNoteRecorderViewAlias = "nt_r_v" ;
	private final String patientNoteRecorderContactMethodViewAlias = "nt_r_c_v" ;
	//005-Supervisor : Medical Condition Recorder
	private final String patientNoteSupervisorViewAlias = "nt_r_v" ;
	private final String patientNoteSupervisorContactMethodViewAlias = "nt_r_c_v" ;
		
	
	private TraverseNoteByView traverseNote = null ;
	
	private Set <Long> noteHashSet = new HashSet<Long>();

	public QueryPatientNote (Connection  connection)
	{
		super(connection);
	}
	
	
	private String preparePatientNoteSQL( long ptntKey ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
										{"ptntnt","ptntnt"} ,
										// 005-Recorder : MEdical Condition Recorder
										{"PERSON","nt_r_v"} ,
										{"PERSON_CONTACT_METHOD","nt_r_c_v"} ,
										//005-Supervisor : Medical Condition Recorder
										{"PERSON","nt_s_v"} ,
										{"PERSON_CONTACT_METHOD","nt_s_c_v"} 
										
									};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" FROM " +
//			 	-- 005: Patient note  
			" 	ptntnt ptntnt " + 
//			 	-- 005-Recorder : Note Recorder 
			" 	left outer join PERSON nt_r_v " + 
			" 		on ptntnt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD nt_r_c_v " + 
			" 		on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey " + 
//			 	-- 005-Supervisor : Note Recorder 
			" 	left outer join PERSON nt_s_v " + 
			" 		on ptntnt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD nt_s_c_v " + 
			" 		on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey " + 
//			 	-- 005-Location : Note Recorder 
			" 	   " + 
			"  where  ptntnt.ptntKey = ? " + 
			" 	 " + 
			"  order by  " + 
			"  ptntnt.ptntntKey, " + 
			"  nt_r_v.prsn_prsnkey, " + 
			"  nt_s_v.prsn_prsnkey " ;
		
		if (logger.isTraceEnabled())  {logger.trace("PatientPersonalInfo : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	public void getPatientNotes(long patientKey , List<Note> noteList) throws Exception 
	{
		
		if(logger.isDebugEnabled()) {logger.debug("Start getPatientNotes");}

		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{	
			String query = preparePatientNoteSQL(patientKey);
			
			ps = connection.prepareStatement(query);
			CommonUtil.setPsLongParam(ps, 1, patientKey); //preparedStatement.setLong(1, ptntKey );
			rs = ps.executeQuery();
		
		
			traverseNote = new TraverseNoteByView(NoteTypeTable.PATIENT_NOTE, patientNoteAlias , 
					/* 0060-SuperVisor Role */
					patientNoteSupervisorViewAlias , patientNoteSupervisorContactMethodViewAlias ,  null ,
					/* 0060-Recorder Role */
					patientNoteRecorderViewAlias ,patientNoteRecorderContactMethodViewAlias,  null ,
					/* Location */ 
					null );
		
			while( rs.next() )
			{
				long noteKey = traverseNote.traverse(rs);
				if( noteKey<= 0 )
					return ;
					
				if( noteHashSet.contains(noteKey) == false  )
				{
					noteList.add(traverseNote.getCurrentNoteObject());
					noteHashSet.add(noteKey);
				}
		
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);

			traverseNote = null ;
			noteHashSet = new HashSet<Long>();			
			if(logger.isDebugEnabled()) {logger.debug("END getPatientNotes");}

		}
	}
	
	
}
