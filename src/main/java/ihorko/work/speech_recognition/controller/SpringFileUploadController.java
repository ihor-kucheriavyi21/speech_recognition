package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.service.AudioRecognitionService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class SpringFileUploadController {

    @GetMapping("/index")
    public String hello() {
        return "uploader";
    }

    @PostMapping("/upload")
    @SneakyThrows
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File fileDestination = new File("D:\\Study\\VNTU\\Dyplom\\speechTherapy\\web_app\\speech_recognition\\src\\main\\resources\\python\\" + fileName);
        file.transferTo(fileDestination);

        System.out.println(new AudioRecognitionService().recognizeAudioRecord(fileDestination.getPath()));
        return ResponseEntity.ok("File uploaded successfully.");

    }

}
