
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import generated.RxHBBusinessEventActionEnum;


/**
 * <p>Java class for CDRPersistResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CDRPersistResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="action" type="{}RxHBBusinessEventActionEnum"/&gt;
 *         &lt;element name="subscriptions" type="{http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist}Subscription" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDRPersistResponse", propOrder = {
    "action",
    "subscriptions"
})
public class CDRPersistResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RxHBBusinessEventActionEnum action;
    protected Subscription subscriptions;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link RxHBBusinessEventActionEnum }
     *     
     */
    public RxHBBusinessEventActionEnum getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxHBBusinessEventActionEnum }
     *     
     */
    public void setAction(RxHBBusinessEventActionEnum value) {
        this.action = value;
    }

    /**
     * Gets the value of the subscriptions property.
     * 
     * @return
     *     possible object is
     *     {@link Subscription }
     *     
     */
    public Subscription getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets the value of the subscriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscription }
     *     
     */
    public void setSubscriptions(Subscription value) {
        this.subscriptions = value;
    }

}
