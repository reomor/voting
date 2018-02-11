package reomor.voting.service;

import reomor.voting.model.Dish;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.to.MenuTo;
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

    Restaurant getWithMenus(int restaurantId);

    Restaurant getWithMenuByDate(int restaurantId, LocalDate date);

    List<Restaurant> getAll();

    // Menu
    Menu addMenu(MenuTo menuTo, int restaurantId);

    void updateMenu(MenuTo menuTo, int restaurantId, int menuId);

    void deleteMenu(int restaurantId, int menuId);

    Menu getMenuByIdAndRestaurant(int menuId, int restaurantId);

    Menu getMenuByRestaurantAndDate(int restaurantId, LocalDate date);

    List<Menu> getAllMenusByDate(LocalDate date);
}
