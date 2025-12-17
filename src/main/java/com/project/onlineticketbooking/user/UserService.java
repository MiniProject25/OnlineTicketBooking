package com.project.onlineticketbooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.onlineticketbooking.util.Jwt;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final Jwt jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder, Jwt jwtUtil) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse findByEmail(String email){

        return userRepository.findById(email)
//                .map(user -> new UserResponse(user.getUserName(), user.getEmail()));
                .map(UserResponse::from).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse createUser(User user) {
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public UserResponse updateUser(User user) {
        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null) {
            // check whether password is changed
            boolean flag = Objects.equals(bcryptPasswordEncoder.encode(user.getPassword()), existing.getPassword());
            if (!flag) {
                existing.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
            }

            // check whether username is changed
            flag = Objects.equals(user.getUserName(), existing.getUserName());
            if (!flag) {
                existing.setUserName(user.getUserName());
            }

            userRepository.save(existing);
        }

        return UserResponse.from(user);
    }

    public String login(User user) {
        User existing = userRepository.findByEmail(user.getEmail());

        try {
            if (existing != null) {
                if (bcryptPasswordEncoder.matches(user.getPassword(), existing.getPassword())) {
                    return jwtUtil.generateJwt(user.getEmail());
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
