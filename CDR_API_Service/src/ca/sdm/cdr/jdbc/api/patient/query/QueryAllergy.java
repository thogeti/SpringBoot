package ca.sdm.cdr.jdbc.api.patient.query;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import ca.sdm.cdr.common.util.ResultSetWrapper;
//import ca.sdm.cdr.jdbc.prescription.query.api.QueryPrescription;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.traverse.TraverseAllergy;
import ca.sdm.cdr.jdbc.api.traverse.TraverseNoteByView;
import ca.sdm.cdr.jdbc.api.traverse.TraverseReaction;
import ca.sdm.cdr.jdbc.api.traverse.TraverseRecorderByView;
import ca.sdm.cdr.jdbc.api.traverse.TraverseSupervisorByView;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;


public class QueryAllergy  extends JDBCConnection {
		private static Logger logger = LogManager.getLogger(QueryAllergy.class);


	private final String allergyAlias ="al";
	private final String allergyDrugAlias = "al_D";
	private final String allergyDrugDosageFormAlias = "al_D_DF";
	private final String allergyDrugColorAlias = "al_D_CLR";
	private final String allergyDrugManufacturerAlias ="al_PD_M";
	private final String allergyDrugManufacturerRecallAlias ="al_PD_MR";
	private final String allergyDrugMoleculeAlias = "al_PD_ML";
	private final String allergyDrugMoleculeActiveIngredientAlias = "al_PD_ML_AI";
		
	// 010 Note
	
	private final String allergyNoteAlias ="al_nt";
	// 005-Recorder : Allergy Note Recorder
	private final String allergyNoteRecorderViewAlias = "nt_r_v" ;
	private final String allergyNoteRecorderContactMethodViewAlias = "nt_r_c_v" ;
	//005-Supervisor : Allergy Note Supervisor
	private final String allergyNoteSupervisorViewAlias = "nt_s_v" ;
	private final String allergyNoteSupervisorContactMethodViewAlias = "nt_s_c_v" ;
	
	// 006-Recorder : Allergy Recorder
	private final String allergyRecorderViewAlias = "r_v" ;
	private final String allergyRecorderContactMethodViewAlias = "r_c_v" ;
	//006-Supervisor : Allergy Supervisor
	private final String allergySupervisorViewAlias = "s_v" ;
	private final String allergySupervisorContactMethodViewAlias = "s_c_v" ;
	//006-Reporter : Allergy Reporter
	private final String allergyReporterViewAlias = "rep_v" ;
	private final String allergyReporterContactMethodViewAlias = "rep_c_v" ;
	//006-Reporter : Allergy Updated Recprder
	private final String allergyUpdatedRecorderViewAlias = "up_r_v" ;
	private final String allergyUpdatedRecorderContactMethodViewAlias = "up_r_c_v" ;
		
	// 0020-Reaction

	private final String reactionAlias ="rctn";
	private final String reactionReactionDrugAlias ="DrgRctn";
	private final String reactionDrugAlias ="R_Drug";
	private final String reactionDrugDosageFormAlias ="R_Drug_DF";
	private final String reactionDrugColorAlias ="R_D_CLR";
	private final String reactionDrugManufacturerAlias ="R_D_PD_M";
	private final String reactionDrugManufacturerRecallAlias ="R_D_PD_MR";
	private final String reactionDrugMoleculeAlias ="R_D_ML";
	private final String reactionDrugMoleActiveIngredientculeAlias ="R_D_ML_AI";		
		
	
	// 0020 Reaction Notes
	
	private final String reactionNoteAlias ="rnt";
	// 005-Recorder : Allergy Note Recorder
	private final String reactionNoteRecorderViewAlias = "rnt_r_v" ;
	private final String reactionNoteRecorderContactMethodViewAlias = "rnt_r_c_v" ;
	//005-Supervisor : Allergy Note Supervisor
	private final String reactionNoteSupervisorViewAlias = "rnt_s_v" ;
	private final String reactionNoteSupervisorContactMethodViewAlias = "rnt_s_c_v" ;
	

