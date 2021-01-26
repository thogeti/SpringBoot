package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Schedule;

public class CompoundGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(CompoundGet.class);
	
	private final static String QUERYSQL = "SELECT CMPND.CMPNDID, CMPND.ADMINRTECD, CMPND.UOMQTYCONVERFCTR, CMPND.NMFR, CMPND.NMENG, "
			+ "CMPND.CNSMRID, CMPND.PREPDIRCTN, CMPND.PRDCRID, CMPND.QTY, CMPND.RTEOFADMIN_CERXTYPKEY, "
			+ "CMPND.CMPNDUOM, CMPND.DSGFRMKEY , CMPND.CMPNDSCHDLTYPKEY  "
			+ "FROM CMPND CMPND "
			+ "WHERE CMPND.CMPNDKEY = ?";

	public Compound fetch(Connection connection, Long compoundKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		try {

			return populate(connection, compoundKey );

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
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
		}
	}

	private Compound populate(Connection connection, Long compoundKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {

		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: CompoundGet.populate. compoundKey : " + compoundKey);}

		Compound compound = null;
		ResultSet resultSet =null;
		PreparedStatement preparedStatement = null;
		try {
		preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, compoundKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: CompoundGet. compoundKey : " + compoundKey );}
		 resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: CompoundGet. compoundKey : " + compoundKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		
		if (resultSet.next()) {
			compound = new Compound();
			// String compoundId  = getString("CMPNDID");
			// AdminRouteCode = getString("ADMINRTECD");
			BigDecimal tmpBigDecimal = CommonUtil.getBigDecimal("UOMQTYCONVERFCTR",resultSet);
			if (tmpBigDecimal != null) {
				BigInteger tmpBigInteger = tmpBigDecimal.toBigInteger();
				compound.setUofMQuantityConversionFactor(tmpBigInteger);
			}
			compound.setNameFrench(resultSet.getString("NMFR"));
			compound.setNameEnglish(resultSet.getString("NMENG"));

			compound.setConsumerId(resultSet.getString("CNSMRID"));
			compound.setPreperationdirections(resultSet.getString("PREPDIRCTN"));
			compound.setProducerId(resultSet.getString("PRDCRID"));
			compound.setQuantity(CommonUtil.getBigDecimal("QTY",resultSet));
			//String routeOfAdmin = getString("RTEOFADMIN_CERXTYPKEY");
			
			compound.setUnitOfMeasure(resultSet.getString("CMPNDUOM"));
			
			/*Long dosageFormKey= getLong("DSGFRMKEY");
			if(dosageFormKey!=null) {
				DosageForm dosageForm = (DosageForm) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,dosageFormKey, "DSGFRM");
				compound.setDosageForm(dosageForm);
			}*/
			/*String dosageFormFullName = getString("DSGFRMFULLNM");
			String dosageFormShortName = getString("DSGFRMSHORTNM");
			Integer dosageFormId = getInt("DSGFRMID");
			if(dosageFormFullName!=null||dosageFormShortName!=null||dosageFormId!=null)
			{
				DosageForm dosageForm = new DosageForm();
				dosageForm.setFullName(dosageFormFullName);
				dosageForm.setShortName(dosageFormShortName);
				dosageForm.setId(dosageFormId);
				compound.setDosageForm(dosageForm);
			}*/

			Long cmpndSchedulKey= CommonUtil.getLong("CMPNDSCHDLTYPKEY",resultSet);
			if(cmpndSchedulKey!=null)
			{
				String cmpndScheduleCode =  getCodeFromKey(LT_DRGSCHDLTYP, cmpndSchedulKey);
				compound.setSchedule(Schedule.fromValue(cmpndScheduleCode)) ;
			}
			CompoundIngredientGet compoundIngredientGet = new CompoundIngredientGet();
			List<CompoundIngredient> compoundIngredientList = compoundIngredientGet.fetchList(connection, compoundKey);
			compound.getCompoundIngredients().addAll(compoundIngredientList);
			
			//added 60 prescribedCompound
			//String routeOfAdmin = getString("RTEOFADMIN_CERXTYPKEY");
			//RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
			   
			//compound.setAdministrationRouteCode(routeOfAdmin);
			/*Long routeOfAdminKey = getLong("RTEOFADMIN_CERXTYPKEY");
			   String routeOfAdminCode = TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
			   RouteOfAdmin routeOfAdmin = (routeOfAdminCode!=null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
			   compound.setAdministrationRouteCode(routeOfAdmin);*/
			//1210
			/*Long routeOfAdminKey = getLong("RTEOFADMIN_CERXTYPKEY");
			if ( routeOfAdminKey != null)
			{
				String routeOfAdminCode = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
				RouteOfAdmin routeOfAdmin = (routeOfAdminCode != null) ? RouteOfAdmin.fromValue(routeOfAdminCode) : null;
				compound.setAdministrationRouteCode(routeOfAdmin);
			}*/
			//1210
			//added 60 prescribedCompound
		}
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: CompoundGet.populate. compoundKey : " + compoundKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return compound;
	}
}
