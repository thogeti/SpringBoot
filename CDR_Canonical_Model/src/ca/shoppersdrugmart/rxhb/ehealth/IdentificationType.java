
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdentificationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Nova Scotia - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="New Brunswick - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Newfoundland and Labrador - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Prince Edward Island - Canada Personal Health Number Identifier"/&gt;
 *     &lt;enumeration value="Quebec - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Ontario - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Manitoba - Health Personal Health Identification Number"/&gt;
 *     &lt;enumeration value="Saskatchewan - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Alberta - Canada Unique Lifetime Identifier"/&gt;
 *     &lt;enumeration value="British Columbia - Personal Health Number"/&gt;
 *     &lt;enumeration value="Nunavut - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Northwest Territories - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Yukon - Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="RCMP NS Based"/&gt;
 *     &lt;enumeration value="Canadian Armed Forces Health Number"/&gt;
 *     &lt;enumeration value="Alternate Client ID for Other Jurisdiction"/&gt;
 *     &lt;enumeration value="Veterans Affairs Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Correctional Service Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Indian and Northern Affairs Canada Personal Health Number"/&gt;
 *     &lt;enumeration value="Shared OPOR Identifiers"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "IdentificationType")
@XmlEnum
public enum IdentificationType {

    @XmlEnumValue("Nova Scotia - Canada Personal Health Number")
    NOVA_SCOTIA_CANADA_PERSONAL_HEALTH_NUMBER("Nova Scotia - Canada Personal Health Number"),
    @XmlEnumValue("New Brunswick - Canada Personal Health Number")
    NEW_BRUNSWICK_CANADA_PERSONAL_HEALTH_NUMBER("New Brunswick - Canada Personal Health Number"),
    @XmlEnumValue("Newfoundland and Labrador - Canada Personal Health Number")
    NEWFOUNDLAND_AND_LABRADOR_CANADA_PERSONAL_HEALTH_NUMBER("Newfoundland and Labrador - Canada Personal Health Number"),
    @XmlEnumValue("Prince Edward Island - Canada Personal Health Number Identifier")
    PRINCE_EDWARD_ISLAND_CANADA_PERSONAL_HEALTH_NUMBER_IDENTIFIER("Prince Edward Island - Canada Personal Health Number Identifier"),
    @XmlEnumValue("Quebec - Canada Personal Health Number")
    QUEBEC_CANADA_PERSONAL_HEALTH_NUMBER("Quebec - Canada Personal Health Number"),
    @XmlEnumValue("Ontario - Canada Personal Health Number")
    ONTARIO_CANADA_PERSONAL_HEALTH_NUMBER("Ontario - Canada Personal Health Number"),
    @XmlEnumValue("Manitoba - Health Personal Health Identification Number")
    MANITOBA_HEALTH_PERSONAL_HEALTH_IDENTIFICATION_NUMBER("Manitoba - Health Personal Health Identification Number"),
    @XmlEnumValue("Saskatchewan - Canada Personal Health Number")
    SASKATCHEWAN_CANADA_PERSONAL_HEALTH_NUMBER("Saskatchewan - Canada Personal Health Number"),
    @XmlEnumValue("Alberta - Canada Unique Lifetime Identifier")
    ALBERTA_CANADA_UNIQUE_LIFETIME_IDENTIFIER("Alberta - Canada Unique Lifetime Identifier"),
    @XmlEnumValue("British Columbia - Personal Health Number")
    BRITISH_COLUMBIA_PERSONAL_HEALTH_NUMBER("British Columbia - Personal Health Number"),
    @XmlEnumValue("Nunavut - Canada Personal Health Number")
    NUNAVUT_CANADA_PERSONAL_HEALTH_NUMBER("Nunavut - Canada Personal Health Number"),
    @XmlEnumValue("Northwest Territories - Canada Personal Health Number")
    NORTHWEST_TERRITORIES_CANADA_PERSONAL_HEALTH_NUMBER("Northwest Territories - Canada Personal Health Number"),
    @XmlEnumValue("Yukon - Canada Personal Health Number")
    YUKON_CANADA_PERSONAL_HEALTH_NUMBER("Yukon - Canada Personal Health Number"),
    @XmlEnumValue("RCMP NS Based")
    RCMP_NS_BASED("RCMP NS Based"),
    @XmlEnumValue("Canadian Armed Forces Health Number")
    CANADIAN_ARMED_FORCES_HEALTH_NUMBER("Canadian Armed Forces Health Number"),
    @XmlEnumValue("Alternate Client ID for Other Jurisdiction")
    ALTERNATE_CLIENT_ID_FOR_OTHER_JURISDICTION("Alternate Client ID for Other Jurisdiction"),
    @XmlEnumValue("Veterans Affairs Canada Personal Health Number")
    VETERANS_AFFAIRS_CANADA_PERSONAL_HEALTH_NUMBER("Veterans Affairs Canada Personal Health Number"),
    @XmlEnumValue("Correctional Service Canada Personal Health Number")
    CORRECTIONAL_SERVICE_CANADA_PERSONAL_HEALTH_NUMBER("Correctional Service Canada Personal Health Number"),
    @XmlEnumValue("Indian and Northern Affairs Canada Personal Health Number")
    INDIAN_AND_NORTHERN_AFFAIRS_CANADA_PERSONAL_HEALTH_NUMBER("Indian and Northern Affairs Canada Personal Health Number"),
    @XmlEnumValue("Shared OPOR Identifiers")
    SHARED_OPOR_IDENTIFIERS("Shared OPOR Identifiers");
    private final String value;

    IdentificationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentificationType fromValue(String v) {
        for (IdentificationType c: IdentificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
