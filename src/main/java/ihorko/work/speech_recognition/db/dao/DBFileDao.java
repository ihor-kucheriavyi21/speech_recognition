package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.File;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public class DBFileDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void persist(File file) {
        getSession().saveOrUpdate(file);
    }

    public File findById(UUID uuid) {
        return getSession().get(File.class, uuid);
    }
}
