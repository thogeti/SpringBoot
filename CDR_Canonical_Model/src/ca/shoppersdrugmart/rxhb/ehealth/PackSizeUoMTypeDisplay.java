
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackSizeUoMTypeDisplay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackSizeUoMTypeDisplay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packSizeUoMTypeCode" type="{http://shoppersdrugmart.ca/RxHB/eHealth}PackSizeUoM" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackSizeUoMTypeDisplay", propOrder = {
    "packSizeUoMTypeCode"
})
public class PackSizeUoMTypeDisplay {

    protected String packSizeUoMTypeCode;

    /**
     * Gets the value of the packSizeUoMTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackSizeUoMTypeCode() {
        return packSizeUoMTypeCode;
    }

    /**
     * Sets the value of the packSizeUoMTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackSizeUoMTypeCode(String value) {
        this.packSizeUoMTypeCode = value;
    }

}
