package ca.sdm.cdr.jdbc.api.traverse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.shoppersdrugmart.rxhb.ehealth.ManufacturerRecall;

public class TraverseManufacturerRecall {

	
	String drugManufacturerRecallAlias ; 

	private Set <Long> manufacturerRecallHashSet = new HashSet<Long>();
	
	private Set <Integer> lotNumberHashSet = new HashSet<Integer>();
	
	
	
	/* current Objects variables */ 
	ManufacturerRecall  currentManufacturerRecallObject = null; 
	
	/******************************************************************************************/ 
	
	
	
	
	
	public TraverseManufacturerRecall( 
			String drugManufacturerRecallAlias )
	{
		this.drugManufacturerRecallAlias = drugManufacturerRecallAlias ; 
	}

	public ManufacturerRecall getCurrentManufacturerRecallObject()
	{
		return currentManufacturerRecallObject;
	}

	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException 
	{
		// Drug key is unique for the manunfacturere recall
		long drugKey =  ResultSetWrapper.getLong(rs, drugManufacturerRecallAlias , "_DrgKey" ) ;


		if( drugKey <= 0 )
			return drugKey;
		
		if ( manufacturerRecallHashSet.contains(drugKey) == false  )
		{
			currentManufacturerRecallObject = null;
			manufacturerRecallHashSet.add(drugKey);
			
			ManufacturerRecall manufRecallObj= PopulateJAXB.populateManufacturerRecall(rs,drugManufacturerRecallAlias);
			if(manufRecallObj != null )
			{
				currentManufacturerRecallObject = manufRecallObj ; 
				
				traverseLotNumber(rs);
			}
			
		}
		else
		{
			traverseLotNumber(rs);
		}
		
		return drugKey; 
	}
	
	public void traverseLotNumber(ResultSet rs) throws SQLException
	{
		int lotNumber =  ResultSetWrapper.getInt(rs, drugManufacturerRecallAlias , "_LOTNUM" ) ;

		if( lotNumberHashSet.contains(lotNumber) == false )
		{
			lotNumberHashSet.add(lotNumber);
			currentManufacturerRecallObject.getLotnumber().add(lotNumber);
		}
	}
}
