package pl.coderslab.snakesprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.snakesprogram.entity.Feeding;
import pl.coderslab.snakesprogram.entity.Food;
import pl.coderslab.snakesprogram.entity.Snake;
import pl.coderslab.snakesprogram.entity.Weight;
import pl.coderslab.snakesprogram.repository.FeedingRepository;
import pl.coderslab.snakesprogram.repository.FoodRepository;
import pl.coderslab.snakesprogram.repository.SnakeRepository;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/feeding")
public class FeedingController {

    @Autowired
    FeedingRepository feedingRepository;

    @Autowired
    SnakeRepository snakeRepository;

    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("feedings", feedingRepository.findAll());
        return "feedingList";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("feeding", new Feeding());
        return "feedingForm";
    }

    @PostMapping("/add")
    public String add(@Valid Feeding feeding, BindingResult result){
        if(result.hasErrors()){
            return "feedingForm";
        }
//        for (Feeding f: feedings) {
//            f.setDate(LocalDate.now());
//            f.setQuantity(1);
//            feedingRepository.save(f);
//        }

//        LocalDate date = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
//        String text = date.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);
        feeding.setDate(LocalDate.now());
//        feeding.setQuantity(1);
        feedingRepository.save(feeding);
        return "redirect:/feeding/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        feedingRepository.findById(id).ifPresent(feeding -> {

            model.addAttribute("feeding", feeding);
        });
//        return "feedingFormEdit";
        return "feedingForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@Valid Feeding feeding, BindingResult result){
        if(result.hasErrors()){
//            return "feedingFormEdit";
            return "feedingForm";
        }


        feedingRepository.save(feeding);
        return "redirect:/feeding/all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        feedingRepository.deleteById(id);
        return "redirect:/feeding/all";
    }


    @PostMapping("/test")
    public String added(@Valid Feeding feeding, BindingResult result){
        if(result.hasErrors()){
            return "feedingForm";
        }
        feedingRepository.save(feeding);
        return "redirect:/feeding/all";
    }

    @ModelAttribute("snakeList")
    public List<Snake> snakeList(){
        return snakeRepository.findAll();
    }

    @ModelAttribute("foodList")
    public List<Food> foodList(){
        return foodRepository.findAll();
    }

}
