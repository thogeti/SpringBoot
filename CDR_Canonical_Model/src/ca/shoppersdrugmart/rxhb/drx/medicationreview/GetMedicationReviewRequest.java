
package ca.shoppersdrugmart.rxhb.drx.medicationreview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedCheckIn;


/**
 * <p>Java class for GetMedicationReviewRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMedicationReviewRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MedIn" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}medCheckIn" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMedicationReviewRequest", propOrder = {
    "medIn"
})
@XmlRootElement(name = "GetMedicationReviewRequest")      // VLAD_CORRECTED
public class GetMedicationReviewRequest {

    @XmlElement(name = "MedIn")
    protected MedCheckIn medIn;

    /**
     * Gets the value of the medIn property.
     * 
     * @return
     *     possible object is
     *     {@link MedCheckIn }
     *     
     */
    public MedCheckIn getMedIn() {
        return medIn;
    }

    /**
     * Sets the value of the medIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedCheckIn }
     *     
     */
    public void setMedIn(MedCheckIn value) {
        this.medIn = value;
    }

}
