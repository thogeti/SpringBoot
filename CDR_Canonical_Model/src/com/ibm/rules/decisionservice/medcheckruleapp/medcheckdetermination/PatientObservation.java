
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
 * <p>Java class for PatientObservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientObservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="observationValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationNumerator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationDenominator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Note" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="supervisor" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Supervisor" minOccurs="0"/>
 *         &lt;element name="recorder" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Recorder" minOccurs="0"/>
 *         &lt;element name="patientObservationTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Location" minOccurs="0"/>
 *         &lt;element name="observationMeasurementModifierCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientObservation", propOrder = {
    "createTimestamp",
    "observationValue",
    "observationTimestamp",
    "producerId",
    "observationNumerator",
    "observationDenominator",
    "consumerId",
    "note",
    "supervisor",
    "recorder",
    "patientObservationTypeCode",
    "location",
    "observationMeasurementModifierCode"
})
public class PatientObservation {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected String observationValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar observationTimestamp;
    protected String producerId;
    protected String observationNumerator;
    protected String observationDenominator;
    protected String consumerId;
    @XmlElement(nillable = true)
    protected List<Note> note;
    protected Supervisor supervisor;
    protected Recorder recorder;
    protected String patientObservationTypeCode;
    protected Location location;
    protected String observationMeasurementModifierCode;

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
     * Gets the value of the observationValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationValue() {
        return observationValue;
    }

    /**
     * Sets the value of the observationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationValue(String value) {
        this.observationValue = value;
    }

    /**
     * Gets the value of the observationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getObservationTimestamp() {
        return observationTimestamp;
    }

    /**
     * Sets the value of the observationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setObservationTimestamp(XMLGregorianCalendar value) {
        this.observationTimestamp = value;
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
     * Gets the value of the observationNumerator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationNumerator() {
        return observationNumerator;
    }

    /**
     * Sets the value of the observationNumerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationNumerator(String value) {
        this.observationNumerator = value;
    }

    /**
     * Gets the value of the observationDenominator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationDenominator() {
        return observationDenominator;
    }

    /**
     * Sets the value of the observationDenominator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationDenominator(String value) {
        this.observationDenominator = value;
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
     * Gets the value of the patientObservationTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientObservationTypeCode() {
        return patientObservationTypeCode;
    }

    /**
     * Sets the value of the patientObservationTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientObservationTypeCode(String value) {
        this.patientObservationTypeCode = value;
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
     * Gets the value of the observationMeasurementModifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationMeasurementModifierCode() {
        return observationMeasurementModifierCode;
    }

    /**
     * Sets the value of the observationMeasurementModifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationMeasurementModifierCode(String value) {
        this.observationMeasurementModifierCode = value;
    }

}
