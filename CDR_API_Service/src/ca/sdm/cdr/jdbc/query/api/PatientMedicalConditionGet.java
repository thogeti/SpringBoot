package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STATCHNGRSNTYP;

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
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.StatusChangeReason;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class PatientMedicalConditionGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(CompoundIngredientGet.class);
	private final static String querySQL = "SELECT "
			+ "CNDTNDESCR, CNDTNSTRTDT, CNDTNENDDT, CHRONICCNDTNFLG, CNDTNCRTTIMESTAMP, "
			+ "CNDTNACTFLG, RPTRKEY, PRDCRID, CNSMRID, RCRDRKEY, "
			+ "SPRVSRKEY, PTNTMDCLCNDTNKEY, STATCHNGRSNTYPKEY, LOCKEY " 
			+ "FROM PTNTMDCLCNDTN " 
			+ "WHERE PTNTKEY = ?";

	public List<PatientMedicalCondition> fetch(Connection connection, Long patientKey) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {
		try {
			return populate(connection, patientKey);
		} catch (SQLException e) {
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
	
	private List<PatientMedicalCondition> populate(Connection connection, Long patientKey) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
//		logger.info("StartApiCall: PatientMedicalConditionGet.populate. patientKey : " + patientKey );

		List<PatientMedicalCondition> patientMedicalConditionList = new ArrayList<PatientMedicalCondition>();
		PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: PatientMedicalConditionGet. patientKey : " + patientKey );
		ResultSet resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: PatientMedicalConditionGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		while(resultSet.next())
		{
			PatientMedicalCondition patientMedicalCondition = new PatientMedicalCondition();
			
			patientMedicalCondition.setConditionStartDate(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("CNDTNSTRTDT"))); 
			patientMedicalCondition.setConditionDescription(resultSet.getString("CNDTNDESCR"));
			patientMedicalCondition.setConditionEndDate(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("CNDTNENDDT") ) );
			patientMedicalCondition.setChronicConditionFlag(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("CHRONICCNDTNFLG")));
			patientMedicalCondition.setConditionCreateTimestamp( CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CNDTNCRTTIMESTAMP"), true));
			patientMedicalCondition.setConditionActiveFlag(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("CNDTNACTFLG")));
			patientMedicalCondition.setProducerId(resultSet.getString("PRDCRID"));
			patientMedicalCondition.setConsumerId(resultSet.getString("CNSMRID"));
			
			Long supervisorKey = CommonUtil.getLong("SPRVSRKEY",resultSet);
			if(supervisorKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Supervisor supervisor = personRoleViewGet.fetchSupervisor(connection, supervisorKey);
				patientMedicalCondition.setSupervisor(supervisor);				
			}
			
			Long reporterKey = CommonUtil.getLong("RPTRKEY",resultSet);
			if(reporterKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Reporter reporter = personRoleViewGet.fetchReporter(connection, reporterKey);
				patientMedicalCondition.setReporter(reporter);				
			};
			
			Long recorderKey = CommonUtil.getLong("RCRDRKEY",resultSet);
			if(recorderKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, recorderKey);
				patientMedicalCondition.setRecorder(recorder);				
			};
			
			Long locationKey = CommonUtil.getLong("LOCKEY",resultSet);
			if(locationKey != null)
			{
				LocationGet locationGet = new LocationGet();
				Location location = locationGet.fetch(connection, locationKey);
				patientMedicalCondition.setServiceLocation(location);
			}

			Long statusChangeReasonTypeKey = CommonUtil.getLong("STATCHNGRSNTYPKEY",resultSet);
			if(statusChangeReasonTypeKey != null)
			{
				String statusChangeReasonTypeCode = getCodeFromKey(LT_STATCHNGRSNTYP, statusChangeReasonTypeKey);
				patientMedicalCondition.setUpdateReasonType(StatusChangeReason.fromValue(statusChangeReasonTypeCode));
			}
			
			Long patientMedicalConditionKey = CommonUtil.getLong("PTNTMDCLCNDTNKEY",resultSet);

			NoteGet noteGet = new NoteGet("PTNTMDCLCNDTNNT", "PTNTMDCLCNDTNKEY", true);
			List<Note> notes = noteGet.fetch(connection, patientMedicalConditionKey);
			if(notes!=null && notes.size() > 0)
				patientMedicalCondition.getNote().addAll(notes);
			
			patientMedicalConditionList.add(patientMedicalCondition);
		}
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
//		logger.info("EndApiCall: PatientMedicalConditionGet.populate. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		return patientMedicalConditionList;
		
	}

}
