package ihorko.work.db_learning.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    public static void main(String[] args) {
        System.out.println(new GeminiService().sendPrompt("Що таке ЄР діаграма"));
    }

    public String sendPrompt(String taskIssue){

        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        String.format("У мене виникають складнощі із завданням по базам даних - '%s'. Будь ласка поясни його мені в межах" +
                                "одного абзацу та в межах 100 слів",taskIssue),
                        null);

        System.out.println(response.text());
        return response.text();
    }
}
