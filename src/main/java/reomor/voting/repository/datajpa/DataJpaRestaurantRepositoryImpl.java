package reomor.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Restaurant add(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }

    @Override
    @Transactional
    public Menu addMenu(Menu menu, int restaurantId) {
        Menu menuNew = new Menu(menu);
        menuNew.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menuNew);
    }

    @Override
    public boolean deleteMenu(int menuId) {
        return crudMenuRepository.delete(menuId) != 0;
    }

    @Override
    public Menu getMenu(int menuId, LocalDate date) {
        return crudMenuRepository.findById(menuId).filter(menu -> menu.getDate().equals(date)).orElse(null);
    }

    @Override
    public List<Menu> getAllMenusByDate(LocalDate date) {
        return crudMenuRepository.getAllMenusByDate(date);
    }
}
