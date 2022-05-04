package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.Sound;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class SoundDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public SoundDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Sound sound) {
        sessionFactory.getCurrentSession().saveOrUpdate(sound);
    }

    public List<Sound> findByName(String name) {
        TypedQuery<Sound> query = sessionFactory.getCurrentSession()
                .createQuery("From sound s where s.name = :name", Sound.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Sound> findByLanguage(String language) {
        TypedQuery<Sound> query = sessionFactory.getCurrentSession()
                .createQuery("From sound s where s.language = :language", Sound.class);
        query.setParameter("language", language);
        return query.getResultList();
    }

    public Sound findById(UUID id) {
        TypedQuery<Sound> query = sessionFactory.getCurrentSession()
                .createQuery("From sound s where s.id = :id", Sound.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Sound> listSounds() {
        TypedQuery<Sound> query = sessionFactory.getCurrentSession().createQuery("From sound", Sound.class);
        return query.getResultList();
    }
}
