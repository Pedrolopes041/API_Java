package com.events.api.domain.event;
import com.events.api.domain.user.User;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "events", schema = "EVENTS")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int vacancy;
    private Date date_start;
    private Date date_end;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
