package ihorko.work.speech_recognition.db.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ExerciseContentDto {

    private UUID id;

    private String questionText;

    private String typeContent;

    private List<String> answers;

    private Integer correctAnswerIndex;

    private UUID exerciseId;

    private String exerciseName;

    private UUID imageFileId;

}
