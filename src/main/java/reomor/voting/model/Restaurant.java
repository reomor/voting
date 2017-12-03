package reomor.voting.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant extends DatabaseEntity {
    private String name;
    private List<Dish> menu;

    Restaurant(int id, String name, Dish ... dishes) {
        super(id);
        this.name = name;
        menu = Arrays.asList(dishes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }
}
