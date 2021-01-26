package ca.sdm.cdr.jdbc.query.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyTest;
public class AllergyTestGet extends CDRGet {

	private static Logger logger = LogManager.getLogger(AllergyTestGet.class);
	private final static String QUERYSQL = "SELECT "
			+ "TSTDTTIME, TSTRSLT, CNSMRID, PRDCRID, ALRGYTSTKEY "
			+ "FROM ALRGYTST "
			+ "WHERE PTNTALRGYKEY = ?";

	private List<AllergyTest> populate(Connection connection, Long patientAllergyKey) throws SQLException, ParseException, DatatypeConfigurationException
	{
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: AllergyTestGet.populate. PatientAllergyKey : " + patientAllergyKey);}
		
		List<AllergyTest> allergyTestList = new ArrayList<AllergyTest>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		preparedStatement=connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientAllergyKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: AllergyTestGet. patientAllergyKey : " + patientAllergyKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: AllergyTestGet. patientAllergyKey : " + patientAllergyKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			AllergyTest allergyTest = new AllergyTest();
			allergyTest.setConsumerId(resultSet.getString("CNSMRID"));
			allergyTest.setProducerId(resultSet.getString("PRDCRID"));
			allergyTest.setTestDatetime(CommonUtil.getCalendar("TSTDTTIME",resultSet));
			allergyTest.setTestResult(resultSet.getString("TSTRSLT"));
			allergyTestList.add(allergyTest);
		};
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: AllergyTestGet.populate. PatientAllergyKey : " + patientAllergyKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		
		return allergyTestList;
	}
	
	public List<AllergyTest> fetch(Connection connection, Long patientAllergyKey) throws SQLException, ParseException, DatatypeConfigurationException
	{
		try {
			return populate(connection, patientAllergyKey);
		} catch (SQLException e) {
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
}
