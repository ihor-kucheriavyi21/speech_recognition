package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundDao;
import ihorko.work.speech_recognition.db.dto.Sound;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoundRepository {
    private static final SoundDao SOUND_DAO = new SoundDao();

    public void save(Sound sound) {
        SOUND_DAO.persist(sound);
    }

    public List<Sound> findAll() {
        return SOUND_DAO.listSounds();
    }

    public Sound findByName(String name) {
        return SOUND_DAO.findByName(name);
    }
}
