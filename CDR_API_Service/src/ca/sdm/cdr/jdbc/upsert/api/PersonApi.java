package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.ConsumerIdMissingException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.CDRPersistResponse;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;
import generated.RxHBBusinessEventActionEnum;


/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
TE99 2018-01-24   NTT Data                   QHR Accuro Project
*/


//public class PersonApi extends CDRUpsert {
public class PersonApi {
	private static Logger logger = LogManager.getLogger(PersonApi.class);

//TE99 Started:	
	private final static String INSERTPERSONQUERY = 
			"INSERT INTO PRSN (PRSNKEY , FRSTNM  , LSTNM , CNSMRID, PRDCRID, STORENUM,MDLNM,CDEFFSTRTDT,  CDEFFENDDT,DTOFBIRTH,DTOFDECEASE)" +
			"  VALUES ( ? , ? , ? , ? , ?, ? ,? , "+ CommonUtil.TO_TIMESTAMP_TZ + "," + CommonUtil.TO_TIMESTAMP_TZ +","+ CommonUtil.TO_TIMESTAMP_TZ + ","+ CommonUtil.TO_TIMESTAMP_TZ + ")" ;

	
/*	
	private final static String QUERYSQL = 
			 " SELECT PRSN.PRSNKEY,    "
		   + "        PRSN.CDEFFENDDT, "
		   + "        PRSNRL.PRSNRLKEY " 
		   + "	FROM  PRSN LEFT OUTER JOIN PRSNRL ON PRSN.PRSNKEY = PRSNRL.PRSNKEY " 
		   + "   AND PRSNRLTYPKEY  = ?  " 
		   + "	WHERE PRSN.CNSMRID = ?  "
		   + "    AND PRSN.STORENUM = ?    ";
*/
	
//  2020-07-14 Started:	
	private final static String QUERYSQL =
	         " SELECT p.PRSNKEY,           "
           + "        p.CDEFFENDDT,        "
           + "        r.PRSNRLKEY,         "
           + "        f.PRESCRIBERADDRKEY  "
           + "   FROM PRSN p               "
           + "             LEFT OUTER JOIN PRSNRL r "
           + "                  ON p.PRSNKEY = r.PRSNKEY AND r.PRSNRLTYPKEY = ? "
           + "                                                                  "
           + "             LEFT OUTER JOIN PRESCRIBER_ADDRESS_FLAG f            "
           + "                  ON r.PRSNRLKEY = f.prscbrkey                    "
           + "  WHERE p.CNSMRID  = ? " 
           + "    AND p.STORENUM = ? ";
//  2020-07-14 Ended:	
	
	
	private final static String QUERYSQLWITHOUTSTORENUM = "  SELECT PRSN.PRSNKEY , PRSNRL.PRSNRLKEY " + 
			 "	FROM " +
			 "	     PRSN LEFT OUTER JOIN PRSNRL ON PRSN.PRSNKEY = PRSNRL.PRSNKEY " + 
			 "	          AND PRSNRLTYPKEY = ?  " +
			 "	WHERE PRSN.CNSMRID=? " ;

	private final static String QUERYSQLCUSTOMER = "  SELECT  PRSNRL.PRSNRLKEY FROM PRSNRL WHERE PRSNRL.PRSNKEY = ? "; 

	private final static String UPDATESQL = " UPDATE PRSN SET  FRSTNM = ? , LSTNM = ? , PRDCRID = ? ,MDLNM=? , DTOFBIRTH = "+CommonUtil.TO_TIMESTAMP_TZ+" , DTOFDECEASE = "+CommonUtil.TO_TIMESTAMP_TZ+" ,CDEFFENDDT = NVL("+CommonUtil.TO_TIMESTAMP_TZ+",CDEFFENDDT )  WHERE PRSNKEY = ? " ;

	private final static String UPDATEPRSNSQL ="UPDATE PRSN " + 
			"	SET (FRSTNM, LSTNM, CNSMRID, PRDCRID, STORENUM) = " +
			"		(SELECT ?, ?, ?, ?, ? FROM DUAL) " +
			" WHERE PRSNKEY = ? ";

	protected Long prescriberAddressKey=null;
	
