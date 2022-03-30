
/**
 * put together the CaesarCipherTwo class that encrypts a message 
 * with two keys.
 *
 * @author Joyce Lin
 * @version Mar 27, 2022
 */

public class CaesarCipherTwo
{
    // instance variables 
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    /**
     * Constructor for objects of class CaesarCipherTwo
     */
    public CaesarCipherTwo (int key1, int key2) {
        // initialise instance variables
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(Character.toLowerCase(currChar));
            if(idx != -1){
                if ((i %2 == 0) && Character.isLowerCase(currChar)){
                    char newCharUp = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newCharUp);
                } 
                else if ((i %2 == 0) && Character.isUpperCase(currChar)){
                    char newCharLow = shiftedAlphabet1.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newCharLow);
                }  
                else if ((i %2 != 0) && Character.isLowerCase(currChar)){
                    char newCharUp = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newCharUp);
                } 
                else {
                    char newCharLow = shiftedAlphabet2.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newCharLow);
                } 
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
   
}
