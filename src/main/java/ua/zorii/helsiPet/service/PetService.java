package ua.zorii.helsiPet.service;

import ua.zorii.helsiPet.entity.Animal;

import java.util.List;

public interface PetService {
    List<Animal> getAllPetsForOwnerByUsername(String username);
    Animal getPetById(Integer id);
    List<Animal> getAllPetsByVetId(Integer id);
    Animal getPetByName(String name);
    void addPet(Animal animal);
    Animal getPetByUniqueID(String uniqueID);
    void assignPetToOwner(String ownerUsername, Integer petId);

}
