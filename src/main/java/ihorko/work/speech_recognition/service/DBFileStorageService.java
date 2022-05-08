package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.DBFile;
import ihorko.work.speech_recognition.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class DBFileStorageService {

    private final DBFileRepository dbFileRepository;

    @Autowired
    public DBFileStorageService(DBFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    @Transactional
    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects
                .requireNonNull(file.getOriginalFilename()));

        DBFile dbFile = null;
        try {
            dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbFileRepository.save(dbFile);
    }

    public DBFile findById(UUID uuid) {
        return dbFileRepository.findById(uuid)
                .orElseThrow();
    }
}
