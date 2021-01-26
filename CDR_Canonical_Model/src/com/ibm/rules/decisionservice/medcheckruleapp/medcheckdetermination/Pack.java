
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Pack complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Pack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alternativepacksize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="alternativepacksizeunitofmeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GTIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isActiveFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCurrentFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manufacturerDiscontinuedDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="masterid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packSize" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="strengthUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drug" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Drug" minOccurs="0"/>
 *         &lt;element name="packSizeUOMCode" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}PackSizeUoMTypeDisplay" minOccurs="0"/>
 *         &lt;element name="adheranceCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refillReminderFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pickupReminderFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autoRefillFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trrFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ring" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}FormularyRing" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pack", propOrder = {
    "alternativepacksize",
    "alternativepacksizeunitofmeasure",
    "strength",
    "gtin",
    "isActiveFlag",
    "consumerId",
    "isCurrentFlag",
    "producerId",
    "manufacturerDiscontinuedDate",
    "masterid",
    "packSize",
    "strengthUOMCode",
    "drug",
    "packSizeUOMCode",
    "adheranceCategory",
    "refillReminderFlag",
    "pickupReminderFlag",
    "autoRefillFlag",
    "trrFlag",
    "ring"
})
public class Pack {

    protected Integer alternativepacksize;
    protected String alternativepacksizeunitofmeasure;
    protected String strength;
    @XmlElement(name = "GTIN")
    protected String gtin;
    @XmlElement(defaultValue = "false")
    protected Boolean isActiveFlag;
    protected String consumerId;
    @XmlElement(defaultValue = "false")
    protected Boolean isCurrentFlag;
    protected String producerId;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar manufacturerDiscontinuedDate;
    protected String masterid;
    protected BigDecimal packSize;
    protected String strengthUOMCode;
    protected Drug drug;
    protected PackSizeUoMTypeDisplay packSizeUOMCode;
    protected String adheranceCategory;
    protected String refillReminderFlag;
    protected String pickupReminderFlag;
    protected String autoRefillFlag;
    protected String trrFlag;
    @XmlElement(nillable = true)
    protected List<FormularyRing> ring;

    /**
     * Gets the value of the alternativepacksize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAlternativepacksize() {
        return alternativepacksize;
    }

    /**
     * Sets the value of the alternativepacksize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAlternativepacksize(Integer value) {
        this.alternativepacksize = value;
    }

    /**
     * Gets the value of the alternativepacksizeunitofmeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternativepacksizeunitofmeasure() {
        return alternativepacksizeunitofmeasure;
    }

    /**
     * Sets the value of the alternativepacksizeunitofmeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternativepacksizeunitofmeasure(String value) {
        this.alternativepacksizeunitofmeasure = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrength(String value) {
        this.strength = value;
    }

    /**
     * Gets the value of the gtin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGTIN() {
        return gtin;
    }

    /**
     * Sets the value of the gtin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGTIN(String value) {
        this.gtin = value;
    }

    /**
     * Gets the value of the isActiveFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsActiveFlag() {
        return isActiveFlag;
    }

    /**
     * Sets the value of the isActiveFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsActiveFlag(Boolean value) {
        this.isActiveFlag = value;
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
     * Gets the value of the isCurrentFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCurrentFlag() {
        return isCurrentFlag;
    }

    /**
     * Sets the value of the isCurrentFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCurrentFlag(Boolean value) {
        this.isCurrentFlag = value;
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
     * Gets the value of the manufacturerDiscontinuedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getManufacturerDiscontinuedDate() {
        return manufacturerDiscontinuedDate;
    }

    /**
     * Sets the value of the manufacturerDiscontinuedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setManufacturerDiscontinuedDate(XMLGregorianCalendar value) {
        this.manufacturerDiscontinuedDate = value;
    }

    /**
     * Gets the value of the masterid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterid() {
        return masterid;
    }

    /**
     * Sets the value of the masterid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterid(String value) {
        this.masterid = value;
    }

    /**
     * Gets the value of the packSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPackSize() {
        return packSize;
    }

    /**
     * Sets the value of the packSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPackSize(BigDecimal value) {
        this.packSize = value;
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

    /**
     * Gets the value of the drug property.
     * 
     * @return
     *     possible object is
     *     {@link Drug }
     *     
     */
    public Drug getDrug() {
        return drug;
    }

    /**
     * Sets the value of the drug property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drug }
     *     
     */
    public void setDrug(Drug value) {
        this.drug = value;
    }

    /**
     * Gets the value of the packSizeUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link PackSizeUoMTypeDisplay }
     *     
     */
    public PackSizeUoMTypeDisplay getPackSizeUOMCode() {
        return packSizeUOMCode;
    }

    /**
     * Sets the value of the packSizeUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackSizeUoMTypeDisplay }
     *     
     */
    public void setPackSizeUOMCode(PackSizeUoMTypeDisplay value) {
        this.packSizeUOMCode = value;
    }

    /**
     * Gets the value of the adheranceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdheranceCategory() {
        return adheranceCategory;
    }

    /**
     * Sets the value of the adheranceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdheranceCategory(String value) {
        this.adheranceCategory = value;
    }

    /**
     * Gets the value of the refillReminderFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefillReminderFlag() {
        return refillReminderFlag;
    }

    /**
     * Sets the value of the refillReminderFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefillReminderFlag(String value) {
        this.refillReminderFlag = value;
    }

    /**
     * Gets the value of the pickupReminderFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupReminderFlag() {
        return pickupReminderFlag;
    }

    /**
     * Sets the value of the pickupReminderFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupReminderFlag(String value) {
        this.pickupReminderFlag = value;
    }

    /**
     * Gets the value of the autoRefillFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoRefillFlag() {
        return autoRefillFlag;
    }

    /**
     * Sets the value of the autoRefillFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoRefillFlag(String value) {
        this.autoRefillFlag = value;
    }

    /**
     * Gets the value of the trrFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrrFlag() {
        return trrFlag;
    }

    /**
     * Sets the value of the trrFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrrFlag(String value) {
        this.trrFlag = value;
    }

    /**
     * Gets the value of the ring property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ring property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormularyRing }
     * 
     * 
     */
    public List<FormularyRing> getRing() {
        if (ring == null) {
            ring = new ArrayList<FormularyRing>();
        }
        return this.ring;
    }

}
