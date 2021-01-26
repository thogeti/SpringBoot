
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessExceptionCorrelation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessExceptionCorrelation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="exceptionId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityUUID" minOccurs="0"/&gt;
 *         &lt;element name="details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="interactionIssueDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument" minOccurs="0"/&gt;
 *         &lt;element name="managedIssueDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument" minOccurs="0"/&gt;
 *         &lt;element name="instantiationDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument" minOccurs="0"/&gt;
 *         &lt;element name="priority" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Priority" minOccurs="0"/&gt;
 *         &lt;element name="severity" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Severity" minOccurs="0"/&gt;
 *         &lt;element name="exceptionType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ExceptionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessExceptionCorrelation", propOrder = {
    "exceptionId",
    "details",
    "interactionIssueDocument",
    "managedIssueDocument",
    "instantiationDocument",
    "priority",
    "severity",
    "exceptionType"
})
public class BusinessExceptionCorrelation {

    protected String exceptionId;
    protected String details;
    protected ManagedIssueDocument interactionIssueDocument;
    protected ManagedIssueDocument managedIssueDocument;
    protected ManagedIssueDocument instantiationDocument;
    @XmlSchemaType(name = "string")
    protected Priority priority;
    @XmlSchemaType(name = "string")
    protected Severity severity;
    protected String exceptionType;

    /**
     * Gets the value of the exceptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionId() {
        return exceptionId;
    }

    /**
     * Sets the value of the exceptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionId(String value) {
        this.exceptionId = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

    /**
     * Gets the value of the interactionIssueDocument property.
     * 
     * @return
     *     possible object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public ManagedIssueDocument getInteractionIssueDocument() {
        return interactionIssueDocument;
    }

    /**
     * Sets the value of the interactionIssueDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public void setInteractionIssueDocument(ManagedIssueDocument value) {
        this.interactionIssueDocument = value;
    }

    /**
     * Gets the value of the managedIssueDocument property.
     * 
     * @return
     *     possible object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public ManagedIssueDocument getManagedIssueDocument() {
        return managedIssueDocument;
    }

    /**
     * Sets the value of the managedIssueDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public void setManagedIssueDocument(ManagedIssueDocument value) {
        this.managedIssueDocument = value;
    }

    /**
     * Gets the value of the instantiationDocument property.
     * 
     * @return
     *     possible object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public ManagedIssueDocument getInstantiationDocument() {
        return instantiationDocument;
    }

    /**
     * Sets the value of the instantiationDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagedIssueDocument }
     *     
     */
    public void setInstantiationDocument(ManagedIssueDocument value) {
        this.instantiationDocument = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link Severity }
     *     
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Severity }
     *     
     */
    public void setSeverity(Severity value) {
        this.severity = value;
    }

    /**
     * Gets the value of the exceptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionType() {
        return exceptionType;
    }

    /**
     * Sets the value of the exceptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionType(String value) {
        this.exceptionType = value;
    }

}
