package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.SoundContentDao;
import ihorko.work.speech_recognition.db.dto.SoundContent;

import java.util.List;

public class SoundContentRepository {

    private static final SoundContentDao SOUND_CONTENT_DAO = new SoundContentDao();

    public void save(SoundContent soundContent) {
        SOUND_CONTENT_DAO.persist(soundContent);
    }

    public List<SoundContent> findAll() {
        return SOUND_CONTENT_DAO.listSoundsContent();
    }
}
