
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriptionHoldReasonTypeDisplay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrescriptionHoldReasonTypeDisplay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="displayValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PrescriptionHoldReasonTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionHoldReason" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrescriptionHoldReasonTypeDisplay", propOrder = {
    "displayValue",
    "prescriptionHoldReasonTypeCode"
})
public class PrescriptionHoldReasonTypeDisplay {

    protected String displayValue;
    @XmlElement(name = "PrescriptionHoldReasonTypeCode")
    @XmlSchemaType(name = "string")
    protected PrescriptionHoldReason prescriptionHoldReasonTypeCode;

    /**
     * Gets the value of the displayValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * Sets the value of the displayValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayValue(String value) {
        this.displayValue = value;
    }

    /**
     * Gets the value of the prescriptionHoldReasonTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionHoldReason }
     *     
     */
    public PrescriptionHoldReason getPrescriptionHoldReasonTypeCode() {
        return prescriptionHoldReasonTypeCode;
    }

    /**
     * Sets the value of the prescriptionHoldReasonTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionHoldReason }
     *     
     */
    public void setPrescriptionHoldReasonTypeCode(PrescriptionHoldReason value) {
        this.prescriptionHoldReasonTypeCode = value;
    }

}
