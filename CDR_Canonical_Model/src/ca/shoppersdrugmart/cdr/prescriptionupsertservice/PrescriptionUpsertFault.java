
package ca.shoppersdrugmart.cdr.prescriptionupsertservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrescriptionUpsertFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prescriptionUpsertFault"
})
@XmlRootElement(name = "PrescriptionUpsertFault")
public class PrescriptionUpsertFault {

    @XmlElement(name = "PrescriptionUpsertFault", required = true)
    protected String prescriptionUpsertFault;

    /**
     * Gets the value of the prescriptionUpsertFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionUpsertFault() {
        return prescriptionUpsertFault;
    }

    /**
     * Sets the value of the prescriptionUpsertFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionUpsertFault(String value) {
        this.prescriptionUpsertFault = value;
    }

}
