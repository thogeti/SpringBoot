
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="cardName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="thirdPartyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="province" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Province" minOccurs="0"/&gt;
 *         &lt;element name="cardFrontImage" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Image" minOccurs="0"/&gt;
 *         &lt;element name="cardBackImage" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Image" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "cardName",
    "thirdPartyName",
    "isActive",
    "province",
    "cardFrontImage",
    "cardBackImage",
    "consumerId",
    "producerId"
})
public class CoverageCard {

    @XmlElement(required = true)
    protected String cardName;
    protected String thirdPartyName;
    protected boolean isActive;
    @XmlSchemaType(name = "string")
    protected Province province;
    protected byte[] cardFrontImage;
    protected byte[] cardBackImage;
    @XmlElement(required = true)
    protected String consumerId;
    protected String producerId;

    /**
     * Gets the value of the cardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the value of the cardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardName(String value) {
        this.cardName = value;
    }

    /**
     * Gets the value of the thirdPartyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPartyName() {
        return thirdPartyName;
    }

    /**
     * Sets the value of the thirdPartyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPartyName(String value) {
        this.thirdPartyName = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link Province }
     *     
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link Province }
     *     
     */
    public void setProvince(Province value) {
        this.province = value;
    }

    /**
     * Gets the value of the cardFrontImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCardFrontImage() {
        return cardFrontImage;
    }

    /**
     * Sets the value of the cardFrontImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCardFrontImage(byte[] value) {
        this.cardFrontImage = value;
    }

    /**
     * Gets the value of the cardBackImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCardBackImage() {
        return cardBackImage;
    }

    /**
     * Sets the value of the cardBackImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCardBackImage(byte[] value) {
        this.cardBackImage = value;
    }

    /**
     * Gets the value of the consumerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerId() {
        return consumerId;
    }

    /**
     * Sets the value of the consumerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerId(String value) {
        this.consumerId = value;
    }

    /**
     * Gets the value of the producerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerId() {
        return producerId;
    }

    /**
     * Sets the value of the producerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerId(String value) {
        this.producerId = value;
    }

}
