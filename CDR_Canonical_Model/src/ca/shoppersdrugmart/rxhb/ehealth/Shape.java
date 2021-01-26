
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Shape.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Shape"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *     &lt;enumeration value="airplane-shaped"/&gt;
 *     &lt;enumeration value="almond-shaped"/&gt;
 *     &lt;enumeration value="apple-shaped"/&gt;
 *     &lt;enumeration value="barrel-shaped"/&gt;
 *     &lt;enumeration value="blazon"/&gt;
 *     &lt;enumeration value="capsule-like"/&gt;
 *     &lt;enumeration value="car-shaped"/&gt;
 *     &lt;enumeration value="character"/&gt;
 *     &lt;enumeration value="cream/ovule"/&gt;
 *     &lt;enumeration value="cream"/&gt;
 *     &lt;enumeration value="cylindrical"/&gt;
 *     &lt;enumeration value="diamond-shaped"/&gt;
 *     &lt;enumeration value="d-shaped"/&gt;
 *     &lt;enumeration value="elliptical"/&gt;
 *     &lt;enumeration value="foam"/&gt;
 *     &lt;enumeration value="gel"/&gt;
 *     &lt;enumeration value="granule"/&gt;
 *     &lt;enumeration value="hexagon"/&gt;
 *     &lt;enumeration value="lotion"/&gt;
 *     &lt;enumeration value="oblong"/&gt;
 *     &lt;enumeration value="octagonal"/&gt;
 *     &lt;enumeration value="ointment"/&gt;
 *     &lt;enumeration value="oval"/&gt;
 *     &lt;enumeration value="ovule-like"/&gt;
 *     &lt;enumeration value="pellet"/&gt;
 *     &lt;enumeration value="pentagonal"/&gt;
 *     &lt;enumeration value="peanut-shaped"/&gt;
 *     &lt;enumeration value="powder"/&gt;
 *     &lt;enumeration value="rectangle"/&gt;
 *     &lt;enumeration value="round"/&gt;
 *     &lt;enumeration value="rod-shaped"/&gt;
 *     &lt;enumeration value="roundRectangular"/&gt;
 *     &lt;enumeration value="roundedSquare"/&gt;
 *     &lt;enumeration value="roundedTriangle"/&gt;
 *     &lt;enumeration value="squaredCapsule-like"/&gt;
 *     &lt;enumeration value="shield"/&gt;
 *     &lt;enumeration value="square"/&gt;
 *     &lt;enumeration value="squarePyramid"/&gt;
 *     &lt;enumeration value="tear-drop"/&gt;
 *     &lt;enumeration value="torpedo-shaped"/&gt;
 *     &lt;enumeration value="train-shaped"/&gt;
 *     &lt;enumeration value="trapezoid"/&gt;
 *     &lt;enumeration value="triangle"/&gt;
 *     &lt;enumeration value="tub-shaped"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Shape")
@XmlEnum
public enum Shape {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("airplane-shaped")
    AIRPLANE_SHAPED("airplane-shaped"),
    @XmlEnumValue("almond-shaped")
    ALMOND_SHAPED("almond-shaped"),
    @XmlEnumValue("apple-shaped")
    APPLE_SHAPED("apple-shaped"),
    @XmlEnumValue("barrel-shaped")
    BARREL_SHAPED("barrel-shaped"),
    @XmlEnumValue("blazon")
    BLAZON("blazon"),
    @XmlEnumValue("capsule-like")
    CAPSULE_LIKE("capsule-like"),
    @XmlEnumValue("car-shaped")
    CAR_SHAPED("car-shaped"),
    @XmlEnumValue("character")
    CHARACTER("character"),
    @XmlEnumValue("cream/ovule")
    CREAM_OVULE("cream/ovule"),
    @XmlEnumValue("cream")
    CREAM("cream"),
    @XmlEnumValue("cylindrical")
    CYLINDRICAL("cylindrical"),
    @XmlEnumValue("diamond-shaped")
    DIAMOND_SHAPED("diamond-shaped"),
    @XmlEnumValue("d-shaped")
    D_SHAPED("d-shaped"),
    @XmlEnumValue("elliptical")
    ELLIPTICAL("elliptical"),
    @XmlEnumValue("foam")
    FOAM("foam"),
    @XmlEnumValue("gel")
    GEL("gel"),
    @XmlEnumValue("granule")
    GRANULE("granule"),
    @XmlEnumValue("hexagon")
    HEXAGON("hexagon"),
    @XmlEnumValue("lotion")
    LOTION("lotion"),
    @XmlEnumValue("oblong")
    OBLONG("oblong"),
    @XmlEnumValue("octagonal")
    OCTAGONAL("octagonal"),
    @XmlEnumValue("ointment")
    OINTMENT("ointment"),
    @XmlEnumValue("oval")
    OVAL("oval"),
    @XmlEnumValue("ovule-like")
    OVULE_LIKE("ovule-like"),
    @XmlEnumValue("pellet")
    PELLET("pellet"),
    @XmlEnumValue("pentagonal")
    PENTAGONAL("pentagonal"),
    @XmlEnumValue("peanut-shaped")
    PEANUT_SHAPED("peanut-shaped"),
    @XmlEnumValue("powder")
    POWDER("powder"),
    @XmlEnumValue("rectangle")
    RECTANGLE("rectangle"),
    @XmlEnumValue("round")
    ROUND("round"),
    @XmlEnumValue("rod-shaped")
    ROD_SHAPED("rod-shaped"),
    @XmlEnumValue("roundRectangular")
    ROUND_RECTANGULAR("roundRectangular"),
    @XmlEnumValue("roundedSquare")
    ROUNDED_SQUARE("roundedSquare"),
    @XmlEnumValue("roundedTriangle")
    ROUNDED_TRIANGLE("roundedTriangle"),
    @XmlEnumValue("squaredCapsule-like")
    SQUARED_CAPSULE_LIKE("squaredCapsule-like"),
    @XmlEnumValue("shield")
    SHIELD("shield"),
    @XmlEnumValue("square")
    SQUARE("square"),
    @XmlEnumValue("squarePyramid")
    SQUARE_PYRAMID("squarePyramid"),
    @XmlEnumValue("tear-drop")
    TEAR_DROP("tear-drop"),
    @XmlEnumValue("torpedo-shaped")
    TORPEDO_SHAPED("torpedo-shaped"),
    @XmlEnumValue("train-shaped")
    TRAIN_SHAPED("train-shaped"),
    @XmlEnumValue("trapezoid")
    TRAPEZOID("trapezoid"),
    @XmlEnumValue("triangle")
    TRIANGLE("triangle"),
    @XmlEnumValue("tub-shaped")
    TUB_SHAPED("tub-shaped");
    private final String value;

    Shape(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Shape fromValue(String v) {
        for (Shape c: Shape.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
