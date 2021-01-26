
package ca.shoppersdrugmart.message.errorxml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransportMechanism.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransportMechanism">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REST"/>
 *     &lt;enumeration value="SFTP"/>
 *     &lt;enumeration value="FTP"/>
 *     &lt;enumeration value="ODBC"/>
 *     &lt;enumeration value="JDBC"/>
 *     &lt;enumeration value="JMS"/>
 *     &lt;enumeration value="SOAP"/>
 *     &lt;enumeration value="MQ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransportMechanism")
@XmlEnum
public enum TransportMechanism {

    REST,
    SFTP,
    FTP,
    ODBC,
    JDBC,
    JMS,
    SOAP,
    MQ;

    public String value() {
        return name();
    }

    public static TransportMechanism fromValue(String v) {
        return valueOf(v);
    }

}
