package ihorko.work.speech_recognition.service;

import ihorko.work.speech_recognition.common.RecognitionResult;
import org.springframework.stereotype.Service;

@Service
public interface IStringService {

    RecognitionResult findCorrectAndWrongPartInExpectedText(String expectedResult, String result);
}
