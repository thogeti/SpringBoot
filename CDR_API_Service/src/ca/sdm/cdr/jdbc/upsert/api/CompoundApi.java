package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;
/**
 * Compound API used to persist Compound entity.
 * 
 * @author LTrevino
 *
 */
public class CompoundApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(CompoundApi.class);
	/*private final static String UPDATESQL =
	"UPDATE CMPND SET " +
		"CMPNDID=?,				ADMINRTECD=?,	UOMQTYCONVERFCTR=?,	NMFR=?,	NMENG=?"	+
		", CNSMRID=?,			PREPDIRCTN=?,	PRDCRID=?,			QTY=?,	RTEOFADMIN_CERXTYPKEY=?"	+
		", CMPNDUOM=?,	DSGFRMKEY=?, CMPNDSCHDLTYPKEY = ? "	+
	" WHERE CMPNDKEY=?";
*/
	//" +"
	private final static String UPDATESQL =
			"UPDATE CMPND SET " +
				"CMPNDID=?,	" +
				"ADMINRTECD=?,	" +
				"UOMQTYCONVERFCTR=?, " +
				"NMFR=?, " +
				"NMENG=?, "	+
				"CNSMRID=?, " +
				"PREPDIRCTN=?,	" +
				"PRDCRID=?, " +
				"QTY=?,	" +
				"RTEOFADMIN_CERXTYPKEY=nvl(?, RTEOFADMIN_CERXTYPKEY), "	+
				"CMPNDUOM=?, " +
				"DSGFRMKEY=nvl(?, DSGFRMKEY), " +
				"CMPNDSCHDLTYPKEY = nvl(?, CMPNDSCHDLTYPKEY) "	+
			" WHERE CMPNDKEY=?";

	private final static String INSERTSQL = 
	"INSERT INTO CMPND (" +
		"CMPNDID,			ADMINRTECD,	UOMQTYCONVERFCTR,	NMFR,	NMENG, "	+
		"CNSMRID,			PREPDIRCTN,	PRDCRID,			QTY,	RTEOFADMIN_CERXTYPKEY, "	+
		"CMPNDUOM,	DSGFRMKEY, CMPNDSCHDLTYPKEY,	CMPNDKEY "	+
	") VALUES (" +
		" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?" +
	")";
	
	/**
	 * Attempt to persist a Compound instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Compound doesn't exist, then insert a new record.
	 * If the Compound exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compound		Compound instance.
	 * 
	 * @return				CompoundKey value.
	 * 
	 * @throws SQLException
	 * @throws EntityNotFoundException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	public Long upsertCompound(Connection connection, String storeNum, Compound compound,Map <String, Long> drgPK,Map <String, Timestamp> drgTime,Map <String, Long> packPK,Map <String, Timestamp> packTime) throws EntityNotFoundException, SQLException,
			KeyNotFoundFromTableCacheException, InvalidInputException, NamingException, IOException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertCompound. store number " + storeNum + ", compound consumerId: " + compound.getConsumerId());}
			Long dosageFormKey = null;
			DosageForm dosageForm = compound.getDosageForm();
		//	Integer dosageFormId = (compound.getDosageForm() != null) ? compound.getDosageForm().getId() : null;
			if (dosageForm != null){
				dosageFormKey =TableCacheSingleton.getInstance(connection).getKeyFromObject(connection, "DSGFRM", dosageForm);
			}

			Map<String, Object> compoundData = FindUtil.findCompoundData(connection, compound.getConsumerId(),
					storeNum);
			Long compoundKey = (Long) compoundData.get("CMPNDKEY");
			Long rteOfAdminCerxTypeKey = (Long) compoundData.get("RTEOFADMIN_CERXTYPKEY");
			if (rteOfAdminCerxTypeKey != null && rteOfAdminCerxTypeKey.intValue() < 1)
				rteOfAdminCerxTypeKey = null;
			//added 60 prescribedCompound
			else
			{
				String adminRouteCode = (compound.getAdministrationRouteCode()!=null) ? compound.getAdministrationRouteCode().value() : null; //RteOfAdmin_CeRXTypKey
				Long adminRouteKey = getKeyFromCode(LT_RTEOFADMIN_CERXTYP, adminRouteCode);
				rteOfAdminCerxTypeKey = adminRouteKey;
			}
			//added 60 prescribedCompound
			// Long strengthUomTypeKey = (Long)
			// compoundData.get("STRNGTHUOMTYPKEY");
			// if (strengthUomTypeKey!=null && strengthUomTypeKey.intValue() <
			// 1)
			// strengthUomTypeKey = null;
			String strengthUom = compound.getUnitOfMeasure();
			// Persist main entity
			if (compoundKey == null) {
				compoundKey = insertCompound(connection, storeNum, compound, dosageFormKey, rteOfAdminCerxTypeKey, strengthUom);
			} else {
				// CMPND table doesn't have an Update Timestamp field.
				updateCompound(connection, storeNum, compoundKey, compound, dosageFormKey, rteOfAdminCerxTypeKey, strengthUom);
			}
			if (compound.getCompoundIngredients() != null && compound.getCompoundIngredients().size() > 0) {
				CompoundIngredientApi compoundIngredientApi = new CompoundIngredientApi();
				for (CompoundIngredient compoundIngredient : compound.getCompoundIngredients()) {
					compoundIngredientApi.upsertCompoundIngredient(connection, storeNum, compoundIngredient, compoundKey,drgPK,drgTime,packPK,packTime);
				}
			}
			return compoundKey;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertCompound. store number " + storeNum + ", compound consumerId: " + compound.getConsumerId());}
		}
	}
	
	/**
	 * Attempt to update a Compound instance, which is matched by compoundKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundKey	Compound Key.
	 * @param compound		Compound instance.
	 * 
	 * @return				CompoundKey value.
	 * 
	 * @throws SQLException
	 * @throws InvalidInputException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long updateCompound(Connection connection, String storeNum, Long compoundKey, Compound compound, Long dosageFormKey, Long rteOfAdminCerxTypeKey, String strengthUom) throws SQLException, InvalidInputException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, compoundKey, compound, dosageFormKey, rteOfAdminCerxTypeKey, strengthUom);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Compound intances updated: " + res + ". CompoundKey: " + compoundKey + ", consumerId: '" + compound.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return compoundKey;
	}

	/**
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * Attempt to insert a new Compound into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compound		Compound instance.
	 * 
	 * @return				CompoundKey.
	 * 
	 * @throws SQLException
	 * @throws  
	 */
	private Long insertCompound(Connection connection, String storeNum, Compound compound, Long dosageFormKey, Long rteOfAdminCerxTypeKey, String strengthUom) throws InvalidInputException, SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long compoundKey = IdGenerator.generate(connection, "CMPND");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, compoundKey, compound, dosageFormKey, rteOfAdminCerxTypeKey, strengthUom);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Compound intances updated: " + res + ". CompoundKey: " + compoundKey + ", consumerId: '" + compound.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return compoundKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param compoundKey	Compound Key.
	 * @param compound		Compound instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long compoundKey, Compound compound, Long dosageFormKey, Long rteOfAdminCerxTypeKey, String strengthUom ) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		String compoundId = compound.getConsumerId(); // awaiting mapping document
		setPsStringParam(1, compoundId);
		String adminRteCd = null;
		setPsStringParam(2, adminRteCd);
		Double uomQuantityConversionFactor = (compound.getUofMQuantityConversionFactor()!=null) ? compound.getUofMQuantityConversionFactor().doubleValue() : null;
		setPsDoubleParam(3, uomQuantityConversionFactor);
		setPsStringParam(4, compound.getNameFrench());
		setPsStringParam(5, compound.getNameEnglish());
		setPsStringParam(6, compound.getConsumerId());
		setPsStringParam(7, compound.getPreperationdirections());
		setPsStringParam(8, compound.getProducerId());
		Double quantity = (compound.getQuantity()!=null) ? compound.getQuantity().doubleValue() : null;
		setPsDoubleParam(9, quantity);
		setPsLongParam(10, rteOfAdminCerxTypeKey); 
		setPsStringParam(11, strengthUom); 
		setPsLongParam(12, dosageFormKey);
		String cmpndSchTypeCode = (compound.getSchedule()!=null) ? compound.getSchedule().value() : null;
		//select dst.DRGSCHDLTYPKEY from DrgSchdlTyp dst WHERE dst.CdDescr = '[drugSchTypeCode]'
		Long cmpndSchTypeKey = getKeyFromCode(LT_DRGSCHDLTYP, cmpndSchTypeCode);
		setPsLongParam(13, cmpndSchTypeKey);
		setPsLongParam(14, compoundKey);
	}
}
