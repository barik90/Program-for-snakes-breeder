package pl.coderslab.snakesprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.snakesprogram.entity.Feeding;
import pl.coderslab.snakesprogram.entity.Food;
import pl.coderslab.snakesprogram.entity.Snake;
import pl.coderslab.snakesprogram.repository.FeedingRepository;
import pl.coderslab.snakesprogram.repository.FoodRepository;
import pl.coderslab.snakesprogram.repository.SnakeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    FeedingRepository feedingRepository;

    @Autowired
    SnakeRepository snakeRepository;

    @Autowired
    FoodRepository foodRepository;


    @GetMapping("")
    public String add(){

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("today", LocalDateTime.now());
        return "logoutPage";
    }

    @ModelAttribute("snakeList")
    public List<Snake> snakeList(){
        return snakeRepository.findAll();
    }

    @ModelAttribute("foodList")
    public List<Food> foodList(){
        return foodRepository.findAll();
    }

    @ModelAttribute("feedingList")
    public List<Feeding> feedingList(){
        return feedingRepository.findAll();
    }

}
