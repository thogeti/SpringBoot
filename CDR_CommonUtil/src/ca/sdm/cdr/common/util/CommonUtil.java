package ca.sdm.cdr.common.util;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNTCTMTHDTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTCTGRYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PACKSZUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.constant.Constants;
import ca.sdm.cdr.common.singleton.CDRConfigFileReader;
import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.CDRPersistResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PatientUpsert;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PatientUpsertResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PrescriberUpsertResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PrescriptionUpsertResponse;
import ca.shoppersdrugmart.cdr.notificationService.NotificationRequest;
import ca.shoppersdrugmart.cdr.notificationService.NotificationResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.AssociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.AssociateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.DissociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.DissociateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.dispensing.Refill;
import ca.shoppersdrugmart.rxhb.drx.dispensing.RefillResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatient;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByPatientId;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByPatientIdResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByQueryCriteria;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByQueryCriteriaResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescription;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationreview.GetMedicationReviewRequest;
import ca.shoppersdrugmart.rxhb.drx.medicationreview.GetMedicationReviewResponse;
import ca.shoppersdrugmart.rxhb.drx.preference.EntityPreference;
import ca.shoppersdrugmart.rxhb.drx.preference.EntityPreferenceResponse;
import ca.shoppersdrugmart.rxhb.drx.rxtransfer.RxTransfer;
import ca.shoppersdrugmart.rxhb.drx.rxtransfer.RxTransferResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPatientAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPatientAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetDispenseAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetDispenseAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionResponse;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.ContactPurposeType;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.NoteCategory;
import ca.shoppersdrugmart.rxhb.ehealth.NoteType;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.PackSizeUoMTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.Province;
import ca.shoppersdrugmart.rxhb.ehealth.ReauthFaxFlag;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.businessevent.BusinessEventPayload;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyCentralBusinessEvent;

//import oracle.sql.TIMESTAMP;

//HW4BService #HW4B changes Praveen T
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.HW4BEventUpsert;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.HW4BEventUpsertResponse;;

/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL34 2017-12-21   NTT Data     Vlad Eidinov  See Launcher class for CDR StandAlone project
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project

*/



public class CommonUtil {
	final static Logger logger = LogManager.getLogger(CommonUtil.class);
    final static String FS = System.getProperty("file.separator");
	
	public final static String errNameSpace = "http://www.shoppersdrugmart.ca/message/ErrorXML";
	public final static String errPrefix = "err";
	public final static String messagetype_system_exception = "system exception";
	public final static String errorcategory_weblogic = "Weblogic";
	public static String ORACLE_TS_FMT = "yyyy-MM-dd HH:mm:ss.SSSXXX";  
	public static String ORACLE_DATE_FMT = "yyyy-MM-dd";
	public static String TO_TIMESTAMP_TZ = "TO_TIMESTAMP_TZ(?, 'YYYY-mm-DD HH24:MI:SS.FF TZH:TZM')";
	private static long lastLogTime = System.currentTimeMillis();
	private static String CDR_VERSION ="WebLogic_Drop65-2020-12-11_02-00";
	private static Properties CDRConfigProperties;
	public static String createStoreLeadingZeros(String storeNumber) {
		return String.format("%04d", new Integer(storeNumber));
	}

	public static String createStoreLeadingZeros(int storeNumber) {
		return String.format("%04d", storeNumber);

	}
	  private static DatatypeFactory datatypeFactory;
	  static {
	       try {
	           datatypeFactory = DatatypeFactory.newInstance();
	       } catch (DatatypeConfigurationException e) {
	                throw new RuntimeException("Init Error!", e);
	       }
	  }

	
	/*public static Connection getConnection1() throws NamingException, SQLException, IOException {
		Context context = null;
		String dataSourceJndi = null;
          
		try {
			Connection connection = null;
			context = new InitialContext();
			String jndiVersion = TableCacheSingleton.JNDI_VERSION;
	//		System.out.println("jndiVersion = "+ jndiVersion);
			
            if (jndiVersion == null) {
            	if (logger.isInfoEnabled())  {logger.info("JNDI Version has been hard coded to new");}
            }
			else if (jndiVersion.equals("new")) {
				dataSourceJndi = System.getProperty("CDR_MANAGED_SERVER");
				if (dataSourceJndi == null) {
					throw new IOException("CDR_MANAGED_SERVER variable has not been defined in Weblogic console");
				}
			} else if (jndiVersion.equals("old")) {
				dataSourceJndi = CDRConfigFileReader.getInstance().getProperty(Constants.DATA_SOURCE_JNDI);
				if (dataSourceJndi == null) {
					throw new IOException("Error while reading old jndi = " + dataSourceJndi + "   from CDR.config ");
				}
			} 

			if (logger.isInfoEnabled())  {logger.info("Enable  jndiVersion for    " + dataSourceJndi);}
	//		System.out.println("Enable  jndiVersion for    " + dataSourceJndi);
			DataSource dataSource = (javax.sql.DataSource) context.lookup(dataSourceJndi);
			connection = dataSource.getConnection();
			return connection;
		} catch (NamingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (context != null)
				context.close();
		}
	}
	*/
//  2020-10-06 Started:
   /**
	* Below approach demonstrates "best practice" while retrieving connections from Weblogic DataSource:
	* To use below "best practice" approach you have to define in Weblogic Admin Console: 
	* 
	* Services/Data Sources/Connection Pool/ "Advanced"
    * Initial Capacity: 20  Maximum Capacity :100
    *
    * Initial Capacity is the number of physical connections to create when creating 
    * the connection pool in the data source.
    *
    * */
	private static DataSource dataSource;
	public static Connection getConnection() throws NamingException, SQLException {
		   if(dataSource == null){
			  dataSource = getDataSource();
		   }
		   return dataSource.getConnection();
	}	
		
