package ca.sdm.cdr.jdbc.upsert.api;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Store;

public class LocationApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(LocationApi.class);
	private final static String SELECTSQL = "SELECT LOC.LOCKEY FROM LOC, STORE WHERE LOC.LOCKEY = STORE.LOCKEY	AND STORE.STORENUM = ? ";

	private final static String INSERT_SQL="insert into loc ( lockey ) values(loc_seq.nextval)";

	public Long insert(Connection connection) throws SQLException
	{
		try
		{
			ps = connection.prepareStatement(INSERT_SQL,new String[] {"LOCKEY"}  );
			int res = ps.executeUpdate();
			Long locationKey = null;  
			rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				locationKey = rs.getLong(1);
			}

			return locationKey;
		}
		catch( SQLException e )
		{
			super.close();
			throw e;
		}
	}

	public Long findLocation(Connection connection, Store store, Location location) throws SQLException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: findLocation. location consumer id : " + location.getConsumerId());	}
			String storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
			Long LocKey = null;
			// Store store = location.getStore();
			if (store != null) {
				ps = connection.prepareStatement(SELECTSQL);
				ps.setString(1, storeNumberString);

				rs = ps.executeQuery();
				if (rs.next()) {
					LocKey = rs.getLong("LOCKEY");
					return LocKey;
				}
			}
			return LocKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally
		{
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: findLocation. location consumer id : " + location.getConsumerId());	}	
		}
	}
}
