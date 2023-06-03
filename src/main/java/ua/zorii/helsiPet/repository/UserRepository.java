package ua.zorii.helsiPet.repository;

import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.UserType;

import java.util.List;

public interface UserRepository<T> {
    void addUser(User user, UserDTO userDTO);
    User getUserByUsername(String username);
    User getUserByVerificationCode(String code);
    void updateUserAccountStatus(User user);
    Owner getOwnerInfo(String username);
    Vet getVetInfo(String username);
    void editOwnerProfile(Owner owner);
    void editVetProfile(Vet vet);
    Vet getVetByName(String name);

}
