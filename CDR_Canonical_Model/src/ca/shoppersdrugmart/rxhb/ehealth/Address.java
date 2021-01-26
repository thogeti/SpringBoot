
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The legally associated specification of the patients place of residence.
 * 
 * <p>Java class for Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Address"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressLineOne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressLineTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isPrimaryAddressFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="primaryPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="faxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contactPurposeType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ContactPurposeType" minOccurs="0"/&gt;
 *         &lt;element name="provinceCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Province" minOccurs="0"/&gt;
 *         &lt;element name="alternatePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="isActiveAddressFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isMailAddressFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isReauthEmailFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isReauthPhoneFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isReauthFaxFlag" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ReauthFaxFlag" minOccurs="0"/&gt;
 *         &lt;element name="isReauthVisitFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="lastUpdateTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = {
    "addressLineOne",
    "addressLineTwo",
    "cityName",
    "countryCode",
    "consumerId",
    "postalCode",
    "producerId",
    "email",
    "isPrimaryAddressFlag",
    "primaryPhoneNumber",
    "faxNumber",
    "contactPurposeType",
    "provinceCode",
    "alternatePhoneNumber",
    "isActiveAddressFlag",
    "isMailAddressFlag",
    "isReauthEmailFlag",
    "isReauthPhoneFlag",
    "isReauthFaxFlag",
    "isReauthVisitFlag",
    "lastUpdateTimestamp"
})
public class Address {

    protected String addressLineOne;
    protected String addressLineTwo;
    protected String cityName;
    protected String countryCode;
    protected String consumerId;
    protected String postalCode;
    protected String producerId;
    protected String email;
    @XmlElement(defaultValue = "false")
    protected Boolean isPrimaryAddressFlag;
    protected String primaryPhoneNumber;
    protected String faxNumber;
    @XmlSchemaType(name = "string")
    protected ContactPurposeType contactPurposeType;
    @XmlSchemaType(name = "string")
    protected Province provinceCode;
    protected List<String> alternatePhoneNumber;
    @XmlElement(defaultValue = "false")
    protected Boolean isActiveAddressFlag;
    protected Boolean isMailAddressFlag;
    protected Boolean isReauthEmailFlag;
    protected Boolean isReauthPhoneFlag;
    @XmlSchemaType(name = "string")
    protected ReauthFaxFlag isReauthFaxFlag;
    protected Boolean isReauthVisitFlag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateTimestamp;

    /**
     * Gets the value of the addressLineOne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLineOne() {
        return addressLineOne;
    }

    /**
     * Sets the value of the addressLineOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLineOne(String value) {
        this.addressLineOne = value;
    }

    /**
     * Gets the value of the addressLineTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    /**
     * Sets the value of the addressLineTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLineTwo(String value) {
        this.addressLineTwo = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
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
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the isPrimaryAddressFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPrimaryAddressFlag() {
        return isPrimaryAddressFlag;
    }

    /**
     * Sets the value of the isPrimaryAddressFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPrimaryAddressFlag(Boolean value) {
        this.isPrimaryAddressFlag = value;
    }

    /**
     * Gets the value of the primaryPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * Sets the value of the primaryPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPhoneNumber(String value) {
        this.primaryPhoneNumber = value;
    }

    /**
     * Gets the value of the faxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the value of the faxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNumber(String value) {
        this.faxNumber = value;
    }

    /**
     * Gets the value of the contactPurposeType property.
     * 
     * @return
     *     possible object is
     *     {@link ContactPurposeType }
     *     
     */
    public ContactPurposeType getContactPurposeType() {
        return contactPurposeType;
    }

    /**
     * Sets the value of the contactPurposeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactPurposeType }
     *     
     */
    public void setContactPurposeType(ContactPurposeType value) {
        this.contactPurposeType = value;
    }

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link Province }
     *     
     */
    public Province getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Province }
     *     
     */
    public void setProvinceCode(Province value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the alternatePhoneNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternatePhoneNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternatePhoneNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAlternatePhoneNumber() {
        if (alternatePhoneNumber == null) {
            alternatePhoneNumber = new ArrayList<String>();
        }
        return this.alternatePhoneNumber;
    }

    /**
     * Gets the value of the isActiveAddressFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsActiveAddressFlag() {
        return isActiveAddressFlag;
    }

    /**
     * Sets the value of the isActiveAddressFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsActiveAddressFlag(Boolean value) {
        this.isActiveAddressFlag = value;
    }

    /**
     * Gets the value of the isMailAddressFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsMailAddressFlag() {
        return isMailAddressFlag;
    }

    /**
     * Sets the value of the isMailAddressFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMailAddressFlag(Boolean value) {
        this.isMailAddressFlag = value;
    }

    /**
     * Gets the value of the isReauthEmailFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReauthEmailFlag() {
        return isReauthEmailFlag;
    }

    /**
     * Sets the value of the isReauthEmailFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReauthEmailFlag(Boolean value) {
        this.isReauthEmailFlag = value;
    }

    /**
     * Gets the value of the isReauthPhoneFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReauthPhoneFlag() {
        return isReauthPhoneFlag;
    }

    /**
     * Sets the value of the isReauthPhoneFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReauthPhoneFlag(Boolean value) {
        this.isReauthPhoneFlag = value;
    }

    /**
     * Gets the value of the isReauthFaxFlag property.
     * 
     * @return
     *     possible object is
     *     {@link ReauthFaxFlag }
     *     
     */
    public ReauthFaxFlag getIsReauthFaxFlag() {
        return isReauthFaxFlag;
    }

    /**
     * Sets the value of the isReauthFaxFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReauthFaxFlag }
     *     
     */
    public void setIsReauthFaxFlag(ReauthFaxFlag value) {
        this.isReauthFaxFlag = value;
    }

    /**
     * Gets the value of the isReauthVisitFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReauthVisitFlag() {
        return isReauthVisitFlag;
    }
 
    /**
     * Sets the value of the isReauthVisitFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReauthVisitFlag(Boolean value) {
        this.isReauthVisitFlag = value;
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
	public void setAlternatePhoneNumber(List<String> alterPhList) {
		this.alternatePhoneNumber=alterPhList;
		
	}

}
