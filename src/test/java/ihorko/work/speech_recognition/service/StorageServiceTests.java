package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.db.entity.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class StorageServiceTests {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    void testSavingFile() {
        String testData = "Hello from file";
        MultipartFile mockMultipartFile = new MockMultipartFile("testFile.txt", testData.getBytes());
        File file = fileStorageService.storeFile(mockMultipartFile);
        File savedFile = fileStorageService.findById(file.getId());
        Assertions.assertArrayEquals(testData.getBytes(), savedFile.getData());
        fileStorageService.delete(savedFile);
    }

    @Test
    void testDeleteFile() {
        String testData = "Hello from file";
        MultipartFile mockMultipartFile = new MockMultipartFile("testFile.txt", testData.getBytes());
        File file = fileStorageService.storeFile(mockMultipartFile);
        fileStorageService.delete(file);
        UUID fileId = file.getId();
        Assertions.assertThrows(NoSuchElementException.class, () -> fileStorageService.findById(fileId));
    }
}
