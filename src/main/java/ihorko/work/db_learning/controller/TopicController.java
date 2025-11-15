package ihorko.work.db_learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    @GetMapping("/topic")
    public String showTopicPage() {
        return "/topic";
    }
}
