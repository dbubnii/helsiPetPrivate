package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.UserType;
import ua.zorii.helsiPet.service.UserService;
import ua.zorii.helsiPet.util.ConvertorUtil;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/edit")
    public String editProfile(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String email,
                            @RequestParam String phoneNumber,
                            @RequestParam(required = false) String dateEmployed,
                            @RequestParam(required = false) String area) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        final String username = (String) session.getAttribute("user");

        final User userByUsername = userService.getUserInfoFromUsersTableByUsername(username);

        if (userByUsername.getUserType() == UserType.ВЕТЕРИНАР) {
            final Vet userToUpdate =
                    ConvertorUtil.buildVet(firstName, lastName, email, phoneNumber, dateEmployed, area, username);
            userService.editVetProfile(userToUpdate);
        } else {
            final Owner userToUpdate = ConvertorUtil.buildOwner(firstName, lastName, email, phoneNumber, username);
            userService.editOwnerProfile(userToUpdate);
        }
        return "redirect:/";
    }

    @PostMapping
    public String getUser(@RequestParam String name, Model model) {
        final Vet vetByName = userService.getVetByFirstnameAndLastname(name);
        model.addAttribute("vet", vetByName);
        return "vetProfile";
    }


}
