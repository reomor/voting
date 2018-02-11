package reomor.voting;

import reomor.voting.model.Dish;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DishTestData {
    public static final Dish dish0 = new Dish(0, "burger", 17900);
    public static final Dish dish1 = new Dish(1, "fri", 7500);
    public static final Dish dish2 = new Dish(2, "naggets", 10500);

    public static final Dish dish3 = new Dish(3, "burger", 24000);
    public static final Dish dish4 = new Dish(4, "milk", 7900);

    public static final Dish dish5 = new Dish(5, "coffee", 9000);
    public static final Dish dish6 = new Dish(6, "steak", 25000);

    public static final List<Dish> dish_menu0 = Arrays.asList(dish0, dish1, dish2);
    public static final List<Dish> dish_menu1 = Arrays.asList(dish3, dish4);
    public static final List<Dish> dish_menu2 = Collections.singletonList(dish5);
    public static final List<Dish> dish_menu3 = Collections.singletonList(dish6);
}
