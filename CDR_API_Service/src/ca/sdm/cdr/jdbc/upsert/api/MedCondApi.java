package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMedicalCondition;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class MedCondApi extends CDRUpsert {
	   final static Logger logger = LogManager.getLogger("MedCondApi.class");

	   private final static String UPDATESQL =
				"UPDATE PTNTMDCLCNDTN " + 
					    "		SET (CNDTNDESCR, CNDTNSTRTDT, CNDTNENDDT,CHRONICCNDTNFLG,CNDTNCRTTIMESTAMP, " +
				  	 	"			CNDTNACTFLG,RPTRKEY,PRDCRID,CNSMRID,RCRDRKEY,SPRVSRKEY,STATCHNGRSNTYPKEY, " +
				  	 	"			LOCKEY, PTNTKEY) = (SELECT ?, " 
		  	 	+ CommonUtil.TO_TIMESTAMP_TZ + ", "
				+ CommonUtil.TO_TIMESTAMP_TZ + ", ?, " 
		  	 	+ CommonUtil.TO_TIMESTAMP_TZ 
		  	 	+ ", ?, ?, ?, ?, ?, ?, ?, ?, ? FROM DUAL) WHERE PTNTMDCLCNDTNKEY = ?";

	   private final static String INSERTSQL = 
				"INSERT INTO PTNTMDCLCNDTN "
					+ "(CNDTNDESCR, CNDTNSTRTDT, CNDTNENDDT,CHRONICCNDTNFLG,CNDTNCRTTIMESTAMP, " +
						 " CNDTNACTFLG,RPTRKEY,PRDCRID,CNSMRID,RCRDRKEY,SPRVSRKEY,STATCHNGRSNTYPKEY, " +
						 " LOCKEY, PTNTKEY, PTNTMDCLCNDTNKEY) " +
						 " VALUES (?, " 
				 + CommonUtil.TO_TIMESTAMP_TZ 
				 + ", " 
				 + CommonUtil.TO_TIMESTAMP_TZ 
				 + ",	?, " 
				 + CommonUtil.TO_TIMESTAMP_TZ 
				 + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		private List<PatientMedicalCondition> MedConditions = null;
		
		public MedCondApi (Patient patient)
		{
			MedConditions = patient.getPatientMedicalCondition();
		}

		public void upsertMedCond(Connection connection, Store store, long patientKey , List<PatientMedicalCondition> MedConditionList) throws SQLException, IOException, NamingException, CDRException
		{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertMedCond, patientKey : " + patientKey);	}	
			
			if (MedConditionList != null && MedConditionList.size() > 0) {
				for (PatientMedicalCondition thisCondition : MedConditions) {
					String consumerId = thisCondition.getConsumerId();

					if (consumerId == null)
						throw new ConsumerIdMissingException("PatientMetrics");

					Long medicalConditionKey;
					medicalConditionKey = FindUtil.findPatientMedicalConditionKey(connection, patientKey, consumerId);
					if (medicalConditionKey == null || medicalConditionKey <= 0L) {
						insertMedCond(connection, store, thisCondition, patientKey);
					} else {
						updateMedCond(connection, store, thisCondition, patientKey, medicalConditionKey);
					}

					NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.PATIENT_MEDICAL_CONDITION_NOTE);

					noteApi.upsertNoteList(connection, store, thisCondition.getNote(), medicalConditionKey);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertMedCond, patientKey : " + patientKey);		}
		}
		}
		
		private void insertMedCond(Connection connection, Store store, PatientMedicalCondition thisCondition, long patientKey) throws SQLException, IOException, NamingException, CDRException
		{
			Long locationKey 	= null;
			Long recorderKey 	= null;
			Long reporterKey 	= null; 
			Long supervisorKey 	= null;

			LocationApi locApi = new LocationApi();
			locationKey = locApi.findLocation(connection, store, thisCondition.getServiceLocation());
			
			PersonApi personApi = new PersonApi();
			/************************************************************************************************************/
			/*							 upsert into Recorder table											     	    */
			/************************************************************************************************************/
			Recorder recorder = thisCondition.getRecorder();
			if( recorder != null ) {
				PersonRole recorderPersonRole = new PersonRole (recorder);
				recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
			}

			/************************************************************************************************************/
			/*							 upsert into Supervisor table											     	    */
			/************************************************************************************************************/
			Supervisor supervisor= thisCondition.getSupervisor();
			if( supervisor != null ) {
				PersonRole supervisorPersonRole = new PersonRole (supervisor);
				supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
			}
			
			ps = connection.prepareStatement(INSERTSQL);
			
			long PtntMdclCndtnKey = IdGenerator.generate(connection, "PtntMdclCndtn");
			String updateReasonCode = (thisCondition.getUpdateReasonType()!=null) ? thisCondition.getUpdateReasonType().value() : null; 
			Long StatChngRsnTypKey = getKeyFromCode(LT_STATCHNGRSNTYP, updateReasonCode);
			
			CommonUtil.setPsStringParam(ps, 1, thisCondition.getConditionDescription());
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 2, thisCondition.getConditionStartDate());
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 3, thisCondition.getConditionEndDate());
			CommonUtil.setPsBooleanParam(ps, 4, thisCondition.isChronicConditionFlag() );
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 5, thisCondition.getConditionCreateTimestamp());
			CommonUtil.setPsBooleanParam(ps, 6, thisCondition.isConditionActiveFlag() );

			CommonUtil.setPsLongParam(ps, 7, reporterKey);
			CommonUtil.setPsStringParam(ps, 8, thisCondition.getProducerId());
			CommonUtil.setPsStringParam(ps, 9, thisCondition.getConsumerId());
			CommonUtil.setPsLongParam(ps, 10, recorderKey);
			CommonUtil.setPsLongParam(ps, 11, supervisorKey);

			CommonUtil.setPsLongParam(ps, 12, StatChngRsnTypKey); 
			CommonUtil.setPsLongParam(ps, 13, locationKey);
			CommonUtil.setPsLongParam(ps, 14, patientKey);
			CommonUtil.setPsLongParam(ps, 15, PtntMdclCndtnKey);
 
			ps.executeUpdate();
			if (logger.isInfoEnabled())  {logger.info("new record was inserted into PtntMdclCndtn table with PtntMdclCndtnKey = " +  PtntMdclCndtnKey);}
		}
		
		private void updateMedCond(Connection connection, Store store, PatientMedicalCondition thisCondition, long patientKey , long medicalConditionKey) throws SQLException, IOException, NamingException, CDRException
		{
			Long locationKey 	= null;
			Long recorderKey 	= null;
			Long reporterKey 	= null; 
			Long supervisorKey 	= null;

			LocationApi locApi = new LocationApi();
			locationKey = locApi.findLocation(connection, store, thisCondition.getServiceLocation());
			
			PersonApi personApi = new PersonApi();
			/************************************************************************************************************/
			/*							 upsert into Recorder table											     	    */
			/************************************************************************************************************/
			Recorder recorder = thisCondition.getRecorder();
			if( recorder != null ) {
				PersonRole recorderPersonRole = new PersonRole (recorder);
				recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
			}

			/************************************************************************************************************/
			/*							 upsert into Supervisor table											     	    */
			/************************************************************************************************************/
			Supervisor supervisor= thisCondition.getSupervisor();
			if( supervisor != null ) {
				PersonRole supervisorPersonRole = new PersonRole (supervisor);
				supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
			}
			
			ps = connection.prepareStatement(UPDATESQL);
			
			String updateReasonCode = (thisCondition.getUpdateReasonType()!=null) ? thisCondition.getUpdateReasonType().value() : null;
			Long StatChngRsnTypKey = getKeyFromCode(LT_STATCHNGRSNTYP, updateReasonCode);
			
			CommonUtil.setPsStringParam(ps, 1, thisCondition.getConditionDescription());
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 2, thisCondition.getConditionStartDate());
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 3, thisCondition.getConditionEndDate());
			CommonUtil.setPsBooleanParam(ps, 4, thisCondition.isChronicConditionFlag() );
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 5, thisCondition.getConditionCreateTimestamp());
			CommonUtil.setPsBooleanParam(ps, 6, thisCondition.isConditionActiveFlag() );

			CommonUtil.setPsLongParam(ps, 7, reporterKey);
			CommonUtil.setPsStringParam(ps, 8, thisCondition.getProducerId());
			CommonUtil.setPsStringParam(ps, 9, thisCondition.getConsumerId());
			CommonUtil.setPsLongParam(ps, 10, recorderKey);
			CommonUtil.setPsLongParam(ps, 11, supervisorKey);

			CommonUtil.setPsLongParam(ps, 12, StatChngRsnTypKey); 
			CommonUtil.setPsLongParam(ps, 13, locationKey);
			CommonUtil.setPsLongParam(ps, 14, patientKey);
			CommonUtil.setPsLongParam(ps, 15, medicalConditionKey);
			
			ps.executeUpdate();
			if (logger.isInfoEnabled())  {logger.info("PtntMdclCndtn table was updated with PtntMdclCndtnKey = " +  medicalConditionKey);}
		}
}
