package ihorko.work.speech_recognition.db.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "exercise_content")
@Getter
@Setter
public class ExerciseContent {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 2000)
    private String questionText;

    private String typeContent;

    @ElementCollection
    @CollectionTable(name = "exercise_answers", joinColumns = @JoinColumn(name = "exercise_content_id"))
    @Column(name = "answer_text")
    private List<String> answers = new ArrayList<>();

    private Integer correctAnswerIndex;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    // 🟢 Зверни увагу: mappedBy = "exerciseContent"
    @OneToMany(mappedBy = "exerciseContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    public void addFile(File file) {
        files.add(file);
        file.setExerciseContent(this);
    }
}
