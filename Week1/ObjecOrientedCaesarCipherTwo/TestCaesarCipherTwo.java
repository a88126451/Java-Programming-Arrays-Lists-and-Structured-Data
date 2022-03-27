
/**
 * Test examples that use the CaesarCipherTwo class, including writing a method 
 * that will automatically decrypt an encrypted file by determining the two keys 
 * that were used to encrypt it. 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo
{
    // return a new String that is every other character from message starting with the start position.
    private String halfOfString (String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i += 2) {
            halfString += message.charAt(i);
        }
        return halfString;
    }
    // get an array of the letter frequencies in String s
    private int[] countLetters (String s) {
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
    private int maxIndex (int[] values) {
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
    
    private int getKey (String s) {
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
    
    public String breakCaesarCipher (String input) {
        String oddString = halfOfString(input, 0);
        String evenString = halfOfString(input, 1);
        int key1 = getKey(oddString);
        int key2 = getKey(evenString);       
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(input);
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        
        System.out.println(message);
        String encrypted = cc.encrypt(message);
        System.out.println("Encryption");
        System.out.println("==========");
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decryption");
        System.out.println("==========");
        System.out.println(decrypted);

        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Break Caesar Cipher");
        System.out.println("===================");
        System.out.println(decrypted);     
    }
    
    public void test(){
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);
        String result = cc.decrypt(message);
        System.out.println(result);
    }
}
