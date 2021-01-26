
package ca.shoppersdrugmart.rxhb.ehealth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * If yes, there is a medical document related to this prescription in the possession of a dispensing authority.
 * 
 * <p>Java class for Prescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="updateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="isAuthoritative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quantityDispensed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="remainingQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="refillsRemainingCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="totalQuantityAuthorized" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="drugTotalDaysOfSupplyCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="drugTrialDaysOfSupplyCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="drugTrialFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="drugTrialQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="inferredPrescriptionFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="noSubstitutionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numberOfRefills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="renewalReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SIGAdditionalInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SIGCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SIGDescriptionPatientLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionDispensableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="fillStatusEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="legacyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="holdEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="transferableCode" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="administrationForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="administrationPeriodStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="statusChangeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusChangeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="updateEffectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionValidFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="administrationPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="daysInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="updateEffectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="createTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="daysSupplyPrescribed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="quantityPrescribed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="noMoreDispensesAllowedFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isBatchFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="serviceLocation" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Location" minOccurs="0"/&gt;
 *         &lt;element name="prescribedPack" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Pack" minOccurs="0"/&gt;
 *         &lt;element name="substitutionInitiator" type="{http://shoppersdrugmart.ca/RxHB/eHealth}SubstitutionInitiator" minOccurs="0"/&gt;
 *         &lt;element name="fillStatusCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RxFillStatus" minOccurs="0"/&gt;
 *         &lt;element name="linkedrx" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" minOccurs="0"/&gt;
 *         &lt;element name="patient" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Patient" minOccurs="0"/&gt;
 *         &lt;element name="prescriber" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescriber" minOccurs="0"/&gt;
 *         &lt;element name="treatmentType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}TreatmentType" minOccurs="0"/&gt;
 *         &lt;element name="dispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Source" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Source" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="prescribedDrug" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Drug" minOccurs="0"/&gt;
 *         &lt;element name="prescribedCompound" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Compound" minOccurs="0"/&gt;
 *         &lt;element name="discontinuedReasonCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}DiscontinuedReason" minOccurs="0"/&gt;
 *         &lt;element name="resumeReasonCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionResumeReason" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="holdReasonCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriptionHoldReasonTypeDisplay" minOccurs="0"/&gt;
 *         &lt;element name="responsiblePerson" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ResponsiblePerson" minOccurs="0"/&gt;
 *         &lt;element name="supervisor" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Supervisor" minOccurs="0"/&gt;
 *         &lt;element name="firstSummaryDispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="lastSummaryDispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="firstDispenseInstruction" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="fillStatusSubCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RxFillStatus" minOccurs="0"/&gt;
 *         &lt;element name="forwardRx" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" minOccurs="0"/&gt;
 *         &lt;element name="rxParams" type="{http://shoppersdrugmart.ca/RxHB/eHealth}RxParams" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prescription", propOrder = {
    "updateTimestamp",
    "isAuthoritative",
    "consumerId",
    "producerId",
    "quantityDispensed",
    "remainingQuantity",
    "prescriptionNumber",
    "refillsRemainingCount",
    "totalQuantityAuthorized",
    "drugTotalDaysOfSupplyCount",
    "drugTrialDaysOfSupplyCount",
    "drugTrialFlag",
    "drugTrialQuantity",
    "inferredPrescriptionFlag",
    "noSubstitutionReason",
    "numberOfRefills",
    "prescriptionDate",
    "prescriptionExpirationDate",
    "renewalReasonCode",
    "sigAdditionalInformation",
    "sigCode",
    "sigDescriptionPatientLanguage",
    "prescriptionDispensableFlag",
    "fillStatusEffectiveDate",
    "legacyFlag",
    "holdEndDate",
    "transferableCode",
    "administrationForm",
    "administrationPeriodStartDate",
    "statusChangeType",
    "statusChangeReasonCode",
    "updateEffectiveStartDate",
    "prescriptionValidFromDate",
    "administrationPeriodEndDate",
    "daysInterval",
    "updateEffectiveEndDate",
    "createTimestamp",
    "daysSupplyPrescribed",
    "quantityPrescribed",
    "noMoreDispensesAllowedFlag",
    "isBatchFlag",
    "serviceLocation",
    "prescribedPack",
    "substitutionInitiator",
    "fillStatusCode",
    "linkedrx",
    "patient",
    "prescriber",
    "treatmentType",
    "dispense",
    "source",
    "note",
    "prescribedDrug",
    "prescribedCompound",
    "discontinuedReasonCode",
    "resumeReasonCode",
    "recorder",
    "holdReasonCode",
    "responsiblePerson",
    "supervisor",
    "firstSummaryDispense",
    "lastSummaryDispense",
    "firstDispenseInstruction",
    "fillStatusSubCode",
    "forwardRx",
    "rxParams"
})
public class Prescription {
	
