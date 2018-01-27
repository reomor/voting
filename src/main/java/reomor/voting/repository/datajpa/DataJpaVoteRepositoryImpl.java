package reomor.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    //meal.setUser(crudUserRepository.getOne(userId));

    @Override
    public Vote make(Vote vote, int userId, int restaurantId) {
        vote.setUser(crudUserRepository.getOne(userId));
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
}
