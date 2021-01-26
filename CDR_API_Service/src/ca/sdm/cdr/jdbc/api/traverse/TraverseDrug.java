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
import ca.shoppersdrugmart.rxhb.ehealth.Colour;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;

public class TraverseDrug {


	/******************************************************************************************/ 
	/*    Table Alias variables */   
	String drugAlias;
	String drugManufacturerAlias ;
	String drugManufacturerRecallAlias ; 
	String drugDosageFormAlias;
	String drugColourAlias;
	String moleculeAlias ; 
	String moleculeActiveIngredientAlias; 
	
	/* Table Key variables */ 
	long currentDrugKey = 0;
	long currentDrugColourKey = 0 ;
	/* HashSet variables */ 
	
	private Set <Long> drugHashSet = new HashSet<Long>();
	private Set <Long> moleculeHashSet = new HashSet<Long>();
	private Set <Long> drugColourHashSet = new HashSet<Long>();
	private Set <Long> manufacturerRecallHashSet = new HashSet<Long>();
	
	
	
	
	/* current Objects variables */ 
	Drug	currentDrugObject = null; 
	Molecule currentMoleculeObject  = null; 
	/* Travers Classes */
	TraverseMolecule traverseMolecule ;
	TraverseManufacturerRecall traverseManufacturerRecall = null ;
	
	/******************************************************************************************/ 
	
	
	
	
	public Drug getCurrentDrugObject()
	{
		return currentDrugObject;
	}
	
	public TraverseDrug( 
			String drugAlias , 
			String drugManufacturerAlias , 
			String drugManufacturerRecallAlias , 
			String drugDosageFormAlias , 
			String drugColourAlias ,
			String moleculeAlias , 
			String moleculeActiveIngredientAlias )
	{
		this.drugAlias = drugAlias ;
		this.drugManufacturerAlias = drugManufacturerAlias;
		this.drugManufacturerRecallAlias = drugManufacturerRecallAlias ; 
		this.drugDosageFormAlias = drugDosageFormAlias; 
		this.drugColourAlias = drugColourAlias; 
		this.moleculeAlias = moleculeAlias; 
		this.moleculeActiveIngredientAlias = moleculeActiveIngredientAlias;
	}

	public Long traverse(ResultSet rs) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		currentDrugKey =  ResultSetWrapper.getLong(rs, drugAlias , "_DRGKEY" ) ;


		if( currentDrugKey <= 0 )
			return currentDrugKey;
		
		if ( drugHashSet.contains(currentDrugKey) == false  )
		{
			/* reset Drug variables */
			
			currentDrugObject = null;
			
			moleculeHashSet.clear();	
			currentMoleculeObject = null ;
			currentMoleculeObject = new Molecule();
			traverseMolecule= new TraverseMolecule ( moleculeAlias ,  moleculeActiveIngredientAlias );
			
			manufacturerRecallHashSet.clear();
			traverseManufacturerRecall = new TraverseManufacturerRecall(drugManufacturerRecallAlias ) ;
			
			/* reset Color variable */
			drugColourHashSet.clear();
			
			
			Drug drug = PopulateJAXB.populateDrug(rs, drugAlias , drugManufacturerAlias  , drugDosageFormAlias );
			
			
			if( drug != null )
			{
				currentDrugObject = drug ;
				drugHashSet.add(currentDrugKey);
				traverseDrugColour(rs);
				traverseMolecule(rs);
				traverseManufacturerRecall(rs);
			}
		}
		else
		{
			traverseDrugColour(rs);
			traverseMolecule(rs);
			traverseManufacturerRecall(rs);
		}
	
		return currentDrugKey ;
	}	
	
	private void traverseMolecule(ResultSet rs ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long moleculeKey = traverseMolecule.traverse(rs);
		if( moleculeHashSet.contains(moleculeKey) == false  )
		{
			currentDrugObject.setMolecule(traverseMolecule.getCurrentMoleculeObject());
			moleculeHashSet.add(moleculeKey);
		}

	}
	
	private void traverseManufacturerRecall(ResultSet rs ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long drugKey = traverseManufacturerRecall.traverse(rs);
		if( manufacturerRecallHashSet.contains(drugKey) == false  )
		{
			currentDrugObject.setRecall(traverseManufacturerRecall.getCurrentManufacturerRecallObject());
			manufacturerRecallHashSet.add(drugKey);
		}

	}
	
	public void traverseDrugColour(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException
	{
		currentDrugColourKey =  ResultSetWrapper.getLong(rs, drugColourAlias , "_DRGCLRKEY" ) ;
		if( currentDrugColourKey <= 0 )
			return ;
		if ( drugColourHashSet.contains(currentDrugColourKey) == false  )
		{
	
			Colour colour = PopulateJAXB.populateDrugColour(rs, drugColourAlias ) ;
			
			if( colour != null )
			{
				currentDrugObject.getColour().add(colour);
				
				drugColourHashSet.add(currentDrugColourKey);
			}
		}
	
	}
}
