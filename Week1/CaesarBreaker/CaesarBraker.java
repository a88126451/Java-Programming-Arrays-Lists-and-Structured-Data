
/**
 *  decrypt a message that was encrypted with two keys, using ideas from the 
 *  single key decryption method and the encryption with two keys method 
 *  from the program.
 * 
 * @author Joyce Lin 
 * @version Mar 25, 2022
 */
import edu.duke.*;

public class CaesarBraker {
    // return a new String that is every other character from message starting with the start position.
    public String halfOfString (String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i += 2) {
            halfString += message.charAt(i);
        }
        return halfString;
    }
    // get an array of the letter frequencies in String s
    public int[] countLetters (String s) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            int idx = alph.indexOf(ch);
            if (idx != -1) {
                counts[idx]++;
            }
        }
        return counts;
    }
    // calculate the index of the largest letter frequency
    public int maxIndex (int[] values) {
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
    
    public int getKey (String s) {
        int[] counts = countLetters(s);
        int maxIndex = maxIndex(counts);
        int key;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        } else {
            key = maxIndex - 4;
        }
        return key;
    }
    
    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    // Determine the two keys used to encrypt the message, prints the two keys, and then returns the decrypted 
    // String with those two keys.
    public String decryptTwoKeys (String encrypted) {
        StringBuilder decrypted= new StringBuilder(encrypted);
        String halfOne = halfOfString(encrypted, 0);
        String halfTwo = halfOfString(encrypted, 1);
        int keyOne = getKey(halfOne);
        int keyTwo = getKey(halfTwo);
        
        String decryptOne = decrypt(halfOne, keyOne);
        String decryptTwo = decrypt(halfTwo, keyTwo);
        
        for (int i = 0; i < decryptOne.length(); i++) {
            decrypted.setCharAt((2 * i), decryptOne.charAt(i));
        }
        for (int i = 0; i < decryptTwo.length(); i++) {
            decrypted.setCharAt((2 * i) + 1, decryptTwo.charAt(i));
        }
        
        System.out.println("The first key is "+ keyOne + "\n"+"The second Key is: "+ keyTwo); 
        return decrypted.toString();   

    }
    public void testDecrypt() {
        int key = 23;
        FileResource fr = new FileResource("wordsLotsOfEs.txt");
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, key);
        String decrypted = cc.encrypt(encrypted, 26 - key);
        String encrypted2 = cc.encryptTwoKeys(message, 23, 2);
        System.out.println("Key is "+ key + "\n" + encrypted);
        System.out.println("Key is "+ key + "\n" + decrypted);
        System.out.println("Key is "+ key + "\n" + encrypted2);
    }
    
    public void test() {
       //FileResource resource = new FileResource();
       //String message = resource.asString();
       String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
       System.out.println(message);
       //System.out.println("half of String starts with 0 " + halfOfString(message, 0));
       //System.out.println("half of String starts with 1 " + halfOfString(message, 1));
       String result = decryptTwoKeys (message);
       System.out.println(result);
    }

}
