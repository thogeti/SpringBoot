package ca.sdm.cdr.jdbc.query.api;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

import ca.shoppersdrugmart.rxhb.ehealth.Location;

public class LocationGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(LocationGet.class);

	public Location fetch(Connection connection, Long locationKey)
	{
		return populate(connection, locationKey);
	}
	
	private Location populate(Connection connection, Long locationKey)
	{
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: LocationGet.populate. locationKey : " + locationKey);}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: LocationGet.populate. locationKey : " + locationKey);}
		return null;
	}
}
