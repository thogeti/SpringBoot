package ca.sdm.cdr.jdbc.api.subscription;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientAlreadySubscribedException;
import com.sdm.cdr.exception.api.PatientSubscriptionNotFoundException;
import com.sdm.cdr.exception.api.RxAlreadySubscribedException;
import com.sdm.cdr.exception.api.RxSubscriptionNotFoundException;
import com.sdm.cdr.exception.api.SourceSystemNotValidException;

import ca.sdm.cdr.common.constant.Constants;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.upsert.api.CDRUpsert;
import ca.sdm.cdr.jdbc.upsert.api.NotificationApi;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.SourceSystem;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.Subscription;
import ca.shoppersdrugmart.rxhb.drx.subscriptionservice.SubscribeToPatient;
import ca.shoppersdrugmart.rxhb.drx.subscriptionservice.SubscribeToRx;
import ca.shoppersdrugmart.rxhb.drx.subscriptionservice.UnsubscribeToPatient;
import ca.shoppersdrugmart.rxhb.drx.subscriptionservice.UnsubscribeToRx;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project
*/

public class SubscriptionAPI extends CDRUpsert {
	
	private final static Logger logger = LogManager.getLogger("SubscriptionService");
	private Long patientKey = null;
	private String patientSubscriptionKey = null ;
	
	private Long rxKey = null ;
	private String rxSubscriptionKey = null;
	
	private final static String PATIENTSUBSCRIPTIONSQL =	
			" SELECT PTNT.PTNTKEY , SUBS.PTNTSUBSCRPTNSRCESYSKEY "
			+ "FROM PTNT PTNT "
			+ "LEFT OUTER JOIN PTNTSUBSCRPTNSRCESYS SUBS ON PTNT.PTNTKEY = SUBS.PTNTKEY AND SUBS.SRCESYSKEY = ? "
			+ "WHERE PTNT.CNSMRID = ? AND PTNT.STORENUM = ? " ;
	
	private final static String PRESCRIPTIONSUBSCRIPTIONSQL = 
			"SELECT RX.RXKEY  , RXSUB.RXSUBSCRPTNSRCESYSKEY "
			+ "FROM RX RX "
			+ "LEFT OUTER JOIN RXSUBSCRPTNSRCESYS RXSUB ON RX.RXKEY = RXSUB.RXKEY "
			+ "AND RXSUB.SRCESYSKEY = ? "
			+ "WHERE RX.RXNUM = ? AND RX.STORENUM = ?";
	
	private final static String DELETERXSQL = "DELETE FROM RXSUBSCRPTNSRCESYS WHERE RXSUBSCRPTNSRCESYSKEY = ? ";
	
	//TE97 added added for linked rx Subscription.start
		private final static String INSERTSUBSCRIBESQL="insert into RXSUBSCRPTNSRCESYS select RXSUBSCRPTNSRCESYS_SEQ.NEXTVAL as RXSUBSCRPTNSRCESYSKEY,"
				+ "origRx.SRCESYSKEY, ? as linkRx_RXKEY from RXSUBSCRPTNSRCESYS origRx where origRx.RXKEY = ? and origRx.SRCESYSKEY not in (select linkRx.SRCESYSKEY"
				+ " from RXSUBSCRPTNSRCESYS linkRx  where linkRx.RXKEY = ?)";

