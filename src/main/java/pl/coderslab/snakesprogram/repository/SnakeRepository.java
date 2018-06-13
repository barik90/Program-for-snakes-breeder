package pl.coderslab.snakesprogram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.snakesprogram.entity.Snake;

import java.util.List;

public interface SnakeRepository extends JpaRepository<Snake, Long> {

    List<Snake> findAllByMorph(String morph);


}
