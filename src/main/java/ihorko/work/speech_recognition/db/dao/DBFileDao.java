package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.DBFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public class DBFileDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public DBFileDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(DBFile dbFile) {
        sessionFactory.getCurrentSession().saveOrUpdate(dbFile);
    }

    public DBFile findById(UUID uuid) {
        return sessionFactory.getCurrentSession().get(DBFile.class, uuid);
    }
}
