package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.Exercise;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ExerciseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void persist(Exercise exercise) {
        getSession().saveOrUpdate(exercise);
    }

    public List<Exercise> findByName(String name) {
        TypedQuery<Exercise> query = getSession()
                .createQuery("FROM Exercise s WHERE s.name = :name", Exercise.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Exercise> findByTopic(String topic) {
        TypedQuery<Exercise> query = getSession()
                .createQuery("FROM Exercise s WHERE s.topic = :topic", Exercise.class);
        query.setParameter("topic", topic);
        return query.getResultList();
    }

    public Exercise findById(UUID id) {
        return getSession().get(Exercise.class, id);
    }

    public List<Exercise> listExercises() {
        TypedQuery<Exercise> query = getSession()
                .createQuery("FROM Exercise", Exercise.class);
        return query.getResultList();
    }

    public void deleteExercise(UUID id) {
        Exercise exercise = findById(id);
        if (exercise != null) {
            getSession().delete(exercise);
        }
    }
}