		//TE97 added added for linked rx Subscription.end
	
//VL99 Started
//	private final static String PTNTSUBSQL = "SELECT PTNTSUBSCRPTNSRCESYSKEY FROM PTNTSUBSCRPTNSRCESYS WHERE PTNTKEY = ? AND  SRCESYSKEY = ? ";
	private final static String PTNTSUBSQL =
			     "select distinct s.SRCESYSCD        "
			   + "  from PTNTSUBSCRPTNSRCESYS p,     "
			   + "       SRCESYS              s      "
			   + " where p.PTNTKEY = ?               "
			   + "   and p.SRCESYSKEY = s.SRCESYSKEY ";


//	private final static String RXSUBSQL = "SELECT RXSUBSCRPTNSRCESYSKEY FROM RXSUBSCRPTNSRCESYS WHERE RXKEY = ? AND SRCESYSKEY = ?";
	private final static String RXSUBSQL =
			     "SELECT distinct s.SRCESYSCD  " 
			   + "  FROM RXSUBSCRPTNSRCESYS r, "
			   + "       SRCESYS            s  "
			   + " WHERE r.RXKEY = ?           " 
			   + "   and r.SRCESYSKEY = s.SRCESYSKEY ";
//VL99 Ended	
	
	
	private final static String INSERTRXSQL = " INSERT INTO RXSUBSCRPTNSRCESYS (RXSUBSCRPTNSRCESYSKEY, SRCESYSKEY, RXKEY) VALUES ( ?,?,? ) ";
	private final static String INSERTPTNTSQL = " INSERT INTO PTNTSUBSCRPTNSRCESYS (PTNTSUBSCRPTNSRCESYSKEY, SRCESYSKEY, PTNTKEY) VALUES ( ?,?,? ) ";
	private final static String DELETEPTNTSQL = "DELETE FROM PTNTSUBSCRPTNSRCESYS WHERE SRCESYSKEY = ? AND PTNTKEY = ?  ";
	private final static String DELETENOTIFSQL="delete from notification "
			 +"where ENTITYKEY  = ?"
			   +" and EVENTNAME  = 'RxData'"
			   +" and ENTITYTYPE = 'Rx' "
			  +" and NOTIFICATIONTYPE in ('Rx_AdminStartDate', 'Rx_AdminStopDate')"
			  +" and 0 = (select sum(tmp_tbl.subscrCount) subscrCount"
			             +" from (select count(*) subscrCount"
			              +" from ptntsubscrptnsrcesys s,rx r"
			                    +" where r.rxkey = ?"
			                     +" and r.ptntkey = s.ptntkey"
			                     +" union all"
			                     +" select count(*) subscrCount"
			                      +" from rxsubscrptnsrcesys s,rx r"
			                      +" where r.rxkey = ?"
			                        +" and r.rxkey = s.rxkey) tmp_tbl)";
	
/*	public boolean isPatientSubscribed(Connection connection, String patientId, Integer storeNumber ) throws SourceSystemNotValidException, PatientSubscriptionNotFoundException, SQLException, NamingException, IOException 
	{
		   String storeNumberString = (storeNumber!=null) ? CommonUtil.createStoreLeadingZeros(storeNumber) : "";
		   return isPatientSubscribed(connection, patientId, storeNumberString );
	}
*/	
	public boolean isPatientSubscribed(Connection connection, String patientId, String storeNumber, String SrceSysName) throws SourceSystemNotValidException, SQLException, PatientSubscriptionNotFoundException, NamingException, IOException {  
		try {
			boolean isSubscribed = false ;
			Long SrceSysKey = null;
			
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, SrceSysName);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for " + SrceSysName);}
				throw new SourceSystemNotValidException(SrceSysName);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			if (logger.isInfoEnabled())  {logger.info("Start isPatientSubscribed for SrceSysName: " + SrceSysName + " storeNumber: " + storeNumber + " PatientId: " + patientId);}
			
			ps = connection.prepareStatement(PATIENTSUBSCRIPTIONSQL);
			setPsLongParam(1, SrceSysKey);  
			setPsStringParam(2, patientId); 
			setPsStringParam(3, storeNumber); 
			rs = ps.executeQuery();
	
			if (!rs.next()) {
				if(logger.isErrorEnabled()) {logger.error("patient not found for SrceSysName: " + SrceSysName + " storeNum: " + storeNumber + " patientID: " + patientId);}
				throw new PatientSubscriptionNotFoundException(SrceSysName, String.valueOf(storeNumber), patientId);
			}
			
			patientKey = rs.getLong("PTNTKEY");
			patientSubscriptionKey = rs.getString("PTNTSUBSCRPTNSRCESYSKey");
	
			if (patientSubscriptionKey != null ) {
				if(logger.isErrorEnabled()) {logger.error("subscription exists for SrceSysName: " + SrceSysName  + " storeNum: " + storeNumber + "  patientID: " + patientId);}
				isSubscribed = true ; 
			}
			return isSubscribed ;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End isPatientSubscribed");}
		}		
	}

	
	// VL99 Started
	public Subscription isPatientSubscribedByPatientKey(Connection connection, long patientKey ) throws SQLException, NamingException, IOException, SourceSystemNotValidException {  
		try {
			String systemName = null;
			Long SrceSysKey = null;
			Subscription subs = new Subscription();
			
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, Constants.SOURCESYS_DIGITAL_RX);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for "+Constants.SOURCESYS_DIGITAL_RX);}
				throw new SourceSystemNotValidException(Constants.SOURCESYS_DIGITAL_RX);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			if (logger.isInfoEnabled())  {logger.info("Start isPatientSubscribed : patientKey " + patientKey);}

			ps = connection.prepareStatement(PTNTSUBSQL);
			setPsLongParam(1, patientKey); 
			rs = ps.executeQuery();
		    while (rs.next()) {
		    	  systemName = rs.getString("SRCESYSCD").trim();
		    	  if (systemName.equals(SourceSystem.ACCURO.value())) {
		    		  subs.getSourceSystem().add(SourceSystem.ACCURO);
		    	  }
		    	  
		    	  if (systemName.equals(SourceSystem.DIGITAL_RX.value())) {
		    		  subs.getSourceSystem().add(SourceSystem.DIGITAL_RX);
		    	  }
		    }
		    
		    if (subs.getSourceSystem().size() == 0) {
		    	if (logger.isWarnEnabled())  {logger.warn("SubscribedPatient not found for patientKey " + patientKey);}
		    }
			return subs;
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End isPatientSubscribed");}
		}		
	}
	// VL99 Ended
	
	
	public void insertPtntSubscrptnSrceSys(Connection connection, SubscribeToPatient subscribeToPatient, String SrceSysName)
			throws SQLException, NamingException, IOException, SourceSystemNotValidException, PatientAlreadySubscribedException, PatientSubscriptionNotFoundException, InvalidInputException {
		try {
			patientKey = 0L;
			Long SrceSysKey = null;
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, SrceSysName);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for " + SrceSysName);}
				throw new SourceSystemNotValidException(SrceSysName);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
			String patientId = subscribeToPatient.getPatientId();
			String storeNumber = CommonUtil.createStoreLeadingZeros(subscribeToPatient.getStoreNumber());
			
			if (patientId == null || patientId.trim().length() == 0) {
				throw new InvalidInputException("patient id not valid");
			}

			boolean isPatientSubscribed = isPatientSubscribed(connection, patientId, storeNumber, SrceSysName);
			if ( ! isPatientSubscribed ) { 
				long pTNTSUBSCRPTNSRCESYSKEY = IdGenerator.generate(connection, "PTNTSUBSCRPTNSRCESYS");
				ps = connection.prepareStatement(INSERTPTNTSQL);
				setPsLongParam(1, pTNTSUBSCRPTNSRCESYSKEY); 
				setPsLongParam(2, SrceSysKey); 
				setPsLongParam(3, patientKey); 
				ps.executeUpdate();
			} else {				
				if(logger.isErrorEnabled()) {logger.error("Patient subscription already exists for SrceSysName: " + SrceSysName + " storeNum: " + storeNumber + " patientID " + patientId);}
				//Changes for Drop57 
				throw new PatientAlreadySubscribedException(SrceSysName, String.valueOf(storeNumber), patientId);
				//Changes for Drop57 
			};
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (PatientSubscriptionNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End insertPtntSubscrptnSrceSys");}
		}
	}	

	public void deletePtntSubscrptnSrceSys(Connection connection, UnsubscribeToPatient unsubscribeToPatient, String SrceSysName) throws SQLException, InvalidInputException, NamingException, IOException, SourceSystemNotValidException, PatientSubscriptionNotFoundException	 {
		try {
			patientKey = 0L ;
			String storeNumber = CommonUtil.createStoreLeadingZeros(unsubscribeToPatient.getStoreNumber());
			String patientId = unsubscribeToPatient.getPatientId();
			
			if (patientId == null || patientId.trim().length() == 0) {
				throw new InvalidInputException("patient id not valid");
			}
			if (logger.isInfoEnabled())  {logger.info("Start deletePtntSubscrptnSrceSys. SrceSysName: " + SrceSysName + " storeNum: " + storeNumber + " PatientId: " + patientId);}
			Long SrceSysKey = null;
			
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, SrceSysName);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for " + SrceSysName);}
				throw new SourceSystemNotValidException(SrceSysName);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
			boolean isPatientSubscribed = isPatientSubscribed(connection, patientId, storeNumber, SrceSysName);
			if ( isPatientSubscribed ) {
				ps = connection.prepareStatement(DELETEPTNTSQL);
				setPsLongParam(1, SrceSysKey); 
				setPsLongParam(2, patientKey); 
				ps.executeUpdate();
			} else {     				
				if(logger.isErrorEnabled()) {logger.error("Patient subscription not found for SrceSysName: " + SrceSysName + " storeNum: " + storeNumber + " patientID: " + patientId);	}		
				//Changes for Drop57 0709
				throw new PatientSubscriptionNotFoundException(SrceSysName, String.valueOf(storeNumber), patientId);
				//Changes for Drop57 0709
			}
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
			throw e;
		}
		finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End deletePtntSubscrptnSrceSys ");}
		}
	}
	
