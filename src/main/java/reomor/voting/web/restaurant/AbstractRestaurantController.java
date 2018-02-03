package reomor.voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    RestaurantService service;

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Restaurant add(Restaurant restaurant) {
        log.info("add Restaurant");
        return service.add(restaurant);
    }

    public void delete(int restaurantId) {
        log.info("delete Restaurant{}", restaurantId);
        service.delete(restaurantId);
    }

    public void update(Restaurant restaurant, int restaurantId) {
        log.info("update Restaurant{} with id{}", restaurant, restaurantId);
        service.update(restaurant, restaurantId);
    }

    /* MENU */

    public Menu get(int restaurantId, LocalDate date) {
        log.info("get Restaurant {}", restaurantId);
        return service.getMenuByRestaurantAndDate(restaurantId, date);
    }

    public Menu getMenu(int menuId) {
        log.info("get Menu with id{}", menuId);
        return service.getMenu(menuId);
    }

    public Menu getMenu(int menuId, LocalDate date) {
        log.info("get Menu {} with {}", menuId, date);
        return service.getMenu(menuId, date);
    }

    public Menu getMenuByRestaurantAndDate(int restaurantId, LocalDate date) {
        log.info("get menu for Restaurant {} by Date {}", restaurantId, date);
        return service.getMenuByRestaurantAndDate(restaurantId, date);
    }

    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        log.info("get all menus by Restaurant {}", restaurantId);
        return service.getAllMenusByRestaurant(restaurantId);
    }

    public List<Menu> getAllMenusByDate(LocalDate date) {
        log.info("get menus by date {}", date);
        return service.getAllMenusByDate(date);
    }
}
