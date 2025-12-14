package com.project.onlineticketbooking.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponse> findByEmail(String email){
        return userRepository.findById(email)
//                .map(user -> new UserResponse(user.getUserName(), user.getEmail()));
                .map(UserResponse::from);
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
}
