package ca.sdm.cdr.jdbc.api.patient.query;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.traverse.TraverseAddressByView;
import ca.sdm.cdr.jdbc.api.traverse.TraversePatientIdentification;
import ca.sdm.cdr.jdbc.query.api.PatientIdentificationGet;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;
import ca.shoppersdrugmart.rxhb.ehealth.PatientMetrics;


/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL46 2018-02-15   NTT Data     Vlad Eidinov  SQL Optimization to improve 
                                             GetPatientByQueryCriteria performance
                                 
*/

public class QueryPatient  extends JDBCConnection {

	private static Logger logger = LogManager.getLogger(QueryPatient.class);
	String patientAlias  = "PTNT" ;
	String patientContactMethodViewAlias = "pv";
	Long timer = 0L;
	private final String patientIdAlias  = "PtntId" ;
	TraversePatientIdentification traversePatientIdentification = null;
	 private Set <Long> patientIdHashSet = new HashSet<Long>();
	 Patient currentPatientObject = null;
	 public String jndiVersion=null;
	
	public QueryPatient (Connection  connection) {
		super(connection);
		jndiVersion= TableCacheSingleton.JNDI_VERSION;
	}


	public Patient getPatientByPatientKey(Long patientKey) throws Exception { 
		Patient patient = null;

		QueryPatientPersonalInfo patientPersonalInfo = new QueryPatientPersonalInfo(connection);
		patient = patientPersonalInfo.getPatientPersonalInfo(patientKey) ;

		if( patient == null )
			return patient;


		QueryPatientMedicalCondition queryMedicalCondition= new QueryPatientMedicalCondition(connection);
		queryMedicalCondition.getPatientMedicalCondition(patientKey, patient.getPatientMedicalCondition());

		QueryPatientNote queryPatientNote = new QueryPatientNote(connection) ;
		queryPatientNote.getPatientNotes(patientKey, patient.getNote());

		List<PatientMetrics> patientMetrics =patient.getPatientMetrics() ;
		QueryPatientMetrics queryPatientMetrics = new QueryPatientMetrics(connection);
		queryPatientMetrics.getPatientMetrics(patientKey , patientMetrics);

		List<AdverseDrugReaction> adverseDrugReactionList = patient.getAdverseDrugReaction() ;
		QueryPatientADR queryPatientADR = new QueryPatientADR(connection);
		queryPatientADR.getPatientAdverseDrugReaction(patientKey, adverseDrugReactionList);

		List<Allergy> patientAllergies = patient.getAllergy();
		QueryAllergy queryAllergy = new QueryAllergy(connection);
		queryAllergy.getAllergy(patientKey, patientAllergies, false );

		return patient;
	}

