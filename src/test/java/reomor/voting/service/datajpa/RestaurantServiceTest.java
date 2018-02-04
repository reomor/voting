package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.RestaurantService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static reomor.voting.MenuTestData.*;
import static reomor.voting.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    /* Restaurant */
    @Test
    public void getRestaurantTest() throws Exception {
        Restaurant actual = service.get(101);
        assertMatch(actual, restaurant101);
    }

    @Test
    public void updateRestaurantTest() throws Exception {
        Restaurant updated = new Restaurant(restaurant100);
        updated.setName("VabiSabi");
        service.update(updated, updated.getId());
        Restaurant actual = service.get(updated.getId());
        assertMatch(actual, updated);
    }

    @Test
    public void getAllRestaurantTest() throws Exception {
        assertMatch(service.getAll(), restaurant102, restaurant101, restaurant100);
    }

    @Test
    public void addRestaurantTest() throws Exception {
        Restaurant restaurantNew = new Restaurant(103, "VabiSabi");
        service.add(restaurantNew);
        assertMatch(service.getAll(), Arrays.asList(restaurant102, restaurant101, restaurant100, restaurantNew));
    }

    @Test
    public void deleteRestaurantTest() throws Exception {
        service.delete(101);
        assertMatch(service.getAll(), restaurant102, restaurant100);
    }

    /* MENU */
    @Test
    public void getMenuTest() throws Exception {
        Menu actual = service.getMenu(0, LocalDate.of(2017, Month.DECEMBER, 3));
        assertMatch(actual, menu0);
    }

    @Test
    public void getAllMenusByDateTest() throws Exception {
        final List<Menu> actual = service.getAllMenusByDate(LocalDate.of(2017, Month.DECEMBER, 3));
        //assertMatch(actual, menu0, menu1);
        assertMatch(actual, menu1, menu0);
    }

    @Test
    public void addMenuTest() throws Exception {
        Menu menu = new Menu(null, LocalDate.of(2018, Month.JANUARY, 29), restaurant102, Collections.emptyList());
        Integer id = service.addMenu(menu, restaurant102.getId()).getId();
        menu.setId(id);
        Menu actual = service.getMenu(id, LocalDate.of(2018, Month.JANUARY, 29));
        assertMatch(actual, menu);
    }

    @Test
    public void updateMenuTest() throws Exception {
        Menu update = service.getMenu(1, LocalDate.of(2017, Month.DECEMBER, 3));
        update.setRestaurant(restaurant102);
        service.updateMenu(update, update.getRestaurant().getId(), update.getId());
        assertMatch(service.getMenu(1, LocalDate.of(2017, Month.DECEMBER, 3)), update);
    }


}