	@XmlTransient
	protected Long rxKey = null;
	
	

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimestamp;
    @XmlElement(defaultValue = "false")
    protected Boolean isAuthoritative;
    protected String consumerId;
    protected String producerId;
    protected BigDecimal quantityDispensed;
    protected BigDecimal remainingQuantity;
    protected Integer prescriptionNumber;
    protected Integer refillsRemainingCount;
    protected BigDecimal totalQuantityAuthorized;
    protected Integer drugTotalDaysOfSupplyCount;
    protected Integer drugTrialDaysOfSupplyCount;
    @XmlElement(defaultValue = "false")
    protected Boolean drugTrialFlag;
    protected Integer drugTrialQuantity;
    @XmlElement(defaultValue = "false")
    protected Boolean inferredPrescriptionFlag;
    protected String noSubstitutionReason;
    protected Integer numberOfRefills;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionExpirationDate;
    protected String renewalReasonCode;
    @XmlElement(name = "SIGAdditionalInformation")
    protected String sigAdditionalInformation;
    @XmlElement(name = "SIGCode")
    protected String sigCode;
    @XmlElement(name = "SIGDescriptionPatientLanguage")
    protected String sigDescriptionPatientLanguage;
    @XmlElement(defaultValue = "false")
    protected Boolean prescriptionDispensableFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fillStatusEffectiveDate;
    @XmlElement(defaultValue = "false")
    protected Boolean legacyFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar holdEndDate;
    @XmlElement(defaultValue = "false")
    protected Boolean transferableCode;
    protected String administrationForm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodStartDate;
    protected String statusChangeType;
    protected String statusChangeReasonCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateEffectiveStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prescriptionValidFromDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar administrationPeriodEndDate;
    protected Integer daysInterval;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updateEffectiveEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTimestamp;
    protected Integer daysSupplyPrescribed;
    protected BigDecimal quantityPrescribed;
    protected Boolean noMoreDispensesAllowedFlag;
    protected Boolean isBatchFlag;
    protected Location serviceLocation;
    protected Pack prescribedPack;
    @XmlSchemaType(name = "string")
    protected SubstitutionInitiator substitutionInitiator;
    @XmlSchemaType(name = "string")
    protected RxFillStatus fillStatusCode;
    protected Prescription linkedrx;
    protected Patient patient;
    protected Prescriber prescriber;
    @XmlSchemaType(name = "string")
    protected TreatmentType treatmentType;
    protected List<Dispense> dispense;
    @XmlElement(name = "Source")
    @XmlSchemaType(name = "string")
    protected Source source;
    protected List<Note> note;
    protected Drug prescribedDrug;
    protected Compound prescribedCompound;
    @XmlSchemaType(name = "string")
    protected DiscontinuedReason discontinuedReasonCode;
    @XmlSchemaType(name = "string")
    protected PrescriptionResumeReason resumeReasonCode;
    protected Recorder recorder;
    protected PrescriptionHoldReasonTypeDisplay holdReasonCode;
    protected ResponsiblePerson responsiblePerson;
    protected Supervisor supervisor;
    protected Dispense firstSummaryDispense;
    protected Dispense lastSummaryDispense;
    protected Dispense firstDispenseInstruction;
    @XmlSchemaType(name = "string")
    protected RxFillStatus fillStatusSubCode;
    protected Prescription forwardRx;
    protected RxParams rxParams;
    
