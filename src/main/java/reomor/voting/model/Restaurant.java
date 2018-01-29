package reomor.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant extends BaseEntity {
    @Column(name = "NAME")
    @NotBlank
    @Size(max = 150)
    private String name;

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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + "'" +
                "}";
    }
}
