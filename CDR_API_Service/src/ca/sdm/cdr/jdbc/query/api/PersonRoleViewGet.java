package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSNRLTYP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.ReauthFaxFlag;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.ResponsiblePerson;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
TE99
*/


public class PersonRoleViewGet  extends CDRGet {
	private static Logger logger = LogManager.getLogger(PersonRoleViewGet.class);
//	private static final String SYNONYM_PERSON = "PERSON_ROLE_VW";
	private static final String SYNONYM_PERSON = "PERSON";	
//	private static final String SYNONYM_PERSON_CONTACT_METHOD = "PERSON_CONTACT_METHOD_VW";
	private static final String SYNONYM_PERSON_CONTACT_METHOD = "PERSON_CONTACT_METHOD";
	
	
//TE99 Started	
	//,CONTACT.ACTVFLG ACTVFLG temporary fix for Drop 50 MAILFLG                  CHAR(1)       
	//REAUTHEMAILFLAG          CHAR(1)       
	//REAUTHPHONEFLAG          CHAR(1)       
	//REAUTHFAXFLAG            CHAR(1)       
	//REAUTHVISITFLAG          CHAR(1) 
	private final static String queryRoleSQL = "SELECT "
			+ "ROLE.PRSNRL_ISACTFLG PRSNRL_ISACTFLG, ROLE.PRSNRL_USRNM PRSNRL_USRNM, ROLE.PRSNRL_PRSNRLTYPKEY PRSNRL_PRSNRLTYPKEY, ROLE.PRSNRL_PRSCBRTYPKEY PRSNRL_PRSCBRTYPKEY, ROLE.PRSNRL_PRSCBRTYPKEY PRSNRL_PRSCBRTYPKEY, ROLE.PRSNRL_PROVKEY PRSNRL_PROVKEY, "
			+ "ROLE.PRSNRL_PRSNKEY PRSNRL_PRSNKEY, "
			+ "ROLE.PRSN_CNSMRID PRSN_CNSMRID, ROLE.PRSN_FRSTNM PRSN_FRSTNM, ROLE.PRSN_LSTNM PRSN_LSTNM, ROLE.PRSN_PRDCRID PRSN_PRDCRID,"
			+ "ROLE.PRSN_MDLNM, "
			+ "CONTACT.EMAIL_ROWID EMAIL_ROWID,  CONTACT.EMAIL_EMAILKEY EMAIL_EMAILKEY,  CONTACT.EMAIL_EMAILADDR EMAIL_EMAILADDR,  "
			+ "CONTACT.PHONE_ROWID PHONE_ROWID,  CONTACT.PHONE_TELECOMKEY PHONE_TELECOMKEY,  CONTACT.PHONE_TELFAXIND PHONE_TELFAXIND,"
			+ "CONTACT.PHONE_CNTRYCD PHONE_CNTRYCD,  CONTACT.PHONE_TELECOMNUM PHONE_TELECOMNUM,  "
			+ "CONTACT.ALTERN_ROWID ALTERN_ROWID,CONTACT.ALTERN_TELECOMKEY ALTERN_TELECOMKEY,"
			+ "CONTACT.ALTERN_TELECOMNUM ALTERN_TELECOMNUM,"
			+ "CONTACT.FAX_ROWID FAX_ROWID,  CONTACT.FAX_TELECOMKEY FAX_TELECOMKEY,"
			+ "CONTACT.FAX_TELFAXIND FAX_TELFAXIND,  CONTACT.FAX_CNTRYCD FAX_CNTRYCD, "
			+ "CONTACT.FAX_TELECOMNUM FAX_TELECOMNUM,  "
			+ "CONTACT.ADDR_ROWID ADDR_ROWID,  CONTACT.ADDR_ADDRKEY ADDR_ADDRKEY,  CONTACT.ADDR_ADDRLNONE ADDR_ADDRLNONE,  "
			+ "CONTACT.CM_CNSMRID, CONTACT.ADDR_ADDRLNTWO ADDR_ADDRLNTWO,  CONTACT.ADDR_CITYNM ADDR_CITYNM,  "
			+ "CONTACT.ADDR_PROVCD ADDR_PROVCD,  CONTACT.ADDR_CNTRYCD ADDR_CNTRYCD,  CONTACT.ADDR_POSTALCD ADDR_POSTALCD, "
			+"CONTACT.ADDR_TYPE ADDR_TYPE, CONTACT.ADDR_TYPE_CODE ADDR_TYPE_CODE,"
			+ "CONTACT.ACTVFLG ACTVFLG, CONTACT.MAILFLG MAILFLG ,CONTACT.PRIMARYFLG PRIMARYFLG, CONTACT.REAUTHEMAILFLAG REAUTHEMAILFLAG , CONTACT.REAUTHPHONEFLAG REAUTHPHONEFLAG , CONTACT.REAUTHFAXFLAG REAUTHFAXFLAG , CONTACT.REAUTHVISITFLAG REAUTHVISITFLAG   " //TE97_023 
			+ " FROM "
			+ SYNONYM_PERSON
			+ " ROLE "
			+ "LEFT OUTER JOIN "
			+ SYNONYM_PERSON_CONTACT_METHOD
			+ " CONTACT ON ROLE.PRSNRL_PRSNKEY = CONTACT.PRSN_PRSNKEY "			
			+ "WHERE ROLE.PRSNRL_PRSNRLKEY = ?";
//TE99 Ended	
	
