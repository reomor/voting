package reomor.voting.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reomor.voting.model.Menu;
import reomor.voting.model.Restaurant;
import reomor.voting.to.MenuTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant getWithMenuByDate(@PathVariable("id") int restaurantId) {
        return super.getWithMenuByDate(restaurantId, LocalDate.now());
    }

    @DeleteMapping(value = "/admin/{id}")
    public void deleteRestaurant(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant created = super.add(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/admin/{id}")
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("id") int restaurantId) {
        super.update(restaurant, restaurantId);
    }

    @GetMapping(value = "/{id}/menus")
    public List<Menu> getAllMenusByRestaurant(@PathVariable("id") int restaurantId) {
        return super.getWithMenus(restaurantId).getMenus();
    }

    @GetMapping(value = "/{id}/menus/{menuid}")
    public Menu getMenuByIdAndRestaurant(@PathVariable("id") int restaurantId, @PathVariable("menuid") int menuId) {
        return super.getMenuByIdAndRestaurant(menuId, restaurantId);
    }

    @Override
    @GetMapping(value = "/menus")
    public List<Menu> getAllMenusByDate(@RequestParam(name = "date", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return super.getAllMenusByDate(date);
    }

    @PostMapping(value = "/{restaurantid}/menus/admin")
    public ResponseEntity<Menu> addMenu(@RequestBody MenuTo menuTo, @PathVariable("restaurantid") int restaurantId) {
        Menu create = super.addMenuFromMenuTo(menuTo, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/menus/{id}")
                .buildAndExpand(create.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(create);
    }

    @PutMapping(value = "/{restaurantid}/menus/admin/{id}")
    public void updateMenu(@RequestBody MenuTo menuTo, @PathVariable("restaurantid") int restaurantId, @PathVariable("id") int menuId) {
        super.updateMenuFromMenuTo(menuTo, restaurantId, menuId);
    }

    @DeleteMapping(value = "/{restaurantid}/menus/admin/{id}")
    public void deleteMenu(@PathVariable("restaurantid") int restaurantId, @PathVariable("id") int menuId) {
        super.deleteMenu(restaurantId, menuId);
    }
}
