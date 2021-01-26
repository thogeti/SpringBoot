package ca.sdm.cdr.jdbc.api.util.idgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.JDBCConnection;


public class IdGenerator  extends JDBCConnection {

	private static Logger logger = LogManager.getLogger(IdGenerator.class);

	public IdGenerator (Connection  connection)
	{
		super(connection);
	}
	
	public static Long generate(Connection  connection, String tableName ) throws SQLException
	{
		Long sequenceNextVal = null;
		PreparedStatement ps = null ;
		ResultSet rs = null; 
		try
		{
			String sequenceName = tableName + "_SEQ.nextval";
	//		ps = connection.prepareStatement("Select Max(" + tableName + "Key) From  " + tableName );
			String query = "Select " + sequenceName + "  From  dual " ;
			
	//		logger.debug("sequence query : \n" + query);
			ps = connection.prepareStatement( query );
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				sequenceNextVal= rs.getLong(1);
				if ( sequenceNextVal == null || sequenceNextVal <= 0 )
				{
					throw new SQLException("No value for sequence : " + sequenceName);
				}
			}
			else
			{
				throw new SQLException("No value for sequence : " + sequenceName);
			}
			return sequenceNextVal;
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw e;
		} 
		finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
	}
}
