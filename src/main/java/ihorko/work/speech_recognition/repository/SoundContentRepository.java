package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SoundContentRepository {

    void save(SoundContent soundContent);

    List<SoundContent> findAll();

    List<SoundContent> findListSoundContentBySound(UUID sound);

    SoundContent findById(UUID uuid);
}
