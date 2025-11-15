package ihorko.work.speech_recognition.db.dao;

import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ExerciseContentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ExerciseContentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(ExerciseContent ExerciseContent) {
        sessionFactory.getCurrentSession().saveOrUpdate(ExerciseContent);
    }

    public List<ExerciseContent> listExercisesContent() {
        TypedQuery<ExerciseContent> query = sessionFactory.getCurrentSession()
                .createQuery("From ExerciseContent", ExerciseContent.class);
        return query.getResultList();
    }

    public List<ExerciseContent> listExercisesContentByExercise(UUID exercise) {
        TypedQuery<ExerciseContent> query = sessionFactory.getCurrentSession()
                .createQuery("from ExerciseContent s where s.exercise.id= :exercise_id", ExerciseContent.class);
        query.setParameter("exercise_id", exercise);
        return query.getResultList();
    }

    public ExerciseContent findById(UUID uuid) {
        return sessionFactory.getCurrentSession()
                .get(ExerciseContent.class, uuid);
    }

    public void delete(UUID uuid) {
        sessionFactory.getCurrentSession().delete(findById(uuid));
    }
}
