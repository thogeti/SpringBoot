
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriptionResumeReasonTypeDisplay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrescriptionResumeReasonTypeDisplay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="displayValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PrescriptionResumeReasonTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionResumeReason" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrescriptionResumeReasonTypeDisplay", propOrder = {
    "displayValue",
    "prescriptionResumeReasonTypeCode"
})
public class PrescriptionResumeReasonTypeDisplay {

    protected String displayValue;
    @XmlElement(name = "PrescriptionResumeReasonTypeCode")
    @XmlSchemaType(name = "string")
    protected PrescriptionResumeReason prescriptionResumeReasonTypeCode;

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
     * Gets the value of the prescriptionResumeReasonTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionResumeReason }
     *     
     */
    public PrescriptionResumeReason getPrescriptionResumeReasonTypeCode() {
        return prescriptionResumeReasonTypeCode;
    }

    /**
     * Sets the value of the prescriptionResumeReasonTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionResumeReason }
     *     
     */
    public void setPrescriptionResumeReasonTypeCode(PrescriptionResumeReason value) {
        this.prescriptionResumeReasonTypeCode = value;
    }

}
