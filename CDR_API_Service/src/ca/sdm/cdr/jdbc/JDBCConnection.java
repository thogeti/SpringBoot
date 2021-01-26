package ca.sdm.cdr.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnection {
	public Connection  connection = null;
	
	public JDBCConnection(Connection connection)
	{
		this.connection = connection ;
	}
}
