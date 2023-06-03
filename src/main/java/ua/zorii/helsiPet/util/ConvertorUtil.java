package ua.zorii.helsiPet.util;

import net.bytebuddy.utility.RandomString;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;

public class ConvertorUtil {
    public static Owner buildOwner(String firstName, String lastName, String email, String phoneNumber, String username) {
        return Owner.builder()
                .usersUsername(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static Vet buildVet(String firstName, String lastName, String email, String phoneNumber, String dateEmployed, String area, String username) {
        return Vet.builder()
                .usersUsername(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .dateEmployed(dateEmployed)
                .area(area)
                .build();
    }

    public static User buildUser(UserDTO userDTO, String securePassword) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(securePassword)
                .userType(userDTO.getUserType())
                .verificationCode(RandomString.make(64))
                .enabled(false)
                .build();
    }
}
