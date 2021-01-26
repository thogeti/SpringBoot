
package ca.shoppersdrugmart.message.sdmdatatypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Province.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Province">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Quebec"/>
 *     &lt;enumeration value="Iowa"/>
 *     &lt;enumeration value="Kansas"/>
 *     &lt;enumeration value="Kentucky"/>
 *     &lt;enumeration value="Louisiana"/>
 *     &lt;enumeration value="Maine"/>
 *     &lt;enumeration value="Maryland"/>
 *     &lt;enumeration value="Massachusetts"/>
 *     &lt;enumeration value="Michigan"/>
 *     &lt;enumeration value="Minnesota"/>
 *     &lt;enumeration value="Mississippi"/>
 *     &lt;enumeration value="Missouri"/>
 *     &lt;enumeration value="Montana"/>
 *     &lt;enumeration value="Nebraska"/>
 *     &lt;enumeration value="Nevada"/>
 *     &lt;enumeration value="New Hampshire"/>
 *     &lt;enumeration value="New Jersey"/>
 *     &lt;enumeration value="New Mexico"/>
 *     &lt;enumeration value="New York"/>
 *     &lt;enumeration value="North Carolina"/>
 *     &lt;enumeration value="North Dakota"/>
 *     &lt;enumeration value="Ohio"/>
 *     &lt;enumeration value="Oklahoma"/>
 *     &lt;enumeration value="Oregon"/>
 *     &lt;enumeration value="Pennsylvania"/>
 *     &lt;enumeration value="Rhode Island"/>
 *     &lt;enumeration value="South Carolina"/>
 *     &lt;enumeration value="South Dakota"/>
 *     &lt;enumeration value="Tennessee"/>
 *     &lt;enumeration value="Texas"/>
 *     &lt;enumeration value="Utah"/>
 *     &lt;enumeration value="Vermont"/>
 *     &lt;enumeration value="Virginia"/>
 *     &lt;enumeration value="Washington"/>
 *     &lt;enumeration value="West Virginia"/>
 *     &lt;enumeration value="Wisconsin"/>
 *     &lt;enumeration value="Wyoming"/>
 *     &lt;enumeration value="Ontario"/>
 *     &lt;enumeration value="Manitoba"/>
 *     &lt;enumeration value="New Brunswick"/>
 *     &lt;enumeration value="Newfoundland and Labrador"/>
 *     &lt;enumeration value="Nova Scotia"/>
 *     &lt;enumeration value="Northwest Territories"/>
 *     &lt;enumeration value="Nunavut"/>
 *     &lt;enumeration value="Prince Edward Island"/>
 *     &lt;enumeration value="Saskatchewan"/>
 *     &lt;enumeration value="Yukon"/>
 *     &lt;enumeration value="British Columbia"/>
 *     &lt;enumeration value="Alberta"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Province", namespace = "http://www.shoppersdrugmart.ca/message/SDMDataTypes")
@XmlEnum
public enum Province {

    @XmlEnumValue("Quebec")
    QUEBEC("Quebec"),
    @XmlEnumValue("Iowa")
    IOWA("Iowa"),
    @XmlEnumValue("Kansas")
    KANSAS("Kansas"),
    @XmlEnumValue("Kentucky")
    KENTUCKY("Kentucky"),
    @XmlEnumValue("Louisiana")
    LOUISIANA("Louisiana"),
    @XmlEnumValue("Maine")
    MAINE("Maine"),
    @XmlEnumValue("Maryland")
    MARYLAND("Maryland"),
    @XmlEnumValue("Massachusetts")
    MASSACHUSETTS("Massachusetts"),
    @XmlEnumValue("Michigan")
    MICHIGAN("Michigan"),
    @XmlEnumValue("Minnesota")
    MINNESOTA("Minnesota"),
    @XmlEnumValue("Mississippi")
    MISSISSIPPI("Mississippi"),
    @XmlEnumValue("Missouri")
    MISSOURI("Missouri"),
    @XmlEnumValue("Montana")
    MONTANA("Montana"),
    @XmlEnumValue("Nebraska")
    NEBRASKA("Nebraska"),
    @XmlEnumValue("Nevada")
    NEVADA("Nevada"),
    @XmlEnumValue("New Hampshire")
    NEW_HAMPSHIRE("New Hampshire"),
    @XmlEnumValue("New Jersey")
    NEW_JERSEY("New Jersey"),
    @XmlEnumValue("New Mexico")
    NEW_MEXICO("New Mexico"),
    @XmlEnumValue("New York")
    NEW_YORK("New York"),
    @XmlEnumValue("North Carolina")
    NORTH_CAROLINA("North Carolina"),
    @XmlEnumValue("North Dakota")
    NORTH_DAKOTA("North Dakota"),
    @XmlEnumValue("Ohio")
    OHIO("Ohio"),
    @XmlEnumValue("Oklahoma")
    OKLAHOMA("Oklahoma"),
    @XmlEnumValue("Oregon")
    OREGON("Oregon"),
    @XmlEnumValue("Pennsylvania")
    PENNSYLVANIA("Pennsylvania"),
    @XmlEnumValue("Rhode Island")
    RHODE_ISLAND("Rhode Island"),
    @XmlEnumValue("South Carolina")
    SOUTH_CAROLINA("South Carolina"),
    @XmlEnumValue("South Dakota")
    SOUTH_DAKOTA("South Dakota"),
    @XmlEnumValue("Tennessee")
    TENNESSEE("Tennessee"),
    @XmlEnumValue("Texas")
    TEXAS("Texas"),
    @XmlEnumValue("Utah")
    UTAH("Utah"),
    @XmlEnumValue("Vermont")
    VERMONT("Vermont"),
    @XmlEnumValue("Virginia")
    VIRGINIA("Virginia"),
    @XmlEnumValue("Washington")
    WASHINGTON("Washington"),
    @XmlEnumValue("West Virginia")
    WEST_VIRGINIA("West Virginia"),
    @XmlEnumValue("Wisconsin")
    WISCONSIN("Wisconsin"),
    @XmlEnumValue("Wyoming")
    WYOMING("Wyoming"),
    @XmlEnumValue("Ontario")
    ONTARIO("Ontario"),
    @XmlEnumValue("Manitoba")
    MANITOBA("Manitoba"),
    @XmlEnumValue("New Brunswick")
    NEW_BRUNSWICK("New Brunswick"),
    @XmlEnumValue("Newfoundland and Labrador")
    NEWFOUNDLAND_AND_LABRADOR("Newfoundland and Labrador"),
    @XmlEnumValue("Nova Scotia")
    NOVA_SCOTIA("Nova Scotia"),
    @XmlEnumValue("Northwest Territories")
    NORTHWEST_TERRITORIES("Northwest Territories"),
    @XmlEnumValue("Nunavut")
    NUNAVUT("Nunavut"),
    @XmlEnumValue("Prince Edward Island")
    PRINCE_EDWARD_ISLAND("Prince Edward Island"),
    @XmlEnumValue("Saskatchewan")
    SASKATCHEWAN("Saskatchewan"),
    @XmlEnumValue("Yukon")
    YUKON("Yukon"),
    @XmlEnumValue("British Columbia")
    BRITISH_COLUMBIA("British Columbia"),
    @XmlEnumValue("Alberta")
    ALBERTA("Alberta");
    private final String value;

    Province(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Province fromValue(String v) {
        for (Province c: Province.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
