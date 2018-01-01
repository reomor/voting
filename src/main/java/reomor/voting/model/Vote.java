package reomor.voting.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Vote extends BaseEntity {
    User user;
    Restaurant restaurant;
    LocalDateTime dateTime;

    Vote(int id) {
        super(id);
    }
}
