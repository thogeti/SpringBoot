
package ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.AdherenceEligibility;


/**
 * <p>Java class for GetPatientAdherenceEligibilityResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPatientAdherenceEligibilityResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdherenceEligibility" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}AdherenceEligibility"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPatientAdherenceEligibilityResponse", propOrder = {
    "adherenceEligibility"
})
public class GetPatientAdherenceEligibilityResponse {

    @XmlElement(name = "AdherenceEligibility", required = true)
    protected AdherenceEligibility adherenceEligibility;

    /**
     * Gets the value of the adherenceEligibility property.
     * 
     * @return
     *     possible object is
     *     {@link AdherenceEligibility }
     *     
     */
    public AdherenceEligibility getAdherenceEligibility() {
        return adherenceEligibility;
    }

    /**
     * Sets the value of the adherenceEligibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdherenceEligibility }
     *     
     */
    public void setAdherenceEligibility(AdherenceEligibility value) {
        this.adherenceEligibility = value;
    }

}
