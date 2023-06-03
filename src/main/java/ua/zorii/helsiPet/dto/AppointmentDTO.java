package ua.zorii.helsiPet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.RequestStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private Integer id;
    private Vet vet;
    private Owner owner;
    private Animal animal;
    private String vetAvailableDate;
    private String details;
    private String status;
    private String receiptId;
}
