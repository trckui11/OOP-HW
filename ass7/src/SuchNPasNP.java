// 216303990 Arbel Feldman

/**
 * The pattern "such NP as NP {, NP, ..., {and|or} NP}".
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class SuchNPasNP implements RegEx {
    public static final String REGEX =
            "such <np>([^<]+)</np> as (<np>([^<]+)</np>(( , <np>([^<]+)</np>)*(( ,|) (and|or) <np>([^<]+)</np>|)|))";
    public static final int HYPERNYM_GROUP = 1;
    public static final int HYPONYM_GROUP = 2;

    /**
     * Constructor.
     */
    public SuchNPasNP() { }

    @Override
    public String regEx() {
        return REGEX;
    }

    @Override
    public int hypernymGroup() {
        return HYPERNYM_GROUP;
    }

    @Override
    public int hyponymGroup() {
        return HYPONYM_GROUP;
    }
}
