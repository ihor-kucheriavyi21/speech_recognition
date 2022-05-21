package ihorko.work.speech_recognition.controller;

import com.google.gson.Gson;
import ihorko.work.speech_recognition.common.Language;
import ihorko.work.speech_recognition.common.RecognitionResult;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import ihorko.work.speech_recognition.service.AudioRecognitionService;
import ihorko.work.speech_recognition.service.SoundContentService;
import ihorko.work.speech_recognition.service.StringService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
public class RecognizeController {

    private final AudioRecognitionService audioRecognitionService;

    private final StringService stringService;

    private final SoundContentService soundContentService;

    private static final Gson gson = new Gson();

    @Autowired
    public RecognizeController(AudioRecognitionService audioRecognitionService, StringService stringService, SoundContentService soundContentService) {
        this.audioRecognitionService = audioRecognitionService;
        this.stringService = stringService;
        this.soundContentService = soundContentService;
    }

    @SneakyThrows
    @PostMapping(value = "/recognize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recognizeAudioFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam("soundContentId") String soundContentId) {
        File fileDestination = new ClassPathResource(
                "/python/recorderDestination.wav").getFile();

        try (OutputStream os = new FileOutputStream(fileDestination)) {
            os.write(file.getBytes());
        }

        SoundContent soundContent = soundContentService.findById(UUID.fromString(soundContentId));

        String recognizedAudioRecord = audioRecognitionService.recognizeAudioRecord(
                fileDestination.getPath(),
                Language.valueOf(soundContent.getSound().getLanguage().toUpperCase()));
        if (recognizedAudioRecord == null) {
            return ResponseEntity.badRequest()
                    .body(gson.toJson(""));
        }
        RecognitionResult recognitionResult = stringService
                .findCorrectAndWrongPartInExpectedText(soundContent.getContentText(),
                        recognizedAudioRecord);
        return ResponseEntity.ok()
                .body(gson.toJson(recognitionResult));
    }
}
