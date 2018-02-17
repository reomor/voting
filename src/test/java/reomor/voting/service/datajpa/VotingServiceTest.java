package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Vote;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.VotingService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;

import static reomor.voting.UserTestData.*;
import static reomor.voting.RestaurantTestData.*;
import static reomor.voting.VoteTestData.*;

public class VotingServiceTest extends AbstractServiceTest {

    @Autowired
    private VotingService service;

    @Test
    public void getTest() {
        Vote actual = service.get(2, 1000);
        assertMatch(actual, vote2);
    }

    @Test
    public void updateTest() {
        Vote expected = service.get(1, 1001);
        expected.setRestaurant(restaurant102);
        service.update(expected, 1001, 101);
        assertMatch(service.get(1, 1001), expected);
    }

    @Test
    public void deleteTest() {
        service.delete(0, 1000);
        assertMatch(service.getAllByUser(user1000.getId()), Collections.singletonList(vote2));
    }

    @Test
    public void makeNewTest() {
        Vote vote = new Vote(null, LocalDateTime.of(2017, Month.DECEMBER, 5, 10, 0, 0), null, null);
        Vote created = service.make(vote, 1000, 100);
        System.out.println("+++ compare actual and expected +++");
        assertMatch(service.getAllByUser(1000),  created, vote2, vote0);
    }

    @Test
    public void getAllByUserTest() {
        assertMatch(service.getAllByUser(1000), vote2, vote0);
    }


}