package ca.sdm.cdr.common.util;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GETPROVKEY;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.FormularyRing;
//import com.sdm.cdr.exception.ExceptionConstants;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.ReactionCodeMissingException;
import com.sdm.cdr.exception.api.RxNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.singleton.bean.ReferenceTableBean;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.Province;


public class FindUtil {
	
//	private static Logger logger = Logger.getLogger(FindUtil.class);
	
	
	public static Long findValue(Connection connection, ReferenceTableBean referenceTableBean, String newValue) throws  SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long key = null; 
    		String query =	"SELECT " +  referenceTableBean.getKeyColumnName() + " , "  + referenceTableBean.getCodeColumnName() + " FROM " + referenceTableBean.getTableName() 
    					+	" WHERE " + referenceTableBean.getCodeColumnName() + " = ? "	;

			ps = connection.prepareStatement(query);
			CommonUtil.setPsStringParam(ps, 1, newValue);
			rs = ps.executeQuery();
			if (rs.next()) {
				key = rs.getLong(referenceTableBean.getKeyColumnName());
			}
	
			return key;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}	
	
	
	public static Long findValue_virtual(Connection connection, String newValue) throws  SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long key = null; 
    		String query =	"select p.provkey from store     s,CNTCTMTHD c,addr      a,prov      p where s.CNTCTMTHDKEY = c.CNTCTMTHDKEY "
    					+" and c.cntctmthdtypcd = 'Postal Address'   and c.CNTCTMTHDKEY = a.addrkey    and a.provcd = p.cddescr and s.storenum = ? ";

			ps = connection.prepareStatement(query);
			CommonUtil.setPsStringParam(ps, 1, newValue);
			rs = ps.executeQuery();
			if (rs.next()) {
				key = rs.getLong("provkey");
			}
	
			return key;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}	
	public static String getStoreAddress(Connection connection, String storeNumber , Address address) throws SQLException, RxNotFoundException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sqlQuery = " select ADDR_POSTALCD,ADDR_CNTRYCD,ADDR_PROVCD,ADDR_CITYNM,ADDR_ADDRLNTWO,ADDR_ADDRLNONE,ADDR_ADDRKEY,FAX_TELECOMNUM,FAX_CNTRYCD,FAX_TELFAXIND,FAX_TELECOMKEY,PHONE_TELECOMNUM,PHONE_CNTRYCD,PHONE_TELFAXIND,PHONE_TELECOMKEY,EMAIL_EMAILADDR,EMAIL_EMAILKEY,STORE_STORENUM,STORE_STOREKEY,STORE_DIVISION,CM_CNSMRID from STORE_ADDRESS_VW where STORE_STORENUM = ?  ";
			
					
