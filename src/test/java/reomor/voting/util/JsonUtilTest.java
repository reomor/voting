package reomor.voting.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import reomor.voting.model.User;
import reomor.voting.model.Menu;

import static reomor.voting.UserTestData.admin1001;
import static reomor.voting.UserTestData.user1000;

import static reomor.voting.MenuTestData.menu0;
import static reomor.voting.MenuTestData.menu1;

public class JsonUtilTest {

    @Test
    public void testReadWriteSimpleValue() throws Exception {
        String json = JsonUtil.writeValue(user1000);
        System.out.println(json);
        User user = JsonUtil.readValue(json, User.class);
        reomor.voting.UserTestData.assertMatch(user, user1000);
    }

    @Test
    public void testReadWriteSimpleValues() throws Exception {
        final List<User> expected = Arrays.asList(user1000, admin1001);
        String json = JsonUtil.writeValue(expected);
        List<User> actual = JsonUtil.readValues(json, User.class);
        reomor.voting.UserTestData.assertMatch(actual, expected);
    }

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(menu0);
        System.out.println(json);
        Menu menu = JsonUtil.readValue(json, Menu.class);
        reomor.voting.MenuTestData.assertMatch(menu, menu0);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        final List<Menu> expected = Arrays.asList(menu0, menu1);
        String json = JsonUtil.writeValue(expected);
        System.out.println(json);
        final List<Menu> actual = JsonUtil.readValues(json, Menu.class);
        reomor.voting.MenuTestData.assertMatch(actual, expected);
    }
}