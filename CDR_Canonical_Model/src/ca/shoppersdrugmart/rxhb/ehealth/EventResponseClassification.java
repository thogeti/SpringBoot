
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventResponseClassification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventResponseClassification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="classification" type="{http://shoppersdrugmart.ca/RxHB/eHealth}EventSourceClassification"/&gt;
 *         &lt;element name="event" type="{http://shoppersdrugmart.ca/RxHB/eHealth}EventEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventResponseClassification", propOrder = {
    "classification",
    "event"
})
public class EventResponseClassification {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EventSourceClassification classification;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EventEnum event;

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link EventSourceClassification }
     *     
     */
    public EventSourceClassification getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventSourceClassification }
     *     
     */
    public void setClassification(EventSourceClassification value) {
        this.classification = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link EventEnum }
     *     
     */
    public EventEnum getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventEnum }
     *     
     */
    public void setEvent(EventEnum value) {
        this.event = value;
    }

}
