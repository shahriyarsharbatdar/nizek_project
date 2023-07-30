package model;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int userId;
    private UserRole role;

    public User(String name, String lastName, String email, String password, int userId, UserRole role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", role=" + role +
                '}'+'\n';
    }
}
