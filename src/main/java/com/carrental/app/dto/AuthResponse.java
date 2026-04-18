package com.carrental.app.dto;

import com.carrental.app.model.Role;
import com.carrental.app.model.User;

public class AuthResponse {
    private Long id;
    private String fullName;
    private String username;
    private Role role;
    private String email;
    private String phone;

    public static AuthResponse from(User user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        return response;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
