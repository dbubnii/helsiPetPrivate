package ua.zorii.helsiPet.repository;

import ua.zorii.helsiPet.entity.Animal;

import java.util.List;

public interface PetRepository {
    List<Animal> getAllPetsForUser(String username);
    Animal getPetById(Integer id);
    List<Animal> getAllPetsByVetId(Integer id);
    Animal getPetByName(String name);
    void addPet(Animal animal);
    Animal getPetByUniqueID(String uniqueID);
    void assignPetToOwner(String ownerUsername, Integer petId);

}
