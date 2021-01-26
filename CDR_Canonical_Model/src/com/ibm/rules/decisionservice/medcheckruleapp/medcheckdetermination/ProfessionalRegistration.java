
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfessionalRegistration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfessionalRegistration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="licenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licensedProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionalCategoryCode" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}ProfessionalCategoryTypeDisplay" minOccurs="0"/>
 *         &lt;element name="issuingBodyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    protected String licensedProvince;
    protected ProfessionalCategoryTypeDisplay professionalCategoryCode;
    protected String issuingBodyCode;

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
     *     {@link String }
     *     
     */
    public String getLicensedProvince() {
        return licensedProvince;
    }

    /**
     * Sets the value of the licensedProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicensedProvince(String value) {
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
     *     {@link String }
     *     
     */
    public String getIssuingBodyCode() {
        return issuingBodyCode;
    }

    /**
     * Sets the value of the issuingBodyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingBodyCode(String value) {
        this.issuingBodyCode = value;
    }

}
