package ua.zorii.helsiPet.service;

import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Vaccine;

import java.util.List;

public interface PetService {
    List<Animal> getAllPetsForOwnerByUsername(String username);
    Animal getPetById(Integer id);
    Animal getPetByName(String name);
    void addPet(Animal animal);
    Animal getPetByUniqueID(String uniqueID);
    void assignPetToOwner(String ownerUsername, Integer petId);
    void updateVaccineForPet(String petName, Vaccine vaccine);
    Vaccine getVaccineForPet(String petName);
}
