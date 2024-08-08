package com.events.api.repositories;

import com.events.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventyUser extends JpaRepository<User, UUID> {
}
