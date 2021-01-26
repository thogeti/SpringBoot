
package ca.shoppersdrugmart.cdr.notificationService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.EventName;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyBusinessEventProcessEnum;

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
 *         &lt;element name="BusinessProcess" type="{http://www.w3.org/2001/XMLSchema}PharmacyBusinessEventProcessEnum"/>
 *         &lt;element name="RequestTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *          &lt;element name="EventName" type="{http://www.w3.org/2001/XMLSchema}EventName"/>
 *           &lt;element name="MaxEntityCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "businessProcess","requestTimestamp","eventName", "maxEntityCount"})
@XmlRootElement(name = "NotificationRequest")
public class NotificationRequest {
	@XmlElement(name = "BusinessProcess", required = true)
	protected PharmacyBusinessEventProcessEnum businessProcess;
	@XmlElement(name = "RequestTimestamp", required = true)
	@XmlSchemaType(name = "dateTime")
	protected String requestTimestamp;
	@XmlElement(name = "ObjectType", required = true)
	protected EventName eventName;
	
	@XmlElement(name = "MaxEntityCount", required = true)
	protected int maxEntityCount;
	
	
	
	
	
	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	/**
	 * Sets the value of the requestTimestamp property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRequestTimestamp(String value) {
		this.requestTimestamp = value;
	}

	public EventName getEventName() {
		return eventName;
	}

	public void setEventName(EventName eventName) {
		this.eventName = eventName;
	}

	public PharmacyBusinessEventProcessEnum getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(PharmacyBusinessEventProcessEnum businessProcess) {
		this.businessProcess = businessProcess;
	}

	public int getMaxEntityCount() {
		return maxEntityCount;
	}

	public void setMaxEntityCount(int maxEntityCount) {
		this.maxEntityCount = maxEntityCount;
	}

}
