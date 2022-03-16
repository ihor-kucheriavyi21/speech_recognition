package ihorko.work.speech_recognition.db;

import ihorko.work.speech_recognition.db.dao.SoundDao;
import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreatingSoundEntitiesInDBTest {

    @Autowired
    private SoundDao soundDao;

    @Test
    public void testCreateSound() {
        SoundContent soundContent = new SoundContent();
        soundContent.setContentText("Chocolate chance");
        Sound sound = new Sound();
        sound.addSoundContent(soundContent);
        sound.setName("Ch");
        sound.setLanguage("English");
        soundDao.persist(sound);
        List<Sound> byName = soundDao.findByName(sound.getName());
        Assertions.assertNotNull(byName.get(0));
    }
}
