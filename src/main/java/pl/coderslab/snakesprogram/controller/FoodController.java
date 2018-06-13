package pl.coderslab.snakesprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.snakesprogram.entity.Food;
import pl.coderslab.snakesprogram.repository.FoodRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/create")
    @ResponseBody
    public String create(Model model){
        Food food1 = new Food("mouse", "1-3g");
        Food food2 = new Food("mouse", "4-6g");
        Food food3 = new Food("mouse", "7-8g");
        Food food4 = new Food("mouse", "10-15g");
        Food food5 = new Food("mouse", "18-23g");
        Food food6 = new Food("mouse", "25g+");
        Food food7 = new Food("mouse", "30g+");
        Food food8 = new Food("rat", "5-7g");
        Food food9 = new Food("rat", "8-12g");
        Food food10 = new Food("rat", "35-45g");
        Food food11 = new Food("rat", "60-80g");
        Food food12 = new Food("rat", "90-110g");
        Food food13 = new Food("rat", "130-160g");
        Food food14 = new Food("rat", "180-210g");
        Food food15 = new Food("rat", "250-300g");
        Food food16 = new Food("rat", "300-500g");
        Food food17 = new Food("chicken", "1 day old");
        foodRepository.save(food1);
        foodRepository.save(food2);
        foodRepository.save(food3);
        foodRepository.save(food4);
        foodRepository.save(food5);
        foodRepository.save(food6);
        foodRepository.save(food7);
        foodRepository.save(food8);
        foodRepository.save(food9);
        foodRepository.save(food10);
        foodRepository.save(food11);
        foodRepository.save(food12);
        foodRepository.save(food13);
        foodRepository.save(food14);
        foodRepository.save(food15);
        foodRepository.save(food16);
        foodRepository.save(food17);
        return "Dodano pokarm";
    }

    @GetMapping("/all")
    public String all(Model model){
//        model.addAttribute("foods", foodRepository.findAll());
        model.addAttribute("foods", foodRepository.findAllByOrderByIdAsc());
//        return "index";
        return "foodList";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("food", new Food());
        return "foodForm";
    }

    @PostMapping("/add")
    public String add(@Valid Food food, BindingResult result){
        if(result.hasErrors()){
            return "foodForm";
        }
        foodRepository.save(food);
        return "redirect:/food/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        foodRepository.findById(id).ifPresent(food -> {

            model.addAttribute("food", food);
        });
        return "foodForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@Valid Food food, BindingResult result){
        if(result.hasErrors()){
            return "foodForm";
        }
        foodRepository.save(food);
        return "redirect:/food/all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        foodRepository.deleteById(id);
        return "redirect:/food/all";
    }

}
