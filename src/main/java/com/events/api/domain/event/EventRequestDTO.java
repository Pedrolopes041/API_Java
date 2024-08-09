package com.events.api.domain.event;

import java.util.Date;

public record EventRequestDTO(String name, int vacancy, Long date_start, Long date_end) {
}
