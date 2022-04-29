package ihorko.work.speech_recognition.common;

public class RabinaKarpa {
    // d is the number of characters in the input alphabet
    public final static int d = 256;

    /* pat -> pattern
        txt -> text
        q -> A prime number
    */
    public boolean search(String pat, String txt) {
        int q = 10;
        int patternLength = pat.length();
        int textLength = txt.length();
        int i, j;
        int hashValueForPattern = 0; // hash value for pattern
        int hashValueForText = 0; // hash value for txt
        int h = 1;

        // The value of h would be "pow(d, patternLength-1)%q"
        for (i = 0; i < patternLength - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < patternLength; i++) {
            hashValueForPattern = (d * hashValueForPattern + pat.charAt(i)) % q;
            hashValueForText = (d * hashValueForText + txt.charAt(i)) % q;
        }

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
                    System.out.println("Pattern found at index " + i);
                    return true;
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            //hash полініомінального обертання
            if (i < textLength - patternLength) {
                hashValueForText = (d * (hashValueForText - txt.charAt(i) * h) + txt.charAt(i + patternLength)) % q;

                // We might get negative value of hashValueForText, converting it
                // to positive
                if (hashValueForText < 0)
                    hashValueForText = (hashValueForText + q);
            }
        }
        return false;
    }
}
