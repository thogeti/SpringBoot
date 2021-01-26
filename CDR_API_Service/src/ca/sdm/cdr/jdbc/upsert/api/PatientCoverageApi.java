package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


/**
 * @author sshiran
 *
 */
public class PatientCoverageApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(PatientCoverageApi.class);

	private final static String MERGESQL = 
			" MERGE INTO PTNTCVRG TARGET   "  
					+ "  USING    " 
					+ "  ( SELECT PTNTCVRGKEY  FROM    "  
					+ "       ( SELECT PTNTCVRGKEY FROM PTNTCVRG WHERE PTNTCVRG.PTNTKEY = ? AND PTNTCVRGID = ?  "  
					+ "            UNION ALL   " 
					+ "            SELECT NULL PTNTCVRGKEY FROM DUAL    "  
					+ "       )WHERE ROWNUM=1   " 
					+ "       ORDER BY PTNTCVRGKEY DESC   "  
					+ "  )  SRC    " 
					+ "  ON  ( SRC.PTNTCVRGKEY = TARGET.PTNTCVRGKEY )   " 
					+ "    WHEN NOT MATCHED THEN   " 
					+ "      INSERT ("
					+ "				CRRID	,	GRPID						,	CLNTID	,	PLANNUM		, " 
					+ " 			ACTFLG	,	PRIORITY					,	CARDID	,	INSCVRGID	, " 
					+ " 			TPID	,	BNFTCARDHLDRRLTNSHPTYPKEY	,	PTNTKEY	,	PTNTCVRGID	," 
					+ " 			PTNTCVRGKEY  )"  
					+ "      VALUES ( "
					+ " 				?, ?, ?, ? , "   
					+ " 				?, ?, ?, ? , "   
					+ " 				?, ?, ?, ? , " 
					+ " 				PTNTCVRG_SEQ.NEXTVAL )" 
					+ "		WHEN MATCHED THEN   " 
					+ "			UPDATE  SET " 
					+ "          		TARGET.CRRID =?		, TARGET.GRPID =? 						, TARGET.CLNTID = ?	, TARGET.PLANNUM = ? ," 
					+ "          		TARGET.ACTFLG =?  	, TARGET.PRIORITY =? 					, TARGET.CARDID = ? , TARGET.INSCVRGID = ? ," 
					+ "          		TARGET.TPID =?  	, TARGET.BNFTCARDHLDRRLTNSHPTYPKEY =? 	, TARGET.PTNTCVRGID = ? " 
					+ "          		" ;
	
	public void mergePatientCoverageApi(Connection connection, InsuranceCoverage insCvrg, Long patientKey) throws ConsumerIdMissingException, SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: mergePatientCoverageApi, patient key : "  + patientKey );}

			String insuranceCoverageConsumerId = insCvrg.getConsumerId();

			if (insuranceCoverageConsumerId == null || "".equals(insuranceCoverageConsumerId))
				throw new ConsumerIdMissingException("insuranceCoverage");

			ps = connection.prepareStatement(MERGESQL);

			int paramIndex = 1;
			/* set search parameters */
			setPsLongParam(paramIndex++, patientKey);
			setPsStringParam(paramIndex++, insuranceCoverageConsumerId);

			/* set Insert parameters */
			setPsStringParam(paramIndex++, insCvrg.getCarrierId());
			setPsStringParam(paramIndex++, insCvrg.getGroupId());
			setPsStringParam(paramIndex++, insCvrg.getClientId());
			setPsStringParam(paramIndex++, insCvrg.getPlanNumber());

			setPsStringParam(paramIndex++, CommonUtil.convertBooleanToYesNoFlag(insCvrg.isIsActive()));
			setPsIntParam(paramIndex++, insCvrg.getPriority());
			setPsStringParam(paramIndex++, insCvrg.getCardIdentifier());
			setPsStringParam(paramIndex++, insCvrg.getInsuranceCoverageIdentifier());

			setPsStringParam(paramIndex++, insCvrg.getThirdPartyConsumerIdentifier());

			Long bnftCardhldrRltnshpTypKey = null;
			if (insCvrg.getBenefitsCardholderRelationshipCode() != null) {
				String benefitCardCode = insCvrg.getBenefitsCardholderRelationshipCode().value();
				bnftCardhldrRltnshpTypKey = getKeyFromCode(LT_BNFTCARDHLDRRLTNSHPTYP, benefitCardCode);

			}
			setPsLongParam(paramIndex++, bnftCardhldrRltnshpTypKey);
			setPsLongParam(paramIndex++, patientKey);
			setPsStringParam(paramIndex++, insCvrg.getConsumerId());

			/* set update parameters */

			setPsStringParam(paramIndex++, insCvrg.getCarrierId());
			setPsStringParam(paramIndex++, insCvrg.getGroupId());
			setPsStringParam(paramIndex++, insCvrg.getClientId());
			setPsStringParam(paramIndex++, insCvrg.getPlanNumber());

			setPsStringParam(paramIndex++, CommonUtil.convertBooleanToYesNoFlag(insCvrg.isIsActive()));
			setPsIntParam(paramIndex++, insCvrg.getPriority());
			setPsStringParam(paramIndex++, insCvrg.getCardIdentifier());
			setPsStringParam(paramIndex++, insCvrg.getInsuranceCoverageIdentifier());

			setPsStringParam(paramIndex++, insCvrg.getThirdPartyConsumerIdentifier());
			setPsLongParam(paramIndex++, bnftCardhldrRltnshpTypKey);
			setPsStringParam(paramIndex++, insCvrg.getConsumerId());

			ps.executeUpdate();
			CommonUtil.closePreparedStatementResultSet(ps, null);

			if(logger.isDebugEnabled()) {logger.debug("End mergePatientCoverageApi");}

		} catch (ConsumerIdMissingException e) {
			e.printStackTrace();
			throw e;
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
		} finally
		{
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: mergePatientCoverageApi, patient key : "  + patientKey );}
		}
	}
}
