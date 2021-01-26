package ca.sdm.cdr.jdbc.api.address.query;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.shoppersdrugmart.rxhb.ehealth.Address;

public class AddressApi {

	
	
  	public static Address populateAddress(ResultSet rs,
            Address currAddressObj, 
            String currEmail,
            String currPhone,
            String currFax,
            String ContactMethodAlias,
            String AddressAlias,
            String EmailAlias,
            String phoneAlias,
            String faxAlias) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {

		/****************************************************************************
		/* populating address ,  email ,  fax ,  phone */
		/****************************************************************************/

		if( currAddressObj == null || currEmail == null || currPhone == null || currFax == null )
		{

			Address address = null ;
			if( currAddressObj == null )
			{
				currAddressObj = new Address();
			}
			address = PopulateJAXB.populateAddress(rs, ContactMethodAlias , AddressAlias );
			if( address != null )
			{
				// 
				//patient.getPerson().setAddress(currAddressObj);
				if( currEmail != null )
				{
					address.setEmail(currEmail);
				}
				if( currAddressObj.getPrimaryPhoneNumber() != null )
				{
					address.setPrimaryPhoneNumber(currAddressObj.getPrimaryPhoneNumber());
				}
				if( currAddressObj.getFaxNumber() != null )
				{
					address.setFaxNumber(currAddressObj.getFaxNumber());
				}
				currAddressObj =	 address ;
			}
			


			String email = PopulateJAXB.getEmailFromResultSet(rs, ContactMethodAlias, EmailAlias);
			if( email != null )
			{
				currEmail = email ;
				if( currAddressObj != null )
				{
					currAddressObj.setEmail(currEmail);
				}
			}
			else
			{
				
				String phone = PopulateJAXB.getPhoneFromResultSet(rs,  ContactMethodAlias ,phoneAlias);	
				if( phone != null)
				{
					currPhone = phone ;
					if( currAddressObj != null )
					{
						currAddressObj.setPrimaryPhoneNumber(currPhone);
					}

				}
				else
				{
					String fax = PopulateJAXB.getFaxFromResultSet(rs,  ContactMethodAlias ,faxAlias);
					if( fax != null)
					{
						currFax = fax ;	
						if( currAddressObj != null )
						{
							currAddressObj.setFaxNumber(currFax);
						}
					}					
				}

			}
			
		}
		return currAddressObj;
		/****************************************************************************/		
	}
  	
}
