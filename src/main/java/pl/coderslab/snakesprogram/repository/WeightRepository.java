package pl.coderslab.snakesprogram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.snakesprogram.entity.Snake;
import pl.coderslab.snakesprogram.entity.Weight;
import java.util.List;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight> findAllBySnakeLike(Snake snake);
}
