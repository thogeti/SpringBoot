package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class RxStatApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(RxStatApi.class);
	//APR.DPHARM.023  TE97_023 changes start
	private final static String UPDATESQL = "UPDATE RXSTAT SET FILLSTATKEY = ?, FILLSTATEFFDT = " 
	+ CommonUtil.getPsToDateFunctionStr() 
	+ ",SUBFILLSTATKEY=?  WHERE RXSTATKEY = ? AND RXKEY = ?" ;//APR.DPHARM.023  TE97_023 changes start,method parameter added  for status sub code end
	//APR.DPHARM.023  TE97_023 changes end
	private final static String QUERYSQL = "SELECT RXSTATKEY FROM RXSTAT WHERE RXKEY = ?";
	
	private final static String MERGER_RTSTATSQL="MERGE INTO RXSTAT a" + 
			"      USING (SELECT  ?  FILLSTATKEY," + 
			"                     ?  RXKEY," + 
			"                     ?  FILLSTATEFFDT," + 
			"                     ?  SUBFILLSTATKEY" + 
			"             FROM dual) b" + 
			"      ON ( a.RXKEY     = b.RXKEY)" + 
			"      WHEN MATCHED THEN" + 
			"        UPDATE SET FILLSTATKEY    = b.FILLSTATKEY," + 
			"                   FILLSTATEFFDT  = b.FILLSTATEFFDT," + 
			"                   SUBFILLSTATKEY = b.SUBFILLSTATKEY" + 
			"      WHEN NOT MATCHED THEN" + 
			"           insert (RXSTATKEY," + 
			"                   FILLSTATKEY," + 
			"                   RXKEY," + 
			"                   FILLSTATEFFDT," + 
			"                   SUBFILLSTATKEY)" + 
			"           values (RXSTAT_SEQ.nextval," + 
			"                   b.FILLSTATKEY," + 
			"                   b.RXKEY," + 
			"                   b.FILLSTATEFFDT," + 
			"                   b.SUBFILLSTATKEY)" ;
			
	
	
	
	//APR.DPHARM.023  TE97_023 changes start,method parameter added  for status sub code start
			private final static String INSERTSQL = "INSERT INTO RXSTAT (RXSTATKEY, FILLSTATKEY, RXKEY, FILLSTATEFFDT,SUBFILLSTATKEY) VALUES (? , ? , ? ,"
					+ CommonUtil.getPsToDateFunctionStr() 
					+",?)";//APR.DPHARM.023  TE97_023 changes start,method parameter added  for status sub code end

			//APR.DPHARM.023  TE97_023 changes start,method parameter added  for status sub code start
			//APR.DPHARM.023  TE97_023 changes start,method parameter added  for status sub code start
			public void upsertRxStat(Connection connection, Long rxKey, RxFillStatus rxFillStatus, XMLGregorianCalendar rxEffectiveDate,RxFillStatus rxFillStatusSubCode) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException  
			{
				try {
					if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertRxStat, rxKey : " + rxKey);}
					insertRxState(connection, rxEffectiveDate, rxKey, rxFillStatus,rxFillStatusSubCode);
					
				} catch (SQLException e) {
					throw e;
				}
				finally
				{
					super.close();
					if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertRxStat, rxKey : " + rxKey);}
				}
			}
	// APR.DPHARM.023  TE97_023 fillsubstatus code changes end

			// APR.DPHARM.023  TE97_023 fillsubstatus code changes start

			private Long insertRxState(Connection connection, XMLGregorianCalendar rxEffectiveDate, Long rxKey, RxFillStatus rxFillStatus,RxFillStatus rxFillSubStatus) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException
			{
			//	long rxStatKey = IdGenerator.generate(connection, "RxStat");
				if (logger.isInfoEnabled())  {logger.info( " RxState started rxstat table with rxKey " + rxKey +",rxFillStatus" +rxFillStatus+",rxFillSubStatus"+rxFillSubStatus);}
				ps = connection.prepareStatement(MERGER_RTSTATSQL);
				Long rxFillStatTypeKey = null;
				Long rxFillSubStatTypeKey = null;// APR.DPHARM.023  TE97_023 fillsubstatus code changes start
				if(rxFillStatus !=null){
					rxFillStatTypeKey = getKeyFromCode(LT_RXFILLSTATTYP, rxFillStatus.value());
				if(rxFillSubStatus !=null){
					rxFillSubStatTypeKey = getKeyFromCode(LT_RXFILLSTATTYP, rxFillSubStatus.value());// APR.DPHARM.023  TE97_023 fillsubstatus code changes start
				}
				}
				//setPsLongParam(1, rxStatKey);
				setPsLongParam(1, rxFillStatTypeKey);
				setPsLongParam(2, rxKey);
				String rxEffectiveDateString = CommonUtil.toTimestampStr(rxEffectiveDate);
				setPsStringParam(3, rxEffectiveDateString);
				setPsLongParam(4, rxFillSubStatTypeKey);// APR.DPHARM.023  TE97_023 fillsubstatus code changes start

				Long res = (long) ps.executeUpdate();
				
				if (logger.isInfoEnabled())  {logger.info(res + " RxState has been inserted into rxstat table with rxKey " + rxKey );}
				
				return res;
			}
	private Long updateRxState(Connection connection, XMLGregorianCalendar rxEffectiveDate, Long rxStatKey, Long rxKey, RxFillStatus rxFillStatus, RxFillStatus rxFillSubStatus) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException 
	{
		ps = connection.prepareStatement(UPDATESQL);
		Long rxFillStatTypeKey = null;
		Long rxFillSubStatTypeKey = null;// APR.DPHARM.023  TE97_023 fillsubstatus code changes start
		if (rxFillStatus != null){
			rxFillStatTypeKey = getKeyFromCode(LT_RXFILLSTATTYP, rxFillStatus.value());
		if(rxFillSubStatus != null){
			rxFillSubStatTypeKey = getKeyFromCode(LT_RXFILLSTATTYP, rxFillSubStatus.value());// APR.DPHARM.023  TE97_023 fillsubstatus code changes start
		
		}
		}
		setPsLongParam(1, rxFillStatTypeKey);
		String rxEffectiveDateString = CommonUtil.toTimestampStr(rxEffectiveDate);
		setPsStringParam(2, rxEffectiveDateString);
		setPsLongParam(3, rxFillSubStatTypeKey);// APR.DPHARM.023  TE97_023 fillsubstatus code changes modified index
		setPsLongParam(4, rxStatKey);// APR.DPHARM.023  TE97_023 fillsubstatus code changes modified index
		setPsLongParam(5, rxKey);// APR.DPHARM.023  TE97_023 fillsubstatus code changes modified index

		int res = ps.executeUpdate();
		
		if (logger.isInfoEnabled())  {logger.info(res + " RxState has been inserted into rxstat table with rxstatKey " + rxStatKey );}
		
		return rxStatKey;
	}
}
