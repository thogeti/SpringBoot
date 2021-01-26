package ca.sdm.cdr.jdbc.api.traverse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;

public class TraverseMedicalPractitionerByView extends TraversePersonRoleByView {


	MedicalPractitioner medicalPractitioner = null;
	

	public TraverseMedicalPractitionerByView(PersonRoleType roleType , String personRoleViewAlias , String contactMethodViewAlias ,  String prfsnlRegAlias)
	{
		super(roleType , personRoleViewAlias , contactMethodViewAlias ,  prfsnlRegAlias);
	}
	
	@Override
	public void setCurrentPersonRoleObject(ResultSet rs) throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		medicalPractitioner = PopulateJAXB.populateMedicalPractitionerFromView(rs, personRoleViewAlias);
										   

	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)medicalPractitioner;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return medicalPractitioner.getPerson();
	}

	/*@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		return medicalPractitioner.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		medicalPractitioner = null;
	}

	public MedicalPractitioner getMedicalPractitioner()
	{
		return medicalPractitioner;
	}
	
	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		medicalPractitioner.setProfessionalRegistration(currentProfRegistrationObj);
	}

/*	@Override
	public void setParentPersonRoleObject() {
		// TODO Auto-generated method stub

	}
*/
}
