package ihorko.work.speech_recognition.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class RecognitionResult {
    private String correctText;
    private String wrongText;
    private String fullText;
    private String userAssistantText;

    public RecognitionResult(String correctText, String wrongText, String fullText) {
        this.correctText = correctText;
        this.wrongText = wrongText;
        this.fullText = fullText;
        this.userAssistantText = "";
    }
}
