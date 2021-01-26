package ca.sdm.cdr.jdbc.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class QueryLoaderSingleton {
	
	private static Logger logger = LogManager.getLogger(QueryLoaderSingleton.class);
	
	private static volatile QueryLoaderSingleton instance = null;
	
	private static HashMap<String, String> queryMap = null;
	

	/**
	 * Disable direct instantiation via constructor.
	 */
	private QueryLoaderSingleton() {}
	
	
	/**
	 * Public instantiation.
	 * 
	 * @return	QueryLoaderSingleton instance.
	 */
	public static QueryLoaderSingleton getInstance() {
		if (instance==null) {
			synchronized(QueryLoaderSingleton.class) {
				if (instance==null) {
					instance = new QueryLoaderSingleton();
					queryMap = new HashMap<String, String>();
				}
			}
		}
		return instance;
	}

	

	/**
	 * Get SQL contents from query file.
	 *  
	 * @param queryFileName	Query file name.
	 * @return				SQL contents from specified query file.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public String getSqlFromFile(String queryFileName) throws IOException, SQLException {
		String cachedSql = queryMap.get(queryFileName);
		if (!StringUtil.isEmpty(cachedSql)) {
			return cachedSql; 
		}
		if (logger.isInfoEnabled())  {logger.info("Loading SQL file '" + queryFileName + "'. ");}		
		InputStream is = QueryLoaderSingleton.class.getClassLoader().getResourceAsStream(queryFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer raw = new StringBuffer();
		String line = null;
		while ((line=reader.readLine())!=null) {
			raw.append(line + "\n");
		}
		cachedSql = raw.toString();		
		if (cachedSql==null || cachedSql.trim().length()<1)
			throw new SQLException("SQL cannot be empty");
		queryMap.put(queryFileName, cachedSql);
		return cachedSql;
	}

	
	
}
