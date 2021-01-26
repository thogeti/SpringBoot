
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for medCheckOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medCheckOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}HeaderParam" minOccurs="0"/>
 *         &lt;element name="outputParams" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}InputParams" minOccurs="0"/>
 *         &lt;element name="prescription" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}Prescription" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medCheckOut", propOrder = {
    "header",
    "outputParams",
    "prescription"
})
public class MedCheckOut {

    @XmlElement(name = "Header")
    protected HeaderParam header;
    protected InputParams outputParams;
    @XmlElement(nillable = true)
    protected List<Prescription> prescription;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderParam }
     *     
     */
    public HeaderParam getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderParam }
     *     
     */
    public void setHeader(HeaderParam value) {
        this.header = value;
    }

    /**
     * Gets the value of the outputParams property.
     * 
     * @return
     *     possible object is
     *     {@link InputParams }
     *     
     */
    public InputParams getOutputParams() {
        return outputParams;
    }

    /**
     * Sets the value of the outputParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputParams }
     *     
     */
    public void setOutputParams(InputParams value) {
        this.outputParams = value;
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

}