package ihorko.work.db_learning.controller;

import ihorko.work.db_learning.db.entity.Exercise;
import ihorko.work.db_learning.service.ExerciseService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public void setExerciseService(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercise/create/page")
    public String showCreateExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "exerciseCreate";
    }

    @PostMapping("/exercise/create")
    public String createExercise(Exercise exercise) {
        exerciseService.save(exercise);
        return "redirect:/exercise/create/page";
    }

    @GetMapping("/exercises/list")
    public String showListExercises(Model model) {
        model.addAttribute("exercisesLists", ListUtils.partition(exerciseService.findAll(), 4));
        return "exercisesList";
    }

    @GetMapping("/exercises/list/{topic}")
    public String showListExercisesByTopic(@PathVariable String topic, Model model) {
        model.addAttribute("exercisesLists", ListUtils.partition(exerciseService.findByTopic(topic), 4));
        return "exercisesList";
    }
}
