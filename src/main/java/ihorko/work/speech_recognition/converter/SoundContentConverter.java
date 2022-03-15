package ihorko.work.speech_recognition.converter;

import ihorko.work.speech_recognition.db.dto.SoundContentDto;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SoundContentConverter {

    public SoundContentDto convert(SoundContent soundContent) {
        SoundContentDto soundContentDto = new SoundContentDto();
        soundContentDto.setContentText(soundContent.getContentText());
        soundContentDto.setSound(soundContent.getSound());
        soundContentDto.setId(soundContent.getId());
        soundContentDto.setAudioFile(soundContent.getDbFiles()
                .stream()
                .filter(content -> content.getFileType().contains("audio"))
                .findFirst().orElse(null));
        soundContentDto.setGifFile(soundContent.getDbFiles()
                .stream()
                .filter(content -> content.getFileType().contains("image"))
                .findFirst().orElse(null));
        return soundContentDto;
    }

    public List<SoundContentDto> convert(List<SoundContent> soundContents) {
        List<SoundContentDto> listToConvert = new LinkedList<>();
        soundContents.forEach(element -> listToConvert.add(convert(element)));
        return listToConvert;
    }
}
