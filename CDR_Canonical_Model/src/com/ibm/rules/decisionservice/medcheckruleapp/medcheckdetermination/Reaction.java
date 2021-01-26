
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Reaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Reaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="producerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exposureMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reactionStartTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reactionDescriptionEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exposedMaterialName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reactionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reactionSeverityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exposedMaterial" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Note" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="drug" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Drug" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="causativeRx" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Prescription" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reaction", propOrder = {
    "producerid",
    "exposureMethod",
    "reactionStartTimestamp",
    "reactionDescriptionEnglish",
    "exposedMaterialName",
    "reactionCode",
    "reactionSeverityCode",
    "exposedMaterial",
    "note",
    "drug",
    "causativeRx"
})
public class Reaction {

    protected String producerid;
    protected String exposureMethod;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reactionStartTimestamp;
    protected String reactionDescriptionEnglish;
    protected String exposedMaterialName;
    protected String reactionCode;
    protected String reactionSeverityCode;
    @XmlElement(nillable = true)
    protected List<String> exposedMaterial;
    @XmlElement(nillable = true)
    protected List<Note> note;
    @XmlElement(nillable = true)
    protected List<Drug> drug;
    @XmlElement(nillable = true)
    protected List<Prescription> causativeRx;

    /**
     * Gets the value of the producerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerid() {
        return producerid;
    }

    /**
     * Sets the value of the producerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerid(String value) {
        this.producerid = value;
    }

    /**
     * Gets the value of the exposureMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposureMethod() {
        return exposureMethod;
    }

    /**
     * Sets the value of the exposureMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposureMethod(String value) {
        this.exposureMethod = value;
    }

    /**
     * Gets the value of the reactionStartTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReactionStartTimestamp() {
        return reactionStartTimestamp;
    }

    /**
     * Sets the value of the reactionStartTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReactionStartTimestamp(XMLGregorianCalendar value) {
        this.reactionStartTimestamp = value;
    }

    /**
     * Gets the value of the reactionDescriptionEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReactionDescriptionEnglish() {
        return reactionDescriptionEnglish;
    }

    /**
     * Sets the value of the reactionDescriptionEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReactionDescriptionEnglish(String value) {
        this.reactionDescriptionEnglish = value;
    }

    /**
     * Gets the value of the exposedMaterialName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposedMaterialName() {
        return exposedMaterialName;
    }

    /**
     * Sets the value of the exposedMaterialName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposedMaterialName(String value) {
        this.exposedMaterialName = value;
    }

    /**
     * Gets the value of the reactionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReactionCode() {
        return reactionCode;
    }

    /**
     * Sets the value of the reactionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReactionCode(String value) {
        this.reactionCode = value;
    }

    /**
     * Gets the value of the reactionSeverityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReactionSeverityCode() {
        return reactionSeverityCode;
    }

    /**
     * Sets the value of the reactionSeverityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReactionSeverityCode(String value) {
        this.reactionSeverityCode = value;
    }

    /**
     * Gets the value of the exposedMaterial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exposedMaterial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExposedMaterial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExposedMaterial() {
        if (exposedMaterial == null) {
            exposedMaterial = new ArrayList<String>();
        }
        return this.exposedMaterial;
    }

    /**
     * Gets the value of the note property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the note property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     * 
     * 
     */
    public List<Note> getNote() {
        if (note == null) {
            note = new ArrayList<Note>();
        }
        return this.note;
    }

    /**
     * Gets the value of the drug property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drug property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrug().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drug }
     * 
     * 
     */
    public List<Drug> getDrug() {
        if (drug == null) {
            drug = new ArrayList<Drug>();
        }
        return this.drug;
    }

    /**
     * Gets the value of the causativeRx property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the causativeRx property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCausativeRx().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prescription }
     * 
     * 
     */
    public List<Prescription> getCausativeRx() {
        if (causativeRx == null) {
            causativeRx = new ArrayList<Prescription>();
        }
        return this.causativeRx;
    }

}
