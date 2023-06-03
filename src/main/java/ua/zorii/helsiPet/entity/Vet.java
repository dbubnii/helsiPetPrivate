package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vet {
    @Id
    private Integer id;
    private Integer clinicId;
    private String usersUsername;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String dateEmployed;
    private String area;
    private String availableAppointmentDate;
}
