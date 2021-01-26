package ca.sdm.cdr.jdbc.query.api;

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
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.NoteCategory;
import ca.shoppersdrugmart.rxhb.ehealth.NoteType;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class PatientMetricsGet  extends CDRGet {

private static Logger logger = LogManager.getLogger(PatientMetricsGet.class);	
private final static String querySQL = "SELECT "
	+ "PTNTMTRCS.PTNTVTLDT PTNTMTRCS_PTNTVTLDT, PTNTMTRCS.PTNTVTLVALUE PTNTMTRCS_PTNTVTLVALUE, PTNTMTRCS.AUTMTCLYMSRDFLG PTNTMTRCS_AUTMTCLYMSRDFLG, PTNTMTRCS.CNSMRID PTNTMTRCS_CNSMRID, PTNTMTRCS.PRDCRID PTNTMTRCS_PRDCRID, "
	+ "PTNTVTLTYPKEY PTNTMTRCS_PTNTVTLTYPKEY, PTNTMTRCS.PTNTMTRCSKEY PTNTMTRCS_PTNTMTRCSKEY, "
	+ "NT.ISPHRMCST NT_ISPHRMCST, NT.UPDTTIMESTAMP NT_UPDTTIMESTAMP, NT.NTENG NT_NTENG, NT.CNSMRID NT_CNSMRID, NT.PRDCRID NT_PRDCRID, "
	+ "NT.NTFR NT_NTFR, NT.CRTTIMESTAMP NT_CRTTIMESTAMP, NT.CRTUSRID NT_CRTUSRID, NT.RCRDRKEY NT_RCRDRKEY, NT.SPRVSRKEY NT_SPRVSRKEY, "
	+ "NT.PTNTMTRCSNTKEY NT_PTNTMTRCSNTKEY, NT.NTCTGRYTYPKEY NT_NTCTGRYTYPKEY, NT.NTTYPKEY NT_NTTYPKEY, NT.LOCKEY NT_LOCKEY "
	+ "FROM PTNTMTRCS PTNTMTRCS "
	+ "LEFT OUTER JOIN PTNTMTRCSNT NT ON PTNTMTRCS.PTNTMTRCSKEY = NT.PTNTMTRCSKEY "
	+ "WHERE PTNTMTRCS.PTNTKEY = ? "
	+ "ORDER BY PTNTMTRCS.PTNTMTRCSKEY, PTNTMTRCS.CNSMRID ";
 
	public List<PatientMetrics> fetch(Connection connection, Long patientKey) throws SQLException, ParseException, DatatypeConfigurationException, NamingException, IOException, CDRInternalException {
		try {
			return populate(connection, patientKey);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			super.close();
		}
	}
	
	private List<PatientMetrics> populate(Connection connection, Long patientKey) throws SQLException, ParseException,
			DatatypeConfigurationException, NamingException, IOException, CDRInternalException {

		Long timer = System.currentTimeMillis();
//		logger.info("StartApiCall: PatientMetricsGet.populate. patientKey : " + patientKey);
		List<PatientMetrics> patientMetricsList = new ArrayList<PatientMetrics>();
		PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: PatientMetricsGet. patientKey : " + patientKey);
		ResultSet resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: PatientMetricsGet. patientKey : " + patientKey + ". Total time is : "
//				+ (System.currentTimeMillis() - querytimer) + " ms");
		PatientMetrics metrics = null;
		String previousConsumerId = "";
		while (resultSet.next()) {
			String currentConsumerId = resultSet.getString("PTNTMTRCS_CNSMRID");
			if (currentConsumerId == null)
				currentConsumerId = "";
			if (!currentConsumerId.equalsIgnoreCase(previousConsumerId)) {
				metrics = new PatientMetrics();
				previousConsumerId = currentConsumerId;
				metrics.setConsumerId(currentConsumerId);
				metrics.setProducerId(resultSet.getString("PTNTMTRCS_PRDCRID"));
				// every line is a note
				Long noteKey = CommonUtil.getLong("NT_PTNTMTRCSNTKEY",resultSet);
				if (noteKey != null) {
					Note note =  populateNote(connection,resultSet);
					metrics.getNote().add(note);
				};
				
				patientMetricsList.add(metrics);
			};

			Long ptntVtlTypKey = CommonUtil.getLong("PTNTMTRCS_PTNTVTLTYPKEY",resultSet);
			String ptntVtlTypCode = getCodeFromKey(LT_PTNTVTLTYP, ptntVtlTypKey);

			if (!"Height".equals(ptntVtlTypCode) && !"Weight".equals(ptntVtlTypCode)&&metrics.getVitalsDate()==null)
				metrics.setVitalsDate(CommonUtil.getCalendar("PTNTMTRCS_PTNTVTLDT",resultSet));

			if ("Respiratory Rate".equals(ptntVtlTypCode)) {
				metrics.setRespiratoryRate(CommonUtil.getBigInteger("PTNTMTRCS_PTNTVTLVALUE",resultSet));
			} else if ("Glucose".equals(ptntVtlTypCode)) {
				metrics.setBloodGlucose(CommonUtil.getBigDecimal("PTNTMTRCS_PTNTVTLVALUE",resultSet));
				metrics.setBloodGlucoseAutomaticMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
			} else if ("Height".equals(ptntVtlTypCode)) {
				metrics.setHeight(CommonUtil.getBigDecimal("PTNTMTRCS_PTNTVTLVALUE",resultSet));
				metrics.setHeightMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
				metrics.setHeightDate(CommonUtil.getCalendar("PTNTMTRCS_PTNTVTLDT",resultSet));				
			} else if ("Weight".equals(ptntVtlTypCode)) {
				metrics.setWeightMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
				metrics.setWeight(CommonUtil.getBigDecimal("PTNTMTRCS_PTNTVTLVALUE",resultSet));
				metrics.setWeightDate(CommonUtil.getCalendar("PTNTMTRCS_PTNTVTLDT",resultSet));
			} else if ("Heart Rate".equals(ptntVtlTypCode)) {
				metrics.setHeartRate( CommonUtil.getBigInteger("PTNTMTRCS_PTNTVTLVALUE",resultSet));
			} else if ("Body Temperature".equals(ptntVtlTypCode)) {
				metrics.setBodyTemperature(CommonUtil.getBigDecimal("PTNTMTRCS_PTNTVTLVALUE",resultSet));
			} else if ("Systolic Blood Pressure".equals(ptntVtlTypCode)) {
				metrics.setSystolicBloodPressure(CommonUtil.getBigInteger("PTNTMTRCS_PTNTVTLVALUE",resultSet));
			} else if ("Diastolic Blood Pressure".equals(ptntVtlTypCode)) {
				metrics.setDiastolicBloodPressure(CommonUtil.getBigInteger("PTNTMTRCS_PTNTVTLVALUE",resultSet));
			}
		}
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
//		logger.info("EndApiCall: PatientMetricsGet.populate. patientKey : " + patientKey + ". Total time is : "
//				+ (System.currentTimeMillis() - timer) + " ms");
		return patientMetricsList;
	}
	
	public Note populateNote(Connection connection,ResultSet rs) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException
	{
		Note note = new Note();
		
		note.setConsumerId(rs.getString("NT_CNSMRID"));
		note.setProducerId(rs.getString("NT_PRDCRID"));
		note.setNoteFrench(rs.getString("NT_NTFR"));
		note.setNoteEnglish(rs.getString("NT_NTENG"));
		note.setCreateTimestamp(CommonUtil.getCalendar("NT_CRTTIMESTAMP",rs));
		note.setUpdateTimestamp(CommonUtil.getCalendar("NT_UPDTTIMESTAMP",rs));
		note.setIsPharmacist(CommonUtil.convertYesNoFlagToBoolean(rs.getString("NT_ISPHRMCST")));
		note.setCreateUserId(rs.getString("NT_CRTUSRID"));
		note.setDispenser(null);
		Long recorderKey = CommonUtil.getLong("NT_RCRDRKEY",rs);
		if(recorderKey!=null)
		{
			PersonRoleGet personRoleGet = new PersonRoleGet();
			Recorder recorder = personRoleGet.fetchRecorder(connection, recorderKey);
			note.setRecorder(recorder);
		}
		
		Long supervisorKey = CommonUtil.getLong("NT_SPRVSRKEY",rs);
		if(supervisorKey != null)
		{
			PersonRoleGet personRoleGet = new PersonRoleGet();
			Supervisor supervisor = personRoleGet.fetchSupervisor(connection, supervisorKey);
			note.setSupervisor(supervisor);
		}
		
		Long noteTypeKey = CommonUtil.getLong("NT_NTTYPKEY",rs);
		if(noteTypeKey != null)
		{
			String noteTypeCode = getCodeFromKey(LT_NTTYP, noteTypeKey);
			note.setNoteTypeCode(NoteType.fromValue(noteTypeCode));
		}
		
		Long noteCategoryTypeKey =  CommonUtil.getLong("NT_NTCTGRYTYPKEY",rs);
		if(noteCategoryTypeKey != null)
		{
			String noteCategoryTypeCode = getCodeFromKey(LT_NTCTGRYTYP, noteCategoryTypeKey);
			note.setNoteCategory(NoteCategory.fromValue(noteCategoryTypeCode));
		}

		Long locationKey = CommonUtil.getLong("NT_LOCKEY",rs);
		if(locationKey != null)
		{
			
		};
		return note;
	}
}
