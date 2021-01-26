
package ca.shoppersdrugmart.rxhb.drx.getpatient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetPatientByTransactionNumber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPatientByTransactionNumber"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Banner" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="StoreNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="ConsumerName" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="ConsumerSendTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="CorrelationId" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}StringValue"/&gt;
 *         &lt;element name="TransactionNumber" type="{http://www.shoppersdrugmart.ca/message/SDMDataTypes}IdentityAlphanumeric"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPatientByTransactionNumber", propOrder = {
    "banner",
    "storeNumber",
    "consumerName",
    "consumerSendTimestamp",
    "correlationId",
    "transactionNumber"
})
public class GetPatientByTransactionNumber {

    @XmlElement(name = "Banner", required = true)
    protected String banner;
    @XmlElement(name = "StoreNumber", required = true)
    protected String storeNumber;
    @XmlElement(name = "ConsumerName", required = true)
    protected String consumerName;
    @XmlElement(name = "ConsumerSendTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consumerSendTimestamp;
    @XmlElement(name = "CorrelationId", required = true)
    protected String correlationId;
    @XmlElement(name = "TransactionNumber", required = true)
    protected String transactionNumber;

    /**
     * Gets the value of the banner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanner() {
        return banner;
    }

    /**
     * Sets the value of the banner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanner(String value) {
        this.banner = value;
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
     * Gets the value of the consumerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * Sets the value of the consumerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerName(String value) {
        this.consumerName = value;
    }

    /**
     * Gets the value of the consumerSendTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsumerSendTimestamp() {
        return consumerSendTimestamp;
    }

    /**
     * Sets the value of the consumerSendTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsumerSendTimestamp(XMLGregorianCalendar value) {
        this.consumerSendTimestamp = value;
    }

    /**
     * Gets the value of the correlationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the value of the transactionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Sets the value of the transactionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionNumber(String value) {
        this.transactionNumber = value;
    }

}
