package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.sql.Connection;
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
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Note;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PatientMetrics;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class PatientMetricsMedReviewGet  extends CDRMedReviewGet {

private static Logger logger = LogManager.getLogger(PatientMetricsGet.class);	
private final static String querySQL = "SELECT "
	+ "PTNTMTRCS.PTNTVTLDT PTNTMTRCS_PTNTVTLDT, PTNTMTRCS.PTNTVTLVALUE PTNTMTRCS_PTNTVTLVALUE, PTNTMTRCS.AUTMTCLYMSRDFLG PTNTMTRCS_AUTMTCLYMSRDFLG, PTNTMTRCS.CNSMRID PTNTMTRCS_CNSMRID, PTNTMTRCS.PRDCRID PTNTMTRCS_PRDCRID, "
	+ "PTNTVTLTYPKEY PTNTMTRCS_PTNTVTLTYPKEY, PTNTMTRCS.PTNTMTRCSKEY PTNTMTRCS_PTNTMTRCSKEY, "
//	+ "NT.ISPHRMCST NT_ISPHRMCST, NT.UPDTTIMESTAMP NT_UPDTTIMESTAMP, NT.NTENG NT_NTENG, NT.CNSMRID NT_CNSMRID, NT.PRDCRID NT_PRDCRID, "
//	+ "NT.NTFR NT_NTFR, NT.CRTTIMESTAMP NT_CRTTIMESTAMP, NT.CRTUSRID NT_CRTUSRID, NT.RCRDRKEY NT_RCRDRKEY, NT.SPRVSRKEY NT_SPRVSRKEY, "
	+ "NT.PTNTMTRCSNTKEY NT_PTNTMTRCSNTKEY  "
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
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PatientMetricsGet.populate. patientKey : " + patientKey);}
		List<PatientMetrics> patientMetricsList = new ArrayList<PatientMetrics>();
		preparedStatement = connection.prepareStatement(querySQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: PatientMetricsGet. patientKey : " + patientKey);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: PatientMetricsGet. patientKey : " + patientKey + ". Total time is : "
				+ (System.currentTimeMillis() - querytimer) + " ms");}
		PatientMetrics metrics = null;
		String previousConsumerId = "";
		while (resultSet.next()) {
			String currentConsumerId = getString("PTNTMTRCS_CNSMRID");
			if (currentConsumerId == null)
				currentConsumerId = "";
			if (!currentConsumerId.equalsIgnoreCase(previousConsumerId)) {
				metrics = new PatientMetrics();
				previousConsumerId = currentConsumerId;
				metrics.setConsumerId(currentConsumerId);
				metrics.setProducerId(getString("PTNTMTRCS_PRDCRID"));
				// every line is a note
				Long noteKey = getLong("NT_PTNTMTRCSNTKEY");
				if (noteKey != null) {
					Note note = populateNote(connection);
					metrics.getNote().add(note);
				};
				
				patientMetricsList.add(metrics);
			};

			Long ptntVtlTypKey = getLong("PTNTMTRCS_PTNTVTLTYPKEY");
			String ptntVtlTypCode = getCodeFromKey(LT_PTNTVTLTYP, ptntVtlTypKey);

			if (!"Height".equals(ptntVtlTypCode) && !"Weight".equals(ptntVtlTypCode)&&metrics.getVitalsDate()==null)
				metrics.setVitalsDate(getCalendar("PTNTMTRCS_PTNTVTLDT"));

			if ("Respiratory Rate".equals(ptntVtlTypCode)) {
				metrics.setRespiratoryRate(getBigInteger("PTNTMTRCS_PTNTVTLVALUE"));
			} else if ("Glucose".equals(ptntVtlTypCode)) {
				metrics.setBloodGlucose(getBigDecimal("PTNTMTRCS_PTNTVTLVALUE"));
				metrics.setBloodGlucoseAutomaticMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
			} else if ("Height".equals(ptntVtlTypCode)) {
				metrics.setHeight(getBigDecimal("PTNTMTRCS_PTNTVTLVALUE"));
				metrics.setHeightMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
				metrics.setHeightDate(getCalendar("PTNTMTRCS_PTNTVTLDT"));				
			} else if ("Weight".equals(ptntVtlTypCode)) {
				metrics.setWeightMeasurementFlag(
						CommonUtil.convertYesNoFlagToBoolean(getString("PTNTMTRCS_AUTMTCLYMSRDFLG")));
				metrics.setWeight(getBigDecimal("PTNTMTRCS_PTNTVTLVALUE"));
				metrics.setWeightDate(getCalendar("PTNTMTRCS_PTNTVTLDT"));
			} else if ("Heart Rate".equals(ptntVtlTypCode)) {
				metrics.setHeartRate(getBigInteger("PTNTMTRCS_PTNTVTLVALUE"));
			} else if ("Body Temperature".equals(ptntVtlTypCode)) {
				metrics.setBodyTemperature(getBigDecimal("PTNTMTRCS_PTNTVTLVALUE"));
			} else if ("Systolic Blood Pressure".equals(ptntVtlTypCode)) {
				metrics.setSystolicBloodPressure(getBigInteger("PTNTMTRCS_PTNTVTLVALUE"));
			} else if ("Diastolic Blood Pressure".equals(ptntVtlTypCode)) {
				metrics.setDiastolicBloodPressure(getBigInteger("PTNTMTRCS_PTNTVTLVALUE"));
			}
		}
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: PatientMetricsGet.populate. patientKey : " + patientKey + ". Total time is : "
				+ (System.currentTimeMillis() - timer) + " ms");}
		return patientMetricsList;
	}
}
