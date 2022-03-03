package ihorko.work.speech_recognition.db.dto;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "file")
public class DBFile {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "sound_content_id")
    private SoundContent soundContent;

    public DBFile() {
    }

    public DBFile(String fileName, String fileType, byte[] data) {
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
