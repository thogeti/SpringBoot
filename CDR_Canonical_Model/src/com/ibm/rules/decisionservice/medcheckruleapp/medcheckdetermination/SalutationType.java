
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for salutationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="salutationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Doctor"/>
 *     &lt;enumeration value="Fr"/>
 *     &lt;enumeration value="Miss"/>
 *     &lt;enumeration value="Mister"/>
 *     &lt;enumeration value="Mrs."/>
 *     &lt;enumeration value="MW."/>
 *     &lt;enumeration value="NP"/>
 *     &lt;enumeration value="Registered Pharmacist"/>
 *     &lt;enumeration value="Reverant"/>
 *     &lt;enumeration value="Senior"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "salutationType")
@XmlEnum
public enum SalutationType {

    @XmlEnumValue("Doctor")
    DOCTOR("Doctor"),
    @XmlEnumValue("Fr")
    FR("Fr"),
    @XmlEnumValue("Miss")
    MISS("Miss"),
    @XmlEnumValue("Mister")
    MISTER("Mister"),
    @XmlEnumValue("Mrs.")
    MRS("Mrs."),
    @XmlEnumValue("MW.")
    MW("MW."),
    NP("NP"),
    @XmlEnumValue("Registered Pharmacist")
    REGISTERED_PHARMACIST("Registered Pharmacist"),
    @XmlEnumValue("Reverant")
    REVERANT("Reverant"),
    @XmlEnumValue("Senior")
    SENIOR("Senior");
    private final String value;

    SalutationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SalutationType fromValue(String v) {
        for (SalutationType c: SalutationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
