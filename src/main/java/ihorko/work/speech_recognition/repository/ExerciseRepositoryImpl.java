package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.ExerciseDao;
import ihorko.work.speech_recognition.db.entity.Exercise;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class ExerciseRepositoryImpl implements ExerciseRepository{

    private final ExerciseDao exerciseDao;

    @Autowired
    public ExerciseRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public void save(Exercise exercise) {
        exerciseDao.persist(exercise);
    }

    public List<Exercise> findAll() {
        return exerciseDao.listExercises();
    }

    public List<Exercise> findByName(String name) {
        return exerciseDao.findByName(name);
    }

    public List<Exercise> findByTopic(String topic) {
        return exerciseDao.findByTopic(topic);
    }

    public Exercise findById(UUID id) {
        return exerciseDao.findById(id);
    }

    public void delete(UUID id){
        exerciseDao.deleteExercise(id);
    }
}