	public Patient getPatient(String storeNumber, long rxNumber) throws Exception {
		Patient patient = null;
		PreparedStatement ps = null ;
		ResultSet rs  = null ;

		try {
			String sqlQuery = 
					" 	select rx.ptntKey as ptntKey " + 
							" 	from  " + 
							" 		rx  " + 
							" 	where  " + 
							" 		RXNUM = ? " + 
							" 		and STORENUM = ? " ;

			storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);
			String rxNumberString = String.format("%d", rxNumber);

			if (logger.isTraceEnabled())  {logger.trace("sqlQuery : " + sqlQuery );}
			ps = connection.prepareStatement(sqlQuery);
			CommonUtil.setPsStringParam(ps, 1, rxNumberString); 
			CommonUtil.setPsStringParam(ps, 2, storeNumber); 
			rs = ps.executeQuery();

			TableCacheSingleton.getInstance(jndiVersion);
			TableColumnSingleton.getInstance();

			Long ptntKey = null;

			if( rs.next() ) {
				ptntKey = rs.getLong("ptntKey") ;
				if (ptntKey==null || ptntKey.longValue() < 1) {
					// if no patient found , throws an exception 
					throw new PatientNotFoundException("No patient found for RxNum: '" + rxNumberString + "', StoreNum: '" + storeNumber + "'. ");
				}

			}
			else {
				String errorMessage = String.format(PatientNotFoundException.ErrorMessage_PATIENT_NOT_FOUND_BY_RX, storeNumber , rxNumberString);
				throw new PatientNotFoundException(errorMessage);
			}

			CommonUtil.closePreparedStatementResultSet(ps, rs);

			patient = getPatientByPatientKey(ptntKey);


		}
		catch( Exception e )
		{
			throw e;
		}
		finally
		{
			CommonUtil.closePreparedStatementResultSet(ps, rs);

		}			
		return patient;
	}

	//new addedd
	public Patient getPatient(String storeNumber, String txNumber) throws Exception {
		Patient patient = null;
		PreparedStatement ps = null ;
		ResultSet rs  = null ;		
	
	try {
		String sqlQuery =
		       "    select r.ptntkey as ptntKey " +
		       "      from tx t,                " +
		       "           rx r                 " +
		       "     where t.rxkey = r.rxkey    " + 
		       "       and t.txnnum = ?         " +
		       "       and t.storenum = ?       "; 
		
		storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);
		if(logger.isDebugEnabled()) {logger.debug("sqlQuery : " + sqlQuery );}
		ps = connection.prepareStatement(sqlQuery);
		CommonUtil.setPsStringParam(ps, 1, txNumber); 
		CommonUtil.setPsStringParam(ps, 2, storeNumber); 
		rs = ps.executeQuery();
		TableCacheSingleton.getInstance(jndiVersion);
		TableColumnSingleton.getInstance();

		Long ptntKey = null;
		if( rs.next() ) {
			ptntKey = rs.getLong("ptntKey") ;
			if (ptntKey==null || ptntKey.longValue() < 1) {
				throw new PatientNotFoundException("No patient found for TxNum: '" + txNumber + "', StoreNum: '" + storeNumber + "'. ");
			}
		}
		else {
			String errorMessage = String.format(PatientNotFoundException.ErrorMessage_PATIENT_NOT_FOUND_BY_TX, storeNumber , txNumber);
			throw new PatientNotFoundException(errorMessage);
		}

		CommonUtil.closePreparedStatementResultSet(ps, rs);
		patient = getPatientByPatientKey(ptntKey);
	} catch( Exception e ) {
		throw e;
	} finally {
		CommonUtil.closePreparedStatementResultSet(ps, rs);
	}			
	return patient;
}


	// This method should return Patient (& Person) simple types + Person address 
	public Patient getPatientByPatientId(String storeNumber, String patientId ) throws Exception { 
		Long patientKey = FindUtil.findPatientKey(connection, patientId, storeNumber);
		if(patientKey == null || patientKey == 0L ) {
			String errorMessage = String.format(PatientNotFoundException.ErrorMessage_PATIENT_NOT_FOUND_BY_ID,storeNumber,patientId);
			throw new PatientNotFoundException(errorMessage);

		}
		Patient patient = getPatientByPatientKey(patientKey);
		return patient;

	}



	
	
    private void populateTmpTable(String firstName,
                                  String lastName,
                                  String dateOfBirth,
                                  String phoneNum,
                                  String storeNumber ) throws SQLException, IOException, NamingException, Exception {

    	    String sql1 = 
    	    	  "	insert into tmp_eligibility(NOTIFICATIONKEY) "
    	    	+ "	select p.ptntkey       "
    	    	+ "	  from ptnt p          "
    	    	+ "	 where p.storeNum = ?  ";


    	    String sql2 = 
      	    	  "	insert into tmp_eligibility(NOTIFICATIONKEY) "
      	    	+ "	select p.ptntkey       "
      	    	+ "	  from ptnt      p,    "
                + "        cntctmthd c,    "
                + "        telecom   t     "
      	    	+ "	 where p.storeNum = ?  "
                + "    and p.ptntkey  = c.ptntkey        "
                + "    and c.cntctmthdkey = t.telecomkey "
                + "    and c.cntctmthdtypcd = 'Telecom'  "
                + "    and t.TELECOMNUM = ?    	         ";
    	     //   + "  fetch first 25 rows only            "; 
//              + "    and t.TELFAXIND in ('T','P','A')  "

    	    
    		if(logger.isDebugEnabled()) {logger.debug("getPatientByQueryCriteria : \n" 
                    +  "  storeNumber:" +  storeNumber + "\n"
                    +  "  firstName:" +  firstName + "\n"
                    +  "  lastName:" +  lastName + "\n"
                    +  "  dateOfBirth:" +  dateOfBirth + "\n"
                    +  "  phoneNum:" +  phoneNum);}
    		
    	    if( ((firstName   == null || firstName.equals("") )  && 
      		     (lastName    == null || lastName.equals(""))    && 
      		     (dateOfBirth == null || dateOfBirth.equals("")) && 
      		     (phoneNum    == null || phoneNum.equals("") )) || (storeNumber== null )) {

      		      String errorMessage = String.format(PatientNotFoundException.ErrorMessage_PATIENT_NOT_FOUND,storeNumber);
      		      throw new PatientNotFoundException(errorMessage);
            }
    	    
    	    List<String> parameterLists = new ArrayList<String>();
    		PreparedStatement ps = null ;
    		ResultSet rs  = null ;
    		timer = System.currentTimeMillis();
    		try {
    			if (phoneNum == null || phoneNum.equals("") || phoneNum.length() == 0) {
	    	    		parameterLists.add(storeNumber);
	    	    		if ( firstName !=null && (! firstName.equals("")) ) {
	    	    			sql1 += " and p.SNDXFRSTNM LIKE TRANSLATE(SOUNDEX(?), '0', '_') ";
	    	    			parameterLists.add(firstName);
	    	    			firstName = firstName.toUpperCase();
	    	    		}
	    	    		
	    	    		if ( lastName !=null && ( ! lastName.equals("")) ) {
	    	    			sql1 += " and p.SNDXLSTNM LIKE TRANSLATE(SOUNDEX(?), '0', '_') ";
	    	    			parameterLists.add(lastName);
	    	    			lastName = lastName.toUpperCase();
	    	    		}
	
	    	    		if ( dateOfBirth !=null && ( ! dateOfBirth.equals("")) ) {
	    	    			sql1 += "  and p.DTOFBIRTH = to_date(?,'YYYY-MM-DD') ";
	    	    			parameterLists.add(dateOfBirth);
	    	    		}
	    	    //		sql1 += "  fetch first 25 rows only";
	    	    		ps = connection.prepareStatement(sql1);
	    	    		for (int i = 0; i < parameterLists.size(); i++) {
	    	    			if (logger.isDebugEnabled()) {logger.debug(String.format("parameterLists(%s): %s", i+1, parameterLists.get(i)));}
	    					CommonUtil.setPsStringParam(ps, i+1, parameterLists.get(i));
	    				}
	    	    		
    			} else {
    		    	    ps = connection.prepareStatement(sql2);
	    		    	CommonUtil.setPsStringParam(ps, 1, storeNumber);
	    		    	CommonUtil.setPsStringParam(ps, 2, phoneNum);
    			}
    			int rows = ps.executeUpdate();
    			if (logger.isDebugEnabled()) {logger.debug(String.format("%s rows inserted into tmp_eligibility. insert sql ended in %s ms", rows, (System.currentTimeMillis() - timer)));}    			
    			CommonUtil.closePreparedStatementResultSet(ps, null);
    			
    	    } catch (SQLException e) {
		  	        e.printStackTrace();
		  	        throw e;
	        } catch (Exception e) {
	    	        e.printStackTrace();
	                throw e;    			
	        } finally {
    	             CommonUtil.closePreparedStatementResultSet(ps, null);
            }			    
    }    		
    		
    		
	
	// VL46 This method should return List (max 25 elements) of Patient (& Person) simple types + Person address
	public List<Patient> getPatientByQueryCriteria(String firstName,
			                                       String lastName,
			                                       Date   dateOfBirth,
			                                       String phoneNum,
			                                       String storeNumber ) throws SQLException, IOException, NamingException, Exception {
		
		storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String DateOfBirthString = null;
		if( dateOfBirth != null && dateOfBirth.equals("") == false ) {
			DateOfBirthString = dateFormat.format(dateOfBirth);
		}
		populateTmpTable(firstName, lastName, DateOfBirthString, phoneNum, storeNumber);
		String sql = TableCacheSingleton.getResource("PatientByQueryCriteria.sql");
		
		String firstAddressCNSMRID = null;
		Address addr = null;
		Patient pat = null;
		List<Patient> patients = new ArrayList<Patient>();
		List<String> alterPhonesList = new ArrayList<String>();
		
		Long ptntKey = null;
		Set<Long> ptntKeySet = new HashSet<>();

		PreparedStatement ps = null ;
		ResultSet rs  = null ;
		
		try {
			Long timer = System.currentTimeMillis();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (logger.isDebugEnabled()) {logger.debug(String.format("PatientByQueryCriteria.sql ended in %s ms.",  (System.currentTimeMillis() - timer)));}
			
			Long timer2 = System.currentTimeMillis();
			int i=1;
			while (rs.next()  && i < 26) {
				   boolean isMatched = true;
				   if (firstName != null && ! (firstName.equals(""))) {
					   firstName = firstName.toUpperCase();
					   String fName = rs.getString("PTNT_FRSTNM");
					   if (! fName.startsWith(firstName)) {
						   isMatched = false;
					   }
				   }

				   if (lastName != null && ! (lastName.equals(""))) {
					   lastName = lastName.toUpperCase();
					   String lName = rs.getString("PTNT_LSTNM");
					   if (! lName.startsWith(lastName)) {
						   isMatched = false;
					   }
				   }

// One patient may have multiple addresses but the following block will populate 
// only the first address instance for the given patient and ignore other addresses:				
					if (isMatched == true) {
						ptntKey = rs.getLong("PTNT_PTNTKEY");
						if (! ptntKeySet.contains(ptntKey)) {
							firstAddressCNSMRID = rs.getString("CM_CNSMRID");
							ptntKeySet.add(ptntKey);
							pat = PopulateJAXB.populatePatient(rs, "PTNT");
							currentPatientObject = pat;
							alterPhonesList = new ArrayList<String>();
							addr = new Address();
							PopulateJAXB.PopulateAddress46(rs, "", addr, alterPhonesList);
							pat.getPerson().getAddress().add(addr);
							traversePatientIdentification = null;
							traversePatientIdentification = new TraversePatientIdentification(patientIdAlias);
							traversrPatientIdentification(rs);// Added for PHN Number
							patients.add(pat);
						} else {
							String currentAddressCNSMRID = rs.getString("CM_CNSMRID");
							if (currentAddressCNSMRID.equals(firstAddressCNSMRID)) {
								PopulateJAXB.PopulateAddress46(rs, "", addr, alterPhonesList);
							}
						}
						i++;
					}
					
		    }   // while loop ended
			if (logger.isDebugEnabled()) {logger.debug(String.format("PatientByQueryCriteria.sql ResultSet ended in %s ms.",  (System.currentTimeMillis() - timer2)));}
			
		    if (ptntKeySet.isEmpty()) {
			    String errorMessage = String.format(PatientNotFoundException.ErrorMessage_PATIENT_NOT_FOUND,  storeNumber);
			    throw new PatientNotFoundException(errorMessage);
		    }
		    ptntKeySet.clear();
		    CommonUtil.closePreparedStatementResultSet(ps, rs);
		    
		} catch(Exception e ) {
			    throw e;
		} finally {
			    CommonUtil.closePreparedStatementResultSet(ps, rs);
		}	
	    return patients;
	}
	
	
		private void traversrPatientIdentification(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException, DatatypeConfigurationException, ParseException {  
				if(traversePatientIdentification != null) {
					long patientIdKey = traversePatientIdentification.traverse(rs);
					if( patientIdKey<= 0 )
						return;
						
					if( patientIdHashSet.contains(patientIdKey ) == false ) {
						currentPatientObject.getPatientIdentification().add(traversePatientIdentification.getCurrentPatientIdentificationObject());
						patientIdHashSet.add(patientIdKey);
					}
				}

		}
}
