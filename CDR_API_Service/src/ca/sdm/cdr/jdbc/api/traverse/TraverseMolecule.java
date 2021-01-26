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
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;


public class TraverseMolecule {


	/******************************************************************************************/ 
	/*    Table Alias variables */   
	String moleculeAlias ; 
	String moleculeActiveIngredientAlias; 
	
	/* Table Key variables */ 
	long currentMoleculeKey = 0;
	/* HashSet variables */ 
	
	private Set <Long> moleculeHashSet = new HashSet<Long>();
	private Set <Long> moleculeActiveIngerientHashSet = new HashSet<Long>();
	
	
	
	/* current Objects variables */ 
	Molecule currentMoleculeObject  = null; 
	/* Travers Classes */
	TraverseActiveIngerient traverseActiveIngerient;
	/******************************************************************************************/ 
	
	
	
	
	public Molecule getCurrentMoleculeObject()
	{
		return currentMoleculeObject;
	}
	
	public TraverseMolecule( String moleculeAlias , String moleculeActiveIngredientAlias)
	{
		this.moleculeAlias = moleculeAlias; 
		this.moleculeActiveIngredientAlias = moleculeActiveIngredientAlias; 
	}

	public Long traverse(ResultSet rs) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		currentMoleculeKey =  ResultSetWrapper.getLong(rs, moleculeAlias  ,  "_MlclKey") ;


		if( currentMoleculeKey <= 0 )
			return currentMoleculeKey;
		
		if ( moleculeHashSet.contains(currentMoleculeKey) == false  )
		{
			/* reset Molecule variables */
			
			currentMoleculeObject = null;
			
			traverseActiveIngerient = null ;
			traverseActiveIngerient = new TraverseActiveIngerient(moleculeActiveIngredientAlias);
			
			moleculeActiveIngerientHashSet.clear();
			
			Molecule molecule = PopulateJAXB.populateMolecule(rs, moleculeAlias  );
			
			
			if( molecule != null )
			{
				currentMoleculeObject = molecule ;
				moleculeHashSet.add(currentMoleculeKey);
				traverseMoleculeActiveIngerient(rs);
				
			}
		}
		else
		{
			traverseMoleculeActiveIngerient(rs);
		}
	
		return currentMoleculeKey ;
	}	
	
	private void traverseMoleculeActiveIngerient(ResultSet rs ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long moleculeActiveIngerientKey = traverseActiveIngerient.traverse(rs);
		if( moleculeActiveIngerientKey<= 0 )
			return ;
		if( moleculeActiveIngerientHashSet.contains(moleculeActiveIngerientKey) == false  )
		{
			currentMoleculeObject.getAdditionalIngredient().add(traverseActiveIngerient.getCurrentActiveIngredientObject());
			moleculeActiveIngerientHashSet.add(moleculeActiveIngerientKey);
		}

	}
	
}
