package reomor.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;
import reomor.voting.to.MenuTo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static reomor.voting.util.ValidationUtil.checkNotFound;
import static reomor.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant add(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant for add must not be null");
        return repository.add(restaurant);
    }

    @Override
    public void update(Restaurant restaurant, int restaurantId) {
        Assert.notNull(restaurant, "restaurant for update must not be null");
        checkNotFoundWithId(repository.add(restaurant), restaurantId);
    }

    @Override
    public Restaurant get(int restaurantId) {
        return checkNotFoundWithId(repository.get(restaurantId), restaurantId);
    }

    @Override
    public Restaurant getWithMenus(int restaurantId) {
        return getWithMenuByDate(restaurantId, null);
    }

    @Override
    public Restaurant getWithMenuByDate(int restaurantId, LocalDate date) {
        final Restaurant restaurant = repository.getWithMenus(restaurantId);
        if (date == null) {
            return restaurant;
        }
        restaurant.setMenus(restaurant.getMenus().stream().filter(menu -> menu.getDate().equals(date)).collect(Collectors.toList()));
        return restaurant;
    }


    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int restaurantId) {
        checkNotFoundWithId(repository.delete(restaurantId), restaurantId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu addMenu(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu for add must not be null");
        return repository.addMenu(menu, restaurantId);
    }

    @Override
    public Menu addMenu(MenuTo menuTo, int restaurantId) {
        return repository.addMenu(menuTo, restaurantId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void updateMenu(Menu menu, int restaurantId, int menuId) {
        Assert.notNull(menu, "menu for update must not be null");
        checkNotFoundWithId(repository.addMenu(menu, restaurantId), menuId);
    }

    @Override
    public void updateMenu(MenuTo menuTo, int restaurantId, int menuId) {
        checkNotFoundWithId(repository.addMenu(menuTo, restaurantId), menuId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void deleteMenu(int restaurantId, int menuId) {
        checkNotFoundWithId(repository.deleteMenu(restaurantId, menuId), menuId);
    }

    @Override
    public Menu getMenu(int menuId) {
        return checkNotFoundWithId(repository.getMenu(menuId), menuId);
    }

    @Override
    public Menu getMenu(int menuId, LocalDate date) {
        return checkNotFoundWithId(repository.getMenu(menuId, date), menuId);
    }

    @Override
    public Menu getMenuByIdAndRestaurant(int menuId, int restaurantId) {
        return repository.getMenuByIdAndRestaurant(menuId, restaurantId);
    }

    @Override
    public Menu getMenuByRestaurantAndDate(int restaurantId, LocalDate date) {
        return checkNotFound(repository.getMenuByRestaurantAndDate(restaurantId, date), "get menu for restaurant with id=" + restaurantId);
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAllMenusByDate(LocalDate date) {
        return repository.getAllMenusByDate(date);
    }

    @Override
    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        return repository.getAllMenusByRestaurant(restaurantId);
    }
}
