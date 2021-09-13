package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(Integer id) {
        if (userRepository.existsById(id))
            return userRepository.getById(id);
        return null;

    }
}
