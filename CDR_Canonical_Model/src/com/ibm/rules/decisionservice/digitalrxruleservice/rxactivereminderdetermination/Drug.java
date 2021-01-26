
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drug complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drug">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="administrationRouteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adultDosageMaximumQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="adultDosageMinimumQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="branddrug" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chemicalLabelNameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chemicalLabelNameFrench" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="childDosageMaximumQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="childDosageMinimumQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="colour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="din" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dintype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dosageRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formvariant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isdevice" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isimmunization" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isproprietary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isreportable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="iswritten" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="markings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monograph" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="schedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shape" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeNameEnglish" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tradeNameFrench" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drug", propOrder = {
    "administrationRouteCode",
    "adultDosageMaximumQuantity",
    "adultDosageMinimumQuantity",
    "branddrug",
    "chemicalLabelNameEnglish",
    "chemicalLabelNameFrench",
    "childDosageMaximumQuantity",
    "childDosageMinimumQuantity",
    "colour",
    "consumerId",
    "din",
    "dintype",
    "dosageRange",
    "drugDescription",
    "formvariant",
    "isdevice",
    "isimmunization",
    "isproprietary",
    "isreportable",
    "iswritten",
    "markings",
    "monograph",
    "producerId",
    "productGroup",
    "schedule",
    "shape",
    "tradeNameEnglish",
    "tradeNameFrench"
})
public class Drug {

    protected String administrationRouteCode;
    protected Double adultDosageMaximumQuantity;
    protected Double adultDosageMinimumQuantity;
    protected String branddrug;
    protected String chemicalLabelNameEnglish; //CR100
    protected String chemicalLabelNameFrench; //CR100
    protected Double childDosageMaximumQuantity;
    protected Double childDosageMinimumQuantity;
    protected String colour;
    protected String consumerId;
    protected String din;
    protected String dintype;
    protected String dosageRange;
    protected String drugDescription;
    protected Integer formvariant;
    protected boolean isdevice;
    protected boolean isimmunization;
    protected boolean isproprietary;
    protected boolean isreportable;
    protected boolean iswritten;
    protected String markings;
    protected String monograph;
    protected String producerId;
    protected String productGroup;
    protected String schedule;
    protected String shape;
    protected String tradeNameEnglish; //CR100
    protected String tradeNameFrench;//CR100

    /**
     * Gets the value of the administrationRouteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationRouteCode() {
        return administrationRouteCode;
    }

    /**
     * Sets the value of the administrationRouteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationRouteCode(String value) {
        this.administrationRouteCode = value;
    }

    /**
     * Gets the value of the adultDosageMaximumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAdultDosageMaximumQuantity() {
        return adultDosageMaximumQuantity;
    }

    /**
     * Sets the value of the adultDosageMaximumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAdultDosageMaximumQuantity(Double value) {
        this.adultDosageMaximumQuantity = value;
    }

    /**
     * Gets the value of the adultDosageMinimumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAdultDosageMinimumQuantity() {
        return adultDosageMinimumQuantity;
    }

    /**
     * Sets the value of the adultDosageMinimumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAdultDosageMinimumQuantity(Double value) {
        this.adultDosageMinimumQuantity = value;
    }

    /**
     * Gets the value of the branddrug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranddrug() {
        return branddrug;
    }

    /**
     * Sets the value of the branddrug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranddrug(String value) {
        this.branddrug = value;
    }

  //CR100 start
    /**
     * Gets the value of the chemicalLabelNameEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChemicalLabelNameEnglish() {
        return chemicalLabelNameEnglish;
    }

    /**
     * Sets the value of the chemicalLabelNameEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChemicalLabelNameEnglish(String value) {
        this.chemicalLabelNameEnglish = value;
    }

    /**
     * Gets the value of the chemicalLabelNameFrench property.
     * 
     */
    public String isChemicalLabelNameFrench() {
        return chemicalLabelNameFrench;
    }

