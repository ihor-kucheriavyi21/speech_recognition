package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.db.dto.Sound;
import ihorko.work.speech_recognition.db.dto.SoundContent;
import ihorko.work.speech_recognition.repository.SoundContentRepository;
import ihorko.work.speech_recognition.repository.SoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SoundContentController {

    //todo learn about autowired
    @Autowired
    private SoundRepository soundRepository;
    @Autowired
    private SoundContentRepository soundContentRepository;

    @GetMapping("/sound-content/create/page")
    public String showSoundContentCreatePage(Model model) {
        model.addAttribute("soundContent", new SoundContent());
        model.addAttribute("sounds", soundRepository.findAll());
        return "sound_content/soundContentCreate";
    }

    //todo change usage hardcoding
    @PostMapping("/sound-content/create")
    public String createSoundContent(SoundContent soundContent) {

        Sound sound = soundRepository.findById(soundContent.getSound().getId());
        soundContent.setSound(sound);
        soundContentRepository.save(soundContent);
        return "redirect:/sound-content/create/page";
    }

    @GetMapping("/sound-contents/list")
    public String showSoundContentsList(Model model) {
        model.addAttribute("soundContentsList", soundContentRepository.findAll());
        return "sound_content/soundContentsList";
    }
}
