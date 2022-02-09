package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.service.AudioRecognitionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        try {
            //todo add file utils
//            ClassPathResource classPathResource = new ClassPathResource("python\\"+fileName);
//            classPathResource.getPath();
            File dest = new File("D:\\Study\\VNTU\\Dyplom\\speechTherapy\\web_app\\speech_recognition\\src\\main\\resources\\python\\" + fileName);
            file.transferTo(dest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        System.out.println(new AudioRecognitionService().recognizeAudioRecord());
        return ResponseEntity.ok("File uploaded successfully.");

    }

}
