
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calendarReminder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calendarReminder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="autoRefillable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dispense" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}dispense" minOccurs="0"/>
 *         &lt;element name="pickupReminderEligible" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="prescription" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}prescription" minOccurs="0"/>
 *         &lt;element name="refillReminderEligible" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="refillable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reminderDates" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}reminderDate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="renewable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calendarReminder", propOrder = {
    "active",
    "autoRefillable",
    "dispense",
    "pickupReminderEligible",
    "prescription",
    "refillReminderEligible",
    "refillable",
    "reminderDates",
    "renewable",
    "portalAutoRefillFlag",//CR-101
    "portalRefillReminderFlag",//CR-101
})
public class CalendarReminder {

    protected boolean active;
    protected boolean autoRefillable;
    protected Dispense dispense;
    protected boolean pickupReminderEligible;
  
    protected Prescription prescription;
    protected boolean refillReminderEligible;
    protected boolean refillable;
    @XmlElement(nillable = true)
    protected List<ReminderDate> reminderDates;
    protected boolean renewable;
    protected boolean portalAutoRefillFlag;//CR-101
    protected boolean portalRefillReminderFlag;//CR-101
    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the autoRefillable property.
     * 
     */
    public boolean isAutoRefillable() {
        return autoRefillable;
    }

    /**
     * Sets the value of the autoRefillable property.
     * 
     */
    public void setAutoRefillable(boolean value) {
        this.autoRefillable = value;
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
     * Gets the value of the pickupReminderEligible property.
     * 
     */
    public boolean isPickupReminderEligible() {
        return pickupReminderEligible;
    }

    /**
     * Sets the value of the pickupReminderEligible property.
     * 
     */
    public void setPickupReminderEligible(boolean value) {
        this.pickupReminderEligible = value;
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
     * Gets the value of the refillReminderEligible property.
     * 
     */
    public boolean isRefillReminderEligible() {
        return refillReminderEligible;
    }

    /**
     * Sets the value of the refillReminderEligible property.
     * 
     */
    public void setRefillReminderEligible(boolean value) {
        this.refillReminderEligible = value;
    }

    /**
     * Gets the value of the refillable property.
     * 
     */
    public boolean isRefillable() {
        return refillable;
    }

    /**
     * Sets the value of the refillable property.
     * 
     */
    public void setRefillable(boolean value) {
        this.refillable = value;
    }

    /**
     * Gets the value of the reminderDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reminderDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReminderDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReminderDate }
     * 
     * 
     */
    public List<ReminderDate> getReminderDates() {
        if (reminderDates == null) {
            reminderDates = new ArrayList<ReminderDate>();
        }
        return this.reminderDates;
    }

    /**
     * Gets the value of the renewable property.
     * 
     */
    public boolean isRenewable() {
        return renewable;
    }

    /**
     * Sets the value of the renewable property.
     * 
     */
    public void setRenewable(boolean value) {
        this.renewable = value;
    }

	public boolean isPortalAutoRefillFlag() {
		return portalAutoRefillFlag;
	}

	public void setPortalAutoRefillFlag(boolean portalAutoRefillFlag) {
		this.portalAutoRefillFlag = portalAutoRefillFlag;
	}

	public boolean isPortalRefillReminderFlag() {
		return portalRefillReminderFlag;
	}

	public void setPortalRefillReminderFlag(boolean portalRefillReminderFlag) {
		this.portalRefillReminderFlag = portalRefillReminderFlag;
	}

	

}
