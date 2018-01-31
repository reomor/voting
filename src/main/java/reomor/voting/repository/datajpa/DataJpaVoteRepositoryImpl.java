package reomor.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Vote;
import reomor.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Vote make(Vote vote, int userId, int restaurantId) {
        Vote existed = get(vote.getDateTime(), userId);
        if (vote.isNew() && existed != null) {
            vote.setId(existed.getId());
        }
        if (!crudUserRepository.findById(userId).isPresent()) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        if (!crudRestaurantRepository.findById(restaurantId).isPresent()) {
            return null;
        }
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(LocalDateTime dateTime, int userId) {
        return crudVoteRepository.delete(dateTime, userId) != 0;
    }

    @Override
    public Vote get(LocalDateTime dateTime, int userId) {
        return crudVoteRepository.get(dateTime, userId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return crudVoteRepository.getAllByUser(userId);
    }
}
