package ca.sdm.cdr.common.singleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.util.CommonUtil;

public abstract class SingletonReferesh {

	private static Logger logger = LogManager.getLogger(SingletonReferesh.class);

	protected Calendar scheduledRefreshCalendar ; 
	protected Calendar lastRefreshedCalendar ; 

	protected String configFileName = null ;
	protected int refreshInterval = 0;  // referesh  interval in hours

	protected boolean isForcedRefreshed = false ;
	
	
	/**
	 * @param interval				: the number of hours for each refresh
	 * @param fileName				: the name of the file that should be refreshed from
	 */
	protected void initialize( /* String refreshscheduledTime , */ int interval, String fileName) 
	{

		if(logger.isDebugEnabled()) {logger.debug("Start initializing Singleton for : " + fileName + " , with refreshInterval : " + interval );}

		refreshInterval = interval ;
		configFileName = fileName ;

        lastRefreshedCalendar 	 = Calendar.getInstance();
        scheduledRefreshCalendar = Calendar.getInstance();
        resetRefreshTimeStamps();
        
	}
	
	protected boolean isRefreshable()
	{
		boolean isRfereshable = false ;
	//	Calendar currentCalendar = Calendar.getInstance();
		
	//	if( scheduledRefreshCalendar.before(currentCalendar) == true ||  isForcedRefreshed == true )
		if( isForcedRefreshed == true ) 
		{
			isRfereshable   = true;
		}
		return isRfereshable  ;
		
	}
	
	protected void resetRefreshTimeStamps()
	{
		if(logger.isDebugEnabled()) {logger.debug("resetRefreshTimeStamps : current refresh schedule : " + CommonUtil.dateToString(scheduledRefreshCalendar.getTime(),"yyyy-MM-dd HH:mm:ss") );}
		
		scheduledRefreshCalendar = Calendar.getInstance();
		scheduledRefreshCalendar.add(Calendar.HOUR_OF_DAY, refreshInterval);
		if(logger.isDebugEnabled()) {logger.debug("resetRefreshTimeStamps : new refresh schedule : " + CommonUtil.dateToString(scheduledRefreshCalendar.getTime(),"yyyy-MM-dd HH:mm:ss") );}
		isForcedRefreshed = false;
	}


    public void forceRefresh() throws NamingException, SQLException, IOException, JAXBException
    {
    	isForcedRefreshed = true;
    	refresh();
    }

	protected abstract  void refresh()  throws NamingException, SQLException, IOException, JAXBException ;
	
}
