package reomor.voting.service;

import reomor.voting.model.Dish;
import reomor.voting.model.Restaurant;
import reomor.voting.to.RestaurantWithMenu;

import java.util.Date;
import java.util.List;

public interface RestaurantService {
    // Restaurant
    void add(Restaurant restaurant);

    void get(int id);

    void update(Restaurant restaurant, int id);

    void delete(int id);

    // Menu
    void saveMenu(int id, List<Dish> menu);

    void getMenu(int id);

    //List<RestaurantWithMenu> getAllRestaurantWithMenus(Date date);
}
