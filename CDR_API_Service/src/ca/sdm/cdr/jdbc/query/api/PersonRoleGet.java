package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNTCTMTHDTYP;
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
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.TelecomType;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.Province;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.ResponsiblePerson;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
                                 
*/


public class PersonRoleGet extends CDRGet {

	private static Logger logger = LogManager.getLogger(PersonRoleGet.class);
	private final static String queryRoleSQL = "SELECT "
			+ "PRSNRL.ISACTFLG PRSNRL_ISACTFLG, PRSNRL.USRNM PRSNRL_USRNM, PRSNRL.PRSNRLTYPKEY PRSNRL_PRSNRLTYPKEY, PRSNRL.PRSCBRTYPKEY PRSNRL_PRSCBRTYPKEY, PRSNRL.PROVKEY PRSNRL_PROVKEY, "
			+ "PRSNRL.PRSNKEY PRSNRL_PRSNKEY, "
			+ "PRSN.CNSMRID PRSN_CNSMRID, PRSN.FRSTNM PRSN_FRSTNM, PRSN.LSTNM PRSN_LSTNM, PRSN.PRDCRID PRSN_PRDCRID, "
			+ "CNTCTMTHD.CNTCTMTHDTYPKEY CNTCTMTHD_CNTCTMTHDTYPKEY, CNTCTMTHD.CNTCTMTHDKEY CNTCTMTHD_CNTCTMTHDKEY, CNTCTMTHD.CNSMRID CNTCTMTHD_CNSMRID "
			+ "TELECOM.TELFAXIND TELECOM_TELFAXIND, TELECOM.CNTRYCD TELECOM_CNTRYCD, TELECOM.TELECOMNUM TELECOM_TELECOMNUM, "
			+ "EMAIL.EMAILADDR EMAIL_EMAILADDR, "
			+ "ADDR.ADDRLNONE ADDR_ADDRLNONE, ADDR.ADDRLNTWO ADDR_ADDRLNTWO, ADDR.CITYNM ADDR_CITYNM, ADDR.PROVCD ADDR_PROVCD, ADDR.CNTRYCD ADDR_CNTRYCD, ADDR.POSTALCD ADDR_POSTALCD "
			+ "FROM PRSNRL, PRSN "
			+ "LEFT OUTER JOIN CNTCTMTHD CNTCTMTHD ON PRSN.PRSNKEY = CNTCTMTHD.PRSNKEY "
			+ "LEFT OUTER JOIN TELECOM TELECOM ON CNTCTMTHD.CNTCTMTHDKEY = TELECOM.TELECOMKEY "
			+ "LEFT OUTER JOIN ADDR ADDR ON CNTCTMTHD.CNTCTMTHDKEY = ADDR.ADDRKEY "
			+ "LEFT OUTER JOIN EMAIL EMAIL ON CNTCTMTHD.CNTCTMTHDKEY = EMAIL.EMAILKEY "
			+ "WHERE PRSN.PRSNKEY = PRSNRL.PRSNKEY AND PRSNRL.PRSNRLKEY = ?";

	public boolean isActiveFlag;
	public String userName;
	private Long personRoleTypeKey;
	public Long PrescriberTypeKey;
	public Long ProvinceKey;
	private Person person = null;
	private String consumerId;
	private String producerId;
	CDREnumerations.PersonRoleType role;

	private void populate(Connection connection, Long personRoleKey) throws SQLException, CDRInternalException, NamingException, IOException {
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: PersonRoleGet.populate. personRoleKey : " + personRoleKey );}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		preparedStatement=	connection.prepareStatement(queryRoleSQL);
		preparedStatement.setLong(1, personRoleKey);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			if (person == null) {
				person = new Person();
				isActiveFlag = CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("PRSNRL_ISACTFLG"));
				userName = resultSet.getString("PRSNRL_USRNM");
				personRoleTypeKey = CommonUtil.getLong("PRSNRL_PRSNRLTYPKEY",resultSet);
				// PrescriberTypeKey = getLong("PRSNRL_PRSCBRTYPKEY");
				ProvinceKey = CommonUtil.getLong("PRSNRL_PROVKEY",resultSet);
				// personKey = getLong("PRSNRL_PRSNKEY");
				person.setFirstName(resultSet.getString("PRSN_FRSTNM"));
				person.setLastName(resultSet.getString("PRSN_LSTNM"));
				consumerId = resultSet.getString("PRSN_CNSMRID");
				producerId = resultSet.getString("PRSN_PRDCRID");
			};

			Long contactMethodKey = CommonUtil.getLong("CNTCTMTHD_CNTCTMTHDKEY",resultSet);
			if (contactMethodKey != null) {
				if (person.getAddress() == null) {
					Address address = new Address();
//					person.setAddress(address);
					person.getAddress().add(address);        //VL99 not yet
				}
				
				populateAddress(person.getAddress().get(0),resultSet); //VL99 not yet
			}
		}
		}finally{
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: PersonRoleGet.populate. personRoleKey : " + personRoleKey  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		}
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
	public void populateAddress(Address address,ResultSet rs) throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException
	{
		Long contactMethodTypeKey = CommonUtil.getLong("CNTCTMTHD_CNTCTMTHDTYPKEY",rs);
		String contactMethodTypeCode = getCodeFromKey(LT_CNTCTMTHDTYP, contactMethodTypeKey);
		if ("Email Address".equals(contactMethodTypeCode)) {
			address.setEmail(rs.getString("EMAIL_EMAILADDR"));
		} else if ("Telecom".equals(contactMethodTypeCode)) {
			String isFax = rs.getString("TELECOM_TELFAXIND");
			if (TelecomType.FAX.value().equals(isFax))
				address.setFaxNumber(rs.getString("TELECOM_TELECOMNUM"));
			else if (TelecomType.TELEPHONE.value().equals(isFax))
				address.setPrimaryPhoneNumber(rs.getString("TELECOM_TELECOMNUM"));
		} else if ("Postal Address".equals(contactMethodTypeCode)) {
			address.setAddressLineOne(rs.getString("ADDR_ADDRLNONE"));
			address.setAddressLineTwo(rs.getString("ADDR_ADDRLNTWO"));
			address.setCityName(rs.getString("ADDR_CITYNM"));
			address.setCountryCode(rs.getString("ADDR_CNTRYCD"));
			address.setPostalCode(rs.getString("ADDR_POSTALCD"));
			String provinceCode = rs.getString("ADDR_PROVCD");
			Province province = Province.fromValue(provinceCode);
			address.setProvinceCode(province);
		}
		String consumerId = rs.getString("CNTCTMTHD_CNSMRID");
		if ( consumerId !=null)
			address.setConsumerId(consumerId);
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
	
	public Prescriber fetchPrescriber(Connection connection, Long prescriberKey) throws SQLException, CDRInternalException, NamingException, IOException {
		try {
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
			
			Prescriber prescriber = new Prescriber();
			prescriber.setConsumerId(consumerId);
			prescriber.setProducerId(producerId);
			prescriber.setIsActiveFlag(isActiveFlag);
			prescriber.setPerson(person);
			
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
