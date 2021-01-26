package ca.sdm.cdr.common.constant;


/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL34 2017-12-21   NTT Data     Vlad Eidinov  Launcher class for CDR StandAlone project
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project

*/

public class Constants {

	public static final int UNIQUE_CONSTRAINT_VIOLATION = 1; 

	// Property for config files path on the server CDR.config DB_SCH
	public final static String CONFIG_DIR_PROPERTIES = "ConfigDirectory" ; 	
	public final static String TableCacheSingleton_RefreshInterval = "TableCacheSingleton_RefreshInterval";
	public final static String TableCacheSingleton_RefreshSchedulerStartTime = "TableCacheSingleton_RefreshSchedulerStartTime";
	public final static String CDRConfigFile_RefreshInterval = "CDRConfigFile_RefreshInterval";
	
	//CR-4 PurgingService configuring number of years to be purged 
	public final static String PURGE_YEAR_DURATION = "PURGE_YEAR_DURATION" ;
	
	// CDR DB Schema
	public final static String CDR_PROPERTIES_DB_SCHEMA = "DB_SCHEMA";
	
	// CDR DB JNDI
	public final static String DATA_SOURCE_JNDI = "DATA_SOURCE_JNDI";
	
	
	// CDR CB NEW JNDI added by Siddhi for CR 53
	
	public final static String DATA_SOURCE_JNDI_DIGITAL = "DATA_SOURCE_JNDI_DIGITAL";
	
	// DB Source System in SRCESYS table
	public final static String SOURCESYS_DIGITAL_RX = "DigitalRx";
	public final static String SOURCESYS_ACCURO = "Accuro";    //VL99

	
	// DB Telecom TelFax Indicators
/*	public final static String TELECOM_TABLE_TEL_INDICATOR = "T";
	public final static String TELECOM_TABLE_FAX_INDICATOR = "F";
*/	
	public final static String CDR_PROPERTIES_ODMURL = "ODMURL";
	
	//
	
	public final static String TABLE_CACHE_CONFIG_FILENAME = "TableCache.config";
	public final static String SOURCE_PHARMACYCHANNEL_CONFIG_FILENAME = "SourcePharmacyChannel.xml";

//  VL32 Started
//	public final static String dbName = "local";
//	public final static String dbName = "dit";
//	public final static String dbName = "sit";
//	public final static String dbName = "uat";
	public final static String dbName = "qhr_dit";
//	public final static String dbName = "qhr_sit";
//	public final static String dbName = "qhr_uat";
//	public final static String dbName = "prod";
//  VL32 Ended

	public static final String CDR_PROPERTIES_ODMURL_MEDREVIEW = "ODMURL_MEDREVIEW";
}
