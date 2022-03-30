
/**
 * Determine the word that occurs the most often in a file.
 *
 * @author Joyce Lin
 * @version Mar, 30, 2022
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    // instance variables
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    /**
     * Constructor for objects of class WordFrequencies
     */
    public WordFrequencies()
    {
        // initialise instance variables
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    
    public int findMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (max < myFreqs.get(i)) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        int index = findMax();
        //for (int i = 0; i < myWords.size(); i++) {
        //    System.out.println(myWords.get(i) + "\t" + myFreqs.get(i)); 
        //}
        System.out.println("The word that occurs most often and its count are: " +
        myWords.get(index) +" " + myFreqs.get(index));
    }
}
