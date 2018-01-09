package reomor.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        return repository.add(restaurant);
    }

    @Override
    public void update(Restaurant restaurant, int restaurantId) {
        repository.add(restaurant);
    }

    @Override
    public Restaurant get(int restaurantId) {
        return repository.get(restaurantId);
    }

    @Override
    public boolean delete(int restaurantId) {
        return repository.delete(restaurantId);
    }

    @Override
    public Menu addMenu(Menu menu) {
        return repository.addMenu(menu);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return repository.addMenu(menu);
    }

    @Override
    public boolean deleteMenu(int menuId) {
        return repository.deleteMenu(menuId);
    }

    @Override
    public Menu getMenu(int menuId, LocalDate date) {
        return repository.getMenu(menuId, date);
    }

    @Override
    public List<Menu> getAllMenusByDate(LocalDate date) {
        return repository.getAllMenusByDate(date);
    }
}