    /**
     * Sets the value of the chemicalLabelNameFrench property.
     * 
     */
    public void setChemicalLabelNameFrench(String value) {
        this.chemicalLabelNameFrench = value;
    }
  //CR100 end
    /**
     * Gets the value of the childDosageMaximumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getChildDosageMaximumQuantity() {
        return childDosageMaximumQuantity;
    }

    /**
     * Sets the value of the childDosageMaximumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setChildDosageMaximumQuantity(Double value) {
        this.childDosageMaximumQuantity = value;
    }

    /**
     * Gets the value of the childDosageMinimumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getChildDosageMinimumQuantity() {
        return childDosageMinimumQuantity;
    }

    /**
     * Sets the value of the childDosageMinimumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setChildDosageMinimumQuantity(Double value) {
        this.childDosageMinimumQuantity = value;
    }

    /**
     * Gets the value of the colour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets the value of the colour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColour(String value) {
        this.colour = value;
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
     * Gets the value of the din property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDin() {
        return din;
    }

    /**
     * Sets the value of the din property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDin(String value) {
        this.din = value;
    }

    /**
     * Gets the value of the dintype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDintype() {
        return dintype;
    }

    /**
     * Sets the value of the dintype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDintype(String value) {
        this.dintype = value;
    }

    /**
     * Gets the value of the dosageRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosageRange() {
        return dosageRange;
    }

    /**
     * Sets the value of the dosageRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosageRange(String value) {
        this.dosageRange = value;
    }

    /**
     * Gets the value of the drugDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugDescription() {
        return drugDescription;
    }

    /**
     * Sets the value of the drugDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugDescription(String value) {
        this.drugDescription = value;
    }

    /**
     * Gets the value of the formvariant property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFormvariant() {
        return formvariant;
    }

    /**
     * Sets the value of the formvariant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFormvariant(Integer value) {
        this.formvariant = value;
    }

    /**
     * Gets the value of the isdevice property.
     * 
     */
    public boolean isIsdevice() {
        return isdevice;
    }

    /**
     * Sets the value of the isdevice property.
     * 
     */
    public void setIsdevice(boolean value) {
        this.isdevice = value;
    }

    /**
     * Gets the value of the isimmunization property.
     * 
     */
    public boolean isIsimmunization() {
        return isimmunization;
    }

    /**
     * Sets the value of the isimmunization property.
     * 
     */
    public void setIsimmunization(boolean value) {
        this.isimmunization = value;
    }

    /**
     * Gets the value of the isproprietary property.
     * 
     */
    public boolean isIsproprietary() {
        return isproprietary;
    }

    /**
     * Sets the value of the isproprietary property.
     * 
     */
    public void setIsproprietary(boolean value) {
        this.isproprietary = value;
    }

    /**
     * Gets the value of the isreportable property.
     * 
     */
    public boolean isIsreportable() {
        return isreportable;
    }

    /**
     * Sets the value of the isreportable property.
     * 
     */
    public void setIsreportable(boolean value) {
        this.isreportable = value;
    }

    /**
     * Gets the value of the iswritten property.
     * 
     */
    public boolean isIswritten() {
        return iswritten;
    }

    /**
     * Sets the value of the iswritten property.
     * 
     */
    public void setIswritten(boolean value) {
        this.iswritten = value;
    }

    /**
     * Gets the value of the markings property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarkings() {
        return markings;
    }

    /**
     * Sets the value of the markings property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarkings(String value) {
        this.markings = value;
    }

    /**
     * Gets the value of the monograph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonograph() {
        return monograph;
    }

    /**
     * Sets the value of the monograph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonograph(String value) {
        this.monograph = value;
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
     * Gets the value of the productGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductGroup() {
        return productGroup;
    }

    /**
     * Sets the value of the productGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductGroup(String value) {
        this.productGroup = value;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchedule(String value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShape(String value) {
        this.shape = value;
    }

  //CR100 start
    /**
     * Gets the value of the tradeNameEnglish property.
     * 
     */
    public String isTradeNameEnglish() {
        return tradeNameEnglish;
    }

    /**
     * Sets the value of the tradeNameEnglish property.
     * 
     */
    public void setTradeNameEnglish(String value) {
        this.tradeNameEnglish = value;
    }

    /**
     * Gets the value of the tradeNameFrench property.
     * 
     */
    public String isTradeNameFrench() {
        return tradeNameFrench;
    }

    /**
     * Sets the value of the tradeNameFrench property.
     * 
     */
    public void setTradeNameFrench(String value) {
        this.tradeNameFrench = value;
    }
  //CR100 end

}
