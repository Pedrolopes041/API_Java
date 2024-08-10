package com.events.api.controller;

import com.events.api.domain.event.Event;
import com.events.api.domain.user.User;
import com.events.api.domain.user.UserRequestDTO;
import com.events.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

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

    @GetMapping("/{userId}/events")
    public ResponseEntity<Set<Event>> listUserEvents(@PathVariable UUID userId) {
        Set<Event> events = userService.listUserEvents(userId);
        return ResponseEntity.ok(events);
    }
}
