package ua.zorii.helsiPet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.zorii.helsiPet.enums.UserType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private UserType userType;
    private String phoneNumber;
    private String dateEmployed;
    private String area;
}
