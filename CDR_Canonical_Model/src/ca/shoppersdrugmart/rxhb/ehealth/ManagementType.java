
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ManagementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ManagementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Emergency Authorization Override"/&gt;
 *     &lt;enumeration value="Therapy Appropriate"/&gt;
 *     &lt;enumeration value="Consulted Supplier"/&gt;
 *     &lt;enumeration value="Assessed Patient"/&gt;
 *     &lt;enumeration value="Patient Explanation"/&gt;
 *     &lt;enumeration value="Consulted Other Source"/&gt;
 *     &lt;enumeration value="Consulted Prescriber"/&gt;
 *     &lt;enumeration value="Prescriber Declined Change"/&gt;
 *     &lt;enumeration value="Interacting Therapy No Longer Active/Planned"/&gt;
 *     &lt;enumeration value="Supply Appropriate"/&gt;
 *     &lt;enumeration value="Replacement"/&gt;
 *     &lt;enumeration value="Vacation Supply"/&gt;
 *     &lt;enumeration value="Weekend Supply"/&gt;
 *     &lt;enumeration value="Leave Of Absence"/&gt;
 *     &lt;enumeration value="Augment Current Supply-On-Hand"/&gt;
 *     &lt;enumeration value="Additional Quantity On Separate Dispense"/&gt;
 *     &lt;enumeration value="Other Action Taken"/&gt;
 *     &lt;enumeration value="Provided Patient Education"/&gt;
 *     &lt;enumeration value="Added Concurrent Therapy"/&gt;
 *     &lt;enumeration value="Temporarily Suspended Concurrent Therapy"/&gt;
 *     &lt;enumeration value="Stopped Concurrent Therapy"/&gt;
 *     &lt;enumeration value="Instituted Ongoing Monitoring Program"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ManagementType")
@XmlEnum
public enum ManagementType {

    @XmlEnumValue("Emergency Authorization Override")
    EMERGENCY_AUTHORIZATION_OVERRIDE("Emergency Authorization Override"),
    @XmlEnumValue("Therapy Appropriate")
    THERAPY_APPROPRIATE("Therapy Appropriate"),
    @XmlEnumValue("Consulted Supplier")
    CONSULTED_SUPPLIER("Consulted Supplier"),
    @XmlEnumValue("Assessed Patient")
    ASSESSED_PATIENT("Assessed Patient"),
    @XmlEnumValue("Patient Explanation")
    PATIENT_EXPLANATION("Patient Explanation"),
    @XmlEnumValue("Consulted Other Source")
    CONSULTED_OTHER_SOURCE("Consulted Other Source"),
    @XmlEnumValue("Consulted Prescriber")
    CONSULTED_PRESCRIBER("Consulted Prescriber"),
    @XmlEnumValue("Prescriber Declined Change")
    PRESCRIBER_DECLINED_CHANGE("Prescriber Declined Change"),
    @XmlEnumValue("Interacting Therapy No Longer Active/Planned")
    INTERACTING_THERAPY_NO_LONGER_ACTIVE_PLANNED("Interacting Therapy No Longer Active/Planned"),
    @XmlEnumValue("Supply Appropriate")
    SUPPLY_APPROPRIATE("Supply Appropriate"),
    @XmlEnumValue("Replacement")
    REPLACEMENT("Replacement"),
    @XmlEnumValue("Vacation Supply")
    VACATION_SUPPLY("Vacation Supply"),
    @XmlEnumValue("Weekend Supply")
    WEEKEND_SUPPLY("Weekend Supply"),
    @XmlEnumValue("Leave Of Absence")
    LEAVE_OF_ABSENCE("Leave Of Absence"),
    @XmlEnumValue("Augment Current Supply-On-Hand")
    AUGMENT_CURRENT_SUPPLY_ON_HAND("Augment Current Supply-On-Hand"),
    @XmlEnumValue("Additional Quantity On Separate Dispense")
    ADDITIONAL_QUANTITY_ON_SEPARATE_DISPENSE("Additional Quantity On Separate Dispense"),
    @XmlEnumValue("Other Action Taken")
    OTHER_ACTION_TAKEN("Other Action Taken"),
    @XmlEnumValue("Provided Patient Education")
    PROVIDED_PATIENT_EDUCATION("Provided Patient Education"),
    @XmlEnumValue("Added Concurrent Therapy")
    ADDED_CONCURRENT_THERAPY("Added Concurrent Therapy"),
    @XmlEnumValue("Temporarily Suspended Concurrent Therapy")
    TEMPORARILY_SUSPENDED_CONCURRENT_THERAPY("Temporarily Suspended Concurrent Therapy"),
    @XmlEnumValue("Stopped Concurrent Therapy")
    STOPPED_CONCURRENT_THERAPY("Stopped Concurrent Therapy"),
    @XmlEnumValue("Instituted Ongoing Monitoring Program")
    INSTITUTED_ONGOING_MONITORING_PROGRAM("Instituted Ongoing Monitoring Program");
    private final String value;

    ManagementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ManagementType fromValue(String v) {
        for (ManagementType c: ManagementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
