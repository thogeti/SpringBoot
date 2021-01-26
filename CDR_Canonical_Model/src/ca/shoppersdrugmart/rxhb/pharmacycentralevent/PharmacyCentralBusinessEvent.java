
package ca.shoppersdrugmart.rxhb.pharmacycentralevent;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.ehealth.QueryReason;
import ca.shoppersdrugmart.rxhb.ehealth.businessevent.BusinessEventPayload;


/**
 * <p>Java class for PharmacyCentralBusinessEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PharmacyCentralBusinessEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="correlationID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="consumerSendTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="requestedStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="reason" type="{http://shoppersdrugmart.ca/RxHB/eHealth}QueryReason" minOccurs="0"/&gt;
 *         &lt;element name="businessEventPayload" type="{http://shoppersdrugmart.ca/RxHB/eHealth/BusinessEvent}BusinessEventPayload"/&gt;
 *         &lt;element name="producerRecordId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="causedByEventName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="originalEventTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="eventReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="businessProcess" type="{http://shoppersdrugmart.ca/RxHB/PharmacyCentralEvent}PharmacyBusinessEventProcessEnum"/&gt;
 *         &lt;element name="eventName" type="{http://shoppersdrugmart.ca/RxHB/PharmacyCentralEvent}PharmacyBusinessEventEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmacyCentralBusinessEvent", propOrder = {
    "correlationID",
    "consumerSendTimestamp",
    "requestedStartDate",
    "reason",
    "businessEventPayload",
    "producerRecordId",
    "causedByEventName",
    "originalEventTimestamp",
    "eventReasonCode",
    "businessProcess",
    "eventName"
})
public class PharmacyCentralBusinessEvent {

    @XmlElement(required = true)
    protected String correlationID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consumerSendTimestamp;
    @XmlElementRef(name = "requestedStartDate", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> requestedStartDate;
    @XmlSchemaType(name = "string")
    protected QueryReason reason;
    @XmlElement(required = true)
    protected BusinessEventPayload businessEventPayload;
    protected String producerRecordId;
    protected String causedByEventName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar originalEventTimestamp;
    protected String eventReasonCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PharmacyBusinessEventProcessEnum businessProcess;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PharmacyBusinessEventEnum eventName;

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
     * Gets the value of the consumerSendTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsumerSendTimestamp() {
        return consumerSendTimestamp;
    }

    /**
     * Sets the value of the consumerSendTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsumerSendTimestamp(XMLGregorianCalendar value) {
        this.consumerSendTimestamp = value;
    }

    /**
     * Gets the value of the requestedStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getRequestedStartDate() {
        return requestedStartDate;
    }

    /**
     * Sets the value of the requestedStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setRequestedStartDate(JAXBElement<XMLGregorianCalendar> value) {
        this.requestedStartDate = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link QueryReason }
     *     
     */
    public QueryReason getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryReason }
     *     
     */
    public void setReason(QueryReason value) {
        this.reason = value;
    }

    /**
     * Gets the value of the businessEventPayload property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessEventPayload }
     *     
     */
    public BusinessEventPayload getBusinessEventPayload() {
        return businessEventPayload;
    }

    /**
     * Sets the value of the businessEventPayload property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessEventPayload }
     *     
     */
    public void setBusinessEventPayload(BusinessEventPayload value) {
        this.businessEventPayload = value;
    }

    /**
     * Gets the value of the producerRecordId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerRecordId() {
        return producerRecordId;
    }

    /**
     * Sets the value of the producerRecordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerRecordId(String value) {
        this.producerRecordId = value;
    }

    /**
     * Gets the value of the causedByEventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausedByEventName() {
        return causedByEventName;
    }

    /**
     * Sets the value of the causedByEventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausedByEventName(String value) {
        this.causedByEventName = value;
    }

    /**
     * Gets the value of the originalEventTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOriginalEventTimestamp() {
        return originalEventTimestamp;
    }

    /**
     * Sets the value of the originalEventTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOriginalEventTimestamp(XMLGregorianCalendar value) {
        this.originalEventTimestamp = value;
    }

    /**
     * Gets the value of the eventReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventReasonCode() {
        return eventReasonCode;
    }

    /**
     * Sets the value of the eventReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventReasonCode(String value) {
        this.eventReasonCode = value;
    }

    /**
     * Gets the value of the businessProcess property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyBusinessEventProcessEnum }
     *     
     */
    public PharmacyBusinessEventProcessEnum getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the value of the businessProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyBusinessEventProcessEnum }
     *     
     */
    public void setBusinessProcess(PharmacyBusinessEventProcessEnum value) {
        this.businessProcess = value;
    }

    /**
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyBusinessEventEnum }
     *     
     */
    public PharmacyBusinessEventEnum getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyBusinessEventEnum }
     *     
     */
    public void setEventName(PharmacyBusinessEventEnum value) {
        this.eventName = value;
    }

}
