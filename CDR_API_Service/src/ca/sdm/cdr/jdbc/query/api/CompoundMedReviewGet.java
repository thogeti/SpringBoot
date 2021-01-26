package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;


import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Compound;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.CompoundIngredient;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DosageForm;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Drug;


public class CompoundMedReviewGet extends CDRMedReviewGet {
	private static Logger logger = LogManager.getLogger(CompoundMedReviewGet.class);
	private final static String QUERYSQL = "SELECT  CMPND.NMFR, CMPND.NMENG, CMPND.RTEOFADMIN_CERXTYPKEY , "
			/*+ "CMPND.CNSMRID, CMPND.PREPDIRCTN, CMPND.PRDCRID, CMPND.QTY, CMPND.RTEOFADMIN_CERXTYPKEY, "
			+ "CMPND.CMPNDUOM, CMPND.DSGFRMKEY,  " */
			+ " CMPND.CMPNDSCHDLTYPKEY ,CMPND.DSGFRMKEY " 
			+ " FROM CMPND CMPND "
			+ "WHERE CMPND.CMPNDKEY = ?";

	public Compound fetch(Connection connection, Long compoundKey, Map<Long, Drug> drgPK,Map <Long, Compound> cmpndPK) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {
		try {
			 if ((cmpndPK!=null)&&cmpndPK.containsKey(compoundKey)) {
				 if(logger.isDebugEnabled()) {	 logger.debug("Compound MedReview fetch compoundKey  : " + compoundKey  + " cmpndPK.size = " + cmpndPK.size());}
				 Compound compound = cmpndPK.get(compoundKey);
				 return compound;
			    } else { 
			return populate(connection, compoundKey,drgPK,cmpndPK);
			    }
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

	private Compound populate(Connection connection, Long compoundKey, Map<Long, Drug> drgPK,Map <Long, Compound> cmpndPK) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException {

		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: CompoundMedReviewGet.populate. compoundKey : " + compoundKey);}

		Compound compound = null;
		preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, compoundKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: CompoundMedReviewGet. compoundKey : " + compoundKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: CompoundMedReviewGet. compoundKey : " + compoundKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		if (resultSet.next()) {
			compound = new Compound();
			compound.setNameFrench(getString("NMFR"));//A
			compound.setNameEnglish(getString("NMENG"));//A
			Long routeOfAdminKey = getLong("RTEOFADMIN_CERXTYPKEY"); 
			if (routeOfAdminKey != null) {
				String routeOfAdminCode = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
        		compound.setAdministrationRouteCode(routeOfAdminCode);//A
			}
			Long dosageFormKey = getLong("DSGFRMKEY");
			if(dosageFormKey!=null)
			{
				ca.shoppersdrugmart.rxhb.ehealth.DosageForm dosageFormEhealth = (ca.shoppersdrugmart.rxhb.ehealth.DosageForm) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,dosageFormKey, "DSGFRM");
				DosageForm dosageForm = new DosageForm();
				dosageForm.setFullName(dosageFormEhealth.getFullName());
				dosageForm.setFullNameFrench(dosageFormEhealth.getFullNameFrench());
			   	compound.setDosageForm(dosageForm);
			}
			
			Long cmpndSchedulKey= getLong("CMPNDSCHDLTYPKEY");//A
			if(cmpndSchedulKey!=null)
			{
				String cmpndScheduleCode =  getCodeFromKey(LT_DRGSCHDLTYP, cmpndSchedulKey);
				compound.setSchedule(cmpndScheduleCode) ;
			}
		}
		if(cmpndPK!=null) {
			cmpndPK.put(compoundKey, compound);
			}
		if(logger.isInfoEnabled())	logger.info("EndApiCall: CompoundMedReviewGet.populate. compoundKey : " + compoundKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		return compound;
	}
}