    @XmlTransient
    public Long getRxKey() {
           return rxKey;
    }
    public void setRxKey(Long value) {
           this.rxKey = value;
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
     * Gets the value of the remainingQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRemainingQuantity() {
        return remainingQuantity;
    }

    /**
     * Sets the value of the remainingQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRemainingQuantity(BigDecimal value) {
        this.remainingQuantity = value;
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
     * Gets the value of the totalQuantityAuthorized property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalQuantityAuthorized() {
        return totalQuantityAuthorized;
    }

    /**
     * Sets the value of the totalQuantityAuthorized property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalQuantityAuthorized(BigDecimal value) {
        this.totalQuantityAuthorized = value;
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
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDrugTrialFlag() {
        return drugTrialFlag;
    }

    /**
     * Sets the value of the drugTrialFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDrugTrialFlag(Boolean value) {
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
     * Gets the value of the inferredPrescriptionFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInferredPrescriptionFlag() {
        return inferredPrescriptionFlag;
    }

    /**
     * Sets the value of the inferredPrescriptionFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInferredPrescriptionFlag(Boolean value) {
        this.inferredPrescriptionFlag = value;
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
     * Gets the value of the prescriptionDispensableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrescriptionDispensableFlag() {
        return prescriptionDispensableFlag;
    }

    /**
     * Sets the value of the prescriptionDispensableFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrescriptionDispensableFlag(Boolean value) {
        this.prescriptionDispensableFlag = value;
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
     * Gets the value of the legacyFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLegacyFlag() {
        return legacyFlag;
    }

    /**
     * Sets the value of the legacyFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLegacyFlag(Boolean value) {
        this.legacyFlag = value;
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
     * Gets the value of the transferableCode property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTransferableCode() {
        return transferableCode;
    }

    /**
     * Sets the value of the transferableCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTransferableCode(Boolean value) {
        this.transferableCode = value;
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
     * Gets the value of the quantityPrescribed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantityPrescribed() {
        return quantityPrescribed;
    }

    /**
     * Sets the value of the quantityPrescribed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantityPrescribed(BigDecimal value) {
        this.quantityPrescribed = value;
    }

    /**
     * Gets the value of the noMoreDispensesAllowedFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoMoreDispensesAllowedFlag() {
        return noMoreDispensesAllowedFlag;
    }

    /**
     * Sets the value of the noMoreDispensesAllowedFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoMoreDispensesAllowedFlag(Boolean value) {
        this.noMoreDispensesAllowedFlag = value;
    }

    /**
     * Gets the value of the isBatchFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsBatchFlag() {
        return isBatchFlag;
    }

    /**
     * Sets the value of the isBatchFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsBatchFlag(Boolean value) {
        this.isBatchFlag = value;
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
     * Gets the value of the prescribedPack property.
     * 
     * @return
     *     possible object is
     *     {@link Pack }
     *     
     */
    public Pack getPrescribedPack() {
        return prescribedPack;
    }

    /**
     * Sets the value of the prescribedPack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pack }
     *     
     */
    public void setPrescribedPack(Pack value) {
        this.prescribedPack = value;
    }

    /**
     * Gets the value of the substitutionInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link SubstitutionInitiator }
     *     
     */
    public SubstitutionInitiator getSubstitutionInitiator() {
        return substitutionInitiator;
    }

    /**
     * Sets the value of the substitutionInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubstitutionInitiator }
     *     
     */
    public void setSubstitutionInitiator(SubstitutionInitiator value) {
        this.substitutionInitiator = value;
    }

    /**
     * Gets the value of the fillStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link RxFillStatus }
     *     
     */
    public RxFillStatus getFillStatusCode() {
        return fillStatusCode;
    }

    /**
     * Sets the value of the fillStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxFillStatus }
     *     
     */
    public void setFillStatusCode(RxFillStatus value) {
        this.fillStatusCode = value;
    }

    /**
     * Gets the value of the linkedrx property.
     * 
     * @return
     *     possible object is
     *     {@link Prescription }
     *     
     */
    public Prescription getLinkedrx() {
        return linkedrx;
    }

    /**
     * Sets the value of the linkedrx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescription }
     *     
     */
    public void setLinkedrx(Prescription value) {
        this.linkedrx = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

    /**
     * Gets the value of the prescriber property.
     * 
     * @return
     *     possible object is
     *     {@link Prescriber }
     *     
     */
    public Prescriber getPrescriber() {
        return prescriber;
    }

    /**
     * Sets the value of the prescriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescriber }
     *     
     */
    public void setPrescriber(Prescriber value) {
        this.prescriber = value;
    }

    /**
     * Gets the value of the treatmentType property.
     * 
     * @return
     *     possible object is
     *     {@link TreatmentType }
     *     
     */
    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    /**
     * Sets the value of the treatmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreatmentType }
     *     
     */
    public void setTreatmentType(TreatmentType value) {
        this.treatmentType = value;
    }

    /**
     * Gets the value of the dispense property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispense property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDispense().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dispense }
     * 
     * 
     */
    public List<Dispense> getDispense() {
        if (dispense == null) {
            dispense = new ArrayList<Dispense>();
        }
        return this.dispense;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
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
     * Gets the value of the discontinuedReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link DiscontinuedReason }
     *     
     */
    public DiscontinuedReason getDiscontinuedReasonCode() {
        return discontinuedReasonCode;
    }

    /**
     * Sets the value of the discontinuedReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscontinuedReason }
     *     
     */
    public void setDiscontinuedReasonCode(DiscontinuedReason value) {
        this.discontinuedReasonCode = value;
    }

    /**
     * Gets the value of the resumeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionResumeReason }
     *     
     */
    public PrescriptionResumeReason getResumeReasonCode() {
        return resumeReasonCode;
    }

    /**
     * Sets the value of the resumeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionResumeReason }
     *     
     */
    public void setResumeReasonCode(PrescriptionResumeReason value) {
        this.resumeReasonCode = value;
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
     * Gets the value of the responsiblePerson property.
     * 
     * @return
     *     possible object is
     *     {@link ResponsiblePerson }
     *     
     */
    public ResponsiblePerson getResponsiblePerson() {
        return responsiblePerson;
    }

    /**
     * Sets the value of the responsiblePerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsiblePerson }
     *     
     */
    public void setResponsiblePerson(ResponsiblePerson value) {
        this.responsiblePerson = value;
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
     * Gets the value of the fillStatusSubCode property.
     * 
     * @return
     *     possible object is
     *     {@link RxFillStatus }
     *     
     */
    public RxFillStatus getFillStatusSubCode() {
        return fillStatusSubCode;
    }

    /**
     * Sets the value of the fillStatusSubCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxFillStatus }
     *     
     */
    public void setFillStatusSubCode(RxFillStatus value) {
        this.fillStatusSubCode = value;
    }

    /**
     * Gets the value of the forwardRx property.
     * 
     * @return
     *     possible object is
     *     {@link Prescription }
     *     
     */
    public Prescription getForwardRx() {
        return forwardRx;
    }

    /**
     * Sets the value of the forwardRx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescription }
     *     
     */
    public void setForwardRx(Prescription value) {
        this.forwardRx = value;
    }

    /**
     * Gets the value of the rxParams property.
     * 
     * @return
     *     possible object is
     *     {@link RxParams }
     *     
     */
    public RxParams getRxParams() {
        return rxParams;
    }

    /**
     * Sets the value of the rxParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxParams }
     *     
     */
    public void setRxParams(RxParams value) {
        this.rxParams = value;
    }

}
