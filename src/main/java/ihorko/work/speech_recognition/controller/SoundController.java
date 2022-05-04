package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.service.SoundService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SoundController {

    private SoundService soundService;

    @Autowired
    public void setSoundService(SoundService soundService) {
        this.soundService = soundService;
    }

    @GetMapping("/sound/create/page")
    public String showCreateSoundForm(Model model) {
        model.addAttribute("sound", new Sound());
        return "soundCreate";
    }

    @PostMapping("/sound/create")
    public String createSound(Sound sound) {
        soundService.save(sound);
        return "redirect:/sound/create/page";
    }

    @GetMapping("/sounds/list")
    public String showListSounds(Model model) {
        model.addAttribute("soundsLists", ListUtils.partition(soundService.findAll(), 4));
        return "soundsList";
    }

    @GetMapping("/sounds/list/{language}")
    public String showListSoundsByLanguage(@PathVariable String language, Model model) {
        model.addAttribute("soundsLists", ListUtils.partition(soundService.findByLanguage(language), 4));
        return "soundsList";
    }
}
