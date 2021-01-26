package ca.sdm.cdr.common.singleton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import javax.naming.NamingException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.RxActiveReminderDeterminationDecisionService;
import com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.RxActiveReminderDeterminationDecisionService_Service;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedcheckDeterminationDecisionService;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedcheckDeterminationDecisionService_Service;
//VL46 import com.bea.core.repackaged.springframework.util.WeakReferenceMonitor.ReleaseListener;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.constant.Constants;
import ca.sdm.cdr.common.singleton.bean.ReferenceTableBean;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Manufacturer;


/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL46 2018-01-26   NTT Data     Vlad Eidinov  SQL Optimization to improve 
                                             GetPatientByQueryCriteria performance
                                             also data type changes from timestamp to date in:
                                                  PTNT.DTOFBIRTH
                                                  PTNT.DCSDDT
                                                  PTNTMTRCS.PTNTVTLDT

VL99 2018-02-15   NTT Data     Vlad Eidinov  QHR Accuro Project
*/


public class TableCacheSingleton extends SingletonReferesh {

	final static Logger logger = LogManager.getLogger(TableCacheSingleton.class);	

	private static volatile TableCacheSingleton instance;
	private static Map<String,Map<String,Long>> codeToKeyHashMap;
	private static Map<String,Map<Long,String>> keyToCodeHashMap;

	private static Map<String, Map<Long, Object>>  TblObjFromKey;  // tbl, <primaryKey, JAXB>
	private static Map<String, Map<String, Long>>  TblKeyFromCode; // tbl, <BusinessID, primaryKey>

	// VL46 Started:
	private static String PatientByQueryCriteria;
	private static String NotificationInsert;
	private static String PersonQueryRole;
	private static String PersonQueryRoleODM;
	private static String PatPersInfo;
	// VL46 Ended
	private static String PatPersInfo_MedProfile;
	private static String GetCustomerQueryByPtntId;
	private static String GetCustomerQueryByCustKeyCaregiver;
	private static String GetCustomerQueryByCustKeyCustomer_PreRegCustomer;
	private static String GetCustKeyAndPrsnKeyQuery;
	private static String GetCustomerQueryByWithOutPatient;
	private static String PrescriptionGet1;
	private static String GetPrescriptions1;
	public  static String JNDI_VERSION;
    public static String  ODMURL_MEDREVIEW;
    public static String ODMURL_MEDCATIONPROFILE;
    
    public static RxActiveReminderDeterminationDecisionService_Service MedProfile_ODM_Service;
    public static MedcheckDeterminationDecisionService_Service MedReview_ODM_Service;
    public static InputStream inpPayload;


	
	private final static String DSGFRM_UPSERT =  
			  "	 MERGE INTO DSGFRM a                       "
		    + "  USING (SELECT ?  DSGFRMKEY,               "
		    + "                ?  DSGFRMSHORTNM,           "
		    + "                ?  DSGFRMFULLNM,            "
		    + "                ?  DSGFRMFULLNMFR           "
		    + "           FROM dual) b                     "
		    + "      ON (a.DSGFRMSHORTNM = b.DSGFRMSHORTNM) " 
		    + "   WHEN MATCHED THEN "
		    + "        UPDATE SET DSGFRMFULLNM   = nvl(b.DSGFRMFULLNM, DSGFRMFULLNM),    "
			+ "                   DSGFRMFULLNMFR = nvl(b.DSGFRMFULLNMFR, DSGFRMFULLNMFR) "
			+ "   WHEN NOT MATCHED THEN          "
			+ "        insert (DSGFRMKEY,        "
			+ "                DSGFRMSHORTNM,    "
			+ "                DSGFRMFULLNM,     "
			+ "                DSGFRMFULLNMFR)   "
			+ "        values (b.DSGFRMKEY,      "
			+ "                b.DSGFRMSHORTNM,  "
			+ "                b.DSGFRMFULLNM,   "
			+ "                b.DSGFRMFULLNMFR) ";


		private final static String MFCTR_UPSERT =
			  " MERGE INTO MFCTR a            "
			+ " USING (SELECT ?  MFCTRKEY,    "
			+ "               ?  MFCTRID,     "
			+ "               ?  VENDORCD,    "
			+ "               ?  MFCTRNMENG,  "
			+ "               ?  MFCTRNMFR    "
			+ "          FROM dual) b         "
			+ "     ON (a.MFCTRID = b.MFCTRID)  "
			+ "   WHEN MATCHED THEN           "
			+ " UPDATE SET VENDORCD   = nvl(b.VENDORCD, VENDORCD),      "
			+ "            MFCTRNMENG = nvl(b.MFCTRNMENG, MFCTRNMENG),  "
			+ "            MFCTRNMFR  = nvl(b.MFCTRNMFR, MFCTRNMFR)     "
	        + "   WHEN NOT MATCHED THEN       " 
			+ "        insert (MFCTRKEY,      "
			+ "                MFCTRID,       "
			+ "                VENDORCD,      "
			+ "                MFCTRNMENG,    "
			+ "                MFCTRNMFR)     "
			+ "        values (b.MFCTRKEY,    "
			+ "                b.MFCTRID,     "
			+ "                b.VENDORCD,    "
			+ "                b.MFCTRNMENG,  "
			+ "                b.MFCTRNMFR)   ";

	
	
	
	
	/* April 25, 2017
	 * change: if the value does not exist in the cache ( database ), insert the value in the database and update the cache.
	 * Solution : 
	 * 		most of the reference tables follow the same pattern in the data model, therefore the insert and update of the cache would be similar.
	 * 		some other tables i.e STORE , may need to be dealt with differently, because they data model is not the same as reference tables.  
	 * 		
	 */
	private static Map<String,ReferenceTableBean> referenceTableMap;
	
	
	// updatableExceptionList contains the list of the tables that are excepted from being updated.
	private static List<String> updatableExceptionList;
	
//    public static Connection  connection = null;
	
    public TableCacheSingleton() { }

