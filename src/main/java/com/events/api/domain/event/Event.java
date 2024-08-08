package com.events.api.domain.event;

import com.events.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private Integer vagas;
    private Date date_star;
    private Date date_end;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
