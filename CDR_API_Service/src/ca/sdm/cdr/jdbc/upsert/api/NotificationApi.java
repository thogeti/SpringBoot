package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;

public class NotificationApi extends CDRUpsert {

	private static Logger logger = LogManager.getLogger(NotificationApi.class);
	
	
	public void upsertNotification(Connection connection,long rxkey ) throws SQLException, NamingException, IOException {
		String notificationInsertQuery=null;
		try{
			notificationInsertQuery = TableCacheSingleton.getResource("NotificationInsert.sql");
			ps = connection.prepareStatement(notificationInsertQuery);
			setPsLongParam(1, rxkey);
			setPsLongParam(2, rxkey);
		/*	setPsLongParam(3, rxkey);
			setPsLongParam(4, rxkey); */
			int res = ps.executeUpdate();
			
			if (logger.isInfoEnabled())  {logger.info(res + " Nofication  has been inserted for  rxkey " + rxkey );}
		
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				super.close();
				if (logger.isInfoEnabled())  {logger.info("End insertRxSubscrptnSrceSys ");}
			}	
		}
	
	
	
	
}
