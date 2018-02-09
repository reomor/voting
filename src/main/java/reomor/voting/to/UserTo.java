package reomor.voting.to;

import reomor.voting.model.User;

import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {

    private String name;

    private String email;

    private String password;

    public UserTo(User user) {
        super(user.getId());
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
