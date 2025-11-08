package ihorko.work.speech_recognition.db.dto;

import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.db.entity.Exercise;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class ExerciseContentDto {

    private UUID id;

    private String contentText;

    private String contentType;

    private Exercise exercise;

    private File audioFile;

    private File gifFile;
}
