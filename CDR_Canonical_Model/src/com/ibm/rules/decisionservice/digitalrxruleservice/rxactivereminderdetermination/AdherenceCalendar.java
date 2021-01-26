
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
 * <p>Java class for adherenceCalendar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adherenceCalendar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calendarReminders" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}calendarReminder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calendarType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storeProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *          &lt;element name="Division" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adherenceCalendar", propOrder = {
    "calendarReminders",
    "calendarType",
    "patientId",
    "storeNumber",
////TE97_JAXB added as requested by raja on 04/04/2018.start
    "accountEligible",
    "dateofBirth",
    "deceasedDate",
    "isPatientActiveFlag",
  //TE97_JAXB added as requested by raja on 04/04/2018.end
    "patientCountry",
    "patientProvince",
    "storeProvince",
    "division"
})
public class AdherenceCalendar {

    @XmlElement(nillable = true)
    protected List<CalendarReminder> calendarReminders;
    protected String calendarType;
    protected String patientId;
    protected String storeNumber;
    //TE97_JAXB added as requested by raja on 04/04/2018. start
    // Always default to false. ODM will calculate and populate right value on response
    @XmlElement(defaultValue = "false")
    protected boolean accountEligible;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateofBirth;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deceasedDate;
    protected boolean isPatientActiveFlag;
    //TE97_JAXB added as requested by raja on 04/04/2018.end
    protected String patientCountry;
    protected String patientProvince;
    protected String storeProvince;
    @XmlElement(name = "Division")
    protected String division;

    /**
     * Gets the value of the calendarReminders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calendarReminders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalendarReminders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalendarReminder }
     * 
     * 
     */
    public List<CalendarReminder> getCalendarReminders() {
        if (calendarReminders == null) {
            calendarReminders = new ArrayList<CalendarReminder>();
        }
        return this.calendarReminders;
    }

    /**
     * Gets the value of the calendarType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarType() {
        return calendarType;
    }

    /**
     * Sets the value of the calendarType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarType(String value) {
        this.calendarType = value;
    }

    /**
     * Gets the value of the patientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientId(String value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the storeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreNumber() {
        return storeNumber;
    }

    /**
     * Sets the value of the storeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreNumber(String value) {
        this.storeNumber = value;
    }

    /**
     * Gets the value of the patientCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientCountry() {
        return patientCountry;
    }

    /**
     * Sets the value of the patientCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientCountry(String value) {
        this.patientCountry = value;
    }

    /**
     * Gets the value of the patientProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientProvince() {
        return patientProvince;
    }

    /**
     * Sets the value of the patientProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientProvince(String value) {
        this.patientProvince = value;
    }

    /**
     * Gets the value of the storeProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreProvince() {
        return storeProvince;
    }

    /**
     * Sets the value of the storeProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreProvince(String value) {
        this.storeProvince = value;
    }
  //TE97_JAXB added as requested by raja on 04/04/2018.start
  	public XMLGregorianCalendar getDateofBirth() {
  		return dateofBirth;
  	}

  	public void setDateofBirth(XMLGregorianCalendar dateofBirth) {
  		this.dateofBirth = dateofBirth;
  	}

  	public XMLGregorianCalendar getDeceasedDate() {
  		return deceasedDate;
  	}

  	public void setDeceasedDate(XMLGregorianCalendar deceasedDate) {
  		this.deceasedDate = deceasedDate;
  	}

  	public boolean isPatientActiveFlag() {
  		return isPatientActiveFlag;
  	}

  	public void setPatientActiveFlag(boolean isPatientActiveFlag) {
  		this.isPatientActiveFlag = isPatientActiveFlag;
  	}
  	
  	public boolean isAccountEligible() {
  		return accountEligible;
  	}

  	public void setAccountEligible(boolean accountEligible) {
  		this.accountEligible = accountEligible;
  	}
  	//TE97_JAXB added as requested by raja on 04/04/2018.end
  	/**
     * Gets the value of the division property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivision(String value) {
        this.division = value;
    }

  	
}
