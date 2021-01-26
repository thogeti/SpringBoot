
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Allergy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Allergy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allergyActiveFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="intoleranceFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descriptionEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergyStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="reportedDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="updateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updateReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergyTest" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}AllergyTest" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reaction" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Reaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="linkedAllergy" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Allergy" minOccurs="0"/>
 *         &lt;element name="recorder" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Recorder" minOccurs="0"/>
 *         &lt;element name="allergySeverityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergyConfirmationStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drug" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Drug" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Note" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reporter" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Reporter" minOccurs="0"/>
 *         &lt;element name="serviceLocation" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Location" minOccurs="0"/>
 *         &lt;element name="nonDrugAllergen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateRecorder" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Recorder" minOccurs="0"/>
 *         &lt;element name="supervisor" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Supervisor" minOccurs="0"/>
 *         &lt;element name="statusChangeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusChangeReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenser" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Dispenser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Allergy", propOrder = {
    "allergyActiveFlag",
    "intoleranceFlag",
    "consumerId",
    "createTimestamp",
    "descriptionEnglish",
    "producerId",
    "descriptionFrench",
    "allergyType",
    "allergen",
    "allergyStartDate",
    "reportedDate",
    "updateTimestamp",
    "updateReason",
    "allergyTest",
    "reaction",
    "linkedAllergy",
    "recorder",
    "allergySeverityCode",
    "allergyConfirmationStatusCode",
    "drug",
    "note",
    "reporter",
    "serviceLocation",
    "nonDrugAllergen",
    "updateRecorder",
    "supervisor",
    "statusChangeType",
    "statusChangeReason",
    "dispenser"
})
public class Allergy {

    @XmlElement(defaultValue = "false")
    protected Boolean allergyActiveFlag;
    @XmlElement(defaultValue = "false")
    protected Boolean intoleranceFlag;
    protected String consumerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected String descriptionEnglish;
    protected String producerId;
    protected String descriptionFrench;
    protected String allergyType;
    protected String allergen;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar allergyStartDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar reportedDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;
    protected String updateReason;
    @XmlElement(nillable = true)
    protected List<AllergyTest> allergyTest;
    @XmlElement(nillable = true)
    protected List<Reaction> reaction;
    protected Allergy linkedAllergy;
    protected Recorder recorder;
    protected String allergySeverityCode;
    protected String allergyConfirmationStatusCode;
    protected Drug drug;
    @XmlElement(nillable = true)
    protected List<Note> note;
    protected Reporter reporter;
    protected Location serviceLocation;
    protected String nonDrugAllergen;
    protected Recorder updateRecorder;
    protected Supervisor supervisor;
    protected String statusChangeType;
    protected String statusChangeReason;
    protected Dispenser dispenser;

    /**
     * Gets the value of the allergyActiveFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllergyActiveFlag() {
        return allergyActiveFlag;
    }

    /**
     * Sets the value of the allergyActiveFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllergyActiveFlag(Boolean value) {
        this.allergyActiveFlag = value;
    }

    /**
     * Gets the value of the intoleranceFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntoleranceFlag() {
        return intoleranceFlag;
    }

    /**
     * Sets the value of the intoleranceFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntoleranceFlag(Boolean value) {
        this.intoleranceFlag = value;
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
     * Gets the value of the descriptionEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionEnglish() {
        return descriptionEnglish;
    }

    /**
     * Sets the value of the descriptionEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionEnglish(String value) {
        this.descriptionEnglish = value;
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
     * Gets the value of the descriptionFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionFrench() {
        return descriptionFrench;
    }

    /**
     * Sets the value of the descriptionFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionFrench(String value) {
        this.descriptionFrench = value;
    }

    /**
     * Gets the value of the allergyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyType() {
        return allergyType;
    }

    /**
     * Sets the value of the allergyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyType(String value) {
        this.allergyType = value;
    }

    /**
     * Gets the value of the allergen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergen() {
        return allergen;
    }

    /**
     * Sets the value of the allergen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergen(String value) {
        this.allergen = value;
    }

    /**
     * Gets the value of the allergyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllergyStartDate() {
        return allergyStartDate;
    }

    /**
     * Sets the value of the allergyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllergyStartDate(XMLGregorianCalendar value) {
        this.allergyStartDate = value;
    }

    /**
     * Gets the value of the reportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReportedDate() {
        return reportedDate;
    }

    /**
     * Sets the value of the reportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReportedDate(XMLGregorianCalendar value) {
        this.reportedDate = value;
    }

    /**
     * Gets the value of the updateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * Sets the value of the updateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimestamp(XMLGregorianCalendar value) {
        this.updateTimestamp = value;
    }

    /**
     * Gets the value of the updateReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateReason() {
        return updateReason;
    }

    /**
     * Sets the value of the updateReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateReason(String value) {
        this.updateReason = value;
    }

    /**
     * Gets the value of the allergyTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allergyTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllergyTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AllergyTest }
     * 
     * 
     */
    public List<AllergyTest> getAllergyTest() {
        if (allergyTest == null) {
            allergyTest = new ArrayList<AllergyTest>();
        }
        return this.allergyTest;
    }

