package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.ActiveIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;

public class MoleculeGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(MoleculeGet.class);
	private final static String QUERYSQL = "SELECT "
			+ "MLCL.MLCLID MLCL_MLCLID, MLCL.ISREFRIGERATED MLCL_ISREFRIGERATED, MLCL.MLCLNM MLCL_MLCLNM, MLCL.STRNGTH MLCL_STRNGTH, MLCL.CNSMRID MLCL_CNSMRID, "
			+ "MLCL.PRDCRID MLCL_PRDCRID, MLCL.STRNGTHUOMTYPKEY MLCL_STRNGTHUOMTYPKEY, MLCL.MLCLKEY MLCL_MLCLKEY, "
			+ "ACTINGRDNT.CHMCLNMALTRNTV ACTINGRDNT_CHMCLNMALTRNTV, ACTINGRDNT.CHMCLNMENG ACTINGRDNT_CHMCLNMENG, ACTINGRDNT.CHMCLNMFR ACTINGRDNT_CHMCLNMFR, ACTINGRDNT.STRNGTH ACTINGRDNT_STRNGTH,	ACTINGRDNT.CNSMRID ACTINGRDNT_CNSMRID,	"
			+ "ACTINGRDNT.PRDCRID ACTINGRDNT_PRDCRID, ACTINGRDNT.ACTINGRDNTKEY ACTINGRDNT_ACTINGRDNTKEY, ACTINGRDNT.STRNGTHUOMTYPKEY ACTINGRDNT_STRNGTHUOMTYPKEY "
			+ "FROM MLCL "
			+ "LEFT OUTER JOIN ACTINGRDNT ACTINGRDNT ON ACTINGRDNT.MLCLKEY = MLCL.MLCLKEY "			
			+ "WHERE MLCL.MLCLKEY = ?";

	public Molecule fetch(Connection connection, Long moleculeKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			return populate(connection, moleculeKey);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
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

	private Molecule populate(Connection connection, Long moleculeKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: MoleculeGet.populate. moleculeKey : " + moleculeKey);}

		Molecule molecule = null;
		PreparedStatement preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, moleculeKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: MoleculeGet. moleculeKey : " + moleculeKey );}
		ResultSet resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: MoleculeGet. moleculeKey : " + moleculeKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while (resultSet.next()) {
			if (molecule == null) {
				molecule = new Molecule();
				molecule.setConsumerId(resultSet.getString("MLCL_CNSMRID"));
				molecule.setIsRefrigerated(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("MLCL_ISREFRIGERATED")));
				molecule.setMoleculeName(resultSet.getString("MLCL_MLCLNM"));
				molecule.setProducerId(resultSet.getString("MLCL_PRDCRID"));
				molecule.setStrength(CommonUtil.getDouble("MLCL_STRNGTH",resultSet));
				Long strengthUomTypeKey = CommonUtil.getLong("MLCL_STRNGTHUOMTYPKEY",resultSet);
				if (strengthUomTypeKey != null) {
					String strengthUomTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, strengthUomTypeKey);
					molecule.setStrengthUOMCode(strengthUomTypeCode);
				}
				;
			};
			
			Long activeIngredientKey = CommonUtil.getLong("ACTINGRDNT_ACTINGRDNTKEY",resultSet);
			if(activeIngredientKey!=null)
			{
				ActiveIngredient activeIngredient = new ActiveIngredient();
				activeIngredient.setChemicalNameAlternative(resultSet.getString("ACTINGRDNT_CHMCLNMALTRNTV"));
				activeIngredient.setChemicalNameEnglish(resultSet.getString("ACTINGRDNT_CHMCLNMENG"));
				activeIngredient.setChemicalNameFrench(resultSet.getString("ACTINGRDNT_CHMCLNMFR"));
				activeIngredient.setConsumerId(resultSet.getString("ACTINGRDNT_CNSMRID"));
				activeIngredient.setProducerId(resultSet.getString("ACTINGRDNT_PRDCRID"));
				activeIngredient.setStrength(CommonUtil.getDouble("ACTINGRDNT_STRNGTH",resultSet));
				Long StrengthUOMTypeKey = CommonUtil.getLong("ACTINGRDNT_STRNGTHUOMTYPKEY",resultSet);
				if (StrengthUOMTypeKey != null) {
					String StrengthUOMTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, StrengthUOMTypeKey);
					activeIngredient.setStrengthUOMCode(StrengthUOMTypeCode);
				}
				;

				molecule.getAdditionalIngredient().add(activeIngredient);
			}
		}
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: MoleculeGet.populate. moleculeKey : " + moleculeKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return molecule;
	}
	
}
