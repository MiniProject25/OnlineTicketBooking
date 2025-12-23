package com.project.onlineticketbooking.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
//    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 4)
    private String password;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}

    public User(String email, String password, String userName, UserRole role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
