package reomor.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

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
    public Menu addMenu(Menu menu) {
        return crudMenuRepository.save(menu);
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
