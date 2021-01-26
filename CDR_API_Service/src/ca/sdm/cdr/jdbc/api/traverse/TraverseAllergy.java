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
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;

public class TraverseAllergy {

	String allergyAlias ;
	String allergyDrugAlias ;
	String allergyDrugDosageFormAlias ;
	String allergyDrugColorAlias ;
	String allergyDrugManufacturerAlias ;
	String allergyDrugManufacturerRecallAlias ;
	String allergyDrugMoleculeAlias ;
	String allergyDrugMoleculeActiveIngredientAlias ;
	
	Set <Long> allergyHashSet = new HashSet<Long>();
	private Set <Long> drugHashSet = new HashSet<Long>();

	
	Allergy currentAllergyObject = null ;
	/* Travers Classes */
	TraverseDrug traverseDrug ;

	

	public TraverseAllergy(	String allergyAlias , String allergyDrugAlias , String allergyDrugDosageFormAlias 
				, String allergyDrugColorAlias , String allergyDrugManufacturerAlias , String allergyDrugManufacturerRecallAlias 
				, String allergyDrugMoleculeAlias , String allergyDrugMoleculeActiveIngredientAlias )

	{
		this.allergyAlias = allergyAlias;
		this.allergyDrugAlias = allergyDrugAlias  ;
		this.allergyDrugDosageFormAlias = allergyDrugDosageFormAlias ;
		this.allergyDrugColorAlias = allergyDrugColorAlias ;
		this.allergyDrugManufacturerAlias = allergyDrugManufacturerAlias ;
		this.allergyDrugManufacturerRecallAlias = allergyDrugManufacturerRecallAlias ;
		this.allergyDrugMoleculeAlias = allergyDrugMoleculeAlias  ;
		this.allergyDrugMoleculeActiveIngredientAlias = allergyDrugMoleculeActiveIngredientAlias  ;
	
	}
	
	public Allergy getCurrentAllergyObject()
	{
		return currentAllergyObject ;
	}
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentAllergyKey = ResultSetWrapper.getLong(rs, allergyAlias , "_ptntAlrgyKey" ) ;
		
		if( currentAllergyKey <= 0 )
			return currentAllergyKey;
		if( allergyHashSet.contains(currentAllergyKey) == false ) 
		{
			currentAllergyObject = null ;
			traverseDrug = null ;
			traverseDrug = new TraverseDrug(allergyDrugAlias , allergyDrugManufacturerAlias , allergyDrugManufacturerRecallAlias 
					, allergyDrugDosageFormAlias, allergyDrugColorAlias 
					, allergyDrugMoleculeAlias , allergyDrugMoleculeActiveIngredientAlias); 
					
			drugHashSet.clear();
			
			allergyHashSet.add(currentAllergyKey);
			Allergy allergy = PopulateJAXB.populateAllergy(rs, allergyAlias);
			if( allergy  != null )
			{
				currentAllergyObject = allergy  ; 
				allergyHashSet.add(currentAllergyKey);
				
				traverseDrug(rs);

			}
			  
		}
		else
		{
			traverseDrug(rs);
		}
		return currentAllergyKey;

	}
	
	private void traverseDrug(ResultSet rs ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long drugKey = traverseDrug.traverse(rs);
		if( drugKey <= 0 )
			return ;
		if( drugHashSet.contains(drugKey) == false  )
		{
			currentAllergyObject.setDrug(traverseDrug.getCurrentDrugObject());
			drugHashSet.add(drugKey);
		}
		
	}
}
