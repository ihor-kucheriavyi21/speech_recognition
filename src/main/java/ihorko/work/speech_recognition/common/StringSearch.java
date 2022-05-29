package ihorko.work.speech_recognition.common;

import java.util.logging.Logger;

public class StringSearch {
    // d is the number of characters in the input alphabet
    public static final int D = 256;

    /* pat -> pattern
        txt -> text
        q -> A prime number
    */
    private static final Logger LOGGER = Logger.getLogger(StringSearch.class.getName());

    public boolean search(String pat, String txt) {
        if (pat.length() > txt.length())
            return false;
        int q = 10;
        int patternLength = pat.length();
        int textLength = txt.length();
        int i;
        int hashValueForPattern = 0;
        int hashValueForText = 0;
        int h = 1;

        // The value of h would be "pow(d, patternLength-1)%q"
        for (i = 0; i < patternLength - 1; i++)
            h = (h * D) % q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < patternLength; i++) {
            hashValueForPattern = (D * hashValueForPattern + pat.charAt(i)) % q;
            hashValueForText = (D * hashValueForText + txt.charAt(i)) % q;
        }
        int j;

        // Slide the pattern over text one by one
        for (i = 0; i <= textLength - patternLength; i++) {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters one by one
            if (hashValueForPattern == hashValueForText) {
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if hashValueForPattern == hashValueForText and pat[0...patternLength-1] = txt[i, i+1, ...i+patternLength-1]
                if (j == patternLength) {
                    LOGGER.info("Pattern found at index " + i);
                    return true;
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            //hash полініомінального обертання
            if (i < textLength - patternLength) {
                hashValueForText = (D * (hashValueForText - txt.charAt(i) * h) + txt.charAt(i + patternLength)) % q;

                // We might get negative value of hashValueForText, converting it
                // to positive
                if (hashValueForText < 0)
                    hashValueForText = (hashValueForText + q);
            }
        }
        return false;
    }

    public boolean searchUsingDefaultHash(String pat, String txt) {
        if (pat.length() > txt.length())
            return false;

        int patternLength = pat.length();
        int textLength = txt.length();
        int i;
        int hashValueForPattern = pat.hashCode();
        int hashValueForText = 0;

        // Calculate the hash value of pattern and first
        // window of text\
        int j;

        // Slide the pattern over text one by one
        for (i = 0; i <= textLength - patternLength; i++) {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters one by one
            if (i < textLength - patternLength) {
                hashValueForText = txt.substring(i, pat.length()+i).hashCode();
            }

            if (hashValueForPattern == hashValueForText) {
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if hashValueForPattern == hashValueForText and pat[0...patternLength-1] = txt[i, i+1, ...i+patternLength-1]
                if (j == patternLength) {
                    LOGGER.info("Pattern found at index " + i);
                    return true;
                }
            }
        }
        return false;
    }
}
