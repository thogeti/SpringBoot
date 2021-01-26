
package ca.shoppersdrugmart.rxhb.ehealth;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 * The licensed  professional legally authorized to prescribe drugs.
 * 
 * <p>Java class for Prescriber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prescriber"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isActiveFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="prescriberTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PrescriberType" minOccurs="0"/&gt;
 *         &lt;element name="person" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Person" minOccurs="0"/&gt;
 *         &lt;element name="professionalRegistration" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ProfessionalRegistration" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="isPrimaryFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prescriber", propOrder = {
    "consumerId",
    "producerId",
    "isActiveFlag",
    "prescriberTypeCode",
    "person",
    "professionalRegistration",
    "isPrimaryFlag"
})
public class Prescriber {

    protected String consumerId;
    protected String producerId;
    @XmlElement(defaultValue = "false")
    protected boolean isActiveFlag;
    @XmlSchemaType(name = "string")
    protected PrescriberType prescriberTypeCode;
    protected Person person;
    protected List<ProfessionalRegistration> professionalRegistration;
    @XmlElement(defaultValue = "false")
    protected Boolean isPrimaryFlag;
    
    @XmlTransient
	protected Long prscbrkey = null;
    
    @XmlTransient
    public Long getPrscbrKey() {
           return prscbrkey;
    }
    public void setPrscbrKey(Long value) {
           this.prscbrkey = value;
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

    /**
     * Gets the value of the isActiveFlag property.
     * 
     */
    public boolean isIsActiveFlag() {
        return isActiveFlag;
    }

    /**
     * Sets the value of the isActiveFlag property.
     * 
     */
    public void setIsActiveFlag(boolean value) {
        this.isActiveFlag = value;
    }

    /**
     * Gets the value of the prescriberTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriberType }
     *     
     */
    public PrescriberType getPrescriberTypeCode() {
        return prescriberTypeCode;
    }

    /**
     * Sets the value of the prescriberTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriberType }
     *     
     */
    public void setPrescriberTypeCode(PrescriberType value) {
        this.prescriberTypeCode = value;
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
     * Gets the value of the professionalRegistration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the professionalRegistration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfessionalRegistration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProfessionalRegistration }
     * 
     * 
     */
    public List<ProfessionalRegistration> getProfessionalRegistration() {
        if (professionalRegistration == null) {
            professionalRegistration = new ArrayList<ProfessionalRegistration>();
        }
        return this.professionalRegistration;
    }

    /**
     * Gets the value of the isPrimaryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPrimaryFlag() {
        return isPrimaryFlag;
    }

    /**
     * Sets the value of the isPrimaryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPrimaryFlag(Boolean value) {
        this.isPrimaryFlag = value;
    }

}
