
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This is the reaction that has occurred due to the patient being exposed to something.
 * 
 * <p>Java class for Reaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Reaction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="producerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="exposureMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reactionStartTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="reactionDescriptionEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="exposedMaterialName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reactionCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}SubjectReaction" minOccurs="0"/&gt;
 *         &lt;element name="reactionSeverityCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ReactionSeverityLevel" minOccurs="0"/&gt;
 *         &lt;element name="exposedMaterial" type="{http://shoppersdrugmart.ca/RxHB/eHealth}NonDrugAllergen" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="drug" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Drug" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="causativeRx" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    @XmlElementRef(name = "exposedMaterialName", type = JAXBElement.class, required = false)
    protected JAXBElement<String> exposedMaterialName;
    @XmlSchemaType(name = "string")
    protected SubjectReaction reactionCode;
    @XmlSchemaType(name = "string")
    protected ReactionSeverityLevel reactionSeverityCode;
    @XmlSchemaType(name = "string")
    protected List<NonDrugAllergen> exposedMaterial;
    protected List<Note> note;
    protected List<Drug> drug;
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExposedMaterialName() {
        return exposedMaterialName;
    }

    /**
     * Sets the value of the exposedMaterialName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExposedMaterialName(JAXBElement<String> value) {
        this.exposedMaterialName = value;
    }

    /**
     * Gets the value of the reactionCode property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectReaction }
     *     
     */
    public SubjectReaction getReactionCode() {
        return reactionCode;
    }

    /**
     * Sets the value of the reactionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectReaction }
     *     
     */
    public void setReactionCode(SubjectReaction value) {
        this.reactionCode = value;
    }

    /**
     * Gets the value of the reactionSeverityCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReactionSeverityLevel }
     *     
     */
    public ReactionSeverityLevel getReactionSeverityCode() {
        return reactionSeverityCode;
    }

    /**
     * Sets the value of the reactionSeverityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReactionSeverityLevel }
     *     
     */
    public void setReactionSeverityCode(ReactionSeverityLevel value) {
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
     * {@link NonDrugAllergen }
     * 
     * 
     */
    public List<NonDrugAllergen> getExposedMaterial() {
        if (exposedMaterial == null) {
            exposedMaterial = new ArrayList<NonDrugAllergen>();
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
