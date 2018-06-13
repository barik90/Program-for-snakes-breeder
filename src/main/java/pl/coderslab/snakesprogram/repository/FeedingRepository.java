package pl.coderslab.snakesprogram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.snakesprogram.entity.Feeding;
import pl.coderslab.snakesprogram.entity.Snake;

import java.util.List;

public interface FeedingRepository extends JpaRepository<Feeding, Long> {

    List<Feeding> findTop5BySnake(Snake snake);

    @Query(value = "SELECT * FROM `feeding` WHERE snake_id=?1 ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Feeding> fintLastTop5(Long id);
}