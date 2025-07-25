package com.project.ecommerce.service;

import com.project.ecommerce.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

}
