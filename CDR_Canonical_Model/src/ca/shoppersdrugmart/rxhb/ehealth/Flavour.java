
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Flavour.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Flavour"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="anise-mint"/&gt;
 *     &lt;enumeration value="apple"/&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *     &lt;enumeration value="apricot"/&gt;
 *     &lt;enumeration value="banana"/&gt;
 *     &lt;enumeration value="bubble-gum"/&gt;
 *     &lt;enumeration value="berry"/&gt;
 *     &lt;enumeration value="black-currant"/&gt;
 *     &lt;enumeration value="blueberry"/&gt;
 *     &lt;enumeration value="butterpecan"/&gt;
 *     &lt;enumeration value="butterscotch"/&gt;
 *     &lt;enumeration value="caféAuLait"/&gt;
 *     &lt;enumeration value="caramel"/&gt;
 *     &lt;enumeration value="cherry-banana"/&gt;
 *     &lt;enumeration value="chocolate-cocoa"/&gt;
 *     &lt;enumeration value="chocolate-mint"/&gt;
 *     &lt;enumeration value="cherry-frutti"/&gt;
 *     &lt;enumeration value="chocolate"/&gt;
 *     &lt;enumeration value="cherry-menthol"/&gt;
 *     &lt;enumeration value="cherry-mint"/&gt;
 *     &lt;enumeration value="cherry"/&gt;
 *     &lt;enumeration value="cherry-vanilla"/&gt;
 *     &lt;enumeration value="cinnamon"/&gt;
 *     &lt;enumeration value="citrus-berry"/&gt;
 *     &lt;enumeration value="citrus"/&gt;
 *     &lt;enumeration value="citrus-vanilla"/&gt;
 *     &lt;enumeration value="coconut"/&gt;
 *     &lt;enumeration value="cocoa"/&gt;
 *     &lt;enumeration value="cranberry"/&gt;
 *     &lt;enumeration value="eggnog"/&gt;
 *     &lt;enumeration value="fruit"/&gt;
 *     &lt;enumeration value="fruit-mint"/&gt;
 *     &lt;enumeration value="grape-banana"/&gt;
 *     &lt;enumeration value="grape"/&gt;
 *     &lt;enumeration value="honey"/&gt;
 *     &lt;enumeration value="honey-lemon"/&gt;
 *     &lt;enumeration value="ice-mint"/&gt;
 *     &lt;enumeration value="lemon-ginger"/&gt;
 *     &lt;enumeration value="lemon-lime"/&gt;
 *     &lt;enumeration value="lemon-mint"/&gt;
 *     &lt;enumeration value="lemon"/&gt;
 *     &lt;enumeration value="licorice"/&gt;
 *     &lt;enumeration value="malt"/&gt;
 *     &lt;enumeration value="melon"/&gt;
 *     &lt;enumeration value="mint"/&gt;
 *     &lt;enumeration value="menthol"/&gt;
 *     &lt;enumeration value="mochaccino"/&gt;
 *     &lt;enumeration value="mint-peach"/&gt;
 *     &lt;enumeration value="orange-banana"/&gt;
 *     &lt;enumeration value="orangeCream"/&gt;
 *     &lt;enumeration value="orange"/&gt;
 *     &lt;enumeration value="orange-vanilla"/&gt;
 *     &lt;enumeration value="peach"/&gt;
 *     &lt;enumeration value="peppermint"/&gt;
 *     &lt;enumeration value="pineapple"/&gt;
 *     &lt;enumeration value="raspberry-orange"/&gt;
 *     &lt;enumeration value="raspberry"/&gt;
 *     &lt;enumeration value="spearmint"/&gt;
 *     &lt;enumeration value="strawberry-banana"/&gt;
 *     &lt;enumeration value="strawberry"/&gt;
 *     &lt;enumeration value="strawberry-watermelon"/&gt;
 *     &lt;enumeration value="tangerine"/&gt;
 *     &lt;enumeration value="tropicalFruit"/&gt;
 *     &lt;enumeration value="tutti-frutti"/&gt;
 *     &lt;enumeration value="unflavoured"/&gt;
 *     &lt;enumeration value="vanilla"/&gt;
 *     &lt;enumeration value="vanilla-fruit"/&gt;
 *     &lt;enumeration value="vanilla-peppermint"/&gt;
 *     &lt;enumeration value="watermelon"/&gt;
 *     &lt;enumeration value="wild-berry"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Flavour")
@XmlEnum
public enum Flavour {

