package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.RestaurantService;

import java.time.LocalDate;
import java.time.Month;

import static reomor.voting.service.RestaurantTestData.assertMatch;
import static reomor.voting.service.RestaurantTestData.menu0;
import static reomor.voting.service.RestaurantTestData.restaurant101;

public class RestaurantServiceImplTest extends AbstractServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    public void getRestaurantTest() throws Exception {
        Restaurant actual = service.get(101);
        assertMatch(actual, restaurant101);
    }

    @Test
    public void getMenuTest() throws Exception {
        Menu actual = service.getMenu(0, LocalDate.of(2017, Month.DECEMBER, 3));
        assertMatch(actual, menu0);
    }
}