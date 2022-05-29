package ihorko.work.speech_recognition.common;

import java.util.logging.Logger;

public class StringSearch {
    public static final int D = 256;

    private static final Logger LOGGER = Logger.getLogger(StringSearch.class.getName());

    public boolean search(String pat, String txt) {
        if (pat.length() > txt.length())
            return false;
        int numberForCalculatingHash = 10;

        int patternLength = pat.length();
        int textLength = txt.length();

        int hashValueForPattern = 0;
        int hashValueForText = 0;
        int numberForChangingHash = 1;

        // The value of numberForChangingHash would be "pow(d, patternLength-1)%numberForCalculatingHash"
        for (int i = 0; i < patternLength - 1; i++)
            numberForChangingHash = (numberForChangingHash * D) % numberForCalculatingHash;

        // hash for pattern and our first part of text
        for (int i = 0; i < patternLength; i++) {
            hashValueForPattern = (D * hashValueForPattern + pat.charAt(i)) % numberForCalculatingHash;
            hashValueForText = (D * hashValueForText + txt.charAt(i)) % numberForCalculatingHash;
        }
        int j;

        //find our pattern in text one by one
        for (int i = 0; i <= textLength - patternLength; i++) {

            if (hashValueForPattern == hashValueForText) {
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == patternLength) {
                    String messageForLogging = String.format("Pattern found at index %d", i);
                    LOGGER.info(() -> messageForLogging);
                    return true;
                }
            }

            //our calculating of polynomial hash
            if (i < textLength - patternLength) {
                hashValueForText = (D * (hashValueForText - txt.charAt(i) * numberForChangingHash) + txt.charAt(i + patternLength)) % numberForCalculatingHash;

                if (hashValueForText < 0)
                    hashValueForText = (hashValueForText + numberForCalculatingHash);
            }
        }
        return false;
    }
}
