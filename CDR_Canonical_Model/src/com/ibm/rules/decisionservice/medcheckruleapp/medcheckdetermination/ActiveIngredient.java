
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActiveIngredient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActiveIngredient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chemicalNameAlternative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chemicalNameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chemicalNameFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strength" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strengthUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActiveIngredient", propOrder = {
    "chemicalNameAlternative",
    "chemicalNameEnglish",
    "chemicalNameFrench",
    "name",
    "strength",
    "consumerId",
    "producerId",
    "strengthUOMCode"
})
public class ActiveIngredient {

    protected String chemicalNameAlternative;
    protected String chemicalNameEnglish;
    protected String chemicalNameFrench;
    protected String name;
    protected Double strength;
    protected String consumerId;
    protected String producerId;
    protected String strengthUOMCode;

    /**
     * Gets the value of the chemicalNameAlternative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChemicalNameAlternative() {
        return chemicalNameAlternative;
    }

    /**
     * Sets the value of the chemicalNameAlternative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChemicalNameAlternative(String value) {
        this.chemicalNameAlternative = value;
    }

    /**
     * Gets the value of the chemicalNameEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChemicalNameEnglish() {
        return chemicalNameEnglish;
    }

    /**
     * Sets the value of the chemicalNameEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChemicalNameEnglish(String value) {
        this.chemicalNameEnglish = value;
    }

    /**
     * Gets the value of the chemicalNameFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChemicalNameFrench() {
        return chemicalNameFrench;
    }

    /**
     * Sets the value of the chemicalNameFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChemicalNameFrench(String value) {
        this.chemicalNameFrench = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStrength(Double value) {
        this.strength = value;
    }

    /**
     * Gets the value of the consumerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerId() {
        return consumerId;
    }

    /**
     * Sets the value of the consumerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerId(String value) {
        this.consumerId = value;
    }

    /**
     * Gets the value of the producerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerId() {
        return producerId;
    }

    /**
     * Sets the value of the producerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerId(String value) {
        this.producerId = value;
    }

    /**
     * Gets the value of the strengthUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrengthUOMCode() {
        return strengthUOMCode;
    }

    /**
     * Sets the value of the strengthUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrengthUOMCode(String value) {
        this.strengthUOMCode = value;
    }

}
