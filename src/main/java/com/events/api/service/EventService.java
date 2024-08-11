package com.events.api.service;

import com.events.api.domain.event.Event;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.domain.user.User;
import com.events.api.repositories.EventRepository;
import com.events.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Event createEvent(EventRequestDTO data) {
        Event event = new Event();
        event.setName(data.name());
        event.setVacancy(data.vacancy());
        event.setDate_start(new Date(data.date_start()));
        event.setDate_end(new Date(data.date_end()));

        return eventRepository.save(event);
    }

    public Event addUserToEvent(UUID eventId, UUID userId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (eventOpt.isPresent() && userOpt.isPresent()) {
            Event event = eventOpt.get();
            User user = userOpt.get();

            if (!event.getUsers().contains(user)) {
                event.getUsers().add(user);
            }

            return eventRepository.save(event);
        }

        throw new RuntimeException("Evento ou Usuário não encontrado!");
    }

    public Set<User> listUsersInEvent(UUID eventId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);

        if (eventOpt.isPresent()) {
            return eventOpt.get().getUsers();
        }

        throw new RuntimeException("Evento não encontrado!");
    }

    public void removeUserFromEvent(UUID eventId, UUID userId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (eventOptional.isPresent() && userOptional.isPresent()) {
            Event event = eventOptional.get();
            User user = userOptional.get();

            event.getUsers().remove(user);
            user.getEventos().remove(event);

            eventRepository.save(event);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Event or User not found");
        }
    }
}