
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfessionalRegistration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfessionalRegistration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="licenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="licensedProvince" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Province" minOccurs="0"/&gt;
 *         &lt;element name="professionalCategoryCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ProfessionalCategoryTypeDisplay" minOccurs="0"/&gt;
 *         &lt;element name="issuingBodyCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}LicenseIssuingBodyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfessionalRegistration", propOrder = {
    "licenseNumber",
    "licensedProvince",
    "professionalCategoryCode",
    "issuingBodyCode"
})
public class ProfessionalRegistration {

    protected String licenseNumber;
    @XmlSchemaType(name = "string")
    protected Province licensedProvince;
    protected ProfessionalCategoryTypeDisplay professionalCategoryCode;
    @XmlSchemaType(name = "string")
    protected LicenseIssuingBodyType issuingBodyCode;

    /**
     * Gets the value of the licenseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the value of the licenseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseNumber(String value) {
        this.licenseNumber = value;
    }

    /**
     * Gets the value of the licensedProvince property.
     * 
     * @return
     *     possible object is
     *     {@link Province }
     *     
     */
    public Province getLicensedProvince() {
        return licensedProvince;
    }

    /**
     * Sets the value of the licensedProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link Province }
     *     
     */
    public void setLicensedProvince(Province value) {
        this.licensedProvince = value;
    }

    /**
     * Gets the value of the professionalCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link ProfessionalCategoryTypeDisplay }
     *     
     */
    public ProfessionalCategoryTypeDisplay getProfessionalCategoryCode() {
        return professionalCategoryCode;
    }

    /**
     * Sets the value of the professionalCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfessionalCategoryTypeDisplay }
     *     
     */
    public void setProfessionalCategoryCode(ProfessionalCategoryTypeDisplay value) {
        this.professionalCategoryCode = value;
    }

    /**
     * Gets the value of the issuingBodyCode property.
     * 
     * @return
     *     possible object is
     *     {@link LicenseIssuingBodyType }
     *     
     */
    public LicenseIssuingBodyType getIssuingBodyCode() {
        return issuingBodyCode;
    }

    /**
     * Sets the value of the issuingBodyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseIssuingBodyType }
     *     
     */
    public void setIssuingBodyCode(LicenseIssuingBodyType value) {
        this.issuingBodyCode = value;
    }

}
