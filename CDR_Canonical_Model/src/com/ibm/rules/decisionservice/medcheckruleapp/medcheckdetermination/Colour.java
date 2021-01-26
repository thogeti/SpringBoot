
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Colour.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Colour">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="amber"/>
 *     &lt;enumeration value="amber-light"/>
 *     &lt;enumeration value="aqua"/>
 *     &lt;enumeration value="beige"/>
 *     &lt;enumeration value="black"/>
 *     &lt;enumeration value="blue"/>
 *     &lt;enumeration value="blue-grey"/>
 *     &lt;enumeration value="blue-light"/>
 *     &lt;enumeration value="bluish-green"/>
 *     &lt;enumeration value="brown"/>
 *     &lt;enumeration value="brown-light"/>
 *     &lt;enumeration value="brownish-orange"/>
 *     &lt;enumeration value="brownish-pink"/>
 *     &lt;enumeration value="brownish-red"/>
 *     &lt;enumeration value="brownish-red-lt"/>
 *     &lt;enumeration value="brownish-yellow"/>
 *     &lt;enumeration value="buff"/>
 *     &lt;enumeration value="caramel"/>
 *     &lt;enumeration value="clear"/>
 *     &lt;enumeration value="coral"/>
 *     &lt;enumeration value="coral-light"/>
 *     &lt;enumeration value="cream"/>
 *     &lt;enumeration value="creamyWhite"/>
 *     &lt;enumeration value="darkBrownish-yellow"/>
 *     &lt;enumeration value="darkMaroon"/>
 *     &lt;enumeration value="darkPink"/>
 *     &lt;enumeration value="dark-green"/>
 *     &lt;enumeration value="dark-orange"/>
 *     &lt;enumeration value="dark-yellow"/>
 *     &lt;enumeration value="gold"/>
 *     &lt;enumeration value="green"/>
 *     &lt;enumeration value="greenOrWhite"/>
 *     &lt;enumeration value="green-light"/>
 *     &lt;enumeration value="greenish-blue"/>
 *     &lt;enumeration value="greenish-white"/>
 *     &lt;enumeration value="grey"/>
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="grey-light"/>
 *     &lt;enumeration value="greyish-beige"/>
 *     &lt;enumeration value="greyish-lightGreen"/>
 *     &lt;enumeration value="greyish-red"/>
 *     &lt;enumeration value="ivory"/>
 *     &lt;enumeration value="lavender"/>
 *     &lt;enumeration value="lavender-orange"/>
 *     &lt;enumeration value="maroon"/>
 *     &lt;enumeration value="mauve"/>
 *     &lt;enumeration value="multi-coloured"/>
 *     &lt;enumeration value="natural"/>
 *     &lt;enumeration value="olive"/>
 *     &lt;enumeration value="orange"/>
 *     &lt;enumeration value="orange-dark"/>
 *     &lt;enumeration value="orange-light"/>
 *     &lt;enumeration value="orangish-yellow"/>
 *     &lt;enumeration value="organge-red"/>
 *     &lt;enumeration value="peach"/>
 *     &lt;enumeration value="peach-light"/>
 *     &lt;enumeration value="pink"/>
 *     &lt;enumeration value="pink-light"/>
 *     &lt;enumeration value="pinkish-brown"/>
 *     &lt;enumeration value="pinkish-orange"/>
 *     &lt;enumeration value="purple"/>
 *     &lt;enumeration value="purplish-pink"/>
 *     &lt;enumeration value="red"/>
 *     &lt;enumeration value="reddish-brown"/>
 *     &lt;enumeration value="reddish-brown-light"/>
 *     &lt;enumeration value="reddish-orange"/>
 *     &lt;enumeration value="reddish-yellow"/>
 *     &lt;enumeration value="salmon"/>
 *     &lt;enumeration value="scarlet"/>
 *     &lt;enumeration value="teal(blue-green)"/>
 *     &lt;enumeration value="turquiose-light"/>
 *     &lt;enumeration value="turquoise-light"/>
 *     &lt;enumeration value="white"/>
 *     &lt;enumeration value="white(creamyWhite)"/>
 *     &lt;enumeration value="white(off-white)"/>
 *     &lt;enumeration value="white(off-white)ToYellow"/>
 *     &lt;enumeration value="yellow"/>
 *     &lt;enumeration value="yellow-cream"/>
 *     &lt;enumeration value="yellow-dark"/>
 *     &lt;enumeration value="yellow-darkOrBrownish-dark"/>
 *     &lt;enumeration value="yellow-light"/>
 *     &lt;enumeration value="yellow-to-orange"/>
 *     &lt;enumeration value="yellowish-brown"/>
 *     &lt;enumeration value="yellowish-ochre"/>
 *     &lt;enumeration value="yellowish-pink"/>
 *     &lt;enumeration value="yellowish-tan"/>
 *     &lt;enumeration value="yellowish-wheat"/>
 *     &lt;enumeration value="yellowish-white"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Colour")
