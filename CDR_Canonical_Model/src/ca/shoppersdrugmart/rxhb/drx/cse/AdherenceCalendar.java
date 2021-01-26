
package ca.shoppersdrugmart.rxhb.drx.cse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adherenceCalendar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adherenceCalendar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="storeNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="calendarType" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}CalendarTypeEnum"/&gt;
 *         &lt;element name="patientId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric" minOccurs="0"/&gt;
 *         &lt;element name="accountEligible" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="calendarReminder" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}CalendarReminder" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adherenceCalendar", propOrder = {
    "storeNumber",
    "calendarType",
    "patientId",
    "accountEligible",
    "calendarReminder"
})
public class AdherenceCalendar {

    @XmlElement(required = true)
    protected String storeNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CalendarTypeEnum calendarType;
    protected String patientId;
    protected String accountEligible;
    protected List<CalendarReminder> calendarReminder;

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
     * Gets the value of the calendarType property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarTypeEnum }
     *     
     */
    public CalendarTypeEnum getCalendarType() {
        return calendarType;
    }

    /**
     * Sets the value of the calendarType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarTypeEnum }
     *     
     */
    public void setCalendarType(CalendarTypeEnum value) {
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
     * Gets the value of the accountEligible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountEligible() {
        return accountEligible;
    }

    /**
     * Sets the value of the accountEligible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountEligible(String value) {
        this.accountEligible = value;
    }

    /**
     * Gets the value of the calendarReminder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calendarReminder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalendarReminder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalendarReminder }
     * 
     * 
     */
    public List<CalendarReminder> getCalendarReminder() {
        if (calendarReminder == null) {
            calendarReminder = new ArrayList<CalendarReminder>();
        }
        return this.calendarReminder;
    }

}