    /**
     * Gets the value of the reaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reaction }
     * 
     * 
     */
    public List<Reaction> getReaction() {
        if (reaction == null) {
            reaction = new ArrayList<Reaction>();
        }
        return this.reaction;
    }

    /**
     * Gets the value of the linkedAllergy property.
     * 
     * @return
     *     possible object is
     *     {@link Allergy }
     *     
     */
    public Allergy getLinkedAllergy() {
        return linkedAllergy;
    }

    /**
     * Sets the value of the linkedAllergy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Allergy }
     *     
     */
    public void setLinkedAllergy(Allergy value) {
        this.linkedAllergy = value;
    }

    /**
     * Gets the value of the recorder property.
     * 
     * @return
     *     possible object is
     *     {@link Recorder }
     *     
     */
    public Recorder getRecorder() {
        return recorder;
    }

    /**
     * Sets the value of the recorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recorder }
     *     
     */
    public void setRecorder(Recorder value) {
        this.recorder = value;
    }

    /**
     * Gets the value of the allergySeverityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergySeverityCode() {
        return allergySeverityCode;
    }

    /**
     * Sets the value of the allergySeverityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergySeverityCode(String value) {
        this.allergySeverityCode = value;
    }

    /**
     * Gets the value of the allergyConfirmationStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyConfirmationStatusCode() {
        return allergyConfirmationStatusCode;
    }

    /**
     * Sets the value of the allergyConfirmationStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyConfirmationStatusCode(String value) {
        this.allergyConfirmationStatusCode = value;
    }

    /**
     * Gets the value of the drug property.
     * 
     * @return
     *     possible object is
     *     {@link Drug }
     *     
     */
    public Drug getDrug() {
        return drug;
    }

    /**
     * Sets the value of the drug property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drug }
     *     
     */
    public void setDrug(Drug value) {
        this.drug = value;
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
     * Gets the value of the reporter property.
     * 
     * @return
     *     possible object is
     *     {@link Reporter }
     *     
     */
    public Reporter getReporter() {
        return reporter;
    }

    /**
     * Sets the value of the reporter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reporter }
     *     
     */
    public void setReporter(Reporter value) {
        this.reporter = value;
    }

    /**
     * Gets the value of the serviceLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getServiceLocation() {
        return serviceLocation;
    }

    /**
     * Sets the value of the serviceLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setServiceLocation(Location value) {
        this.serviceLocation = value;
    }

    /**
     * Gets the value of the nonDrugAllergen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonDrugAllergen() {
        return nonDrugAllergen;
    }

    /**
     * Sets the value of the nonDrugAllergen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonDrugAllergen(String value) {
        this.nonDrugAllergen = value;
    }

    /**
     * Gets the value of the updateRecorder property.
     * 
     * @return
     *     possible object is
     *     {@link Recorder }
     *     
     */
    public Recorder getUpdateRecorder() {
        return updateRecorder;
    }

    /**
     * Sets the value of the updateRecorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recorder }
     *     
     */
    public void setUpdateRecorder(Recorder value) {
        this.updateRecorder = value;
    }

    /**
     * Gets the value of the supervisor property.
     * 
     * @return
     *     possible object is
     *     {@link Supervisor }
     *     
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the value of the supervisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Supervisor }
     *     
     */
    public void setSupervisor(Supervisor value) {
        this.supervisor = value;
    }

    /**
     * Gets the value of the statusChangeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusChangeType() {
        return statusChangeType;
    }

    /**
     * Sets the value of the statusChangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusChangeType(String value) {
        this.statusChangeType = value;
    }

    /**
     * Gets the value of the statusChangeReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusChangeReason() {
        return statusChangeReason;
    }

    /**
     * Sets the value of the statusChangeReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusChangeReason(String value) {
        this.statusChangeReason = value;
    }

    /**
     * Gets the value of the dispenser property.
     * 
     * @return
     *     possible object is
     *     {@link Dispenser }
     *     
     */
    public Dispenser getDispenser() {
        return dispenser;
    }

    /**
     * Sets the value of the dispenser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispenser }
     *     
     */
    public void setDispenser(Dispenser value) {
        this.dispenser = value;
    }

}
