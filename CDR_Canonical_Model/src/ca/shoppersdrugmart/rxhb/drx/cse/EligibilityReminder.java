
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EligibilityReminder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityReminder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prescriptionNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="isRefillEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="isAutoRefillEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="portalAutoRefillFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EligibilityReminder", propOrder = {
    "prescriptionNumber",
    "isRefillEligible",
    "isAutoRefillEligible",
    "portalAutoRefillFlag"
})
public class EligibilityReminder {

    @XmlElement(required = true)
    protected String prescriptionNumber;
    @XmlElement(required = true)
    protected String isRefillEligible;
    protected String isAutoRefillEligible;
    protected String portalAutoRefillFlag;

    /**
     * Gets the value of the prescriptionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    /**
     * Sets the value of the prescriptionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionNumber(String value) {
        this.prescriptionNumber = value;
    }

    /**
     * Gets the value of the isRefillEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRefillEligible() {
        return isRefillEligible;
    }

    /**
     * Sets the value of the isRefillEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRefillEligible(String value) {
        this.isRefillEligible = value;
    }

    /**
     * Gets the value of the isAutoRefillEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAutoRefillEligible() {
        return isAutoRefillEligible;
    }

    /**
     * Sets the value of the isAutoRefillEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAutoRefillEligible(String value) {
        this.isAutoRefillEligible = value;
    }

    /**
     * Gets the value of the portalAutoRefillFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalAutoRefillFlag() {
        return portalAutoRefillFlag;
    }

    /**
     * Sets the value of the portalAutoRefillFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalAutoRefillFlag(String value) {
        this.portalAutoRefillFlag = value;
    }

}
