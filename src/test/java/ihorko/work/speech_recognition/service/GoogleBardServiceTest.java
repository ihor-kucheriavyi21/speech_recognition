package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class GoogleBardServiceTest {

    private static final Logger LOGGER = Logger.getLogger(GoogleBardServiceTest.class.getName());

    @Autowired
    GoogleBardService googleBardService;

    @Test
    void testSimpleAnswerFromGoogleBardService() {
        String answerFromBard = googleBardService.getAnswerFromBard("What is it today?");
        LOGGER.info("Answer from BARD: " + answerFromBard);
        Assertions.assertFalse(answerFromBard.isEmpty(), " Answer from BARD shouldn't be empty");
    }

    @Test
    void testQueryFromGoogleBardService() {
        String wordForTesting = "schedule";
        String answerFromBard = googleBardService.buildQueryAboutPronunciationAndAskBard(wordForTesting, Language.ENGLISH);
        LOGGER.info("Answer from BARD: " + answerFromBard);
        Assertions.assertTrue(answerFromBard.contains(wordForTesting), " Answer from BARD should contains word schedule");
    }
}
