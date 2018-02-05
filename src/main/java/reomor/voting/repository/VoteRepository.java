package reomor.voting.repository;

import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VoteRepository {

    Vote make(Vote vote, int userId, int restaurantId);

    boolean delete(int id, int userId);

    boolean delete(LocalDateTime dateTime, int userId);

    Vote get(int id, int userId);

    Vote get(LocalDateTime dateTime, int userId);

    List<Vote> getAllByUser(int userId);
}