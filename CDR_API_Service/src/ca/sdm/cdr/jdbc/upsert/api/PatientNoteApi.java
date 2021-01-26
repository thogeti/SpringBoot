package ca.sdm.cdr.jdbc.upsert.api;

public class PatientNoteApi 
/*  commented out for test. must be uncommented 
  
 extends NoteApi 
 * */
{

	/*  commented out for test. must be uncommented 
	 * 
	 *
	private Long patientKey ;
	private Note patientNote;
	
	public PatientNoteApi (Long patientKey , Note note , Connection  connection)
	{
		super(connection);
		this.patientKey = patientKey ;
	}
	
	public Long findNote(Note note)
	{
	
	}
	
	public void upsertPatientNote() throws Exception
	{
		
		Long noteKey = 0L ;
		noteKey = findNote(patientNote.getConsumerId() , patientKey  );
		if( noteKey != 0L )
		{
			// the record exist in the database table
			String updatePersonQuery = "update PtntNt set isPhrmcst=? , upDttmstamp=? , NtEng=? , CnsmrId=? , PrdcrId=? , NtFr=? , CrtTimestamp=? , CrtUsrId=? , " ;
			updatePersonQuery += " RcrdrKey=? , SprvsrKey=? , NtCtgryTypKey=? , NtTypKey=? , LocKey=? , PtntKey =?";

			PreparedStatement updatePreparedStatement = connection.prepareStatement(updatePersonQuery);
			updatePreparedStatement.setString(1, CommonUtil.convertBooleanToYesNoFlag( patientNote.isIsPharmacist() ));
			updatePreparedStatement.setDate( 2, new java.sql.Date(patientNote.getUpdateTimestamp().toGregorianCalendar().getTime().getTime() ));
			updatePreparedStatement.setString(3, patientNote.getNoteEnglish());
			updatePreparedStatement.setString(4, patientNote.getConsumerId());
			updatePreparedStatement.setString(5, patientNote.getProducerId());
			updatePreparedStatement.setString(6, patientNote.getNoteFrench());
			updatePreparedStatement.setDate(7, new java.sql.Date(patientNote.getCreateTimestamp().toGregorianCalendar().getTime().getTime()));
			updatePreparedStatement.setString(8, patientNote.getCreateUserId());
			
			updatePreparedStatement.setString(8, patientNote.);
			updatePreparedStatement.setString(8, patientNote.);
			updatePreparedStatement.setString(8, patientNote.);
			updatePreparedStatement.setString(8, patientNote.);
			
			updatePreparedStatement.executeUpdate( );


			String updatePatientQuery = "update ptnt set dateOfBirth=? where ptntKey = ?";

			updatePreparedStatement = connection.prepareStatement(updatePatientQuery);
			updatePreparedStatement.setString(1, patient.getDateOfBirth().toString());
			updatePreparedStatement.setString(2, patientKey);
			
			updatePreparedStatement.executeUpdate( );

			
		}
		else
		{
			
			String insertPersonQuery = "insert into prsn (FrstNm  , LstNm , CnsmrId , StoreNumber , PrsnRlKey ) values ( ? , ? , ? , ? , ? ) ";

			PreparedStatement insertPreparedStatement = connection.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
			insertPreparedStatement.setString(1, patient.getPerson().getFirstName());
			insertPreparedStatement.setString(2, patient.getPerson().getLastName());
			insertPreparedStatement.setString(3, patient.getConsumerId());
			insertPreparedStatement.setString(4, patient.getStoreNumber());
			insertPreparedStatement.setString(5, "1000");
			
			insertPreparedStatement.executeUpdate( );


			long generatedKey = 0 ;
			ResultSet generatedKeyResultSet = insertPreparedStatement.getGeneratedKeys();
			if (generatedKeyResultSet.next()) {
			    generatedKey = generatedKeyResultSet.getLong(1);
			}
/*			
			String insertedPersonKey = "" ;
			insertedPersonKey  = getPatientKeyFromPersonTable( patient.getConsumerId() ,  patient.getStoreNumber()  );
*			
			if (generatedKey  == 0)
			{
				throw new Exception("inserted Person Key not found");
			}

			String insertPatientQuery = "insert into ptnt ( PtntKey , dateOfBirth) values ( ? , ? ) ";


			insertPreparedStatement = connection.prepareStatement(insertPatientQuery);
			insertPreparedStatement.setLong(1, generatedKey);
			insertPreparedStatement.setString(2, patient.getDateOfBirth().toString());
			
			insertPreparedStatement.executeUpdate( );
		}
		
		
/*		upsertPatientNote(patient.pa)
		patient.getNote()
*		
	}
	
	public Long findNote(String consumerId, Long patientKey) throws SQLException
	{
		String selectPatientQuery = "SELECT PtntNtKey FROM ptntnt WHERE CnsmrId = ? and ptntKey = ? ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(selectPatientQuery);
		preparedStatement.setString(1, consumerId);
		preparedStatement.setLong(2, patientKey);
		
		ResultSet rs = preparedStatement.executeQuery( );
		
		Long patientNoteKey = 0L;
		if( rs != null && rs.getFetchSize()>0)
		{
			rs.first();
			patientNoteKey = rs.getLong("PtntNtKey");
		}
		return patientNoteKey ;
	}
	*/
}
