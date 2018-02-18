package reomor.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "MENUS")
public class Menu extends BaseEntity {
    @Column(name = "DATE_TIME")
    @NotNull
    private LocalDate date;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    // https://stackoverflow.com/questions/28179369/spring-rest-json-can-not-handle-managed-back-reference-defaultreference-415
    //@JsonManagedReference
    //@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @NotNull
    @BatchSize(size = 100)
    private Restaurant restaurant;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    // http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private List<Dish> dishes = null;

    public Menu() {
    }

    public Menu(Menu menu) {
        super(menu.id);
        this.date = menu.date;
        this.restaurant = new Restaurant(menu.restaurant);
        setDishes(menu.dishes);
    }

    public Menu (Integer id, LocalDate date, Restaurant restaurant, Collection<Dish> dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        setDishes(dishes);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<Dish> dishes) {
        if (this.dishes == null) {
            if (CollectionUtils.isEmpty(dishes)) {
                this.dishes = Collections.emptyList();
            } else {
                this.dishes = new ArrayList<>(dishes);
            }
        } else {
            this.dishes.clear();
            this.dishes.addAll(dishes);
        }
    }
}
