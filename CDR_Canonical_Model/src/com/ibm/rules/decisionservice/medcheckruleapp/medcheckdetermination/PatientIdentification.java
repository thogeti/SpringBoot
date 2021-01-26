
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssuingAuthorityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdentificationTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientIdentification", propOrder = {
    "identificationNumber",
    "issuingAuthorityName",
    "identificationTypeCode"
})
public class PatientIdentification {

    @XmlElement(name = "IdentificationNumber", required = true)
    protected String identificationNumber;
    @XmlElement(name = "IssuingAuthorityName")
    protected String issuingAuthorityName;
    @XmlElement(name = "IdentificationTypeCode")
    protected String identificationTypeCode;

    /**
     * Gets the value of the identificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the value of the identificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    /**
     * Gets the value of the issuingAuthorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuingAuthorityName() {
        return issuingAuthorityName;
    }

    /**
     * Sets the value of the issuingAuthorityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingAuthorityName(String value) {
        this.issuingAuthorityName = value;
    }

    /**
     * Gets the value of the identificationTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationTypeCode() {
        return identificationTypeCode;
    }

    /**
     * Sets the value of the identificationTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationTypeCode(String value) {
        this.identificationTypeCode = value;
    }

}
