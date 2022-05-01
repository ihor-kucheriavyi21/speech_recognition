package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.SoundContent;
import ihorko.work.speech_recognition.repository.SoundContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SoundContentService {

    private final SoundContentRepository soundContentRepository;

    @Autowired
    public SoundContentService(SoundContentRepository soundContentRepository) {
        this.soundContentRepository = soundContentRepository;
    }

    public void save(SoundContent soundContent) {
        soundContentRepository.save(soundContent);
    }

    public List<SoundContent> findAll() {
        return soundContentRepository.findAll();
    }

    public SoundContent findById(UUID uuid) {
        return soundContentRepository.findById(uuid);
    }
}
