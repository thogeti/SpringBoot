
package ca.shoppersdrugmart.message.errorxml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Security"/>
 *     &lt;enumeration value="Service"/>
 *     &lt;enumeration value="Application"/>
 *     &lt;enumeration value="UnexpectedServiceInterruption"/>
 *     &lt;enumeration value="ApplicationIntegrity"/>
 *     &lt;enumeration value="BusinessRule"/>
 *     &lt;enumeration value="BusinessPermission"/>
 *     &lt;enumeration value="BusinessRuleComposite"/>
 *     &lt;enumeration value="InputViolation"/>
 *     &lt;enumeration value="Validation"/>
 *     &lt;enumeration value="System"/>
 *     &lt;enumeration value="ApplicationDisabled"/>
 *     &lt;enumeration value="DataSourceAccess"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCategory")
@XmlEnum
public enum ErrorCategory {

    @XmlEnumValue("Security")
    SECURITY("Security"),
    @XmlEnumValue("Service")
    SERVICE("Service"),
    @XmlEnumValue("Application")
    APPLICATION("Application"),
    @XmlEnumValue("UnexpectedServiceInterruption")
    UNEXPECTED_SERVICE_INTERRUPTION("UnexpectedServiceInterruption"),
    @XmlEnumValue("ApplicationIntegrity")
    APPLICATION_INTEGRITY("ApplicationIntegrity"),
    @XmlEnumValue("BusinessRule")
    BUSINESS_RULE("BusinessRule"),
    @XmlEnumValue("BusinessPermission")
    BUSINESS_PERMISSION("BusinessPermission"),
    @XmlEnumValue("BusinessRuleComposite")
    BUSINESS_RULE_COMPOSITE("BusinessRuleComposite"),
    @XmlEnumValue("InputViolation")
    INPUT_VIOLATION("InputViolation"),
    @XmlEnumValue("Validation")
    VALIDATION("Validation"),
    @XmlEnumValue("System")
    SYSTEM("System"),
    @XmlEnumValue("ApplicationDisabled")
    APPLICATION_DISABLED("ApplicationDisabled"),
    @XmlEnumValue("DataSourceAccess")
    DATA_SOURCE_ACCESS("DataSourceAccess");
    private final String value;

    ErrorCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorCategory fromValue(String v) {
        for (ErrorCategory c: ErrorCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
