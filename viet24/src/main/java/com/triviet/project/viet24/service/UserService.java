package com.triviet.project.viet24.service;

import com.triviet.project.viet24.entity.User;
import com.triviet.project.viet24.implement.IUserService;
import com.triviet.project.viet24.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUser(String email, String password) {
        return userRepository.loadUser(email,password);
    }
}
