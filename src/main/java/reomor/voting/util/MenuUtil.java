package reomor.voting.util;

import reomor.voting.model.Menu;
import reomor.voting.to.MenuTo;

public class MenuUtil {
    public static MenuTo asMenuTo(Menu menu) {
        MenuTo menuTo = new MenuTo();
        menuTo.setId(menu.getId());
        menuTo.setDate(menu.getDate());
        menuTo.setDishes(menu.getDishes());

        return menuTo;
    }
/*
    public static Menu createNewFromTo(MenuTo menuTo) {
        //Menu menu = new Menu(null, menuTo.getDate(),)
        return null;
    }

    public static Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setDate(menuTo.getDate());
        return null;
    }
    //*/
}
