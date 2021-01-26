package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_BNFTCARDHLDRRLTNSHPTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.BenefitsCardholderRelationship;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;

public class PatientCoverageGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(PatientCoverageGet.class);	
	private final static String QUERYSQL = "SELECT " 
			+ "CRRID, GRPID, CLNTID, PLANNUM, ACTFLG, "
			+ "PRIORITY, CARDID, PTNTCVRGID, INSCVRGID, TPID, " 
			+ "PTNTCVRGKEY, BNFTCARDHLDRRLTNSHPTYPKEY "
			+ "FROM PTNTCVRG " 
			+ "WHERE PTNTKEY = ?";

	public List<InsuranceCoverage> fetch(Connection connection, Long patientKey) throws CDRInternalException, SQLException, NamingException,
			IOException, ParseException, DatatypeConfigurationException {
		try {
			return populate(connection, patientKey);
		} catch (CDRInternalException e) {
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

	private List<InsuranceCoverage> populate(Connection connection, Long patientKey) throws SQLException, CDRInternalException,
			NamingException, IOException, ParseException, DatatypeConfigurationException {
	
		Long timer = System.currentTimeMillis();
//		logger.info("StartApiCall: PatientCoverageGet.populate. patientKey : " + patientKey);
		List<InsuranceCoverage> insuranceCoverageList = new ArrayList<InsuranceCoverage>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet  = null;
		try {
			preparedStatement=connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: PatientCoverageGet. patientKey : " + patientKey );
		 resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: PatientCoverageGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		while (resultSet.next()) {
			InsuranceCoverage insuranceCoverage = new InsuranceCoverage();
			insuranceCoverage.setCarrierId(resultSet.getString("CRRID"));
			insuranceCoverage.setGroupId(resultSet.getString("GRPID"));
			insuranceCoverage.setClientId((resultSet.getString("CLNTID")));
			insuranceCoverage.setPlanNumber(resultSet.getString("PLANNUM"));
			insuranceCoverage.setIsActive(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("ACTFLG")));
			insuranceCoverage.setPriority(CommonUtil.getInt("PRIORITY",resultSet));
			insuranceCoverage.setCardIdentifier(resultSet.getString("CARDID"));
			insuranceCoverage.setInsuranceCoverageIdentifier(resultSet.getString("INSCVRGID"));
			insuranceCoverage.setThirdPartyConsumerIdentifier(resultSet.getString("TPID"));

			Long bnftCardhldrRltnshpTypKey = CommonUtil.getLong("BNFTCARDHLDRRLTNSHPTYPKEY",resultSet);
			if (bnftCardhldrRltnshpTypKey != null) {
				String bnftCardhldrRltnshpTypCode = getCodeFromKey(LT_BNFTCARDHLDRRLTNSHPTYP, bnftCardhldrRltnshpTypKey);
				insuranceCoverage.setBenefitsCardholderRelationshipCode(BenefitsCardholderRelationship.fromValue(bnftCardhldrRltnshpTypCode));
			}
			insuranceCoverage.setConsumerId(resultSet.getString("PTNTCVRGID"));
			insuranceCoverageList.add(insuranceCoverage);
		};
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
	//	logger.info("EndApiCall: PatientCoverageGet.populate. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		return insuranceCoverageList;
	}

}
