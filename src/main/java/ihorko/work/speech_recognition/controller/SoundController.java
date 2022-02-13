package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.db.dto.Sound;
import ihorko.work.speech_recognition.repository.SoundRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SoundController {

    private static final SoundRepository SOUND_REPOSITORY = new SoundRepository();

    @GetMapping("/sound/create/page")
    public String showCreateSoundForm(Model model) {
        model.addAttribute("sound", new Sound());
        return "soundCreate";
    }

    @PostMapping("/sound-create")
    public String createSound(Sound sound) {
        SOUND_REPOSITORY.save(sound);
        return "redirect:/sound/create/page";
    }

    @GetMapping("/sounds-list")
    public String showListSounds(Model model) {
        model.addAttribute("sounds", SOUND_REPOSITORY.findAll());
        return "soundsList";
    }
}
