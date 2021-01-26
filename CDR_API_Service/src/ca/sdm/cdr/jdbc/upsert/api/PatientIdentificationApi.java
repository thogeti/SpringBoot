package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class PatientIdentificationApi  extends CDRUpsert {

	private static Logger logger = LogManager.getLogger(PatientIdentificationApi.class);
	private final static String UPDATESQL =
	"UPDATE PTNTID SET " +
	"	ISSNGATHRTYNM=?,		IDTYPKEY=? " +
	" WHERE  PTNTIDKEY = ? ";

	private final static String INSERTSQL = "INSERT INTO PTNTID(IDNUM , ISSNGATHRTYNM , PTNTIDKEY, IDTYPKEY, PTNTKEY ) VALUES ( ? , ? , ? , ? , ? ) " ;

	public Long upsertPatientIdentification(Connection connection, PatientIdentification patientIdentification, Long patientKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException  
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertPatientIdentification, patient key : "  + patientKey );}
			// Persist main entity
			if (patientIdentification == null)
				return null;

			Long patientIdentificationKey;
			patientIdentificationKey = FindUtil.findPatientIdentificationKey(connection, patientKey,
					patientIdentification.getIdentificationNumber());
			if (patientIdentificationKey == null) {
				patientIdentificationKey = insertPatientIdentification(connection, patientIdentification, patientKey);
			} else {
				// INSCVRG table doesn't have an Update Timestamp field.
				updatePatientIdentification(connection, patientIdentification, patientIdentificationKey);
			}

			return patientIdentificationKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertPatientIdentification, patient key : "  + patientKey );}
		}
		
	}

	private void updatePatientIdentification(Connection connection, PatientIdentification patientIdentification, Long patientIdentificationKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException 
	{
		if (logger.isInfoEnabled())  {logger.info("update patientIdentification");}
		ps = connection.prepareStatement(UPDATESQL);
		
		setPsStringParam(1, patientIdentification.getIssuingAuthorityName());
		String typeCode = patientIdentification.getIdentificationTypeCode() != null ? patientIdentification.getIdentificationTypeCode().value() : null;
		Long idTypeKey = getKeyFromCode(LT_IDTYP, typeCode);
		setPsLongParam(2, idTypeKey); 
		setPsLongParam(3, patientIdentificationKey); 
		
		ps.executeUpdate();
	}

	
	private Long insertPatientIdentification(Connection connection, PatientIdentification patientIdentification, Long patientKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException 
	{
		if (logger.isInfoEnabled())  {logger.info("insert patientIdentification");}
		
		Long patientIdentificationKey = IdGenerator.generate(connection, "PtntId");

		ps = connection.prepareStatement(INSERTSQL);
		
		setPsStringParam(1, patientIdentification.getIdentificationNumber());
		setPsStringParam(2, patientIdentification.getIssuingAuthorityName());
		setPsLongParam(3, patientIdentificationKey); 
		String typeCode = patientIdentification.getIdentificationTypeCode() != null ? patientIdentification.getIdentificationTypeCode().value() : null;
		Long idTypeKey = getKeyFromCode(LT_IDTYP, typeCode);
		setPsLongParam(4, idTypeKey); 
		setPsLongParam(5, patientKey); 
		
		ps.executeUpdate();
		
		return patientIdentificationKey;
	}
}
