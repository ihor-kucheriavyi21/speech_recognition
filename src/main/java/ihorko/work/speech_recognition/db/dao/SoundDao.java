package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.dto.Sound;
import ihorko.work.speech_recognition.db.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class SoundDao {

    @Autowired
    private Session session = SessionUtil.getSession();

    public void persist(Sound sound) {
        Transaction transaction = session.beginTransaction();
        session.persist(sound);
        transaction.commit();
    }

    public List<Sound> findByName(String name) {
        Query query = session.createQuery("From sound s where s.name = :name", Sound.class);
        query.setParameter("name", name);
        return (List<Sound>) query.getResultList();
    }

    public Sound findById(UUID id) {
        Query query = session.createQuery("From sound s where s.id = :id", Sound.class);
        query.setParameter("id", id);
        return (Sound) query.getSingleResult();
    }

    public List<Sound> listSounds() {
        Query query = session.createQuery("From sound");
        return (List<Sound>) query.getResultList();
    }
}
