
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PharmacyChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PharmacyChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channelType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="communicationMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="engagment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    protected String channelType;
    @XmlElement(required = true)
    protected String communicationMode;
    @XmlElement(required = true)
    protected String engagment;

    /**
     * Gets the value of the channelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * Sets the value of the channelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelType(String value) {
        this.channelType = value;
    }

    /**
     * Gets the value of the communicationMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationMode() {
        return communicationMode;
    }

    /**
     * Sets the value of the communicationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationMode(String value) {
        this.communicationMode = value;
    }

    /**
     * Gets the value of the engagment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngagment() {
        return engagment;
    }

    /**
     * Sets the value of the engagment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngagment(String value) {
        this.engagment = value;
    }

}
