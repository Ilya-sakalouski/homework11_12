package ru.mail.ilya6697089.app.repository.model;

public class User {

    private Integer user_id;
    private String username;
    private String password;
    private String createdBy;
    private Role role;

    public Integer getUser_id() { return user_id; }

    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
