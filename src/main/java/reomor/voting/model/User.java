package reomor.voting.model;

import java.time.LocalDateTime;
import java.util.Set;

public class User extends DatabaseEntity {
    private String name;
    private String email;
    private Set<Roles> roles;
    private Restaurant restaurant;
    private LocalDateTime restaurantChoiceDateTime;

    User(int id, String name, String email, Set<Roles> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getRestaurantChoiceDateTime() {
        return restaurantChoiceDateTime;
    }

    public void setRestaurantChoiceDateTime(LocalDateTime restaurantChoiceDateTime) {
        this.restaurantChoiceDateTime = restaurantChoiceDateTime;
    }
}
