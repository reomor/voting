package reomor.voting;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import reomor.voting.model.User;
import reomor.voting.to.UserTo;
import reomor.voting.util.UserUtils;

import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    // user - 1000, admin - 1001
    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getEnabled(), true,
                true, true, user.getRoles());

        this.userTo = UserUtils.asTo(user);
    }

    private static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser authorizedUser = safeGet();
        requireNonNull(authorizedUser, "No authorized user found");
        return authorizedUser;
    }

    public  static int id() {
        return get().userTo.getId();
    }
}
