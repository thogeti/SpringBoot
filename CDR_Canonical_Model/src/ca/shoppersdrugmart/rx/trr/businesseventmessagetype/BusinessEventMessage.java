
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for BusinessEventMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessEventMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceSystem" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}SourceSystemEnum"/>
 *         &lt;element name="sourceSystemID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetSystemGroup" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}QueueNames" maxOccurs="unbounded"/>
 *         &lt;element name="sequence" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="businessEvent" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}BusinessEvent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessEventMessage", propOrder = {
    "sourceSystem",
    "sourceSystemID",
    "targetSystemGroup",
    "sequence",
    "businessEvent"
})
public class BusinessEventMessage {

    @XmlElement(required = true)
    protected SourceSystemEnum sourceSystem;
    protected int sourceSystemID;
    @XmlElement(required = true)
    protected List<QueueNames> targetSystemGroup;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sequence;
    @XmlElement(required = true)
    protected BusinessEvent businessEvent;

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link SourceSystemEnum }
     *     
     */
    public SourceSystemEnum getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceSystemEnum }
     *     
     */
    public void setSourceSystem(SourceSystemEnum value) {
        this.sourceSystem = value;
    }

    /**
     * Gets the value of the sourceSystemID property.
     * 
     */
    public int getSourceSystemID() {
        return sourceSystemID;
    }

    /**
     * Sets the value of the sourceSystemID property.
     * 
     */
    public void setSourceSystemID(int value) {
        this.sourceSystemID = value;
    }

    /**
     * Gets the value of the targetSystemGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetSystemGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetSystemGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueueNames }
     * 
     * 
     */
    public List<QueueNames> getTargetSystemGroup() {
        if (targetSystemGroup == null) {
            targetSystemGroup = new ArrayList<QueueNames>();
        }
        return this.targetSystemGroup;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSequence(XMLGregorianCalendar value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the businessEvent property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessEvent }
     *     
     */
    public BusinessEvent getBusinessEvent() {
        return businessEvent;
    }

    /**
     * Sets the value of the businessEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessEvent }
     *     
     */
    public void setBusinessEvent(BusinessEvent value) {
        this.businessEvent = value;
    }

}
