package reomor.voting.to;

import org.springframework.util.CollectionUtils;
import reomor.voting.model.Dish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTo {
    private Integer id;
    private LocalDate date;
    private List<Dish> dishes;

    public MenuTo() {
        this.id = null;
        this.date = null;
        dishes = Collections.emptyList();
    }

    public MenuTo(Integer id, LocalDate date, List<Dish> dishes) {
        this.id = id;
        this.date = date;
        setDishes(dishes);
    }

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
        this.dishes =  Collections.emptyList();
        if (CollectionUtils.isEmpty(dishes)) {
            return;
        }
        this.dishes = dishes.stream()
                .map(Dish::new)
                .collect(Collectors.toList());
    }
}