    public static TableCacheSingleton getInstance(Connection connection ) throws NamingException, SQLException, IOException
    {
        if (instance == null ) 
        {
            synchronized (TableCacheSingleton.class) 
            {
                if (instance == null) 
                {
//                	connection = conn;
            		codeToKeyHashMap = new HashMap<String,Map<String,Long>>();
                	keyToCodeHashMap = new HashMap<String,Map<Long,String>>();
                	referenceTableMap = new HashMap<String,ReferenceTableBean>();
              	
                    TblKeyFromCode = new HashMap<String, Map<String, Long>>();
                    TblObjFromKey = new HashMap<String, Map<Long, Object>>();
                	
                    instance = new TableCacheSingleton();
                    
			    	String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
			    	if(logger.isDebugEnabled()) {logger.debug("ConfigDirectory ----> : " + ConfigDirectory ) ;}
			    	
			    	String configFileName = ConfigDirectory.trim() + "/" + Constants.TABLE_CACHE_CONFIG_FILENAME;
			    	
			    	String refreshInterval = CDRConfigFileReader.getInstance().getProperty(Constants.TableCacheSingleton_RefreshInterval);
//			    	String schedulerStartTime = CDRConfigFileReader.getInstance().getProperty(Constants.TableCacheSingleton_RefreshSchedulerStartTime);
			    	
			    	instance.initialize( /* schedulerStartTime , */ new Integer(refreshInterval)  ,   configFileName);
                    populateHashMaps(connection);
          
                }
            }
        }
        return instance;    	
    }
    
 /*// Fix for MedReview issue  :Annapurna
    public static MedcheckDeterminationDecisionService getODMPort(String ODMURL_MEDREVIEW ) throws IOException, SQLException {
    	    	com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedcheckDeterminationDecisionService_Service service = new com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedcheckDeterminationDecisionService_Service(new URL(ODMURL_MEDREVIEW));
    	    	com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedcheckDeterminationDecisionService ODMport = service
				.getMedcheckRuleAppMedcheckDeterminationPort();
		return ODMport;
	}
    */
    
    private static RxActiveReminderDeterminationDecisionService_Service getODMServiceForMedicationProfile(
			String DMURL_MEDCATIONPROFILE) throws MalformedURLException {
   // 	com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.RxActiveReminderDeterminationDecisionService_Service service = new com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.RxActiveReminderDeterminationDecisionService_Service(new URL(DMURL_MEDCATIONPROFILE));
   //		com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.RxActiveReminderDeterminationDecisionService port = service.getDigitalRxRuleServiceRxActiveReminderDeterminationPort();
    	 RxActiveReminderDeterminationDecisionService_Service service = new RxActiveReminderDeterminationDecisionService_Service(); 
		return service;
	}
    
    private static MedcheckDeterminationDecisionService_Service getODMServiceForMedReview(
			String DMURL_MEDCATIONPROFILE) throws MalformedURLException {
    	MedcheckDeterminationDecisionService_Service service = new MedcheckDeterminationDecisionService_Service(); 
		return service;
	}

	public static TableCacheSingleton getInstance(String jndiVersion ) throws NamingException,  SQLException, IOException
    {
        if (instance == null ) 
        {
            synchronized (TableCacheSingleton.class) 
            {
                if (instance == null) 
                {
                	Connection connection = null;
                	try
                	{ 
	            		codeToKeyHashMap = new HashMap<String,Map<String,Long>>();
	                	keyToCodeHashMap = new HashMap<String,Map<Long,String>>();
	                	referenceTableMap = new HashMap<String,ReferenceTableBean>();

	                    TblKeyFromCode = new HashMap<String, Map<String, Long>>();
	                    TblObjFromKey = new HashMap<String, Map<Long, Object>>();
	                	
	                    instance = new TableCacheSingleton();
	                    
	                    TableCacheSingleton.JNDI_VERSION = CommonUtil.readConfigFile(jndiVersion);
	                    if(logger.isDebugEnabled()) {logger.debug(" JNDI_VERSION = " + TableCacheSingleton.JNDI_VERSION ) ;}
	                    readResource();    //VL46
   			            connection = CommonUtil.getConnection();
	                    
				    	String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
				    	if(logger.isDebugEnabled()) {logger.debug("ConfigDirectory ----> : " + ConfigDirectory ) ;}
				    	String configFileName = ConfigDirectory.trim() + "/" + Constants.TABLE_CACHE_CONFIG_FILENAME;
				    	String refreshInterval = CDRConfigFileReader.getInstance().getProperty(Constants.TableCacheSingleton_RefreshInterval);
				    	if(logger.isDebugEnabled()) {logger.debug("refreshInterval : " + refreshInterval ) ;}
				    	
				    	instance.initialize( /* schedulerStartTime , */ new Integer(refreshInterval)  ,   configFileName);
     	                populateHashMaps(connection);
				    	if(jndiVersion != null && jndiVersion.equals("MedReviewJndi")&& ODMURL_MEDREVIEW==null) {
				    	  ODMURL_MEDREVIEW = CDRConfigFileReader.getInstance().getProperty(Constants.CDR_PROPERTIES_ODMURL_MEDREVIEW);
				    	  MedReview_ODM_Service = getODMServiceForMedReview(ODMURL_MEDREVIEW);
				//    	  port=getODMPort(ODMURL_MEDREVIEW);// Fix for MedReview issue  :Annapurna
				    	}
				    	if(jndiVersion != null && jndiVersion.equals("MedicationProfileJndi")&& ODMURL_MEDCATIONPROFILE==null) {
				    	 ODMURL_MEDCATIONPROFILE = CDRConfigFileReader.getInstance().getProperty(Constants.CDR_PROPERTIES_ODMURL);
				    	 MedProfile_ODM_Service = getODMServiceForMedicationProfile(ODMURL_MEDCATIONPROFILE);
		         //       port_MedProfile = getODMPortForMedicationProfile(ODMURL_MEDCATIONPROFILE); //Fix for porting issue
				    	}
				    	
				     } catch (SQLException e) {
	        			throw e;
	        		} finally {
	        			CommonUtil.releaseConnection(connection);
	        		}
                }
            }
        }
        return instance;    	
    }
    
