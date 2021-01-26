package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.DataValidationException;

import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;


public class ProfessionalRegistrationApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(ProfessionalRegistrationApi.class);

	private final static String INSERTSQL = "INSERT INTO PRFSNLREG(LICNUM, PROVKEY, ISSNGBODYCD, PRFSNLCTGRYTYPKEY,  PROVPRFSNLREGAGNCYKEY, PRSNRLKEY, PRFSNLREGKEY) VALUES(?,?,?,?,?,?,?)";

	private final static String UPDATESQL = "UPDATE PRFSNLREG SET LICNUM = ?, PROVKEY = ?, ISSNGBODYCD = ?,  PRFSNLCTGRYTYPKEY = ?,  PROVPRFSNLREGAGNCYKEY = ?, PRSNRLKEY = ? WHERE PRFSNLREGKEY = ?";

	public Long upsertProfessionalRegistration(Connection connection, ProfessionalRegistration prfreg, Long prsnRlKey) throws SQLException, NamingException, IOException, CDRException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertProfessionalRegistration, prsnRlKey : " + prsnRlKey);}
			// Persist main entity
			if (prfreg.getLicenseNumber() == null || "".equals(prfreg.getLicenseNumber())
					|| prfreg.getLicensedProvince() == null || "".equals(prfreg.getLicensedProvince().value())
					|| prfreg.getIssuingBodyCode() == null || "".equals(prfreg.getIssuingBodyCode().value())) {
				throw new DataValidationException(
						"professional registration's license number, province and issuing body code are all required fields");
			}

			Long prfsnlRegKey = FindUtil.findProfessionalRegistrationKey(connection, prfreg, prsnRlKey);

			if (prfsnlRegKey == null) {
				prfsnlRegKey = insertProfessionalService(connection, prfreg, prsnRlKey);
			} else {
				updateProfessionalService(connection, prfreg, prsnRlKey, prfsnlRegKey);
			}

			return prfsnlRegKey;
		} catch (DataValidationException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertProfessionalRegistration, prsnRlKey : " + prsnRlKey);}
		}
	}
	
	private Long insertProfessionalService(Connection connection, ProfessionalRegistration prfreg, Long prsnRlKey) throws SQLException, NamingException, IOException, CDRException
	{
		long prfsnlRegKey = IdGenerator.generate(connection, "PrfsnlReg");
		Long provKey = null;
		String licenseNumber = prfreg.getLicenseNumber();
		String issngBodyCd = null;
		Long prfsnlCtgryTypKey = null;
		Long ProvPrfsnlRegAgncyKey = null;		

		if(prfreg.getIssuingBodyCode() !=null)
			issngBodyCd = prfreg.getIssuingBodyCode().value();

		if (prfreg.getProfessionalCategoryCode() != null && prfreg.getProfessionalCategoryCode().getProviderRoleTypeCode() !=null)
			prfsnlCtgryTypKey = getKeyFromCode(LT_PRFSNLCTGRYTYP, prfreg.getProfessionalCategoryCode().getProviderRoleTypeCode().value());
		
		if(prfreg.getLicensedProvince()!=null)
		{
			provKey = getKeyFromCode(LT_PROV, prfreg.getLicensedProvince().value());
		}
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsStringParam(1, licenseNumber);
		setPsLongParam(2, provKey);
		setPsStringParam(3, issngBodyCd);
		setPsLongParam(4, prfsnlCtgryTypKey);
		setPsLongParam(5, ProvPrfsnlRegAgncyKey);
		setPsLongParam(6, prsnRlKey);
		setPsLongParam(7, prfsnlRegKey);		
		ps.executeUpdate();
		
		return prfsnlRegKey;
	}
	
	private Long updateProfessionalService(Connection connection, ProfessionalRegistration prfreg,  Long prsnRlKey, long prfsnlRegKey) throws SQLException, NamingException, IOException, CDRException
	{
		Long provKey = null;
		String licenseNumber = prfreg.getLicenseNumber();
		String issngBodyCd = null;
		Long prfsnlCtgryTypKey = null;
		Long ProvPrfsnlRegAgncyKey = null;		

		if(prfreg.getIssuingBodyCode() !=null)
			issngBodyCd = prfreg.getIssuingBodyCode().value();

		if (prfreg.getProfessionalCategoryCode() != null && prfreg.getProfessionalCategoryCode().getProviderRoleTypeCode() !=null)
			prfsnlCtgryTypKey = getKeyFromCode(LT_PRFSNLCTGRYTYP, prfreg.getProfessionalCategoryCode().getProviderRoleTypeCode().value());
		
		if(prfreg.getLicensedProvince()!=null)
		{
			provKey = getKeyFromCode(LT_PROV, prfreg.getLicensedProvince().value());
		}
		
		ps = connection.prepareStatement(UPDATESQL);
		setPsStringParam(1, licenseNumber);
		setPsLongParam(2, provKey);
		setPsStringParam(3, issngBodyCd);
		setPsLongParam(4, prfsnlCtgryTypKey);
		setPsLongParam(5, ProvPrfsnlRegAgncyKey);
		setPsLongParam(6, prsnRlKey);
		setPsLongParam(7, prfsnlRegKey);
		ps.executeUpdate();
		
		return prfsnlRegKey;
	}
}
