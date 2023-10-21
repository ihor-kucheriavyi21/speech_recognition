package ihorko.work.speech_recognition.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final List<String> listElements = new ArrayList<>(List.of("apple"));

    private final Random random = new Random();

    public String getRandomWord() {
        return listElements.get(random.nextInt(listElements.size()));
    }

    public void addNewWord(String word) {
        listElements.add(word);
    }
}
