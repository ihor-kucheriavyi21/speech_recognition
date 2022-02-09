package ihorko.work.speech_recognition.python;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestTranslating {

    @Test
    public void getPythonRecognition() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/test/resources/audio_recognition.py");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String translatedString = bufferedReader.readLine();
        //verify if text in audio was recognized
        Assertions.assertTrue(translatedString.contains("in relation"));
        Assertions.assertTrue(translatedString.contains("audio recording could not load content"));
    }
}
