
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
 * <p>Java class for Dispense complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dispense"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="additionalDosageInstructionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="administrationPeriodEnd" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="daysSupply" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="administrationPeriodStart" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transactionCancellationReasonText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pickupTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="quantityDispensed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="totalAmountPaid" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="transactionNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dispenseTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="daysInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dosageInstructionExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="drugLotNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="drugExpirationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="sigCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dispenseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigDescriptionPatientLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="previousDispenseDaysSupply" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="quantityRemaining" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dispenseFulfillmentRefusalReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="discontinuedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="administrationForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusChangeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusChangeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isHomeDeliveryFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="pharmacyChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="compound" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Compound" minOccurs="0"/&gt;
 *         &lt;element name="initiator" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Initiator" minOccurs="0"/&gt;
 *         &lt;element name="serviceLocation" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Location" minOccurs="0"/&gt;
 *         &lt;element name="fillStatus" type="{http://shoppersdrugmart.ca/RxHB/eHealth}TxFillStatus" minOccurs="0"/&gt;
 *         &lt;element name="dispenser" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispenser" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="packageForm" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PackageForm" minOccurs="0"/&gt;
 *         &lt;element name="pack" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Pack" minOccurs="0"/&gt;
 *         &lt;element name="administrationRouteCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RouteOfAdmin" minOccurs="0"/&gt;
 *         &lt;element name="dosageForm" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DosageForm" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="transactionSourceCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}TransactionSourceChannel" minOccurs="0"/&gt;
 *         &lt;element name="supervisor" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Supervisor" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="professionalService" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ProfessionalService" minOccurs="0"/&gt;
 *         &lt;element name="holdReasonCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionHoldReasonTypeDisplay" minOccurs="0"/&gt;
 *         &lt;element name="resumeReasonCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionResumeReasonTypeDisplay" minOccurs="0"/&gt;
 *         &lt;element name="interchangeability" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Interchangeability" minOccurs="0"/&gt;
 *         &lt;element name="substitutionReason" type="{http://shoppersdrugmart.ca/RxHB/eHealth}SubstitutionReason" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dispense", propOrder = {
    "additionalDosageInstructionText",
    "administrationPeriodEnd",
    "daysSupply",
    "administrationPeriodStart",
    "createTimestamp",
    "consumerId",
    "producerId",
    "transactionCancellationReasonText",
    "lastUpdate",
    "pickupTime",
    "quantityDispensed",
    "totalAmountPaid",
    "transactionNumber",
    "dispenseTime",
    "daysInterval",
    "dosageInstructionExpiryDate",
    "drugLotNumber",
    "drugExpirationDate",
    "sigCode",
    "dispenseType",
    "sigDescriptionPatientLanguage",
    "sequenceNumber",
    "previousDispenseDaysSupply",
    "quantityRemaining",
    "dispenseFulfillmentRefusalReasonCode",
    "discontinuedReasonCode",
    "administrationForm",
    "statusChangeReasonCode",
    "statusChangeType",
    "isHomeDeliveryFlag",
    "pharmacyChannel",
    "compound",
    "initiator",
    "serviceLocation",
    "fillStatus",
    "dispenser",
    "packageForm",
    "pack",
    "administrationRouteCode",
    "dosageForm",
    "note",
    "transactionSourceCode",
    "supervisor",
    "recorder",
    "professionalService",
    "holdReasonCode",
    "resumeReasonCode",
    "interchangeability",
    "substitutionReason",
    "pickupTime2" //this is optional we added for to handle to database level column;
})
public class Dispense {

