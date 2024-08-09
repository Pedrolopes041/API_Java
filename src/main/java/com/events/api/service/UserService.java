package com.events.api.service;

import com.events.api.domain.user.User;
import com.events.api.domain.user.UserRequestDTO;
import com.events.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserRequestDTO data) {
        User newUser = new User();

        newUser.setName(data.name());

        repository.save(newUser);

        return newUser;
    }
}
