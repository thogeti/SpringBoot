
package ca.shoppersdrugmart.cdr.notificationService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NotificationProcessResponseDetail" type="{http://shoppersdrugmart.ca/cdr/NotificationService}NotificationProcessResponseDetail" maxOccurs="unbounded"/>
 *         &lt;element name="TotalPrescription" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="1" maxOccurs="1"/>
 *         &lt;element name="TotalDispense" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" maxOccurs="1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "notificationResponseDetail", "rowCount", "totalPrescription", "totalDispense" })
@XmlRootElement(name = "NotificationResponse")
public class NotificationResponse {

	@XmlElement(name = "NotificationResponseDetail", required = true)
	protected List<NotificationResponseDetail> notificationResponseDetail;
	@XmlElement(name = "RowCount", required = true)
	protected int rowCount;
	@XmlElement(name = "TotalPrescriptionCount", required = true)
	protected int totalPrescription;
	@XmlElement(name = "TotalDispenseCount", required = true)
	protected int totalDispense;

	/**
	 * Gets the value of the purgingProcessResponseDetail property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the purgingProcessResponseDetail property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPurgingProcessResponseDetail().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link NotificationProcessResponseDetail }
	 * 
	 * 
	 */
	

	/**
	 * Gets the value of the totalDeletedPrescription property.
	 * 
	 */
	public int getTotalPrescription() {
		return totalPrescription;
	}

	/**
	 * Sets the value of the totalDeletedPrescription property.
	 * 
	 */
	public void setTotalPrescription(int value) {
		this.totalPrescription = value;
	}

	/**
	 * Gets the value of the totalDeletedDispense property.
	 * 
	 */
	public int getTotalDispense() {
		return totalDispense;
	}

	/**
	 * Sets the value of the totalDeletedDispense property.
	 * 
	 */
	public void setTotalDispense(int value) {
		this.totalDispense = value;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<NotificationResponseDetail> getNotificationResponseDetail() {
		if (notificationResponseDetail == null) {
			notificationResponseDetail = new ArrayList<NotificationResponseDetail>();
		}
		return notificationResponseDetail;
	}

	public void setNotificationResponseDetail(List<NotificationResponseDetail> notificationResponseDetail) {
		this.notificationResponseDetail = notificationResponseDetail;
	}

}
