
package ca.shoppersdrugmart.cdr.notificationService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.SourceSystem;

/**
 * <p>
 * Java class for PurgingProcessResponseDetail complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="NotificationResponseDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StoreNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrescriptionNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DispenseNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Subscription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificationResponseDetail", propOrder = { "storeNumber", "prescriptionNumber", "dispenseNumber",
		"subscription" })
public class NotificationResponseDetail {

	@XmlElement(name = "StoreNumber", required = true)
	protected String storeNumber;
	@XmlElement(name = "PrescriptionNumber" , required = true)
	protected Integer prescriptionNumber;
	@XmlElement(name = "DispenseNumber" , required = true)
	protected Integer dispenseNumber;
	@XmlElement(name = "Subscription", required = true)
	//protected List<String> subscription;
	protected List<SourceSystem> subscription;
/*	@XmlElement(name = "RowCount", required = true)
	protected Integer rowCount;
	public void setSubscription(List<String> subscription) {
		this.subscription = subscription;
	}

	*//**
	 * @return the rowCount
	 *//*
	public Integer getRowCount() {
		return rowCount;
	}

	*//**
	 * @param rowCount the rowCount to set
	 *//*
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}*/

	/**
	 * Gets the value of the storeNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStoreNumber() {
		return storeNumber;
	}

	/**
	 * Sets the value of the storeNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStoreNumber(String value) {
		this.storeNumber = value;
	}

	/**
	 * Gets the value of the prescriptionNumber property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPrescriptionNumber() {
		return prescriptionNumber;
	}

	/**
	 * Sets the value of the prescriptionNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPrescriptionNumber(Integer value) {
		this.prescriptionNumber = value;
	}

	/**
	 * Gets the value of the dispenseNumber property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getDispenseNumber() {
		return dispenseNumber;
	}

	/**
	 * Sets the value of the dispenseNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDispenseNumber(Integer value) {
		this.dispenseNumber = value;
	}

	public List<SourceSystem> getSubscription() {
		return subscription;
	}

	public void setSubscription(List<SourceSystem> subscription) {
		this.subscription = subscription;
	}

	
	
	/**
	 * Gets the value of the subscription property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the subscription property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSubscription().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	/*public List<String> getSubscription() {
		if (subscription == null) {
			subscription = new ArrayList<String>();
		}
		return this.subscription;
	}*/

}