    protected void refresh( ) throws NamingException, SQLException, IOException 
    {
        if (instance.isRefreshable()  == true ) 
        {
            synchronized (TableCacheSingleton.class) 
            {
	            if (instance.isRefreshable()  == true ) 
	            {
	            	if(logger.isDebugEnabled()) {logger.debug("Refreshing cache : " + configFileName  + " .... " );}
	            	
	    	    	Connection connection = null;
	    	    	try
	    	    	{
						connection = CommonUtil.getConnection();
						clearObjects();
		    	    	
		    	        populateHashMaps(connection);
		    	        resetRefreshTimeStamps();
	                } catch (SQLException e) {
   	        			throw e;
	        		} finally {
	        			CommonUtil.releaseConnection(connection);
	        		}	    	        
	            }
            }
        }    	

    }
    
    public void forceRefresh() throws NamingException, SQLException, IOException
    {
    	isForcedRefreshed = true;
    	refresh();
    }
  
    private void clearObjects() {
    	codeToKeyHashMap.clear();
 		codeToKeyHashMap = null;
    	codeToKeyHashMap = new HashMap<String,Map<String,Long>>();
    	
    	keyToCodeHashMap.clear();
 		keyToCodeHashMap = null ;
    	keyToCodeHashMap = new HashMap<String,Map<Long,String>>();

    	referenceTableMap.clear();
 		referenceTableMap = null;
    	referenceTableMap = new HashMap<String,ReferenceTableBean>();

        TblObjFromKey.clear();
        TblKeyFromCode.clear();    	
    	
    	
    	updatableExceptionList.clear();
    	updatableExceptionList = null;
    	updatableExceptionList = new ArrayList<String>();
    }
  
    private static void populateHashMaps(Connection connection) throws SQLException, IOException {
		String DSGFRM_SQL =
			    "select DSGFRMKEY,     "
	          + "       DSGFRMSHORTNM, "
	          + "       DSGFRMFULLNM,  "
	          + "       DSGFRMFULLNMFR "
	          + "  from dsgfrm ";
	   
		String MFCTR_SQL = 
				"select MFCTRKEY,   "
	          + "       MFCTRID,    "
	          + "       VENDORCD,   "
	          + "       MFCTRNMENG, "
	          + "       MFCTRNMFR   "
	          + "  from MFCTR       ";
    	
    	readConfigFile(connection);	
    	
        TblObjFromKey.clear();
        TblKeyFromCode.clear();
        if(logger.isDebugEnabled()) {logger.debug("DSGFRM cache creation started");}
        createCache(connection, "DSGFRM", DSGFRM_SQL);

        
        if(logger.isDebugEnabled()) {logger.debug("MANUFACTURER cache creation started");}
        createCache(connection, "MFCTR", MFCTR_SQL);
    }

    
    
    private static void readConfigFile(Connection connection) throws SQLException, IOException { 
    	BufferedReader br = null;
    	File fin = null;
    	FileInputStream fis = null ;
    	ResultSet rs= null;
    	Statement stmt = null;
    	try
    	{
	    	fin = new File(instance.configFileName);
	    	if(logger.isDebugEnabled()) {logger.debug("File path : " + fin.getCanonicalPath() ) ; }
	    	
	    	fis = new FileInputStream(fin);
	    	br = new BufferedReader(new InputStreamReader(fis));
	     
	    	String line = null;
	    	while ((line = br.readLine()) != null  &&  (line.trim().equals("") == false) ) 
	    	{
	    		
	    		String[] tokens = line.split(",");
	    		int tokenCount = tokens.length;
	    		
	    		
	    		
	    		if( tokenCount == 3 || tokenCount == 4 )
	    		{
	
			    	updatableExceptionList = new ArrayList<String>();
		    		ReferenceTableBean refRableBean = new ReferenceTableBean();
		    		String tableName = tokens[0].toUpperCase();
		    		String keyColName = tokens[1].toUpperCase();
		    		String codeColName = tokens[2].toUpperCase();
		    		if( tokenCount == 4 )
		    		{
		    			String isUpdatable = tokens[3];
		    			if( isUpdatable.equals("false")==true )
		    				refRableBean.setUpdatable(false);
		    			else
		    				refRableBean.setUpdatable(true);
		    		}
		    		else
		    			
		    			
		    			refRableBean.setUpdatable(true);
		    		
		    		refRableBean.setTableName(tableName);
		    		refRableBean.setKeyColumnName(keyColName);
		    		refRableBean.setCodeColumnName(codeColName);
		    		
		    		referenceTableMap.put(tableName, refRableBean);

/*		    		if(isUpdatable !=null && isUpdatable.equalsIgnoreCase("N") )
		    			updatableExceptionList.add(tableName);
*/		    			
		    		
		    		String query = "SELECT " +  keyColName + " , " + codeColName + " FROM " + tableName ;
		    		//	String query_2 = "SELECT SUA.* FROM Database.ERETDBT.sdm_user_address AS SUA WHERE SUA.id = ?";
		    		if(logger.isDebugEnabled()) {logger.debug("query  : " + query  );	}
					stmt=connection.createStatement();
					rs=stmt.executeQuery(query );
					
					Map<Long,String> keyHM= new HashMap<Long,String>();
					Map<String,Long> codeHM= new HashMap<String,Long>();

					while(rs.next() ) {
						Long key = (Long)rs.getLong(keyColName.trim());  //VL99
						String code = rs.getString(codeColName.trim());  //VL99
						
						keyHM.put(key, code);
						codeHM.put(code, key);
						
						if (logger.isTraceEnabled())  {logger.trace("code: " + code ) ;}
					}
					stmt.close();
					stmt = null;
					if( rs != null)
					{
						rs.close();
						rs = null;
					}
					codeToKeyHashMap.put(tableName.toUpperCase(), codeHM);
		        	keyToCodeHashMap.put(tableName.toUpperCase(), keyHM);
		        	
	    		}
	    		//TE97_024 TableCache Introduced start
	    		else{
	    			
	    			String[] tokens_prov = line.split("\\|");
	    			String query_prov=tokens_prov[0];
	    			String[] column_spilt=tokens_prov[1].split(",");
	    			String query_tableName=column_spilt[0];
	    			String query_keyColName=column_spilt[1];
	    			String query_codeColName=column_spilt[2];
	    			
	    			updatableExceptionList = new ArrayList<String>();
		    		ReferenceTableBean refRableBean = new ReferenceTableBean();
	    			refRableBean.setUpdatable(true);
		    		refRableBean.setTableName(query_tableName);
		    		refRableBean.setKeyColumnName(query_keyColName);
		    		refRableBean.setCodeColumnName(query_codeColName);
		    		
		    		referenceTableMap.put(query_tableName, refRableBean);

/*		    		if(isUpdatable !=null && isUpdatable.equalsIgnoreCase("N") )
		    			updatableExceptionList.add(tableName);
*/		    			
		    		
		    		String query = query_prov;
		    		//	String query_2 = "SELECT SUA.* FROM Database.ERETDBT.sdm_user_address AS SUA WHERE SUA.id = ?";
		    		if(logger.isDebugEnabled()) {logger.debug("query  : " + query_prov  );	}
					stmt=connection.createStatement();
					rs=stmt.executeQuery(query );
					
					Map<Long,String> keyHM= new HashMap<Long,String>();
					Map<String,Long> codeHM= new HashMap<String,Long>();

					while(rs.next() ) {
						Long key = rs.getLong(query_keyColName.trim());  //VL99
						String code = rs.getString(query_codeColName.trim());  //VL99
						
						keyHM.put(key, code);
						codeHM.put(code, key);
						
						if (logger.isTraceEnabled())  {logger.trace("code: " + code ) ;}
					}
					stmt.close();
					stmt = null;
					if( rs != null)
					{
						rs.close();
						rs = null;
					}
					codeToKeyHashMap.put(query_tableName.toUpperCase(), codeHM);
		        	keyToCodeHashMap.put(query_tableName.toUpperCase(), keyHM);
		        	
	    		}
	    		//TE97_024 TableCache Introduced end
	    	}
	    }
    	catch(SQLException e)
    	{
    		throw e;
    	}
    	catch(IOException e )
    	{
    		throw e;
    	}
    	
    	finally
    	{
    		if( br != null )
    			br.close();
    		if( fis != null)
    			fis.close();
    		if( stmt != null )
    			stmt.close();
    		if( rs != null )
    			rs.close();
    	}
    }

