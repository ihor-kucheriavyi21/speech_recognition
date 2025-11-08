package ihorko.work.speech_recognition.db;

import ihorko.work.speech_recognition.db.entity.Exercise;
import ihorko.work.speech_recognition.repository.ExerciseRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ExerciseRepositoryTests {

    @Autowired
    private ExerciseRepository exerciseRepository;
    private static final String TEST_NAME = "TestName1234";

    @Test
    @Order(1)
    void testCreateExercise() {
        Exercise exercise = new Exercise();
        exercise.setName(TEST_NAME);
        exercise.setTopic("English");
        exerciseRepository.save(exercise);
        var createdExercise = exerciseRepository.findByName(TEST_NAME).get(0);
        Assertions.assertNotNull(createdExercise);
        Assertions.assertEquals(TEST_NAME, createdExercise.getName());
    }

    @Test
    @Order(2)
    void testDeleteExercise() {
        Exercise createdExercise = exerciseRepository.findByName(TEST_NAME).get(0);
        var exerciseId = createdExercise.getId();
        exerciseRepository.delete(exerciseId);
        Assertions.assertThrows(EmptyResultDataAccessException.class,
                () -> exerciseRepository.findById(exerciseId));
    }
}
