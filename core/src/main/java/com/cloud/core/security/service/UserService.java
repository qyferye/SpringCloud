package com.cloud.core.security.service;


import com.cloud.core.dto.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findByUsername(String username) {
        User user = new User();
        user.setUsername("cloud");

        user.setPassword(new BCryptPasswordEncoder().encode("cloud"));
        return user;
    }
}
