
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Note complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Note"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isPharmacist" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="updateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="noteEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="noteFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="noteTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}NoteType" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="noteCategory" type="{http://shoppersdrugmart.ca/RxHB/eHealth}NoteCategory" minOccurs="0"/&gt;
 *         &lt;element name="supervisor" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Supervisor" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Location" minOccurs="0"/&gt;
 *         &lt;element name="dispenser" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispenser" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Note", propOrder = {
    "isPharmacist",
    "updateTimestamp",
    "noteEnglish",
    "consumerId",
    "producerId",
    "noteFrench",
    "createTimestamp",
    "createUserId",
    "noteTypeCode",
    "recorder",
    "noteCategory",
    "supervisor",
    "location",
    "dispenser"
})
public class Note {

    @XmlElement(defaultValue = "false")
    protected Boolean isPharmacist;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;
    protected String noteEnglish;
    protected String consumerId;
    protected String producerId;
    protected String noteFrench;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected String createUserId;
    @XmlSchemaType(name = "string")
    protected NoteType noteTypeCode;
    protected Recorder recorder;
    @XmlSchemaType(name = "string")
    protected NoteCategory noteCategory;
    protected Supervisor supervisor;
    protected Location location;
    protected Dispenser dispenser;

    /**
     * Gets the value of the isPharmacist property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPharmacist() {
        return isPharmacist;
    }

    /**
     * Sets the value of the isPharmacist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPharmacist(Boolean value) {
        this.isPharmacist = value;
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
     * Gets the value of the noteEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteEnglish() {
        return noteEnglish;
    }

    /**
     * Sets the value of the noteEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteEnglish(String value) {
        this.noteEnglish = value;
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
     * Gets the value of the noteFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteFrench() {
        return noteFrench;
    }

    /**
     * Sets the value of the noteFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteFrench(String value) {
        this.noteFrench = value;
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
     * Gets the value of the createUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * Sets the value of the createUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUserId(String value) {
        this.createUserId = value;
    }

    /**
     * Gets the value of the noteTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link NoteType }
     *     
     */
    public NoteType getNoteTypeCode() {
        return noteTypeCode;
    }

    /**
     * Sets the value of the noteTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoteType }
     *     
     */
    public void setNoteTypeCode(NoteType value) {
        this.noteTypeCode = value;
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
     * Gets the value of the noteCategory property.
     * 
     * @return
     *     possible object is
     *     {@link NoteCategory }
     *     
     */
    public NoteCategory getNoteCategory() {
        return noteCategory;
    }

    /**
     * Sets the value of the noteCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoteCategory }
     *     
     */
    public void setNoteCategory(NoteCategory value) {
        this.noteCategory = value;
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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
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