/*					" SELECT distinct " 
							+ "        STORE.STOREKEY       STORE_STOREKEY " 
							// EMail  
							+ "      , EMAIL.EMAILKEY     EMAIL_EMAILKEY " 
							+ "      , EMAIL.EMAILADDR    EMAIL_EMAILADDR " 
							// Phone  
							+ "      , PHONE.TELECOMKEY   PHONE_TELECOMKEY " 
							+ "      , PHONE.TELFAXIND    PHONE_TELFAXIND " 
							+ "      , PHONE.CNTRYCD      PHONE_CNTRYCD " 
							+ "      , PHONE.TELECOMNUM   PHONE_TELECOMNUM " 
							// Fax  
							+ "      , FAX.TELECOMKEY     FAX_TELECOMKEY " 
							+ "      , FAX.TELFAXIND      FAX_TELFAXIND " 
							+ "      , FAX.CNTRYCD        FAX_CNTRYCD " 
							+ "      , FAX.TELECOMNUM     FAX_TELECOMNUM " 
							// Address  
							+ "      , ADDR.ADDRKEY       ADDR_ADDRKEY " 
							+ "      , ADDR.ADDRLNONE     ADDR_ADDRLNONE " 
							+ "      , ADDR.ADDRLNTWO     ADDR_ADDRLNTWO " 
							+ "      , ADDR.CITYNM        ADDR_CITYNM " 
							+ "      , ADDR.PROVCD        ADDR_PROVCD " 
							+ "      , ADDR.CNTRYCD       ADDR_CNTRYCD " 
							+ "      , ADDR.POSTALCD      ADDR_POSTALCD " 
							+ " 	 , C_M.CNSMRID        CM_CNSMRID " 
							+ " FROM   STORE " 
							+ " 	LEFT OUTER JOIN (SELECT CM.LOCKEY,  EMAIL.EMAILKEY, EMAIL.EMAILADDR FROM CNTCTMTHD CM, EMAIL WHERE  CM.CNTCTMTHDKEY = EMAIL.EMAILKEY) EMAIL ON  STORE.LOCKEY = EMAIL.LOCKEY " 
							+ " 	LEFT OUTER JOIN (SELECT CM.LOCKEY,  ADDR.ADDRKEY, ADDR.ADDRLNONE, ADDR.ADDRLNTWO, ADDR.CITYNM, ADDR.PROVCD, ADDR.CNTRYCD, ADDR.POSTALCD FROM CNTCTMTHD CM, ADDR WHERE  CM.CNTCTMTHDKEY = ADDR.ADDRKEY) ADDR ON STORE.LOCKEY = ADDR.LOCKEY " 
							+ " 	LEFT OUTER JOIN (SELECT CM.LOCKEY,  TELECOM.TELECOMKEY, TELECOM.TELFAXIND, TELECOM.CNTRYCD,  TELECOM.TELECOMNUM FROM CNTCTMTHD CM, TELECOM WHERE  CM.CNTCTMTHDKEY = TELECOM.TELECOMKEY AND TELECOM.TELFAXIND = 'T') PHONE ON STORE.LOCKEY = PHONE.LOCKEY " 
							+ " 	LEFT OUTER JOIN (SELECT CM.LOCKEY,  TELECOM.TELECOMKEY, TELECOM.TELFAXIND, TELECOM.CNTRYCD,  TELECOM.TELECOMNUM FROM CNTCTMTHD CM, TELECOM WHERE  CM.CNTCTMTHDKEY = TELECOM.TELECOMKEY AND TELECOM.TELFAXIND = 'F') FAX ON STORE.LOCKEY = FAX.LOCKEY  " 
							+ " 	LEFT OUTER JOIN CNTCTMTHD C_M on STORE.LOCkey = C_M.LOCkey " 
							+ " WHERE " 
							+ " store.storenum = ? " ;*/
			
			//PATIENT_CONTACT_METHOD_VW
			
			ps = connection.prepareStatement(sqlQuery);
			CommonUtil.setPsStringParam(ps, 1, storeNumber );
			rs = ps.executeQuery();
	//		 address = new Address();
			
			if (rs.next()) {
				
				address.setAddressLineOne(rs.getString( "ADDR_ADDRLNONE")) ;
				address.setAddressLineTwo(rs.getString("ADDR_ADDRLNTWO".toUpperCase())) ;
				address.setCityName(rs.getString("ADDR_CITYNM".toUpperCase())) ;
				String provCode = rs.getString("ADDR_PROVCD".toUpperCase()) ; 
				if( provCode  != null )
				{
					address.setProvinceCode(Province.fromValue( provCode) ) ;
				}	
				
				address.setCountryCode(rs.getString("ADDR_CNTRYCD".toUpperCase()));
				address.setPostalCode(rs.getString("ADDR_POSTALCD".toUpperCase()));
				address.setConsumerId(rs.getString("CM_CNSMRID".toUpperCase()));

				
				
				address.setEmail(rs.getString("EMAIL_EMAILADDR".toUpperCase()));
				address.setPrimaryPhoneNumber(rs.getString("PHONE_TELECOMNUM".toUpperCase()));
				address.setFaxNumber(rs.getString("FAX_TELECOMNUM".toUpperCase()));
			}
	
		String	division = rs.getString("STORE_DIVISION".toUpperCase());
			return division;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
		
	}			
	
	
	/**
	 * Find Prescription data.
	 * 
	 * @param conn				JDBC connection.
	 * @param consumerId		Prescription's Consumer ID.
	 * @param storeNum			Stote Number.
	 * @return					Prescription data.
	 * @throws SQLException
	 * @throws RxNotFoundException 
	 */
	public static Map<String, Object> findPrescriptionDataByConsumerId(Connection conn, String consumerId, String storeNum) throws SQLException, RxNotFoundException  {
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			String sql = "select Rx.RXKEY, Rx.UPDTTIMESTAMP, Rx.RXID from Rx left outer join Ptnt on Rx.PTNTKEY=Ptnt.PTNTKEY " +
				" where Rx.CNSMRID=? and Rx.STORENUM=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("RXKEY", 		rs.getLong("RXKEY"));
				mapData.put("UPDTTIMESTAMP", rs.getDate("UPDTTIMESTAMP"));
				mapData.put("RXID", 			rs.getString("RXID"));
			}
			return mapData;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}
	}	
	

	
	
	
	/**
	 * 
	 * @param conn
	 * @param rxNum
	 * @return
	 * @throws SQLException
	 * @throws RxNotFoundException 
	 */
	public static Map<String, Object> findPrescriptionDataByRxNum(Connection conn, Integer rxNum , String storeNumber) throws SQLException, RxNotFoundException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			String sql = " select Rx.RXKEY, Rx.UPDTTIMESTAMP, Rx.RXID,Rx.PTNTKEY from Rx where Rx.RxNum=? AND storeNum = ? "; 
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, String.valueOf(rxNum) );
			CommonUtil.setPsStringParam(ps, 2, storeNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("RXKEY", 		rs.getLong("RXKEY"));
				mapData.put("UPDTTIMESTAMP", rs.getTimestamp("UPDTTIMESTAMP"));
				mapData.put("RXID", 			rs.getString("RXID"));
				mapData.put("PTNTKEY", 			rs.getLong("PTNTKEY"));
			}
	
			return mapData;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
		
	}		
	
	

	/**
	 * 
	 * @param conn
	 * @param rxNum
	 * @return
	 * @throws SQLException
	 * @throws RxNotFoundException 
	 */
	public static Long findPrescriptionKeyByRxNum(Connection conn, Integer rxNum , String storeNumber) throws SQLException, RxNotFoundException {
		Map<String, Object> mapData = findPrescriptionDataByRxNum(conn, rxNum , storeNumber );
		return (Long) mapData.get("RXKEY");
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Map<String, Object> findCompoundData(Connection conn, String compoundConsumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map<String, Object> mapData = new HashMap<String, Object>();
			String sql = "select CMPND.CMPNDKEY, CMPND.DSGFRMKEY, CMPND.RTEOFADMIN_CERXTYPKEY from CMPND left outer join DSGFRM on cmpnd.DSGFRMKEY=DSGFRM.DSGFRMKEY " + 
				" where CMPND.CNSMRID=?  fetch first 1 rows only"; //unwanted trick.
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, compoundConsumerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("CMPNDKEY", rs.getLong("CMPNDKEY"));
				mapData.put("DSGFRMKEY", rs.getLong("DSGFRMKEY"));
				mapData.put("RTEOFADMIN_CERXTYPKEY", rs.getLong("RTEOFADMIN_CERXTYPKEY"));
				
			}
			return mapData;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}
	}		
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findCompoundKey(Connection conn, String compoundConsumerId, String storeNum) throws SQLException {
		Map<String, Object> dataMap = findCompoundData(conn, compoundConsumerId, storeNum);
		return (Long) dataMap.get("CMPNDKEY");
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param compoundKey
	 * @param packKey
	 * @param packSizeUOMTypeKey
	 * @return
	 
	 * @throws SQLException 
	 */
	public static Long findCompoundIngredientKey(Connection conn, Long compoundKey, Long packKey, Long packSizeUOMTypeKey) throws  SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long compoundIngredientKey = null; 
			String sql = "select ci.CMPNDINGRDNTKEY from CMPNDINGRDNT ci where ci.CMPNDKEY=? and ci.PACKKEY=? and ci.PACKSZUOMTYPKEY=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, compoundKey);
			CommonUtil.setPsLongParam(ps, 2, packKey);
			CommonUtil.setPsLongParam(ps, 3, packSizeUOMTypeKey);
			rs = ps.executeQuery();
			if (rs.next()) {
				compoundIngredientKey = rs.getLong("CMPNDINGRDNTKEY");
			}
	
			return compoundIngredientKey;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}
	
	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findDosageFormKey(Connection conn, String consumerId, String storeNum) throws SQLException {
		Map<String, Object> dataMap = findCompoundData(conn, consumerId, storeNum);
		return (Long) dataMap.get("DSGFRMKEY");
	}	
	
	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */

	public static Map<String, Object> findDrugData(Connection conn, String drugConsumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map<String, Object> mapData = new HashMap<String, Object>();
			String sql = "select Drg.DRGKEY,Drg.CDEFFENDDT from Drg where CNSMRID =? and STORENUM =? ";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, drugConsumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
						 
			if (rs.next()) {
				mapData.put("DRGKEY", rs.getLong("DRGKEY"));
				mapData.put("LSTUPDATEDTMSTAMP", rs.getTimestamp("CDEFFENDDT"));
				rs.getString("CDEFFENDDT");rs.getDate("CDEFFENDDT");
				rs.getTime("CDEFFENDDT");
			 
		     }
			
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}
	
	
	// update 20200330 Started
	public static void findDrugData2(Connection conn, Drug drug, String storeNum, Map<String, Long> drgPK,
			Map<String, Timestamp> drgTime) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select Drg.DRGKEY, Drg.CDEFFENDDT from Drg where CNSMRID =? and STORENUM =? ";

			if (!(drgPK.containsKey(drug.getConsumerId() + storeNum)
					&& drgTime.containsKey(drug.getConsumerId() + storeNum))) {
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, drug.getConsumerId());
				CommonUtil.setPsStringParam(ps, 2, storeNum);
				rs = ps.executeQuery();
				if (rs.next()) {

					drgPK.put((drug.getConsumerId() + storeNum), rs.getLong("DRGKEY"));
					drgTime.put((drug.getConsumerId() + storeNum), rs.getTimestamp("CDEFFENDDT"));
				}
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
	}
		// update 20200330 Ended

	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Map<String, Object> findDrugKey(Connection conn, String consumerId, String storeNum) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap= findDrugData(conn, consumerId, storeNum);
		return dataMap;
	}
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws Exception 
	  
	 */
	
	public static Map<String, Object> findMoleculeData(Connection conn, String consumerId, String storeNum) throws SQLException  {
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String sql = "select Mlcl.MLCLKEY, Mlcl.MLCLID, DRG.DRGKEY, DRG.DRGID from Drg left outer join Mlcl on Drg.MlclKEY = Mlcl.MlclKEY where Mlcl.CNSMRID=? and Drg.STORENUM=?";
		try {
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			 rs = ps.executeQuery();
			if (rs.next()) {
				dataMap.put("DRGKEY",	rs.getLong("DRGKEY"));
				dataMap.put("DRGID",	rs.getLong("DRGID"));
				dataMap.put("MLCLKEY",	rs.getLong("MLCLKEY"));
				dataMap.put("MLCLID",	rs.getLong("MLCLID"));
			}
			return dataMap;
		} 
		catch (SQLException ex) {
			throw ex;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws Exception 
	  
	 */
	public static Long findMoleculeKey(Connection conn, String consumerId, String storeNum) throws SQLException {
		
		Map<String, Object> dataMap = findMoleculeData(conn, consumerId, storeNum);
		Long moleculeKey = (Long) dataMap.get("MLCLKEY");
		return moleculeKey;
	}

	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Map<String, Object> findActiveIngredientData(Connection conn, String consumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map<String, Object> dataMap = new HashMap<String, Object>();
			String sql = "select ACTINGRDNTKEY, MLCL.MLCLKEY, DRG.DRGKEY from Drg " +
			" left outer join Mlcl on Mlcl.MlclKEY=Drg.MlclKEY " +
			" left outer join ActIngrdnt on ActIngrdnt.MLCLKEY=Mlcl.MLCLKEY " +
			" where ActIngrdnt.CNSMRID=? and Drg.STORENUM=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				dataMap.put("ACTINGRDNTKEY",  rs.getLong("ACTINGRDNTKEY"));
				dataMap.put("MLCLKEY", 		  rs.getLong("MLCLKEY"));
				dataMap.put("DRGKEY", 		  rs.getLong("DRGKEY"));
			}
	
			return dataMap;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}	
	
	
	public static Long findActiveIngredientKey(Connection conn, String consumerId, String storeNum) throws SQLException {
		Map<String, Object> dataMap = findActiveIngredientData(conn, consumerId, storeNum);
		Long activeIngredientKey = (Long) dataMap.get("ACTINGRDNTKEY");
		return activeIngredientKey;
	}

	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static void findPackData(Connection conn, String consumerId, String storeNum,Pack pack,Map<String, Long> packPK,
			Map<String, Timestamp> packTime) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			// Map<String, Object> dataMap = new HashMap<String, Object>();
			String sql = "select Pack.PACKKEY , Pack.CDEFFENDDT  from Pack left outer join Drg on Pack.DRGKEY = Drg.DrgKey where Pack.CNSMRID=? and Drg.STORENUM=? ";
			if (!(packPK.containsKey(pack.getConsumerId() + storeNum)
					&& packTime.containsKey(pack.getConsumerId() + storeNum))) {
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, consumerId);
				CommonUtil.setPsStringParam(ps, 2, storeNum);
				rs = ps.executeQuery();
				if (rs.next()) {
					packPK.put((pack.getConsumerId() + storeNum), rs.getLong("PACKKEY"));
					packTime.put((pack.getConsumerId() + storeNum), rs.getTimestamp("CDEFFENDDT"));

				}

			}
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}					
	}	
	
	

	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	 public static Long findPatientCoverageKey(Connection conn, String consumerId, Long patientKey) throws SQLException  {

		// consumerId is patientCoverageId
		if( consumerId == null || "".equals(consumerId))
			return null;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select PtntCvrgKey from PtntCvrg where ptntcvrg.PtntKey = ? AND PtntCvrgId = ? " ;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, patientKey);
			CommonUtil.setPsStringParam(ps, 2, consumerId);
			rs = ps.executeQuery();
			Long patientCoverageKey = null; 
			if (rs.next()) {
				patientCoverageKey  = rs.getLong("PtntCvrgKey");
			}
	
			return patientCoverageKey ;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}	


	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @return
	 * @throws SQLException
	  
	 */
	 public static Long findProfessionalServiceKey(Connection conn, String consumerId, Long dispenseKey) throws SQLException  {

		// consumerId is patientCoverageId
		if( consumerId == null || "".equals(consumerId))
			return null;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select PrfsnlSvcKey from PrfsnlSvc where TxKey = ? AND CnsmrId = ? " ;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, dispenseKey);
			CommonUtil.setPsStringParam(ps, 2, consumerId);
			rs = ps.executeQuery();
			Long prfsnlSvcKey = null; 
			if (rs.next()) {
				prfsnlSvcKey  = rs.getLong("PrfsnlSvcKey");
			}
			return prfsnlSvcKey ;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
			
	}	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 * @throws PatientNotFoundException 
	 */
	public static Map<String, Object> findPatientData(Connection conn, String consumerId, String storeNum) throws SQLException, PatientNotFoundException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map<String, Object> dataMap = new HashMap<String, Object>();
		
			String sql = "select Ptnt.PTNTKEY , ptnt.STORENUM , ptnt.LSTUPDTTIMESTAMP from Ptnt " +
					" where PTNT.CNSMRID=? and PTNT.STORENUM=?" ;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				dataMap.put("PTNTKEY", ResultSetWrapper.getLong(rs, "PTNTKEY"));
				dataMap.put("STORENUM", ResultSetWrapper.getString(rs, "STORENUM"));
				dataMap.put("LSTUPDTTIMESTAMP", rs.getTimestamp( "LSTUPDTTIMESTAMP"));
			}
	
			return dataMap;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}	
	}	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 * @throws PatientNotFoundException 
	 */
