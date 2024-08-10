package com.events.api.service;

import com.events.api.domain.event.Event;
import com.events.api.domain.user.User;
import com.events.api.domain.user.UserRequestDTO;
import com.events.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get create user sucessfully from db")
    void createUser() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO("Pedro");
        User expectedUser = new User();
        expectedUser.setId(UUID.randomUUID());
        expectedUser.setName("Pedro");

        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        // Act
        User createdUser = userService.createUser(userRequestDTO);

        // Assert
        assertEquals(expectedUser.getName(), createdUser.getName());
        assertEquals(expectedUser.getId(), createdUser.getId());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("should Return UserEvents When User Exists")
    void listUserEvents() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        Set<Event> events = new HashSet<>();
        Event event = new Event();
        event.setId(UUID.randomUUID());
        events.add(event);

        user.setEventos(events);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Set<Event> foundEvents = userService.listUserEvents(userId);

        // Assert
        assertEquals(events, foundEvents);
        verify(userRepository, times(1)).findById(userId);
    }
}