	//DROP52 QUERY optimization fix
	
	private final static String PersonKeySQL =  "select r.PRSNKEY,r.PRSNRLKEY PRSCRBRKEY from prsnrl r where r.PRSNRLKEY=?";
	
	
	private final static String ODMPersonSQL="select r.ISACTFLG     PRSNRL_ISACTFLG, p.CNSMRID      PRSN_CNSMRID,"
			+ "p.FRSTNM       PRSN_FRSTNM,  p.LSTNM        PRSN_LSTNM ,p.MDLNM PRSN_MDLNM from prsnrl r,"
			+ " prsn   p   where r.PRSNRLKEY = ?   and p.PRSNKEY = r.PRSNKEY";
	
	
	private static String queryRoleSQLMAIN=  "";
	private static String queryRoleSQLODM =  "";
	
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
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PersonRoleViewGet.populate. personRoleKey : " + personRoleKey );}
		PreparedStatement preparedStatement = connection.prepareStatement(PersonKeySQL);
		preparedStatement.setLong(1, personRoleKey);
		ResultSet resultSet = preparedStatement.executeQuery();
		Long personKey=null;
		if(resultSet.next()){
		personKey = CommonUtil.getLong("PRSNKEY",resultSet);
		}
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		//DROP52 Query Optimization start
		queryRoleSQLMAIN= TableCacheSingleton.getResource("PersonQueryRole.sql");
		PreparedStatement preparedStatement1 = connection.prepareStatement(queryRoleSQLMAIN); //DROP52 Query Optimization start
		preparedStatement1.setLong(1, personRoleKey);
		preparedStatement1.setLong(2, personKey);
		ResultSet resultSet1 = preparedStatement1.executeQuery();
		Address address =new Address();
		while (resultSet1.next()) {
			person = new Person();
			isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(resultSet1.getString("PRSNRL_ISACTFLG"));
			userName = resultSet1.getString("PRSNRL_USRNM"); 
			personRoleTypeKey = CommonUtil.getLong("PRSNRL_PRSNRLTYPKEY",resultSet1);
			// PrescriberTypeKey = getLong("PRSNRL_PRSCBRTYPKEY");
			ProvinceKey = CommonUtil.getLong("PRSNRL_PROVKEY",resultSet1);
			// personKey = getLong("PRSNRL_PRSNKEY");
			person.setFirstName(resultSet1.getString("PRSN_FRSTNM"));
			person.setMiddleName(resultSet1.getString("PRSN_MDLNM")); 
			person.setLastName(resultSet1.getString("PRSN_LSTNM"));
			consumerId = resultSet1.getString("PRSN_CNSMRID");
			producerId = resultSet1.getString("PRSN_PRDCRID");
			if (resultSet1.getString("CM_CNSMRID") != null && resultSet1.getString("CM_CNSMRID").length() > 1) { 
				address	= CommonUtil.populateAddressPrescriber(address,resultSet1);
	//			person.setAddress(address);
				person.getAddress().add(address);
			}	
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: PersonRoleViewGet.populate. personRoleKey : " + personRoleKey   + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	    }	
		CommonUtil.closePreparedStatementResultSet(preparedStatement1, resultSet1);
	}
	
	
	
