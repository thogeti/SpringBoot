package ca.sdm.cdr.jdbc.upsert.api;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.sdm.cdr.common.util.CommonUtil;

public class DispenserApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(DispenseApi.class);
	
	private final static String MERGEQUERY = 
			" MERGE INTO TXR target   " + 
					"  USING    " + 
					"  ( select TXRKEY  from    " + 
					"       (select TXRKEY from TXR where TXKEY = ? and PRSNRLKEY = ? " + 
					"            union all   " + 
					"            select NULL TXRKEY from dual    " + 
					"       )where rownum=1   " + 
					"       order by TXRKEY desc   " + 
					"  )  src    " + 
					"  ON  ( src.TXRKEY = target.TXRKEY )   " + 
					"    WHEN NOT MATCHED THEN   " + 
					"      INSERT (TXRKEY,TXKEY,PRSNRLKEY)   " + 
					"      VALUES (TXR_SEQ.nextval, ? , ? )  " ;
	
	public void upsertDispenser(Connection connection, Long dispenserPersonRoleKey, Long dispenseKey) throws SQLException 
	{
		try {
			// Long dispenserKey = null ;
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertDispenser. dispenserPersonRoleKey " + dispenserPersonRoleKey
					+ ", dispenseKey: " + dispenseKey);}

			ps = connection.prepareStatement(MERGEQUERY);

			// merge criteria
			CommonUtil.setPsLongParam(ps, 1, dispenseKey);
			CommonUtil.setPsLongParam(ps, 2, dispenserPersonRoleKey);
			CommonUtil.setPsLongParam(ps, 3, dispenseKey);
			CommonUtil.setPsLongParam(ps, 4, dispenserPersonRoleKey);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertDispenser. dispenserPersonRoleKey " + dispenserPersonRoleKey
					+ ", dispenseKey: " + dispenseKey);}
		}
		
	}
}
