
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.param;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination.AdherenceCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calendar" type="{http://www.ibm.com/rules/decisionservice/DigitalRxRuleService/RxActiveReminderDetermination}adherenceCalendar"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "calendar"
})
@XmlRootElement(name = "calendar")
public class Calendar {

    @XmlElement(required = true)
    protected AdherenceCalendar calendar;

    /**
     * Gets the value of the calendar property.
     * 
     * @return
     *     possible object is
     *     {@link AdherenceCalendar }
     *     
     */
    public AdherenceCalendar getCalendar() {
        return calendar;
    }

    /**
     * Sets the value of the calendar property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdherenceCalendar }
     *     
     */
    public void setCalendar(AdherenceCalendar value) {
        this.calendar = value;
    }

}
