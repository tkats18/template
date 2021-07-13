package com.template.demo.service;

import com.template.demo.model.authorization.RegisterRequest;
import com.template.demo.storage.entity.User;

public interface UserManagementService {

    User addUser(RegisterRequest registerRequest);

    User getUser(String username);
}
