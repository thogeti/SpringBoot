
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for param complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="param">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namekey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namevalue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param", propOrder = {
    "namekey",
    "namevalue"
})
public class Param {

    protected String namekey;
    protected String namevalue;

    /**
     * Gets the value of the namekey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamekey() {
        return namekey;
    }

    /**
     * Sets the value of the namekey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamekey(String value) {
        this.namekey = value;
    }

    /**
     * Gets the value of the namevalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamevalue() {
        return namevalue;
    }

    /**
     * Sets the value of the namevalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamevalue(String value) {
        this.namevalue = value;
    }

}
