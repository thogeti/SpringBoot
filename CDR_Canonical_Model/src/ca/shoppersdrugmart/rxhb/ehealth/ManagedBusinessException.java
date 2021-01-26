
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ManagedBusinessException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManagedBusinessException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issueLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issueRangeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="knowledgeBaseVendorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IssueMonographId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateManaged" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="mitigatedBy" type="{http://shoppersdrugmart.ca/RxHB/eHealth}MitigatedBy"/&gt;
 *         &lt;element name="interactionIssueDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument"/&gt;
 *         &lt;element name="managedIssueDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument" minOccurs="0"/&gt;
 *         &lt;element name="exceptionType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ExceptionType" minOccurs="0"/&gt;
 *         &lt;element name="instantiationDocument" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagedIssueDocument" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManagedBusinessException", propOrder = {
    "priority",
    "severity",
    "provinceCode",
    "details",
    "issueLink",
    "issueRangeValue",
    "knowledgeBaseVendorName",
    "issueMonographId",
    "dateManaged",
    "mitigatedBy",
    "interactionIssueDocument",
    "managedIssueDocument",
    "exceptionType",
    "instantiationDocument"
})
public class ManagedBusinessException {

    @XmlElementRef(name = "priority", type = JAXBElement.class, required = false)
    protected JAXBElement<String> priority;
    @XmlElementRef(name = "severity", type = JAXBElement.class, required = false)
    protected JAXBElement<String> severity;
    @XmlElementRef(name = "provinceCode", type = JAXBElement.class, required = false)
    protected JAXBElement<String> provinceCode;
    @XmlElementRef(name = "details", type = JAXBElement.class, required = false)
    protected JAXBElement<String> details;
    @XmlElementRef(name = "issueLink", type = JAXBElement.class, required = false)
    protected JAXBElement<String> issueLink;
    @XmlElementRef(name = "issueRangeValue", type = JAXBElement.class, required = false)
    protected JAXBElement<String> issueRangeValue;
    protected String knowledgeBaseVendorName;
    @XmlElement(name = "IssueMonographId")
    protected String issueMonographId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateManaged;
    @XmlElement(required = true, nillable = true)
    protected MitigatedBy mitigatedBy;
    @XmlElement(required = true, nillable = true)
    protected ManagedIssueDocument interactionIssueDocument;
    @XmlElementRef(name = "managedIssueDocument", type = JAXBElement.class, required = false)
    protected JAXBElement<ManagedIssueDocument> managedIssueDocument;
    @XmlElementRef(name = "exceptionType", type = JAXBElement.class, required = false)
    protected JAXBElement<String> exceptionType;
    protected ManagedIssueDocument instantiationDocument;

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPriority(JAXBElement<String> value) {
        this.priority = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSeverity(JAXBElement<String> value) {
        this.severity = value;
    }

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProvinceCode(JAXBElement<String> value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDetails(JAXBElement<String> value) {
        this.details = value;
    }

    /**
     * Gets the value of the issueLink property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIssueLink() {
        return issueLink;
    }

    /**
     * Sets the value of the issueLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIssueLink(JAXBElement<String> value) {
        this.issueLink = value;
    }

    /**
     * Gets the value of the issueRangeValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIssueRangeValue() {
        return issueRangeValue;
    }

    /**
     * Sets the value of the issueRangeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIssueRangeValue(JAXBElement<String> value) {
        this.issueRangeValue = value;
    }

    /**
     * Gets the value of the knowledgeBaseVendorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnowledgeBaseVendorName() {
        return knowledgeBaseVendorName;
    }

    /**
     * Sets the value of the knowledgeBaseVendorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnowledgeBaseVendorName(String value) {
        this.knowledgeBaseVendorName = value;
    }

    /**
     * Gets the value of the issueMonographId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueMonographId() {
        return issueMonographId;
    }

    /**
     * Sets the value of the issueMonographId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueMonographId(String value) {
        this.issueMonographId = value;
    }

    /**
     * Gets the value of the dateManaged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateManaged() {
        return dateManaged;
    }

    /**
     * Sets the value of the dateManaged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateManaged(XMLGregorianCalendar value) {
        this.dateManaged = value;
    }

    /**
     * Gets the value of the mitigatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link MitigatedBy }
     *     
     */
    public MitigatedBy getMitigatedBy() {
        return mitigatedBy;
    }

    /**
     * Sets the value of the mitigatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link MitigatedBy }
     *     
     */
    public void setMitigatedBy(MitigatedBy value) {
        this.mitigatedBy = value;
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
     *     {@link JAXBElement }{@code <}{@link ManagedIssueDocument }{@code >}
     *     
     */
    public JAXBElement<ManagedIssueDocument> getManagedIssueDocument() {
        return managedIssueDocument;
    }

    /**
     * Sets the value of the managedIssueDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ManagedIssueDocument }{@code >}
     *     
     */
    public void setManagedIssueDocument(JAXBElement<ManagedIssueDocument> value) {
        this.managedIssueDocument = value;
    }

    /**
     * Gets the value of the exceptionType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExceptionType() {
        return exceptionType;
    }

    /**
     * Sets the value of the exceptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExceptionType(JAXBElement<String> value) {
        this.exceptionType = value;
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

}
