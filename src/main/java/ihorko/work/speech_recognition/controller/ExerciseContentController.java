package ihorko.work.speech_recognition.controller;

import ihorko.work.speech_recognition.converter.ExerciseContentConverter;
import ihorko.work.speech_recognition.db.dto.ExerciseContentDto;
import ihorko.work.speech_recognition.db.entity.Exercise;
import ihorko.work.speech_recognition.db.entity.ExerciseAttempt;
import ihorko.work.speech_recognition.db.entity.ExerciseContent;
import ihorko.work.speech_recognition.db.entity.File;
import ihorko.work.speech_recognition.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ExerciseContentController {

    private final ExerciseService exerciseService;
    private final ExerciseContentService exerciseContentService;
    private final ExerciseContentConverter exerciseContentConverter;
    private final FileStorageService fileStorageService;
    private final GeminiService geminiService;
    private final ExerciseAttemptService exerciseAttemptService;

    @Autowired
    public ExerciseContentController(
            ExerciseService exerciseService,
            ExerciseContentService exerciseContentService,
            ExerciseContentConverter exerciseContentConverter,
            FileStorageService fileStorageService,
            ExerciseAttemptService exerciseAttemptService,
            GeminiService geminiService

    ) {
        this.exerciseService = exerciseService;
        this.exerciseContentService = exerciseContentService;
        this.exerciseContentConverter = exerciseContentConverter;
        this.fileStorageService = fileStorageService;
        this.exerciseAttemptService = exerciseAttemptService;

        this.geminiService = geminiService;

    }

    // -------------------------------------------------------
    // 1️⃣ сторінка створення тестового завдання
    // -------------------------------------------------------
    @GetMapping("/exercise-content/create/page")
    public String showCreatePage(Model model) {
        model.addAttribute("ExerciseContent", new ExerciseContent());
        model.addAttribute("exercises", exerciseService.findAll());
        return "exercise_content/exerciseContentCreate";
    }

    // -------------------------------------------------------
    // 2️⃣ логіка створення ExerciseContent
    // -------------------------------------------------------
    @PostMapping("/exercise-content/create")
    public String createExerciseContent(
            @ModelAttribute("ExerciseContent") ExerciseContent exerciseContent,
            @RequestParam("answers") List<String> answers,
            @RequestParam("correctAnswerIndex") Integer correctAnswerIndex,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        // 1) Зв’язуємо Exercise (бо exercise.id автоматично мапиться в ExerciseContent.exercise)
        UUID exerciseId = exerciseContent.getExercise().getId();
        Exercise exercise = exerciseService.findById(exerciseId);
        exerciseContent.setExercise(exercise);

        // 2) Зберігаємо відповіді
        exerciseContent.setAnswers(answers);
        exerciseContent.setCorrectAnswerIndex(correctAnswerIndex);

        // 3) Зберігаємо зображення, якщо є
        if (!imageFile.isEmpty()) {
            File stored = fileStorageService.storeFile(imageFile);
            exerciseContent.addFile(stored);
        }

        // 4) Зберігаємо в БД
        exerciseContentService.save(exerciseContent);

        return "redirect:/exercise-contents/list";
    }

    // -------------------------------------------------------
    // 3️⃣ список усіх ExerciseContent
    // -------------------------------------------------------
    @GetMapping("/exercise-contents/list")
    public String showAllContents(Model model) {
        List<ExerciseContentDto> list = exerciseContentService.findAll()
                .stream()
                .map(exerciseContentConverter::convert)
                .collect(Collectors.toList());

        model.addAttribute("ExerciseContentsList", list);
        return "exercise_content/ExerciseContentsList";
    }

    // -------------------------------------------------------
    // 4️⃣ список ExerciseContent для конкретної теми
    // -------------------------------------------------------
    @GetMapping("/exercise-contents/{exerciseId}")
    public String showContentsByExercise(@PathVariable UUID exerciseId, Model model) {
        List<ExerciseContentDto> list = exerciseContentService
                .findListExerciseContentByExercise(exerciseId)
                .stream()
                .map(exerciseContentConverter::convert)
                .collect(Collectors.toList());

        model.addAttribute("ExerciseContentsList", list);
        return "exercise_content/ExerciseContentsList";
    }

    // -------------------------------------------------------
    // 5️⃣ показ одного ExerciseContent
    // -------------------------------------------------------
    @GetMapping("/exercise-content/{id}")
    public String showExerciseContent(@PathVariable UUID id, Model model) {
        ExerciseContentDto dto = exerciseContentConverter.convert(exerciseContentService.findById(id));
        model.addAttribute("exercise", dto);
        return "exercise_content/exerciseContent";
    }

    // -------------------------------------------------------
    // 6️⃣ перевірка відповіді
    // -------------------------------------------------------
    @PostMapping("/exercise-content/check")
    public String checkAnswer(
            @CookieValue(name = "dblearning_user_id") String sessionId,
            @RequestParam UUID id,
            @RequestParam Integer selectedAnswer,
            Model model
    ) {
        ExerciseContent exerciseContent = exerciseContentService.findById(id);
        ExerciseContentDto dto = exerciseContentConverter.convert(exerciseContent);

        boolean isCorrect = dto.getCorrectAnswerIndex().equals(selectedAnswer);

        model.addAttribute("exercise", dto);
        model.addAttribute("selectedAnswer", selectedAnswer);
        model.addAttribute("isCorrect", isCorrect);

        if (!isCorrect) {
            String hint = geminiService.sendPrompt(dto.getQuestionText());
            model.addAttribute("aiHint", hint);
        }

        // --- ВИЗНАЧАЄМО СПРОБУ КОНКРЕТНОГО КОРИСТУВАЧА ---
        int attemptNumber = exerciseAttemptService.getNextAttemptNumberForUser(
                id,
                UUID.fromString(sessionId)
        );

        ExerciseAttempt attempt = new ExerciseAttempt();
        attempt.setExerciseContent(exerciseContent);
        attempt.setAttemptNumber(attemptNumber);
        attempt.setIsCorrect(isCorrect);
        attempt.setUsedAiHint(!isCorrect);
        attempt.setSelectedAnswer(selectedAnswer);
        attempt.setSessionId(UUID.fromString(sessionId));

        exerciseAttemptService.saveAttempt(attempt);

        return "exercise_content/exerciseContent";
    }



    @GetMapping("/exercise-content/image/{fileId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable UUID fileId) {

        File file = fileStorageService.getFile(fileId);

        if (file == null || file.getData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Type", file.getFileType())  // image/png, image/jpeg
                .body(file.getData());
    }


}
