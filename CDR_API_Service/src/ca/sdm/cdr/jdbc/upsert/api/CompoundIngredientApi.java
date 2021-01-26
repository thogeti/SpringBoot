package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/**
 * CompoundIngredient API used to persist CompoundIngredient entity.
 * 
 * @author LTrevino
 *
 */
public class CompoundIngredientApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(CompoundIngredientApi.class);
	

	private final static String INSERTSQL = 
			"INSERT INTO CMPNDINGRDNT (" +
					"QTYUSED,	PACKSZUOMTYPKEY,	CMPNDKEY,	PACKKEY,	CMPNDINGRDNTKEY"												+
				") VALUES (" +
					" ?, ?, ?, ?, ?" +
				")";

	private final static String UPDATESQL =
			"UPDATE CMPNDINGRDNT SET " +
					" QTYUSED=?,	PACKSZUOMTYPKEY=?,	CMPNDKEY=?,	PACKKEY=?"	+
				" WHERE CMPNDINGRDNTKEY=?";

	/**
	 * Attempt to persist a CompoundIngredient instance, which is searched by consumerId and storeNum.
	 * 
	 * If the CompoundIngredient doesn't exist, then insert a new record.
	 * If the CompoundIngredient exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundIngredient			CompoundIngredient instance.
	 * 
	 * @return				CompoundIngredientKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 * @throws InvalidInputException 
	 */
	public Long upsertCompoundIngredient(Connection connection, String storeNum, CompoundIngredient compoundIngredient, Long compoundKey,Map <String, Long> drgPK,Map <String, Timestamp> drgTime,Map <String, Long> packPK,Map <String, Timestamp> packTime)
			throws SQLException, InvalidInputException, KeyNotFoundFromTableCacheException, EntityNotFoundException,
			NamingException, IOException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertCompoundIngredient. store number " + storeNum + ", compoundKey: "+compoundKey);}
			Pack pack = compoundIngredient.getPack();
			String packConsumerId = (pack != null) ? pack.getConsumerId() : null;
			Long drugKey = null;
			Drug drug = pack.getDrug();
			if (drug != null) {
				DrugApi drugApi;
				drugApi = new DrugApi();
				drugKey = drugApi.upsertDrug(connection, storeNum, drug,drgPK,drgTime);
			}
			Long packKey=null;
			
			if (pack!=null) {
				PackApi packApi = new PackApi();
				String drugConsumerId = (pack.getDrug()!=null) ? pack.getDrug().getConsumerId() : null;
				
				HashMap<String,String> drugMap= new HashMap<String,String>();
				packKey = packApi.upsertPack(connection, drugConsumerId, storeNum, pack,drgPK,drgTime,packPK,packTime);
			}
			/*packKey = FindUtil.findPackKey(connection, packConsumerId, storeNum);
			if (packKey == null || packKey == 0L) {
				PackApi packApi = new PackApi();
				packKey = packApi.upsertPack(connection, null, storeNum, pack);
			}
			 Map<String, Object> dataMap = new HashMap<String, Object>();
			  FindUtil.findPackData(connection, pack.getConsumerId(), storeNum,pack,packPK,packTime);
			 packKey = (Long)dataMap.get("PACKKEY");
			 PackApi packApi = new PackApi();
			if (packKey == null) {
				packKey = packApi.insertPack(connection, storeNum, pack, drugKey);
			} else {
				// PACK table doesn't have an Update Timestamp field.
				packApi.updatePack(connection, storeNum, packKey, pack, drugKey);
			}*/
			if (compoundKey == null) {
				throw new InvalidInputException("Compound Key not provided for Compound Ingredient");
			}
			// String quantityusedUOM= compoundIngredient.getQuantityusedUOM();
			String packSizeUOMTypeCode = compoundIngredient.getPack().getPackSizeUOMCode().getPackSizeUoMTypeCode();

			Long packSizeUOMTypeKey = getKeyFromCode(LT_PACKSZUOMTYP,
					packSizeUOMTypeCode);
			if (packSizeUOMTypeKey == null || packSizeUOMTypeKey == 0L) {
				throw new InvalidInputException("Strength UOM Type Key not provided for Compound Ingredient");
			}

			// Persist main entity
			Long compoundIngredientKey = FindUtil.findCompoundIngredientKey(connection, compoundKey, packKey,
					packSizeUOMTypeKey);
			if (compoundIngredientKey == null) {
				compoundIngredientKey = insertCompoundIngredient(connection, storeNum, compoundIngredient, compoundKey, packKey);
			} else {
				// CMPNDINGRDNT table doesn't have an Update Timestamp field.
				updateCompoundIngredient(connection, storeNum, compoundIngredientKey, compoundIngredient, compoundKey, packKey);
			}

			return compoundIngredientKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertCompoundIngredient. store number " + storeNum + ", compoundKey: "+compoundKey);}
		}
	}	
	
	
	/**
	 * Attempt to update a CompoundIngredient instance, which is matched by compoundIngredientKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundIngredientKey		CompoundIngredient Key.
	 * @param compoundIngredient			CompoundIngredient instance.
	 * 
	 * @return				CompoundIngredientKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long updateCompoundIngredient(Connection connection, String storeNum, Long compoundIngredientKey, CompoundIngredient compoundIngredient, Long compoundKey, Long packKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, compoundIngredientKey, compoundIngredient, compoundKey, packKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total CompoundIngredient intances updated: " + res + ". CompoundIngredientKey: " + compoundIngredientKey + ", storeNum: '" + storeNum + "'.");}
		return compoundIngredientKey;
	}

	/**
	 * Attempt to insert a new CompoundIngredient into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundIngredient			CompoundIngredient instance.
	 * 
	 * @return				CompoundIngredientKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	
	private Long insertCompoundIngredient(Connection connection, String storeNum, CompoundIngredient compoundIngredient, Long compoundKey, Long packKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long compoundIngredientKey = IdGenerator.generate(connection, "CMPNDINGRDNT");
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, compoundIngredientKey, compoundIngredient, compoundKey, packKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total CompoundIngredient intances updated: " + res + ". CompoundIngredientKey: " + compoundIngredientKey + ", storeNum: '" + storeNum + "'.");}
		return compoundIngredientKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundIngredientKey		CompoundIngredient Key.
	 * @param compoundIngredient			CompoundIngredient instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long compoundIngredientKey, CompoundIngredient compoundIngredient, Long compoundKey, Long packKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException {
		setPsBigDecimalParam(1, compoundIngredient.getQuantityUsed());
		String packSizeUomTypeCode = (compoundIngredient.getPack()!=null && compoundIngredient.getPack().getPackSizeUOMCode()!=null) ? compoundIngredient.getPack().getPackSizeUOMCode().getPackSizeUoMTypeCode() : null;
		Long packSizeUomTypeKey = getKeyFromCode(LT_PACKSZUOMTYP, packSizeUomTypeCode);
		setPsLongParam(2, packSizeUomTypeKey);
		setPsLongParam(3, compoundKey);
		setPsLongParam(4, packKey);
		setPsLongParam(5, compoundIngredientKey);
	}
}
