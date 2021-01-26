
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Generic Product Identifier (GPI) defines pharmaceutically equivalent drug products that are identical in terms of: active ingredient(s), route of administration, dosage form, strength or concentration, therapeutic use
 * 					
 * 
 * <p>Java class for GPI complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GPI"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GPINumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="descriptionEnglish" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="descriptionFrench" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="updateTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GPI", propOrder = {
    "gpiNumber",
    "descriptionEnglish",
    "descriptionFrench",
    "isActive",
    "updateTimestamp"
})
public class GPI {

    @XmlElement(name = "GPINumber", required = true)
    protected String gpiNumber;
    protected String descriptionEnglish;
    protected String descriptionFrench;
    @XmlElement(defaultValue = "true")
    protected Boolean isActive;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;

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

    /**
     * Gets the value of the updateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * Sets the value of the updateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimestamp(XMLGregorianCalendar value) {
        this.updateTimestamp = value;
    }

}
