package com.blog.socialshare.controller;

import java.util.List;

import com.blog.socialshare.model.Users;
import com.blog.socialshare.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "user/search", params = { "name" })
    public ResponseEntity<List<Users>> search(@RequestParam("name") String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login Page");
    }

    @GetMapping("/signup")
    public ResponseEntity<String> signup() {
        return ResponseEntity.ok("Signup Page");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@ModelAttribute Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

}
