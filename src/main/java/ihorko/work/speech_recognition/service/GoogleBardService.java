package ihorko.work.speech_recognition.service;

import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import ihorko.work.speech_recognition.common.Language;

public class GoogleBardService {

    public String getAnswerFromBard(String text, Language language) {
        GoogleBardClient client = new GoogleBardClient("agjaz9183at_ZCbL0A9FjJXL5wYNxobR7fgHbzPjyMVLx2NhN9QZgWvIPiYfk1_Vefz_0w.;sidts-CjEB3e41heZGRqkFokqPheHY7fzuACzqR84rTf9T2mM7gfGnr-j0ordK_9IY3NN-X6tIEAA");
        String query;
        if (language == Language.ENGLISH) {
            query = String.format("I have a problem with the pronunciation of words %s. Please teach me how to pronounce" +
                    " that within one paragraph and don't use more than 100 words", text);
        } else {
            query = String.format("У мене проблема з вимовою слів %s. Будь ласка, навчіть мене вимовляти це в одному абзаці та не використовуйте більше 100 слів", text);
        }

        Answer ask = client.ask(query);
        String response = ask.getChosenAnswer();

        System.out.println(response);
        return response;
    }
}
