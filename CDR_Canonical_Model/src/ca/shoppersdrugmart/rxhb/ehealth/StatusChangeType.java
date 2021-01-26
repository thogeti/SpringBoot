
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusChangeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusChangeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Add Allergy"/&gt;
 *     &lt;enumeration value="Add Note"/&gt;
 *     &lt;enumeration value="Cancel Prescription"/&gt;
 *     &lt;enumeration value="Create Prescription"/&gt;
 *     &lt;enumeration value="Discontinue Prescription"/&gt;
 *     &lt;enumeration value="Fill Prescription"/&gt;
 *     &lt;enumeration value="Patient Picked Up Prescription"/&gt;
 *     &lt;enumeration value="Post to Profile"/&gt;
 *     &lt;enumeration value="Record Consent"/&gt;
 *     &lt;enumeration value="Refusal to Fill"/&gt;
 *     &lt;enumeration value="Stop Prescription"/&gt;
 *     &lt;enumeration value="Transfer Prescription"/&gt;
 *     &lt;enumeration value="Update Allergy"/&gt;
 *     &lt;enumeration value="Update Post to Profile"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatusChangeType")
@XmlEnum
public enum StatusChangeType {

    @XmlEnumValue("Add Allergy")
    ADD_ALLERGY("Add Allergy"),
    @XmlEnumValue("Add Note")
    ADD_NOTE("Add Note"),
    @XmlEnumValue("Cancel Prescription")
    CANCEL_PRESCRIPTION("Cancel Prescription"),
    @XmlEnumValue("Create Prescription")
    CREATE_PRESCRIPTION("Create Prescription"),
    @XmlEnumValue("Discontinue Prescription")
    DISCONTINUE_PRESCRIPTION("Discontinue Prescription"),
    @XmlEnumValue("Fill Prescription")
    FILL_PRESCRIPTION("Fill Prescription"),
    @XmlEnumValue("Patient Picked Up Prescription")
    PATIENT_PICKED_UP_PRESCRIPTION("Patient Picked Up Prescription"),
    @XmlEnumValue("Post to Profile")
    POST_TO_PROFILE("Post to Profile"),
    @XmlEnumValue("Record Consent")
    RECORD_CONSENT("Record Consent"),
    @XmlEnumValue("Refusal to Fill")
    REFUSAL_TO_FILL("Refusal to Fill"),
    @XmlEnumValue("Stop Prescription")
    STOP_PRESCRIPTION("Stop Prescription"),
    @XmlEnumValue("Transfer Prescription")
    TRANSFER_PRESCRIPTION("Transfer Prescription"),
    @XmlEnumValue("Update Allergy")
    UPDATE_ALLERGY("Update Allergy"),
    @XmlEnumValue("Update Post to Profile")
    UPDATE_POST_TO_PROFILE("Update Post to Profile");
    private final String value;

    StatusChangeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusChangeType fromValue(String v) {
        for (StatusChangeType c: StatusChangeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
