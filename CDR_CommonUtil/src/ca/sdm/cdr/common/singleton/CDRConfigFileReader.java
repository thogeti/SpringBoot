package ca.sdm.cdr.common.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.sdm.cdr.common.constant.Constants;

public class CDRConfigFileReader extends SingletonReferesh {
	
	final static Logger logger = LogManager.getLogger(CDRConfigFileReader.class.getName());	

	private static volatile CDRConfigFileReader instance;
	private static Properties CDRConfigProperties;
	
    public CDRConfigFileReader() { }

    public static CDRConfigFileReader getInstance() throws IOException  
    {
        if (instance == null ) 
        {
            synchronized (CDRConfigFileReader.class) 
            {
                if (instance == null) 
                {
                    instance = new CDRConfigFileReader();
                    readConfigFile();
                }
            }
        }
        return instance;    	
    }    

    protected void refresh( ) throws IOException, SQLException, NamingException 
    {
        if (isRefreshable()  == true ) 
        {
            synchronized (CDRConfigFileReader.class) 
            {
                if (isRefreshable()  == true ) 
                {
	            	if(logger.isDebugEnabled()) {logger.debug("Refreshing cache : CDRConfigFileReader .... " );}
            	    // Clear the properties if already exist 
            	    if( CDRConfigProperties != null )
            	    {
            	    	CDRConfigProperties.clear();
            	    	CDRConfigProperties = null;
            	    }

                	readConfigFile();
                
                }
            }
        }
    }
    
    private static void readConfigFile() throws IOException 
    {
    	String osName = System.getProperty("os.name");
    	if(logger.isDebugEnabled()) {logger.debug("os ----> : " + osName ) ; }
    	
    	String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
    	if(logger.isDebugEnabled()) {logger.debug("ConfigDirectory ----> : " + ConfigDirectory ) ; }

	    String configFilePath = ConfigDirectory.trim() + "/CDR.config";
	    
	    instance.initialize(/* "00:00:00", */ 24, configFilePath);
	    
	    CDRConfigProperties = new Properties ();
		
		InputStream inputStream = null;
		try 
		{
			inputStream = new FileInputStream(configFilePath);
			CDRConfigProperties.load(inputStream);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileNotFoundException(e.getMessage());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		finally
		{
			if ( inputStream != null )
				inputStream.close();
		}
    }
   
    public String getProperty(String key) throws IOException, SQLException
    {
 //   	refresh();
    	String val = (key!=null && CDRConfigProperties.getProperty(key)!=null) ? CDRConfigProperties.getProperty(key).trim() : null;
    	return val;

    }
 
}
