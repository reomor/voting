package reomor.voting.repository;

import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote make(Vote vote, int userId, int restaurantId);

    boolean delete(LocalDateTime dateTime, int userId);

    Vote get(LocalDateTime dateTime, int userId);

    List<Vote> getAllByUser(int userId);

    default List<Vote> getAllByDate(LocalDate date) {
        /*TODO*/
        return null;
    }

    default List<Vote> getAll() {
        /*TODO*/
        return null;
    }
}