
/**
 * Write a description of class GladLibMap here.
 *
 * @author Jocye Lin
 * @version Apr 1, 2022
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWord;
    private ArrayList<String> usedCategory;
    
    private Random myRandom;
    
    private int totalReplaceWords;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWord = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        totalReplaceWords = 0;
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        usedWord = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        totalReplaceWords = 0;
    }
    
    private void initializeFromSource(String source) {
        String[] categoryList = {"adjective", "noun", "color", "country", "name", "animal", 
            "timeframe", "verb", "fruit"};
        for (String category : categoryList) {
            ArrayList<String> list  = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        else if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (!usedCategory.contains(label)) {
            usedCategory.add(label);
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWord.contains(sub) && sub != "**UNKNOWN**") {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWord.add(sub);
        totalReplaceWords++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int totalWords = 0;
        for (String key : myMap.keySet()) {
            totalWords += myMap.get(key).size();
        }
        return totalWords;
    }
    
    public int totalWordsConsidered() {
        int totalWords = 0;
        for (String key : myMap.keySet()) {
            if (usedCategory.contains(key)) {
                totalWords += myMap.get(key).size();
            }
        }
        return totalWords;    
    }
    
    public void makeStory(){
        usedWord.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal replaced words = " + totalReplaceWords);
        System.out.println("Total words in map = " + totalWordsInMap());
        System.out.println("Total words considered = " + totalWordsConsidered());
    }
    
}
