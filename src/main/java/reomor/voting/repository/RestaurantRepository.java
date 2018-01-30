package reomor.voting.repository;

import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {
    Restaurant add(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Menu addMenu(Menu menu, int restaurantId);

    boolean deleteMenu(int menuId);

    boolean deleteMenu(int menuId, LocalDate date);

    Menu getMenu(int menuId, LocalDate date);

    List<Menu> getAllMenusByDate(LocalDate date);
}
