package com.project.onlineticketbooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponse create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/update")
    public UserResponse update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/data/{email}")
    public Optional<UserResponse> data(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
