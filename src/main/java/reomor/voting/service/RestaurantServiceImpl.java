package reomor.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

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
    public void delete(int restaurantId) {
        checkNotFoundWithId(repository.delete(restaurantId), restaurantId);
    }

    @Override
    public Menu addMenu(Menu menu) {
        Assert.notNull(menu, "menu for add must not be null");
        return repository.addMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu, int menuId) {
        Assert.notNull(menu, "menu for update must not be null");
        checkNotFoundWithId(repository.addMenu(menu), menuId);
    }

    @Override
    public void deleteMenu(int menuId) {
        checkNotFoundWithId(repository.deleteMenu(menuId), menuId);
    }

    @Override
    public Menu getMenu(int menuId, LocalDate date) {
        return checkNotFoundWithId(repository.getMenu(menuId, date), menuId);
    }

    @Override
    public List<Menu> getAllMenusByDate(LocalDate date) {
        return repository.getAllMenusByDate(date);
    }
}
