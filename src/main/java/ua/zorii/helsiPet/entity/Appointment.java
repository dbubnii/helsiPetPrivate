package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    private Integer id;
    private String vetUsername;
    private String ownerUsername;
    private String ownerPetName;
    private String vetAvailableDate;
    private String details;
    private String status;
    private String receiptId;
}
