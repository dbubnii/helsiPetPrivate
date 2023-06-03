package ua.zorii.helsiPet.service;

import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.exceptions.UserAlreadyExistException;
import ua.zorii.helsiPet.exceptions.UserNotFoundException;
import ua.zorii.helsiPet.exceptions.WrongPasswordException;

public interface UserService {
    void register(UserDTO userDTO, String siteUrl) throws UserAlreadyExistException;

    void getUserByCredentials(String username, String password) throws UserNotFoundException, WrongPasswordException;

    boolean verify(String verificationCode);

    void sendVerificationEmail(User user, UserDTO userDTO, String siteUrl);

    User getUserInfoFromUsersTableByUsername(String username);

    Owner getOwnerInfo(String username);

    Vet getVetInfo(String username);

    void editOwnerProfile(Owner owner);

    void editVetProfile(Vet vet);

    Vet getVetByFirstnameAndLastname(String name);
}
