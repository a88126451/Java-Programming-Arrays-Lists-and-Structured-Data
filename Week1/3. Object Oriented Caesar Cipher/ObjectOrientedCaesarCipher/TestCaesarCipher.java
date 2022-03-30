
/**
 * Test examples that use the CaesarCipher class, including writing a method 
 * that will automatically decrypt an encrypted file by determining the key 
 * and then decrypting with that key.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher
{

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
    
    private String breakCaesarCipher (String input) {
        int[] freq = countLetters(input);
        int maxIndex = maxIndex(freq);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        } 
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);  
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
 
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

}
