package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.dto.DBFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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

}
