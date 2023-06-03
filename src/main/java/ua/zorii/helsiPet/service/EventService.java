package ua.zorii.helsiPet.service;

import org.springframework.format.annotation.DateTimeFormat;
import ua.zorii.helsiPet.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> findBetween(@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                   @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);
    void create(Event event);
    void update(Event event);
    Event findById(Long id);
}
