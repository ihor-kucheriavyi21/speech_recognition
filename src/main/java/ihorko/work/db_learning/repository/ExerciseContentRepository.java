package ihorko.work.db_learning.repository;

import ihorko.work.db_learning.db.entity.ExerciseContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseContentRepository {

    void save(ExerciseContent ExerciseContent);

    List<ExerciseContent> findAll();

    List<ExerciseContent> findListExerciseContentByExercise(UUID exercise);

    ExerciseContent findById(UUID uuid);

    void delete(UUID uuid);
}
