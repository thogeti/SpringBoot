
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;


/**
 * <p>Java class for UpdatePreferenceEntityBySourceChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatePreferenceEntityBySourceChannel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entityId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric" minOccurs="0"/&gt;
 *         &lt;element name="entityType" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}EntityType" minOccurs="0"/&gt;
 *         &lt;element name="storeNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="autoRefillFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="pickupReminderFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="globalAutoFill" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Number" minOccurs="0"/&gt;
 *         &lt;element name="sourceChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdateTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="garConfirmationStatus" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatePreferenceEntityBySourceChannel", propOrder = {
    "entityId",
    "entityType",
    "storeNumber",
    "autoRefillFlag",
    "pickupReminderFlag",
    "globalAutoFill",
    "sourceChannel",
    "lastUpdateTimestamp",
    "garConfirmationStatus"
})
public class UpdatePreferenceEntityBySourceChannel {

    protected String entityId;
    @XmlSchemaType(name = "string")
    protected EntityType entityType;
    protected String storeNumber;
    protected String autoRefillFlag;
    protected String pickupReminderFlag;
    protected Integer globalAutoFill;
    protected PharmacyChannel sourceChannel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;
    protected String garConfirmationStatus;

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
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setEntityType(EntityType value) {
        this.entityType = value;
    }

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
     * Gets the value of the autoRefillFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoRefillFlag() {
        return autoRefillFlag;
    }

    /**
     * Sets the value of the autoRefillFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoRefillFlag(String value) {
        this.autoRefillFlag = value;
    }

    /**
     * Gets the value of the pickupReminderFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupReminderFlag() {
        return pickupReminderFlag;
    }

    /**
     * Sets the value of the pickupReminderFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupReminderFlag(String value) {
        this.pickupReminderFlag = value;
    }

    /**
     * Gets the value of the globalAutoFill property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGlobalAutoFill() {
        return globalAutoFill;
    }

    /**
     * Sets the value of the globalAutoFill property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGlobalAutoFill(Integer value) {
        this.globalAutoFill = value;
    }

    /**
     * Gets the value of the sourceChannel property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyChannel }
     *     
     */
    public PharmacyChannel getSourceChannel() {
        return sourceChannel;
    }

    /**
     * Sets the value of the sourceChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyChannel }
     *     
     */
    public void setSourceChannel(PharmacyChannel value) {
        this.sourceChannel = value;
    }

    /**
     * Gets the value of the lastUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    /**
     * Sets the value of the lastUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateTimestamp(XMLGregorianCalendar value) {
        this.lastUpdateTimestamp = value;
    }

    /**
     * Gets the value of the garConfirmationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGarConfirmationStatus() {
        return garConfirmationStatus;
    }

    /**
     * Sets the value of the garConfirmationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGarConfirmationStatus(String value) {
        this.garConfirmationStatus = value;
    }

}
