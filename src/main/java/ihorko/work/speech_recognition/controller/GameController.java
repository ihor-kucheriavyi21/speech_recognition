package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
//    https://www.artstation.com/artwork/14xBK2
//    https://www.artstation.com/artwork/WWzxJ

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String showSoundContentPage(Model model) {
        model.addAttribute("gameWord", gameService.getRandomWord());
        return "game/gamePage";
    }

    @GetMapping("/get-word")
    public ResponseEntity<String> handleFileUpload() {
        return ResponseEntity.ok()
                .body(gameService.getRandomWord());
    }

}
