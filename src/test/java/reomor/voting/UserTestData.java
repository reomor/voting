package reomor.voting;

import reomor.voting.model.Role;
import reomor.voting.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final User user1000 = new User(1000, "User", "user@yandex.ru", "password", true, new Date(), Collections.singleton(Role.ROLE_USER));
    public static final User user1001 = new User(1000, "Admin", "admin@gmail.com", "admin", true, new Date(), Collections.singleton(Role.ROLE_ADMIN));

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password", "registered");
    }

    public static void assertMatch(Iterable<User> actual, User ... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password", "registered").isEqualTo(expected);
    }
}