package ca.sdm.cdr.jdbc.query.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.SourceSystem;
import ca.shoppersdrugmart.cdr.notificationService.NotificationRequest;
import ca.shoppersdrugmart.cdr.notificationService.NotificationResponse;
import ca.shoppersdrugmart.cdr.notificationService.NotificationResponseDetail;

public class NotificationGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(NotificationGet.class);
	static Long statTime = 0L;
	static Long endTime = 0L;
	static int RowCount = 0;
	boolean hasNext = false;

	Long notificationKey = null;
	String BusinessKey1 = null;
	String PrevBusinessKey1 = null;

	String BusinessKey2 = null;
	String PrevBusinessKey2 = null;

	String storeNum = null;
	String rxNum = null;
	String sourceSystem = null;

	static String sqlQuery = "select NOTIFICATIONKEY,  "
			+ " (STORENUM || NOTIFICATIONID)                 BUSINESSKEY1, "
			+ " (STORENUM || NOTIFICATIONID || SOURCESYSTEM) BUSINESSKEY2, " + "       STORENUM,              "
			+ "       NOTIFICATIONID RXNUM,  " + "       EVENTNAME,             " + "       ENTITYTYPE,            "
			+ "       ENTITYKEY,             " + "       NOTIFICATIONTYPE,      " + "       SOURCESYSTEM,          "
			+ " TO_CHAR(NOTIFICATIONDATE, 'YYYY-MM-DD') NOTIFICATIONDATE " + " from NOTIFICATION        "
			+ "where EVENTNAME = ?       " + "  and TO_CHAR(NOTIFICATIONDATE, 'YYYY-MM-DD') <=  TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
			+ "order by STORENUM, NOTIFICATIONID, SOURCESYSTEM " + "FETCH FIRST ? ROWS ONLY";

	static String InertQuery = "INSERT INTO cdradmin.TMP_NOTIFICATION(NOTIFICATIONKEY) ( select NOTIFICATIONKEY"
			+ " from NOTIFICATION        " + "where EVENTNAME = ?       " + "  and  TO_CHAR(NOTIFICATIONDATE, 'YYYY-MM-DD')  <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')"
			+ "order by STORENUM, NOTIFICATIONID, SOURCESYSTEM " + "FETCH FIRST ? ROWS ONLY )";

	private final static String DELETESQL = "DELETE FROM NOTIFICATION WHERE NOTIFICATIONKEY in (SELECT NOTIFICATIONKEY FROM cdradmin.TMP_NOTIFICATION)";
	
	
	private final static String createTempTable = "CREATE GLOBAL TEMPORARY TABLE cdradmin.TMP_NOTIFICATION (      "
			+ "	   NOTIFICATIONKEY  NUMBER(29,0) NOT NULL) " + "ON COMMIT PRESERVE ROWS                  ";

	public NotificationResponse getNotificationDetails(Connection connection,
			NotificationRequest notificationProcess) throws SQLException, Exception {
		RowCount = 0;
		String eventName = notificationProcess.getEventName().value();
		int maxEntityCount = notificationProcess.getMaxEntityCount();
		

		NotificationResponse resp2 = new NotificationResponse();

		

		try {
			
			/*if(checkTempTable(connection)){*/
			if(true) {
			statTime = System.currentTimeMillis();
			insertTempData(connection, eventName, maxEntityCount);
			resp2 = answerRequest2(connection, eventName, maxEntityCount);
			deleteNotifications(connection);
			
			if (logger.isInfoEnabled())  {logger.info("Total records answered : " + RowCount);}
			if (logger.isInfoEnabled())  {logger.info("Total time is : " + (System.currentTimeMillis() - statTime) + " ms");}
			
			  } else { 
				  if (logger.isInfoEnabled())  {logger.info("Some parallel running IIB Request has not completed its traversing/purging process yet"); }
				  }
			 

		} catch (SQLException e) {
			int ErrCode = e.getErrorCode();
			String str = e.getMessage();
			e.printStackTrace();
			if (logger.isInfoEnabled())  {logger.info(":::::::::::: SQLException in getNotificationDetails ::::::::::" + e.toString() + " error code "
					+ ErrCode + " error message " + str);}
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			if (logger.isInfoEnabled())  {logger.info(":::::::::::: Exception in getNotificationDetails ::::::::::" + e);}
			throw new Exception(e.getMessage());
		} finally {
			if (logger.isInfoEnabled())  {logger.info(
					":::::::::::: Under finally block closing resultset and preapred stmt of getNotificationDetails ::::::::::");}

			

		}
		return resp2;

	}

	public void deleteNotifications(Connection connection) throws SQLException, Exception {
		Statement ps = null;
		int num = 0;
		try {
			long start = System.currentTimeMillis();
			ps = connection.createStatement();
			num = ps.executeUpdate(DELETESQL);

			if (logger.isInfoEnabled())  {logger.info("total records deleted from notification table" + num);}
			connection.commit();
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled())  {logger.info("total time taken to delete = " + (end - start) + " ms");}
		} catch (SQLException e) {
			int ErrCode = e.getErrorCode();
			String str = e.getMessage();
			e.printStackTrace();
			if (logger.isInfoEnabled())  {logger.info(":::::::::::: SQLException in getNotificationDetails ::::::::::" + e.toString() + " error code "
					+ ErrCode + " error message " + str);}
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			if (logger.isInfoEnabled())  {logger.info(":::::::::::: Exception in getNotificationDetails ::::::::::" + e);}
			throw new Exception(e.getMessage());
		} finally {
			if (logger.isInfoEnabled())  {logger.info(
					":::::::::::: Under finally block closing resultset and preapred stmt of getNotificationDetails ::::::::::");}
			ps.close();

		}

	}

	private boolean checkTempTable(Connection conn) throws Exception, SQLException {
		boolean returnFlag = true;
		Statement stmt = null;
		

		try {

			stmt = conn.createStatement();
            stmt.executeUpdate(createTempTable);
            stmt.close();
            stmt = conn.createStatement();     
            stmt.executeUpdate("CREATE INDEX cdradmin.TMP_NOTIFICATION_IND ON cdradmin.TMP_NOTIFICATION(NOTIFICATIONKEY)");
                       
            conn.commit();


		} catch (SQLException e) {
			if (e != null && e.getErrorCode() == 955) {
				

				returnFlag = true;
			} else {
				
				e.printStackTrace();
				int ErrCode = e.getErrorCode();
				String str = e.getMessage();
				if (logger.isInfoEnabled())  {logger.info(":::::::::::: SQLException in create temp table ::::::::::" + e.toString() + " error code "
						+ ErrCode + " error message " + str);}
				throw new SQLException(e.getMessage());
			}
		} finally {
			stmt.close();
		}
		return returnFlag;
	}

	private NotificationResponse answerRequest2(Connection conn, String eventName, int maxEntityCount)
			throws Exception, SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		NotificationResponse resp2 = new NotificationResponse();
		List<NotificationResponseDetail> outlist = new ArrayList<NotificationResponseDetail>();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, eventName);
			ps.setInt(2, maxEntityCount);
			rs = ps.executeQuery();

			readRow(rs);
			while (hasNext) {
				NotificationResponseDetail nr2 = new NotificationResponseDetail();

				List<SourceSystem> sourceSystemArr = new ArrayList<SourceSystem>();
				nr2.setStoreNumber(rs.getString("STORENUM"));

				nr2.setPrescriptionNumber(rs.getInt("RXNUM"));
				
				while (hasNext && PrevBusinessKey1.equalsIgnoreCase(BusinessKey1)) {

					
					while (hasNext && PrevBusinessKey2.equalsIgnoreCase(BusinessKey2)) {
						sourceSystemArr.add(SourceSystem.fromValue(rs.getString("SOURCESYSTEM").trim()));
						readRow(rs);
					}
					PrevBusinessKey2 = BusinessKey2;
					List<SourceSystem> sourceArray= new ArrayList<SourceSystem>( new HashSet<SourceSystem>(sourceSystemArr));
					
					nr2.setSubscription(sourceArray);
					outlist.add(nr2);
				}
				PrevBusinessKey1 = BusinessKey1;
			}

			resp2.setTotalPrescription(RowCount);
			resp2.setRowCount(RowCount);
			resp2.setNotificationResponseDetail(outlist);

		} catch (Exception e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return resp2;
	}

	private void readRow(ResultSet rs) throws Exception, SQLException {
		if (hasNext = rs.next()) {
			RowCount++;
			BusinessKey1 = rs.getString("BUSINESSKEY1");
			BusinessKey2 = rs.getString("BUSINESSKEY2");

			notificationKey = rs.getLong("NOTIFICATIONKEY");
			storeNum = rs.getString("STORENUM");
			rxNum = rs.getString("RXNUM");
			sourceSystem = rs.getString("SOURCESYSTEM");

			if (RowCount == 1) {
				PrevBusinessKey1 = BusinessKey1;
				PrevBusinessKey2 = BusinessKey2;
			}
		}
	}

	private void insertTempData(Connection connection, String eventName, int maxEntityCount) throws SQLException {
		PreparedStatement ps = null;
		int num = 0;
		try {
			ps = connection.prepareStatement(InertQuery);
			ps.setString(1, eventName);
			ps.setInt(2, maxEntityCount);
			num = ps.executeUpdate();
			if (logger.isInfoEnabled())  {logger.info("total records inserted into temp table" + num);}
		} catch (SQLException e) {
			e.printStackTrace();
			int ErrCode = e.getErrorCode();
			String str = e.getMessage();
			if (logger.isInfoEnabled())  {logger.info(":::::::::::: SQLException in create temp table ::::::::::" + e.toString() + " error code "
					+ ErrCode + " error message " + str);}
			throw new SQLException(e.getMessage());
		}finally {
			  if(ps!=null) {
				ps.close();
			}
		}

	}

}
