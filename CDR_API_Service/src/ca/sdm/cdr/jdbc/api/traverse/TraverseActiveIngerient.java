package ca.sdm.cdr.jdbc.api.traverse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.shoppersdrugmart.rxhb.ehealth.ActiveIngredient;

public class TraverseActiveIngerient {



	/******************************************************************************************/ 
	/*    Table Alias variables */   
	String activeIngredientAlias; 
	
	/* Table Key variables */ 
	long currentActiveIngredientKey = 0;
	/* HashSet variables */ 
	
	private Set <Long> activeIngredientHashSet = new HashSet<Long>();
	
	
	
	/* current Objects variables */ 
	ActiveIngredient currentActiveIngredientObject  = null; 
	/* Travers Classes */
	
	/******************************************************************************************/ 
	
	
	
	
	public ActiveIngredient getCurrentActiveIngredientObject()
	{
		return currentActiveIngredientObject;
	}
	
	public TraverseActiveIngerient( String activeIngredientAlias )
	{
		this.activeIngredientAlias = activeIngredientAlias; 
	}

	public Long traverse(ResultSet rs) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		currentActiveIngredientKey =  ResultSetWrapper.getLong(rs, activeIngredientAlias  ,  "_ACTINGRDNTKEY") ;


		if( currentActiveIngredientKey <= 0 )
			return currentActiveIngredientKey;
		
		if ( activeIngredientHashSet.contains(currentActiveIngredientKey) == false  )
		{
			/* reset ActiveIngredient variables */
			
			currentActiveIngredientObject = null;
			
			ActiveIngredient activeIngredient = PopulateJAXB.populateMoleculeActiveIngredient(rs, activeIngredientAlias  );
			
			
			if( activeIngredient != null )
			{
				currentActiveIngredientObject = activeIngredient ;
				activeIngredientHashSet.add(currentActiveIngredientKey);
				
			}
		}
		else
		{
		}
	
		return currentActiveIngredientKey ;
	}	
	
	
	
}
