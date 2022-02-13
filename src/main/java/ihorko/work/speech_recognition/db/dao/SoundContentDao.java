package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.dto.SoundContent;
import ihorko.work.speech_recognition.db.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class SoundContentDao {

    public void persist(SoundContent soundContent) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(soundContent);
        transaction.commit();
        session.close();
    }

    public List<SoundContent> listSoundsContent() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("From sound_content ");
        List result = query.getResultList();
        session.close();
        return result;
    }
}
