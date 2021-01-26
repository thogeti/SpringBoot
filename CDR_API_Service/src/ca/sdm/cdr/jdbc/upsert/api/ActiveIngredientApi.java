package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.ActiveIngredient;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

/**
 * ActiveIngredient API used to persist ActiveIngredient entity.
 * 
 * @author LTrevino
 *
 */
public class ActiveIngredientApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(ActiveIngredientApi.class);

	private final static String INSERTSQL = 
	"INSERT INTO ACTINGRDNT(" +
	"	CHMCLNMALTRNTV,	CHMCLNMENG,			CHMCLNMFR,	STRNGTH,		CNSMRID,"	+
	"	PRDCRID,		STRNGTHUOMTYPKEY,	MLCLKEY,	ACTINGRDNTKEY"				+
	") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String UPDATESQL = 
	"UPDATE ACTINGRDNT SET " +
	"	CHMCLNMALTRNTV=?,	CHMCLNMENG=?,			CHMCLNMFR=?,	STRNGTH=?,	CNSMRID=?," +
	"	PRDCRID=?,			STRNGTHUOMTYPKEY=?,		MLCLKEY=?" +
	" WHERE ACTINGRDNTKEY=?";
	
	/**
	 * Attempt to persist a ActiveIngredient instance, which is searched by consumerId and storeNum.
	 * 
	 * If the ActiveIngredient doesn't exist, then insert a new record.
	 * If the ActiveIngredient exists, then update its data.
	 * 
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param activeIngredient	ActiveIngredient instance.
	 * 
	 * @return					ActiveIngredientKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 */
	public Long upsertActiveIngredient(Connection connection, String moleculeConsumerId, String storeNum, ActiveIngredient activeIngredient) throws IOException, SQLException, KeyNotFoundFromTableCacheException, NamingException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertActiveIngredient. molecule consumerId: " + moleculeConsumerId + ", store number " + storeNum);}
			// Persist main entity
			Long moleculeKey;
			moleculeKey = FindUtil.findMoleculeKey(connection, moleculeConsumerId, storeNum);
			Long activeIngredientKey = FindUtil.findActiveIngredientKey(connection,
					StringUtil.emptyIfNull(activeIngredient.getConsumerId()), storeNum);
			if (activeIngredientKey == null) {
				activeIngredientKey = insertActiveIngredient(connection, activeIngredient, moleculeKey);
			} else {
				// ActIngrdnt table doesn't have an Update Timestamp field.
				updateActiveIngredient(connection, activeIngredientKey, activeIngredient, moleculeKey);
			}

			return activeIngredientKey;
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
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertActiveIngredient. molecule consumerId: " + moleculeConsumerId + ", store number " + storeNum);}
		}
	}	
	
	/**
	 * Attempt to update a ActiveIngredient instance, which is matched by activeIngredientKey.
	 * 
	 * @param consumerId				Consumer ID.
	 * @param storeNum					Store Number.
	 * @param activeIngredientKey		ActiveIngredient Key.
	 * @param activeIngredient			ActiveIngredient instance.
	 * 
	 * @return							ActiveIngredientKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long updateActiveIngredient(Connection connection, Long activeIngredientKey, ActiveIngredient activeIngredient, Long moleculeKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(activeIngredientKey, activeIngredient, moleculeKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total ActiveIngredient intances updated: " + res + ". ActiveIngredientKey: " + activeIngredientKey + ", consumerId: '" + activeIngredient.getConsumerId() + "'.");}
		return activeIngredientKey;
	}

	
	/**
	 * Attempt to insert a new ActiveIngredient into corresponding database table.
	 * 
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param activeIngredient	ActiveIngredient instance.
	 * 
	 * @return					ActiveIngredientKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long insertActiveIngredient(Connection connection, ActiveIngredient activeIngredient, Long moleculeKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long activeIngredientKey = IdGenerator.generate(connection, "ActIngrdnt");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(activeIngredientKey, activeIngredient, moleculeKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total ActiveIngredient intances updated: " + res + ". ActiveIngredientKey: " + activeIngredientKey + ", consumerId: '" + activeIngredient.getConsumerId() + "'.");}
		return activeIngredientKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps					PreparedStatement object.
	 * @param consumerId			Consumer ID.
	 * @param storeNum				Store Number.
	 * @param activeIngredientKey	ActiveIngredient Key.
	 * @param activeIngredient		ActiveIngredient instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(Long activeIngredientKey, ActiveIngredient activeIngredient, Long moleculeKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		setPsStringParam(1, activeIngredient.getChemicalNameAlternative());
		setPsStringParam(2, activeIngredient.getChemicalNameEnglish());
		setPsStringParam(3, activeIngredient.getChemicalNameFrench());
		setPsDoubleParam(4, activeIngredient.getStrength());
		setPsStringParam(5, activeIngredient.getConsumerId());
		setPsStringParam(6, activeIngredient.getProducerId());
		String strengthUomCode = activeIngredient.getStrengthUOMCode();
		//select STRNGTHUOMTYP.* from STRNGTHUOMTYP WHERE STRNGTHUOMCD=?
		Long strengthUomKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUomCode);
		setPsLongParam(7, strengthUomKey);
		setPsLongParam(8, moleculeKey);
		setPsLongParam(9, activeIngredientKey);
	}
}
