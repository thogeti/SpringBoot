
package ca.shoppersdrugmart.rxhb.drx.subscriptionservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnsubscribeToRx complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnsubscribeToRx">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StoreNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/>
 *         &lt;element name="RxNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Number"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsubscribeToRx", propOrder = {
    "storeNumber",
    "rxNumber"
})
public class UnsubscribeToRx {

    @XmlElement(name = "StoreNumber", required = true)
    protected String storeNumber;
    @XmlElement(name = "RxNumber")
    protected int rxNumber;

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
     * Gets the value of the rxNumber property.
     * 
     */
    public int getRxNumber() {
        return rxNumber;
    }

    /**
     * Sets the value of the rxNumber property.
     * 
     */
    public void setRxNumber(int value) {
        this.rxNumber = value;
    }

}
