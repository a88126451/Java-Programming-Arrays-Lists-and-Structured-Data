import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice += message.charAt(i);
        }
        return slice;
    }
    // find the shift for each index in the key.
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String sliceMessage = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliceMessage);
        }
        return key;
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> dictionary = new HashMap<String, HashSet<String>>();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        FileResource dictResource;
        for (String language : languages) {
            dictResource = new FileResource("dictionaries/" + language);
            dictionary.put(language, readDictionary(dictResource));
            //System.out.println("Finished Reading " + language + " Dictionary");
        }
        
        FileResource fr = new FileResource("data/secretmessage4.txt");
        String encrypted = fr.asString();
        breakForAllLangs(encrypted, dictionary);

    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> wordList = new HashSet<String>();
        for (String word : fr.lines()) {
            word = word.toLowerCase();
            wordList.add(word);
        }
        return wordList;
    }
    
    public int countWords (String message, HashSet<String> dictionary) {
        String[] wordArray = message.split("\\W+");
        int validWords = 0;
        for (String word : wordArray) {
            if (dictionary.contains(word.toLowerCase())) {
                validWords ++;
            }
        }
        return validWords;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        char mostCommon = mostCommonCharIn(dictionary);
        String result = "";
        int maxCount = 0;
        int[] finalKey = new int[100];
        for (int i = 1; i <= 100; i++) {
            // try all key lengths from 1 to 100
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            // decrypt the message 
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            // count how many of the “words” in it are real words in English
            int currentCount = countWords(decrypted, dictionary);
            if (currentCount > maxCount) {
                maxCount = currentCount;
                result = decrypted;
                finalKey = key;
            }
        }
        //System.out.println("validWords: " + maxCount + " keylength: " + finalKey.length);
        //System.out.println("key: " + Arrays.toString(finalKey));
        return result;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        HashMap<Character,Integer> charCounts = new HashMap<Character,Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if (!charCounts.containsKey(currChar)) {
                    charCounts.put(currChar, 1);
                } else {
                    charCounts.put(currChar, charCounts.get(currChar) + 1);
                }
            }
        }
        char mostCommon = '\0';
        int maxCount = 0;
        for (char key : charCounts.keySet()) {
            if (charCounts.get(key) > maxCount) {
                maxCount = charCounts.get(key);
                mostCommon = key;
            }
        }
        return mostCommon;
    }
    
    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String language = "";
        String decrypted = "";
        // iterate over every language
        for (String key : languages.keySet()) {
            HashSet<String> langDict = languages.get(key);
            System.out.println("Language: " + key);
            String currDecrypted = breakForLanguage(encrypted, langDict);
            int currCount = countWords(currDecrypted, langDict);
            if (currCount > maxCount) {
                maxCount = currCount;
                language = key;
                decrypted = currDecrypted;
            }
        }
        System.out.println("Detected Language: " + language);
        System.out.println("Decrypted message: ");
        System.out.println(decrypted);
    }
}
