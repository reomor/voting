package reomor.voting.repository;

import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Menu saveMenu(Menu menu);

    boolean deleteMenu(int menuId);

    Menu getMenu(int menuId, LocalDate date);
}
