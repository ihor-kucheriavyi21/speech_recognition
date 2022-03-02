package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundContentDao;
import ihorko.work.speech_recognition.db.dto.SoundContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoundContentRepository {

    @Autowired
    private SoundContentDao soundContentDao;

    public void save(SoundContent soundContent) {
        soundContentDao.persist(soundContent);
    }

    public List<SoundContent> findAll() {
        return soundContentDao.listSoundsContent();
    }
}
