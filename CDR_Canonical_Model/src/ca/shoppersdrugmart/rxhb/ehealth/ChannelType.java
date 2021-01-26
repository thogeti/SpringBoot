
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChannelType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DigitalPharmacy"/&gt;
 *     &lt;enumeration value="PatientContactCenter"/&gt;
 *     &lt;enumeration value="Store"/&gt;
 *     &lt;enumeration value="LoblawStore"/&gt;
 *     &lt;enumeration value="IVR"/&gt;
 *     &lt;enumeration value="Prescriber"/&gt;
 *     &lt;enumeration value="eHealth"/&gt;
 *     &lt;enumeration value="Accuro"/&gt;
 *     &lt;enumeration value="CustomerServiceRepresentative"/&gt;
 *     &lt;enumeration value="PatientConsent"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ChannelType")
@XmlEnum
public enum ChannelType {


    /**
     * The request originated from the Digital Rx channel
     * 
     */
    @XmlEnumValue("DigitalPharmacy")
    DIGITAL_PHARMACY("DigitalPharmacy"),

    /**
     * The request originated from the PCC channel
     * 
     */
    @XmlEnumValue("PatientContactCenter")
    PATIENT_CONTACT_CENTER("PatientContactCenter"),

    /**
     * The request originated in the Shoppers Store
     * 
     */
    @XmlEnumValue("Store")
    STORE("Store"),

    /**
     * The request originated in the Loblaw Store
     * 
     */
    @XmlEnumValue("LoblawStore")
    LOBLAW_STORE("LoblawStore"),

    /**
     * The request originated from the IVR channel
     * 
     */
    IVR("IVR"),

    /**
     * The request originated from the Prescriber
     * 
     */
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),

    /**
     * The request originated from the eHealth channel
     * 
     */
    @XmlEnumValue("eHealth")
    E_HEALTH("eHealth"),
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),

    /**
     * The request originated from the CSR channel
     * 
     */
    @XmlEnumValue("CustomerServiceRepresentative")
    CUSTOMER_SERVICE_REPRESENTATIVE("CustomerServiceRepresentative"),

    /**
     * The request originated as a result of Patient Consent
     * 
     */
    @XmlEnumValue("PatientConsent")
    PATIENT_CONSENT("PatientConsent");
    private final String value;

    ChannelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChannelType fromValue(String v) {
        for (ChannelType c: ChannelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