@XmlEnum
public enum Colour {

    @XmlEnumValue("amber")
    AMBER("amber"),
    @XmlEnumValue("amber-light")
    AMBER_LIGHT("amber-light"),
    @XmlEnumValue("aqua")
    AQUA("aqua"),
    @XmlEnumValue("beige")
    BEIGE("beige"),
    @XmlEnumValue("black")
    BLACK("black"),
    @XmlEnumValue("blue")
    BLUE("blue"),
    @XmlEnumValue("blue-grey")
    BLUE_GREY("blue-grey"),
    @XmlEnumValue("blue-light")
    BLUE_LIGHT("blue-light"),
    @XmlEnumValue("bluish-green")
    BLUISH_GREEN("bluish-green"),
    @XmlEnumValue("brown")
    BROWN("brown"),
    @XmlEnumValue("brown-light")
    BROWN_LIGHT("brown-light"),
    @XmlEnumValue("brownish-orange")
    BROWNISH_ORANGE("brownish-orange"),
    @XmlEnumValue("brownish-pink")
    BROWNISH_PINK("brownish-pink"),
    @XmlEnumValue("brownish-red")
    BROWNISH_RED("brownish-red"),
    @XmlEnumValue("brownish-red-lt")
    BROWNISH_RED_LT("brownish-red-lt"),
    @XmlEnumValue("brownish-yellow")
    BROWNISH_YELLOW("brownish-yellow"),
    @XmlEnumValue("buff")
    BUFF("buff"),
    @XmlEnumValue("caramel")
    CARAMEL("caramel"),
    @XmlEnumValue("clear")
    CLEAR("clear"),
    @XmlEnumValue("coral")
    CORAL("coral"),
    @XmlEnumValue("coral-light")
    CORAL_LIGHT("coral-light"),
    @XmlEnumValue("cream")
    CREAM("cream"),
    @XmlEnumValue("creamyWhite")
    CREAMY_WHITE("creamyWhite"),
    @XmlEnumValue("darkBrownish-yellow")
    DARK_BROWNISH_YELLOW("darkBrownish-yellow"),
    @XmlEnumValue("darkMaroon")
    DARK_MAROON("darkMaroon"),
    @XmlEnumValue("darkPink")
    DARK_PINK("darkPink"),
    @XmlEnumValue("dark-green")
    DARK_GREEN("dark-green"),
    @XmlEnumValue("dark-orange")
    DARK_ORANGE("dark-orange"),
    @XmlEnumValue("dark-yellow")
    DARK_YELLOW("dark-yellow"),
    @XmlEnumValue("gold")
    GOLD("gold"),
    @XmlEnumValue("green")
    GREEN("green"),
    @XmlEnumValue("greenOrWhite")
    GREEN_OR_WHITE("greenOrWhite"),
    @XmlEnumValue("green-light")
    GREEN_LIGHT("green-light"),
    @XmlEnumValue("greenish-blue")
    GREENISH_BLUE("greenish-blue"),
    @XmlEnumValue("greenish-white")
    GREENISH_WHITE("greenish-white"),
    @XmlEnumValue("grey")
    GREY("grey"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("grey-light")
    GREY_LIGHT("grey-light"),
    @XmlEnumValue("greyish-beige")
    GREYISH_BEIGE("greyish-beige"),
    @XmlEnumValue("greyish-lightGreen")
    GREYISH_LIGHT_GREEN("greyish-lightGreen"),
    @XmlEnumValue("greyish-red")
    GREYISH_RED("greyish-red"),
    @XmlEnumValue("ivory")
    IVORY("ivory"),
    @XmlEnumValue("lavender")
    LAVENDER("lavender"),
    @XmlEnumValue("lavender-orange")
    LAVENDER_ORANGE("lavender-orange"),
    @XmlEnumValue("maroon")
    MAROON("maroon"),
    @XmlEnumValue("mauve")
    MAUVE("mauve"),
    @XmlEnumValue("multi-coloured")
    MULTI_COLOURED("multi-coloured"),
    @XmlEnumValue("natural")
    NATURAL("natural"),
    @XmlEnumValue("olive")
    OLIVE("olive"),
    @XmlEnumValue("orange")
    ORANGE("orange"),
    @XmlEnumValue("orange-dark")
    ORANGE_DARK("orange-dark"),
    @XmlEnumValue("orange-light")
    ORANGE_LIGHT("orange-light"),
    @XmlEnumValue("orangish-yellow")
    ORANGISH_YELLOW("orangish-yellow"),
    @XmlEnumValue("organge-red")
    ORGANGE_RED("organge-red"),
    @XmlEnumValue("peach")
    PEACH("peach"),
    @XmlEnumValue("peach-light")
    PEACH_LIGHT("peach-light"),
    @XmlEnumValue("pink")
    PINK("pink"),
    @XmlEnumValue("pink-light")
    PINK_LIGHT("pink-light"),
    @XmlEnumValue("pinkish-brown")
    PINKISH_BROWN("pinkish-brown"),
    @XmlEnumValue("pinkish-orange")
    PINKISH_ORANGE("pinkish-orange"),
    @XmlEnumValue("purple")
    PURPLE("purple"),
    @XmlEnumValue("purplish-pink")
    PURPLISH_PINK("purplish-pink"),
    @XmlEnumValue("red")
    RED("red"),
    @XmlEnumValue("reddish-brown")
    REDDISH_BROWN("reddish-brown"),
    @XmlEnumValue("reddish-brown-light")
    REDDISH_BROWN_LIGHT("reddish-brown-light"),
    @XmlEnumValue("reddish-orange")
    REDDISH_ORANGE("reddish-orange"),
    @XmlEnumValue("reddish-yellow")
    REDDISH_YELLOW("reddish-yellow"),
    @XmlEnumValue("salmon")
    SALMON("salmon"),
    @XmlEnumValue("scarlet")
    SCARLET("scarlet"),
    @XmlEnumValue("teal(blue-green)")
    TEAL_BLUE_GREEN("teal(blue-green)"),
    @XmlEnumValue("turquiose-light")
    TURQUIOSE_LIGHT("turquiose-light"),
    @XmlEnumValue("turquoise-light")
    TURQUOISE_LIGHT("turquoise-light"),
    @XmlEnumValue("white")
    WHITE("white"),
    @XmlEnumValue("white(creamyWhite)")
    WHITE_CREAMY_WHITE("white(creamyWhite)"),
    @XmlEnumValue("white(off-white)")
    WHITE_OFF_WHITE("white(off-white)"),
    @XmlEnumValue("white(off-white)ToYellow")
    WHITE_OFF_WHITE_TO_YELLOW("white(off-white)ToYellow"),
    @XmlEnumValue("yellow")
    YELLOW("yellow"),
    @XmlEnumValue("yellow-cream")
    YELLOW_CREAM("yellow-cream"),
    @XmlEnumValue("yellow-dark")
    YELLOW_DARK("yellow-dark"),
    @XmlEnumValue("yellow-darkOrBrownish-dark")
    YELLOW_DARK_OR_BROWNISH_DARK("yellow-darkOrBrownish-dark"),
    @XmlEnumValue("yellow-light")
    YELLOW_LIGHT("yellow-light"),
    @XmlEnumValue("yellow-to-orange")
    YELLOW_TO_ORANGE("yellow-to-orange"),
    @XmlEnumValue("yellowish-brown")
    YELLOWISH_BROWN("yellowish-brown"),
    @XmlEnumValue("yellowish-ochre")
    YELLOWISH_OCHRE("yellowish-ochre"),
    @XmlEnumValue("yellowish-pink")
    YELLOWISH_PINK("yellowish-pink"),
    @XmlEnumValue("yellowish-tan")
    YELLOWISH_TAN("yellowish-tan"),
    @XmlEnumValue("yellowish-wheat")
    YELLOWISH_WHEAT("yellowish-wheat"),
    @XmlEnumValue("yellowish-white")
    YELLOWISH_WHITE("yellowish-white");
    private final String value;

    Colour(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Colour fromValue(String v) {
        for (Colour c: Colour.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
