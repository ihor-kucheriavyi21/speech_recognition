package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.RecognitionResult;
import ihorko.work.speech_recognition.common.StringSearch;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringService implements IStringService {

    private static final StringSearch STRING_SEARCH = new StringSearch();

    public RecognitionResult findCorrectAndWrongPartInExpectedText(String expectedResult, String result) {
        String testResult = (" " + result + " ").toLowerCase(Locale.ROOT);
        String[] values = expectedResult.toLowerCase(Locale.ROOT).split(" ");

        StringBuilder correctText = new StringBuilder();
        StringBuilder wrongText = new StringBuilder();

        for (String pattern : values) {
            if (STRING_SEARCH.search(" " + pattern + " ", testResult)) {
                correctText.append(pattern).append(" ");
                testResult = testResult.replaceFirst(pattern, "");
            } else {
                wrongText.append(pattern).append(" ");
            }
        }

        return new RecognitionResult(correctText.toString(), wrongText.toString(), result);
    }
}
