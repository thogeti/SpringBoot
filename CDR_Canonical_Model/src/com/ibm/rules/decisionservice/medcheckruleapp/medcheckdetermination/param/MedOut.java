
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.param;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.MedCheckOut;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MedOut" type="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination}medCheckOut"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "medOut"
})
@XmlRootElement(name = "MedOut")
public class MedOut {

    @XmlElement(name = "MedOut", required = true)
    protected MedCheckOut medOut;

    /**
     * Gets the value of the medOut property.
     * 
     * @return
     *     possible object is
     *     {@link MedCheckOut }
     *     
     */
    public MedCheckOut getMedOut() {
        return medOut;
    }

    /**
     * Sets the value of the medOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedCheckOut }
     *     
     */
    public void setMedOut(MedCheckOut value) {
        this.medOut = value;
    }

}
