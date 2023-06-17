package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vaccine {
    private Integer id;
    private String purpose;
    private String vaccineName;
    private String expiration;
    private String vaccineDoze;
    private String uuid;
    private String petName;
}
