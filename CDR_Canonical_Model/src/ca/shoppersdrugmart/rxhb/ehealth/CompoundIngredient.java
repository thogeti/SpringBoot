
package ca.shoppersdrugmart.rxhb.ehealth;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The structured list of ingredients for a compound. 
 * 
 * <p>Java class for CompoundIngredient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompoundIngredient"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="quantityUsed" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="pack" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Pack"/&gt;
 *         &lt;element name="quantityusedUOM" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PackSizeUoM"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompoundIngredient", propOrder = {
    "quantityUsed",
    "pack",
    "quantityusedUOM"
})
public class CompoundIngredient {

    @XmlElement(required = true)
    protected BigDecimal quantityUsed;
    @XmlElement(required = true)
    protected Pack pack;
    @XmlElement(required = true)
    protected String quantityusedUOM;

    /**
     * Gets the value of the quantityUsed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantityUsed() {
        return quantityUsed;
    }

    /**
     * Sets the value of the quantityUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantityUsed(BigDecimal value) {
        this.quantityUsed = value;
    }

    /**
     * Gets the value of the pack property.
     * 
     * @return
     *     possible object is
     *     {@link Pack }
     *     
     */
    public Pack getPack() {
        return pack;
    }

    /**
     * Sets the value of the pack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pack }
     *     
     */
    public void setPack(Pack value) {
        this.pack = value;
    }

    /**
     * Gets the value of the quantityusedUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantityusedUOM() {
        return quantityusedUOM;
    }

    /**
     * Sets the value of the quantityusedUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantityusedUOM(String value) {
        this.quantityusedUOM = value;
    }

}
