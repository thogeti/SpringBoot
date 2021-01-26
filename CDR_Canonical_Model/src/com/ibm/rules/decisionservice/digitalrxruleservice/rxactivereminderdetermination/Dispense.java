
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.*;


/**
 * <p>Java class for dispense complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dispense">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="administrationForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="administrationPeriodEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="administrationPeriodStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="administrationRouteCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compound" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}compound" minOccurs="0"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="daysInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="daysSupply" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="discontinuedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenseFulfillmentRefusalReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenseTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dispenseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dosageInstructionExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="drugExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="drugLotNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fillStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="holdReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="packageForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pickupTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="previousDispenseDaysSupply" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionalService" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}professionalService" minOccurs="0"/>
 *         &lt;element name="quantityDispensed" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantityRemaining" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="resumeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sigCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigDescriptionPatientLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusChangeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusChangeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="substitutionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmountPaid" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transactionCancellationReasonText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="transactionSourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispense", propOrder = {
    "administrationForm",
    "administrationPeriodEnd",
    "administrationPeriodStart",
    "administrationRouteCode",
    "compound",
    "consumerId",
    "createTimestamp",
    "daysInterval",
    "daysSupply",
    "discontinuedReasonCode",
    "dispenseFulfillmentRefusalReasonCode",
    "dispenseTime",
    "dispenseType",
    "dosageInstructionExpiryDate",
    "drugExpirationDate",
    "drugLotNumber",
    "fillStatus",
    "holdReasonCode",
    "homeDelivery",
    "lastUpdate",
    "packageForm",
    "pickupTime",
    "pickupTime2",
    "previousDispenseDaysSupply",
    "producerId",
    "professionalService",
    "quantityDispensed",
    "quantityRemaining",
    "resumeReasonCode",
    "sequenceNumber",
    "sigCode",
    "sigDescriptionPatientLanguage",
    "statusChangeReasonCode",
    "statusChangeType",
    "substitutionReason",
    "totalAmountPaid",
    "transactionCancellationReasonText",
    "transactionNumber",
    "transactionSourceCode",
    "pharmacyChannel"
   
})
public class Dispense {

    protected String administrationForm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodEnd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodStart;
    protected String administrationRouteCode;
    protected Compound compound;
    protected String consumerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected Integer daysInterval;
    protected Integer daysSupply;
    protected String discontinuedReasonCode;
    protected String dispenseFulfillmentRefusalReasonCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dispenseTime;
    protected String dispenseType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dosageInstructionExpiryDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar drugExpirationDate;
    protected String drugLotNumber;
    protected String fillStatus;
    protected String holdReasonCode;
    protected boolean homeDelivery;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    protected String packageForm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupTime2;
    protected Integer previousDispenseDaysSupply;
    protected String producerId;
    protected ProfessionalService professionalService;
    protected Double quantityDispensed;
    protected Integer quantityRemaining;
    protected String resumeReasonCode;
    protected Integer sequenceNumber;
    protected String sigCode;
    protected String sigDescriptionPatientLanguage;
    protected String statusChangeReasonCode;
    protected String statusChangeType;
    protected String substitutionReason;
    protected Double totalAmountPaid;
    protected String transactionCancellationReasonText;
    protected Integer transactionNumber;
    protected String transactionSourceCode;
    protected  PharmacyChannel pharmacyChannel;
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

    public XMLGregorianCalendar getPickupTime2() {
		return pickupTime2;
	}

	public void setPickupTime2(XMLGregorianCalendar pickupTime2) {
		this.pickupTime2 = pickupTime2;
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
     * Gets the value of the fillStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillStatus() {
        return fillStatus;
    }

    /**
     * Sets the value of the fillStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillStatus(String value) {
        this.fillStatus = value;
    }

    /**
     * Gets the value of the holdReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldReasonCode() {
        return holdReasonCode;
    }

    /**
     * Sets the value of the holdReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldReasonCode(String value) {
        this.holdReasonCode = value;
    }

    /**
     * Gets the value of the homeDelivery property.
     * 
     */
    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    /**
     * Sets the value of the homeDelivery property.
     * 
     */
    public void setHomeDelivery(boolean value) {
        this.homeDelivery = value;
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
     * Gets the value of the quantityDispensed property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityDispensed() {
        return quantityDispensed;
    }

    /**
     * Sets the value of the quantityDispensed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityDispensed(Double value) {
        this.quantityDispensed = value;
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
     * Gets the value of the resumeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumeReasonCode() {
        return resumeReasonCode;
    }

    /**
     * Sets the value of the resumeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumeReasonCode(String value) {
        this.resumeReasonCode = value;
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
     * Gets the value of the substitutionReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutionReason() {
        return substitutionReason;
    }

    /**
     * Sets the value of the substitutionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutionReason(String value) {
        this.substitutionReason = value;
    }

    /**
     * Gets the value of the totalAmountPaid property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * Sets the value of the totalAmountPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalAmountPaid(Double value) {
        this.totalAmountPaid = value;
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
     * Gets the value of the transactionSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSourceCode() {
        return transactionSourceCode;
    }

    /**
     * Sets the value of the transactionSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSourceCode(String value) {
        this.transactionSourceCode = value;
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
}