	private static DataSource getDataSource() throws NamingException {		
		Context context = null;
		String dataSourceJndi = null;
		try {
			context = new InitialContext();
			dataSourceJndi = System.getProperty("CDR_MANAGED_SERVER");
			if (logger.isInfoEnabled()) {logger.info(String.format("DataSource has been enabled for %s", dataSourceJndi));}
			return (javax.sql.DataSource) context.lookup(dataSourceJndi);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (context != null) context.close();
		}
	}
//  2020-10-06 Ended:	
	
	public static String readConfigFile(String key) throws IOException { 
	    String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
	    String configFilePath = ConfigDirectory.trim() + "/CDR.config";
	    
	    CDRConfigProperties = new Properties();
	    InputStream inputStream = null;
	    if (logger.isInfoEnabled())  {logger.info("readConfigFile got input parameter as  " + key + " from " + configFilePath);}
//	    System.out.println("readConfigFile got input parameter as  " + key + " from " + configFilePath);
		try { 
			inputStream = new FileInputStream(configFilePath);
			CDRConfigProperties.load(inputStream);
	    	String val = (key != null && CDRConfigProperties.getProperty(key) != null) ? CDRConfigProperties.getProperty(key).trim() : null;
	    	if ( val == null || val.trim().length() < 1) {
	    		if(logger.isErrorEnabled()) {logger.error(key + " parameter not found in CDR.config");}
				throw new IOException();
	    	}
	    	return val;
	    	
		} catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
		finally {
			if ( inputStream != null )
				 inputStream.close();
		}
}
	
	// VL34 Started: to use by CDR J2SE StandAlone version. No Weblogic is needed. 
		public static Connection getConnectionTo(String env) throws SQLException {
			   Connection conn = null;
			   java.sql.Statement stmt;
			   String cdrDBSchema = null;
			   String jdbcUrl = null;
			   String passw = null;
			   String login = null;
			   
			   try {
				    cdrDBSchema = CDRConfigFileReader.getInstance().getProperty(Constants.CDR_PROPERTIES_DB_SCHEMA);
					switch (env) {
					  case "local" :
						   jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
						   login = "SYSTEM"; passw = "Vlad_012"; break;
						   
					  case "dit" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.108.108.47:1527/DV815";
						   login = "cdradmin"; passw = "N1ceday!"; break;

					  case "sit" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.108.108.53:1527/ST815";
						   login = "cdradmin"; passw = "N1ceday!"; break;

						   
					  case "qhr_dit" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.108.108.47:1527/DV815";
						   login = "cdradmin2"; passw = "Summ3R$2017"; break;
						
					  case "pprod" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.107.104.21:1527/OCDRSVC";
						   login = "wlogicusr"; passw = "wlogicusr"; break;
						   
					  case "uat" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.108.108.55:1527/UT815";
						   login = "wlogicusr"; passw = "wlogicusr"; break;
						   
					  case "prod" :
						   jdbcUrl = "jdbc:oracle:thin:@//10.107.104.21:1527/CDRSVC";
						   login = "wlogicusr"; passw = "wlogicusr"; break;
				 	   	   
					  default: System.out.println("Unknown Environment = " + env); break;
					}

					Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 	   	    conn= java.sql.DriverManager.getConnection(jdbcUrl, login, passw);
		  	  		if (conn != null) {
		  	  			stmt = conn.createStatement();	
		     	  		stmt.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA = " + cdrDBSchema);
		  	  			stmt.close();
		  	  			conn.setAutoCommit(false);
		  	  			
		  	  			System.out.println("=======================================");
		  	  		    System.out.println(jdbcUrl + "   DBSchema: " + cdrDBSchema);
		  	  			System.out.println("JDBCConnection: Connected successfully ");
		  	  			System.out.println("=======================================");
		  	  			return conn;
		  	  		} else {
		  	  			System.out.println("Failed to make JDBCConnection");
		  	  			System.exit(-3);
		  	  		}
			    

		       } catch (IOException e) {
		    		System.err.println("Failed while reading DBSchema name from config file");
		    		e.printStackTrace();
		    		System.exit(-3);
		  	  		
		       } catch (ClassNotFoundException e) {
		    		System.err.println("JDBC Driver not found in ojdbc7.jar");
		    		e.printStackTrace();
		    		System.exit(-3);

	    	   } catch (SQLException e) {
		    		System.err.println("Failed to SetUp jdbc Connection for env = " + env);
		    		System.err.println("------------------------------------------------------");
		    		System.err.println("jdbcUrl = " + jdbcUrl);
		    		System.err.println("login = " + login);
		    		System.err.println("passw = " + passw);
		    		System.err.println("cdrDBSchema = " + cdrDBSchema);
		    		System.err.println("  ");
		    		e.printStackTrace();
		    		System.exit(-3);
	    	   }
			   return conn;
		}

