package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PROV;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSNRLTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.constant.LookupTableConstants;
import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.	api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriberType;
import ca.shoppersdrugmart.rxhb.ehealth.Store;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
TE99                                         QHR Accuro Project
*/


public class PersonRoleApi   { 
	private static Logger logger = LogManager.getLogger(PersonRoleApi.class);
	private final static String INSERTPERSONROLEQUERY1 = 
			"INSERT INTO PRSNRL (PRSNRLKEY, PRSNRLTYPKEY , PROVKEY , ISACTFLG , PRSNKEY , PRSCBRTYPKEY ) "
					+ "	VALUES  (	?	,	?	,	?	,	?	,	? ,  ? )" ;
	
	private final static String INSERTPERSONROLEQUERY2 = 
			"INSERT INTO PRSNRL (PRSNRLKEY, PRSNRLTYPKEY , PROVKEY , ISACTFLG , PRSNKEY ) "
					+ "	VALUES 	(	?	,	?	,	?	,	?	,	? )" ;
	
	private final static String UPDATEPERSONROLEQUERY1 = "UPDATE PRSNRL SET ISACTFLG = ? , PROVKEY =? , PRSCBRTYPKEY =?    WHERE PRSNRLKEY = ? " ;
	private final static String UPDATEPERSONROLEQUERY2 = "UPDATE PRSNRL SET ISACTFLG = ?, PROVKEY =? WHERE PRSNRLKEY = ? ";
	private final static String UPDATEPERSONROLECUSTOMERQUERY = "UPDATE PRSNRL SET PRSNRLTYPKEY = ?, PROVKEY =? WHERE PRSNRLKEY = ? ";
	
