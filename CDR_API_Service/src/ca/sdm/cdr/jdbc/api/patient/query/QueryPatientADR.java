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
// import ca.sdm.cdr.jdbc.prescription.query.api.QueryPrescription;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.traverse.TraverseAdverseDrugReactionByView;
import ca.sdm.cdr.jdbc.api.traverse.TraverseAllergy;
import ca.sdm.cdr.jdbc.api.traverse.TraverseNoteByView;
import ca.sdm.cdr.jdbc.api.traverse.TraverseReaction;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;

public class QueryPatientADR  extends JDBCConnection {
		private static Logger logger = LogManager.getLogger(QueryPatientADR.class);
		
	/* 
	 * Alias definitions 
	 * */
	private final String patientAlias  = "ptnt" ;
	// 001 : Adverse DrugReaction
	private final String adrAlias ="adr";
	// 001-Supervisor : AdverseDrugReaction Recorder
	private final String adrRecorderViewAlias = "a_rec_v";
	private final String adrRecorderContactMethodViewAlias = "a_rec_c_v";
	// 001-Supervisor : AdverseDrugReaction Supervisor
	private final String adrSupervisorViewAlias = "a_sup_v";
	private final String adrSupervisorContactMethodViewAlias ="a_sup_c_v";
		
	// 010 Note
	
	private final String adrNoteAlias ="adr_nt";
	// 005-Recorder : MEdical Condition Recorder
	private final String adrNoteRecorderViewAlias = "nt_r_v" ;
	private final String adrNoteRecorderContactMethodViewAlias = "nt_r_c_v" ;
	//005-Supervisor : Medical Condition Recorder
	private final String adrNoteSupervisorViewAlias = "nt_s_v" ;
	private final String adrNoteSupervisorContactMethodViewAlias = "nt_s_c_v" ;

	// 020-Reaction

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
	
	
	/* 
	 * 
	 * */
	
	TraverseAdverseDrugReactionByView traverseAdverseDrugReaction = null;
	
	private long currentPatientADRKey ;
	
    private Map<Long, AdverseDrugReaction> adrMap = new HashMap<Long, AdverseDrugReaction>();
    private Set <Long> adrNoteHashSet = new HashSet<Long>();
    private Set <Long> adrHashSet = new HashSet<Long>();
    
    private Set <Long> adrReactionHashSet = new HashSet<Long>();
    
    private AdverseDrugReaction currentADRObject = null ;
    
    TraverseNoteByView traverseNote = null ;
    TraverseReaction traverseReaction = null;
    
    Reaction currentReactionObject = null ;
    
    

///
    
   private Set <Long> reactionNoteHashSet = new HashSet<Long>();
    
   TraverseNoteByView traverseReactionNotes = null ;
    
    
    
    
    
