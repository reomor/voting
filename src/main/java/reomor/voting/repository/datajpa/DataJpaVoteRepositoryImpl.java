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
        if (!crudUserRepository.findById(userId).isPresent() || !crudRestaurantRepository.findById(restaurantId).isPresent()) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public boolean delete(LocalDateTime dateTime, int userId) {
        return crudVoteRepository.delete(dateTime, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
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
