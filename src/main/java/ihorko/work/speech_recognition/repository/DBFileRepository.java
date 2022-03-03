package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.DBFileDao;
import ihorko.work.speech_recognition.db.dto.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DBFileRepository {

    private final DBFileDao dbFileDao;

    @Autowired
    public DBFileRepository(DBFileDao dbFileDao) {
        this.dbFileDao = dbFileDao;
    }

    public DBFile save(DBFile dbFile) {
        dbFileDao.persist(dbFile);
        return dbFile;
    }
}
