package ca.sdm.cdr.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sdm.cdr.exception.CDRInternalException;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL46 2017-12-21   NTT Data     Vlad Eidinov  SQL Optimization to improve 
                                             GetPatientByQueryCriteria performance
*/



public class ResultSetWrapper {

	
	public static Long getLong(ResultSet rs , String columnLabel) throws SQLException
	{
		 String value = rs.getString(columnLabel.toUpperCase()) ;
		 return (value != null ) ? new Long(value) : null;
	}
	
	

	public static Integer getInt(ResultSet rs, String tableAlias, String colName) throws SQLException {
		Integer val = null;
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		val = rs.getInt(tableAlias + prefixedColName.toUpperCase());
		return val;
	}	
	
	public static BigDecimal getBigDecimal(ResultSet rs, String tableAlias, String colName) throws SQLException {
		BigDecimal val = null;
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		val = rs.getBigDecimal(tableAlias + prefixedColName.toUpperCase());
		return val;
	}		
	
	public static BigInteger getBigInteger(ResultSet rs, String tableAlias, String colName) throws SQLException {
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		String val = rs.getString(tableAlias + prefixedColName.toUpperCase());
		if( val != null )
			return new BigInteger(val);
		return null;
	}
	public static Long getLong(ResultSet rs, String tableAlias, String colName) throws SQLException {
		Long val = null;
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		val = rs.getLong(tableAlias + prefixedColName.toUpperCase());
		return val;
	}	
	
	
	public static Long getKey(ResultSet rs, String tableAlias, String colName) throws SQLException {
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		return getLong(rs , tableAlias+prefixedColName);
	}		
	
	
	public static XMLGregorianCalendar getCalendar(ResultSet rs, String tableAlias, String colName) throws ParseException, DatatypeConfigurationException, SQLException {
		XMLGregorianCalendar val = null;
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		val = CommonUtil.getXMLGregorianCalendar(rs.getTimestamp(tableAlias + prefixedColName.toUpperCase()) , true);
		return val;
	}	
	
	public static boolean getBoolean(ResultSet rs, String tableAlias, String colName) throws CDRInternalException, SQLException {
		Boolean val = null;
		String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		String flagStr = getString(rs, tableAlias, prefixedColName.toUpperCase());
		val = (flagStr!=null) ? CommonUtil.convertYesNoFlagToBoolean(flagStr) : false;
		return val;
	}	
	
	

	// VL46 Started
	public static String getString(ResultSet rs, String tableAlias, String colName) throws SQLException {
		   String val = null;
		   
		   if ( !(tableAlias == null || tableAlias.length() == 0) && 
			    !(tableAlias.endsWith("_")) ) {
			   
		          String prefixedColName = colName.startsWith("_") ? colName : "_" + colName;
		          val = rs.getString(tableAlias.toUpperCase() + prefixedColName.toUpperCase());
		   } else {
			    if ( (tableAlias.endsWith("_")) ) {
			    	  val = rs.getString( tableAlias.toUpperCase() + colName.toUpperCase() );
			    } else {
			          val = rs.getString(colName.toUpperCase());
			    }  
		   }
		   return val;
	}
	// VL46 Ended
	
	
	public static String getString(ResultSet rs, String colName) throws SQLException {
		String val = null;
		val = rs.getString(colName.toUpperCase());
		return val;
	}
	
}
