package ihorko.work.speech_recognition.controller;

import com.google.gson.Gson;
import ihorko.work.speech_recognition.common.Language;
import ihorko.work.speech_recognition.common.RecognitionResult;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import ihorko.work.speech_recognition.service.AudioRecognitionService;
import ihorko.work.speech_recognition.service.GoogleBardService;
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

    private final GoogleBardService googleBardService;

    private int wrongAnswerCounter = 0;

    private static final Gson gson = new Gson();

    @Autowired
    public RecognizeController(AudioRecognitionService audioRecognitionService, StringService stringService, SoundContentService soundContentService, GoogleBardService googleBardService) {
        this.audioRecognitionService = audioRecognitionService;
        this.stringService = stringService;
        this.soundContentService = soundContentService;
        this.googleBardService = googleBardService;
    }

    @PostMapping(value = "/recognize-from-content", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recognizeFromContent(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("soundContentId") String soundContentId) {

        File savedFile = saveFileIntoResources(file);
        SoundContent soundContent = soundContentService.findById(UUID.fromString(soundContentId));

        Language language = Language.valueOf(soundContent.getSound().getLanguage().toUpperCase());
        RecognitionResult recognitionResult = makeRecognitionAndSaveToResults(savedFile.getPath(),
                language,
                soundContent.getContentText());

        if (recognitionResult == null) {
            return ResponseEntity.badRequest()
                    .body(gson.toJson(""));
        }

        if (!recognitionResult.getWrongText().isEmpty()) {
            wrongAnswerCounter++;
        }

        int numberMistakes = 2;
        if (wrongAnswerCounter == numberMistakes) {
            recognitionResult.setUserAssistantText(googleBardService.buildQueryAboutPronunciationAndAskBard(recognitionResult.getWrongText(), language));
            wrongAnswerCounter = 0;
        }

        return ResponseEntity.ok()
                .body(gson.toJson(recognitionResult));
    }

    @PostMapping(value = "/recognize-from-text", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recognizeFromText(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("textToRecognize") String textContent,
                                                    @RequestParam("language") String language) {

        File savedFile = saveFileIntoResources(file);

        RecognitionResult recognitionResult = makeRecognitionAndSaveToResults(savedFile.getPath(),
                Language.valueOf(language.toUpperCase()),
                textContent);

        if (recognitionResult == null) {
            return ResponseEntity.badRequest()
                    .body(gson.toJson(""));
        }

        return ResponseEntity.ok()
                .body(gson.toJson(recognitionResult));
    }

    @SneakyThrows
    private File saveFileIntoResources(MultipartFile file) {
        File fileDestination = new ClassPathResource(
                "/python/recorderDestination.wav").getFile();

        try (OutputStream os = new FileOutputStream(fileDestination)) {
            os.write(file.getBytes());
        }
        return fileDestination;
    }


    private RecognitionResult makeRecognitionAndSaveToResults(String filePath, Language language, String textToRecognize) {
        String recognizedAudioRecord = audioRecognitionService.recognizeAudioRecord(
                filePath,
                language);
        if (recognizedAudioRecord == null)
            return null;

        return stringService.findCorrectAndWrongPartInExpectedText(textToRecognize, recognizedAudioRecord);
    }
}
