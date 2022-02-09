import speech_recognition

recognizer = speech_recognition.Recognizer()
test_file = speech_recognition.AudioFile('src/test/resources/testRecorder.wav')
with test_file as source:
    audio = recognizer.record(source)
    text = recognizer.recognize_google(audio, None, "en-US", False)
    print(text)