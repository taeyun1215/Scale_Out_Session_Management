package com.example.demo.user;

import com.example.demo.user.dto.UserLoginRequest;
import com.example.demo.user.dto.UserRegisterRequest;

public interface UserService {

    void saveUser(UserRegisterRequest request);

    User loginUser(UserLoginRequest request);

    User findById(Long userId);

}
