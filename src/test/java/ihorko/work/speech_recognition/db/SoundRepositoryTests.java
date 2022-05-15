package ihorko.work.speech_recognition.db;

import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.repository.SoundRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class SoundRepositoryTests {

    @Autowired
    private SoundRepository soundRepository;
    private static final String TEST_NAME = "TestName1234";

    @Test
    @Order(1)
    void testCreateSound() {
        Sound sound = new Sound();
        sound.setName(TEST_NAME);
        sound.setLanguage("English");
        soundRepository.save(sound);
        var createdSound = soundRepository.findByName(TEST_NAME).get(0);
        Assertions.assertNotNull(createdSound);
        Assertions.assertEquals(TEST_NAME, createdSound.getName());
    }

    @Test
    @Order(2)
    void testDeleteSound() {
        Sound createdSound = soundRepository.findByName(TEST_NAME).get(0);
        var soundId = createdSound.getId();
        soundRepository.delete(soundId);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> soundRepository.findById(soundId));
    }
}
