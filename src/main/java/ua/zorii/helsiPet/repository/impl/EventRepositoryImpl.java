package ua.zorii.helsiPet.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.entity.Event;
import ua.zorii.helsiPet.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public EventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> findBetween(LocalDateTime start, LocalDateTime end) {
        String queryToFindBetween = "SELECT * FROM event WHERE NOT end < '%s' OR start > '%s'";
        final String format = String.format(queryToFindBetween, end, start);
        return jdbcTemplate.query(format, new BeanPropertyRowMapper<>(Event.class));
    }

    @Override
    public void create(Event event) {
        String queryToCreateNewEvent = "INSERT INTO event(color, end, start, text) VALUES(?,?,?,?)";
        jdbcTemplate.update(queryToCreateNewEvent, event.getColor(), event.getEnd(), event.getStart(), event.getText());
    }

    @Override
    public void update(Event event) {
        String queryToUpdate = "UPDATE event SET color = ?, end = ?, start = ?, text = ? WHERE id = ?";
        jdbcTemplate.update(queryToUpdate, event.getColor(), event.getEnd(), event.getStart(), event.getText(), event.getId());
    }

    @Override
    public Event findById(Long id) {
        String queryToFindEventById = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(queryToFindEventById, new BeanPropertyRowMapper<>(Event.class), id);
    }
}
