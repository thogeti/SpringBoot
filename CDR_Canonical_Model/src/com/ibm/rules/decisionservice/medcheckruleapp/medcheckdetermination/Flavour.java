
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Flavour.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Flavour">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="anise-mint"/>
 *     &lt;enumeration value="apple"/>
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="apricot"/>
 *     &lt;enumeration value="banana"/>
 *     &lt;enumeration value="bubble-gum"/>
 *     &lt;enumeration value="berry"/>
 *     &lt;enumeration value="black-currant"/>
 *     &lt;enumeration value="blueberry"/>
 *     &lt;enumeration value="butterpecan"/>
 *     &lt;enumeration value="butterscotch"/>
 *     &lt;enumeration value="caféAuLait"/>
 *     &lt;enumeration value="caramel"/>
 *     &lt;enumeration value="cherry-banana"/>
 *     &lt;enumeration value="chocolate-cocoa"/>
 *     &lt;enumeration value="chocolate-mint"/>
 *     &lt;enumeration value="cherry-frutti"/>
 *     &lt;enumeration value="chocolate"/>
 *     &lt;enumeration value="cherry-menthol"/>
 *     &lt;enumeration value="cherry-mint"/>
 *     &lt;enumeration value="cherry"/>
 *     &lt;enumeration value="cherry-vanilla"/>
 *     &lt;enumeration value="cinnamon"/>
 *     &lt;enumeration value="citrus-berry"/>
 *     &lt;enumeration value="citrus"/>
 *     &lt;enumeration value="citrus-vanilla"/>
 *     &lt;enumeration value="coconut"/>
 *     &lt;enumeration value="cocoa"/>
 *     &lt;enumeration value="cranberry"/>
 *     &lt;enumeration value="eggnog"/>
 *     &lt;enumeration value="fruit"/>
 *     &lt;enumeration value="fruit-mint"/>
 *     &lt;enumeration value="grape-banana"/>
 *     &lt;enumeration value="grape"/>
 *     &lt;enumeration value="honey"/>
 *     &lt;enumeration value="honey-lemon"/>
 *     &lt;enumeration value="ice-mint"/>
 *     &lt;enumeration value="lemon-ginger"/>
 *     &lt;enumeration value="lemon-lime"/>
 *     &lt;enumeration value="lemon-mint"/>
 *     &lt;enumeration value="lemon"/>
 *     &lt;enumeration value="licorice"/>
 *     &lt;enumeration value="malt"/>
 *     &lt;enumeration value="melon"/>
 *     &lt;enumeration value="mint"/>
 *     &lt;enumeration value="menthol"/>
 *     &lt;enumeration value="mochaccino"/>
 *     &lt;enumeration value="mint-peach"/>
 *     &lt;enumeration value="orange-banana"/>
 *     &lt;enumeration value="orangeCream"/>
 *     &lt;enumeration value="orange"/>
 *     &lt;enumeration value="orange-vanilla"/>
 *     &lt;enumeration value="peach"/>
 *     &lt;enumeration value="peppermint"/>
 *     &lt;enumeration value="pineapple"/>
 *     &lt;enumeration value="raspberry-orange"/>
 *     &lt;enumeration value="raspberry"/>
 *     &lt;enumeration value="spearmint"/>
 *     &lt;enumeration value="strawberry-banana"/>
 *     &lt;enumeration value="strawberry"/>
 *     &lt;enumeration value="strawberry-watermelon"/>
 *     &lt;enumeration value="tangerine"/>
 *     &lt;enumeration value="tropicalFruit"/>
 *     &lt;enumeration value="tutti-frutti"/>
 *     &lt;enumeration value="unflavoured"/>
 *     &lt;enumeration value="vanilla"/>
 *     &lt;enumeration value="vanilla-fruit"/>
 *     &lt;enumeration value="vanilla-peppermint"/>
 *     &lt;enumeration value="watermelon"/>
 *     &lt;enumeration value="wild-berry"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
