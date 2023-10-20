package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundDao;
import ihorko.work.speech_recognition.db.entity.Sound;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class SoundRepositoryImpl  implements SoundRepository{

    private final SoundDao soundDao;

    @Autowired
    public SoundRepositoryImpl(SoundDao soundDao) {
        this.soundDao = soundDao;
    }

    public void save(Sound sound) {
        soundDao.persist(sound);
    }

    public List<Sound> findAll() {
        return soundDao.listSounds();
    }

    public List<Sound> findByName(String name) {
        return soundDao.findByName(name);
    }

    public List<Sound> findByLanguage(String language) {
        return soundDao.findByLanguage(language);
    }

    public Sound findById(UUID id) {
        return soundDao.findById(id);
    }

    public void delete(UUID id){
        soundDao.deleteSound(id);
    }
}
