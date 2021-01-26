
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Division.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Division"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SDM"/&gt;
 *     &lt;enumeration value="LCL"/&gt;
 *     &lt;enumeration value="OTHERS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Division")
@XmlEnum
public enum Division {

    SDM,
    LCL,
    OTHERS;

    public String value() {
        return name();
    }

    public static Division fromValue(String v) {
        return valueOf(v);
    }

}
