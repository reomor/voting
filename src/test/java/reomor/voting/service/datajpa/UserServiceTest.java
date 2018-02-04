package reomor.voting.service.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import reomor.voting.model.Role;
import reomor.voting.model.User;
import reomor.voting.service.AbstractServiceTest;
import reomor.voting.service.UserService;

import java.util.Collections;
import java.util.Date;

import static reomor.voting.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Autowired
    CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void createTest() {
        User user = new User(null, "Hulio", "hulio@ya.ru", "olololo", true, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(user);
        user.setId(created.getId());
        assertMatch(service.get(created.getId()), user);
    }

    @Test
    public void deleteTest() {
        service.delete(1001);
        assertMatch(service.getAll(), user1000);
    }

    @Test
    public void updateTest() {
        User updated = new User(user1000);
        updated.setEnabled(false);
        service.update(updated);
        assertMatch(service.get(updated.getId()), updated);
    }

    @Test
    public void getTest() {
        assertMatch(service.get(1001), admin1001);
    }

    @Test
    public void getByEmail() {
        assertMatch(service.getByEmail("admin@gmail.com"), admin1001);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), admin1001, user1000);
    }
}