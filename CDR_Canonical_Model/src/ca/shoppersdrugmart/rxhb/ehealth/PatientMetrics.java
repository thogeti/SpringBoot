
package ca.shoppersdrugmart.rxhb.ehealth;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PatientMetrics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientMetrics"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vitalsDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="bloodGlucose" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="heightMeasurementFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="weightMeasurementFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="bloodGlucoseAutomaticMeasurementFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="heartRate" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="respiratoryRate" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="bodyTemperature" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="systolicBloodPressure" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="diastolicBloodPressure" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="heightDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="weightDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="patientWeightUOMCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}MetricsUnitOfMeasure" minOccurs="0"/&gt;
 *         &lt;element name="patientHeightUOMCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}MetricsUnitOfMeasure" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientMetrics", propOrder = {
    "vitalsDate",
    "height",
    "weight",
    "bloodGlucose",
    "heightMeasurementFlag",
    "weightMeasurementFlag",
    "bloodGlucoseAutomaticMeasurementFlag",
    "heartRate",
    "respiratoryRate",
    "bodyTemperature",
    "systolicBloodPressure",
    "diastolicBloodPressure",
    "consumerId",
    "producerId",
    "note",
    "heightDate",
    "weightDate",
    "patientWeightUOMCode",
    "patientHeightUOMCode"
})
public class PatientMetrics {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vitalsDate;
    protected BigDecimal height;
    protected BigDecimal weight;
    protected BigDecimal bloodGlucose;
    @XmlElement(defaultValue = "false")
    protected Boolean heightMeasurementFlag;
    @XmlElement(defaultValue = "false")
    protected Boolean weightMeasurementFlag;
    @XmlElement(defaultValue = "false")
    protected Boolean bloodGlucoseAutomaticMeasurementFlag;
    protected BigInteger heartRate;
    protected BigInteger respiratoryRate;
    protected BigDecimal bodyTemperature;
    protected BigInteger systolicBloodPressure;
    protected BigInteger diastolicBloodPressure;
    protected String consumerId;
    protected String producerId;
    protected List<Note> note;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar heightDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar weightDate;
    @XmlSchemaType(name = "string")
    protected MetricsUnitOfMeasure patientWeightUOMCode;
    @XmlSchemaType(name = "string")
    protected MetricsUnitOfMeasure patientHeightUOMCode;

    /**
     * Gets the value of the vitalsDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVitalsDate() {
        return vitalsDate;
    }

    /**
     * Sets the value of the vitalsDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVitalsDate(XMLGregorianCalendar value) {
        this.vitalsDate = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHeight(BigDecimal value) {
        this.height = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

    /**
     * Gets the value of the bloodGlucose property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBloodGlucose() {
        return bloodGlucose;
    }

    /**
     * Sets the value of the bloodGlucose property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBloodGlucose(BigDecimal value) {
        this.bloodGlucose = value;
    }

    /**
     * Gets the value of the heightMeasurementFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHeightMeasurementFlag() {
        return heightMeasurementFlag;
    }

    /**
     * Sets the value of the heightMeasurementFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHeightMeasurementFlag(Boolean value) {
        this.heightMeasurementFlag = value;
    }

    /**
     * Gets the value of the weightMeasurementFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWeightMeasurementFlag() {
        return weightMeasurementFlag;
    }

    /**
     * Sets the value of the weightMeasurementFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWeightMeasurementFlag(Boolean value) {
        this.weightMeasurementFlag = value;
    }

    /**
     * Gets the value of the bloodGlucoseAutomaticMeasurementFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloodGlucoseAutomaticMeasurementFlag() {
        return bloodGlucoseAutomaticMeasurementFlag;
    }

    /**
     * Sets the value of the bloodGlucoseAutomaticMeasurementFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloodGlucoseAutomaticMeasurementFlag(Boolean value) {
        this.bloodGlucoseAutomaticMeasurementFlag = value;
    }

    /**
     * Gets the value of the heartRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeartRate() {
        return heartRate;
    }

    /**
     * Sets the value of the heartRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeartRate(BigInteger value) {
        this.heartRate = value;
    }

    /**
     * Gets the value of the respiratoryRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRespiratoryRate() {
        return respiratoryRate;
    }

    /**
     * Sets the value of the respiratoryRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRespiratoryRate(BigInteger value) {
        this.respiratoryRate = value;
    }

    /**
     * Gets the value of the bodyTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBodyTemperature() {
        return bodyTemperature;
    }

    /**
     * Sets the value of the bodyTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBodyTemperature(BigDecimal value) {
        this.bodyTemperature = value;
    }

    /**
     * Gets the value of the systolicBloodPressure property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    /**
     * Sets the value of the systolicBloodPressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSystolicBloodPressure(BigInteger value) {
        this.systolicBloodPressure = value;
    }

    /**
     * Gets the value of the diastolicBloodPressure property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    /**
     * Sets the value of the diastolicBloodPressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDiastolicBloodPressure(BigInteger value) {
        this.diastolicBloodPressure = value;
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
     * Gets the value of the heightDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHeightDate() {
        return heightDate;
    }

    /**
     * Sets the value of the heightDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHeightDate(XMLGregorianCalendar value) {
        this.heightDate = value;
    }

    /**
     * Gets the value of the weightDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWeightDate() {
        return weightDate;
    }

    /**
     * Sets the value of the weightDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWeightDate(XMLGregorianCalendar value) {
        this.weightDate = value;
    }

    /**
     * Gets the value of the patientWeightUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link MetricsUnitOfMeasure }
     *     
     */
    public MetricsUnitOfMeasure getPatientWeightUOMCode() {
        return patientWeightUOMCode;
    }

    /**
     * Sets the value of the patientWeightUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricsUnitOfMeasure }
     *     
     */
    public void setPatientWeightUOMCode(MetricsUnitOfMeasure value) {
        this.patientWeightUOMCode = value;
    }

    /**
     * Gets the value of the patientHeightUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link MetricsUnitOfMeasure }
     *     
     */
    public MetricsUnitOfMeasure getPatientHeightUOMCode() {
        return patientHeightUOMCode;
    }

    /**
     * Sets the value of the patientHeightUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricsUnitOfMeasure }
     *     
     */
    public void setPatientHeightUOMCode(MetricsUnitOfMeasure value) {
        this.patientHeightUOMCode = value;
    }

}
