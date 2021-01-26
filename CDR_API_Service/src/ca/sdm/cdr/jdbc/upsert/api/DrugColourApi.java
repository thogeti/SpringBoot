package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.Colour;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/**
 * Colour API used to persist Colour entity.
 * 
 * @author LTrevino
 *
 */
public class DrugColourApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(DrugColourApi.class);
	private final static String UPDATESQL = "UPDATE DRGCLR SET " 
	+ "	DRGCLRTYPKEY=?,	DRGKEY=?"
	+ " WHERE DRGCLRKEY=?";

	private final static String INSERTSQL = "INSERT INTO DRGCLR(" 
	+ "	DRGCLRTYPKEY,	DRGKEY,	DRGCLRKEY"
	+ ") VALUES (" + " ?, ?, ?" + ")";
	
	/**
	 * Attempt to persist a Colour instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Colour doesn't exist, then insert a new record.
	 * If the Colour exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param colour		Colour instance.
	 * 
	 * @return				ColourKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 */
	public Long upsertColour(Connection connection, String drugConsumerId, String storeNum, Colour colour) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertColour drugConsumerId : " + drugConsumerId + ", storeNum " + storeNum);}
			String colourDescr = colour.value();
			Long colourKey;
			colourKey = FindUtil.findDrugColourKey(connection, drugConsumerId, storeNum, colourDescr);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap = FindUtil.findDrugKey(connection, drugConsumerId, storeNum);
			Long drugKey = (Long)dataMap.get("DRGKEY");
			if (colourKey == null) {
				colourKey = insertColour(connection, colour, drugKey);
			} else {
				// DRGCLR table doesn't have an Update Timestamp field.
				updateColour(connection, colourKey, colour, drugKey);
			}
			return colourKey;
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertColour drugConsumerId : " + drugConsumerId + ", storeNum " + storeNum);}
		}
	}
	
	
	
	/**
	 * Attempt to update a Colour instance, which is matched by colourKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param colourKey		Colour Key.
	 * @param colour		Colour instance.
	 * 
	 * @return				ColourKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	
	private Long updateColour(Connection connection, Long colourKey, Colour colour, Long drugKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(colourKey, colour, drugKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Colour intances updated: " + res + ". ColourKey: " + colourKey + ", drugKey: '" + drugKey + "'.");}
		return colourKey;
	}

	
	/**
	 * Attempt to insert a new Colour into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param colour		Colour instance.
	 * 
	 * @return				ColourKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	
	private Long insertColour(Connection connection, Colour colour, Long drugKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Long colourKey = IdGenerator.generate(connection, "DRGCLR");
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(colourKey, colour, drugKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Colour intances updated: " + res + ". ColourKey: " + colourKey + ", drugKey: '" + drugKey + "'.");}
		return colourKey;
	}
	
	
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param colourKey		Colour Key.
	 * @param colour		Colour instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(Long drugColourKey, Colour colour, Long drugKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {

		String colourName = colour.value();
		//select DRGCLRTYPKEY from DrgClrTyp WHERE upper(CdDescr) = upper('white')
		Long drugColourTypeKey = null ;
		if (colourName!=null) 
			drugColourTypeKey = getKeyFromCode(LT_DRGCLRTYP, colourName);
		CommonUtil.setPsLongParam(ps, 1, drugColourTypeKey);
		CommonUtil.setPsLongParam(ps, 2, drugKey);
		CommonUtil.setPsLongParam(ps, 3, drugColourKey);
	}
}

