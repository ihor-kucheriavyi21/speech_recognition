package ihorko.work.db_learning.service;

import ihorko.work.db_learning.db.entity.ExerciseAttempt;
import ihorko.work.db_learning.repository.ExerciseAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExerciseAttemptService {

    private final ExerciseAttemptRepository attemptRepository;

    @Autowired
    public ExerciseAttemptService(ExerciseAttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public int getNextAttemptNumber(UUID exerciseContentId) {
        long count = attemptRepository.countByExerciseContent_Id(exerciseContentId);
        return (int) count + 1;
    }

    public ExerciseAttempt saveAttempt(ExerciseAttempt attempt) {
        return attemptRepository.save(attempt);
    }

    public int getNextAttemptNumberForUser(UUID exerciseId, UUID sessionId) {
        long count = attemptRepository
                .countByExerciseContent_IdAndSessionId(exerciseId, sessionId);

        return (int) count + 1;
    }

}
