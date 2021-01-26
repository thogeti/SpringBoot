
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="administrationForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="administrationPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="administrationPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="authoritative" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="batchFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="daysSupplyPrescribed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="discontinuedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenses" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}dispense" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="drugTotalDaysOfSupplyCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="drugTrialDaysOfSupplyCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="drugTrialFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="drugTrialQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fillStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fillStatusEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="firstDispenseInstruction" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}dispense" minOccurs="0"/>
 *         &lt;element name="firstSummaryDispense" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}dispense" minOccurs="0"/>
 *         &lt;element name="holdEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="holdReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inferredPrescriptionFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastSummaryDispense" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}dispense" minOccurs="0"/>
 *         &lt;element name="legacyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="noMoreDispensesAllowedFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="noSubstitutionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfRefills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="prescribedCompound" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}compound" minOccurs="0"/>
 *         &lt;element name="prescribedDrug" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}drug" minOccurs="0"/>
 *          &lt;element name="prescribedPack" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}pack" minOccurs="0"/>
 *           &lt;element name="prescriber" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}prescriber" minOccurs="0"/>
 *         &lt;element name="prescriptionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="prescriptionDispensableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="prescriptionExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="prescriptionNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="prescriptionValidFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantityDispensed" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantityPrescribed" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="refillsRemainingCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remainingQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="renewalReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsiblePerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resumeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SIGAdditionalInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SIGCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SIGDescriptionPatientLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusChangeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusChangeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="substitutionInitiator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalQuantityAuthorized" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transferableCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="treatmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateEffectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updateEffectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prescription", propOrder = {
    "administrationForm",
    "administrationPeriodEndDate",
    "administrationPeriodStartDate",
    "isAuthoritative",//TE97_JAXB modified to isAuthoritative
    "batchFlag",
    "consumerId",
    "createTimestamp",
    "daysSupplyPrescribed",
    "discontinuedReasonCode",
    "dispenses",
    "drugTotalDaysOfSupplyCount",
    "drugTrialDaysOfSupplyCount",
    "drugTrialFlag",
    "drugTrialQuantity",
    "fillStatusCode",
    "fillStatusEffectiveDate",
    "fillStatusSubCode",//APR.DPHARM.023  TE97_023 added
    "firstDispenseInstruction",
    "firstSummaryDispense",
    "holdEndDate",
    "holdReasonCode",
    "inferredPrescriptionFlag",
    "lastSummaryDispense",
    "legacyFlag",
    "linkedrx",    //TE97_JAXB Modved after legacy Flag
    "noMoreDispensesAllowedFlag",
    "noSubstitutionReason",
    "numberOfRefills",
    "prescribedCompound",
    "prescribedDrug",
    "prescribedPack",
    "prescriber",
    "prescriptionDate",
    "prescriptionDispensableFlag",
    "prescriptionExpirationDate",
    "prescriptionNumber",
    "prescriptionValidFromDate",
    "producerId",
    "quantityDispensed",
    "quantityPrescribed",
    "refillsRemainingCount",
    "remainingQuantity",
    "renewalReasonCode",
    "responsiblePerson",
    "resumeReasonCode",
    "sigAdditionalInformation",
    "sigCode",
    "sigDescriptionPatientLanguage",
    "statusChangeReasonCode",
    "statusChangeType",
    "substitutionInitiator",
    "totalQuantityAuthorized",
    "transferableCode",
    "treatmentType",
    "updateEffectiveEndDate",
    "updateEffectiveStartDate",
    "updateTimestamp",
    "forwardRx"
})
public class Prescription {

