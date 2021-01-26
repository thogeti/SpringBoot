
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This is a report of an adverse reaction of a patient resulting from a causative agent such as drug, prescription (Rx) or immunization. 
 * 
 * 
 * We define an adverse drug reaction as "an appreciably harmful or unpleasant reaction, resulting from an intervention related to the use of a medicinal product, which predicts hazard from future administration and warrants prevention or specific treatment, or alteration of the dosage regimen, or withdrawal of the product." 
 * 
 * Such reactions are currently reported by use of WHO's Adverse Reaction Terminology, which will eventually become a subset of the International Classification of Diseases. 
 * 
 * Adverse drug reactions are classified into six types (with mnemonics): 
 * dose-related (Augmented), 
 * non-dose-related (Bizarre),
 * dose-related and time-related (Chronic), 
 * time-related (Delayed), 
 * withdrawal (End of use), 
 * and failure of therapy (Failure).
 * 
 * Timing, the pattern of illness, the results of investigations, and rechallenge can help attribute causality to a suspected adverse drug reaction. Management includes withdrawal of the drug if possible and specific treatment of its effects. Suspected adverse drug reactions should be reported. Surveillance methods can detect reactions and prove associations.
 * 
 * <p>Java class for AdverseDrugReaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdverseDrugReaction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="updateTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="descriptionEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="occuranceStartTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="reaction" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Reaction" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reporter" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Reporter" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="serviceLocation" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Location" minOccurs="0"/&gt;
 *         &lt;element name="updateRecorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="supervisor" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Supervisor" minOccurs="0"/&gt;
 *         &lt;element name="prescriber" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescriber" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdverseDrugReaction", propOrder = {
    "consumerId",
    "producerId",
    "createTimestamp",
    "updateTimestamp",
    "descriptionEnglish",
    "occuranceStartTimestamp",
    "reaction",
    "note",
    "reporter",
    "recorder",
    "serviceLocation",
    "updateRecorder",
    "supervisor",
    "prescriber"
})
public class AdverseDrugReaction {

    protected String consumerId;
    protected String producerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;
    protected String descriptionEnglish;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar occuranceStartTimestamp;
    protected Reaction reaction;
    protected List<Note> note;
    protected Reporter reporter;
    protected Recorder recorder;
    protected Location serviceLocation;
    protected Recorder updateRecorder;
    protected Supervisor supervisor;
    protected Prescriber prescriber;

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
     * Gets the value of the occuranceStartTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOccuranceStartTimestamp() {
        return occuranceStartTimestamp;
    }

    /**
     * Sets the value of the occuranceStartTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOccuranceStartTimestamp(XMLGregorianCalendar value) {
        this.occuranceStartTimestamp = value;
    }

    /**
     * Gets the value of the reaction property.
     * 
     * @return
     *     possible object is
     *     {@link Reaction }
     *     
     */
    public Reaction getReaction() {
        return reaction;
    }

    /**
     * Sets the value of the reaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reaction }
     *     
     */
    public void setReaction(Reaction value) {
        this.reaction = value;
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
     * Gets the value of the prescriber property.
     * 
     * @return
     *     possible object is
     *     {@link Prescriber }
     *     
     */
    public Prescriber getPrescriber() {
        return prescriber;
    }

    /**
     * Sets the value of the prescriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescriber }
     *     
     */
    public void setPrescriber(Prescriber value) {
        this.prescriber = value;
    }

}
