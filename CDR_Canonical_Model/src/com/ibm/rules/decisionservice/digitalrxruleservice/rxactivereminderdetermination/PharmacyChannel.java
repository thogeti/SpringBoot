
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmacyChannel", propOrder = {
    "channelType",
    "communicationMode",
    "engagement" //TE97_JAXB modified to engagement ---typo
})
public class PharmacyChannel {
	
	    protected String channelType;
	    protected String communicationMode;
	    protected String engagement;//TE97_JAXB modified to engagement ---typo
		public String getChannelType() {
			return channelType;
		}
		public void setChannelType(String channelType) {
			this.channelType = channelType;
		}
		public String getCommunicationMode() {
			return communicationMode;
		}
		public void setCommunicationMode(String communicationMode) {
			this.communicationMode = communicationMode;
		}
		//TE97_JAXB modified to engagement --typo start
		public String getEngagement() {
			return engagement;
		}
		public void setEngagement(String engagement) {
			this.engagement = engagement;
		}
//TE97_JAXB modified to engagement --typo end

 /*   @XmlElement(required = true)
    protected ChannelType channelType;
    @XmlElement(required = true)
    protected CommunicationModeType communicationMode;
    @XmlElement(required = true)
    protected EngagementType engagment;

    *//**
     * Gets the value of the channelType property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelType }
     *     
     *//*
    public ChannelType getChannelType() {
        return channelType;
    }

    *//**
     * Sets the value of the channelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelType }
     *     
     *//*
    public void setChannelType(ChannelType value) {
        this.channelType = value;
    }

    *//**
     * Gets the value of the communicationMode property.
     * 
     * @return
     *     possible object is
     *     {@link CommunicationModeType }
     *     
     *//*
    public CommunicationModeType getCommunicationMode() {
        return communicationMode;
    }

    *//**
     * Sets the value of the communicationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationModeType }
     *     
     *//*
    public void setCommunicationMode(CommunicationModeType value) {
        this.communicationMode = value;
    }

    *//**
     * Gets the value of the engagment property.
     * 
     * @return
     *     possible object is
     *     {@link EngagementType }
     *     
     *//*
    public EngagementType getEngagment() {
        return engagment;
    }

    *//**
     * Sets the value of the engagment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EngagementType }
     *     
     *//*
    public void setEngagment(EngagementType value) {
        this.engagment = value;
    }
*/
}
