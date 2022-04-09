package ihorko.work.speech_recognition.common;

import java.util.Locale;

/**
 * https://www.geeksforgeeks.org/real-time-optimized-kmp-algorithm-for-pattern-searching/
 * https://stackoverflow.com/questions/5873935/how-to-optimize-knuth-morris-pratt-string-matching-algorithm
 */
public class KMPSearch {
    public int findWordByPatternInText(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                return i - j;
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return -1;
    }

    void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                } else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function
    public static void main(String args[]) {

        String s1 = "which is that from Sian very common way however various constraints are added for example one might want to match".toLowerCase(Locale.ROOT);
        String s2 = "which is the fifth word from the end very commonly, however, various constraints are added. For example, one might want to match";
        String[] twoVals = s2.split("\\ ");

        for (String twoVal : twoVals) {
            System.out.println("Index" + new KMPSearch().findWordByPatternInText(twoVal, s1));
        }

    }
}
