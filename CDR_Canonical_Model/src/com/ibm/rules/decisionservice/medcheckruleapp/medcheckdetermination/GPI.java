
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GPI complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GPI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GPINumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descriptionEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GPI", propOrder = {
    "gpiNumber",
    "descriptionEnglish",
    "descriptionFrench",
    "isActive"
})
public class GPI {

    @XmlElement(name = "GPINumber", required = true)
    protected String gpiNumber;
    protected String descriptionEnglish;
    protected String descriptionFrench;
    @XmlElement(defaultValue = "true")
    protected Boolean isActive;

    /**
     * Gets the value of the gpiNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGPINumber() {
        return gpiNumber;
    }

    /**
     * Sets the value of the gpiNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGPINumber(String value) {
        this.gpiNumber = value;
    }

    /**
     * Gets the value of the descriptionEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionEnglish() {
        return descriptionEnglish;
    }

    /**
     * Sets the value of the descriptionEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionEnglish(String value) {
        this.descriptionEnglish = value;
    }

    /**
     * Gets the value of the descriptionFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionFrench() {
        return descriptionFrench;
    }

    /**
     * Sets the value of the descriptionFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionFrench(String value) {
        this.descriptionFrench = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsActive(Boolean value) {
        this.isActive = value;
    }

}
