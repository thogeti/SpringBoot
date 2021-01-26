
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionSourceChannel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionSourceChannel"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AutomaticRefill"/&gt;
 *     &lt;enumeration value="Batch"/&gt;
 *     &lt;enumeration value="Converted"/&gt;
 *     &lt;enumeration value="DisasterRecovery"/&gt;
 *     &lt;enumeration value="DriveThru"/&gt;
 *     &lt;enumeration value="EhrModuleTransferIn"/&gt;
 *     &lt;enumeration value="ERefill"/&gt;
 *     &lt;enumeration value="Fax"/&gt;
 *     &lt;enumeration value="Internet"/&gt;
 *     &lt;enumeration value="Ivr"/&gt;
 *     &lt;enumeration value="Phone"/&gt;
 *     &lt;enumeration value="PrescriberPhone"/&gt;
 *     &lt;enumeration value="RefillReminder"/&gt;
 *     &lt;enumeration value="TransferInCompetitor"/&gt;
 *     &lt;enumeration value="TransferInCompetitorOutOfProvince"/&gt;
 *     &lt;enumeration value="TransferInSdm"/&gt;
 *     &lt;enumeration value="TransferInSdmOutOfProvince"/&gt;
 *     &lt;enumeration value="WalkIn"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TransactionSourceChannel")
@XmlEnum
public enum TransactionSourceChannel {

    @XmlEnumValue("AutomaticRefill")
    AUTOMATIC_REFILL("AutomaticRefill"),
    @XmlEnumValue("Batch")
    BATCH("Batch"),
    @XmlEnumValue("Converted")
    CONVERTED("Converted"),
    @XmlEnumValue("DisasterRecovery")
    DISASTER_RECOVERY("DisasterRecovery"),
    @XmlEnumValue("DriveThru")
    DRIVE_THRU("DriveThru"),
    @XmlEnumValue("EhrModuleTransferIn")
    EHR_MODULE_TRANSFER_IN("EhrModuleTransferIn"),
    @XmlEnumValue("ERefill")
    E_REFILL("ERefill"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Internet")
    INTERNET("Internet"),
    @XmlEnumValue("Ivr")
    IVR("Ivr"),
    @XmlEnumValue("Phone")
    PHONE("Phone"),
    @XmlEnumValue("PrescriberPhone")
    PRESCRIBER_PHONE("PrescriberPhone"),
    @XmlEnumValue("RefillReminder")
    REFILL_REMINDER("RefillReminder"),
    @XmlEnumValue("TransferInCompetitor")
    TRANSFER_IN_COMPETITOR("TransferInCompetitor"),
    @XmlEnumValue("TransferInCompetitorOutOfProvince")
    TRANSFER_IN_COMPETITOR_OUT_OF_PROVINCE("TransferInCompetitorOutOfProvince"),
    @XmlEnumValue("TransferInSdm")
    TRANSFER_IN_SDM("TransferInSdm"),
    @XmlEnumValue("TransferInSdmOutOfProvince")
    TRANSFER_IN_SDM_OUT_OF_PROVINCE("TransferInSdmOutOfProvince"),
    @XmlEnumValue("WalkIn")
    WALK_IN("WalkIn");
    private final String value;

    TransactionSourceChannel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionSourceChannel fromValue(String v) {
        for (TransactionSourceChannel c: TransactionSourceChannel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
