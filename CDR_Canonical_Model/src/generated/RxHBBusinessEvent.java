
package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RxHBBusinessEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RxHBBusinessEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="businessProcess" type="{}RxHBBusinessEventProcessEnum"/&gt;
 *         &lt;element name="correlationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerSendTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="eventName" type="{}RxHBBusinessEventEnum"/&gt;
 *         &lt;element name="eventReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="requestedStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="RxHBBusinessEventEntity" type="{}RxHBBusinessEventEntity" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RxHBBusinessEvent", propOrder = {
    "businessProcess",
    "correlationID",
    "consumerSendTimestamp",
    "eventName",
    "eventReasonCode",
    "requestedStartDate",
    "rxHBBusinessEventEntity"
})
public class RxHBBusinessEvent {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RxHBBusinessEventProcessEnum businessProcess;
    protected String correlationID;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consumerSendTimestamp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RxHBBusinessEventEnum eventName;
    protected String eventReasonCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestedStartDate;
    @XmlElement(name = "RxHBBusinessEventEntity", required = true)
    protected List<RxHBBusinessEventEntity> rxHBBusinessEventEntity;

    /**
     * Gets the value of the businessProcess property.
     * 
     * @return
     *     possible object is
     *     {@link RxHBBusinessEventProcessEnum }
     *     
     */
    public RxHBBusinessEventProcessEnum getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the value of the businessProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxHBBusinessEventProcessEnum }
     *     
     */
    public void setBusinessProcess(RxHBBusinessEventProcessEnum value) {
        this.businessProcess = value;
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
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link RxHBBusinessEventEnum }
     *     
     */
    public RxHBBusinessEventEnum getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxHBBusinessEventEnum }
     *     
     */
    public void setEventName(RxHBBusinessEventEnum value) {
        this.eventName = value;
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
     * Gets the value of the requestedStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestedStartDate() {
        return requestedStartDate;
    }

    /**
     * Sets the value of the requestedStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestedStartDate(XMLGregorianCalendar value) {
        this.requestedStartDate = value;
    }

    /**
     * Gets the value of the rxHBBusinessEventEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rxHBBusinessEventEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRxHBBusinessEventEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RxHBBusinessEventEntity }
     * 
     * 
     */
    public List<RxHBBusinessEventEntity> getRxHBBusinessEventEntity() {
        if (rxHBBusinessEventEntity == null) {
            rxHBBusinessEventEntity = new ArrayList<RxHBBusinessEventEntity>();
        }
        return this.rxHBBusinessEventEntity;
    }

}
