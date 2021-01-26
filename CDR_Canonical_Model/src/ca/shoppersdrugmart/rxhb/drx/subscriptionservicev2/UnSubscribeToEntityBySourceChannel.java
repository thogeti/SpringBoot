
package ca.shoppersdrugmart.rxhb.drx.subscriptionservicev2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;


/**
 * <p>Java class for UnSubscribeToEntityBySourceChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnSubscribeToEntityBySourceChannel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="storeNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="entityType" type="{http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2}SubscriptionEntityType"/&gt;
 *         &lt;element name="entityId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric"/&gt;
 *         &lt;element ref="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnSubscribeToEntityBySourceChannel", propOrder = {
    "storeNumber",
    "entityType",
    "entityId",
    "pharmacyChannel"
})
public class UnSubscribeToEntityBySourceChannel {

    @XmlElement(required = true)
    protected String storeNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SubscriptionEntityType entityType;
    @XmlElement(required = true)
    protected String entityId;
    @XmlElement(name = "PharmacyChannel", namespace = "http://shoppersdrugmart.ca/RxHB/eHealth", required = true)
    protected PharmacyChannel pharmacyChannel;

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
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionEntityType }
     *     
     */
    public SubscriptionEntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionEntityType }
     *     
     */
    public void setEntityType(SubscriptionEntityType value) {
        this.entityType = value;
    }

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityId(String value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the pharmacyChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getPharmacyChannel() {
        return pharmacyChannel;
    }

    /**
     * Sets the value of the pharmacyChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setPharmacyChannel(PharmacyChannel value) {
        this.pharmacyChannel = value;
    }

}
