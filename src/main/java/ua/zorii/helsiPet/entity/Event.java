package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {
    private Integer id;
    private String text;
    private LocalDateTime start;
    private LocalDateTime end;
    private String color;
}
