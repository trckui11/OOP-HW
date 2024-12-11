// 216303990 Arbel Feldman

/**
 * This interface describes regular expressions.
 */
public interface RegEx {
    /**
     * RegEx string pattern.
     *
     * @return the string
     */
    String regEx();

    /**
     * the group of the Hypernym in the match.
     *
     * @return the group's number
     */
    int hypernymGroup();

    /**
     * the group of the Hyponyms in the match.
     *
     * @return the group's number
     */
    int hyponymGroup();
}
