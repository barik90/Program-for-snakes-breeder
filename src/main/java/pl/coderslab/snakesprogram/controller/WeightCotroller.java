package pl.coderslab.snakesprogram.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.snakesprogram.entity.Feeding;
import pl.coderslab.snakesprogram.entity.Snake;
import pl.coderslab.snakesprogram.entity.Weight;
import pl.coderslab.snakesprogram.repository.SnakeRepository;
import pl.coderslab.snakesprogram.repository.WeightRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/weight")
public class WeightCotroller {

    @Autowired
    WeightRepository weightRepository;

    @Autowired
    SnakeRepository snakeRepository;

    @GetMapping("/create")
    @ResponseBody
    public String create(Model model){

        snakeRepository.findById(2L).ifPresent(snake -> {

            Weight weight = new Weight(snake, 100, LocalDate.of(2018,1,10));
            Weight weight2 = new Weight(snake, 128, LocalDate.of(2018,2,10));
            Weight weight3 = new Weight(snake, 160, LocalDate.of(2018,3,10));
            Weight weight4 = new Weight(snake, 222, LocalDate.of(2018,4,10));
            Weight weight5 = new Weight(snake, 313, LocalDate.of(2018,5,10));

            weightRepository.save(weight);
            weightRepository.save(weight2);
            weightRepository.save(weight3);
            weightRepository.save(weight4);
            weightRepository.save(weight5);
        });

        snakeRepository.findById(3L).ifPresent(snake2 -> {

            Weight weight6 = new Weight(snake2, 20, LocalDate.of(2018,1,7));
            Weight weight7 = new Weight(snake2, 35, LocalDate.of(2018,2,7));
            Weight weight8 = new Weight(snake2, 60, LocalDate.of(2018,3,7));
            Weight weight9 = new Weight(snake2, 78, LocalDate.of(2018,4,7));
            Weight weight10 = new Weight(snake2, 100, LocalDate.of(2018,5,7));

            weightRepository.save(weight6);
            weightRepository.save(weight7);
            weightRepository.save(weight8);
            weightRepository.save(weight9);
            weightRepository.save(weight10);
        });

        return "Dodano wagi";
    }

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("weights", weightRepository.findAll());
        return "weightList";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("weight", new Weight());
        return "weightForm";
    }

    @PostMapping("/add")
    public String add(@Valid Weight weight, BindingResult result){
        if(result.hasErrors()){
            return "weightForm";
        }
        weight.setDateOfWeighing(LocalDate.now());
        weightRepository.save(weight);
        return "redirect:/weight/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        weightRepository.findById(id).ifPresent(weight -> {

            model.addAttribute("weight", weight);
        });

        return "weightForm";
    }

//    @PostMapping("/{id}/edit")
//    public String edit(@Valid Weight weight, BindingResult result){
//        if(result.hasErrors()){
//            return "weightForm";
//        }
//        weightRepository.save(weight);
//        return "redirect:/weight/all";
//    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        weightRepository.deleteById(id);
        return "redirect:/weight/all";
    }

    @GetMapping("/{id}/test")
    public String test(Model model, @PathVariable Long id){
        snakeRepository.findById(id).ifPresent(snake -> {
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

        return "charts";
    }


    @ModelAttribute("snakeList")
    public List<Snake> snakeList(){
        return snakeRepository.findAll();
    }
}
