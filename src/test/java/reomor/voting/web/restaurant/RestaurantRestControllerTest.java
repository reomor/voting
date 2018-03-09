package reomor.voting.web.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.service.RestaurantService;
import reomor.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static reomor.voting.RestaurantTestData.*;
import static reomor.voting.TestUtils.*;
import static reomor.voting.UserTestData.*;
import static reomor.voting.MenuTestData.*;
import static reomor.voting.util.JsonUtil.writeValue;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() {
        super.setUp();
    }

    /* RESTAURANTS */

    @Test
    public void testGetAllRestaurants() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // order alphabet from A to Z
                .andExpect(contentJsonArray(restaurant102, restaurant101, restaurant100));
    }

    //http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
    @Test
    public void testDeleteRestaurant() throws Exception {
        mockMvc.perform(delete(REST_URL + "/admin/102")
                .headers(getAuthHeadersForUser(admin1001)))
                //.with(userHttpBasic(admin1001))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPostNewRestaurant() throws Exception {
        Restaurant restaurantNew = new Restaurant(null, "Papa Johns");

        final ResultActions resultActions = mockMvc.perform(post(REST_URL + "/admin")
                .headers(getAuthHeadersForUser(admin1001))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(restaurantNew)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(resultActions, Restaurant.class);
        restaurantNew.setId(returned.getId());
        assertMatch(returned, restaurantNew);
    }

    @Test
    public void testPostNewRestaurantUnauthorized() throws Exception {
        Restaurant restaurant = new Restaurant(null, "Papa Johns");

        mockMvc.perform(post(REST_URL + "/admin")
                .headers(getAuthHeadersForUser(user1000))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(restaurant)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testPutUpdateRestaurant() throws Exception {
        Restaurant updated = service.get(102);
        updated.setName("Mollie's");

        mockMvc.perform(put(REST_URL + "/admin/" + updated.getId())
                .headers(getAuthHeadersForUser(admin1001))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(updated.getId()), updated);
    }

    /* MENUS */
    @Test
    public void testGetAllMenusByRestaurant() throws Exception {
        Restaurant restaurant = restaurant101;
        mockMvc.perform(get(REST_URL + "/" + restaurant.getId() + "/" + "menus"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(menu3, menu1));
    }

    @Test
    public void testGetMenuById() throws Exception {
        Restaurant restaurant = restaurant100;
        Menu menu = menu2;
        mockMvc.perform(get(REST_URL + "/" + restaurant.getId() + "/menus/" + menu.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(menu));
    }
}