    private Map<Long, Allergy> allergyMap = new HashMap<Long, Allergy>();
    private Set <Long> allergyNoteHashSet = new HashSet<Long>();
    private Set <Long> allergyHashSet = new HashSet<Long>();
    private Set <Long> allergyReactionHashSet = new HashSet<Long>();

    private Set <Long> reactionNoteHashSet = new HashSet<Long>();
    
    Allergy currentAllergyObject = null ;
    Reaction currentReactionObject = null ;
    
    TraverseAllergy traverseAllergy = null; 
    TraverseNoteByView traverseNote = null ;
	TraverseReaction traverseReaction = null;
    
    TraverseNoteByView traverseReactionNotes = null ;

    
	public QueryAllergy (Connection  connection)
	{
		super(connection);
	}

	
	
	private String prepareAllergySQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"ptntAlrgy",  "al"},     

				{"Drg",       "al_D"},              
				{"DsgFrm",  "al_D_DF"}, 
			 
				{"DrgClr",       "al_D_CLR"},           
				{"Mfctr",  "al_PD_M"} ,
				{"MfctrDrgRecall",  "al_PD_MR"},
				{"Mlcl",  "al_PD_ML"},
				{"ActIngrdnt",  "al_PD_ML_AI"}
				};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" from   " +
			"        ptntAlrgy al  " +
			"      , Drg al_D " +
			"      , DsgFrm al_D_DF " +
			"      , DrgClr al_D_CLR " +
			"      , Mfctr al_PD_M " +
			"      , MfctrDrgRecall al_PD_MR " +
			"      , Mlcl al_PD_ML " +
			"      , ActIngrdnt al_PD_ML_AI " +
			"  WHERE   " +
			"           al.DrgKey = al_D.DrgKey(+)   " +
			"      AND al_D.DsgFrmKey = al_D_DF.DsgFrmKey (+)   " +
			"      AND al_D.DrgKey = al_D_CLR.DrgKey (+)   " +
			"      AND al_D.MfctrKey = al_PD_M.MfctrKey(+)   " +
			"      AND al_D.MfctrKey = al_PD_MR.MfctrKey  (+)   " +
			"      AND al_D.DrgKey = al_PD_MR.DrgKey(+)   " +
			"      AND al_D.MlclKey = al_PD_ML.MlclKey (+)   " +
			"      AND al_PD_ML.MlclKey = al_PD_ML_AI.MlclKey(+)  	 " +
			"      AND al.ptntKey = ? " +
			"  order by    " +
			"    al_D.DrgKey " +
			"   , al_PD_M.MfctrKey   " +
			"   , al_PD_MR.MfctrDrgRecallKey  " +
			"   , al_PD_ML.MlclKey " ;

		
		if (logger.isTraceEnabled())  {logger.trace("Allergy query : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}


	private String prepareAllergyNoteSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"ptntAlrgy",  "al"},     
				{"ptntAlrgyNt",  "al_nt"},     
				// Supervisor
		  	    {"PERSON",       "nt_s_v"},              
				{"PERSON_CONTACT_METHOD",  "nt_s_c_v"}, 
				// Recorder
				{"PERSON",       "nt_r_v"},           
				{"PERSON_CONTACT_METHOD",  "nt_r_c_v"}
			};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" from   " +
			" 		ptntAlrgy al  " +
			//	-- 001-Supervisor : Allergy NOTE 
			"     left outer join ptntAlrgyNt al_nt      " +
			"          on al.ptntAlrgyKey = al_nt.ptntAlrgyKey   " +
			//	-- 001-Supervisor : Allergy NOTE Supervisor 
			"     left outer join PERSON nt_s_v  " +
			"          on al_nt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " +
			"     left outer join PERSON_CONTACT_METHOD 	nt_s_c_v  " +
			"          on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey  " +
			//  001-Supervisor : Allergy NOTE Recorder 
			"     left outer join PERSON nt_r_v  " +
			"          on al_nt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD nt_r_c_v  " +
			"          on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey  " +
			" 		 " +
			" where   " +
			" 	al.ptntKey =   ? " +
			"   " +
			" order by   " +
			" 	al.ptntAlrgyKey,  " +
			" 	al_nt.ptntAlrgyNtKey " ;


		
		if (logger.isTraceEnabled())  {logger.trace("Allergy Note query : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	
	
	
	private String prepareAllergyPersonRoleSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"ptntAlrgy",  "al"},     
				// Supervisor
		  	    {"PERSON",       "s_v"},              
				{"PERSON_CONTACT_METHOD",  "s_c_v"}, 
				// Recorder
				{"PERSON",       "r_v"},           
				{"PERSON_CONTACT_METHOD",  "r_c_v"},
				// Reporter
				{"PERSON",       "rep_v"},           
				{"PERSON_CONTACT_METHOD",  "rep_c_v"},
				// Updated Recorder
				{"PERSON",       "up_r_v"},           
				{"PERSON_CONTACT_METHOD",  "up_r_c_v"}
											
			};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" FROM " + 
			" 		ptntAlrgy al  " +
			//	-- 001-Supervisor : Allergy Supervisor 
			"     left outer join PERSON s_v  " +
			"          on al.SprvsrKey = s_v.prsnrl_prsnrlkey  " +
			"     left outer join PERSON_CONTACT_METHOD 	s_c_v  " +
			"          on s_v.prsn_prsnkey = s_c_v.prsn_prsnkey  " +
			//  001-Supervisor : Allergy Recorder 
			"     left outer join PERSON r_v  " +
			"          on al.RcrdrKey = r_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD r_c_v  " +
			"          on r_v.prsn_prsnkey = r_c_v.prsn_prsnkey  " +
			//  001-Reporter : Allergy Reporter 
			"     left outer join PERSON rep_v  " +
			"          on al.RPTRKEY = rep_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD rep_c_v  " +
			"          on rep_v.prsn_prsnkey = rep_c_v.prsn_prsnkey  " +
			//  001-Updated Recorder : Allergy Updated Recorder 
			"     left outer join PERSON up_r_v  " +
			"          on al.UPDTRCRDR = up_r_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD up_r_c_v  " +
			"          on up_r_v.prsn_prsnkey = up_r_c_v.prsn_prsnkey  " +
			" 		 " +
			" where   " +
			" 	al.ptntKey =   ? " +
			"   " +
			" order by   " +
			" 	al.ptntAlrgyKey  " ;


		
		if (logger.isTraceEnabled())  {logger.trace("PatientPersonalInfo : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	public String prepareAllergyReactionSQL() throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"ptntAlrgy",  "al"},
				// 0020 - Reaction     
				{"rctn","rctn"} ,

				// 0020 - Reaction-Note
				{"rctnNt",  "rnt"},     
				// 0020 - Reaction-Note Supervisor
		  	    {"PERSON",       "rnt_s_v"},              
				{"PERSON_CONTACT_METHOD",  "rnt_s_c_v"}, 
				// 0020 - Reaction-Note Recorder
				{"PERSON",       "rnt_r_v"},           
				{"PERSON_CONTACT_METHOD",  "rnt_r_c_v"},
				
				// 0020 - Reaction Drugs
				{"DrgRctn","DrgRctn"} ,
				{"drg","R_Drug"} ,
				{"DsgFrm","R_Drug_DF"} ,
				{"DrgClr","R_D_CLR"} ,
				{"Mfctr","R_D_PD_M"} ,
				{"MfctrDrgRecall","R_D_PD_MR"} ,
				{"Mlcl","R_D_ML"} ,
				{"ActIngrdnt","R_D_ML_AI"} 
				
			};
			
			String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
			
			String sqlQuery = 
				" select distinct " +  selectString +
				" from  " + 
					" ptntAlrgy al " + 
					// 0001 - Reaction   
					" left outer join Rctn rctn              " + 
					" 	on al.ptntAlrgyKey = rctn.ptntAlrgyKey  " +

					//	-- 001-Reaction-Supervisor : Reaction NOTE 
					"     left outer join rctnNt rnt      " +
					"          on rctn.rctnKey = rnt.rctnKey   " +
					//	-- 001-Reaction-Supervisor : Reaction NOTE Supervisor 
					"     left outer join PERSON rnt_s_v  " +
					"          on rnt.SprvsrKey = rnt_s_v.prsnrl_prsnrlkey  " +
					"     left outer join PERSON_CONTACT_METHOD 	rnt_s_c_v  " +
					"          on rnt_s_v.prsn_prsnkey = rnt_s_c_v.prsn_prsnkey  " +
					//  001-Reaction-Supervisor : Reaction NOTE Recorder 
					"     left outer join PERSON rnt_r_v  " +
					"          on rnt.RcrdrKey = rnt_r_v.prsnrl_prsnrlkey   " +
					"     left outer join PERSON_CONTACT_METHOD rnt_r_c_v  " +
					"          on rnt_r_v.prsn_prsnkey = rnt_r_c_v.prsn_prsnkey  " +
		
					// 0005 - Reaction Drugs
					 
					" left outer join DrgRctn DrgRctn                     " + 
					" 	on rctn.RctnKey = DrgRctn.RctnKey           " + 
					" left outer join Drg R_Drug                         " + 
					" 	on DrgRctn.DrgKey = R_Drug.DrgKey       " + 
					" left outer join DsgFrm R_Drug_DF " + 
					"      on R_Drug.DsgFrmKey = R_Drug_DF.DsgFrmKey  " + 
					" left outer join DrgClr R_D_CLR " + 
					"      on R_Drug.DrgKey = R_D_CLR.DrgKey " + 
					" left outer join Mfctr R_D_PD_M " + 
					"      on R_Drug.MfctrKey = R_D_PD_M.MfctrKey " + 
					" left outer join MfctrDrgRecall R_D_PD_MR " + 
					"      on R_D_PD_M.MfctrKey = R_D_PD_MR.MfctrKey " + 
					" left outer join Mlcl R_D_ML " + 
					"      on R_Drug.MlclKey = R_D_ML.MlclKey " + 
					" left outer join ActIngrdnt R_D_ML_AI " + 
					"      on R_D_ML.MlclKey = R_D_ML_AI.MlclKey " + 
				"       " + 
				" where " + 
					" al.PtntKey  = ? " +
				" order by " +
					" rctn.rctnKey ," +
					" rNt.rctnNtKey ," +
					" R_Drug.DrgKey  ," +
					" R_D_PD_M.MfctrKey ," +
					" R_D_ML.MlclKey " ;
				
	
			return sqlQuery; 
	}
	
	private String prepareAllergyReactionNoteSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"ptntAlrgy",  "al"},     
				{"ptntAlrgyNt",  "al_nt"},     
				// Supervisor
		  	    {"PERSON",       "nt_s_v"},              
				{"PERSON_CONTACT_METHOD",  "nt_s_c_v"}, 
				// Recorder
				{"PERSON",       "nt_r_v"},           
				{"PERSON_CONTACT_METHOD",  "nt_r_c_v"}
			};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" from   " +
			" 		ptntAlrgy al  " +
			//	-- 001-Supervisor : Allergy NOTE 
			"     left outer join ptntAlrgyNt al_nt      " +
			"          on al.ptntAlrgyKey = al_nt.ptntAlrgyKey   " +
			//	-- 001-Supervisor : Allergy NOTE Supervisor 
			"     left outer join PERSON nt_s_v  " +
			"          on al_nt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " +
			"     left outer join PERSON_CONTACT_METHOD 	nt_s_c_v  " +
			"          on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey  " +
			//  001-Supervisor : Allergy NOTE Recorder 
			"     left outer join PERSON nt_r_v  " +
			"          on al_nt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD nt_r_c_v  " +
			"          on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey  " +
			" 		 " +
			" where   " +
			" 	al.ptntKey =   ? " +
			"   " +
			" order by   " +
			" 	al.ptntAlrgyKey,  " +
			" 	al_nt.ptntAlrgyNtKey " ;


		
		if (logger.isTraceEnabled())  {logger.trace("Allergy Note query : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	
	public void getAllergy( long patientKey ,  List<Allergy> patientAllergyList , boolean linkedAllergy) throws Exception
	{
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{			
	
			String query = prepareAllergySQL();
			
			ps = connection.prepareStatement(query);
	    	CommonUtil.setPsLongParam(ps, 1, patientKey); //preparedStatement.setLong(1, ptntKey );
			rs = ps.executeQuery();
	
			
			while( rs.next() )
			{
				long patientAllergyKey = ResultSetWrapper.getLong( rs, allergyAlias , "_ptntAlrgyKey");
				
				if ( allergyMap.containsKey(patientAllergyKey) == false )
				{
		
					traverseAllergy = new TraverseAllergy
							( allergyAlias ,  allergyDrugAlias ,  allergyDrugDosageFormAlias 
									,  allergyDrugColorAlias ,  allergyDrugManufacturerAlias ,  allergyDrugManufacturerRecallAlias 
									,  allergyDrugMoleculeAlias ,  allergyDrugMoleculeActiveIngredientAlias);
					
				}
	
				traverseAllergy(rs , patientAllergyList );
			}
	
			CommonUtil.closePreparedStatementResultSet(ps, rs);
	
			if( linkedAllergy == false ) 
			{		
				getAllergyNotes(patientKey);
			
				getAllergyPersonRoles(patientKey);
			}
			
			getAllergyReaction(patientKey);
	
		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if (logger.isInfoEnabled())  {logger.info( "End getTxNotes. patientKey: " + patientKey );}
		}		
		
	}
	
	private void traverseAllergy(ResultSet rs ,  List<Allergy> patientAllergyList ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long allergyKey = traverseAllergy.traverse(rs);
		if( allergyKey<= 0 )
			return ;
			
		if( allergyMap.containsKey(allergyKey) == false  )
		{
			Allergy allergyObj = traverseAllergy.getCurrentAllergyObject();
			patientAllergyList.add(allergyObj);
			allergyMap.put(allergyKey, allergyObj);
		}
	}

	
	private void getAllergyNotes(long patientKey) throws Exception
	{

		
		if (logger.isInfoEnabled())  {logger.info( "Start getAllergyNotes query " );}
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{				
		
			String query = prepareAllergyNoteSQL();
			ps = connection.prepareStatement(query);
			ps.setLong(1 , patientKey);
			rs = ps.executeQuery();
			allergyNoteHashSet.clear();
			currentAllergyObject = null;
				
			allergyHashSet.clear();
			
			while(rs.next()) 
			{
				long allergyKey = ResultSetWrapper.getLong(rs, allergyAlias , "_ptntAlrgyKey" ) ;
				long allergyNoteKey = ResultSetWrapper.getLong(rs, allergyNoteAlias , "_ptntAlrgyNtKey" ) ;
				
				if( allergyHashSet.contains(allergyKey) == false )
				{
					currentAllergyObject = allergyMap.get(allergyKey);
					allergyHashSet.add(allergyKey);
				}
				
				if( allergyNoteHashSet.contains(allergyNoteKey) == false )
				{
					traverseNote = null;
					
				
					
					
					traverseNote = new TraverseNoteByView (NoteTypeTable.PATIENT_ALLERGY_NOTE, allergyNoteAlias , 
							/* 0060-SuperVisor Role */
							allergyNoteSupervisorViewAlias , allergyNoteSupervisorContactMethodViewAlias ,  null ,
							/* 0060-Recorder Role */
							allergyNoteRecorderViewAlias ,allergyNoteRecorderContactMethodViewAlias,  null ,
							/* Location */ 
							null  );
					
					traverseNote(rs);
	
				}
				else
				{
					traverseNote(rs);
					
				}
				
				
			}
				
		
//		if (logger.isInfoEnabled())  {logger.info( "End getTxNotes. TxnNum: " + txnNumber + " : storeNumber " + storeNumber  + ".");}
		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if (logger.isInfoEnabled())  {logger.info( "End getTxNotes. patientKey: " + patientKey );}
	
		}						
	}
	
	private void traverseNote(ResultSet rs) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long noteKey = traverseNote.traverse(rs); // this sets curr_Disp_key, curr_Disp_Obj
		if( noteKey <= 0 )
			return;
		if( allergyNoteHashSet.contains(noteKey) == false )
		{
			currentAllergyObject.getNote().add(traverseNote.getCurrentNoteObject());
			allergyNoteHashSet.add(noteKey);
		}
	}	
	
	
	private void getAllergyPersonRoles(long patientKey) throws Exception
	{

		
		if (logger.isInfoEnabled())  {logger.info( "Start getAllergyNotes query " );}
		
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{				
		
		
			String query = prepareAllergyPersonRoleSQL();
			ps = connection.prepareStatement(query);
			ps.setLong(1 , patientKey);
			rs = ps.executeQuery();
			currentAllergyObject = null;
				
			allergyHashSet.clear();
			
			TraverseRecorderByView traverseAllergyRecorder = null;
			TraverseRecorderByView traverseAllergyUpdatedRecorder = null;
			TraverseSupervisorByView traverseAllergySupervisor = null;
	
			while(rs.next()) 
			{
				long allergyKey = ResultSetWrapper.getLong(rs, allergyAlias , "_ptntAlrgyKey" ) ;
				
				if( allergyHashSet.contains(allergyKey) == false )
				{
					currentAllergyObject = allergyMap.get(allergyKey);
					allergyHashSet.add(allergyKey);
					
					traverseAllergyRecorder = null;
					traverseAllergySupervisor = null;
					traverseNote = null;
									
					traverseAllergyRecorder = new TraverseRecorderByView (	PersonRoleType.RECORDER , allergyRecorderViewAlias , allergyRecorderContactMethodViewAlias 
							, null );
							
					traverseAllergyUpdatedRecorder = new TraverseRecorderByView (	PersonRoleType.RECORDER , allergyUpdatedRecorderViewAlias , allergyUpdatedRecorderContactMethodViewAlias 
							, null );
							
					traverseAllergySupervisor = new TraverseSupervisorByView(	PersonRoleType.SUPERVISOR , allergySupervisorViewAlias , allergySupervisorContactMethodViewAlias 
							, null);
			
					
					
					if( traverseAllergyRecorder.traverse(rs) == true ) 
						currentAllergyObject.setRecorder(traverseAllergyRecorder.getRecorder());
					
					if( traverseAllergyUpdatedRecorder.traverse(rs) == true ) 
						currentAllergyObject.setUpdateRecorder(traverseAllergyUpdatedRecorder.getRecorder());
		
					if( traverseAllergySupervisor.traverse(rs) == true ) 
						currentAllergyObject.setSupervisor(traverseAllergySupervisor.getSupervisor());
		
					
				}
				
				else
				{
					if( traverseAllergyRecorder.traverse(rs) == true ) 
						currentAllergyObject.setRecorder(traverseAllergyRecorder.getRecorder());
					
					if( traverseAllergyUpdatedRecorder.traverse(rs) == true ) 
						currentAllergyObject.setUpdateRecorder(traverseAllergyUpdatedRecorder.getRecorder());
		
					if( traverseAllergySupervisor.traverse(rs) == true ) 
						currentAllergyObject.setSupervisor(traverseAllergySupervisor.getSupervisor());
		
							
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
			if (logger.isInfoEnabled())  {logger.info( "End getTxNotes. patientKey: " + patientKey );}
	
		}							
	}
	
	private void getAllergyReaction(long patientKey) throws Exception
	{
		
		if (logger.isInfoEnabled())  {logger.info( "Start getAllergyReaction query " );}
		
		
		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{				
			String query = prepareAllergyReactionSQL();
			if (logger.isTraceEnabled())  {logger.trace("getAllergyReaction query : \n" + query) ;}
			ps = connection.prepareStatement(query);
			ps.setLong(1 , patientKey);
			rs = ps.executeQuery();
			allergyReactionHashSet.clear();
			currentAllergyObject = null;
				
			allergyHashSet.clear();
			
			while(rs.next()) 
			{
				long allergyKey = ResultSetWrapper.getLong(rs, allergyAlias , "_ptntAlrgyKey" ) ;
				long allergyReactionKey = ResultSetWrapper.getLong(rs, reactionAlias , "_rctnKey" ) ;
	
	
				if( allergyHashSet.contains(allergyKey) == false )
				{
					currentAllergyObject = allergyMap.get(allergyKey);
					allergyHashSet.add(allergyKey);
					
				}
				
				if( allergyReactionHashSet.contains(allergyReactionKey) == false )
				{
					traverseReaction = new TraverseReaction
							(reactionAlias , reactionReactionDrugAlias , reactionDrugAlias 
									, reactionDrugDosageFormAlias ,reactionDrugColorAlias ,  reactionDrugManufacturerAlias 
									, reactionDrugManufacturerRecallAlias , reactionDrugMoleculeAlias , reactionDrugMoleActiveIngredientculeAlias );
					
										
					traverseAllergyReaction(rs);
					
				}
				else
				{
					traverseAllergyReaction(rs);
					
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
			if (logger.isInfoEnabled())  {logger.info( "End getTxNotes. patientKey: " + patientKey );}
	
		}			
	}
	
	public void traverseAllergyReaction(ResultSet rs) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException
	{
		
		long reactionKey = traverseReaction.traverse(rs);
		if( reactionKey  <=0 )
			return ;
		
		
		if( allergyReactionHashSet.contains(reactionKey) == false  )
		{
			reactionNoteHashSet.clear();
			traverseReactionNotes = null;
			
			traverseReactionNotes = new TraverseNoteByView (NoteTypeTable.REACTION_NOTE, reactionNoteAlias , 
					/* 0060-SuperVisor Role */
					reactionNoteSupervisorViewAlias , reactionNoteSupervisorContactMethodViewAlias ,  null ,
					/* 0060-Recorder Role */
					reactionNoteRecorderViewAlias ,reactionNoteRecorderContactMethodViewAlias,  null ,
					/* Location */ 
					null  );

			
			currentReactionObject = null;
			currentReactionObject = traverseReaction.getCurrentReactionObject();
			currentAllergyObject.getReaction().add(currentReactionObject);
			allergyReactionHashSet.add(reactionKey);
		}		
		traverseReactionNote(rs);

	}
	
	
	private void traverseReactionNote(ResultSet rs) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long noteKey = traverseReactionNotes.traverse(rs); // this sets curr_Disp_key, curr_Disp_Obj
		if( noteKey <= 0 )
			return;
		if( reactionNoteHashSet.contains(noteKey) == false )
		{
			currentReactionObject.getNote().add(traverseReactionNotes.getCurrentNoteObject());
			reactionNoteHashSet.add(noteKey);
		}
	}	
	
}       
