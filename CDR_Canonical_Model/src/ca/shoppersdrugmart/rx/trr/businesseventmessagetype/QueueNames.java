
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueNames complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueNames">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirstQueue" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}TargetSystemGroup"/>
 *         &lt;element name="SecondQueue" type="{http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType}TargetSystemGroup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueNames", propOrder = {
    "firstQueue",
    "secondQueue"
})
public class QueueNames {

    @XmlElement(name = "FirstQueue", required = true)
    protected TargetSystemGroup firstQueue;
    @XmlElement(name = "SecondQueue")
    protected TargetSystemGroup secondQueue;

    /**
     * Gets the value of the firstQueue property.
     * 
     * @return
     *     possible object is
     *     {@link TargetSystemGroup }
     *     
     */
    public TargetSystemGroup getFirstQueue() {
        return firstQueue;
    }

    /**
     * Sets the value of the firstQueue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetSystemGroup }
     *     
     */
    public void setFirstQueue(TargetSystemGroup value) {
        this.firstQueue = value;
    }

    /**
     * Gets the value of the secondQueue property.
     * 
     * @return
     *     possible object is
     *     {@link TargetSystemGroup }
     *     
     */
    public TargetSystemGroup getSecondQueue() {
        return secondQueue;
    }

    /**
     * Sets the value of the secondQueue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetSystemGroup }
     *     
     */
    public void setSecondQueue(TargetSystemGroup value) {
        this.secondQueue = value;
    }

}
