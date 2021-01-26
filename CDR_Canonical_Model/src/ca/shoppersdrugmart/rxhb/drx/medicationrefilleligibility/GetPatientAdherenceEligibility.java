
package ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.PopulationStrategyEnum;


/**
 * <p>Java class for GetPatientAdherenceEligibility complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPatientAdherenceEligibility"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ActiveFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *         &lt;element name="StoreNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="PatientId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric"/&gt;
 *         &lt;element name="PopulationStrategy" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}PopulationStrategyEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPatientAdherenceEligibility", propOrder = {
    "activeFlag",
    "storeNumber",
    "patientId",
    "populationStrategy"
})
public class GetPatientAdherenceEligibility {

    @XmlElement(name = "ActiveFlag", required = true)
    protected String activeFlag;
    @XmlElement(name = "StoreNumber", required = true)
    protected String storeNumber;
    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "PopulationStrategy", required = true)
    @XmlSchemaType(name = "string")
    protected PopulationStrategyEnum populationStrategy;

    /**
     * Gets the value of the activeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveFlag() {
        return activeFlag;
    }

    /**
     * Sets the value of the activeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveFlag(String value) {
        this.activeFlag = value;
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
     * Gets the value of the populationStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link PopulationStrategyEnum }
     *     
     */
    public PopulationStrategyEnum getPopulationStrategy() {
        return populationStrategy;
    }

    /**
     * Sets the value of the populationStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PopulationStrategyEnum }
     *     
     */
    public void setPopulationStrategy(PopulationStrategyEnum value) {
        this.populationStrategy = value;
    }

}
