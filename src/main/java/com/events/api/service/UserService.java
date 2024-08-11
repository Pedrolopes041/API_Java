package com.events.api.service;

import com.events.api.domain.event.Event;
import com.events.api.domain.user.User;
import com.events.api.domain.user.UserRequestDTO;
import com.events.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EventService eventService;

    public User createUser(UserRequestDTO data) {
        User newUser = new User();
        newUser.setName(data.name());

        newUser = repository.save(newUser);

        return newUser;
    }

    public Set<Event> listUserEvents(UUID userId) {
        return repository.findById(userId)
                .map(User::getEventos)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
