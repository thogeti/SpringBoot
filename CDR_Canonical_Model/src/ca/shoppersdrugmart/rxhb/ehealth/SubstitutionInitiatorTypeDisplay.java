
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubstitutionInitiatorTypeDisplay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubstitutionInitiatorTypeDisplay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="substitutionInitiator" type="{http://shoppersdrugmart.ca/RxHB/eHealth}SubstitutionInitiator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubstitutionInitiatorTypeDisplay", propOrder = {
    "substitutionInitiator"
})
public class SubstitutionInitiatorTypeDisplay {

    @XmlSchemaType(name = "string")
    protected SubstitutionInitiator substitutionInitiator;

    /**
     * Gets the value of the substitutionInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link SubstitutionInitiator }
     *     
     */
    public SubstitutionInitiator getSubstitutionInitiator() {
        return substitutionInitiator;
    }

    /**
     * Sets the value of the substitutionInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubstitutionInitiator }
     *     
     */
    public void setSubstitutionInitiator(SubstitutionInitiator value) {
        this.substitutionInitiator = value;
    }

}
