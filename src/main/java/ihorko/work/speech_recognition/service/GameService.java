package ihorko.work.speech_recognition.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final List<String> listElements = List.of("apple", "house", "smoke", "jungle", "lost", "window",
            "bow", "probably", "meal", "suddenly", "thick", "represent",
            "lesson", "bring", "quietly", "correct", "throat", "interior",
            "spite", "charge", "refer", "personal", "dust", "pride",
            "bottle", "government", "noon", "hungry", "state", "entirely",
            "original", "potatoes", "along", "atom", "design", "bear",
            "score", "coal", "scientist", "hand", "everybody", "unknown",
            "mission", "nobody", "create", "several", "ruler", "right",
            "clearly", "gate", "share", "curve", "any", "storm",
            "mathematics", "poet", "shoulder", "island", "applied", "arrangement",
            "simply", "load", "beginning", "hung", "drawn", "quickly",
            "adjective", "pass", "maybe", "origin", "has", "grandmother",
            "law", "brief", "twelve", "listen", "where", "so",
            "fall", "laugh", "locate", "union", "political", "difference");
    
    private final Random random = new Random();

    public String getRandomWord() {
        return listElements.get(random.nextInt(listElements.size()));
    }
}
