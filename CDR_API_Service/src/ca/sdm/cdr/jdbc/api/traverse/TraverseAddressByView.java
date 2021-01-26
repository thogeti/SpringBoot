package ca.sdm.cdr.jdbc.api.traverse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Province;

public class TraverseAddressByView {

	private String contactMethodViewAlias ;		// CntctMthd   table for ptntAllergyReaction 

	Address currentAddressObj 		= null ;
	String currentEmail;
	String currentPhone;
	String currentFax;

	public TraverseAddressByView( String contactMethodViewAlias)
	{
		this.contactMethodViewAlias = contactMethodViewAlias;
		
	}
			
	public Address traverse(ResultSet rs) throws CodeNotFoundFromTableCacheException, CDRInternalException, SQLException, IOException, NamingException
	{

		Address address = null;
		
		long contactMethodKey = ResultSetWrapper.getLong(rs,contactMethodViewAlias , "_CM_CNSMRID");
//		long contactMethodKey = rs.getLong(contactMethodViewAlias +"_CM_CNSMRID");
		
		if( contactMethodKey <= 0 )
			return address;
		address = new Address();
		
		address.setAddressLineOne(ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_ADDRLNONE"));
		address.setAddressLineTwo(ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_ADDRLNTWO"));
		address.setCityName(ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_CITYNM"));
		address.setConsumerId(ResultSetWrapper.getString(rs,contactMethodViewAlias , "CM_CNSMRID"));
		address.setCountryCode(ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_CNTRYCD"));
		address.setEmail(ResultSetWrapper.getString(rs,contactMethodViewAlias , "EMAIL_EMAILADDR"));
		address.setFaxNumber(ResultSetWrapper.getString(rs,contactMethodViewAlias , "FAX_TELECOMNUM"));
		address.setPostalCode(ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_POSTALCD"));
		address.setPrimaryPhoneNumber(ResultSetWrapper.getString(rs,contactMethodViewAlias , "PHONE_TELECOMNUM"));
		
		String provCode = ResultSetWrapper.getString(rs,contactMethodViewAlias , "ADDR_PROVCD"); 
		if( provCode  != null )
		{
			address.setProvinceCode(Province.fromValue( provCode) ) ;
		}

		return address;

	}
	
}
