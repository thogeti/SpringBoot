
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommunicationModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommunicationModeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SMS"/&gt;
 *     &lt;enumeration value="Email"/&gt;
 *     &lt;enumeration value="Telephone"/&gt;
 *     &lt;enumeration value="WalkIn"/&gt;
 *     &lt;enumeration value="Web"/&gt;
 *     &lt;enumeration value="Mobile"/&gt;
 *     &lt;enumeration value="System"/&gt;
 *     &lt;enumeration value="Fax"/&gt;
 *     &lt;enumeration value="Batch"/&gt;
 *     &lt;enumeration value="Conversion"/&gt;
 *     &lt;enumeration value="DriveThru"/&gt;
 *     &lt;enumeration value="Accuro"/&gt;
 *     &lt;enumeration value="HealthWatch"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CommunicationModeType")
@XmlEnum
public enum CommunicationModeType {

    SMS("SMS"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("Telephone")
    TELEPHONE("Telephone"),
    @XmlEnumValue("WalkIn")
    WALK_IN("WalkIn"),
    @XmlEnumValue("Web")
    WEB("Web"),
    @XmlEnumValue("Mobile")
    MOBILE("Mobile"),

    /**
     * The system associated to the channel has initiated the communication.
     * 
     */
    @XmlEnumValue("System")
    SYSTEM("System"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Batch")
    BATCH("Batch"),
    @XmlEnumValue("Conversion")
    CONVERSION("Conversion"),
    @XmlEnumValue("DriveThru")
    DRIVE_THRU("DriveThru"),
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),
    @XmlEnumValue("HealthWatch")
    HEALTH_WATCH("HealthWatch");
    private final String value;

    CommunicationModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommunicationModeType fromValue(String v) {
        for (CommunicationModeType c: CommunicationModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
