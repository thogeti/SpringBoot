
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RxFillStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RxFillStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Cancelled"/&gt;
 *     &lt;enumeration value="Log Pending"/&gt;
 *     &lt;enumeration value="Discontinued"/&gt;
 *     &lt;enumeration value="Expired"/&gt;
 *     &lt;enumeration value="Held"/&gt;
 *     &lt;enumeration value="No Refills Remain"/&gt;
 *     &lt;enumeration value="Not Dispensed"/&gt;
 *     &lt;enumeration value="Other Medication"/&gt;
 *     &lt;enumeration value="Refills Remain"/&gt;
 *     &lt;enumeration value="Revoked"/&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="Transfer"/&gt;
 *     &lt;enumeration value="Transferred"/&gt;
 *     &lt;enumeration value="Waiting for data entry"/&gt;
 *     &lt;enumeration value="Refused to Fill"/&gt;
 *     &lt;enumeration value="Adapt"/&gt;
 *     &lt;enumeration value="ePrescription"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RxFillStatus")
@XmlEnum
public enum RxFillStatus {

    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled"),
    @XmlEnumValue("Log Pending")
    LOG_PENDING("Log Pending"),
    @XmlEnumValue("Discontinued")
    DISCONTINUED("Discontinued"),
    @XmlEnumValue("Expired")
    EXPIRED("Expired"),
    @XmlEnumValue("Held")
    HELD("Held"),
    @XmlEnumValue("No Refills Remain")
    NO_REFILLS_REMAIN("No Refills Remain"),
    @XmlEnumValue("Not Dispensed")
    NOT_DISPENSED("Not Dispensed"),
    @XmlEnumValue("Other Medication")
    OTHER_MEDICATION("Other Medication"),
    @XmlEnumValue("Refills Remain")
    REFILLS_REMAIN("Refills Remain"),
    @XmlEnumValue("Revoked")
    REVOKED("Revoked"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Transfer")
    TRANSFER("Transfer"),
    @XmlEnumValue("Transferred")
    TRANSFERRED("Transferred"),
    @XmlEnumValue("Waiting for data entry")
    WAITING_FOR_DATA_ENTRY("Waiting for data entry"),
    @XmlEnumValue("Refused to Fill")
    REFUSED_TO_FILL("Refused to Fill"),
    @XmlEnumValue("Adapt")
    ADAPT("Adapt"),
    @XmlEnumValue("ePrescription")
    E_PRESCRIPTION("ePrescription");
    private final String value;

    RxFillStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RxFillStatus fromValue(String v) {
        for (RxFillStatus c: RxFillStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
