package ua.zorii.helsiPet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.zorii.helsiPet.exceptions.UserNotFoundException;
import ua.zorii.helsiPet.exceptions.WrongPasswordException;
import ua.zorii.helsiPet.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String doLogin(@RequestParam String username,
                          @RequestParam String password) throws UserNotFoundException, WrongPasswordException {
        userService.getUserByCredentials(username, password);
        return "home";
    }

}
