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
import ca.shoppersdrugmart.rxhb.ehealth.Reaction;

public class TraverseReaction {

	String reactionAlias ;
	String reactionReactionDrugAlias ;
	String reactionDrugAlias ;
	String reactionDrugDosageFormAlias ;
	String reactionDrugColorAlias ;
	String reactionDrugManufacturerAlias ;
	String reactionDrugManufacturerRecallAlias ;
	String reactionDrugMoleculeAlias ;
	String reactionDrugMoleActiveIngredientculeAlias ;

	
	Reaction currentReactionObject = null ;
	
	private Set <Long> reactionHashSet = new HashSet<Long>();
	private Set <Long> compoundHashSet = new HashSet<Long>();
	private Set <Long> packHashSet = new HashSet<Long>();
	private Set <Long> drugHashSet = new HashSet<Long>();

	
	TraverseDrug traverseDrug ;

	
	
	public TraverseReaction(String reactionAlias , String reactionReactionDrugAlias , String reactionDrugAlias 
			, String reactionDrugDosageFormAlias , String reactionDrugColorAlias , String reactionDrugManufacturerAlias 
			, String reactionDrugManufacturerRecallAlias , String reactionDrugMoleculeAlias , String reactionDrugMoleActiveIngredientculeAlias )
	{
		this.reactionAlias = reactionAlias;
		this.reactionReactionDrugAlias = reactionReactionDrugAlias;
		this.reactionDrugAlias  = reactionDrugAlias  ;
		this.reactionDrugDosageFormAlias = reactionDrugDosageFormAlias;
		this.reactionDrugColorAlias = reactionDrugColorAlias ;
		this.reactionDrugManufacturerAlias =reactionDrugManufacturerAlias ;
		this.reactionDrugManufacturerRecallAlias = reactionDrugManufacturerRecallAlias ;
		this.reactionDrugMoleculeAlias  = reactionDrugMoleculeAlias;
		this.reactionDrugMoleActiveIngredientculeAlias = reactionDrugMoleActiveIngredientculeAlias ;

		
	}
	
	public Reaction getCurrentReactionObject()
	{
		return currentReactionObject ;
	}
	
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, ParseException, DatatypeConfigurationException, NamingException
	{
		long currentReactionKey = ResultSetWrapper.getLong(rs, reactionAlias , "_rctnKey" ) ;
		if( currentReactionKey <= 0 )
			return currentReactionKey;
		
		if( reactionHashSet.contains(currentReactionKey) == false ) 
		{
			currentReactionObject = null ;
			traverseDrug = null ;
			traverseDrug = new TraverseDrug( reactionDrugAlias , reactionDrugManufacturerAlias ,  reactionDrugManufacturerRecallAlias , reactionDrugDosageFormAlias ,reactionDrugColorAlias
					, reactionDrugMoleculeAlias , reactionDrugMoleActiveIngredientculeAlias);
			
			drugHashSet.clear();
			
			Reaction reaction = PopulateJAXB.populateReaction(rs, reactionAlias);
			if( reaction != null )
			{
				currentReactionObject = reaction ;
				reactionHashSet.add(currentReactionKey);

				traverseDrug(rs);
				
			  }
			  
		}
		else
		{
			traverseDrug(rs);
			  
		}
		return currentReactionKey;
	}
	
	
	
	
	private void traverseDrug(ResultSet rs ) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		long drugKey = traverseDrug.traverse(rs);
		if( drugKey <= 0 )
			return ;
		if( drugHashSet.contains(drugKey) == false  )
		{
			currentReactionObject.getDrug().add(traverseDrug.getCurrentDrugObject());
			drugHashSet.add(drugKey);
		}
		
	}
	
	
	
	
}
