package ihorko.work.speech_recognition.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "exercise_content")
@Getter
public class ExerciseContent {

    @Id
    @GeneratedValue
    private UUID id;

    private String contentText;

    private String typeContent;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "ExerciseContent", targetEntity = File.class, cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    public void addDbFile(File file) {
        files.add(file);
        file.setExerciseContent(this);
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }
}
