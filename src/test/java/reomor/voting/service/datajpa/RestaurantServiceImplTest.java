package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.Restaurant;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.RestaurantService;

import static reomor.voting.service.RestaurantTestData.assertMatch;

public class RestaurantServiceImplTest extends AbstractServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    public void getTest() throws Exception {
        Restaurant actual = service.get(101);
        assertMatch(actual, null);
    }
}