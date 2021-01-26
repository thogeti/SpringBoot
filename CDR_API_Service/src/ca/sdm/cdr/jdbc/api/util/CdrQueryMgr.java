package ca.sdm.cdr.jdbc.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 
 * @author 107659
 *
 */
public class CdrQueryMgr {
	
	private String queryFileName = null;
	private String sql = null;
	private String commentedSql = null;
	
	private String selectClause = "";
	private String fromClause = "";
	private String whereClause = "";
	private String orderByClause = "";
	
	private boolean orderByGenerated = false;

	
	
	/**
	 * 
	 * @param queryName
	 * @throws IOException
	 * @throws SQLException
	 */
	public CdrQueryMgr(String queryFileName) throws IOException, SQLException {
		this.queryFileName = queryFileName;
		QueryLoaderSingleton qLoader = QueryLoaderSingleton.getInstance();
		String cachedSql = qLoader.getSqlFromFile(queryFileName);
		this.commentedSql = cachedSql;
		String sqlStr = getUncommentedSql(commentedSql);
		this.sql = sqlStr;
	}
	
	/**
	 * 
	 * @param queryName
	 * @param commentedSql
	 */
	public CdrQueryMgr(String queryName, String commentedSql) {
		this.queryFileName = queryName;
		this.commentedSql = commentedSql;
		String sqlStr = getUncommentedSql(commentedSql);
		this.sql = sqlStr;
	}
	

	
	/**
	 * 
	 * @param commentedSql
	 * @return
	 */
	public static String getUncommentedSql(String commentedSql) {
		StringBuffer sb = new StringBuffer();
		String[] allLines = commentedSql.split("[\\n]");
		// handle SQL line comments (e.g. "-- comment")
		for (String xLine : allLines) {
			int lineCommIndex = xLine.indexOf("--");
			if (lineCommIndex >= 0) {
				xLine = xLine.substring(0, lineCommIndex);
			}
			if (xLine.trim().length() < 0) 
				continue; // skip comment	
			sb.append(xLine + "\n");
		}		
		String sqlStr = sb.toString();
		return sqlStr;
	}	
	

	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String getTableAliasPairsStr() throws SQLException, Exception {
		StringBuffer result = new StringBuffer("");
		int selectClIndex = sql.toUpperCase().indexOf("SELECT");
		int fromClIndex = sql.toUpperCase().indexOf("FROM");
		int whereClIndex = sql.toUpperCase().indexOf("WHERE");
		int orderByClIndex = sql.toUpperCase().indexOf("ORDER BY");
		if (selectClIndex < 0) {
			throw new SQLException("SELECT clause is required.");
		}		
		this.selectClause = sql.substring(selectClIndex, fromClIndex).trim();
		if (fromClIndex < 0) {
			throw new SQLException("FROM clause is required.");
		}
		if (whereClIndex < 0) {
			throw new SQLException("WHERE clause required");
		}		
		this.fromClause = sql.substring(fromClIndex, whereClIndex).trim();
		if (orderByClIndex < 0) {
			this.whereClause = sql.substring(whereClIndex).trim();
			this.orderByClause = "";
		} else {
			this.whereClause = sql.substring(whereClIndex, orderByClIndex).trim();
			this.orderByClause = sql.substring(orderByClIndex).trim();
		}

		String[] srcLines = fromClause.split("[\\n]");
		StringBuffer orderByTmp = new StringBuffer("\n\nORDER BY \n\n");
		for (int ii=0; srcLines!=null && ii<srcLines.length; ii++) {
			String iLine = srcLines[ii];
			if (iLine.trim().length()<1)
				continue;
			String tableAliasPairStr = null;
			int fromKeyWordIndex = iLine.toUpperCase().indexOf("FROM");
			if (fromKeyWordIndex >= 0) {
				tableAliasPairStr = iLine.substring(fromKeyWordIndex + "FROM".length()).trim();
			}
			int joinClIndex = iLine.toUpperCase().indexOf("JOIN");
			if (joinClIndex >= 0) {
				int onClIndex = iLine.toUpperCase().indexOf("ON");
				if (onClIndex < 0)
					throw new SQLException("JOIN clause requires 'ON' expression on the same line.");
				tableAliasPairStr = iLine.substring(joinClIndex + "JOIN".length(), onClIndex).trim();				
			}
			String[] tableAliasPair = tableAliasPairStr.split("\\s+");
			String tableStr = tableAliasPair[0].toUpperCase();
			String aliasStr = null;
			try {
			   aliasStr = tableAliasPair[1];
			} catch (Exception ex) {
			   aliasStr = tableStr;
			}
			
			if (isOrderByGenerated()) {
				// Generate ORDER BY dynamically
				String simpleTableName = getSimpleTableName(tableStr);
				String likelyPkName = simpleTableName + "KEY";
				String orderSep = (result.toString().trim().length() > 0) ? "\n, " : "\n";
				orderByTmp.append(orderSep + aliasStr + "." + likelyPkName);
			}			
			String tableAliasComma = tableStr + "," + aliasStr;
			String sep = (result.toString().trim().length() > 0) ? "\n" : "";
			result.append(sep + tableAliasComma);
		}
		
		if (this.isOrderByGenerated()) {
			this.orderByClause = orderByTmp.toString();
		}
		return result.toString();	
	}
	
	/**
	 * 
	 * @param qTableName
	 * @param srcSchema
	 * @return
	 */
	public static String getSimpleTableName(String qTableName) {
		if (qTableName==null)
			return "";
		int dotIndex = qTableName.indexOf(".");
		if (dotIndex >= 0) {
			try {
				String simpleTableName = qTableName.substring(dotIndex + 1);
				return simpleTableName;
			} catch (Exception ex) {
				
			}
		}
		return qTableName;
	}
	
	
	/**
	 * 
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
    public static String getFullTableName(String schemaName, String tableName) {
    	String prefix = (schemaName!=null && schemaName.trim().length() > 0) ? schemaName.toUpperCase() + "." : "";
    	tableName = (tableName!=null) ? tableName.toUpperCase() : "";
    	return prefix + tableName;
    } 	
	
	
	/**
	 * 
	 * @param targetSchema
	 * @return
	 */
	public String[][] getTableAliasArray(String targetSchema) {
		try {	
			String tableAliasPairsStr = getTableAliasPairsStr();
			String[] rows = tableAliasPairsStr.split("[\\n]");			
			String[][] multi = new String[rows.length][];
			for (int ii=0; ii<multi.length; ii++) {
				multi[ii] = new String[2];	
				String[] rowParts = rows[ii].split(",");
				String table = rowParts[0];
				if (table.trim().length() < 1)
					continue;
				table = getSimpleTableName(table);
				String alias = rowParts.length > 1 ? rowParts[1] : table;
				multi[ii][0] = table;
				multi[ii][1] = alias;
			}
			return multi;
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[][] getTableAliasArray() {
		return getTableAliasArray("");
	}
	
	
	/**
	 * Append content to specified file.
	 * 
	 * @param outFile
	 * @param content
	 */
	public static void appendToFile(File outFile, String content) {
		if (outFile==null)
			return;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outFile, true);
			fos.write(content.getBytes());
			fos.close();	
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (fos!=null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	public String getQueryFileName() {
		return queryFileName;
	}


	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getCommentedSql() {
		return commentedSql;
	}

	public String getSelectClause() {
		return selectClause;
	}

	public String getFromClause() {
		return fromClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public boolean isOrderByGenerated() {
		return orderByGenerated;
	}

	public void setOrderByGenerated(boolean orderByGenerated) {
		this.orderByGenerated = orderByGenerated;
	}

	
	
}
