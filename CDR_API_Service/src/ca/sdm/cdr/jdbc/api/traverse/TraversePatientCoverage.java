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
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;


public class TraversePatientCoverage {


	private String patientCoverageAlias;

	
	/* current Objects variables */ 
	InsuranceCoverage currentPatientCoverageObject = null;
	/* HashSet variables */ 

	Set <Long> patientCoverageHashSet = new HashSet<Long>();


	
	public TraversePatientCoverage(String patientCoverageAlias)
	{
		this.patientCoverageAlias = patientCoverageAlias ;
		
	}
	
	public InsuranceCoverage getCurrentPatientCoverageObject()
	{
		return currentPatientCoverageObject ;
	}
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentCoverageKey = ResultSetWrapper.getLong(rs, patientCoverageAlias , "_PTNTCVRGKEY" ) ;
		
		if( currentCoverageKey <= 0 )
			return currentCoverageKey;
		if( patientCoverageHashSet.contains(currentCoverageKey) == false ) 
		{
			
			InsuranceCoverage coverage = PopulateJAXB.populateCoverage(rs, patientCoverageAlias);
			if( coverage  != null )
			{
				currentPatientCoverageObject = coverage  ; 
	
			}
			  
		}
		else
		{
		}
		return currentCoverageKey;

	}
	
}
