
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LicenseIssuingBodyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LicenseIssuingBodyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Physicians And Surgeons"/&gt;
 *     &lt;enumeration value="Nurse Practitioners"/&gt;
 *     &lt;enumeration value="Pharmacists"/&gt;
 *     &lt;enumeration value="Optometrists"/&gt;
 *     &lt;enumeration value="Dentists"/&gt;
 *     &lt;enumeration value="Midwifery Regulatory Council Of Nova Scotia"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