    protected String additionalDosageInstructionText;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar administrationPeriodEnd;
    protected Integer daysSupply;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar administrationPeriodStart;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected String consumerId;
    protected String producerId;
    protected String transactionCancellationReasonText;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupTime;
    protected BigDecimal quantityDispensed;
    protected BigDecimal totalAmountPaid;
    protected Integer transactionNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dispenseTime;
    protected Integer daysInterval;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dosageInstructionExpiryDate;
    protected String drugLotNumber;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar drugExpirationDate;
    protected String sigCode;
    protected String dispenseType;
    protected String sigDescriptionPatientLanguage;
    protected Integer sequenceNumber;
    protected Integer previousDispenseDaysSupply;
    protected Integer quantityRemaining;
    protected String dispenseFulfillmentRefusalReasonCode;
    protected String discontinuedReasonCode;
    protected String administrationForm;
    protected String statusChangeReasonCode;
    protected String statusChangeType;
    @XmlElement(defaultValue = "false")
    protected Boolean isHomeDeliveryFlag;
    protected PharmacyChannel pharmacyChannel;
    protected Compound compound;
    protected Initiator initiator;
    protected Location serviceLocation;
    @XmlSchemaType(name = "string")
    protected TxFillStatus fillStatus;
    protected List<Dispenser> dispenser;
    protected String packageForm;
    protected Pack pack;
    @XmlSchemaType(name = "string")
    protected RouteOfAdmin administrationRouteCode;
    protected DosageForm dosageForm;
    protected List<Note> note;
    @XmlSchemaType(name = "string")
    protected TransactionSourceChannel transactionSourceCode;
    protected Supervisor supervisor;
    protected Recorder recorder;
    protected ProfessionalService professionalService;
    protected PrescriptionHoldReasonTypeDisplay holdReasonCode;
    protected PrescriptionResumeReasonTypeDisplay resumeReasonCode;
    @XmlSchemaType(name = "string")
    protected Interchangeability interchangeability;
    @XmlSchemaType(name = "string")
    protected SubstitutionReason substitutionReason;
    protected XMLGregorianCalendar pickupTime2;
    /**
     * Gets the value of the additionalDosageInstructionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalDosageInstructionText() {
        return additionalDosageInstructionText;
    }

    /**
     * Sets the value of the additionalDosageInstructionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalDosageInstructionText(String value) {
        this.additionalDosageInstructionText = value;
    }

    /**
     * Gets the value of the administrationPeriodEnd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdministrationPeriodEnd() {
        return administrationPeriodEnd;
    }

    /**
     * Sets the value of the administrationPeriodEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdministrationPeriodEnd(XMLGregorianCalendar value) {
        this.administrationPeriodEnd = value;
    }

    /**
     * Gets the value of the daysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDaysSupply() {
        return daysSupply;
    }

    /**
     * Sets the value of the daysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDaysSupply(Integer value) {
        this.daysSupply = value;
    }

    /**
     * Gets the value of the administrationPeriodStart property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdministrationPeriodStart() {
        return administrationPeriodStart;
    }

    /**
     * Sets the value of the administrationPeriodStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdministrationPeriodStart(XMLGregorianCalendar value) {
        this.administrationPeriodStart = value;
    }

    /**
     * Gets the value of the createTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Sets the value of the createTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTimestamp(XMLGregorianCalendar value) {
        this.createTimestamp = value;
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
     * Gets the value of the transactionCancellationReasonText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionCancellationReasonText() {
        return transactionCancellationReasonText;
    }

    /**
     * Sets the value of the transactionCancellationReasonText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionCancellationReasonText(String value) {
        this.transactionCancellationReasonText = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Gets the value of the pickupTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickupTime() {
        return pickupTime;
    }

    /**
     * Sets the value of the pickupTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickupTime(XMLGregorianCalendar value) {
        this.pickupTime = value;
    }

    /**
     * Gets the value of the quantityDispensed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantityDispensed() {
        return quantityDispensed;
    }

    /**
     * Sets the value of the quantityDispensed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantityDispensed(BigDecimal value) {
        this.quantityDispensed = value;
    }

    /**
     * Gets the value of the totalAmountPaid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * Sets the value of the totalAmountPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmountPaid(BigDecimal value) {
        this.totalAmountPaid = value;
    }

    /**
     * Gets the value of the transactionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Sets the value of the transactionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTransactionNumber(Integer value) {
        this.transactionNumber = value;
    }

    /**
     * Gets the value of the dispenseTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDispenseTime() {
        return dispenseTime;
    }

    /**
     * Sets the value of the dispenseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDispenseTime(XMLGregorianCalendar value) {
        this.dispenseTime = value;
    }

    /**
     * Gets the value of the daysInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDaysInterval() {
        return daysInterval;
    }

    /**
     * Sets the value of the daysInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDaysInterval(Integer value) {
        this.daysInterval = value;
    }

    /**
     * Gets the value of the dosageInstructionExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDosageInstructionExpiryDate() {
        return dosageInstructionExpiryDate;
    }

    /**
     * Sets the value of the dosageInstructionExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDosageInstructionExpiryDate(XMLGregorianCalendar value) {
        this.dosageInstructionExpiryDate = value;
    }

    /**
     * Gets the value of the drugLotNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugLotNumber() {
        return drugLotNumber;
    }

    /**
     * Sets the value of the drugLotNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugLotNumber(String value) {
        this.drugLotNumber = value;
    }

    /**
     * Gets the value of the drugExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDrugExpirationDate() {
        return drugExpirationDate;
    }

    /**
     * Sets the value of the drugExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDrugExpirationDate(XMLGregorianCalendar value) {
        this.drugExpirationDate = value;
    }

    /**
     * Gets the value of the sigCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigCode() {
        return sigCode;
    }

    /**
     * Sets the value of the sigCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigCode(String value) {
        this.sigCode = value;
    }

    /**
     * Gets the value of the dispenseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispenseType() {
        return dispenseType;
    }

    /**
     * Sets the value of the dispenseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispenseType(String value) {
        this.dispenseType = value;
    }

    /**
     * Gets the value of the sigDescriptionPatientLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigDescriptionPatientLanguage() {
        return sigDescriptionPatientLanguage;
    }

    /**
     * Sets the value of the sigDescriptionPatientLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigDescriptionPatientLanguage(String value) {
        this.sigDescriptionPatientLanguage = value;
    }

    /**
     * Gets the value of the sequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSequenceNumber(Integer value) {
        this.sequenceNumber = value;
    }

    /**
     * Gets the value of the previousDispenseDaysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPreviousDispenseDaysSupply() {
        return previousDispenseDaysSupply;
    }

    /**
     * Sets the value of the previousDispenseDaysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPreviousDispenseDaysSupply(Integer value) {
        this.previousDispenseDaysSupply = value;
    }

    /**
     * Gets the value of the quantityRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantityRemaining() {
        return quantityRemaining;
    }

    /**
     * Sets the value of the quantityRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantityRemaining(Integer value) {
        this.quantityRemaining = value;
    }

    /**
     * Gets the value of the dispenseFulfillmentRefusalReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispenseFulfillmentRefusalReasonCode() {
        return dispenseFulfillmentRefusalReasonCode;
    }

    /**
     * Sets the value of the dispenseFulfillmentRefusalReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispenseFulfillmentRefusalReasonCode(String value) {
        this.dispenseFulfillmentRefusalReasonCode = value;
    }

    /**
     * Gets the value of the discontinuedReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscontinuedReasonCode() {
        return discontinuedReasonCode;
    }

    /**
     * Sets the value of the discontinuedReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscontinuedReasonCode(String value) {
        this.discontinuedReasonCode = value;
    }

    /**
     * Gets the value of the administrationForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationForm() {
        return administrationForm;
    }

    /**
     * Sets the value of the administrationForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationForm(String value) {
        this.administrationForm = value;
    }

    /**
     * Gets the value of the statusChangeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusChangeReasonCode() {
        return statusChangeReasonCode;
    }

    /**
     * Sets the value of the statusChangeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusChangeReasonCode(String value) {
        this.statusChangeReasonCode = value;
    }

    /**
     * Gets the value of the statusChangeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusChangeType() {
        return statusChangeType;
    }

    /**
     * Sets the value of the statusChangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusChangeType(String value) {
        this.statusChangeType = value;
    }

    /**
     * Gets the value of the isHomeDeliveryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsHomeDeliveryFlag() {
        return isHomeDeliveryFlag;
    }

    /**
     * Sets the value of the isHomeDeliveryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsHomeDeliveryFlag(Boolean value) {
        this.isHomeDeliveryFlag = value;
    }

    /**
     * Gets the value of the pharmacyChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getPharmacyChannel() {
        return pharmacyChannel;
    }

    /**
     * Sets the value of the pharmacyChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setPharmacyChannel(PharmacyChannel value) {
        this.pharmacyChannel = value;
    }

    /**
     * Gets the value of the compound property.
     * 
     * @return
     *     possible object is
     *     {@link Compound }
     *     
     */
    public Compound getCompound() {
        return compound;
    }

