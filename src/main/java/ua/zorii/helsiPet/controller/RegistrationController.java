package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.exceptions.InvalidVerificationCodeException;
import ua.zorii.helsiPet.exceptions.NotEqualPasswordException;
import ua.zorii.helsiPet.exceptions.UserAlreadyExistException;
import ua.zorii.helsiPet.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String doRegister(UserDTO userDTO, HttpServletRequest request)
            throws UserAlreadyExistException, NotEqualPasswordException {

        if (!userDTO.getPassword().equalsIgnoreCase(userDTO.getPasswordConfirm())) {
            throw new NotEqualPasswordException();
        }

        userService.register(userDTO, getSiteURL(request));
        return "verify";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) throws InvalidVerificationCodeException {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            throw new InvalidVerificationCodeException("Невірний код перевірки!");
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


}
