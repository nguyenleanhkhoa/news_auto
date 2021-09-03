package com.triviet.project.viet24.implement;

import com.triviet.project.viet24.entity.User;

public interface IUserService {
    User loadUser(String email, String password);
}
