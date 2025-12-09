package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.Exercise;
import ihorko.work.speech_recognition.repository.ExerciseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseRepositoryImpl exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepositoryImpl exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public void save(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> findByTopic(String topic) {
        return exerciseRepository.findByTopic(topic);
    }

    public List<Exercise> findByName(String name) {
        return exerciseRepository.findByName(name);
    }

    public Exercise findById(UUID id) {
        return exerciseRepository.findById(id);
    }
}