/*	public boolean isRxSubscribed(Connection connection, int rxNumber, int storeNumber ) throws SourceSystemNotValidException, RxSubscriptionNotFoundException, NamingException, IOException, SQLException 
	{
		   String storeNumberString = CommonUtil.createStoreLeadingZeros(storeNumber);
		   String rxNumberString = String.format("%d", rxNumber);
		   return isRxSubscribed(connection, rxNumberString, storeNumberString );
	}
*/	
	public boolean isRxSubscribed(Connection connection, String rxNumber, String storeNumber, String SrceSysName) throws SourceSystemNotValidException, NamingException, IOException, RxSubscriptionNotFoundException, SQLException {  
		try {
			Long SrceSysKey = null;
			boolean isSubscribed = false ;
			storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);
			
			if(logger.isDebugEnabled()) {logger.debug("Start isRxSubscribed  SrceSysName: " + SrceSysName + " storeNumber: " + storeNumber + " , RxNumber : " + rxNumber);}
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, SrceSysName);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for " + SrceSysName);}
				throw new SourceSystemNotValidException(SrceSysName);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
			ps = connection.prepareStatement(PRESCRIPTIONSUBSCRIPTIONSQL);
			setPsLongParam(1, SrceSysKey); 
			setPsStringParam(2, rxNumber); 
			setPsStringParam(3, storeNumber); 
			rs = ps.executeQuery();
	
			if (!rs.next()) {
				if(logger.isErrorEnabled()) {logger.error("prescription not found for SrceSysName: " + SrceSysName + " store number: " + storeNumber + ", rxnumber: " + rxNumber);}
				throw new RxSubscriptionNotFoundException(SrceSysName, storeNumber, rxNumber);
			};
	
			rxKey = rs.getLong("rxKey".toUpperCase());
			rxSubscriptionKey = rs.getString("RXSUBSCRPTNSRCESYSKey".toUpperCase());
			if(logger.isDebugEnabled()) {logger.debug(" rx key found : " + rxKey);}
	
			if (rxSubscriptionKey != null ) { 
				if(logger.isDebugEnabled()) {logger.debug("subscription exists for SrceSysName: " + SrceSysName + " store number: " + storeNumber + ", rxnumber: " + rxNumber);}
				isSubscribed =  true ;
			};
			return isSubscribed ;
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End isRxSubscribed");}
		}		
	}
	
	public Subscription isRxSubscribedByRxKey(Connection connection, Long rxKey ) throws SQLException, NamingException, IOException, SourceSystemNotValidException { 
		try {
			String systemName = null;
			Long SrceSysKey = null;
			Subscription subs = new Subscription();
			
			if(logger.isDebugEnabled()) {logger.debug("Start isRxSubscribed : RxKey " + rxKey );}
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, Constants.SOURCESYS_DIGITAL_RX);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for "+Constants.SOURCESYS_DIGITAL_RX);}
			} 
			
			
			ps = connection.prepareStatement(RXSUBSQL);
			setPsLongParam(1, rxKey); 
			rs = ps.executeQuery();
		    while (rs.next()) {
		    	  systemName = rs.getString("SRCESYSCD").trim();
		    	  if (systemName.equals(SourceSystem.ACCURO.value())) {
		    		  subs.getSourceSystem().add(SourceSystem.ACCURO);
		    	  }
		    	  
		    	  if (systemName.equals(SourceSystem.DIGITAL_RX.value())) {
		    		  subs.getSourceSystem().add(SourceSystem.DIGITAL_RX);
		    	  }
		    }

			
		    if (subs.getSourceSystem().size() == 0) {
		    	if(logger.isErrorEnabled()) {logger.error("SubscribedPrescription not found for rxKey " + rxKey);}
		    }
			return subs;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End isRxSubscribed");}
		}		
	}

	//TE97_024
	public void insertRxSubscrptnSrceSys(Connection connection, SubscribeToRx subscribeToRx, String SrceSysName)
			throws SQLException, IOException, NamingException, SourceSystemNotValidException, RxSubscriptionNotFoundException, RxAlreadySubscribedException, InvalidInputException {
		
		try {
			Long SrceSysKey = null;
			try {
				SrceSysKey = getKeyFromCode(LT_SRCESYS, SrceSysName);
			} catch (KeyNotFoundFromTableCacheException e) {
				if(logger.isErrorEnabled()) {logger.error("The source system in request is not valid for " + SrceSysName);}
				throw new SourceSystemNotValidException(SrceSysName);
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
			String rxNumber = String.valueOf(subscribeToRx.getRxNumber());
			String storeNumber = CommonUtil.createStoreLeadingZeros(subscribeToRx.getStoreNumber());
			if (logger.isInfoEnabled())  {logger.info("Start insertRxSubscrptn : SrceSysName: " + SrceSysName + " storeNumber: " + storeNumber + " , RxNumber: " + rxNumber);}
			if (rxNumber == null || rxNumber.trim().length() == 0) {
				throw new InvalidInputException("rxNumber is not valid");
			}
			
			boolean isRxSubscribed = isRxSubscribed(connection, rxNumber, storeNumber, SrceSysName);
			if ( ! isRxSubscribed ) {
				long RXSUBSCRPTNSRCESYSKey = IdGenerator.generate(connection, "RXSUBSCRPTNSRCESYS");
				ps = connection.prepareStatement(INSERTRXSQL);
				setPsLongParam(1, RXSUBSCRPTNSRCESYSKey); 
				setPsLongParam(2, SrceSysKey); 
				setPsLongParam(3, rxKey); 
				ps.executeUpdate();
				NotificationApi notificationApi=new NotificationApi();
				notificationApi.upsertNotification(connection,rxKey);
			} else {				
				if(logger.isErrorEnabled()) {logger.error("Rx subscription already exists for SrceSysName: " + SrceSysName + " storeNum: " + storeNumber + ", rxnumber: " + rxNumber);}
//				//Changes for Drop57 
				throw new RxAlreadySubscribedException(SrceSysName, storeNumber, rxNumber);
				//Changes for Drop57 
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End insertRxSubscrptnSrceSys ");}
		}		
	}

	public void deleteRxSubscrptnSrceSys(Connection connection, UnsubscribeToRx unsubscribeToRx, String SrceSysName) throws SQLException, SourceSystemNotValidException, RxSubscriptionNotFoundException, NamingException, IOException {
		try {
			String rxNumber = new Integer(unsubscribeToRx.getRxNumber()).toString();
			Long noitRXNumber = new Long(unsubscribeToRx.getRxNumber()).longValue();
			String storeNumber = CommonUtil.createStoreLeadingZeros(unsubscribeToRx.getStoreNumber());
			if (logger.isInfoEnabled())  {logger.info("Start deleteRxSubscrptnSrceSys for SrceSysName: " + SrceSysName + " storeNumber: " + storeNumber + " RxNumber: " + rxNumber);}
			
			boolean isRxSubscribed = isRxSubscribed(connection, rxNumber, storeNumber, SrceSysName);
			if (isRxSubscribed) {
				ps = connection.prepareStatement(DELETERXSQL);
				setPsStringParam(1, rxSubscriptionKey);
				ps.executeUpdate();
				//Changes for Drop57  delete operation
				
				deleteNotificationRx(connection,rxKey);
				//Changes for Drop57 
			} else {
				if(logger.isErrorEnabled()) {logger.error("Rx subscription does not exists for store number " + storeNumber + ", rxnumber "+rxNumber);}
				//Changes for Drop57 0709
				throw new RxSubscriptionNotFoundException(SrceSysName, storeNumber, rxNumber);
				//Changes for Drop57 0709
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("End deleteRxSubscrptnSrceSys ");}
		}		
	}
	
	//TE97 Subscription API for Linked Rx start
	//TE97_024
		public void SubscribeLinkedRx(Connection connection, Long origRx_RXKEY, Long linkRx_RXKEY)
				throws SQLException, NamingException, IOException {
				
				try{
				
				if (linkRx_RXKEY != null ) {
					ps = connection.prepareStatement(INSERTSUBSCRIBESQL);
					setPsLongParam(1, linkRx_RXKEY); 
					setPsLongParam(2, origRx_RXKEY); 
					setPsLongParam(3, linkRx_RXKEY); 
					ps.executeUpdate();
					NotificationApi notificationApi=new NotificationApi();
					notificationApi.upsertNotification(connection,origRx_RXKEY);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} 
				catch (IOException e) {
					e.printStackTrace();
					throw e;
				} finally {
				super.close();
				if (logger.isInfoEnabled())  {logger.info("End insertRxSubscrptnSrceSys ");}
			}		
		}
		//TE97 Subscription API for Linked Rx end

		//Changes for Drop57  delete operation
		public void deleteNotificationRx(Connection connection, Long rx_RXKEY)
				throws SQLException {
				
				try{
				
				if (rx_RXKEY != null ) {
					ps = connection.prepareStatement(DELETENOTIFSQL);
					setPsLongParam(1, rx_RXKEY); 
					setPsLongParam(2, rx_RXKEY); 
					setPsLongParam(3, rx_RXKEY); 
					ps.executeUpdate();
					
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
				/*catch (NamingException e) {
				e.printStackTrace();
				throw e;
			} 
				catch (IOException e) {
					e.printStackTrace();
					throw e;
				}*/
				finally {
				super.close();
				if (logger.isInfoEnabled())  {logger.info("End insertRxSubscrptnSrceSys ");}
			}		
		}
	
		//Changes for Drop57  delete operation
}
