
package ca.shoppersdrugmart.cdr.prescriberupsertservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrescriberUpsertFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prescriberUpsertFault"
})
@XmlRootElement(name = "PrescriberUpsertFault")
public class PrescriberUpsertFault {

    @XmlElement(name = "PrescriberUpsertFault", required = true)
    protected String prescriberUpsertFault;

    /**
     * Gets the value of the prescriberUpsertFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberUpsertFault() {
        return prescriberUpsertFault;
    }

    /**
     * Sets the value of the prescriberUpsertFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberUpsertFault(String value) {
        this.prescriberUpsertFault = value;
    }

}
