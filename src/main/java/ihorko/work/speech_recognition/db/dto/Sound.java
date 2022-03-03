package ihorko.work.speech_recognition.db.dto;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "sound")
@Getter
public class Sound {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String language;

    @OneToMany(mappedBy = "sound", cascade = CascadeType.ALL, targetEntity = SoundContent.class)
    private List<SoundContent> soundContents = new ArrayList<>();

    public void addSoundContent(SoundContent soundContent) {
        soundContents.add(soundContent);
        soundContent.setSound(this);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
