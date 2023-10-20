package ihorko.work.speech_recognition.db.dto;

import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.db.entity.Sound;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class SoundContentDto {

    private UUID id;

    private String contentText;

    private String contentType;

    private Sound sound;

    private File audioFile;

    private File gifFile;
}