    //0422
    private static void createCache(Connection connection, String CacheTbl, String sql) throws SQLException, IOException {
	       PreparedStatement ps = null;
	       ResultSet rs = null; 
	       DosageForm dsgfrm;
	       Manufacturer mnf;
	       
		   Map<Long, Object> ObjFromKey  = new HashMap<Long, Object>();  // <primaryKey, JAXB>
		   Map<String, Long> KeyFromCode = new HashMap<String, Long>();  // <BusinessID, primaryKey>
		   
		   try {
			   ps = connection.prepareStatement(sql);
			   rs = ps.executeQuery();
			   int i = 0;
			   while (rs.next()) {
				      switch (CacheTbl) {
				           case "DSGFRM" :
				        	     i++;
				        	     dsgfrm = new DosageForm();
	                             dsgfrm.setShortName(rs.getString("DSGFRMSHORTNM"));
	                             dsgfrm.setFullName(rs.getString("DSGFRMFULLNM"));
	                             dsgfrm.setFullNameFrench(rs.getString("DSGFRMFULLNMFR"));
	                             
	                             ObjFromKey.put(rs.getLong("DSGFRMKEY"), dsgfrm);
	                             KeyFromCode.put(dsgfrm.getShortName(),  rs.getLong("DSGFRMKEY")); 
	                             break;

				           case "MFCTR" :
				        	     i++;
				        	     mnf = new Manufacturer(); 
				           	     mnf.setManufacturerId(rs.getInt("MFCTRID"));
				           	     mnf.setVendorCode(rs.getString("VENDORCD"));
				           	     mnf.setManufacturerNameEnglish(rs.getString("MFCTRNMENG"));
				           	     mnf.setManufacturerNameFrench(rs.getString("MFCTRNMFR"));
				           	     
				           	     ObjFromKey.put(rs.getLong("MFCTRKEY"), mnf);
				           	     KeyFromCode.put(mnf.getManuId(),  rs.getLong("MFCTRKEY"));
				        	     break;
				      }
			   }
			   TblObjFromKey.put(CacheTbl, ObjFromKey); 
			   TblKeyFromCode.put(CacheTbl, KeyFromCode);
			   CommonUtil.closePreparedStatementResultSet(ps, rs);
		       if(logger.isDebugEnabled()) {logger.debug(i + " records have been inserted into " + CacheTbl + "cache");}
        } catch (Exception e) {
		            throw e;
	       } finally {
	    	        CommonUtil.closePreparedStatementResultSet(ps, rs);
	       }

 }

 
    public Long getKeyFromObject(Connection connection, String tblName, Object obj) throws SQLException {
	      ResultSet rs = null;
	      PreparedStatement ps = null;
	      boolean upsertCache = false;
	      Long primaryKey = null;
	      String msg = null;
	      String ObjectID = null;
	      String Code = null;

	      
	      
	      switch (tblName) {
	           case "DSGFRM" :
	     	         ObjectID = ((DosageForm)obj).toString();
	     	         if(logger.isDebugEnabled()) {logger.debug("getKeyFromObject: VLAD_DEBUG DSGFRM  ObjectID = " + ObjectID);}
	    	         Code = ((DosageForm)obj).getShortName();
	    	         break;
	    	         
	           case "MFCTR" :
	     	         ObjectID = ((Manufacturer)obj).toString();
	    	         Code = ((Manufacturer)obj).getManufacturerId().toString();
	        	     break;
	        	     
	           default:
	        	     if(logger.isErrorEnabled()) {logger.error("Unknown table name = " + tblName);}
	                 break;	        	     
	      }

	      
	      
	      try {
		      if (Code != null && Code.trim().length() > 1) {
		    	  primaryKey = TblKeyFromCode.get(tblName).get(Code);
		    	  if (primaryKey == null || primaryKey == 0L) {
		    		  upsertCache = true;
		    		  primaryKey = CommonUtil.generatePK(connection, tblName);
		    		  msg = ObjectID + "  inserted into " + tblName + " primaryKey = " + primaryKey;
		    	  } else {	 
		    		  if  (! ObjectID.equals(TblObjFromKey.get(tblName).get(primaryKey).toString() )  ) {
		    			  upsertCache = true;
		    			  msg = ObjectID + "  updated in " + tblName + " primaryKey = " + primaryKey;
		    		  }
		    	  }
		      }
		      
		      
		      if (upsertCache == true) {
		    	  TblObjFromKey.get(tblName).put(primaryKey, obj);   // tbl, <primaryKey, JAXB>
		    	  TblKeyFromCode.get(tblName).put(Code, primaryKey); // tbl, <Code, primaryKey>
			      switch (tblName) {
		               case "DSGFRM" :
		            	     ps = connection.prepareStatement(DSGFRM_UPSERT);
		   		    	     CommonUtil.setPsLongParam(ps, 1, primaryKey);
		   		    	     CommonUtil.setPsStringParam(ps, 2, Code);
		   		    	     CommonUtil.setPsStringParam(ps, 3, ((DosageForm)obj).getFullName());
		   		    	     CommonUtil.setPsStringParam(ps, 4, ((DosageForm)obj).getFullNameFrench());
		    	             break;
		    	         
		               case "MFCTR" :
		            	     ps = connection.prepareStatement(MFCTR_UPSERT);
		   		    	     CommonUtil.setPsLongParam(ps, 1, primaryKey);
		   		    	     CommonUtil.setPsIntParam(ps, 2, ((Manufacturer)obj).getManufacturerId());
		   		    	     CommonUtil.setPsStringParam(ps, 3, ((Manufacturer)obj).getVendorCode());
		   		    	     CommonUtil.setPsStringParam(ps, 4, ((Manufacturer)obj).getManufacturerNameEnglish());
		   		    	     CommonUtil.setPsStringParam(ps, 5, ((Manufacturer)obj).getManufacturerNameFrench());
		        	         break;
		        	     
		          }
	    		  int res = ps.executeUpdate();
	    		  CommonUtil.closePreparedStatementResultSet(ps, rs);
	    		  if(logger.isDebugEnabled()) {logger.debug("getKeyFromObject: upsertCache is true : " + msg);}
            } else {
      	      if(logger.isDebugEnabled()) {logger.debug("getKeyFromObject: upsertCache is false: " + msg);}
	          }
		      
		  } catch (SQLException e) {
	            e.printStackTrace();
   } finally {
	        CommonUtil.closePreparedStatementResultSet(ps, rs);
   }	      
	      return primaryKey;
}
    /*
    private static void createCache(Connection connection, String CacheTbl, String sql) throws SQLException, IOException {
	       PreparedStatement ps = null;
	       ResultSet rs = null; 
	       DosageForm dsgfrm;
	       Manufacturer mnf;
	       
		   Map<Long, Object> ObjFromKey  = new HashMap<Long, Object>();  // <primaryKey, JAXB>
		   Map<String, Long> KeyFromCode = new HashMap<String, Long>();  // <BusinessID, primaryKey>
		   
		   try {
			   ps = connection.prepareStatement(sql);
			   rs = ps.executeQuery();
			   int i = 0;
			   while (rs.next()) {
				      switch (CacheTbl) {
				           case "DSGFRM" :
				        	     i++;
				        	     dsgfrm = new DosageForm();
	                             dsgfrm.setShortName(rs.getString("DSGFRMSHORTNM"));
	                             dsgfrm.setFullName(rs.getString("DSGFRMFULLNM"));
	                             dsgfrm.setFullNameFrench(rs.getString("DSGFRMFULLNMFR"));
	                             
	                             ObjFromKey.put(rs.getLong("DSGFRMKEY"), dsgfrm);
	                             KeyFromCode.put(dsgfrm.getShortName(),  rs.getLong("DSGFRMKEY")); 
	                             break;

				           case "MFCTR" :
				        	     i++;
				        	     mnf = new Manufacturer(); 
				           	     mnf.setManufacturerId(rs.getInt("MFCTRID"));
				           	     mnf.setVendorCode(rs.getString("VENDORCD"));
				           	     mnf.setManufacturerNameEnglish(rs.getString("MFCTRNMENG"));
				           	     mnf.setManufacturerNameFrench(rs.getString("MFCTRNMFR"));
				           	     
				           	     ObjFromKey.put(rs.getLong("MFCTRKEY"), mnf);
				           	     KeyFromCode.put(mnf.getManuId(),  rs.getLong("MFCTRKEY"));
				        	     break;
				      }
			   }
			   TblObjFromKey.put(CacheTbl, ObjFromKey); 
			   TblKeyFromCode.put(CacheTbl, KeyFromCode);
		       logger.debug(i + " records have been inserted into " + CacheTbl + "cache");
           } catch (Exception e) {
		            throw e;
	       } finally {
	    	        CommonUtil.closePreparedStatementResultSet(ps, rs);
	       }

    }

    
    public Long getKeyFromObject(Connection connection, String tblName, Object obj) throws SQLException {
	      ResultSet rs = null;
	      PreparedStatement ps = null;
	      boolean upsertCache = false;
	      Long primaryKey = null;
	      String msg = null;
	      String ObjectID = null;
	      String Code = null;

	      
	      
	      switch (tblName) {
	           case "DSGFRM" :
	     	         ObjectID = ((DosageForm)obj).toString();
	    	         Code = ((DosageForm)obj).getShortName();
	    	         break;
	    	         
	           case "MFCTR" :
	     	         ObjectID = ((Manufacturer)obj).toString();
	    	      //   Code = ((Manufacturer)obj).getManuId();
	    	         Code = ((Manufacturer)obj).getManufacturerId().toString();
	        	     break;
	        	     
	           default:
	        	     if(logger.isErrorEnabled()) {logger.error("Unknown table name = " + tblName);}
	                 break;	        	     
	      }

	      
	      
	      try {
		      if (Code != null && Code.trim().length() > 1) {
		    	  primaryKey = TblKeyFromCode.get(tblName).get(Code);
		    	  if (primaryKey == null || primaryKey == 0L) {
		    		  upsertCache = true;
		    		  primaryKey = CommonUtil.generatePK(connection, tblName);
		    		  msg = ObjectID + "  record has been inserted into " + tblName + " cache";
		    	  } else {	 
		    		  if  (! ObjectID.equals(TblObjFromKey.get(tblName).get(primaryKey).toString() )  ) {
		    			  upsertCache = true;
		    			  msg = ObjectID + "  record has been updated in " + tblName + " cache";
		    		  }
		    	  }
		      }
		      
		      
		      
		      
		      if (upsertCache == true) {
		    	  TblObjFromKey.get(tblName).put(primaryKey, obj);   // tbl, <primaryKey, JAXB>
		    	  TblKeyFromCode.get(tblName).put(Code, primaryKey); // tbl, <Code, primaryKey>
			      switch (tblName) {
		               case "DSGFRM" :
		            	     ps = connection.prepareStatement(DSGFRM_UPSERT);
		   		    	     CommonUtil.setPsLongParam(ps, 1, primaryKey);
		   		    	     CommonUtil.setPsStringParam(ps, 2, Code);
		   		    	     CommonUtil.setPsStringParam(ps, 3, ((DosageForm)obj).getFullName());
		   		    	     CommonUtil.setPsStringParam(ps, 4, ((DosageForm)obj).getFullNameFrench());
		    	             break;
		    	         
		               case "MFCTR" :
		            	     ps = connection.prepareStatement(MFCTR_UPSERT);
		   		    	     CommonUtil.setPsLongParam(ps, 1, primaryKey);
		   		    	     CommonUtil.setPsIntParam(ps, 2, ((Manufacturer)obj).getManufacturerId());
		   		    	     CommonUtil.setPsStringParam(ps, 3, ((Manufacturer)obj).getVendorCode());
		   		    	     CommonUtil.setPsStringParam(ps, 4, ((Manufacturer)obj).getManufacturerNameEnglish());
		   		    	     CommonUtil.setPsStringParam(ps, 5, ((Manufacturer)obj).getManufacturerNameFrench());
		        	         break;
		        	     
		          }
	    		  int res = ps.executeUpdate();
	    		 //	    	      if(logger.isErrorEnabled()) {logger.error(msg);}
		      }
		      
		  } catch (SQLException e) {
	            e.printStackTrace();
        } finally {
    	        CommonUtil.closePreparedStatementResultSet(ps, rs);
        }	      
	      return primaryKey;
    }
    */
 //0422
    public Object getObjectFromKey(Connection conn, Long key, String tblName) throws SQLException, IOException {
	      Object obj = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      
	      DosageForm dsgfrm;
	      Manufacturer mnf;
	       
		  Map<Long, Object> ObjFromKey  = new HashMap<Long, Object>();  // <primaryKey, JAXB>
		  Map<String, Long> KeyFromCode = new HashMap<String, Long>();  // <BusinessID, primaryKey>
		   
	  	  String DSGFRM_SQL =
			    "select DSGFRMKEY,     "
	          + "       DSGFRMSHORTNM, "
	          + "       DSGFRMFULLNM,  "
	          + "       DSGFRMFULLNMFR "
	          + "  from dsgfrm         "
	  	      + " where DSGFRMKEY = ?  ";
	   
		  String MFCTR_SQL = 
				"select MFCTRKEY,   "
	          + "       MFCTRID,    "
	          + "       VENDORCD,   "
	          + "       MFCTRNMENG, "
	          + "       MFCTRNMFR   "
	          + "  from MFCTR       "
		      + " where MFCTRKEY = ? ";
		  int i = 0;
		  
	      if (key == null || key.equals(0L)) {
	    	  throw new IOException("key is empty = " + key + " tblName = " + tblName);
//	    	  throw new CodeNotFoundFromTableCacheException(tblName, key);
	      } else {
	    	  obj = TblObjFromKey.get(tblName).get(key); 
	    	  if ( obj == null) {

	    	      switch (tblName) {
		               case "DSGFRM" :
			    		    ps = conn.prepareStatement(DSGFRM_SQL);
			    		    ps.setLong(1, key);
						    rs = ps.executeQuery();
						    
						    if (rs.next()) {
						    	i++;
							    dsgfrm = new DosageForm();
	                            dsgfrm.setShortName(rs.getString("DSGFRMSHORTNM"));
	                            dsgfrm.setFullName(rs.getString("DSGFRMFULLNM"));
	                            dsgfrm.setFullNameFrench(rs.getString("DSGFRMFULLNMFR"));
	                            
	                            ObjFromKey.put(rs.getLong("DSGFRMKEY"), dsgfrm);
	                            KeyFromCode.put(dsgfrm.getShortName(),  rs.getLong("DSGFRMKEY"));
						    }
                          break;

		               case "MFCTR" :
			    		    ps = conn.prepareStatement(MFCTR_SQL);
			    		    ps.setLong(1, key);
						    rs = ps.executeQuery();
						    
						    if (rs.next()) {
						    	i++;
					   	        mnf = new Manufacturer(); 
				           	    mnf.setManufacturerId(rs.getInt("MFCTRID"));
				           	    mnf.setVendorCode(rs.getString("VENDORCD"));
				           	    mnf.setManufacturerNameEnglish(rs.getString("MFCTRNMENG"));
				           	    mnf.setManufacturerNameFrench(rs.getString("MFCTRNMFR"));
				           	     
				           	    ObjFromKey.put(rs.getLong("MFCTRKEY"), mnf);
				           	    KeyFromCode.put(mnf.getManuId(),  rs.getLong("MFCTRKEY"));
						    }
		        	        break;
		          }
	    		  
				  if (i > 0) {
					  TblObjFromKey.put(tblName, ObjFromKey); 
					  TblKeyFromCode.put(tblName, KeyFromCode);
					  obj = TblObjFromKey.get(tblName).get(key); 
				  } else {	  
		    	       throw new IOException("Object not found while searching key = " + key + " and tblName = " + tblName);
//		    	       throw new CodeNotFoundFromTableCacheException(tblName, key);
				  }
	    	  }	  
	      }
	      CommonUtil.closePreparedStatementResultSet(ps, rs);
  	  return obj;
 }

//  VL46 Started:
    private static void readResource() throws IOException {
    	    InputStream is = null;
    	    try {
    	    	
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PatientByQueryCriteria.sql");
    	        PatientByQueryCriteria = InputStream2String(is);

    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("NotificationInsert.sql");
    	        NotificationInsert = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PersonQueryRole.sql");
    	        PersonQueryRole  = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PersonQueryRoleODM.sql");
    	        PersonQueryRoleODM  = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PatPersInfo.sql");
    	        PatPersInfo = InputStream2String(is); 
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PatPersInfo_MedProfile.sql");
    	        PatPersInfo_MedProfile = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetCustomerQueryByPtntId.sql");
    	        GetCustomerQueryByPtntId = InputStream2String(is);
    	        
    	       
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetCustomerQueryByCustKeyCaregiver.sql");
    	        GetCustomerQueryByCustKeyCaregiver = InputStream2String(is);
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetCustomerQueryByCustKeyCustomer_PreRegCustomer.sql");
    	        GetCustomerQueryByCustKeyCustomer_PreRegCustomer = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetCustKeyAndPrsnKeyQuery.sql");
    	        GetCustKeyAndPrsnKeyQuery = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetCustomerQueryByWithOutPatient.sql");
    	        GetCustomerQueryByWithOutPatient = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("PrescriptionGet1.sql");
    	        PrescriptionGet1 = InputStream2String(is);
    	        
    	    	is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("GetPrescriptions1.sql");
    	        GetPrescriptions1 = InputStream2String(is);
    	        
    	        is = TableCacheSingleton.class.getClassLoader().getResourceAsStream("inpPayload.xml");
    	        inpPayload = is;
    	    } catch(IOException e )  {
    	    	throw e;
    	   	}
    	    
        	finally {
        		if (is != null) is.close();
      	    }
    }
    
