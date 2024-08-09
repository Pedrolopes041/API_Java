package com.events.api.service;

import com.events.api.domain.event.Event;
import com.events.api.domain.event.EventRequestDTO;
import com.events.api.domain.user.User;
import com.events.api.repositories.EventRepository;
import com.events.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
}
