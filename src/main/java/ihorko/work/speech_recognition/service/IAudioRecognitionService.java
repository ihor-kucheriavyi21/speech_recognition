package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.Language;
import org.springframework.stereotype.Service;

@Service
public interface IAudioRecognitionService {

    String recognizeAudioRecord(String filePathFromSourceRoot, Language language);
}
