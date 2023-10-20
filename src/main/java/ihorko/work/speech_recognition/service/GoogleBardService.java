package ihorko.work.speech_recognition.service;

import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import ihorko.work.speech_recognition.common.Language;
import org.springframework.stereotype.Service;

@Service
public class GoogleBardService {

    public String getAnswerFromBard(String text) {
        //__Secure-1PSID;__Secure-1PSIDTS
        GoogleBardClient client = new GoogleBardClient("bgjaz3UlV_agKHh2dcfTozDQ-m5Gu5ZgsWS5qQ7VUAKdhTL1iVqsdKEAA");
        Answer ask = client.ask(text);
        return ask.getChosenAnswer();
    }

    public String buildQueryAboutPronunciationAndAskBard(String text, Language language) {
        String query;
        if (language == Language.ENGLISH) {
            query = String.format("I have a problem with the pronunciation of words %s. Please teach me how to pronounce" +
                    " that within one paragraph and don't use more than 100 words", text);
        } else {
            query = String.format("У мене проблема з вимовою слів %s. Будь ласка, навчіть мене вимовляти це в одному абзаці та не використовуйте більше 100 слів", text);
        }
        return getAnswerFromBard(query);
    }
}
