
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.ehealth.EventEnum;


/**
 * <p>Java class for RefillRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefillRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchIdentifier" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="correlationId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="eventName" type="{http://shoppersdrugmart.ca/RxHB/eHealth}EventEnum" minOccurs="0"/&gt;
 *         &lt;element name="fillRequestId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="fillRequestStatus" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="fillRequestType" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="lastModifiedTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="promisedTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="requestTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="retryCount" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Count" minOccurs="0"/&gt;
 *         &lt;element name="userId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="verificationState" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}VerificationStateEnum" minOccurs="0"/&gt;
 *         &lt;element name="version" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Count" minOccurs="0"/&gt;
 *         &lt;element name="storeNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefillRequest", propOrder = {
    "batchIdentifier",
    "correlationId",
    "eventName",
    "fillRequestId",
    "fillRequestStatus",
    "fillRequestType",
    "lastModifiedTimestamp",
    "promisedTimestamp",
    "requestTimestamp",
    "retryCount",
    "userId",
    "verificationState",
    "version",
    "storeNumber",
    "prescriptionNumber"
})
public class RefillRequest {

    protected String batchIdentifier;
    protected String correlationId;
    @XmlSchemaType(name = "string")
    protected EventEnum eventName;
    protected String fillRequestId;
    protected String fillRequestStatus;
    protected String fillRequestType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar promisedTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestTimestamp;
    protected Integer retryCount;
    protected String userId;
    @XmlSchemaType(name = "string")
    protected VerificationStateEnum verificationState;
    protected Integer version;
    protected String storeNumber;
    protected String prescriptionNumber;

    /**
     * Gets the value of the batchIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchIdentifier() {
        return batchIdentifier;
    }

    /**
     * Sets the value of the batchIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchIdentifier(String value) {
        this.batchIdentifier = value;
    }

    /**
     * Gets the value of the correlationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link EventEnum }
     *     
     */
    public EventEnum getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventEnum }
     *     
     */
    public void setEventName(EventEnum value) {
        this.eventName = value;
    }

    /**
     * Gets the value of the fillRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillRequestId() {
        return fillRequestId;
    }

    /**
     * Sets the value of the fillRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillRequestId(String value) {
        this.fillRequestId = value;
    }

    /**
     * Gets the value of the fillRequestStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillRequestStatus() {
        return fillRequestStatus;
    }

    /**
     * Sets the value of the fillRequestStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillRequestStatus(String value) {
        this.fillRequestStatus = value;
    }

    /**
     * Gets the value of the fillRequestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillRequestType() {
        return fillRequestType;
    }

    /**
     * Sets the value of the fillRequestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillRequestType(String value) {
        this.fillRequestType = value;
    }

    /**
     * Gets the value of the lastModifiedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    /**
     * Sets the value of the lastModifiedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedTimestamp(XMLGregorianCalendar value) {
        this.lastModifiedTimestamp = value;
    }

    /**
     * Gets the value of the promisedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPromisedTimestamp() {
        return promisedTimestamp;
    }

    /**
     * Sets the value of the promisedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPromisedTimestamp(XMLGregorianCalendar value) {
        this.promisedTimestamp = value;
    }

    /**
     * Gets the value of the requestTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestTimestamp() {
        return requestTimestamp;
    }

    /**
     * Sets the value of the requestTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestTimestamp(XMLGregorianCalendar value) {
        this.requestTimestamp = value;
    }

    /**
     * Gets the value of the retryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetryCount() {
        return retryCount;
    }

    /**
     * Sets the value of the retryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetryCount(Integer value) {
        this.retryCount = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the verificationState property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationStateEnum }
     *     
     */
    public VerificationStateEnum getVerificationState() {
        return verificationState;
    }

    /**
     * Sets the value of the verificationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationStateEnum }
     *     
     */
    public void setVerificationState(VerificationStateEnum value) {
        this.verificationState = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the storeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreNumber() {
        return storeNumber;
    }

    /**
     * Sets the value of the storeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreNumber(String value) {
        this.storeNumber = value;
    }

    /**
     * Gets the value of the prescriptionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    /**
     * Sets the value of the prescriptionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionNumber(String value) {
        this.prescriptionNumber = value;
    }

}
