package reomor.voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.RestaurantService;
import reomor.voting.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    private RestaurantService service;

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

    Restaurant getWithMenuByDate(int restaurantId, LocalDate date) {
        log.info("");
        return service.getWithMenuByDate(restaurantId, date);
    }

    Restaurant getWithMenus(int restaurantId) {
        log.info("");
        return service.getWithMenus(restaurantId);
    }

    /* MENU */

    public Menu get(int restaurantId, LocalDate date) {
        log.info("get Restaurant {}", restaurantId);
        return service.getMenuByRestaurantAndDate(restaurantId, date);
    }

    public Menu addMenuFromMenuTo(MenuTo menuTo, int restaurantId) {
        return service.addMenu(menuTo, restaurantId);
    }

    public void updateMenuFromMenuTo(MenuTo menuTo, int restaurantId, int menuId) {
        service.updateMenu(menuTo, restaurantId, menuId);
    }

    public void deleteMenu(int restaurantId, int menuId) {
        service.deleteMenu(restaurantId, menuId);
    }

    Menu getMenuByIdAndRestaurant(int menuId, int restaurantId) {
        return service.getMenuByIdAndRestaurant(menuId, restaurantId);
    }

    public List<Menu> getAllMenusByDate(LocalDate date) {
        log.info("get menus by date {}", date);
        return service.getAllMenusByDate(date);
    }
}
