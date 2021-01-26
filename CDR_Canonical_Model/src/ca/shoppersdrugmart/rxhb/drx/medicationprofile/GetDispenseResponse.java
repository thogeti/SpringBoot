
package ca.shoppersdrugmart.rxhb.drx.medicationprofile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;


/**
 * <p>Java class for GetDispenseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDispenseResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Dispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDispenseResponse", propOrder = {
    "dispense"
})
public class GetDispenseResponse {

    @XmlElement(name = "Dispense", required = true)
    protected Dispense dispense;

    /**
     * Gets the value of the dispense property.
     * 
     * @return
     *     possible object is
     *     {@link Dispense }
     *     
     */
    public Dispense getDispense() {
        return dispense;
    }

    /**
     * Sets the value of the dispense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispense }
     *     
     */
    public void setDispense(Dispense value) {
        this.dispense = value;
    }

}
