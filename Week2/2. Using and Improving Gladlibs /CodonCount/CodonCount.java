
/**
 * Find out how many times each codon occurs in a strand of DNA based on 
 * reading frames.
 *
 * @author Joyce Lin
 * @version Apr 1, 2022
 */
import edu.duke.*;
import java.util.HashMap;

public class CodonCount
{
    // instance variables 
    private HashMap<String, Integer> counts;

    /**
     * Constructor for objects of class CodonCount
     */
    public CodonCount()
    {
        // initialise instance variables
        counts = new HashMap<String, Integer>();
    }

    public void buildCodonMap (int start, String dna) {
        counts.clear();
        for (int i = start; i < (dna.length() - 2); i += 3) {
            String codon = dna.substring(i, i + 3);
            if (counts.containsKey(codon)) {
                counts.put(codon, counts.get(codon) + 1);
            } else {
                counts.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon () {
        int max = Integer.MIN_VALUE;
        String mostCommonCodon = "";
        for (String key : counts.keySet()) {
            int currCount = counts.get(key);
            if (currCount > max) {
                max = currCount;
                mostCommonCodon = key;
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts (int start, int end) {
        for (String key : counts.keySet()) {
            int currCount = counts.get(key);
            if ( currCount >= start && currCount <= end) {
                System.out.println(key + " " + currCount);
            }
        }
    }
    
    public void test() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        int start = 0; // start = 1; start = 2;
        buildCodonMap(start, dna);
        System.out.println("Reading frame starting with " + start + " results in " 
        + counts.size() + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + 
        " with count " + counts.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 7 and 7 inclusive are:");
        printCodonCounts(7, 7);
    }
}