	public static List< Map<String, Object> > resultSetToList(ResultSet rs2) throws SQLException {
		   ResultSetMetaData md = rs2.getMetaData();
		   int colCount = md.getColumnCount();
		   List< Map<String, Object> > rows = new ArrayList< Map<String, Object> >(127);
		   
		   long rowCount = 0;
		   while(rs2.next() && rowCount < 127 ) {
			     Map<String, Object> row = new HashMap<String, Object>(colCount);
//			     System.out.println("Row Number = " + rowCount);
				 for (int i = 1; i <= colCount; ++i) {
//					  System.out.println(md.getTableName(i) + "  ColumnName = " + md.getColumnName(i) + "  " + md.getColumnType(i) + "  " + md.getColumnTypeName(i) );
				  	  row.put(md.getColumnName(i), rs2.getObject(i));
				 }
				 rows.add(row);  row = null;
			     rowCount++;   
		   }
		   
		   if (rowCount > 0) {return rows;}
		   else {return null;}
	}
	
/*	
 if ( md.getColumnName(i).equals("PTNT_PTNTKEY") && rs2.getLong("PTNT_PTNTKEY") == 15976627 ) {
	     System.out.println("THIS PTNT_PTNTKEY = " + rs2.getLong("PTNT_PTNTKEY"));
		 row.put("VladFlag", "Y");
	}
*/

	
//	This method converts oracle.sql.TIMESTAMP to Date	
    /*public static Date TIMESTAMP2Date(Object val) throws Exception {
        if ( val instanceof TIMESTAMP ) {
            try {
                TIMESTAMP ts = (TIMESTAMP)val;
                return ts.dateValue();
            } catch (SQLException e) {
                throw e;
            }
        } else {
            return (Date)val;   // fallback to default impl
        }
    }	*/
//  ---------------- VL34 Ended  -------------------------------------
	
	
	
	
	
	
	
