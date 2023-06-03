package ua.zorii.helsiPet.service.impl;

import jakarta.servlet.http.HttpSession;
import net.bytebuddy.utility.RandomString;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.exceptions.UserAlreadyExistException;
import ua.zorii.helsiPet.exceptions.UserNotFoundException;
import ua.zorii.helsiPet.exceptions.WrongPasswordException;
import ua.zorii.helsiPet.repository.UserRepository;
import ua.zorii.helsiPet.service.UserService;
import ua.zorii.helsiPet.util.ConvertorUtil;
import ua.zorii.helsiPet.util.HashPassword;
import ua.zorii.helsiPet.util.MailUtil;

import java.util.List;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserDTO userDTO, String siteUrl) throws UserAlreadyExistException {
        String securedPassword = HashPassword.hashPassword(userDTO.getPassword());

        try {
            userRepository.getUserByUsername(userDTO.getUsername());
        } catch (EmptyResultDataAccessException exception) {
            createNewUser(userDTO, securedPassword, siteUrl);
            return;
        }

        throw new UserAlreadyExistException("Користувач з псевдонімом " + userDTO.getUsername() + " вже існує");
    }

    @Override
    public void getUserByCredentials(String username, String password) throws UserNotFoundException, WrongPasswordException {
        User userFromDB;
        try {
            userFromDB = userRepository.getUserByUsername(username);
        } catch (EmptyResultDataAccessException exception) {
            throw new UserNotFoundException("Користувача з псевдонімом " + username + " не знайдено!");
        }

        final boolean isPasswordValid = HashPassword.checkPassword(password, userFromDB.getPassword());
        if (isPasswordValid) {
            setSessionForValidUser(username, userFromDB);
        } else {
            throw new WrongPasswordException();
        }

        ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public boolean verify(String verificationCode) {
        User user;
        try {
            user = userRepository.getUserByVerificationCode(verificationCode);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepository.updateUserAccountStatus(user);
        setSessionForValidUser(user.getUsername(), user);

        return true;
    }


    @Override
    public void sendVerificationEmail(User user, UserDTO userDTO, String siteUrl) {
        String subject = "Please verify your registration";
        String content = "Dear " + userDTO.getFirstname()
                + ", Please click the link below to verify your registration: "
                + siteUrl + "/registration/verify?code=" + user.getVerificationCode()
                + " Thank you!";

        MailUtil.initializeMessage(userDTO.getEmail(), subject, content);
    }

    @Override
    public User getUserInfoFromUsersTableByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public Owner getOwnerInfo(String username) {
        return userRepository.getOwnerInfo(username);
    }

    @Override
    public Vet getVetInfo(String username) {
        return userRepository.getVetInfo(username);
    }

    @Override
    public void editOwnerProfile(Owner owner) {
        userRepository.editOwnerProfile(owner);
    }

    @Override
    public void editVetProfile(Vet vet) {
        userRepository.editVetProfile(vet);
    }

    @Override
    public Vet getVetByFirstnameAndLastname(String name) {
        return userRepository.getVetByName(name);
    }

    private void createNewUser(UserDTO userDTO, String securePassword, String siteUrl) {
        User createdUser = ConvertorUtil.buildUser(userDTO, securePassword);

        userRepository.addUser(createdUser, userDTO);
        sendVerificationEmail(createdUser, userDTO, siteUrl);
    }

    private void setSessionForValidUser(String username, User userByUsername) {
        ServletRequestAttributes attr = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("user", username);
        session.setAttribute("userId", userByUsername.getId());
        session.setAttribute("userType", userByUsername.getUserType().toString());
    }
}
