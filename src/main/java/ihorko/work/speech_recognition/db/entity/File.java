package ihorko.work.speech_recognition.db.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "file")
public class File {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;

    private String fileType;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "sound_content_id")
    private SoundContent soundContent;

    public File() {
    }

    public File(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public SoundContent getSoundContent() {
        return soundContent;
    }

    public void setSoundContent(SoundContent soundContent) {
        this.soundContent = soundContent;
    }
}
