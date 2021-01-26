
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChannelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DigitalPharmacy"/>
 *     &lt;enumeration value="PatientContactCenter"/>
 *     &lt;enumeration value="Store"/>
 *     &lt;enumeration value="IVR"/>
 *     &lt;enumeration value="Prescriber"/>
 *     &lt;enumeration value="eHealth"/>
 *     &lt;enumeration value="Accuro"/>
 *     &lt;enumeration value="CustomerServiceRepresentative"/>
 *     &lt;enumeration value="PatientConsent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChannelType")
@XmlEnum
public enum ChannelType {

    @XmlEnumValue("DigitalPharmacy")
    DIGITAL_PHARMACY("DigitalPharmacy"),
    @XmlEnumValue("PatientContactCenter")
    PATIENT_CONTACT_CENTER("PatientContactCenter"),
    @XmlEnumValue("Store")
    STORE("Store"),
    IVR("IVR"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("eHealth")
    E_HEALTH("eHealth"),
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),
    @XmlEnumValue("CustomerServiceRepresentative")
    CUSTOMER_SERVICE_REPRESENTATIVE("CustomerServiceRepresentative"),
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
