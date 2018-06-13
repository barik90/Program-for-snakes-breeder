package pl.coderslab.snakesprogram.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feeding")
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeding_id")
    private Long id;
//(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "snake_id")
    private Snake snake;
//(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private Integer quantity;
//    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDate date;
    private String note;

    public Feeding() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
