package pl.coderslab.snakesprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.snakesprogram.repository.FeedingRepository;

@Controller
public class TestController {

    @Autowired
    FeedingRepository feedingRepository;

    @GetMapping("/test")
    public String test() {
        return "index";
    }

    @GetMapping("/table")
    public String table(Model model) {

        model.addAttribute("feedings", feedingRepository.findAll());
        return "feedingList";
    }
}
