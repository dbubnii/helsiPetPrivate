package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.UserType;
import ua.zorii.helsiPet.service.UserService;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getProfilePage(Model model) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        final String usernameFromSession = (String) session.getAttribute("user");
        final User userFromDB = userService.getUserInfoFromUsersTableByUsername(usernameFromSession);

        if (userFromDB.getUserType() == UserType.ВЕТЕРИНАР) {
            Vet vetInfo = userService.getVetInfo(usernameFromSession);
            model.addAttribute("userInfo", vetInfo);
        } else {
            Owner ownerInfo = userService.getOwnerInfo(usernameFromSession);
            model.addAttribute("userInfo", ownerInfo);
        }

        model.addAttribute("userType", userFromDB.getUserType());
        return "profile";
    }
}
