
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriberUpsertResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrescriberUpsertResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="response" type="{http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist}CDRPersistResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrescriberUpsertResponse", propOrder = {
    "response"
})
public class PrescriberUpsertResponse {

    @XmlElement(required = true)
    protected CDRPersistResponse response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link CDRPersistResponse }
     *     
     */
    public CDRPersistResponse getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDRPersistResponse }
     *     
     */
    public void setResponse(CDRPersistResponse value) {
        this.response = value;
    }

}
