package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNTCTMTHDTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTCTGRYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PACKSZUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.TelecomType;
import ca.sdm.cdr.jdbc.upsert.api.PrescriptionApi;
//start
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Address;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ContactPurposeType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Note;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.NoteCategory;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.NoteType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Pack;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.PackSizeUoMTypeDisplay;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Province;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ReauthFaxFlag;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Recorder;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Supervisor;
//End


/*
 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
TE99              NTT Data                   QHR Accuro Project
*/


public class CDRMedReviewGet {
	public ResultSet resultSet;
	public PreparedStatement preparedStatement;
	String jndiVersion ;
	
	//constructor 
	public CDRMedReviewGet() {
		jndiVersion = TableCacheSingleton.JNDI_VERSION;
	}
	
	
	public void close() {
		if (resultSet != null)
			try {
				resultSet.close();
				resultSet = null;
			} catch (SQLException e) {
				e.printStackTrace();
			};
			
		if (preparedStatement != null)
			try {
				preparedStatement.close();
				preparedStatement = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public Integer getInt(String columnName) throws SQLException {
		Integer value = new Integer(resultSet.getInt(columnName));
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}
	
	public Long getLong(String columnName) throws SQLException {
		Long value = new Long(resultSet.getLong(columnName));
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	

	public String getString(String columnName) throws SQLException {
		return resultSet.getString(columnName);
	}
	
	public BigDecimal getBigDecimal(String columnName) throws SQLException {
		BigDecimal value = resultSet.getBigDecimal(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	
	
	public Double getDouble(String columnName) throws SQLException {
		Double value = resultSet.getDouble(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	

	public Date getDate(String columnName) throws SQLException {
		Date value = resultSet.getDate(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return value;
	}	
	
	public BigInteger getBigInteger(String columnName) throws SQLException {
		String value = resultSet.getString(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return new BigInteger(value);
	}	
	
	public XMLGregorianCalendar getCalendar(String columnName) throws SQLException, ParseException, DatatypeConfigurationException {
		Timestamp timestamp = resultSet.getTimestamp(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return CommonUtil.getXMLGregorianCalendar(timestamp, true);
	}	
	
	public Boolean getBoolean(String columnName) throws CDRInternalException, SQLException {
		String flagStr = resultSet.getString(columnName);
		if (resultSet.wasNull())
			return null;
		else
			return new Boolean(CommonUtil.convertYesNoFlagToBoolean(flagStr));
	}
	
	public Long getKeyFromCode(String table, String code) throws KeyNotFoundFromTableCacheException, NamingException, SQLException, IOException {
		return TableCacheSingleton.getInstance(jndiVersion).getKeyFromCode(table, code);
	};
	
	public String getCodeFromKey(String table, Long key) throws CodeNotFoundFromTableCacheException, NamingException, SQLException, IOException{
		return TableCacheSingleton.getInstance(jndiVersion).getCodeFromKey(table, key);
	}
	
	public Pack populatePack() throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException
	{
		Pack pack = new Pack();
	//	pack.setAlternativepacksize(getInt("PACK_ALTRNTVPACKSZ"));
	//	pack.setAlternativepacksizeunitofmeasure(getString("PACK_ALTRNTVPACKSZUOM"));
		pack.setStrength(getString("PACK_STRNGTH"));//TE97_024
		pack.setGTIN(getString("PACK_GTIN")) ;
		
	//	pack.setIsActiveFlag(CommonUtil.convertYesNoFlagToBoolean(getString("PACK_ISACTFLG" )));
	//	pack.setConsumerId(getString("PACK_CNSMRID")) ;
	//	pack.setIsCurrentFlag(CommonUtil.convertYesNoFlagToBoolean(getString("PACK_ISCRNTFLG"))) ;
	//	pack.setProducerId(getString("PACK_PRDCRID")) ;
	//	pack.setManufacturerDiscontinuedDate(CommonUtil.getXMLGregorianCalendar(getDate("PACK_MFCTRDISCNTDDT") , true));
		pack.setPackSize(getBigDecimal("PACK_PACKSZ") ) ;
	//	pack.setMasterid(getString("PACK_MSTRID")) ;
		Long packSizeUOMTypeKey = getLong("PACK_PACKSZUOMTYPKEY");
		if(packSizeUOMTypeKey != null)
		{
	        String packSizeUOMTypeCode = getCodeFromKey(LT_PACKSZUOMTYP, packSizeUOMTypeKey);
	        if (packSizeUOMTypeCode != null)
	        {
	        	PackSizeUoMTypeDisplay packSizeUoMTypeDisplay = new PackSizeUoMTypeDisplay();
	        	packSizeUoMTypeDisplay.setPackSizeUoMTypeCode(packSizeUOMTypeCode);
	        	pack.setPackSizeUOMCode(packSizeUoMTypeDisplay);			        	
	        }
		}
		
		Long strengthUOMTypeKey = getLong("PACK_STRNGTHUOMTYPKEY");
		if(strengthUOMTypeKey!=null)
		{
	        String strengthUOMTypeCode = getCodeFromKey(LT_STRNGTHUOMTYP, strengthUOMTypeKey);
			pack.setStrengthUOMCode(strengthUOMTypeCode);
		}
		return pack;
	}
	
	public Note populateNote(Connection connection) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException
	{
		Note note = new Note();
		
		note.setConsumerId(getString("NT_CNSMRID"));
		note.setProducerId(getString("NT_PRDCRID"));
		note.setNoteFrench(getString("NT_NTFR"));
		note.setNoteEnglish(getString("NT_NTENG"));
		note.setCreateTimestamp(getCalendar("NT_CRTTIMESTAMP"));
		note.setUpdateTimestamp(getCalendar("NT_UPDTTIMESTAMP"));
		note.setIsPharmacist(CommonUtil.convertYesNoFlagToBoolean(getString("NT_ISPHRMCST")));
		note.setCreateUserId(getString("NT_CRTUSRID"));
		note.setDispenser(null);
		Long recorderKey = getLong("NT_RCRDRKEY");
		/*if(recorderKey!=null)
		{
			PersonRoleMedReviewGet personRoleGet = new PersonRoleGet();
			Recorder recorder = personRoleMedReviewGet.fetchRecorder(connection, recorderKey);
			note.setRecorder(recorder);
		}
		
		Long supervisorKey = getLong("NT_SPRVSRKEY");
		if(supervisorKey != null)
		{
			PersonRoleGet personRoleGet = new PersonRoleGet();
			Supervisor supervisor = personRoleGet.fetchSupervisor(connection, supervisorKey);
			note.setSupervisor(supervisor);
		}
		
		Long noteTypeKey = getLong("NT_NTTYPKEY");
		if(noteTypeKey != null)
		{
			String noteTypeCode = getCodeFromKey(LT_NTTYP, noteTypeKey);
			note.setNoteTypeCode(NoteType.fromValue(noteTypeCode));
		}
		
		Long noteCategoryTypeKey =  getLong("NT_NTCTGRYTYPKEY");
		if(noteCategoryTypeKey != null)
		{
			String noteCategoryTypeCode = getCodeFromKey(LT_NTCTGRYTYP, noteCategoryTypeKey);
			note.setNoteCategory(NoteCategory.fromValue(noteCategoryTypeCode));
		}

		Long locationKey = getLong("NT_LOCKEY");
		if(locationKey != null)
		{
			
		};*/
		return note;
	}

	public void populateAddress(Address address) throws SQLException, CodeNotFoundFromTableCacheException, NamingException, IOException
	{
		Long contactMethodTypeKey = getLong("CNTCTMTHD_CNTCTMTHDTYPKEY");
		String contactMethodTypeCode = getCodeFromKey(LT_CNTCTMTHDTYP, contactMethodTypeKey);
		if ("Email Address".equals(contactMethodTypeCode)) {
			address.setEmail(getString("EMAIL_EMAILADDR"));
		} else if ("Telecom".equals(contactMethodTypeCode)) {
			String isFax = getString("TELECOM_TELFAXIND");
			if (TelecomType.FAX.value().equals(isFax))
				address.setFaxNumber(getString("TELECOM_TELECOMNUM"));
			else if (TelecomType.TELEPHONE.value().equals(isFax))
				address.setPrimaryPhoneNumber(getString("TELECOM_TELECOMNUM"));
		} else if ("Postal Address".equals(contactMethodTypeCode)) {
			address.setAddressLineOne(getString("ADDR_ADDRLNONE"));
			address.setAddressLineTwo(getString("ADDR_ADDRLNTWO"));
			address.setCityName(getString("ADDR_CITYNM"));
			address.setCountryCode(getString("ADDR_CNTRYCD"));
			address.setPostalCode(getString("ADDR_POSTALCD"));
			String provinceCode = getString("ADDR_PROVCD");
		//	Province province = Province.fromValue(provinceCode);
			address.setProvinceCode(provinceCode);
		}
	/*	String consumerId = getString("CNTCTMTHD_CNSMRID");
		if ( consumerId !=null)
			address.setConsumerId(consumerId); */
	}
	
	public Address populateAddress() throws SQLException {
		Address address = new Address();
		boolean hasValue = false;
		String value = null;
		value = getString("EMAIL_EMAILADDR");
		if (value != null) {
			address.setEmail(value);
			hasValue = true;
		}
		;
		value = getString("FAX_TELECOMNUM");
		if (value != null) {
			address.setFaxNumber(value);
			hasValue = true;
		}
		;

		value = getString("PHONE_TELECOMNUM");
		if (value != null) {
			address.setPrimaryPhoneNumber(value);
			hasValue = true;
		}
		;

		
	/*	value = getString("ALTERN_TELECOMNUM");
		if (value != null) {
			address.setAlternatePhoneNumber(value);
			hasValue = true;
		}*/
		
		
		value = getString("ADDR_ADDRLNONE");

		if (value != null) {
			address.setAddressLineOne(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_ADDRLNTWO");
		if (value != null) {
			address.setAddressLineTwo(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_CITYNM");
		if (value != null) {
			address.setCityName(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_CNTRYCD");
		if (value != null) {
			address.setCountryCode(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_PROVCD");
		if (value != null) {
			Province province = Province.fromValue(value);
			address.setProvinceCode(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_POSTALCD");
		if (value != null) {
			address.setPostalCode(value);
			hasValue = true;
		}
		;

		value = getString("CM_CNSMRID");
		if (value != null) {
			address.setConsumerId(value);;
			hasValue = true;
		}
		;
		

		value = getString("ADDR_TYPE");
		if (value != null) {
			address.setContactPurposeType(value); 
			hasValue = true;
		}
		
		
		if (hasValue)
			return address;
		else
			return null;
	}
	
	
//T99 Started	
	public Address populateAddressPrescriber(Address address) throws SQLException {
		boolean hasValue = false;
		String value = null;
		value = getString("EMAIL_EMAILADDR");
		if (value != null) {
			address.setEmail(value);
			hasValue = true;
		}
		;
		value = getString("FAX_TELECOMNUM");
		if (value != null) {
			address.setFaxNumber(value);
			hasValue = true;
		}
		;

		value = getString("PHONE_TELECOMNUM");
		if (value != null) {
			address.setPrimaryPhoneNumber(value);
			hasValue = true;
		}
		;

		
		/*value = getString("ALTERN_TELECOMNUM");
		if (value != null) {
			address.setAlternatePhoneNumber(value);
			hasValue = true;
		}*/
	
		
		value = getString("ADDR_ADDRLNONE");
		if (value != null) {
			address.setAddressLineOne(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_ADDRLNTWO");
		if (value != null) {
			address.setAddressLineTwo(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_CITYNM");
		if (value != null) {
			address.setCityName(value);
			hasValue = true;
		}
		;
		
		value = getString("ADDR_CNTRYCD");
		if (value != null) {
			address.setCountryCode(value);
			hasValue = true;
		}
		;
		
		value = getString("ADDR_PROVCD");
		if (value != null) {
			Province province = Province.fromValue(value);
			address.setProvinceCode(value);
			hasValue = true;
		}
		;

		value = getString("ADDR_POSTALCD");
		if (value != null) {
			address.setPostalCode(value);
			hasValue = true;
		}
		;

		value = getString("CM_CNSMRID");
		if (value != null) {
			address.setConsumerId(value);;
			hasValue = true;
		}
		;
		
		value = getString("ADDR_TYPE");
		if (value != null) {
			address.setContactPurposeType( value); 
			hasValue = true;
		}
		//TE97_024 Commented for future implementation
        value = getString("ACTVFLG");
        if (value != null) {
	        address.setIsActiveAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024   Mail_Flag         (Default Y)
        value = getString("MAILFLG");
        if (value != null) {
	        address.setIsMailAddressFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
      //TE97_024   Mail_Flag         (Default null)
        value = getString("PRIMARYFLG");
        if (value != null) {
	        try {
				address.setIsPrimaryAddressFlag(CommonUtil.convertYesNoFlagToBoolean(value));
			} catch (CDRInternalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        hasValue = true;
        }
    	//TE97_024  Reauth_Email_Flag (Default N)
        value = getString("REAUTHEMAILFLAG");
        if (value != null) {
	        address.setIsReauthEmailFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Phone_Flag (Default Y)
        value = getString("REAUTHPHONEFLAG");
        if (value != null) {
	        address.setIsReauthPhoneFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
    	//TE97_024 Reauth_Fax_Flag   (Default U)
      //TE97_024 Reauth_Fax_Flag   (Default U)
        value = getString("REAUTHFAXFLAG");
        if (value != null && !value.equals("U")) {
	        address.setIsReauthFaxFlag(CommonUtil.convertTrueFlaseReauthFlagString(value)); 
	        hasValue = true;
        }/*else{
        	 address.setIsReauthFaxFlag(ReauthFaxFlag.NOT_SPECIFIED); 
 	        hasValue = true;
        }*/
        
    	//TE97_024 Reauth_Visit_Flag (Default N)
        value = getString("REAUTHVISITFLAG");
        if (value != null) {
	        address.setIsReauthVisitFlag(CommonUtil.convertTrueFlaseFlagString(value)); 
	        hasValue = true;
        }
        
		if (hasValue)
			return address;
		else
			return null;
    }
//T99 Ended	
	
}
