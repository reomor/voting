package reomor.voting.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reomor.voting.model.Vote;

import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/profile/votes";

    @Override
    @GetMapping
    public List<Vote> getAllByUser() {
        final List<Vote> allByUser = super.getAllByUser();
        return super.getAllByUser();
    }

    @Override
    public Vote make(Vote vote, int restaurantId) {
        return super.make(vote, restaurantId);
    }

    @Override
    public Vote update(Vote vote, int restaurantId) {
        return super.update(vote, restaurantId);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public Vote get(int id) {
        return super.get(id);
    }
}
