package ua.zorii.helsiPet.service.impl;

import org.springframework.stereotype.Service;
import ua.zorii.helsiPet.entity.Event;
import ua.zorii.helsiPet.repository.EventRepository;
import ua.zorii.helsiPet.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findBetween(start, end);
    }

    @Override
    public void create(Event event) {
        eventRepository.create(event);
    }

    @Override
    public void update(Event event) {
        eventRepository.update(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id);
    }
}