    /**
     * Sets the value of the compound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compound }
     *     
     */
    public void setCompound(Compound value) {
        this.compound = value;
    }

    /**
     * Gets the value of the initiator property.
     * 
     * @return
     *     possible object is
     *     {@link Initiator }
     *     
     */
    public Initiator getInitiator() {
        return initiator;
    }

    /**
     * Sets the value of the initiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Initiator }
     *     
     */
    public void setInitiator(Initiator value) {
        this.initiator = value;
    }

    /**
     * Gets the value of the serviceLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getServiceLocation() {
        return serviceLocation;
    }

    /**
     * Sets the value of the serviceLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setServiceLocation(Location value) {
        this.serviceLocation = value;
    }

    /**
     * Gets the value of the fillStatus property.
     * 
     * @return
     *     possible object is
     *     {@link TxFillStatus }
     *     
     */
    public TxFillStatus getFillStatus() {
        return fillStatus;
    }

    /**
     * Sets the value of the fillStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link TxFillStatus }
     *     
     */
    public void setFillStatus(TxFillStatus value) {
        this.fillStatus = value;
    }

    /**
     * Gets the value of the dispenser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispenser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDispenser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dispenser }
     * 
     * 
     */
    public List<Dispenser> getDispenser() {
        if (dispenser == null) {
            dispenser = new ArrayList<Dispenser>();
        }
        return this.dispenser;
    }

