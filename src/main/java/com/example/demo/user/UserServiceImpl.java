package com.example.demo.user;

import com.example.demo.user.dto.UserLoginRequest;
import com.example.demo.user.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void saveUser(UserRegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepo.save(user);
    }

    @Override
    public User loginUser(UserLoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername());
        if (user != null && request.getPassword().equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}
