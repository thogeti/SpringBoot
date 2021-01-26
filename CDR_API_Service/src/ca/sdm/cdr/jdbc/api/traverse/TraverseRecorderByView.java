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

public class TraverseRecorderByView extends TraversePersonRoleByView {

	Recorder recorder = null;
	

	public TraverseRecorderByView(PersonRoleType roleType , String personRoleViewAlias , String contactMethodViewAlias ,  String prfsnlRegAlias)
	{
		super(roleType , personRoleViewAlias , contactMethodViewAlias ,  prfsnlRegAlias);
	}
	
	@Override
	public void setCurrentPersonRoleObject(ResultSet rs) throws CDRInternalException, SQLException {
		// TODO Auto-generated method stub
		recorder = PopulateJAXB.populateRecorderFromView(rs, personRoleViewAlias);

	}

	@Override
	public Object getCurrentPersonRoleObjects() {
		// TODO Auto-generated method stub
		return (Object)recorder;
	}

	@Override
	public Person getCurrentPresonRolePerson() {
		// TODO Auto-generated method stub
		return recorder.getPerson();
	}

	/*@Override
	public List<ProfessionalRegistration> getCurrentCurrentPresonRoleObjectProfessionalRegistration() {
		// TODO Auto-generated method stub
		return recorder.getProfessionalRegistration();
	}
*/
	@Override
	public void resetPersonRoleObject() {
		// TODO Auto-generated method stub

		recorder = null;
	}

	public Recorder getRecorder()
	{
		return recorder;
	}
	
	@Override
	public void  setCurrentPresonRoleObjectProfessionalRegistration(ProfessionalRegistration currentProfRegistrationObj)
	{
		recorder.getProfessionalRegistration().add(currentProfRegistrationObj);
	}

/*	@Override
	public void setParentPersonRoleObject() {
		// TODO Auto-generated method stub

	}
*/
}
