
package ca.shoppersdrugmart.rxhb.drx.medicationprofile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.PopulationStrategyEnum;


/**
 * <p>Java class for GetDispenseAdherenceCalendar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDispenseAdherenceCalendar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StoreNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="DispenseNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Number"/&gt;
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
@XmlType(name = "GetDispenseAdherenceCalendar", propOrder = {
    "storeNumber",
    "dispenseNumber",
    "populationStrategy"
})
public class GetDispenseAdherenceCalendar {

    @XmlElement(name = "StoreNumber", required = true)
    protected String storeNumber;
    @XmlElement(name = "DispenseNumber")
    protected int dispenseNumber;
    @XmlElement(name = "PopulationStrategy", required = true)
    @XmlSchemaType(name = "string")
    protected PopulationStrategyEnum populationStrategy;

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
     * Gets the value of the dispenseNumber property.
     * 
     */
    public int getDispenseNumber() {
        return dispenseNumber;
    }

    /**
     * Sets the value of the dispenseNumber property.
     * 
     */
    public void setDispenseNumber(int value) {
        this.dispenseNumber = value;
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
