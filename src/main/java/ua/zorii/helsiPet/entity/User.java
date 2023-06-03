package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ua.zorii.helsiPet.enums.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String verificationCode;
    private Boolean enabled;
    private UserType userType;
}
