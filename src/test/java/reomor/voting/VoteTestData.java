package reomor.voting;

import reomor.voting.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

import static reomor.voting.UserTestData.*;
import static reomor.voting.RestaurantTestData.*;

public class VoteTestData {
    public static final Vote vote0 = new Vote(0, LocalDateTime.of(2017, Month.DECEMBER, 3, 10, 0, 0), user1000, restaurant100);
    public static final Vote vote1 = new Vote(1, LocalDateTime.of(2017, Month.DECEMBER, 3, 10, 30, 0), user1000, restaurant101);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
        assertThat(actual.getUser()).isEqualTo(expected.getUser());
        assertThat(actual.getRestaurant()).isEqualTo(expected.getRestaurant());
    }
}
