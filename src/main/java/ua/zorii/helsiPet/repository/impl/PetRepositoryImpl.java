package ua.zorii.helsiPet.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.mappers.PetRowMapper;
import ua.zorii.helsiPet.repository.PetRepository;

import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {
    private final JdbcTemplate jdbcTemplate;

    public PetRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Animal> getAllPetsForUser(String username) {
        String queryToGetAllPets = "SELECT id, name, breed, sex, age, size, weight, type, photo, details," +
                "sterilized, owner_username, vet_id, uniqueID FROM animals where owner_username = ?";
        return jdbcTemplate.query(queryToGetAllPets, new PetRowMapper(), username);
    }

    @Override
    public Animal getPetById(Integer id) {
        String queryToGetPetById = "SELECT id, name, breed, sex, age, size, weight, type, photo, details, sterilized," +
                "owner_username, vet_id, uniqueID FROM animals WHERE id = ?";
        return jdbcTemplate.queryForObject(queryToGetPetById, new PetRowMapper(), id);
    }

    @Override
    public List<Animal> getAllPetsByVetId(Integer id) {
        String queryToGetAllPetsByVetId = "SELECT * FROM animals WHERE vet_id = ?";
        return jdbcTemplate.query(queryToGetAllPetsByVetId, new PetRowMapper(), id);
    }

    @Override
    public Animal getPetByName(String name) {
        String queryToGetPetByName = "SELECT * FROM animals WHERE name = ?";
        return jdbcTemplate.queryForObject(queryToGetPetByName, new PetRowMapper(), name);
    }

    @Override
    public void addPet(Animal animal) {
        String queryToAddPet = "INSERT INTO animals(name, breed, sex, age, size, weight, type, photo," +
                "details, sterilized, owner_username, vet_id, uniqueID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(queryToAddPet, animal.getName(), animal.getBreed(), animal.getSex().toString(), animal.getAge(),
                animal.getSize().toString(), animal.getWeight(), animal.getType().toString(), animal.getPhoto(), animal.getDetails(),
                animal.getSterilized(), animal.getOwnerUsername(), animal.getVetId(), animal.getUniqueID());
    }

    @Override
    public Animal getPetByUniqueID(String uniqueID) {
        String queryToGetPetByUniqueID = "SELECT * FROM animals WHERE uniqueID = ?";
        final Animal animal;
        try {
            animal = jdbcTemplate.queryForObject(queryToGetPetByUniqueID, new PetRowMapper(), uniqueID);
        } catch (DataAccessException e) {
            return null;
        }
        return animal;
    }

    @Override
    public void assignPetToOwner(String ownerUsername, Integer petId) {
        String queryToAssignPetToOwner = "UPDATE animals SET owner_username = ? WHERE id = ?";
        jdbcTemplate.update(queryToAssignPetToOwner, ownerUsername, petId);
    }
}
