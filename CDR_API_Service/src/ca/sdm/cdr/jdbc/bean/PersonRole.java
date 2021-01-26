package ca.sdm.cdr.jdbc.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomer;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Reporter;
import ca.shoppersdrugmart.rxhb.ehealth.ResponsiblePerson;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import javax.xml.datatype.XMLGregorianCalendar;

public class PersonRole {

	protected Person person;
	protected List<ProfessionalRegistration> professionalRegistration = new ArrayList<ProfessionalRegistration>();
	protected String consumerId;
	protected String producerId;
	protected boolean isActiveFlag;
	private CDREnumerations.PersonRoleType role;
	protected XMLGregorianCalendar payloadUpdateTimeStamp;
	protected Timestamp dbUpdateTimeStamp;
	protected Long personRoleKey = null; 
	private Object personRole;


	private void assignProfessionalRegistration(List<ProfessionalRegistration> prfregs)
	{
		java.util.Iterator<ProfessionalRegistration>  iterator = prfregs.iterator();
		while (iterator.hasNext()) { 
			ProfessionalRegistration current = iterator.next();
			if(current !=null)
			{
				professionalRegistration.add(current);
			}					
		}
	}
	public PersonRole(Recorder recorder) {
		this.person = recorder.getPerson();
//		this.professionalRegistration = recorder.getProfessionalRegistration();
		assignProfessionalRegistration(recorder.getProfessionalRegistration());
		this.consumerId = recorder.getConsumerId();
		this.producerId = recorder.getProducerId();
		this.setActiveFlag(recorder.isIsActiveFlag());
		this.role = CDREnumerations.PersonRoleType.RECORDER;
		personRole = recorder;
	}

	public PersonRole(Supervisor supervisor) {
		this.person = supervisor.getPerson();
//		this.professionalRegistration = supervisor.getProfessionalRegistration();
		assignProfessionalRegistration(supervisor.getProfessionalRegistration());
		this.consumerId = supervisor.getConsumerId();
		this.producerId = supervisor.getProducerId();
		this.setActiveFlag(supervisor.isIsActiveFlag());
		this.role = CDREnumerations.PersonRoleType.SUPERVISOR;
		personRole = supervisor;
	}

	public PersonRole(Dispenser dispenser) {
		this.person = dispenser.getPerson();
//		this.professionalRegistration = dispenser.getProfessionalRegistration();
		assignProfessionalRegistration(dispenser.getProfessionalRegistration());
		this.consumerId = dispenser.getConsumerId();
		this.producerId = dispenser.getProducerId();
		this.setActiveFlag(dispenser.isIsActiveFlag());
		this.role = CDREnumerations.PersonRoleType.DISPENSER;
		personRole = dispenser;

	}

	public PersonRole(Reporter reporter) {
		this.person = reporter.getPerson();
//		this.professionalRegistration = null;
		this.consumerId = reporter.getConsumerId();
		this.producerId = reporter.getProducerId();
		this.isActiveFlag = true;
		this.role = CDREnumerations.PersonRoleType.REPORTER;
		personRole = reporter;

	}

	public PersonRole(Prescriber prescriber) {
		this.person = prescriber.getPerson();
//		this.professionalRegistration = prescriber.getProfessionalRegistration();
		assignProfessionalRegistration(prescriber.getProfessionalRegistration());		
		this.consumerId = prescriber.getConsumerId();
		this.producerId = prescriber.getProducerId();
		this.setActiveFlag(prescriber.isIsActiveFlag());
		this.role = CDREnumerations.PersonRoleType.PRESCRIBER;
		if(this.person.getLastUpdateTimestamp()!=null) {
		this.payloadUpdateTimeStamp = this.person.getLastUpdateTimestamp();
		}
		this.dbUpdateTimeStamp=null;
		personRole = prescriber;

	}

	public PersonRole(ResponsiblePerson responsiblePerson) {
		this.person = responsiblePerson.getPerson();
//		this.professionalRegistration = responsiblePerson.getProfessionalRegistration();
		assignProfessionalRegistration(responsiblePerson.getProfessionalRegistration());		
		this.consumerId = responsiblePerson.getConsumerId();
		this.producerId = responsiblePerson.getProducerId();
		this.setActiveFlag(true);
		this.role = CDREnumerations.PersonRoleType.RESPONSIBLE;
		personRole = responsiblePerson;

	}

