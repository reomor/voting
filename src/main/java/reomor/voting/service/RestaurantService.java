package reomor.voting.service;

import reomor.voting.model.Dish;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.to.RestaurantWithMenu;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RestaurantService {
    // Restaurant
    Restaurant add(Restaurant restaurant);

    void update(Restaurant restaurant, int restaurantId);

    void delete(int restaurantId);

    Restaurant get(int restaurantId);

    // Menu
    Menu addMenu(Menu menu);

    void updateMenu(Menu menu, int menuId);

    void deleteMenu(int menuId);

    Menu getMenu(int menuId, LocalDate date);

    List<Menu> getAllMenusByDate(LocalDate date);
}
