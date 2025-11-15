package ihorko.work.db_learning.repository;

import ihorko.work.db_learning.db.entity.Exercise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseRepository {

    void save(Exercise exercise);

    List<Exercise> findAll();

    List<Exercise> findByName(String name);

    List<Exercise> findByTopic(String topic);

    Exercise findById(UUID id);

    void delete(UUID id);
}
