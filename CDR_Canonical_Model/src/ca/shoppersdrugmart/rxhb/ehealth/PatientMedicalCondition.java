
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Describes a medical condition for a patient. A patient may have multiple conditions
 * 
 * <p>Java class for PatientMedicalCondition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientMedicalCondition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="conditionDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="conditionStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="conditionEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="chronicConditionFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="conditionCreateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="conditionActiveFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="updateReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="updateTimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="updateRecorderTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="conditionNote" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="conditionTypeDisplay" type="{http://shoppersdrugmart.ca/RxHB/eHealth}MedicalConditionTypeTypeDisplay" minOccurs="0"/&gt;
 *         &lt;element name="serviceLocation" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Location" minOccurs="0"/&gt;
 *         &lt;element name="reporter" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Reporter" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="supervisor" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Supervisor" minOccurs="0"/&gt;
 *         &lt;element name="updateReasonType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}StatusChangeReason" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="linkedCondition" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientMedicalCondition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reportedDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientMedicalCondition", propOrder = {
    "conditionDescription",
    "conditionStartDate",
    "conditionEndDate",
    "chronicConditionFlag",
    "conditionCreateTimestamp",
    "conditionActiveFlag",
    "updateReason",
    "producerId",
    "consumerId",
    "updateTimeStamp",
    "updateRecorderTimestamp",
    "conditionNote",
    "conditionTypeDisplay",
    "serviceLocation",
    "reporter",
    "recorder",
    "supervisor",
    "updateReasonType",
    "note",
    "linkedCondition",
    "reportedDate"
})
public class PatientMedicalCondition {

    protected String conditionDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar conditionStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar conditionEndDate;
    @XmlElement(defaultValue = "false")
    protected Boolean chronicConditionFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar conditionCreateTimestamp;
    @XmlElement(defaultValue = "false")
    protected boolean conditionActiveFlag;
    protected String updateReason;
    protected String producerId;
    protected String consumerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimeStamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateRecorderTimestamp;
    protected List<Note> conditionNote;
    protected MedicalConditionTypeTypeDisplay conditionTypeDisplay;
    protected Location serviceLocation;
    protected Reporter reporter;
    protected Recorder recorder;
    protected Supervisor supervisor;
    @XmlSchemaType(name = "string")
    protected StatusChangeReason updateReasonType;
    protected List<Note> note;
    protected List<PatientMedicalCondition> linkedCondition;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar reportedDate;

    /**
     * Gets the value of the conditionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionDescription() {
        return conditionDescription;
    }

    /**
     * Sets the value of the conditionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionDescription(String value) {
        this.conditionDescription = value;
    }

    /**
     * Gets the value of the conditionStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConditionStartDate() {
        return conditionStartDate;
    }

    /**
     * Sets the value of the conditionStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConditionStartDate(XMLGregorianCalendar value) {
        this.conditionStartDate = value;
    }

    /**
     * Gets the value of the conditionEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConditionEndDate() {
        return conditionEndDate;
    }

    /**
     * Sets the value of the conditionEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConditionEndDate(XMLGregorianCalendar value) {
        this.conditionEndDate = value;
    }

    /**
     * Gets the value of the chronicConditionFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChronicConditionFlag() {
        return chronicConditionFlag;
    }

    /**
     * Sets the value of the chronicConditionFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChronicConditionFlag(Boolean value) {
        this.chronicConditionFlag = value;
    }

    /**
     * Gets the value of the conditionCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConditionCreateTimestamp() {
        return conditionCreateTimestamp;
    }

    /**
     * Sets the value of the conditionCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConditionCreateTimestamp(XMLGregorianCalendar value) {
        this.conditionCreateTimestamp = value;
    }

    /**
     * Gets the value of the conditionActiveFlag property.
     * 
     */
    public boolean isConditionActiveFlag() {
        return conditionActiveFlag;
    }

    /**
     * Sets the value of the conditionActiveFlag property.
     * 
     */
    public void setConditionActiveFlag(boolean value) {
        this.conditionActiveFlag = value;
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
     * Gets the value of the updateTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    /**
     * Sets the value of the updateTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimeStamp(XMLGregorianCalendar value) {
        this.updateTimeStamp = value;
    }

    /**
     * Gets the value of the updateRecorderTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateRecorderTimestamp() {
        return updateRecorderTimestamp;
    }

    /**
     * Sets the value of the updateRecorderTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateRecorderTimestamp(XMLGregorianCalendar value) {
        this.updateRecorderTimestamp = value;
    }

    /**
     * Gets the value of the conditionNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditionNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditionNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * 
     * 
     */
    public List<Note> getConditionNote() {
        if (conditionNote == null) {
            conditionNote = new ArrayList<Note>();
        }
        return this.conditionNote;
    }

    /**
     * Gets the value of the conditionTypeDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link MedicalConditionTypeTypeDisplay }
     *     
     */
    public MedicalConditionTypeTypeDisplay getConditionTypeDisplay() {
        return conditionTypeDisplay;
    }

    /**
     * Sets the value of the conditionTypeDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedicalConditionTypeTypeDisplay }
     *     
     */
    public void setConditionTypeDisplay(MedicalConditionTypeTypeDisplay value) {
        this.conditionTypeDisplay = value;
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
     * Gets the value of the updateReasonType property.
     * 
     * @return
     *     possible object is
     *     {@link StatusChangeReason }
     *     
     */
    public StatusChangeReason getUpdateReasonType() {
        return updateReasonType;
    }

    /**
     * Sets the value of the updateReasonType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusChangeReason }
     *     
     */
    public void setUpdateReasonType(StatusChangeReason value) {
        this.updateReasonType = value;
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
     * Gets the value of the linkedCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkedCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkedCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientMedicalCondition }
     * 
     * 
     */
    public List<PatientMedicalCondition> getLinkedCondition() {
        if (linkedCondition == null) {
            linkedCondition = new ArrayList<PatientMedicalCondition>();
        }
        return this.linkedCondition;
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

}
