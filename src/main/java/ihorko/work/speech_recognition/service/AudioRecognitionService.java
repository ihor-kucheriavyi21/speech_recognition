package ihorko.work.speech_recognition.service;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AudioRecognitionService {

    @SneakyThrows
    public String recognizeAudioRecord(String filePathFromSourceRoot) {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/python/audio_recognition.py", filePathFromSourceRoot);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (InputStream inputStream = process.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return bufferedReader.readLine();
        }
    }
}