    protected String administrationForm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodStartDate;
    protected boolean isAuthoritative; //TE97_JAXB modified to isAuthoritative
    protected boolean batchFlag;
    protected String consumerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected Integer daysSupplyPrescribed;
    protected String discontinuedReasonCode;
    @XmlElement(nillable = true)
    protected List<Dispense> dispenses;
    protected Integer drugTotalDaysOfSupplyCount;
    protected Integer drugTrialDaysOfSupplyCount;
    protected boolean drugTrialFlag;
    protected Integer drugTrialQuantity;
    protected String fillStatusCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fillStatusEffectiveDate;
    protected String fillStatusSubCode; //APR.DPHARM.023  TE97_023 added
    protected Dispense firstDispenseInstruction;
    protected Dispense firstSummaryDispense;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar holdEndDate;
    protected String holdReasonCode;
    protected boolean inferredPrescriptionFlag;
    protected Dispense lastSummaryDispense;
    protected boolean legacyFlag;
    protected boolean noMoreDispensesAllowedFlag;
    protected String noSubstitutionReason;
    protected Integer numberOfRefills;
    protected Compound prescribedCompound;
    protected Drug prescribedDrug;
    protected Pack prescribedPack;
    protected Prescriber prescriber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionDate;
    protected boolean prescriptionDispensableFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionExpirationDate;
    protected Integer prescriptionNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionValidFromDate;
    protected String producerId;
    protected Double quantityDispensed;
    protected Double quantityPrescribed;
    protected Integer refillsRemainingCount;
    protected Double remainingQuantity;
    protected String renewalReasonCode;
    protected String responsiblePerson;
    protected String resumeReasonCode;
    @XmlElement(name = "SIGAdditionalInformation")
    protected String sigAdditionalInformation;
    @XmlElement(name = "SIGCode")
    protected String sigCode;
    @XmlElement(name = "SIGDescriptionPatientLanguage")
    protected String sigDescriptionPatientLanguage;
    protected String statusChangeReasonCode;
    protected String statusChangeType;
    protected String substitutionInitiator;
    protected Double totalQuantityAuthorized;
    protected boolean transferableCode;
    protected String treatmentType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateEffectiveEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateEffectiveStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;
    protected Prescription linkedrx; //TE97_JAXB
    protected ForwardRx forwardRx; 
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
     * Gets the value of the administrationPeriodEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdministrationPeriodEndDate() {
        return administrationPeriodEndDate;
    }

    /**
     * Sets the value of the administrationPeriodEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdministrationPeriodEndDate(XMLGregorianCalendar value) {
        this.administrationPeriodEndDate = value;
    }

    /**
     * Gets the value of the administrationPeriodStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdministrationPeriodStartDate() {
        return administrationPeriodStartDate;
    }

    /**
     * Sets the value of the administrationPeriodStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdministrationPeriodStartDate(XMLGregorianCalendar value) {
        this.administrationPeriodStartDate = value;
    }

  //TE97_JAXB modified to IsAuthoritative start
    /**
     * Gets the value of the isAuthoritative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAuthoritative() {
        return isAuthoritative;
    }

    /**
     * Sets the value of the isAuthoritative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAuthoritative(Boolean value) {
        this.isAuthoritative = value;
    }
    /**
     * Gets the value of the batchFlag property.
     * 
     */
    public boolean isBatchFlag() {
        return batchFlag;
    }

