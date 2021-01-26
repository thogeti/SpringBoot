package ca.sdm.cdr.jdbc.api.patient.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 

//import com.sdm.cdr.exception.CDRDataException;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.address.query.AddressApi;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.traverse.TraversePatientMetrics;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PatientVitalType;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class QueryPatientMetrics extends JDBCConnection {
	private static Logger logger = LogManager.getLogger(QueryPatientMetrics.class);
	
	/******************************************************************************
	 * query alias
	 ******************************************************************************/
	private final String patientMetricsAlias  = "PtntMtrcs" ;
	
	private final String patientMetricsNoteAlias   = "PtntMtrcsNt" ;
	private final String patientMetricsNoteSupervisorViewAlias  = "nt_s_v";
	private final String patientMetricsNoteSupervisorContactMethodViewAlias  = "nt_s_c_v";
	private final String patientMetricsNoteRecorderViewAlias  = "nt_r_v";
	private final String patientMetricsNoteRecorderContactMethodViewAlias  = "nt_r_c_v";
	
	private final String patientVitalTypeAlias = "PtntVtlTyp";
	
	/******************************************************************************
	 * JaxB objects
	 ******************************************************************************/

	private Patient currentPatientObject = null;
	private PatientMetrics currentPatientMetricsObject = null;
	private TraversePatientMetrics traversePatientMetrics = null ; 
	
	
	
	/*
	 * HashSet
	 */
	private Set <String> patientMetricsConsumerIdHashSet = new HashSet<String>();
	

	private String currentrsCnsmrId;
	
	
	public QueryPatientMetrics (Connection  connection)
	{
		super(connection);
	}
	 
	private String preparePatientMetricsSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"PtntMtrcs","PtntMtrcs"} ,
				// 001 : Medical condition
				{"PtntMtrcsNt","PtntMtrcsNt"} ,
				//005-Supervisor : Patient Metrics Note Supervisor
				{"PERSON","nt_s_v"} ,
				{"PERSON_CONTACT_METHOD","nt_s_c_v"}, 
				// 005-Recorder : Patient Metrics Note Recorder
				{"PERSON","nt_r_v"} ,
				{"PERSON_CONTACT_METHOD","nt_r_c_v"} 
										
									};
				
				
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);

		
		//----------- new SQL below was introduced on 20160616 ---------------------
		String sqlQuery = 
//				" select distinct " +  selectString +
				" select " +  selectString +				
				"  from   " +
				"  		PtntMtrcs PtntMtrcs  " +
				"       left outer join PtntMtrcsNt PtntMtrcsNt                " +
				"       	on PtntMtrcs.PtntMtrcsKey = PtntMtrcsNt.PtntMtrcsKey   " +
				" 		left outer join PERSON nt_s_v  " +
				" 			on PtntMtrcsNt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " +
				" 		left outer join PERSON_CONTACT_METHOD 	nt_s_c_v  " +
				" 			on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey  " +
				" 		 left outer join PERSON nt_r_v  " +
				" 			on PtntMtrcsNt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey   " +
				" 		left outer join PERSON_CONTACT_METHOD nt_r_c_v  " +
				" 			on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey  " +
				"  		left outer join PtntVtlTyp PtntVtlTyp                                 " +
				"  			on PtntMtrcs.PtntVtlTypKey = PtntVtlTyp.PtntVtlTypKey  " +
				"  where   " +
				"  	PtntMtrcs.ptntKey = ?   " +
				"  order by   " +
				"  	PtntMtrcs.PtntMtrcsKey,  " +
				"  	PtntMtrcsNt.PtntMtrcsNtKey,        " +
				"  	PtntVtlTyp.PtntVtlTypKey   		 " ;

		
		
		
		if (logger.isTraceEnabled())  {logger.trace("PatientMetrics Query \n" + sqlQuery) ;}
		return sqlQuery ;
		
	}
	
	public void  getPatientMetrics( long ptntKey , List<PatientMetrics> patientMetricsList) throws Exception
	{
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{	

		
			String query = preparePatientMetricsSQL();
			
			ps = connection.prepareStatement(query);
	    	CommonUtil.setPsLongParam(ps, 1, ptntKey); //preparedStatement.setLong(1, ptntKey );
			rs = ps.executeQuery();
	
		
			traversePatientMetrics = new TraversePatientMetrics
					(	patientMetricsAlias  
						,patientMetricsNoteAlias 
						,patientMetricsNoteRecorderViewAlias , patientMetricsNoteRecorderContactMethodViewAlias
						,patientMetricsNoteSupervisorViewAlias , patientMetricsNoteSupervisorContactMethodViewAlias );
				
			
			while( rs.next() )
			{
				String patientMetricsConsumerId = traversePatientMetrics.traverse(rs);
				if( patientMetricsConsumerId != null && patientMetricsConsumerId != "" )
				{				
					if( patientMetricsConsumerIdHashSet.contains(patientMetricsConsumerId) == false  )
					{
						patientMetricsList.add(traversePatientMetrics.getCurrentPatientMetricsObject());
						patientMetricsConsumerIdHashSet.add(patientMetricsConsumerId);
					}
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

			currentPatientObject = null;
			currentPatientMetricsObject = null;
			traversePatientMetrics = null ; 		
		}		
		
	}


	private void traversePatientMetrics(ResultSet rs , List<PatientMetrics> patientMetricsList) throws IOException, SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, CodeNotFoundFromTableCacheException
	{
	
		String patientMetricsConsumerId = traversePatientMetrics.traverse(rs);
		if( patientMetricsConsumerId == null || patientMetricsConsumerId == "" )
			return ;
			
		if( patientMetricsConsumerIdHashSet.contains(patientMetricsConsumerId) == false  )
		{
			patientMetricsList.add(traversePatientMetrics.getCurrentPatientMetricsObject());
			patientMetricsConsumerIdHashSet.add(patientMetricsConsumerId);
		}
	}
	
}
