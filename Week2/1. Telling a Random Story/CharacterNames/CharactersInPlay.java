
/**
 * Determine the characters in one of Shakespeare’s plays that 
 * have the most speaking parts.
 *
 * @author Joyce Lin
 * @version Mar 30, 2022
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay
{
    // instance variables 
    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    /**
     * Constructor for objects of class CharacterNames
     */
    public CharactersInPlay()
    {
        // initialise instance variables
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update(String person) {
        person = person.trim(); // eliminate the space
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int index = line.indexOf(".");
            if (index != -1) {
                String characterName = line.substring(0, index);
                update(characterName);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < names.size(); i++) {
            int count = counts.get(i);
            if (count >= num1 && count <= num2) {               
                System.out.println(names.get(i));
            }
        }
    }
    
    public int findMax() {
        int max = counts.get(0);
        int maxIndex = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (max < counts.get(i)) {
                max = counts.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void tester() {
        findAllCharacters();
        final int MIN_PART = 3;
        //String format = "%-30s%s%n";
        int index = findMax();
        System.out.println("Main Characters:");
        System.out.println("The character with the most speaking parts is: " +
        names.get(index) +" " + counts.get(index));
        //for (int i = 0; i < names.size(); i++) {
            //int count = counts.get(i);
            //if (count >= MIN_PART) {               
                //System.out.printf(format, names.get(i),  count);
            //}
        //}
        
        System.out.println("Character with num of plays between 10-15: ");
        charactersWithNumParts(10,15);
    }
    
    
}
