
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Schedule.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Schedule"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Narcotics"/&gt;
 *     &lt;enumeration value="Targeted Substance"/&gt;
 *     &lt;enumeration value="Schedule 1"/&gt;
 *     &lt;enumeration value="Controlled 1"/&gt;
 *     &lt;enumeration value="Controlled 2"/&gt;
 *     &lt;enumeration value="Controlled 3"/&gt;
 *     &lt;enumeration value="Rx Not Required"/&gt;
 *     &lt;enumeration value="Schedule 2"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Schedule")
@XmlEnum
public enum Schedule {

    @XmlEnumValue("Narcotics")
    NARCOTICS("Narcotics"),
    @XmlEnumValue("Targeted Substance")
    TARGETED_SUBSTANCE("Targeted Substance"),
    @XmlEnumValue("Schedule 1")
    SCHEDULE_1("Schedule 1"),
    @XmlEnumValue("Controlled 1")
    CONTROLLED_1("Controlled 1"),
    @XmlEnumValue("Controlled 2")
    CONTROLLED_2("Controlled 2"),
    @XmlEnumValue("Controlled 3")
    CONTROLLED_3("Controlled 3"),
    @XmlEnumValue("Rx Not Required")
    RX_NOT_REQUIRED("Rx Not Required"),
    @XmlEnumValue("Schedule 2")
    SCHEDULE_2("Schedule 2");
    private final String value;

    Schedule(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Schedule fromValue(String v) {
        for (Schedule c: Schedule.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