	public CDRPersistResponse  verifyPersonExists(Connection connection, Store store , PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, ConsumerIdMissingException{
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertPerson personRole consumer id : " + personRole.getConsumerId());}
			
			String storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());

			Long prsnKey = null;
			Long personRoleKey = null;

			ps = connection.prepareStatement(QUERYSQL);
			String personRoleTypeCode = (personRole.getRole() != null) ? personRole.getRole().getRoleType() : null;
			Long roleTypeKey = (!StringUtil.isEmpty(personRoleTypeCode)) ? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PRSNRLTYP, personRoleTypeCode) : null;
			CommonUtil.setPsLongParam(ps,1, roleTypeKey);
			CommonUtil.setPsStringParam(ps,2, personRole.getConsumerId());
			CommonUtil.setPsStringParam(ps,3, storeNumberString);
			rs = ps.executeQuery();
			if (rs.next()) {
				prsnKey = ResultSetWrapper.getLong(rs, "prsnKey".toUpperCase());
				Timestamp dbUpdateTimeStamp=rs.getTimestamp("CDEFFENDDT");
				personRole.setDbUpdateTimeStamp(dbUpdateTimeStamp);
				
// 2020-07-14 Started				
				personRoleKey = ResultSetWrapper.getLong(rs, "prsnRlKey".toUpperCase());       // ---> rx.PRSCBRKEY
				personRole.setPersonRoleKey(personRoleKey);                                    // ---> rx.PRSCBRKEY
				if ( personRole.getRole() == CDREnumerations.PersonRoleType.PRESCRIBER  ||
					 personRole.getRole() == CDREnumerations.PersonRoleType.MEDICALPRACTITIONER ) {
					
				     prescriberAddressKey = ResultSetWrapper.getLong(rs, "PRESCRIBERADDRKEY"); // ---> rx.PRESCRIBERADDRKEY
				}
// 2020-07-14 Ended				
			}
			
			CDRPersistResponse response = new CDRPersistResponse();
			if (prsnKey != null) {
				response.setAction(RxHBBusinessEventActionEnum.UPDATE);
			} else {
				response.setAction(RxHBBusinessEventActionEnum.CREATE);
			}
			
			
		//	super.close();     
            return  response;
			
		} catch (SQLException e) {
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
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: verifyPersonExists  consumer id : " + personRole.getConsumerId());}
		}
	}
