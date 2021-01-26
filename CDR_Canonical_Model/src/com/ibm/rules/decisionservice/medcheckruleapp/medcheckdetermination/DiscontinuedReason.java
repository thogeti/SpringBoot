
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiscontinuedReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DiscontinuedReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Allergy Intolerance"/>
 *     &lt;enumeration value="Revoke-Intolerance"/>
 *     &lt;enumeration value="Revoke-New Strength SIG"/>
 *     &lt;enumeration value="Revoke-New Therapy"/>
 *     &lt;enumeration value="Revoke-No Longer Required"/>
 *     &lt;enumeration value="Transferred"/>
 *     &lt;enumeration value="Clinical Issue"/>
 *     &lt;enumeration value="Discontinued/Recalled"/>
 *     &lt;enumeration value="Duplicate Therapy"/>
 *     &lt;enumeration value="Ineffective"/>
 *     &lt;enumeration value="New SIG"/>
 *     &lt;enumeration value="No Insurance"/>
 *     &lt;enumeration value="Not Able to Use"/>
 *     &lt;enumeration value="Not Appropriate"/>
 *     &lt;enumeration value="Not Needed"/>
 *     &lt;enumeration value="Patient Refused"/>
 *     &lt;enumeration value="Product Recall"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DiscontinuedReason")
@XmlEnum
public enum DiscontinuedReason {

    @XmlEnumValue("Allergy Intolerance")
    ALLERGY_INTOLERANCE("Allergy Intolerance"),
    @XmlEnumValue("Revoke-Intolerance")
    REVOKE_INTOLERANCE("Revoke-Intolerance"),
    @XmlEnumValue("Revoke-New Strength SIG")
    REVOKE_NEW_STRENGTH_SIG("Revoke-New Strength SIG"),
    @XmlEnumValue("Revoke-New Therapy")
    REVOKE_NEW_THERAPY("Revoke-New Therapy"),
    @XmlEnumValue("Revoke-No Longer Required")
    REVOKE_NO_LONGER_REQUIRED("Revoke-No Longer Required"),
    @XmlEnumValue("Transferred")
    TRANSFERRED("Transferred"),
    @XmlEnumValue("Clinical Issue")
    CLINICAL_ISSUE("Clinical Issue"),
    @XmlEnumValue("Discontinued/Recalled")
    DISCONTINUED_RECALLED("Discontinued/Recalled"),
    @XmlEnumValue("Duplicate Therapy")
    DUPLICATE_THERAPY("Duplicate Therapy"),
    @XmlEnumValue("Ineffective")
    INEFFECTIVE("Ineffective"),
    @XmlEnumValue("New SIG")
    NEW_SIG("New SIG"),
    @XmlEnumValue("No Insurance")
    NO_INSURANCE("No Insurance"),
    @XmlEnumValue("Not Able to Use")
    NOT_ABLE_TO_USE("Not Able to Use"),
    @XmlEnumValue("Not Appropriate")
    NOT_APPROPRIATE("Not Appropriate"),
    @XmlEnumValue("Not Needed")
    NOT_NEEDED("Not Needed"),
    @XmlEnumValue("Patient Refused")
    PATIENT_REFUSED("Patient Refused"),
    @XmlEnumValue("Product Recall")
    PRODUCT_RECALL("Product Recall");
    private final String value;

    DiscontinuedReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DiscontinuedReason fromValue(String v) {
        for (DiscontinuedReason c: DiscontinuedReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
