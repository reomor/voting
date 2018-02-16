package reomor.voting.service.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.RestaurantService;
import reomor.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static reomor.voting.DishTestData.*;
import static reomor.voting.MenuTestData.*;
import static reomor.voting.RestaurantTestData.*;
import static reomor.voting.util.MenuUtil.asMenuTo;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("menus").clear();
        cacheManager.getCache("restaurants").clear();
    }

    /* Restaurant */

    @Test
    public void getRestaurantTest() throws Exception {
        Restaurant actual = service.get(101);
        assertMatch(actual, restaurant101);
    }

    @Test
    public void getWithMenusRestaurantTest() throws Exception {
        final Restaurant actual = service.getWithMenus(101);
        final List<Menu> actual_menus = Arrays.asList(menu3, menu1);
        assertMatch(actual, restaurant101);
        reomor.voting.MenuTestData.assertMatch(actual.getMenus(), actual_menus);
    }

    @Test
    public void getWithMenusByDateRestaurantTest() throws Exception {
        final Restaurant actual = service.getWithMenuByDate(100, LocalDate.of(2017, Month.DECEMBER, 3));
        assertMatch(actual, restaurant100);
        reomor.voting.MenuTestData.assertMatch(actual.getMenus(), Collections.singletonList(menu0));
    }

    @Test
    public void getAllRestaurantTest() throws Exception {
        assertMatch(service.getAll(), restaurant102, restaurant101, restaurant100);
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
    public void addRestaurantTest() throws Exception {
        Restaurant restaurantNew = new Restaurant(null, "VabiSabi");
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
        Menu actual = service.getMenu(1);
        reomor.voting.MenuTestData.assertMatch(actual, menu1);
    }

    @Test
    public void addMenuTest() throws Exception {
        Menu menu = new Menu(null, LocalDate.of(2018, Month.JANUARY, 29), restaurant102, dish_menu3);
        Integer id = service.addMenu(asMenuTo(menu), restaurant102.getId()).getId();
        menu.setId(id);
        Menu actual = service.getMenu(id);
        assertMatch(actual, menu);
    }

    @Test
    public void updateMenuTest() throws Exception {
        Menu update = service.getMenu(1);
        update.setRestaurant(restaurant102);
        service.updateMenu(asMenuTo(update), update.getRestaurant().getId(), update.getId());
        Menu actual = service.getMenu(1);
        assertMatch(actual, update);
    }

    @Test
    public void deleteMenuTest() throws Exception {
        service.deleteMenu(101, 1);
        assertMatch(service.getAllMenusByDate(LocalDate.of(2017, Month.DECEMBER, 3)), Collections.singletonList(menu0));
    }

    @Test
    public void testGetMenuByIdAndRestaurant() throws Exception {
        final Menu actual = service.getMenuByIdAndRestaurant(0, 100);
        assertMatch(actual, menu0);
    }

    @Test(expected = NotFoundException.class)
    public void testGetMenuByIdAndRestaurantException() throws Exception {
        final Menu actual = service.getMenuByIdAndRestaurant(2000, 101);
    }

    @Test
    public void testGetMenuByRestaurantAndDate() throws Exception {
        final Menu actual = service.getMenuByRestaurantAndDate(100, LocalDate.of(2017, Month.DECEMBER, 4));
        assertMatch(actual, menu2);
    }

    @Test(expected = NotFoundException.class)
    public void testGetMenuByRestaurantAndDateException() throws Exception {
        final Menu actual = service.getMenuByRestaurantAndDate(1000, LocalDate.of(2017, Month.DECEMBER, 4));
    }

    @Test
    public void getAllMenusByDateTest() throws Exception {
        final List<Menu> actual = service.getAllMenusByDate(LocalDate.of(2017, Month.DECEMBER, 3));
        //assertMatch(actual, menu0, menu1);
        assertMatch(actual, menu1, menu0);
    }

}