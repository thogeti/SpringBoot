package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyTest;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

/**
 * AllergyTest API used to persist AllergyTest entity.
 * 
 * @author LTrevino
 *
 */
public class AllergyTestApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(AllergyTestApi.class);
	private final static String INSERTSQL = 
	"insert into ALRGYTST (" +
		"TSTDTTIME,	TSTRSLT,	CNSMRID,	PRDCRID,	ALRGYTSTTYPKEY,	" 		+
		"PTNTALRGYKEY,	ALRGYTSTKEY" 		+	
	") values (" +
		CommonUtil.getPsToDateFunctionStr() +", ?, ?, ?, ?, ?, ?" +
	")";

	private final static String UPDATESQL =
	"update ALRGYTST set " +
	"	TSTDTTIME=" + CommonUtil.getPsToDateFunctionStr() + ",	TSTRSLT=?,	CNSMRID=?,	PRDCRID=?,	ALRGYTSTTYPKEY=?,"	+
	"	PTNTALRGYKEY=? "	+
	" where ALRGYTSTKEY=?";
	
	/**
	 * Attempt to persist a AllergyTest instance, which is searched by consumerId and storeNum.
	 * 
	 * If the AllergyTest doesn't exist, then insert a new record.
	 * If the AllergyTest exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergyTest	AllergyTest instance.
	 * 
	 * @return				AllergyTestKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 * @throws EntityNotFoundException 
	 */
	public Long upsertAllergyTest(Connection connection, String storeNum, AllergyTest allergyTest, Long allergyKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertAllergyTest. store number " + storeNum + ", allergyKey: " + allergyKey);}
			// Long allergyKey = FindUtil.findAllergyKey(connection,
			// allergyConsumerId, storeNum);
			Long allergyTestKey;
			allergyTestKey = FindUtil.findAllergyTestKey(connection, allergyTest.getConsumerId(), storeNum);
			if (allergyTestKey == null) {
				allergyTestKey = insertAllergyTest(connection, storeNum, allergyTest, allergyKey);
			} else {
				// ALRGYTST table doesn't have an Update Timestamp field.
				updateAllergyTest(connection, storeNum, allergyTestKey, allergyTest, allergyKey);
			}
			return allergyTestKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertAllergyTest. store number " + storeNum + ", allergyKey: " + allergyKey);}
		}
	}
	
	/**
	 * Attempt to update a AllergyTest instance, which is matched by allergyTestKey.
	 * 
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param allergyTestKey	AllergyTest Key.
	 * @param allergyTest		AllergyTest instance.
	 * 
	 * @return					AllergyTestKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long updateAllergyTest(Connection connection, String storeNum, Long allergyTestKey, AllergyTest allergyTest, Long allergyKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, allergyTestKey, allergyTest, allergyKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total AllergyTest intances updated: " + res + ". AllergyTestKey: " + allergyTestKey + ", consumerId: '" + allergyTest.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return allergyTestKey;
	}

	/**
	 * Attempt to insert a new AllergyTest into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param allergyTest	AllergyTest instance.
	 * 
	 * @return				AllergyTestKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long insertAllergyTest(Connection connection, String storeNum, AllergyTest allergyTest, Long allergyKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long allergyTestKey = IdGenerator.generate(connection, "ALRGYTST");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, allergyTestKey, allergyTest, allergyKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total AllergyTest intances updated: " + res + ". AllergyTestKey: " + allergyTestKey + ", consumerId: '" + allergyTest.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return allergyTestKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps				PreparedStatement object.
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param allergyTestKey	AllergyTest Key.
	 * @param allergyTest		AllergyTest instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long allergyTestKey, AllergyTest allergyTest, Long allergyKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		String testDt = CommonUtil.toTimestampStr(allergyTest.getTestDatetime());
		setPsStringParam(1, testDt);
		setPsStringParam(2, allergyTest.getTestResult());
		setPsStringParam(3, allergyTest.getConsumerId());
		setPsStringParam(4, allergyTest.getProducerId());
		
		String allergyTestTypeCode = allergyTest.getTestType();
		//select * from ALRGYTSTTYP where CDDESCR='Type 1'
		Long allergyTestTypeKey = getKeyFromCode(LT_ALRGYTSTTYP, allergyTestTypeCode);
		setPsLongParam(5, allergyTestTypeKey);
		setPsLongParam(6, allergyKey);
		setPsLongParam(7, allergyTestKey);
	}
}
