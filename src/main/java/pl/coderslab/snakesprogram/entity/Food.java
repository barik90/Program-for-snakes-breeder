package pl.coderslab.snakesprogram.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @NotEmpty
    private String type;

    @NotEmpty
    private String size;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food", cascade = CascadeType.ALL )
    private List<Feeding> feedings;

    public Food() {}

    public Food(String type, String size) {
        this.type = type;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Feeding> getFeedings() {
        return feedings;
    }

    public void setFeedings(List<Feeding> feedings) {
        this.feedings = feedings;
    }

    public String getFullName(Food food){
        String fullName = getType() + " " + getSize();
        return fullName;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