    /**
     * Sets the value of the batchFlag property.
     * 
     */
    public void setBatchFlag(boolean value) {
        this.batchFlag = value;
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
     * Gets the value of the daysSupplyPrescribed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDaysSupplyPrescribed() {
        return daysSupplyPrescribed;
    }

    /**
     * Sets the value of the daysSupplyPrescribed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDaysSupplyPrescribed(Integer value) {
        this.daysSupplyPrescribed = value;
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
     * Gets the value of the dispenses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispenses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDispenses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dispense }
     * 
     * 
     */
    public List<Dispense> getDispenses() {
        if (dispenses == null) {
            dispenses = new ArrayList<Dispense>();
        }
        return this.dispenses;
    }

    /**
     * Gets the value of the drugTotalDaysOfSupplyCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDrugTotalDaysOfSupplyCount() {
        return drugTotalDaysOfSupplyCount;
    }

    /**
     * Sets the value of the drugTotalDaysOfSupplyCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDrugTotalDaysOfSupplyCount(Integer value) {
        this.drugTotalDaysOfSupplyCount = value;
    }

    /**
     * Gets the value of the drugTrialDaysOfSupplyCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDrugTrialDaysOfSupplyCount() {
        return drugTrialDaysOfSupplyCount;
    }

    /**
     * Sets the value of the drugTrialDaysOfSupplyCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDrugTrialDaysOfSupplyCount(Integer value) {
        this.drugTrialDaysOfSupplyCount = value;
    }

    /**
     * Gets the value of the drugTrialFlag property.
     * 
     */
    public boolean isDrugTrialFlag() {
        return drugTrialFlag;
    }

    /**
     * Sets the value of the drugTrialFlag property.
     * 
     */
    public void setDrugTrialFlag(boolean value) {
        this.drugTrialFlag = value;
    }

    /**
     * Gets the value of the drugTrialQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDrugTrialQuantity() {
        return drugTrialQuantity;
    }

    /**
     * Sets the value of the drugTrialQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDrugTrialQuantity(Integer value) {
        this.drugTrialQuantity = value;
    }

    /**
     * Gets the value of the fillStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillStatusCode() {
        return fillStatusCode;
    }

    /**
     * Sets the value of the fillStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillStatusCode(String value) {
        this.fillStatusCode = value;
    }

    /**
     * Gets the value of the fillStatusEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFillStatusEffectiveDate() {
        return fillStatusEffectiveDate;
    }

    /**
     * Sets the value of the fillStatusEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFillStatusEffectiveDate(XMLGregorianCalendar value) {
        this.fillStatusEffectiveDate = value;
    }

    /**
     * Gets the value of the firstDispenseInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link Dispense }
     *     
     */
    public Dispense getFirstDispenseInstruction() {
        return firstDispenseInstruction;
    }

    /**
     * Sets the value of the firstDispenseInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispense }
     *     
     */
    public void setFirstDispenseInstruction(Dispense value) {
        this.firstDispenseInstruction = value;
    }

    /**
     * Gets the value of the firstSummaryDispense property.
     * 
     * @return
     *     possible object is
     *     {@link Dispense }
     *     
     */
    public Dispense getFirstSummaryDispense() {
        return firstSummaryDispense;
    }

    /**
     * Sets the value of the firstSummaryDispense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispense }
     *     
     */
    public void setFirstSummaryDispense(Dispense value) {
        this.firstSummaryDispense = value;
    }

    /**
     * Gets the value of the holdEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHoldEndDate() {
        return holdEndDate;
    }

    /**
     * Sets the value of the holdEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHoldEndDate(XMLGregorianCalendar value) {
        this.holdEndDate = value;
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
     * Gets the value of the inferredPrescriptionFlag property.
     * 
     */
    public boolean isInferredPrescriptionFlag() {
        return inferredPrescriptionFlag;
    }

    /**
     * Sets the value of the inferredPrescriptionFlag property.
     * 
     */
    public void setInferredPrescriptionFlag(boolean value) {
        this.inferredPrescriptionFlag = value;
    }

    /**
     * Gets the value of the lastSummaryDispense property.
     * 
     * @return
     *     possible object is
     *     {@link Dispense }
     *     
     */
    public Dispense getLastSummaryDispense() {
        return lastSummaryDispense;
    }

    /**
     * Sets the value of the lastSummaryDispense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispense }
     *     
     */
    public void setLastSummaryDispense(Dispense value) {
        this.lastSummaryDispense = value;
    }

    /**
     * Gets the value of the legacyFlag property.
     * 
     */
    public boolean isLegacyFlag() {
        return legacyFlag;
    }

    /**
     * Sets the value of the legacyFlag property.
     * 
     */
    public void setLegacyFlag(boolean value) {
        this.legacyFlag = value;
    }

    /**
     * Gets the value of the noMoreDispensesAllowedFlag property.
     * 
     */
    public boolean isNoMoreDispensesAllowedFlag() {
        return noMoreDispensesAllowedFlag;
    }

    /**
     * Sets the value of the noMoreDispensesAllowedFlag property.
     * 
     */
    public void setNoMoreDispensesAllowedFlag(boolean value) {
        this.noMoreDispensesAllowedFlag = value;
    }

    /**
     * Gets the value of the noSubstitutionReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoSubstitutionReason() {
        return noSubstitutionReason;
    }

    /**
     * Sets the value of the noSubstitutionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoSubstitutionReason(String value) {
        this.noSubstitutionReason = value;
    }

    /**
     * Gets the value of the numberOfRefills property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfRefills() {
        return numberOfRefills;
    }

    /**
     * Sets the value of the numberOfRefills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfRefills(Integer value) {
        this.numberOfRefills = value;
    }

    /**
     * Gets the value of the prescribedCompound property.
     * 
     * @return
     *     possible object is
     *     {@link Compound }
     *     
     */
    public Compound getPrescribedCompound() {
        return prescribedCompound;
    }

    /**
     * Sets the value of the prescribedCompound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compound }
     *     
     */
    public void setPrescribedCompound(Compound value) {
        this.prescribedCompound = value;
    }

    /**
     * Gets the value of the prescribedDrug property.
     * 
     * @return
     *     possible object is
     *     {@link Drug }
     *     
     */
    public Drug getPrescribedDrug() {
        return prescribedDrug;
    }

    /**
     * Sets the value of the prescribedDrug property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drug }
     *     
     */
    public void setPrescribedDrug(Drug value) {
        this.prescribedDrug = value;
    }

    /**
     * Gets the value of the prescriptionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrescriptionDate() {
        return prescriptionDate;
    }

    /**
     * Sets the value of the prescriptionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrescriptionDate(XMLGregorianCalendar value) {
        this.prescriptionDate = value;
    }

    /**
     * Gets the value of the prescriptionDispensableFlag property.
     * 
     */
    public boolean isPrescriptionDispensableFlag() {
        return prescriptionDispensableFlag;
    }

    /**
     * Sets the value of the prescriptionDispensableFlag property.
     * 
     */
    public void setPrescriptionDispensableFlag(boolean value) {
        this.prescriptionDispensableFlag = value;
    }

    /**
     * Gets the value of the prescriptionExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrescriptionExpirationDate() {
        return prescriptionExpirationDate;
    }

    /**
     * Sets the value of the prescriptionExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrescriptionExpirationDate(XMLGregorianCalendar value) {
        this.prescriptionExpirationDate = value;
    }

    /**
     * Gets the value of the prescriptionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrescriptionNumber() {
        return prescriptionNumber;
    }

    /**
     * Sets the value of the prescriptionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrescriptionNumber(Integer value) {
        this.prescriptionNumber = value;
    }

    /**
     * Gets the value of the prescriptionValidFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrescriptionValidFromDate() {
        return prescriptionValidFromDate;
    }

    /**
     * Sets the value of the prescriptionValidFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrescriptionValidFromDate(XMLGregorianCalendar value) {
        this.prescriptionValidFromDate = value;
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
     * Gets the value of the quantityPrescribed property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityPrescribed() {
        return quantityPrescribed;
    }

    /**
     * Sets the value of the quantityPrescribed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityPrescribed(Double value) {
        this.quantityPrescribed = value;
    }

    /**
     * Gets the value of the refillsRemainingCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRefillsRemainingCount() {
        return refillsRemainingCount;
    }

    /**
     * Sets the value of the refillsRemainingCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRefillsRemainingCount(Integer value) {
        this.refillsRemainingCount = value;
    }

    /**
     * Gets the value of the remainingQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRemainingQuantity() {
        return remainingQuantity;
    }

    /**
     * Sets the value of the remainingQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRemainingQuantity(Double value) {
        this.remainingQuantity = value;
    }

    /**
     * Gets the value of the renewalReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalReasonCode() {
        return renewalReasonCode;
    }

    /**
     * Sets the value of the renewalReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalReasonCode(String value) {
        this.renewalReasonCode = value;
    }

    /**
     * Gets the value of the responsiblePerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    /**
     * Sets the value of the responsiblePerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsiblePerson(String value) {
        this.responsiblePerson = value;
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
     * Gets the value of the sigAdditionalInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIGAdditionalInformation() {
        return sigAdditionalInformation;
    }

    /**
     * Sets the value of the sigAdditionalInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIGAdditionalInformation(String value) {
        this.sigAdditionalInformation = value;
    }

    /**
     * Gets the value of the sigCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIGCode() {
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
    public void setSIGCode(String value) {
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
    public String getSIGDescriptionPatientLanguage() {
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
    public void setSIGDescriptionPatientLanguage(String value) {
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
     * Gets the value of the substitutionInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutionInitiator() {
        return substitutionInitiator;
    }

    /**
     * Sets the value of the substitutionInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutionInitiator(String value) {
        this.substitutionInitiator = value;
    }

    /**
     * Gets the value of the totalQuantityAuthorized property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalQuantityAuthorized() {
        return totalQuantityAuthorized;
    }

    /**
     * Sets the value of the totalQuantityAuthorized property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalQuantityAuthorized(Double value) {
        this.totalQuantityAuthorized = value;
    }

    /**
     * Gets the value of the transferableCode property.
     * 
     */
    public boolean isTransferableCode() {
        return transferableCode;
    }

    /**
     * Sets the value of the transferableCode property.
     * 
     */
    public void setTransferableCode(boolean value) {
        this.transferableCode = value;
    }

    /**
     * Gets the value of the treatmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatmentType() {
        return treatmentType;
    }

    /**
     * Sets the value of the treatmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatmentType(String value) {
        this.treatmentType = value;
    }

    /**
     * Gets the value of the updateEffectiveEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateEffectiveEndDate() {
        return updateEffectiveEndDate;
    }

    /**
     * Sets the value of the updateEffectiveEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateEffectiveEndDate(XMLGregorianCalendar value) {
        this.updateEffectiveEndDate = value;
    }

    /**
     * Gets the value of the updateEffectiveStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateEffectiveStartDate() {
        return updateEffectiveStartDate;
    }

    /**
     * Sets the value of the updateEffectiveStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateEffectiveStartDate(XMLGregorianCalendar value) {
        this.updateEffectiveStartDate = value;
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

    
    //TE97_JAXB started
    public Prescription getLinkedrx() {
		return linkedrx;
	}

	public void setLinkedrx(Prescription linkedrx) {
		this.linkedrx = linkedrx;
	}
	//TE97_JAXB ended
	
	//APR.DPHARM.023  TE97_023 start
  	public String getFillStatusSubCode() {
  		return fillStatusSubCode;
  	}

  	public void setFillStatusSubCode(String fillStatusSubCode) {
  		this.fillStatusSubCode = fillStatusSubCode;
  	}

	public Pack getPrescribedPack() {
		return prescribedPack;
	}

	public void setPrescribedPack(Pack prescribedPack) {
		this.prescribedPack = prescribedPack;
	}

	public Prescriber getPrescriber() {
		return prescriber;
	}

	public void setPrescriber(Prescriber prescriber) {
		this.prescriber = prescriber;
	}

	

	public ForwardRx getForwardRx() {
		return forwardRx;
	}

	public void setForwardRx(ForwardRx forwardRx) {
		this.forwardRx = forwardRx;
	}
  	
  //APR.DPHARM.023  TE97_023 end

}