    /**
     * Gets the value of the packageForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageForm() {
        return packageForm;
    }

    /**
     * Sets the value of the packageForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageForm(String value) {
        this.packageForm = value;
    }

    /**
     * Gets the value of the pack property.
     * 
     * @return
     *     possible object is
     *     {@link Pack }
     *     
     */
    public Pack getPack() {
        return pack;
    }

    /**
     * Sets the value of the pack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pack }
     *     
     */
    public void setPack(Pack value) {
        this.pack = value;
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
     * Gets the value of the transactionSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionSourceChannel }
     *     
     */
    public TransactionSourceChannel getTransactionSourceCode() {
        return transactionSourceCode;
    }

    /**
     * Sets the value of the transactionSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionSourceChannel }
     *     
     */
    public void setTransactionSourceCode(TransactionSourceChannel value) {
        this.transactionSourceCode = value;
    }

    /**
     * Gets the value of the supervisor property.
     * 
     * @return
     *     possible object is
     *     {@link Supervisor }
     *     
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the value of the supervisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Supervisor }
     *     
     */
    public void setSupervisor(Supervisor value) {
        this.supervisor = value;
    }

    /**
     * Gets the value of the recorder property.
     * 
     * @return
     *     possible object is
     *     {@link Recorder }
     *     
     */
    public Recorder getRecorder() {
        return recorder;
    }

    /**
     * Sets the value of the recorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recorder }
     *     
     */
    public void setRecorder(Recorder value) {
        this.recorder = value;
    }

    /**
     * Gets the value of the professionalService property.
     * 
     * @return
     *     possible object is
     *     {@link ProfessionalService }
     *     
     */
    public ProfessionalService getProfessionalService() {
        return professionalService;
    }

    /**
     * Sets the value of the professionalService property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfessionalService }
     *     
     */
    public void setProfessionalService(ProfessionalService value) {
        this.professionalService = value;
    }

    /**
     * Gets the value of the holdReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionHoldReasonTypeDisplay }
     *     
     */
    public PrescriptionHoldReasonTypeDisplay getHoldReasonCode() {
        return holdReasonCode;
    }

    /**
     * Sets the value of the holdReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionHoldReasonTypeDisplay }
     *     
     */
    public void setHoldReasonCode(PrescriptionHoldReasonTypeDisplay value) {
        this.holdReasonCode = value;
    }

    /**
     * Gets the value of the resumeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionResumeReasonTypeDisplay }
     *     
     */
    public PrescriptionResumeReasonTypeDisplay getResumeReasonCode() {
        return resumeReasonCode;
    }

    /**
     * Sets the value of the resumeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionResumeReasonTypeDisplay }
     *     
     */
    public void setResumeReasonCode(PrescriptionResumeReasonTypeDisplay value) {
        this.resumeReasonCode = value;
    }

    /**
     * Gets the value of the interchangeability property.
     * 
     * @return
     *     possible object is
     *     {@link Interchangeability }
     *     
     */
    public Interchangeability getInterchangeability() {
        return interchangeability;
    }

    /**
     * Sets the value of the interchangeability property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interchangeability }
     *     
     */
    public void setInterchangeability(Interchangeability value) {
        this.interchangeability = value;
    }

    /**
     * Gets the value of the substitutionReason property.
     * 
     * @return
     *     possible object is
     *     {@link SubstitutionReason }
     *     
     */
    public SubstitutionReason getSubstitutionReason() {
        return substitutionReason;
    }

    /**
     * Sets the value of the substitutionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubstitutionReason }
     *     
     */
    public void setSubstitutionReason(SubstitutionReason value) {
        this.substitutionReason = value;
    }

	public XMLGregorianCalendar getPickupTime2() {
		return pickupTime2;
	}

	public void setPickupTime2(XMLGregorianCalendar pickupTime2) {
		this.pickupTime2 = pickupTime2;
	}

}
