package com.events.api.controller;

import com.events.api.domain.user.User;
import com.events.api.domain.user.UserRequestDTO;
import com.events.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequestDTO body) {
        User newUser = this.userService.createUser(body);
               return ResponseEntity.ok(newUser);
    }
}
