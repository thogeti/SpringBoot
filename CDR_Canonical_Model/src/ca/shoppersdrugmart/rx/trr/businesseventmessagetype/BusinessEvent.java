
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessProcess" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}ProcessEnum"/>
 *         &lt;element name="correlationEntity" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}EntityReference"/>
 *         &lt;element name="entity" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}EntityReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventName" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}EventEnum"/>
 *         &lt;element name="parameters" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}ParameterKeyValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userReference" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}StringEntityReference" minOccurs="0"/>
 *         &lt;element name="previousCorrelationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causedByEvent" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}EventEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessEvent", propOrder = {
    "businessProcess",
    "correlationEntity",
    "entity",
    "eventName",
    "parameters",
    "userReference",
    "previousCorrelationID",
    "causedByEvent"
})
public class BusinessEvent {

    @XmlElement(required = true)
    protected ProcessEnum businessProcess;
    @XmlElement(required = true)
    protected EntityReference correlationEntity;
    protected List<EntityReference> entity;
    @XmlElement(required = true)
    protected EventEnum eventName;
    protected List<ParameterKeyValue> parameters;
    protected StringEntityReference userReference;
    protected String previousCorrelationID;
    protected EventEnum causedByEvent;

    /**
     * Gets the value of the businessProcess property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessEnum }
     *     
     */
    public ProcessEnum getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the value of the businessProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessEnum }
     *     
     */
    public void setBusinessProcess(ProcessEnum value) {
        this.businessProcess = value;
    }

    /**
     * Gets the value of the correlationEntity property.
     * 
     * @return
     *     possible object is
     *     {@link EntityReference }
     *     
     */
    public EntityReference getCorrelationEntity() {
        return correlationEntity;
    }

    /**
     * Sets the value of the correlationEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityReference }
     *     
     */
    public void setCorrelationEntity(EntityReference value) {
        this.correlationEntity = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityReference }
     * 
     * 
     */
    public List<EntityReference> getEntity() {
        if (entity == null) {
            entity = new ArrayList<EntityReference>();
        }
        return this.entity;
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
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterKeyValue }
     * 
     * 
     */
    public List<ParameterKeyValue> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<ParameterKeyValue>();
        }
        return this.parameters;
    }

    /**
     * Gets the value of the userReference property.
     * 
     * @return
     *     possible object is
     *     {@link StringEntityReference }
     *     
     */
    public StringEntityReference getUserReference() {
        return userReference;
    }

    /**
     * Sets the value of the userReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringEntityReference }
     *     
     */
    public void setUserReference(StringEntityReference value) {
        this.userReference = value;
    }

    /**
     * Gets the value of the previousCorrelationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousCorrelationID() {
        return previousCorrelationID;
    }

    /**
     * Sets the value of the previousCorrelationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousCorrelationID(String value) {
        this.previousCorrelationID = value;
    }

    /**
     * Gets the value of the causedByEvent property.
     * 
     * @return
     *     possible object is
     *     {@link EventEnum }
     *     
     */
    public EventEnum getCausedByEvent() {
        return causedByEvent;
    }

    /**
     * Sets the value of the causedByEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventEnum }
     *     
     */
    public void setCausedByEvent(EventEnum value) {
        this.causedByEvent = value;
    }

}
