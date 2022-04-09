package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.KMPSearch;
import ihorko.work.speech_recognition.common.RecognitionResult;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringService {

    private static final KMPSearch KMP_SEARCH = new KMPSearch();

    public RecognitionResult findCorrectAndWrongPronunciation(String result, String expectedResult) {

        expectedResult = expectedResult.toLowerCase(Locale.ROOT);
        String[] values = result.toLowerCase(Locale.ROOT).split("\\ ");

        StringBuilder correctText = new StringBuilder();
        StringBuilder wrongText = new StringBuilder();
        //todo improve using stream
        for (String pattern : values) {
            if (KMP_SEARCH.findWordByPatternInText(pattern, expectedResult) > -1) {
                correctText.append(pattern).append(" ");
                expectedResult = expectedResult.replaceFirst(pattern, "");
            } else {
                wrongText.append(pattern).append(" ");
            }
        }
        return new RecognitionResult(correctText.toString(), wrongText.toString());
    }
}
