package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class GoogleBardServiceTest {

    private static final Logger LOGGER = Logger.getLogger(GoogleBardServiceTest.class.getName());

    @Autowired
    GeminiService geminiService;

    @Test
    public void test() {
        String answer = geminiService.sendPrompt("Поясни що таке ER-діаграма простими словами");
        System.out.println(answer);
    }


}
