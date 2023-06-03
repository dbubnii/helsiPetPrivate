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
public class Owner {
    @Id
    private Integer id;
    private String usersUsername;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
