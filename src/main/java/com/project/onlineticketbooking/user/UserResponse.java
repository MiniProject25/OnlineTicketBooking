package com.project.onlineticketbooking.user;

public class UserResponse {
    private final String userName;
    private final String email;

    public UserResponse(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getUserName(), user.getEmail());
    }
}
