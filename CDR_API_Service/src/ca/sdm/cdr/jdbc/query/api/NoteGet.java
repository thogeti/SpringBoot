package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTCTGRYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTTYP;

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
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class NoteGet extends CDRGet  {
	private static Logger logger = LogManager.getLogger(NoteGet.class);
	private boolean hasNoteType = false;
	private String QUERYSQL = "SELECT "
			+ "NT.ISPHRMCST NT_ISPHRMCST, NT.UPDTTIMESTAMP NT_UPDTTIMESTAMP, NT.NTENG NT_NTENG, NT.CNSMRID NT_CNSMRID, NT.PRDCRID NT_PRDCRID, "
			+ "NT.NTFR NT_NTFR, NT.CRTTIMESTAMP NT_CRTTIMESTAMP, NT.CRTUSRID NT_CRTUSRID, NT.RCRDRKEY NT_RCRDRKEY, NT.SPRVSRKEY NT_SPRVSRKEY, "
			+ "NT.NTCTGRYTYPKEY NT_NTCTGRYTYPKEY, NT.NTTYPKEY NT_NTTYPKEY, NT.LOCKEY NT_LOCKEY "
			+ "FROM NTTABLE NT "
			+ "WHERE NT.FOREIGNKEY = ? ";

	
	public NoteGet(String tableName, String foreignKeyName, boolean hasNoteType) throws SQLException {
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
//		if (logger.isInfoEnabled())  {logger.info("StartApiCall: NoteGet.populate. foreignKey : " + foreignKey);}

		List<Note> noteList = new ArrayList<Note>();
		PreparedStatement preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, foreignKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: NoteGet. foreignKey : " + foreignKey );
		ResultSet resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: NoteGet. foreignKey : " + foreignKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		while(resultSet.next())
		{
			Note note = new Note();
			noteList.add(note);
			note.setConsumerId(resultSet.getString("NT_CNSMRID"));
			note.setProducerId(resultSet.getString("NT_PRDCRID"));
			note.setNoteFrench(resultSet.getString("NT_NTFR"));
			note.setNoteEnglish(resultSet.getString("NT_NTENG"));
			note.setCreateTimestamp(CommonUtil.getCalendar("NT_CRTTIMESTAMP",resultSet));
			note.setUpdateTimestamp(CommonUtil.getCalendar("NT_UPDTTIMESTAMP",resultSet));
			note.setIsPharmacist(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("NT_ISPHRMCST")));
			note.setCreateUserId(resultSet.getString("NT_CRTUSRID"));
			note.setDispenser(null);
			Long recorderKey = CommonUtil.getLong("NT_RCRDRKEY",resultSet);
			if(recorderKey!=null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Recorder recorder = personRoleViewGet.fetchRecorder(connection, recorderKey);
				note.setRecorder(recorder);
			}
			
			Long supervisorKey = CommonUtil.getLong("NT_SPRVSRKEY",resultSet);
			if(supervisorKey != null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Supervisor supervisor = personRoleViewGet.fetchSupervisor(connection, supervisorKey);
				note.setSupervisor(supervisor);
			}
			
			if (hasNoteType) {
				Long noteTypeKey = CommonUtil.getLong("NT_NTTYPKEY",resultSet);
				if (noteTypeKey != null) {
					String noteTypeCode = getCodeFromKey(LT_NTTYP, noteTypeKey);
					note.setNoteTypeCode(NoteType.fromValue(noteTypeCode));
				}
			}
			;
			
			Long noteCategoryTypeKey =  CommonUtil.getLong("NT_NTCTGRYTYPKEY",resultSet);
			if(noteCategoryTypeKey != null)
			{
				String noteCategoryTypeCode = getCodeFromKey(LT_NTCTGRYTYP, noteCategoryTypeKey);
				note.setNoteCategory(NoteCategory.fromValue(noteCategoryTypeCode));
			}

			Long locationKey = CommonUtil.getLong("NT_LOCKEY",resultSet);
			if(locationKey != null)
			{
				
			};			
		};
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
//		if (logger.isInfoEnabled())  {logger.info("EndApiCall: NoteGet.populate. foreignKey : " + foreignKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return noteList;
	}
}
