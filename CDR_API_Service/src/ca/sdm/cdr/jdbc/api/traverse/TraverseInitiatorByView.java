package ca.sdm.cdr.jdbc.api.traverse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.PersonRoleType;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;

public class TraverseInitiatorByView extends TraversePersonRoleByView {

	Initiator initiator = null;
	
	public TraverseInitiatorByView (PersonRoleType roleType , String personRoleViewAlias , String contactMethodViewAlias ,  String prfsnlRegAlias)
	{
		super(roleType , personRoleViewAlias , contactMethodViewAlias ,  prfsnlRegAlias);
	}
	@Override
	public void setCurrentPersonRoleObject(ResultSet rs)
			throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		initiator = PopulateJAXB.populateInitiatorFromView(rs, personRoleViewAlias);
	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)initiator;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return initiator.getPerson();
	}
/*
	@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		List<ProfessionalRegistration> profRegList =  new ArrayList<ProfessionalRegistration>(); 
		initiator.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		initiator = null;
	}

	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		initiator.setProfessionalRegistration(currentProfRegistrationObj);
	}
	
	public Initiator getInitiator()
	{
		return initiator;
	}
}
