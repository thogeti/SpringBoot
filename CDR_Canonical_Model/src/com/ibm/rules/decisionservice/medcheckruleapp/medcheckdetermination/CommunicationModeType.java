
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommunicationModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommunicationModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SMS"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="Telephone"/>
 *     &lt;enumeration value="WalkIn"/>
 *     &lt;enumeration value="Web"/>
 *     &lt;enumeration value="Mobile"/>
 *     &lt;enumeration value="System"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Batch"/>
 *     &lt;enumeration value="Conversion"/>
 *     &lt;enumeration value="DriveThru"/>
 *     &lt;enumeration value="Accuro"/>
 *     &lt;enumeration value="HealthWatch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
