package reomor.voting.service;

import reomor.voting.model.Dish;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final Restaurant restaurant100 = new Restaurant(100, "MacDonalds");
    public static final Restaurant restaurant101 = new Restaurant(101, "KFC");


    public static final Dish dish0 = new Dish(0, "burger", 17900);
    public static final Dish dish1 = new Dish(1, "fri", 7500);
    public static final Dish dish2 = new Dish(2, "naggets", 10500);

    public static final Dish dish3 = new Dish(3, "burger", 10500);
    public static final Dish dish4 = new Dish(4, "naggets", 10500);

    public static final List<Dish> dish_menu0 = Arrays.asList(dish0, dish1, dish2);
    public static final List<Dish> dish_menu1 = Arrays.asList(dish3, dish4);

    public static final Menu menu0 = new Menu(0, LocalDate.of(2017, Month.DECEMBER, 3), restaurant100, dish_menu0);
    public static final Menu menu1 = new Menu(1, LocalDate.of(2017, Month.DECEMBER, 3), restaurant101, dish_menu1);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dishes");
        assertThat(actual.getRestaurant()).isEqualToComparingFieldByField(expected.getRestaurant());
        assertThat(actual.getDishes()).usingElementComparatorIgnoringFields("menu").isEqualTo(expected.getDishes());
    }
    public static void assertMatch(Iterable<Menu> actual, Menu ... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dishes").isEqualTo(expected);
    }
}
