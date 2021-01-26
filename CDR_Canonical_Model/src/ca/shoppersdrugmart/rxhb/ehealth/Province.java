
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Province.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Province"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Not Specified"/&gt;
 *     &lt;enumeration value="Alaska"/&gt;
 *     &lt;enumeration value="Alabama"/&gt;
 *     &lt;enumeration value="Arkansas"/&gt;
 *     &lt;enumeration value="Arizona"/&gt;
 *     &lt;enumeration value="California"/&gt;
 *     &lt;enumeration value="Colorado"/&gt;
 *     &lt;enumeration value="Connecticut"/&gt;
 *     &lt;enumeration value="District Of Columbia"/&gt;
 *     &lt;enumeration value="Delaware"/&gt;
 *     &lt;enumeration value="Florida"/&gt;
 *     &lt;enumeration value="Georgia"/&gt;
 *     &lt;enumeration value="Hawaii"/&gt;
 *     &lt;enumeration value="Iowa"/&gt;
 *     &lt;enumeration value="Idaho"/&gt;
 *     &lt;enumeration value="Illinois"/&gt;
 *     &lt;enumeration value="Indiana"/&gt;
 *     &lt;enumeration value="Kansas"/&gt;
 *     &lt;enumeration value="Kentucky"/&gt;
 *     &lt;enumeration value="Louisiana"/&gt;
 *     &lt;enumeration value="Massachusetts"/&gt;
 *     &lt;enumeration value="Maryland"/&gt;
 *     &lt;enumeration value="Maine"/&gt;
 *     &lt;enumeration value="Michigan"/&gt;
 *     &lt;enumeration value="Minnesota"/&gt;
 *     &lt;enumeration value="Missouri"/&gt;
 *     &lt;enumeration value="Mississippi"/&gt;
 *     &lt;enumeration value="Montana"/&gt;
 *     &lt;enumeration value="North Carolina"/&gt;
 *     &lt;enumeration value="North Dakota"/&gt;
 *     &lt;enumeration value="Nebraska"/&gt;
 *     &lt;enumeration value="Vermont"/&gt;
 *     &lt;enumeration value="New Hampshire"/&gt;
 *     &lt;enumeration value="New Jersey"/&gt;
 *     &lt;enumeration value="New Mexico"/&gt;
 *     &lt;enumeration value="Nevada"/&gt;
 *     &lt;enumeration value="New York"/&gt;
 *     &lt;enumeration value="Ohio"/&gt;
 *     &lt;enumeration value="Oklahoma"/&gt;
 *     &lt;enumeration value="Oregon"/&gt;
 *     &lt;enumeration value="Pennsylvania"/&gt;
 *     &lt;enumeration value="Rhode Island"/&gt;
 *     &lt;enumeration value="South Carolina"/&gt;
 *     &lt;enumeration value="South Dakota"/&gt;
 *     &lt;enumeration value="Tennessee"/&gt;
 *     &lt;enumeration value="Texas"/&gt;
 *     &lt;enumeration value="Utah"/&gt;
 *     &lt;enumeration value="Virginia"/&gt;
 *     &lt;enumeration value="Washington"/&gt;
 *     &lt;enumeration value="Wisconsin"/&gt;
 *     &lt;enumeration value="West Virginia"/&gt;
 *     &lt;enumeration value="Wyoming"/&gt;
 *     &lt;enumeration value="alberta"/&gt;
 *     &lt;enumeration value="quebec"/&gt;
 *     &lt;enumeration value="ontario"/&gt;
 *     &lt;enumeration value="manitoba"/&gt;
 *     &lt;enumeration value="newBrunswick"/&gt;
 *     &lt;enumeration value="newfoundlandAndLabrador"/&gt;
 *     &lt;enumeration value="novaScotia"/&gt;
 *     &lt;enumeration value="northwestTerritories"/&gt;
 *     &lt;enumeration value="nunavut"/&gt;
 *     &lt;enumeration value="princeEdwardIsland"/&gt;
 *     &lt;enumeration value="saskatchewan"/&gt;
 *     &lt;enumeration value="yukon"/&gt;
 *     &lt;enumeration value="britishColumbia"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Province")
@XmlEnum
public enum Province {

