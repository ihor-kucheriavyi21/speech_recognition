package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ExerciseContentDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void persist(ExerciseContent exerciseContent) {
        getSession().saveOrUpdate(exerciseContent);
    }

    public List<ExerciseContent> listExercisesContent() {
        TypedQuery<ExerciseContent> query = getSession()
                .createQuery("From ExerciseContent", ExerciseContent.class);
        return query.getResultList();
    }

    public List<ExerciseContent> listExercisesContentByExercise(UUID exercise) {
        TypedQuery<ExerciseContent> query = getSession()
                .createQuery("from ExerciseContent s where s.exercise.id = :exercise_id", ExerciseContent.class);
        query.setParameter("exercise_id", exercise);
        return query.getResultList();
    }

    public ExerciseContent findById(UUID uuid) {
        return getSession().get(ExerciseContent.class, uuid);
    }

    public void delete(UUID uuid) {
        ExerciseContent entity = findById(uuid);
        if (entity != null) {
            getSession().delete(entity);
        }
    }
}
