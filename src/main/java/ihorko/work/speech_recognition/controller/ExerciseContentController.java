package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.converter.ExerciseContentConverter;
import ihorko.work.speech_recognition.db.dto.ExerciseContentDto;
import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.db.entity.Exercise;
import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import ihorko.work.speech_recognition.service.FileStorageService;
import ihorko.work.speech_recognition.service.ExerciseContentService;
import ihorko.work.speech_recognition.service.ExerciseService;
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
public class ExerciseContentController {

    private final ExerciseService exerciseService;
    private final ExerciseContentService exerciseContentService;
    private final FileStorageService fileStorageService;
    private final ExerciseContentConverter ExerciseContentConverter;

    @Autowired
    public ExerciseContentController(ExerciseService exerciseService, ExerciseContentService exerciseContentService,
                                  FileStorageService fileStorageService, ExerciseContentConverter ExerciseContentConverter) {
        this.exerciseService = exerciseService;
        this.exerciseContentService = exerciseContentService;
        this.fileStorageService = fileStorageService;
        this.ExerciseContentConverter = ExerciseContentConverter;
    }

    @GetMapping("/exercise-content/create/page")
    public String showExerciseContentCreatePage(Model model) {
        model.addAttribute("ExerciseContent", new ExerciseContent());
        model.addAttribute("exercises", exerciseService.findAll());
        return "exercise_content/ExerciseContentCreate";
    }

    @PostMapping("/exercise-content/create")
    public String createExerciseContent(ExerciseContent ExerciseContent,
                                     @RequestParam MultipartFile imageFile,
                                     @RequestParam MultipartFile audioFile, RedirectAttributes redirectAttributes) {
        File file = fileStorageService.storeFile(imageFile);
        File dbAudioFile = fileStorageService.storeFile(audioFile);

        Exercise exercise = exerciseService.findById(ExerciseContent.getExercise().getId());
        if (exercise.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            throw new IllegalArgumentException("Name for our exercise is empty");
        }
        exercise.addExerciseContent(ExerciseContent);
        ExerciseContent.setExercise(exercise);
        ExerciseContent.addDbFile(file);
        ExerciseContent.addDbFile(dbAudioFile);

        exerciseContentService.save(ExerciseContent);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/exercise-contents/list";
    }

    @GetMapping("/exercise-contents/list")
    public String showExerciseContentsList(Model model) {
        List<ExerciseContentDto> collect = exerciseContentService.findAll().stream().map(ExerciseContentConverter::convert).collect(Collectors.toList());
        model.addAttribute("ExerciseContentsList", collect);
        return "exercise_content/ExerciseContentsList";
    }

    @GetMapping("/exercise-contents/{exerciseId}")
    public String showExerciseContentsList(@PathVariable String exerciseId, Model model) {

        List<ExerciseContentDto> collect = exerciseContentService.findListExerciseContentByExercise(UUID.fromString(exerciseId))
                .stream()
                .map(ExerciseContentConverter::convert)
                .collect(Collectors.toList());
        model.addAttribute("ExerciseContentsList", collect);
        return "exercise_content/ExerciseContentsList";
    }

    @GetMapping("/exercise-content/{id}")
    public String showExerciseContentPage(@PathVariable UUID id, Model model) {
        ExerciseContentDto ExerciseContent = ExerciseContentConverter.convert(exerciseContentService.findById(id));
        model.addAttribute("ExerciseContent", ExerciseContent);
        return "exercise_content/ExerciseContent";
    }
}
