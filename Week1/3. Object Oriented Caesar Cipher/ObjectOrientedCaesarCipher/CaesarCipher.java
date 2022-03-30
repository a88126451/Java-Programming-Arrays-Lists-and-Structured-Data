
/**
 * put together the CaesarCipher class from the lesson and add a decrypt 
 * method to decrypt with the same key.
 *
 * @author Joyce Lin
 * @version Mar 26, 2022
 */
public class CaesarCipher
{
    // private fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    /**
     * Constructor for objects of class CeaserCipher
     */
    public CaesarCipher(int key)
    {
        // initialise instance variables
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        // initiate a stringbuilder to store the encrypted message
        StringBuilder encrypted = new StringBuilder(input);
        // itertate over the whole stringbuilder
        for (int i = 0; i < encrypted.length(); i++) {
            // get current character
            char currChar = encrypted.charAt(i);
            // check whether the current character is uppercase or lowercase
            if (Character.isLowerCase(currChar)) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = alphabet.toUpperCase().indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }   
            
        }
        return encrypted.toString();
    }
    // thies method returns a String that is the encrypted String decrypted using the key associated with this CaesarCipher object.
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey); 
        String message = cc.encrypt(input);
        return message;
    }
}

