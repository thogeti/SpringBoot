package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.ActiveIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/**
 * Molecule API used to persist Molecule entity.
 * 
 * @author LTrevino
 *
 */
public class MoleculeApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(MoleculeApi.class);
	private final static String INSERTSQL = 
	"INSERT INTO MLCL(" +
	"	MLCLID,		ISREFRIGERATED,		MLCLNM,	STRNGTH,	CNSMRID,"	+
	"	PRDCRID,	STRNGTHUOMTYPKEY,	MLCLKEY	"				+
	") VALUES (" +
		" ?, ?, ?, ?, ?, ?, ?, ?" +
	")";

	private final static String UPDATESQL = 
	"UPDATE MLCL SET " +
	"	MLCLID=?,	ISREFRIGERATED=?,	MLCLNM=?,	STRNGTH=?,	CNSMRID=?," +
	"	PRDCRID=?,	STRNGTHUOMTYPKEY=? " +
	" WHERE MLCLKEY=?";

	/**
	 * Attempt to persist a Molecule instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Molecule doesn't exist, then insert a new record.
	 * If the Molecule exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param molecule		Molecule instance.
	 * 
	 * @return				MoleculeKey value.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 * @throws Exception 
	 */
	public Long upsertMolecule(Connection connection, String drugConsumerId, String storeNum, Molecule molecule)
			throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		// Persist main entity
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertMolecule, drugConsumerId : " + drugConsumerId);	}	
			Long moleculeKey;
			moleculeKey = FindUtil.findMoleculeKey(connection, StringUtil.emptyIfNull(molecule.getConsumerId()),
					storeNum);
			// Long drugKey = FindUtil.findDrugKey(connection, drugConsumerId,
			// storeNum);

			if (moleculeKey == null) {
				moleculeKey = insertMolecule(connection, molecule /* , drugKey */);
			} else {
				// MLCL table doesn't have an Update Timestamp field.
				updateMolecule(connection, moleculeKey, molecule);
			}

			List<ActiveIngredient> activeIngredients = molecule.getAdditionalIngredient();
			if (activeIngredients != null && activeIngredients.size() > 0) {
				ActiveIngredientApi activeIngredientApi = new ActiveIngredientApi();
				String moleculeConsumerId = StringUtil.emptyIfNull(molecule.getConsumerId());
				for (ActiveIngredient iActiveIngredient : molecule.getAdditionalIngredient()) {
					activeIngredientApi.upsertActiveIngredient(connection, moleculeConsumerId, storeNum,
							iActiveIngredient);
				}
			}

			List<ActiveIngredient> additionalIngredients = molecule.getAdditionalIngredient();
			if (additionalIngredients != null && additionalIngredients.size() > 0) {
				ActiveIngredientApi activeIngredientApi = new ActiveIngredientApi();
				String moleculeConsumerId = StringUtil.emptyIfNull(molecule.getConsumerId());
				for (ActiveIngredient iAdditionalIngredient : additionalIngredients) {
					activeIngredientApi.upsertActiveIngredient(connection, moleculeConsumerId, storeNum,
							iAdditionalIngredient);
				}
			}
			return moleculeKey;
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertMolecule, drugConsumerId : " + drugConsumerId);	}	
		}
	}	
	
	
	/**
	 * Attempt to update a Molecule instance, which is matched by moleculeKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param moleculeKey	Molecule Key.
	 * @param molecule		Molecule instance.
	 * 
	 * @return				MoleculeKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long updateMolecule(Connection connection, Long moleculeKey, Molecule molecule) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(moleculeKey, molecule);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Molecule intances updated: " + res + ". MoleculeKey: " + moleculeKey + ", consumerId: '" + molecule.getConsumerId() + "'.");}
		return moleculeKey;
	}

	
	/**
	 * Attempt to insert a new Molecule into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param molecule		Molecule instance.
	 * 
	 * @return				MoleculeKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private Long insertMolecule(Connection connection, Molecule molecule /*, Long drugKey */) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long moleculeKey = IdGenerator.generate(connection, "MLCL");
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(moleculeKey, molecule);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Molecule intances updated: " + res + ". MoleculeKey: " + moleculeKey + ", consumerId: '" + molecule.getConsumerId() + "'.");}
		return moleculeKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param moleculeKey	Molecule Key.
	 * @param molecule		Molecule instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(Long moleculeKey, Molecule molecule) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException {
		setPsStringParam(1, String.valueOf(moleculeKey)); // moleculeId field
		setPsStringParam(2, CommonUtil.convertBooleanToYesNoFlag(molecule.isIsRefrigerated()));
		setPsStringParam(3, molecule.getMoleculeName());
		setPsDoubleParam(4, molecule.getStrength());
		setPsStringParam(5, molecule.getConsumerId());
		setPsStringParam(6, molecule.getProducerId());
		
		String strengthUomCode = molecule.getStrengthUOMCode();
		//select STRNGTHUOMTYP.* from STRNGTHUOMTYP WHERE STRNGTHUOMCD=?
		Long strengthUomKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUomCode);
		setPsLongParam(7, strengthUomKey);
		setPsLongParam(8, moleculeKey);
	}
}

