package ca.sdm.cdr.jdbc.query.api;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefillDispenseGet extends CDRGet {
	
	private static Logger logger = LogManager.getLogger(RefillDispenseGet.class);
	
	
	///get latest TXDRX record within 48 Hours .
		private final static String GET_TXDRX_48="select TXDRXKEY,LSTUPDTIMESTAMP"
				+ " from TXDRX where RXKEY  = ? and STORENUM = ? and EVENTNM = 'RefillSuccess' "
				+ " and TXKEY is null and LSTUPDTIMESTAMP > (SYSDATE - INTERVAL '48' hour) "
				+ " order by LSTUPDTIMESTAMP desc fetch first 1 rows only";
		
		
		/***
		 * 
		 * @param connection
		 * @param prescriptionNumber
		 * @param storeNum
		 * @param eventName
		 * @return
		 * @throws SQLException
		 */
		public Long getDispenseWithIn48Frame(Connection connection,Long rxkey,String storeNum,String eventName) throws SQLException{
			Long timer = System.currentTimeMillis();
			Long txdrxkey=null;
			try {
				if (logger.isInfoEnabled())  {logger.info("StartApiCall: RefillDispenseGet.getDispenseWithIn48Frame. PrescriptionNumber : " + rxkey +", StoreNum :" + storeNum +", EventName :"+ eventName);}
			if (logger.isTraceEnabled())  {logger.trace("RefillDispenseGet.getDispenseWithIn48Frame :sqlQuey \n" + GET_TXDRX_48 );}
			preparedStatement = connection.prepareStatement(GET_TXDRX_48);
			preparedStatement.setLong(1, rxkey);
			preparedStatement.setString(2, storeNum);
			Long querytimer = System.currentTimeMillis();
			if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery : RefillDispenseGet.getDispenseWithIn48Frame. rxkey : " + rxkey +", StoreNum :" + storeNum +", EventName :"+ eventName+ ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
			resultSet = preparedStatement.executeQuery();
			if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery : RefillDispenseGet.getDispenseWithIn48Frame. rxkey : " + rxkey +", StoreNum :" + storeNum +", EventName :"+ eventName+ ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
			if (resultSet.next()) {
			txdrxkey=getLong("txdrxkey");
			}
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: RefillDispenseGet.getDispenseWithIn48Frame. PrescriptionNumber : " + rxkey +", StoreNum :" + storeNum +", EventName :"+ eventName+ ". Total time is : " + (System.currentTimeMillis() - timer) + " ms") ;}
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally
			{
				super.close();
			}
			return txdrxkey;
			
		}
		}
