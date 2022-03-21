package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.Language;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class AudioRecognitionService {

    @SneakyThrows
    public String recognizeAudioRecord(String filePathFromSourceRoot, Language language) {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/python/audio_recognition.py", filePathFromSourceRoot, language.getCode());
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (InputStream inputStream = process.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            //todo remove sout
            String s = bufferedReader.readLine();
            System.out.println(s);
            return s;
        }
    }
}
