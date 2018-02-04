package reomor.voting.repository;

import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {
    Restaurant add(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getWithMenus(int restaurantId);

    List<Restaurant> getAll();

    Menu addMenu(Menu menu, int restaurantId);

    boolean deleteMenu(int menuId);

    boolean deleteMenu(int menuId, LocalDate date);

    Menu getMenu(int menuId);

    Menu getMenu(int menuId, LocalDate date);

    Menu getMenuByIdAndRestaurant(int menuId, int restaurantId);

    Menu getMenuByRestaurantAndDate(int restaurantId, LocalDate date);

    List<Menu> getAllMenusByDate(LocalDate date);

    List<Menu> getAllMenusByRestaurant(int restaurantId);
}
