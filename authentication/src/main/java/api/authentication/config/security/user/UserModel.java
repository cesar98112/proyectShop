package api.authentication.config.security.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Document(value ="user")
public class UserModel {

    @Id
    private String id;
    private String username;
    private String password;
    private Set<Roles> roles;
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialsNoExpired;

    public UserModel() {
    }

    public UserModel(String id, String username, String password,Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isEnabled = true;
        this.accountNoExpired = true;
        this.accountNoLocked = true;
        this.credentialsNoExpired = true;
    }

    public UserModel(String username, String password,Set<Roles> roles) {
        this.username = username;
        this.password = password;
        this.roles= roles;
        this.isEnabled = true;
        this.accountNoExpired = true;
        this.accountNoLocked = true;
        this.credentialsNoExpired = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(boolean accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public boolean isCredentialsNoExpired() {
        return credentialsNoExpired;
    }

    public void setCredentialsNoExpired(boolean credentialsNoExpired) {
        this.credentialsNoExpired = credentialsNoExpired;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(rol -> "Role_"+rol)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(boolean accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", isEnabled=" + isEnabled +
                ", accountNoExpired=" + accountNoExpired +
                ", accountNoLocked=" + accountNoLocked +
                ", credentialsNoExpired=" + credentialsNoExpired +
                '}';
    }
}
