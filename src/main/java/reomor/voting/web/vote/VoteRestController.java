package reomor.voting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reomor.voting.model.Vote;
import reomor.voting.util.exception.CustomErrorType;
import reomor.voting.util.exception.TimeToVoteLeftException;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static reomor.voting.util.VoteConstraintsUtils.*;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/profile/votes";

    @Override
    @GetMapping
    public List<Vote> getAllByUser() {
        return super.getAllByUser();
    }

    @GetMapping(value = "/{id}")
    public Vote getVote(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PostMapping(value = "/restaurant/{restaurantId}")
    public ResponseEntity<?> makeVote(@PathVariable("restaurantId") int restaurantId) {

        if (isLateTime(LocalTime.now())) {
            //return new ResponseEntity<CustomErrorType>(new CustomErrorType("it's too late to vote"), HttpStatus.BAD_REQUEST);
            throw new TimeToVoteLeftException("it's too late to vote");
        }

        Vote vote = new Vote();
        vote.setDateTime(LocalDateTime.now());

        //vote.setDateTime(LocalDateTime.of(2017, 12, 3, 10, 59));

        Vote create = super.make(vote, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/menus/{id}")
                .buildAndExpand(create.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(create);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
