
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TxFillStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TxFillStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Cancelled"/&gt;
 *     &lt;enumeration value="Complete"/&gt;
 *     &lt;enumeration value="Data entry comple and blocked"/&gt;
 *     &lt;enumeration value="Deferred"/&gt;
 *     &lt;enumeration value="Waiting for clinical verification"/&gt;
 *     &lt;enumeration value="Waiting for data entry"/&gt;
 *     &lt;enumeration value="Waiting for data verification"/&gt;
 *     &lt;enumeration value="Waiting for pickup"/&gt;
 *     &lt;enumeration value="Waiting for product check"/&gt;
 *     &lt;enumeration value="Cancel Pending"/&gt;
 *     &lt;enumeration value="Waiting to be filled"/&gt;
 *     &lt;enumeration value="Adapt"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TxFillStatus")
@XmlEnum
public enum TxFillStatus {

    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("Data entry comple and blocked")
    DATA_ENTRY_COMPLE_AND_BLOCKED("Data entry comple and blocked"),
    @XmlEnumValue("Deferred")
    DEFERRED("Deferred"),
    @XmlEnumValue("Waiting for clinical verification")
    WAITING_FOR_CLINICAL_VERIFICATION("Waiting for clinical verification"),
    @XmlEnumValue("Waiting for data entry")
    WAITING_FOR_DATA_ENTRY("Waiting for data entry"),
    @XmlEnumValue("Waiting for data verification")
    WAITING_FOR_DATA_VERIFICATION("Waiting for data verification"),
    @XmlEnumValue("Waiting for pickup")
    WAITING_FOR_PICKUP("Waiting for pickup"),
    @XmlEnumValue("Waiting for product check")
    WAITING_FOR_PRODUCT_CHECK("Waiting for product check"),
    @XmlEnumValue("Cancel Pending")
    CANCEL_PENDING("Cancel Pending"),
    @XmlEnumValue("Waiting to be filled")
    WAITING_TO_BE_FILLED("Waiting to be filled"),
    @XmlEnumValue("Adapt")
    ADAPT("Adapt");
    private final String value;

    TxFillStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TxFillStatus fromValue(String v) {
        for (TxFillStatus c: TxFillStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
