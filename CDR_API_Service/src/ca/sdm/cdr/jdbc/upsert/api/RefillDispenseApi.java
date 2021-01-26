package ca.sdm.cdr.jdbc.upsert.api;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyBusinessEventEnum;

public class RefillDispenseApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(RefillDispenseApi.class);
	  //FFT
	
    private final static String UPDATE_LATEST_DISPENSE="update TXDRX set TXKEY = ? where TXDRXKEY = ?";
	
    	
  	private final static String UPDATE_TXDRX="update TXDRX set  EVENTNM =?,RSNCD=?,LSTUPDTIMESTAMP="+ CommonUtil.getPsToDateFunctionStr()
  			+ " where CORRID = ?  and RXNUM  = ? and STORENUM = ?";
  	
	//FFT    


	/**
	 * Attempt to update a Prescription instance, which is matched by prescriptionKey.
	 * 
	 * @param correlationID		correlationID.
	 * @param prescriptionNumber prescriptionNumber.
	 * @param storeNum	storeNum.
	 * @param reason 
	 * @param eventName 
	 * 
	 * 
	 * @return					.
	 * 
	 * @throws SQLException
	 * @throws NamingException 
	  *///FFT
	public Long updateTXDX(Connection connection,String correlationID,Integer prescriptionNumber, String storeNum, PharmacyBusinessEventEnum eventName, String reason,XMLGregorianCalendar consumerSendTime) throws SQLException, NamingException {
	if (logger.isTraceEnabled())  {logger.trace("updateTXDX :sqlQuey \n" + UPDATE_TXDRX );}
	Long res =0L;
	Long timer = System.currentTimeMillis();
	if (logger.isInfoEnabled())  {logger.info("StartApiCall: RefillDispenseApi.updateTXDX. correlationID : " + correlationID +", prescriptionNumber :" + prescriptionNumber +", eventName :"+ eventName.name()+",consumerSendTime"+consumerSendTime);}
	try{
	ps = connection.prepareStatement(UPDATE_TXDRX);
		setPsStringParam(1, eventName.value());
		setPsStringParam(2, reason);
		setPsStringParam(3, CommonUtil.toTimestampStr(consumerSendTime));
		setPsStringParam(4, correlationID);
		setPsLongParam(5, prescriptionNumber);
		setPsStringParam(6, storeNum);
		res = (long) ps.executeUpdate();
		
		if (logger.isInfoEnabled())  {logger.info("Total updateTXDX intances updated: " + res + ". correlationID: " + correlationID + ", prescriptionNumber: '" + prescriptionNumber + "', storeNum: '" + storeNum + "'.");
		logger.info("EndApiCall: RefillDispenseApi.updateTXDX. correlationID : " + correlationID +", prescriptionNumber :" + prescriptionNumber +", eventName :"+ eventName.name()+",consumerSendTime"+consumerSendTime+ ". Total time is : " + (System.currentTimeMillis() - timer) + " ms") ;}
		} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	}finally
	{
		super.close();
	}
	return res;
	}

/***
 * updateLatestRefillDispense
 * @param connection
 * @param rxkey
 * @param txkey
 * @param txdrxkey
 * @return
 * @throws SQLException
 */
	public  Long updateLatestRefillDispense(Connection connection,Long txkey,Long txdrxkey) throws SQLException{
		Long res =0L;
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: RefillDispenseGet.updateLatestRefillDispense. TxKey :" + txkey +", TxdrxKey :"+ txdrxkey);}
		try{
		if (logger.isTraceEnabled())  {logger.trace("updateLatestRefillDispense :sqlQuey \n" + UPDATE_LATEST_DISPENSE );}
		ps = connection.prepareStatement(UPDATE_LATEST_DISPENSE);
		//ps.setLong(1,rxkey );
		ps.setLong(1,txkey );
		ps.setLong(2,txdrxkey );
		res= (long) ps.executeUpdate();
		
		if (logger.isInfoEnabled())  {logger.info("Total Txdrxkey intances updated: " + res + ". TxKey: '" + txkey + "',Txdrxkey: '" + txdrxkey + "'.");
		logger.info("EndApiCall: RefillDispenseGet.getDispenseWithIn48Frame.  TxKey :" + txkey +", Txdrxkey :"+ txdrxkey+ ". Total time is : " + (System.currentTimeMillis() - timer) + " ms") ;}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally
		{
			super.close();
		}
		return res;
	}
}
