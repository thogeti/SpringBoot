
package ca.shoppersdrugmart.rxhb.drx.rxtransfer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ca.shoppersdrugmart.rxhb.drx.cse.Customer;
import ca.shoppersdrugmart.rxhb.ehealth.Patient;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;


/**
 * <p>Java class for RxTransfer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RxTransfer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}Customer" minOccurs="0"/&gt;
 *         &lt;element name="patient" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Patient"/&gt;
 *         &lt;element name="prescription" type="{http://shoppersdrugmart.ca/RxHB/eHealth}Prescription" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="fromStoreType" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="fromStoreName" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="fromStorePhoneNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="transferAllPrescriptions" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Flag" minOccurs="0"/&gt;
 *         &lt;element name="transferToStoreId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *         &lt;element name="initiationTime" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}Timestamp" minOccurs="0"/&gt;
 *         &lt;element name="sourceChannel" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PharmacyChannel" minOccurs="0"/&gt;
 *         &lt;element name="prescriptionTransferId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RxTransfer", propOrder = {
    "customer",
    "patient",
    "prescription",
    "fromStoreType",
    "fromStoreName",
    "fromStorePhoneNumber",
    "transferAllPrescriptions",
    "transferToStoreId",
    "initiationTime",
    "sourceChannel",
    "prescriptionTransferId"
})
public class RxTransfer {

    protected Customer customer;
    @XmlElement(required = true)
    protected Patient patient;
    protected List<Prescription> prescription;
    protected String fromStoreType;
    protected String fromStoreName;
    protected String fromStorePhoneNumber;
    protected String transferAllPrescriptions;
    protected String transferToStoreId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar initiationTime;
        protected PharmacyChannel sourceChannel;
    protected String prescriptionTransferId;

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
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

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
     * Gets the value of the fromStoreType property.
     * 
     * @return
     *     possible object is
     *     {@link Division }
     *     
     */
    public String getFromStoreType() {
        return fromStoreType;
    }

    /**
     * Sets the value of the fromStoreType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Division }
     *     
     */
    public void setFromStoreType(String value) {
        this.fromStoreType = value;
    }

    /**
     * Gets the value of the fromStoreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStoreName() {
        return fromStoreName;
    }

    /**
     * Sets the value of the fromStoreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStoreName(String value) {
        this.fromStoreName = value;
    }

    /**
     * Gets the value of the fromStorePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStorePhoneNumber() {
        return fromStorePhoneNumber;
    }

    /**
     * Sets the value of the fromStorePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStorePhoneNumber(String value) {
        this.fromStorePhoneNumber = value;
    }

    /**
     * Gets the value of the transferAllPrescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferAllPrescriptions() {
        return transferAllPrescriptions;
    }

    /**
     * Sets the value of the transferAllPrescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferAllPrescriptions(String value) {
        this.transferAllPrescriptions = value;
    }

    /**
     * Gets the value of the transferToStoreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferToStoreId() {
        return transferToStoreId;
    }

    /**
     * Sets the value of the transferToStoreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferToStoreId(String value) {
        this.transferToStoreId = value;
    }

    /**
     * Gets the value of the initiationTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInitiationTime() {
        return initiationTime;
    }

    /**
     * Sets the value of the initiationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInitiationTime(XMLGregorianCalendar value) {
        this.initiationTime = value;
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
     * Gets the value of the prescriptionTransferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionTransferId() {
        return prescriptionTransferId;
    }

    /**
     * Sets the value of the prescriptionTransferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionTransferId(String value) {
        this.prescriptionTransferId = value;
    }

}
