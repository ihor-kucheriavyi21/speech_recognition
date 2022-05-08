package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.db.entity.DBFile;
import ihorko.work.speech_recognition.service.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class DBFileController {

    private DBFileStorageService dbFileStorageService;

    @Autowired
    public void setDbFileStorageService(DBFileStorageService dbFileStorageService) {
        this.dbFileStorageService = dbFileStorageService;
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.findById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}
