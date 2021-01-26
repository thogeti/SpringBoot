
package ca.shoppersdrugmart.message.errorxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This is the root node of the Error Message Schema
 * 
 * <p>Java class for ErrorMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorHeader" type="{http://www.shoppersdrugmart.ca/message/ErrorXML}ErrorHeader"/>
 *         &lt;element name="ErrorDetail" type="{http://www.shoppersdrugmart.ca/message/ErrorXML}ErrorDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorMessage", propOrder = {
    "errorHeader",
    "errorDetail"
})
public class ErrorMessage {

    @XmlElement(name = "ErrorHeader", required = true)
    protected ErrorHeader errorHeader;
    @XmlElement(name = "ErrorDetail", required = true)
    protected List<ErrorDetail> errorDetail;

    /**
     * Gets the value of the errorHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorHeader }
     *     
     */
    public ErrorHeader getErrorHeader() {
        return errorHeader;
    }

    /**
     * Sets the value of the errorHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorHeader }
     *     
     */
    public void setErrorHeader(ErrorHeader value) {
        this.errorHeader = value;
    }

    /**
     * Gets the value of the errorDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorDetail }
     * 
     * 
     */
    public List<ErrorDetail> getErrorDetail() {
        if (errorDetail == null) {
            errorDetail = new ArrayList<ErrorDetail>();
        }
        return this.errorDetail;
    }

}
