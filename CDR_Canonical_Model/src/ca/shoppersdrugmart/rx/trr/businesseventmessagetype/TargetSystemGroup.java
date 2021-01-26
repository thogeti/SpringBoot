
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TargetSystemGroup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TargetSystemGroup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ODSLogicalQueue"/>
 *     &lt;enumeration value="EHealthLogicalQueue"/>
 *     &lt;enumeration value="ErrorMessageLogicalQueue"/>
 *     &lt;enumeration value="FileSystem"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TargetSystemGroup")
@XmlEnum
public enum TargetSystemGroup {

    @XmlEnumValue("ODSLogicalQueue")
    ODS_LOGICAL_QUEUE("ODSLogicalQueue"),
    @XmlEnumValue("EHealthLogicalQueue")
    E_HEALTH_LOGICAL_QUEUE("EHealthLogicalQueue"),
    @XmlEnumValue("ErrorMessageLogicalQueue")
    ERROR_MESSAGE_LOGICAL_QUEUE("ErrorMessageLogicalQueue"),
    @XmlEnumValue("FileSystem")
    FILE_SYSTEM("FileSystem");
    private final String value;

    TargetSystemGroup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TargetSystemGroup fromValue(String v) {
        for (TargetSystemGroup c: TargetSystemGroup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
