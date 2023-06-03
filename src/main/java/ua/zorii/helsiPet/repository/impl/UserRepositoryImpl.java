package ua.zorii.helsiPet.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.UserType;
import ua.zorii.helsiPet.mappers.OwnerRowMapper;
import ua.zorii.helsiPet.mappers.VetRowMapper;
import ua.zorii.helsiPet.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user, UserDTO userDTO) {
        String queryForCreateNewUser;
        if (user.getUserType() == UserType.ВЕТЕРИНАР) {
            queryForCreateNewUser = "INSERT INTO vets(users_username, firstName, lastName," +
                    "phoneNumber, email, dateEmployed, area) VALUES(?,?,?,?,?,?,?)";
            jdbcTemplate.update(queryForCreateNewUser, user.getUsername(), userDTO.getFirstname(), userDTO.getLastname(),
                    userDTO.getPhoneNumber(), userDTO.getEmail(), userDTO.getDateEmployed(), userDTO.getArea());
        } else {
            queryForCreateNewUser = "INSERT INTO owners(users_username, firstName, lastName," +
                    "phoneNumber, email) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(queryForCreateNewUser, user.getUsername(), userDTO.getFirstname(), userDTO.getLastname(),
                    userDTO.getPhoneNumber(), userDTO.getEmail());
        }

        String queryForAddingNewUser = "INSERT INTO users(password, username, " +
                "userType, verificationCode, enabled) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(queryForAddingNewUser,
                user.getPassword(), user.getUsername(), user.getUserType().toString(),
                user.getVerificationCode(), user.getEnabled());

    }


    @Override
    public User getUserByUsername(String username) {
        final String queryToGetUserByUsername =
                """
                        SELECT id, password, username, userType, verificationCode, enabled FROM users WHERE username = ?
                        """;
        return jdbcTemplate.queryForObject(queryToGetUserByUsername, new BeanPropertyRowMapper<>(User.class), username);
    }

    //TODO
    @Override
    public User getUserByVerificationCode(String code) {
        String queryForGettingUserByVerificationCode = "SELECT id, username, userType, password, " +
                " verificationCode, enabled FROM users WHERE verificationCode = ?";
        return jdbcTemplate.queryForObject(queryForGettingUserByVerificationCode, new BeanPropertyRowMapper<>(User.class), code);
    }

    @Override
    public void updateUserAccountStatus(User user) {
        String queryForUpdateUser = "UPDATE users SET verificationCode = ?, enabled = ? WHERE id = ?";
        jdbcTemplate.update(queryForUpdateUser, user.getVerificationCode(), user.getEnabled(), user.getId());
    }

    @Override
    public Owner getOwnerInfo(String username) {
        String queryToGetUserInfoBasedOnType = "SELECT id, firstName, lastName, phoneNumber, email, users_username FROM owners" +
                " WHERE users_username = ?";
        return jdbcTemplate.queryForObject(queryToGetUserInfoBasedOnType, new OwnerRowMapper(), username);
    }

    @Override
    public Vet getVetInfo(String username) {
        String queryToGetUserInfoBasedOnType = "SELECT id, firstName, lastName, phoneNumber," +
                "email, dateEmployed, area, clinicId, users_username, availableAppointmentDate FROM vets WHERE users_username = ?";
        return jdbcTemplate.queryForObject(queryToGetUserInfoBasedOnType, new BeanPropertyRowMapper<>(Vet.class), username);
    }

    @Override
    public void editOwnerProfile(Owner owner) {
        String queryToUpdate = "UPDATE owners SET firstName = ?, lastName = ?, phoneNumber = ?, email = ? WHERE users_username = ?";
        jdbcTemplate.update(queryToUpdate, owner.getFirstName(), owner.getLastName(), owner.getPhoneNumber(), owner.getEmail(), owner.getUsersUsername());
    }

    @Override
    public void editVetProfile(Vet vet) {
        String queryToUpdate = "UPDATE vets SET firstName, lastName, phoneNumber, email, dateEmployed, area, clinicId, availableAppointmentDate WHERE users_username = ?";
        jdbcTemplate.update(queryToUpdate, vet.getFirstName(), vet.getLastName(), vet.getPhoneNumber(), vet.getEmail(),
                vet.getDateEmployed(), vet.getArea(), vet.getUsersUsername());
    }

    @Override
    public Vet getVetByName(String name) {
        final String[] firstNameAndLastName = name.split(" ");
        final String firstName = firstNameAndLastName[0];
        final String lastName = firstNameAndLastName[1];

        String queryToFindUserByName = "SELECT * FROM vets WHERE firstName = ? AND lastName = ?";
        return jdbcTemplate.queryForObject(queryToFindUserByName, new VetRowMapper(), firstName, lastName);
    }

}
