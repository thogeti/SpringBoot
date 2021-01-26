
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Consent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Consent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentEffectiveTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="consentEndTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentOverrideReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenser" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Dispenser" minOccurs="0"/>
 *         &lt;element name="consentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentProvider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentRelationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificationMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsentUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentCreateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="consentUpdateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consent", propOrder = {
    "consumerId",
    "consentEffectiveTimestamp",
    "consentEndTimestamp",
    "producerId",
    "consentOverrideReasonCode",
    "dispenser",
    "consentType",
    "consentReasonCode",
    "consentProvider",
    "agentFirstName",
    "agentLastName",
    "agentRelationship",
    "notificationMethod",
    "consentUserId",
    "consentCreateTimestamp",
    "consentUpdateTimestamp"
})
public class Consent {

    protected String consumerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consentEffectiveTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consentEndTimestamp;
    protected String producerId;
    protected String consentOverrideReasonCode;
    protected Dispenser dispenser;
    protected String consentType;
    protected String consentReasonCode;
    protected String consentProvider;
    protected String agentFirstName;
    protected String agentLastName;
    protected String agentRelationship;
    protected String notificationMethod;
    @XmlElement(name = "ConsentUserId")
    protected String consentUserId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consentCreateTimestamp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consentUpdateTimestamp;

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
     * Gets the value of the consentEffectiveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsentEffectiveTimestamp() {
        return consentEffectiveTimestamp;
    }

    /**
     * Sets the value of the consentEffectiveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsentEffectiveTimestamp(XMLGregorianCalendar value) {
        this.consentEffectiveTimestamp = value;
    }

    /**
     * Gets the value of the consentEndTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsentEndTimestamp() {
        return consentEndTimestamp;
    }

    /**
     * Sets the value of the consentEndTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsentEndTimestamp(XMLGregorianCalendar value) {
        this.consentEndTimestamp = value;
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

    /**
     * Gets the value of the consentOverrideReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentOverrideReasonCode() {
        return consentOverrideReasonCode;
    }

    /**
     * Sets the value of the consentOverrideReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentOverrideReasonCode(String value) {
        this.consentOverrideReasonCode = value;
    }

    /**
     * Gets the value of the dispenser property.
     * 
     * @return
     *     possible object is
     *     {@link Dispenser }
     *     
     */
    public Dispenser getDispenser() {
        return dispenser;
    }

    /**
     * Sets the value of the dispenser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispenser }
     *     
     */
    public void setDispenser(Dispenser value) {
        this.dispenser = value;
    }

    /**
     * Gets the value of the consentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentType() {
        return consentType;
    }

    /**
     * Sets the value of the consentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentType(String value) {
        this.consentType = value;
    }

    /**
     * Gets the value of the consentReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentReasonCode() {
        return consentReasonCode;
    }

    /**
     * Sets the value of the consentReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentReasonCode(String value) {
        this.consentReasonCode = value;
    }

    /**
     * Gets the value of the consentProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentProvider() {
        return consentProvider;
    }

    /**
     * Sets the value of the consentProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentProvider(String value) {
        this.consentProvider = value;
    }

    /**
     * Gets the value of the agentFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentFirstName() {
        return agentFirstName;
    }

    /**
     * Sets the value of the agentFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentFirstName(String value) {
        this.agentFirstName = value;
    }

    /**
     * Gets the value of the agentLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentLastName() {
        return agentLastName;
    }

    /**
     * Sets the value of the agentLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentLastName(String value) {
        this.agentLastName = value;
    }

    /**
     * Gets the value of the agentRelationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentRelationship() {
        return agentRelationship;
    }

    /**
     * Sets the value of the agentRelationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentRelationship(String value) {
        this.agentRelationship = value;
    }

    /**
     * Gets the value of the notificationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationMethod() {
        return notificationMethod;
    }

    /**
     * Sets the value of the notificationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationMethod(String value) {
        this.notificationMethod = value;
    }

    /**
     * Gets the value of the consentUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentUserId() {
        return consentUserId;
    }

    /**
     * Sets the value of the consentUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentUserId(String value) {
        this.consentUserId = value;
    }

    /**
     * Gets the value of the consentCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsentCreateTimestamp() {
        return consentCreateTimestamp;
    }

    /**
     * Sets the value of the consentCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsentCreateTimestamp(XMLGregorianCalendar value) {
        this.consentCreateTimestamp = value;
    }

    /**
     * Gets the value of the consentUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsentUpdateTimestamp() {
        return consentUpdateTimestamp;
    }

    /**
     * Sets the value of the consentUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsentUpdateTimestamp(XMLGregorianCalendar value) {
        this.consentUpdateTimestamp = value;
    }

}
