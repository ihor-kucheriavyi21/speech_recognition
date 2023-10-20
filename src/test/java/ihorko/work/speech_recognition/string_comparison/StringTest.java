package ihorko.work.speech_recognition.string_comparison;

import ihorko.work.speech_recognition.common.RecognitionResult;
import ihorko.work.speech_recognition.service.StringService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringTest {

    private final StringService stringService = new StringService();
    private final String firstTestString = "this is first text example";
    private final String secondTestString = "this is next text example";

    @Test
    void testCheckAlgorithmForFindingSubTextInText() {
        RecognitionResult result = stringService
                .findCorrectAndWrongPartInExpectedText(firstTestString, secondTestString);
        Assertions.assertTrue(result.getCorrectText().trim().equalsIgnoreCase("this is text example"));
    }

    @Test
    void testCheckAlgorithmForFindingWrongText() {
        RecognitionResult result = stringService
                .findCorrectAndWrongPartInExpectedText(firstTestString, secondTestString);
        Assertions.assertEquals("first", result.getWrongText().trim());
    }

    @Test
    void testIfObjectContainsFullText(){
        RecognitionResult result = stringService
                .findCorrectAndWrongPartInExpectedText(firstTestString, secondTestString);
        Assertions.assertTrue(result.getFullText().equalsIgnoreCase("this is next text example"));
    }
}
