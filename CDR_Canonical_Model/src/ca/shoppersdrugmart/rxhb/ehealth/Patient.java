
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.drx.cse.Preference;


/**
 * The individual who has been prescribed a particular drug or drug.
 * 
 * <p>Java class for Patient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Patient"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="deceaseddate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isactive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="noKnownAllergyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="person" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Person" minOccurs="0"/&gt;
 *         &lt;element name="allergy" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Allergy" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="genderTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientGender" minOccurs="0"/&gt;
 *         &lt;element name="groupmembership" type="{http://shoppersdrugmart.ca/RxHB/eHealth}GroupMembership" minOccurs="0"/&gt;
 *         &lt;element name="patientrefillreminder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}OptInOptions" minOccurs="0"/&gt;
 *         &lt;element name="patienttype" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientType" minOccurs="0"/&gt;
 *         &lt;element name="consent" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Consent" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="adverseDrugReaction" type="{http://shoppersdrugmart.ca/RxHB/eHealth}AdverseDrugReaction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="patientMetrics" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientMetrics" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="patientIdentification" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientIdentification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="patientMedicalCondition" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientMedicalCondition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="patientObservation" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientObservation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="InsuranceCoverage" type="{http://shoppersdrugmart.ca/RxHB/eHealth}InsuranceCoverage" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="associationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="preference" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}Preference" minOccurs="0"/&gt;
 *         &lt;element name="store" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Store" minOccurs="0"/&gt;
 *         &lt;element name="verificationChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="languageCorrespondence" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Language" minOccurs="0"/&gt;
 *         &lt;element name="sigLanguage" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Language" minOccurs="0"/&gt;
 *         &lt;element name="monographLanguage" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Language" minOccurs="0"/&gt;
 *         &lt;element name="primaryPrescriber" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescriber" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Patient", propOrder = {
    "dateOfBirth",
    "deceaseddate",
    "consumerId",
    "producerId",
    "isactive",
    "noKnownAllergyFlag",
    "createTimestamp",
    "lastUpdateTimestamp",
    "person",
    "allergy",
    "genderTypeCode",
    "groupmembership",
    "patientrefillreminder",
    "patienttype",
    "consent",
    "note",
    "adverseDrugReaction",
    "patientMetrics",
    "patientIdentification",
    "patientMedicalCondition",
    "patientObservation",
    "insuranceCoverage",
    "mobileNumber",
    "associationType",
    "preference",
    "store",
    "verificationChannel",
    "languageCorrespondence",
    "sigLanguage",
    "monographLanguage",
    "primaryPrescriber"
})
public class Patient {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfBirth;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deceaseddate;
    protected String consumerId;
    protected String producerId;
    @XmlElement(defaultValue = "false")
    protected Boolean isactive;
    protected Boolean noKnownAllergyFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;
    protected Person person;
    protected List<Allergy> allergy;
    @XmlSchemaType(name = "string")
    protected PatientGender genderTypeCode;
    @XmlSchemaType(name = "string")
    protected GroupMembership groupmembership;
    @XmlSchemaType(name = "string")
    protected OptInOptions patientrefillreminder;
    @XmlSchemaType(name = "string")
    protected PatientType patienttype;
    protected List<Consent> consent;
    protected List<Note> note;
    protected List<AdverseDrugReaction> adverseDrugReaction;
    protected List<PatientMetrics> patientMetrics;
    protected List<PatientIdentification> patientIdentification;
    protected List<PatientMedicalCondition> patientMedicalCondition;
    protected List<PatientObservation> patientObservation;
    @XmlElement(name = "InsuranceCoverage")
    protected List<InsuranceCoverage> insuranceCoverage;
    protected String mobileNumber;
    protected String associationType;
    protected Preference preference;
    protected Store store;
    protected PharmacyChannel verificationChannel;
    @XmlSchemaType(name = "string")
    protected Language languageCorrespondence;
    @XmlSchemaType(name = "string")
    protected Language sigLanguage;
    @XmlSchemaType(name = "string")
    protected Language monographLanguage;
    protected Prescriber primaryPrescriber;

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the deceaseddate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeceaseddate() {
        return deceaseddate;
    }

    /**
     * Sets the value of the deceaseddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeceaseddate(XMLGregorianCalendar value) {
        this.deceaseddate = value;
    }

    /**
     * Gets the value of the consumerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerId() {
        return consumerId;
    }

    /**
     * Sets the value of the consumerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerId(String value) {
        this.consumerId = value;
    }

    /**
     * Gets the value of the producerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerId() {
        return producerId;
    }

    /**
     * Sets the value of the producerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerId(String value) {
        this.producerId = value;
    }

    /**
     * Gets the value of the isactive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsactive() {
        return isactive;
    }

    /**
     * Sets the value of the isactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsactive(Boolean value) {
        this.isactive = value;
    }

    /**
     * Gets the value of the noKnownAllergyFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoKnownAllergyFlag() {
        return noKnownAllergyFlag;
    }

    /**
     * Sets the value of the noKnownAllergyFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoKnownAllergyFlag(Boolean value) {
        this.noKnownAllergyFlag = value;
    }

    /**
     * Gets the value of the createTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Sets the value of the createTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTimestamp(XMLGregorianCalendar value) {
        this.createTimestamp = value;
    }

    /**
     * Gets the value of the lastUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    /**
     * Sets the value of the lastUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateTimestamp(XMLGregorianCalendar value) {
        this.lastUpdateTimestamp = value;
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
     * Gets the value of the allergy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allergy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllergy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Allergy }
     * 
     * 
     */
    public List<Allergy> getAllergy() {
        if (allergy == null) {
            allergy = new ArrayList<Allergy>();
        }
        return this.allergy;
    }

    /**
     * Gets the value of the genderTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PatientGender }
     *     
     */
    public PatientGender getGenderTypeCode() {
        return genderTypeCode;
    }

    /**
     * Sets the value of the genderTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientGender }
     *     
     */
    public void setGenderTypeCode(PatientGender value) {
        this.genderTypeCode = value;
    }

    /**
     * Gets the value of the groupmembership property.
     * 
     * @return
     *     possible object is
     *     {@link GroupMembership }
     *     
     */
    public GroupMembership getGroupmembership() {
        return groupmembership;
    }

    /**
     * Sets the value of the groupmembership property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupMembership }
     *     
     */
    public void setGroupmembership(GroupMembership value) {
        this.groupmembership = value;
    }

    /**
     * Gets the value of the patientrefillreminder property.
     * 
     * @return
     *     possible object is
     *     {@link OptInOptions }
     *     
     */
    public OptInOptions getPatientrefillreminder() {
        return patientrefillreminder;
    }

    /**
     * Sets the value of the patientrefillreminder property.
     * 
     * @param value
     *     allowed object is
     *     {@link OptInOptions }
     *     
     */
    public void setPatientrefillreminder(OptInOptions value) {
        this.patientrefillreminder = value;
    }

    /**
     * Gets the value of the patienttype property.
     * 
     * @return
     *     possible object is
     *     {@link PatientType }
     *     
     */
    public PatientType getPatienttype() {
        return patienttype;
    }

    /**
     * Sets the value of the patienttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientType }
     *     
     */
    public void setPatienttype(PatientType value) {
        this.patienttype = value;
    }

    /**
     * Gets the value of the consent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Consent }
     * 
     * 
     */
    public List<Consent> getConsent() {
        if (consent == null) {
            consent = new ArrayList<Consent>();
        }
        return this.consent;
    }

    /**
     * Gets the value of the note property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the note property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * 
     * 
     */
    public List<Note> getNote() {
        if (note == null) {
            note = new ArrayList<Note>();
        }
        return this.note;
    }

    /**
     * Gets the value of the adverseDrugReaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adverseDrugReaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdverseDrugReaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdverseDrugReaction }
     * 
     * 
     */
    public List<AdverseDrugReaction> getAdverseDrugReaction() {
        if (adverseDrugReaction == null) {
            adverseDrugReaction = new ArrayList<AdverseDrugReaction>();
        }
        return this.adverseDrugReaction;
    }

    /**
     * Gets the value of the patientMetrics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientMetrics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientMetrics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientMetrics }
     * 
     * 
     */
    public List<PatientMetrics> getPatientMetrics() {
        if (patientMetrics == null) {
            patientMetrics = new ArrayList<PatientMetrics>();
        }
        return this.patientMetrics;
    }

    /**
     * Gets the value of the patientIdentification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientIdentification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientIdentification }
     * 
     * 
     */
    public List<PatientIdentification> getPatientIdentification() {
        if (patientIdentification == null) {
            patientIdentification = new ArrayList<PatientIdentification>();
        }
        return this.patientIdentification;
    }

    /**
     * Gets the value of the patientMedicalCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientMedicalCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientMedicalCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientMedicalCondition }
     * 
     * 
     */
    public List<PatientMedicalCondition> getPatientMedicalCondition() {
        if (patientMedicalCondition == null) {
            patientMedicalCondition = new ArrayList<PatientMedicalCondition>();
        }
        return this.patientMedicalCondition;
    }

    /**
     * Gets the value of the patientObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientObservation }
     * 
     * 
     */
    public List<PatientObservation> getPatientObservation() {
        if (patientObservation == null) {
            patientObservation = new ArrayList<PatientObservation>();
        }
        return this.patientObservation;
    }

    /**
     * Gets the value of the insuranceCoverage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insuranceCoverage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsuranceCoverage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsuranceCoverage }
     * 
     * 
     */
    public List<InsuranceCoverage> getInsuranceCoverage() {
        if (insuranceCoverage == null) {
            insuranceCoverage = new ArrayList<InsuranceCoverage>();
        }
        return this.insuranceCoverage;
    }

    /**
     * Gets the value of the mobileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the value of the mobileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNumber(String value) {
        this.mobileNumber = value;
    }

    /**
     * Gets the value of the associationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociationType() {
        return associationType;
    }

    /**
     * Sets the value of the associationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociationType(String value) {
        this.associationType = value;
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
     * Gets the value of the store property.
     * 
     * @return
     *     possible object is
     *     {@link Store }
     *     
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the value of the store property.
     * 
     * @param value
     *     allowed object is
     *     {@link Store }
     *     
     */
    public void setStore(Store value) {
        this.store = value;
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
     * Gets the value of the languageCorrespondence property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguageCorrespondence() {
        return languageCorrespondence;
    }

    /**
     * Sets the value of the languageCorrespondence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguageCorrespondence(Language value) {
        this.languageCorrespondence = value;
    }

    /**
     * Gets the value of the sigLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getSigLanguage() {
        return sigLanguage;
    }

    /**
     * Sets the value of the sigLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setSigLanguage(Language value) {
        this.sigLanguage = value;
    }

    /**
     * Gets the value of the monographLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getMonographLanguage() {
        return monographLanguage;
    }

    /**
     * Sets the value of the monographLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setMonographLanguage(Language value) {
        this.monographLanguage = value;
    }

    /**
     * Gets the value of the primaryPrescriber property.
     * 
     * @return
     *     possible object is
     *     {@link Prescriber }
     *     
     */
    public Prescriber getPrimaryPrescriber() {
        return primaryPrescriber;
    }

    /**
     * Sets the value of the primaryPrescriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescriber }
     *     
     */
    public void setPrimaryPrescriber(Prescriber value) {
        this.primaryPrescriber = value;
    }

}
