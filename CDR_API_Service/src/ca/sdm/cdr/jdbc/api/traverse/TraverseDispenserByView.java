package ca.sdm.cdr.jdbc.api.traverse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;

public class TraverseDispenserByView extends TraversePersonRoleByView {

	Dispenser  dispenser  = null;
	
	public TraverseDispenserByView(PersonRoleType roleType , String personRoleViewAlias , String contactMethodViewAlias ,  String prfsnlRegAlias)
	{
		super(roleType , personRoleViewAlias , contactMethodViewAlias ,  prfsnlRegAlias);
	}
	@Override
	public void setCurrentPersonRoleObject(ResultSet rs)
			throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		dispenser = PopulateJAXB.populateDispenserFromView(rs, personRoleViewAlias );

	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)dispenser;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return dispenser.getPerson();
	}
/*
	@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		return dispenser.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		dispenser = null;
	}
	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		dispenser.getProfessionalRegistration().add(currentProfRegistrationObj);
	}
	
	public Dispenser getDispenser()
	{
		return dispenser;
	}
}
