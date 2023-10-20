package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.converter.SoundContentConverter;
import ihorko.work.speech_recognition.db.dto.SoundContentDto;
import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.db.entity.Sound;
import ihorko.work.speech_recognition.db.entity.SoundContent;
import ihorko.work.speech_recognition.service.FileStorageService;
import ihorko.work.speech_recognition.service.SoundContentService;
import ihorko.work.speech_recognition.service.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class SoundContentController {

    private final SoundService soundService;
    private final SoundContentService soundContentService;
    private final FileStorageService fileStorageService;
    private final SoundContentConverter soundContentConverter;

    @Autowired
    public SoundContentController(SoundService soundService, SoundContentService soundContentService,
                                  FileStorageService fileStorageService, SoundContentConverter soundContentConverter) {
        this.soundService = soundService;
        this.soundContentService = soundContentService;
        this.fileStorageService = fileStorageService;
        this.soundContentConverter = soundContentConverter;
    }

    @GetMapping("/sound-content/create/page")
    public String showSoundContentCreatePage(Model model) {
        model.addAttribute("soundContent", new SoundContent());
        model.addAttribute("sounds", soundService.findAll());
        return "sound_content/soundContentCreate";
    }

    @PostMapping("/sound-content/create")
    public String createSoundContent(SoundContent soundContent,
                                     @RequestParam MultipartFile imageFile,
                                     @RequestParam MultipartFile audioFile, RedirectAttributes redirectAttributes) {
        File file = fileStorageService.storeFile(imageFile);
        File dbAudioFile = fileStorageService.storeFile(audioFile);

        Sound sound = soundService.findById(soundContent.getSound().getId());
        if (sound.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            throw new IllegalArgumentException("Name for our sound is empty");
        }
        sound.addSoundContent(soundContent);
        soundContent.setSound(sound);
        soundContent.addDbFile(file);
        soundContent.addDbFile(dbAudioFile);

        soundContentService.save(soundContent);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/sound-contents/list";
    }

    @GetMapping("/sound-contents/list")
    public String showSoundContentsList(Model model) {
        List<SoundContentDto> collect = soundContentService.findAll().stream().map(soundContentConverter::convert).collect(Collectors.toList());
        model.addAttribute("soundContentsList", collect);
        return "sound_content/soundContentsList";
    }

    @GetMapping("/sound-contents/{soundId}")
    public String showSoundContentsList(@PathVariable String soundId, Model model) {

        List<SoundContentDto> collect = soundContentService.findListSoundContentBySound(UUID.fromString(soundId))
                .stream()
                .map(soundContentConverter::convert)
                .collect(Collectors.toList());
        model.addAttribute("soundContentsList", collect);
        return "sound_content/soundContentsList";
    }

    @GetMapping("/sound-content/{id}")
    public String showSoundContentPage(@PathVariable UUID id, Model model) {
        SoundContentDto soundContent = soundContentConverter.convert(soundContentService.findById(id));
        model.addAttribute("soundContent", soundContent);
        return "sound_content/soundContent";
    }
}
