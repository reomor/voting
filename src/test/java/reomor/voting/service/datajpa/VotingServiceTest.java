package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Vote;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.VotingService;

import java.time.LocalDateTime;
import java.time.Month;

import static reomor.voting.UserTestData.*;
import static reomor.voting.RestaurantTestData.*;
import static reomor.voting.VoteTestData.*;

public class VotingServiceTest extends AbstractServiceTest {

    @Autowired
    private VotingService service;

    @Test
    public void getTest() {
        assertMatch(service.get(LocalDateTime.of(2017, Month.DECEMBER, 3, 10, 0, 0), 1000), vote0);
    }

    @Test
    public void makeNewTest() {
        Vote vote = new Vote(null, LocalDateTime.of(2017, Month.DECEMBER, 4, 10, 0, 0), user1000, restaurant100);
        service.make(vote, 1000, 100);
        assertMatch(service.get(LocalDateTime.of(2017, Month.DECEMBER, 4, 10, 0, 0), vote.getUser().getId()), vote);
    }

    @Test
    public void getAllByUserTest() {
        assertMatch(service.getAllByUser(1000), vote2, vote0);
    }

    @Test
    public void deleteTest() {
        service.delete( LocalDateTime.of(2017, Month.DECEMBER, 3, 10, 0, 0), user1000.getId());
        assertMatch(service.getAllByUser(user1000.getId()), vote2);
    }
}