import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));    
            //If currChar is in the alphabet
            if(idx != -1){
                if (Character.isUpperCase(currChar)){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newCharUp = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newCharUp);
                } 
                else {
                    char newCharLow = shiftedAlphabet.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newCharLow);
                } 
            }
            
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
                if ((i %2 == 0) && Character.isUpperCase(currChar)){
                    char newCharUp = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newCharUp);
                } 
                else if ((i %2 == 0) && Character.isLowerCase(currChar)){
                    char newCharLow = shiftedAlphabet1.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newCharLow);
                }  
                else if ((i %2 != 0) && Character.isUpperCase(currChar)){
                    char newCharUp = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newCharUp);
                } 
                else {
                    char newCharLow = shiftedAlphabet2.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newCharLow);
                } 
            }
        }
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("key is " + key + "\n" + decrypted);
    }
    
    public void test() {
        //String text = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //System.out.println(encrypt(text,15));
        String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(encryptTwoKeys(text, 21, 8));
        //System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }
}

