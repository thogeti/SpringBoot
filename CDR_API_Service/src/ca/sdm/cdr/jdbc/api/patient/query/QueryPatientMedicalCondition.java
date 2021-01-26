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
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.traverse.TraverseMedicalCondition;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;

public class QueryPatientMedicalCondition  extends JDBCConnection {
	private static Logger logger = LogManager.getLogger(QueryPatientMedicalCondition.class);
	
	/***** 
	 * Alias
	 */
	private final String patientAlias  = "ptnt" ;
	// 001 : Medical condition
	private final String medicalConditionAlias ="mc";
	// 001-Recorder : MEdical Condition Recorder
	private final String medicalConditionRecorderViewAlias = "m_r_v";
	private final String medicalConditionRecorderContactMethodViewAlias = "m_r_c_v";
	// 001-Supervisor : MEdical Condition Supervisor
	private final String medicalConditionSupervisorViewAlias = "m_s_v";
	private final String medicalConditionSupervisorContactMethodViewAlias ="m_s_c_v";
	
	//005: Medical condition note
	private final String medicalConditionNoteAlias ="mc_nt";
	// 005-Recorder : MEdical Condition Recorder
	private final String medicalConditionNoteRecorderViewAlias = "nt_r_v" ;
	private final String medicalConditionNoteRecorderContactMethodViewAlias = "nt_r_c_v" ;
	//005-Supervisor : Medical Condition Recorder
	private final String medicalConditionNoteSupervisorViewAlias = "nt_s_v" ;
	private final String medicalConditionNoteSupervisorContactMethodViewAlias = "nt_s_c_v" ;
	
	
	private long currentPtntKey;
	
	private Patient currentPatientObject = null;
	private PatientMedicalCondition currentMedicalConditionObject = null;
	private TraverseMedicalCondition traverseMedicalCondition = null ; 
	
	
	private Set <Long> medicalConditionHashSet = new HashSet<Long>();
	
	
	public QueryPatientMedicalCondition (Connection  connection)
	{
		super(connection);
	}
	
	private String prepareMedicalConditionSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
										{"Ptnt","Ptnt"} ,
										// 001 : Medical condition
										{"PtntMdclCndtn","mc"} ,
										// 001-Recorder : MEdical Condition Recorder
										{"PERSON","m_r_v"} ,
										{"PERSON_CONTACT_METHOD","m_r_c_v"} ,
										// 001-Supervisor : MEdical Condition Supervisor
										{"PERSON","m_s_v"} ,
										{"PERSON_CONTACT_METHOD","m_s_c_v"} ,
										//005: Medical condition note
										{"PtntMdclCndtnNt","mc_nt"} ,
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
			" 	ptnt ptnt " + 
//			 	-- 001 : Medical condition 
			" 	left outer join    PtntMdclCndtn mc " + 
			" 	     	on ptnt.ptntKey = mc.ptntKey   " + 
//			 	-- 001-Recorder : MEdical Condition Recorder 
			" 	left outer join PERSON m_r_v " + 
			" 		on mc.RcrdrKey = m_r_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD m_r_c_v " + 
			" 		on m_r_v.prsn_prsnkey = m_r_c_v.prsn_prsnkey " + 
//			 	-- 001-Supervisor : MEdical Condition Supervisor 
			" 	left outer join PERSON m_s_v " + 
			" 		on mc.SprvsrKey = m_s_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD 	m_s_c_v " + 
			" 		on m_s_v.prsn_prsnkey = m_s_c_v.prsn_prsnkey " + 
			" 	 " + 
//			 	-- 005: Medical condition note  
			" 	left outer join PtntMdclCndtnNt mc_nt " + 
			" 		on mc.PtntMdclCndtnKey = mc_nt.PtntMdclCndtnKey " + 
//			 	-- 005-Recorder : MEdical Condition Recorder 
			" 	left outer join PERSON nt_r_v " + 
			" 		on mc_nt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD nt_r_c_v " + 
			" 		on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey " + 
//			 	-- 005-Supervisor : Medical Condition Recorder 
			" 	left outer join PERSON nt_s_v " + 
			" 		on mc_nt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " + 
			" 	left outer join PERSON_CONTACT_METHOD nt_s_c_v " + 
			" 		on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey " + 
//			 	-- 005-Location : Medical Condition Recorder 
			" 	   " + 
			"  where  ptnt.ptntKey = ? " + 
			" 	 " + 
			"  order by  " + 
			"  mc.PtntMdclCndtnKey , " + 
			"  m_r_v.prsn_prsnkey , " + 
			"  m_s_v.prsn_prsnkey,  " + 
			"  mc_nt.PtntMdclCndtnNtKey, " + 
			"  nt_r_v.prsn_prsnkey, " + 
			"  nt_s_v.prsn_prsnkey " ;
		
		if (logger.isTraceEnabled())  {logger.trace("PatientPersonalInfo : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	
	

	public Patient getPatientMedicalCondition( long ptntKey ,  List<PatientMedicalCondition> patientMedicalConditionList ) throws Exception
	{
		
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{	
			String query = prepareMedicalConditionSQL();
			
			ps = connection.prepareStatement(query);
	    	CommonUtil.setPsLongParam(ps, 1, ptntKey); //preparedStatement.setLong(1, ptntKey );
			rs = ps.executeQuery();

	
	
		
			while( rs.next() )
			{
				long rsPtntKey = rs.getLong( patientAlias +"_PTNTKEY");
				if ( currentPtntKey != rsPtntKey )
				{
					currentPtntKey = rsPtntKey ;
					Patient p = PopulateJAXB.populatePatient(rs, patientAlias );
					if( p != null )
					{
						
					
						currentPatientObject =  p ;
						currentMedicalConditionObject = null ;
						
	
					
						traverseMedicalCondition = new TraverseMedicalCondition
								(patientAlias, medicalConditionAlias , 
										medicalConditionRecorderViewAlias , medicalConditionRecorderContactMethodViewAlias 
										, medicalConditionSupervisorViewAlias , medicalConditionSupervisorContactMethodViewAlias
										,medicalConditionNoteAlias 
										,medicalConditionNoteRecorderViewAlias , medicalConditionNoteRecorderContactMethodViewAlias
										,medicalConditionNoteSupervisorViewAlias , medicalConditionNoteSupervisorContactMethodViewAlias );
						
						traverseMedicalCondition(rs , patientMedicalConditionList);
	
	/*					traversePatientCoverage.traverse(rs);
						traversePatientIdentification.traverse(rs);
						Address address = traverseAddress.traverse() ;
						if( address != null )
							patient.getPerson().setAddress(address);
						
						currentPatientConsent =
						 	*/
					}
				}
	
				traverseMedicalCondition(rs , patientMedicalConditionList );
			}
		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			traverseMedicalCondition = null;
			
			currentPatientObject = null;
			currentMedicalConditionObject = null;
			traverseMedicalCondition = null ; 
			medicalConditionHashSet.clear();
			medicalConditionHashSet= null;
			
		
		}
		return currentPatientObject;
		
	}
	
	
	
	private void traverseMedicalCondition(ResultSet rs ,  List<PatientMedicalCondition> patientMedicalConditionList) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long medicalConditionKey = traverseMedicalCondition.traverse(rs);
		if( medicalConditionKey<= 0 )
			return ;
			
		if( medicalConditionHashSet.contains(medicalConditionKey) == false  )
		{
			patientMedicalConditionList.add(traverseMedicalCondition.getCurrentMedicalConditionObject());
			medicalConditionHashSet.add(medicalConditionKey);
		}
	}
	
}
