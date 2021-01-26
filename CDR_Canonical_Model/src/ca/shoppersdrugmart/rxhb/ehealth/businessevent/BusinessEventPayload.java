
package ca.shoppersdrugmart.rxhb.ehealth.businessevent;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.AdverseDrugReaction;
import ca.shoppersdrugmart.rxhb.ehealth.Allergy;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.HW4BEvent;
import ca.shoppersdrugmart.rxhb.ehealth.MitigatedBy;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.QueryCriteria;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;


/**
 * <p>Java class for BusinessEventPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessEventPayload"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="queryCriteria" type="{http://shoppersdrugmart.ca/RxHB/eHealth}QueryCriteria" minOccurs="0"/&gt;
 *         &lt;element name="allergy" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Allergy" minOccurs="0"/&gt;
 *         &lt;element name="compound" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Compound" minOccurs="0"/&gt;
 *         &lt;element name="consent" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Consent" minOccurs="0"/&gt;
 *         &lt;element name="dispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="dispenser" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispenser" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="drug" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Drug" minOccurs="0"/&gt;
 *         &lt;element name="mitigatedBy" type="{http://shoppersdrugmart.ca/RxHB/eHealth}MitigatedBy" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Note" minOccurs="0"/&gt;
 *         &lt;element name="pack" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Pack" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="patient" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Patient" minOccurs="0"/&gt;
 *         &lt;element name="prescriber" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescriber" minOccurs="0"/&gt;
 *         &lt;element name="prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" minOccurs="0"/&gt;
 *         &lt;element name="store" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Store"/&gt;
 *         &lt;element name="adverseDrugReaction" type="{http://shoppersdrugmart.ca/RxHB/eHealth}AdverseDrugReaction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="recorder" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Recorder" minOccurs="0"/&gt;
 *         &lt;element name="hw4bEvent" type="{http://shoppersdrugmart.ca/RxHB/eHealth}HW4BEvent" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessEventPayload", propOrder = {
    "queryCriteria",
    "allergy",
    "compound",
    "consent",
    "dispense",
    "dispenser",
    "drug",
    "mitigatedBy",
    "note",
    "pack",
    "patient",
    "prescriber",
    "prescription",
    "store",
    "adverseDrugReaction",
    "recorder",
    "hw4BEvent"
})
public class BusinessEventPayload {

    protected QueryCriteria queryCriteria;
    protected Allergy allergy;
    protected Compound compound;
    protected Consent consent;
    protected Dispense dispense;
    protected List<Dispenser> dispenser;
    protected Drug drug;
    protected List<MitigatedBy> mitigatedBy;
    protected Note note;
    protected List<Pack> pack;
    protected Patient patient;
    protected Prescriber prescriber;
    protected Prescription prescription;
    @XmlElement(required = true)
    protected Store store;
    protected List<AdverseDrugReaction> adverseDrugReaction;
    protected Recorder recorder;
    @XmlElement(name = "hw4bEvent")
    protected HW4BEvent hw4BEvent;

    /**
     * Gets the value of the queryCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCriteria }
     *     
     */
    public QueryCriteria getQueryCriteria() {
        return queryCriteria;
    }

    /**
     * Sets the value of the queryCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCriteria }
     *     
     */
    public void setQueryCriteria(QueryCriteria value) {
        this.queryCriteria = value;
    }

    /**
     * Gets the value of the allergy property.
     * 
     * @return
     *     possible object is
     *     {@link Allergy }
     *     
     */
    public Allergy getAllergy() {
        return allergy;
    }

    /**
     * Sets the value of the allergy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Allergy }
     *     
     */
    public void setAllergy(Allergy value) {
        this.allergy = value;
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
     * Gets the value of the consent property.
     * 
     * @return
     *     possible object is
     *     {@link Consent }
     *     
     */
    public Consent getConsent() {
        return consent;
    }

    /**
     * Sets the value of the consent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Consent }
     *     
     */
    public void setConsent(Consent value) {
        this.consent = value;
    }

    /**
     * Gets the value of the dispense property.
     * 
     * @return
     *     possible object is
     *     {@link Dispense }
     *     
     */
    public Dispense getDispense() {
        return dispense;
    }

    /**
     * Sets the value of the dispense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispense }
     *     
     */
    public void setDispense(Dispense value) {
        this.dispense = value;
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
     * Gets the value of the mitigatedBy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mitigatedBy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMitigatedBy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MitigatedBy }
     * 
     * 
     */
    public List<MitigatedBy> getMitigatedBy() {
        if (mitigatedBy == null) {
            mitigatedBy = new ArrayList<MitigatedBy>();
        }
        return this.mitigatedBy;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link Note }
     *     
     */
    public Note getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link Note }
     *     
     */
    public void setNote(Note value) {
        this.note = value;
    }

    /**
     * Gets the value of the pack property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pack property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPack().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pack }
     * 
     * 
     */
    public List<Pack> getPack() {
        if (pack == null) {
            pack = new ArrayList<Pack>();
        }
        return this.pack;
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
     * Gets the value of the prescription property.
     * 
     * @return
     *     possible object is
     *     {@link Prescription }
     *     
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Sets the value of the prescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prescription }
     *     
     */
    public void setPrescription(Prescription value) {
        this.prescription = value;
    }

    /**
     * Gets the value of the store property.
     * 
     * @return
     *     possible object is
     *     {@link Store }
     *     
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the value of the store property.
     * 
     * @param value
     *     allowed object is
     *     {@link Store }
     *     
     */
    public void setStore(Store value) {
        this.store = value;
    }

    /**
     * Gets the value of the adverseDrugReaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adverseDrugReaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdverseDrugReaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdverseDrugReaction }
     * 
     * 
     */
    public List<AdverseDrugReaction> getAdverseDrugReaction() {
        if (adverseDrugReaction == null) {
            adverseDrugReaction = new ArrayList<AdverseDrugReaction>();
        }
        return this.adverseDrugReaction;
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
     * Gets the value of the hw4BEvent property.
     * 
     * @return
     *     possible object is
     *     {@link HW4BEvent }
     *     
     */
    public HW4BEvent getHw4BEvent() {
        return hw4BEvent;
    }

    /**
     * Sets the value of the hw4BEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link HW4BEvent }
     *     
     */
    public void setHw4BEvent(HW4BEvent value) {
        this.hw4BEvent = value;
    }

}
