package reomor.voting.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void createTest() {
        User user = new User(null, "Hulio", "hulio@ya.ru", "ololo", true, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(user);
        user.setId(created.getId());
        assertMatch(service.get(created.getId()), user);
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }
}