package reomor.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "VOTES")
@JsonIgnoreProperties({"user"})
public class Vote extends BaseEntity {
    @Column(name = "DATE_TIME")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    public Vote() {}

    public Vote(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = new User(user);
        this.restaurant = new Restaurant(restaurant);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
