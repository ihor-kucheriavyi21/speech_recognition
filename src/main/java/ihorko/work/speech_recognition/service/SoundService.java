package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.repository.SoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SoundService {

    private final SoundRepository soundRepository;

    @Autowired
    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public void save(Sound sound) {
        soundRepository.save(sound);
    }

    public List<Sound> findAll() {
        return soundRepository.findAll();
    }

    public List<Sound> findByLanguage(String language) {
        return soundRepository.findByLanguage(language);
    }

    public List<Sound> findByName(String name) {
        return soundRepository.findByName(name);
    }

    public Sound findById(UUID id) {
        return soundRepository.findById(id);
    }
}
