package ca.sdm.cdr.common.singleton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.constant.Constants;

public class TableColumnSingleton extends SingletonReferesh {
	
	protected String version = "1.0";
	final static Logger logger = LogManager.getLogger(TableColumnSingleton.class);
	private static volatile TableColumnSingleton instance;
	// this hash map, maps the table name to the List of the table columns. 
	private static Map<String,List<String>> tableColumnsHashMap;
	private static boolean isForcedRefreshed = false;
    private static String cdrDBSchema ;

	public TableColumnSingleton() { }

	public static TableColumnSingleton getInstance( ) throws IOException, SQLException 
    {
        if (instance == null ) 
        {
            synchronized (TableColumnSingleton.class) 
            {
                if (instance == null) 
                {
                	cdrDBSchema = CDRConfigFileReader.getInstance().getProperty(Constants.CDR_PROPERTIES_DB_SCHEMA);
                	tableColumnsHashMap = new HashMap<String,List<String>>();
                    instance = new TableColumnSingleton();
                }
            }
        }
        return instance;
    }
    
    public String createSQLSelectFromColumns(Connection connection, String tableAlias[][]) throws CodeNotFoundFromTableCacheException, SQLException
    {
		String selectFields = "" ;
		for(int i = 0 ; i< tableAlias.length ; i++)
		{
			String tableName = tableAlias[i][0] ;
			String aliasName = tableAlias[i][1] ;
			String selStr = createSQLSelectFromColumns(connection, tableName, aliasName);
			if( i < tableAlias.length-1 )
				selectFields += selStr + " , ";
			else
				selectFields += selStr ;
		}
    	return selectFields;
    }
    
    public String createSQLSelectFromColumns(Connection connection, String tableName,String alias) throws CodeNotFoundFromTableCacheException, SQLException
    {
    	if(logger.isDebugEnabled()) {logger.debug("createSQLSelectFromColumns ,  table : " + tableName);}
    	List<String> columnList = tableColumnsHashMap.get(tableName);
    	if (columnList == null ) 
        {
            synchronized (TableColumnSingleton.class) 
            {
            	columnList = tableColumnsHashMap.get(tableName);
                if (columnList == null) 
                {
                	columnList = getTableColumns(connection, tableName);
                	tableColumnsHashMap.put(tableName, columnList);
                }
            }
        }    
    	int listSize = columnList.size();
    	String columName = columnList.get(0);
    	String SqlSelectTableColumns =  alias +"." +columName + " as " + alias + "_" + columName ;
    	
    	for ( int i = 1 ; i<listSize ; i++ )
    	{
    		columName = columnList.get(i);
    		SqlSelectTableColumns += " , " + alias +"." + columName + " as " + alias + "_" + columName ;
    	}
    	return SqlSelectTableColumns;
    }

    protected void refresh( ) throws NamingException, SQLException, IOException 
    {
        if ( isForcedRefreshed  == true ) 
        {
            synchronized (TableColumnSingleton.class) 
            {

	            if ( isForcedRefreshed == true ) 
	            {
	            	if(logger.isDebugEnabled()) {logger.debug("Refreshing cache : TableColumnSingleton  .... " );}
	            	
	            	tableColumnsHashMap.clear();
	            	tableColumnsHashMap = null;
	            	
	            	tableColumnsHashMap = new HashMap<String,List<String>>();
	    	    	
	            }
            }
        }    	
    }

    private static List<String> getTableColumns(Connection connection, String tableName) throws SQLException
    {
//    	String query = "SELECT  COLUMN_NAME FROM sys.ALL_TAB_COLUMNS  WHERE OWNER = ? AND TABLE_NAME = ? " ;
    	
    	String query = "SELECT  COLUMN_NAME FROM sys.ALL_TAB_COLUMNS  WHERE OWNER = ? AND TABLE_NAME = ?"
    			+ "union "
    			+ "select  COLUMN_NAME FROM sys.ALL_TAB_COLUMNS  WHERE OWNER = ? AND TABLE_NAME in( select TABLE_NAME from ALL_SYNONYMS where SYNONYM_NAME =  ? )";

    			
    	List<String> tableColumnList = new ArrayList<String>();
    	ResultSet rs = null;
    	PreparedStatement preparedStatement = null;
    	try
    	{
	    	preparedStatement = connection.prepareStatement(query);
	    	
	    	preparedStatement.setString(1, cdrDBSchema.toUpperCase());    	
	    	preparedStatement.setString(2, tableName.toUpperCase());    	
	    	preparedStatement.setString(3, cdrDBSchema.toUpperCase());    	
	    	preparedStatement.setString(4, tableName.toUpperCase());    	
			rs = preparedStatement.executeQuery();
			
			while (rs.next())
			{
				tableColumnList.add(rs.getString("COLUMN_NAME"));
			}
    	}
    	catch(SQLException e)
    	{
    		throw e;
    	}
    	finally
    	{
    		if( rs != null )
    			rs.close();
    		if(preparedStatement != null)
    			preparedStatement.close();
    	}
		return tableColumnList;
    }
}