//TE99 Ended	
	
	
	public Long upsertPerson(Connection connection, Store store , PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, ConsumerIdMissingException {
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertPerson personRole consumer id : " + personRole.getConsumerId());}
			String storeNumberString = null;
			boolean custflag = store.getStorenumber() == null && (personRole.getRole().getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())
					|| personRole.getRole().getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType()));
			if(custflag){
				 storeNumberString = null;
			}
			else {
			 storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
			}

			// Long prsnKey = FindUtil.findPersonKey(connection,
			// personRole.getConsumerId(), storeNumberString);

			Long prsnKey = null;
			Long personRoleKey = null;

			if(custflag) {
				ps = connection.prepareStatement(QUERYSQLWITHOUTSTORENUM);
			}
			else {
			ps = connection.prepareStatement(QUERYSQL);
			}
			String personRoleTypeCode = (personRole.getRole() != null) ? personRole.getRole().getRoleType() : null;
			Long roleTypeKey = (!StringUtil.isEmpty(personRoleTypeCode))
					? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PRSNRLTYP, personRoleTypeCode) : null;
			CommonUtil.setPsLongParam(ps,1, roleTypeKey);
			CommonUtil.setPsStringParam(ps,2, personRole.getConsumerId());
			if(!custflag) {
			CommonUtil.setPsStringParam(ps,3, storeNumberString);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				prsnKey = ResultSetWrapper.getLong(rs, "prsnKey".toUpperCase());
				personRoleKey = ResultSetWrapper.getLong(rs, "prsnRlKey".toUpperCase());
			}

		//	super.close();

			PersonRoleApi personRoleApi = new PersonRoleApi();
			if (prsnKey != null) {
				// Person exist. check if the role exist for the person
				if (personRoleKey == null || personRoleKey <= 0L) {
					// role does not exist and must be inserted.
					/************************************************************************************************************/
					/* insert into PersonRole table */
					/************************************************************************************************************/
					personRoleKey = personRoleApi.insertPersonRole(connection, prsnKey, store, personRole);
				} else {
					personRoleApi.updatePersonRole(connection, prsnKey, personRoleKey, store, personRole);
				}
				updatePerson(connection, prsnKey, personRoleKey, store, personRole);
			} else {
				// Person does not exist. and must be inserted.
				prsnKey = insertPerson(connection, store, personRole,custflag);

				// role does not exist and must be inserted.
				/************************************************************************************************************/
				/* insert into PersonRole table */
				/************************************************************************************************************/
				personRoleKey = personRoleApi.insertPersonRole(connection, prsnKey, store, personRole);
			}

			ContactMethodApi contactMethodApi = new ContactMethodApi();
			prescriberAddressKey=contactMethodApi.upsertContactMethodByPersonKey(connection, storeNumberString, prsnKey,
					personRole,personRoleKey);
			// Note: professional registration is not required at current stage
			// if(personRole.getProfessionalRegistration()!=null &&
			// personRole.getProfessionalRegistration().size() >0 )
			// {
			// List<ProfessionalRegistration> prfslregs =
			// personRole.getProfessionalRegistration();
			// java.util.Iterator<ProfessionalRegistration> iterator =
			// prfslregs.iterator();
			// while (iterator.hasNext()) {
			// ProfessionalRegistration current = iterator.next();
			// if(current !=null)
			// {
			// ProfessionalRegistrationApi professionalRegistrationApi = new
			// ProfessionalRegistrationApi(connection);
			// professionalRegistrationApi.upsertProfessionalRegistration(current,
			// personRoleKey);
			// }
			// }
			// }
			return personRoleKey;
		} catch (SQLException e) {
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
		} catch (ConsumerIdMissingException e) {
			e.printStackTrace();
			throw e;
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertPerson personRole consumer id : " + personRole.getConsumerId());}
		}
	}
	
	

	public void updatePatientPerson(Connection connection, Patient patient, long PersonKey, Store store) throws SQLException {
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: updatePatientPerson PersonKey : " + PersonKey);}
			
			/************************************************************************************************************/
			/* update Person table */
			/************************************************************************************************************/
			Person person = patient.getPerson();
			String storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());

			ps = connection.prepareStatement(UPDATEPRSNSQL);

			CommonUtil.setPsStringParam(ps,1, person.getFirstName());
			CommonUtil.setPsStringParam(ps,2, person.getLastName());
			CommonUtil.setPsStringParam(ps,3, patient.getConsumerId());
			CommonUtil.setPsStringParam(ps,4, patient.getProducerId());
			CommonUtil.setPsStringParam(ps,5, storeNumberString);
			CommonUtil.setPsLongParam(ps,6, PersonKey);

			int res = ps.executeUpdate();
			/*
			 * ContactMethodApi contactMethodApi = new
			 * ContactMethodApi(connection);
			 * contactMethodApi.upsertContactMethodByPatientKey(store,
			 * PersonKey, person);
			 */
			/*
			 * CntctMthdApi CntctMthd = new CntctMthdApi(connection);
			 * CntctMthd.upsertCntctMthd(store, PersonKey, person);
			 */
			if (logger.isInfoEnabled())  {logger.info(res + " PatientPerson has been updated in prsn table with PrsnKey " + PersonKey);}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
		//	super.close();
			CommonUtil.closePreparedStatementResultSet(ps, rs);
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: updatePatientPerson PersonKey : " + PersonKey);}
		}
	}	
	
	private void updatePerson(Connection connection, Long prsnKey , Long prsnrlkey, Store store , PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		Person person = personRole.getPerson();
		PreparedStatement ps = null;
		 
try {	
		ps = connection.prepareStatement(UPDATESQL);
		
		CommonUtil.setPsStringParam(ps,1, person.getFirstName());
		CommonUtil.setPsStringParam(ps,2, person.getLastName());
		CommonUtil.setPsStringParam(ps,3, personRole.getProducerId());
		CommonUtil.setPsStringParam(ps,4, person.getMiddleName());

		CommonUtil.setPsStringParam(ps,5, CommonUtil.toTimestampStr(person.getDateOfBirth()));
		CommonUtil.setPsStringParam(ps, 6, CommonUtil.toTimestampStr(person.getDeceaseddate()));
		CommonUtil.setPsStringParam(ps, 7, CommonUtil.toTimestampStr(person.getLastUpdateTimestamp()));
		CommonUtil.setPsLongParam(ps,8, prsnKey);
		ps.executeUpdate();
}catch (SQLException e) {
    e.printStackTrace();
   throw e;
 } 
finally {
	CommonUtil.closePreparedStatementResultSet(ps, null);
}
	}

	
	private Long insertPerson(Connection connection, Store store , PersonRole personRole,boolean custflag) throws SQLException, KeyNotFoundFromTableCacheException, IOException, NamingException {
		/************************************************************************************************************/
		/*							 insert into Person table											     	    */
		/************************************************************************************************************/
		long prsnKey = IdGenerator.generate(connection, "Prsn");
		Person person = personRole.getPerson();
		String storeNumberString = null;
		PreparedStatement ps = null;
		 
		try {
		if(!custflag) {
		 storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
		}
		
		ps = connection.prepareStatement(INSERTPERSONQUERY);
		CommonUtil.setPsLongParam(ps,1, prsnKey);
		CommonUtil.setPsStringParam(ps,2, person.getFirstName());
		CommonUtil.setPsStringParam(ps,3, person.getLastName());
		CommonUtil.setPsStringParam(ps,4, personRole.getConsumerId());
		CommonUtil.setPsStringParam(ps,5, personRole.getProducerId());
		CommonUtil.setPsStringParam(ps,6, storeNumberString);
		CommonUtil.setPsStringParam(ps,7, person.getMiddleName());
		CommonUtil.setPsStringParam(ps,8, CommonUtil.toTimestampStr(person.getLastUpdateTimestamp()));
		CommonUtil.setPsStringParam(ps,9,CommonUtil.toTimestampStr(person.getLastUpdateTimestamp()));
		CommonUtil.setPsStringParam( ps,10, CommonUtil.toTimestampStr(person.getDateOfBirth()));
		CommonUtil.setPsStringParam( ps,11, CommonUtil.toTimestampStr(person.getDeceaseddate()));
  	    
		ps.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
           throw e;
         } finally {
			CommonUtil.closePreparedStatementResultSet(ps, null);
		}
		return prsnKey;
	}

	public Long upsertPersonCustomer(Connection connection,  PersonRole personRole,Long prsnKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, ConsumerIdMissingException {  
		try {
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertPersonCustomer personRole prsnkey : " + prsnKey);}
			
			Long personRoleKey = null;
			String personRoleTypeCode = (personRole.getRole() != null) ? personRole.getRole().getRoleType() : null;
			Long roleTypeKey = (!StringUtil.isEmpty(personRoleTypeCode))
					? TableCacheSingleton.getInstance(connection).getKeyFromCode(LT_PRSNRLTYP, personRoleTypeCode) : null;
					PreparedStatement ps = null;
					ResultSet rs= null;
			try {		
			ps = connection.prepareStatement(QUERYSQLCUSTOMER);
			
			CommonUtil.setPsLongParam(ps,1, prsnKey);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				
				personRoleKey = ResultSetWrapper.getLong(rs, "prsnRlKey".toUpperCase());
			}

		//	super.close();
			}catch (SQLException e) {
                e.printStackTrace();
               throw e;
             } finally {
				CommonUtil.closePreparedStatementResultSet(ps, rs);
			}

			PersonRoleApi personRoleApi = new PersonRoleApi();
			if (prsnKey != null) {
				// Person exist. check if the role exist for the person
				if (personRoleKey != null) {
					
					personRoleApi.updatePersonRoleCustomer(connection, prsnKey, personRoleKey, roleTypeKey, personRole);
				} 
				updatePerson(connection, prsnKey, personRoleKey, null, personRole);
			} 

			ContactMethodApi contactMethodApi = new ContactMethodApi();
			prescriberAddressKey=contactMethodApi.upsertContactMethodByPersonKey(connection, null, prsnKey,
					personRole,personRoleKey);
			
			return personRoleKey;
		} catch (SQLException e) {
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
		}  finally {
		//	super.close();
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertPerson personRole consumer id : " + personRole.getConsumerId());}
		}
	}
	
	public Long upsertCreatePerson(Connection connection, Store store , PersonRole personRole) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, ConsumerIdMissingException {  
		try {
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertCreatePerson personRole consumer id : " + personRole.getConsumerId());}
			String storeNumberString = null;
			boolean custflag = store.getStorenumber() == null && (personRole.getRole().getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType())
					|| personRole.getRole().getRoleType().equalsIgnoreCase(CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType()));
			if(custflag){
				 storeNumberString = null;
			}
			else {
			 storeNumberString = CommonUtil.createStoreLeadingZeros(store.getStorenumber());
			}

			// Long prsnKey = FindUtil.findPersonKey(connection,
			// personRole.getConsumerId(), storeNumberString);

			Long prsnKey = null;
			Long personRoleKey = null;

			

			PersonRoleApi personRoleApi = new PersonRoleApi();
			
				// Person does not exist. and must be inserted.
				prsnKey = insertPerson(connection, store, personRole,custflag);

				// role does not exist and must be inserted.
				/************************************************************************************************************/
				/* insert into PersonRole table */
				/************************************************************************************************************/
				personRoleKey = personRoleApi.insertPersonRole(connection, prsnKey, store, personRole);
		

			ContactMethodApi contactMethodApi = new ContactMethodApi();
			prescriberAddressKey=contactMethodApi.upsertContactMethodByPersonKey(connection, storeNumberString, prsnKey,
					personRole,personRoleKey);
			
			return personRoleKey;
		} catch (SQLException e) {
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
		} catch (ConsumerIdMissingException e) {
			e.printStackTrace();
			throw e;
		} finally {
	//		super.close();
			if(personRole !=null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertPerson personRole consumer id : " + personRole.getConsumerId());}
		}
	}
	
	
}
