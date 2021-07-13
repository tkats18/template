package com.template.demo.service.impl;

import com.template.demo.model.authorization.RegisterRequest;
import com.template.demo.service.UserManagementService;
import com.template.demo.storage.entity.User;
import com.template.demo.storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private UserRepository userRepository;

    @Override
    public User addUser(RegisterRequest registerRequest) {
        User user = new User();

        if (userRepository.findByUserName(registerRequest.getUsername())!=null){
            throw new RuntimeException("username_taken");
        }

        user.setFullName(registerRequest.getFullName());
        user.setPassword(registerRequest.getPassword());
        user.setUserName(registerRequest.getUsername());

        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
