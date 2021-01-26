
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

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
 *     &lt;enumeration value="Not Specified"/>
 *     &lt;enumeration value="Alaska"/>
 *     &lt;enumeration value="Alabama"/>
 *     &lt;enumeration value="Arkansas"/>
 *     &lt;enumeration value="Arizona"/>
 *     &lt;enumeration value="California"/>
 *     &lt;enumeration value="Colorado"/>
 *     &lt;enumeration value="Connecticut"/>
 *     &lt;enumeration value="District Of Columbia"/>
 *     &lt;enumeration value="Delaware"/>
 *     &lt;enumeration value="Florida"/>
 *     &lt;enumeration value="Georgia"/>
 *     &lt;enumeration value="Hawaii"/>
 *     &lt;enumeration value="Iowa"/>
 *     &lt;enumeration value="Idaho"/>
 *     &lt;enumeration value="Illinois"/>
 *     &lt;enumeration value="Indiana"/>
 *     &lt;enumeration value="Kansas"/>
 *     &lt;enumeration value="Kentucky"/>
 *     &lt;enumeration value="Louisiana"/>
 *     &lt;enumeration value="Massachusetts"/>
 *     &lt;enumeration value="Maryland"/>
 *     &lt;enumeration value="Maine"/>
 *     &lt;enumeration value="Michigan"/>
 *     &lt;enumeration value="Minnesota"/>
 *     &lt;enumeration value="Missouri"/>
 *     &lt;enumeration value="Mississippi"/>
 *     &lt;enumeration value="Montana"/>
 *     &lt;enumeration value="North Carolina"/>
 *     &lt;enumeration value="North Dakota"/>
 *     &lt;enumeration value="Nebraska"/>
 *     &lt;enumeration value="Vermont"/>
 *     &lt;enumeration value="New Hampshire"/>
 *     &lt;enumeration value="New Jersey"/>
 *     &lt;enumeration value="New Mexico"/>
 *     &lt;enumeration value="Nevada"/>
 *     &lt;enumeration value="New York"/>
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
 *     &lt;enumeration value="Virginia"/>
 *     &lt;enumeration value="Washington"/>
 *     &lt;enumeration value="Wisconsin"/>
 *     &lt;enumeration value="West Virginia"/>
 *     &lt;enumeration value="Wyoming"/>
 *     &lt;enumeration value="alberta"/>
 *     &lt;enumeration value="quebec"/>
 *     &lt;enumeration value="ontario"/>
 *     &lt;enumeration value="manitoba"/>
 *     &lt;enumeration value="newBrunswick"/>
 *     &lt;enumeration value="newfoundlandAndLabrador"/>
 *     &lt;enumeration value="novaScotia"/>
 *     &lt;enumeration value="northwestTerritories"/>
 *     &lt;enumeration value="nunavut"/>
 *     &lt;enumeration value="princeEdwardIsland"/>
 *     &lt;enumeration value="saskatchewan"/>
 *     &lt;enumeration value="yukon"/>
 *     &lt;enumeration value="britishColumbia"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
