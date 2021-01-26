
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CalendarReminderEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CalendarReminderEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AutoRefill"/&gt;
 *     &lt;enumeration value="RefillReminder"/&gt;
 *     &lt;enumeration value="RefillWindowStart"/&gt;
 *     &lt;enumeration value="PickupReminder"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CalendarReminderEnum")
@XmlEnum
public enum CalendarReminderEnum {

    @XmlEnumValue("AutoRefill")
    AUTO_REFILL("AutoRefill"),
    @XmlEnumValue("RefillReminder")
    REFILL_REMINDER("RefillReminder"),
    @XmlEnumValue("RefillWindowStart")
    REFILL_WINDOW_START("RefillWindowStart"),
    @XmlEnumValue("PickupReminder")
    PICKUP_REMINDER("PickupReminder");
    private final String value;

    CalendarReminderEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CalendarReminderEnum fromValue(String v) {
        for (CalendarReminderEnum c: CalendarReminderEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
