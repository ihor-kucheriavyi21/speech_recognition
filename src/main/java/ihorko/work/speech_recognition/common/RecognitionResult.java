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

}
