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
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class AdverseDrugReactionGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(AdverseDrugReactionGet.class);
	private final static String QUERYSQL = "SELECT "
			+ "CNSMRID, PRDCRID, CRTTIMESTAMP, UPDTTIMESTAMP, DESCRENG, OCCRSTRTTIMESTAMP, UPDTRCRDR, RCRDR, SPRVSR, RPTR, SVCLOCKEY, PTNTADVRSDRGRCTNKEY "
			+ "FROM PTNTADVRSDRGRCTN "
			+ "WHERE PTNTKEY = ?";			
	
	private List<AdverseDrugReaction> populate(Connection connection, Long patientKey) throws SQLException, ParseException, DatatypeConfigurationException, CDRInternalException, NamingException, IOException 
	{
		Long timer = System.currentTimeMillis();
//		if (logger.isInfoEnabled())  {logger.info("StartApiCall: AdverseDrugReactionGet.populate. PatientKey : " + patientKey);}

		List<AdverseDrugReaction> adverseDrugReactionList = new ArrayList<AdverseDrugReaction>();
		PreparedStatement preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientKey);
	
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: AdverseDrugReactionGet. patientKey : " + patientKey );
		ResultSet resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: AdverseDrugReactionGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		try {
		while(resultSet.next())
		{
			AdverseDrugReaction adverseDrugReaction = new AdverseDrugReaction();
			adverseDrugReaction.setConsumerId(resultSet.getString("CNSMRID"));
			adverseDrugReaction.setProducerId(resultSet.getString("PRDCRID"));
			adverseDrugReaction.setCreateTimestamp(CommonUtil.getCalendar("CRTTIMESTAMP",resultSet));
			adverseDrugReaction.setDescriptionEnglish("DESCRENG");
			adverseDrugReaction.setUpdateTimestamp(CommonUtil.getCalendar("UPDTTIMESTAMP",resultSet));
			adverseDrugReaction.setOccuranceStartTimestamp(CommonUtil.getCalendar("OCCRSTRTTIMESTAMP",resultSet));
			Long updateRecorderKey = CommonUtil.getLong("UPDTRCRDR",resultSet);
			if(updateRecorderKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, updateRecorderKey);
				adverseDrugReaction.setUpdateRecorder(recorder);				
			};
			
			Long recorderKey = CommonUtil.getLong("RCRDR",resultSet);
			if(recorderKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, recorderKey);
				adverseDrugReaction.setRecorder(recorder);				
			};

			Long supervisorKey = CommonUtil.getLong("SPRVSR",resultSet);
			if(supervisorKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Supervisor supervisor = personRoleViewGet.fetchSupervisor(connection, supervisorKey);
				adverseDrugReaction.setSupervisor(supervisor);				
			};
			
			Long reporterKey = CommonUtil.getLong("RPTR",resultSet);
			if(reporterKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Reporter reporter = personRoleViewGet.fetchReporter(connection, reporterKey);
				adverseDrugReaction.setReporter(reporter);				
			};
			
			Long locationKey = CommonUtil.getLong("SVCLOCKEY",resultSet);
			if(locationKey != null)
			{
				LocationGet locationGet = new LocationGet();
				Location location = locationGet.fetch(connection, locationKey);
				adverseDrugReaction.setServiceLocation(location);
			}
			
			Long patientAdverseDrugReactionKey = CommonUtil.getLong("PTNTADVRSDRGRCTNKEY",resultSet);
			NoteGet noteGet = new NoteGet("PTNTADVRSDRGRCTNNT", "PTNTADVRSDRGRCTNKEY", false);
			List<Note> notes = noteGet.fetch(connection, patientAdverseDrugReactionKey);
			if(notes!=null && notes.size() > 0)
				adverseDrugReaction.getNote().addAll(notes);
			
			adverseDrugReactionList.add(adverseDrugReaction);
		};
		}finally {
			CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
			}
		
		
//		if (logger.isInfoEnabled())  {logger.info("EndApiCall: AdverseDrugReactionGet.populate. PatientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	////	CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		return adverseDrugReactionList;
	}
	
	public List<AdverseDrugReaction> fetch(Connection connection, Long patientKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
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
		} catch (CDRInternalException e) {
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
