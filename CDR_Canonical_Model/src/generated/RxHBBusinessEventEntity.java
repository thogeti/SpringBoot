
package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RxHBBusinessEventEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RxHBBusinessEventEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entityAction" type="{}RxHBBusinessEventActionEnum"/&gt;
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="entityType" type="{}RxHBBusinessEventEntityTypeEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RxHBBusinessEventEntity", propOrder = {
    "entityAction",
    "entityId",
    "entityType"
})
public class RxHBBusinessEventEntity {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RxHBBusinessEventActionEnum entityAction;
    @XmlElement(required = true)
    protected String entityId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RxHBBusinessEventEntityTypeEnum entityType;

    /**
     * Gets the value of the entityAction property.
     * 
     * @return
     *     possible object is
     *     {@link RxHBBusinessEventActionEnum }
     *     
     */
    public RxHBBusinessEventActionEnum getEntityAction() {
        return entityAction;
    }

    /**
     * Sets the value of the entityAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxHBBusinessEventActionEnum }
     *     
     */
    public void setEntityAction(RxHBBusinessEventActionEnum value) {
        this.entityAction = value;
    }

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityId(String value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link RxHBBusinessEventEntityTypeEnum }
     *     
     */
    public RxHBBusinessEventEntityTypeEnum getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RxHBBusinessEventEntityTypeEnum }
     *     
     */
    public void setEntityType(RxHBBusinessEventEntityTypeEnum value) {
        this.entityType = value;
    }

}
