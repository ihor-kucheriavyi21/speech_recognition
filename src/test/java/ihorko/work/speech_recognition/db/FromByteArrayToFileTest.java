package ihorko.work.speech_recognition.db;

import ihorko.work.speech_recognition.db.dao.DBFileDao;
import ihorko.work.speech_recognition.db.entity.DBFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FromByteArrayToFileTest {

    @Autowired
    private DBFileDao dbFileDao;

    @Test
    public void saveFileFromByteArray() {
        DBFile byId = dbFileDao.findById(UUID.fromString("704c7afa-2548-4ee7-9054-0a35ee6b0b9f"));
        try {
            Files.write(Path.of("result.jpg"), byId.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
