package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.DBFileDao;
import ihorko.work.speech_recognition.db.entity.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DBFileRepository {

    @Autowired
    private DBFileDao dbFileDao;

    public DBFile save(DBFile dbFile) {
        dbFileDao.persist(dbFile);
        return dbFile;
    }

    public DBFile findById(UUID uuid) {
        return dbFileDao.findById(uuid);
    }
}
