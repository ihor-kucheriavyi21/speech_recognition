package ihorko.work.speech_recognition.db.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "sound_content")
@Getter
@Setter
public class SoundContent {

    @Id
    @GeneratedValue
    private UUID id;
    private String contentText;

    @ManyToOne
    @JoinColumn(name = "sound_id")
    private Sound sound;

    private String soundName;
}
