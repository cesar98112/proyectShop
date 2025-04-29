package api.gateway.model;

public class UserDto {

    private String Username;
    private String password;
    private String Roles;

    public UserDto(){

    }

    public UserDto(String username, String password, String roles) {
        Username = username;
        this.password = password;
        Roles = roles;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", Roles='" + Roles + '\'' +
                '}';
    }
}
