package ua.zorii.helsiPet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.Vet;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HistoryDTO {
    private int id;
    private Vet vet;
    private Owner owner;
    private Animal animal;
    private Appointment appointment;
}
