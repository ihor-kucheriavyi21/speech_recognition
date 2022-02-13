package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.dto.Sound;
import ihorko.work.speech_recognition.db.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class SoundDao {

    public void persist(Sound sound) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(sound);
        transaction.commit();
        session.close();
    }

    public Sound findByName(String name) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("From sound s where s.name = :name", Sound.class);
        query.setParameter("name", name);
        Sound result = (Sound) query.getSingleResult();
        session.close();
        return result;
    }

    public List listSounds() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("From sound");
        List result = query.getResultList();
        session.close();
        return result;
    }
}
