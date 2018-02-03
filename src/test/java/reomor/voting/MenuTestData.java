package reomor.voting;

import reomor.voting.model.Menu;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import static reomor.voting.DishTestData.*;
import static reomor.voting.RestaurantTestData.*;

public class MenuTestData {
    public static final Menu menu0 = new Menu(0, LocalDate.of(2017, Month.DECEMBER, 3), restaurant100, dish_menu0);
    public static final Menu menu1 = new Menu(1, LocalDate.of(2017, Month.DECEMBER, 3), restaurant101, dish_menu1);

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dishes");
        assertThat(actual.getRestaurant()).isEqualToIgnoringGivenFields(expected.getRestaurant(), "menus");
        assertThat(actual.getDishes()).usingElementComparatorIgnoringFields("menu").isEqualTo(expected.getDishes());
    }

    public static void assertMatch(Iterable<Menu> actual, Menu ... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dishes").isEqualTo(expected);
    }
}