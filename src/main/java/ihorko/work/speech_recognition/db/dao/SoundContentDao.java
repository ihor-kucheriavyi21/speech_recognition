package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.dto.SoundContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SoundContentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public SoundContentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(SoundContent soundContent) {
        sessionFactory.getCurrentSession().saveOrUpdate(soundContent);
    }

    public List<SoundContent> listSoundsContent() {
        TypedQuery<SoundContent> query = sessionFactory.getCurrentSession()
                .createQuery("From sound_content", SoundContent.class);
        return query.getResultList();
    }
}
