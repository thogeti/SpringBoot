
package ca.shoppersdrugmart.rxhb.drx.cse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;


/**
 * <p>Java class for CalendarReminder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalendarReminder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription"/&gt;
 *         &lt;element name="isRefillReminderEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="isPickupReminderEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="isAutoRefillEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="isActive" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="reminderDate" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}ReminderDate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dispense" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispense" minOccurs="0"/&gt;
 *         &lt;element name="isRefillEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="isRenewalEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="portalAutoRefillFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="portalRefillReminderFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarReminder", propOrder = {
    "prescription",
    "isRefillReminderEligible",
    "isPickupReminderEligible",
    "isAutoRefillEligible",
    "isActive",
    "reminderDate",
    "dispense",
    "isRefillEligible",
    "isRenewalEligible",
    "portalAutoRefillFlag",
    "portalRefillReminderFlag"
})
public class CalendarReminder {

    @XmlElement(required = true)
    protected Prescription prescription;
    @XmlElement(required = true)
    protected String isRefillReminderEligible;
    @XmlElement(required = true)
    protected String isPickupReminderEligible;
    @XmlElement(required = true)
    protected String isAutoRefillEligible;
    @XmlElement(required = true)
    protected String isActive;
    protected List<ReminderDate> reminderDate;
    protected Dispense dispense;
    @XmlElement(required = true)
    protected String isRefillEligible;
    @XmlElement(required = true)
    protected String isRenewalEligible;
    protected String portalAutoRefillFlag;
    protected String portalRefillReminderFlag;

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
     * Gets the value of the isRefillReminderEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRefillReminderEligible() {
        return isRefillReminderEligible;
    }

    /**
     * Sets the value of the isRefillReminderEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRefillReminderEligible(String value) {
        this.isRefillReminderEligible = value;
    }

    /**
     * Gets the value of the isPickupReminderEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPickupReminderEligible() {
        return isPickupReminderEligible;
    }

    /**
     * Sets the value of the isPickupReminderEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPickupReminderEligible(String value) {
        this.isPickupReminderEligible = value;
    }

    /**
     * Gets the value of the isAutoRefillEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAutoRefillEligible() {
        return isAutoRefillEligible;
    }

    /**
     * Sets the value of the isAutoRefillEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAutoRefillEligible(String value) {
        this.isAutoRefillEligible = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsActive(String value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the reminderDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reminderDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReminderDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReminderDate }
     * 
     * 
     */
    public List<ReminderDate> getReminderDate() {
        if (reminderDate == null) {
            reminderDate = new ArrayList<ReminderDate>();
        }
        return this.reminderDate;
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
     * Gets the value of the isRefillEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRefillEligible() {
        return isRefillEligible;
    }

    /**
     * Sets the value of the isRefillEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRefillEligible(String value) {
        this.isRefillEligible = value;
    }

    /**
     * Gets the value of the isRenewalEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRenewalEligible() {
        return isRenewalEligible;
    }

    /**
     * Sets the value of the isRenewalEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRenewalEligible(String value) {
        this.isRenewalEligible = value;
    }

    /**
     * Gets the value of the portalAutoRefillFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalAutoRefillFlag() {
        return portalAutoRefillFlag;
    }

    /**
     * Sets the value of the portalAutoRefillFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalAutoRefillFlag(String value) {
        this.portalAutoRefillFlag = value;
    }

    /**
     * Gets the value of the portalRefillReminderFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalRefillReminderFlag() {
        return portalRefillReminderFlag;
    }

    /**
     * Sets the value of the portalRefillReminderFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalRefillReminderFlag(String value) {
        this.portalRefillReminderFlag = value;
    }

}
