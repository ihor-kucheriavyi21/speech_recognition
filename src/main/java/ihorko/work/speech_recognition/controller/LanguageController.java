package ihorko.work.speech_recognition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    @GetMapping("/language")
    public String showLanguagePage() {
        return "/language";
    }
}
