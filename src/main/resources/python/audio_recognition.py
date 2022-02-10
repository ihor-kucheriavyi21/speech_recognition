import sys
import speech_recognition

fileNameForRecognition = sys.argv[1]
recognizer = speech_recognition.Recognizer()
test_file = speech_recognition.AudioFile(fileNameForRecognition)
with test_file as source:
    audio = recognizer.record(source)
    text = recognizer.recognize_google(audio, None, "en-US", False)
    print(text)