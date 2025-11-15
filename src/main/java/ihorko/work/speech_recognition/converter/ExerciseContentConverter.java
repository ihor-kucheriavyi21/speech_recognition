package ihorko.work.speech_recognition.converter;

import ihorko.work.speech_recognition.db.dto.ExerciseContentDto;
import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import org.springframework.stereotype.Component;

@Component
public class ExerciseContentConverter {

    public ExerciseContentDto convert(ExerciseContent entity) {

        ExerciseContentDto dto = new ExerciseContentDto();

        dto.setId(entity.getId());
        dto.setQuestionText(entity.getQuestionText());
        dto.setTypeContent(entity.getTypeContent());
        dto.setAnswers(entity.getAnswers());
        dto.setCorrectAnswerIndex(entity.getCorrectAnswerIndex());
        if (entity.getFiles() != null && !entity.getFiles().isEmpty()) {
            dto.setImageFileId(entity.getFiles().get(0).getId());
        }

        if (entity.getExercise() != null) {
            dto.setExerciseId(entity.getExercise().getId());
            dto.setExerciseName(entity.getExercise().getName());
        }

        return dto;
    }
}
