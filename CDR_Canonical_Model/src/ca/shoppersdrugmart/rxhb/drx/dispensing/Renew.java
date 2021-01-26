
package ca.shoppersdrugmart.rxhb.drx.dispensing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;


/**
 * <p>Java class for Renew complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Renew"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" maxOccurs="unbounded"/&gt;
 *         &lt;element name="sourceChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Renew", propOrder = {
    "prescription",
    "sourceChannel"
})
public class Renew {

    @XmlElement(name = "Prescription", required = true)
    protected List<Prescription> prescription;
    @XmlElement(required = true)
    protected PharmacyChannel sourceChannel;

    /**
     * Gets the value of the prescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prescription }
     * 
     * 
     */
    public List<Prescription> getPrescription() {
        if (prescription == null) {
            prescription = new ArrayList<Prescription>();
        }
        return this.prescription;
    }

    /**
     * Gets the value of the sourceChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getSourceChannel() {
        return sourceChannel;
    }

    /**
     * Sets the value of the sourceChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setSourceChannel(PharmacyChannel value) {
        this.sourceChannel = value;
    }

}