	public QueryPatientADR (Connection  connection)
	{
		super(connection);
	}

	
	private String prepareADRSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"PtntAdvrsDrgRctn",  "adr"},     

				// Recorder
				{"PERSON",       "a_rec_v"},              
				{"PERSON_CONTACT_METHOD",  "a_rec_c_v"}, 
			 
				// Supervisor
				{"PERSON",       "a_sup_v"},           
				{"PERSON_CONTACT_METHOD",  "a_sup_c_v"}
				
				};
		
		String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);
		
		String sqlQuery = 
			" select distinct " +  selectString +
			" from   " +
			" 		PtntAdvrsDrgRctn adr  " +
			//	-- 001-Supervisor : ADR RECOREDR 
			"	left outer join PERSON a_rec_v " +
			"		on adr.Rcrdr = a_rec_v.prsnrl_prsnrlkey  " +
			"	left outer join PERSON_CONTACT_METHOD a_rec_c_v " +
			"		on a_rec_v.prsn_prsnkey = a_rec_c_v.prsn_prsnkey " +
			//	-- 001-Supervisor : ADR Supervisor 
			"	left outer join PERSON a_sup_v " +
			"		on adr.Sprvsr = a_sup_v.prsnrl_prsnrlkey  " +
			"	left outer join PERSON_CONTACT_METHOD  a_sup_c_v	 " +
			"		on a_sup_v.prsn_prsnkey = a_sup_c_v.prsn_prsnkey " +
			" where   " +
			" 	adr.ptntKey =   ?" +
			"   " +
			" order by   " +
			" 	adr.PtntAdvrsDrgRctnKey " ;


		
		if (logger.isTraceEnabled())  {logger.trace("ADR query : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	
	private String prepareADRNoteSQL( ) throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"PtntAdvrsDrgRctn",  "adr"},     
				{"PtntAdvrsDrgRctnNt",  "adr_nt"},     
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
			" 		PtntAdvrsDrgRctn adr  " +
			//	-- 001-Supervisor : ADR NOTE 
			"     left outer join PtntAdvrsDrgRctnNt adr_nt      " +
			"          on adr.PtntAdvrsDrgRctnKey = adr_nt.PtntAdvrsDrgRctnKey   " +
			//	-- 001-Supervisor : ADR NOTE Supervisor 
			"     left outer join PERSON nt_s_v  " +
			"          on adr_nt.SprvsrKey = nt_s_v.prsnrl_prsnrlkey  " +
			"     left outer join PERSON_CONTACT_METHOD 	nt_s_c_v  " +
			"          on nt_s_v.prsn_prsnkey = nt_s_c_v.prsn_prsnkey  " +
			//  001-Supervisor : ADR NOTE Recorder 
			"     left outer join PERSON nt_r_v  " +
			"          on adr_nt.RcrdrKey = nt_r_v.prsnrl_prsnrlkey   " +
			"     left outer join PERSON_CONTACT_METHOD nt_r_c_v  " +
			"          on nt_r_v.prsn_prsnkey = nt_r_c_v.prsn_prsnkey  " +
			" 		 " +
			" where   " +
			" 	adr.ptntKey =   ? " +
			"   " +
			" order by   " +
			" 	adr.PtntAdvrsDrgRctnKey,  " +
			" 	adr_nt.PtntAdvrsDrgRctnNtKey " ;


		
		if (logger.isTraceEnabled())  {logger.trace("ADR Note query : \n" + sqlQuery) ;}
		return sqlQuery ;
			
			
	}
	

	public String prepareADRReactionSQL() throws CodeNotFoundFromTableCacheException, SQLException, IOException
	{
		String tableAliasList[][] = {
				{"PtntAdvrsDrgRctn","adr"} ,
				{"rctn","rctn"} ,
				// 0020 - Reaction-Note
				{"rctnNt",  "rnt"},     
				// 0020 - Reaction-Note Supervisor
		  	    {"PERSON",       "rnt_s_v"},              
				{"PERSON_CONTACT_METHOD",  "rnt_s_c_v"}, 
				// 0020 - Reaction-Note Recorder
				{"PERSON",       "rnt_r_v"},           
				{"PERSON_CONTACT_METHOD",  "rnt_r_c_v"},
				
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
					" PtntAdvrsDrgRctn adr " + 
					" left outer join Rctn rctn              " + 
					" 	on adr.PtntAdvrsDrgRctnKey = rctn.PtntAdvrsDrgRctnKey  " + 
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
					" adr.PtntKey  = ? " +
					" order by " +
					" rctn.rctnKey ," +
					" rNt.rctnNtKey ," +
					" R_Drug.DrgKey  ," +
					" R_D_PD_M.MfctrKey ," +
					" R_D_ML.MlclKey " ;
	
			return sqlQuery; 
	}

	
	public void getPatientAdverseDrugReaction( long patientKey ,  List<AdverseDrugReaction> adverseDrugReactionList ) throws Exception
	{

		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{	

			String query = prepareADRSQL();
			
			ps = connection.prepareStatement(query);
	    	CommonUtil.setPsLongParam(ps, 1, patientKey); //preparedStatement.setLong(1, ptntKey );
			rs = ps.executeQuery();
	
		
			
			while( rs.next() )
			{
				long patientADRKey = ResultSetWrapper.getLong( rs, adrAlias , "_PTNTADVRSDRGRCTNKEY");
				if ( adrMap.containsKey(patientADRKey) == false )
				{
		
					traverseAdverseDrugReaction = new TraverseAdverseDrugReactionByView
							( adrAlias  
									, adrRecorderViewAlias , adrRecorderContactMethodViewAlias 
									, adrSupervisorViewAlias ,adrSupervisorContactMethodViewAlias);
					
					traverseAdverseDrugReaction(rs , adverseDrugReactionList);
				}
	
				traverseAdverseDrugReaction(rs , adverseDrugReactionList );
			}
	
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			getADRNotes(patientKey);
			
			getADRReaction(patientKey);
	
		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			traverseAdverseDrugReaction = null;
	
		}				
		
	}
	private void traverseAdverseDrugReaction(ResultSet rs ,  List<AdverseDrugReaction> adverseDrugReactionList ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long adrKey = traverseAdverseDrugReaction.traverse(rs);
		if( adrKey<= 0 )
			return ;
			
		if( adrMap.containsKey(adrKey) == false  )
		{
			AdverseDrugReaction adrObj = traverseAdverseDrugReaction.getCurrentAdverseDrugReactionObject();
			adverseDrugReactionList.add(adrObj);
			adrMap.put(adrKey, adrObj);
		}
	}
	
	private void getADRNotes(long patientKey) throws Exception
	{

		
		if (logger.isInfoEnabled())  {logger.info( "Start getADRNotes query " );}
		

		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{			
		
			String query = prepareADRNoteSQL();
			ps = connection.prepareStatement(query);
			ps.setLong(1 , patientKey);
			rs = ps.executeQuery();
			adrNoteHashSet.clear();
			currentADRObject = null;
				
			adrHashSet.clear();
			
			while(rs.next()) 
			{
				long adrKey = ResultSetWrapper.getLong(rs, adrAlias , "_PTNTADVRSDRGRCTNKEY" ) ;
				long adrNoteKey = ResultSetWrapper.getLong(rs, adrNoteAlias , "_PtntAdvrsDrgRctnNtKey" ) ;
				
				if( adrHashSet.contains(adrKey) == false )
				{
					currentADRObject = adrMap.get(adrKey);
					adrHashSet.add(adrKey);
				}
				
				if( adrNoteHashSet.contains(adrNoteKey) == false )
				{
					traverseNote = null;
					
				
					
					
					traverseNote = new TraverseNoteByView (NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE, adrNoteAlias , 
							/* 0060-SuperVisor Role */
							adrNoteSupervisorViewAlias , adrNoteSupervisorContactMethodViewAlias ,  null ,
							/* 0060-Recorder Role */
							adrNoteRecorderViewAlias ,adrNoteSupervisorContactMethodViewAlias,  null ,
							/* Location */ 
							null  );
					
					traverseNote(rs);
	
				}
				else
				{
					traverseNote(rs);
					
				}
				
				
			}
			
			CommonUtil.closePreparedStatementResultSet(ps, rs);
				
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
	
	private void getADRReaction(long patientKey) throws Exception
	{
		
		if (logger.isInfoEnabled())  {logger.info( "Start getADRReaction query " );}

		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try
		{			
		
			
			String query = prepareADRReactionSQL();
			if (logger.isTraceEnabled())  {logger.trace("getADRReaction query : \n" + query) ;}
			ps = connection.prepareStatement(query);
			ps.setLong(1 , patientKey);
			rs = ps.executeQuery();
			adrReactionHashSet.clear();
			currentADRObject = null;
				
			adrHashSet.clear();
			
			while(rs.next()) 
			{
				long adrKey = ResultSetWrapper.getLong(rs, adrAlias , "_PTNTADVRSDRGRCTNKEY" ) ;
				long adrReactionKey = ResultSetWrapper.getLong(rs, reactionAlias , "_rctnKey" ) ;
				
				if( adrHashSet.contains(adrKey) == false )
				{
					currentADRObject = adrMap.get(adrKey);
					adrHashSet.add(adrKey);
				}
				
				if( adrReactionHashSet.contains(adrReactionKey) == false )
				{
					traverseReaction = new TraverseReaction
							(reactionAlias , reactionReactionDrugAlias , reactionDrugAlias 
									, reactionDrugDosageFormAlias ,reactionDrugColorAlias ,  reactionDrugManufacturerAlias 
									, reactionDrugManufacturerRecallAlias , reactionDrugMoleculeAlias , reactionDrugMoleActiveIngredientculeAlias );
					
					traverseADRReaction(rs);
					
				}
				else
				{
					traverseADRReaction(rs);
					
				}
				
				
			}
			
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			
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
	
	public void traverseADRReaction(ResultSet rs) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException
	{
		
		long reactionKey = traverseReaction.traverse(rs);
		if( reactionKey  <=0 )
			return ;
		
		
		if( adrReactionHashSet.contains(reactionKey) == false  )
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

			currentADRObject.setReaction(currentReactionObject);
			adrReactionHashSet.add(reactionKey);
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
	private void traverseNote(ResultSet rs) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long noteKey = traverseNote.traverse(rs); // this sets curr_Disp_key, curr_Disp_Obj
		if( noteKey <= 0 )
			return;
		if( adrNoteHashSet.contains(noteKey) == false )
		{
			currentADRObject.getNote().add(traverseNote.getCurrentNoteObject());
			adrNoteHashSet.add(noteKey);
		}
	}
	
	
}
