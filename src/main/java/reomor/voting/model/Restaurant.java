package reomor.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant extends BaseEntity {
    @Column(name = "NAME")
    @NotBlank
    @Size(max = 150)
    private String name;

    @JsonBackReference
    //@JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    private List<Menu> menus;

    public Restaurant() {}

    public Restaurant(Restaurant restaurant) {
        super(restaurant.id);
        this.name = restaurant.name;
    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + "'" +
                "}";
    }
}