/*	public static Long findPatientKey(Connection conn, Long consumerId, Long storeNum) throws SQLException, PatientNotFoundException {
		String consumerIdString = String.valueOf(consumerId);	
		String storeNumberString = CommonUtil.createStoreLeadingZeros(storeNum);

		Map<String, Object> dataMap = findPatientData(conn, consumerIdString, storeNumberString);
		return (Long) dataMap.get("PTNTKEY");
	}
*/
	public static Long findPatientKey(Connection conn, String consumerId, String storeNum) throws SQLException, PatientNotFoundException {
		storeNum = CommonUtil.createStoreLeadingZeros(storeNum);
		Map<String, Object> dataMap = findPatientData(conn, consumerId, storeNum);
		return (Long) dataMap.get("PTNTKEY");
	}

	
	public static Long findPersonKey(Connection conn, String consumerId, String storeNum) throws SQLException, PatientNotFoundException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = " select prsn.prsnKey from Prsn where Prsn.CNSMRID=? and Prsn.STORENUM=? " ;
			Long prsnKey = null;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				prsnKey = rs.getLong("prsnKey".toUpperCase()) ;
			}
			
			return prsnKey;
		
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}		
	}

	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 * @throws PatientNotFoundException 
	 */
	public static String findPatientStoreNum(Connection conn, String consumerId, String storeNum) throws SQLException, PatientNotFoundException {
		Map<String, Object> dataMap = findPatientData(conn, consumerId, storeNum);
		return (String) dataMap.get("STORENUM");
	}	
	

	
	public static Long findPatientConsentKey(Connection conn, String consumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select PtntCnsnt.PTNTCNSNTKEY from PtntCnsnt left outer join Ptnt on PtntCnsnt.PTNTKEY = Ptnt.PTNTKEY " + 
					" where PtntCnsnt.CNSMRID=? and Ptnt.STORENUM=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			Long packKey  = null;
			if (rs.next()) {
				packKey = rs.getLong("PTNTCNSNTKEY");
			}
			
			return packKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}	
	
	
	
	/**
	 * 
	 * @param conn
	 * @param manufacturerId
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findManufacturerKey(Connection conn, Long manufacturerId) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select MFCTR.MFCTRKEY from MFCTR where MfctrId=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, manufacturerId);
			rs = ps.executeQuery();
			Long manufacturerKey = null;
			if (rs.next()) {
				manufacturerKey = rs.getLong("MFCTRKEY");
				return manufacturerKey;
			}
			
	
			return manufacturerKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}	
	
		
	

	/**
	 * 
	 * @param conn
	 * @param drugConsumerId
	 * @param storeNum
	 * @param manufacturerId
	 * @param recallLotNum
	 * @param recallTimestamp
	 * @return
	 * @throws SQLException
	 */
	public static Long findManufacturerRecallKey(Connection conn, Long drugKey, Long manufacturerKey, String recallLotNum, XMLGregorianCalendar recallTimestamp) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select MFCTRDRGRECALL.MFCTRDRGRECALLKEY from MFCTRDRGRECALL " + 
				" where DRGKEY=? and MFCTRKEY=? and LOTNUM=? and RECALLDT=" + CommonUtil.getPsToDateFunctionStr();
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, drugKey);
			CommonUtil.setPsLongParam(ps, 2, manufacturerKey);
			CommonUtil.setPsStringParam(ps, 3, recallLotNum);
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 4, recallTimestamp);
			rs = ps.executeQuery();
			Long manufacturerRecallKey = null;
			if (rs.next()) {
				manufacturerRecallKey = rs.getLong("MFCTRDRGRECALLKEY");
				return manufacturerRecallKey;
			}
			
			return manufacturerRecallKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}	
	
	
	/**
	 * 
	 * @param conn
	 * @param drugConsumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findDrugColourKey(Connection conn, String drugConsumerId, String storeNum, String colourDescr) throws SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long drugColourKey = null;
	//		String sql = "select drg.*, DRGCLR.* from DRGCLR " +
			String sql = "select DRGCLR.DRGCLRKEY from DRGCLR " +		
				" left outer join DRG on DRGCLR.DRGKEY=Drg.DRGKEY " +
				" left outer join DRGCLRTYP on DRGCLR.DRGCLRTYPKEY=DRGCLRTYP.DRGCLRTYPKEY " + 
				" WHERE Drg.CNSMRID=? and DRG.STORENUM=? and upper(DRGCLRTYP.CDDESCR)=upper(?)";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, drugConsumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			CommonUtil.setPsStringParam(ps, 3, colourDescr);
			rs = ps.executeQuery();
			if (rs.next()) {
				drugColourKey = rs.getLong("DRGCLRKEY");
			}
			return drugColourKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}

	
	/**
	 * 
	 * @param conn
	 * @param storeNum
	 * @param carrierName
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findLocationKey(Connection conn, String locationConsumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select str.LOCKEY from Store str left outer join LOC on str.LOCKEY=LOC.LOCKEY " +
				" where storeNum=?";
				//sql += " and LOC.CNSMRID=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, storeNum);
			rs = ps.executeQuery();
			Long locKey = null;
			if (rs.next()) {
				locKey = rs.getLong("LOCKEY");
			}
			
			return locKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}	

	

	/**
	 * 
	 * @param conn
	 * @param allergyConsumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	  
	 */
	public static Long findAdverseDrugReactionKey(Connection conn, String adverseDrugReactionConsumerId,  Long patientKey , String storeNum) throws SQLException  {
		try
		{
			Map<String, Object>  adrDataMap = findAdverseDrugReactionData( conn, adverseDrugReactionConsumerId,  patientKey , storeNum);
			Long patientAdverseDrugReactionKey = (Long) adrDataMap.get("PTNTADVRSDRGRCTNKEY");
	
			return patientAdverseDrugReactionKey;
		}
		catch (SQLException e) {
			throw e;
		} 		
	}		
	
	public static Map<String, Object>  findAdverseDrugReactionData(Connection conn, String adverseDrugReactionConsumerId,  Long patientKey , String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			
			String sql = null;
			sql = "select PTNTADVRSDRGRCTNKEY , UPDTTIMESTAMP from PTNTADVRSDRGRCTN padr left outer join Ptnt pt on padr.PTNTKEY=pt.PTNTKEY " + 
			" where padr.CNSMRID=? and pt.STORENUM=? and pt.ptntkey=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, adverseDrugReactionConsumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			CommonUtil.setPsLongParam(ps, 3, patientKey);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("PTNTADVRSDRGRCTNKEY",  rs.getLong("PTNTADVRSDRGRCTNKEY"));
				mapData.put("UPDTTIMESTAMP",  rs.getTimestamp("UPDTTIMESTAMP"));
			}
	
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}		
	
	/**
	 * Find Allergy Key by Allergy's Consumer ID and Store Number.
	 * If noPatient flag is chosen (i.e. no associated patient), then Store Number will taken from allergy location's store.
	 * Otherwise (default) Store Number will be taken from Patient.
	 * 
	 * @param conn				JDBC Connection.
	 * @param allergyConsumerId	Allergy Consumer ID.
	 * @param storeNum			Store Number.
	 * @param noPatient			No patient associated?
	 * 
	 * @return					Allergy Key of found allergy. 
	 * @throws SQLException
	  
	 */
	/*public static Long findAllergyKey(Connection conn, String allergyConsumerId, String storeNum, boolean noPatient) throws SQLException  {
		String sql = null;
		if (noPatient) {
			sql = "select * from PTNTALRGY al left outer join Loc on al.LOCKEY=Loc.LOCKEY " +
			" left outer join store st on Loc.LOCKEY=st.LOCKEY " +
			" where al.CNSMRID=? and st.STORENUM=?";
		} else {
			sql = "select * from PTNTALRGY pa " +
			" left outer join Ptnt pt on pa.PTNTKEY=pt.PTNTKEY " +
			" where pa.CNSMRID=? and pt.STORENUM=?";
		}	
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, allergyConsumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Long allergyKey = rs.getLong("PTNTALRGYKEY");
				return allergyKey;
			}
		} catch (Exception ex) {
			throw new EntityNotFoundException(ExceptionConstants.ErrorCode_ALLERGY_NOT_FOUND, storeNum, allergyConsumerId);
		}
		return null;
	}	*/
	
	/**
	 * Find Allergy Key by Allergy's Consumer ID and Store Number.
	 * Store Number will be taken from Patient.
	 *  
	 * @param conn				JDBC Connection.
	 * @param allergyConsumerId	Allergy Consumer ID.
	 * @param storeNum			Store Number.
	 * @return					Allergy Key of found allergy.
	 * @throws SQLException
	  
	 */
	public static Long findAllergyKey(Connection conn, Long ptntKey , String allergyConsumerId) throws SQLException{
//		return findAllergyKey(conn, allergyConsumerId, storeNum /*, false*/);
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map<String, Object> mapData = findAllergyData( conn,  ptntKey , allergyConsumerId);
			Long allergyKey = (Long)mapData.get("PTNTALRGYKEY");
			return allergyKey ;
		}
		catch (SQLException e) {
			throw e;
		} 		
	}
	
	

	public static Map<String, Object>  findAllergyData(Connection conn, Long ptntKey , String allergyConsumerId) throws SQLException{
//		return findAllergyKey(conn, allergyConsumerId, storeNum /*, false*/);
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			String sql = null;
			
			sql = "select PTNTALRGYKEY , UPDTTIMESTAMP from PTNTALRGY pa " +
			" left outer join Ptnt pt on pa.PTNTKEY=pt.PTNTKEY " +
			" where pa.CNSMRID=? and pt.ptntKey=? ";
	
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, allergyConsumerId);
			CommonUtil.setPsLongParam(ps, 2, ptntKey);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("PTNTALRGYKEY", rs.getLong("PTNTALRGYKEY"));
				mapData.put("UPDTTIMESTAMP", rs.getTimestamp("UPDTTIMESTAMP"));
			}
			
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}
	
	
	/**
	 * Find Allergy Test Key by Allergy Test's Consumer ID and Store Number.
	 * Store Number will be taken from Patient.
	 * 
	 * @param conn				JDBC Connection.
	 * @param allergyTestConsumerId	Allergy Test's Consumer ID.
	 * @param storeNum			Store Number.
	 * @return					Allergy Key of found allergy. 
	 * @throws SQLException
	 */
	public static Long findAllergyTestKey(Connection conn, String allergyTestConsumerId, String storeNum) throws SQLException  {
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select ALRGYTSTKEY from ALRGYTST at left outer join PTNTALRGY pa on at.PTNTALRGYKEY=pa.PTNTALRGYKEY " + 
				" left outer join Ptnt pt on pa.PTNTKEY=pt.PTNTKEY " + 
				" where at.CNSMRID=? and pt.STORENUM=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, allergyTestConsumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			Long allergyTestKey = null ;
			if (rs.next()) {
				allergyTestKey = rs.getLong("ALRGYTSTKEY");
				return allergyTestKey;
			}
			
			return allergyTestKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}		
	
	
	/**
	 * 
	 * @param conn
	 * @param reaction
	 * @param allergyKey
	 * @return
	 * @throws SQLException
	 * @throws ReactionCodeMissingException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	public static Long findReactionKeyForAllergy(Connection conn, Long subRctnTypKey, Long allergyKey) throws SQLException, ReactionCodeMissingException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = "select RCTNKEY from rctn where PTNTALRGYKEY=? and subRctnTypKey=?";
			
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, allergyKey);
			CommonUtil.setPsLongParam(ps, 2, subRctnTypKey );
			rs = ps.executeQuery();
			Long reactionKey = null ;
			if (rs.next()) {
				reactionKey = rs.getLong("RCTNKEY");
				return reactionKey;
			}
			
			return reactionKey;		
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}
	

	/**
	 * 
	 * @param conn
	 * @param reaction
	 * @param adverseDrugReactionKey
	 * @return
	 * @throws SQLException
	 */
	public static Long findReactionKeyForAdverseDrugReaction(Connection conn, Long subRctnTypKey, Long adverseDrugReactionKey) throws SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{

			//		String reactionCode = (reaction.getReactionCode()!=null) ? reaction.getReactionCode().value() : null;
			String sql = "select RCTNKEY from rctn where PTNTADVRSDRGRCTNKEY=?  and subRctnTypKey=? ";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, adverseDrugReactionKey);
			CommonUtil.setPsLongParam(ps, 2, subRctnTypKey);
			rs = ps.executeQuery();
			Long reactionKey = null ;
			if (rs.next()) {
				reactionKey = rs.getLong("RCTNKEY");
			}
			
			return reactionKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}			
	}

	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> findDispenseData(Connection conn, String consumerId, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			String sql = "select TXKEY, LSTUPDT,  TXID from TX  " +
				" where CNSMRID=? and STORENUM=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, consumerId);
			CommonUtil.setPsStringParam(ps, 2, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("TXKEY",	rs.getLong("TXKEY"));
				mapData.put("LSTUPDT",	rs.getTimestamp("LSTUPDT"));				
				mapData.put("TXID", 	rs.getString("TXID"));
			}
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}					
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param consumerId
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 */
	public static Long findDispenseKey(Connection conn, String consumerId, String storeNum) throws SQLException {
		Map<String, Object> dataMap = findDispenseData(conn, consumerId, storeNum);
		return (Long) dataMap.get("TXKEY");
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param txKey
	 * @return
	 * @throws SQLException
	 
	 */
	public static Map<String, Object> findPharmacyChannelData(Connection conn, Long txKey) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Map <String, Object> mapData = new HashMap<String, Object>();
			String sql = "select PHARMACYCHNLKEY,  CRTDTTIME, PARENTRXKEY, RXKEY, TXKEY, TXNNUM from PharmacyChnl where TxKey=?";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, txKey);
			rs = ps.executeQuery();
			if (rs.next()) {
				mapData.put("PHARMACYCHNLKEY", 	rs.getLong("PHARMACYCHNLKEY"));
				mapData.put("CRTDTTIME", 		rs.getTimestamp("CRTDTTIME"));
				mapData.put("PARENTRXKEY",		rs.getLong("PARENTRXKEY"));
				mapData.put("RXKEY",			rs.getLong("RXKEY"));				
				mapData.put("TXKEY", 			rs.getLong("TXKEY"));
				mapData.put("TXKEY", 			rs.getString("TXNNUM"));
			}
			
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}
	
	

	public static Long findPatientMedicalConditionKey(Connection conn, Long patientKey, String medicalConditionConsumerId ) throws SQLException  {
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long patientMedicalConditionKey = null;
			
			String sql = " select PtntMdclcndtnkey  from PTNTMDCLCNDTN where ptntkey = ? AND CnsmrId = ? ";
			
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, patientKey);
			CommonUtil.setPsStringParam(ps, 2, medicalConditionConsumerId);
	
			rs = ps.executeQuery();
			if (rs.next()) {
				patientMedicalConditionKey = rs.getLong("PtntMdclcndtnkey");
			}
	
			return patientMedicalConditionKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}	
	

	public static Long findPatientIdentificationKey(Connection conn, Long patientKey, String patientIdentificationNumber) throws SQLException  {
		
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			Long patientIdentificationKey = null; 
			
			String sql = " select PtntIdKey from PtntId where ptntkey = ? AND IdNum = ? ";
			
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsLongParam(ps, 1, patientKey);
			CommonUtil.setPsStringParam(ps, 2, patientIdentificationNumber);
	
			rs = ps.executeQuery();
			if (rs.next()) {
				patientIdentificationKey = rs.getLong("PtntIdKey");			
			}
			return patientIdentificationKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}
	
	public static Long findProfessionalRegistrationKey(Connection conn, ProfessionalRegistration prfreg, Long prsnRlKey) throws SQLException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String licenseNumber = prfreg.getLicenseNumber();
			String provinceStr = (prfreg.getLicensedProvince() != null) ? prfreg.getLicensedProvince().value():null;
			String issuingBodyCode = (prfreg.getIssuingBodyCode()!=null) ? prfreg.getIssuingBodyCode().value():null;
			String sql = " select PrfsnlReg.PrfsnlRegKey from  PrfsnlReg, Prov where PrfsnlReg.LicNum = ? AND Prov.CdDescr = ? AND PrfsnlReg.IssngBodyCd = ? AND PrfsnlReg.PrsnRlKey = ? AND PrfsnlReg.ProvKey=Prov.ProvKey";
	
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, licenseNumber);
			CommonUtil.setPsStringParam(ps, 2, provinceStr);
			CommonUtil.setPsStringParam(ps, 3, issuingBodyCode);
			CommonUtil.setPsLongParam(ps, 4, prsnRlKey);
	
			rs = ps.executeQuery();
			Long professionalRegistrationKey = null;
			if (rs.next()) {
				professionalRegistrationKey = rs.getLong("PrfsnlRegKey");			
			}
	
			return professionalRegistrationKey;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}
	
	public static Long findSpecialDrugProgram(Connection conn, Long drugkey, Long programTypeKey) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = " SELECT DRGSPCLPROGKEY FROM DRGSPCLPROG WHERE DRGKEY = ? AND SPCLDRGPROGTYPKEY = ? ";
			ps = conn.prepareStatement(query);
			ps.setLong(1, drugkey);
			ps.setLong(2, programTypeKey);
			rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Long rxKey = rs.getLong("DRGSPCLPROGKEY");
			return rxKey;
		} catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}
	}

	
	public static Map<String, Object> findStoreData(Connection conn, String storeNum) throws SQLException, PatientNotFoundException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = " SELECT storeKey , locKey ,CDEFFENDDT from store where storeNum = ?  " ;
			Long storeKey = null;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, storeNum);
			rs = ps.executeQuery();
			Map <String, Object> mapData = new HashMap<String, Object>();
			if (rs.next()) {
				mapData.put("STOREKEY", 	rs.getLong("STOREKEY"));
				mapData.put("LOCKEY", 	rs.getLong("LOCKEY"));
				mapData.put("CDEFFENDDT", rs.getTimestamp("CDEFFENDDT"));
			}
			
			return mapData;
		
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}		
	}

	
	public static Long findStoreKey(Connection conn, String storeNum) throws SQLException, PatientNotFoundException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			String sql = " SELECT storeKey from store where storeNum = ?  " ;
			Long storeKey = null;
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, storeNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				storeKey = rs.getLong("STOREKEY".toUpperCase()) ;
			}
			
			return storeKey;
		
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}		
	}
	//ams
	public static Map<String, String> findCustKey(Connection connection, String storeNumber, String patientId) throws SQLException , IOException {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		 String GETCUSTKEYSQL = null;
		try
		{
			GETCUSTKEYSQL = TableCacheSingleton.getResource("GetCustKeyAndPrsnKeyQuery.sql");
			ps = connection.prepareStatement(GETCUSTKEYSQL);
			CommonUtil.setPsStringParam(ps, 1, storeNumber);
			CommonUtil.setPsStringParam(ps, 2, patientId);
			rs = ps.executeQuery();
			Map<String, String> mapData = new HashMap<String, String>();
			if (rs.next()) {
				mapData.put("CUSTKEY", rs.getString("CUSTKEY"));
				mapData.put("PRSNKEY", rs.getString("PRSNKEY"));
			}
			return mapData;
		}
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}
	}	
	//FFT
	public static Long findTXDRXData(Connection conn, String corrid,Integer rxNum, String storeNum) throws SQLException  {
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try
		{
			 String sql="select count(*) noOfRecords from TXDRX  where CORRID = ?  and RXNUM  = ?  and STORENUM = ? ";
			ps = conn.prepareStatement(sql);
			CommonUtil.setPsStringParam(ps, 1, corrid);
			CommonUtil.setPsLongParam(ps, 2, rxNum);
			CommonUtil.setPsStringParam(ps, 3, storeNum);
			rs = ps.executeQuery();
			Long count = null ;
			if (rs.next()) {
				count  = rs.getLong("noOfRecords");
			}
	
			return count ;
		} 
		catch (SQLException e) {
			throw e;
		} finally {
			CommonUtil.closePreparedStatementResultSet(ps,rs);
		}				
	}
	
	//FFT
		public static Long findEntPref(Connection conn, Long cdrptntmkey,Long ptntkey, Long rxkey,String entityVal) throws SQLException  {
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try
			{
				/* String sql="select ENTPREFKEY from ENTPREF WHERE ";
				 if(ptntkey !=null && entityVal.equals("P")) {
					 sql+="PTNTKEY="+ptntkey;
					  }else
					if(rxkey != null && entityVal.equals("R")) {
						sql+="RXKEY="+rxkey;	
					}
				 ps = conn.prepareStatement(sql);*/
				 //Fix for JIRA-HW4GAR 14
				   String sql="select max(ENTPREFKEY) ENTPREFKEY from ENTPREF WHERE PTNTKEY = ";
				   if (ptntkey !=null && entityVal.equals("P")) {
	                              sql += ptntkey + " AND enttyp = 'P' AND RXKEY IS NULL";
				   } 
				   if (rxkey != null && entityVal.equals("R")) {
	                              sql += ptntkey + " AND enttyp = 'R' AND RXKEY IS NOT NULL" + " AND RXKEY = " + rxkey;
				   }
				   ps = conn.prepareStatement(sql);

			//	CommonUtil.setPsLongParam(ps, 1, cdrptntmkey);
				
				
				rs = ps.executeQuery();
				Long entprefkey = null ;
				if (rs.next()) {
					entprefkey  = rs.getLong("ENTPREFKEY");
				}
				CommonUtil.closePreparedStatementResultSet(ps,rs);
				return entprefkey ;
			} 
			catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps,rs);
			}				
		}
          	// FFT
		public static Long findRXTRFKEY(Connection conn,String rxtrfid)
				throws SQLException {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "select RXTRFKEY from rxtrf where  RXTRFID=? ";
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, rxtrfid);
				
				
				rs = ps.executeQuery();
				Long rxtrfkey = null;
				if (rs.next()) {
					rxtrfkey = rs.getLong("RXTRFKEY");
				}

				return rxtrfkey;
			} catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		}
		
		public static Long findPTNTTXFRKEY(Connection conn,String userid,  String storeNum,String firstName,String lastName)
				throws SQLException {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "select PTNTTXFRKEY from PTNTTXFR where  ";
				if(userid != null) {
					sql = sql + "USERID ='"+userid+"'";
				}
				if(storeNum != null) {
					sql = sql + " and STORENUM ='"+storeNum+"'";
				}
				if(firstName != null) {
					sql = sql + " and FRSTNM ='"+firstName+"'";
				}
				if(userid != null) {
					sql = sql + " and LSTNM ='"+lastName+"'";
				}
				ps = conn.prepareStatement(sql);
				
				rs = ps.executeQuery();
				Long ptnttxfrkey = null;
				if (rs.next()) {
					ptnttxfrkey = rs.getLong("PTNTTXFRKEY");
				}

				return ptnttxfrkey;
			} catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		}
		
		public static List<String> findRXNumbersbyPTNTKEY(Connection conn,Long ptntkey)
				throws SQLException {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<String > rxNumList = new ArrayList<String>();
			try {
				String sql = "select rxnum from rx where ptntkey = ? ";
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsLongParam(ps, 1, ptntkey);
				rs = ps.executeQuery();
				 
				while(rs.next()) {
					rxNumList.add( rs.getString("RXNUM"));
				}

				return rxNumList;
			} catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		}
		
		public static String findStoreNumberByPTNTKey(Connection conn,Long ptntkey)
				throws SQLException {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "select distinct storenum from rx where PTNTKEy= ? ";
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsLongParam(ps, 1, ptntkey);
				rs = ps.executeQuery();
				String storeNum = null;
				if (rs.next()) {
					storeNum = rs.getString("STORENUM");
				}

				return storeNum;
			} catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}
		}
		//HW4BService #HW4B changes Praveen T
		/**
		 * 
		 * @param conn
		 * @param txnNum
		 * @param storeNum
		 * @return
		 * @throws SQLException
		 */
		public static Map<String, Object> findDispenseDataByTxnNum(Connection conn, String txnNum, String storeNum) throws SQLException  {
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try
			{
				Map <String, Object> mapData = new HashMap<String, Object>();
				String sql = "select TXKEY, LSTUPDT,  TXID from TX  " +
					" where TXNNUM=? and STORENUM=?";
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, txnNum);
				CommonUtil.setPsStringParam(ps, 2, storeNum);
				rs = ps.executeQuery();
				if (rs.next()) {
					mapData.put("TXKEY",	rs.getLong("TXKEY"));
					mapData.put("LSTUPDT",	rs.getTimestamp("LSTUPDT"));				
					mapData.put("TXID", 	rs.getString("TXID"));
				}
				return mapData;
			}
			catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps,rs);
			}					
		}
		//HW4BService #HW4B changes Praveen T

		/**
		 * 
		 * @param conn
		 * @param txnNum
		 * @param storeNum
		 * @return
		 * @throws SQLException
		 */
		public static Long findDispenseKeyByTxnNum(Connection conn, String txnNum, String storeNum) throws SQLException {
			Map<String, Object> dataMap = findDispenseDataByTxnNum(conn, txnNum, storeNum);
			return (Long) dataMap.get("TXKEY");
		}
		
	public static List<FormularyRing> populateFormularyRingForPrescribedPack(Connection connection, String gTIN,
			String storeNum) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<FormularyRing> ringList = new ArrayList<FormularyRing>();
		Integer ringTypeKey = 0;
		String isActive = null;
		Long provKey = null;
		provKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_GETPROVKEY, storeNum);
		String naturalGtin = null;
		naturalGtin = gTIN.trim(); // removed leading and trailing spaces
		naturalGtin = naturalGtin.replaceFirst("^0+(?!$)", ""); // removed leading zeros
		String queryRingFromPricribedPackSQL = "select u.RINGTYPKEY  RINGID, u.ISACTIVE ISACTIVE from UPCINTERCHGLIST u  where  ltrim(trim(u.gtin),'0') = ? and u.provkey  = ?";
		preparedStatement = connection.prepareStatement(queryRingFromPricribedPackSQL);
		preparedStatement.setString(1, naturalGtin);
		preparedStatement.setLong(2, provKey);

		Long querytimer = System.currentTimeMillis();

		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			FormularyRing ring = new FormularyRing();
			ringTypeKey = resultSet.getInt("RINGID");
			ring.setRingId(ringTypeKey);
			isActive = resultSet.getString("ISACTIVE");
			ring.setIsActive(isActive);
			ringList.add(ring);
		}

		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return ringList;
	}
		
		
	public static List<FormularyRing> populateFormularyRingForPrescribedDrug(Connection connection, String din,
			String storeNum) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String queryRingFromPricribedDrugSQL = "select d.RINGTYPKEY  RINGID, d.ISACTIVE ISACTIVE from    DININTERCHGLIST d  where  ltrim(trim(d.din),'0') = ? and  d.provkey = ?";
		List<FormularyRing> ringList = new ArrayList<FormularyRing>();
		Integer ringTypeKey = 0;
		String isActive = null;
		Long provKey = null;
		provKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_GETPROVKEY, storeNum);
		String naturalDin = null;
		naturalDin = din.trim(); // removed leading and trailing spaces
		naturalDin = naturalDin.replaceFirst("^0+(?!$)", ""); // removed leading zeros
		preparedStatement = connection.prepareStatement(queryRingFromPricribedDrugSQL);
		preparedStatement.setString(1, naturalDin);
		preparedStatement.setLong(2, provKey);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			FormularyRing ring = new FormularyRing();
			ringTypeKey = resultSet.getInt("RINGID");
			ring.setRingId(ringTypeKey);
			isActive = resultSet.getString("ISACTIVE");
			ring.setIsActive(isActive);
			ringList.add(ring);
		}

		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
		return ringList;
	}
		
		public static String getDrugScheduleFromCRX_REF(Connection connection,String storenum,String din) throws SQLException {
		 String QUERYDRUGSCHEDULEFROMCRXREF = "SELECT s.CDDESCR DRG_SCHDL FROM Prov2Store_VW p,crx_reference c,DRGSCHDLTYP   s"
					+ " where p.STORENUM = ?  and c.din = ? and p.provkey = c.provkey and c.SCHDL_FED_CD = s.SHORTCD"
					+ " and s.SHORTCD is not null";
		 PreparedStatement preparedStatement = null ;
		 String drugScheduleCode = null;
		 preparedStatement = connection.prepareStatement(QUERYDRUGSCHEDULEFROMCRXREF);
		 preparedStatement.setString(1, storenum);
		 preparedStatement.setString(2, din);
		 ResultSet resultSet = null ;
		 resultSet = preparedStatement.executeQuery();
	     if (resultSet.next()) {
			drugScheduleCode = resultSet.getString("DRG_SCHDL");
		  }
			 
	     CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet); // VLAD FIXED: ORA-01000: maximum open cursors exceeded 
		 return drugScheduleCode;
		}
		
				
		public static Long findHW4BCount(Connection conn, String CNSMRID,String STORENUM) throws SQLException  {
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try
			{
				 String sql="SELECT count (*) noOfRecords FROM HW4B_EVENT HW WHERE HW.CNSMRID = ? and HW.STORENUM = ? ";
				 
				ps = conn.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, CNSMRID);
				CommonUtil.setPsLongParam(ps, 2, STORENUM);
				
				rs = ps.executeQuery();
				Long count = null ;
				if (rs.next()) {
					count  = rs.getLong("noOfRecords");
				}
		
				return count ;
			} 
			catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps,rs);
			}				
		}
		//HW4BService #HW4B changes Praveen T

		public static Boolean hasDigitalAcct(Connection connection, Long ptntkey) {
			String QUERY = "  select distinct 1 from  CDRPTNTMPNG  where VRFPHARMACYCHNLTYPKEY = 10 and ASSOCTYP = 'Customer' and custkey is not null " +
		                    "  and ptntkey is not null  and ASSOCFLAG = 'Y' and ptntkey = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = connection.prepareStatement(QUERY);
				ps.setLong(1, ptntkey);
				rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
			return false;
		}
		
		public static Boolean  hasAutoRefill(Connection connection,Long ptntkey) {
			String checkFlag = null;
			String QUERY =  " SELECT distinct  nvl(PPEP.RXAUTOFILL, PPEP.GLBAUOTFILL)  checkFlag FROM ENTPREF EP" +
			                " LEFT OUTER JOIN PRSNPREF PPEP on EP.ENTPREFKEY = PPEP.ENTPREFKEY " +
					        " where EP.PTNTKEY = ?  ";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = connection.prepareStatement(QUERY);
				ps.setLong(1, ptntkey);
				rs = ps.executeQuery();
				if (rs.next()) {
					checkFlag = rs.getString("checkFlag"); 
				}
				if(checkFlag!=null && checkFlag.equals("1")) {
					return true;
				}
				

			} catch (SQLException e) {

				e.printStackTrace();
			}
			return false;
		}
		
     
		public static Long findGPITypKey(Connection connection, String gpiNumber) throws SQLException {
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try
			{
				String sql = "select GPITYP.GPITYPKEY from GPITYP where GPITYPCD=?";
				ps = connection.prepareStatement(sql);
				CommonUtil.setPsStringParam(ps, 1, gpiNumber);
				rs = ps.executeQuery();
				Long gPITYPKEY = null;
				if (rs.next()) {
					gPITYPKEY = rs.getLong("GPITYPKEY");
					return gPITYPKEY;
				}
				
		
				return gPITYPKEY;
			}
			catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps,rs);
			}	
		}


	/*	public static String getDivisionByStoreNumber(Connection connection,String storeNumber) throws SQLException {
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try {
				String sql = "select division from store where storenum=?";
				ps = connection.prepareStatement(sql);
				CommonUtil.setPsLongParam(ps, 1, storeNumber);
				rs = ps.executeQuery();
				String division = null;
				if(rs.next()) {
					division=rs.getString("DIVISION");
					return division;
				}
			}catch (SQLException e) {
				throw e;
			} finally {
				CommonUtil.closePreparedStatementResultSet(ps,rs);
			}	
			return null;
		}*/

}
