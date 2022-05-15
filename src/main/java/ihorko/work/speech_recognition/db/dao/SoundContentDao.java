package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.SoundContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class SoundContentDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void persist(SoundContent soundContent) {
        sessionFactory.getCurrentSession().saveOrUpdate(soundContent);
    }

    public List<SoundContent> listSoundsContent() {
        TypedQuery<SoundContent> query = sessionFactory.getCurrentSession()
                .createQuery("From sound_content", SoundContent.class);
        return query.getResultList();
    }

    public List<SoundContent> listSoundsContentBySound(UUID sound) {
        TypedQuery<SoundContent> query = sessionFactory.getCurrentSession()
                .createQuery("from sound_content s where s.sound.id= :sound_id", SoundContent.class);
        query.setParameter("sound_id", sound);
        return query.getResultList();
    }

    public SoundContent findById(UUID uuid) {
        return sessionFactory.getCurrentSession()
                .get(SoundContent.class, uuid);
    }

    public void delete(UUID uuid){
        sessionFactory.getCurrentSession().delete(findById(uuid));
    }
}
