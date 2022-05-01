package ihorko.work.speech_recognition.controller;

import com.google.gson.Gson;
import ihorko.work.speech_recognition.common.Language;
import ihorko.work.speech_recognition.common.RecognitionResult;
import ihorko.work.speech_recognition.service.AudioRecognitionService;
import ihorko.work.speech_recognition.service.StringService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class RecognizeController {

    @Autowired
    private AudioRecognitionService audioRecognitionService;

    @Autowired
    private StringService stringService;

    private static final Gson gson = new Gson();

    @GetMapping("/index")
    public String hello() {
        return "uploader";
    }

    @SneakyThrows
    @PostMapping(value = "/recognize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recognizeAudioFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam("contentText") String contentText) {
        String fileName = file.getOriginalFilename();
        File fileDestination = new File("D:\\Study\\VNTU\\Dyplom\\speechTherapy\\web_app\\speech_recognition\\src\\main\\resources\\python\\" + fileName);
        file.transferTo(fileDestination);

        String recognizedAudioRecord = audioRecognitionService.recognizeAudioRecord(fileDestination.getPath(), Language.ENGLISH);
        if (recognizedAudioRecord == null) {
            return ResponseEntity.badRequest()
                    .body(gson.toJson(""));
        }
        RecognitionResult correctAndWrongPronunciation = stringService.findCorrectAndWrongPartInExpectedText(recognizedAudioRecord, contentText);
        return ResponseEntity.ok()
                .body(gson.toJson(correctAndWrongPronunciation));
    }
}
