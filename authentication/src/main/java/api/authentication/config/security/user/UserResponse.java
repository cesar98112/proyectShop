package api.authentication.config.security.user;

public class UserResponse {
    private String username;
    private String token;

    public UserResponse(){

    }

    public UserResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
