package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_IDTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.IdentificationType;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;

public class PatientIdentificationGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(PatientIdentificationGet.class);		
	private final static String QUERYSQL = "SELECT "
			+ "IDNUM, ISSNGATHRTYNM, PTNTIDKEY, IDTYPKEY, PTNTKEY "
			+ "FROM PTNTID "
			+ "WHERE PTNTKEY = ?"
			+ " ORDER BY PTNTID.PTNTIDKEY desc ";			
	
	public List<PatientIdentification> fetch(Connection connection, Long patientKey) throws CodeNotFoundFromTableCacheException, SQLException, NamingException, IOException {
		try {
			return populate(connection, patientKey);
		} catch (CodeNotFoundFromTableCacheException e) {
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
		} finally
		{
			super.close();
		}
	};

	private List<PatientIdentification> populate(Connection connection, Long patientKey) throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException
	{
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PatientIdentificationGet.populate. patientKey : " + patientKey);}

		List<PatientIdentification> patientIdentificationList = new ArrayList<PatientIdentification>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
		preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: PatientIdentificationGet. patientKey : " + patientKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: PatientIdentificationGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			PatientIdentification patientIdentification = new PatientIdentification();
			patientIdentification.setIdentificationNumber(resultSet.getString("IDNUM"));
			patientIdentification.setIssuingAuthorityName(resultSet.getString("ISSNGATHRTYNM"));
			Long idTypeKey = CommonUtil.getLong("IDTYPKEY",resultSet);
			if ( idTypeKey != null)
			{
				String idTypeCode = getCodeFromKey(LT_IDTYP, idTypeKey);
				IdentificationType identificationType = IdentificationType.fromValue(idTypeCode);
				patientIdentification.setIdentificationTypeCode(identificationType);
			}
			patientIdentificationList.add(patientIdentification);
		};
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: PatientIdentificationGet.populate. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return patientIdentificationList;
	}
}

