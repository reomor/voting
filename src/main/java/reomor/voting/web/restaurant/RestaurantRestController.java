package reomor.voting.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getWithMenuByDate(@PathVariable("id") int restaurantId) {
        return super.getWithMenuByDate(restaurantId, LocalDate.now());
    }

    @Override
    @GetMapping(value = "/{id}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllMenusByRestaurant(@PathVariable("id") int restaurantId) {
        return super.getWithMenus(restaurantId).getMenus();
    }

    @GetMapping(value = "/{id}/menus/{menuid}")
    public Menu getMenuByIdAndRestaurant(@PathVariable("id") int restaurantId, @PathVariable("menuid") int menuId) {
        return super.getMenuByIdAndRestaurant(menuId, restaurantId);
    }

    @Override
    @GetMapping("/menus")
    public List<Menu> getAllMenusByDate(@RequestParam(name = "date", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return super.getAllMenusByDate(date);
    }
}
