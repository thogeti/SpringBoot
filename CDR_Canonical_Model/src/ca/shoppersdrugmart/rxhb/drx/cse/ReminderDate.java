
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ReminderDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReminderDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="reminder" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}CalendarReminderEnum"/&gt;
 *         &lt;element name="sequenceNum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="date" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReminderDate", propOrder = {
    "reminder",
    "sequenceNum",
    "date"
})
public class ReminderDate {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CalendarReminderEnum reminder;
    protected int sequenceNum;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;

    /**
     * Gets the value of the reminder property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarReminderEnum }
     *     
     */
    public CalendarReminderEnum getReminder() {
        return reminder;
    }

    /**
     * Sets the value of the reminder property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarReminderEnum }
     *     
     */
    public void setReminder(CalendarReminderEnum value) {
        this.reminder = value;
    }

    /**
     * Gets the value of the sequenceNum property.
     * 
     */
    public int getSequenceNum() {
        return sequenceNum;
    }

    /**
     * Sets the value of the sequenceNum property.
     * 
     */
    public void setSequenceNum(int value) {
        this.sequenceNum = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
