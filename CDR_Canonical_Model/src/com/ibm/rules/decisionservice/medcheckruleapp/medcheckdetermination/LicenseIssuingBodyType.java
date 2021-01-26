
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LicenseIssuingBodyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LicenseIssuingBodyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Physicians And Surgeons"/>
 *     &lt;enumeration value="Nurse Practitioners"/>
 *     &lt;enumeration value="Pharmacists"/>
 *     &lt;enumeration value="Optometrists"/>
 *     &lt;enumeration value="Dentists"/>
 *     &lt;enumeration value="Midwifery Regulatory Council Of Nova Scotia"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LicenseIssuingBodyType")
@XmlEnum
public enum LicenseIssuingBodyType {

    @XmlEnumValue("Physicians And Surgeons")
    PHYSICIANS_AND_SURGEONS("Physicians And Surgeons"),
    @XmlEnumValue("Nurse Practitioners")
    NURSE_PRACTITIONERS("Nurse Practitioners"),
    @XmlEnumValue("Pharmacists")
    PHARMACISTS("Pharmacists"),
    @XmlEnumValue("Optometrists")
    OPTOMETRISTS("Optometrists"),
    @XmlEnumValue("Dentists")
    DENTISTS("Dentists"),
    @XmlEnumValue("Midwifery Regulatory Council Of Nova Scotia")
    MIDWIFERY_REGULATORY_COUNCIL_OF_NOVA_SCOTIA("Midwifery Regulatory Council Of Nova Scotia");
    private final String value;

    LicenseIssuingBodyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LicenseIssuingBodyType fromValue(String v) {
        for (LicenseIssuingBodyType c: LicenseIssuingBodyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
