package reomor.voting.repository.datajpa;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Dish;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.repository.RestaurantRepository;
import reomor.voting.to.MenuTo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    private CrudDishRepository crudDishRepository;

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
    public Restaurant getWithMenus(int restaurantId) {
        return crudRestaurantRepository.getWithMenus(restaurantId);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }


    @Override
    @Transactional
    public Menu addMenu(MenuTo menuTo, int restaurantId) {
        if (!menuTo.isNew() && getMenu(menuTo.getId()) == null) {
            return null;
        }

        Menu menuNew;
        if (menuTo.isNew()) {
            menuNew = new Menu();
        } else {
            menuNew = getMenu(menuTo.getId());
            crudDishRepository.deleteByMenuId(menuTo.getId());
        }

        menuNew.setDate(menuTo.getDate());
        menuNew.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        for (Dish dish : menuTo.getDishes()) {
            dish.setId(null);
            dish.setMenu(menuNew);
        }
        menuNew.setDishes(menuTo.getDishes());

        // https://stackoverflow.com/questions/19928568/hibernate-best-practice-to-pull-all-lazy-collections
        Hibernate.initialize(menuNew.getRestaurant());
        return crudMenuRepository.save(menuNew);
    }

    @Override
    public boolean deleteMenu(int restaurantId, int menuId) {
        return crudMenuRepository.delete(restaurantId, menuId) != 0;
    }

    @Override
    public Menu getMenu(int menuId) {
        return crudMenuRepository.findById(menuId).orElse(null);
    }

    @Override
    public Menu getMenuByIdAndRestaurant(int menuId, int restaurantId) {
        return crudMenuRepository.getMenuByIdAndRestaurant(menuId, restaurantId);
    }

    @Override
    public Menu getMenuByRestaurantAndDate(int restaurantId, LocalDate date) {
        return crudMenuRepository.getMenuByRestaurantAndDate(restaurantId, date);
    }

    @Override
    public List<Menu> getAllMenusByDate(LocalDate date) {
        return crudMenuRepository.getAllMenusByDate(date);
    }
}
