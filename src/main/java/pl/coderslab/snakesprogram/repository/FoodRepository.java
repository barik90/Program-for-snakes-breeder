package pl.coderslab.snakesprogram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.snakesprogram.entity.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

      List<Food> findAllByOrderByIdAsc();

}
