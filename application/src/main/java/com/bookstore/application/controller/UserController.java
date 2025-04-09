package com.bookstore.application.controller;
import com.bookstore.application.entity.Users;
import com.bookstore.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> log(@RequestBody Users user) {
        return userService.login(user);
    }
}


