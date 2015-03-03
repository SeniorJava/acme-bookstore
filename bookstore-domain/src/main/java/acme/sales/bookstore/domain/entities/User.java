package acme.sales.bookstore.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author vmuravlev
 */
@Entity
@Table(name = "users", schema = "acme_bookstore")
public class User {
    private String username;
    private String password;
    private boolean enabled = true;

    private Collection<String> authorities;

    public User() {
        this.authorities = new ArrayList<>();
    }

    public User(Client client) {
        this();
        this.username = client.getFirstName().toLowerCase();
        this.password = client.getLastName().toLowerCase();
    }

    @Id
    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", schema = "acme_bookstore",
            joinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")})
    @Column(name = "authority")
    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }
}