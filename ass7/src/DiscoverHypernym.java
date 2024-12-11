// 216303990 Arbel Feldman

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class extracts hypernym-hyponyms relations from text files, using regular expressions.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class DiscoverHypernym {
    /**
     * @param map a map with String key and Integer value.
     * @return the map sorted by the value in descending order.
     */
    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        // Convert Map to List of Map's entries:
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(map.entrySet());

        // Sort list and providing a custom Comparator:
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // Go through the list and insert entries into an insertion order Map LinkedHashMap:
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /**
     * This method takes a line of text and a lemma and finds all the lemma's
     * hypernyms in the line that match the regEx pattern.
     *
     * @param hypernyms a map, the keys are hypernyms and the values are their count.
     * @param line      the line of text
     * @param lemma     the lemma
     * @param regEx     the regular expression that matches a pattern of hypernym-hyponym relation.
     */
    public static void findPattern(Map<String, Integer> hypernyms, String line, String lemma, RegEx regEx) {
        Pattern pattern = Pattern.compile(regEx.regEx());
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            // Check if the lemma is indeed the hyponym in the match:
            if (matcher.group(regEx.hyponymGroup()).contains(np(lemma))) {
                // Extract the hypernym from the match:
                String hypernym = matcher.group(regEx.hypernymGroup());
                if (hypernyms.containsKey(hypernym)) {
                    int value = hypernyms.get(hypernym);
                    hypernyms.replace(hypernym, value, value + 1);
                } else {
                    hypernyms.put(hypernym, 1);
                }
            }
        }

    }

    /**
     * Adds the np tag to a lemma.
     *
     * @param lemma the lemma
     * @return the tagged lemma
     */
    public static String np(String lemma) {
        return "<np>" + lemma + "</np>";
    }

    /**
     * Finds all hypernyms of a lemma in a line of text that match the existing patterns.
     *
     * @param hypernyms the map of hypernyms and their count.
     * @param line      the line of text
     * @param lemma     the lemma
     */
    public static void findHypernyms(Map<String, Integer> hypernyms, String line, String lemma) {
        findPattern(hypernyms, line, lemma, new NPsuchAsNP());
        findPattern(hypernyms, line, lemma, new SuchNPasNP());
        findPattern(hypernyms, line, lemma, new NPincludingNP());
        findPattern(hypernyms, line, lemma, new NPespeciallyNP());
        findPattern(hypernyms, line, lemma, new NPwhichIsNP());
    }

    /**
     * Print all hypernyms of a lemma in a list of files (corpus).
     *
     * @param corpus the corpus
     * @param lemma  the lemma
     */
    public static void printAllHypernyms(File[] corpus, String lemma) {
        Map<String, Integer> hypernyms = new TreeMap<>();

        for (File file : corpus) {
            try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    if (!line.contains(lemma)) {
                        continue;
                    }
                    findHypernyms(hypernyms, line, lemma);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

        hypernyms = sortMap(hypernyms);

        if (hypernyms.entrySet().size() == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }
        for (Map.Entry<String, Integer> hypernym : hypernyms.entrySet()) {
            System.out.println(hypernym.getKey() + ": (" + hypernym.getValue() + ")");
        }
    }

    /**
     * The main activates the whole system to find the hypernyms of a lemma in a corpus.
     *
     * @param args the path to the corpus and a lemma.
     */
    public static void main(String[] args) {
        File corpus = new File(args[0]);
        String lemma = args[1];
        File[] files;
        if (corpus.isDirectory()) {
            files = corpus.listFiles();
            if (files == null) {
                return;
            }
        } else {
            files = new File[] {corpus};
        }

        printAllHypernyms(files, lemma);
    }
}
