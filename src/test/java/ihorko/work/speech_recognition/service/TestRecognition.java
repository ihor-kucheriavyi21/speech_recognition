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
    void testEnglishRecognition(){
        String translatedString = audioRecognitionService.recognizeAudioRecord(
                "src/test/resources/englishRecord.wav", Language.ENGLISH);

        //verify if text in audio was recognized
        Assertions.assertTrue(translatedString.contains("in relation"),
                "String doesn't contains expected text - in relation");
        Assertions.assertTrue(translatedString.contains("audio recording could not load content"),
                "String doesn't contains expected text -audio recording could not load content");
    }

    @Test
    void testUkraineRecognition(){
        String translatedString = audioRecognitionService.recognizeAudioRecord(
                "src/test/resources/ukraineRecord.wav", Language.UKRAINIAN);

        //verify if text in audio was recognized
        Assertions.assertTrue(translatedString
                .equalsIgnoreCase("інтернаціональний паляниця філіжанка швидкісний"));
    }
}
