
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.param.MedOut;


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
 *         &lt;element name="DecisionID" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination/param}MedOut"/>
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
    "decisionID",
    "medOut"
})
@XmlRootElement(name = "MedcheckDeterminationResponse")
public class MedcheckDeterminationResponse {

    @XmlElement(name = "DecisionID", namespace = "http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination", required = true)
    protected String decisionID;
    @XmlElement(name = "MedOut", namespace = "http://www.ibm.com/rules/decisionservice/MedcheckRuleApp/MedcheckDetermination/param", required = true)
    protected MedOut medOut;

    /**
     * Gets the value of the decisionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionID() {
        return decisionID;
    }

    /**
     * Sets the value of the decisionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionID(String value) {
        this.decisionID = value;
    }

    /**
     * Gets the value of the medOut property.
     * 
     * @return
     *     possible object is
     *     {@link MedOut }
     *     
     */
    public MedOut getMedOut() {
        return medOut;
    }

    /**
     * Sets the value of the medOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedOut }
     *     
     */
    public void setMedOut(MedOut value) {
        this.medOut = value;
    }

}