	public PersonRole(Initiator initiator) {
		this.person = initiator.getPerson();
		if (initiator.getProfessionalRegistration() != null)
			this.professionalRegistration.add(initiator.getProfessionalRegistration());
		this.consumerId = initiator.getConsumerId();
		this.producerId = initiator.getProducerId();
		this.setActiveFlag(true);
		this.role = CDREnumerations.PersonRoleType.INITIATOR;
		personRole = initiator;

	}

	public PersonRole(Patient patient) {
		this.person = patient.getPerson();
		this.consumerId = patient.getConsumerId();
		this.producerId = patient.getProducerId();
		if (patient.isIsactive() != null)
			this.setActiveFlag(patient.isIsactive().booleanValue());
		this.role = CDREnumerations.PersonRoleType.PATIENT;
	}

	public PersonRole(MedicalPractitioner medicalPractitioner) {
		this.person = medicalPractitioner.getPerson();
		if (medicalPractitioner.getProfessionalRegistration() != null)
			this.professionalRegistration.add(medicalPractitioner.getProfessionalRegistration());
		this.consumerId = medicalPractitioner.getConsumerId();
		this.producerId = medicalPractitioner.getProducerId();
		this.setActiveFlag(medicalPractitioner.isIsActive());
		this.role = CDREnumerations.PersonRoleType.MEDICALPRACTITIONER;
	}

	public PersonRole(CreateCustomer customer) {
		this.person = customer.getCustomer().getPerson();
		
		if(customer.getCustomer() != null) {
		this.consumerId = customer.getCustomer().getUserId()!=null ? customer.getCustomer().getUserId() : customer.getCustomer().getCustomerid();
		
		}
		if (CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equals(customer.getCustomer().getUserType().value()))
			this.role = CDREnumerations.PersonRoleType.PREREGCUSTOMER;
		else if (CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equals(customer.getCustomer().getUserType().value()))
			this.role = CDREnumerations.PersonRoleType.REGCUSTOMER;
		
		
	}
	
	public PersonRole(UpdateCustomer customer) {
		this.person = customer.getCustomer().getPerson();
		
		
		if (CDREnumerations.PersonRoleType.PREREGCUSTOMER.getRoleType().equals(customer.getCustomer().getUserType().value()))
			this.role = CDREnumerations.PersonRoleType.PREREGCUSTOMER;
		else if (CDREnumerations.PersonRoleType.REGCUSTOMER.getRoleType().equals(customer.getCustomer().getUserType().value()))
			this.role = CDREnumerations.PersonRoleType.REGCUSTOMER;
		
		
	}

	public CDREnumerations.PersonRoleType getRole() {
		return role;
	}

	public void setRole(CDREnumerations.PersonRoleType role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<ProfessionalRegistration> getProfessionalRegistration() {
		return professionalRegistration;
	}

	public void setProfessionalRegistration(List<ProfessionalRegistration> professionalRegistration) {
		this.professionalRegistration = professionalRegistration;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public boolean isActiveFlag() {
		return isActiveFlag;
	}

	public void setActiveFlag(boolean isActiveFlag) {
		this.isActiveFlag = isActiveFlag;
	}
	
	public Object getPersonRole() {
		return personRole;
	}
	public void setPersonRole(Object personRole) {
		this.personRole = personRole;
	}
	public XMLGregorianCalendar getPayloadUpdateTimeStamp() {
		return payloadUpdateTimeStamp;
	}
	public void setPayloadUpdateTimeStamp(XMLGregorianCalendar payloadUpdateTimeStamp) {
		this.payloadUpdateTimeStamp = payloadUpdateTimeStamp;
	}
	public Timestamp getDbUpdateTimeStamp() {
		return dbUpdateTimeStamp;
	}
	public void setDbUpdateTimeStamp(Timestamp dbUpdateTimeStamp) {
		this.dbUpdateTimeStamp = dbUpdateTimeStamp;
	}
	public Long getPersonRoleKey() {
		return personRoleKey;
	}
	public void setPersonRoleKey(Long personRoleKey) {
		this.personRoleKey = personRoleKey;
	}
}
