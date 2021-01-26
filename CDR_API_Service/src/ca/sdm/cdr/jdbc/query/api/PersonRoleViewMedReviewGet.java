package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSNRLTYP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Address;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Dispenser;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Initiator;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedicalPractitioner;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Person;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Prescriber;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ReauthFaxFlag;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Recorder;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Reporter;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ResponsiblePerson;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Supervisor;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
TE99
*/


public class PersonRoleViewMedReviewGet  extends CDRMedReviewGet {
	private static Logger logger = LogManager.getLogger(PersonRoleViewMedReviewGet.class);
//	private static final String SYNONYM_PERSON = "PERSON_ROLE_VW";
	private static final String SYNONYM_PERSON = "PERSON";	
//	private static final String SYNONYM_PERSON_CONTACT_METHOD = "PERSON_CONTACT_METHOD_VW";
	private static final String SYNONYM_PERSON_CONTACT_METHOD = "PERSON_CONTACT_METHOD";
	
	

	
	//DROP52 QUERY optimization fix
	
	private final static String PersonKeySQL =  "select r.PRSNKEY,r.PRSNRLKEY PRSCRBRKEY from prsnrl r where r.PRSNRLKEY=?";
	
	
	private final static String ODMPersonSQL="select r.ISACTFLG     PRSNRL_ISACTFLG,  "
			+ "p.FRSTNM       PRSN_FRSTNM,  p.LSTNM        PRSN_LSTNM  from prsnrl r,"
			+ " prsn   p   where r.PRSNRLKEY = ?   and p.PRSNKEY = r.PRSNKEY";
	
	
	private static String queryRoleSQLMAIN=  "";

	
	//////
	
	
	public boolean isActiveFlag;
	public String userName;
	private Long personRoleTypeKey;
	public Long PrescriberTypeKey;
	public Long ProvinceKey;
	private Person person = null;
	private String consumerId;
	private String producerId;

	
	private void populate(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PersonRoleViewMedReviewGet.populate. personRoleKey : " + personRoleKey );}
		preparedStatement = connection.prepareStatement(PersonKeySQL);
		preparedStatement.setLong(1, personRoleKey);
		resultSet = preparedStatement.executeQuery();
		Long personKey=null;
		if(resultSet.next()){
		personKey = getLong("PRSNKEY");
		}
		
		//DROP52 Query Optimization start
		queryRoleSQLMAIN= TableCacheSingleton.getResource("PersonQueryRole.sql");
		preparedStatement = connection.prepareStatement(queryRoleSQLMAIN); //DROP52 Query Optimization start
		preparedStatement.setLong(1, personRoleKey);
		preparedStatement.setLong(2, personKey);
		resultSet = preparedStatement.executeQuery();
		Address address =new Address();
		while (resultSet.next()) {
			person = new Person();
			isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(getString("PRSNRL_ISACTFLG"));
			userName = getString("PRSNRL_USRNM"); 
			personRoleTypeKey = getLong("PRSNRL_PRSNRLTYPKEY");
			// PrescriberTypeKey = getLong("PRSNRL_PRSCBRTYPKEY");
			ProvinceKey = getLong("PRSNRL_PROVKEY");
			// personKey = getLong("PRSNRL_PRSNKEY");
			person.setFirstName(getString("PRSN_FRSTNM"));
			person.setMiddleName(getString("PRSN_MDLNM")); 
			person.setLastName(getString("PRSN_LSTNM"));
			consumerId = getString("PRSN_CNSMRID");
			producerId = getString("PRSN_PRDCRID");
			if (getString("CM_CNSMRID") != null && getString("CM_CNSMRID").length() > 1) { 
				address	= populateAddressPrescriber(address);
	//			person.setAddress(address);
				person.getAddress().add(address);
			}	
			if(logger.isInfoEnabled()) {logger.info("EndApiCall: PersonRoleViewMedReviewGet.populate. personRoleKey : " + personRoleKey   + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	    }	
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
	}
	
	
	
	private void populateODMPeson(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: PersonRoleViewMedReviewGet.populateODMPeson. personRoleKey : " + personRoleKey );}
	    preparedStatement = connection.prepareStatement(ODMPersonSQL); //DROP52 Query Optimization start
		preparedStatement.setLong(1, personRoleKey);
		resultSet = preparedStatement.executeQuery();
		person = new Person();
		while (resultSet.next()) {
			
			isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(getString("PRSNRL_ISACTFLG"));
		    person.setFirstName(getString("PRSN_FRSTNM")); //A
		 	person.setLastName(getString("PRSN_LSTNM"));//A
		 	;
			
		 	if(logger.isInfoEnabled()) {logger.info("EndApiCall: PersonRoleViewMedReviewGet.populateODMPeson. personRoleKey : " + personRoleKey   + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	    }	
		super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded
	}
	
	
	
	
	
	
	
	
	
	public Initiator fetchInitiator(Connection connection, Long personalRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, personalRoleKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Initiator".equals(personRoleTypeCode))
			{
				return null;
			};
			
			Initiator initiator = new Initiator();
			initiator.setConsumerId(consumerId);
			initiator.setProducerId(producerId);
			initiator.setPerson(person);
			initiator.setSubstitutionInitiator(null);
			initiator.setProfessionalRegistration(null);
			
			return initiator;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		}
		finally
		{
			super.close();
		}
	}

	public Dispenser fetchDispenser(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, personRoleKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Dispenser".equals(personRoleTypeCode))
			{
				return null;
			};
			
			Dispenser dispenser = new Dispenser();
			dispenser.setConsumerId(consumerId);
			dispenser.setProducerId(producerId);
			dispenser.setIsActiveFlag(isActiveFlag);
			dispenser.setPerson(person);
			return dispenser;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}

	public Supervisor fetchSupervisor(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, personRoleKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Supervisor".equals(personRoleTypeCode))
			{
				return null;
			};
			
			Supervisor supervisor = new Supervisor();
			supervisor.setConsumerId(consumerId);
			supervisor.setProducerId(producerId);
			supervisor.setIsActiveFlag(isActiveFlag);
			supervisor.setPerson(person);
			return supervisor;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	
	
	public Recorder fetchRecorder(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, personRoleKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Recorder".equals(personRoleTypeCode))
			{
				return null;
			};
			
			Recorder recorder = new Recorder();
			recorder.setConsumerId(consumerId);
			recorder.setProducerId(producerId);
			recorder.setIsActiveFlag(isActiveFlag);
			recorder.setPerson(person);
			return recorder;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	
	public MedicalPractitioner fetchMedicalPractitioner(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, personRoleKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"MedicalPractitioner".equals(personRoleTypeCode))
			{
				return null;
			};
			
			MedicalPractitioner serviceProvider = new MedicalPractitioner();
			serviceProvider.setConsumerId(consumerId);
			serviceProvider.setProducerId(producerId);
			serviceProvider.setIsActive(isActiveFlag);
			serviceProvider.setPerson(person);
			
			return serviceProvider;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	public Prescriber fetchPrescriber(Connection connection, Long prescriberKey,Long rxkey,Long prescriberAddressKey,boolean sendToODM) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			  Prescriber prescriber = null;;
			if (sendToODM ) {
				prescriber=new Prescriber();
				populateODMPeson(connection, prescriberKey);
				prescriber.setPerson(person);
			  /*  prescriber.setConsumerId(consumerId);
				if( prescriberAddressKey != null){
				populateODM(connection, prescriberAddressKey);
				}*/
				
			}else{
			    populate(connection, prescriberKey);
			
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Prescriber".equals(personRoleTypeCode))
			{
				return null;
			};
			
			prescriber=new Prescriber();
			prescriber.setConsumerId(consumerId);
			prescriber.setProducerId(producerId);
			prescriber.setIsActiveFlag(isActiveFlag);
			prescriber.setPerson(person);
			}
			return prescriber;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	public ResponsiblePerson fetchResponsiblePerson(Connection connection, Long responsiblePersonKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, responsiblePersonKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Responsible".equals(personRoleTypeCode))
			{
				return null;
			};
			
			ResponsiblePerson responsiblePerson = new ResponsiblePerson();
			responsiblePerson.setConsumerId(consumerId);
			responsiblePerson.setProducerId(producerId);
			responsiblePerson.setPerson(person);
			
			return responsiblePerson;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	
	public Reporter fetchReporter(Connection connection, Long reporterKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
			populate(connection, reporterKey);
			if(personRoleTypeKey == null)
			{
				return null;
			}

			String personRoleTypeCode = getCodeFromKey(LT_PRSNRLTYP, new Long(personRoleTypeKey));
			if(!"Reporter".equals(personRoleTypeCode))
			{
				return null;
			};
			
			Reporter reporter = new Reporter();
			reporter.setConsumerId(consumerId);
			reporter.setProducerId(producerId);
			reporter.setPerson(person);
//			reporter.setReporterRoleCode(null);
			
			return reporter;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}

}
