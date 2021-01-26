
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CoverageCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoverageCard"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cardFront" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Image"/&gt;
 *         &lt;element name="cardBack" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Image"/&gt;
 *         &lt;element name="groupNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric"/&gt;
 *         &lt;element name="planNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric"/&gt;
 *         &lt;element name="carrierName" type="{http://www.w3.org/2001/XMLSchema}Name"/&gt;
 *         &lt;element name="isActive" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoverageCard", propOrder = {
    "cardFront",
    "cardBack",
    "groupNumber",
    "planNumber",
    "carrierName",
    "isActive"
})
public class CoverageCard {

    @XmlElement(required = true)
    protected byte[] cardFront;
    @XmlElement(required = true)
    protected byte[] cardBack;
    @XmlElement(required = true)
    protected String groupNumber;
    @XmlElement(required = true)
    protected String planNumber;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "Name")
    protected String carrierName;
    @XmlElement(required = true)
    protected String isActive;

    /**
     * Gets the value of the cardFront property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCardFront() {
        return cardFront;
    }

    /**
     * Sets the value of the cardFront property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCardFront(byte[] value) {
        this.cardFront = value;
    }

    /**
     * Gets the value of the cardBack property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCardBack() {
        return cardBack;
    }

    /**
     * Sets the value of the cardBack property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCardBack(byte[] value) {
        this.cardBack = value;
    }

    /**
     * Gets the value of the groupNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupNumber() {
        return groupNumber;
    }

    /**
     * Sets the value of the groupNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupNumber(String value) {
        this.groupNumber = value;
    }

    /**
     * Gets the value of the planNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanNumber() {
        return planNumber;
    }

    /**
     * Sets the value of the planNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanNumber(String value) {
        this.planNumber = value;
    }

    /**
     * Gets the value of the carrierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierName() {
        return carrierName;
    }

    /**
     * Sets the value of the carrierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierName(String value) {
        this.carrierName = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsActive(String value) {
        this.isActive = value;
    }

}
