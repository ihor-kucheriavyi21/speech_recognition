package ihorko.work.speech_recognition.db;

import ihorko.work.speech_recognition.db.dao.SoundDao;
import ihorko.work.speech_recognition.db.dto.Sound;
import ihorko.work.speech_recognition.db.dto.SoundContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatingSoundEntitiesInDBTest {

    @Test
    public void testCreateSound() {
        SoundContent soundContent = new SoundContent();
        soundContent.setContentText("Chocolate chance");
        Sound sound = new Sound();
        sound.addSoundContent(soundContent);
        sound.setName("Ch");
        sound.setLanguage("English");
        SoundDao soundDao = new SoundDao();
        soundDao.persist(sound);
        Sound byName = soundDao.findByName(sound.getName());
        Assertions.assertEquals(sound.getLanguage(), byName.getLanguage());
    }
}
