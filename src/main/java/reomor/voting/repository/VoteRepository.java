package reomor.voting.repository;

import reomor.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote make(Vote vote, LocalDateTime dateTime);

    boolean delete(int id);

    Vote get(int userId, LocalDate date);
}