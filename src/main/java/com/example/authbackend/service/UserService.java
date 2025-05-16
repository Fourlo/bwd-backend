package com.example.authbackend.service;

import com.example.authbackend.entity.User;
import com.example.authbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public User register(User user) {
        String hasedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hasedPassword);
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            if(passwordEncoder.matches(password, user.get().getPassword())){
                return user.get();
            }
        }
        return null;
    }

}
