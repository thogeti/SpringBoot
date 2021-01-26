
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SourceSystemEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SourceSystemEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SourceSystemEnum")
@XmlEnum
public enum SourceSystemEnum {

    HW;

    public String value() {
        return name();
    }

    public static SourceSystemEnum fromValue(String v) {
        return valueOf(v);
    }

}
