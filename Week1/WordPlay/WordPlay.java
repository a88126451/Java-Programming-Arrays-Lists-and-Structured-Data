
/**
 * Transform words from a file into another form, such as replacing vowels with an asterix.
 * 
 * @author Joyce Lin 
 * @version Mar 24, 2022
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "aeiouAEIOU";
        return vowel.indexOf(ch) != -1;
    }
    public String replaceVowels(String phrase, char ch) {
        StringBuilder input = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);
            if (isVowel(currentChar)) {
                input.setCharAt(i, ch);
            }
        }
        return input.toString();
    }
    public String emphasize(String phrase, char ch) {
        char chup, chlow;
        StringBuilder input = new StringBuilder(phrase);
        chup = Character.toUpperCase(ch);
        chlow = Character.toLowerCase(ch);
        
        for(int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);
            if (currentChar == chup || currentChar == chlow) {
                if (i % 2 == 0) {
                    input.setCharAt(i, '*');
                } else {
                    input.setCharAt(i, '+');
                }
            }
        }
        return input.toString();
    }
    
    public void test() {
        //System.out.println(isVowel('a'));
        //System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
