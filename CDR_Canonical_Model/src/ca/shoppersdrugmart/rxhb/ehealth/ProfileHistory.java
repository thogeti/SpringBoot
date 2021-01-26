
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfileHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfileHistory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" minOccurs="0"/&gt;
 *         &lt;element name="patientMedicalCondition" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PatientMedicalCondition" minOccurs="0"/&gt;
 *         &lt;element name="dispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="statusHistory" type="{http://shoppersdrugmart.ca/RxHB/eHealth}StatusHistory" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="allergy" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Allergy" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfileHistory", propOrder = {
    "prescription",
    "patientMedicalCondition",
    "dispense",
    "statusHistory",
    "allergy"
})
public class ProfileHistory {

    protected Prescription prescription;
    protected PatientMedicalCondition patientMedicalCondition;
    protected Dispense dispense;
    protected List<StatusHistory> statusHistory;
    protected Allergy allergy;

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
     * Gets the value of the patientMedicalCondition property.
     * 
     * @return
     *     possible object is
     *     {@link PatientMedicalCondition }
     *     
     */
    public PatientMedicalCondition getPatientMedicalCondition() {
        return patientMedicalCondition;
    }

    /**
     * Sets the value of the patientMedicalCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientMedicalCondition }
     *     
     */
    public void setPatientMedicalCondition(PatientMedicalCondition value) {
        this.patientMedicalCondition = value;
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
     * Gets the value of the statusHistory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusHistory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusHistory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatusHistory }
     * 
     * 
     */
    public List<StatusHistory> getStatusHistory() {
        if (statusHistory == null) {
            statusHistory = new ArrayList<StatusHistory>();
        }
        return this.statusHistory;
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

}
