
package ca.shoppersdrugmart.rxhb.drx.dispensing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.drx.cse.RefillRequest;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;


/**
 * <p>Java class for Refill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Refill"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" maxOccurs="unbounded"/&gt;
 *         &lt;element name="RequestedPickupTime" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp"/&gt;
 *         &lt;element name="sourceChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel"/&gt;
 *         &lt;element name="RefillRequest" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}RefillRequest" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="persistStrategyFlag" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Refill", propOrder = {
    "prescription",
    "requestedPickupTime",
    "sourceChannel",
    "refillRequest",
    "persistStrategyFlag"
})
public class Refill {

    @XmlElement(name = "Prescription", required = true)
    protected List<Prescription> prescription;
    @XmlElement(name = "RequestedPickupTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestedPickupTime;
    @XmlElement(required = true)
    protected PharmacyChannel sourceChannel;
    @XmlElement(name = "RefillRequest")
    protected List<RefillRequest> refillRequest;
    protected String persistStrategyFlag;

    /**
     * Gets the value of the prescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prescription }
     * 
     * 
     */
    public List<Prescription> getPrescription() {
        if (prescription == null) {
            prescription = new ArrayList<Prescription>();
        }
        return this.prescription;
    }

    /**
     * Gets the value of the requestedPickupTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestedPickupTime() {
        return requestedPickupTime;
    }

    /**
     * Sets the value of the requestedPickupTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestedPickupTime(XMLGregorianCalendar value) {
        this.requestedPickupTime = value;
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
     * Gets the value of the refillRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refillRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefillRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefillRequest }
     * 
     * 
     */
    public List<RefillRequest> getRefillRequest() {
        if (refillRequest == null) {
            refillRequest = new ArrayList<RefillRequest>();
        }
        return this.refillRequest;
    }

    /**
     * Gets the value of the persistStrategyFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistStrategyFlag() {
        return persistStrategyFlag;
    }

    /**
     * Sets the value of the persistStrategyFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersistStrategyFlag(String value) {
        this.persistStrategyFlag = value;
    }

}
