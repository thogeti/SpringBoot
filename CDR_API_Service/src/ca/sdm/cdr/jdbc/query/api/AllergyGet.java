package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ALRGYCONFSTATTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ALRGYSVRTYLVLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ALRGYTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.AllergySeverityLevel;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyStatus;
import ca.shoppersdrugmart.rxhb.ehealth.AllergyTest;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.NonDrugAllergen;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class AllergyGet extends CDRGet {

	private static Logger logger = LogManager.getLogger(AllergyGet.class);
	private final static String QUERYSQL = "SELECT "
	+ "ALRGYACTFLG, INTOLERANCEFLG, CNSMRID, CRTTIMESTAMP, DESCRENG, "
	+ "PRDCRID, DESCRFR, ALRGYSTRTDT, RPTDT, UPDTTIMESTAMP, "
	+ "UPDTRSN, SVCLOCKEY, NONDRGALLRGN, RXID, RCRDRKEY, "
	+ "RPTRKEY, UPDTRCRDR, SPRVSRKEY, ALLRGNTYPKEY, "
	+ "ALRGYCONFSTATTYPKEY, ALRGYSVRTYLVLTYPKEY, ALRGYTYPKEY, DRGKEY, LOCKEY, "
	+ "PTNTALRGYKEY "
	+ "FROM PTNTALRGY "
	+ "WHERE PTNTKEY = ?";
	
	public List<Allergy> fetch(Connection connection, Long patientKey, Map<Long, Drug> drgPK) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		try {
			return populate(connection, patientKey,drgPK);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
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
		} finally
		{
			super.close();
		}
	}
	
	private List<Allergy> populate(Connection connection, Long patientKey, Map<Long, Drug> drgPK) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException 
	{
		Long timer = System.currentTimeMillis();
//		logger.info("StartApiCall: AllergyGet.populate. PatientKey : " + patientKey);

		List<Allergy> allergyList = new ArrayList<Allergy>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet= null;
		try {
		preparedStatement=connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: AllergyGet. patientKey : " + patientKey );
		  resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: AllergyGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		while(resultSet.next())
		{
			Allergy allergy = new Allergy();
			allergy.setAllergyActiveFlag(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("ALRGYACTFLG")));
			allergy.setIntoleranceFlag(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("INTOLERANCEFLG")));
			allergy.setConsumerId(resultSet.getString("CNSMRID" ));
			allergy.setCreateTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CRTTIMESTAMP"), true));
			allergy.setDescriptionEnglish(resultSet.getString("DESCRENG"));
			allergy.setProducerId(resultSet.getString("PRDCRID"));
			allergy.setDescriptionFrench(resultSet.getString("DESCRFR"));

			Long allergyTypeKey = CommonUtil.getLong("ALRGYTYPKEY",resultSet);
			if (allergyTypeKey!=null)
			{	
				String allergyTypeCode = getCodeFromKey(LT_ALRGYTYP,allergyTypeKey);
				allergy.setAllergyType(allergyTypeCode);
			};	

			allergy.setAllergyStartDate(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("ALRGYSTRTDT")));
			allergy.setReportedDate(CommonUtil.getXMLGregorianCalendarByDate(resultSet.getDate("RPTDT")));
			allergy.setUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("UPDTTIMESTAMP"),true));
			allergy.setUpdateReason(resultSet.getString("UPDTRSN"));

			Long locationKey = CommonUtil.getLong("SVCLOCKEY",resultSet);			
			if(locationKey != null)
			{
				LocationGet locationGet = new LocationGet();
				Location location = locationGet.fetch(connection, locationKey);
				allergy.setServiceLocation(location);
			}
			
			String nonDrugAllergy =resultSet.getString("NONDRGALLRGN");
			if(nonDrugAllergy !=null)
			{	
			allergy.setNonDrugAllergen(NonDrugAllergen.fromValue(resultSet.getString("NONDRGALLRGN").trim()));
			};
			
			Long recorderKey = CommonUtil.getLong("RCRDRKEY",resultSet);
			if(recorderKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, recorderKey);
				allergy.setRecorder(recorder);				
			};
			
			Long reporterKey = CommonUtil.getLong("RPTRKEY",resultSet);
			if(reporterKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Reporter reporter = personRoleViewGet.fetchReporter(connection, reporterKey);
				allergy.setReporter(reporter);				
			};
			
			Long updateRecorderKey = CommonUtil.getLong("UPDTRCRDR",resultSet);
			if(updateRecorderKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, updateRecorderKey);
				allergy.setUpdateRecorder(recorder);				
			};
			
			Long supervisorKey = CommonUtil.getLong("SPRVSRKEY",resultSet);
			if(supervisorKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Supervisor supervisor = personRoleViewGet.fetchSupervisor(connection, supervisorKey);
				allergy.setSupervisor(supervisor);				
			};
			
			Long allergyConfirmationStatusKey = CommonUtil.getLong("ALRGYCONFSTATTYPKEY",resultSet);
			if(allergyConfirmationStatusKey != null)
			{	
				String allergyConfirmationStatusCode = getCodeFromKey(LT_ALRGYCONFSTATTYP, allergyConfirmationStatusKey);
				allergy.setAllergyConfirmationStatusCode(AllergyStatus.fromValue(allergyConfirmationStatusCode));
			};	
			
			Long allergySeverityLevelTypeKey = CommonUtil.getLong("ALRGYSVRTYLVLTYPKEY",resultSet);
			if (allergySeverityLevelTypeKey != null) {
				String allergySeverityLevelTypeCode = getCodeFromKey(LT_ALRGYSVRTYLVLTYP, allergySeverityLevelTypeKey);
				allergy.setAllergySeverityCode(AllergySeverityLevel.fromValue(allergySeverityLevelTypeCode));
			};
			

			Long drugKey = CommonUtil.getLong("DRGKEY",resultSet);
			if(drugKey != null)
			{
				DrugGet drugGet = new DrugGet();
				Drug drug = drugGet.fetch(connection, drugKey);
				if (drug != null) {
					allergy.setDrug(drug);	
				}
			};
			
			Long patientAllergyKey = CommonUtil.getLong("PTNTALRGYKEY",resultSet);
			NoteGet noteGet = new NoteGet("PTNTALRGYNT", "PTNTALRGYKEY", true);
			List<Note> notes = noteGet.fetch(connection, patientAllergyKey);
			if(notes!=null && notes.size() > 0)
				allergy.getNote().addAll(notes);
			
			AllergyTestGet allergyTestGet = new AllergyTestGet();
			List<AllergyTest> allergyTests = allergyTestGet.fetch(connection, patientAllergyKey);
			if (allergyTests != null && allergyTests.size() > 0)
				allergy.getAllergyTest().addAll(allergyTests);			
			
			allergyList.add(allergy);
		}
		}finally {
		
//		logger.info("EndApiCall: AdverseDrugReactionGet.populate. PatientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		return allergyList;
	}
}
