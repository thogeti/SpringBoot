
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * The licensed  professional legally authorized to prescribe drugs.
 * 
 * <p>Java class for Prescriber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prescriber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isMailAddressFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isPrimaryAddressFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReauthEmailFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReauthFaxFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReauthPhoneFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReauthVisitFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prescriber", propOrder = {
    "isMailAddressFlag",
    "isReauthEmailFlag",
    "isReauthFaxFlag",
    "isReauthPhoneFlag",
    "isReauthVisitFlag"
})
public class Prescriber {

	  protected boolean isMailAddressFlag;
	 // protected boolean isPrimaryAddressFlag;
	  protected boolean isReauthEmailFlag;
	  protected String isReauthFaxFlag;
	  protected boolean isReauthPhoneFlag;
	  protected boolean isReauthVisitFlag;
	  
	public boolean isMailAddressFlag() {
		return isMailAddressFlag;
	}
	public void setMailAddressFlag(boolean isMailAddressFlag) {
		this.isMailAddressFlag = isMailAddressFlag;
	}
	/*public boolean isPrimaryAddressFlag() {
		return isPrimaryAddressFlag;
	}
	public void setPrimaryAddressFlag(boolean isPrimaryAddressFlag) {
		this.isPrimaryAddressFlag = isPrimaryAddressFlag;
	}*/
	public boolean isReauthEmailFlag() {
		return isReauthEmailFlag;
	}
	public void setReauthEmailFlag(boolean isReauthEmailFlag) {
		this.isReauthEmailFlag = isReauthEmailFlag;
	}
	public String getIsReauthFaxFlag() {
		return isReauthFaxFlag;
	}
	public void setIsReauthFaxFlag(String isReauthFaxFlag) {
		this.isReauthFaxFlag = isReauthFaxFlag;
	}
	public boolean isReauthPhoneFlag() {
		return isReauthPhoneFlag;
	}
	public void setReauthPhoneFlag(boolean isReauthPhoneFlag) {
		this.isReauthPhoneFlag = isReauthPhoneFlag;
	}
	public boolean isReauthVisitFlag() {
		return isReauthVisitFlag;
	}
	public void setReauthVisitFlag(boolean isReauthVisitFlag) {
		this.isReauthVisitFlag = isReauthVisitFlag;
	}

   
}
