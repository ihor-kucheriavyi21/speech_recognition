package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.Exercise;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ExerciseDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ExerciseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Exercise exercise) {
        sessionFactory.getCurrentSession().saveOrUpdate(exercise);
    }

    public List<Exercise> findByName(String name) {
        TypedQuery<Exercise> query = sessionFactory.getCurrentSession()
                .createQuery("From exercise s where s.name = :name", Exercise.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Exercise> findByTopic(String topic) {
        TypedQuery<Exercise> query = sessionFactory.getCurrentSession()
                .createQuery("From exercise s where s.topic = :topic", Exercise.class);
        query.setParameter("topic", topic);
        return query.getResultList();
    }

    public Exercise findById(UUID id) {
        TypedQuery<Exercise> query = sessionFactory.getCurrentSession()
                .createQuery("From exercise s where s.id = :id", Exercise.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Exercise> listExercises() {
        TypedQuery<Exercise> query = sessionFactory.getCurrentSession().createQuery("From exercise", Exercise.class);
        return query.getResultList();
    }

    public void deleteExercise(UUID id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }
}
