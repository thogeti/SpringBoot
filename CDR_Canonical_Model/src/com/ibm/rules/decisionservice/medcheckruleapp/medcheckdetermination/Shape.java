
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Shape.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Shape">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="airplane-shaped"/>
 *     &lt;enumeration value="almond-shaped"/>
 *     &lt;enumeration value="apple-shaped"/>
 *     &lt;enumeration value="barrel-shaped"/>
 *     &lt;enumeration value="blazon"/>
 *     &lt;enumeration value="capsule-like"/>
 *     &lt;enumeration value="car-shaped"/>
 *     &lt;enumeration value="character"/>
 *     &lt;enumeration value="cream/ovule"/>
 *     &lt;enumeration value="cream"/>
 *     &lt;enumeration value="cylindrical"/>
 *     &lt;enumeration value="diamond-shaped"/>
 *     &lt;enumeration value="d-shaped"/>
 *     &lt;enumeration value="elliptical"/>
 *     &lt;enumeration value="foam"/>
 *     &lt;enumeration value="gel"/>
 *     &lt;enumeration value="granule"/>
 *     &lt;enumeration value="hexagon"/>
 *     &lt;enumeration value="lotion"/>
 *     &lt;enumeration value="oblong"/>
 *     &lt;enumeration value="octagonal"/>
 *     &lt;enumeration value="ointment"/>
 *     &lt;enumeration value="oval"/>
 *     &lt;enumeration value="ovule-like"/>
 *     &lt;enumeration value="pellet"/>
 *     &lt;enumeration value="pentagonal"/>
 *     &lt;enumeration value="peanut-shaped"/>
 *     &lt;enumeration value="powder"/>
 *     &lt;enumeration value="rectangle"/>
 *     &lt;enumeration value="round"/>
 *     &lt;enumeration value="rod-shaped"/>
 *     &lt;enumeration value="roundRectangular"/>
 *     &lt;enumeration value="roundedSquare"/>
 *     &lt;enumeration value="roundedTriangle"/>
 *     &lt;enumeration value="squaredCapsule-like"/>
 *     &lt;enumeration value="shield"/>
 *     &lt;enumeration value="square"/>
 *     &lt;enumeration value="squarePyramid"/>
 *     &lt;enumeration value="tear-drop"/>
 *     &lt;enumeration value="torpedo-shaped"/>
 *     &lt;enumeration value="train-shaped"/>
 *     &lt;enumeration value="trapezoid"/>
 *     &lt;enumeration value="triangle"/>
 *     &lt;enumeration value="tub-shaped"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