    @XmlEnumValue("anise-mint")
    ANISE_MINT("anise-mint"),
    @XmlEnumValue("apple")
    APPLE("apple"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("apricot")
    APRICOT("apricot"),
    @XmlEnumValue("banana")
    BANANA("banana"),
    @XmlEnumValue("bubble-gum")
    BUBBLE_GUM("bubble-gum"),
    @XmlEnumValue("berry")
    BERRY("berry"),
    @XmlEnumValue("black-currant")
    BLACK_CURRANT("black-currant"),
    @XmlEnumValue("blueberry")
    BLUEBERRY("blueberry"),
    @XmlEnumValue("butterpecan")
    BUTTERPECAN("butterpecan"),
    @XmlEnumValue("butterscotch")
    BUTTERSCOTCH("butterscotch"),
    @XmlEnumValue("caf\u00e9AuLait")
    CAFÉ_AU_LAIT("caf\u00e9AuLait"),
    @XmlEnumValue("caramel")
    CARAMEL("caramel"),
    @XmlEnumValue("cherry-banana")
    CHERRY_BANANA("cherry-banana"),
    @XmlEnumValue("chocolate-cocoa")
    CHOCOLATE_COCOA("chocolate-cocoa"),
    @XmlEnumValue("chocolate-mint")
    CHOCOLATE_MINT("chocolate-mint"),
    @XmlEnumValue("cherry-frutti")
    CHERRY_FRUTTI("cherry-frutti"),
    @XmlEnumValue("chocolate")
    CHOCOLATE("chocolate"),
    @XmlEnumValue("cherry-menthol")
    CHERRY_MENTHOL("cherry-menthol"),
    @XmlEnumValue("cherry-mint")
    CHERRY_MINT("cherry-mint"),
    @XmlEnumValue("cherry")
    CHERRY("cherry"),
    @XmlEnumValue("cherry-vanilla")
    CHERRY_VANILLA("cherry-vanilla"),
    @XmlEnumValue("cinnamon")
    CINNAMON("cinnamon"),
    @XmlEnumValue("citrus-berry")
    CITRUS_BERRY("citrus-berry"),
    @XmlEnumValue("citrus")
    CITRUS("citrus"),
    @XmlEnumValue("citrus-vanilla")
    CITRUS_VANILLA("citrus-vanilla"),
    @XmlEnumValue("coconut")
    COCONUT("coconut"),
    @XmlEnumValue("cocoa")
    COCOA("cocoa"),
    @XmlEnumValue("cranberry")
    CRANBERRY("cranberry"),
    @XmlEnumValue("eggnog")
    EGGNOG("eggnog"),
    @XmlEnumValue("fruit")
    FRUIT("fruit"),
    @XmlEnumValue("fruit-mint")
    FRUIT_MINT("fruit-mint"),
    @XmlEnumValue("grape-banana")
    GRAPE_BANANA("grape-banana"),
    @XmlEnumValue("grape")
    GRAPE("grape"),
    @XmlEnumValue("honey")
    HONEY("honey"),
    @XmlEnumValue("honey-lemon")
    HONEY_LEMON("honey-lemon"),
    @XmlEnumValue("ice-mint")
    ICE_MINT("ice-mint"),
    @XmlEnumValue("lemon-ginger")
    LEMON_GINGER("lemon-ginger"),
    @XmlEnumValue("lemon-lime")
    LEMON_LIME("lemon-lime"),
    @XmlEnumValue("lemon-mint")
    LEMON_MINT("lemon-mint"),
    @XmlEnumValue("lemon")
    LEMON("lemon"),
    @XmlEnumValue("licorice")
    LICORICE("licorice"),
    @XmlEnumValue("malt")
    MALT("malt"),
    @XmlEnumValue("melon")
    MELON("melon"),
    @XmlEnumValue("mint")
    MINT("mint"),
    @XmlEnumValue("menthol")
    MENTHOL("menthol"),
    @XmlEnumValue("mochaccino")
    MOCHACCINO("mochaccino"),
    @XmlEnumValue("mint-peach")
    MINT_PEACH("mint-peach"),
    @XmlEnumValue("orange-banana")
    ORANGE_BANANA("orange-banana"),
    @XmlEnumValue("orangeCream")
    ORANGE_CREAM("orangeCream"),
    @XmlEnumValue("orange")
    ORANGE("orange"),
    @XmlEnumValue("orange-vanilla")
    ORANGE_VANILLA("orange-vanilla"),
    @XmlEnumValue("peach")
    PEACH("peach"),
    @XmlEnumValue("peppermint")
    PEPPERMINT("peppermint"),
    @XmlEnumValue("pineapple")
    PINEAPPLE("pineapple"),
    @XmlEnumValue("raspberry-orange")
    RASPBERRY_ORANGE("raspberry-orange"),
    @XmlEnumValue("raspberry")
    RASPBERRY("raspberry"),
    @XmlEnumValue("spearmint")
    SPEARMINT("spearmint"),
    @XmlEnumValue("strawberry-banana")
    STRAWBERRY_BANANA("strawberry-banana"),
    @XmlEnumValue("strawberry")
    STRAWBERRY("strawberry"),
    @XmlEnumValue("strawberry-watermelon")
    STRAWBERRY_WATERMELON("strawberry-watermelon"),
    @XmlEnumValue("tangerine")
    TANGERINE("tangerine"),
    @XmlEnumValue("tropicalFruit")
    TROPICAL_FRUIT("tropicalFruit"),
    @XmlEnumValue("tutti-frutti")
    TUTTI_FRUTTI("tutti-frutti"),
    @XmlEnumValue("unflavoured")
    UNFLAVOURED("unflavoured"),
    @XmlEnumValue("vanilla")
    VANILLA("vanilla"),
    @XmlEnumValue("vanilla-fruit")
    VANILLA_FRUIT("vanilla-fruit"),
    @XmlEnumValue("vanilla-peppermint")
    VANILLA_PEPPERMINT("vanilla-peppermint"),
    @XmlEnumValue("watermelon")
    WATERMELON("watermelon"),
    @XmlEnumValue("wild-berry")
    WILD_BERRY("wild-berry");
    private final String value;

    Flavour(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Flavour fromValue(String v) {
        for (Flavour c: Flavour.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