    private static String InputStream2String(InputStream is) throws IOException {
	        int ch;
	        StringBuilder sb = new StringBuilder();
	        while((ch = is.read()) != -1)
	               sb.append((char)ch);
//	        reset();
	        return sb.toString();
    }
    
    public static String getResource(String fileName) throws IOException {
           String returnValue = null;
           
		   switch (fileName) {
			    case "PatientByQueryCriteria.sql" : returnValue = PatientByQueryCriteria; break;
			    case "NotificationInsert.sql" : returnValue = NotificationInsert; break;
			    case "PersonQueryRole.sql" : returnValue = PersonQueryRole; break;
			    case "PersonQueryRoleODM.sql" : returnValue = PersonQueryRoleODM; break;
			    case "PatPersInfo.sql" : returnValue = PatPersInfo; break;
			    case "PatPersInfo_MedProfile.sql" : returnValue=PatPersInfo_MedProfile;break;
			    case "GetCustomerQueryByPtntId.sql" :returnValue = GetCustomerQueryByPtntId;break;
			    case "GetCustomerQueryByCustKeyCustomer_PreRegCustomer.sql" :returnValue = GetCustomerQueryByCustKeyCustomer_PreRegCustomer;break;
			    case "GetCustomerQueryByCustKeyCaregiver.sql" :returnValue = GetCustomerQueryByCustKeyCaregiver;break;
			    case "PrescriptionGet1.sql" : returnValue = PrescriptionGet1;break;
			    case "GetPrescriptions1.sql" : returnValue = GetPrescriptions1;break;
			    case"GetCustKeyAndPrsnKeyQuery.sql" :returnValue = GetCustKeyAndPrsnKeyQuery;break;
			    case"GetCustomerQueryByWithOutPatient.sql" :returnValue = GetCustomerQueryByWithOutPatient;break;
			    default: returnValue = "Unknown Resource"; break;
		   }
           return returnValue;
    }
//  VL46 Ended
    
    
    
