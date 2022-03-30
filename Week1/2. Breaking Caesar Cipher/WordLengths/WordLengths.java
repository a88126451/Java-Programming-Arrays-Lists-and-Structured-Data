
/**
 * Figure out the most common word length of words from a file..
 * 
 * @author Joyce Lin 
 * @version Mar 25, 2022
 */
import edu.duke.*;

public class WordLengths {
    public int[] countWordLengths (FileResource resource, int[] counts) {
        int size = counts.length;
        for (String word : resource.words()) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char currChar = word.charAt(i);
                if ((i == 0) || (i == word.length() - 1)){
                    if (!Character.isLetter(currChar)) length--;
                }
            }
            
            if (length < size) {
                counts[length] += 1;
            } else if (length > size) {
                counts[size] += 1;
            }
        }
        return counts;
    }
    public int indexOfMax (int[] values) {
        int max = 0;
        int idx = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max){
                max = values[i];
                idx = i;
            }
        }
        return idx;
    }
    public void testCountWordLengths () {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        int result = indexOfMax(counts);
        System.out.println("Most common words is at position: " + result);
        for (int i = 0; i < counts.length; i++){
            System.out.println("counts " + i + "\t" + counts[i]);
        }
        
    }
}
