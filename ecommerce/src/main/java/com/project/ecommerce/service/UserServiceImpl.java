package com.project.ecommerce.service;


import com.project.ecommerce.exception.DuplicateUsernameException;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new DuplicateUsernameException("Username already exists with this username");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirm_Password(passwordEncoder.encode(user.getConfirm_Password()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userRepository.findByUsername(username);
    }


}

