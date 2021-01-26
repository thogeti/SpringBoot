
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A description of the drug packing specifications and constraints associated with the prescription.
 * 
 * <p>Java class for Pack complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Pack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       	&lt;element name="GTIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       	&lt;element name="autoRefillReminderFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       	&lt;element name="pickupReminderFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       	&lt;element name="refillReminderFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         	&lt;element name="strength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         	&lt;element name="strengthUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *          &lt;element name="trrFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *          &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pack", propOrder = {
    "gtin",
    "autoRefillReminderFlag",
    "pickupReminderFlag",
    "refillReminderFlag",
    "strength",
    "strengthUOMCode",
    "trrFlag"
})
public class Pack {

	 @XmlElement(name = "GTIN")
	    protected String gtin;
	 protected String autoRefillReminderFlag;
	    protected String pickupReminderFlag;
	    protected String refillReminderFlag;
	    protected String strength;
	    protected String strengthUOMCode;
	    protected String trrFlag;
   
   
    

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStrength(String value) {
        this.strength = value;
    }

    /**
     * Gets the value of the gtin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGTIN() {
        return gtin;
    }

    /**
     * Sets the value of the gtin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGTIN(String value) {
        this.gtin = value;
    }

  

    /**
     * Gets the value of the strengthUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrengthUOMCode() {
        return strengthUOMCode;
    }

    /**
     * Sets the value of the strengthUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrengthUOMCode(String value) {
        this.strengthUOMCode = value;
    }

   

	public String getAutoRefillReminderFlag() {
		return autoRefillReminderFlag;
	}

	public void setAutoRefillReminderFlag(String autoRefillReminderFlag) {
		this.autoRefillReminderFlag = autoRefillReminderFlag;
	}

	public String getPickupReminderFlag() {
		return pickupReminderFlag;
	}

	public void setPickupReminderFlag(String pickupReminderFlag) {
		this.pickupReminderFlag = pickupReminderFlag;
	}

	public String getRefillReminderFlag() {
		return refillReminderFlag;
	}

	public void setRefillReminderFlag(String refillReminderFlag) {
		this.refillReminderFlag = refillReminderFlag;
	}

	public String getTrrFlag() {
		return trrFlag;
	}

	public void setTrrFlag(String trrFlag) {
		this.trrFlag = trrFlag;
	}

}
