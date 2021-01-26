
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FaultDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="faultTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="correlationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="errorEventSource" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="errorClassification" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="resubmitFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultDetail", propOrder = {
    "faultTimestamp",
    "correlationID",
    "errorEventSource",
    "errorClassification",
    "resubmitFlag"
})
public class FaultDetail {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar faultTimestamp;
    protected String correlationID;
    @XmlElement(required = true)
    protected String errorEventSource;
    @XmlElement(required = true)
    protected String errorClassification;
    protected String resubmitFlag;

    /**
     * Gets the value of the faultTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFaultTimestamp() {
        return faultTimestamp;
    }

    /**
     * Sets the value of the faultTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFaultTimestamp(XMLGregorianCalendar value) {
        this.faultTimestamp = value;
    }

    /**
     * Gets the value of the correlationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * Sets the value of the correlationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * Gets the value of the errorEventSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorEventSource() {
        return errorEventSource;
    }

    /**
     * Sets the value of the errorEventSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorEventSource(String value) {
        this.errorEventSource = value;
    }

    /**
     * Gets the value of the errorClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorClassification() {
        return errorClassification;
    }

    /**
     * Sets the value of the errorClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorClassification(String value) {
        this.errorClassification = value;
    }

    /**
     * Gets the value of the resubmitFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResubmitFlag() {
        return resubmitFlag;
    }

    /**
     * Sets the value of the resubmitFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResubmitFlag(String value) {
        this.resubmitFlag = value;
    }

}
