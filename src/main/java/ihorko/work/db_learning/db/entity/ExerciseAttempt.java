package ihorko.work.db_learning.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "exercise_attempt")
@Getter
@Setter
public class ExerciseAttempt {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "exercise_content_id")
    private ExerciseContent exerciseContent;

    private Integer attemptNumber;

    private Boolean isCorrect;

    private Boolean usedAiHint;

    private Integer selectedAnswer;

    private Timestamp createdAt = Timestamp.from(Instant.now());

    @Column(name = "session_id", nullable = false)
    private UUID sessionId;

}

