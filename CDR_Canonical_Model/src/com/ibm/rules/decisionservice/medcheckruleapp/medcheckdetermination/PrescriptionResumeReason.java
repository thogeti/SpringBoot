
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriptionResumeReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrescriptionResumeReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Suspend Reason No Longer Applies"/>
 *     &lt;enumeration value="Suspend Reason Inappropriate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrescriptionResumeReason")
@XmlEnum
public enum PrescriptionResumeReason {

    @XmlEnumValue("Suspend Reason No Longer Applies")
    SUSPEND_REASON_NO_LONGER_APPLIES("Suspend Reason No Longer Applies"),
    @XmlEnumValue("Suspend Reason Inappropriate")
    SUSPEND_REASON_INAPPROPRIATE("Suspend Reason Inappropriate");
    private final String value;

    PrescriptionResumeReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrescriptionResumeReason fromValue(String v) {
        for (PrescriptionResumeReason c: PrescriptionResumeReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
