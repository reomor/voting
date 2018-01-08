package reomor.voting.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MENUS")
public class Menu extends BaseEntity {
    @Column(name = "DATE_TIME")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTAURANT_ID")
    @NotNull
    private Restaurant restaurant;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "DISHES", joinColumns = @JoinColumn(name = "MENU_ID"))
    @GenericGenerator(name = "gen", strategy = "auto")
    @CollectionId(columns = {@Column(name = "ID")}, generator = "gen", type = @Type(type = "int"))
    private List<Dish> dishes;
}
