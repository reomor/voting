package reomor.voting.util;

import reomor.voting.model.User;
import reomor.voting.to.UserTo;

public class UserUtils {

    public static UserTo asTo(User user) {
        return new UserTo(user);
    }
}
