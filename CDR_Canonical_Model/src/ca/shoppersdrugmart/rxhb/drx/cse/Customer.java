
package ca.shoppersdrugmart.rxhb.drx.cse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.ehealth.Division;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Person;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;


/**
 * The customer information including the
 *                 preference information that will be passed from consumer channel.
 * 
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customerid" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="phonenumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="preference" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}Preference" minOccurs="0"/&gt;
 *         &lt;element name="patient" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Patient" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="language" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="userId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="userType" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}UserTypeEnum" minOccurs="0"/&gt;
 *         &lt;element name="person" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Person" minOccurs="0"/&gt;
 *         &lt;element name="optimumNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="creationTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="requestSource" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="deactivationReason" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="SourceChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="detailedNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Count" minOccurs="0"/&gt;
 *         &lt;element name="lastLoginTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="registrationReminderId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="succesfullLoginAttempt" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Count" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}AccountStatusEnum" minOccurs="0"/&gt;
 *         &lt;element name="version" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Count" minOccurs="0"/&gt;
 *         &lt;element name="patientVerification" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}PatientVerificationEnum" minOccurs="0"/&gt;
 *         &lt;element name="verificationChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="division" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Division" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "customerid",
    "email",
    "phonenumber",
    "preference",
    "patient",
    "language",
    "userId",
    "userType",
    "person",
    "optimumNumber",
    "creationTimestamp",
    "requestSource",
    "deactivationReason",
    "sourceChannel",
    "detailedNotification",
    "lastLoginTimestamp",
    "registrationReminderId",
    "succesfullLoginAttempt",
    "status",
    "version",
    "patientVerification",
    "verificationChannel",
    "division"
})
public class Customer {

    protected String customerid;
    protected String email;
    protected String phonenumber;
    protected Preference preference;
    protected List<Patient> patient;
    protected String language;
    protected String userId;
    @XmlSchemaType(name = "string")
    protected UserTypeEnum userType;
    protected Person person;
    protected String optimumNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTimestamp;
    protected String requestSource;
    protected String deactivationReason;
    @XmlElement(name = "SourceChannel")
    protected PharmacyChannel sourceChannel;
    protected Integer detailedNotification;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginTimestamp;
    protected String registrationReminderId;
    protected Integer succesfullLoginAttempt;
    @XmlSchemaType(name = "string")
    protected AccountStatusEnum status;
    protected Integer version;
    @XmlSchemaType(name = "string")
    protected PatientVerificationEnum patientVerification;
    protected PharmacyChannel verificationChannel;
    @XmlSchemaType(name = "string")
    protected Division division;

    /**
     * Gets the value of the customerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * Sets the value of the customerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerid(String value) {
        this.customerid = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the phonenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * Sets the value of the phonenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhonenumber(String value) {
        this.phonenumber = value;
    }

    /**
     * Gets the value of the preference property.
     * 
     * @return
     *     possible object is
     *     {@link Preference }
     *     
     */
    public Preference getPreference() {
        return preference;
    }

    /**
     * Sets the value of the preference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Preference }
     *     
     */
    public void setPreference(Preference value) {
        this.preference = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patient }
     * 
     * 
     */
    public List<Patient> getPatient() {
        if (patient == null) {
            patient = new ArrayList<Patient>();
        }
        return this.patient;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link UserTypeEnum }
     *     
     */
    public UserTypeEnum getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTypeEnum }
     *     
     */
    public void setUserType(UserTypeEnum value) {
        this.userType = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    /**
     * Gets the value of the optimumNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptimumNumber() {
        return optimumNumber;
    }

    /**
     * Sets the value of the optimumNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptimumNumber(String value) {
        this.optimumNumber = value;
    }

    /**
     * Gets the value of the creationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Sets the value of the creationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationTimestamp(XMLGregorianCalendar value) {
        this.creationTimestamp = value;
    }

    /**
     * Gets the value of the requestSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestSource() {
        return requestSource;
    }

    /**
     * Sets the value of the requestSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestSource(String value) {
        this.requestSource = value;
    }

    /**
     * Gets the value of the deactivationReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeactivationReason() {
        return deactivationReason;
    }

    /**
     * Sets the value of the deactivationReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeactivationReason(String value) {
        this.deactivationReason = value;
    }

    /**
     * Gets the value of the sourceChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getSourceChannel() {
        return sourceChannel;
    }

    /**
     * Sets the value of the sourceChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setSourceChannel(PharmacyChannel value) {
        this.sourceChannel = value;
    }

    /**
     * Gets the value of the detailedNotification property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDetailedNotification() {
        return detailedNotification;
    }

    /**
     * Sets the value of the detailedNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDetailedNotification(Integer value) {
        this.detailedNotification = value;
    }

    /**
     * Gets the value of the lastLoginTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    /**
     * Sets the value of the lastLoginTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLoginTimestamp(XMLGregorianCalendar value) {
        this.lastLoginTimestamp = value;
    }

    /**
     * Gets the value of the registrationReminderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationReminderId() {
        return registrationReminderId;
    }

    /**
     * Sets the value of the registrationReminderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationReminderId(String value) {
        this.registrationReminderId = value;
    }

    /**
     * Gets the value of the succesfullLoginAttempt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSuccesfullLoginAttempt() {
        return succesfullLoginAttempt;
    }

    /**
     * Sets the value of the succesfullLoginAttempt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSuccesfullLoginAttempt(Integer value) {
        this.succesfullLoginAttempt = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatusEnum }
     *     
     */
    public AccountStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatusEnum }
     *     
     */
    public void setStatus(AccountStatusEnum value) {
        this.status = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the patientVerification property.
     * 
     * @return
     *     possible object is
     *     {@link PatientVerificationEnum }
     *     
     */
    public PatientVerificationEnum getPatientVerification() {
        return patientVerification;
    }

    /**
     * Sets the value of the patientVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientVerificationEnum }
     *     
     */
    public void setPatientVerification(PatientVerificationEnum value) {
        this.patientVerification = value;
    }

    /**
     * Gets the value of the verificationChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getVerificationChannel() {
        return verificationChannel;
    }

    /**
     * Sets the value of the verificationChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setVerificationChannel(PharmacyChannel value) {
        this.verificationChannel = value;
    }

    /**
     * Gets the value of the division property.
     * 
     * @return
     *     possible object is
     *     {@link Division }
     *     
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param value
     *     allowed object is
     *     {@link Division }
     *     
     */
    public void setDivision(Division value) {
        this.division = value;
    }

}
