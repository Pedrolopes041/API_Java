package com.events.api.controller;

import com.events.api.domain.event.Event;
import com.events.api.domain.user.User;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventRequestDTO data) {
        Event event = this.eventService.createEvent(data);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/{eventId}/users/{userId}")
    public ResponseEntity<Event> addUserToEvent(@PathVariable UUID eventId, @PathVariable UUID userId) {
        Event event = eventService.addUserToEvent(eventId, userId);
        return ResponseEntity.ok(event);
    }

    //falta esse
    @GetMapping("/{eventId}/users")
    public ResponseEntity<Set<User>> listUsersInEvent(@PathVariable UUID eventId) {
        Set<User> users = eventService.listUsersInEvent(eventId);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{eventId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromEvent(@PathVariable UUID eventId, @PathVariable UUID userId) {
        eventService.removeUserFromEvent(eventId, userId);
        return ResponseEntity.noContent().build();
    }
}
