
package ca.shoppersdrugmart.rxhb.drx.customerservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.Customer;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;


/**
 * <p>Java class for AssociateCustomer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociateCustomer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Customer" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}Customer"/&gt;
 *         &lt;element name="Patient" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Patient" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CareRecipient" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}Customer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociateCustomer", propOrder = {
    "customer",
    "patient",
    "careRecipient"
})
public class AssociateCustomer {

    @XmlElement(name = "Customer", required = true)
    protected Customer customer;
    @XmlElement(name = "Patient")
    protected List<Patient> patient;
    @XmlElement(name = "CareRecipient")
    protected List<Customer> careRecipient;

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patient }
     * 
     * 
     */
    public List<Patient> getPatient() {
        if (patient == null) {
            patient = new ArrayList<Patient>();
        }
        return this.patient;
    }

    /**
     * Gets the value of the careRecipient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the careRecipient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCareRecipient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Customer }
     * 
     * 
     */
    public List<Customer> getCareRecipient() {
        if (careRecipient == null) {
            careRecipient = new ArrayList<Customer>();
        }
        return this.careRecipient;
    }

}
