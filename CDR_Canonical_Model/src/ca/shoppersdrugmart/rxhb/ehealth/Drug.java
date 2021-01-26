
package ca.shoppersdrugmart.rxhb.ehealth;

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
 * <p>Java class for Drug complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Drug"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="branddrug" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="din" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dosageRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="monograph" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="formvariant" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="isdevice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="adultDosageMaximumQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="childDosageMinimumQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="adultDosageMinimumQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="childDosageMaximumQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="isimmunization" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isproprietary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isreportable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="iswritten" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="markings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="chemicalLabelNameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="chemicalLabelNameFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tradeNameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tradeNameFrench" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="drugDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="productGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="administrationRouteCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RouteOfAdmin" minOccurs="0"/&gt;
 *         &lt;element name="alternativename" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DrugNameAlternative" minOccurs="0"/&gt;
 *         &lt;element name="colour" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Colour" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dintype" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DINType" minOccurs="0"/&gt;
 *         &lt;element name="dosageform" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DosageForm" minOccurs="0"/&gt;
 *         &lt;element name="flavour" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Flavour" minOccurs="0"/&gt;
 *         &lt;element name="manufacturer" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Manufacturer" minOccurs="0"/&gt;
 *         &lt;element name="molecule" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Molecule" minOccurs="0"/&gt;
 *         &lt;element name="program" type="{http://shoppersdrugmart.ca/RxHB/eHealth}SpecialDrugProgram" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="recall" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManufacturerRecall" minOccurs="0"/&gt;
 *         &lt;element name="schedule" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Schedule" minOccurs="0"/&gt;
 *         &lt;element name="shape" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Shape" minOccurs="0"/&gt;
 *         &lt;element name="gpi" type="{http://shoppersdrugmart.ca/RxHB/eHealth}GPI" minOccurs="0"/&gt;
 *         &lt;element name="ring" type="{http://shoppersdrugmart.ca/RxHB/eHealth}FormularyRing" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Drug", propOrder = {
    "branddrug",
    "din",
    "consumerId",
    "dosageRange",
    "monograph",
    "producerId",
    "formvariant",
    "isdevice",
    "adultDosageMaximumQuantity",
    "childDosageMinimumQuantity",
    "adultDosageMinimumQuantity",
    "childDosageMaximumQuantity",
    "isimmunization",
    "isproprietary",
    "isreportable",
    "iswritten",
    "markings",
    "chemicalLabelNameEnglish",
    "chemicalLabelNameFrench",
    "tradeNameEnglish",
    "tradeNameFrench",
    "drugDescription",
    "productGroup",
    "administrationRouteCode",
    "alternativename",
    "colour",
    "dintype",
    "dosageform",
    "flavour",
    "manufacturer",
    "molecule",
    "program",
    "recall",
    "schedule",
    "shape",
    "gpi",
    "ring",
    "lastUpdateTimestamp"
})
public class Drug {

    protected String branddrug;
    protected String din;
    protected String consumerId;
    protected String dosageRange;
    protected String monograph;
    protected String producerId;
    protected Integer formvariant;
    @XmlElement(defaultValue = "false")
    protected Boolean isdevice;
    protected BigDecimal adultDosageMaximumQuantity;
    protected BigDecimal childDosageMinimumQuantity;
    protected BigDecimal adultDosageMinimumQuantity;
    protected BigDecimal childDosageMaximumQuantity;
    @XmlElement(defaultValue = "false")
    protected Boolean isimmunization;
    @XmlElement(defaultValue = "false")
    protected Boolean isproprietary;
    @XmlElement(defaultValue = "false")
    protected Boolean isreportable;
    @XmlElement(defaultValue = "false")
    protected Boolean iswritten;
    protected String markings;
    protected String chemicalLabelNameEnglish;
    protected String chemicalLabelNameFrench;
    protected String tradeNameEnglish;
    protected String tradeNameFrench;
    protected String drugDescription;
    protected String productGroup;
    @XmlSchemaType(name = "string")
    protected RouteOfAdmin administrationRouteCode;
    protected DrugNameAlternative alternativename;
    @XmlSchemaType(name = "string")
    protected List<Colour> colour;
    @XmlSchemaType(name = "string")
    protected DINType dintype;
    protected DosageForm dosageform;
    @XmlSchemaType(name = "string")
    protected Flavour flavour;
    protected Manufacturer manufacturer;
    protected Molecule molecule;
    @XmlSchemaType(name = "string")
    protected List<SpecialDrugProgram> program;
    protected ManufacturerRecall recall;
    @XmlSchemaType(name = "string")
    protected Schedule schedule;
    @XmlSchemaType(name = "string")
    protected Shape shape;
    protected GPI gpi;
    protected List<FormularyRing> ring;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;

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
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsdevice() {
        return isdevice;
    }

