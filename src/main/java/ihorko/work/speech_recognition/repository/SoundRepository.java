package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.entity.Sound;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SoundRepository {

    void save(Sound sound);

    List<Sound> findAll();

    List<Sound> findByName(String name);

    List<Sound> findByLanguage(String language);

    Sound findById(UUID id);

    void delete(UUID id);
}
