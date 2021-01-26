
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpecialDrugProgram.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SpecialDrugProgram">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="arthritis"/>
 *     &lt;enumeration value="depression"/>
 *     &lt;enumeration value="asthma"/>
 *     &lt;enumeration value="hypertension"/>
 *     &lt;enumeration value="cholesterol"/>
 *     &lt;enumeration value="diabetes"/>
 *     &lt;enumeration value="osteoporosis&amp;Menopause"/>
 *     &lt;enumeration value="healthInform"/>
 *     &lt;enumeration value="medrvDesc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SpecialDrugProgram")
@XmlEnum
public enum SpecialDrugProgram {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("arthritis")
    ARTHRITIS("arthritis"),
    @XmlEnumValue("depression")
    DEPRESSION("depression"),
    @XmlEnumValue("asthma")
    ASTHMA("asthma"),
    @XmlEnumValue("hypertension")
    HYPERTENSION("hypertension"),
    @XmlEnumValue("cholesterol")
    CHOLESTEROL("cholesterol"),
    @XmlEnumValue("diabetes")
    DIABETES("diabetes"),
    @XmlEnumValue("osteoporosis&Menopause")
    OSTEOPOROSIS_MENOPAUSE("osteoporosis&Menopause"),
    @XmlEnumValue("healthInform")
    HEALTH_INFORM("healthInform"),
    @XmlEnumValue("medrvDesc")
    MEDRV_DESC("medrvDesc");
    private final String value;

    SpecialDrugProgram(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpecialDrugProgram fromValue(String v) {
        for (SpecialDrugProgram c: SpecialDrugProgram.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
