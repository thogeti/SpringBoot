package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PatientVitalType;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class MetricsApi extends CDRUpsert {
	final static Logger logger = LogManager.getLogger("MetricsApi.class");

	private final static String INSERTSQL =
			"INSERT INTO PTNTMTRCS( " +
	"		PTNTVTLDT,	" +
	"		PTNTVTLVALUE,	" +
	"		AUTMTCLYMSRDFLG,	" +
	"		CNSMRID,	" +
	"		PRDCRID,	" +
	"		PTNTVTLTYPKEY,	" +
	"		PTNTKEY,	" +
	"		PTNTMTRCSKEY)	" +
	"	VALUES ( " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
	" 			?, ?, ?, ?, ?, ?, ?)";
	
	private final static String UPDATESQL =
			" UPDATE PTNTMTRCS " +
	"	SET (PTNTVTLDT, " +
	"		PTNTVTLVALUE, " +
	"		AUTMTCLYMSRDFLG, " +
	"		CNSMRID, " +
	"		PRDCRID, " +
	"		PTNTVTLTYPKEY, " +
	"		PTNTKEY) = " +
	"	(SELECT " + CommonUtil.TO_TIMESTAMP_TZ + ", " +
	"			?, ?, ?, ?, ?, ? FROM DUAL) " +
	"	WHERE PTNTMTRCSKEY = ?";

	private final static String QUERYSQL = 
	"SELECT PTNTMTRCSKEY " +
	"	FROM PTNTMTRCS   " +
	"	WHERE PTNTKEY = ? " +
	"	  AND PTNTVTLTYPKEY = ? " +
	"     AND CNSMRID = ? ";
	
	public void upsertMetrics(Connection connection, long patientKey, List<PatientMetrics> metricsList)
			throws KeyNotFoundFromTableCacheException, ConsumerIdMissingException, SQLException, IOException,
			NamingException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertMetrics, patientKey : " + patientKey);	}	
			
			// String CheckName = PatientVitalType.Respiratory_Rate.name();
			// String CheckName = PatientVitalType.Respiratory_Rate.value();
			if (metricsList != null && metricsList.size() > 0) {
				for (PatientMetrics thisMetric : metricsList) {
					Long PtntVtlTypKey = null;
					String consumerId = thisMetric.getConsumerId();
					if (consumerId == null)
						throw new ConsumerIdMissingException("PatientMetrics");

					/*
					 * --------------- Check Weight : ---------------
					 */
					BigDecimal weight = thisMetric.getWeight();
					if (weight != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Weight.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric,
									PatientVitalType.Weight.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Weight.value());
						}
						super.close();
					}

					/*
					 * --------------- Check height : ---------------
					 */
					BigDecimal height = thisMetric.getHeight();
					if (height != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Height.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Height.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Height.value());
						}
						super.close();
					}

					/*
					 * -------------------- Check bloodGlucose : * --------------------
					 */
					BigDecimal glucose = thisMetric.getBloodGlucose();
					if (glucose != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Glucose.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Glucose.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Glucose.value());
						}
						super.close();
					}

					/*
					 * -------------------- Check HeartRate : * --------------------
					 */
					BigInteger heartRate = thisMetric.getHeartRate();
					if (heartRate != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.HeartRate.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.HeartRate.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.HeartRate.value());
						}
						super.close();
					}

					/*
					 * ---------------------- Check respiratoryRate : * ----------------------
					 */
					BigInteger respiratoryRate = thisMetric.getRespiratoryRate();
					if (respiratoryRate != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Respiratory_Rate.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Respiratory_Rate.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Respiratory_Rate.value());
						}
						super.close();
					}

					/*
					 * ---------------------- Check bodyTemperature :  * ----------------------
					 */
					BigDecimal bodyTemperature = thisMetric.getBodyTemperature();
					if (bodyTemperature != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Body_Temperature.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Body_Temperature.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Body_Temperature.value());
						}
						super.close();
					}

					/*
					 * --------------------------- Check systolicBloodPressure : * -------------------------
					 */
					BigInteger symbolicBloodPressure = thisMetric.getSystolicBloodPressure();
					if (symbolicBloodPressure != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Systolic_Blood_Pressure.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Systolic_Blood_Pressure.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric, PatientVitalType.Systolic_Blood_Pressure.value());
						}
						super.close();
					}

					/*
					 * ----------------------------- Check * diastolicBloodPressure : ---------------------------
					 */
					BigInteger diastolicBloodPressure = thisMetric.getDiastolicBloodPressure();
					if (diastolicBloodPressure != null) {
						PtntVtlTypKey = getKeyFromCode(LT_PTNTVTLTYP, PatientVitalType.Diastolic_Blood_Pressure.value());
						Long ptntMetricsKey = PtntMtrcsKeyFound(connection, patientKey, PtntVtlTypKey, consumerId);
						super.close();
						if (ptntMetricsKey != null && ptntMetricsKey > 0L) {
							updateRow(connection, patientKey, ptntMetricsKey, PtntVtlTypKey, thisMetric, PatientVitalType.Diastolic_Blood_Pressure.value());
						} else {
							insertRow(connection, patientKey, PtntVtlTypKey, thisMetric,PatientVitalType.Diastolic_Blood_Pressure.value());
						}
						super.close();
					}
				}
			}
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertMetrics, patientKey : " + patientKey);		}
		}
	}
	
	private Long PtntMtrcsKeyFound(Connection connection, long patientKey, long PtntVtlTypKey, String consumerId) throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		Long ptntMtrcsKey = null ;
		ps = connection.prepareStatement(QUERYSQL);
		ps.setLong(1, patientKey);
		ps.setLong(2, PtntVtlTypKey);
		ps.setString(3, consumerId);
		rs = ps.executeQuery( );
		
		if (rs.next()) {
			ptntMtrcsKey = rs.getLong("PTNTMTRCSKEY" );
		}
		return ptntMtrcsKey;
	}
	
	private void insertRow(Connection connection, long patientKey,	long PtntVtlTypKey,	PatientMetrics thisMetric,	String elementName) 
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		ps = connection.prepareStatement(INSERTSQL);
		setParams(patientKey, PtntVtlTypKey ,  thisMetric, elementName);
		
		Long PtntMtrcsKey = IdGenerator.generate(connection, "PtntMtrcs");
		setPsLongParam(8, PtntMtrcsKey);
		
		ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug("new record was inserted into PtntMtrcs table with PtntMtrcsKey = " + PtntMtrcsKey);}
	}
	
	private void updateRow(Connection connection, long patientKey, long ptntMetricsKey, long PtntVtlTypKey,	PatientMetrics thisMetric, String elementName)
			throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		ps = connection.prepareStatement(UPDATESQL);
		setParams(patientKey, PtntVtlTypKey, thisMetric, elementName);
		setPsLongParam(8, ptntMetricsKey);

		ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug("PtntMtrcs table was updated with PtntMtrcsKey = " + ptntMetricsKey);}
	}

	private void setParams (long patientKey, long PtntVtlTypKey , PatientMetrics thisMetric, String elementName) throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		if (elementName.equals(PatientVitalType.Weight.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getWeightDate());
			setPsBigDecimalParam(2, thisMetric.getWeight());
			setPsBooleanParam(3, thisMetric.isWeightMeasurementFlag());
		}
		
		if (elementName.equals( PatientVitalType.Height.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getHeightDate());
			setPsBigDecimalParam(2, thisMetric.getHeight());
			setPsBooleanParam(3, thisMetric.isHeightMeasurementFlag());
		}
		
		
		if (elementName.equals(PatientVitalType.Glucose.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsBigDecimalParam(2, thisMetric.getBloodGlucose());
			setPsBooleanParam(3, thisMetric.isBloodGlucoseAutomaticMeasurementFlag());
		}
		
		if (elementName.equals(PatientVitalType.HeartRate.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsLongParam(2, thisMetric.getHeartRate());
			setPsBooleanParam(3, true);
		}
		
		
		if (elementName.equals(PatientVitalType.Respiratory_Rate.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsLongParam(2, thisMetric.getRespiratoryRate());
			setPsBooleanParam(3, true);
		}
		
		
		if (elementName.equals(PatientVitalType.Body_Temperature.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsBigDecimalParam(2, thisMetric.getBodyTemperature());
			setPsBooleanParam(3, true);
		}
		
		if (elementName.equals(PatientVitalType.Systolic_Blood_Pressure.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsLongParam(2, thisMetric.getSystolicBloodPressure());
			setPsBooleanParam(3, true);
		}
		
		
		if (elementName.equals(PatientVitalType.Diastolic_Blood_Pressure.value())) {
			setPsXMLGregorianCalendarParam(1, thisMetric.getVitalsDate());
			setPsLongParam(2, thisMetric.getDiastolicBloodPressure());
			setPsBooleanParam(3, true);
		}
		
		setPsStringParam(4, thisMetric.getConsumerId());
		setPsStringParam(5, thisMetric.getProducerId());
		setPsLongParam(6, PtntVtlTypKey);
		setPsLongParam(7, patientKey);
	}
}		