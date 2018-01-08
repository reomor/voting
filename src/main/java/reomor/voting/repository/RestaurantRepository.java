package reomor.voting.repository;

import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {
    Restaurant add(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Menu addMenu(Menu menu);

    boolean deleteMenu(int menuId);

    Menu getMenu(int menuId, LocalDate date);

    List<Menu> getAllMenusByDate(LocalDate date);
}
