package com.events.api.service;

import com.events.api.domain.event.Event;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.domain.user.User;
import com.events.api.repositories.EventRepository;
import com.events.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent() {
        EventRequestDTO requestDTO = new EventRequestDTO("Test Event", 10, System.currentTimeMillis(), System.currentTimeMillis() + 100000);
        Event event = new Event();
        event.setName(requestDTO.name());
        event.setVacancy(requestDTO.vacancy());
        event.setDate_start(new Date(requestDTO.date_start()));
        event.setDate_end(new Date(requestDTO.date_end()));

        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.createEvent(requestDTO);

        assertNotNull(createdEvent);
        assertEquals(requestDTO.name(), createdEvent.getName());
        assertEquals(requestDTO.vacancy(), createdEvent.getVacancy());
    }

    @Test
    void addUserToEvent() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Event event = new Event();
        User user = new User();

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event updatedEvent = eventService.addUserToEvent(eventId, userId);

        assertNotNull(updatedEvent);
        assertTrue(updatedEvent.getUsers().contains(user));
    }

    @Test
    void listUsersInEvent() {
        UUID eventId = UUID.randomUUID();
        User user = new User();
        Set<User> users = new HashSet<>();
        users.add(user);

        Event event = new Event();
        event.setUsers(users);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Set<User> usersInEvent = eventService.listUsersInEvent(eventId);

        assertNotNull(usersInEvent);
        assertTrue(usersInEvent.contains(user));
    }

    @Test
    void removeUserFromEvent() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Event event = new Event();
        User user = new User();
        event.getUsers().add(user);
        user.getEventos().add(event);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        when(userRepository.save(any(User.class))).thenReturn(user);

        eventService.removeUserFromEvent(eventId, userId);

        assertFalse(event.getUsers().contains(user));
        assertFalse(user.getEventos().contains(event));
    }
}