    /**
     * Sets the value of the isdevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsdevice(Boolean value) {
        this.isdevice = value;
    }

    /**
     * Gets the value of the adultDosageMaximumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultDosageMaximumQuantity() {
        return adultDosageMaximumQuantity;
    }

    /**
     * Sets the value of the adultDosageMaximumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultDosageMaximumQuantity(BigDecimal value) {
        this.adultDosageMaximumQuantity = value;
    }

    /**
     * Gets the value of the childDosageMinimumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildDosageMinimumQuantity() {
        return childDosageMinimumQuantity;
    }

    /**
     * Sets the value of the childDosageMinimumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildDosageMinimumQuantity(BigDecimal value) {
        this.childDosageMinimumQuantity = value;
    }

    /**
     * Gets the value of the adultDosageMinimumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultDosageMinimumQuantity() {
        return adultDosageMinimumQuantity;
    }

    /**
     * Sets the value of the adultDosageMinimumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultDosageMinimumQuantity(BigDecimal value) {
        this.adultDosageMinimumQuantity = value;
    }

    /**
     * Gets the value of the childDosageMaximumQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildDosageMaximumQuantity() {
        return childDosageMaximumQuantity;
    }

    /**
     * Sets the value of the childDosageMaximumQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildDosageMaximumQuantity(BigDecimal value) {
        this.childDosageMaximumQuantity = value;
    }

    /**
     * Gets the value of the isimmunization property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsimmunization() {
        return isimmunization;
    }

    /**
     * Sets the value of the isimmunization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsimmunization(Boolean value) {
        this.isimmunization = value;
    }

    /**
     * Gets the value of the isproprietary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsproprietary() {
        return isproprietary;
    }

    /**
     * Sets the value of the isproprietary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsproprietary(Boolean value) {
        this.isproprietary = value;
    }

    /**
     * Gets the value of the isreportable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsreportable() {
        return isreportable;
    }

    /**
     * Sets the value of the isreportable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsreportable(Boolean value) {
        this.isreportable = value;
    }

    /**
     * Gets the value of the iswritten property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIswritten() {
        return iswritten;
    }

    /**
     * Sets the value of the iswritten property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIswritten(Boolean value) {
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
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChemicalLabelNameFrench() {
        return chemicalLabelNameFrench;
    }

    /**
     * Sets the value of the chemicalLabelNameFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChemicalLabelNameFrench(String value) {
        this.chemicalLabelNameFrench = value;
    }

    /**
     * Gets the value of the tradeNameEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeNameEnglish() {
        return tradeNameEnglish;
    }

    /**
     * Sets the value of the tradeNameEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeNameEnglish(String value) {
        this.tradeNameEnglish = value;
    }

    /**
     * Gets the value of the tradeNameFrench property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeNameFrench() {
        return tradeNameFrench;
    }

    /**
     * Sets the value of the tradeNameFrench property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeNameFrench(String value) {
        this.tradeNameFrench = value;
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
     * Gets the value of the alternativename property.
     * 
     * @return
     *     possible object is
     *     {@link DrugNameAlternative }
     *     
     */
    public DrugNameAlternative getAlternativename() {
        return alternativename;
    }

    /**
     * Sets the value of the alternativename property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugNameAlternative }
     *     
     */
    public void setAlternativename(DrugNameAlternative value) {
        this.alternativename = value;
    }

    /**
     * Gets the value of the colour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Colour }
     * 
     * 
     */
    public List<Colour> getColour() {
        if (colour == null) {
            colour = new ArrayList<Colour>();
        }
        return this.colour;
    }

    /**
     * Gets the value of the dintype property.
     * 
     * @return
     *     possible object is
     *     {@link DINType }
     *     
     */
    public DINType getDintype() {
        return dintype;
    }

    /**
     * Sets the value of the dintype property.
     * 
     * @param value
     *     allowed object is
     *     {@link DINType }
     *     
     */
    public void setDintype(DINType value) {
        this.dintype = value;
    }

    /**
     * Gets the value of the dosageform property.
     * 
     * @return
     *     possible object is
     *     {@link DosageForm }
     *     
     */
    public DosageForm getDosageform() {
        return dosageform;
    }

    /**
     * Sets the value of the dosageform property.
     * 
     * @param value
     *     allowed object is
     *     {@link DosageForm }
     *     
     */
    public void setDosageform(DosageForm value) {
        this.dosageform = value;
    }

    /**
     * Gets the value of the flavour property.
     * 
     * @return
     *     possible object is
     *     {@link Flavour }
     *     
     */
    public Flavour getFlavour() {
        return flavour;
    }

    /**
     * Sets the value of the flavour property.
     * 
     * @param value
     *     allowed object is
     *     {@link Flavour }
     *     
     */
    public void setFlavour(Flavour value) {
        this.flavour = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link Manufacturer }
     *     
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Manufacturer }
     *     
     */
    public void setManufacturer(Manufacturer value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the molecule property.
     * 
     * @return
     *     possible object is
     *     {@link Molecule }
     *     
     */
    public Molecule getMolecule() {
        return molecule;
    }

    /**
     * Sets the value of the molecule property.
     * 
     * @param value
     *     allowed object is
     *     {@link Molecule }
     *     
     */
    public void setMolecule(Molecule value) {
        this.molecule = value;
    }

    /**
     * Gets the value of the program property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the program property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgram().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpecialDrugProgram }
     * 
     * 
     */
    public List<SpecialDrugProgram> getProgram() {
        if (program == null) {
            program = new ArrayList<SpecialDrugProgram>();
        }
        return this.program;
    }

    /**
     * Gets the value of the recall property.
     * 
     * @return
     *     possible object is
     *     {@link ManufacturerRecall }
     *     
     */
    public ManufacturerRecall getRecall() {
        return recall;
    }

    /**
     * Sets the value of the recall property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManufacturerRecall }
     *     
     */
    public void setRecall(ManufacturerRecall value) {
        this.recall = value;
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
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link Shape }
     *     
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shape }
     *     
     */
    public void setShape(Shape value) {
        this.shape = value;
    }

    /**
     * Gets the value of the gpi property.
     * 
     * @return
     *     possible object is
     *     {@link GPI }
     *     
     */
    public GPI getGpi() {
        return gpi;
    }

    /**
     * Sets the value of the gpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link GPI }
     *     
     */
    public void setGpi(GPI value) {
        this.gpi = value;
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

    /**
     * Gets the value of the lastUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    /**
     * Sets the value of the lastUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateTimestamp(XMLGregorianCalendar value) {
        this.lastUpdateTimestamp = value;
    }

}
