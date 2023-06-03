package ua.zorii.helsiPet.service.impl;

import org.springframework.stereotype.Service;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.repository.PetRepository;
import ua.zorii.helsiPet.service.PetService;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Animal> getAllPetsForOwnerByUsername(String username) {
        return petRepository.getAllPetsForUser(username);
    }

    @Override
    public Animal getPetById(Integer id) {
        return petRepository.getPetById(id);
    }

    @Override
    public List<Animal> getAllPetsByVetId(Integer id) {
        return petRepository.getAllPetsByVetId(id);
    }

    @Override
    public Animal getPetByName(String name) {
        return petRepository.getPetByName(name);
    }

    @Override
    public void addPet(Animal animal) {
        petRepository.addPet(animal);
    }

    @Override
    public Animal getPetByUniqueID(String uniqueID) {
        return petRepository.getPetByUniqueID(uniqueID);
    }

    @Override
    public void assignPetToOwner(String ownerUsername, Integer petId) {
        petRepository.assignPetToOwner(ownerUsername, petId);
    }

}
