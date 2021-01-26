package ca.sdm.cdr.jdbc.upsert.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;

import ca.shoppersdrugmart.rxhb.ehealth.GPI;

public class GpiApi extends CDRUpsert{
	private static Logger logger = LogManager.getLogger(GpiApi.class);
	
private final static String INSERTSQL =	"INSERT INTO GPITYP (GPITYPCD, CDDESCR, CDDESCRFR,GPITYPKEY) VALUES (?, ?, ?, ?)";
	
private final static String UPDATESQL =	
"UPDATE GPITYP SET  GPITYPCD= ?, CDDESCR= ?, CDDESCRFR=?"
 +" WHERE GPITYPKEY = ? ";
 

private final static String SELECTSQL = "SELECT GPITYPKEY, GPITYPCD, CDDESCR, CDDESCRFR FROM GPITYP "
                        				+" where GPITYPKEY = ?"
                        			    +" and (GPITYPCD<> ? or CDDESCR<> ? or CDDESCRFR <> ?) ";
	
	private final static String MERGER_GPISQL=" MERGE INTO GPITYP a " + 
			"      USING (SELECT ?  GPITYPCD,\r\n" + 
			"                    ?  CDDESCR,\r\n" + 
			"                    ?  CDDESCRFR\r\n" + 
			"             FROM dual) b\r\n" + 
			"      ON (a.GPITYPCD = b.GPITYPCD) \r\n" + 
			"      WHEN MATCHED THEN" + 
			"        UPDATE SET CDDESCR   = b.CDDESCR,\r\n" + 
			"                   CDDESCRFR = b.CDDESCRFR\r\n" + 
			"         where CDDESCR   <> b.CDDESCR\r\n" + 
			"            or CDDESCRFR <> b.CDDESCRFR\r\n" + 
			"      WHEN NOT MATCHED THEN\r\n" + 
			"           insert (GPITYPKEY,\r\n" + 
			"                   GPITYPCD,\r\n" + 
			"                   CDDESCR,\r\n" + 
			"                   CDDESCRFR)\r\n" + 
			"           values (b.GPITYPKEY,\r\n" + 
			"                   b.GPITYPCD,\r\n" + 
			"                   b.CDDESCR,\r\n" + 
			"                   b.CDDESCRFR)";
	
	
	public Long upsertGPI(Connection connection,  GPI gpi) throws SQLException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: GpiApi");}
			// Persist main entity
			// LTPHAMRS29

			String gpiNumber = (gpi.getGPINumber() != null)
					? new String (gpi.getGPINumber()) : null;
			Long gpiTypKey;
			gpiTypKey = FindUtil.findGPITypKey(connection, gpiNumber);
			if (gpiTypKey == null || gpiTypKey == 0L) {
				gpiTypKey = insertGPI(connection,  gpi);
			} else {
				// DSGFRM table doesn't have an Update Timestamp field.
				updateGPI(connection,  gpiTypKey, gpi);
			}
			return gpiTypKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		 finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: GpiApi" );}
		}
	}
		@SuppressWarnings("finally")
		private Long updateGPI(Connection connection, Long gpiTypKey, GPI gpi) throws SQLException {
			if (logger.isInfoEnabled())  {logger.info("GpiApi:starting updateGPI");}
			PreparedStatement preparedStament = null;
			PreparedStatement ps =null;
			ResultSet rs = null;
		try {
			if (logger.isInfoEnabled())  {logger.info(SELECTSQL);}
			 
			preparedStament = connection.prepareStatement(SELECTSQL);
			CommonUtil.setPsLongParam(preparedStament,1, gpiTypKey);
			CommonUtil.setPsStringParam(preparedStament, 2,gpi.getGPINumber());
			CommonUtil.setPsStringParam(preparedStament, 3,String.valueOf(gpi.getDescriptionEnglish()));
			CommonUtil.setPsStringParam(preparedStament, 4,String.valueOf(gpi.getDescriptionFrench()));
			
			rs = preparedStament.executeQuery();
			if (rs.next()) {
				if (logger.isInfoEnabled())  {logger.info(UPDATESQL);}
				 
				ps = connection.prepareStatement(UPDATESQL);
				setPsParams(ps,gpiTypKey, gpi);
				int res = ps.executeUpdate();
				if (res > 0) {
					if(logger.isDebugEnabled()) {logger.debug("GPI has been updated: " + res + ". gpiTypKey: " + gpiTypKey);}
				}
			} else {
				if(logger.isDebugEnabled()) {logger.debug("GPI has not been updated: "  +" gpiTypKey: " + gpiTypKey);}
			}
		}
          catch (Exception e) {
		  e.printStackTrace();
	        } 
	finally {
	  	   CommonUtil.closePreparedStatementResultSet(ps, rs);
	   }
			return gpiTypKey;
		 
 	    }
		/**
		 * Attempt to insert a new GPI into corresponding database table.
		
		 * @param gpi	GPI instance.
		 * 
		 * @return				dosageFormKey.
		 * 
		 * @throws SQLException
		 *
		 */
	private Long insertGPI(Connection connection, GPI gpi) throws SQLException {
		if (logger.isInfoEnabled())  {logger.info("GpiApi:starting insertGPI");}
		Long GPITYPKEY = IdGenerator.generate(connection, "GPITYP");
		PreparedStatement ps = null;
		try {
			 
			ps = connection.prepareStatement(INSERTSQL);
			setPsParams(ps, GPITYPKEY, gpi);
			int res = ps.executeUpdate();
			if (logger.isInfoEnabled())  {logger.info("  GPI   inserted: " + res + ". GPITYPKEY: " + GPITYPKEY + ", ID: '" + gpi.getGPINumber());}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}

		return GPITYPKEY;
	}
		
		
		
		/**t
		 * Private method to set Prepared Statement Parameters.
		 * 
		 * @param ps			PreparedStatement object.
		 * @param consumerId	Consumer ID.
		 * @param storeNum		Store Number.
		 * @param dosageFormKey	DosageForm Key.
		 * @param dosageForm	DosageForm instance.
		 * 
		 * @throws SQLException
		 */
		private void setPsParams(PreparedStatement ps ,Long gPITypKey, GPI gpi) throws SQLException {
			CommonUtil.setPsStringParam(ps, 1,gpi.getGPINumber());
			CommonUtil.setPsStringParam(ps, 2,String.valueOf(gpi.getDescriptionEnglish()));
			CommonUtil.setPsStringParam(ps, 3,String.valueOf(gpi.getDescriptionFrench()));
			CommonUtil.setPsLongParam(ps,4, gPITypKey);
		}
		
	}
	


