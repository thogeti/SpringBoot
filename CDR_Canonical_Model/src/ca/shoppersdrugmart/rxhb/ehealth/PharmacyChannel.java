
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PharmacyChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PharmacyChannel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="channelType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ChannelType"/&gt;
 *         &lt;element name="communicationMode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}CommunicationModeType"/&gt;
 *         &lt;element name="engagment" type="{http://shoppersdrugmart.ca/RxHB/eHealth}EngagementType"/&gt;
 *         &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmacyChannel", propOrder = {
    "channelType",
    "communicationMode",
    "engagment"
})
public class PharmacyChannel {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ChannelType channelType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CommunicationModeType communicationMode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EngagementType engagment;
 
    /**
     * Gets the value of the channelType property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelType }
     *     
     */
    public ChannelType getChannelType() {
        return channelType;
    }

    /**
     * Sets the value of the channelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelType }
     *     
     */
    public void setChannelType(ChannelType value) {
        this.channelType = value;
    }

    /**
     * Gets the value of the communicationMode property.
     * 
     * @return
     *     possible object is
     *     {@link CommunicationModeType }
     *     
     */
    public CommunicationModeType getCommunicationMode() {
        return communicationMode;
    }

    /**
     * Sets the value of the communicationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationModeType }
     *     
     */
    public void setCommunicationMode(CommunicationModeType value) {
        this.communicationMode = value;
    }

    /**
     * Gets the value of the engagment property.
     * 
     * @return
     *     possible object is
     *     {@link EngagementType }
     *     
     */
    public EngagementType getEngagment() {
        return engagment;
    }

    /**
     * Sets the value of the engagment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EngagementType }
     *     
     */
    public void setEngagment(EngagementType value) {
        this.engagment = value;
    }

}