	public static void closePreparedStatementResultSet(PreparedStatement ps , ResultSet rs) 
	{
		
		try {
			if ( rs != null )
			{
					rs.close();
			}
			if ( ps != null )
			{
					ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void releaseConnection(Connection connection) {
		if (connection != null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// This nw connection method is for closing the connection
/*	public static void releaseNewDBConnection(Connection connection) {
		if (connection != null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public static void rollback(Connection connection) {
		try {
			if (connection != null)
				connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized long getLastLogTime() {
		return lastLogTime;
	}
	
	public static synchronized void setLastLogTime(long logTime) {
		lastLogTime = logTime;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(ORACLE_TS_FMT);
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
	public static String getEndTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(ORACLE_TS_FMT);
		String endDate = "9999-12-31 23:59:59";
		return endDate;
	}
	
	public static String convertBooleanToYesNoFlag(boolean status)
	{
		return (status == true ) ? "Y" : "N";
		
	}
	
	public static String convertBooleanToYesNoFlag(Boolean booleanObj) {
		return (booleanObj!=null) ? convertBooleanToYesNoFlag(booleanObj.booleanValue()) : null;
	}
	
	
	public static boolean convertYesNoFlagToBoolean(String flag) throws CDRInternalException
	{
		if( flag == null )
			return false;
		if( flag.equalsIgnoreCase("Y") )
			return true;
		if( flag.equalsIgnoreCase("N") )
			return false;

		throw new CDRInternalException("DAT2500" , "Flag is not 'Y'/'N'");
		
	}

	
	//TE97_024
	/*public static String convertYesNoFlagBooleanString(String flag) 
	{
		if( flag == null )
			return "N";
		if( flag.equalsIgnoreCase("true") )
			return "Y";
		if( flag.equalsIgnoreCase("false") )
			return "N";
		if( flag.equalsIgnoreCase("Not Specified") )
			return "U";
		return "N";
	
		
	}*/
	
	public static String convertYesNoFlagBooleanString(Boolean flag) 
	{
		if( flag == null )
			return "N";
		if( flag==true)
			return "Y";
		if( flag==false )
			return "N";
		/*if( flag.equalsIgnoreCase("Not Specified") )
			return "U";*/
		return "N";
	
		
	}
	
	public static String convertYesNoReauthString(String flag) 
	{
		if( flag == null )
			return "N";
		if( flag.equalsIgnoreCase("true") )
			return "Y";
		if( flag.equalsIgnoreCase("false") )
			return "N";
		if( flag.equalsIgnoreCase("Not Specified") )
			return "U";
		return "N";
	
		
	}
	
	
	
	//TE97_024
	public static String convertTrueFlaseReauthFlagString(String flag) 
	{
		if( flag == null )
			return "false";
		if( flag.equalsIgnoreCase("N") )
			return "false";
		if( flag.equalsIgnoreCase("Y") )
			return "true";
		return "false";
	
		
	}
	public static boolean convertTrueFlaseFlagString(String flag) 
	{
		if( flag == null )
			return false;
		if( flag.equalsIgnoreCase("N") )
			return false;
		if( flag.equalsIgnoreCase("Y") )
			return true;
		return false;
	
		
	}
	
	// converting String to XMLGregorianCalendar
	public static XMLGregorianCalendar getXMLGregorianCalendarByDate(Date date) throws ParseException, DatatypeConfigurationException
	{
		if( date == null  )
			return null;
	    XMLGregorianCalendar xmlCalendar=null;
	    GregorianCalendar calendar = new GregorianCalendar();
	    String dateStr = dateToString(date , CommonUtil.ORACLE_DATE_FMT);
	    calendar.setTime(stringToJavaDate(dateStr , CommonUtil.ORACLE_DATE_FMT));
//	    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	    xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
    	xmlCalendar.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
	    return xmlCalendar;
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(Date date , boolean isSetTimeZone ) throws ParseException, DatatypeConfigurationException{
		if( date == null  )
			return null;
	    XMLGregorianCalendar xmlCalendar=null;
	    GregorianCalendar calendar = new GregorianCalendar();
	    String dateStr = dateToString(date , CommonUtil.ORACLE_TS_FMT);
	    calendar.setTime(stringToJavaDate(dateStr , CommonUtil.ORACLE_TS_FMT));
	//    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	    xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
	    if( isSetTimeZone == false )
	    {
	    	xmlCalendar.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
	    }
	    return xmlCalendar;
	}
	
	public static XMLGregorianCalendar getCalendar(String columnName,ResultSet resultSet) throws SQLException, ParseException, DatatypeConfigurationException {
		Timestamp timestamp = resultSet.getTimestamp(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return CommonUtil.getXMLGregorianCalendar(timestamp, true);
	}	

	
/*	
	// converting String to XMLGregorianCalendar
	public static XMLGregorianCalendar getXMLGregorianCalendar(String dateStr) throws ParseException, DatatypeConfigurationException{
		if( dateStr == null  || dateStr.equals(""))
			return null;
	    XMLGregorianCalendar xmlCalendar=null;
	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(stringToJavaDate(dateStr));
	    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	    return xmlCalendar;
	}
*/	
	public static String dateToString(Date date, String format)
	{
		String dateStr = null;
		dateStr = new SimpleDateFormat(format).format(date);
		
		return dateStr;
	}
	
	
	public static Date stringToJavaDate(String strDate , String format ) throws ParseException 
	{
		if( strDate == null || "".equals(strDate))
			return null;
		Date date = new SimpleDateFormat(format).parse(strDate);
		return date;

	}
	/*
	public static Date stringToJavaDate(String sDate) throws ParseException {
		Date date = null;
		if (sDate.length()==ORACLE_TS_FMT.length()) {
			try {
				date = new SimpleDateFormat(ORACLE_TS_FMT, Locale.ENGLISH).parse(sDate);
				return date;
			} catch (ParseException pe) {
			}
		}
		if (sDate.length() == "yyyy-MM-dd HH:mm:ss.SSSSSS".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss.SSSSS".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss.SSSS".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss.SSS".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss.SS".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss.S".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(sDate);
		} else if (sDate.length() == "yyyy-MM-dd HH:mm:ss".length()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(sDate);
		}
		return date;
	}
*/	
/*	public static Long findPerson(Connection connection , String consumerId, int storeNumber) throws SQLException
	{
		String selectPatientQuery = "SELECT prsnKey FROM prsn WHERE CnsmrId = ? and storeNum = ? ";
		Long personKey = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try
		{
			preparedStatement = connection.prepareStatement(selectPatientQuery);
			preparedStatement.setString(1, consumerId);
			preparedStatement.setInt(2, storeNumber);
			
			rs = preparedStatement.executeQuery( );
			
			if( rs != null && rs.next())
			{
				personKey = rs.getLong("prsnKey");
				return personKey;
			}
			else
				return null;
		}
		catch(SQLException e )
		{
			throw e;
		}
		finally
		{
			rs.close();
//			preparedStatement.close();
			CommonUtil.closePreparedStatementResultSet(preparedStatement, rs);
		}
	}
	*/
	
/*	public static Long findPersonAsRecorder(Connection connection , String consumerId, String storeNumber) throws SQLException
	{
		String selectPatientQuery = "SELECT prsnKey FROM prsn WHERE CnsmrId = ? and storeNum = ? ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(selectPatientQuery);
		preparedStatement.setString(1, consumerId);
		preparedStatement.setString(2, storeNumber);
		
		ResultSet rs = preparedStatement.executeQuery( );
		
		Long personKey = 0L;
		if( rs != null && rs.getFetchSize()>0)
		{
			rs.first();
			personKey = rs.getLong("prsnKey");
			return personKey;
		}
		return null;
	}
	
*/	
	public static SOAPFaultException faultGenerator(Exception exception, String serviceName , String operationName ,String requestor ) {
		System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.captureStackTrace", "false");		
//		System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
	//  CDRDataException, CDRException	
		String messageType = messagetype_system_exception;
		String errorCode = "GEN0001";
		String errorMessageString = "Generic Service failure";
		String state ="NON-RECOVERABLE";
		
		String exceptionAsString ="";
		String internalExceptionType =  "GenericException";
		String internalErrorCode = "GEN2500" ;

		// handlig API business exceptions 
		
		
		if(exception instanceof CDRInternalException)
		{
			messageType = "GenericException";
			errorCode = "GEN2500";
			state ="RECOVERABLE";
			internalErrorCode = ((CDRInternalException) exception).getErrorCode();
			Object obj = (CDRException)exception;
			internalExceptionType =  obj.getClass().getSimpleName();
		}
		else
		if (exception instanceof CDRException)
		{
			Object obj = (CDRException)exception;
			messageType = obj.getClass().getSimpleName();
			errorCode = ((CDRException) exception).getErrorCode();
			internalErrorCode = errorCode ; 
			internalExceptionType =  obj.getClass().getSimpleName();
			errorMessageString = exception.getMessage() ;
		}
		else 
		if(exception instanceof Exception)
		{
			
			messageType = "GenericException";
			errorCode = "GEN2500";
			state ="RECOVERABLE";
		};
		
		try {
			SOAPFault fault = null;
			fault = SOAPFactory.newInstance().createFault();
			fault.setFaultCode("soap:Server");
			fault.setFaultString(exception.getMessage());

			Detail detail = fault.addDetail();
			QName qName = null;
			SOAPElement errorElement = null;

			/*         
			 * Create Error Header
			 */
			
			qName = new QName(errNameSpace, "ErrorMessage", errPrefix);
			DetailEntry errorMessage = detail.addDetailEntry(qName);
			
			
			qName = new QName("ErrorHeader");
			SOAPElement errorHeader = errorMessage.addChildElement(qName);
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(UUID.randomUUID().toString());
			
	
			qName = new QName("MessageType");
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(messageType);			

			qName = new QName("ProcessingComponent");
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(CDR_VERSION);			
			
			qName = new QName("ServiceName");
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(serviceName);
	
			qName = new QName("State");
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(state);
			
			qName = new QName("TimeStamp");
			errorElement = errorHeader.addChildElement(qName);
			errorElement.setTextContent(CommonUtil.getCurrentTimeStamp());
	
			/* 
			 * Create FrontEnd Error Detail
			 */
			
			qName = new QName("ErrorDetail");
			SOAPElement errorDetail = errorMessage.addChildElement(qName);
			
			qName = new QName("ErrorNumber");
			errorElement = errorDetail.addChildElement(qName);
			errorElement.setTextContent(errorCode);
	
			qName = new QName("ErrorMessageSummary");
			errorElement = errorDetail.addChildElement(qName);
			errorElement.setTextContent(errorMessageString);
	
			qName = new QName("Category");
			errorElement = errorDetail.addChildElement(qName);
			errorElement.setTextContent(errorcategory_weblogic);
	
			qName = new QName("SequenceID");
			errorElement = errorDetail.addChildElement(qName);
			errorElement.setTextContent("0");
			
			
			/* 
			 * Create Internal Error Detail
			 */
			
			qName = new QName("ErrorDetail");
			SOAPElement errorDetail1 = errorMessage.addChildElement(qName);
			
			qName = new QName("ErrorNumber");
			errorElement = errorDetail1.addChildElement(qName);
			errorElement.setTextContent(internalErrorCode);
	
			qName = new QName("ErrorMessageSummary");
			errorElement = errorDetail1.addChildElement(qName);
			errorElement.setTextContent(exception.getMessage());
	
			qName = new QName("Category");
			errorElement = errorDetail1.addChildElement(qName);
			errorElement.setTextContent(errorcategory_weblogic);

			if(!requestor.equalsIgnoreCase("DP")) {
			qName = new QName("CompleteException");
			errorElement = errorDetail1.addChildElement(qName);
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			exceptionAsString = sw.toString();
			errorElement.setTextContent(exceptionAsString);

			qName = new QName("SequenceID");
			errorElement = errorDetail1.addChildElement(qName);
			errorElement.setTextContent("0");			
			}
			// log the exception stack trace
			if(logger.isErrorEnabled()) {logger.error(exceptionAsString);}
			return new SOAPFaultException(fault);
		} catch (SOAPException e) {
			e.printStackTrace();
			return null;
		}
		
		finally
		{
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			exceptionAsString = sw.toString();
			if(logger.isErrorEnabled()) {logger.error(exceptionAsString);}
		}			
	}

	


	
	/**
	 * Get Prepared Statement to_date function string.
	 * 
	 * @return			Oracle to_date function string for Prepared Statement, e.g. "to_date(?, 'YYYY-MM-DD HH24:MI:SS')".
	 */
	public static String getPsToDateFunctionStr() {
		return TO_TIMESTAMP_TZ;
	}

	
	/**
	 * Get Timestamp object from XMLGregorianCalendar.
	 * 
	 * @param 		xgc	XMLGregorianCalendar instance.
	 * @return		Timestamp object.
	 */
	public static Timestamp toTimestamp(XMLGregorianCalendar xgc) {
		if (xgc==null) {
			return null;
		}
		Calendar cal = xgc.toGregorianCalendar();
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		return ts;
	}
	
	/**
	 * Convert to Timestamp string.
	 * 
	 * @param xgc	XMLGregorianCalendar object.
	 * @return		Timestamp string.
	 */
	public static String toTimestampStr(XMLGregorianCalendar xgc) {
		if (xgc==null) {
			return null;
		}
		DateFormat otf = new SimpleDateFormat(ORACLE_TS_FMT);
		Date date = xgc.toGregorianCalendar().getTime();
		String timestampStr = otf.format(date);
		return timestampStr;
	}
	
	public static String toDateStr(XMLGregorianCalendar xgc) {
		if (xgc==null) {
			return null;
		}
		DateFormat otf = new SimpleDateFormat(ORACLE_DATE_FMT);
		Date date = xgc.toGregorianCalendar().getTime();
		String timestampStr = otf.format(date);
		return timestampStr;
	}
	
	public static void setPsStringParam(PreparedStatement ps, int paramIndex, String val) throws SQLException {
		if (val!=null) {
			ps.setString(paramIndex, val);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}
	
	public static void setPsLongParam(PreparedStatement ps, int paramIndex, String numStr) throws SQLException {
		Long numVal = null;
		if (numStr!=null) try {
			numVal = new Long(numStr);
		} catch (Exception parseEx) {
			
		}
		setPsLongParam(ps, paramIndex, numVal);
	}

	
	public static void setPsLongParam(PreparedStatement ps, int paramIndex, Long num) throws SQLException {
		if (num!=null) {
			ps.setLong(paramIndex, num);
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}
	
	

	public static void setPsDoubleParam(PreparedStatement ps, int paramIndex, Double num) throws SQLException {
		if (num!=null) {
			ps.setDouble(paramIndex, num);
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}

	public static void setPsLongParam(PreparedStatement ps, int paramIndex, BigDecimal num) throws SQLException {
		if (num!=null) {
			ps.setLong(paramIndex, num.longValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}	

	public static void setPsLongParam(PreparedStatement ps, int paramIndex, BigInteger num) throws SQLException {
		if (num!=null) {
			ps.setLong(paramIndex, num.longValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}	
	
	public static void setPsIntParam(PreparedStatement ps, int paramIndex, Integer num) throws SQLException {
		if (num !=null)
		{
			ps.setInt(paramIndex, num);
		}
		else
			ps.setNull(paramIndex, Types.NUMERIC);
	}
		
	public static String toDateStrWithOutTime(XMLGregorianCalendar xgc) {
		if (xgc==null) {
			return null;
		}
		xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		DateFormat otf = new SimpleDateFormat(ORACLE_DATE_FMT);
		Date date = xgc.toGregorianCalendar().getTime();
		String timestampStr = otf.format(date);
		return timestampStr;
	}
	public static void setPsBooleanParam(PreparedStatement ps, int paramIndex, Boolean flag) throws SQLException {
		if (flag!=null) {
			ps.setString (paramIndex, CommonUtil.convertBooleanToYesNoFlag(flag.booleanValue() ) );
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	
	
	public static void setPsXMLGregorianCalendarParam(PreparedStatement ps, int paramIndex, XMLGregorianCalendar xmlCalendar) throws SQLException {
		if (xmlCalendar!=null) {
			String timestampStr = toTimestampStr(xmlCalendar);
			ps.setString (paramIndex, timestampStr);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	

	public static void setPsXMLGregorianCalendarToDateParam(PreparedStatement ps, int paramIndex, XMLGregorianCalendar xmlCalendar) throws SQLException {
		if (xmlCalendar!=null) {
			String timestampStr = toDateStr(xmlCalendar);
			ps.setString (paramIndex, timestampStr);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	

	public static void setPsLongParam(PreparedStatement ps, int paramIndex, Integer numVal) throws SQLException {
		if (numVal!=null) {
			ps.setLong(paramIndex, numVal.longValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}
	
	/**
	 * Determine if update request is new (later than database's data) for a given JAXB entity. 
	 * 
	 * @param updateTimestampDb	Update timestamp in database.
	 * @param updateTsJaxb		Update timestamp of JAXB object.
	 * @return					True if Update Request is new (later than database's data), or false otherwise.
	 * 
	 * @throws InvalidInputException 
	 */
	public static boolean isUpdateRequestNew(Timestamp updateTimestampDb, XMLGregorianCalendar updateTimestampJaxb) throws InvalidInputException {
		if (updateTimestampJaxb==null) {
			throw new InvalidInputException("Update Timestamp of JAXB entity not populated.");
		}
		Date updateTimestampJaxbDate = CommonUtil.toTimestamp(updateTimestampJaxb);
		boolean isUpdateRequestNew = updateTimestampDb==null || updateTimestampJaxbDate.compareTo(updateTimestampDb)>0 ;
		if(logger.isDebugEnabled()) {logger.debug("CommonUtil.isUpdateRequestNew ="+isUpdateRequestNew);
		logger.debug("dbUpdateTimeStamp ="+updateTimestampDb +"updateTimestampJaxbDate= "+updateTimestampJaxbDate);	}
	    return isUpdateRequestNew;
	}
	
	public static boolean isUpdateRequestNew2(Timestamp dbUpdateTimeStamp,
			XMLGregorianCalendar payloadUpdateTimeStamp) {
		if(payloadUpdateTimeStamp == null) {
			return true;
		}
		Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
		boolean isUpdateRequestNew = dbUpdateTimeStamp==null || updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp)>0 ;
		return isUpdateRequestNew;
		
	}
	
	public static Long generatePK(Connection  connection, String tableName) throws SQLException {
		Long sequenceNextVal = null;
		PreparedStatement ps = null ;
		ResultSet rs = null; 
		try {
			String query = "Select " + tableName + "_SEQ.nextval from dual " ;
			
			if(logger.isDebugEnabled()) {logger.debug("sequence query : \n" + query);}
			ps = connection.prepareStatement( query );
			rs = ps.executeQuery();
			if (rs.next()) {
				sequenceNextVal= rs.getLong(1);
				if ( sequenceNextVal == null || sequenceNextVal <= 0 ) {
					throw new SQLException("No value for sequence : " + tableName + "_SEQ.nextval");
				}
			} else {
				throw new SQLException("No value for sequence : " + tableName + "_SEQ.nextval");
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
//			throw e;
		} 
		finally {
			closePreparedStatementResultSet(ps, rs);
		}
		return sequenceNextVal;
	}
	
	public static Pack populatePack(ResultSet rs) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException
	{
		Pack pack = new Pack();
		pack.setAlternativepacksize(CommonUtil.getInt("PACK_ALTRNTVPACKSZ",rs));
		pack.setAlternativepacksizeunitofmeasure(rs.getString("PACK_ALTRNTVPACKSZUOM"));
		pack.setStrength(rs.getString("PACK_STRNGTH"));//TE97_024
		pack.setGTIN(rs.getString("PACK_GTIN")) ;
		
		pack.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISACTFLG" )));
		pack.setConsumerId(rs.getString("PACK_CNSMRID")) ;
		pack.setIsCurrentFlag(CommonUtil.convertYesNoFlagToBoolean(rs.getString("PACK_ISCRNTFLG"))) ;
		pack.setProducerId(rs.getString("PACK_PRDCRID")) ;
		pack.setManufacturerDiscontinuedDate(CommonUtil.getXMLGregorianCalendar(rs.getDate("PACK_MFCTRDISCNTDDT") , true));
		pack.setPackSize(CommonUtil.getBigDecimal("PACK_PACKSZ",rs) ) ;
		pack.setMasterid(rs.getString("PACK_MSTRID")) ;
		Long packSizeUOMTypeKey = CommonUtil.getLong("PACK_PACKSZUOMTYPKEY",rs);
		String jndiVersion=TableCacheSingleton.JNDI_VERSION;
		if(packSizeUOMTypeKey != null)
		{
	      //  String packSizeUOMTypeCode = getCodeFromKey(LT_PACKSZUOMTYP, packSizeUOMTypeKey);
	        String packSizeUOMTypeCode =TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_PACKSZUOMTYP, packSizeUOMTypeKey);
	        if (packSizeUOMTypeCode != null)
	        {
	        	PackSizeUoMTypeDisplay packSizeUoMTypeDisplay = new PackSizeUoMTypeDisplay();
	        	packSizeUoMTypeDisplay.setPackSizeUoMTypeCode(packSizeUOMTypeCode);
	        	pack.setPackSizeUOMCode(packSizeUoMTypeDisplay);			        	
	        }
		}
		
		Long strengthUOMTypeKey = CommonUtil.getLong("PACK_STRNGTHUOMTYPKEY",rs);
		if(strengthUOMTypeKey!=null)
		{
	     //   String strengthUOMTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, strengthUOMTypeKey);
			 String strengthUOMTypeCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_STRNGTHUOMTYP, strengthUOMTypeKey);
			pack.setStrengthUOMCode(strengthUOMTypeCode);
		}
		return pack;
	}
	
	public static  Address populateAddressPrescriber(Address address,ResultSet rs) throws SQLException {
		boolean hasValue = false;
		String value = null;
		value = rs.getString("EMAIL_EMAILADDR");
		if (value != null) {
			address.setEmail(value);
			hasValue = true;
		}
		;
		value = rs.getString("FAX_TELECOMNUM");
		if (value != null) {
			address.setFaxNumber(value);
			hasValue = true;
		}
		;

		value = rs.getString("PHONE_TELECOMNUM");
		if (value != null) {
			address.setPrimaryPhoneNumber(value);
			hasValue = true;
		}
		;

		
		/*value = getString("ALTERN_TELECOMNUM");
		if (value != null) {
			address.setAlternatePhoneNumber(value);
			hasValue = true;
		}*/
	
		
		value = rs.getString("ADDR_ADDRLNONE");
		if (value != null) {
			address.setAddressLineOne(value);
			hasValue = true;
		}
		;

		value = rs.getString("ADDR_ADDRLNTWO");
		if (value != null) {
			address.setAddressLineTwo(value);
			hasValue = true;
		}
		;

		value = rs.getString("ADDR_CITYNM");
		if (value != null) {
			address.setCityName(value);
			hasValue = true;
		}
		;
		
		value = rs.getString("ADDR_CNTRYCD");
		if (value != null) {
			address.setCountryCode(value);
			hasValue = true;
		}
		;
		
		value = rs.getString("ADDR_PROVCD");
		if (value != null) {
			Province province = Province.fromValue(value);
			address.setProvinceCode(province);
			hasValue = true;
		}
		;

		value = rs.getString("ADDR_POSTALCD");
		if (value != null) {
			address.setPostalCode(value);
			hasValue = true;
		}
		;

		value = rs.getString("CM_CNSMRID");
		if (value != null) {
			address.setConsumerId(value);;
			hasValue = true;
		}
		;
		
		value = rs.getString("ADDR_TYPE");
		if (value != null) {
			address.setContactPurposeType(ContactPurposeType.fromValue(value)); 
			hasValue = true;
		}
		//TE97_024 Commented for future implementation
        value = rs.getString("ACTVFLG");
        if (value != null) {
	        address.setIsActiveAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024   Mail_Flag         (Default Y)
        value = rs.getString("MAILFLG");
        if (value != null) {
	        address.setIsMailAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
      //TE97_024   Mail_Flag         (Default null)
        value = rs.getString("PRIMARYFLG");
        if (value != null) {
	        try {
				address.setIsPrimaryAddressFlag(CommonUtil.convertYesNoFlagToBoolean(value));
			} catch (CDRInternalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        hasValue = true;
        }
    	//TE97_024  Reauth_Email_Flag (Default N)
        value = rs.getString("REAUTHEMAILFLAG");
        if (value != null) {
	        address.setIsReauthEmailFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Phone_Flag (Default Y)
        value = rs.getString("REAUTHPHONEFLAG");
        if (value != null) {
	        address.setIsReauthPhoneFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Fax_Flag   (Default U)
      //TE97_024 Reauth_Fax_Flag   (Default U)
        value = rs.getString("REAUTHFAXFLAG");
        if (value != null && !value.equals("U")) {
	        address.setIsReauthFaxFlag(ReauthFaxFlag.fromValue(CommonUtil.convertTrueFlaseReauthFlagString(value))); 
	        hasValue = true;
        }/*else{
        	 address.setIsReauthFaxFlag(ReauthFaxFlag.NOT_SPECIFIED); 
 	        hasValue = true;
        }*/
        
    	//TE97_024 Reauth_Visit_Flag (Default N)
        value = rs.getString("REAUTHVISITFLAG");
        if (value != null) {
	        address.setIsReauthVisitFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
		if (hasValue)
			return address;
		else
			return null;
    }
//T99 Ended	
	
	
	public static String emptyIfNull(String srcStr) {
		return (srcStr!=null && srcStr.trim().length() > 0) ? srcStr : "";
	}
	
	public static boolean isEmpty(String srcStr) {
		return (srcStr!=null && srcStr.trim().length() > 0) ? false : true;
	}	
	public static BigInteger getBigInteger(String columnName,ResultSet resultSet) throws SQLException {
		String value = resultSet.getString(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return new BigInteger(value);
	}
	public static Integer getInt(String columnName,ResultSet resultSet) throws SQLException {
		Integer value = new Integer(resultSet.getInt(columnName));
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}
	
	
	public static  Long getLong(String columnName,ResultSet resultSet) throws SQLException {
		Long value = new Long(resultSet.getLong(columnName));
		if (resultSet.wasNull())
			return null;
		else
			return value;
	} 
	public static BigDecimal getBigDecimal(String columnName,ResultSet resultSet) throws SQLException {
		BigDecimal value = resultSet.getBigDecimal(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	
	
	public static Double getDouble(String columnName,ResultSet resultSet) throws SQLException {
		Double value = resultSet.getDouble(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	
	
	public static Boolean getBoolean(String columnName,ResultSet resultSet) throws CDRInternalException, SQLException {
		String flagStr = resultSet.getString(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return new Boolean(CommonUtil.convertYesNoFlagToBoolean(flagStr));
	}

	public static Date getDate(String columnName,ResultSet resultSet) throws SQLException {
		Date value = resultSet.getDate(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	
	

	
	public static void log(Logger log, String logContent) {
		try {
			long current = System.currentTimeMillis();
			long diff = current - getLastLogTime();
			setLastLogTime(current);
			logContent = String.valueOf(diff) + " ms: " + logContent;
			log.info(logContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	 public static <T> T InpStream2JAXB(InputStream inpStream, Class<T> clazz) throws JAXBException {
   	  try {
		      JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		      Object obj = unmarshaller.unmarshal(inpStream);
		      if (clazz.isInstance(obj))  {
		          return clazz.cast(obj);
		      }
   	  } catch (UnmarshalException e) {
			    if(logger.isErrorEnabled()) {logger.error("1. XML payload (inpStream) does not represent an instance of " + clazz.getName());}
			    e.printStackTrace();
		  } catch (JAXBException e) {
			    if(logger.isErrorEnabled()) {logger.error("2 XML payload (inpStream) does not represent an instance of " + clazz.getName());}
			    e.printStackTrace();
		  } catch (IllegalArgumentException e) {
			    if(logger.isErrorEnabled()) {logger.error("3. XML payload (inpStream) does not represent an instance of " + clazz.getName());}
			    e.printStackTrace();
		  } catch (Exception e) {
			    e.printStackTrace();
		  }
		  return null;
	}	

  
	  public static <T> void DisplayJAXB(T obj, Class<T> clazz) throws JAXBException {
          System.out.println("-----------------------------------------------------");
          System.out.println(" Display JAXB class:  " + obj.getClass().getName()    );
          System.out.println("-----------------------------------------------------");
          JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
          jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          jaxbMarshaller.marshal(obj, System.out);
   }
   
	  public static <T> String JAXB2String(T obj, Class<T> clazz) throws JAXBException {
          StringWriter sw = new StringWriter();
          JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         jaxbMarshaller.marshal(obj, sw);
         return sw.toString();
      }

   public static <T> void JAXB2File(T obj, Class<T> clazz, File file) throws JAXBException {
          JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         jaxbMarshaller.marshal(obj, file);
    }

   public static <T> void archivePayload(T obj, Class<T> clazz, String filename) throws JAXBException, Exception  {
		try {

			String DirName = CDRConfigFileReader.getInstance().getProperty("CDRArchiveDIR");

			String FileName = null;

			// Wrapper01 wrapper = new Wrapper01(); ----> delete this line

			if (DirName != null && DirName.trim().length() > 3) {

				String dateTimeFormat = "yyyy_MMddHH_mmss_SSS";

				SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);

				String payloadTime = sdf.format(Calendar.getInstance().getTime());

				FileName = DirName + FS + payloadTime + filename;

				File file = new File(FileName);

				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(obj, file);

			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
   
   public static String getMemoryUsage()  {
	   Runtime runtime = Runtime.getRuntime();

	   NumberFormat format = NumberFormat.getInstance();
	   StringBuilder sb = new StringBuilder();
	   long maxMemory = runtime.maxMemory();
	   long allocatedMemory = runtime.totalMemory();
	   long freeMemory = runtime.freeMemory();
	   long usedMemory = allocatedMemory - freeMemory ; 
	
	   sb.append("  JVM memory usage (MB)  " + "\n");
	   sb.append("=========================" + "\n");
	   sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
	   sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
	   sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
	   sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
	   sb.append("total used memory: " + format.format(usedMemory / 1024) + "\n");
	   return sb.toString();
}


}

	
	
