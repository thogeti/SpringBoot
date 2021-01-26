package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class NoteApi  extends CDRUpsert {
	
	private final static Logger logger = LogManager.getLogger("NoteApi");
	
	private CDREnumerations.NoteTypeTable noteTypeTable;
	
//	Long inputPatientKey;
	
	public NoteApi ( CDREnumerations.NoteTypeTable noteTypeTable )
	{
		this.noteTypeTable = noteTypeTable;
	}	
	
	
	public void upsertNoteList(Connection connection, Store store, List<Note> notes  , Long parentKey) throws SQLException, IOException, NamingException, CDRException
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertNoteList, parentKey : " + parentKey);}		
			
			for (Note note : notes) {
				Map<String, Object> noteData = findNote(connection, note, parentKey);
				Long noteKey = ( Long ) noteData.get("NTKEY");
				Timestamp updateTimestampDb = (Timestamp) noteData.get("UPDTTIMESTAMP");
				boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, note.getUpdateTimestamp());

				if( isUpdateRequestNew == true )
				{
					if ( noteKey != null && noteKey > 0 ) {
						// The note exist, and needs to be updated
						updateNote(connection, noteKey, store, note);
					} else {
						insertNote(connection, store, note, parentKey);
					}
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertNoteList, parentKey : " + parentKey);		}
		}
	}
	
	private void insertNote(Connection connection, Store store, Note note , Long parentKey ) throws SQLException, IOException, NamingException, CDRException
	{
		Long recorderKey 	= null;
//		Long dispenserKey 	= null; 
		Long supervisorKey 	= null;
		Long locationKey 	= null;

		PersonApi personApi = new PersonApi();
//		LocationApi locApi = new LocationApi();
		
		String storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
		locationKey = FindUtil.findLocationKey(connection, store.getConsumerId(), storeNumberString);
		
		
//		This will be processed in personApi.upsertPerson			
//		CntctMthdApi CntctMthd = new CntctMthdApi(connection);
		

		
		/************************************************************************************************************/
		/*							 upsert into Recorder table											     	    */
		/************************************************************************************************************/
		Recorder recorder = note.getRecorder();
		if( recorder != null ) {
			PersonRole recorderPersonRole = new PersonRole (recorder);
			recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
			
//			This will be processed in personApi.upsertPerson			
//			CntctMthd.upsertCntctMthd(store, recorderKey, recorderPersonRole.getPerson());
			
//			locationKey = locApi.findLocation(store, thisCondition.getServiceLocation());			
//          CntctMthd.upsertCntctMthd(store, recorderKey, locationKey, recorderPersonRole.getPerson());			
			
		}
		
		/************************************************************************************************************/
		/*							 upsert into Supervisor table											     	    */
		/************************************************************************************************************/
		Supervisor supervisor= note.getSupervisor();
		if( supervisor != null ) {
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
			
//			This will be processed in personApi.upsertPerson
//			CntctMthd.upsertCntctMthd(store, supervisorKey, supervisorPersonRole.getPerson());
		}
		
		/************************************************************************************************************/
		/*							 upsert into Dispenser table IS NOT REQUIRED. 						     	    */
		/************************************************************************************************************
		Dispenser dispenser= note.getDispenser();
		if( dispenser != null )
		{
			PersonRole dispenserPersonRole = new PersonRole (dispenser);
			dispenserKey = personApi.upsertPerson(store, dispenserPersonRole);

		}
		 */
		
		/************************************************************************************************************/
		/*							 upsert into Dispenser table											     	    */
		/************************************************************************************************************/
		String noteCategoryCode =  (note.getNoteCategory()!=null) ? note.getNoteCategory().value() : null;
		Long ntCtgryTypKey = (!StringUtil.isEmpty(noteCategoryCode)) ? getKeyFromCode(LT_NTCTGRYTYP, noteCategoryCode) : null;
		String noteTypeCode = (note.getNoteTypeCode()!=null) ? note.getNoteTypeCode().value() : null;
		Long ntTypKey = (!StringUtil.isEmpty(noteTypeCode)) ? getKeyFromCode(LT_NTTYP, noteTypeCode) : null;
		
/*		
		Long generatedNoteKey = IdGenerator.generate(connection, noteTypeTable.getTableName());
		String insertNoteQuery =  "insert into " + noteTypeTable.getTableName() + 
				" ( isPhrmcst	,	upDttmstamp	,	NtEng			,	CnsmrId		, "
				+ " PrdcrId		,	NtFr		,	CrtTimestamp	,	CrtUsrId	, " 
			    + " RcrdrKey	,	SprvsrKey	,	NtCtgryTypKey	,	NtTypKey	, "
			    + " LocKey		,  " + noteTypeTable.getTableName()+"Key";
*/		

		if ( noteTypeTable == NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE )
		{
			
		}
		Long generatedNoteKey = IdGenerator.generate(connection, noteTypeTable.getNoteTableName());

		String insertNoteQuery ="" ;
		
		if ( noteTypeTable != NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE )
		{
			insertNoteQuery =  "insert into " + noteTypeTable.getNoteTableName() + 
					" ( isPhrmcst	,	UPDTTIMESTAMP,	NtEng			,	CnsmrId		 "
					+ " , PrdcrId		,	NtFr		,	CrtTimestamp	,	CrtUsrId " 
				    + " , RcrdrKey	,	SprvsrKey	,	NtCtgryTypKey "
				    + " , LocKey		,  " + noteTypeTable.getNoteTableName()+"Key ," +noteTypeTable.getTableName() +"Key " 
					+ "   , NtTypKey	) "  
					+ " values (?,									"
					+ "			" + CommonUtil.TO_TIMESTAMP_TZ + ","
					+ "			?,?,?,?,							"
					+ "			" + CommonUtil.TO_TIMESTAMP_TZ + ","
					+ "			?,?,?,?,?,?,? , ?)";
		}
		else
		{
			insertNoteQuery =  "insert into " + noteTypeTable.getNoteTableName() + 
					" ( isPhrmcst	,	UPDTTIMESTAMP,	NtEng			,	CnsmrId		 "
					+ " , PrdcrId		,	NtFr		,	CrtTimestamp	,	CrtUsrId " 
				    + " , RcrdrKey	,	SprvsrKey	,	NtCtgryTypKey "
				    + " , LocKey		,  " + noteTypeTable.getNoteTableName()+"Key ," +noteTypeTable.getTableName() +"Key " 
					+ "  ) "  
					+ " values (?,									"
					+ "			" + CommonUtil.TO_TIMESTAMP_TZ + ","
					+ "			?,?,?,?,							"
					+ "			" + CommonUtil.TO_TIMESTAMP_TZ + ","
					+ "			?,?,?,?,?,?,? )";
					
		}

		if(logger.isDebugEnabled()) {logger.debug("insertNoteQuery : " + insertNoteQuery );}
		ps = connection.prepareStatement(insertNoteQuery);
		CommonUtil.setPsBooleanParam(ps, 1, note.isIsPharmacist() );
		
		
//		ps.setDate(2 , new java.sql.Date(note.getUpdateTimestamp().toGregorianCalendar().getTime().getTime() ));
		CommonUtil.setPsXMLGregorianCalendarParam(ps, 2, note.getUpdateTimestamp());

		CommonUtil.setPsStringParam(ps, 3, note.getNoteEnglish());
		CommonUtil.setPsStringParam(ps, 4, note.getConsumerId());
		CommonUtil.setPsStringParam(ps, 5, note.getProducerId());
		CommonUtil.setPsStringParam(ps, 6, note.getNoteFrench());
		
//		ps.setDate(7 , new java.sql.Date(note.getCreateTimestamp().toGregorianCalendar().getTime().getTime()));
		CommonUtil.setPsXMLGregorianCalendarParam(ps, 7, note.getCreateTimestamp());
		
		CommonUtil.setPsStringParam(ps, 8, note.getCreateUserId());
		CommonUtil.setPsLongParam(ps, 9, recorderKey);
		CommonUtil.setPsLongParam(ps, 10, supervisorKey);
		CommonUtil.setPsLongParam(ps, 11, ntCtgryTypKey);
		CommonUtil.setPsLongParam(ps, 12, locationKey);
		CommonUtil.setPsLongParam(ps, 13, generatedNoteKey);
		CommonUtil.setPsLongParam(ps, 14, parentKey);
		
		if ( noteTypeTable != NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE )
		{
			CommonUtil.setPsLongParam(ps, 15, ntTypKey);
		}
		

		ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("New record was inserted into " + noteTypeTable.getTableName()  +
				    " with noteTypeTable.getTableName()" + "Key  = " + 
				      noteTypeTable.getTableName() + "Key");}
	}

	
	private void updateNote(Connection connection, Long noteKey , Store store, Note note ) throws SQLException, IOException, NamingException, CDRException
	{
		
		Long recorderKey 	= null;
//		Long dispenserKey 	= null; 
		Long supervisorKey 	= null;

		PersonApi personApi = new PersonApi();
//		CntctMthdApi CntctMthd = new CntctMthdApi(connection);


		/************************************************************************************************************/
		/*							 upsert into Recorder table											     	    */
		/************************************************************************************************************/

		Recorder recorder = note.getRecorder();
		if( recorder != null )
		{
			PersonRole recorderPersonRole = new PersonRole (recorder);
			recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
			
//			This will be processed in personApi.upsertPerson			
//			CntctMthd.upsertCntctMthd(store, recorderKey, recorderPersonRole.getPerson());
			
		}
		
		/************************************************************************************************************/
		/*							 upsert into Supervisor table											     	    */
		/************************************************************************************************************/
		Supervisor supervisor= note.getSupervisor();
		
		if( supervisor != null )
		{
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
			
//			This will be processed in personApi.upsertPerson			
//			CntctMthd.upsertCntctMthd(store, supervisorKey, supervisorPersonRole.getPerson());
		}
		
		/************************************************************************************************************/
		/*							 upsert into Dispenser table IS NOT REQUIRED. 						     	    */
		/************************************************************************************************************
		Dispenser dispenser= note.getDispenser();
		if( dispenser != null )
		{
			PersonRole dispenserPersonRole = new PersonRole (dispenser);
			dispenserKey = personApi.upsertPerson(store, dispenserPersonRole);

		}
		 */
		
		
		/************************************************************************************************************/
		/*							 upsert into Dispenser table											     	    */
		/************************************************************************************************************/
		
		/************************************************************************************************************/
		/*							 update Note table											     	    */
		/************************************************************************************************************/

		String noteCategoryCode = (note.getNoteCategory()!=null) ? note.getNoteCategory().value() : null;
		Long ntCtgryTypKey = (!StringUtil.isEmpty(noteCategoryCode)) ? getKeyFromCode(LT_NTCTGRYTYP, noteCategoryCode) : null;
		String noteTypeCode = (note.getNoteTypeCode()!=null) ? note.getNoteTypeCode().value() : null;
		Long ntTypKey = (!StringUtil.isEmpty(noteTypeCode)) ? getKeyFromCode(LT_NTTYP, noteTypeCode) : null;

		String updateNoteQuery = "" ;
		if ( noteTypeTable != NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE )
		{

			updateNoteQuery  =  "update " + noteTypeTable.getNoteTableName() + " set isPhrmcst = ?  , UPDTTIMESTAMP = " + CommonUtil.TO_TIMESTAMP_TZ + " , " +
					" NtEng = ? , CnsmrId = ? " +
					" , PrdcrId = ? , NtFr = ? , CrtTimestamp = " + CommonUtil.TO_TIMESTAMP_TZ + " , CrtUsrId = ?, "+
				    " RcrdrKey = ? , SprvsrKey = ? , NtCtgryTypKey = ? , NtTypKey = ?  " 
					+"Where "+ noteTypeTable.getNoteTableName()+"Key = ? " ;
		}
		else
		{

			updateNoteQuery  =  "update " + noteTypeTable.getNoteTableName() + " set isPhrmcst = ?  , UPDTTIMESTAMP = " + CommonUtil.TO_TIMESTAMP_TZ + " , " +
					" NtEng = ? , CnsmrId = ? " +
					" , PrdcrId = ? , NtFr = ? , CrtTimestamp = " + CommonUtil.TO_TIMESTAMP_TZ + " , CrtUsrId = ?, "+
				    " RcrdrKey = ? , SprvsrKey = ? , NtCtgryTypKey = ?   " 
					+"Where "+ noteTypeTable.getNoteTableName()+"Key = ? " ;
			
		}
		
		ps = connection.prepareStatement(updateNoteQuery);
//		ps.setString(1 , CommonUtil.convertBooleanToYesNoFlag( note.isIsPharmacist() ));
		CommonUtil.setPsBooleanParam(ps, 1, note.isIsPharmacist() );
		
//		ps.setDate(2 , new java.sql.Date(note.getUpdateTimestamp().toGregorianCalendar().getTime().getTime() ));
		CommonUtil.setPsXMLGregorianCalendarParam(ps, 2, note.getUpdateTimestamp());
		
		CommonUtil.setPsStringParam(ps, 3, note.getNoteEnglish());
		CommonUtil.setPsStringParam(ps, 4, note.getConsumerId());
		CommonUtil.setPsStringParam(ps, 5, note.getProducerId());
		CommonUtil.setPsStringParam(ps, 6, note.getNoteFrench());

//		ps.setDate(7 , new java.sql.Date(note.getCreateTimestamp().toGregorianCalendar().getTime().getTime()));
		CommonUtil.setPsXMLGregorianCalendarParam(ps, 7, note.getCreateTimestamp());
		
		CommonUtil.setPsStringParam(ps, 8, note.getCreateUserId());
		CommonUtil.setPsLongParam(ps, 9, recorderKey);
		CommonUtil.setPsLongParam(ps, 10, supervisorKey);
		CommonUtil.setPsLongParam(ps, 11, ntCtgryTypKey);
		if ( noteTypeTable != NoteTypeTable.ADVERSE_DRUG_REACTION_NOTE )
		{
			CommonUtil.setPsLongParam(ps, 12, ntTypKey);
			CommonUtil.setPsLongParam(ps, 13, noteKey);
		}
		else
		{
			CommonUtil.setPsLongParam(ps, 12, noteKey);
		}
		ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info(noteTypeTable.getTableName() + "  table was updated with " +
				    " noteTypeTable.getTableName()" + "Key  = " + 
				      noteTypeTable.getTableName() + "Key");}
	}
	
	private  Map<String, Object> findNote(Connection connection, Note note , Long parentKey) throws SQLException {

		String noteTableKeyColumnName = noteTypeTable.getNoteTableName() + "Key";
		String tableKeyColumnName = noteTypeTable.getTableName() + "Key";
		String findNoteQuery = " SELECT " + noteTypeTable.getNoteTableName() + "." + noteTableKeyColumnName 
				+ " , "+ noteTypeTable.getNoteTableName() + "." + "updttimestamp FROM " + noteTypeTable.getTableName() + " , "
				+ noteTypeTable.getNoteTableName() + " WHERE  " + noteTypeTable.getTableName() + "."
				+ tableKeyColumnName + " = " + noteTypeTable.getNoteTableName() + "." + tableKeyColumnName + "	   AND "
				+ noteTypeTable.getNoteTableName() + ".CnsmrId = ? " + " AND " + noteTypeTable.getNoteTableName() + "."
				+ tableKeyColumnName + " = ?";

		if(logger.isDebugEnabled()) {logger.debug("findNoteQuery : " + findNoteQuery);}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		ps = connection.prepareStatement(findNoteQuery);
		CommonUtil.setPsStringParam(ps, 1, note.getConsumerId());
		CommonUtil.setPsLongParam(ps, 2, parentKey);
		rs = ps.executeQuery();

		if (rs.next()) {
			dataMap.put("NTKEY", ResultSetWrapper.getLong(rs, noteTableKeyColumnName.toUpperCase()));
			dataMap.put("UPDTTIMESTAMP", rs.getTimestamp( "UPDTTIMESTAMP"));
			
		}
		super.close();
		return dataMap;
	}
}
