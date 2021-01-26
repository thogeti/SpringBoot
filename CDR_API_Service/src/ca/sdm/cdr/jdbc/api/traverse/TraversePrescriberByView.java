package ca.sdm.cdr.jdbc.api.traverse;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;

public class TraversePrescriberByView extends TraversePersonRoleByView {

Prescriber prescriber = null ;
	
	public TraversePrescriberByView(PersonRoleType roleType , String personRoleViewAlias , String contactMethodViewAlias ,  String prfsnlRegAlias)
	{
		super(roleType , personRoleViewAlias , contactMethodViewAlias ,  prfsnlRegAlias);
	}

	@Override
	public void setCurrentPersonRoleObject(ResultSet rs) throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		prescriber = PopulateJAXB.populatePrescriberFromView(rs, personRoleViewAlias);
	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)prescriber;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return prescriber.getPerson();
	}
/*
	@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		return prescriber.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		prescriber = null;
	}

	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		prescriber.getProfessionalRegistration().add(currentProfRegistrationObj);
	}
	
	public Prescriber getPrescriber()
	{
		return prescriber;
	}

}
