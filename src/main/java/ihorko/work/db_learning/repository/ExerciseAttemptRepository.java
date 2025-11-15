package ihorko.work.db_learning.repository;

import ihorko.work.db_learning.db.entity.ExerciseAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseAttemptRepository extends JpaRepository<ExerciseAttempt, UUID> {

    // рахуємо, скільки вже було спроб для цього завдання
    long countByExerciseContent_Id(UUID exerciseContentId);

    long countByExerciseContent_IdAndSessionId(UUID exerciseContentId, UUID sessionId);

}

