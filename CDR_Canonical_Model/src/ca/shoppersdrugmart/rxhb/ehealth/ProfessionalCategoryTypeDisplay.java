
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfessionalCategoryTypeDisplay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfessionalCategoryTypeDisplay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="displayValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="providerRoleTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ProviderRoleType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfessionalCategoryTypeDisplay", propOrder = {
    "displayValue",
    "providerRoleTypeCode"
})
public class ProfessionalCategoryTypeDisplay {

    protected String displayValue;
    @XmlSchemaType(name = "string")
    protected ProviderRoleType providerRoleTypeCode;

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
     * Gets the value of the providerRoleTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderRoleType }
     *     
     */
    public ProviderRoleType getProviderRoleTypeCode() {
        return providerRoleTypeCode;
    }

    /**
     * Sets the value of the providerRoleTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderRoleType }
     *     
     */
    public void setProviderRoleTypeCode(ProviderRoleType value) {
        this.providerRoleTypeCode = value;
    }

}
