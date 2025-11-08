package ihorko.work.speech_recognition.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "exercise")
@Getter
public class Exercise {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String topic;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, targetEntity = ExerciseContent.class)
    private List<ExerciseContent> ExerciseContents = new ArrayList<>();

    public void addExerciseContent(ExerciseContent ExerciseContent) {
        ExerciseContents.add(ExerciseContent);
        ExerciseContent.setExercise(this);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
