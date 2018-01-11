package reomor.voting.service;

import reomor.voting.model.Vote;
import reomor.voting.to.RestaurantWithMenu;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VotingService {
    Vote make(LocalDateTime dateTime, int userId, int restaurantId);

    boolean delete(LocalDateTime dateTime, int userId);

    Vote get(LocalDateTime dateTime, int userId);
}
