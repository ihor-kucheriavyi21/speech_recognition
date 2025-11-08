package ihorko.work.speech_recognition.converter;

import ihorko.work.speech_recognition.db.dto.ExerciseContentDto;
import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import org.springframework.stereotype.Component;

@Component
public class ExerciseContentConverter {

    public ExerciseContentDto convert(ExerciseContent ExerciseContent) {
        ExerciseContentDto ExerciseContentDto = new ExerciseContentDto();
        ExerciseContentDto.setContentText(ExerciseContent.getContentText());
        ExerciseContentDto.setExercise(ExerciseContent.getExercise());
        ExerciseContentDto.setId(ExerciseContent.getId());
        ExerciseContentDto.setContentType(ExerciseContent.getTypeContent());
        ExerciseContentDto.setAudioFile(ExerciseContent.getFiles()
                .stream()
                .filter(content -> content.getFileType().contains("audio"))
                .findFirst().orElse(null));
        ExerciseContentDto.setGifFile(ExerciseContent.getFiles()
                .stream()
                .filter(content -> content.getFileType().contains("image"))
                .findFirst().orElse(null));
        return ExerciseContentDto;
    }

}
