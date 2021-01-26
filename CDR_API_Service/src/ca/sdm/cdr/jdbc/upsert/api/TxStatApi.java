package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.TxFillStatus;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class TxStatApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(TxStatApi.class);
	private final static String TXSTATSQL = "SELECT TXSTATKEY FROM TXSTAT WHERE TXKEY = ?";
	private final static String INSERTTXSTATSQL = "INSERT INTO TXSTAT (TXSTATKEY, TXFILLSTATTYPKEY, TXKEY) VALUES (? , ? , ?)";
	private final static String UPDATETXSTATSQL = "UPDATE TXSTAT SET TXFILLSTATTYPKEY = ? WHERE TXSTATKEY = ? AND TXKEY = ?";
	
	public void upsertTxStat(Connection connection, Long txKey, TxFillStatus txFillStatus) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException  
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertTxStat txKey : " + txKey);}
			
			Long txStatKey = null;		
			ps = connection.prepareStatement(TXSTATSQL);
			setPsLongParam(1, txKey);		
			
			rs = ps.executeQuery();
			if (rs.next()) {
				txStatKey = rs.getLong("txstatkey".toUpperCase()) ;
			}
			
			super.close();
			if(txStatKey != null)
			{
				updateTxState(connection, txStatKey, txKey, txFillStatus);
			}
			else
			{
				insertTxState(connection, txKey, txFillStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertTxStat txKey : " + txKey);}
		}
	}
	
	private Long insertTxState(Connection connection, Long txKey, TxFillStatus txFillStatus) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException
	{
		long txStatKey = IdGenerator.generate(connection, "TxStat");
		ps = connection.prepareStatement(INSERTTXSTATSQL);
		
		Long txFillStatTypeKey = null;
		if (txFillStatus != null)
			txFillStatTypeKey = getKeyFromCode(LT_TXFILLSTATTYP, txFillStatus.value());
		setPsLongParam(1, txStatKey);
		setPsLongParam(2, txFillStatTypeKey);
		setPsLongParam(3, txKey);

		ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug(" TxState has been inserted into txstat table with txstatKey " + txStatKey );}
		return txStatKey;
	}

	private Long updateTxState(Connection connection, Long txStatKey, Long txKey, TxFillStatus txFillStatus) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException
	{
		ps = connection.prepareStatement(UPDATETXSTATSQL);
		Long txFillStatTypeKey = null;
		if (txFillStatus != null)
			txFillStatTypeKey = getKeyFromCode(LT_TXFILLSTATTYP, txFillStatus.value());
		setPsLongParam(1, txFillStatTypeKey);
		setPsLongParam(2, txStatKey);
		setPsLongParam(3, txKey);

		ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug(" TxState has been inserted into txstat table with txstatKey " + txStatKey);}
		return txStatKey;
	}
}
