package reomor.voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.User;
import reomor.voting.service.UserService;

import java.util.List;

import static reomor.voting.util.ValidationUtil.*;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get User with id={}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create User={}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete User id={}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update User={} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user, id);
    }

    public User getByEmail(String email) {
        log.info("getByEmail");
        return service.getByEmail(email);
    }
}
