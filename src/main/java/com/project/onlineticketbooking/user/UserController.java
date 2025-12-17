package com.project.onlineticketbooking.user;

import com.project.onlineticketbooking.util.Jwt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Jwt jwtUtil;

    public UserController(UserService userService, Jwt jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public UserResponse create(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/update")
    public UserResponse update(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/data")
    public UserResponse data(@RequestHeader("Authorization") String header) {

        String token = header.replace("Bearer ", "");
        String email = jwtUtil.extractEmailFromToken(token);

        return userService.findByEmail(email);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody User user) {
        return userService.login(user);
    }
}
