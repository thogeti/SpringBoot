
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyCentralBusinessEvent;


/**
 * <p>Java class for DispenseUpsert complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DispenseUpsert">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pharmacyBusinessEvent" type="{http://shoppersdrugmart.ca/RxHB/PharmacyCentralEvent}PharmacyCentralBusinessEvent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DispenseUpsert", propOrder = {
    "pharmacyBusinessEvent"
})
public class DispenseUpsert {

    @XmlElement(required = true)
    protected PharmacyCentralBusinessEvent pharmacyBusinessEvent;

    /**
     * Gets the value of the pharmacyBusinessEvent property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyCentralBusinessEvent }
     *     
     */
    public PharmacyCentralBusinessEvent getPharmacyBusinessEvent() {
        return pharmacyBusinessEvent;
    }

    /**
     * Sets the value of the pharmacyBusinessEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyCentralBusinessEvent }
     *     
     */
    public void setPharmacyBusinessEvent(PharmacyCentralBusinessEvent value) {
        this.pharmacyBusinessEvent = value;
    }

}
