package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestRecognition {

    @Autowired
    AudioRecognitionService audioRecognitionService;

    @Test
    void testEnglishRecognition() {
        String translatedString = audioRecognitionService.recognizeAudioRecord(
                "src/test/resources/englishRecord.wav", Language.ENGLISH);

        //verify if text in audio was recognized
        Assertions.assertTrue(translatedString.contains("error"),
                String.format("String doesn't contain expected text - in relation"
                        + "\n But contains [%s]", translatedString));
        Assertions.assertTrue(translatedString.contains("audio recording could not load content"),
                String.format("String doesn't contains expected text -audio recording could not load content"
                        + "\n But contains [%s]", translatedString));

    }

    @Test
    void testUkraineRecognition() {
        String translatedString = audioRecognitionService.recognizeAudioRecord(
                "src/test/resources/ukraineRecord.wav", Language.UKRAINIAN);

        //verify if text in audio was recognized
        Assertions.assertTrue(translatedString
                .equalsIgnoreCase("інтернаціональний паляниця філіжанка швидкісний"),
                "Actual result is " + translatedString);
    }
}
