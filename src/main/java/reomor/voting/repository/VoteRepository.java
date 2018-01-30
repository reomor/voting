package reomor.voting.repository;

import reomor.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote make(Vote vote, int userId, int restaurantId);

    boolean delete(LocalDateTime dateTime, int userId);

    Vote get(LocalDateTime dateTime, int userId);

    List<Vote> getAllByUser(int userId);
}