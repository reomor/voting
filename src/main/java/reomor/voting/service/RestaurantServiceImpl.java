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

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant add(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant for add must not be null");
        return repository.add(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
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

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int restaurantId) {
        checkNotFoundWithId(repository.delete(restaurantId), restaurantId);
    }


    @Override
    public Menu getMenu(int menuId) {
        return checkNotFoundWithId(repository.getMenu(menuId), menuId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu addMenu(MenuTo menuTo, int restaurantId) {
        Assert.notNull(menuTo, "menuTo must not be null");
        return checkNotFoundWithId(repository.addMenu(menuTo, restaurantId), menuTo.getId());
    }

    @CacheEvict(value = "menus", allEntries = true)
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
    public Menu getMenuByIdAndRestaurant(int menuId, int restaurantId) {
        return checkNotFoundWithId(repository.getMenuByIdAndRestaurant(menuId, restaurantId), menuId);
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
}
