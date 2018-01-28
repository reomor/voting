package reomor.voting;

import reomor.voting.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final Restaurant restaurant100 = new Restaurant(100, "MacDonalds");
    public static final Restaurant restaurant101 = new Restaurant(101, "KFC");
    public static final Restaurant restaurant102 = new Restaurant(102, "BurgerKing");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant ... expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
