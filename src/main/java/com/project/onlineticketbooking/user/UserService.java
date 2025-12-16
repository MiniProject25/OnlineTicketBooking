package com.project.onlineticketbooking.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse findByEmail(String email){
        return userRepository.findById(email)
//                .map(user -> new UserResponse(user.getUserName(), user.getEmail()));
                .map(UserResponse::from).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse createUser(User user) {
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public UserResponse updateUser(User user) {
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public boolean login(User user) {
        User existing = userRepository.findByEmail(user.getEmail());

        try {
            if (existing != null) {
                if (existing.getPassword().equals(user.getPassword())) {
                    return true;
                }else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
