package reomor.voting.model;

import java.util.Date;
import java.util.Set;

//@Entity
//@Table(name = "USERS")
public class User extends BaseEntity {
    //@NotBlank
    //@Size(min = 1, max = 100)
    //@Column(name = "NAME", nullable = false)
    private String name;

    private String email;
    private String password;
    private Set<Roles> roles;
    private Date registered = new Date();
    private boolean enabled;

    User(int id, String name, String email, Set<Roles> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", registered=" + registered +
                ", enabled=" + enabled +
                '}';
    }
}
