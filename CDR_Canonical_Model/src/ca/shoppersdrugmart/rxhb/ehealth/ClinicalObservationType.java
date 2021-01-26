
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClinicalObservationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClinicalObservationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="height"/&gt;
 *     &lt;enumeration value="weight"/&gt;
 *     &lt;enumeration value="respiratoryRate"/&gt;
 *     &lt;enumeration value="heartRate"/&gt;
 *     &lt;enumeration value="bloodGlucose"/&gt;
 *     &lt;enumeration value="bloodPressure"/&gt;
 *     &lt;enumeration value="bodyTemperature"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ClinicalObservationType")
@XmlEnum
public enum ClinicalObservationType {

    @XmlEnumValue("height")
    HEIGHT("height"),
    @XmlEnumValue("weight")
    WEIGHT("weight"),
    @XmlEnumValue("respiratoryRate")
    RESPIRATORY_RATE("respiratoryRate"),
    @XmlEnumValue("heartRate")
    HEART_RATE("heartRate"),
    @XmlEnumValue("bloodGlucose")
    BLOOD_GLUCOSE("bloodGlucose"),
    @XmlEnumValue("bloodPressure")
    BLOOD_PRESSURE("bloodPressure"),
    @XmlEnumValue("bodyTemperature")
    BODY_TEMPERATURE("bodyTemperature");
    private final String value;

    ClinicalObservationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClinicalObservationType fromValue(String v) {
        for (ClinicalObservationType c: ClinicalObservationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
