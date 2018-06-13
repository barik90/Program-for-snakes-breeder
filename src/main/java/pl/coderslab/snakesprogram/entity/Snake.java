package pl.coderslab.snakesprogram.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "snakes")
public class Snake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "snake_id")
    private Long id;

    @NotEmpty
    @Column(name = "breedingNumber", unique = true)
    private String breedingNumber;

    @NotEmpty
    private String species;

    @NotEmpty
    private String morph;

    @NotEmpty
    private String sex;

    private LocalDate dateOfBirth;

//    @ManyToOne
//    @JoinColumn(name = "weight_id")
//    @NotNull
//    private Weight weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @NotNull
    private User user;

    @OneToMany(mappedBy = "snake")
    private List<Weight> weights = new ArrayList<>();

    //private double weight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "snake", cascade = CascadeType.ALL)
    private List<Feeding> feedings;

    public Snake() {}

    public Snake(String breedingNumber, String species, String morph, String sex, LocalDate dateOfBirth) {
        this.breedingNumber = breedingNumber;
        this.species = species;
        this.morph = morph;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    //    public Snake(String breedingNumber, String species, String morph, String sex, LocalDate dateOfBirth, Double weight) {
//        this.breedingNumber = breedingNumber;
//        this.species = species;
//        this.morph = morph;
//        this.sex = sex;
//        this.dateOfBirth = dateOfBirth;
//        this.weight = weight;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getMorph() {
        return morph;
    }

    public void setMorph(String morph) {
        this.morph = morph;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public Double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Double weight) {
//        this.weight = weight;
//    }

    public String getBreedingNumber() {
        return breedingNumber;
    }

    public void setBreedingNumber(String breedingNumber) {
        this.breedingNumber = breedingNumber;
    }

    public List<Feeding> getFeedings() {
        return feedings;
    }

    public void setFeedings(List<Feeding> feedings) {
        this.feedings = feedings;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "id=" + id +
                ", breedingNumber='" + breedingNumber + '\'' +
                ", species='" + species + '\'' +
                ", morph='" + morph + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", user=" + user +
                ", weights=" + weights +
                '}';
    }
}
