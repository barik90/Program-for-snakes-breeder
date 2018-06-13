package pl.coderslab.snakesprogram.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.snakesprogram.entity.Feeding;
import pl.coderslab.snakesprogram.entity.Snake;
import pl.coderslab.snakesprogram.entity.Weight;
import pl.coderslab.snakesprogram.repository.FeedingRepository;
import pl.coderslab.snakesprogram.repository.SnakeRepository;
import pl.coderslab.snakesprogram.repository.WeightRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/snake")
public class SnakeController {

    @Autowired
    SnakeRepository snakeRepository;

    @Autowired
    WeightRepository weightRepository;

    @Autowired
    FeedingRepository feedingRepository;

    @GetMapping("/create")
    @ResponseBody
    public String create(Model model){
        Snake snake = new Snake("RB-AS2015M","Corn snake", "Anery Scaleless", "male", LocalDate.now());
        Snake snake2 = new Snake("RB-S2013F","Corn snake", "Scaleless", "female", LocalDate.now());
        Snake snake3 = new Snake("RB-A2016M","Corn snake", "Amel", "male", LocalDate.now());
        Snake snake4 = new Snake("RB-GB2014F","Corn snake", "Ghost Blood", "female", LocalDate.now());
       snakeRepository.save(snake);
       snakeRepository.save(snake2);
       snakeRepository.save(snake3);
       snakeRepository.save(snake4);
        return "Dodano węże";
    }

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("snakes", snakeRepository.findAll());
        return "snakeList";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("snake", new Snake());
        return "snakeForm";
    }

    @PostMapping("/add")
    public String add(@Valid Snake snake, BindingResult result){
        if(result.hasErrors()){
            return "snakeForm";
        }
        snakeRepository.save(snake);
        return "redirect:/snake/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        snakeRepository.findById(id).ifPresent(snake -> {

            System.out.println(snake.getMorph());
            model.addAttribute("snake", snake);
        });
        return "snakeForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@Valid Snake snake, BindingResult result){
        if(result.hasErrors()){
            return "snakeForm";
        }
        snakeRepository.save(snake);
        return "redirect:/snake/all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        snakeRepository.deleteById(id);
        return "redirect:/snake/all";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model){
        snakeRepository.findById(id).ifPresent(snake -> {
            model.addAttribute("snake", snake);

//               List<Feeding> last5 = feedingRepository.fintLastTop5(id);
               List<Feeding> last5 = feedingRepository.findTop5BySnake(snake);

            model.addAttribute("last5", last5);

            List<Weight> weights = weightRepository.findAllBySnakeLike(snake);
            List<LocalDate> dates = new ArrayList<>();
            List<Integer> weightList = new ArrayList<>();
            for (Weight w: weights) {
                dates.add(w.getDateOfWeighing());
                weightList.add(w.getWeight());
            }
            model.addAttribute("listDate", dates);
            model.addAttribute("listWeight", weightList);
        });
        return "snakeDetails";
    }


}

//przy edycji kasuje się data. trzeda to przerobic
