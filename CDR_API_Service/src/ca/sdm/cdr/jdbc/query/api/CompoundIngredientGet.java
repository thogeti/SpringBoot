package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PACKSZUOMTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;

public class CompoundIngredientGet extends CDRGet {

	private static Logger logger = LogManager.getLogger(CompoundIngredientGet.class);
	private final static String QUERYSQL = "SELECT CMPNDINGRDNT.QTYUSED, CMPNDINGRDNT.CMPNDINGRDNTKEY, CMPNDINGRDNT.PACKSZUOMTYPKEY, CMPNDINGRDNT.PACKKEY, "
			+ "PACK.PACKID PACK_PACKID, PACK.ALTRNTVPACKSZ PACK_ALTRNTVPACKSZ, PACK.ALTRNTVPACKSZUOM PACK_ALTRNTVPACKSZUOM, PACK.STRNGTH PACK_STRNGTH, PACK.GTIN PACK_GTIN, "
			+ "PACK.ISACTFLG PACK_ISACTFLG, PACK.CNSMRID PACK_CNSMRID, PACK.ISCRNTFLG PACK_ISCRNTFLG, PACK.PRDCRID PACK_PRDCRID, PACK.MFCTRDISCNTDDT PACK_MFCTRDISCNTDDT, "
			+ "PACK.PACKSZ PACK_PACKSZ, PACK.MSTRID PACK_MSTRID, PACK.PACKKEY PACK_PACKKEY, PACK.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY, PACK.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, "
			+ "PACK.DRGKEY PACK_DRGKEY " 
			+ "FROM CMPNDINGRDNT CMPNDINGRDNT "
			+ "LEFT OUTER JOIN PACK PACK ON CMPNDINGRDNT.PACKKEY = PACK.PACKKEY "
			+ "WHERE CMPNDINGRDNT.CMPNDKEY = ?";

	public List<CompoundIngredient> fetchList(Connection connection, Long compoundKey) throws SQLException, IOException, NamingException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		try {
			return populate(connection, compoundKey); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CodeNotFoundFromTableCacheException e) {
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
		}
	}

	private List<CompoundIngredient> populate(Connection connection, Long compoundKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException,
			DatatypeConfigurationException {

		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: CompoundIngredientGet.populate. compoundKey : " + compoundKey);}

		List<CompoundIngredient> compoundIngredientList = new ArrayList<CompoundIngredient>(); 

		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
		preparedStatement=connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, compoundKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: CompoundIngredientGet. compoundKey : " + compoundKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: CompoundIngredientGet. compoundKey : " + compoundKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while (resultSet.next()) {
			CompoundIngredient compoundIngredient = new CompoundIngredient();
			compoundIngredient.setQuantityUsed(CommonUtil.getBigDecimal("QTYUSED",resultSet));
			Long quantityusedUOMKey = CommonUtil.getLong("PACKSZUOMTYPKEY",resultSet);
			if (quantityusedUOMKey != null) {
				compoundIngredient.setQuantityusedUOM(getCodeFromKey(LT_PACKSZUOMTYP, quantityusedUOMKey));
			}
         //Pack pack = populatePAck();
			Pack pack = CommonUtil.populatePack(resultSet);
			if (pack != null) {
				compoundIngredient.setPack(pack);
				Long drugKey = CommonUtil.getLong("PACK_DRGKEY",resultSet);
				if (drugKey != null) {
					DrugGet drugGet = new DrugGet();
					Drug drug = drugGet.fetch(connection, drugKey);
					if (drug != null) {
						pack.setDrug(drug);
					}
				}
			};
			compoundIngredientList.add(compoundIngredient);
		}
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: CompoundIngredientGet.populate. compoundKey : " + compoundKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return compoundIngredientList;
	}
}
