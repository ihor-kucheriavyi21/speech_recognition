package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.Language;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@Service
public class AudioRecognitionService {

    private static final Logger LOGGER = Logger.getLogger(AudioRecognitionService.class.getName());

    @SneakyThrows
    public String recognizeAudioRecord(String filePathFromSourceRoot, Language language) {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/python/audio_recognition.py", filePathFromSourceRoot, language.getCode());
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (InputStream inputStream = process.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"windows-1251"))) {

            String recognizedText = bufferedReader.readLine();
            if (recognizedText != null)
                LOGGER.warning(() -> "Recognized text: " + recognizedText);
            return recognizedText;
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }
}