    @XmlEnumValue("Not Specified")
    NOT_SPECIFIED("Not Specified"),
    @XmlEnumValue("Alaska")
    ALASKA("Alaska"),
    @XmlEnumValue("Alabama")
    ALABAMA("Alabama"),
    @XmlEnumValue("Arkansas")
    ARKANSAS("Arkansas"),
    @XmlEnumValue("Arizona")
    ARIZONA("Arizona"),
    @XmlEnumValue("California")
    CALIFORNIA("California"),
    @XmlEnumValue("Colorado")
    COLORADO("Colorado"),
    @XmlEnumValue("Connecticut")
    CONNECTICUT("Connecticut"),
    @XmlEnumValue("District Of Columbia")
    DISTRICT_OF_COLUMBIA("District Of Columbia"),
    @XmlEnumValue("Delaware")
    DELAWARE("Delaware"),
    @XmlEnumValue("Florida")
    FLORIDA("Florida"),
    @XmlEnumValue("Georgia")
    GEORGIA("Georgia"),
    @XmlEnumValue("Hawaii")
    HAWAII("Hawaii"),
    @XmlEnumValue("Iowa")
    IOWA("Iowa"),
    @XmlEnumValue("Idaho")
    IDAHO("Idaho"),
    @XmlEnumValue("Illinois")
    ILLINOIS("Illinois"),
    @XmlEnumValue("Indiana")
    INDIANA("Indiana"),
    @XmlEnumValue("Kansas")
    KANSAS("Kansas"),
    @XmlEnumValue("Kentucky")
    KENTUCKY("Kentucky"),
    @XmlEnumValue("Louisiana")
    LOUISIANA("Louisiana"),
    @XmlEnumValue("Massachusetts")
    MASSACHUSETTS("Massachusetts"),
    @XmlEnumValue("Maryland")
    MARYLAND("Maryland"),
    @XmlEnumValue("Maine")
    MAINE("Maine"),
    @XmlEnumValue("Michigan")
    MICHIGAN("Michigan"),
    @XmlEnumValue("Minnesota")
    MINNESOTA("Minnesota"),
    @XmlEnumValue("Missouri")
    MISSOURI("Missouri"),
    @XmlEnumValue("Mississippi")
    MISSISSIPPI("Mississippi"),
    @XmlEnumValue("Montana")
    MONTANA("Montana"),
    @XmlEnumValue("North Carolina")
    NORTH_CAROLINA("North Carolina"),
    @XmlEnumValue("North Dakota")
    NORTH_DAKOTA("North Dakota"),
    @XmlEnumValue("Nebraska")
    NEBRASKA("Nebraska"),
    @XmlEnumValue("Vermont")
    VERMONT("Vermont"),
    @XmlEnumValue("New Hampshire")
    NEW_HAMPSHIRE("New Hampshire"),
    @XmlEnumValue("New Jersey")
    NEW_JERSEY("New Jersey"),
    @XmlEnumValue("New Mexico")
    NEW_MEXICO("New Mexico"),
    @XmlEnumValue("Nevada")
    NEVADA("Nevada"),
    @XmlEnumValue("New York")
    NEW_YORK("New York"),
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
    @XmlEnumValue("Virginia")
    VIRGINIA("Virginia"),
    @XmlEnumValue("Washington")
    WASHINGTON("Washington"),
    @XmlEnumValue("Wisconsin")
    WISCONSIN("Wisconsin"),
    @XmlEnumValue("West Virginia")
    WEST_VIRGINIA("West Virginia"),
    @XmlEnumValue("Wyoming")
    WYOMING("Wyoming"),
    @XmlEnumValue("alberta")
    ALBERTA("alberta"),
    @XmlEnumValue("quebec")
    QUEBEC("quebec"),
    @XmlEnumValue("ontario")
    ONTARIO("ontario"),
    @XmlEnumValue("manitoba")
    MANITOBA("manitoba"),
    @XmlEnumValue("newBrunswick")
    NEW_BRUNSWICK("newBrunswick"),
    @XmlEnumValue("newfoundlandAndLabrador")
    NEWFOUNDLAND_AND_LABRADOR("newfoundlandAndLabrador"),
    @XmlEnumValue("novaScotia")
    NOVA_SCOTIA("novaScotia"),
    @XmlEnumValue("northwestTerritories")
    NORTHWEST_TERRITORIES("northwestTerritories"),
    @XmlEnumValue("nunavut")
    NUNAVUT("nunavut"),
    @XmlEnumValue("princeEdwardIsland")
    PRINCE_EDWARD_ISLAND("princeEdwardIsland"),
    @XmlEnumValue("saskatchewan")
    SASKATCHEWAN("saskatchewan"),
    @XmlEnumValue("yukon")
    YUKON("yukon"),
    @XmlEnumValue("britishColumbia")
    BRITISH_COLUMBIA("britishColumbia");
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
