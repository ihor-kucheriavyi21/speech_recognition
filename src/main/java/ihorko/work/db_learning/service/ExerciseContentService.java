package ihorko.work.db_learning.service;

import ihorko.work.db_learning.db.entity.ExerciseContent;
import ihorko.work.db_learning.repository.ExerciseContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseContentService {

    private final ExerciseContentRepository ExerciseContentRepository;

    @Autowired
    public ExerciseContentService(ExerciseContentRepository ExerciseContentRepository) {
        this.ExerciseContentRepository = ExerciseContentRepository;
    }

    public void save(ExerciseContent ExerciseContent) {
        ExerciseContentRepository.save(ExerciseContent);
    }

    public List<ExerciseContent> findAll() {
        return ExerciseContentRepository.findAll();
    }

    public List<ExerciseContent> findListExerciseContentByExercise(UUID exercise) {
        return ExerciseContentRepository.findListExerciseContentByExercise(exercise);
    }

    public ExerciseContent findById(UUID uuid) {
        return ExerciseContentRepository.findById(uuid);
    }
}
