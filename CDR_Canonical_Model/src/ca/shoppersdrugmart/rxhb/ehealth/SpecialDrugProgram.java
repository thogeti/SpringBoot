
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpecialDrugProgram.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SpecialDrugProgram"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *     &lt;enumeration value="arthritis"/&gt;
 *     &lt;enumeration value="depression"/&gt;
 *     &lt;enumeration value="asthma"/&gt;
 *     &lt;enumeration value="hypertension"/&gt;
 *     &lt;enumeration value="cholesterol"/&gt;
 *     &lt;enumeration value="diabetes"/&gt;
 *     &lt;enumeration value="osteoporosis&amp;Menopause"/&gt;
 *     &lt;enumeration value="healthInform"/&gt;
 *     &lt;enumeration value="medrvDesc"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