    private static void updateCacheByTableName(Connection connection , String tableName, ReferenceTableBean refRableBean) throws SQLException
    {
    	ResultSet rs= null;
    	Statement stmt = null;
		
    	String keyColName = refRableBean.getKeyColumnName();
    	String codeColName = refRableBean.getCodeColumnName() ;
    	
		String query = "SELECT " +  keyColName+ " , " + codeColName + " FROM " + tableName ;
		//	String query_2 = "SELECT SUA.* FROM Database.ERETDBT.sdm_user_address AS SUA WHERE SUA.id = ?";
		if(logger.isDebugEnabled()) {logger.debug("query  : " + query  );}	
		try
		{
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query );
			
			Map<Long,String> keyHM= keyToCodeHashMap.get(tableName.toUpperCase());
			Map<String,Long> codeHM= codeToKeyHashMap.get(tableName.toUpperCase());
	
			while(rs.next() )
			{
				Long key = (Long)rs.getLong(keyColName);
				String code = rs.getString(codeColName);
				if(  keyHM.get(key) == null)
				{
					keyHM.put(key, code);
					codeHM.put(code, key);
				}				
				if (logger.isTraceEnabled())  {logger.trace("code: " + code ) ;}
			}
//			codeToKeyHashMap.put(tableName.toUpperCase(), codeHM);
//	    	keyToCodeHashMap.put(tableName.toUpperCase(), keyHM);
	    }
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			if( stmt != null )
				stmt.close();
			if( rs != null )
				rs.close();
		}
    	
    }
    
    private static void updateCacheByTableByKey(String tableName, ReferenceTableBean refRableBean , Long key) throws SQLException, NamingException, IOException
    {
    	ResultSet rs= null;
    	PreparedStatement ps = null;
		Connection connection = null;
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: updateCacheByTableByKey. Table : " + tableName + " , key : " + key);}

    	String keyColName = refRableBean.getKeyColumnName();
    	String codeColName = refRableBean.getCodeColumnName() ;
    	
		Map<Long,String> keyHM= keyToCodeHashMap.get(tableName.toUpperCase());
		Map<String,Long> codeHM= codeToKeyHashMap.get(tableName.toUpperCase());

		String query = "SELECT " +  keyColName+ " , " + codeColName + " FROM " + tableName + " WHERE " + keyColName + " = ?";
		//	String query_2 = "SELECT SUA.* FROM Database.ERETDBT.sdm_user_address AS SUA WHERE SUA.id = ?";
		if(logger.isDebugEnabled()) {logger.debug("query  : " + query  );}	
		try
		{
			synchronized(keyHM)
			{
				connection = CommonUtil.getConnection();
				ps=connection.prepareStatement(query);
				CommonUtil.setPsLongParam(ps, 1, key);
				rs=ps.executeQuery();
		
				while(rs.next() )
				{
					String code = rs.getString(codeColName);
					if(  keyHM.get(key) == null)
					{
						keyHM.put(key, code);
						codeHM.put(code, key);
					}				
					if (logger.isDebugEnabled() ) { logger.debug("updateCacheByTableByKey code: " + code); }
				}
			}

		} catch (SQLException e) {
 			throw e;
 		} 
 		catch (NamingException e) {
 			throw e;
 		} 
 		finally {
 			CommonUtil.closePreparedStatementResultSet(ps, rs);
 			CommonUtil.releaseConnection(connection);
 			if (logger.isInfoEnabled())  {logger.info("EndApiCall: updateCacheByTableByKey. Table : " + tableName + " , key : " + key);}
 		}
    }
    
    public void refreshTable(String tableName, Long tableKey , String tableValue)
    {
        synchronized (TableCacheSingleton.class) 
        {
        	Map<Long,String> mapToCode = keyToCodeHashMap.get(tableName.toUpperCase()) ;
        	
        	if( mapToCode != null && mapToCode.get(tableKey) == null )
        		mapToCode.put(tableKey, tableValue);
        	
        	Map<String,Long> mapToKey = codeToKeyHashMap.get(tableName.toUpperCase());
        	if( mapToKey != null && mapToKey.get(tableValue) == null )
        		mapToKey.put(tableValue, tableKey);
        	if (logger.isDebugEnabled() ) {logger.debug(tableName + " single cache element has been refreshed by key = " + tableKey + " and value = " + tableValue);}
        }
    }
    
    private void refreshTableByKey(String tableName, Long key) throws IOException, SQLException, NamingException
    {
		Connection connection = null ;
		ReferenceTableBean referenceTableBean = referenceTableMap.get(tableName.toUpperCase());
		updateCacheByTableByKey(tableName, referenceTableBean,key);
    
    }

    public String getCodeFromKey(String tableName,Long key, boolean refresh) throws CodeNotFoundFromTableCacheException, NamingException, SQLException, IOException 
    {
    	if (key==null || key.equals(0L)) {
    		return null;
    	}

    	refresh();
    	
    	Map<Long,String> map = keyToCodeHashMap.get(tableName.toUpperCase()) ;
    	if( map != null)
    	{
    		String value = (String) map.get(key);
    		if( value == null && refresh == true )
    		{
				TableCacheSingleton.getInstance(JNDI_VERSION).refreshTableByKey(tableName, key);
				value = getCodeFromKey(tableName, key, false);
    		}
    		else
    		if( value == null)
    			throw new CodeNotFoundFromTableCacheException(tableName, key );
  
    		
    		
    		return value;
    	}
    	throw new CodeNotFoundFromTableCacheException(tableName, key );
    	
    }
    
    public String getCodeFromKey(String tableName,Long key) throws CodeNotFoundFromTableCacheException, NamingException, SQLException, IOException 
    {
    	return getCodeFromKey(tableName, key , true);
    }
    //LTPHCP-11 TableCacheSingleton data cache optimization 
    public Long getKeyFromCode(String tableName,String code) throws KeyNotFoundFromTableCacheException, NamingException, SQLException, IOException 
    {
    	if (code==null || code.trim().length() < 1) {
    		return null;
    	}

    	refresh();

    	Map<String,Long> map = codeToKeyHashMap.get(tableName.toUpperCase());
    	if( map != null )
    	{
    		Long value = (Long) map.get(code);
    		if( value == null)
    		{
    			if( referenceTableMap.get(tableName.toUpperCase()).isUpdatable() == false )
    				throw new KeyNotFoundFromTableCacheException(tableName, code);
    			
    			value = updateReferenceTableByDescription(tableName, code);
    			
    		}else {
    			if (logger.isDebugEnabled() ) {logger.debug("getKeyFromCode returns key = " + value + " from " + tableName + " cache. No DB or Cache update" );}
    		}
    		return  value;
    	}
    	
    	throw new KeyNotFoundFromTableCacheException(tableName,code);
    }
    

	private Long updateReferenceTableByDescription( String tableName, String newValue) throws SQLException, IOException, NamingException  {

		Long key = null;
		Connection connection = null;
		tableName = tableName.toUpperCase();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: insertNewValue. Table : " + tableName + " , new incoming value : " + newValue);}
		
		Map<Long,String> mapToCode = keyToCodeHashMap.get(tableName);
		try
		{
			synchronized (mapToCode)
			{
				ReferenceTableBean referenceTableBean = referenceTableMap.get(tableName);
			
				// if the reference table is not eligible for being updated. for instance STORE is not eligible.
				if( referenceTableBean.isUpdatable() == false )
					return null;
				
				connection = CommonUtil.getConnection();
				key = FindUtil.findValue(connection, referenceTableBean, newValue);
				if (key == null) 
				{
					key = insertNewValue(connection, referenceTableBean, newValue);
					TableCacheSingleton.getInstance(JNDI_VERSION).refreshTable(tableName, key, newValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		} 
		catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} 
		 
		finally {
			CommonUtil.releaseConnection(connection);
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: insertNewValue. Table : " + tableName + " , new incoming value : " + newValue);}
		}
		return key;
	}    
	
	private Long insertNewValue(Connection connection, ReferenceTableBean referenceTableBean, String newValue) throws SQLException
	{
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long key = null; 
			String tableSequence = referenceTableBean.getTableName() +"_SEQ.nextval";
			String insertStatement = " INSERT INTO " + referenceTableBean.getTableName() + " (" + referenceTableBean.getKeyColumnName() + " , " + referenceTableBean.getCodeColumnName()+ " ) "
					+ "VALUES ( " + tableSequence  + " , ? )";
			
			if(logger.isDebugEnabled()) {logger.debug("insertStatement : " + insertStatement);}
			
			ps = connection.prepareStatement(insertStatement, new String[] {referenceTableBean.getKeyColumnName()});
			CommonUtil.setPsStringParam(ps, 1, newValue);
			
			int res = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				key = rs.getLong(1);
			}
			return key;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
			CommonUtil.releaseConnection(connection);
		}			
		
		
	}
}
