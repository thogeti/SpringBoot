
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CalendarTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CalendarTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PatientCalendar"/&gt;
 *     &lt;enumeration value="PrescriptionCalendar"/&gt;
 *     &lt;enumeration value="DispenseCalendar"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CalendarTypeEnum")
@XmlEnum
public enum CalendarTypeEnum {

    @XmlEnumValue("PatientCalendar")
    PATIENT_CALENDAR("PatientCalendar"),
    @XmlEnumValue("PrescriptionCalendar")
    PRESCRIPTION_CALENDAR("PrescriptionCalendar"),
    @XmlEnumValue("DispenseCalendar")
    DISPENSE_CALENDAR("DispenseCalendar");
    private final String value;

    CalendarTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CalendarTypeEnum fromValue(String v) {
        for (CalendarTypeEnum c: CalendarTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
