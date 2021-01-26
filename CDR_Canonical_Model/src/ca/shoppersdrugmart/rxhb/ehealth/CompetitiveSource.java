
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompetitiveSource.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CompetitiveSource"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *     &lt;enumeration value="genericSingleSource"/&gt;
 *     &lt;enumeration value="genericMultiSource"/&gt;
 *     &lt;enumeration value="brandSingleSource"/&gt;
 *     &lt;enumeration value="brandMultiSourceWithGeneric"/&gt;
 *     &lt;enumeration value="brandMultiSourceWithBrand"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CompetitiveSource")
@XmlEnum
public enum CompetitiveSource {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("genericSingleSource")
    GENERIC_SINGLE_SOURCE("genericSingleSource"),
    @XmlEnumValue("genericMultiSource")
    GENERIC_MULTI_SOURCE("genericMultiSource"),
    @XmlEnumValue("brandSingleSource")
    BRAND_SINGLE_SOURCE("brandSingleSource"),
    @XmlEnumValue("brandMultiSourceWithGeneric")
    BRAND_MULTI_SOURCE_WITH_GENERIC("brandMultiSourceWithGeneric"),
    @XmlEnumValue("brandMultiSourceWithBrand")
    BRAND_MULTI_SOURCE_WITH_BRAND("brandMultiSourceWithBrand");
    private final String value;

    CompetitiveSource(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CompetitiveSource fromValue(String v) {
        for (CompetitiveSource c: CompetitiveSource.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
