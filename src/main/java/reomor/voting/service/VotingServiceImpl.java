package reomor.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reomor.voting.model.Vote;
import reomor.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static reomor.voting.util.ValidationUtil.checkNotFound;
import static reomor.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VotingServiceImpl implements VotingService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote make(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote for making must not be null");
        return checkNotFound(repository.make(vote, userId, restaurantId), "make vote=" + vote + " userId=" + userId + " restaurantId=" + restaurantId);
    }

    @Override
    public Vote update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote for update must not be null");
        return checkNotFound(repository.make(vote, userId, restaurantId), "update vote=" + vote + " userId=" + userId + " restaurantId=" + restaurantId);
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public void delete(LocalDateTime dateTime, int userId) {
        Assert.notNull(dateTime, "dateTime for delete must not be null");
        checkNotFoundWithId(repository.delete(dateTime, userId), userId);
    }

    @Override
    public Vote get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public Vote get(LocalDateTime dateTime, int userId) {
        Assert.notNull(dateTime, "dateTime for delete must not be null");
        return checkNotFound(repository.get(dateTime, userId), "dateTime=" + dateTime + " userId=" + userId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        checkNotFoundWithId(!repository.getAllByUser(userId).isEmpty(), userId);
        return repository.getAllByUser(userId);
    }
}
