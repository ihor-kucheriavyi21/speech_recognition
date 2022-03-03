package ihorko.work.speech_recognition.db.dto;


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

    @ManyToOne
    @JoinColumn(name = "sound_id", nullable = false)
    private Sound sound;

    //todo why we can not use in this place mappedBy
    @OneToMany(cascade = CascadeType.ALL, targetEntity = DBFile.class)
    private List<DBFile> dbFiles = new ArrayList<>();


    public void addDbFile(DBFile dbFile) {
        dbFiles.add(dbFile);
        dbFile.setSoundContent(this);
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
}
