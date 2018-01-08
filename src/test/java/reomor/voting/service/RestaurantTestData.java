package reomor.voting.service;

import reomor.voting.model.Restaurant;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
