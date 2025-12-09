package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.dao.ExerciseContentDao;
import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class ExerciseContentRepositoryImpl implements ExerciseContentRepository {

    private final ExerciseContentDao ExerciseContentDao;

    @Autowired
    public ExerciseContentRepositoryImpl(ExerciseContentDao ExerciseContentDao) {
        this.ExerciseContentDao = ExerciseContentDao;
    }

    public void save(ExerciseContent ExerciseContent) {
        ExerciseContentDao.persist(ExerciseContent);
    }

    public List<ExerciseContent> findAll() {
        return ExerciseContentDao.listExercisesContent();
    }

    public List<ExerciseContent> findListExerciseContentByExercise(UUID exercise) {
        return ExerciseContentDao.listExercisesContentByExercise(exercise);
    }

    public ExerciseContent findById(UUID uuid) {
        return ExerciseContentDao.findById(uuid);
    }

    public void delete(UUID id) {
        ExerciseContentDao.delete(id);
    }
}
