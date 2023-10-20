package ihorko.work.speech_recognition.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "sound_content")
@Getter
public class SoundContent {

    @Id
    @GeneratedValue
    private UUID id;

    private String contentText;

    private String typeContent;

    @ManyToOne
    @JoinColumn(name = "sound_id", nullable = false)
    private Sound sound;

    @OneToMany(mappedBy = "soundContent", targetEntity = File.class, cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    public void addDbFile(File file) {
        files.add(file);
        file.setSoundContent(this);
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }
}
