
package ca.shoppersdrugmart.rxhb.drx.medicationprofile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.AdherenceCalendar;


/**
 * <p>Java class for GetPrescriptionAdherenceCalendarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPrescriptionAdherenceCalendarResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdherenceCalendar" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}adherenceCalendar"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrescriptionAdherenceCalendarResponse", propOrder = {
    "adherenceCalendar"
})
public class GetPrescriptionAdherenceCalendarResponse {

    @XmlElement(name = "AdherenceCalendar", required = true)
    protected AdherenceCalendar adherenceCalendar;

    /**
     * Gets the value of the adherenceCalendar property.
     * 
     * @return
     *     possible object is
     *     {@link AdherenceCalendar }
     *     
     */
    public AdherenceCalendar getAdherenceCalendar() {
        return adherenceCalendar;
    }

    /**
     * Sets the value of the adherenceCalendar property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdherenceCalendar }
     *     
     */
    public void setAdherenceCalendar(AdherenceCalendar value) {
        this.adherenceCalendar = value;
    }

}
