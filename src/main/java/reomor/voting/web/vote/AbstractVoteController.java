package reomor.voting.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.AuthorizedUser;
import reomor.voting.model.Vote;
import reomor.voting.service.VotingService;

import java.util.List;

public class AbstractVoteController {
    private static final Logger log = LoggerFactory.getLogger(AbstractVoteController.class);

    @Autowired
    private VotingService service;

    public Vote make(Vote vote, int restaurantId) {
        int userId = AuthorizedUser.id();
        return service.make(vote, userId, restaurantId);
    }

    public Vote update(Vote vote, int restaurantId) {
        int userId = AuthorizedUser.id();
        return service.update(vote, userId, restaurantId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public Vote get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public List<Vote> getAllByUser() {
        int userId = AuthorizedUser.id();
        return service.getAllByUser(userId);
    }
}
