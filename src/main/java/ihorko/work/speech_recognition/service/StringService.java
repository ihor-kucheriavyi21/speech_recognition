package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.KMPSearch;
import ihorko.work.speech_recognition.common.RabinaKarpa;
import ihorko.work.speech_recognition.common.RecognitionResult;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringService {

    private static final KMPSearch KMP_SEARCH = new KMPSearch();

    private static final RabinaKarpa RABINA_KARPA = new RabinaKarpa();

    public RecognitionResult findCorrectAndWrongPartInExpectedText(String expectedResult, String result) {

        result = result.toLowerCase(Locale.ROOT);
        String[] values = expectedResult.toLowerCase(Locale.ROOT).split("\\ ");

        StringBuilder correctText = new StringBuilder();
        StringBuilder wrongText = new StringBuilder();
        //todo improve using stream
        for (String pattern : values) {
            if (RABINA_KARPA.search(pattern, result)) {
                correctText.append(pattern).append(" ");
                result = result.replaceFirst(pattern, "");
            } else {
                wrongText.append(pattern).append(" ");
            }
        }
        return new RecognitionResult(correctText.toString(), wrongText.toString());
    }
}
