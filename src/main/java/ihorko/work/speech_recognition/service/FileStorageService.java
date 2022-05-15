package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {

    private final FileRepository fileRepository;

    @Autowired
    public FileStorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public File storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects
                .requireNonNull(file.getOriginalFilename()));

        File dbFile = null;
        try {
            dbFile = new File(fileName, file.getContentType(), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileRepository.save(Objects.requireNonNull(dbFile));
    }

    public File findById(UUID uuid) {
        return fileRepository.findById(uuid)
                .orElseThrow();
    }

    public void delete(File file){
        fileRepository.delete(file);
    }
}
