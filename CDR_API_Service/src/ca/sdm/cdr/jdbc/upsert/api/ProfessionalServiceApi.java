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

import ca.sdm.cdr.common.constant.LookupTableConstants;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalService;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class ProfessionalServiceApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(ProfessionalServiceApi.class);
	// populate all children element first
	private final static String INSERTSQL = 
			" INSERT INTO PRFSNLSVC "
			+ " (SVCLOC, "	
			+ " CNSLTTNTIMESTAMP, "		
			+ " CNSLTTNLENGTH, CNSMRID, "	 
			+ " PRDCRID, SVCPRVDRKEY, "		
			+ " SPRVSRKEY , LOCKEY, "		
			+ " TXKEY, PRFSNLSVCTYPKEY, "	
			+ " PRFSNLSVCKEY) "  
			+ " VALUES ( "
			+ "? , "			
			+ CommonUtil.getPsToDateFunctionStr()	 
			+ ", ? , ? "	
			+ ", ? , ? "	
			+ ", ? , ? "	
			+ ", ? , ? "	
			+ ", ? )";		 

	private final static String UPDATESQL = 
			"UPDATE PRFSNLSVC SET "
			+ " SVCLOC = ? , "	
			+ " CNSLTTNTIMESTAMP = " 
			+ CommonUtil.getPsToDateFunctionStr() 
			+ "  , "	
			+ " CNSLTTNLENGTH = ? , CNSMRID = ? , "	 
			+ " PRDCRID = ? , SVCPRVDRKEY = ? ,"	
			+ " SPRVSRKEY = ? , LOCKEY = ? , "		
			+ " TXKEY = ? , PRFSNLSVCTYPKEY = ? "
			+ " WHERE PRFSNLSVCKEY = ? ";				 
	
	public Long upsertProfessionalService(Connection connection, ProfessionalService prfsvc, Store store, Long dispenseKey) throws SQLException, NamingException, IOException, CDRException {
		try {
			// Persist main entity
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertProfessionalService dispenseKey : " + dispenseKey);}
			String professionalServiceConsumerId = prfsvc.getConsumerId();

			if (professionalServiceConsumerId == null || "".equals(professionalServiceConsumerId))
				throw new ConsumerIdMissingException("professionalService");

			Long professionalServiceKey = FindUtil.findProfessionalServiceKey(connection, professionalServiceConsumerId,
					dispenseKey);
			// upsert into PtntCvrg
			if (professionalServiceKey == null) {
				professionalServiceKey = insertProfessionalService(connection, prfsvc, store, dispenseKey);
			} else {
				updateProfessionalService(connection, prfsvc, store, dispenseKey, professionalServiceKey);
			}

			return professionalServiceKey;
		} catch (ConsumerIdMissingException e) {
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
		} catch (CDRException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertProfessionalService dispenseKey : " + dispenseKey);}
		}
	}

	private Long insertProfessionalService(Connection connection, ProfessionalService prfsvc, Store store, Long dispenseKey ) throws SQLException, NamingException, IOException, CDRException  
	{
		long prfsnlSvcKey = IdGenerator.generate(connection, "PrfsnlSvc");
		String svcLoc = null;
		Long svcProviderKey = null;
		Long obsrvtnTypKey = null;
		Long locKey = null;
		Long supervisorKey = null;
		
		if (prfsvc.getServiceLocation() != null) {
			LocationApi locationApi = new LocationApi();
			locKey = locationApi.findLocation(connection, store, prfsvc.getServiceLocation());
		};
		
		Supervisor supervisor = prfsvc.getSupervisor();
		if (supervisor!=null) {
			PersonApi personApi = new PersonApi();		
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}		
		
		MedicalPractitioner svcProvider = prfsvc.getServiceProvider();
		if(svcProvider !=null)
		{
			PersonApi personApi = new PersonApi();		
			PersonRole supervisorPersonRole = new PersonRole (svcProvider);
			svcProviderKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsStringParam(1, svcLoc);
		String cnslttnTimestampStr = CommonUtil.toTimestampStr(prfsvc.getConsultationTimestamp());
		setPsStringParam(2, cnslttnTimestampStr);
		setPsLongParam(3, prfsvc.getConsultationLength());
		setPsLongParam(4, prfsvc.getConsumerId());
		setPsStringParam(5, prfsvc.getProducerId());
		setPsLongParam(6, svcProviderKey);
		setPsLongParam(7, supervisorKey);
		setPsLongParam(8, locKey);
		setPsLongParam(9, dispenseKey);

		String observationTypeCode = prfsvc.getObservationTypeCode() ;
		if ( observationTypeCode != null ) 
		{
			obsrvtnTypKey = getKeyFromCode(LookupTableConstants.LT_PRFSNLSVCTYP,observationTypeCode);
		}
		
		setPsLongParam(10, obsrvtnTypKey);
		setPsLongParam(11, prfsnlSvcKey);
		
		int res = ps.executeUpdate();
		
		List<Note> notes = prfsvc.getNote();
		if(notes!=null && notes.size() >0)
		{
			NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.PROFESSIONALSERVICE_NOTE);
			noteApi.upsertNoteList(connection, store, notes, prfsnlSvcKey);
		}
		
		if (logger.isInfoEnabled())  {logger.info(res + " prfsnlSvc has been inserted into prfsnlSvc table with rxstatKey " + prfsnlSvcKey );}
		
		return prfsnlSvcKey;
	}
	
	private void updateProfessionalService(Connection connection, ProfessionalService prfsvc, Store store, Long dispenseKey, Long prfsnlSvcKey ) throws NamingException, IOException, SQLException, CDRException  
	{
		String svcLoc = null;
		Long svcProviderKey = null;
		Long obsrvtnTypKey = null;
		Long locKey = null;
		Long supervisorKey = null;
		
		if (prfsvc.getServiceLocation() != null) {
			LocationApi locationApi = new LocationApi();
			locKey = locationApi.findLocation(connection, store, prfsvc.getServiceLocation());
		};

		Supervisor supervisor = prfsvc.getSupervisor();
		if (supervisor!=null) {
			PersonApi personApi = new PersonApi();		
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}		

		MedicalPractitioner svcProvider = prfsvc.getServiceProvider();
		if(svcProvider !=null)
		{
			PersonApi personApi = new PersonApi();		
			PersonRole supervisorPersonRole = new PersonRole (svcProvider);
			svcProviderKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}
		
//		if(prfsvc.getObservationTypeCode()!=null)
//		ptntObsrvtnTypKey = prfsvc.getObservationTypeCode().value();
		// populate all children element first
		
		ps = connection.prepareStatement(UPDATESQL);

		setPsStringParam(1, svcLoc);
		String cnslttnTimestampStr = CommonUtil.toTimestampStr(prfsvc.getConsultationTimestamp());
		setPsStringParam(2, cnslttnTimestampStr);
		setPsLongParam(3, prfsvc.getConsultationLength());
		setPsLongParam(4, prfsvc.getConsumerId());
		setPsStringParam(5, prfsvc.getProducerId());
		setPsLongParam(6, svcProviderKey);
		setPsLongParam(7, supervisorKey);
		setPsLongParam(8, locKey);
		setPsLongParam(9, dispenseKey);

		String observationTypeCode = prfsvc.getObservationTypeCode() ;
		if ( observationTypeCode != null ) 
		{
			obsrvtnTypKey = getKeyFromCode(LookupTableConstants.LT_PRFSNLSVCTYP,observationTypeCode);
		}
		setPsLongParam(10, obsrvtnTypKey);
		
		setPsLongParam(11, prfsnlSvcKey);
		int res = ps.executeUpdate();
		
		List<Note> notes = prfsvc.getNote();
		if(notes!=null && notes.size() >0)
		{
			NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.PROFESSIONALSERVICE_NOTE);
			noteApi.upsertNoteList(connection, store, notes, prfsnlSvcKey);
		}
		
		if (logger.isInfoEnabled())  {logger.info(res + " prfsnlSvc has been inserted into prfsnlSvc table with rxstatKey " + prfsnlSvcKey );}
	}
}