	public Long insertPersonRole(Connection connection, Long prsnKey , Store store, PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		PreparedStatement ps = null;
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: insertPersonRole prsnKey : " + prsnKey);}

			Person person = personRole.getPerson();
			long prsnRlKey = IdGenerator.generate(connection, "PrsnRl");

			String insertPersonRoleQuery = "";

			if (PersonRoleType.PRESCRIBER == personRole.getRole()) {
				insertPersonRoleQuery = INSERTPERSONROLEQUERY1;
			} else {
				insertPersonRoleQuery = INSERTPERSONROLEQUERY2;
			}

			ps = connection.prepareStatement(insertPersonRoleQuery);

			String personRoleTypeCode = (personRole.getRole() != null) ? personRole.getRole().getRoleType() : null;
			Long PrsnRlTypKey = (!StringUtil.isEmpty(personRoleTypeCode))
					? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PRSNRLTYP, personRoleTypeCode) : null;
					
					
//TE99 Started:					
			String provinceCode = (person.getAddress() != null && person.getAddress().size()>0 && person.getAddress().get(0).getProvinceCode() != null) 
					? person.getAddress().get(0).getProvinceCode().value() : null;
//TE99 Ended					
					
					
			Long provKey = (!StringUtil.isEmpty(provinceCode)) ? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PROV, provinceCode) : null;

			CommonUtil.setPsLongParam(ps,1, prsnRlKey);
			CommonUtil.setPsLongParam(ps,2, PrsnRlTypKey);
			CommonUtil.setPsLongParam(ps,3, provKey);
			CommonUtil.setPsBooleanParam(ps,4, personRole.isActiveFlag());
			CommonUtil.setPsLongParam(ps,5, prsnKey);

			if (PersonRoleType.PRESCRIBER == personRole.getRole()) {
				Long prescriberTypeKey = null;
				PrescriberType prescriberType = ((Prescriber) personRole.getPersonRole()).getPrescriberTypeCode();

				if (prescriberType != null) {
					String typeCode = prescriberType.value();
					prescriberTypeKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LookupTableConstants.LT_PRSCBRTYP, typeCode);
				}
				CommonUtil.setPsLongParam(ps,6, prescriberTypeKey);

			}

			// insertPreparedStatement.setString(4,
			// CommonUtil.convertBooleanToYesNoFlag( personRole.isActiveFlag() )
			// );

			ps.executeUpdate();
			return prsnRlKey;
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
		} finally
		{
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, null);
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: insertPersonRole prsnKey : " + prsnKey);}
		}
	}

	public void updatePersonRole(Connection connection, Long prsnKey , Long personRoleKey , Store store, PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException 
	{
		PreparedStatement ps = null;
		
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: updatePersonRole prsnKey : " + prsnKey + ", personRoleKey :" + personRoleKey);}

			String updatePersonRoleQuery = "";
			if (PersonRoleType.PRESCRIBER == personRole.getRole()) {
				updatePersonRoleQuery = UPDATEPERSONROLEQUERY1;
			} else {
				updatePersonRoleQuery = UPDATEPERSONROLEQUERY2;
			}

			Person person = personRole.getPerson();

			ps = connection.prepareStatement(updatePersonRoleQuery);

			ps.setString(1, CommonUtil.convertBooleanToYesNoFlag(personRole.isActiveFlag()));

			
//VL99 Started			
			Long provKey = null;
			if (person.getAddress().size() > 0) {
				String provinceCode = (person.getAddress() != null && person.getAddress().get(0).getProvinceCode() != null)   //VL99 not yet
						? person.getAddress().get(0).getProvinceCode().value() : null;
				provKey = (!StringUtil.isEmpty(provinceCode)) ? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PROV, provinceCode) : null;
			}
			CommonUtil.setPsLongParam(ps,2, provKey);
//VL99 Started
			
			
			if (PersonRoleType.PRESCRIBER == personRole.getRole()) {
				Long prescriberTypeKey = null;
				PrescriberType prescriberType = ((Prescriber) personRole.getPersonRole()).getPrescriberTypeCode();

				if (prescriberType != null) {
					String typeCode = prescriberType.value();
					prescriberTypeKey = TableCacheSingleton.getInstance(connection).getKeyFromCode(LookupTableConstants.LT_PRSCBRTYP, typeCode);
				}
				CommonUtil.setPsLongParam(ps,3, prescriberTypeKey);

				CommonUtil.setPsLongParam(ps,4, personRoleKey);
			} else {
				CommonUtil.setPsLongParam(ps,3, personRoleKey);
			}

			ps.executeUpdate();
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
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, null);
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: updatePersonRole prsnKey : " + prsnKey + ", personRoleKey :" + personRoleKey);}
		}
	}
	
	public void updatePersonRoleCustomer(Connection connection, Long prsnKey , Long personRoleKey , Long prsnRoleTypCode, PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException 
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: updatePersonRoleCustomer prsnKey : " + prsnKey + ", personRoleKey :" + personRoleKey);}

			
			
			Person person = personRole.getPerson();
			PreparedStatement ps = null;
			ResultSet rs= null;
	try {	
			ps = connection.prepareStatement(UPDATEPERSONROLECUSTOMERQUERY);

			ps.setLong(1, prsnRoleTypCode);

			
//VL99 Started			
			Long provKey = null;
			if (person.getAddress().size() > 0) {
				String provinceCode = (person.getAddress() != null && person.getAddress().get(0).getProvinceCode() != null)   //VL99 not yet
						? person.getAddress().get(0).getProvinceCode().value() : null;
				provKey = (!StringUtil.isEmpty(provinceCode)) ? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PROV, provinceCode) : null;
			}
			
			if(provKey != null){
			ps.setLong(2, provKey);
			}
			else{
				ps.setNull(2, Types.NUMERIC);
			}
//VL99 Started
			
			
			ps.setLong(3, personRoleKey);
			

			ps.executeUpdate();
	}finally {
		CommonUtil.closePreparedStatementResultSet(ps, rs);
	}
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
			//super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: updatePersonRoleCustomer prsnKey : " + prsnKey + ", personRoleKey :" + personRoleKey);}
		}
	}
}
