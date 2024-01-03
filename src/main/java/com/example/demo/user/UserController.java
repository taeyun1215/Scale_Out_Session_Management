package com.example.demo.user;

import com.example.demo.user.dto.UserLoginRequest;
import com.example.demo.user.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest request) {
        userService.saveUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request, HttpServletRequest httpServletRequest) {
        User user = userService.loginUser(request);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(LOGIN_MEMBER, user.getId());

        return ResponseEntity.ok("User logged in successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            ResponseEntity.ok("User logged out successfully");
        }

        return ResponseEntity.ok("not login User");
    }
}