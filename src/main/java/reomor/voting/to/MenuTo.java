package reomor.voting.to;

import reomor.voting.model.Dish;

import java.time.LocalDate;
import java.util.List;

public class MenuTo {
    private Integer id;
    private LocalDate date;
    private List<Dish> dishes;

    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
