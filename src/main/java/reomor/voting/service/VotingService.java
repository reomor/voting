package reomor.voting.service;

import reomor.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VotingService {
    Vote make(Vote vote, int userId, int restaurantId);

    void delete(LocalDateTime dateTime, int userId);

    Vote get(LocalDateTime dateTime, int userId);

    List<Vote> getAllByUser(int userId);
}
