
package ca.shoppersdrugmart.message.errorxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This is the granular detail of the message which contains all of the information related to the specifics of the error.
 * 
 * <p>Java class for ErrorDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Category" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}Category"/>
 *         &lt;element name="CompleteException" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}CompleteException" minOccurs="0"/>
 *         &lt;element name="ErrorMessageSummary" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}ErrorMessageSummary"/>
 *         &lt;element name="ErrorNumber" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}ErrorNumber" minOccurs="0"/>
 *         &lt;element name="ErrorSourceComponent" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}ErrorSourceComponent" minOccurs="0"/>
 *         &lt;element name="NameValuePair" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}NameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SequenceID" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}SequenceID"/>
 *         &lt;element name="xPath" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}XPath" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorDetail", propOrder = {
    "category",
    "completeException",
    "errorMessageSummary",
    "errorNumber",
    "errorSourceComponent",
    "nameValuePair",
    "sequenceID",
    "xPath"
})
public class ErrorDetail {

    @XmlElement(name = "Category", required = true)
    protected String category;
    @XmlElement(name = "CompleteException")
    protected String completeException;
    @XmlElement(name = "ErrorMessageSummary", required = true)
    protected String errorMessageSummary;
    @XmlElement(name = "ErrorNumber")
    protected String errorNumber;
    @XmlElement(name = "ErrorSourceComponent")
    protected String errorSourceComponent;
    @XmlElement(name = "NameValuePair")
    protected List<String> nameValuePair;
    @XmlElement(name = "SequenceID")
    protected int sequenceID;
    protected String xPath;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the completeException property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompleteException() {
        return completeException;
    }

    /**
     * Sets the value of the completeException property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompleteException(String value) {
        this.completeException = value;
    }

    /**
     * Gets the value of the errorMessageSummary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessageSummary() {
        return errorMessageSummary;
    }

    /**
     * Sets the value of the errorMessageSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessageSummary(String value) {
        this.errorMessageSummary = value;
    }

    /**
     * Gets the value of the errorNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorNumber() {
        return errorNumber;
    }

    /**
     * Sets the value of the errorNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorNumber(String value) {
        this.errorNumber = value;
    }

    /**
     * Gets the value of the errorSourceComponent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorSourceComponent() {
        return errorSourceComponent;
    }

    /**
     * Sets the value of the errorSourceComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorSourceComponent(String value) {
        this.errorSourceComponent = value;
    }

    /**
     * Gets the value of the nameValuePair property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameValuePair property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameValuePair().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNameValuePair() {
        if (nameValuePair == null) {
            nameValuePair = new ArrayList<String>();
        }
        return this.nameValuePair;
    }

    /**
     * Gets the value of the sequenceID property.
     * 
     */
    public int getSequenceID() {
        return sequenceID;
    }

    /**
     * Sets the value of the sequenceID property.
     * 
     */
    public void setSequenceID(int value) {
        this.sequenceID = value;
    }

    /**
     * Gets the value of the xPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPath() {
        return xPath;
    }

    /**
     * Sets the value of the xPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPath(String value) {
        this.xPath = value;
    }

}
