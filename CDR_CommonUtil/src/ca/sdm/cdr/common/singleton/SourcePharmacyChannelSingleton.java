package ca.sdm.cdr.common.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.ChannelNotFoundException;

import ca.sdm.cdr.common.constant.Constants;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.sourcepharmacychannel.SourcePharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.sourcepharmacychannel.SourcePharmacyChannelMap;

public class SourcePharmacyChannelSingleton extends SingletonReferesh {

	final static Logger logger = LogManager.getLogger(SourcePharmacyChannelSingleton.class);
	private static volatile SourcePharmacyChannelSingleton instance;
	// this hash map, maps the table name to the List of the table columns. 
	private static Map<String,PharmacyChannel> sourceChannelHashMap;

	private SourcePharmacyChannelSingleton()
	{

	}

	public static SourcePharmacyChannelSingleton getInstance() throws NamingException, SQLException, IOException, JAXBException
	{
		if (instance == null ) 
		{
			synchronized (SourcePharmacyChannelSingleton.class) 
			{
				if (instance == null) 
				{
					sourceChannelHashMap = new HashMap<String,PharmacyChannel>();

					instance = new SourcePharmacyChannelSingleton();

					String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
					if(logger.isDebugEnabled()) {logger.debug("ConfigDirectory ----> : " + ConfigDirectory ) ;}

					String sourcePharmacyChannelXmlFileName = ConfigDirectory.trim() + "/" + Constants.SOURCE_PHARMACYCHANNEL_CONFIG_FILENAME;

					SourcePharmacyChannel sourcePharmacyChannel  = XMLToSourcePharmacyChannelJaxB(sourcePharmacyChannelXmlFileName);
					String refreshInterval = CDRConfigFileReader.getInstance().getProperty(Constants.TableCacheSingleton_RefreshInterval);
					
					instance.initialize( /* schedulerStartTime , */ new Integer(refreshInterval)  ,   sourcePharmacyChannelXmlFileName);

					populateHashMaps(sourcePharmacyChannel);
				}
			}
		}
		return instance;    	
	}
	
	protected void refresh( ) throws NamingException, SQLException, IOException, JAXBException
    {
        if (isRefreshable()  == true ) 
        {
            synchronized (SourcePharmacyChannelSingleton.class) 
            {
	            if (isRefreshable()  == true ) 
	            {
	            	if(logger.isDebugEnabled()) {logger.debug("Refreshing cache : SourcePharmacyChannelSingleton " );}
	            	sourceChannelHashMap.clear();
	            	sourceChannelHashMap = null;
					sourceChannelHashMap = new HashMap<String,PharmacyChannel>();

					String ConfigDirectory = System.getProperty(Constants.CONFIG_DIR_PROPERTIES);
					if(logger.isDebugEnabled()) {logger.debug("ConfigDirectory ----> : " + ConfigDirectory ) ;}

					String sourcePharmacyChannelXmlFileName = ConfigDirectory.trim() + "/" + Constants.SOURCE_PHARMACYCHANNEL_CONFIG_FILENAME;
					SourcePharmacyChannel sourcePharmacyChannel  = XMLToSourcePharmacyChannelJaxB(sourcePharmacyChannelXmlFileName);
					populateHashMaps(sourcePharmacyChannel);
	    	        resetRefreshTimeStamps();
	            }
            }
        }    	
    }

	public PharmacyChannel getPharmacyChannel(String transactionSourceChannel) throws ChannelNotFoundException, NamingException, SQLException, IOException, JAXBException
	{
		refresh();
		PharmacyChannel pharmacyChannel = sourceChannelHashMap.get(transactionSourceChannel);
		if( pharmacyChannel == null)
		{
			throw new ChannelNotFoundException(Constants.SOURCE_PHARMACYCHANNEL_CONFIG_FILENAME,transactionSourceChannel);
		}
		return pharmacyChannel;
	}
	
	private static void populateHashMaps(SourcePharmacyChannel sourcePharmacyChannel)
	{
		List<SourcePharmacyChannelMap> sourcePharmacyChannelMapList = sourcePharmacyChannel.getSourcePharmacyChannel();
		for(SourcePharmacyChannelMap sourcePharmacyChannelMap : sourcePharmacyChannelMapList )
		{
			sourceChannelHashMap.put(sourcePharmacyChannelMap.getTransactionSourceChannel().value(), sourcePharmacyChannelMap.getPharmacyChannel());
		}
	}
	
	private static SourcePharmacyChannel XMLToSourcePharmacyChannelJaxB(String fileName) throws JAXBException, FileNotFoundException 
	{
		File file = new File(fileName);

		JAXBContext jaxbContext = JAXBContext.newInstance(SourcePharmacyChannel.class); 
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
		// return (Setup)unmarshaller.unmarshal(dataHandler.getDataSource().getInputStream()); /* Original Code */
		InputStream targetStream = new FileInputStream(file);

		JAXBElement<SourcePharmacyChannel> root = unmarshaller.unmarshal(new StreamSource(targetStream), SourcePharmacyChannel.class);
		try {
			targetStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root.getValue(); 
	}
	
}
