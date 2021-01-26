package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;

public class CDRUpsert {
	public ResultSet rs;
	public PreparedStatement ps;
	public String jndiVersion;
	public CDRUpsert() {
		jndiVersion = TableCacheSingleton.JNDI_VERSION; 
	}

	public void close() {
		if (rs != null)
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			};

		if (ps != null)
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public Long getKeyFromCode(String table, String code) throws KeyNotFoundFromTableCacheException, NamingException, SQLException, IOException {
		return TableCacheSingleton.getInstance(jndiVersion).getKeyFromCode(table, code);
	};

	public void setPsStringParam(int paramIndex, String val) throws SQLException {
		if (val!=null) {
			ps.setString(paramIndex, val);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}
	
	public void setPsLongParam(int paramIndex, String numStr) throws SQLException {
		Long numVal = null;
		if (numStr!=null) try {
			numVal = new Long(numStr);
		} catch (Exception parseEx) {
			
		}
		setPsLongParam(paramIndex, numVal);
	}

	
	public void setPsLongParam(int paramIndex, Long num) throws SQLException {
		if (num!=null) {
			ps.setLong(paramIndex, num);
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}
	
	

	public void setPsDoubleParam(int paramIndex, Double num) throws SQLException {
		if (num!=null) {
			ps.setDouble(paramIndex, num);
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}

	public void setPsBigDecimalParam(int paramIndex, BigDecimal num) throws SQLException {
		if (num!=null) {
			ps.setFloat(paramIndex, num.floatValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}	

	public void setPsLongParam(int paramIndex, BigInteger num) throws SQLException {
		if (num!=null) {
			ps.setLong(paramIndex, num.longValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}	
	
	public void setPsIntParam(int paramIndex, Integer num) throws SQLException {
		if (num !=null)
		{
			ps.setInt(paramIndex, num);
		}
		else
			ps.setNull(paramIndex, Types.NUMERIC);
	}
		
	public void setPsBooleanParam(int paramIndex, Boolean flag) throws SQLException {
		if (flag!=null) {
			ps.setString (paramIndex, CommonUtil.convertBooleanToYesNoFlag(flag.booleanValue() ) );
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	
	
	public void setPsXMLGregorianCalendarParam(int paramIndex, XMLGregorianCalendar xmlCalendar) throws SQLException {
		if (xmlCalendar!=null) {
			String timestampStr = CommonUtil.toTimestampStr(xmlCalendar);
			ps.setString (paramIndex, timestampStr);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	

	public void setPsXMLGregorianCalendarToDateParam(int paramIndex, XMLGregorianCalendar xmlCalendar) throws SQLException {
		if (xmlCalendar!=null) {
			String timestampStr = CommonUtil.toDateStr(xmlCalendar);
			ps.setString (paramIndex, timestampStr);
		} else {
			ps.setNull(paramIndex, Types.VARCHAR);
		}
	}	

	public void setPsLongParam(int paramIndex, Integer numVal) throws SQLException {
		if (numVal!=null) {
			ps.setLong(paramIndex, numVal.longValue());
		} else {
			ps.setNull(paramIndex, Types.NUMERIC);
		}
	}
}
