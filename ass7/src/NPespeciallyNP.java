// 216303990 Arbel Feldman

/**
 * The pattern "NP {,} especially NP {, NP, ..., {and|or} NP}".
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class NPespeciallyNP implements RegEx {
    public static final String REGEX =
       "<np>([^<]+)</np>( ,|) especially (<np>([^<]+)</np>(( , <np>([^<]+)</np>)*(( ,|) (and|or) <np>([^<]+)</np>|)|))";
    public static final int HYPERNYM_GROUP = 1;
    public static final int HYPONYM_GROUP = 3;

    /**
     * Constructor.
     */
    public NPespeciallyNP() { }

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
