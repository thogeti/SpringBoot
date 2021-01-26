package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTCTGRYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTTYP;

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
import ca.sdm.cdr.jdbc.query.api.CDRMedReviewGet;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Note;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.NoteCategory;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.NoteType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Recorder;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Supervisor;

public class NoteMedReviewGet extends CDRMedReviewGet  {
	private static Logger logger = LogManager.getLogger(NoteMedReviewGet.class);
	private boolean hasNoteType = false;
	private String QUERYSQL = "SELECT "
			+ "NT.ISPHRMCST NT_ISPHRMCST, NT.UPDTTIMESTAMP NT_UPDTTIMESTAMP, NT.NTENG NT_NTENG, NT.CNSMRID NT_CNSMRID, NT.PRDCRID NT_PRDCRID, "
			+ "NT.NTFR NT_NTFR, NT.CRTTIMESTAMP NT_CRTTIMESTAMP, NT.CRTUSRID NT_CRTUSRID, NT.RCRDRKEY NT_RCRDRKEY, NT.SPRVSRKEY NT_SPRVSRKEY, "
			+ "NT.NTCTGRYTYPKEY NT_NTCTGRYTYPKEY, NT.NTTYPKEY NT_NTTYPKEY, NT.LOCKEY NT_LOCKEY "
			+ "FROM NTTABLE NT "
			+ "WHERE NT.FOREIGNKEY = ? ";

	
	public NoteMedReviewGet(String tableName, String foreignKeyName, boolean hasNoteType) throws SQLException {
		QUERYSQL = QUERYSQL.replace("NTTABLE", tableName);
		QUERYSQL = QUERYSQL.replace("FOREIGNKEY", foreignKeyName);
		this.hasNoteType = hasNoteType;
		if(!hasNoteType)
			QUERYSQL = QUERYSQL.replace("NT.NTTYPKEY NT_NTTYPKEY,", "");
	}
	
	
	public List<Note> fetch(Connection connection, Long foreignKey) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		try {
			return populate(connection, foreignKey);
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
		}
		finally {
			super.close();
		}
	}
	
	private List<Note> populate(Connection connection, Long foreignKey) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: NoteMedReviewGet.populate. foreignKey : " + foreignKey);}

		List<Note> noteList = new ArrayList<Note>();
		preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, foreignKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) { logger.debug("StartExecuteQuery: NoteMedReviewGet. foreignKey : " + foreignKey );}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: NoteMedReviewGet. foreignKey : " + foreignKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			Note note = new Note();
			noteList.add(note);
			note.setConsumerId(getString("NT_CNSMRID"));
			note.setProducerId(getString("NT_PRDCRID"));
			note.setNoteFrench(getString("NT_NTFR"));
			note.setNoteEnglish(getString("NT_NTENG"));
			note.setCreateTimestamp(getCalendar("NT_CRTTIMESTAMP"));
			note.setUpdateTimestamp(getCalendar("NT_UPDTTIMESTAMP"));
			note.setIsPharmacist(CommonUtil.convertYesNoFlagToBoolean(getString("NT_ISPHRMCST")));
			note.setCreateUserId(getString("NT_CRTUSRID"));
			note.setDispenser(null);
			Long recorderKey = getLong("NT_RCRDRKEY");
			if(recorderKey!=null)
			{
				PersonRoleViewMedReviewGet personRoleViewMedReviewGet = new PersonRoleViewMedReviewGet();
				Recorder recorder = personRoleViewMedReviewGet.fetchRecorder(connection, recorderKey);
				note.setRecorder(recorder);
			}
			
			Long supervisorKey = getLong("NT_SPRVSRKEY");
			if(supervisorKey != null)
			{
				PersonRoleViewMedReviewGet personRoleViewMedReviewGet = new PersonRoleViewMedReviewGet();
				Supervisor supervisor = personRoleViewMedReviewGet.fetchSupervisor(connection, supervisorKey);
				note.setSupervisor(supervisor);
			}
			
			if (hasNoteType) {
				Long noteTypeKey = getLong("NT_NTTYPKEY");
				if (noteTypeKey != null) {
					String noteTypeCode = getCodeFromKey(LT_NTTYP, noteTypeKey);
					note.setNoteTypeCode(noteTypeCode);
				}
			}
			;
			
			Long noteCategoryTypeKey =  getLong("NT_NTCTGRYTYPKEY");
			if(noteCategoryTypeKey != null)
			{
				String noteCategoryTypeCode = getCodeFromKey(LT_NTCTGRYTYP, noteCategoryTypeKey);
				note.setNoteCategory(noteCategoryTypeCode);
			}

			Long locationKey = getLong("NT_LOCKEY");
			if(locationKey != null)
			{
				
			};			
		};
		if(logger.isInfoEnabled()) {logger.info("EndApiCall: NoteMedReviewGet.populate. foreignKey : " + foreignKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return noteList;
	}
}
