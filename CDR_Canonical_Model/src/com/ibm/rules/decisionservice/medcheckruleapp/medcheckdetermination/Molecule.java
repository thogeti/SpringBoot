
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Molecule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Molecule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isRefrigerated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="moleculeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strength" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalIngredient" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}ActiveIngredient" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Molecule", propOrder = {
    "isRefrigerated",
    "moleculeName",
    "strength",
    "consumerId",
    "producerId",
    "additionalIngredient",
    "strengthUOMCode"
})
public class Molecule {

    @XmlElement(defaultValue = "false")
    protected Boolean isRefrigerated;
    protected String moleculeName;
    protected Double strength;
    protected String consumerId;
    protected String producerId;
    @XmlElement(nillable = true)
    protected List<ActiveIngredient> additionalIngredient;
    protected String strengthUOMCode;

    /**
     * Gets the value of the isRefrigerated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRefrigerated() {
        return isRefrigerated;
    }

    /**
     * Sets the value of the isRefrigerated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefrigerated(Boolean value) {
        this.isRefrigerated = value;
    }

    /**
     * Gets the value of the moleculeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoleculeName() {
        return moleculeName;
    }

    /**
     * Sets the value of the moleculeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoleculeName(String value) {
        this.moleculeName = value;
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
     * Gets the value of the additionalIngredient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalIngredient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalIngredient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActiveIngredient }
     * 
     * 
     */
    public List<ActiveIngredient> getAdditionalIngredient() {
        if (additionalIngredient == null) {
            additionalIngredient = new ArrayList<ActiveIngredient>();
        }
        return this.additionalIngredient;
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
