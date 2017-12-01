package reomor.voting.model;

import java.time.LocalDateTime;
import java.util.Set;

public class User extends DatabaseEntity {
    private String name;
    private String email;
    private Set<Roles> roles;
    private Restaurant restaurant;
    private LocalDateTime restaurantChoiceDateTime;
}
