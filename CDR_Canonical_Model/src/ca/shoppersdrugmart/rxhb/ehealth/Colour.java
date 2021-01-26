
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Colour.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Colour"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="amber"/&gt;
 *     &lt;enumeration value="amber-light"/&gt;
 *     &lt;enumeration value="aqua"/&gt;
 *     &lt;enumeration value="beige"/&gt;
 *     &lt;enumeration value="black"/&gt;
 *     &lt;enumeration value="blue"/&gt;
 *     &lt;enumeration value="blue-grey"/&gt;
 *     &lt;enumeration value="blue-light"/&gt;
 *     &lt;enumeration value="bluish-green"/&gt;
 *     &lt;enumeration value="brown"/&gt;
 *     &lt;enumeration value="brown-light"/&gt;
 *     &lt;enumeration value="brownish-orange"/&gt;
 *     &lt;enumeration value="brownish-pink"/&gt;
 *     &lt;enumeration value="brownish-red"/&gt;
 *     &lt;enumeration value="brownish-red-lt"/&gt;
 *     &lt;enumeration value="brownish-yellow"/&gt;
 *     &lt;enumeration value="buff"/&gt;
 *     &lt;enumeration value="caramel"/&gt;
 *     &lt;enumeration value="clear"/&gt;
 *     &lt;enumeration value="coral"/&gt;
 *     &lt;enumeration value="coral-light"/&gt;
 *     &lt;enumeration value="cream"/&gt;
 *     &lt;enumeration value="creamyWhite"/&gt;
 *     &lt;enumeration value="darkBrownish-yellow"/&gt;
 *     &lt;enumeration value="darkMaroon"/&gt;
 *     &lt;enumeration value="darkPink"/&gt;
 *     &lt;enumeration value="dark-green"/&gt;
 *     &lt;enumeration value="dark-orange"/&gt;
 *     &lt;enumeration value="dark-yellow"/&gt;
 *     &lt;enumeration value="gold"/&gt;
 *     &lt;enumeration value="green"/&gt;
 *     &lt;enumeration value="greenOrWhite"/&gt;
 *     &lt;enumeration value="green-light"/&gt;
 *     &lt;enumeration value="greenish-blue"/&gt;
 *     &lt;enumeration value="greenish-white"/&gt;
 *     &lt;enumeration value="grey"/&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *     &lt;enumeration value="grey-light"/&gt;
 *     &lt;enumeration value="greyish-beige"/&gt;
 *     &lt;enumeration value="greyish-lightGreen"/&gt;
 *     &lt;enumeration value="greyish-red"/&gt;
 *     &lt;enumeration value="ivory"/&gt;
 *     &lt;enumeration value="lavender"/&gt;
 *     &lt;enumeration value="lavender-orange"/&gt;
 *     &lt;enumeration value="maroon"/&gt;
 *     &lt;enumeration value="mauve"/&gt;
 *     &lt;enumeration value="multi-coloured"/&gt;
 *     &lt;enumeration value="natural"/&gt;
 *     &lt;enumeration value="olive"/&gt;
 *     &lt;enumeration value="orange"/&gt;
 *     &lt;enumeration value="orange-dark"/&gt;
 *     &lt;enumeration value="orange-light"/&gt;
 *     &lt;enumeration value="orangish-yellow"/&gt;
 *     &lt;enumeration value="organge-red"/&gt;
 *     &lt;enumeration value="peach"/&gt;
 *     &lt;enumeration value="peach-light"/&gt;
 *     &lt;enumeration value="pink"/&gt;
 *     &lt;enumeration value="pink-light"/&gt;
 *     &lt;enumeration value="pinkish-brown"/&gt;
 *     &lt;enumeration value="pinkish-orange"/&gt;
 *     &lt;enumeration value="purple"/&gt;
 *     &lt;enumeration value="purplish-pink"/&gt;
 *     &lt;enumeration value="red"/&gt;
 *     &lt;enumeration value="reddish-brown"/&gt;
 *     &lt;enumeration value="reddish-brown-light"/&gt;
 *     &lt;enumeration value="reddish-orange"/&gt;
 *     &lt;enumeration value="reddish-yellow"/&gt;
 *     &lt;enumeration value="salmon"/&gt;
 *     &lt;enumeration value="scarlet"/&gt;
 *     &lt;enumeration value="teal(blue-green)"/&gt;
 *     &lt;enumeration value="turquiose-light"/&gt;
 *     &lt;enumeration value="turquoise-light"/&gt;
 *     &lt;enumeration value="white"/&gt;
 *     &lt;enumeration value="white(creamyWhite)"/&gt;
 *     &lt;enumeration value="white(off-white)"/&gt;
 *     &lt;enumeration value="white(off-white)ToYellow"/&gt;
 *     &lt;enumeration value="yellow"/&gt;
 *     &lt;enumeration value="yellow-cream"/&gt;
 *     &lt;enumeration value="yellow-dark"/&gt;
 *     &lt;enumeration value="yellow-darkOrBrownish-dark"/&gt;
 *     &lt;enumeration value="yellow-light"/&gt;
 *     &lt;enumeration value="yellow-to-orange"/&gt;
 *     &lt;enumeration value="yellowish-brown"/&gt;
 *     &lt;enumeration value="yellowish-ochre"/&gt;
 *     &lt;enumeration value="yellowish-pink"/&gt;
 *     &lt;enumeration value="yellowish-tan"/&gt;
 *     &lt;enumeration value="yellowish-wheat"/&gt;
 *     &lt;enumeration value="yellowish-white"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
