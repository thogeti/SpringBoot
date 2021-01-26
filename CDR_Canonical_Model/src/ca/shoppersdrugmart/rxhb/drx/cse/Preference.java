
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The preference information that Customer
 *                 is associated with.
 * 
 * <p>Java class for Preference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Preference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="issubscribedtomarketinginfr" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="issubscribedtomarketinginen" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="iscommunicationoptionbyemail" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="iscommunicationoptionbysms" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="defaultcommunicationmethod" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="isnotificationdetailshowingdrug" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="isautoreminderselected" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="callWhenReadyFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="emailNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="emailFillNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="emailPickupNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="smsFillNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="emailMarketing" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="smsNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="smsPickupNotification" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdateTimestamp" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="globalAutoFill" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Number" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preference", propOrder = {
    "issubscribedtomarketinginfr",
    "issubscribedtomarketinginen",
    "iscommunicationoptionbyemail",
    "iscommunicationoptionbysms",
    "defaultcommunicationmethod",
    "isnotificationdetailshowingdrug",
    "isautoreminderselected",
    "callWhenReadyFlag",
    "emailNotification",
    "emailFillNotification",
    "emailPickupNotification",
    "smsFillNotification",
    "emailMarketing",
    "smsNotification",
    "smsPickupNotification",
    "lastUpdateTimestamp",
    "globalAutoFill"
})
public class Preference {

    protected String issubscribedtomarketinginfr;
    protected String issubscribedtomarketinginen;
    protected String iscommunicationoptionbyemail;
    protected String iscommunicationoptionbysms;
    protected String defaultcommunicationmethod;
    protected String isnotificationdetailshowingdrug;
    protected String isautoreminderselected;
    protected String callWhenReadyFlag;
    protected String emailNotification;
    protected String emailFillNotification;
    protected String emailPickupNotification;
    protected String smsFillNotification;
    protected String emailMarketing;
    protected String smsNotification;
    protected String smsPickupNotification;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;
    protected Integer globalAutoFill;

    /**
     * Gets the value of the issubscribedtomarketinginfr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssubscribedtomarketinginfr() {
        return issubscribedtomarketinginfr;
    }

    /**
     * Sets the value of the issubscribedtomarketinginfr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssubscribedtomarketinginfr(String value) {
        this.issubscribedtomarketinginfr = value;
    }

    /**
     * Gets the value of the issubscribedtomarketinginen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssubscribedtomarketinginen() {
        return issubscribedtomarketinginen;
    }

    /**
     * Sets the value of the issubscribedtomarketinginen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssubscribedtomarketinginen(String value) {
        this.issubscribedtomarketinginen = value;
    }

    /**
     * Gets the value of the iscommunicationoptionbyemail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIscommunicationoptionbyemail() {
        return iscommunicationoptionbyemail;
    }

    /**
     * Sets the value of the iscommunicationoptionbyemail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIscommunicationoptionbyemail(String value) {
        this.iscommunicationoptionbyemail = value;
    }

    /**
     * Gets the value of the iscommunicationoptionbysms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIscommunicationoptionbysms() {
        return iscommunicationoptionbysms;
    }

    /**
     * Sets the value of the iscommunicationoptionbysms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIscommunicationoptionbysms(String value) {
        this.iscommunicationoptionbysms = value;
    }

    /**
     * Gets the value of the defaultcommunicationmethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultcommunicationmethod() {
        return defaultcommunicationmethod;
    }

    /**
     * Sets the value of the defaultcommunicationmethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultcommunicationmethod(String value) {
        this.defaultcommunicationmethod = value;
    }

    /**
     * Gets the value of the isnotificationdetailshowingdrug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsnotificationdetailshowingdrug() {
        return isnotificationdetailshowingdrug;
    }

    /**
     * Sets the value of the isnotificationdetailshowingdrug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsnotificationdetailshowingdrug(String value) {
        this.isnotificationdetailshowingdrug = value;
    }

    /**
     * Gets the value of the isautoreminderselected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsautoreminderselected() {
        return isautoreminderselected;
    }

    /**
     * Sets the value of the isautoreminderselected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsautoreminderselected(String value) {
        this.isautoreminderselected = value;
    }

    /**
     * Gets the value of the callWhenReadyFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallWhenReadyFlag() {
        return callWhenReadyFlag;
    }

    /**
     * Sets the value of the callWhenReadyFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallWhenReadyFlag(String value) {
        this.callWhenReadyFlag = value;
    }

    /**
     * Gets the value of the emailNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailNotification() {
        return emailNotification;
    }

    /**
     * Sets the value of the emailNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailNotification(String value) {
        this.emailNotification = value;
    }

    /**
     * Gets the value of the emailFillNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailFillNotification() {
        return emailFillNotification;
    }

    /**
     * Sets the value of the emailFillNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailFillNotification(String value) {
        this.emailFillNotification = value;
    }

    /**
     * Gets the value of the emailPickupNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPickupNotification() {
        return emailPickupNotification;
    }

    /**
     * Sets the value of the emailPickupNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPickupNotification(String value) {
        this.emailPickupNotification = value;
    }

    /**
     * Gets the value of the smsFillNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsFillNotification() {
        return smsFillNotification;
    }

    /**
     * Sets the value of the smsFillNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsFillNotification(String value) {
        this.smsFillNotification = value;
    }

    /**
     * Gets the value of the emailMarketing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailMarketing() {
        return emailMarketing;
    }

    /**
     * Sets the value of the emailMarketing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailMarketing(String value) {
        this.emailMarketing = value;
    }

    /**
     * Gets the value of the smsNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsNotification() {
        return smsNotification;
    }

    /**
     * Sets the value of the smsNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsNotification(String value) {
        this.smsNotification = value;
    }

    /**
     * Gets the value of the smsPickupNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsPickupNotification() {
        return smsPickupNotification;
    }

    /**
     * Sets the value of the smsPickupNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsPickupNotification(String value) {
        this.smsPickupNotification = value;
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

}
