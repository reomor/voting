package reomor.voting.web.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reomor.voting.service.RestaurantService;
import reomor.voting.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static reomor.voting.RestaurantTestData.*;
import static reomor.voting.TestUtils.*;
import static reomor.voting.UserTestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void testGetAllRestaurants() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // order alphabet from A to Z
                .andExpect(contentJsonArray(restaurant102, restaurant101, restaurant100));
    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        String plainCredentials = admin1001.getEmail() + ":" + admin1001.getPassword();
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        headers.add("Authorization", "Basic " + base64Credentials);

        mockMvc.perform(delete(REST_URL + "/admin/102").headers(headers))
                //.with(userHttpBasic(admin1001))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}