	private void populateODMPeson(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PersonRoleViewGet.populateODMPeson. personRoleKey : " + personRoleKey );}
		PreparedStatement preparedStatement = connection.prepareStatement(ODMPersonSQL); //DROP52 Query Optimization start
		preparedStatement.setLong(1, personRoleKey);
		ResultSet resultSet = preparedStatement.executeQuery();
		person = new Person();
		while (resultSet.next()) {
			
			isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PRSNRL_ISACTFLG"));
		    person.setFirstName(resultSet.getString("PRSN_FRSTNM"));
			person.setMiddleName(resultSet.getString("PRSN_MDLNM")); 
			person.setLastName(resultSet.getString("PRSN_LSTNM"));
			consumerId = resultSet.getString("PRSN_CNSMRID");
			;
			
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: PersonRoleViewGet.populateODMPeson. personRoleKey : " + personRoleKey   + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
	    }	
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
	}
	
	
	
	private void populateODM(Connection connection, Long prescriberAddressKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();

		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PersonRoleViewGet.populate. prescriberAddressKey : " + prescriberAddressKey );}
		queryRoleSQLODM= TableCacheSingleton.getResource("PersonQueryRoleODM.sql");
		PreparedStatement preparedStatement = connection.prepareStatement(queryRoleSQLODM);
		preparedStatement.setLong(1, prescriberAddressKey);
		ResultSet resultSet = preparedStatement.executeQuery();
		Address address =new Address();
		while (resultSet.next()) {
			//person = new Person();
			address	= populateAddressPrescriberODM(address,resultSet);			
			person.getAddress().add(address);
			}	
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: PersonRoleViewGet.populate. prescriberAddressKey : " + prescriberAddressKey   + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
			CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
	    }	
	
	
	
	
	private Address populateAddressPrescriberODM(Address address,ResultSet rs) throws SQLException{
		
		boolean hasValue = false;
		String value = null;
		
		
		
	  
		//TE97_024   Mail_Flag         (Default Y)
        value = rs.getString("isMailAddressFlag");
        if (value != null) {
	        address.setIsMailAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
     /* //TE97_024   Mail_Flag         (Default null)
        value = getString("PRIMARYFLG");
        if (value != null) {
	        try {
				address.setIsPrimaryAddressFlag(CommonUtil.convertYesNoFlagToBoolean(value));
			} catch (CDRInternalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        hasValue = true;
        }*/
		//TE97_024  Reauth_Email_Flag (Default N)
        value = rs.getString("isReauthEmailFlag");
        if (value != null) {
	        address.setIsReauthEmailFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Phone_Flag (Default Y)
        value = rs.getString("isReauthPhoneFlag");
        if (value != null) {
	        address.setIsReauthPhoneFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Fax_Flag   (Default U)
      //TE97_024 Reauth_Fax_Flag   (Default U)
        value = rs.getString("isReauthFaxFlag");
        if (value != null && !value.equals("U")) {
	        address.setIsReauthFaxFlag(ReauthFaxFlag.fromValue(CommonUtil.convertTrueFlaseReauthFlagString(value))); 
	        hasValue = true;
        }/*else{
        	 address.setIsReauthFaxFlag(ReauthFaxFlag.NOT_SPECIFIED); 
 	        hasValue = true;
        }*/
        
    	//TE97_024 Reauth_Visit_Flag (Default N)
        value = rs.getString("isReauthVisitFlag");
        if (value != null) {
	        address.setIsReauthVisitFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        if (hasValue)
			return address;
		else
			return null;
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
			    prescriber.setConsumerId(consumerId);
				if( prescriberAddressKey != null){
				populateODM(connection, prescriberAddressKey);
				}
				
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
