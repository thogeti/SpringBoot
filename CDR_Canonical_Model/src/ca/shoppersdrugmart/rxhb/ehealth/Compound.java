
package ca.shoppersdrugmart.rxhb.ehealth;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A combination of drugs mixed together according to a particular set of specifications and instructions.
 * 
 * <p>Java class for Compound complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Compound"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UofMQuantityConversionFactor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="nameFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="preperationdirections" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="compoundIngredients" type="{http://shoppersdrugmart.ca/RxHB/eHealth}CompoundIngredient" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dosageForm" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DosageForm" minOccurs="0"/&gt;
 *         &lt;element name="schedule" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Schedule" minOccurs="0"/&gt;
 *         &lt;element name="administrationRouteCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RouteOfAdmin" minOccurs="0"/&gt;
 *         &lt;element name="totalPackageQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="totalPackageUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Compound", propOrder = {
    "unitOfMeasure",
    "uofMQuantityConversionFactor",
    "nameFrench",
    "nameEnglish",
    "preperationdirections",
    "consumerId",
    "producerId",
    "quantity",
    "compoundIngredients",
    "dosageForm",
    "schedule",
    "administrationRouteCode",
    "totalPackageQuantity",
    "totalPackageUnit"
})
public class Compound {

    @XmlElement(name = "UnitOfMeasure")
    protected String unitOfMeasure;
    @XmlElement(name = "UofMQuantityConversionFactor")
    protected BigInteger uofMQuantityConversionFactor;
    protected String nameFrench;
    protected String nameEnglish;
    protected String preperationdirections;
    protected String consumerId;
    protected String producerId;
    protected BigDecimal quantity;
    protected List<CompoundIngredient> compoundIngredients;
    protected DosageForm dosageForm;
    @XmlSchemaType(name = "string")
    protected Schedule schedule;
    @XmlSchemaType(name = "string")
    protected RouteOfAdmin administrationRouteCode;
    protected BigDecimal totalPackageQuantity;
    protected String totalPackageUnit;

    /**
     * Gets the value of the unitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Gets the value of the uofMQuantityConversionFactor property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUofMQuantityConversionFactor() {
        return uofMQuantityConversionFactor;
    }

    /**
     * Sets the value of the uofMQuantityConversionFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUofMQuantityConversionFactor(BigInteger value) {
        this.uofMQuantityConversionFactor = value;
    }

    /**
     * Gets the value of the nameFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameFrench() {
        return nameFrench;
    }

    /**
     * Sets the value of the nameFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameFrench(String value) {
        this.nameFrench = value;
    }

    /**
     * Gets the value of the nameEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameEnglish() {
        return nameEnglish;
    }

    /**
     * Sets the value of the nameEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameEnglish(String value) {
        this.nameEnglish = value;
    }

    /**
     * Gets the value of the preperationdirections property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreperationdirections() {
        return preperationdirections;
    }

    /**
     * Sets the value of the preperationdirections property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreperationdirections(String value) {
        this.preperationdirections = value;
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
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the compoundIngredients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compoundIngredients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompoundIngredients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompoundIngredient }
     * 
     * 
     */
    public List<CompoundIngredient> getCompoundIngredients() {
        if (compoundIngredients == null) {
            compoundIngredients = new ArrayList<CompoundIngredient>();
        }
        return this.compoundIngredients;
    }

    /**
     * Gets the value of the dosageForm property.
     * 
     * @return
     *     possible object is
     *     {@link DosageForm }
     *     
     */
    public DosageForm getDosageForm() {
        return dosageForm;
    }

    /**
     * Sets the value of the dosageForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link DosageForm }
     *     
     */
    public void setDosageForm(DosageForm value) {
        this.dosageForm = value;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setSchedule(Schedule value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the administrationRouteCode property.
     * 
     * @return
     *     possible object is
     *     {@link RouteOfAdmin }
     *     
     */
    public RouteOfAdmin getAdministrationRouteCode() {
        return administrationRouteCode;
    }

    /**
     * Sets the value of the administrationRouteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RouteOfAdmin }
     *     
     */
    public void setAdministrationRouteCode(RouteOfAdmin value) {
        this.administrationRouteCode = value;
    }

    /**
     * Gets the value of the totalPackageQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPackageQuantity() {
        return totalPackageQuantity;
    }

    /**
     * Sets the value of the totalPackageQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPackageQuantity(BigDecimal value) {
        this.totalPackageQuantity = value;
    }

    /**
     * Gets the value of the totalPackageUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPackageUnit() {
        return totalPackageUnit;
    }

    /**
     * Sets the value of the totalPackageUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPackageUnit(String value) {
        this.totalPackageUnit = value;
    }

}
