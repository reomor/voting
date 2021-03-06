package reomor.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "DISHES")
public class Dish extends BaseEntity {
    @Column(name = "DISH_NAME")
    @NotBlank
    @Size(max = 100)
    private String description;

    @Column(name = "PRICE")
    @Range(min = 0)
    private Integer price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public Dish() {}

    public Dish(Dish dish) {
        super(dish.id);
        this.description = dish.description;
        this.price = dish.price;
    }

    public Dish(Integer id, String description, Integer price) {
        super(id);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
