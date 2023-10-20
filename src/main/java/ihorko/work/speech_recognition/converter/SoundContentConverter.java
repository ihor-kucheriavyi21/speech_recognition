package ihorko.work.speech_recognition.converter;

import ihorko.work.speech_recognition.db.dto.SoundContentDto;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.springframework.stereotype.Component;

@Component
public class SoundContentConverter {

    public SoundContentDto convert(SoundContent soundContent) {
        SoundContentDto soundContentDto = new SoundContentDto();
        soundContentDto.setContentText(soundContent.getContentText());
        soundContentDto.setSound(soundContent.getSound());
        soundContentDto.setId(soundContent.getId());
        soundContentDto.setContentType(soundContent.getTypeContent());
        soundContentDto.setAudioFile(soundContent.getFiles()
                .stream()
                .filter(content -> content.getFileType().contains("audio"))
                .findFirst().orElse(null));
        soundContentDto.setGifFile(soundContent.getFiles()
                .stream()
                .filter(content -> content.getFileType().contains("image"))
                .findFirst().orElse(null));
        return soundContentDto;
    }

}
