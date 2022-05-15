package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundContentDao;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SoundContentRepositoryImpl implements SoundContentRepository {

    private final SoundContentDao soundContentDao;

    @Autowired
    public SoundContentRepositoryImpl(SoundContentDao soundContentDao) {
        this.soundContentDao = soundContentDao;
    }

    public void save(SoundContent soundContent) {
        soundContentDao.persist(soundContent);
    }

    public List<SoundContent> findAll() {
        return soundContentDao.listSoundsContent();
    }

    public List<SoundContent> findListSoundContentBySound(UUID sound) {
        return soundContentDao.listSoundsContentBySound(sound);
    }

    public SoundContent findById(UUID uuid) {
        return soundContentDao.findById(uuid);
    }

    public void delete(UUID id) {
        soundContentDao.delete(id);
    }
}
