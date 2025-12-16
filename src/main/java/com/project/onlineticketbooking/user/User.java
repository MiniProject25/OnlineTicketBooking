package com.project.onlineticketbooking.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
    @Id
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
//    @Column(unique = true)
    private String email;
    private String password;
    private String userName;

    protected User() {}

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
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
}
