package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.repository.SoundRepository;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SoundController {

    private SoundRepository soundRepository;

    @Autowired
    public void setSoundRepository(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    @GetMapping("/sound/create/page")
    public String showCreateSoundForm(Model model) {
        model.addAttribute("sound", new Sound());
        return "soundCreate";
    }

    @PostMapping("/sound/create")
    public String createSound(Sound sound) {
        soundRepository.save(sound);
        return "redirect:/sound/create/page";
    }

    @GetMapping("/sounds/list")
    public String showListSounds(Model model) {
        model.addAttribute("soundsLists", ListUtils.partition(soundRepository.findAll(), 4));
        return "soundsList";
    }
}
