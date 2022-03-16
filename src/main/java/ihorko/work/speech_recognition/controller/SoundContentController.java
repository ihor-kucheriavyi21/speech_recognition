package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.converter.SoundContentConverter;
import ihorko.work.speech_recognition.db.dto.SoundContentDto;
import ihorko.work.speech_recognition.db.entity.DBFile;
import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import ihorko.work.speech_recognition.repository.SoundContentRepository;
import ihorko.work.speech_recognition.repository.SoundRepository;
import ihorko.work.speech_recognition.service.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class SoundContentController {

    @Autowired
    private SoundRepository soundRepository;
    @Autowired
    private SoundContentRepository soundContentRepository;
    @Autowired
    private DBFileStorageService dbFileStorageService;
    @Autowired
    private SoundContentConverter soundContentConverter;

    @GetMapping("/sound-content/create/page")
    public String showSoundContentCreatePage(Model model) {
        model.addAttribute("soundContent", new SoundContent());
        model.addAttribute("sounds", soundRepository.findAll());
        return "sound_content/soundContentCreate";
    }

    //todo thymeleaf session object
    @PostMapping("/sound-content/create")
    public String createSoundContent(SoundContent soundContent,
                                     @RequestParam MultipartFile file,
                                     @RequestParam MultipartFile audioFile) {
        DBFile dbFile = dbFileStorageService.storeFile(file);
        DBFile dbAudioFile = dbFileStorageService.storeFile(audioFile);

        Sound sound = soundRepository.findById(soundContent.getSound().getId());
        soundContent.setSound(sound);
        soundContent.addDbFile(dbFile);
        soundContent.addDbFile(dbAudioFile);
        soundContentRepository.save(soundContent);

        return "redirect:/sound-content/create/page";
    }

    @GetMapping("/sound-contents/list")
    public String showSoundContentsList(Model model) {
        List<SoundContentDto> collect = soundContentRepository.findAll().stream().map(soundContentConverter::convert).collect(Collectors.toList());
        model.addAttribute("soundContentsList", collect);
        return "sound_content/soundContentsList";
    }

    @GetMapping("/sound-content/{id}")
    public String showSoundContentPage(@PathVariable UUID id, Model model) {
        SoundContentDto soundContent = soundContentConverter.convert(soundContentRepository.findById(id));
        model.addAttribute("soundContent", soundContent);
        return "sound_content/soundContent";
    }
}
