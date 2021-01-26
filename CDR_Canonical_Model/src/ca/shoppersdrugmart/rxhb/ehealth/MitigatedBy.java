
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MitigatedBy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MitigatedBy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="managementDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timeOfMitigation" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="exceptionId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityUUID" minOccurs="0"/&gt;
 *         &lt;element name="managementType" type="{http://shoppersdrugmart.ca/RxHB/eHealth}ManagementType" minOccurs="0"/&gt;
 *         &lt;element name="dispenser" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Dispenser" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MitigatedBy", propOrder = {
    "managementDescription",
    "timeOfMitigation",
    "exceptionId",
    "managementType",
    "dispenser"
})
public class MitigatedBy {

    protected String managementDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeOfMitigation;
    protected String exceptionId;
    @XmlSchemaType(name = "string")
    protected ManagementType managementType;
    protected Dispenser dispenser;

    /**
     * Gets the value of the managementDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagementDescription() {
        return managementDescription;
    }

    /**
     * Sets the value of the managementDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagementDescription(String value) {
        this.managementDescription = value;
    }

    /**
     * Gets the value of the timeOfMitigation property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeOfMitigation() {
        return timeOfMitigation;
    }

    /**
     * Sets the value of the timeOfMitigation property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeOfMitigation(XMLGregorianCalendar value) {
        this.timeOfMitigation = value;
    }

    /**
     * Gets the value of the exceptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionId() {
        return exceptionId;
    }

    /**
     * Sets the value of the exceptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionId(String value) {
        this.exceptionId = value;
    }

    /**
     * Gets the value of the managementType property.
     * 
     * @return
     *     possible object is
     *     {@link ManagementType }
     *     
     */
    public ManagementType getManagementType() {
        return managementType;
    }

    /**
     * Sets the value of the managementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagementType }
     *     
     */
    public void setManagementType(ManagementType value) {
        this.managementType = value;
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

}
