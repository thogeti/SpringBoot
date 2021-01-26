
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A group of freeform textual names that identify the drug in a specific standardised way. Used for downstream consumers of the data.
 * 
 * <p>Java class for DrugNameAlternative complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugNameAlternative"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="equivalentto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tradenamealternative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tradereference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugNameAlternative", propOrder = {
    "equivalentto",
    "tradenamealternative",
    "tradereference"
})
public class DrugNameAlternative {

    protected String equivalentto;
    protected String tradenamealternative;
    protected String tradereference;

    /**
     * Gets the value of the equivalentto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentto() {
        return equivalentto;
    }

    /**
     * Sets the value of the equivalentto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentto(String value) {
        this.equivalentto = value;
    }

    /**
     * Gets the value of the tradenamealternative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradenamealternative() {
        return tradenamealternative;
    }

    /**
     * Sets the value of the tradenamealternative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradenamealternative(String value) {
        this.tradenamealternative = value;
    }

    /**
     * Gets the value of the tradereference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradereference() {
        return tradereference;
    }

    /**
     * Sets the value of the tradereference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradereference(String value) {
        this.tradereference = value;
    }

}
