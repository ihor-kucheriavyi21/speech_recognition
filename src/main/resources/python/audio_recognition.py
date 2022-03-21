import speech_recognition
import sys

fileNameForRecognition = sys.argv[1]
languageCode = sys.argv[2]
recognizer = speech_recognition.Recognizer()
test_file = speech_recognition.AudioFile(fileNameForRecognition)
with test_file as source:
    audio = recognizer.record(source)
    text = recognizer.recognize_google(audio, None, languageCode, False)
    print(text)
