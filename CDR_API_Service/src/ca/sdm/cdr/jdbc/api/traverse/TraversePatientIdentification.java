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
import ca.shoppersdrugmart.rxhb.ehealth.PatientIdentification;

public class TraversePatientIdentification {


	private String patientIdentificationAlias;

	
	/* current Objects variables */ 
	PatientIdentification currentPatientIdentificationObject = null;
	/* HashSet variables */ 

	Set <Long> patientIdentificationHashSet = new HashSet<Long>();


	
	public TraversePatientIdentification(String patientIdentificationAlias)
	{
		this.patientIdentificationAlias = patientIdentificationAlias ;
		
	}
	
	public PatientIdentification getCurrentPatientIdentificationObject()
	{
		return currentPatientIdentificationObject ;
	}
	
	public Long traverse(ResultSet rs) throws SQLException, CDRInternalException, IOException, DatatypeConfigurationException, NamingException, ParseException
	{
		long currentPatientIdentificationKey = ResultSetWrapper.getLong(rs, patientIdentificationAlias , "_PTNTIDKEY" ) ;
		
		if( currentPatientIdentificationKey <= 0 )
			return currentPatientIdentificationKey;
		if( patientIdentificationHashSet.contains(currentPatientIdentificationKey) == false ) 
		{
			
			PatientIdentification patientIdentification = PopulateJAXB.populatePatientIdentification(rs , patientIdentificationAlias  );
			if( patientIdentification  != null )
			{
				currentPatientIdentificationObject = patientIdentification  ; 
	
			}
			  
		}
		else
		{
		}
		return currentPatientIdentificationKey;

	}
}
