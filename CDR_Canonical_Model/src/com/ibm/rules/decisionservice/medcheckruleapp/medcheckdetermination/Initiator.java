
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Initiator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Initiator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="person" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Person" minOccurs="0"/>
 *         &lt;element name="substitutionInitiator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionalRegistration" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}ProfessionalRegistration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Initiator", propOrder = {
    "consumerId",
    "producerId",
    "person",
    "substitutionInitiator",
    "professionalRegistration"
})
public class Initiator {

    protected String consumerId;
    protected String producerId;
    protected Person person;
    protected String substitutionInitiator;
    protected ProfessionalRegistration professionalRegistration;

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

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    /**
     * Gets the value of the substitutionInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutionInitiator() {
        return substitutionInitiator;
    }

    /**
     * Sets the value of the substitutionInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutionInitiator(String value) {
        this.substitutionInitiator = value;
    }

    /**
     * Gets the value of the professionalRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link ProfessionalRegistration }
     *     
     */
    public ProfessionalRegistration getProfessionalRegistration() {
        return professionalRegistration;
    }

    /**
     * Sets the value of the professionalRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfessionalRegistration }
     *     
     */
    public void setProfessionalRegistration(ProfessionalRegistration value) {
        this.professionalRegistration = value;
    }

}
