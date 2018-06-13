package pl.coderslab.snakesprogram.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "weight")
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "weight")
//    private List<Snake> snakes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "snake_id")
    @NotNull
    private Snake snake;

    private Integer weight;

    private LocalDate dateOfWeighing;

    public Weight() {}

    public Weight(Snake snake, Integer weight, LocalDate dateOfWeighing) {
        this.snake = snake;
        this.weight = weight;
        this.dateOfWeighing = dateOfWeighing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfWeighing() {
        return dateOfWeighing;
    }

    public void setDateOfWeighing(LocalDate dateOfWeighing) {
        this.dateOfWeighing = dateOfWeighing;
    }
}
