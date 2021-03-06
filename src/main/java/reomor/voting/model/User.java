package reomor.voting.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL", name = "USERS_UNIQUE_EMAIL_IDX")})
public class User extends BaseEntity {
    @NotBlank
    @Size(min = 4, max = 100)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank
    @Size(min = 6, max = 64)
    private String password;

    @Column(name = "REGISTERED", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "ENABLED", nullable = false, columnDefinition = "bool default true")
    private Boolean enabled = true;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLE")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {}

    public User(User user) {
        super(user.getId());
        this.name = user.name;
        this.email = user.email;
        this.password = user.password;
        this.registered = (Date) user.registered.clone();
        this.enabled = user.enabled;
        setRoles(user.getRoles());
    }

    public User(Integer id, String name, String email, String password, Boolean enabled, Date registered, Role role, Role... roles) {
        this(id, name, email, password, enabled, registered, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Boolean enabled, Date registered, Collection<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        setRoles(roles);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
