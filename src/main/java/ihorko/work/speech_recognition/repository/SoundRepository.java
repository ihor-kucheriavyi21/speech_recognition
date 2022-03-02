package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundDao;
import ihorko.work.speech_recognition.db.dto.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SoundRepository {

    @Autowired
    private SoundDao soundDao;

    public void save(Sound sound) {
        soundDao.persist(sound);
    }

    public List<Sound> findAll() {
        return soundDao.listSounds();
    }

    public List<Sound> findByName(String name) {
        return soundDao.findByName(name);
    }

    public Sound findById(UUID id) {
        return soundDao.findById(id);
    }
}
