package ca.sdm.cdr.jdbc.api.traverse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class TraverseSupervisor extends TraversePersonRole {

	Supervisor supervisor = null ;
	
	public TraverseSupervisor(PersonRoleType roleType , String personRoleAlias , String personAlias , String contactMethodAlias ,  String contactMethodEmailAlias ,
			String contactMethodAddressAlias , String contactMethodTelcomAlias , String prfsnlRegAlias)
	{
		super(roleType , personRoleAlias , personAlias , contactMethodAlias ,  contactMethodEmailAlias ,
			contactMethodAddressAlias , contactMethodTelcomAlias , prfsnlRegAlias);
	}

	@Override
	public void setCurrentPersonRoleObject(ResultSet rs, String personAlias, String personRoleAlias)
			throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		supervisor = PopulateJAXB.populateSupervisor(rs, personAlias, personRoleAlias);
	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)supervisor;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return supervisor.getPerson();
	}
/*
	@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		return supervisor.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		supervisor = null;
	}

	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		supervisor.getProfessionalRegistration().add(currentProfRegistrationObj);
	}
	
	public Supervisor getSupervisor()
	{
		return supervisor;
	}
}
